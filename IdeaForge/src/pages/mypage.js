import { layout, bindLayoutEvents } from '../ui/layout.js';
import { supabase } from '../lib/supabaseClient.js';
import { getAuthState, isLoggedIn } from '../lib/auth.js';
import { escapeHtml } from '../lib/markdown.js';

function openIdeaModal(idea) {
  const backdrop = document.createElement('div');
  backdrop.className = 'modal-backdrop';
  backdrop.innerHTML = `
    <div class="modal" role="dialog" aria-modal="true">
      <div class="modal-header">
        <strong>저장 콘텐츠 상세</strong>
        <button type="button" class="btn btn-secondary" data-close>닫기</button>
      </div>
      <div class="table-wrap">
        <table>
          <tbody>
            <tr><th>항목</th><th>내용</th></tr>
            <tr><td>선택한 제목</td><td>${escapeHtml(idea.selected_title)}</td></tr>
            <tr><td>키워드</td><td>${escapeHtml((idea.keywords || []).join(', '))}</td></tr>
            <tr><td>독자층</td><td>${escapeHtml(idea.audience)}</td></tr>
            <tr><td>글 유형</td><td>${escapeHtml(idea.post_type)}</td></tr>
            <tr><td>톤</td><td>${escapeHtml(idea.tone)}</td></tr>
            <tr><td>검색 의도</td><td>${escapeHtml(idea.outline?.search_intent)}</td></tr>
            <tr><td>도입부</td><td>${escapeHtml(idea.outline?.intro)}</td></tr>
            <tr><td>본문</td><td>${escapeHtml(
              (idea.outline?.sections || [])
                .map(
                  (s) =>
                    `${s.heading}\n- ${(s.points || []).join('\n- ')}\n예시: ${s.example || ''}`
                )
                .join('\n\n')
            )}</td></tr>
            <tr><td>마무리</td><td>${escapeHtml(idea.outline?.outro)}</td></tr>
            <tr><td>해시태그</td><td>${escapeHtml((idea.outline?.hashtags || []).join(' '))}</td></tr>
            <tr><td>이미지 아이디어</td><td>${escapeHtml((idea.outline?.image_ideas || []).join('\n'))}</td></tr>
            <tr><td>유의점</td><td>${escapeHtml((idea.outline?.notes || []).join('\n'))}</td></tr>
            <tr><td>저장 시각</td><td>${escapeHtml(new Date(idea.created_at).toLocaleString('ko-KR'))}</td></tr>
          </tbody>
        </table>
      </div>
    </div>`;
  const close = () => backdrop.remove();
  backdrop.addEventListener('click', (e) => {
    if (e.target === backdrop) close();
  });
  backdrop.querySelector('[data-close]').addEventListener('click', close);
  document.addEventListener(
    'keydown',
    function onKey(e) {
      if (e.key === 'Escape') {
        close();
        document.removeEventListener('keydown', onKey);
      }
    },
    { once: true }
  );
  document.body.appendChild(backdrop);
}

export async function renderMyPage(root, tab = 'activity') {
  if (!isLoggedIn()) {
    root.innerHTML = layout(
      `<div class="card"><div class="alert">로그인이 필요합니다. <a href="#/login">로그인</a></div></div>`,
      { active: 'mypage' }
    );
    bindLayoutEvents(root);
    return;
  }

  const me = getAuthState().session.user.id;
  const profile = getAuthState().profile;

  root.innerHTML = layout(`<div class="card muted">불러오는 중…</div>`, { active: 'mypage' });
  bindLayoutEvents(root);

  if (profile?.__queryError) {
    root.innerHTML = layout(
      `<div class="alert error">프로필 조회 오류입니다. 계정 상태/활동 0건으로 오표시하지 않습니다.</div>`,
      { active: 'mypage' }
    );
    bindLayoutEvents(root);
    return;
  }

  try {
    const [{ data: posts, error: perr }, { data: comments, error: cerr }, { data: ideas, error: ierr }] =
      await Promise.all([
        supabase.from('posts').select('id, title, created_at').eq('author_id', me).order('created_at', { ascending: false }),
        supabase.from('comments').select('id, body, post_id, created_at').eq('author_id', me).order('created_at', { ascending: false }),
        supabase.from('saved_ideas').select('*').eq('user_id', me).order('created_at', { ascending: false }),
      ]);

    if (perr || cerr || ierr) {
      throw new Error(perr?.message || cerr?.message || ierr?.message || 'QUERY_ERROR');
    }

    root.innerHTML = layout(
      `
      <h2 class="page-title">마이페이지</h2>
      <p class="muted">${escapeHtml(profile?.nickname || '')} 님의 활동과 저장 콘텐츠</p>
      <div class="tabs">
        <button type="button" class="${tab === 'activity' ? 'active' : ''}" data-tab="activity">커뮤니티 활동</button>
        <button type="button" class="${tab === 'saved' ? 'active' : ''}" data-tab="saved">저장 콘텐츠</button>
      </div>
      <div id="panel"></div>
    `,
      { active: 'mypage' }
    );
    bindLayoutEvents(root);

    const panel = root.querySelector('#panel');
    const paintTab = (t) => {
      if (t === 'saved') {
        panel.innerHTML =
          (ideas || [])
            .map(
              (idea) => `
            <article class="card list-card" style="margin-bottom:12px">
              <strong>${escapeHtml(idea.selected_title)}</strong>
              <div class="meta">${new Date(idea.created_at).toLocaleString('ko-KR')} · ${(idea.keywords || []).map(escapeHtml).join(', ')}</div>
              <button class="btn btn-secondary" type="button" data-idea="${idea.id}">상세 보기</button>
            </article>`
            )
            .join('') || `<div class="card muted">저장한 콘텐츠가 없습니다.</div>`;
        panel.querySelectorAll('[data-idea]').forEach((btn) => {
          btn.addEventListener('click', () => {
            const idea = (ideas || []).find((x) => x.id === btn.getAttribute('data-idea'));
            if (idea) openIdeaModal(idea);
          });
        });
      } else {
        panel.innerHTML = `
          <div class="stack">
            <div class="card">
              <h3>내 게시글</h3>
              ${(posts || [])
                .map(
                  (p) =>
                    `<div class="list-card"><a href="#/community/${p.id}">${escapeHtml(p.title)}</a><div class="meta">${new Date(p.created_at).toLocaleString('ko-KR')}</div></div>`
                )
                .join('') || '<p class="muted">작성한 글이 없습니다.</p>'}
            </div>
            <div class="card">
              <h3>내 댓글</h3>
              ${(comments || [])
                .map(
                  (c) =>
                    `<div class="list-card"><a href="#/community/${c.post_id}">${escapeHtml(c.body.slice(0, 80))}</a><div class="meta">${new Date(c.created_at).toLocaleString('ko-KR')}</div></div>`
                )
                .join('') || '<p class="muted">작성한 댓글이 없습니다.</p>'}
            </div>
          </div>`;
      }
    };

    paintTab(tab);
    root.querySelectorAll('[data-tab]').forEach((btn) => {
      btn.addEventListener('click', () => {
        root.querySelectorAll('[data-tab]').forEach((b) => b.classList.remove('active'));
        btn.classList.add('active');
        paintTab(btn.dataset.tab);
      });
    });
  } catch (err) {
    root.innerHTML = layout(
      `<div class="alert error">조회 오류: ${escapeHtml(err.message)}</div>`,
      { active: 'mypage' }
    );
    bindLayoutEvents(root);
  }
}
