import { initAuth, onAuthChange } from './lib/auth.js';
import { renderHome } from './pages/home.js';
import { renderLogin, renderSignup, renderAdminGate } from './pages/auth.js';
import { renderIdeas } from './pages/ideas.js';
import {
  renderCommunity,
  renderCommunityNew,
  renderCommunityDetail,
} from './pages/community.js';
import { renderMyPage } from './pages/mypage.js';
import {
  renderAdminDashboard,
  renderAdminMembers,
  renderAdminActivity,
  renderAdminKeywords,
  renderAdminSettings,
} from './pages/admin.js';

const root = document.getElementById('app');

async function route() {
  const hash = location.hash || '#/';
  const path = hash.replace(/^#/, '') || '/';

  if (path === '/' || path === '') return renderHome(root);
  if (path === '/login') return renderLogin(root);
  if (path === '/signup') return renderSignup(root);
  if (path === '/ideas') return renderIdeas(root);
  if (path === '/community') return renderCommunity(root);
  if (path === '/community/new') return renderCommunityNew(root);
  if (path.startsWith('/community/')) return renderCommunityDetail(root, path.split('/')[2]);
  if (path === '/mypage') return renderMyPage(root);
  if (path === '/admin') return renderAdminGate(root);
  if (path === '/admin/dashboard') return renderAdminDashboard(root);
  if (path === '/admin/members') return renderAdminMembers(root);
  if (path === '/admin/activity') return renderAdminActivity(root);
  if (path === '/admin/keywords') return renderAdminKeywords(root);
  if (path === '/admin/settings') return renderAdminSettings(root);

  root.innerHTML = `<div class="container" style="padding:40px 0"><div class="card">페이지를 찾을 수 없습니다. <a href="#/">홈</a></div></div>`;
}

async function boot() {
  await initAuth();
  window.addEventListener('hashchange', () => route());
  onAuthChange(() => route());
  await route();
}

boot();
