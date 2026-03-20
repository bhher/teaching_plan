# useEffect 실전 예제 (간단 버전)

## 📌 useEffect 한 줄 요약

> **"렌더링 후에 실행되는 부가 작업"**  
> 데이터 가져오기, 타이틀 변경, 구독 설정 등

---

## 🎯 의존성 배열 3가지 패턴

| 패턴 | 문법 | 실행 시점 |
|------|------|----------|
| 매번 | `useEffect(() => {})` | 렌더링할 때마다 |
| 한 번 | `useEffect(() => {}, [])` | **마운트 시 1회** |
| 값 변경 시 | `useEffect(() => {}, [count])` | **count가 바뀔 때** |

---

## 예제 1. 마운트 시 한 번만 실행 (초기화)

**상황:** 페이지 들어왔을 때 환영 메시지 띄우기

```jsx
import { useEffect } from 'react';

function Welcome() {
  useEffect(() => {
    console.log('환영합니다!');
  }, []);  // 빈 배열 = 한 번만

  return <div>안녕하세요</div>;
}
```

**설명:**
- `[]` 빈 배열 → 컴포넌트가 **처음 나타날 때 1번만** 실행
- API 호출, 초기 데이터 로드에 사용

---

## 예제 2. 값이 바뀔 때마다 실행 (반응)

**상황:** 카운터 숫자가 바뀔 때마다 브라우저 탭 제목도 바꾸기

```jsx
import { useState, useEffect } from 'react';

function Counter() {
  const [count, setCount] = useState(0);

  useEffect(() => {
    document.title = `카운트: ${count}`;
  }, [count]);  // count가 바뀔 때마다 실행

  return (
    <div>
      <p>카운트: {count}</p>
      <button onClick={() => setCount(count + 1)}>+1</button>
    </div>
  );
}
```

**설명:**
- `[count]` → **count가 변경될 때만** 실행
- `document.title`로 브라우저 탭 제목 변경

---

## 예제 3. 정리(Cleanup) - 컴포넌트 사라질 때

**상황:** 컴포넌트가 사라질 때 "종료" 메시지 출력

```jsx
import { useEffect } from 'react';

function Timer() {
  useEffect(() => {
    console.log('타이머 시작');

    return () => {
      console.log('타이머 종료');
    };
  }, []);

  return <div>타이머</div>;
}
```

**설명:**
- `return () => { }` → **언마운트(사라질 때)** 실행되는 정리 함수
- 타이머 제거, 구독 해제 등에 사용

---

## 예제 4. 통합 예제 - 카운터 + 타이틀 + 로그

```jsx
import { useState, useEffect } from 'react';

function App() {
  const [count, setCount] = useState(0);

  // 1. 마운트 시 1회
  useEffect(() => {
    console.log('컴포넌트 마운트됨');
    return () => console.log('컴포넌트 언마운트됨');
  }, []);

  // 2. count 변경 시
  useEffect(() => {
    document.title = `클릭 ${count}회`;
  }, [count]);

  return (
    <div>
      <p>클릭 횟수: {count}</p>
      <button onClick={() => setCount(count + 1)}>클릭</button>
    </div>
  );
}
```

---

## ⚠️ 자주 하는 실수

### 1. 의존성 배열 누락 → 매번 실행

```jsx
useEffect(() => {
  console.log('실행');
});  // [] 없음 → 렌더링마다 실행!
```

### 2. 무한 루프

```jsx
const [count, setCount] = useState(0);
useEffect(() => {
  setCount(count + 1);  // count 변경 → useEffect 실행 → 또 setCount → 무한!
}, [count]);
```

### 3. cleanup 누락

```jsx
useEffect(() => {
  const id = setInterval(() => {}, 1000);
  // return () => clearInterval(id);  ← 이거 안 하면 메모리 누수!
}, []);
```

---

## 📋 체크리스트

| 사용 목적 | 의존성 배열 | 예시 |
|----------|-------------|------|
| 초기 데이터 로드 | `[]` | API 호출 |
| 특정 값에 반응 | `[값]` | 검색어, 탭 전환 |
| 정리 작업 | `return () => {}` | 타이머/구독 해제 |
