import { useEffect, useState } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { api } from '../api/client';
import { useAuth } from '../context/AuthContext';

export default function EditPage() {
  const { id } = useParams();
  const navigate = useNavigate();
  const { member, loading } = useAuth();
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');
  const [ownerId, setOwnerId] = useState(null);
  const [error, setError] = useState(null);

  useEffect(() => {
    if (!loading && !member) {
      navigate('/login', { replace: true });
    }
  }, [loading, member, navigate]);

  useEffect(() => {
    api
      .getPostForEdit(id)
      .then(({ post }) => {
        setTitle(post.title);
        setContent(post.content ?? '');
        setOwnerId(post.memberId);
      })
      .catch((e) => setError(e.message));
  }, [id]);

  useEffect(() => {
    if (member && ownerId != null && member.id !== ownerId) {
      navigate('/', { replace: true });
    }
  }, [member, ownerId, navigate]);

  const submit = async (e) => {
    e.preventDefault();
    setError(null);
    try {
      await api.updatePost(id, { title, content });
      navigate(`/posts/${id}`);
    } catch (err) {
      setError(err.data?.error || err.message);
    }
  };

  if (loading || !member) {
    return <p>확인 중…</p>;
  }

  if (error && ownerId == null) {
    return (
      <p className="error">
        {error} <Link to="/">목록</Link>
      </p>
    );
  }

  return (
    <div>
      <h1>수정</h1>
      <p className="hint">edit.do → <code>/posts/:id/edit</code></p>
      <form onSubmit={submit} className="form">
        <label>
          제목
          <input value={title} onChange={(e) => setTitle(e.target.value)} required />
        </label>
        <label>
          내용
          <textarea value={content} onChange={(e) => setContent(e.target.value)} rows={12} />
        </label>
        {error && <p className="error">{error}</p>}
        <button type="submit">저장</button>
      </form>
    </div>
  );
}
