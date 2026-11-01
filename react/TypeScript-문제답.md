# TypeScript 문제 · 답

이론: [TypeScript-정리.md](./TypeScript-정리.md)  
예제: `example-ts-basics`, `jsx-tsx-compare`

---

# A. 문제

## 문제 1. 기본 타입 ⭐

다음 변수에 **올바른 타입 주석**을 붙이세요.

```ts
let title = "React";
let price = 15000;
let open = true;
let tags = ["react", "ts"];
```

---

## 문제 2. 함수 타입 ⭐

`multiply` 함수를 완성하세요.  
인자 두 개는 `number`, 반환도 `number`입니다.

```ts
function multiply(a, b) {
  return a * b;
}
```

---

## 문제 3. 유니온 ⭐⭐

`printStatus`는 `"ready" | "loading" | "error"` 만 받도록 작성하세요.  
잘못된 문자열은 타입 오류가 나야 합니다.

```ts
function printStatus(status) {
  console.log(status);
}

printStatus("ready");
// printStatus("ok");  // 이게 오류가 나야 함
```

---

## 문제 4. interface ⭐⭐

`Product` interface를 만드세요.

| 필드 | 타입 | 비고 |
|------|------|------|
| id | number | 필수 |
| name | string | 필수 |
| price | number | 필수 |
| sale | boolean | 선택(`?`) |

그 다음 `const book: Product = { ... }` 객체를 하나 작성하세요.

---

## 문제 5. Props (TSX) ⭐⭐

`Hello` 컴포넌트 props 타입을 추가하세요.

```tsx
// 요구: name은 string, age는 number
export function Hello({ name, age }) {
  return (
    <p>
      {name} ({age}세)
    </p>
  );
}
```

---

## 문제 6. useState ⭐⭐

카운터 state가 **숫자만** 되도록 고치세요.

```tsx
function Counter() {
  const [count, setCount] = useState(0);
  // setCount("1") 이 타입 오류가 나도록
  return <button onClick={() => setCount(count + 1)}>{count}</button>;
}
```

---

## 문제 7. 코드 고치기 ⭐⭐

아래 코드의 **타입 오류 원인**을 말하고 고치세요.

```tsx
interface User {
  name: string;
  age: number;
}

const user: User = {
  name: "김철수",
  age: "20",
};

function Profile({ user }: { user: User }) {
  return <p>{user.name}</p>;
}

<Profile user={{ name: "이영희" }} />;
```

---

## 문제 8. 서술 ⭐

1. `interface`와 `type`을 수업에서 어떻게 나누어 쓰면 좋은가?  
2. JSX와 TSX의 차이는?  
3. `useState<User | null>(null)` 을 쓰는 이유는?  
4. `any`를 남용하면 안 되는 이유는?

---

## 문제 9. 종합 — Todo 한 줄 ⭐⭐⭐

아래를 TypeScript로 완성하세요.

1. `Todo` interface: `id: number`, `text: string`, `done: boolean`  
2. `todos: Todo[]` 배열 초기값 1개  
3. `TodoItem` 컴포넌트 props: `todo: Todo`, `onToggle: (id: number) => void`

---

# B. 모범 답

## 답 1

```ts
let title: string = "React";
let price: number = 15000;
let open: boolean = true;
let tags: string[] = ["react", "ts"];
```

---

## 답 2

```ts
function multiply(a: number, b: number): number {
  return a * b;
}
```

---

## 답 3

```ts
type Status = "ready" | "loading" | "error";

function printStatus(status: Status): void {
  console.log(status);
}

printStatus("ready");
// printStatus("ok"); // Error
```

---

## 답 4

```ts
interface Product {
  id: number;
  name: string;
  price: number;
  sale?: boolean;
}

const book: Product = {
  id: 1,
  name: "TS 입문",
  price: 20000,
  sale: true,
};
```

---

## 답 5

```tsx
interface HelloProps {
  name: string;
  age: number;
}

export function Hello({ name, age }: HelloProps) {
  return (
    <p>
      {name} ({age}세)
    </p>
  );
}
```

---

## 답 6

```tsx
import { useState } from "react";

function Counter() {
  const [count, setCount] = useState<number>(0);
  return (
    <button type="button" onClick={() => setCount(count + 1)}>
      {count}
    </button>
  );
}
```

초기값이 `0`이면 추론만으로도 `number`인 경우가 많지만,  
수업에서는 **`useState<number>(0)`을 명시**하는 습관을 권장합니다.

---

## 답 7

**원인**

1. `age: "20"` → `number`여야 함  
2. `<Profile user={{ name: "이영희" }} />` → `age` 누락  

**수정**

```tsx
const user: User = {
  name: "김철수",
  age: 20,
};

<Profile user={{ name: "이영희", age: 22 }} />;
```

---

## 답 8

1. **객체 모양 → interface**, **문자열 선택지(유니온) → type** 으로 나누면 학생에게 설명하기 쉽습니다. (둘 다 객체에 쓸 수 있음)  
2. **TSX = JSX + 타입**. 화면은 같고 타입 검사가 추가됩니다.  
3. 처음에는 데이터가 없고(`null`), 나중에 `User`가 로드되는 패턴을 표현합니다.  
4. `any`는 타입 검사를 **끄는** 것과 같아서 TS 이점이 사라집니다.

---

## 답 9

```tsx
import { useState } from "react";

interface Todo {
  id: number;
  text: string;
  done: boolean;
}

interface TodoItemProps {
  todo: Todo;
  onToggle: (id: number) => void;
}

function TodoItem({ todo, onToggle }: TodoItemProps) {
  return (
    <label>
      <input
        type="checkbox"
        checked={todo.done}
        onChange={() => onToggle(todo.id)}
      />
      {todo.text}
    </label>
  );
}

export default function App() {
  const [todos, setTodos] = useState<Todo[]>([
    { id: 1, text: "TS 공부", done: false },
  ]);

  const onToggle = (id: number) => {
    setTodos(
      todos.map((t) => (t.id === id ? { ...t, done: !t.done } : t))
    );
  };

  return (
    <ul>
      {todos.map((todo) => (
        <li key={todo.id}>
          <TodoItem todo={todo} onToggle={onToggle} />
        </li>
      ))}
    </ul>
  );
}
```

---

## 채점 · 수업 가이드

| 문제 | 배점 가이드 | 추천 |
|------|-------------|------|
| 1~2 | 기본 문법 | 필수 |
| 3~4 | 유니온 · interface | 필수 |
| 5~6 | React props · state | 필수 |
| 7 | 디버깅 | 퀴즈 |
| 8 | 이해도 | 퀴즈 |
| 9 | 종합 | 숙제 |

**오늘 수업:** 정리 MD 1~7절 → 문제 1~6  
**숙제:** 문제 9 (+ `example-ts-basics`에서 Counter·DemoButton 코드 읽기)
