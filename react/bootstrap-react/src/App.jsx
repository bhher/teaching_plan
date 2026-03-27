import { Routes, Route, Navigate } from 'react-router-dom';
import Layout from './components/Layout';
import Home from './pages/Home';
import LoginFormPage from './pages/LoginFormPage';
import NavigationPage from './pages/NavigationPage';
import CardsPage from './pages/CardsPage';
import ButtonsAlertsPage from './pages/ButtonsAlertsPage';
import ModalsPage from './pages/ModalsPage';
import FormComponentsPage from './pages/FormComponentsPage';
import GridPage from './pages/GridPage';

/**
 * RR v6: path 없는 부모 + index + 상대 path("03-card") 가
 * path="/" 부모보다 /03-card 매칭에 안정적입니다.
 */
export default function App() {
  return (
    <Routes>
      <Route path="/01-login" element={<LoginFormPage />} />
      <Route path="/02-nav" element={<NavigationPage />} />

      <Route element={<Layout />}>
        <Route index element={<Home />} />
        <Route path="03-card" element={<CardsPage />} />
        <Route path="03-cards" element={<Navigate to="/03-card" replace />} />
        <Route path="04-buttons" element={<ButtonsAlertsPage />} />
        <Route path="05-modals" element={<ModalsPage />} />
        <Route path="06-forms" element={<FormComponentsPage />} />
        <Route path="07-grid" element={<GridPage />} />
      </Route>

      <Route path="*" element={<Navigate to="/" replace />} />
    </Routes>
  );
}
