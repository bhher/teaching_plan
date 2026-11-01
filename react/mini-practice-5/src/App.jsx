import { useState } from 'react';
import Practice01Like from './practices/Practice01Like';
import Practice02Cart from './practices/Practice02Cart';
import Practice03Search from './practices/Practice03Search';
import Practice04Todo from './practices/Practice04Todo';
import Practice05Optimize from './practices/Practice05Optimize';

const tabs = [
  { id: 1, label: '① 좋아요', Component: Practice01Like },
  { id: 2, label: '② 장바구니', Component: Practice02Cart },
  { id: 3, label: '③ 검색 필터', Component: Practice03Search },
  { id: 4, label: '④ Todo', Component: Practice04Todo },
  { id: 5, label: '⑤ 최적화', Component: Practice05Optimize },
];

function App() {
  const [tab, setTab] = useState(1);
  const Active = tabs.find((t) => t.id === tab).Component;

  return (
    <div className="app">
      <h1>React 미니 실습 5개</h1>
      <p className="lead">useState → useMemo → memo / useCallback 복습</p>

      <div className="tabs">
        {tabs.map((t) => (
          <button
            key={t.id}
            type="button"
            className={`tab ${tab === t.id ? 'active' : ''}`}
            onClick={() => setTab(t.id)}
          >
            {t.label}
          </button>
        ))}
      </div>

      <Active />
    </div>
  );
}

export default App;
