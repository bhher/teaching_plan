import { useEffect, useState } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { api } from '../api/client';
import { useAuth } from '../context/AuthContext';

export default function PostPage() {
  const { id } = useParams();
  const navigate = useNavigate();
  const { member } = useAuth();
  const [payload, setPayload] = useState(null);
  const [error, setError] = useState(null);
  const [commentText, setCommentText] = useState('');
  const [commentErr, setCommentErr] = useState(null);

  useEffect(() => {
    setError(null);
    api
      .getPost(id)
      .then(setPayload)
      .catch((e) => {
        setError(e.message);
        setPayload(null);
      });
  }, [id]);

  const handleComment = async (e) => {
    e.preventDefault();
    setCommentErr(null);
    if (!member) {
      navigate('/login');
      return;
    }
    try {
      await api.createComment(id, { content: commentText });
      setCommentText('');
      const next = await api.getPost(id);
      setPayload(next);
    } catch (err) {
      setCommentErr(err.data?.error || err.message);
    }
  };

  const handleDelete = async () => {
    if (!window.confirm('삭제할까요?')) return;
    try {
      await api.deletePost(id);
      navigate('/');
    } catch (err) {
      setError(err.data?.error || err.message);
    }
  };

  if (error && !payload) {
    return (
      <div>
        <p className="error">{error}</p>
        <Link to="/">목록으로</Link>
      </div>
    );
  }

  if (!payload) {
    return <p>불러오는 중…</p>;
  }

  const { post, comments } = payload;
  const canEdit = member && member.id === post.memberId;

  return (
    <article className="post-detail">
      <p className="hint">view.do → <code>/posts/:id</code></p>
      <header>
        <h1>
          {post.categoryIcon} {post.title}
        </h1>
        <p className="meta">
          {post.categoryName} · {post.memberName} · 조회 {post.viewCount}
        </p>
      </header>
      {post.imageFilename ? (
        <figure className="post-figure">
          <img src={`/uploads/${post.imageFilename}`} alt="" className="post-image" />
        </figure>
      ) : null}
      <div className="body">{post.content}</div>

      {canEdit && (
        <div className="actions">
          <Link to={`/posts/${post.id}/edit`} className="button">
            수정
          </Link>
          <button type="button" className="danger" onClick={handleDelete}>
            삭제
          </button>
        </div>
      )}

      <section className="comments">
        <h2>댓글</h2>
        <ul>
          {comments.length === 0 ? <li>댓글이 없습니다.</li> : null}
          {comments.map((c) => (
            <li key={c.id}>
              <strong>{c.memberName}</strong> <span className="nat">({c.nationality})</span>
              <p>{c.content}</p>
            </li>
          ))}
        </ul>
        {member ? (
          <form onSubmit={handleComment} className="comment-form">
            <textarea
              value={commentText}
              onChange={(e) => setCommentText(e.target.value)}
              placeholder="댓글을 입력하세요"
              rows={3}
            />
            {commentErr && <p className="error">{commentErr}</p>}
            <button type="submit">등록</button>
          </form>
        ) : (
          <p>
            <Link to="/login">로그인</Link> 후 댓글을 작성할 수 있습니다.
          </p>
        )}
      </section>

      <p>
        <Link to="/">← 목록</Link>
      </p>
    </article>
  );
}
