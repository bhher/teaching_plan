import express from 'express';
import cors from 'cors';
import path from 'path';
import { fileURLToPath } from 'url';
import { env } from './env.js';
import { generateTitles, generateOutline } from './gemini.js';
import {
  getAuthUser,
  getActiveProfile,
  requireAdmin,
  supabaseAsUser,
  supabaseAdmin,
} from './supabase.js';

const __dirname = path.dirname(fileURLToPath(import.meta.url));
const app = express();
app.use(cors());
app.use(express.json({ limit: '1mb' }));

function sendError(res, err) {
  const code = err.code || 'QUERY_ERROR';
  const status = err.status || 500;
  console.error('[api]', code, err.message, err.cause || '');
  res.status(status).json({ error: code, message: err.message });
}

function parseKeywords(input) {
  const list = Array.isArray(input)
    ? input
    : String(input || '')
        .split(/[,\n]/)
        .map((s) => s.trim())
        .filter(Boolean);
  const uniq = [...new Set(list.map((k) => k.slice(0, 30)))].slice(0, 10);
  return uniq;
}

app.get('/api/health', (_req, res) => {
  res.json({
    ok: true,
    name: 'IdeaForge',
    hasGemini: Boolean(env.geminiKey),
    hasSupabase: Boolean(env.supabaseUrl && env.supabaseService),
  });
});

app.get('/api/usage', async (req, res) => {
  try {
    const { token } = await getAuthUser(req);
    await getActiveProfile((await getAuthUser(req)).user.id);
    const sb = supabaseAsUser(token);
    const { data, error } = await sb.rpc('get_usage');
    if (error) throw Object.assign(new Error(error.message), { code: 'QUERY_ERROR', status: 500 });
    res.json(data);
  } catch (err) {
    sendError(res, err);
  }
});

app.post('/api/generate/titles', async (req, res) => {
  let consumed = false;
  let token;
  try {
    const auth = await getAuthUser(req);
    token = auth.token;
    await getActiveProfile(auth.user.id);
    const keywords = parseKeywords(req.body.keywords);
    const audience = String(req.body.audience || '').slice(0, 80);
    const postType = String(req.body.postType || req.body.post_type || '').slice(0, 80);
    const tone = String(req.body.tone || '').slice(0, 80);
    if (!keywords.length) {
      return res.status(400).json({ error: 'VALIDATION', message: '키워드를 1개 이상 입력하세요.' });
    }

    const sb = supabaseAsUser(token);
    const { data: usage, error: uerr } = await sb.rpc('consume_usage', { p_kind: 'title' });
    if (uerr) {
      const msg = uerr.message || '';
      if (msg.includes('LIMIT_EXCEEDED')) {
        return res.status(429).json({ error: 'LIMIT_EXCEEDED', message: '오늘 제목 생성 한도를 초과했습니다.' });
      }
      if (msg.includes('AUTH_INACTIVE')) {
        return res.status(403).json({ error: 'AUTH_INACTIVE', message: '정지된 계정입니다.' });
      }
      throw Object.assign(new Error(uerr.message), { code: 'QUERY_ERROR', status: 500 });
    }
    consumed = true;

    const titles = await generateTitles({ keywords, audience, postType, tone });
    res.json({ titles, usage });
  } catch (err) {
    if (consumed && token) {
      try {
        await supabaseAsUser(token).rpc('refund_usage', { p_kind: 'title' });
      } catch (_) {
        /* ignore */
      }
    }
    sendError(res, err);
  }
});

app.post('/api/generate/outline', async (req, res) => {
  let consumed = false;
  let token;
  try {
    const auth = await getAuthUser(req);
    token = auth.token;
    await getActiveProfile(auth.user.id);
    const keywords = parseKeywords(req.body.keywords);
    const audience = String(req.body.audience || '').slice(0, 80);
    const postType = String(req.body.postType || req.body.post_type || '').slice(0, 80);
    const tone = String(req.body.tone || '').slice(0, 80);
    const title = String(req.body.title || '').trim();
    if (!title || !keywords.length) {
      return res.status(400).json({ error: 'VALIDATION', message: '제목과 키워드가 필요합니다.' });
    }

    const sb = supabaseAsUser(token);
    const { data: usage, error: uerr } = await sb.rpc('consume_usage', { p_kind: 'outline' });
    if (uerr) {
      const msg = uerr.message || '';
      if (msg.includes('LIMIT_EXCEEDED')) {
        return res.status(429).json({ error: 'LIMIT_EXCEEDED', message: '오늘 글 구성 생성 한도를 초과했습니다.' });
      }
      throw Object.assign(new Error(uerr.message), { code: 'QUERY_ERROR', status: 500 });
    }
    consumed = true;

    const outline = await generateOutline({ keywords, audience, postType, tone, title });
    res.json({ outline, usage });
  } catch (err) {
    if (consumed && token) {
      try {
        await supabaseAsUser(token).rpc('refund_usage', { p_kind: 'outline' });
      } catch (_) {
        /* ignore */
      }
    }
    sendError(res, err);
  }
});

app.post('/api/ideas', async (req, res) => {
  try {
    const { user, token } = await getAuthUser(req);
    await getActiveProfile(user.id);
    const keywords = parseKeywords(req.body.keywords);
    const row = {
      user_id: user.id,
      keywords,
      audience: String(req.body.audience || '').slice(0, 80),
      post_type: String(req.body.postType || req.body.post_type || '').slice(0, 80),
      tone: String(req.body.tone || '').slice(0, 80),
      selected_title: String(req.body.selectedTitle || req.body.selected_title || '').trim(),
      outline: req.body.outline,
    };
    if (!row.selected_title || !row.outline) {
      return res.status(400).json({ error: 'VALIDATION', message: '제목과 글 구성이 필요합니다.' });
    }
    const sb = supabaseAsUser(token);
    const { data, error } = await sb.from('saved_ideas').insert(row).select('id').single();
    if (error) throw Object.assign(new Error(error.message), { code: 'SAVE_FAIL', status: 500 });
    res.json({ id: data.id, saved: true });
  } catch (err) {
    sendError(res, err);
  }
});

// ---- Admin APIs ----
app.get('/api/admin/dashboard', async (req, res) => {
  try {
    await requireAdmin(req);
    const admin = supabaseAdmin();
    const today = new Date().toLocaleDateString('en-CA', { timeZone: 'Asia/Seoul' });
    const [{ count: members }, { count: posts }, { count: comments }, usageRows] = await Promise.all([
      admin.from('profiles').select('*', { count: 'exact', head: true }),
      admin.from('posts').select('*', { count: 'exact', head: true }),
      admin.from('comments').select('*', { count: 'exact', head: true }),
      admin.from('usage_daily').select('title_count, outline_count').eq('usage_date', today),
    ]);
    const titleToday = (usageRows.data || []).reduce((s, r) => s + (r.title_count || 0), 0);
    const outlineToday = (usageRows.data || []).reduce((s, r) => s + (r.outline_count || 0), 0);
    res.json({
      members: members || 0,
      posts: posts || 0,
      comments: comments || 0,
      titleToday,
      outlineToday,
    });
  } catch (err) {
    sendError(res, err);
  }
});

app.get('/api/admin/members', async (req, res) => {
  try {
    await requireAdmin(req);
    const q = String(req.query.q || '').trim();
    const admin = supabaseAdmin();
    let query = admin.from('profiles').select('id, email, nickname, real_name, role, status, created_at').order('created_at', { ascending: false }).limit(100);
    if (q) {
      query = query.or(`email.ilike.%${q}%,nickname.ilike.%${q}%`);
    }
    const { data, error } = await query;
    if (error) throw Object.assign(new Error(error.message), { code: 'QUERY_ERROR', status: 500 });
    res.json({ members: data || [] });
  } catch (err) {
    sendError(res, err);
  }
});

app.patch('/api/admin/members/:id', async (req, res) => {
  try {
    const { user } = await requireAdmin(req);
    const admin = supabaseAdmin();
    const id = req.params.id;
    const { data: before } = await admin.from('profiles').select('*').eq('id', id).maybeSingle();
    if (!before) return res.status(404).json({ error: 'NOT_FOUND' });

    const patch = {};
    if (req.body.status === 'active' || req.body.status === 'inactive') patch.status = req.body.status;
    if (req.body.role === 'member' || req.body.role === 'admin') patch.role = req.body.role;
    patch.updated_at = new Date().toISOString();

    const { data: after, error } = await admin.from('profiles').update(patch).eq('id', id).select('*').single();
    if (error) throw Object.assign(new Error(error.message), { code: 'QUERY_ERROR', status: 500 });

    await admin.from('admin_audit_logs').insert({
      actor_id: user.id,
      action: 'member_update',
      target: id,
      before,
      after,
    });
    res.json({ member: after });
  } catch (err) {
    sendError(res, err);
  }
});

app.get('/api/admin/activity', async (req, res) => {
  try {
    await requireAdmin(req);
    const admin = supabaseAdmin();
    const [{ data: ideas }, { data: posts }, { data: comments }] = await Promise.all([
      admin.from('saved_ideas').select('id, user_id, selected_title, created_at').order('created_at', { ascending: false }).limit(20),
      admin.from('posts').select('id, author_id, title, created_at').order('created_at', { ascending: false }).limit(20),
      admin.from('comments').select('id, post_id, author_id, body, created_at').order('created_at', { ascending: false }).limit(20),
    ]);
    res.json({ ideas: ideas || [], posts: posts || [], comments: comments || [] });
  } catch (err) {
    sendError(res, err);
  }
});

app.get('/api/admin/keywords/top', async (req, res) => {
  try {
    await requireAdmin(req);
    const admin = supabaseAdmin();
    const { data, error } = await admin.from('saved_ideas').select('keywords');
    if (error) throw Object.assign(new Error(error.message), { code: 'QUERY_ERROR', status: 500 });
    const map = new Map();
    for (const row of data || []) {
      for (const k of row.keywords || []) {
        const key = String(k).trim();
        if (!key) continue;
        map.set(key, (map.get(key) || 0) + 1);
      }
    }
    const top = [...map.entries()]
      .sort((a, b) => b[1] - a[1])
      .slice(0, 5)
      .map(([keyword, count]) => ({ keyword, count }));
    res.json({ top });
  } catch (err) {
    sendError(res, err);
  }
});

app.get('/api/admin/settings/limits', async (req, res) => {
  try {
    await requireAdmin(req);
    const admin = supabaseAdmin();
    const { data, error } = await admin.from('app_settings').select('key, value').in('key', ['daily_title_limit', 'daily_outline_limit']);
    if (error) throw Object.assign(new Error(error.message), { code: 'QUERY_ERROR', status: 500 });
    const limits = { daily_title_limit: 10, daily_outline_limit: 5 };
    for (const row of data || []) {
      limits[row.key] = Number(row.value);
    }
    res.json(limits);
  } catch (err) {
    sendError(res, err);
  }
});

app.put('/api/admin/settings/limits', async (req, res) => {
  try {
    const { user } = await requireAdmin(req);
    const titleLimit = Number(req.body.daily_title_limit);
    const outlineLimit = Number(req.body.daily_outline_limit);
    if (!Number.isFinite(titleLimit) || titleLimit < 0 || !Number.isFinite(outlineLimit) || outlineLimit < 0) {
      return res.status(400).json({ error: 'VALIDATION', message: '한도는 0 이상 숫자여야 합니다.' });
    }
    const admin = supabaseAdmin();
    const { data: before } = await admin.from('app_settings').select('*').in('key', ['daily_title_limit', 'daily_outline_limit']);
    await admin.from('app_settings').upsert([
      { key: 'daily_title_limit', value: titleLimit, updated_at: new Date().toISOString(), updated_by: user.id },
      { key: 'daily_outline_limit', value: outlineLimit, updated_at: new Date().toISOString(), updated_by: user.id },
    ]);
    const { data: after } = await admin.from('app_settings').select('*').in('key', ['daily_title_limit', 'daily_outline_limit']);
    await admin.from('admin_audit_logs').insert({
      actor_id: user.id,
      action: 'limits_update',
      target: 'app_settings',
      before,
      after,
    });
    res.json({
      daily_title_limit: titleLimit,
      daily_outline_limit: outlineLimit,
    });
  } catch (err) {
    sendError(res, err);
  }
});

app.get('/api/admin/me', async (req, res) => {
  try {
    await requireAdmin(req);
    res.json({ admin: true });
  } catch (err) {
    sendError(res, err);
  }
});

// local/prod Node 서버에서만 정적 파일 제공 (Vercel은 dist를 CDN으로 서빙)
if (!process.env.VERCEL) {
  const dist = path.resolve(__dirname, '../dist');
  app.use(express.static(dist));
  app.get(/^(?!\/api).*/, (req, res, next) => {
    if (req.method !== 'GET') return next();
    res.sendFile(path.join(dist, 'index.html'), (err) => {
      if (err) next();
    });
  });

  app.listen(env.port, () => {
    console.log(`IdeaForge API http://127.0.0.1:${env.port}`);
  });
}

export default app;