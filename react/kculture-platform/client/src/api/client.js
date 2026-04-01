/**
 * Same-origin `/api` (Vite dev proxy → Express). Session cookie 전송을 위해
 * credentials: 'include' 필수. (직접 API 포트로 호출할 때는 백엔드 CORS에 credentials 허용 필요)
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

export const api = {
  getCategories: () => request('/api/categories'),
  getPosts: (params) => {
    const q = new URLSearchParams();
    if (params?.categoryId) q.set('categoryId', String(params.categoryId));
    if (params?.page) q.set('page', String(params.page));
    if (params?.pageSize) q.set('pageSize', String(params.pageSize));
    const s = q.toString();
    return request(`/api/posts${s ? `?${s}` : ''}`);
  },
  getPost: (id) => request(`/api/posts/${id}`),
  getPostForEdit: (id) => request(`/api/posts/${id}/edit`),
  createPost: (body) => request('/api/posts', { method: 'POST', json: body }),
  updatePost: (id, body) => request(`/api/posts/${id}`, { method: 'PATCH', json: body }),
  deletePost: (id) => request(`/api/posts/${id}`, { method: 'DELETE' }),
  createComment: (postId, body) =>
    request(`/api/posts/${postId}/comments`, { method: 'POST', json: body }),
  me: () => request('/api/auth/me'),
  login: (body) => request('/api/auth/login', { method: 'POST', json: body }),
  logout: () => request('/api/auth/logout', { method: 'POST' }),
  join: (body) => request('/api/auth/join', { method: 'POST', json: body }),
};
