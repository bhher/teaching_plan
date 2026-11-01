import { layout, bindLayoutEvents } from '../ui/layout.js';
import { isAdmin, enterAdminMode, isLoggedIn } from '../lib/auth.js';
import { api } from '../lib/api.js';
import { escapeHtml } from '../lib/markdown.js';
import { renderAdminGate } from './auth.js';

async function ensureAdmin(root) {
  if (!isLoggedIn()) {
    renderAdminGate(root);
    return false;
  }
  if (!isAdmin()) {
    try {
      await enterAdminMode();
    } catch {
      renderAdminGate(root);
      return false;
    }
  }
  return true;
}

function adminShell(inner) {
  return `
    <div class="admin-layout stack">
      <div style="display:flex;justify-content:space-between;gap:12px;flex-wrap:wrap;align-items:center">
        <h2 class="page-title" style="margin:0">관리자</h2>
        <span class="admin-on">관리자 ON</span>
      </div>
      <div class="tabs">
        <a class="btn btn-secondary" href="#/admin/dashboard">대시보드</a>
        <a class="btn btn-secondary" href="#/admin/members">회원</a>
        <a class="btn btn-secondary" href="#/admin/activity">활동</a>
        <a class="btn btn-secondary" href="#/admin/keywords">TOP 5 키워드</a>
        <a class="btn btn-secondary" href="#/admin/settings">일일 한도</a>
      </div>
      ${inner}
    </div>`;
}

export async function renderAdminDashboard(root) {
  if (!(await ensureAdmin(root))) return;
  root.innerHTML = layout(adminShell(`<div class="card muted">불러오는 중…</div>`), { active: 'home' });
  bindLayoutEvents(root);
  try {
    const d = await api('/api/admin/dashboard');
    root.innerHTML = layout(
      adminShell(`
        <div class="stat-grid">
          <div class="stat"><span class="muted">회원</span><strong>${d.members}</strong></div>
          <div class="stat"><span class="muted">게시글</span><strong>${d.posts}</strong></div>
          <div class="stat"><span class="muted">댓글</span><strong>${d.comments}</strong></div>
          <div class="stat"><span class="muted">오늘 제목 생성</span><strong>${d.titleToday}</strong></div>
          <div class="stat"><span class="muted">오늘 글 구성</span><strong>${d.outlineToday}</strong></div>
        </div>`),
      { active: 'home' }
    );
    bindLayoutEvents(root);
  } catch (err) {
    root.innerHTML = layout(adminShell(`<div class="alert error">${escapeHtml(err.message)}</div>`), { active: 'home' });
    bindLayoutEvents(root);
  }
}

export async function renderAdminMembers(root) {
  if (!(await ensureAdmin(root))) return;
  root.innerHTML = layout(adminShell(`<div class="card muted">불러오는 중…</div>`), { active: 'home' });
  bindLayoutEvents(root);

  const load = async (q = '') => {
    const { members } = await api(`/api/admin/members?q=${encodeURIComponent(q)}`);
    root.innerHTML = layout(
      adminShell(`
        <div class="card stack">
          <form id="search" class="actions-row">
            <input name="q" placeholder="이메일·닉네임 검색" value="${escapeHtml(q)}" style="flex:1" />
            <button class="btn" type="submit">검색</button>
          </form>
          <div class="table-wrap">
            <table>
              <thead><tr><th>닉네임</th><th>이메일</th><th>역할</th><th>상태</th><th>조치</th></tr></thead>
              <tbody>
                ${(members || [])
                  .map(
                    (m) => `
                  <tr>
                    <td>${escapeHtml(m.nickname)}</td>
                    <td>${escapeHtml(m.email)}</td>
                    <td>${escapeHtml(m.role)}</td>
                    <td>${escapeHtml(m.status)}</td>
                    <td>
                      <button class="btn btn-secondary" data-toggle-status="${m.id}" data-status="${m.status}">${m.status === 'active' ? '비활성' : '활성'}</button>
                    </td>
                  </tr>`
                  )
                  .join('')}
              </tbody>
            </table>
          </div>
        </div>`),
      { active: 'home' }
    );
    bindLayoutEvents(root);
    root.querySelector('#search').addEventListener('submit', (e) => {
      e.preventDefault();
      load(new FormData(e.target).get('q'));
    });
    root.querySelectorAll('[data-toggle-status]').forEach((btn) => {
      btn.addEventListener('click', async () => {
        const id = btn.getAttribute('data-toggle-status');
        const cur = btn.getAttribute('data-status');
        const next = cur === 'active' ? 'inactive' : 'active';
        await api(`/api/admin/members/${id}`, { method: 'PATCH', body: JSON.stringify({ status: next }) });
        load(q);
      });
    });
  };

  try {
    await load();
  } catch (err) {
    root.innerHTML = layout(adminShell(`<div class="alert error">${escapeHtml(err.message)}</div>`), { active: 'home' });
    bindLayoutEvents(root);
  }
}

export async function renderAdminActivity(root) {
  if (!(await ensureAdmin(root))) return;
  try {
    const data = await api('/api/admin/activity');
    root.innerHTML = layout(
      adminShell(`
        <div class="grid-3">
          <div class="card"><h3>최근 저장</h3>${(data.ideas || []).map((i) => `<div class="list-card"><div>${escapeHtml(i.selected_title)}</div><div class="meta">${new Date(i.created_at).toLocaleString('ko-KR')}</div></div>`).join('') || '<p class="muted">없음</p>'}</div>
          <div class="card"><h3>최근 글</h3>${(data.posts || []).map((p) => `<div class="list-card"><div>${escapeHtml(p.title)}</div><div class="meta">${new Date(p.created_at).toLocaleString('ko-KR')}</div></div>`).join('') || '<p class="muted">없음</p>'}</div>
          <div class="card"><h3>최근 댓글</h3>${(data.comments || []).map((c) => `<div class="list-card"><div>${escapeHtml(c.body.slice(0, 60))}</div><div class="meta">${new Date(c.created_at).toLocaleString('ko-KR')}</div></div>`).join('') || '<p class="muted">없음</p>'}</div>
        </div>`),
      { active: 'home' }
    );
    bindLayoutEvents(root);
  } catch (err) {
    root.innerHTML = layout(adminShell(`<div class="alert error">${escapeHtml(err.message)}</div>`), { active: 'home' });
    bindLayoutEvents(root);
  }
}

export async function renderAdminKeywords(root) {
  if (!(await ensureAdmin(root))) return;
  try {
    const { top } = await api('/api/admin/keywords/top');
    root.innerHTML = layout(
      adminShell(`
        <div class="card">
          <h3>TOP 5 키워드</h3>
          <ol>${(top || []).map((t) => `<li>${escapeHtml(t.keyword)} (${t.count})</li>`).join('') || '<li class="muted">데이터 없음</li>'}</ol>
        </div>`),
      { active: 'home' }
    );
    bindLayoutEvents(root);
  } catch (err) {
    root.innerHTML = layout(adminShell(`<div class="alert error">${escapeHtml(err.message)}</div>`), { active: 'home' });
    bindLayoutEvents(root);
  }
}

export async function renderAdminSettings(root) {
  if (!(await ensureAdmin(root))) return;
  try {
    const limits = await api('/api/admin/settings/limits');
    root.innerHTML = layout(
      adminShell(`
        <div class="card">
          <h3>일일 한도 설정</h3>
          <div id="msg"></div>
          <form class="form-grid" id="limits-form">
            <label>제목 생성 한도<input name="daily_title_limit" type="number" min="0" value="${limits.daily_title_limit}" required /></label>
            <label>글 구성 생성 한도<input name="daily_outline_limit" type="number" min="0" value="${limits.daily_outline_limit}" required /></label>
            <button class="btn" type="submit">저장</button>
          </form>
        </div>`),
      { active: 'home' }
    );
    bindLayoutEvents(root);
    root.querySelector('#limits-form').addEventListener('submit', async (e) => {
      e.preventDefault();
      const fd = new FormData(e.target);
      const btn = e.target.querySelector('button');
      btn.disabled = true;
      try {
        const saved = await api('/api/admin/settings/limits', {
          method: 'PUT',
          body: JSON.stringify({
            daily_title_limit: Number(fd.get('daily_title_limit')),
            daily_outline_limit: Number(fd.get('daily_outline_limit')),
          }),
        });
        const again = await api('/api/admin/settings/limits');
        root.querySelector('#msg').innerHTML = `<div class="alert ok">저장·재조회 완료 (제목 ${again.daily_title_limit} / 글구성 ${again.daily_outline_limit})</div>`;
        e.target.daily_title_limit.value = saved.daily_title_limit;
        e.target.daily_outline_limit.value = saved.daily_outline_limit;
      } catch (err) {
        root.querySelector('#msg').innerHTML = `<div class="alert error">${escapeHtml(err.message)}</div>`;
      } finally {
        btn.disabled = false;
      }
    });
  } catch (err) {
    root.innerHTML = layout(adminShell(`<div class="alert error">${escapeHtml(err.message)}</div>`), { active: 'home' });
    bindLayoutEvents(root);
  }
}
