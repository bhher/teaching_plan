import { supabase } from './supabaseClient.js';

async function authHeader() {
  if (!supabase) return {};
  const { data } = await supabase.auth.getSession();
  const token = data.session?.access_token;
  return token ? { Authorization: `Bearer ${token}` } : {};
}

export async function api(path, options = {}) {
  const headers = {
    'Content-Type': 'application/json',
    ...(await authHeader()),
    ...(options.headers || {}),
  };
  const res = await fetch(path, { ...options, headers });
  let body = null;
  try {
    body = await res.json();
  } catch {
    body = null;
  }
  if (!res.ok) {
    const err = new Error(body?.message || body?.error || 'REQUEST_FAILED');
    err.code = body?.error || 'QUERY_ERROR';
    err.status = res.status;
    err.body = body;
    throw err;
  }
  return body;
}
