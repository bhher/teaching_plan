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
  const [imageFilename, setImageFilename] = useState(null);
  const [file, setFile] = useState(null);
  const [removeImage, setRemoveImage] = useState(false);
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
        setImageFilename(post.imageFilename ?? null);
        setOwnerId(post.memberId);
        setRemoveImage(false);
        setFile(null);
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
      const fd = new FormData();
      fd.append('title', title);
      fd.append('content', content);
      if (removeImage) fd.append('removeImage', '1');
      if (file) fd.append('image', file);
      await api.updatePost(id, fd);
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
      <p className="hint">새 이미지를 올리면 기존 파일은 삭제됩니다.</p>
      <form onSubmit={submit} className="form">
        <label>
          제목
          <input value={title} onChange={(e) => setTitle(e.target.value)} required />
        </label>
        <label>
          내용
          <textarea value={content} onChange={(e) => setContent(e.target.value)} rows={12} />
        </label>
        {imageFilename && !file && (
          <div className="current-image">
            {!removeImage && (
              <>
                <p>현재 이미지</p>
                <img src={`/uploads/${imageFilename}`} alt="" className="preview" />
              </>
            )}
            <label className="check">
              <input
                type="checkbox"
                checked={removeImage}
                onChange={(e) => setRemoveImage(e.target.checked)}
              />
              이미지 제거
            </label>
          </div>
        )}
        <label>
          새 이미지로 교체 (선택)
          <input
            type="file"
            accept="image/jpeg,image/png,image/gif,image/webp"
            onChange={(e) => {
              setFile(e.target.files?.[0] ?? null);
              if (e.target.files?.[0]) setRemoveImage(false);
            }}
          />
        </label>
        {error && <p className="error">{error}</p>}
        <button type="submit">저장</button>
      </form>
    </div>
  );
}
