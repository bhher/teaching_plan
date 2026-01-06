# 3차시. 컴포넌트 개념

## 학습 목표
- 컴포넌트의 개념을 이해할 수 있다
- 함수형 컴포넌트를 작성할 수 있다
- 컴포넌트를 분리하고 재사용할 수 있다
- Header와 Footer 컴포넌트를 분리할 수 있다

---

## 1. 컴포넌트란?

### 1.1 컴포넌트의 정의

**컴포넌트**는 UI를 독립적이고 재사용 가능한 조각으로 나눈 것입니다.

**비유:**
- 레고 블록처럼 조립해서 사용
- 각 블록은 독립적이지만 함께 사용 가능

**컴포넌트의 장점:**
- ✅ **재사용성**: 한 번 만들면 여러 곳에서 사용
- ✅ **유지보수**: 수정이 쉬움
- ✅ **가독성**: 코드가 명확하고 이해하기 쉬움
- ✅ **테스트**: 각 컴포넌트를 독립적으로 테스트 가능

### 1.2 컴포넌트의 종류

**함수형 컴포넌트 (Function Component)** - 권장 ⭐
```jsx
function Welcome() {
  return <h1>안녕하세요!</h1>;
}
```

**클래스형 컴포넌트 (Class Component)** - 구식
```jsx
class Welcome extends React.Component {
  render() {
    return <h1>안녕하세요!</h1>;
  }
}
```

**현재는 함수형 컴포넌트를 사용합니다!**

---

## 2. 함수형 컴포넌트

### 2.1 기본 구조

**기본 형태:**
```jsx
function 컴포넌트명() {
  return (
    <div>
      {/* JSX 내용 */}
    </div>
  );
}
```

**예시:**
```jsx
function Welcome() {
  return <h1>환영합니다!</h1>;
}
```

### 2.2 화살표 함수 사용

**일반 함수:**
```jsx
function Welcome() {
  return <h1>환영합니다!</h1>;
}
```

**화살표 함수:**
```jsx
const Welcome = () => {
  return <h1>환영합니다!</h1>;
};
```

**간단한 경우 (한 줄):**
```jsx
const Welcome = () => <h1>환영합니다!</h1>;
```

**둘 다 사용 가능하지만, 화살표 함수가 더 현대적입니다.**

### 2.3 컴포넌트 사용하기

**컴포넌트를 HTML 태그처럼 사용:**
```jsx
function App() {
  return (
    <div>
      <Welcome />
      <Welcome />
      <Welcome />
    </div>
  );
}
```

**예시:**
```jsx
// Welcome.jsx
function Welcome() {
  return <h1>환영합니다!</h1>;
}

// App.jsx
import Welcome from './Welcome';

function App() {
  return (
    <div>
      <Welcome />
      <p>React를 배우고 있습니다.</p>
      <Welcome />
    </div>
  );
}
```

---

## 3. 컴포넌트 분리

### 3.1 왜 컴포넌트를 분리할까?

**하나의 큰 컴포넌트 (나쁜 예):**
```jsx
function App() {
  return (
    <div>
      <header>
        <h1>웹사이트 제목</h1>
        <nav>
          <a href="/">홈</a>
          <a href="/about">소개</a>
        </nav>
      </header>
      
      <main>
        <h2>메인 콘텐츠</h2>
        <p>내용...</p>
      </main>
      
      <footer>
        <p>© 2024 웹사이트</p>
      </footer>
    </div>
  );
}
```

**문제점:**
- ❌ 코드가 길고 복잡함
- ❌ 재사용이 어려움
- ❌ 유지보수가 어려움

**컴포넌트로 분리 (좋은 예):**
```jsx
function Header() {
  return (
    <header>
      <h1>웹사이트 제목</h1>
      <nav>
        <a href="/">홈</a>
        <a href="/about">소개</a>
      </nav>
    </header>
  );
}

function Main() {
  return (
    <main>
      <h2>메인 콘텐츠</h2>
      <p>내용...</p>
    </main>
  );
}

function Footer() {
  return (
    <footer>
      <p>© 2024 웹사이트</p>
    </footer>
  );
}

function App() {
  return (
    <div>
      <Header />
      <Main />
      <Footer />
    </div>
  );
}
```

**장점:**
- ✅ 코드가 간결하고 읽기 쉬움
- ✅ 각 컴포넌트를 독립적으로 수정 가능
- ✅ 재사용 가능

### 3.2 컴포넌트 분리 기준

**분리 기준:**
1. **기능별**: Header, Footer, Sidebar 등
2. **재사용성**: 여러 곳에서 사용하는 부분
3. **복잡도**: 너무 복잡한 부분은 분리
4. **관심사 분리**: 각 컴포넌트는 하나의 역할만

---

## 4. 컴포넌트 재사용

### 4.1 재사용의 개념

**같은 컴포넌트를 여러 번 사용:**
```jsx
function Button() {
  return <button>클릭</button>;
}

function App() {
  return (
    <div>
      <Button />
      <Button />
      <Button />
    </div>
  );
}
```

### 4.2 재사용 예시

**카드 컴포넌트:**
```jsx
function Card() {
  return (
    <div className="card">
      <h3>제목</h3>
      <p>내용</p>
    </div>
  );
}

function App() {
  return (
    <div>
      <Card />
      <Card />
      <Card />
    </div>
  );
}
```

**결과:**
- 같은 내용의 카드가 3개 생성됨
- 각 카드의 내용을 다르게 하려면? → 다음 차시에서 Props 배움!

---

## 5. 실습: Header / Footer 컴포넌트 분리

### 실습 1: 기본 구조 만들기

**1단계: Header 컴포넌트 생성**

`src/components/Header.jsx` 파일 생성:
```jsx
function Header() {
  return (
    <header style={{ 
      backgroundColor: '#333', 
      color: 'white', 
      padding: '1rem',
      textAlign: 'center'
    }}>
      <h1>My Website</h1>
      <nav>
        <a href="/" style={{ color: 'white', margin: '0 10px' }}>홈</a>
        <a href="/about" style={{ color: 'white', margin: '0 10px' }}>소개</a>
        <a href="/contact" style={{ color: 'white', margin: '0 10px' }}>연락처</a>
      </nav>
    </header>
  );
}

export default Header;
```

**2단계: Footer 컴포넌트 생성**

`src/components/Footer.jsx` 파일 생성:
```jsx
function Footer() {
  return (
    <footer style={{ 
      backgroundColor: '#333', 
      color: 'white', 
      padding: '1rem',
      textAlign: 'center',
      marginTop: '2rem'
    }}>
      <p>© 2024 My Website. All rights reserved.</p>
      <p>Contact: info@mywebsite.com</p>
    </footer>
  );
}

export default Footer;
```

**3단계: App.jsx에서 사용**

`src/App.jsx` 수정:
```jsx
import Header from './components/Header';
import Footer from './components/Footer';
import './App.css';

function App() {
  return (
    <div className="App">
      <Header />
      
      <main style={{ padding: '2rem', minHeight: '60vh' }}>
        <h2>메인 콘텐츠</h2>
        <p>여기에 메인 콘텐츠가 들어갑니다.</p>
      </main>
      
      <Footer />
    </div>
  );
}

export default App;
```

### 실습 2: 컴포넌트 폴더 구조

**권장 폴더 구조:**
```
src/
├── components/          # 재사용 가능한 컴포넌트
│   ├── Header.jsx
│   ├── Footer.jsx
│   └── Button.jsx
├── App.jsx
├── main.jsx
└── App.css
```

**컴포넌트 파일명 규칙:**
- 첫 글자는 대문자 (예: `Header.jsx`)
- 파일명과 컴포넌트명이 일치하는 것이 좋음

### 실습 3: 추가 컴포넌트 만들기

**Button 컴포넌트:**
```jsx
// src/components/Button.jsx
function Button() {
  return (
    <button style={{
      padding: '10px 20px',
      backgroundColor: '#007bff',
      color: 'white',
      border: 'none',
      borderRadius: '5px',
      cursor: 'pointer'
    }}>
      클릭하세요
    </button>
  );
}

export default Button;
```

**App.jsx에서 사용:**
```jsx
import Header from './components/Header';
import Footer from './components/Footer';
import Button from './components/Button';

function App() {
  return (
    <div className="App">
      <Header />
      
      <main>
        <h2>메인 콘텐츠</h2>
        <Button />
        <Button />
        <Button />
      </main>
      
      <Footer />
    </div>
  );
}
```

---

## 6. 실습 과제

### 과제 1: 기본 레이아웃 만들기

**요구사항:**
- Header 컴포넌트 생성 (로고, 네비게이션)
- Footer 컴포넌트 생성 (저작권 정보)
- Main 컴포넌트 생성 (메인 콘텐츠)
- App에서 모두 조합하여 사용

### 과제 2: 재사용 가능한 컴포넌트 만들기

**요구사항:**
- Card 컴포넌트 생성
- App에서 Card를 3개 이상 사용
- 각 Card는 같은 구조이지만 내용은 다르게 (나중에 Props로 개선)

**예시 구조:**
```jsx
function Card() {
  return (
    <div className="card">
      <h3>카드 제목</h3>
      <p>카드 내용</p>
    </div>
  );
}
```

### 과제 3: 프로필 컴포넌트 만들기

**요구사항:**
- Profile 컴포넌트 생성
- 이름, 사진, 소개 포함
- App에서 여러 개 사용

---

## 7. 자주 발생하는 오류

### 오류 1: `'컴포넌트명' is not defined`

**원인:** 컴포넌트를 import하지 않음

**해결:**
```jsx
// 컴포넌트 파일에서 export
export default Header;

// 사용하는 파일에서 import
import Header from './components/Header';
```

### 오류 2: `Cannot find module`

**원인:** 파일 경로가 잘못됨

**해결:**
- 파일 경로 확인
- 대소문자 확인 (Windows는 대소문자 구분 안 하지만 확인 필요)
- 파일 확장자 확인 (.jsx)

### 오류 3: 컴포넌트가 화면에 안 보임

**원인:** 컴포넌트를 사용하지 않음

**해결:**
```jsx
// ❌
function App() {
  return <div>Hello</div>;
}

// ✅
function App() {
  return (
    <div>
      <Header />
      <div>Hello</div>
    </div>
  );
}
```

---

## 8. 컴포넌트 네이밍 규칙

### 규칙 1: 첫 글자는 대문자

**✅ 올바른 예:**
```jsx
function Header() { }
function UserProfile() { }
function NavigationBar() { }
```

**❌ 잘못된 예:**
```jsx
function header() { }      // 소문자 시작
function userProfile() { }  // 소문자 시작
```

### 규칙 2: 파일명과 컴포넌트명 일치

**✅ 올바른 예:**
- 파일: `Header.jsx` → 컴포넌트: `Header`
- 파일: `UserProfile.jsx` → 컴포넌트: `UserProfile`

### 규칙 3: 의미 있는 이름 사용

**✅ 좋은 예:**
```jsx
function UserCard() { }
function ProductList() { }
function NavigationMenu() { }
```

**❌ 나쁜 예:**
```jsx
function Component1() { }
function Test() { }
function A() { }
```

---

## 9. 다음 차시 예고

다음 차시에서는 **Props**를 배웁니다:
- Props 개념
- 부모에서 자식으로 데이터 전달
- Props 구조 분해 할당
- 게시글 카드 컴포넌트 만들기

---

## 요약

### 핵심 개념

1. **컴포넌트**: UI를 독립적이고 재사용 가능한 조각으로 나눈 것
2. **함수형 컴포넌트**: 함수로 작성한 컴포넌트 (현재 표준)
3. **컴포넌트 분리**: 큰 컴포넌트를 작은 컴포넌트로 나누기
4. **재사용성**: 같은 컴포넌트를 여러 번 사용

### 필수 문법

```jsx
// 컴포넌트 정의
function 컴포넌트명() {
  return <div>내용</div>;
}

// 컴포넌트 사용
<컴포넌트명 />

// export
export default 컴포넌트명;

// import
import 컴포넌트명 from './경로';
```

### 체크리스트

- [ ] 컴포넌트의 개념 이해
- [ ] 함수형 컴포넌트 작성 가능
- [ ] 컴포넌트 분리 방법 이해
- [ ] Header 컴포넌트 분리 완료
- [ ] Footer 컴포넌트 분리 완료
- [ ] 컴포넌트 재사용 이해

---

**다음 차시에서 만나요! 🚀**

