import { layout, bindLayoutEvents } from '../ui/layout.js';

export function renderHome(root) {
  root.innerHTML = layout(
    `
    <section class="hero">
      <div class="hero-card">
        <h1>네이버 블로그 글<br />아이디어 생성기</h1>
        <p class="slogan">생각을 아이디어로, 아이디어를 현실로.</p>
        <a class="btn btn-grad" href="#/ideas">아이디어 생성하기</a>
      </div>
      <div class="hero-visual" aria-hidden="true">
        <img src="/logo.png" alt="" />
      </div>
    </section>
    <section class="grid-3">
      <article class="card">
        <h3>추천 제목 5개</h3>
        <p>키워드·독자·유형·톤을 반영한 서로 다른 제목을 한 번에 제안합니다.</p>
      </article>
      <article class="card">
        <h3>네이버 블로그 글 구성</h3>
        <p>검색 의도부터 소제목·해시태그·이미지 아이디어까지 바로 쓸 수 있게 구성합니다.</p>
      </article>
      <article class="card">
        <h3>커뮤니티</h3>
        <p>회원들과 글·댓글로 아이디어를 나누고, 마이페이지에서 저장 콘텐츠를 관리합니다.</p>
      </article>
    </section>
  `,
    { active: 'home' }
  );
  bindLayoutEvents(root);
}
