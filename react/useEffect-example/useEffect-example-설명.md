# useEffect-example — React · CSS 설명

**폴더:** `react/useEffect-example`  
**관련:** [React-useEffect-정리.md](../React-useEffect-정리.md) · [useEffect-scroll-simple](../useEffect-scroll-simple/)

```bash
cd useEffect-example
npm install
npm run dev
```

반드시 **F12 → Console** 을 열고 탭을 바꿔 가며 확인합니다.

---

## 1. 프로젝트 구조

```
useEffect-example/
├── index.html
├── package.json
├── vite.config.js
└── src/
    ├── main.jsx          ← React 진입
    ├── App.jsx           ← 탭 UI + 예제 전환
    ├── index.css         ← 전역 스타일
    ├── Example1.jsx      ← 마운트 1회 []
    ├── Example2.jsx      ← [count] + document.title
    ├── Example3.jsx      ← Cleanup (보이기/숨기기)
    ├── Example4.jsx      ← 마운트 + count + Cleanup
    ├── Ex01.jsx          ← [] 비교 (버튼으로 증명)
    ├── Ex02.jsx          ← [count] 비교
    └── Ex03.jsx          ← 의존성 배열 없음
```

| 구분 | 파일 | 목적 |
|------|------|------|
| 기존 | Example1~4 | 기본 실습 |
| 추가 | Ex01~03 | 의존성 배열 **3가지 비교** |

---

## 2. 진입점 — main.jsx

```jsx
import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import App from './App'
import './index.css'   // ← CSS 여기서 한 번 불러옴

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <App />
  </StrictMode>
)
```

- `index.css`를 import 해야 `.tab`, `.btn` 등이 적용됩니다.
- `StrictMode`(개발 모드)에서는 Effect가 **두 번** 보일 수 있습니다. 수업 때 “개발 모드라서 그렇다”고 안내하면 됩니다.

---

## 3. App.jsx — 탭으로 예제 바꾸기

### 핵심 아이디어

한 화면에 예제를 다 넣지 않고, **탭 state**로 하나만 보여 줍니다.

```jsx
const [activeTab, setActiveTab] = useState(1)
```

| 값 | 보이는 컴포넌트 |
|----|-----------------|
| `1` | Example1 |
| `2` | Example2 |
| `3` | Example3 |
| `4` | Example4 |
| `'ex01'` | Ex01 |
| `'ex02'` | Ex02 |
| `'ex03'` | Ex03 |

### 탭 버튼

```jsx
<button
  className={`tab ${activeTab === tab.id ? 'active' : ''}`}
  onClick={() => setActiveTab(tab.id)}
>
  {tab.label}
</button>
```

- `` className={`tab ${조건 ? 'active' : ''}`} ``  
  → 기본 클래스 `tab` + 선택되면 `active` 추가  
- CSS에서 `.tab.active`가 초록 배경이 됩니다.

### 조건부 렌더링

```jsx
{activeTab === 1 && <Example1 />}
{activeTab === 'ex01' && <Ex01 />}
```

- `&&` : 앞이 참일 때만 뒤 JSX를 그림  
- 탭을 바꾸면 이전 예제는 **언마운트**, 새 예제는 **마운트**  
  → Example3/4 Cleanup, Ex01의 “탭 다시 선택 시 1회 실행”을 보여 주기 좋음

---

## 4. React — useEffect란?

> 컴포넌트 **렌더링이 끝난 뒤** 실행할 부수 효과(로그, title, 타이머, 이벤트 등록 등)

```jsx
useEffect(() => {
  // 실행할 코드
}, 의존성배열)
```

| useState | useEffect |
|----------|-----------|
| 데이터(상태) 관리 | 렌더 후 작업 실행 |

---

## 5. 예제1~4 설명

### Example1 — `[]` 마운트 1회

```jsx
useEffect(() => {
  console.log('환영합니다!')
}, [])
```

- 이 탭을 **처음 열 때** 콘솔 1회  
- state가 없어서 “버튼으로 재실행”은 없음  
- 다른 탭 갔다가 다시 오면 **다시 마운트** → 또 1회

### Example2 — `[count]`

```jsx
const [count, setCount] = useState(0)

useEffect(() => {
  console.log('count 변경됨 →', count)
  document.title = `카운트: ${count}`
}, [count])
```

- 처음 + count가 바뀔 때마다 Effect  
- `document.title` → 브라우저 **탭 제목** 변경 (DOM 조작 예)

### Example3 — Cleanup

```jsx
// 자식
useEffect(() => {
  console.log('마운트됨')
  return () => {
    console.log('언마운트됨 (Cleanup)')
  }
}, [])

// 부모
{show && <TimerChild />}
```

| 동작 | 결과 |
|------|------|
| 보이기 | 자식 마운트 → Effect |
| 숨기기 | 자식 언마운트 → **Cleanup** |

스크롤 예제의 `removeEventListener`와 같은 자리입니다.  
**스크롤할 때가 아니라, 컴포넌트가 사라질 때** 정리합니다.

### Example4 — 통합

```jsx
useEffect(() => { /* 마운트 로그 + Cleanup */ }, [])
useEffect(() => { document.title = ... }, [count])
```

- Effect를 **여러 개** 써도 됨 (역할별로 나누기)  
- 다른 탭으로 이동 → Example4 언마운트 → Cleanup 로그

---

## 6. Ex01 ~ Ex03 — 의존성 배열 비교 (수업 핵심)

| 파일 | 의존성 | 언제 Effect 실행 |
|------|--------|------------------|
| **Ex01** | `[]` | 처음 1회만. +1해도 **안 함** |
| **Ex02** | `[count]` | 처음 + count 변경 시 |
| **Ex03** | (없음) | **렌더마다** (count·입력 모두) |

### Ex01

```jsx
useEffect(() => {
  console.log('[] → 처음 한 번만')
}, [])
```

count용 버튼은 “화면은 바뀌지만 Effect는 안 돈다”를 **증명**하려고 둔 것입니다.

### Ex02

```jsx
useEffect(() => {
  console.log('count:', count)
}, [count])
```

한 줄: **「count가 변경되면 이 코드를 실행해라」**

### Ex03

```jsx
useEffect(() => {
  console.log('렌더마다')
}) // 두 번째 인자 없음
```

- count 버튼 · input 타이핑 **둘 다** 로그가 쌓임  
- 초보가 배열을 빼먹으면 API가 계속 호출될 수 있음 → **주의**

```text
① []     → 처음 1회
② [값]   → 그 값이 바뀔 때
③ 생략   → 매 렌더 (위험할 수 있음)
```

---

## 7. CSS 설명 (`index.css`)

`main.jsx`에서 `import './index.css'` → 전역 적용.

### 리셋 · 페이지 배경

```css
* { margin: 0; padding: 0; box-sizing: border-box; }

body {
  font-family: 'Malgun Gothic', sans-serif;
  background: #f5f5f5;   /* 연한 회색 배경 */
  padding: 24px;
  min-height: 100vh;
}
```

| 속성 | 역할 |
|------|------|
| `box-sizing: border-box` | padding 포함해 너비 계산 (레이아웃 안정) |
| `min-height: 100vh` | 화면 높이만큼 최소 확보 |

### 가운데 정렬 컨테이너

```css
.app {
  max-width: 600px;
  margin: 0 auto;   /* 좌우 자동 → 가운데 */
}
```

### 탭 버튼

```css
.tabs {
  display: flex;
  gap: 8px;
  flex-wrap: wrap;   /* 좁으면 다음 줄 */
}

.tab {
  padding: 10px 18px;
  border: 1px solid #ddd;
  background: white;
  border-radius: 8px;
  cursor: pointer;
}

.tab:hover {
  background: #f0f0f0;
}

.tab.active {
  background: #4CAF50;   /* 선택된 탭 */
  color: white;
  border-color: #4CAF50;
}
```

React의 `` className={`tab ${active ? 'active' : ''}`} `` 와 짝입니다.

### 예제 카드 영역

```css
.example-box {
  background: white;
  padding: 24px;
  border-radius: 12px;
  box-shadow: 0 2px 10px rgba(0,0,0,0.08);
}
```

흰 카드 + 약한 그림자로 본문 영역을 구분합니다.

### 공통 버튼 · 숫자

```css
.btn {
  padding: 10px 20px;
  border: none;
  border-radius: 8px;
  background: #4CAF50;
  color: white;
  cursor: pointer;
}

.btn:hover {
  background: #45a049;
}

.count-display {
  font-size: 2em;
  font-weight: bold;
  color: #4CAF50;
  margin: 16px 0;
}
```

### 안내 박스 · 코드 블록

```css
.console-hint {
  margin-top: 16px;
  padding: 12px;
  background: #f9f9f9;
  border-radius: 8px;
  font-size: 12px;
  color: #888;
}

.code-block {
  background: #1e1e1e;   /* 에디터 느낌 */
  color: #d4d4d4;
  padding: 12px 14px;
  border-radius: 8px;
  font-size: 13px;
  overflow-x: auto;      /* 긴 코드 가로 스크롤 */
}

.example-box code {
  background: #eef7ee;
  padding: 2px 6px;
  border-radius: 4px;
}
```

- `.console-hint` → “F12 확인” 같은 안내  
- `.code-block` → Ex01~03에 보이는 검은 코드 박스  
- `code` → 본문 속 짧은 코드 강조

### 인라인 style vs CSS 파일

| 방식 | 예 | 언제 |
|------|-----|------|
| `index.css` + `className` | `.btn`, `.tab` | 공통·재사용 |
| `style={{ ... }}` | Example3 초록 박스, Ex03 input | 그 예제만 살짝 |

수업에서는 **공통은 CSS 파일, 예제 전용은 인라인도 OK** 라고 하면 됩니다.

---

## 8. className 연결표

| JSX | CSS |
|-----|-----|
| `className="app"` | `.app` |
| `className="tabs"` | `.tabs` |
| `className="tab"` / `"tab active"` | `.tab` / `.tab.active` |
| `className="example-box"` | `.example-box` |
| `className="btn"` | `.btn` |
| `className="count-display"` | `.count-display` |
| `className="console-hint"` | `.console-hint` |
| `className="code-block"` | `.code-block` |

HTML은 `class`, React는 **`className`** 입니다.

---

## 9. 수업 진행 추천 순서

1. **예제1** — `[]` 한 번  
2. **예제2** — `[count]` + 탭 제목  
3. **Ex01 → Ex02 → Ex03** — 의존성 세 가지 비교 (가장 중요)  
4. **예제3** — Cleanup = 언마운트 때  
5. **예제4** — Effect 여러 개  
6. (심화) [useEffect-scroll-simple](../useEffect-scroll-simple/) — scroll + Cleanup

---

## 10. 학생에게 강조할 문장

1. **useEffect는 “렌더 후 작업”**이지, 버튼 onClick이 아니다.  
2. **`[]`는 리스너/초기 작업을 한 번 등록**할 때.  
3. **`[count]`는 count가 바뀔 때 다시 실행.**  
4. **배열 생략은 매 렌더 → 초보는 거의 쓰지 말 것.**  
5. **Cleanup은 스크롤마다가 아니라, 컴포넌트가 사라질 때(또는 Effect 재실행 전).**  
6. **탭의 `active` 클래스는 CSS로 선택된 버튼을 표시.**

---

## 11. 한눈에

```text
App (activeTab)
  ├─ tabs 버튼  →  className tab / tab active  (CSS)
  └─ example-box
        ├─ Example1~4
        └─ Ex01 [] | Ex02 [count] | Ex03 없음
              └─ 콘솔(F12)로 Effect 실행 횟수 확인
```
