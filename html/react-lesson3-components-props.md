## 3차시 – 컴포넌트 구조화 & Props 실습 교안

### 0. 학습 목표

- 화면을 **여러 컴포넌트로 쪼개는 기준**을 설명할 수 있다.
- props로 부모 → 자식으로 데이터를 전달할 수 있다.
- `children`을 활용해 **레이아웃 컴포넌트**를 만들 수 있다.

---

### 1. 이론 (슬라이드 흐름)

#### 1) 지난 시간 복습

- 1차시: React/JSX/Vite 구조
- 2차시: `useState`로 카운터 만들기

짧은 퀴즈:
- “state가 바뀌면 React는 무엇을 다시 하나요?”
- “JSX에서 반드시 지켜야 하는 규칙 2가지?”

#### 2) 컴포넌트를 왜 나누는가?

- 큰 한 파일에 모두 쓰면:
  - 읽기 어렵고, 수정이 힘듦
  - 재사용이 불가능
- 컴포넌트를 나누면:
  - **재사용 가능**
  - **역할이 분리**되어 이해가 쉬움

비유:
- 웹 페이지 = 레고 완성품
- 컴포넌트 = 레고 브릭(조각)

#### 3) props 기본 문법

```jsx
function Hello({ name }) {
  return <h1>안녕하세요, {name}님</h1>;
}

function App() {
  return (
    <div>
      <Hello name="홍길동" />
      <Hello name="React 입문자" />
    </div>
  );
}
```

- 부모가 `name="홍길동"` 을 넘기면, 자식에서 `props.name` 혹은 `{ name }`으로 사용

#### 4) children props

- 특정 위치에 **자식 컴포넌트/내용을 삽입**하고 싶을 때 사용

```jsx
function Layout({ children }) {
  return (
    <div className="layout">
      <header>헤더</header>
      <main>{children}</main>
      <footer>푸터</footer>
    </div>
  );
}

function App() {
  return (
    <Layout>
      <h1>메인 내용</h1>
      <p>본문...</p>
    </Layout>
  );
}
```

> 강의 멘트 예시  
> “Layout은 집의 구조, children은 그 안에 들어가는 가구라고 생각하면 됩니다.”

---

### 2. 실습 – 카드 & 레이아웃 컴포넌트 만들기

#### 2.1 실습 목표

- `Card` 컴포넌트 1개를 만들어서 다양한 데이터로 재사용
- `Layout` 컴포넌트로 공통 헤더/푸터 감싸기

#### 2.2 프로젝트 구조 예시

```txt
src/
  components/
    Card.jsx
    Layout.jsx
  App.jsx
  main.jsx
```

---

### 3. 코드 스켈레톤

#### 3.1 `Card` 컴포넌트

```jsx
// src/components/Card.jsx
function Card({ title, description }) {
  return (
    <div className="card">
      <h3>{title}</h3>
      <p>{description}</p>
    </div>
  );
}

export default Card;
```

CSS는 수업 중 간단히 추가(테두리, padding 정도).

#### 3.2 `Layout` 컴포넌트

```jsx
// src/components/Layout.jsx
function Layout({ children }) {
  return (
    <div className="layout">
      <header style={{ padding: '10px', background: '#667eea', color: 'white' }}>
        React 3차시 – 컴포넌트 & Props
      </header>

      <main style={{ padding: '20px' }}>{children}</main>

      <footer style={{ padding: '10px', background: '#eee', marginTop: '20px' }}>
        © 2025 React Course
      </footer>
    </div>
  );
}

export default Layout;
```

#### 3.3 `App.jsx` 에서 사용

```jsx
// src/App.jsx
import Layout from './components/Layout.jsx';
import Card from './components/Card.jsx';

function App() {
  return (
    <Layout>
      <h1>대시보드</h1>
      <div style={{ display: 'grid', gridTemplateColumns: 'repeat(3, 1fr)', gap: '16px' }}>
        <Card title="오늘 할 일" description="React 강의 듣기" />
        <Card title="알림" description="메일함 확인" />
        <Card title="메모" description="아이디어 정리" />
      </div>
    </Layout>
  );
}

export default App;
```

---

### 4. 과제

1. **`ProfileCard` 만들기**
   - props: `name`, `job`, `skills(문자열 배열)`
   - 예시:
     ```jsx
     <ProfileCard
       name="홍길동"
       job="Frontend Developer"
       skills={['React', 'JavaScript', 'CSS']}
     />
     ```
   - `skills.map()`을 사용해 리스트로 렌더링

2. **`Layout` 확장**
   - 사이드바 영역을 추가:
     - 좌측: 메뉴 리스트
     - 우측: 메인 내용 (`children`)

> 다음 차시 예고:  
> “다음 시간에는 지금 만든 카드 리스트에 **상태**를 추가해서, 동적으로 리스트를 추가/삭제하는 ToDo 앱을 만들어 봅니다.”



