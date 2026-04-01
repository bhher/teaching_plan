# TypeScript 핵심 정리 (`example-ts-todo`)

`example-ts-todo` 기준으로, JavaScript 대비 **TypeScript에서 추가로 쓰는 개념**을 한곳에 정리했습니다.

---

## 1. TypeScript에서 추가된 핵심 개념

### 가장 중요한 2가지

#### ① `interface` — 객체 타입 정의

```ts
export interface Todo {
  id: number;
  text: string;
  done: boolean;
}
```

| 포인트 | 설명 |
|--------|------|
| 의미 | `Todo` 객체는 **반드시** 위 구조(id, text, done)를 따라야 함 |
| 효과 | 구조가 틀리면 **컴파일 단계에서 에러** (실행 전에 발견) |

#### ② `type` — 값의 집합 제한 (유니온)

```ts
export type TodoFilter = 'all' | 'active' | 'completed';
```

| 포인트 | 설명 |
|--------|------|
| 의미 | `filter`는 **위 세 문자열만** 허용 |
| 효과 | `"al"` 같은 **오타·잘못된 값**을 컴파일 시 차단 |

---

## 2. JS vs TS 차이 (한 줄 요약)

```jsx
// ❌ JS — props 구조를 언어가 강제하지 않음
function TodoList({ todos }) { ... }
```

```tsx
// ✅ TS — props 형태를 미리 선언
export interface TodoListProps {
  todos: Todo[];
}

export function TodoList({ todos, ... }: TodoListProps) { ... }
```

→ **props(와 state)의 모양을 타입으로 고정**해 둔다는 점이 큰 차이입니다.

---

## 3. 각 컴포넌트에서 타입 적용

### 3-1. TodoFilterBar

```ts
export interface TodoFilterBarProps {
  filter: TodoFilter;
  onChange: (filter: TodoFilter) => void;
  activeCount: number;
  completedCount: number;
}
```

| 항목 | 설명 |
|------|------|
| `filter` | 반드시 `"all" \| "active" \| "completed"` |
| `onChange` | **함수 타입**까지 지정 (인자·반환 의도 명확) |

**배열 안 객체까지 타입 지정:**

```ts
const FILTERS: { value: TodoFilter; label: string }[] = [
  { value: 'all', label: '전체' },
  // ...
];
```

---

### 3-2. TodoForm

```ts
export interface TodoFormProps {
  editing: Todo | null;
  onCreate: (text: string) => void;
  onUpdate: (todo: Todo) => void;
  onCancelEdit: () => void;
}
```

| 항목 | 설명 |
|------|------|
| `editing` | `Todo` **또는** `null` |
| 콜백 | 인자 타입(`string`, `Todo`)을 명시 |

**이벤트 타입 (폼 제출 등):**

```ts
import type { FormEvent } from 'react';

const handleSubmit = (e: FormEvent) => {
  e.preventDefault();
  // ...
};
```

→ DOM/React 이벤트도 타입을 붙일 수 있습니다.

---

### 3-3. TodoList

```ts
export interface TodoListProps {
  todos: Todo[];
  onToggle: (id: number) => void;
  onEdit: (id: number) => void;
  onDelete: (id: number) => void;
}
```

→ 배열 **원소가 `Todo`**임을 명시.

---

### 3-4. TodoStats

```ts
export interface TodoStatsProps {
  todos: Todo[];
}
```

→ `todos`만 넘기면 되고, 컴포넌트 **반환 타입**은 대부분 **자동 추론**됩니다.

---

## 4. 로직 함수 (`lib/todoQueries.ts`)

### `filterTodos`

```ts
export function filterTodos(todos: Todo[], filter: TodoFilter): Todo[] {
  // ...
}
```

| 항목 | 설명 |
|------|------|
| 입력 | `Todo[]`, `TodoFilter` |
| 출력 | `Todo[]` |

### `countByDone`

```ts
export function countByDone(todos: Todo[]): {
  active: number;
  completed: number;
} {
  // ...
}
```

→ 반환 **객체의 키와 타입**이 명확해짐.

---

## 5. App — 상태와 핸들러

### 상태에 제네릭으로 타입 지정

```ts
const [todos, setTodos] = useState<Todo[]>([...]);
const [editing, setEditing] = useState<Todo | null>(null);
const [filter, setFilter] = useState<TodoFilter>('all');
```

→ **상태가 담는 값의 종류**를 `useState<...>`에 직접 적습니다.

### 핸들러 인자 타입

```ts
const handleCreate = (text: string) => { ... };
const handleUpdate = (updated: Todo) => { ... };
const handleToggle = (id: number) => { ... };
const handleDelete = (id: number) => { ... };
```

---

### `useMemo` — 반환 타입 추론

```ts
const visible = useMemo(() => filterTodos(todos, filter), [todos, filter]);
```

→ `filterTodos`의 반환 타입이 `Todo[]`이므로, `visible`도 **`Todo[]`로 추론**됩니다.

---

## 6. JS → TS로 올릴 때 요약 표

| 항목 | JavaScript | TypeScript |
|------|------------|------------|
| 데이터 구조 | 약속만 있음 | `interface` / `type` |
| 특정 값만 허용 | 런타임 검사 또는 관례 | 유니온 `type` |
| 함수 인자 | 문서·주석에 의존 | 매개변수에 타입 |
| 컴포넌트 props | 자유 | `XxxProps` interface |
| 잘못된 사용 | 대부분 **실행 중** 에러 | **`tsc` / 에디터에서 사전 차단** |

---

## 7. 핵심 한 줄

> **TypeScript는 “실행하기 전에” 타입을 검사해서, 구조·오타·잘못된 호출을 줄여 주는 안전장치다.**

---

*프로젝트: `react/example-ts-todo`*  
*JS 대응 예제: `react/example-js-todo`*
