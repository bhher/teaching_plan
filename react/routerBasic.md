# React Router 설명 및 예제

예제 프로젝트: **`router-basic/`**

```bash
cd react/router-basic
npm install
npm run dev
```

---

## 1. React Router란?

React는 기본적으로 **한 페이지(SPA)** 에서 화면을 바꾸는 방식입니다.

일반적인 웹사이트:

```
/             → 메인 페이지
/about        → 소개 페이지
/products     → 상품 페이지
/contact      → 연락처 페이지
```

주소가 바뀌면서 페이지가 이동합니다.

React에서는 이런 **URL에 따라 다른 컴포넌트를 보여주는 기능**을 **React Router**가 담당합니다.

쉽게 말하면:

> **주소(URL)와 React 컴포넌트를 연결해 주는 라이브러리**

---

## 2. React Router 설치

Vite React 프로젝트에서:

```bash
npm install react-router-dom
```

설치 후:

```bash
npm run dev
```

---

## 3. 가장 기본적인 구조

```
/
├── 홈
├── /about        → 소개
├── /products     → 상품
└── /contact      → 연락처
```

파일 구조:

```
src
├── App.jsx
├── main.jsx
└── pages
    ├── Home.jsx
    ├── About.jsx
    ├── Products.jsx
    ├── ProductDetail.jsx
    ├── Contact.jsx
    └── Login.jsx
```

---

## 4. 페이지 컴포넌트

### Home.jsx

```jsx
function Home() {
  return (
    <div>
      <h1>홈 페이지</h1>
      <p>React Router 실습입니다.</p>
    </div>
  );
}

export default Home;
```

### About.jsx

```jsx
function About() {
  return (
    <div>
      <h1>소개 페이지</h1>
      <p>우리 사이트를 소개합니다.</p>
    </div>
  );
}

export default About;
```

### Products.jsx / Contact.jsx

상품·연락처도 같은 방식으로 `h1` + 본문만 두면 됩니다.  
실습 프로젝트에서는 상품 목록에 `map` + `Link`를 넣었습니다.

---

## 5. App.jsx에서 Router 설정

가장 중요한 부분입니다.

```jsx
import {
  BrowserRouter,
  Routes,
  Route,
  Link,
} from "react-router-dom";

import Home from "./pages/Home";
import About from "./pages/About";
import Products from "./pages/Products";
import Contact from "./pages/Contact";

function App() {
  return (
    <BrowserRouter>
      <nav>
        <Link to="/">홈</Link>
        <Link to="/about">소개</Link>
        <Link to="/products">상품</Link>
        <Link to="/contact">연락처</Link>
      </nav>

      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/about" element={<About />} />
        <Route path="/products" element={<Products />} />
        <Route path="/contact" element={<Contact />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
```

---

## 6. 가장 중요한 3가지 (+ Link)

| 구성 | 역할 |
|------|------|
| **BrowserRouter** | 라우터 환경 만들기 |
| **Routes** | 여러 `Route` 관리 |
| **Route** | URL ↔ 컴포넌트 연결 |
| **Link** | 클릭으로 페이지 이동 |

### BrowserRouter

```jsx
<BrowserRouter>
```

React Router를 사용할 수 있도록 환경을 만듭니다.

### Routes

```jsx
<Routes>
```

여러 개의 `Route`를 관리합니다.

### Route

```jsx
<Route path="/about" element={<About />} />
```

의미: **`/about` 주소로 들어오면 `<About />`을 보여줘라.**

### Link

```jsx
<Link to="/about">소개</Link>
```

클릭하면 `/about`으로 이동합니다.

---

## 7. `<a>` 태그와 Link 차이

| | 일반 HTML | React Router |
|--|-----------|--------------|
| 태그 | `<a href="/about">` | `<Link to="/about">` |
| 동작 | 브라우저가 페이지 **다시 요청** | URL만 바꾸고 **컴포넌트만 교체** |

쉽게:

- **`<a>`** → 페이지 이동 (새로고침에 가깝게)
- **`Link`** → React 앱 **내부 이동** (SPA)

---

## 8. URL 파라미터

```
/products/1
/products/2
/products/3
```

```jsx
<Route path="/products/:id" element={<ProductDetail />} />
```

`:id` = URL 파라미터

---

## 9. useParams()

```jsx
import { useParams } from "react-router-dom";

function ProductDetail() {
  const { id } = useParams();

  return (
    <div>
      <h1>상품 상세</h1>
      <p>상품 번호 : {id}</p>
    </div>
  );
}

export default ProductDetail;
```

주소 `/products/10` → `id` 값은 `"10"`

---

## 10. 상품 목록 예제

```jsx
import { Link } from "react-router-dom";

const products = [
  { id: 1, name: "노트북", price: 1200000 },
  { id: 2, name: "마우스", price: 30000 },
  { id: 3, name: "키보드", price: 80000 },
];

function Products() {
  return (
    <div>
      <h1>상품 목록</h1>
      {products.map((product) => (
        <div key={product.id}>
          <h3>{product.name}</h3>
          <p>{product.price.toLocaleString()}원</p>
          <Link to={`/products/${product.id}`}>상세보기</Link>
        </div>
      ))}
    </div>
  );
}

export default Products;
```

- 노트북 상세보기 → `/products/1`
- 마우스 → `/products/2`
- 키보드 → `/products/3`

---

## 11. ProductDetail + Route 추가

```jsx
<Route path="/products/:id" element={<ProductDetail />} />
```

---

## 12. useNavigate()

버튼 클릭으로 이동:

```jsx
import { useNavigate } from "react-router-dom";

function Login() {
  const navigate = useNavigate();

  const handleLogin = () => {
    // 로그인 처리
    navigate("/");
  };

  return (
    <button onClick={handleLogin}>로그인</button>
  );
}

export default Login;
```

| 코드 | 의미 |
|------|------|
| `navigate("/")` | 홈으로 이동 |
| `navigate(-1)` | 뒤로가기 |
| `navigate(1)` | 앞으로가기 |

```jsx
<button onClick={() => navigate(-1)}>뒤로가기</button>
```

---

## 13. 중첩 라우팅 (다음 단계)

```
/mypage
/mypage/profile
/mypage/orders
/mypage/settings
```

```jsx
<Route path="/mypage" element={<MyPage />}>
  <Route path="profile" element={<Profile />} />
  <Route path="orders" element={<Orders />} />
  <Route path="settings" element={<Settings />} />
</Route>
```

오늘 수업에서는 **필수가 아닙니다.**  
다음 단계에서 `Outlet`, 404, 로그인 인증 라우팅으로 넘어가면 됩니다.

---

## 14. 추천 실습 순서

| 실습 | 내용 | 사용 |
|------|------|------|
| **1** | 홈 · 소개 · 상품 · 연락처 | BrowserRouter, Routes, Route, Link |
| **2** | 노트북·마우스·키보드 + 상세보기 Link | map, Link |
| **3** | 상세에서 상품 ID 출력 | useParams |
| **4** | 버튼으로 홈 이동 | useNavigate |

---

## 학생용 한 장 정리

```
React Router

URL
 ↓
Route
 ↓
컴포넌트

-------------------

Link          사용자가 클릭해서 이동
Routes        Route들을 관리
Route         URL과 컴포넌트 연결
useParams()   URL의 값 가져오기
useNavigate() 코드로 페이지 이동
```

### 프로젝트 구조 이미지

```
App
│
├── Header (nav + Link)
│
├── Routes
│    ├── /                 → Home
│    ├── /about            → About
│    ├── /products         → Products
│    ├── /products/:id     → ProductDetail
│    ├── /contact          → Contact
│    └── /login            → Login (useNavigate)
│
└── (선택) Footer
```

### 오늘 한 세트 (6개)

1. `BrowserRouter`  
2. `Routes`  
3. `Route`  
4. `Link`  
5. `useParams`  
6. `useNavigate`  

그다음: **Outlet · 중첩 라우팅 · 404 · 인증 라우팅**

---

## 관련 자료

| 파일 / 폴더 | 설명 |
|-------------|------|
| `router-basic/` | 이 문서의 실습 예제 (권장) |
| `router-simple/` | Layout + Outlet 중첩 예제 |
| `react-17차시-React-Router.md` | 차시형 수업 자료 |
| `react-18차시-URL파라미터.md` | URL 파라미터 심화 |
