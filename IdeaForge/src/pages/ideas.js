import { layout, bindLayoutEvents } from '../ui/layout.js';
import { isLoggedIn, isActive, getAuthState } from '../lib/auth.js';
import { api } from '../lib/api.js';
import { outlineToMarkdown, escapeHtml } from '../lib/markdown.js';

const CHIP = {
  audience: ['초보', '직장인', '부모', '취준생'],
  postType: ['후기', '가이드', '비교', '팁'],
  tone: ['친근', '전문', '유머', '담백'],
};

function renderOutline(outline) {
  if (!outline) return '';
  const sections = (outline.sections || [])
    .map(
      (s) => `
      <div>
        <h4>${escapeHtml(s.heading)}</h4>
        <ul>${(s.points || []).map((p) => `<li>${escapeHtml(p)}</li>`).join('')}</ul>
        ${s.example ? `<p class="muted">예시: ${escapeHtml(s.example)}</p>` : ''}
      </div>`
    )
    .join('');
  return `
    <div class="outline-block card">
      <div><h4>검색 의도</h4><p>${escapeHtml(outline.search_intent)}</p></div>
      <div><h4>도입부</h4><p>${escapeHtml(outline.intro)}</p></div>
      <div><h4>본문</h4>${sections}</div>
      <div><h4>마무리</h4><p>${escapeHtml(outline.outro)}</p></div>
      <div><h4>해시태그</h4><p>${escapeHtml((outline.hashtags || []).join(' '))}</p></div>
      <div><h4>이미지 아이디어</h4><ul>${(outline.image_ideas || []).map((i) => `<li>${escapeHtml(i)}</li>`).join('')}</ul></div>
      <div><h4>유의점</h4><ul>${(outline.notes || []).map((n) => `<li>${escapeHtml(n)}</li>`).join('')}</ul></div>
    </div>`;
}

export function renderIdeas(root) {
  if (!isLoggedIn()) {
    root.innerHTML = layout(
      `<div class="card"><h2 class="page-title">아이디어 생성</h2>
       <div class="alert">로그인이 필요합니다. <a href="#/login">로그인</a> 후 이용해 주세요.</div></div>`,
      { active: 'ideas' }
    );
    bindLayoutEvents(root);
    return;
  }
  if (getAuthState().profile?.__queryError) {
    root.innerHTML = layout(
      `<div class="card"><div class="alert error">프로필 조회 오류입니다. 비활성/빈 목록으로 오인하지 마세요. 새로고침 후 다시 시도하세요.</div></div>`,
      { active: 'ideas' }
    );
    bindLayoutEvents(root);
    return;
  }
  if (!isActive()) {
    root.innerHTML = layout(
      `<div class="card"><div class="alert error">정지된 계정입니다. 아이디어 생성을 사용할 수 없습니다.</div></div>`,
      { active: 'ideas' }
    );
    bindLayoutEvents(root);
    return;
  }

  const state = {
    keywords: '',
    audience: '',
    postType: '',
    tone: '',
    titles: [],
    selectedTitle: '',
    outline: null,
    usage: null,
    pending: null,
  };

  const paint = () => {
    root.innerHTML = layout(
      `
      <h2 class="page-title">아이디어 생성</h2>
      <div class="usage-bar" id="usage">
        ${
          state.usage
            ? `오늘 제목 ${state.usage.title_count}/${state.usage.title_limit} · 글 구성 ${state.usage.outline_count}/${state.usage.outline_limit} (KST 00:00 기준)`
            : '사용량 불러오는 중…'
        }
      </div>
      <div id="msg"></div>
      <div class="card stack">
        <form class="form-grid" id="idea-form">
          <label>키워드 (최대 10개, 쉼표/줄바꿈)
            <textarea name="keywords" rows="3" required placeholder="예: 재택근무, 생산성, 루틴">${escapeHtml(state.keywords)}</textarea>
          </label>
          <label>독자층
            <input name="audience" maxlength="80" value="${escapeHtml(state.audience)}" placeholder="예: 직장인 초보" />
          </label>
          <div class="chips" data-chip="audience">${CHIP.audience.map((c) => `<button type="button" class="chip" data-val="${c}">${c}</button>`).join('')}</div>
          <label>글 유형
            <input name="postType" maxlength="80" value="${escapeHtml(state.postType)}" />
          </label>
          <div class="chips" data-chip="postType">${CHIP.postType.map((c) => `<button type="button" class="chip" data-val="${c}">${c}</button>`).join('')}</div>
          <label>톤
            <input name="tone" maxlength="80" value="${escapeHtml(state.tone)}" />
          </label>
          <div class="chips" data-chip="tone">${CHIP.tone.map((c) => `<button type="button" class="chip" data-val="${c}">${c}</button>`).join('')}</div>
          <button class="btn" type="submit" id="btn-titles" ${state.pending ? 'disabled' : ''}>
            ${state.pending === 'titles' ? '<span class="spinner"></span> 생성 중' : '추천 제목 생성'}
          </button>
        </form>

        ${
          state.titles.length
            ? `<div>
                <h3>추천 제목 (하나만 선택)</h3>
                <div class="radio-list">
                  ${state.titles
                    .map(
                      (t, i) => `
                    <label class="radio-item">
                      <input type="radio" name="title" value="${escapeHtml(t)}" ${state.selectedTitle === t ? 'checked' : ''} />
                      <span>${i + 1}. ${escapeHtml(t)}</span>
                    </label>`
                    )
                    .join('')}
                </div>
                <button class="btn" type="button" id="btn-outline" ${state.pending || !state.selectedTitle ? 'disabled' : ''}>
                  ${state.pending === 'outline' ? '<span class="spinner"></span> 생성 중' : '네이버 블로그 글 구성 생성'}
                </button>
              </div>`
            : ''
        }

        ${state.outline ? renderOutline(state.outline) : ''}

        ${
          state.outline
            ? `<div class="actions-row">
                <button class="btn btn-secondary" type="button" id="btn-copy">전체 복사</button>
                <button class="btn" type="button" id="btn-save" ${state.pending === 'save' ? 'disabled' : ''}>저장</button>
                <button class="btn btn-ghost" type="button" id="btn-reset">새 아이디어 생성</button>
              </div>`
            : ''
        }
      </div>
    `,
      { active: 'ideas' }
    );
    bindLayoutEvents(root);
    wire();
  };

  const snapshotForm = () => {
    const form = root.querySelector('#idea-form');
    if (!form) return;
    const fd = new FormData(form);
    state.keywords = String(fd.get('keywords') || '');
    state.audience = String(fd.get('audience') || '');
    state.postType = String(fd.get('postType') || '');
    state.tone = String(fd.get('tone') || '');
  };

  const showMsg = (html) => {
    const el = root.querySelector('#msg');
    if (el) el.innerHTML = html;
  };

  function wire() {
    root.querySelectorAll('.chips').forEach((group) => {
      const field = group.dataset.chip;
      group.querySelectorAll('.chip').forEach((btn) => {
        btn.addEventListener('click', () => {
          const input = root.querySelector(`[name="${field}"]`);
          if (input) input.value = btn.dataset.val;
        });
      });
    });

    root.querySelector('#idea-form')?.addEventListener('submit', async (e) => {
      e.preventDefault();
      if (state.pending) return;
      snapshotForm();
      state.pending = 'titles';
      paint();
      try {
        const res = await api('/api/generate/titles', {
          method: 'POST',
          body: JSON.stringify({
            keywords: state.keywords,
            audience: state.audience,
            postType: state.postType,
            tone: state.tone,
          }),
        });
        state.titles = res.titles;
        state.selectedTitle = '';
        state.outline = null;
        state.usage = res.usage;
        showMsg(`<div class="alert ok">추천 제목 5개가 생성되었습니다.</div>`);
      } catch (err) {
        showMsg(`<div class="alert error">${err.message}</div>`);
      } finally {
        state.pending = null;
        paint();
      }
    });

    root.querySelectorAll('input[name="title"]').forEach((r) => {
      r.addEventListener('change', () => {
        state.selectedTitle = r.value;
        paint();
      });
    });

    root.querySelector('#btn-outline')?.addEventListener('click', async () => {
      if (state.pending || !state.selectedTitle) return;
      snapshotForm();
      state.pending = 'outline';
      paint();
      try {
        const res = await api('/api/generate/outline', {
          method: 'POST',
          body: JSON.stringify({
            keywords: state.keywords,
            audience: state.audience,
            postType: state.postType,
            tone: state.tone,
            title: state.selectedTitle,
          }),
        });
        state.outline = res.outline;
        state.usage = res.usage;
      } catch (err) {
        showMsg(`<div class="alert error">${err.message}</div>`);
      } finally {
        state.pending = null;
        paint();
      }
    });

    root.querySelector('#btn-copy')?.addEventListener('click', async () => {
      const md = outlineToMarkdown(state.selectedTitle, state.outline);
      await navigator.clipboard.writeText(md);
      showMsg(`<div class="alert ok">마크다운으로 복사했습니다.</div>`);
    });

    root.querySelector('#btn-save')?.addEventListener('click', async () => {
      if (state.pending) return;
      snapshotForm();
      state.pending = 'save';
      paint();
      try {
        await api('/api/ideas', {
          method: 'POST',
          body: JSON.stringify({
            keywords: state.keywords,
            audience: state.audience,
            postType: state.postType,
            tone: state.tone,
            selectedTitle: state.selectedTitle,
            outline: state.outline,
          }),
        });
        showMsg(`<div class="alert ok">저장되었습니다. 마이페이지에서 확인할 수 있습니다.</div>`);
      } catch (err) {
        showMsg(`<div class="alert error">저장 실패: ${err.message}</div>`);
      } finally {
        state.pending = null;
        paint();
      }
    });

    root.querySelector('#btn-reset')?.addEventListener('click', () => {
      state.keywords = '';
      state.audience = '';
      state.postType = '';
      state.tone = '';
      state.titles = [];
      state.selectedTitle = '';
      state.outline = null;
      paint();
      showMsg(`<div class="alert ok">초기화했습니다. (사용량은 차감되지 않습니다)</div>`);
    });
  }

  paint();
  api('/api/usage')
    .then((u) => {
      state.usage = u;
      const el = root.querySelector('#usage');
      if (el) {
        el.textContent = `오늘 제목 ${u.title_count}/${u.title_limit} · 글 구성 ${u.outline_count}/${u.outline_limit} (KST 00:00 기준)`;
      }
    })
    .catch((err) => {
      showMsg(`<div class="alert error">사용량 조회 오류: ${err.message}</div>`);
    });
}
