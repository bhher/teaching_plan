# Express 서버와 React — 관계와 기초

## 1. Express 서버란? (React와의 관계)

**Express.js**는 **Node.js** 기반의 **백엔드 서버 프레임워크**입니다.

| 역할 | 담당 |
|------|------|
| **React** | 화면(UI) |
| **Express** | 데이터·비즈니스 로직 |

### 구조 한눈에

```
[React (프론트)]
      ↓ 요청 (API)
[Express 서버 (백엔드)]
      ↓
[DB (MySQL 등)]
```

React는 브라우저에서 동작하고, Express는 서버에서 동작합니다. React가 `fetch` 등으로 API를 호출하면 Express가 DB를 읽고 쓰고, JSON 등으로 응답합니다.

---

## 2. Express 서버로 할 수 있는 것

React만으로는 어렵거나 부적절한 일을 **서버**에서 처리합니다.

### 주요 역할

- 로그인 / 회원가입 처리  
- DB 조회·저장 (**CRUD**)  
- **REST API** 제공  
- 파일 업로드  
- 인증 (**JWT**, **세션**)  
- 외부 API 연동 (키 숨기기, 프록시 등)

### 예시 흐름

**React**

```js
fetch('/api/posts');
```

**Express**

```js
app.get('/api/posts', (req, res) => {
  res.json([{ title: '글1' }, { title: '글2' }]);
});
```

---

## 3. Express 서버 설치 방법 (기초)

### ① 폴더 구조

```
project/
├── client/     ← React (Vite 등)
└── server/     ← Express
```

### ② 패키지 설치

```bash
cd server
npm init -y
npm install express cors
```

### ③ 기본 서버 코드 (`server/index.js`)

CommonJS 예시:

```js
const express = require('express');
const cors = require('cors');

const app = express();

// 미들웨어
app.use(cors());
app.use(express.json());

// 테스트
app.get('/', (req, res) => {
  res.send('서버 정상 작동');
});

app.get('/api/test', (req, res) => {
  res.json({ message: 'Hello React!' });
});

app.listen(5000, () => {
  console.log('Server running on port 5000');
});
```

### ④ 실행

```bash
node index.js
```

브라우저에서 `http://localhost:5000` 으로 접속해 동작을 확인합니다.

---

## 4. React에서 서버 호출

```jsx
useEffect(() => {
  fetch('http://localhost:5000/api/test')
    .then((res) => res.json())
    .then((data) => console.log(data));
}, []);
```

**응답 예:** `{ "message": "Hello React!" }`

개발 시에는 Vite **프록시**로 `/api`만 맞추면 `fetch('/api/test')`처럼 짧게 쓸 수 있습니다.

---

## 5. CORS가 필요한 이유

- React 개발 서버: 예) `http://localhost:5173`  
- Express: 예) `http://localhost:5000`  

**출처(origin)가 다르면** 브라우저가 기본적으로 다른 포트로의 요청을 막을 수 있습니다.

**해결:** Express에서 `cors` 미들웨어 사용.

```js
app.use(cors());
```

프로덕션에서는 허용할 **origin**을 좁히는 설정이 권장됩니다.

---

## 6. 실제 프로젝트 구조 (추천)

```
project/
├── client/           ← React
└── server/           ← Express
    ├── index.js      ← 진입점
    ├── routes/       ← URL별 라우트
    ├── controllers/  ← 요청 처리
    └── models/       ← DB 접근 (선택)
```

규모가 작으면 `routes`만 두거나 `index.js`에 모아도 됩니다.

---

## 7. 한 줄 정리

- **React** = 화면  
- **Express** = 데이터·서버 로직  

둘을 **API로 연결**해야 DB·인증·파일 업로드까지 포함한 **실제 서비스**에 가깝게 만들 수 있습니다.

---

## 참고 (이 저장소 예제)

- `react/kculture-platform` — Vite + React Router + Express + MySQL + 세션 쿠키  
- `react/kculture-platform1` — 위와 동일 계열 + 게시글 이미지 업로드 (`multer`, `server/uploads`)  
- `react/ex/Node-Express-React-fullstack` — 풀스택 예제 (경로는 저장소 기준으로 확인)
