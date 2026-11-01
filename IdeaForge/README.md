# IdeaForge

네이버 블로그 글 아이디어 생성기  
슬로건: **생각을 아이디어로, 아이디어를 현실로.**

## 로컬 실행

1. 의존성 설치
   ```bash
   npm install
   ```
2. `.env.local` 작성 (`.env.example` 참고)
3. Supabase 마이그레이션 적용
4. 실행
   ```bash
   npm run dev
   ```
   - 프론트: http://127.0.0.1:5173  
   - API: http://127.0.0.1:3001  

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

## GitHub / Vercel

요청 시 Chrome 확장으로 연결합니다. 지금은 로컬 검증만 진행합니다.
