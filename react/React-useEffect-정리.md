# React useEffect 정리

> 컴포넌트가 **렌더링된 후** 특정 작업을 실행하는 Hook

**예제 폴더:** [useEffect-example](./useEffect-example/)  
**예제 설명(React·CSS):** [useEffect-example/useEffect-example-설명.md](./useEffect-example/useEffect-example-설명.md)  
실행: `cd useEffect-example` → `npm install` → `npm run dev` → **F12 콘솔** 확인

---

## 한 문장

> `useEffect(() => { ... }, 의존성)`  
> = **렌더링이 끝난 뒤** 이 코드를 실행해라.  
> 언제 다시 실행할지는 **의존성 배열**이 정한다.

---

## 어디에 쓰나

| 용도 | 예 |
|------|-----|
| 화면이 **처음** 나타날 때 | 환영 로그, API 목록 조회 |
| **State가 바뀔 때** | count 변경 로그, document.title |
| API 호출 | fetch / axios |
| 타이머 | setInterval |
| 이벤트 등록/해제 | addEventListener + cleanup |
| 외부 라이브러리 | 차트, 맵 연결 |

---

## 1. 기본 문법

```jsx
import { useEffect } from 'react'

useEffect(() => {
  // 실행할 코드
}, [])
```

```text
useEffect( 실행할 함수 , 의존성 배열 )
```

---

## 2. 의존성 배열 3가지 (수업 핵심)

| | 코드 | 언제 실행 |
|--|------|-----------|
| ① | `useEffect(() => {}, [])` | **처음 한 번만** |
| ② | `useEffect(() => {}, [count])` | 처음 + **count 변경 시** |
| ③ | `useEffect(() => {})` 배열 없음 | **렌더링될 때마다** |

예제 폴더 탭:

- **예제1~4** — 기존 실습 (마운트 / 값 변경 / Cleanup / 통합)
- **Ex01** → `[]` 처음 한 번만  
- **Ex02** → `[count]` count 변경 시  
- **Ex03** → 의존성 배열 **없음** (매 렌더)

---

### ① `[]` — 처음 한 번만

```jsx
useEffect(() => {
  console.log('실행!')
}, [])
```

```text
App 실행 → 렌더링 → useEffect 실행 → "실행!"
count 변경 → 렌더링 → Effect 다시 안 함
```

### ② `[count]` — count가 바뀔 때

```jsx
useEffect(() => {
  console.log('count:', count)
}, [count])
```

```text
처음 → Effect (count=0)
+1 → count=1 → 렌더링 → Effect
+1 → count=2 → 렌더링 → Effect
```

한 줄: **「count가 변경되면 이 코드를 실행해라」**

### ③ 의존성 배열 없음 — 매 렌더

```jsx
useEffect(() => {
  console.log('실행')
})
```

```text
렌더링 → Effect
State 변경 → 렌더링 → Effect
다른 State 변경 → 렌더링 → Effect
```

초보자가 배열을 빼먹으면 Effect가 **계속** 돌 수 있습니다.

---

## 3. useState vs useEffect

| | useState | useEffect |
|--|----------|-----------|
| 역할 | **상태(데이터) 관리** | 렌더링 후 **부수 효과** |
| 예 | `const [count, setCount] = useState(0)` | API, 로그, 타이틀, 타이머 |

```text
useState  → 상태 관리
useEffect → 상태 변화·렌더링에 따라 작업 실행
```

---

## 4. Todo와 연결

```jsx
const [todo, setTodo] = useState(mockTodo)

useEffect(() => {
  console.log('Todo 변경:', todo)
}, [todo])
```

```text
Todo 추가 → setTodo → todo 변경 → 렌더링 → useEffect
```

---

## 5. API 호출 (실무에서 자주)

```jsx
useEffect(() => {
  fetch('https://jsonplaceholder.typicode.com/users')
    .then((res) => res.json())
    .then((data) => console.log(data))
}, [])
```

```text
컴포넌트 실행 → 렌더링 → useEffect → fetch → 서버 → 데이터
```

게시판·상품·영화 목록 조회 시 자연스럽게 이어집니다.

---

## 6. 수업용 최소 예제

```jsx
import { useState, useEffect } from 'react'

export default function App() {
  const [count, setCount] = useState(0)

  useEffect(() => {
    console.log('count가 변경됨:', count)
  }, [count])

  return (
    <div>
      <h1>카운트 : {count}</h1>
      <button onClick={() => setCount(count + 1)}>증가</button>
    </div>
  )
}
```

다음 단계: **API → Todo 목록 → Spring Boot 연결**

---

## 7. 체크리스트

- [ ] `[]` = 마운트 1회  
- [ ] `[값]` = 그 값이 바뀔 때  
- [ ] 배열 생략 = 매 렌더 (주의)  
- [ ] useState ≠ useEffect  
- [ ] API는 보통 `[]` 또는 `[id]` 등과 함께
