import { Link } from 'react-router-dom';

export default function NotFound() {
  return (
    <div className="page narrow center">
      <h1>404</h1>
      <p className="lead">페이지를 찾을 수 없습니다.</p>
      <Link className="btn" to="/">
        홈으로
      </Link>
    </div>
  );
}
