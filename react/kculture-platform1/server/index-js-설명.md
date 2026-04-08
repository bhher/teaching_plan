# `server/index.js` 설명 (K-Culture Platform 1 API)

Express 서버의 **진입 파일**입니다. 환경 로드, 미들웨어, REST API 라우트, 이미지 업로드, 세션 인증이 한 파일에 정의되어 있습니다.

---

## 1. 상단 import (1~9행)

| import | 역할 |
|--------|------|
| `./env.js` | `server/.env` 로드 (가장 먼저 실행) |
| `express` | 웹 서버·라우팅 |
| `cors` | 브라우저 다른 출처(React 개발 서버)에서 API 호출 허용 |
| `express-session` | 로그인 세션(쿠키) |
| `multer` | multipart 폼 → 디스크에 이미지 저장 |
| `fs` / `path` | 업로드 폴더 생성·파일 삭제·경로 처리 |
| `./db.js` | MySQL 연결 풀 `pool` |

---

## 2. 업로드 디렉터리 & Multer (11~32행)

- **`UPLOAD_DIR`**: `server/uploads` (없으면 생성).
- **`multer.diskStorage`**: 파일을 `uploads`에 두고, 파일명은 `시간-랜덤.확장자` 형태로 충돌을 줄임.
- **`limits.fileSize`**: 5MB 제한.
- **`fileFilter`**: JPEG, PNG, GIF, WebP만 허용.

---

## 3. 설정 상수 (34~36행)

| 변수 | 출처 | 기본값 |
|------|------|--------|
| `PORT` | `process.env.PORT` | `3002` |
| `CLIENT_ORIGIN` | `.env` | `http://localhost:5173` (CORS 허용 출처) |
| `SESSION_SECRET` | `.env` | 개발용 문자열 (운영에서는 반드시 변경) |

---

## 4. 미들웨어 (38~63행)

| 순서 | 내용 |
|------|------|
| `trust proxy` | 리버스 프록시 뒤에서도 올바른 IP/HTTPS 인식(배포 시 유용) |
| `cors` | `CLIENT_ORIGIN` + `credentials: true` → 쿠키 포함 요청 허용 |
| `express.json()` | `Content-Type: application/json` 본문 파싱 |
| `express.static('/uploads')` | 저장된 이미지를 URL `/uploads/파일명`으로 제공 |
| `session` | 쿠키 이름 `kculture1.sid`, httpOnly, 7일, sameSite `lax` |

---

## 5. 헬퍼 함수

### `unlinkImageFilename(filename)` (65~75행)

- DB에 있는 **파일명**만 받아 `uploads` 안 실제 파일을 삭제.
- `..`, `/`, `\` 등으로 디렉터리 탈출 시도는 무시.

### `mapMemberRow(row)` (77~86행)

- DB `member` 행 → API 응답용 객체(비밀번호 제외).

### `requireAuth` (88~94행)

- `req.session.memberId` 없으면 **401** JSON.  
- 로그인 필요한 라우트에 연결.

---

## 6. API 라우트 개요

### 카테고리

| 메서드 | 경로 | 설명 |
|--------|------|------|
| GET | `/api/categories` | K-컬쳐 카테고리 목록 |

### 게시글

| 메서드 | 경로 | 인증 | 설명 |
|--------|------|------|------|
| GET | `/api/posts` | - | 목록·페이지네이션·`categoryId` 쿼리 |
| GET | `/api/posts/:id/edit` | 필요 | 수정 폼용 글 조회(조회수 **증가 없음**) |
| GET | `/api/posts/:id` | - | 상세 + 조회수 +1, 댓글 목록 |
| POST | `/api/posts` | 필요 | 글 작성 + 선택 이미지(`multipart`) |
| PATCH | `/api/posts/:id` | 필요 | 수정 + 이미지 교체/제거 |
| DELETE | `/api/posts/:id` | 필요 | 삭제 + 디스크 이미지 삭제 |

### 댓글

| 메서드 | 경로 | 인증 |
|--------|------|------|
| POST | `/api/posts/:id/comments` | 필요 |

### 인증

| 메서드 | 경로 | 설명 |
|--------|------|------|
| GET | `/api/auth/me` | 현재 로그인 회원(JSON) |
| POST | `/api/auth/login` | 이메일·비밀번호 → 세션 |
| POST | `/api/auth/logout` | 세션 삭제·쿠키 제거 |
| POST | `/api/auth/join` | 회원가입 후 자동 로그인 |

---

## 7. SQL·데이터 흐름 요약

- **`POST_SELECT_LIST` / `POST_SELECT_DETAIL`**: 게시글 + 회원 이름 + 카테고리명/아이콘 조인, `image_filename` → JSON 필드 `imageFilename`.
- **목록 `GET /api/posts`**: 카테고리 필터 유무에 따라 `WHERE category_id = ?` 분기, `COUNT`로 전체 건수·`totalPages` 계산.
- **상세 `GET /api/posts/:id`**: 글 조회 후 `view_count` UPDATE, 댓글은 `comment` + `member` 조인.

---

## 8. 이미지가 붙는 구간

- **작성 `POST /api/posts`**: `multer`가 `req.file` 채움 → `image_filename`에 파일명만 INSERT.
- **수정 `PATCH /api/posts/:id`**: `removeImage` 또는 새 파일 업로드 시 기존 파일 `unlink`, DB 갱신.
- **삭제 `DELETE`**: 글 삭제 성공 후 디스크 파일 삭제.

---

## 9. 서버 기동 (448~450행)

- `app.listen(PORT)` → 콘솔에 API 주소 출력.

---

## 10. 프론트엔드와의 연결

- 개발 시 Vite가 `/api`, `/uploads`를 이 서버로 **프록시**하면, React에서는 `fetch('/api/...', { credentials: 'include' })` 로 호출합니다.
- **CORS**는 `CLIENT_ORIGIN`과 Vite 주소(포트)가 일치해야 합니다.

---

## 관련 파일

- `env.js` — `.env` 경로 지정  
- `db.js` — MySQL 풀  
- `schema_post_image.sql` — `post.image_filename` 컬럼  
