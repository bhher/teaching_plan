# K-Culture Platform (Vite + React Router + Express)

JSP `17-Kculture-Platform`와 같은 DB·기능을 기준으로 한 풀스택 예제입니다.

| JSP | React |
|-----|--------|
| `list.do` | `/` (`?categoryId=&page=`) |
| `view.do?id=` | `/posts/:id` |
| `write.do` | `/write` |
| `edit.do?id=` | `/posts/:id/edit` |
| `login.do` / `join.do` | `/login` / `/join` |

- **프론트**: `fetch` + `credentials: 'include'` → 세션 쿠키(`kculture.sid`, httpOnly) 전송  
- **백엔드**: `express-session` + MySQL (`mysql2`)  
- **개발 시**: Vite가 `/api`를 `http://localhost:3001`으로 프록시해 같은 출처처럼 쿠키가 동작합니다.

## 준비

1. MySQL에 스키마 적용 (저장소 기준):

   `jsp/예제/17-Kculture-Platform/schema.sql`

2. 서버 환경 변수:

   ```bash
   cd server
   copy .env.example .env
   ```

   `.env`에서 `MYSQL_USER`, `MYSQL_PASSWORD` 등을 맞춥니다.  
   (`.env`는 `server` 폴더에 두고, `env.js`가 그 경로를 기준으로 읽습니다. `npm run dev`를 다른 폴더에서 실행해도 동작합니다.)

3. Vite가 **5173이 아닌 포트**(예: 5175)로 떴다면 `CLIENT_ORIGIN`을 그 주소로 맞춥니다. 그렇지 않으면 CORS로 API가 막힐 수 있습니다.

4. 의존성 설치:

   ```bash
   cd server && npm install
   cd ../client && npm install
   ```

## 실행 (터미널 두 개)

**API**

```bash
cd server
npm run dev
```

**웹** (http://localhost:5173)

```bash
cd client
npm run dev
```

테스트 계정(스키마 시드): `tourist@test.com` / `1234`

## JWT 대신

이 예제는 **서버 세션 + 쿠키**입니다. JWT + httpOnly를 쓰려면 로그인 시 토큰을 발급하고, 프론트는 `Authorization` 헤더 또는 쿠키에 담긴 JWT를 보내도록 바꾸면 됩니다.
