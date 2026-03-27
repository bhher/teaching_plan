import { useApp } from '../context/AppContext';

/**
 * ContentPanel — 역시 useApp()으로 theme, count를 읽습니다.
 */
function ContentPanel() {
  const { theme, count } = useApp();

  return (
    <section className="panel">
      <p className="note">
        <strong>ContentPanel</strong>도 <code>useApp()</code>으로 동일한 값을 구독합니다.
      </p>
      <p>현재 테마 문자열: <code>{theme}</code></p>
      <p className="counter">{count}</p>
    </section>
  );
}

export default ContentPanel;
