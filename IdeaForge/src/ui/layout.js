import {
  getAuthState,
  isLoggedIn,
  isAdmin,
  signOut,
  leaveAdminMode,
} from '../lib/auth.js';

export function layout(contentHtml, { active = 'home' } = {}) {
  const auth = getAuthState();
  const loggedIn = isLoggedIn();
  const adminOn = isAdmin();

  return `
  <div class="top-util">
    <div class="top-util-inner">
      <span>IdeaForge</span>
      <span>네이버 블로그 아이디어 생성기</span>
    </div>
  </div>
  <header class="site-header">
    <div class="nav-inner">
      <a class="brand" href="#/">
        <img src="/logo.png" alt="IdeaForge 로고" />
        <span>IdeaForge</span>
      </a>
      <nav class="nav-links" aria-label="주요 메뉴">
        <a href="#/" class="${active === 'home' ? 'active' : ''}">홈</a>
        <a href="#/ideas" class="${active === 'ideas' ? 'active' : ''}">아이디어 생성</a>
        <a href="#/community" class="${active === 'community' ? 'active' : ''}">커뮤니티</a>
        <a href="#/mypage" class="${active === 'mypage' ? 'active' : ''}">마이페이지</a>
      </nav>
      <div class="nav-actions">
        ${
          loggedIn
            ? `<button class="btn btn-secondary" type="button" data-action="logout">로그아웃</button>`
            : `<a class="btn btn-secondary" href="#/login">로그인</a>
               <a class="btn" href="#/signup">회원가입</a>`
        }
        <a class="btn btn-ghost" href="#/admin">관리자</a>
        ${adminOn ? `<span class="admin-on" aria-label="관리자 모드">관리자 ON</span>` : ''}
      </div>
    </div>
  </header>
  <div class="accent-bar">
    <div class="container">
      <span>생각을 아이디어로, 아이디어를 현실로.</span>
      <span>제목 추천 · 글 구성 · 커뮤니티</span>
    </div>
  </div>
  <main>
    <div class="container">${contentHtml}</div>
  </main>`;
}

export function bindLayoutEvents(root) {
  root.querySelector('[data-action="logout"]')?.addEventListener('click', async () => {
    leaveAdminMode();
    await signOut();
    location.hash = '#/';
  });
}
