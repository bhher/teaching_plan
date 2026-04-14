import { NavLink, Outlet } from 'react-router-dom';

const navLinkClass = ({ isActive }) =>
  isActive ? 'nav-link nav-link--active' : 'nav-link';

export default function Layout() {
  return (
    <div className="layout">
      <header className="site-header">
        <div className="site-header__inner">
          <NavLink to="/" className="brand" end>
            Router Simple
          </NavLink>
          <nav className="site-nav" aria-label="주 메뉴">
            <NavLink to="/" className={navLinkClass} end>
              홈
            </NavLink>
            <NavLink to="/about" className={navLinkClass}>
              소개
            </NavLink>
            <NavLink to="/contact" className={navLinkClass}>
              문의
            </NavLink>
          </nav>
        </div>
      </header>

      <main className="site-main">
        <Outlet />
      </main>

      <footer className="site-footer">
        <p>React Router DOM v6 · 중첩 라우트 · NavLink</p>
      </footer>
    </div>
  );
}
