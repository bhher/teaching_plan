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
      const { id } = await api.createPost({
        categoryId: Number(categoryId),
        title,
        content,
      });
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
      <p className="hint">write.do → <code>/write</code></p>
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
        {error && <p className="error">{error}</p>}
        <button type="submit">등록</button>
      </form>
    </div>
  );
}
