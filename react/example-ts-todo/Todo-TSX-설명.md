# Todo 앱 (TypeScript · TSX) 설명

이 문서는 `example-ts-todo` 프로젝트의 **React + TypeScript(TSX)** Todo 앱이 어떻게 동작하는지, `App.tsx`를 중심으로 정리한 것입니다. `example-js-todo`와 **동작은 같고**, `Todo`·필터·Props에 **타입이 붙어** 컴파일 시점에 실수를 잡을 수 있습니다.

---

## 1. 개요

- **할 일(Todo)** 은 `types/todo.ts`의 **`Todo` 인터페이스**(`id`, `text`, `done`)로 표현합니다.
- **`useState<Todo[]>`**, **`useState<Todo | null>`**, **`useState<TodoFilter>`** 로 목록·수정 중 항목·필터를 관리합니다.
- **`useMemo`** 로 필터링된 목록(`visible`)과 통계(`active`, `completed`)를 계산합니다.
- **CRUD**는 `App.tsx`의 핸들러에서 `setTodos`로 처리합니다.
- **`filterTodos` / `countByDone`** 은 `src/lib/todoQueries.ts`의 **타입이 붙은 순수 함수**입니다.

---

## 2. 폴더 구조

```
example-ts-todo/
├── src/
│   ├── App.tsx                 ← 상태·핸들러·레이아웃·헤더의 중심
│   ├── main.tsx
│   ├── index.css
│   ├── vite-env.d.ts
│   ├── types/
│   │   └── todo.ts             ← Todo, TodoFilter
│   ├── components/
│   │   ├── TodoForm.tsx
│   │   ├── TodoList.tsx
│   │   ├── TodoFilterBar.tsx
│   │   └── TodoStats.tsx
│   └── lib/
│       └── todoQueries.ts      ← filterTodos, countByDone
├── Todo-TSX-설명.md            ← 이 파일
└── vite.config.ts
```

**JS 버전과의 차이**: TS 예제는 상단 헤더를 **`TodoPageHeader` 컴포넌트가 아니라 `App.tsx` 안의 `<header>`** 로 직접 작성합니다. 모델과 쿼리는 `types/`, `lib/`로 분리되어 있습니다.

---

## 3. Todo 데이터 모델 (`types/todo.ts`)

### `Todo` 인터페이스

| 필드   | 타입    | 의미        |
|--------|---------|-------------|
| `id`   | number  | 고유 식별자 |
| `text` | string  | 할 일 내용  |
| `done` | boolean | 완료 여부   |

### `TodoFilter` 타입

`"all" | "active" | "completed"` **문자열 유니온**으로, 필터에 넣을 수 있는 값만 제한합니다.

`App.tsx` 초기값 예:

```ts
{ id: 1, text: "TypeScript interface 연습", done: true }
```

새 항목의 `id`는 `nextId(list: Todo[]): number`로 기존 목록의 최대 `id + 1`을 씁니다.

---

## 4. `App.tsx` 흐름

### 4.1 상태 (`useState`)

| state      | 타입 / 초기값 / 역할 |
|------------|----------------------|
| `todos`    | `Todo[]` — 전체 목록 |
| `editing`  | `Todo \| null` — 수정 중인 항목 또는 없음 |
| `filter`   | `TodoFilter` — `"all"` \| `"active"` \| `"completed"` |

### 4.2 파생 데이터 (`useMemo`)

- **`visible`**: `filterTodos(todos, filter)` 결과. 목록에 넘기는 **화면에 보이는** 항목만 담습니다.
- **`active`, `completed`**: `countByDone(todos)`로 미완료·완료 개수. 필터 바에 사용합니다.

### 4.3 핸들러 (CRUD + 편집)

| 함수            | 하는 일 |
|-----------------|--------|
| `handleCreate`  | 새 `{ id, text, done: false }` 를 배열 끝에 추가 |
| `handleUpdate`  | 같은 `id`의 항목을 `updated`로 교체 후 `editing` 초기화 |
| `handleToggle`  | 해당 `id`의 `done`을 반전 |
| `handleDelete`  | 해당 `id` 제거. 지우는 항목이 수정 중이면 `editing`도 `null` |
| `handleEdit`    | `todos`에서 `id`로 찾아 `editing`에 설정 |

### 4.4 TSX 레이아웃

1. **`<header className="header">`**: 제목과 학습 포인트 안내 (`example-js-todo`의 `TodoPageHeader` 역할을 인라인으로 처리).
2. **패널 1 — `TodoForm`**: `editing` 유무에 따라 추가 / 수정 저장. `onCreate`, `onUpdate`, `onCancelEdit` 연결.
3. **`TodoStats`**: 전체 `todos` 기준 요약.
4. **패널 2 — `TodoFilterBar` + `TodoList`**: 필터 변경 → `visible` 갱신 후 `TodoList`에 전달.

---

## 5. 컴포넌트별 역할 (TSX)

각 컴포넌트는 **`XXXProps` 인터페이스**로 props 타입을 명시하고, 값 타입은 `import type { Todo }` 등으로 가져옵니다.

### `TodoForm.tsx`

- `TodoFormProps`: `editing`, `onCreate`, `onUpdate`, `onCancelEdit`.
- 내부 `text` state와 `editing`을 `useEffect`로 동기화합니다.
- 제출 시 `FormEvent`로 `preventDefault`, `trim()` 후 비어 있으면 무시.
- 수정 중이면 `onUpdate`, 아니면 `onCreate` 후 입력 비움.

### `TodoList.tsx`

- `TodoListProps`: `todos`, `onToggle`, `onEdit`, `onDelete`.
- `todos`가 비면 “표시할 항목이 없습니다.”
- 각 행: 체크박스, 수정, 삭제 버튼.

### `TodoFilterBar.tsx`

- `TodoFilterBarProps`: `filter`, `onChange`, `activeCount`, `completedCount`.
- `FILTERS` 배열의 `value` 타입이 `TodoFilter`와 맞습니다.

### `TodoStats.tsx`

- `TodoStatsProps`: `todos`.
- `countByDone(todos)`로 “남은 할 일 / 완료” 한 줄 표시 (`App`의 `useMemo`와 별도로 한 번 더 계산).

---

## 5-1. 타입·라이브러리·컴포넌트 코드 포함

### `src/types/todo.ts`

```ts
/** 할 일 한 건 */
export interface Todo {
  id: number;
  text: string;
  done: boolean;
}

/** 목록 필터 (타입 유니온으로 허용 값만 제한) */
export type TodoFilter = "all" | "active" | "completed";
```

### `src/lib/todoQueries.ts`

```ts
import type { Todo, TodoFilter } from "../types/todo";

/** 필터에 맞는 항목만 반환 */
export function filterTodos(todos: Todo[], filter: TodoFilter): Todo[] {
  switch (filter) {
    case "active":
      return todos.filter((t) => !t.done);
    case "completed":
      return todos.filter((t) => t.done);
    default:
      return todos;
  }
}

/** 완료 / 미완료 개수 */
export function countByDone(todos: Todo[]): { active: number; completed: number } {
  return todos.reduce(
    (acc, t) => {
      if (t.done) acc.completed += 1;
      else acc.active += 1;
      return acc;
    },
    { active: 0, completed: 0 }
  );
}
```

### `src/components/TodoForm.tsx`

```tsx
import { useEffect, useState, type FormEvent } from "react";
import type { Todo } from "../types/todo";

export interface TodoFormProps {
  editing: Todo | null;
  onCreate: (text: string) => void;
  onUpdate: (todo: Todo) => void;
  onCancelEdit: () => void;
}

export function TodoForm({ editing, onCreate, onUpdate, onCancelEdit }: TodoFormProps) {
  const [text, setText] = useState("");

  useEffect(() => {
    setText(editing ? editing.text : "");
  }, [editing]);

  const handleSubmit = (e: FormEvent) => {
    e.preventDefault();
    const trimmed = text.trim();
    if (!trimmed) return;

    if (editing) {
      onUpdate({ ...editing, text: trimmed });
    } else {
      onCreate(trimmed);
      setText("");
    }
  };

  return (
    <form className="todo-form" onSubmit={handleSubmit}>
      <input
        type="text"
        placeholder={editing ? "할 일 수정…" : "할 일을 입력하세요"}
        value={text}
        onChange={(e) => setText(e.target.value)}
        autoComplete="off"
      />
      <button type="submit">{editing ? "수정 저장" : "추가"}</button>
      {editing ? (
        <button type="button" className="ghost" onClick={onCancelEdit}>
          취소
        </button>
      ) : null}
    </form>
  );
}
```

### `src/components/TodoList.tsx`

```tsx
import type { Todo } from "../types/todo";

export interface TodoListProps {
  todos: Todo[];
  onToggle: (id: number) => void;
  onEdit: (id: number) => void;
  onDelete: (id: number) => void;
}

export function TodoList({ todos, onToggle, onEdit, onDelete }: TodoListProps) {
  if (todos.length === 0) {
    return <p className="muted">표시할 항목이 없습니다.</p>;
  }

  return (
    <ul className="todo-list">
      {todos.map((todo) => (
        <li key={todo.id} className={todo.done ? "item done" : "item"}>
          <label className="todo-label">
            <input type="checkbox" checked={todo.done} onChange={() => onToggle(todo.id)} />
            <span className="todo-text">{todo.text}</span>
          </label>
          <div className="item-actions">
            <button type="button" className="small" onClick={() => onEdit(todo.id)}>
              수정
            </button>
            <button type="button" className="small danger" onClick={() => onDelete(todo.id)}>
              삭제
            </button>
          </div>
        </li>
      ))}
    </ul>
  );
}
```

### `src/components/TodoFilterBar.tsx`

```tsx
import type { TodoFilter } from "../types/todo";

export interface TodoFilterBarProps {
  filter: TodoFilter;
  onChange: (filter: TodoFilter) => void;
  activeCount: number;
  completedCount: number;
}

const FILTERS: { value: TodoFilter; label: string }[] = [
  { value: "all", label: "전체" },
  { value: "active", label: "할 일" },
  { value: "completed", label: "완료" },
];

export function TodoFilterBar({ filter, onChange, activeCount, completedCount }: TodoFilterBarProps) {
  return (
    <div className="filter-bar">
      {FILTERS.map(({ value, label }) => (
        <button
          key={value}
          type="button"
          className={filter === value ? "filter active" : "filter"}
          onClick={() => onChange(value)}
        >
          {label}
          {value === "active" ? ` (${activeCount})` : null}
          {value === "completed" ? ` (${completedCount})` : null}
        </button>
      ))}
    </div>
  );
}
```

### `src/components/TodoStats.tsx`

```tsx
import { countByDone } from "../lib/todoQueries";
import type { Todo } from "../types/todo";

export interface TodoStatsProps {
  todos: Todo[];
}

export function TodoStats({ todos }: TodoStatsProps) {
  const { active, completed } = countByDone(todos);
  return (
    <p className="stats">
      남은 할 일 <strong>{active}</strong>개 · 완료 <strong>{completed}</strong>개
    </p>
  );
}
```

---

## 6. `lib/todoQueries.ts` 요약

React에 의존하지 않아 **테스트·재사용**하기 쉽습니다. 인자·반환값에 `Todo`, `TodoFilter`가 붙어 **호출부와 구현이 맞는지** 에디터와 `tsc`가 도와줍니다.

- **`filterTodos(todos, filter)`**  
  - `"active"`: `!done`  
  - `"completed"`: `done`  
  - 그 외(기본 `"all"`): 전체 배열 반환

- **`countByDone(todos)`**  
  - `{ active, completed }` 누적

---

## 7. 데이터 흐름 (한 줄 요약)

```
사용자 입력/클릭
  → App 핸들러 → setTodos / setFilter / setEditing
  → todos·filter 변경
  → useMemo로 visible·통계 갱신
  → TodoList·TodoFilterBar·TodoStats에 props로 전달되어 화면 반영
```

---

## 8. `App.tsx` 전체 코드 (참고)

```tsx
import { useMemo, useState } from "react";
import { TodoFilterBar } from "./components/TodoFilterBar";
import { TodoForm } from "./components/TodoForm";
import { TodoList } from "./components/TodoList";
import { TodoStats } from "./components/TodoStats";
import { countByDone, filterTodos } from "./lib/todoQueries";
import type { Todo, TodoFilter } from "./types/todo";

function nextId(list: Todo[]): number {
  if (list.length === 0) return 1;
  return Math.max(...list.map((t) => t.id)) + 1;
}

export default function App() {
  const [todos, setTodos] = useState<Todo[]>([
    { id: 1, text: "TypeScript interface 연습", done: true },
    { id: 2, text: "Todo CRUD 완성하기", done: false },
    { id: 3, text: "filterTodos 타입 따라가 보기", done: false },
  ]);
  const [editing, setEditing] = useState<Todo | null>(null);
  const [filter, setFilter] = useState<TodoFilter>("all");

  const visible = useMemo(() => filterTodos(todos, filter), [todos, filter]);

  const handleCreate = (text: string) => {
    setTodos((prev) => [...prev, { id: nextId(prev), text, done: false }]);
  };

  const handleUpdate = (updated: Todo) => {
    setTodos((prev) => prev.map((t) => (t.id === updated.id ? updated : t)));
    setEditing(null);
  };

  const handleToggle = (id: number) => {
    setTodos((prev) =>
      prev.map((t) => (t.id === id ? { ...t, done: !t.done } : t))
    );
  };

  const handleDelete = (id: number) => {
    setTodos((prev) => prev.filter((t) => t.id !== id));
    setEditing((e) => (e?.id === id ? null : e));
  };

  const handleEdit = (id: number) => {
    const found = todos.find((t) => t.id === id);
    setEditing(found ?? null);
  };

  const { active, completed } = useMemo(() => countByDone(todos), [todos]);

  return (
    <div className="app">
      <header className="header">
        <h1>Todo · TypeScript</h1>
        <p className="lead">
          <code>Todo</code> 모델 · <code>useState&lt;Todo[]&gt;</code> ·{" "}
          <code>filterTodos</code> / <code>countByDone</code> · 컴포넌트 Props로 CRUD를 연습합니다.
        </p>
      </header>

      <main className="main">
        <section className="panel">
          <h2>{editing ? "할 일 수정" : "할 일 추가"}</h2>
          <TodoForm
            editing={editing}
            onCreate={handleCreate}
            onUpdate={handleUpdate}
            onCancelEdit={() => setEditing(null)}
          />
        </section>

        <TodoStats todos={todos} />

        <section className="panel">
          <h2>목록</h2>
          <TodoFilterBar
            filter={filter}
            onChange={setFilter}
            activeCount={active}
            completedCount={completed}
          />
          <TodoList
            todos={visible}
            onToggle={handleToggle}
            onEdit={handleEdit}
            onDelete={handleDelete}
          />
        </section>
      </main>
    </div>
  );
}
```

---

## 9. 실행 방법

프로젝트 루트(`example-ts-todo`)에서:

```bash
npm install
npm run dev
```

브라우저에서 Vite가 안내하는 주소로 접속하면 Todo 앱을 확인할 수 있습니다.

---

## 10. 학습 포인트 체크리스트

- [ ] `Todo` **인터페이스**와 `TodoFilter` **유니온**이 모델·필터 실수를 줄이는 이유
- [ ] **`import type`** 과 값 import 구분 (`types`는 런타임에 사라짐)
- [ ] **`useState` 제네릭** (`useState<Todo[]>`, `useState<TodoFilter>`)
- [ ] 컴포넌트 **`Props` 인터페이스**로 콜백 시그니처 명시
- [ ] **상향 데이터 흐름**: 자식은 콜백만 호출, 배열 변경은 `App`의 `setTodos`
- [ ] **`visible`은 state가 아니라 `todos`+`filter`에서 계산** (`useMemo`)
- [ ] `filterTodos` / `countByDone`을 컴포넌트 밖으로 뺀 이유 (순수 함수 + 타입 재사용)

이 문서는 `App.tsx`, `src/types/todo.ts`, `src/components`, `src/lib/todoQueries.ts`를 기준으로 작성되었습니다.
