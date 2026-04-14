# Router Simple (react-router-dom) 설명

이 문서는 `react/router-simple` 프로젝트가 **React Router DOM v6**로 여러 URL에 다른 화면을 보여 주는 방식을 정리한 것입니다. 아래에는 **전체 JSX 소스**를 그대로 포함합니다.

---

## 1. 개요

- **`BrowserRouter`**: 브라우저 주소창의 URL과 React 트리를 연결합니다. (`main.jsx`)
- **`Routes` / `Route`**: 경로(`path`)와 그때 그릴 컴포넌트(`element`)를 쌍으로 둡니다. (`App.jsx`)
- **중첩 라우트**: 부모 `Route`의 `element`가 `<Layout />`이고, 자식 `Route`들은 **같은 레이아웃 안**에서만 바뀌는 영역을 담당합니다.
- **`Outlet`**: “자식 라우트가 여기 들어간다”는 자리 표시자입니다. (`Layout.jsx`)
- **`NavLink`**: 클릭 시 이동 + **현재 경로와 일치하면** `className` 함수로 활성 스타일을 줄 수 있습니다.
- **`Link`**: 일반 링크(예: 404에서 홈으로).

스타일은 `index.css`에 있으며, 이 문서에서는 JSX만 포함합니다.

---

## 2. 폴더 구조

```
router-simple/
├── index.html
├── vite.config.js
├── package.json
├── Router-Simple-설명.md    ← 이 파일
└── src/
    ├── main.jsx             ← BrowserRouter
    ├── App.jsx              ← Routes 정의
    ├── index.css
    ├── components/
    │   └── Layout.jsx       ← NavLink, Outlet, 푸터
    └── pages/
        ├── Home.jsx
        ├── About.jsx
        ├── Contact.jsx
        └── NotFound.jsx
```

---

## 3. URL과 컴포넌트

부모 경로가 `/`이므로 자식은 **상대 경로**로 붙습니다 (`about` → 실제 URL `/about`).

| 브라우저 URL   | 매칭되는 화면 | 비고 |
|----------------|---------------|------|
| `/`            | `Layout` + `Home` (index) | |
| `/about`       | `Layout` + `About` | |
| `/contact`     | `Layout` + `Contact` | |
| 그 외 (예: `/x`) | `Layout` + `NotFound` | `path="*"` 스플랫 |

`Layout`은 항상 그려지고, `<main>` 안의 **`Outlet`만** 위 표에 따라 바뀝니다.

---

## 4. 렌더링 흐름 (한 줄 요약)

```
URL 변경 (주소 입력·NavLink·Link)
  → Router가 현재 URL과 일치하는 Route 트리 선택
  → Layout 렌더 → Outlet 위치에 해당 페이지 컴포넌트 삽입
```

---

## 5. 포인트 정리

- **`NavLink`의 `end`**: `to="/"` 일 때 `/about`에서는 홈 링크가 활성으로 남지 않게 **정확히 일치**할 때만 active 처리합니다.
- **`Contact`**: `onSubmit`에서 `preventDefault` 후 로컬 state만 바꿉니다. **API 호출 없음** (연습용).
- **`NotFound`**: 정의되지 않은 경로용; `Link to="/"` 로 홈 복귀.

---

## 6. 전체 JSX 코드

### `src/main.jsx`

```jsx
import React from 'react';
import ReactDOM from 'react-dom/client';
import { BrowserRouter } from 'react-router-dom';
import App from './App';
import './index.css';

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <BrowserRouter>
      <App />
    </BrowserRouter>
  </React.StrictMode>
);
```

### `src/App.jsx`

```jsx
import { Routes, Route } from 'react-router-dom';
import Layout from './components/Layout';
import Home from './pages/Home';
import About from './pages/About';
import Contact from './pages/Contact';
import NotFound from './pages/NotFound';

export default function App() {
  return (
    <Routes>
      <Route path="/" element={<Layout />}>
        <Route index element={<Home />} />
        <Route path="about" element={<About />} />
        <Route path="contact" element={<Contact />} />
        <Route path="*" element={<NotFound />} />
      </Route>
    </Routes>
  );
}
```

### `src/components/Layout.jsx`

```jsx
import { NavLink, Outlet } from 'react-router-dom';

const navLinkClass = ({ isActive }) =>
  isActive ? 'nav-link nav-link--active' : 'nav-link';

export default function Layout() {
  return (
    <div className="layout">
      <header className="site-header">
        <div className="site-header__inner">
          <NavLink to="/" className="brand" end>
            Router Simple
          </NavLink>
          <nav className="site-nav" aria-label="주 메뉴">
            <NavLink to="/" className={navLinkClass} end>
              홈
            </NavLink>
            <NavLink to="/about" className={navLinkClass}>
              소개
            </NavLink>
            <NavLink to="/contact" className={navLinkClass}>
              문의
            </NavLink>
          </nav>
        </div>
      </header>

      <main className="site-main">
        <Outlet />
      </main>

      <footer className="site-footer">
        <p>React Router DOM v6 · 중첩 라우트 · NavLink</p>
      </footer>
    </div>
  );
}
```

### `src/pages/Home.jsx`

```jsx
export default function Home() {
  return (
    <article className="page">
      <h1>홈</h1>
      <p className="lead">
        이 사이트는 <code>react-router-dom</code>의 <code>BrowserRouter</code>,{' '}
        <code>Routes</code>, <code>Route</code>, <code>NavLink</code>,{' '}
        <code>Outlet</code>으로 페이지를 나눈 예제입니다.
      </p>
      <ul className="feature-list">
        <li>
          <strong>소개</strong> — 라우팅 구조와 역할을 짧게 설명합니다.
        </li>
        <li>
          <strong>문의</strong> — 폼은 데모용이며 실제 전송은 하지 않습니다.
        </li>
      </ul>
    </article>
  );
}
```

### `src/pages/About.jsx`

```jsx
export default function About() {
  return (
    <article className="page">
      <h1>소개</h1>
      <p>
        <code>App.jsx</code>에서 경로별로 <code>element</code>를 연결하고, 공통 뼈대는{' '}
        <code>Layout</code> 한 곳에 둡니다. 자식 페이지는 <code>Outlet</code> 위치에
        렌더링됩니다.
      </p>
      <section className="card-block">
        <h2>경로 요약</h2>
        <table className="route-table">
          <thead>
            <tr>
              <th>URL</th>
              <th>컴포넌트</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>
                <code>/</code>
              </td>
              <td>Home</td>
            </tr>
            <tr>
              <td>
                <code>/about</code>
              </td>
              <td>About</td>
            </tr>
            <tr>
              <td>
                <code>/contact</code>
              </td>
              <td>Contact</td>
            </tr>
            <tr>
              <td>
                <code>/*</code> (없는 주소)
              </td>
              <td>NotFound</td>
            </tr>
          </tbody>
        </table>
      </section>
    </article>
  );
}
```

### `src/pages/Contact.jsx`

```jsx
import { useState } from 'react';

export default function Contact() {
  const [sent, setSent] = useState(false);

  const handleSubmit = (e) => {
    e.preventDefault();
    setSent(true);
  };

  return (
    <article className="page">
      <h1>문의</h1>
      <p>아래는 라우팅 연습용 폼입니다. 제출해도 서버로 전송되지 않습니다.</p>

      {sent ? (
        <p className="notice notice--ok" role="status">
          데모: 제출된 것처럼 표시만 했습니다.
        </p>
      ) : null}

      <form className="contact-form" onSubmit={handleSubmit}>
        <label className="field">
          <span>이름</span>
          <input name="name" type="text" required autoComplete="name" placeholder="홍길동" />
        </label>
        <label className="field">
          <span>메시지</span>
          <textarea name="message" rows={4} required placeholder="내용을 입력하세요" />
        </label>
        <button type="submit" className="btn-primary">
          보내기 (데모)
        </button>
      </form>
    </article>
  );
}
```

### `src/pages/NotFound.jsx`

```jsx
import { Link } from 'react-router-dom';

export default function NotFound() {
  return (
    <article className="page page--center">
      <h1>404</h1>
      <p>이 주소에는 페이지가 없습니다.</p>
      <Link to="/" className="btn-primary">
        홈으로
      </Link>
    </article>
  );
}
```

---

## 7. 실행 방법

프로젝트 루트(`router-simple`)에서:

```bash
npm install
npm run dev
```

---

## 8. 학습 포인트 체크리스트

- [ ] `BrowserRouter`를 **한 번만** 최상단(보통 `main.jsx`)에 두는 이유
- [ ] 중첩 `Route`에서 부모 `element`와 자식 `element`가 어떻게 합쳐지는지 (`Outlet`)
- [ ] `index` 라우트와 `path="about"` 의 URL 차이
- [ ] `path="*"` 가 “나머지 전부”에 쓰이는 방식
- [ ] `NavLink`의 `className` 함수와 `isActive`, 홈 링크에 `end`를 쓰는 이유
- [ ] `Link`와 일반 `<a href>`의 차이(클라이언트 쪽 이동)

이 문서는 `src/main.jsx`, `src/App.jsx`, `src/components/Layout.jsx`, `src/pages/*.jsx`를 기준으로 작성되었습니다.
