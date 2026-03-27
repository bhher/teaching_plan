/**
 * ContentPanel — theme, count를 props로만 표시합니다.
 */
function ContentPanel({ theme, count }) {
  return (
    <section className="panel">
      <p className="note">
        <strong>ContentPanel</strong>은 부모가 넘긴 props로 화면을 그립니다.
      </p>
      <p>현재 테마 문자열: <code>{theme}</code></p>
      <p className="counter">{count}</p>
    </section>
  );
}

export default ContentPanel;
