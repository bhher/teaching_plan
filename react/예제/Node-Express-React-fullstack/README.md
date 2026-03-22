# Node-Express-React 분리 예제

`Node-Express-React-API-분리-가이드.md`와 동일한 구조의 실습용 프로젝트입니다.

## 구조

```text
Node-Express-React-fullstack/
├── api-server/    Express (포트 3001)
└── web-client/    Vite + React (포트 5173)
```

## 실행 방법

터미널을 **두 개** 켜고 순서대로:

1. API 서버

   ```bash
   cd api-server
   npm install
   npm run start
   ```

2. React 클라이언트

   ```bash
   cd web-client
   npm install
   npm run dev
   ```

브라우저에서 Vite 주소(보통 `http://localhost:5173`)로 접속합니다. API는 Vite 프록시로 `/api` → `localhost:3001` 에 전달됩니다.

## 확인

- API만 테스트: `GET http://localhost:3001/api/items`
