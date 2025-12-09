## 7차시 – React Router v6로 SPA 라우팅 만들기

### 0. 학습 목표

- React Router v6로 **여러 페이지처럼 보이는 SPA**를 구성할 수 있다.
- `Route`, `Link`를 사용해 페이지 이동을 구현할 수 있다.

---

### 1. 이론 (슬라이드 흐름)

#### 1) 라우팅 기본 개념

- 서버 라우팅:
  - URL이 바뀔 때마다 **서버에서 새로운 HTML**을 내려줌
- 클라이언트 라우팅(SPA):
  - URL만 바꾸고, **현재 페이지 안에서 컴포넌트만 교체**
  - React Router가 이 역할을 담당

#### 2) React Router v6 핵심 컴포넌트

- `BrowserRouter`
  - 앱 전체를 감싸는 라우터 컨테이너
- `Routes` / `Route`
  - 어떤 URL에 어떤 컴포넌트를 렌더링할지 매핑
- `Link`
  - a 태그 역할을 하는 컴포넌트 (새로고침 없이 이동)

예시:

```jsx
<BrowserRouter>
  <Routes>
    <Route path="/" element={<HomePage />} />
    <Route path="/about" element={<AboutPage />} />
  </Routes>
</BrowserRouter>
```

---

### 2. 실습 – 3페이지 SPA 만들기

#### 2.1 실습 목표

- `/` : 홈(Home)
- `/todos` : ToDo 페이지 (이전 차시의 ToDoApp 사용)
- `/about` : 소개 페이지

#### 2.2 설치

```bash
npm install react-router-dom
```

---

### 3. 코드 스켈레톤

#### 3.1 `main.jsx` – BrowserRouter 적용

```jsx
// src/main.jsx
import React from 'react';
import ReactDOM from 'react-dom/client';
import { BrowserRouter } from 'react-router-dom';
import App from './App.jsx';

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <BrowserRouter>
      <App />
    </BrowserRouter>
  </React.StrictMode>
);
```

#### 3.2 페이지 컴포넌트들

```jsx
// src/pages/HomePage.jsx
function HomePage() {
  return (
    <div>
      <h1>홈 페이지</h1>
      <p>React 입문 강의에 오신 것을 환영합니다.</p>
    </div>
  );
}

export default HomePage;
```

```jsx
// src/pages/TodoPage.jsx
import TodoApp from '../TodoApp.jsx';

function TodoPage() {
  return (
    <div>
      <h1>할 일 관리</h1>
      <TodoApp />
    </div>
  );
}

export default TodoPage;
```

```jsx
// src/pages/AboutPage.jsx
function AboutPage() {
  return (
    <div>
      <h1>소개</h1>
      <p>이 강의는 React 18을 기반으로 합니다.</p>
      <p>목표: 입문자에서 중급으로 성장!</p>
    </div>
  );
}

export default AboutPage;
```

#### 3.3 `App.jsx` – 라우터 설정

```jsx
// src/App.jsx
import { Routes, Route, Link } from 'react-router-dom';
import HomePage from './pages/HomePage.jsx';
import TodoPage from './pages/TodoPage.jsx';
import AboutPage from './pages/AboutPage.jsx';

function App() {
  return (
    <div>
      <nav style={{ marginBottom: '16px' }}>
        <Link to="/" style={{ marginRight: 8 }}>홈</Link>
        <Link to="/todos" style={{ marginRight: 8 }}>할 일</Link>
        <Link to="/about">소개</Link>
      </nav>

      <hr />

      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/todos" element={<TodoPage />} />
        <Route path="/about" element={<AboutPage />} />
      </Routes>
    </div>
  );
}

export default App;
```

---

### 4. 과제

1. **네비게이션 스타일링**
   - 현재 페이지에 해당하는 링크를 굵게 표시하거나 색상 변경
   - `NavLink`를 사용하면 `isActive`에 따라 스타일 적용 가능 (심화)

2. **할 일 상세 페이지 기획**
   - URL 설계: `/todos/:id`
   - `TodoPage`에서 각 항목을 클릭했을 때 해당 상세 페이지로 이동시키는 구조를 설계해 보기
   - 아직 데이터 연동은 필요 없음, **라우트만 추가**해도 좋음

> 다음 차시 예고:  
> “다음 시간에는 서버에서 데이터를 가져오는 법,  
> 즉 **`useEffect`와 fetch/axios로 API를 호출**해서 화면에 렌더링하는 방법을 배워 봅니다.”




