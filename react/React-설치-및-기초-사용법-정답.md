# React 설치 및 기초 사용법 — 정답

문제: [React-설치-및-기초-사용법-문제.md](./React-설치-및-기초-사용법-문제.md)  
교안: [React-설치-및-기초-사용법-교안.md](./React-설치-및-기초-사용법-교안.md)

---

# Part 1

## 1번
② UI를 만들기 위한 JavaScript 라이브러리이다

## 2번
① 화면을 컴포넌트 단위로 나눠 개발한다

## 3번
② Node.js (npm)

## 4번
② `node -v` / `npm -v`

## 5번
② Vite

## 6번

```bash
npm create vite@latest my-react-app -- --template react
```

## 7번
② `npm install` → `npm run dev`

## 8번
② `http://localhost:5173`

---

# Part 2

## 9번
② `main.jsx`

## 10번
② React 컴포넌트가 이 안에 렌더링된다

## 11번
② `createRoot(...).render(<App />)`

## 12번

```jsx
function App() {
  return (
    <h1>Hello React!</h1>
  )
}

export default App
```

## 13번
`index.html` → **`main.jsx`** → **`App.jsx`** → 자식 컴포넌트

---

# Part 3

## 14번
② `className="box"`

## 15번
② `{}`

## 16번
③ `<input>` (닫지 않음) — Self-Closing 필요: `<input />`

## 17번
② 하나의 부모 또는 Fragment로 감싼다

## 18번

```jsx
const style = {
  color: 'red',
  fontSize: 24,
}
```

## 19번
② `{/* 주석 */}`

## 20번

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

---

# Part 4

## 21번
① `export default` 후 `import`

## 22번

```jsx
function Hello() {
  return <div>안녕하세요</div>
}

export default Hello
```

## 23번

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

## 24번
② 부모 → 자식으로 값을 전달한다

## 25번

```jsx
// Hello.jsx
function Hello({ color, name }) {
  return (
    <div style={{ color }}>
      안녕하세요 {name}
    </div>
  )
}

export default Hello
```

```jsx
// App.jsx
import Hello from './Hello.jsx'

function App() {
  return <Hello name="react" color="blue" />
}

export default App
```

## 26번
② 기본 매개변수

## 27번

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

## 28번
② 태그 사이에 넣은 내용을 받아 렌더링

## 29번

```jsx
// Wrapper.jsx
function Wrapper({ children }) {
  const style = {
    border: '2px solid black',
    padding: '16px',
  }
  return <div style={style}>{children}</div>
}

export default Wrapper
```

```jsx
// App.jsx
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

# Part 5

## 30번
② `isSpecial && <b>*</b>`

## 31번
② `isSpecial={true}`

## 32번 (완성 예시)

```jsx
// Hello.jsx
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

```jsx
// Wrapper.jsx
function Wrapper({ children }) {
  const style = {
    border: '2px solid black',
    padding: '16px',
  }
  return <div style={style}>{children}</div>
}

export default Wrapper
```

```jsx
// App.jsx
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

예상 화면:

```
┌─────────────────────┐
│ *안녕하세요 react   │  (빨간색)
│ 안녕하세요 이름없음 │  (분홍색)
└─────────────────────┘
```

---

# 강사 채점 팁

| 문항 | 포인트 |
|------|--------|
| 5~8 | Vite / npm install / run dev / 5173 |
| 11 | createRoot (구 ReactDOM.render와 구분) |
| 14~17 | className, {}, Self-Closing, Fragment |
| 26~27 | **기본 매개변수** (defaultProps 감점 가능) |
| 32 | 파일 `.jsx`, children, isSpecial 동작 |

실행 확인: `npm run dev` 후 `*` 와 `이름없음`이 보이는지 확인하면 만점 처리하기 쉽습니다.
