import { createClient } from '@supabase/supabase-js';

const url = String(import.meta.env.VITE_SUPABASE_URL || '').trim();
const anon = String(import.meta.env.VITE_SUPABASE_ANON_KEY || '').trim();

const looksPlaceholder =
  !url ||
  !anon ||
  url.includes('YOUR_PROJECT') ||
  anon === 'your_anon_key' ||
  anon.startsWith('your_');

export const supabaseConfigured = Boolean(url && anon && !looksPlaceholder);

export const supabase = supabaseConfigured
  ? createClient(url, anon, {
      auth: {
        persistSession: true,
        autoRefreshToken: true,
        detectSessionInUrl: true,
      },
    })
  : null;
