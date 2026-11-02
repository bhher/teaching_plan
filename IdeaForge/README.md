# IdeaForge

네이버 블로그 글 아이디어 생성기  
슬로건: **생각을 아이디어로, 아이디어를 현실로.**

랜딩 UX 참고: [ai-studio2-ruddy.vercel.app](https://ai-studio2-ruddy.vercel.app/)  
(브랜드·로고·API는 IdeaForge PRD 기준, 홈 구성·카피는 참고 사이트 흐름을 반영)

## 로컬 실행

1. 의존성 설치
   ```bash
   npm install
   ```
2. **`.env.local` 설정** (없으면 `.env.example`을 복사)
   ```bash
   copy .env.example .env.local
   ```
3. [Supabase](https://supabase.com) 프로젝트 → **Project Settings → API**
   - **Project URL** → `VITE_SUPABASE_URL` 과 `SUPABASE_URL`
   - **anon public** → `VITE_SUPABASE_ANON_KEY` 과 `SUPABASE_ANON_KEY`
   - **service_role** → `SUPABASE_SERVICE_ROLE_KEY` (서버만, 절대 공개 금지)
4. SQL Editor에서 `supabase/migrations/20261031000000_init.sql` 실행
5. Auth → Providers → Email: 테스트 시 **Confirm email OFF** 권장
6. 실행 (**env 수정 후 반드시 재시작**)
   ```bash
   npm run dev
   ```
   - 프론트: http://127.0.0.1:5173  
   - API: http://127.0.0.1:3001  

> 로그인 화면에 「Supabase 환경변수가 없습니다」가 보이면  
> `.env.local`의 `VITE_…` 값이 비어 있거나, 수정 후 **dev 서버를 재시작**하지 않은 경우입니다.

## 환경변수

| 키 | 위치 | 설명 |
|---|---|---|
| `VITE_SUPABASE_URL` | 클라 | Supabase URL |
| `VITE_SUPABASE_ANON_KEY` | 클라 | anon key |
| `SUPABASE_URL` | 서버 | 동일 URL |
| `SUPABASE_ANON_KEY` | 서버 | anon key |
| `SUPABASE_SERVICE_ROLE_KEY` | 서버만 | service_role (비공개) |
| `GEMINI_API_KEY` | 서버만 | Google AI Studio 키 |
| `GEMINI_MODEL` | 서버 | 기본 `gemini-3.1-flash-lite` |

## 최초 관리자 지정

SQL Editor에서:

```sql
update public.profiles
set role = 'admin'
where email = 'YOUR_EMAIL@example.com';

insert into public.admin_audit_logs (actor_id, action, target, after)
select id, 'role_bootstrap', id::text, jsonb_build_object('role','admin')
from public.profiles
where email = 'YOUR_EMAIL@example.com';
```

## 운영 인증 강화 (배포 전)

- Supabase Auth: 이메일 확인 ON
- 비밀번호 최소 길이·복잡도 강화
- 유출 비밀번호 차단 ON

## GitHub / Vercel (PRD)

1. 이 폴더(`IdeaForge`)를 Vercel **Root Directory**로 연결
2. Vercel Environment Variables에 로컬 `.env.local`과 동일한 키 등록  
   - 클라(빌드): `VITE_SUPABASE_URL`, `VITE_SUPABASE_ANON_KEY`  
   - 서버: `SUPABASE_URL`, `SUPABASE_ANON_KEY`, `SUPABASE_SERVICE_ROLE_KEY`, `GEMINI_API_KEY`, `GEMINI_MODEL`
3. Supabase Auth → URL Configuration에 배포 도메인 추가 (Site URL / Redirect URLs)
4. `api/index.js`가 Express API를 Vercel Serverless로 제공합니다
