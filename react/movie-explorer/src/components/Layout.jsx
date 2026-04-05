import { Link, NavLink, Outlet } from 'react-router-dom';
import { useFavorites } from '../context/FavoritesContext';

export default function Layout() {
  const { favorites } = useFavorites();

  return (
    <div className="app-shell">
      <header className="header">
        <Link to="/" className="logo">
          Movie Explorer
        </Link>
        <nav className="nav">
          <NavLink to="/" end>
            홈
          </NavLink>
          <NavLink to="/favorites">
            즐겨찾기 {favorites.length > 0 ? `(${favorites.length})` : ''}
          </NavLink>
        </nav>
      </header>
      <Outlet />
      <footer className="footer">TMDB API · 교육용 예제</footer>
    </div>
  );
}
