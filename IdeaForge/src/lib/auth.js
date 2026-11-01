import { supabase, supabaseConfigured } from './supabaseClient.js';
import { api } from './api.js';

let state = {
  session: null,
  profile: null,
  isAdminView: false,
  ready: false,
};

const listeners = new Set();

export function getAuthState() {
  return state;
}

export function onAuthChange(fn) {
  listeners.add(fn);
  return () => listeners.delete(fn);
}

function emit() {
  for (const fn of listeners) fn(state);
}

async function loadProfile(userId) {
  if (!supabase || !userId) return null;
  const { data, error } = await supabase.from('profiles').select('*').eq('id', userId).maybeSingle();
  if (error) {
    console.error('profile query error', error);
    return { __queryError: true };
  }
  return data;
}

export async function initAuth() {
  if (!supabaseConfigured || !supabase) {
    state = { ...state, ready: true };
    emit();
    return state;
  }

  const { data } = await supabase.auth.getSession();
  state.session = data.session;
  if (state.session?.user) {
    state.profile = await loadProfile(state.session.user.id);
    if (state.profile && !state.profile.__queryError && state.profile.role === 'admin') {
      // keep isAdminView if already set via admin login route
    }
  } else {
    state.profile = null;
    state.isAdminView = false;
  }
  state.ready = true;
  emit();

  supabase.auth.onAuthStateChange(async (_event, session) => {
    state.session = session;
    if (session?.user) {
      state.profile = await loadProfile(session.user.id);
    } else {
      state.profile = null;
      state.isAdminView = false;
    }
    emit();
  });

  return state;
}

export async function signUp({ email, password, nickname, realName }) {
  const { data, error } = await supabase.auth.signUp({
    email,
    password,
    options: {
      data: { nickname, real_name: realName || '' },
    },
  });
  if (error) throw error;
  return data;
}

export async function signIn({ email, password }) {
  const { data, error } = await supabase.auth.signInWithPassword({ email, password });
  if (error) throw error;
  state.session = data.session;
  state.profile = await loadProfile(data.user.id);
  emit();
  return data;
}

export async function signOut() {
  state.isAdminView = false;
  await supabase.auth.signOut();
  state.session = null;
  state.profile = null;
  emit();
}

export async function enterAdminMode() {
  if (!state.session) throw Object.assign(new Error('AUTH_REQUIRED'), { code: 'AUTH_REQUIRED' });
  await api('/api/admin/me');
  state.isAdminView = true;
  emit();
}

export function leaveAdminMode() {
  state.isAdminView = false;
  emit();
}

export function isLoggedIn() {
  return Boolean(state.session?.user);
}

export function isActive() {
  return state.profile && !state.profile.__queryError && state.profile.status === 'active';
}

export function isAdmin() {
  return state.profile && state.profile.role === 'admin' && state.isAdminView;
}
