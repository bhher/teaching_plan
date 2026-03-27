/**
 * Toolbar — App에서 props로 theme, count, 핸들러를 받습니다.
 */
function Toolbar({ theme, onToggleTheme, count, onIncrement }) {
  return (
    <section className="panel">
      <p className="note">
        <strong>Toolbar</strong>는 props만 받아 버튼을 렌더링합니다.
      </p>
      <button type="button" className="btn-toggle" onClick={onToggleTheme}>
        테마: {theme === 'light' ? '라이트' : '다크'} (전환)
      </button>
      <button type="button" className="btn-toggle" onClick={onIncrement} style={{ marginLeft: '0.5rem' }}>
        카운트 +1 (현재 {count})
      </button>
    </section>
  );
}

export default Toolbar;
