import { API, SAMPLE_POST } from './config';

/** 브라우저 내장 fetch */
export async function getWithFetch() {
  const res = await fetch(`${API}?_limit=3`);
  // fetch는 HTTP 4xx/5xx도 reject하지 않음 → 직접 확인
  if (!res.ok) throw new Error(`HTTP ${res.status}`);
  return res.json();
}

export async function postWithFetch() {
  const res = await fetch(API, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(SAMPLE_POST),
  });
  if (!res.ok) throw new Error(`HTTP ${res.status}`);
  return res.json();
}
