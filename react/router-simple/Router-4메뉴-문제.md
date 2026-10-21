# Router 문제 — 4메뉴 사이트 조립하기

**목표:** 아래에 제공된 **Home · About · Portfolio · Contact** JSX를 사용해  
`router-simple`과 같은 방식으로 **중첩 라우트 사이트**를 완성하세요.

**참고 완성 구조:** `react/router-simple/`  
**메뉴 4개:** 홈 `/` · 소개 `/about` · 포트폴리오 `/portfolio` · 문의 `/contact`

---

## 완성 기준

```text
헤더:  [브랜드]   홈 | 소개 | 포트폴리오 | 문의
본문:  Outlet (URL마다 아래 페이지 중 하나)
푸터:  한 줄 문구
```

| URL | 컴포넌트 |
|-----|----------|
| `/` | Home |
| `/about` | About |
| `/portfolio` | Portfolio |
| `/contact` | Contact |
| 그 외 | NotFound (직접 작성) |

---

## 0. 프로젝트 준비

```bash
npm create vite@latest router-four-menu -- --template react
cd router-four-menu
npm install
npm install react-router-dom
npm run dev
```

폴더를 만드세요.

```text
src/
  main.jsx
  App.jsx
  index.css
  components/
    Layout.jsx
  pages/
    Home.jsx
    About.jsx
    Portfolio.jsx
    Contact.jsx
    NotFound.jsx
```

---

## 1. 제공 JSX — 페이지 (복사해서 사용)

아래 4개 파일은 **내용이 주어집니다.**  
학생 작업은 **라우터 연결 · Layout · NotFound · CSS** 입니다.

### `pages/Home.jsx` (제공)

```jsx
export default function Home() {
  return (
    <article className="page">
      <h1>홈</h1>
      <p className="lead">
        React Router로 만든 4메뉴 미니 사이트입니다. 헤더와 푸터는 고정되고,
        가운데만 페이지가 바뀝니다.
      </p>
      <ul className="feature-list">
        <li>
          <strong>소개</strong> — 사이트와 라우팅 구조를 설명합니다.
        </li>
        <li>
          <strong>포트폴리오</strong> — 프로젝트 카드를 보여 줍니다.
        </li>
        <li>
          <strong>문의</strong> — 데모용 연락 폼입니다.
        </li>
      </ul>
    </article>
  );
}
```

### `pages/About.jsx` (제공)

```jsx
export default function About() {
  return (
    <article className="page">
      <h1>소개</h1>
      <p>
        공통 레이아웃은 <code>Layout</code>에 두고, 자식 페이지는{' '}
        <code>Outlet</code> 자리에 렌더링합니다. 메뉴는 <code>NavLink</code>로
        연결합니다.
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
                <code>/portfolio</code>
              </td>
              <td>Portfolio</td>
            </tr>
            <tr>
              <td>
                <code>/contact</code>
              </td>
              <td>Contact</td>
            </tr>
            <tr>
              <td>
                <code>/*</code>
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

### `pages/Portfolio.jsx` (제공)

```jsx
const works = [
  {
    id: 1,
    title: 'Todo 앱',
    desc: '추가 · 완료 · 삭제 실습',
    tag: 'React',
  },
  {
    id: 2,
    title: '검색 필터',
    desc: 'useMemo로 목록 필터링',
    tag: 'Hooks',
  },
  {
    id: 3,
    title: 'Router Simple',
    desc: 'Layout + Outlet 중첩 라우트',
    tag: 'Router',
  },
];

export default function Portfolio() {
  return (
    <article className="page">
      <h1>포트폴리오</h1>
      <p className="lead">map으로 카드 목록을 렌더링합니다.</p>
      <div className="card-grid">
        {works.map((item) => (
          <article className="work-card" key={item.id}>
            <span className="work-tag">{item.tag}</span>
            <h2>{item.title}</h2>
            <p>{item.desc}</p>
          </article>
        ))}
      </div>
    </article>
  );
}
```

### `pages/Contact.jsx` (제공)

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
      <p>라우팅 연습용 폼입니다. 제출해도 서버로 전송되지 않습니다.</p>

      {sent ? (
        <p className="notice notice--ok" role="status">
          데모: 제출된 것처럼 표시만 했습니다.
        </p>
      ) : null}

      <form className="contact-form" onSubmit={handleSubmit}>
        <label className="field">
          <span>이름</span>
          <input name="name" type="text" required placeholder="홍길동" />
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

---

## 2. 학생이 직접 작성할 것

### 문제 A — `main.jsx` ⭐

- `BrowserRouter`로 `<App />`을 감싸세요.
- `index.css`를 import 하세요.

---

### 문제 B — `Layout.jsx` ⭐⭐

`router-simple`의 Layout처럼 작성하세요.

**필수**

1. 브랜드 `NavLink` → `/` (`end` 사용)
2. 메뉴 4개 `NavLink`  
   - 홈 `/` (`end`)  
   - 소개 `/about`  
   - 포트폴리오 `/portfolio`  
   - 문의 `/contact`
3. 활성 클래스 함수 사용 예:

```jsx
const navLinkClass = ({ isActive }) =>
  isActive ? 'nav-link nav-link--active' : 'nav-link';
```

4. `<main>` 안에 **`<Outlet />`**
5. `<footer>` 문구 1줄

**힌트 뼈대**

```jsx
import { NavLink, Outlet } from 'react-router-dom';

export default function Layout() {
  return (
    <div className="layout">
      <header>{/* NavLink 4개 */}</header>
      <main>
        <Outlet />
      </main>
      <footer>{/* 문구 */}</footer>
    </div>
  );
}
```

---

### 문제 C — `App.jsx` 중첩 Routes ⭐⭐

제공 페이지를 import 하고, **Layout을 부모**로 연결하세요.

채워야 할 형태:

```jsx
import { Routes, Route } from 'react-router-dom';
import Layout from './components/Layout';
import Home from './pages/Home';
import About from './pages/About';
import Portfolio from './pages/Portfolio';
import Contact from './pages/Contact';
import NotFound from './pages/NotFound';

export default function App() {
  return (
    <Routes>
      <Route path="/" element={<Layout />}>
        {/* TODO: index, about, portfolio, contact, * */}
      </Route>
    </Routes>
  );
}
```

**요구**

| Route | 설정 |
|-------|------|
| 홈 | `index` + `<Home />` |
| 소개 | `path="about"` |
| 포트폴리오 | `path="portfolio"` |
| 문의 | `path="contact"` |
| 404 | `path="*"` + `<NotFound />` |

---

### 문제 D — `NotFound.jsx` ⭐

제공되지 않습니다. 직접 작성하세요.

- 제목 `404`
- 안내 문구
- `Link`로 `to="/"` 홈 이동

```jsx
import { Link } from 'react-router-dom';
// ...
```

---

### 문제 E — `index.css` (최소) ⭐

자유 디자인이지만 아래는 포함할 것.

- [ ] `.layout` 세로 전체 높이
- [ ] `.nav-link--active` 현재 메뉴 강조
- [ ] `.card-grid` / `.work-card` (포트폴리오)
- [ ] `.contact-form` / `.field` (문의)
- [ ] `.page` 본문 여백

`router-simple/src/index.css`를 참고해도 됩니다.

---

## 3. 서술 문제

1. 포트폴리오 메뉴를 추가할 때 `App.jsx`와 `Layout.jsx` **둘 다** 수정해야 하는 이유는?  
2. `Outlet`이 하는 일을 한 문장으로 쓰세요.  
3. 홈 `NavLink`에 `end`가 필요한 이유는?  
4. Contact에서 `preventDefault`를 쓰는 이유는?

---

## 4. 자가 테스트

- [ ] `/` 홈 내용, 홈 메뉴 active  
- [ ] `/about` 표에 portfolio 행이 보임  
- [ ] `/portfolio` 카드 3개  
- [ ] `/contact` 제출 → 데모 메시지  
- [ ] `/xyz` → 404 → 홈으로  
- [ ] 메뉴 이동 시 헤더/푸터 유지  

---

## 5. 채점표

| 항목 | 배점 |
|------|------|
| BrowserRouter | 10 |
| Layout (NavLink 4 + Outlet + end) | 25 |
| App 중첩 Route 5개 | 25 |
| 제공 페이지 연결·동작 | 15 |
| NotFound + Link | 10 |
| CSS 최소 요구 | 10 |
| 서술 문제 | 5 |
| **합계** | **100** |

---

## 6. 금지

- 페이지 이동을 `<a href>`만 사용
- 제공 JSX를 라우터 없이 한 파일에만 붙여 넣기
- `Outlet` 없이 페이지마다 헤더를 복사해서 붙이기

---

## 한 줄 요약

> **페이지 JSX는 제공됨 → 여러분은 router-simple처럼 Layout + Routes로 조립하세요.**
