# useCallback - 함수 재사용하기

## 📌 useCallback 한 줄 요약

> **"함수 참조를 캐시해서, 의존성이 바뀔 때만 새 함수 생성"**

---

## 🎯 왜 사용하나?

```jsx
// 매 렌더링마다 새 함수 생성
function Parent() {
  const [count, setCount] = useState(0);

  const handleClick = () => {
    console.log('클릭');
  };  // count 변경 시마다 새 함수!

  return <Child onClick={handleClick} />;  // Child가 React.memo여도 매번 리렌더
}
```

- `handleClick`은 **렌더링마다 새로 생성**됨
- `Child`가 `React.memo`로 감싸져 있어도, `onClick` prop이 매번 새 참조 → **리렌더 발생**
- `useCallback`으로 감싸면 → **의존성 변경 시에만** 새 함수 생성

---

## 📖 기본 문법

```jsx
import { useCallback } from 'react';

const memoizedFn = useCallback(() => {
  // 함수 본문
}, [의존성1, 의존성2]);
```

| 인자 | 설명 |
|------|------|
| **함수** | 메모이제이션할 함수 |
| **의존성 배열** | 이 값들이 바뀔 때만 새 함수 생성 |

---

## useCallback vs useMemo

```jsx
// useCallback: 함수를 메모이제이션
const fn = useCallback(() => doSomething(a), [a]);

// useMemo로 함수 반환 = useCallback과 동일
const fn = useMemo(() => () => doSomething(a), [a]);
```

| Hook | 반환값 | 용도 |
|------|--------|------|
| **useCallback** | 함수 | 이벤트 핸들러, 자식에 전달하는 함수 |
| **useMemo** | 값 | 계산 결과, 객체/배열 |

---

## 예제 1. 이벤트 핸들러 고정

```jsx
import { useState, useCallback } from 'react';

function Counter() {
  const [count, setCount] = useState(0);

  const handleIncrease = useCallback(() => {
    setCount(c => c + 1);
  }, []);  // 의존성 없음 → 항상 같은 함수

  const handleDecrease = useCallback(() => {
    setCount(c => c - 1);
  }, []);

  return (
    <div>
      <p>{count}</p>
      <button onClick={handleIncrease}>+1</button>
      <button onClick={handleDecrease}>-1</button>
    </div>
  );
}
```

**설명:** `setCount(c => c + 1)`처럼 함수형 업데이트를 쓰면 의존성 배열을 `[]`로 둘 수 있음.

---

## 예제 2. 의존성이 있는 함수

```jsx
function SearchBox() {
  const [keyword, setKeyword] = useState('');
  const [results, setResults] = useState([]);

  const handleSearch = useCallback(() => {
    fetch(`/api/search?q=${keyword}`)
      .then(res => res.json())
      .then(setResults);
  }, [keyword]);  // keyword가 바뀔 때만 새 함수

  return (
    <div>
      <input value={keyword} onChange={e => setKeyword(e.target.value)} />
      <button onClick={handleSearch}>검색</button>
    </div>
  );
}
```

---

## 예제 3. 자식 컴포넌트에 함수 전달 (React.memo와 함께)

```jsx
const Child = React.memo(({ onClick }) => {
  console.log('Child 렌더');
  return <button onClick={onClick}>클릭</button>;
});

function Parent() {
  const [count, setCount] = useState(0);

  const handleClick = useCallback(() => {
    setCount(c => c + 1);
  }, []);

  return (
    <div>
      <p>count: {count}</p>
      <Child onClick={handleClick} />
    </div>
  );
}
```

**설명:**
- `useCallback` 없으면 → Parent 리렌더 시 `handleClick` 새로 생성 → Child도 리렌더
- `useCallback` 사용 → `handleClick` 참조 유지 → Child는 리렌더 안 함 (React.memo)

---

## ⚠️ 자주 하는 실수

### 1. 의존성 누락

```jsx
const [count, setCount] = useState(0);
const handleClick = useCallback(() => {
  setCount(count + 1);  // count를 사용하는데
}, []);  // []에 없음 → stale closure! 항상 count=0
```

**해결:** `setCount(c => c + 1)` 함수형 업데이트 사용

### 2. 모든 함수에 useCallback

```jsx
const handleClick = useCallback(() => {
  console.log('hi');  // 가벼운 작업
}, []);  // 불필요한 최적화
```

**권장:** 자식에 전달하거나, 의존성 배열이 복잡할 때만 사용

---

## 📋 체크리스트

- [ ] 자식 컴포넌트에 함수를 prop으로 전달할 때
- [ ] 의존성 배열에 실제 사용하는 값만 넣기
- [ ] setState는 함수형 업데이트로 의존성 최소화
