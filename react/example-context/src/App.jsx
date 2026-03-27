import { AppProvider, useApp } from './context/AppContext';
import Toolbar from './components/Toolbar';
import ContentPanel from './components/ContentPanel';

/**
 * Context 방식: 상태는 Provider 안에 있고,
 * 자식은 useApp()으로 접근합니다 (props는 최소화).
 */
function AppShell() {
  const { theme } = useApp();

  return (
    <div className={`app ${theme}`}>
      <header>
        <h1>useContext 예제</h1>
        <span className="badge">상태는 AppContext · useApp()</span>
      </header>

      <Toolbar />
      <ContentPanel />
    </div>
  );
}

export default function App() {
  return (
    <AppProvider>
      <AppShell />
    </AppProvider>
  );
}
