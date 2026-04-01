import { Link, NavLink, Outlet } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';

export default function Layout() {
  const { member, logout } = useAuth();

  return (
    <div className="layout">
      <header className="header">
        <Link to="/" className="brand">
          K-Culture Platform
        </Link>
        <nav className="nav">
          <NavLink to="/" end>
            목록
          </NavLink>
          <NavLink to="/write">글쓰기</NavLink>
          {member ? (
            <>
              <span className="user">{member.name}</span>
              <button type="button" className="linkish" onClick={() => logout()}>
                로그아웃
              </button>
            </>
          ) : (
            <>
              <NavLink to="/login">로그인</NavLink>
              <NavLink to="/join">회원가입</NavLink>
            </>
          )}
        </nav>
      </header>
      <main className="main">
        <Outlet />
      </main>
      <footer className="footer">Vite · React Router · fetch(credentials) · Express session</footer>
    </div>
  );
}
