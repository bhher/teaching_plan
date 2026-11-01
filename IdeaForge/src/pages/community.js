import { layout, bindLayoutEvents } from '../ui/layout.js';
import { supabase } from '../lib/supabaseClient.js';
import { getAuthState, isLoggedIn, isActive } from '../lib/auth.js';
import { escapeHtml } from '../lib/markdown.js';

async function fetchPosts() {
  const { data, error } = await supabase
    .from('posts')
    .select('id, title, body, author_id, created_at, profiles(nickname)')
    .order('created_at', { ascending: false })
    .limit(50);
  if (error) throw Object.assign(new Error(error.message), { code: 'QUERY_ERROR' });
  return data || [];
}

export async function renderCommunity(root) {
  root.innerHTML = layout(`<div class="card"><p class="muted">불러오는 중…</p></div>`, { active: 'community' });
  bindLayoutEvents(root);

  if (!supabase) {
    root.innerHTML = layout(`<div class="alert error">Supabase 설정이 필요합니다.</div>`, { active: 'community' });
    bindLayoutEvents(root);
    return;
  }

  try {
    const posts = await fetchPosts();
    const me = getAuthState().session?.user?.id;
    root.innerHTML = layout(
      `
      <div class="stack">
        <div style="display:flex;justify-content:space-between;gap:12px;align-items:center;flex-wrap:wrap">
          <h2 class="page-title" style="margin:0">커뮤니티</h2>
          ${isLoggedIn() && isActive() ? `<a class="btn" href="#/community/new">글쓰기</a>` : `<a class="btn btn-secondary" href="#/login">로그인 후 글쓰기</a>`}
        </div>
        <div id="msg"></div>
        ${
          posts.length
            ? posts
                .map(
                  (p) => `
            <article class="card list-card">
              <a href="#/community/${p.id}"><strong>${escapeHtml(p.title)}</strong></a>
              <div class="meta">${escapeHtml(p.profiles?.nickname || '회원')} · ${new Date(p.created_at).toLocaleString('ko-KR')}</div>
              <p>${escapeHtml(p.body.slice(0, 120))}${p.body.length > 120 ? '…' : ''}</p>
            </article>`
                )
                .join('')
            : `<div class="card muted">아직 게시글이 없습니다.</div>`
        }
      </div>
    `,
      { active: 'community' }
    );
    bindLayoutEvents(root);
  } catch (err) {
    root.innerHTML = layout(
      `<div class="alert error">조회 오류입니다. (빈 목록/비활성/로그아웃으로 표시하지 않음) ${escapeHtml(err.message)}</div>`,
      { active: 'community' }
    );
    bindLayoutEvents(root);
  }
}

export function renderCommunityNew(root) {
  if (!isLoggedIn() || !isActive()) {
    location.hash = '#/login';
    return;
  }
  root.innerHTML = layout(
    `
    <div class="card">
      <h2 class="page-title">글쓰기</h2>
      <div id="msg"></div>
      <form class="form-grid" id="post-form">
        <label>제목<input name="title" required maxlength="120" /></label>
        <label>내용<textarea name="body" rows="8" required></textarea></label>
        <button class="btn" type="submit">등록</button>
      </form>
    </div>
  `,
    { active: 'community' }
  );
  bindLayoutEvents(root);
  root.querySelector('#post-form').addEventListener('submit', async (e) => {
    e.preventDefault();
    const fd = new FormData(e.target);
    const btn = e.target.querySelector('button');
    btn.disabled = true;
    const me = getAuthState().session.user.id;
    const { data, error } = await supabase
      .from('posts')
      .insert({
        author_id: me,
        title: String(fd.get('title')),
        body: String(fd.get('body')),
      })
      .select('id')
      .single();
    btn.disabled = false;
    if (error) {
      root.querySelector('#msg').innerHTML = `<div class="alert error">${escapeHtml(error.message)}</div>`;
      return;
    }
    location.hash = `#/community/${data.id}`;
  });
}

export async function renderCommunityDetail(root, id) {
  root.innerHTML = layout(`<div class="card muted">불러오는 중…</div>`, { active: 'community' });
  bindLayoutEvents(root);

  const { data: post, error } = await supabase
    .from('posts')
    .select('id, title, body, author_id, created_at, profiles(nickname)')
    .eq('id', id)
    .maybeSingle();

  if (error) {
    root.innerHTML = layout(`<div class="alert error">조회 오류: ${escapeHtml(error.message)}</div>`, { active: 'community' });
    bindLayoutEvents(root);
    return;
  }
  if (!post) {
    root.innerHTML = layout(`<div class="alert">게시글을 찾을 수 없습니다.</div>`, { active: 'community' });
    bindLayoutEvents(root);
    return;
  }

  const { data: comments, error: cerr } = await supabase
    .from('comments')
    .select('id, body, author_id, created_at, profiles(nickname)')
    .eq('post_id', id)
    .order('created_at', { ascending: true });

  const me = getAuthState().session?.user?.id;
  const isOwner = me && me === post.author_id;
  const canComment = isLoggedIn() && isActive() && me && me !== post.author_id;

  root.innerHTML = layout(
    `
    <div class="stack">
      <article class="card">
        <h2 class="page-title">${escapeHtml(post.title)}</h2>
        <div class="meta muted">${escapeHtml(post.profiles?.nickname || '')} · ${new Date(post.created_at).toLocaleString('ko-KR')}</div>
        <p style="white-space:pre-wrap;margin-top:14px">${escapeHtml(post.body)}</p>
        ${
          isOwner
            ? `<div class="actions-row">
                <button class="btn btn-secondary" type="button" id="edit-post">수정</button>
                <button class="btn btn-danger" type="button" id="del-post">삭제</button>
              </div>`
            : ''
        }
      </article>

      <section class="card">
        <h3>댓글</h3>
        <div id="msg"></div>
        ${
          cerr
            ? `<div class="alert error">댓글 조회 오류: ${escapeHtml(cerr.message)}</div>`
            : (comments || [])
                .map(
                  (c) => `
              <div class="list-card" style="padding:10px 0;border-bottom:1px solid var(--line)">
                <div class="meta">${escapeHtml(c.profiles?.nickname || '')} · ${new Date(c.created_at).toLocaleString('ko-KR')}</div>
                <div style="white-space:pre-wrap">${escapeHtml(c.body)}</div>
                ${
                  me === c.author_id
                    ? `<div class="actions-row">
                        <button class="btn btn-ghost" data-edit-comment="${c.id}">수정</button>
                        <button class="btn btn-ghost" data-del-comment="${c.id}">삭제</button>
                      </div>`
                    : ''
                }
              </div>`
                )
                .join('') || `<p class="muted">댓글이 없습니다.</p>`
        }

        ${
          !isLoggedIn()
            ? `<div class="alert">로그인 후 댓글을 작성할 수 있습니다. <a href="#/login">로그인</a></div>`
            : isOwner
              ? `<div class="alert">본인 글에는 댓글을 작성할 수 없습니다. (기존 댓글은 조회 가능)</div>`
              : canComment
                ? `<form class="form-grid" id="comment-form" style="margin-top:12px">
                    <label>댓글<textarea name="body" rows="3" required></textarea></label>
                    <button class="btn" type="submit">댓글 등록</button>
                  </form>`
                : `<div class="alert error">활성 회원만 댓글을 작성할 수 있습니다.</div>`
        }
      </section>
      <a href="#/community">← 목록</a>
    </div>
  `,
    { active: 'community' }
  );
  bindLayoutEvents(root);

  root.querySelector('#del-post')?.addEventListener('click', async () => {
    if (!confirm('게시글을 삭제할까요?')) return;
    const { error: derr } = await supabase.from('posts').delete().eq('id', id);
    if (derr) alert(derr.message);
    else location.hash = '#/community';
  });

  root.querySelector('#edit-post')?.addEventListener('click', async () => {
    const title = prompt('제목', post.title);
    const body = prompt('내용', post.body);
    if (title == null || body == null) return;
    const { error: uerr } = await supabase
      .from('posts')
      .update({ title, body, updated_at: new Date().toISOString() })
      .eq('id', id);
    if (uerr) alert(uerr.message);
    else renderCommunityDetail(root, id);
  });

  root.querySelector('#comment-form')?.addEventListener('submit', async (e) => {
    e.preventDefault();
    const fd = new FormData(e.target);
    const btn = e.target.querySelector('button');
    btn.disabled = true;
    const { error: ierr } = await supabase.from('comments').insert({
      post_id: id,
      author_id: me,
      body: String(fd.get('body')),
    });
    btn.disabled = false;
    if (ierr) {
      root.querySelector('#msg').innerHTML = `<div class="alert error">${escapeHtml(ierr.message)}</div>`;
      return;
    }
    renderCommunityDetail(root, id);
  });

  root.querySelectorAll('[data-del-comment]').forEach((btn) => {
    btn.addEventListener('click', async () => {
      const cid = btn.getAttribute('data-del-comment');
      const { error: derr } = await supabase.from('comments').delete().eq('id', cid);
      if (derr) alert(derr.message);
      else renderCommunityDetail(root, id);
    });
  });

  root.querySelectorAll('[data-edit-comment]').forEach((btn) => {
    btn.addEventListener('click', async () => {
      const cid = btn.getAttribute('data-edit-comment');
      const cur = (comments || []).find((c) => c.id === cid);
      const body = prompt('댓글 수정', cur?.body || '');
      if (body == null) return;
      const { error: uerr } = await supabase
        .from('comments')
        .update({ body, updated_at: new Date().toISOString() })
        .eq('id', cid);
      if (uerr) alert(uerr.message);
      else renderCommunityDetail(root, id);
    });
  });
}
