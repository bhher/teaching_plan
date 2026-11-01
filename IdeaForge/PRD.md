# IdeaForge PRD — 블로그 아이디어 생성기

| 항목 | 내용 |
|---|---|
| **제품명** | IdeaForge |
| **슬로건** | 생각을 아이디어로, 아이디어를 현실로. |
| **한 줄** | 키워드·독자·유형·톤으로 네이버 블로그용 **추천 제목 5개**와 **글 구성**을 만들고, 커뮤니티·저장·관리 기능을 제공하는 웹앱 |
| **문서 목적** | 구현 가능한 기획·권한·API·인수 기준 정의 |
| **참조 자산** | `assets/logo.png`, `assets/참고웹화면.png` |

---

## 0. 가정·기본값 (미정 사항 확정)

| 항목 | 기본값 |
|---|---|
| 스택 | 정적 **HTML/CSS/JS** 프론트 + **Vercel Serverless**(또는 동등 Node API) + **Supabase**(Auth·Postgres·RLS) |
| LLM | **`gemini-3.1-flash-lite`** ([공식 모델](https://ai.google.dev/gemini-api/docs/models/gemini-3.1-flash-lite) 확인됨). 구현 직전 엔드포인트 재확인. 불가 시 `gemini-3.5-flash-lite`로 폴백하고 README에 기록 |
| 일일 한도(기본) | 제목 생성 **10회/일**, 글 구성 생성 **5회/일** (관리자 화면에서 변경) |
| 일일 기준 시각 | **KST 00:00** (저장·차감 기준 타임존 `Asia/Seoul`) |
| 사용량 차감 | **제목 생성 성공 시 1**, **글 구성 생성 성공 시 1**. 「새 아이디어 생성」은 **화면 초기화만** (차감 없음). ※요구문에 ‘새 아이디어=사용량 1감소’ 표현이 있으나, 5절 ‘성공 시 차감’과 충돌 → **성공 차감만** 채택 |
| 키워드 | 최대 **10개**, 각 키워드 최대 30자, 쉼표/엔터 구분 |
| 독자층·글 유형·톤 | 자유 텍스트(각 80자) + 추천 칩 제공(예: 초보/직장인, 후기/가이드, 친근/전문) |
| 커뮤니티 | 게시글·댓글만(대댓글·좋아요·신고 **1차 미구현**) |
| 회원 필드 | 이메일, 비밀번호, **닉네임**(필수), 실명(선택). 프로필 `status=active\|inactive`, `role=member\|admin` |
| 최초 관리자 | `profiles`에 `role=admin`을 **SQL Editor 1회 지정** + `admin_audit_logs`에 `bootstrap` 기록. 운영 강화: 이메일 확인 ON, 비밀번호 정책 강화 |
| 세션 | Supabase Auth 세션(로컬 스토리지/쿠키) 유지. 새로고침 후 `getSession`으로 복원 |
| 비밀키 | `GEMINI_API_KEY`, `SUPABASE_SERVICE_ROLE_KEY`는 **서버 전용**. 브라우저는 `SUPABASE_URL` + `SUPABASE_ANON_KEY`만 |
| 환경파일 | 로컬 `.env.local`, 배포 시 Vercel Env. `.env.example`만 커밋 |

---

## 1. 화면·사용 흐름

### 1.1 UX·디자인 방향

- **브랜드**: `logo.png`의 **주황→마젠타→블루** 대각 그라데이션 스쿼클 아이콘을 헤더·파비콘·주요 CTA 하이라이트에 사용. 제품 표기는 **ideaForget**(로고 파일 내 문구와 달라도 UI 카피는 ideaForget 우선).
- **레이아웃 참조**: `참고웹화면.png`의 **흰 배경 + 카드(벤토) 그리드 + 상단 유틸/메인 네비 + 딥블루 액센트 바**.
- **컬러 토큰(가정)**  
  - `--brand-deep: #0D47A1` (네비·주요 버튼, 참고 화면 딥블루 계열)  
  - `--brand-grad: linear-gradient(135deg, #FF6A3D → #C43BFF → #2F5BFF)` (로고·히어로 포인트)  
  - `--admin-on: #C62828` (관리자 ON 전용)  
  - 배경 `#F5F7FA` / 카드 `#FFFFFF` / 본문 `#212121` / 보조 `#757575`
- **타이포**: 한국어 산세리프(예: Pretendard/Noto Sans KR). 제목 Bold, 본문 Regular, 메타 텍스트 muted.
- **컴포넌트**: 둥근 카드(12–16px), 얇은 보더·얕은 그림자. 히어로에 통계 나열·배지 스티커 남발 금지. **한 섹션 한 목적**.
- **반응형**: ≥1024px 2–3열 카드 / ≤767px 1열 스택. 저장 상세 모달은 모바일 전체 폭·세로 스크롤.
- **접근성**: 포커스 링, 버튼 disabled 시 이유 문구, 모달 Esc·배경 클릭 닫기.

### 1.2 공통 헤더(메뉴 순서 고정)

`[로고 ideaForget]` · **홈** · **아이디어 생성** · **커뮤니티** · **마이페이지** · **로그인** / (로그인 시 **로그아웃**) · **회원가입** · **관리자**

- 「관리자」는 회원가입 버튼 **오른쪽**의 별도 진입(관리자 로그인/대시보드).
- 비로그인: 마이페이지·아이디어 생성 클릭 시 로그인 유도.
- 관리자 세션 유효 시 **모든 관리자 화면 우상단**에 굵은 빨간 **「관리자 ON」**만 표시(**닉네임·실명·이메일 비노출**).

### 1.3 홈

- 히어로: 로고 그라데이션 포인트 + 「블로그 아이디어, 잊기 전에 완성」 + CTA「아이디어 생성하기」.
- 하단 카드 3개: 제목 추천 / 글 구성 / 커뮤니티(참고 화면형 카드 그리드).

### 1.4 아이디어 생성 (한 화면 단계형)

1. **입력**: 키워드(≤10) · 독자층 · 글 유형 · 톤 → 「추천 제목 생성」  
2. **제목 5개**: 서로 다른 추천 제목을 **라디오**로 선택 → 「네이버 블로그 글 구성 생성」  
3. **글 구성 출력 필드**: 검색 의도 · 도입부 · 본문(소제목·핵심 포인트·예시) · 마무리 · 해시태그 · 이미지 아이디어 · 유의점  
4. **하단 버튼**: **전체 복사** · **저장** · **새 아이디어 생성**

| 버튼 | 동작 |
|---|---|
| 전체 복사 | 제목 계층·문단·목록·강조가 유지되는 **마크다운**으로 클립보드 복사 |
| 저장 | 키워드·독자층·글 유형·톤·선택 제목·글 구성 JSON/구조화 컬럼을 DB 저장(로그인·활성 필수) |
| 새 아이디어 생성 | 입력·결과·선택 상태 **초기화**(사용량 차감 없음) |

- 생성 중: 해당 버튼 disabled + 스피너. 실패 시 입력·선택값 유지·버튼 복구.
- 남은 횟수 표시: `오늘 제목 N/한도 · 글 구성 M/한도`.

### 1.5 커뮤니티

- 목록(카드) → 상세(본문 + 댓글).
- 작성자 본인: 글/댓글 **수정·삭제**.
- **타인 글**: 활성 회원만 댓글 작성란 표시.
- **본인 글**: 댓글 작성란 **숨김**(조회는 가능).
- 비로그인: 댓글란 대신 「로그인 후 댓글을 작성할 수 있습니다」.

### 1.6 마이페이지

- 탭: **커뮤니티 활동**(내 글·내 댓글) · **저장 콘텐츠**.
- 본인 `author_id`/`user_id`만 필터.
- 저장 상세: JSON raw 금지 → **「항목 / 내용」표 모달**. 긴 문장 줄바꿈, 목록 불릿, 모바일 스크롤. **HTML 미실행**(텍스트/`textContent`·안전한 마크다운 plain만).

### 1.7 인증

- 이메일·비밀번호. 테스트: 이메일 확인 **OFF**, PW **≥6자**, 조합·유출차단 **OFF**.
- 가입 성공 → `profiles` **active + member** 자동 생성 → **자동 로그인** → 세션 유지.

### 1.8 관리자

- 회원으로 가입 후 DB에서 admin 지정.
- 화면: **대시보드**(요약) · **회원 검색·상태**(active/inactive) · **활동 내역** · **TOP 5 키워드** · **일일 한도 설정**(제목/글 구성).
- 모든 관리 API는 서버에서 세션·활성·`role=admin` 검증.

---

## 2. 데이터·권한

### 2.1 테이블(요약)

| 테이블 | 주요 열 | 비고 |
|---|---|---|
| `profiles` | `id(uuid PK=auth.users)`, `email`, `nickname`, `real_name`, `role`, `status`, `created_at`, `updated_at` | 가입 트리거로 insert |
| `usage_daily` | `user_id`, `usage_date(date KST)`, `title_count`, `outline_count`, PK(`user_id`,`usage_date`) | 일일 사용량 |
| `app_settings` | `key`, `value(jsonb)`, `updated_at`, `updated_by` | `daily_title_limit`, `daily_outline_limit` |
| `saved_ideas` | `id`, `user_id`, `keywords(text[])`, `audience`, `post_type`, `tone`, `selected_title`, `outline(jsonb)`, `created_at` | 저장 콘텐츠 |
| `posts` | `id`, `author_id`, `title`, `body`, `created_at`, `updated_at` | 커뮤니티 글 |
| `comments` | `id`, `post_id`, `author_id`, `body`, `created_at`, `updated_at` | 본인 글 댓글 금지 트리거 |
| `admin_audit_logs` | `id`, `actor_id`, `action`, `target`, `before`, `after`, `created_at` | 관리자 변경 이력 |
| `generation_jobs`(선택) | `id`, `user_id`, `kind(title\|outline)`, `request_hash`, `status`, `created_at` | 중복·동시 차감 방지용 idempotency |

### 2.2 권한 매트릭스 (실행 주체)

| 자원 | anon | authenticated | service_role(서버) |
|---|---|---|---|
| `profiles` SELECT | 불가(또는 공개 닉만 최소) | **본인** / 게시 표시용 닉네임 | 전체(관리) |
| `profiles` UPDATE | 불가 | **본인** 닉·실명만 | 상태·role 변경 |
| `saved_ideas` CRUD | 불가 | **본인만** | 관리 조회 |
| `posts` R | 전체 읽기 | 전체 읽기 | 전체 |
| `posts` CUD | 불가 | **본인 글** C/U/D | 관리 삭제 가능 |
| `comments` R | 전체 읽기 | 전체 읽기 | 전체 |
| `comments` C | 불가 | 활성 + **post.author_id ≠ me** + author_id=me | — |
| `comments` U/D | 불가 | **본인 댓글** | 관리 삭제 |
| `usage_daily` | 불가 | 본인 SELECT | 서버 upsert·차감 |
| `app_settings` | 불가 | 한도 **읽기만**(또는 서버 경유) | 관리자 쓰기 |
| `admin_audit_logs` | 불가 | 불가 | 관리자 읽기/쓰기 |
| Gemini 호출 | 불가 | 불가(직접) | **서버만** |
| RPC `consume_usage` | 불가 | 호출 가능하나 **함수 내 auth.uid()** 강제 | 동일 |

### 2.3 RLS · GRANT · 서버

- **RLS ON** 모든 업무 테이블. 정책은 `auth.uid()`·`status=active`·소유자 일치 기준.
- **본인 글 댓글 차단**: DB `BEFORE INSERT` 트리거로 `NEW.author_id = posts.author_id`이면 raise. 클라이언트 작성란 숨김과 이중화. `author_id`는 클라이언트 입력 무시, **`auth.uid()`로 강제**.
- **GRANT**: `anon`/`authenticated`는 필요한 테이블·컬럼만. `service_role`은 서버 API 전용(브라우저 금지).
- **마이그레이션**: `supabase/migrations/*.sql`에 스키마·RLS·GRANT·트리거·함수·시드(`app_settings` 기본 한도) 전부 포함. **새 프로젝트에 순서대로 적용 시 동작**이 완료 조건.

### 2.4 사용량·동시성

1. 요청 수신 → 세션·active 확인 → 오늘(KST) 한도 조회.  
2. `generation_jobs`에 `(user_id, kind, request_hash)` **unique 짧은 TTL** 또는 `consume_usage`를 **단일 트랜잭션** `UPDATE … WHERE count < limit RETURNING`으로 원자 증가.  
3. **증가 성공 후에만** Gemini 호출.  
4. Gemini **실패 시**: 트랜잭션 롤백 또는 `refund_usage`로 **차감 복구**(성공 차감 원칙 유지).  
5. 동일 본문 연속 클릭: 클라이언트 잠금 + 서버 idempotency로 **이중 차감 방지**.

---

## 3. API·오류 처리

### 3.1 공개/회원 API (예시)

| Method | Path | 역할 | 성공 | 주요 실패 |
|---|---|---|---|---|
| POST | `/api/auth/session` 등 | 클라→Supabase Auth 직접 가능 | 세션 | `AUTH_INVALID`, `AUTH_INACTIVE` |
| POST | `/api/generate/titles` | 로그인·활성 | 제목 5개 + 남은 횟수 | `LIMIT`, `VALIDATION`, `LLM_FAIL`(미차감/환급) |
| POST | `/api/generate/outline` | 로그인·활성 + 선택 제목 | outline 객체 | 동상 |
| POST | `/api/ideas` | 저장 | `id` | `VALIDATION`, `SAVE_FAIL` |
| GET | `/api/ideas` · `/api/ideas/:id` | 본인 | 목록/상세 DTO | `NOT_FOUND`(0건과 구분: 목록은 빈 배열, 상세는 404) |
| CRUD | posts/comments | Supabase 클라 + RLS 또는 API | — | `FORBIDDEN_OWN_POST_COMMENT`, `FORBIDDEN` |

### 3.2 관리자 API (전부 서버 검증)

| Method | Path | 내용 |
|---|---|---|
| GET | `/api/admin/dashboard` | 회원수·오늘 생성수·게시/댓글 수 등 |
| GET/PATCH | `/api/admin/members` | 검색·status 변경 + audit |
| GET | `/api/admin/activity` | 최근 생성·저장·게시 활동 |
| GET | `/api/admin/keywords/top` | TOP 5 키워드 |
| GET/PUT | `/api/admin/settings/limits` | 일일 한도 조회·저장 후 재조회 가능해야 함 |

비관리자·비활성·미소션 → **403/401**, 본문 데이터 없음.

### 3.3 오류 코드 구분 (오표시 금지)

| 코드 | 의미 | UI |
|---|---|---|
| `AUTH_REQUIRED` / `AUTH_EXPIRED` | 미로그인·세션 만료 | 로그인 유도 (**강제 비활성 표시 금지**) |
| `AUTH_INACTIVE` | DB상 inactive | 「정지된 계정」전용 문구 |
| `FORBIDDEN` | 권한 없음 | 권한 안내 |
| `NOT_FOUND` | 단건 없음 | 없음 안내 |
| `QUERY_ERROR` | 조회 실패 | 「불러오지 못했습니다. 다시 시도」(**빈 목록·로그아웃·비활성과 분리**) |
| `LIMIT_EXCEEDED` | 일일 한도 | 한도·초기화 시각 안내 |
| `VALIDATION` | 입력 오류 | 필드별 메시지 |
| `LLM_FAIL` / `SAVE_OK_REFETCH_FAIL` | 생성 실패 / 저장은 됐으나 목록 재조회 실패 | 각각 구분 토스트 |

### 3.4 프론트 비동기 규칙

1. submit 직전 폼 값 **스냅샷**.  
2. pending 중 중복 제출 차단.  
3. 실패 → 입력 유지·버튼 복구.  
4. 저장 성공 vs 이후 목록 조회 실패 메시지 분리.

### 3.5 최초 관리자·이력

1. 일반 가입 → SQL: `update profiles set role='admin' where email='…';`  
2. 동시에 `admin_audit_logs` insert (`action=role_bootstrap`).  
3. 이후 상태·한도·role 변경은 관리 API만, **before/after** 기록.

---

## 4. 구현 순서

1. **프로젝트 골격**: 폴더·라우팅(페이지 전환)·디자인 토큰·헤더·반응형 카드.  
2. **Supabase**: 마이그레이션(스키마·RLS·트리거·함수·시드) + Auth 설정(테스트 정책).  
3. **가입/로그인/세션/프로필 트리거** + inactive 구분.  
4. **사용량 RPC** + 서버 `/api/generate/*` (Gemini 키 서버만) + 아이디어 생성 UI.  
5. **저장·마이페이지 표 모달**(XSS 방지).  
6. **커뮤니티** 글/댓글 + 본인 글 댓글 차단.  
7. **관리자** 로그인 진입·ON 배지·대시보드·회원·TOP5·한도·audit.  
8. **README**: 로컬 실행, env, 관리자 지정, 운영 시 이메일 확인·PW 정책 ON.  
9. **인수 테스트** 수행·결과/미검증 분리 보고. (배포는 후속)

---

## 5. 인수 테스트

### 5.1 공통 합격 원칙

- 시나리오마다 **정상 / 실패 / 권한 거부**와 기대 결과를 기록.  
- **빌드 성공·SQL Editor 수동 성공만으로 완료 금지**. 브라우저 + API + `anon`/`authenticated`/`service_role` 각각 확인.  
- **빈 DB에 마이그레이션만 적용**해도 동일 동작.

### 5.2 핵심 시나리오 체크리스트

| ID | 시나리오 | 합격 기준 |
|---|---|---|
| A1 | 회원 A·B 가입·자동 로그인·재로그인·새로고침 | 세션 유지, 프로필 active/member |
| A2 | A 제목 5개 생성 → 선택 → 글 구성 생성 → 저장 | 각 성공 1회 차감, 저장행 1건 |
| A3 | 전체 복사 | 마크다운 계층/목록/강조 유지, 붙여넣기 확인 |
| A4 | 마이페이지 저장 상세 | 항목/내용 표, HTML 미실행, 모바일 스크롤 |
| A5 | A·B 활동 분리 | 서로의 글·댓글·저장 비노출 |
| C1 | B가 A 글에 댓글 | 성공 |
| C2 | A가 본인 글에 댓글 | UI 숨김 + API/DB 거부 |
| C3 | author_id 위조 | 서버/DB가 auth.uid로 무시·거부 |
| C4 | 비로그인 댓글란 | 로그인 안내, 기존 댓글은 조회 가능 |
| Q1 | 한도 초과 | 4xx `LIMIT`, 추가 차감 없음 |
| Q2 | 생성 중 더블클릭·실패 | 중복 차감 없음 / 실패 시 미차감 또는 환급 |
| Q3 | 「새 아이디어」 | 폼 리셋, 사용량 불변 |
| M1 | 관리자 지정 후 대시보드·한도 저장·재조회 | 값 일치 + audit 1건+ |
| M2 | 관리자 화면 우상단 | `#C62828` 굵은「관리자 ON」, 개인정보 비노출 |
| M3 | 일반 회원 관리 API | 401/403, 데이터 없음 |
| E1 | 조회 오류 주입(네트워크) | `QUERY_ERROR` 안내, 로그아웃·inactive·0건으로 오인 없음 |
| E2 | inactive 계정 | 전용 메시지, 생성·댓글 불가 |

### 5.3 보고 형식

- **검증됨**: ID + 일시 + 방법(브라우저/curl/역할)  
- **미검증**: ID + 사유 + 잔여 리스크  
- README에 로컬·env·관리자 지정·운영 인증 강화 절차 포함

---

## 6. 비기능·보안 요약

- Gemini·`service_role` 키 브라우저 번들·Git 커밋 금지.  
- 저장·커뮤니티 본문 출력 시 **HTML 실행 금지**(escape).  
- CORS·Rate limit은 Vercel/서버에서 기본 제한(가정: IP당 분당 생성 API 30).  
- 개인정보: 관리자 ON 영역·로그에 불필요 이메일 노출 최소화.

---

## 7. 문서 변경 이력

| 버전 | 일자 | 내용 |
|---|---|---|
| 0.1 | 2026-10-31 | 초안. 로고·참고화면 UX, 권한·한도·인수조건, 사용량 규칙 충돌 해소(성공 시만 차감) |
