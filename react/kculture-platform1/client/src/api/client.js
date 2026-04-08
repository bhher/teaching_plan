/**
 * Same-origin `/api`, `/uploads` (Vite dev proxy → Express).
 * Session cookie 전송: credentials: 'include'
 */
async function request(path, options = {}) {
  const { json, ...init } = options;
  const headers = { ...init.headers };
  if (json !== undefined) {
    headers['Content-Type'] = 'application/json';
    init.body = JSON.stringify(json);
  }
  const res = await fetch(path, {
    ...init,
    headers,
    credentials: 'include',
  });
  const text = await res.text();
  let data = null;
  if (text) {
    try {
      data = JSON.parse(text);
    } catch {
      data = text;
    }
  }
  if (!res.ok) {
    const err = new Error(data?.error || res.statusText || 'Request failed');
    err.status = res.status;
    err.data = data;
    throw err;
  }
  return data;
}

/** FormData 전송 (Content-Type 자동 boundary — 헤더 지정하지 않음) */
async function requestForm(path, method, formData) {
  const res = await fetch(path, {
    method,
    body: formData,
    credentials: 'include',
  });
  const text = await res.text();
  let data = null;
  if (text) {
    try {
      data = JSON.parse(text);
    } catch {
      data = text;
    }
  }
  if (!res.ok) {
    const err = new Error(data?.error || res.statusText || 'Request failed');
    err.status = res.status;
    err.data = data;
    throw err;
  }
  return data;
}

export const api = {
  /** GET: 카테고리 전체 목록 (필터·메뉴용) */
  getCategories: () => request('/api/categories'),
  /** GET: 게시글 목록 — params로 categoryId·page·pageSize 쿼리스트링 구성 후 요청 */
  getPosts: (params) => {
    const q = new URLSearchParams();
    if (params?.categoryId) q.set('categoryId', String(params.categoryId));
    if (params?.page) q.set('page', String(params.page));
    if (params?.pageSize) q.set('pageSize', String(params.pageSize));
    const s = q.toString();
    return request(`/api/posts${s ? `?${s}` : ''}`);
  },
  /** GET: 글 상세 + 댓글 (서버에서 조회수 증가) */
  getPost: (id) => request(`/api/posts/${id}`),
  /** GET: 편집 폼용 글 한 건 (본인만, 조회수 증가 없음) */
  getPostForEdit: (id) => request(`/api/posts/${id}/edit`),
  /** POST: 새 글 — multipart FormData(이미지 필드 포함 가능) */
  createPost: (formData) => requestForm('/api/posts', 'POST', formData),
  /** PATCH: 글 수정 — FormData로 본문·이미지 교체/삭제 */
  updatePost: (id, formData) => requestForm(`/api/posts/${id}`, 'PATCH', formData),
  /** DELETE: 글 삭제 */
  deletePost: (id) => request(`/api/posts/${id}`, { method: 'DELETE' }),
  /** POST: 해당 글에 댓글 작성 (JSON body) */
  createComment: (postId, body) =>
    request(`/api/posts/${postId}/comments`, { method: 'POST', json: body }),
  /** GET: 현재 세션 로그인 여부·회원 정보 */
  me: () => request('/api/auth/me'),
  /** POST: 로그인 (세션 쿠키 설정) */
  login: (body) => request('/api/auth/login', { method: 'POST', json: body }),
  /** POST: 로그아웃 (세션 제거) */
  logout: () => request('/api/auth/logout', { method: 'POST' }),
  /** POST: 회원가입 (성공 시 자동 로그인) */
  join: (body) => request('/api/auth/join', { method: 'POST', json: body }),
};
