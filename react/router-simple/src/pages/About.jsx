export default function About() {
  return (
    <article className="page">
      <h1>소개</h1>
      <p>
        <code>App.jsx</code>에서 경로별로 <code>element</code>를 연결하고, 공통 뼈대는{' '}
        <code>Layout</code> 한 곳에 둡니다. 자식 페이지는 <code>Outlet</code> 위치에
        렌더링됩니다.
      </p>
      <section className="card-block">
        <h2>경로 요약</h2>
        <table className="route-table">
          <thead>
            <tr>
              <th>URL</th>
              <th>컴포넌트</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>
                <code>/</code>
              </td>
              <td>Home</td>
            </tr>
            <tr>
              <td>
                <code>/about</code>
              </td>
              <td>About</td>
            </tr>
            <tr>
              <td>
                <code>/contact</code>
              </td>
              <td>Contact</td>
            </tr>
            <tr>
              <td>
                <code>/*</code> (없는 주소)
              </td>
              <td>NotFound</td>
            </tr>
          </tbody>
        </table>
      </section>
    </article>
  );
}
