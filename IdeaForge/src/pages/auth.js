import { layout, bindLayoutEvents } from '../ui/layout.js';
import { signIn, signUp, enterAdminMode, getAuthState, isLoggedIn } from '../lib/auth.js';
import { supabaseConfigured } from '../lib/supabaseClient.js';

function configBanner() {
  if (supabaseConfigured) return '';
  return `<div class="alert error">Supabase 환경변수가 없습니다. .env.local에 VITE_SUPABASE_URL / VITE_SUPABASE_ANON_KEY를 설정하세요.</div>`;
}

export function renderLogin(root) {
  root.innerHTML = layout(
    `
    <div class="card auth-box">
      <h2 class="page-title">로그인</h2>
      ${configBanner()}
      <div id="msg"></div>
      <form class="form-grid" id="login-form">
        <label>이메일<input name="email" type="email" required autocomplete="username" /></label>
        <label>비밀번호<input name="password" type="password" required minlength="6" autocomplete="current-password" /></label>
        <button class="btn" type="submit">로그인</button>
      </form>
      <p class="muted" style="margin-top:12px">계정이 없나요? <a href="#/signup">회원가입</a></p>
    </div>
  `,
    { active: 'home' }
  );
  bindLayoutEvents(root);
  root.querySelector('#login-form').addEventListener('submit', async (e) => {
    e.preventDefault();
    const fd = new FormData(e.target);
    const msg = root.querySelector('#msg');
    const btn = e.target.querySelector('button');
    btn.disabled = true;
    msg.innerHTML = '';
    try {
      await signIn({ email: fd.get('email'), password: fd.get('password') });
      const profile = getAuthState().profile;
      if (profile?.__queryError) {
        msg.innerHTML = `<div class="alert error">프로필 조회에 실패했습니다. 강제 로그아웃하지 않았습니다. 잠시 후 다시 시도하세요.</div>`;
      } else if (profile?.status === 'inactive') {
        msg.innerHTML = `<div class="alert error">정지된 계정입니다.</div>`;
      } else {
        location.hash = '#/ideas';
      }
    } catch (err) {
      msg.innerHTML = `<div class="alert error">${err.message || '로그인 실패'}</div>`;
    } finally {
      btn.disabled = false;
    }
  });
}

export function renderSignup(root) {
  root.innerHTML = layout(
    `
    <div class="card auth-box">
      <h2 class="page-title">회원가입</h2>
      ${configBanner()}
      <div id="msg"></div>
      <form class="form-grid" id="signup-form">
        <label>이메일<input name="email" type="email" required /></label>
        <label>비밀번호 (6자 이상)<input name="password" type="password" required minlength="6" /></label>
        <label>닉네임<input name="nickname" required maxlength="40" /></label>
        <label>실명 (선택)<input name="realName" maxlength="40" /></label>
        <button class="btn" type="submit">가입하기</button>
      </form>
    </div>
  `,
    { active: 'home' }
  );
  bindLayoutEvents(root);
  root.querySelector('#signup-form').addEventListener('submit', async (e) => {
    e.preventDefault();
    const fd = new FormData(e.target);
    const msg = root.querySelector('#msg');
    const btn = e.target.querySelector('button');
    btn.disabled = true;
    try {
      await signUp({
        email: String(fd.get('email')),
        password: String(fd.get('password')),
        nickname: String(fd.get('nickname')),
        realName: String(fd.get('realName') || ''),
      });
      // email confirm OFF → often already session; otherwise sign in
      if (!isLoggedIn()) {
        await signIn({ email: String(fd.get('email')), password: String(fd.get('password')) });
      }
      msg.innerHTML = `<div class="alert ok">가입이 완료되었습니다.</div>`;
      location.hash = '#/ideas';
    } catch (err) {
      msg.innerHTML = `<div class="alert error">${err.message || '가입 실패'}</div>`;
    } finally {
      btn.disabled = false;
    }
  });
}

export function renderAdminGate(root) {
  root.innerHTML = layout(
    `
    <div class="card auth-box admin-layout">
      <h2 class="page-title">관리자 로그인</h2>
      <p class="muted">회원 계정으로 로그인한 뒤, DB에서 admin으로 지정된 계정만 관리자 화면에 진입할 수 있습니다.</p>
      <div id="msg"></div>
      ${
        isLoggedIn()
          ? `<button class="btn" type="button" id="enter-admin">관리자 모드 시작</button>
             <p class="muted" style="margin-top:12px"><a href="#/admin/dashboard">대시보드로 이동</a></p>`
          : `<a class="btn" href="#/login">먼저 로그인</a>`
      }
    </div>
  `,
    { active: 'home' }
  );
  bindLayoutEvents(root);
  root.querySelector('#enter-admin')?.addEventListener('click', async () => {
    const msg = root.querySelector('#msg');
    try {
      await enterAdminMode();
      location.hash = '#/admin/dashboard';
    } catch (err) {
      msg.innerHTML = `<div class="alert error">${err.code === 'FORBIDDEN' ? '관리자 권한이 없습니다.' : err.message}</div>`;
    }
  });
}
