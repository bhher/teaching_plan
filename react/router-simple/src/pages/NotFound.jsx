import { Link } from 'react-router-dom';

export default function NotFound() {
  return (
    <article className="page page--center">
      <h1>404</h1>
      <p>이 주소에는 페이지가 없습니다.</p>
      <Link to="/" className="btn-primary">
        홈으로
      </Link>
    </article>
  );
}
