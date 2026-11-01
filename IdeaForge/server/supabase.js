import { createClient } from '@supabase/supabase-js';
import { env } from './env.js';

export function supabaseAsUser(accessToken) {
  return createClient(env.supabaseUrl, env.supabaseAnon, {
    global: { headers: { Authorization: `Bearer ${accessToken}` } },
    auth: { persistSession: false, autoRefreshToken: false },
  });
}

export function supabaseAdmin() {
  if (!env.supabaseService) {
    throw Object.assign(new Error('SUPABASE_SERVICE_ROLE_KEY missing'), { code: 'SERVER_MISCONFIG' });
  }
  return createClient(env.supabaseUrl, env.supabaseService, {
    auth: { persistSession: false, autoRefreshToken: false },
  });
}

export async function getAuthUser(req) {
  const header = req.headers.authorization || '';
  const token = header.startsWith('Bearer ') ? header.slice(7) : '';
  if (!token) {
    const err = new Error('AUTH_REQUIRED');
    err.code = 'AUTH_REQUIRED';
    err.status = 401;
    throw err;
  }
  const admin = supabaseAdmin();
  const { data, error } = await admin.auth.getUser(token);
  if (error || !data?.user) {
    const err = new Error('AUTH_EXPIRED');
    err.code = 'AUTH_EXPIRED';
    err.status = 401;
    throw err;
  }
  return { user: data.user, token };
}

export async function getActiveProfile(userId) {
  const admin = supabaseAdmin();
  const { data, error } = await admin.from('profiles').select('*').eq('id', userId).maybeSingle();
  if (error) {
    const err = new Error('QUERY_ERROR');
    err.code = 'QUERY_ERROR';
    err.status = 500;
    throw err;
  }
  if (!data) {
    const err = new Error('NOT_FOUND');
    err.code = 'NOT_FOUND';
    err.status = 404;
    throw err;
  }
  if (data.status !== 'active') {
    const err = new Error('AUTH_INACTIVE');
    err.code = 'AUTH_INACTIVE';
    err.status = 403;
    throw err;
  }
  return data;
}

export async function requireAdmin(req) {
  const { user, token } = await getAuthUser(req);
  const profile = await getActiveProfile(user.id);
  if (profile.role !== 'admin') {
    const err = new Error('FORBIDDEN');
    err.code = 'FORBIDDEN';
    err.status = 403;
    throw err;
  }
  return { user, token, profile };
}
