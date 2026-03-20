# useMemo - 연산한 값 재사용하기

## 📌 useMemo 한 줄 요약

> **"비용이 큰 연산 결과를 캐시해서, 의존성이 바뀔 때만 다시 계산"**

---

## 🎯 왜 사용하나?

```jsx
// 매 렌더링마다 실행됨 (비효율)
function App() {
  const [count, setCount] = useState(0);
  const [name, setName] = useState('');

  const expensiveResult = heavyCalculation(count);  // count와 무관한 name 변경 시에도 실행!

  return <div>...</div>;
}
```

- `name`만 바꿔도 컴포넌트가 리렌더링 → `heavyCalculation(count)`가 **매번** 실행
- `useMemo`로 감싸면 → **count가 바뀔 때만** 다시 계산

---

## 📖 기본 문법

```jsx
import { useMemo } from 'react';

const memoizedValue = useMemo(() => {
  return 계산_결과;
}, [의존성1, 의존성2]);
```

| 인자 | 설명 |
|------|------|
| **함수** | 계산을 수행하고 결과를 반환 |
| **의존성 배열** | 이 값들이 바뀔 때만 다시 계산 |

---

## 예제 1. 간단한 계산 캐시

```jsx
import { useState, useMemo } from 'react';

function Counter() {
  const [count, setCount] = useState(0);

  const doubled = useMemo(() => {
    console.log('계산 실행');
    return count * 2;
  }, [count]);

  return (
    <div>
      <p>count: {count}</p>
      <p>doubled: {doubled}</p>
      <button onClick={() => setCount(count + 1)}>+1</button>
    </div>
  );
}
```

**동작:**
- `count`가 바뀔 때만 "계산 실행" 로그 출력
- `doubled`는 count 변경 시에만 다시 계산

---

## 예제 2. 배열 필터링 (실전)

```jsx
import { useState, useMemo } from 'react';

function TodoList() {
  const [todos] = useState([
    { id: 1, text: 'React 공부', done: true },
    { id: 2, text: 'useMemo 학습', done: false },
    { id: 3, text: '프로젝트', done: false },
  ]);
  const [filter, setFilter] = useState('all');  // all | done | pending

  const filteredTodos = useMemo(() => {
    console.log('필터링 실행');
    if (filter === 'done') return todos.filter(t => t.done);
    if (filter === 'pending') return todos.filter(t => !t.done);
    return todos;
  }, [todos, filter]);

  return (
    <div>
      <button onClick={() => setFilter('all')}>전체</button>
      <button onClick={() => setFilter('done')}>완료</button>
      <button onClick={() => setFilter('pending')}>미완료</button>
      <ul>
        {filteredTodos.map(t => <li key={t.id}>{t.text}</li>)}
      </ul>
    </div>
  );
}
```

**설명:**
- `filter`나 `todos`가 바뀔 때만 필터링 실행
- 다른 state 변경으로 리렌더링되어도 `filteredTodos`는 재사용

---

## 예제 3. 비용 큰 연산 (팩토리얼)

```jsx
function factorial(n) {
  console.log('팩토리얼 계산:', n);
  if (n <= 1) return 1;
  return n * factorial(n - 1);
}

function FactorialDemo() {
  const [count, setCount] = useState(5);
  const [name, setName] = useState('');

  const result = useMemo(() => factorial(count), [count]);

  return (
    <div>
      <p>팩토리얼({count}) = {result}</p>
      <input value={name} onChange={e => setName(e.target.value)} placeholder="이름" />
      <button onClick={() => setCount(c => c + 1)}>숫자+1</button>
    </div>
  );
}
```

**설명:**
- `name` 입력 시 → 리렌더링되지만 `result`는 **재계산 안 함**
- `count` 변경 시에만 `factorial` 실행

---

## ⚠️ useMemo를 쓰지 않아도 되는 경우

| 상황 | useMemo 필요? |
|------|----------------|
| 단순 계산 (a + b) | ❌ 불필요 |
| 이미 빠른 연산 | ❌ 불필요 |
| **무거운 연산** (큰 배열 처리, 복잡한 계산) | ✅ 권장 |
| **자식 컴포넌트에 객체/배열 전달** (참조 안정성) | ✅ 고려 |

---

## useMemo vs useCallback

| Hook | 용도 |
|------|------|
| **useMemo** | **값**을 메모이제이션 (계산 결과) |
| **useCallback** | **함수**를 메모이제이션 |

```jsx
const value = useMemo(() => compute(a, b), [a, b]);
const fn = useCallback(() => doSomething(a), [a]);
```

---

## 📋 체크리스트

- [ ] 연산이 무겁다 → useMemo 고려
- [ ] 의존성 배열에 실제 사용하는 값만 넣기
- [ ] 과도한 사용은 오히려 메모리·복잡도 증가 → 필요한 곳에만

---

> **useCallback** 설명은 `useCallback_설명_및_예제.md` 참고
