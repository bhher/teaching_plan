import { useState } from 'react';
import Toolbar from './components/Toolbar';
import ContentPanel from './components/ContentPanel';

/**
 * Props 방식: theme, count, setters 를 자식에게 props 로 전달합니다.
 * 중첩이 깊어지면 "props drilling" 이 됩니다.
 */
function App() {
  const [theme, setTheme] = useState('light');
  const [count, setCount] = useState(0);

  const toggleTheme = () => {
    setTheme((t) => (t === 'light' ? 'dark' : 'light'));
  };

  return (
    <div className={`app ${theme}`}>
      <header>
        <h1>Props 예제</h1>
        <span className="badge">상태는 App · props로 전달</span>
      </header>

      <Toolbar
        theme={theme}
        onToggleTheme={toggleTheme}
        count={count}
        onIncrement={() => setCount((c) => c + 1)}
      />

      <ContentPanel theme={theme} count={count} />
    </div>
  );
}

export default App;
