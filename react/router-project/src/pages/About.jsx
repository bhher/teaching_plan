export default function About() {
  return (
    <div className="page narrow">
      <p className="eyebrow">About</p>
      <h1>안녕하세요, StudioRoute입니다</h1>
      <p className="lead">
        React Router로 홈 · 소개 · 포트폴리오 · 연락처를 연결한 수업용 미니 사이트입니다.
      </p>

      <div className="about-grid">
        <div className="about-block">
          <h3>무엇을 하나요</h3>
          <p>웹 UI, 랜딩, 간단한 SPA 구성을 연습합니다. 슬라이드와 카드로 콘텐츠를 배치합니다.</p>
        </div>
        <div className="about-block">
          <h3>기술 스택</h3>
          <p>React 18 · Vite · React Router DOM v6 · CSS</p>
        </div>
        <div className="about-block">
          <h3>라우팅</h3>
          <p>Layout + Outlet 중첩 라우트, NavLink 활성 스타일을 사용합니다.</p>
        </div>
      </div>
    </div>
  );
}
