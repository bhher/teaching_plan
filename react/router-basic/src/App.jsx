import { BrowserRouter, Routes, Route, NavLink } from 'react-router-dom';
import Home from './pages/Home';
import About from './pages/About';
import Products from './pages/Products';
import ProductDetail from './pages/ProductDetail';
import Contact from './pages/Contact';
import Login from './pages/Login';

function App() {
  return (
    <BrowserRouter>
      <div className="layout">
        <nav>
          <NavLink to="/" end>
            홈
          </NavLink>
          <NavLink to="/about">소개</NavLink>
          <NavLink to="/products">상품</NavLink>
          <NavLink to="/contact">연락처</NavLink>
          <NavLink to="/login">로그인</NavLink>
        </nav>

        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/about" element={<About />} />
          <Route path="/products" element={<Products />} />
          <Route path="/products/:id" element={<ProductDetail />} />
          <Route path="/contact" element={<Contact />} />
          <Route path="/login" element={<Login />} />
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
