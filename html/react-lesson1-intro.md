## 1차시 React 입문 교안 (입문자용)

### 0. 강의 목표

- **React가 무엇인지** 설명할 수 있다.
- **SPA 개념**을 이해한다.
- **JSX 기본 문법**을 사용할 수 있다.
- **함수형 컴포넌트**로 간단한 UI를 만들 수 있다.
- **Vite 기반 React 프로젝트 구조**를 이해하고, `HelloReact` 컴포넌트를 직접 만든다.

---

### 1. React란 무엇인가

#### 1) 정의

- **React**: 메타(페이스북)가 만든 **UI 라이브러리**
  - “페이지 전체”를 만드는 프레임워크라기보다, **화면의 한 조각(컴포넌트)**을 만드는 도구
- 목적:
  - 데이터가 바뀔 때마다 **화면을 효율적으로 다시 그려 주는 것**
  - 복잡한 UI를 **작은 컴포넌트 단위**로 쪼개서 관리

#### 2) 왜 React를 쓰나?

- **상태(state)**가 바뀌면, React가 알아서 필요한 부분만 다시 렌더링
- 컴포넌트 단위로 나누면:
  - 재사용이 쉬움
  - 유지보수가 편해짐
- 생태계(라이브러리, 자료, 커뮤니티)가 매우 크다.

> 강의 멘트 예시  
> “예전에는 jQuery로 DOM을 직접 잡아서 수정했죠. React는 ‘DOM을 직접 건드리지 말고, **상태를 기준으로 화면을 선언**해라’라는 철학을 갖고 있습니다.”

---

### 2. SPA(Single Page Application) 개념

#### 1) 기존 웹(멀티 페이지) 방식

- 링크를 클릭할 때마다 **서버에서 새로운 HTML 페이지를 받아옴**
- 화면 전체가 깜빡이며 새로고침됨

#### 2) SPA 방식

- 처음에 **한 번만 HTML + JS 번들**을 가져옴
- 그 이후에는:
  - 주소(URL)는 바뀌지만,
  - **필요한 데이터만 서버에서 받아와서** JavaScript로 **현재 페이지 안에서 화면만 바꿔줌**

**장점**
- 빠른 화면 전환
- 앱(App) 같은 부드러운 UX

**단점**
- 초기 로딩이 상대적으로 무거울 수 있음
- SEO(검색엔진 최적화) 대응이 추가로 필요할 수 있음

> 강의 멘트 예시  
> “여러분이 자주 쓰는 유튜브, 넷플릭스 같은 사이트들이 대표적인 SPA입니다. 주소는 바뀌는데, 전체 새로고침 없이 화면만 바뀌죠.”

---

### 3. JSX 문법

#### 1) JSX란?

- JavaScript 안에서 **XML/HTML과 비슷한 문법**을 쓸 수 있게 해주는 문법
- 브라우저가 직접 이해하는 건 아니고, **빌드 과정에서 JS 코드로 변환**됨

```jsx
const element = <h1>Hello, React!</h1>;
```

이는 빌드 후 대략 다음과 비슷한 코드가 된다:

```js
const element = React.createElement('h1', null, 'Hello, React!');
```

#### 2) JSX 기본 규칙

- **반드시 하나의 부모 요소로 감싸야 함**

```jsx
// ❌ 오류
return (
  <h1>Hello</h1>
  <p>React</p>
);

// ✅ 올바른 예 (부모로 한 번 감싸기)
return (
  <div>
    <h1>Hello</h1>
    <p>React</p>
  </div>
);
```

- **JavaScript 표현식은 `{}` 안에 쓴다**

```jsx
const name = '홍길동';

return <h1>안녕하세요, {name}님</h1>;
```

- **속성 이름은 `camelCase` 사용 (HTML과 조금 다름)**

```jsx
// HTML
<div class="box"></div>

// JSX
<div className="box"></div>

// HTML
<button onclick="handleClick()"></button>

// JSX
<button onClick={handleClick}></button>
```

- **Self-closing 태그 사용 가능**

```jsx
// 둘 다 가능
<img src="/logo.png" />
<img src="/logo.png"></img>
```

---

### 4. 함수형 컴포넌트 작성법

#### 1) 함수형 컴포넌트란?

- **JavaScript 함수** 하나가 곧 하나의 컴포넌트
- **이름은 반드시 대문자로 시작**
- JSX를 `return` 한다

```jsx
function HelloReact() {
  return <h1>Hello, React!</h1>;
}
```

사용 예:

```jsx
function App() {
  return (
    <div>
      <HelloReact />
    </div>
  );
}
```

- React 16+ 이후에는 **함수형 컴포넌트 + Hooks** 방식이 표준

#### 2) props 사용 예

```jsx
function HelloReact(props) {
  return <h1>Hello, {props.name}!</h1>;
}

function App() {
  return (
    <div>
      <HelloReact name="홍길동" />
      <HelloReact name="React 입문자" />
    </div>
  );
}
```

---

### 5. Vite 기반 React 프로젝트 구조 설명

#### 1) Vite란?

- 새로운 프론트엔드 빌드 도구
  - 매우 빠른 개발 서버
  - React 템플릿 제공
- CRA(create-react-app)의 **가벼운 대체제**로 많이 사용

#### 2) 프로젝트 생성 (설명용)

```bash
# 1) Vite 프로젝트 생성
npm create vite@latest my-react-app

# 2) 템플릿 선택
# ? Select a framework: React
# ? Select a variant:   JavaScript 또는 TypeScript

# 3) 프로젝트 폴더로 이동
cd my-react-app

# 4) 의존성 설치
npm install

# 5) 개발 서버 실행
npm run dev
```

#### 3) 기본 구조 (React + Vite)

설명 예시:

- `index.html`
  - **단 하나의 HTML 파일**
  - `<div id="root"></div>` 안에 React가 렌더링됨
- `src/main.jsx`
  - React 앱의 **진입점(Entry)**
  - `ReactDOM.createRoot(...).render(<App />)` 코드가 있음
- `src/App.jsx`
  - 실제 화면을 구성하는 **루트 컴포넌트**
  - 여기에 우리가 만든 `HelloReact` 등 컴포넌트를 배치

```jsx
// src/main.jsx
import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App.jsx';
import './index.css';

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);
```

```jsx
// src/App.jsx
function App() {
  return (
    <div>
      <h1>Vite + React 시작</h1>
    </div>
  );
}

export default App;
```

> 강의 멘트 예시  
> “실제로는 브라우저가 `index.html` 하나만 로딩합니다. 그리고 `main.jsx`에서 `App` 컴포넌트를 `root`라는 div 안에 그려주는 구조예요. 이게 바로 SPA의 기본 골격입니다.”

---

### 6. 실습 1 — HelloReact 컴포넌트 만들기

#### 1) 목표

- `HelloReact`라는 **함수형 컴포넌트**를 만들고,
- `App` 컴포넌트에서 **여러 번 사용**해 본다.

#### 2) 단계별 안내

1. `src/HelloReact.jsx` 파일 생성

```jsx
// src/HelloReact.jsx
function HelloReact() {
  return <h1>Hello, React!</h1>;
}

export default HelloReact;
```

2. `src/App.jsx`에서 `HelloReact` 사용

```jsx
// src/App.jsx
import HelloReact from './HelloReact.jsx';

function App() {
  return (
    <div>
      <HelloReact />
      <HelloReact />
      <HelloReact />
    </div>
  );
}

export default App;
```

3. 브라우저에서 확인
   - `npm run dev`
   - 터미널에 나오는 주소(보통 `http://localhost:5173`) 접속
   - 화면에 **Hello, React!** 문구가 여러 번 나오는지 확인

#### 3) props 추가해 보기 (확장 실습)

```jsx
// src/HelloReact.jsx
function HelloReact({ name }) {
  return <h1>Hello, {name}!</h1>;
}

export default HelloReact;
```

```jsx
// src/App.jsx
import HelloReact from './HelloReact.jsx';

function App() {
  return (
    <div>
      <HelloReact name="홍길동" />
      <HelloReact name="React 입문자" />
      <HelloReact name="JavaScript 개발자" />
    </div>
  );
}

export default App;
```

---

### 7. 실습 2 — JSX 문법 오류 예시

“왜 에러가 나는지”를 보여주는 게 JSX 이해에 도움이 됩니다.

#### 7.1 부모 요소가 둘 이상인 경우

```jsx
// ❌ 오류 예제: JSX expressions must have one parent element
function BadComponent() {
  return (
    <h1>Hello</h1>
    <p>React</p>
  );
}
```

**설명 포인트**

- JSX는 **하나의 부모 요소만** 반환해야 한다.
- 아래처럼 `<div>` 또는 `<>...</>`(Fragment)로 감싸야 한다.

```jsx
// ✅ 올바른 예제
function GoodComponent() {
  return (
    <div>
      <h1>Hello</h1>
      <p>React</p>
    </div>
  );
}
```

#### 7.2 JS 표현식에 문자열을 직접 섞는 경우

```jsx
// ❌ 오류 예제
function BadComponent() {
  const name = '홍길동';
  return <h1>안녕하세요, {name 님}</h1>; // name 뒤에 문자열을 그냥 이어붙임
}
```

**설명 포인트**

- `{}` 안에는 **순수한 JS 표현식**만 와야 한다.
- 문자열을 섞고 싶으면 **템플릿 리터럴**을 사용하거나, `{}`를 나눠 써야 한다.

```jsx
// ✅ 수정 1: 템플릿 리터럴 사용
function GoodComponent() {
  const name = '홍길동';
  return <h1>{`안녕하세요, ${name}님`}</h1>;
}

// ✅ 수정 2: JSX 안에서 나눠 쓰기
function GoodComponent2() {
  const name = '홍길동';
  return <h1>안녕하세요, {name}님</h1>;
}
```

#### 7.3 속성 이름을 HTML 방식으로 쓰는 경우

```jsx
// ❌ 오류 or 경고
function BadComponent() {
  return <div class="container">Hello</div>;
}
```

```jsx
// ✅ JSX 방식
function GoodComponent() {
  return <div className="container">Hello</div>;
}
```

**강조 포인트**

- `class` → `className`
- `onclick` → `onClick`
- `for` → `htmlFor` (label 태그에서)

---

### 8. 마무리 정리

1차시에서 학습한 내용 정리:

- **React란?**
  - UI를 효율적으로 만들기 위한 **라이브러리**
  - 상태가 바뀌면 자동으로 화면을 다시 그려줌
- **SPA 개념**
  - 한 번 로드된 페이지 안에서 JS로 화면만 교체
- **JSX 문법**
  - JS 안에서 HTML 비슷한 문법
  - 하나의 부모 요소, `{}` 안에 JS 표현식, camelCase 속성
- **함수형 컴포넌트**
  - 함수 + JSX `return`
  - 이름은 대문자로 시작
- **Vite 프로젝트 구조**
  - `index.html` → `main.jsx` → `App.jsx` 구조 이해
- **실습**
  - `HelloReact` 컴포넌트 만들기
  - JSX에서 자주 나오는 오류 패턴 살펴보기

> 다음 차시 예고 멘트 예시  
> “다음 시간에는 React에서 **상태(state)**를 어떻게 관리하는지, 그리고 버튼 클릭 같은 **이벤트 처리(onClick)**는 어떻게 하는지 살펴보겠습니다. 오늘 만든 `HelloReact` 컴포넌트에 버튼을 달아서, 클릭하면 문구가 바뀌도록 만들어볼 거예요.”



