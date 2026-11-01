-- IdeaForge initial schema, RLS, grants, triggers

create extension if not exists "pgcrypto";

-- profiles
create table if not exists public.profiles (
  id uuid primary key references auth.users(id) on delete cascade,
  email text not null,
  nickname text not null,
  real_name text,
  role text not null default 'member' check (role in ('member', 'admin')),
  status text not null default 'active' check (status in ('active', 'inactive')),
  created_at timestamptz not null default now(),
  updated_at timestamptz not null default now()
);

-- usage_daily (KST date stored as date)
create table if not exists public.usage_daily (
  user_id uuid not null references public.profiles(id) on delete cascade,
  usage_date date not null,
  title_count int not null default 0 check (title_count >= 0),
  outline_count int not null default 0 check (outline_count >= 0),
  primary key (user_id, usage_date)
);

-- app_settings
create table if not exists public.app_settings (
  key text primary key,
  value jsonb not null,
  updated_at timestamptz not null default now(),
  updated_by uuid references public.profiles(id)
);

insert into public.app_settings (key, value)
values
  ('daily_title_limit', '10'::jsonb),
  ('daily_outline_limit', '5'::jsonb)
on conflict (key) do nothing;

-- saved_ideas
create table if not exists public.saved_ideas (
  id uuid primary key default gen_random_uuid(),
  user_id uuid not null references public.profiles(id) on delete cascade,
  keywords text[] not null default '{}',
  audience text not null default '',
  post_type text not null default '',
  tone text not null default '',
  selected_title text not null,
  outline jsonb not null,
  created_at timestamptz not null default now()
);

-- posts
create table if not exists public.posts (
  id uuid primary key default gen_random_uuid(),
  author_id uuid not null references public.profiles(id) on delete cascade,
  title text not null,
  body text not null,
  created_at timestamptz not null default now(),
  updated_at timestamptz not null default now()
);

-- comments
create table if not exists public.comments (
  id uuid primary key default gen_random_uuid(),
  post_id uuid not null references public.posts(id) on delete cascade,
  author_id uuid not null references public.profiles(id) on delete cascade,
  body text not null,
  created_at timestamptz not null default now(),
  updated_at timestamptz not null default now()
);

-- admin_audit_logs
create table if not exists public.admin_audit_logs (
  id uuid primary key default gen_random_uuid(),
  actor_id uuid references public.profiles(id),
  action text not null,
  target text,
  before jsonb,
  after jsonb,
  created_at timestamptz not null default now()
);

-- generation_jobs (idempotency helper, optional short-lived)
create table if not exists public.generation_jobs (
  id uuid primary key default gen_random_uuid(),
  user_id uuid not null references public.profiles(id) on delete cascade,
  kind text not null check (kind in ('title', 'outline')),
  request_hash text not null,
  status text not null default 'pending',
  created_at timestamptz not null default now(),
  unique (user_id, kind, request_hash)
);

-- helper: KST today
create or replace function public.kst_today()
returns date
language sql
stable
as $$
  select (timezone('Asia/Seoul', now()))::date;
$$;

-- auto profile on signup
create or replace function public.handle_new_user()
returns trigger
language plpgsql
security definer
set search_path = public
as $$
begin
  insert into public.profiles (id, email, nickname, real_name, role, status)
  values (
    new.id,
    coalesce(new.email, ''),
    coalesce(new.raw_user_meta_data->>'nickname', split_part(coalesce(new.email, 'user'), '@', 1)),
    nullif(new.raw_user_meta_data->>'real_name', ''),
    'member',
    'active'
  );
  return new;
end;
$$;

drop trigger if exists on_auth_user_created on auth.users;
create trigger on_auth_user_created
  after insert on auth.users
  for each row execute function public.handle_new_user();

-- block comments on own posts + force author_id
create or replace function public.enforce_comment_rules()
returns trigger
language plpgsql
security definer
set search_path = public
as $$
declare
  post_author uuid;
begin
  if auth.uid() is null then
    raise exception 'AUTH_REQUIRED';
  end if;

  new.author_id := auth.uid();

  select author_id into post_author from public.posts where id = new.post_id;
  if post_author is null then
    raise exception 'NOT_FOUND';
  end if;
  if post_author = auth.uid() then
    raise exception 'FORBIDDEN_OWN_POST_COMMENT';
  end if;

  if not exists (
    select 1 from public.profiles p
    where p.id = auth.uid() and p.status = 'active'
  ) then
    raise exception 'AUTH_INACTIVE';
  end if;

  return new;
end;
$$;

drop trigger if exists trg_enforce_comment_rules on public.comments;
create trigger trg_enforce_comment_rules
  before insert on public.comments
  for each row execute function public.enforce_comment_rules();

-- consume usage atomically; returns new counts + limits
create or replace function public.consume_usage(p_kind text)
returns jsonb
language plpgsql
security definer
set search_path = public
as $$
declare
  uid uuid := auth.uid();
  d date := public.kst_today();
  title_limit int;
  outline_limit int;
  cur_title int;
  cur_outline int;
  st text;
begin
  if uid is null then
    raise exception 'AUTH_REQUIRED';
  end if;

  select status into st from public.profiles where id = uid;
  if st is distinct from 'active' then
    raise exception 'AUTH_INACTIVE';
  end if;

  select (value#>>'{}')::int into title_limit from public.app_settings where key = 'daily_title_limit';
  select (value#>>'{}')::int into outline_limit from public.app_settings where key = 'daily_outline_limit';
  title_limit := coalesce(title_limit, 10);
  outline_limit := coalesce(outline_limit, 5);

  insert into public.usage_daily (user_id, usage_date, title_count, outline_count)
  values (uid, d, 0, 0)
  on conflict (user_id, usage_date) do nothing;

  select title_count, outline_count into cur_title, cur_outline
  from public.usage_daily
  where user_id = uid and usage_date = d
  for update;

  if p_kind = 'title' then
    if cur_title >= title_limit then
      raise exception 'LIMIT_EXCEEDED';
    end if;
    update public.usage_daily
      set title_count = title_count + 1
      where user_id = uid and usage_date = d
      returning title_count, outline_count into cur_title, cur_outline;
  elsif p_kind = 'outline' then
    if cur_outline >= outline_limit then
      raise exception 'LIMIT_EXCEEDED';
    end if;
    update public.usage_daily
      set outline_count = outline_count + 1
      where user_id = uid and usage_date = d
      returning title_count, outline_count into cur_title, cur_outline;
  else
    raise exception 'VALIDATION';
  end if;

  return jsonb_build_object(
    'title_count', cur_title,
    'outline_count', cur_outline,
    'title_limit', title_limit,
    'outline_limit', outline_limit,
    'usage_date', d
  );
end;
$$;

create or replace function public.refund_usage(p_kind text)
returns void
language plpgsql
security definer
set search_path = public
as $$
declare
  uid uuid := auth.uid();
  d date := public.kst_today();
begin
  if uid is null then
    return;
  end if;
  if p_kind = 'title' then
    update public.usage_daily
      set title_count = greatest(title_count - 1, 0)
      where user_id = uid and usage_date = d;
  elsif p_kind = 'outline' then
    update public.usage_daily
      set outline_count = greatest(outline_count - 1, 0)
      where user_id = uid and usage_date = d;
  end if;
end;
$$;

create or replace function public.get_usage()
returns jsonb
language plpgsql
security definer
set search_path = public
as $$
declare
  uid uuid := auth.uid();
  d date := public.kst_today();
  title_limit int;
  outline_limit int;
  cur_title int := 0;
  cur_outline int := 0;
begin
  if uid is null then
    raise exception 'AUTH_REQUIRED';
  end if;

  select (value#>>'{}')::int into title_limit from public.app_settings where key = 'daily_title_limit';
  select (value#>>'{}')::int into outline_limit from public.app_settings where key = 'daily_outline_limit';
  title_limit := coalesce(title_limit, 10);
  outline_limit := coalesce(outline_limit, 5);

  select coalesce(title_count, 0), coalesce(outline_count, 0)
    into cur_title, cur_outline
  from public.usage_daily
  where user_id = uid and usage_date = d;

  return jsonb_build_object(
    'title_count', coalesce(cur_title, 0),
    'outline_count', coalesce(cur_outline, 0),
    'title_limit', title_limit,
    'outline_limit', outline_limit,
    'usage_date', d
  );
end;
$$;

-- RLS
alter table public.profiles enable row level security;
alter table public.usage_daily enable row level security;
alter table public.app_settings enable row level security;
alter table public.saved_ideas enable row level security;
alter table public.posts enable row level security;
alter table public.comments enable row level security;
alter table public.admin_audit_logs enable row level security;
alter table public.generation_jobs enable row level security;

-- profiles policies (닉네임 표시용: anon도 조회 가능)
create policy profiles_select_public on public.profiles
  for select to anon, authenticated
  using (true);

create policy profiles_update_own on public.profiles
  for update to authenticated
  using (id = auth.uid())
  with check (
    id = auth.uid()
    and role = (select role from public.profiles p where p.id = auth.uid())
    and status = (select status from public.profiles p where p.id = auth.uid())
  );

-- usage
create policy usage_select_own on public.usage_daily
  for select to authenticated
  using (user_id = auth.uid());

-- settings readable by authenticated
create policy settings_select on public.app_settings
  for select to authenticated
  using (true);

-- saved ideas
create policy saved_select_own on public.saved_ideas
  for select to authenticated using (user_id = auth.uid());
create policy saved_insert_own on public.saved_ideas
  for insert to authenticated with check (user_id = auth.uid());
create policy saved_delete_own on public.saved_ideas
  for delete to authenticated using (user_id = auth.uid());

-- posts
create policy posts_select_all on public.posts
  for select to anon, authenticated using (true);
create policy posts_insert_own on public.posts
  for insert to authenticated with check (
    author_id = auth.uid()
    and exists (select 1 from public.profiles p where p.id = auth.uid() and p.status = 'active')
  );
create policy posts_update_own on public.posts
  for update to authenticated using (author_id = auth.uid()) with check (author_id = auth.uid());
create policy posts_delete_own on public.posts
  for delete to authenticated using (author_id = auth.uid());

-- comments
create policy comments_select_all on public.comments
  for select to anon, authenticated using (true);
create policy comments_insert_active on public.comments
  for insert to authenticated with check (
    author_id = auth.uid()
    and exists (select 1 from public.profiles p where p.id = auth.uid() and p.status = 'active')
  );
create policy comments_update_own on public.comments
  for update to authenticated using (author_id = auth.uid()) with check (author_id = auth.uid());
create policy comments_delete_own on public.comments
  for delete to authenticated using (author_id = auth.uid());

-- generation_jobs own
create policy jobs_own on public.generation_jobs
  for all to authenticated
  using (user_id = auth.uid())
  with check (user_id = auth.uid());

-- admin_audit: no direct client access (service_role only by default)
revoke all on public.admin_audit_logs from anon, authenticated;

grant usage on schema public to anon, authenticated;
grant select on public.posts, public.comments to anon, authenticated;
grant select on public.profiles to anon, authenticated;
grant update on public.profiles to authenticated;
grant select on public.app_settings to authenticated;
grant select on public.usage_daily to authenticated;
grant select, insert, delete on public.saved_ideas to authenticated;
grant select, insert, update, delete on public.posts to authenticated;
grant select, insert, update, delete on public.comments to authenticated;
grant select, insert, update, delete on public.generation_jobs to authenticated;
grant execute on function public.consume_usage(text) to authenticated;
grant execute on function public.refund_usage(text) to authenticated;
grant execute on function public.get_usage() to authenticated;
grant execute on function public.kst_today() to authenticated, anon;

create index if not exists idx_posts_created on public.posts (created_at desc);
create index if not exists idx_comments_post on public.comments (post_id, created_at);
create index if not exists idx_saved_user on public.saved_ideas (user_id, created_at desc);
create index if not exists idx_profiles_email on public.profiles (email);
