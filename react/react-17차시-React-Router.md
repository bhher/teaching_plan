# 17차시. React Router

## 학습 목표
- SPA 라우팅의 개념을 이해할 수 있다
- React Router를 설치하고 설정할 수 있다
- BrowserRouter, Route, Link를 사용할 수 있다
- 페이지 이동을 구현할 수 있다

---

## 1. SPA 라우팅이란?

### 1.1 전통적인 웹사이트

**MPA (Multi Page Application):**
```
사용자 클릭
    ↓
서버에 요청
    ↓
새로운 HTML 페이지 받음
    ↓
전체 페이지 새로고침
```

**문제점:**
- ❌ 페이지 새로고침 발생
- ❌ 느린 사용자 경험
- ❌ 서버 부하

### 1.2 SPA 라우팅

**SPA (Single Page Application):**
```
사용자 클릭
    ↓
JavaScript가 URL 변경
    ↓
해당하는 컴포넌트만 렌더링
    ↓
페이지 새로고침 없음
```

**장점:**
- ✅ 빠른 페이지 전환
- ✅ 부드러운 사용자 경험
- ✅ 서버 부하 감소

---

## 2. React Router 설치

### 2.1 설치

```bash
npm install react-router-dom
```

### 2.2 기본 설정

**App.jsx:**
```jsx
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Home from './pages/Home';
import About from './pages/About';
import Contact from './pages/Contact';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/about" element={<About />} />
        <Route path="/contact" element={<Contact />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
```

---

## 3. 주요 컴포넌트

### 3.1 BrowserRouter

**역할:** 라우팅을 활성화하는 최상위 컴포넌트

```jsx
import { BrowserRouter } from 'react-router-dom';

function App() {
  return (
    <BrowserRouter>
      {/* 라우트 설정 */}
    </BrowserRouter>
  );
}
```

### 3.2 Routes & Route

**Routes:** 여러 Route를 그룹화

**Route:** 경로와 컴포넌트를 연결

```jsx
import { Routes, Route } from 'react-router-dom';

<Routes>
  <Route path="/" element={<Home />} />
  <Route path="/about" element={<About />} />
</Routes>
```

### 3.3 Link

**역할:** 페이지 이동을 위한 링크

```jsx
import { Link } from 'react-router-dom';

<Link to="/about">소개</Link>
```

**a 태그와의 차이:**
- `Link`: 페이지 새로고침 없이 이동 (SPA)
- `a`: 페이지 새로고침 발생 (전통적인 방식)

---

## 4. 실습: 페이지 이동 구현

### 실습 1: 기본 라우팅

**요구사항:**
- 홈, 소개, 연락처 페이지
- 네비게이션 메뉴
- 페이지 이동

**폴더 구조:**
```
src/
├── pages/
│   ├── Home.jsx
│   ├── About.jsx
│   └── Contact.jsx
├── components/
│   └── Navigation.jsx
└── App.jsx
```

**Home.jsx:**
```jsx
function Home() {
  return (
    <div style={{ padding: '2rem' }}>
      <h1>홈</h1>
      <p>홈 페이지입니다.</p>
    </div>
  );
}

export default Home;
```

**About.jsx:**
```jsx
function About() {
  return (
    <div style={{ padding: '2rem' }}>
      <h1>소개</h1>
      <p>소개 페이지입니다.</p>
    </div>
  );
}

export default About;
```

**Contact.jsx:**
```jsx
function Contact() {
  return (
    <div style={{ padding: '2rem' }}>
      <h1>연락처</h1>
      <p>연락처 페이지입니다.</p>
    </div>
  );
}

export default Contact;
```

**Navigation.jsx:**
```jsx
import { Link } from 'react-router-dom';

function Navigation() {
  return (
    <nav style={{
      backgroundColor: '#333',
      padding: '1rem'
    }}>
      <div style={{
        display: 'flex',
        gap: '1rem'
      }}>
        <Link 
          to="/" 
          style={{ color: 'white', textDecoration: 'none' }}
        >
          홈
        </Link>
        <Link 
          to="/about" 
          style={{ color: 'white', textDecoration: 'none' }}
        >
          소개
        </Link>
        <Link 
          to="/contact" 
          style={{ color: 'white', textDecoration: 'none' }}
        >
          연락처
        </Link>
      </div>
    </nav>
  );
}

export default Navigation;
```

**App.jsx:**
```jsx
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Navigation from './components/Navigation';
import Home from './pages/Home';
import About from './pages/About';
import Contact from './pages/Contact';

function App() {
  return (
    <BrowserRouter>
      <Navigation />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/about" element={<About />} />
        <Route path="/contact" element={<Contact />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
```

### 실습 2: NavLink 사용

**활성 링크 스타일링:**
```jsx
import { NavLink } from 'react-router-dom';

function Navigation() {
  return (
    <nav>
      <NavLink 
        to="/"
        style={({ isActive }) => ({
          color: isActive ? '#007bff' : 'white',
          textDecoration: 'none',
          fontWeight: isActive ? 'bold' : 'normal'
        })}
      >
        홈
      </NavLink>
      <NavLink 
        to="/about"
        style={({ isActive }) => ({
          color: isActive ? '#007bff' : 'white',
          textDecoration: 'none',
          fontWeight: isActive ? 'bold' : 'normal'
        })}
      >
        소개
      </NavLink>
    </nav>
  );
}
```

---

## 5. useNavigate Hook

### 5.1 프로그래밍 방식 네비게이션

**useNavigate 사용:**
```jsx
import { useNavigate } from 'react-router-dom';

function LoginButton() {
  const navigate = useNavigate();
  
  const handleLogin = () => {
    // 로그인 처리 후
    navigate('/dashboard');
  };
  
  return <button onClick={handleLogin}>로그인</button>;
}
```

**뒤로 가기:**
```jsx
const navigate = useNavigate();

<button onClick={() => navigate(-1)}>뒤로</button>
```

---

## 6. 실습 과제

### 과제 1: 블로그 라우팅

**요구사항:**
- 홈, 포스트 목록, 포스트 작성 페이지
- 네비게이션 메뉴
- 페이지 이동

---

## 7. 다음 차시 예고

다음 차시에서는 **URL 파라미터**를 배웁니다:
- useParams Hook
- 동적 라우팅
- 상세 페이지 구성

---

## 요약

### 핵심 개념

1. **SPA 라우팅**: 페이지 새로고침 없이 화면 전환
2. **BrowserRouter**: 라우팅 활성화
3. **Route**: 경로와 컴포넌트 연결
4. **Link**: 페이지 이동 링크

### 필수 문법

```jsx
// 라우팅 설정
<BrowserRouter>
  <Routes>
    <Route path="/" element={<Home />} />
  </Routes>
</BrowserRouter>

// 링크
<Link to="/about">소개</Link>

// 프로그래밍 방식
const navigate = useNavigate();
navigate('/about');
```

### 체크리스트

- [ ] SPA 라우팅 개념 이해
- [ ] React Router 설치 및 설정
- [ ] Route 설정 가능
- [ ] Link 사용 가능
- [ ] 페이지 이동 구현 완료

---

**다음 차시에서 만나요! 🚀**

