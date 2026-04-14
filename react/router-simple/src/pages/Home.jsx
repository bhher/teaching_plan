export default function Home() {
  return (
    <article className="page">
      <h1>홈</h1>
      <p className="lead">
        이 사이트는 <code>react-router-dom</code>의 <code>BrowserRouter</code>,{' '}
        <code>Routes</code>, <code>Route</code>, <code>NavLink</code>,{' '}
        <code>Outlet</code>으로 페이지를 나눈 예제입니다.
      </p>
      <ul className="feature-list">
        <li>
          <strong>소개</strong> — 라우팅 구조와 역할을 짧게 설명합니다.
        </li>
        <li>
          <strong>문의</strong> — 폼은 데모용이며 실제 전송은 하지 않습니다.
        </li>
      </ul>
    </article>
  );
}
