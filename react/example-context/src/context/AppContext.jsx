import { createContext, useContext, useState, useMemo } from 'react';

const AppContext = createContext(null);

/**
 * App 전역 상태(theme, count)를 Context로 제공합니다.
 * 깊은 트리에서도 props drilling 없이 useApp()으로 접근합니다.
 */
export function AppProvider({ children }) {
  const [theme, setTheme] = useState('light');
  const [count, setCount] = useState(0);

  const toggleTheme = () => {
    setTheme((t) => (t === 'light' ? 'dark' : 'light'));
  };

  const increment = () => setCount((c) => c + 1);

  const value = useMemo(
    () => ({
      theme,
      count,
      toggleTheme,
      increment,
    }),
    [theme, count]
  );

  return <AppContext.Provider value={value}>{children}</AppContext.Provider>;
}

export function useApp() {
  const ctx = useContext(AppContext);
  if (!ctx) {
    throw new Error('useApp은 AppProvider 안에서만 사용하세요.');
  }
  return ctx;
}
