import { NavLink, Route, Routes } from 'react-router-dom';
import Home from './pages/Home';
import PlaceDetail from './pages/PlaceDetail';
import Plans from './pages/Plans';

export default function App() {
  return (
    <div className="layout">
      <nav className="nav">
        <NavLink to="/" end>
          여행지
        </NavLink>
        <NavLink to="/plans">내 일정</NavLink>
      </nav>
      <main>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/place/:id" element={<PlaceDetail />} />
          <Route path="/plans" element={<Plans />} />
        </Routes>
      </main>
    </div>
  );
}
