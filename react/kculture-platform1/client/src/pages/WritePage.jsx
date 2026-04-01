import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { api } from '../api/client';
import { useAuth } from '../context/AuthContext';

export default function WritePage() {
  const navigate = useNavigate();
  const { member, loading } = useAuth();
  const [categories, setCategories] = useState([]);
  const [categoryId, setCategoryId] = useState('');
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');
  const [file, setFile] = useState(null);
  const [error, setError] = useState(null);

  useEffect(() => {
    if (!loading && !member) {
      navigate('/login', { replace: true, state: { from: '/write' } });
    }
  }, [loading, member, navigate]);

  useEffect(() => {
    api.getCategories().then((cats) => {
      setCategories(cats);
      if (cats[0]) setCategoryId(String(cats[0].id));
    });
  }, []);

  const submit = async (e) => {
    e.preventDefault();
    setError(null);
    try {
      const fd = new FormData();
      fd.append('categoryId', String(categoryId));
      fd.append('title', title);
      fd.append('content', content);
      if (file) fd.append('image', file);
      const { id } = await api.createPost(fd);
      navigate(`/posts/${id}`);
    } catch (err) {
      setError(err.data?.error || err.message);
    }
  };

  if (loading || !member) {
    return <p>확인 중…</p>;
  }

  return (
    <div>
      <h1>글쓰기</h1>
      <p className="hint">
        이미지는 선택 사항입니다. 서버 <code>server/uploads</code>에 저장됩니다.
      </p>
      <form onSubmit={submit} className="form">
        <label>
          카테고리
          <select value={categoryId} onChange={(e) => setCategoryId(e.target.value)}>
            {categories.map((c) => (
              <option key={c.id} value={c.id}>
                {c.icon} {c.nameEn}
              </option>
            ))}
          </select>
        </label>
        <label>
          제목
          <input value={title} onChange={(e) => setTitle(e.target.value)} required />
        </label>
        <label>
          내용
          <textarea value={content} onChange={(e) => setContent(e.target.value)} rows={12} />
        </label>
        <label>
          이미지 (JPEG / PNG / GIF / WebP, 최대 5MB)
          <input
            type="file"
            accept="image/jpeg,image/png,image/gif,image/webp"
            onChange={(e) => setFile(e.target.files?.[0] ?? null)}
          />
        </label>
        {error && <p className="error">{error}</p>}
        <button type="submit">등록</button>
      </form>
    </div>
  );
}
