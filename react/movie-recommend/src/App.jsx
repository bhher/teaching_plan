import { NavLink, Route, Routes } from 'react-router-dom';
import Home from './pages/Home';
import MovieDetail from './pages/MovieDetail';
import Wishlist from './pages/Wishlist';

export default function App() {
  return (
    <div className="layout">
      <nav className="nav">
        <NavLink to="/" end>
          영화
        </NavLink>
        <NavLink to="/wishlist">찜 목록</NavLink>
      </nav>
      <main>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/movie/:id" element={<MovieDetail />} />
          <Route path="/wishlist" element={<Wishlist />} />
        </Routes>
      </main>
    </div>
  );
}
