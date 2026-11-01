# Router Simple 설명

`react/router-simple`은 **React Router DOM v6**로  
**공통 레이아웃(헤더·푸터) + 가운데만 바뀌는 페이지**를 만드는 예제입니다.

```bash
cd react/router-simple
npm install
npm run dev
```



---

## 1. 이 프로젝트가 보여주는 것

일반 사이트처럼 주소가 바뀌면 화면이 바뀝니다.

| URL | 화면 |
|-----|------|
| `/` | 홈 |
| `/about` | 소개 |
| `/contact` | 문의 폼 |
| `/없는주소` | 404 |

다만 **헤더·푸터는 그대로** 두고, **본문(`Outlet`)만** 바꿉니다.  
이게 **중첩 라우트(Nested Routes)** 입니다.

```text
┌──────────────────────────┐
│  Header (NavLink)        │  ← Layout (항상)
├──────────────────────────┤
│  Outlet ← 여기만 교체      │  ← Home / About / Contact / 404
├──────────────────────────┤
│  Footer                  │  ← Layout (항상)
└──────────────────────────┘
```

---

## 2. 폴더 구조

```text
router-simple/
└── src/
    ├── main.jsx          ← BrowserRouter
    ├── App.jsx           ← Routes / Route
    ├── index.css
    ├── components/
    │   └── Layout.jsx    ← NavLink + Outlet
    └── pages/
        ├── Home.jsx
        ├── About.jsx
        ├── Contact.jsx
        └── NotFound.jsx
```

| 파일 | 역할 |
|------|------|
| `main.jsx` | 앱 전체에 라우터 환경 제공 |
| `App.jsx` | URL ↔ 페이지 연결표 |
| `Layout.jsx` | 공통 UI + 자식이 들어갈 구멍(`Outlet`) |
| `pages/*` | 각 URL의 실제 내용 |

---

## 3. 핵심 API 6개

| API | 역할 | 위치 |
|-----|------|------|
| **BrowserRouter** | 브라우저 주소와 React 연결 | `main.jsx` |
| **Routes** | Route들을 모아 관리 | `App.jsx` |
| **Route** | `path`와 `element` 한 쌍 | `App.jsx` |
| **Outlet** | 자식 페이지가 그려질 자리 | `Layout.jsx` |
| **NavLink** | 이동 + **현재 페이지면 활성 스타일** | `Layout.jsx` |
| **Link** | 단순 이동 (활성 스타일 불필요) | `NotFound.jsx` |

---

## 4. main.jsx — BrowserRouter

```jsx
import { BrowserRouter } from 'react-router-dom';

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <BrowserRouter>
      <App />
    </BrowserRouter>
  </React.StrictMode>
);
```

`BrowserRouter` 안에 있는 컴포넌트만 `Routes`, `NavLink`, `Link` 등을 쓸 수 있습니다.

---

## 5. App.jsx — 중첩 Route

```jsx
<Routes>
  <Route path="/" element={<Layout />}>
    <Route index element={<Home />} />
    <Route path="about" element={<About />} />
    <Route path="contact" element={<Contact />} />
    <Route path="*" element={<NotFound />} />
  </Route>
</Routes>
```

### 읽는 법

1. **부모** `path="/"` + `element={<Layout />}`  
   → 레이아웃을 먼저 그린다.
2. **자식**은 Layout의 `<Outlet />` 자리에 들어간다.
3. `index` → 부모와 같은 URL(`/`)일 때 Home
4. `path="about"` → 실제 주소는 **`/about`** (부모 `/` + about)
5. `path="*"` → 위에 해당 없는 모든 주소 → NotFound

> 자식 path에 `/about`처럼 앞에 `/`를 붙이지 않는 것이 일반적입니다.  
> 상대 경로로 부모 아래에 붙습니다.

---

## 6. Layout.jsx — NavLink + Outlet

### Outlet

```jsx
<main className="site-main">
  <Outlet />
</main>
```

URL이 `/about`이면 여기에 `<About />`이 들어갑니다.  
헤더·푸터 코드는 한 번만 작성하면 됩니다.

### NavLink

```jsx
const navLinkClass = ({ isActive }) =>
  isActive ? 'nav-link nav-link--active' : 'nav-link';

<NavLink to="/about" className={navLinkClass}>
  소개
</NavLink>
```

- `Link`와 같이 SPA 내부 이동
- 추가로 **지금 이 메뉴가 선택된 페이지인지** `isActive`로 알려 줌
- CSS에서 `.nav-link--active`로 강조

### end 속성

```jsx
<NavLink to="/" className={navLinkClass} end>
  홈
</NavLink>
```

`to="/"` 는 `/about`의 **앞부분**과도 겹칩니다.  
`end`가 없으면 `/about`에서도 홈이 활성으로 남을 수 있습니다.  
`end` → **경로가 정확히 `/`일 때만** 홈 active.

---

## 7. 페이지별 역할

### Home

라우터로 나눈 사이트라는 소개 + 소개/문의 안내 목록.

### About

Layout · Outlet 개념 설명 + URL/컴포넌트 **표**.

### Contact

```jsx
const [sent, setSent] = useState(false);

const handleSubmit = (e) => {
  e.preventDefault();
  setSent(true);
};
```

- 제출해도 **서버로 안 보냄** (수업용 데모)
- `preventDefault`로 새로고침 방지
- `sent`가 true면 안내 문구 표시

### NotFound

```jsx
<Link to="/" className="btn-primary">
  홈으로
</Link>
```

없는 주소용. `NavLink` 대신 `Link`만 써도 됩니다 (메뉴 활성 불필요).

---

## 8. 동작 흐름

```text
사용자가 「소개」 NavLink 클릭
        ↓
주소창이 /about 으로 변경 (페이지 전체 새로고침 없음)
        ↓
Routes가 일치하는 Route 선택
        ↓
Layout 렌더 (헤더·푸터 유지)
        ↓
Outlet 자리에 About 삽입
        ↓
소개 NavLink에 isActive → 활성 스타일
```

---

## 9. `<a>` vs Link / NavLink

| | `<a href>` | `Link` / `NavLink` |
|--|------------|-------------------|
| 동작 | 브라우저가 문서를 다시 요청 | React가 URL만 바꾸고 컴포넌트 교체 |
| 결과 | 깜빡임 · 상태 초기화 가능 | SPA답게 부드러운 전환 |
| 이 예제 | 사용하지 않음 | 메뉴·홈으로 버튼에 사용 |

---

## 10. CSS 포인트 (`index.css`)

- CSS 변수(`--bg`, `--accent` 등)로 다크 톤 UI
- `.layout` = column flex, 최소 높이 100vh
- `.nav-link--active` = 현재 메뉴 강조
- `.contact-form` / `.field` = 문의 폼 간격

스타일은 자유롭게 바꿔도 라우팅 구조는 그대로입니다.

---

## 11. 학생용 한 장 정리

```text
BrowserRouter     라우터 켜기
     ↓
Routes / Route    URL ↔ 화면 표
     ↓
Layout            헤더 · 푸터 고정
     ↓
Outlet            가운데 페이지만 교체
     ↓
NavLink           메뉴 이동 + 활성 표시
Link              단순 이동 (404 → 홈)
```

**외울 구조**

```jsx
<Route path="/" element={<Layout />}>
  <Route index element={<Home />} />
  <Route path="about" element={<About />} />
  <Route path="*" element={<NotFound />} />
</Route>
```

---

## 12. 자주 하는 실수

| 실수 | 결과 | 해결 |
|------|------|------|
| `BrowserRouter` 없음 | NavLink/Routes 에러 | `main.jsx`에서 감싸기 |
| Layout에 `Outlet` 없음 | 자식 페이지가 안 보임 | `<Outlet />` 추가 |
| 홈 NavLink에 `end` 없음 | 다른 페이지에서도 홈 활성 | `end` 추가 |
| 자식 path를 `/about`으로만 헷갈림 | 중첩 매칭 혼란 | 예제는 `about` (상대) |
| Contact에서 `preventDefault` 누락 | 제출 시 새로고침 | `e.preventDefault()` |

---

## 13. 다음 단계

| 주제 | 참고 |
|------|------|
| URL 파라미터 `/products/:id` | `router-basic`, `routerBasic.md` |
| 슬라이드·포트폴리오 | `router-project` |
| 직접 만들어 보기 | `Router-Simple-작업지시서.md` |
