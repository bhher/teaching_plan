# useEffect-example 예제 전체 설명

**폴더:** `react/useEffect-example`  
**CSS·구조:** [useEffect-example-설명.md](./useEffect-example-설명.md)  
**개념:** [../React-useEffect-정리.md](../React-useEffect-정리.md)

```bash
cd useEffect-example
npm install
npm run dev
```

**F12 → Console** 필수

---

## 목차

1. [예제1 — `[]` 마운트 1회](#예제1)
2. [예제2 — `[count]`](#예제2)
3. [예제3 — Cleanup (보이기/숨기기)](#예제3)
4. [예제4 — 통합](#예제4)
5. [Ex01 — `[]` 비교](#ex01)
6. [Ex02 — `[count]` 비교](#ex02)
7. [Ex03 — 의존성 배열 없음](#ex03)
8. [ExTimer — setInterval + Cleanup](#extimer) ← 타이머 예제
9. [Cleanup이 도는 시점](#cleanup-시점)
10. [한눈에 비교표](#비교표)

---

<a id="예제1"></a>
## 예제1 — `Example1.jsx` (`[]`)

```jsx
useEffect(() => {
  console.log('✅ [예제1] 환영합니다! (마운트 시 1회만 실행)')
}, [])
```

| 항목 | 내용 |
|------|------|
| 의존성 | `[]` |
| 실행 | 이 탭을 **처음 열 때(마운트) 1회** |
| 확인 | 콘솔에 “환영합니다!” |

다른 탭으로 갔다가 다시 예제1을 고르면 **다시 마운트**되어 Effect가 또 1회 실행됩니다.

---

<a id="예제2"></a>
## 예제2 — `Example2.jsx` (`[count]`)

```jsx
const [count, setCount] = useState(0)

useEffect(() => {
  console.log('예제2: count 변경됨 →', count)
  document.title = `카운트: ${count}`
}, [count])
```

| 항목 | 내용 |
|------|------|
| 의존성 | `[count]` |
| 실행 | 처음 + **count가 바뀔 때마다** |
| 화면 | `+1` 버튼, 큰 숫자 |
| DOM | `document.title` → 브라우저 탭 제목 변경 |

**한 줄:** count가 변경되면 이 코드를 실행해라.

---

<a id="예제3"></a>
## 예제3 — `Example3.jsx` (Cleanup)

```jsx
function TimerChild() {
  useEffect(() => {
    console.log('✅ [예제3] 타이머 컴포넌트 마운트됨')
    return () => {
      console.log('🧹 [예제3] 타이머 컴포넌트 언마운트됨 (Cleanup)')
    }
  }, [])

  return <div style={{ ... }}>타이머 컴포넌트</div>
}

function Example3() {
  const [show, setShow] = useState(true)
  return (
    <>
      <button onClick={() => setShow(!show)}>
        {show ? '숨기기' : '보이기'}
      </button>
      {show && <TimerChild />}
    </>
  )
}
```

| 동작 | 결과 |
|------|------|
| 보이기 | 자식 마운트 → `✅` 로그 |
| 숨기기 | 자식 언마운트 → `🧹` Cleanup |

예제3는 Cleanup **개념**만 보여 줍니다.  
실제 `setInterval` 정리는 아래 **ExTimer**에서 합니다.

---

<a id="예제4"></a>
## 예제4 — `Example4.jsx` (통합)

```jsx
useEffect(() => {
  console.log('✅ [예제4] 컴포넌트 마운트됨')
  return () => console.log('🧹 [예제4] 컴포넌트 언마운트됨')
}, [])

useEffect(() => {
  document.title = `클릭 ${count}회`
}, [count])
```

- Effect를 **역할별로 여러 개** 사용
- `[]` → 마운트/언마운트 로그
- `[count]` → 탭 제목
- 다른 탭으로 이동 → 예제4 언마운트 → Cleanup

---

<a id="ex01"></a>
## Ex01 — `Ex01.jsx` (`[]` + count 버튼으로 증명)

```jsx
const [count, setCount] = useState(0)

useEffect(() => {
  console.log('✅ [Ex01] [] → 처음 한 번만 실행')
}, [])
```

- +1을 눌러도 **화면의 count만** 바뀌고  
- **콘솔 Effect 로그는 다시 안 찍힘**  
→ `[]` = “처음 한 번만”을 눈으로 확인

---

<a id="ex02"></a>
## Ex02 — `Ex02.jsx` (`[count]`)

```jsx
useEffect(() => {
  console.log('✅ [Ex02] [count] → count 변경됨:', count)
  document.title = `Ex02 카운트: ${count}`
}, [count])
```

- Ex01과 달리 +1마다 Effect 실행  
- 탭 제목도 함께 변경

---

<a id="ex03"></a>
## Ex03 — `Ex03.jsx` (의존성 배열 없음)

```jsx
useEffect(() => {
  console.log('⚠️ [Ex03] 의존성 배열 없음 → 렌더링마다 실행', { count, text })
}) // ← 두 번째 인자 없음
```

- count 버튼 · 글자 입력 **둘 다** 로그가 쌓임  
- 초보가 배열을 빼먹으면 API가 계속 호출될 수 있음 → **비권장**

---

<a id="extimer"></a>
## ExTimer — `setInterval` + Cleanup (핵심 추가)

탭: **ExTimer: setInterval**  
파일: `ExTimer.jsx` + `Timer.jsx`

### App 쪽 (보이기/숨기기)

```jsx
import { useState } from 'react'
import Timer from './Timer'

function ExTimer() {  // 수업용 App과 동일한 역할
  const [show, setShow] = useState(true)

  return (
    <div>
      <button onClick={() => setShow(!show)}>
        {show ? '타이머 숨기기' : '타이머 보이기'}
      </button>

      {/* show가 true일 때만 Timer 생성 */}
      {show && <Timer />}
    </div>
  )
}
```

```text
show = true   →  <Timer /> 마운트
show = false  →  Timer 제거(언마운트) → Cleanup
```

### Timer.jsx

```jsx
import { useState, useEffect } from 'react'

function Timer() {
  const [count, setCount] = useState(0)

  useEffect(() => {
    console.log('✅ 타이머 시작')

    const timer = setInterval(() => {
      console.log('1초마다 실행')
      setCount((prev) => prev + 1)
    }, 1000)

    // Cleanup — 컴포넌트가 사라질 때
    return () => {
      console.log('🧹 타이머 정리')
      clearInterval(timer)
    }
  }, [])

  return (
    <div style={{ padding: 20, marginTop: 20, backgroundColor: '#e8f5e9', borderRadius: 10 }}>
      <h2>⏰ 타이머</h2>
      <p>{count}초 경과</p>
    </div>
  )
}

export default Timer
```

### 왜 Cleanup이 필수인가

| | Cleanup **있음** | Cleanup **없음** |
|--|------------------|------------------|
| 숨기기 후 | `clearInterval` → 멈춤 | 타이머가 **계속** 돌음 |
| 콘솔 | “1초마다 실행” 중단 | 로그가 계속 쌓임 |
| 다시 보이기 | 새 타이머 1개 | 타이머가 **여러 개** 겹칠 수 있음 |

### 흐름

```text
[보이기]
Timer 마운트
  → useEffect
  → setInterval 등록
  → 1초마다 setCount

[숨기기]
Timer 언마운트
  → return () => clearInterval(timer)
  → 🧹 로그
  → 더 이상 "1초마다 실행" 안 나옴
```

### `setCount((prev) => prev + 1)` 인 이유

인터벌 콜백이 오래된 `count`를 닫아걸지(closure) 않도록,  
**이전 값 `prev`를 받아** +1 하는 함수형 업데이트를 씁니다.

### 예제3 vs ExTimer

| | 예제3 | ExTimer |
|--|-------|---------|
| Cleanup | 로그만 | **`clearInterval` 실전** |
| 타이머 | 이름만 “타이머” | 진짜 1초 카운트 |

---

<a id="cleanup-시점"></a>
## Cleanup이 도는 시점 (중요)

```jsx
useEffect(() => {
  // 등록 (setInterval, addEventListener 등)

  return () => {
    // Cleanup ← 여기
  }
}, [])
```

| 상황 | Cleanup? |
|------|----------|
| 1초마다 / 스크롤할 때마다 | ❌ |
| 컴포넌트 **언마운트** (숨기기, 다른 탭) | ✅ |
| 의존성이 바뀌어 Effect를 **다시** 실행하기 직전 | ✅ |

> Cleanup ≠ “매 초 실행”  
> Cleanup = “이 Effect를 끝낼 때 정리”

---

<a id="비교표"></a>
## 한눈에 비교표

| 탭 | 파일 | 의존성 | 핵심 |
|----|------|--------|------|
| 예제1 | Example1 | `[]` | 마운트 1회 로그 |
| 예제2 | Example2 | `[count]` | count + title |
| 예제3 | Example3 | `[]` + return | Cleanup 개념 |
| 예제4 | Example4 | `[]` + `[count]` | Effect 여러 개 |
| Ex01 | Ex01 | `[]` | +1해도 Effect 안 함 |
| Ex02 | Ex02 | `[count]` | +1마다 Effect |
| Ex03 | Ex03 | 없음 | 매 렌더 (주의) |
| **ExTimer** | Timer + ExTimer | `[]` + clearInterval | **실전 Cleanup** |

### 의존성 세 가지

```text
[]        → 처음 1회
[count]   → count 변경 시
(생략)    → 매 렌더
```

---

## 수업 추천 순서

1. 예제1 → Ex01 (`[]`)  
2. 예제2 → Ex02 (`[count]`)  
3. Ex03 (배열 없음 = 위험)  
4. 예제3 (Cleanup 개념)  
5. **ExTimer** (`clearInterval` 실전) ← 가장 와닿음  
6. 예제4 (통합)  
7. (심화) [useEffect-scroll-simple](../useEffect-scroll-simple/) scroll + removeEventListener

---

## 학생에게 한 문장

> **useEffect로 타이머·이벤트를 켰으면, Cleanup으로 꺼야 한다.**  
> 끄는 시점은 “매 초”가 아니라 **컴포넌트가 사라질 때**이다.
