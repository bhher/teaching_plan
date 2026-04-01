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
  createPost: (formData) => requestForm('/api/posts', 'POST', formData),
  updatePost: (id, formData) => requestForm(`/api/posts/${id}`, 'PATCH', formData),
  deletePost: (id) => request(`/api/posts/${id}`, { method: 'DELETE' }),
  createComment: (postId, body) =>
    request(`/api/posts/${postId}/comments`, { method: 'POST', json: body }),
  me: () => request('/api/auth/me'),
  login: (body) => request('/api/auth/login', { method: 'POST', json: body }),
  logout: () => request('/api/auth/logout', { method: 'POST' }),
  join: (body) => request('/api/auth/join', { method: 'POST', json: body }),
};
