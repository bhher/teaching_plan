# React 설치 및 기초 사용법 교안

**작성일:** 2026-09-15  
**대상:** 비전공자·입문자  
**환경:** Node.js + **Vite** + React (**JavaScript / `.jsx`**)

**관련 자료**
- [react-0회차-개발환경-설정.md](./react-0회차-개발환경-설정.md)
- [react-1차시-React개요-개발환경.md](./react-1차시-React개요-개발환경.md)
- [react-2차시-JSX문법.md](./react-2차시-JSX문법.md)
- [react-3차시-컴포넌트개념.md](./react-3차시-컴포넌트개념.md)
- [react-4차시-Props.md](./react-4차시-Props.md)
- **문제 / 정답:** [React-설치-및-기초-사용법-문제.md](./React-설치-및-기초-사용법-문제.md) · [React-설치-및-기초-사용법-정답.md](./React-설치-및-기초-사용법-정답.md)

> 이 교안은 **Create React App(CRA) 방식이 아니라 Vite** 기준입니다.  
> 파일 확장자는 **`.jsx`**, 기본값은 **`defaultProps` 대신 기본 매개변수**를 사용합니다.

---

## 목차

1. [React란?](#1-react란)
2. [필요한 프로그램](#2-react-개발에-필요한-프로그램)
3. [Node.js 설치](#3-nodejs-설치)
4. [Vite로 프로젝트 생성](#4-react-프로젝트-생성-vite)
5. [패키지 설치 · 실행](#5-패키지-설치--개발-서버-실행)
6. [프로젝트 구조](#6-react-프로젝트-구조)
7. [main.jsx · App.jsx](#7-mainjsx--appjsx)
8. [JSX란? · 기본 규칙](#8-jsx란--기본-규칙)
9. [첫 컴포넌트 Hello.jsx](#9-나의-첫-리액트-컴포넌트-hellojsx)
10. [props로 값 전달](#10-props로-컴포넌트에게-값-전달하기)
11. [props.children](#11-propschildren)
12. [조건부 렌더링](#12-조건부-렌더링)
13. [오늘 체크리스트](#13-오늘-실습-체크리스트)

---

## 1. React란?

React는 **사용자 인터페이스(UI)** 를 만들기 위한 **JavaScript 라이브러리**입니다.

가장 큰 특징은 화면을 **컴포넌트(Component)** 단위로 나눠 개발한다는 점입니다.

쇼핑몰 예:

```
App
 ├── Header
 ├── Navigation
 ├── ProductList
 │    ├── Product
 │    ├── Product
 │    └── Product
 ├── Cart
 └── Footer
```

| 개념 | 설명 |
|------|------|
| 컴포넌트 | 재사용 가능한 UI 조각 |
| JSX | JS 안에서 HTML처럼 화면을 작성하는 문법 |
| 렌더링 | 컴포넌트를 화면에 그리는 것 |

---

## 2. React 개발에 필요한 프로그램

| 프로그램 | 용도 |
|----------|------|
| **Node.js** | 프로젝트 생성·실행 (`node`, `npm`) |
| **VS Code** | 코드 편집 |
| **웹 브라우저** | Chrome 등 |

Node.js를 설치하면 다음을 사용할 수 있습니다.

- `node` — JavaScript 실행
- `npm` — 패키지 설치·스크립트 실행

---

## 3. Node.js 설치

1. [https://nodejs.org](https://nodejs.org) 접속  
2. **LTS** 버전 설치 권장  
3. 설치 후 CMD / PowerShell에서 확인:

```bash
node --version
npm --version
```

또는:

```bash
node -v
npm -v
```

예:

```
v22.x.x
10.x.x
```

버전이 나오면 정상입니다.

---

## 4. React 프로젝트 생성 (Vite)

요즘 학습·실습에서는 **Vite**가 빠르고 간단합니다.

### 4-1. 폴더로 이동

```bash
D:
cd D:\react
```

### 4-2. 대화형 생성

```bash
npm create vite@latest
```

예시 선택:

| 질문 | 선택 |
|------|------|
| Project name | `my-react-app` |
| Framework | **React** |
| Variant | **JavaScript** |

### 4-3. 한 줄로 생성 (수업용 추천)

```bash
npm create vite@latest my-react-app -- --template react
```

→ `my-react-app` 폴더가 생성됩니다.

```bash
cd my-react-app
```

---

## 5. 패키지 설치 · 개발 서버 실행

### 설치

```bash
npm install
```

`npm i` 와 같습니다.

### 실행

```bash
npm run dev
```

터미널에 대략 다음과 같이 나옵니다.

```
Local: http://localhost:5173/
```

브라우저에서 [http://localhost:5173](http://localhost:5173) 접속 → React 화면이 보이면 성공입니다.

서버 종료: 터미널에서 `Ctrl + C`

---

## 6. React 프로젝트 구조

```
my-react-app
│
├── node_modules/     ← 설치된 패키지 (직접 수정 X)
├── public/
├── src/
│   ├── assets/
│   ├── App.css
│   ├── App.jsx       ← 기본 화면 컴포넌트 ⭐
│   ├── index.css
│   └── main.jsx      ← 프로그램 시작점 ⭐
│
├── index.html        ← root div가 있는 HTML
├── package.json
└── vite.config.js
```

초급에서 특히 볼 파일:

- `src/main.jsx`
- `src/App.jsx`
- `src/App.css`
- `package.json`
- `index.html`

---

## 7. main.jsx · App.jsx

### 7-1. main.jsx — 시작점

```jsx
import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <App />
  </StrictMode>,
)
```

핵심:

- `index.html` 의 `<div id="root"></div>` 안에  
- `<App />` 컴포넌트를 그려 넣습니다.

> (참고) 예전 CRA는 `ReactDOM.render(...)` 를 썼습니다.  
> **지금은 `createRoot(...).render(...)`** 를 사용합니다.

### 7-2. App.jsx — 기본 컴포넌트

`src/App.jsx` 를 단순화해 실습합니다.

```jsx
function App() {
  return (
    <h1>Hello React!</h1>
  )
}

export default App
```

브라우저: **Hello React!**

---

## 8. JSX란? · 기본 규칙

React에서는 HTML과 비슷한 문법을 JS 안에서 쓸 수 있는데, 이를 **JSX**라고 합니다.

```jsx
function App() {
  return (
    <div>
      <h1>홍길동</h1>
      <p>React를 공부하고 있습니다.</p>
    </div>
  )
}

export default App
```

Babel이 JSX를 JavaScript로 변환해 브라우저가 이해할 수 있게 합니다.

### 8-1. class → className

```html
<!-- HTML -->
<div class="box">
```

```jsx
{/* JSX */}
<div className="box">
```

### 8-2. JS 값 넣기 — `{ }`

```jsx
function App() {
  const name = '홍길동'
  const age = 20

  return (
    <div>
      <h1>이름 : {name}</h1>
      <p>나이 : {age}</p>
    </div>
  )
}

export default App
```

### 8-3. 태그는 반드시 닫기

잘못된 예:

```jsx
<input>
<br>
```

올바른 예 (Self-Closing):

```jsx
<input />
<br />
<Hello />
```

### 8-4. 여러 요소는 하나로 감싸기

잘못된 예 (형제만 나열):

```jsx
return (
  <Hello />
  <div>안녕히계세요</div>
)
```

올바른 예:

```jsx
return (
  <div>
    <Hello />
    <div>안녕히계세요</div>
  </div>
)
```

불필요한 `div`가 싫으면 **Fragment** (`<>...</>`):

```jsx
return (
  <>
    <Hello />
    <div>안녕히계세요</div>
  </>
)
```

Fragment는 화면에 별도 태그를 만들지 않습니다.

### 8-5. style · className

인라인 style은 **객체**, CSS 속성은 **camelCase**:

```jsx
const style = {
  backgroundColor: 'black',
  color: 'aqua',
  fontSize: 24,      // 기본 단위 px
  padding: '1rem',   // 다른 단위는 문자열
}

return <div style={style}>{name}</div>
```

CSS 클래스:

```css
/* App.css */
.gray-box {
  background: gray;
  width: 64px;
  height: 64px;
}
```

```jsx
import './App.css'

return <div className="gray-box"></div>
```

### 8-6. 주석

```jsx
{/* JSX 안 주석 — 화면에 안 보임 */}

<Hello
  // 열리는 태그 안에서는 // 주석 가능
/>
```

`/* ... */` 만 쓰면 **화면에 글자로 보일 수** 있으니 주의합니다.

---

## 9. 나의 첫 리액트 컴포넌트 (Hello.jsx)

컴포넌트 = **재사용 가능한 UI 조각**입니다.

### 9-1. Hello.jsx 만들기

`src/Hello.jsx` 파일 생성:

```jsx
function Hello() {
  return <div>안녕하세요</div>
}

export default Hello
```

> Vite + 최신 React에서는 파일마다 `import React from 'react'` 가 **필수는 아닙니다**.  
> (예전 CRA 교재에는 항상 적혀 있었습니다.)

- **함수형 컴포넌트**로 작성 (수업 기본)
- `export default Hello` → 다른 파일에서 import 가능

### 9-2. App.jsx에서 사용

```jsx
import Hello from './Hello.jsx'

function App() {
  return (
    <div>
      <Hello />
    </div>
  )
}

export default App
```

불필요한 `logo.svg` / 기본 CSS import는 실습 때 지워도 됩니다.

### 9-3. 재사용

```jsx
import Hello from './Hello.jsx'

function App() {
  return (
    <div>
      <Hello />
      <Hello />
      <Hello />
    </div>
  )
}

export default App
```

화면에 **안녕하세요**가 세 번 보입니다.

### 9-4. 렌더링 흐름 (정리)

```
index.html  →  <div id="root">
main.jsx    →  createRoot(...).render(<App />)
App.jsx     →  <Hello /> 등을 조합
Hello.jsx   →  실제 UI 조각
```

---

## 10. props로 컴포넌트에게 값 전달하기

**props** = properties 줄임말. 부모 → 자식으로 값을 전달합니다.

### 10-1. 기본 사용

**App.jsx**

```jsx
import Hello from './Hello.jsx'

function App() {
  return <Hello name="react" />
}

export default App
```

**Hello.jsx**

```jsx
function Hello(props) {
  return <div>안녕하세요 {props.name}</div>
}

export default Hello
```

### 10-2. 여러 props · 비구조화 할당

**App.jsx**

```jsx
import Hello from './Hello.jsx'

function App() {
  return <Hello name="react" color="red" />
}

export default App
```

**Hello.jsx** (권장)

```jsx
function Hello({ color, name }) {
  return (
    <div style={{ color }}>
      안녕하세요 {name}
    </div>
  )
}

export default Hello
```

### 10-3. 기본값 — 요즘 방식 (기본 매개변수)

예전에는 `Hello.defaultProps = { ... }` 를 많이 썼지만,  
**함수형 컴포넌트에서는 기본 매개변수**를 권장합니다.

```jsx
function Hello({ color, name = '이름없음' }) {
  return (
    <div style={{ color }}>
      안녕하세요 {name}
    </div>
  )
}

export default Hello
```

**App.jsx**

```jsx
import Hello from './Hello.jsx'

function App() {
  return (
    <>
      <Hello name="react" color="red" />
      <Hello color="pink" />
    </>
  )
}

export default App
```

결과:

```
안녕하세요 react
안녕하세요 이름없음
```

| 방식 | 비고 |
|------|------|
| `name = '이름없음'` | **현재 권장** |
| `Hello.defaultProps` | 예전 방식 (함수형에서는 비권장으로 바뀌는 중) |

---

## 11. props.children

태그 **사이**에 넣은 내용을 받으려면 `children`을 사용합니다.

### 11-1. Wrapper.jsx

```jsx
function Wrapper({ children }) {
  const style = {
    border: '2px solid black',
    padding: '16px',
  }

  return <div style={style}>{children}</div>
}

export default Wrapper
```

`children`을 렌더링하지 않으면 안쪽 내용이 화면에 안 보입니다.

### 11-2. App.jsx

```jsx
import Hello from './Hello.jsx'
import Wrapper from './Wrapper.jsx'

function App() {
  return (
    <Wrapper>
      <Hello name="react" color="red" />
      <Hello color="pink" />
    </Wrapper>
  )
}

export default App
```

---

## 12. 조건부 렌더링

조건에 따라 다른 UI를 보여 줍니다.

### 12-1. props 전달

```jsx
<Hello name="react" color="red" isSpecial={true} />
```

`true`는 JS 값이므로 `{true}` 형태입니다.

이름만 쓰면 `={true}` 와 같습니다.

```jsx
<Hello name="react" color="red" isSpecial />
```

### 12-2. 삼항 연산자

보여줄 내용이 **다를 때**:

```jsx
function Hello({ color, name = '이름없음', isSpecial }) {
  return (
    <div style={{ color }}>
      {isSpecial ? <b>*</b> : null}
      안녕하세요 {name}
    </div>
  )
}

export default Hello
```

JSX에서 `null` / `false` / `undefined` 는 화면에 안 나옵니다.

### 12-3. && 연산자 (더 간편)

**있을 때만** 보여줄 때:

```jsx
function Hello({ color, name = '이름없음', isSpecial }) {
  return (
    <div style={{ color }}>
      {isSpecial && <b>*</b>}
      안녕하세요 {name}
    </div>
  )
}

export default Hello
```

### 12-4. 완성 예 (App.jsx)

```jsx
import Hello from './Hello.jsx'
import Wrapper from './Wrapper.jsx'

function App() {
  return (
    <Wrapper>
      <Hello name="react" color="red" isSpecial />
      <Hello color="pink" />
    </Wrapper>
  )
}

export default App
```

---

## 13. 오늘 실습 체크리스트

### 환경

- [ ] `node -v` / `npm -v` 확인
- [ ] Vite로 `my-react-app` 생성
- [ ] `npm install` → `npm run dev`
- [ ] localhost:5173 접속 성공

### 코드 (모두 `.jsx`)

- [ ] `App.jsx`에 Hello React 출력
- [ ] `Hello.jsx` 만들고 App에서 불러오기
- [ ] props `name`, `color` 전달
- [ ] 기본 매개변수로 기본값 설정
- [ ] `Wrapper.jsx` + `children`
- [ ] `isSpecial` 조건부 `*` 표시
- [ ] `className`, 인라인 `style` 사용
- [ ] Fragment `<>` 사용

### 예전 방식과 구분

| 예전 (CRA 등) | 지금 (이 교안) |
|---------------|----------------|
| `App.js` / `Hello.js` | **`App.jsx` / `Hello.jsx`** |
| `ReactDOM.render` | **`createRoot().render`** |
| 매 파일 `import React` | Vite에선 **생략 가능** |
| `defaultProps` | **기본 매개변수 `name = '...'`** |
| `create-react-app` | **`npm create vite@latest`** |

---

## 강사 설명 포인트

1. React = UI용 **라이브러리**, 화면은 **컴포넌트**로 쪼갠다.  
2. 실행 흐름: `index.html` → `main.jsx` → `App.jsx` → 자식들.  
3. JSX는 HTML 같지만 **`className`**, **닫는 태그**, **하나의 루트(또는 Fragment)**.  
4. props는 **위에서 아래로** 전달.  
5. 기본값은 **`defaultProps`보다 기본 매개변수**.  

**다음 수업 예고:** `useState`(상태), 이벤트 처리  
→ [react-5차시-State-useState.md](./react-5차시-State-useState.md) · [react-6차시-이벤트처리.md](./react-6차시-이벤트처리.md)
