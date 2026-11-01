import { NavLink, Outlet } from 'react-router-dom';

const linkClass = ({ isActive }) => (isActive ? 'nav-link active' : 'nav-link');

export default function Layout() {
  return (
    <div className="site">
      <header className="header">
        <div className="header-inner">
          <NavLink to="/" className="brand" end>
            Studio<span>Route</span>
          </NavLink>
          <nav className="nav">
            <NavLink to="/" className={linkClass} end>
              Home
            </NavLink>
            <NavLink to="/about" className={linkClass}>
              About
            </NavLink>
            <NavLink to="/portfolio" className={linkClass}>
              Portfolio
            </NavLink>
            <NavLink to="/contact" className={linkClass}>
              Contact
            </NavLink>
          </nav>
        </div>
      </header>

      <main className="main">
        <Outlet />
      </main>

      <footer className="footer">
        <p>React Router · Slide · Card · About · Portfolio · Contact</p>
      </footer>
    </div>
  );
}
