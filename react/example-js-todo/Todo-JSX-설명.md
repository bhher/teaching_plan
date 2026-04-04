# Todo 앱 (JavaScript · JSX) 설명

이 문서는 `example-js-todo` 프로젝트의 **React + JSX** Todo 앱이 어떻게 동작하는지, `App.jsx`를 중심으로 정리한 것입니다. TypeScript 버전과 **동작은 같고 타입만 없습니다.**

---

## 1. 개요

- **할 일(Todo)** 은 `{ id, text, done }` 형태의 **JavaScript 객체**로 표현합니다.
- **`useState`** 로 전체 목록·수정 중 항목·필터 상태를 관리합니다.
- **`useMemo`** 로 필터링된 목록(`visible`)과 통계(`active`, `completed`)를 계산 비용을 줄이며 유지합니다.
- **CRUD**: 추가(Create), 읽기(목록 표시), 수정(Update), 삭제(Delete), 완료 토글까지 모두 `App.jsx`의 핸들러에서 `setTodos`로 처리합니다.

---

## 2. 폴더 구조

```
example-js-todo/
├── src/
│   ├── App.jsx                 ← 상태·핸들러·레이아웃의 중심
│   ├── main.jsx
│   ├── index.css
│   ├── components/
│   │   ├── TodoPageHeader.jsx  ← 제목·설명 (default export)
│   │   ├── TodoForm.jsx        ← 추가 / 수정 폼
│   │   ├── TodoList.jsx        ← 체크·수정·삭제 UI
│   │   ├── TodoFilterBar.jsx   ← 전체 / 할 일 / 완료 필터
│   │   └── TodoStats.jsx       ← 남은 일·완료 개수 요약
│   └── lib/
│       └── todoQueries.js      ← filterTodos, countByDone (순수 함수)
└── Todo-JSX-설명.md            ← 이 파일
```

---

## 3. Todo 데이터 모델

각 항목은 다음과 같은 **객체**입니다.

| 필드   | 타입    | 의미        |
|--------|---------|-------------|
| `id`   | number  | 고유 식별자 |
| `text` | string  | 할 일 내용  |
| `done` | boolean | 완료 여부   |

`App.jsx` 초기값 예:

```js
{ id: 1, text: 'JavaScript 객체로 Todo 모델', done: true }
```

새 항목의 `id`는 `nextId(list)`로 기존 목록의 최대 `id + 1`을 씁니다.

---

## 4. `App.jsx` 흐름

### 4.1 상태 (`useState`)

| state      | 초기값 / 역할 |
|------------|----------------|
| `todos`    | 할 일 객체 배열 |
| `editing`  | 수정 중인 항목 전체 객체 또는 `null` |
| `filter`   | `'all'` \| `'active'` \| `'completed'` |

### 4.2 파생 데이터 (`useMemo`)

- **`visible`**: `filterTodos(todos, filter)` 결과. 목록 영역에 넘기는 **화면에 보이는** 항목만 담습니다.
- **`active`, `completed`**: `countByDone(todos)`로 미완료·완료 개수. 필터 바·통계에 사용합니다.

### 4.3 핸들러 (CRUD + 편집)

| 함수            | 하는 일 |
|-----------------|--------|
| `handleCreate`  | 새 `{ id, text, done: false }` 를 배열 끝에 추가 |
| `handleUpdate`  | 같은 `id`의 항목을 `updated`로 교체 후 `editing` 초기화 |
| `handleToggle`  | 해당 `id`의 `done`을 반전 |
| `handleDelete`  | 해당 `id` 제거. 지우는 항목이 수정 중이면 `editing`도 `null` |
| `handleEdit`    | `todos`에서 `id`로 찾아 `editing`에 설정 → 폼이 수정 모드로 전환 |

### 4.4 JSX 레이아웃

1. **`TodoPageHeader`**: 제목과 `<code>` 등으로 학습 포인트 안내 (`children`).
2. **패널 1 — `TodoForm`**: `editing` 유무에 따라 “추가” / “수정 저장” 모드. `onCreate`, `onUpdate`, `onCancelEdit` 연결.
3. **`TodoStats`**: 전체 `todos` 기준 요약 문구.
4. **패널 2 — `TodoFilterBar` + `TodoList`**: 필터 버튼으로 `filter` 변경 → `visible`이 바뀌고 `TodoList`에 전달.

---

## 5. 컴포넌트별 역할 (JSX)

### `TodoPageHeader.jsx` (default export)

- `title`, `children`만 받아 `<header>`에 렌더링합니다.
- 페이지 상단 설명 문구를 넣기 좋은 **프레젠테이션 컴포넌트**입니다.

### `TodoForm.jsx`

- 내부 `text` state와 `editing`을 `useEffect`로 동기화합니다 (`editing`이 바뀌면 입력창 내용 갱신).
- 제출 시 `trim()` 후 비어 있으면 무시.
- 수정 중이면 `onUpdate`, 아니면 `onCreate` 후 입력 비움.

### `TodoList.jsx`

- `todos`가 비면 “표시할 항목이 없습니다.”
- 각 행: 체크박스(`onToggle`), 수정(`onEdit`), 삭제(`onDelete`).

### `TodoFilterBar.jsx`

- `FILTERS` 배열로 버튼 생성. 선택된 값은 `filter === value`일 때 `active` 클래스.
- “할 일”“완료” 옆에 `activeCount`, `completedCount` 표시.

### `TodoStats.jsx`

- `countByDone(todos)`를 다시 호출해 “남은 할 일 / 완료” 한 줄로 표시합니다.

---

## 5-1. 컴포넌트 파일 코드 포함

아래 코드는 `src/components` 폴더에 있는 JSX 컴포넌트들의 내용입니다.

### `src/components/TodoPageHeader.jsx`

```jsx
/**
 * example-ts-todo에는 없는 컴포넌트 — 제목/설명만 props로 받아 표시합니다.
 */
export default function TodoPageHeader({ title, children }) {
  return (
    <header className="header">
      <h1>{title}</h1>
      {children ? <p className="lead">{children}</p> : null}
    </header>
  );
}
```

### `src/components/TodoForm.jsx`

```jsx
import { useEffect, useState } from 'react';

export function TodoForm({ editing, onCreate, onUpdate, onCancelEdit }) {
  const [text, setText] = useState('');

  useEffect(() => {
    setText(editing ? editing.text : '');
  }, [editing]);

  const handleSubmit = (e) => {
    e.preventDefault();
    const trimmed = text.trim();
    if (!trimmed) return;

    if (editing) {
      onUpdate({ ...editing, text: trimmed });
    } else {
      onCreate(trimmed);
      setText('');
    }
  };

  return (
    <form className="todo-form" onSubmit={handleSubmit}>
      <input
        type="text"
        placeholder={editing ? '할 일 수정…' : '할 일을 입력하세요'}
        value={text}
        onChange={(e) => setText(e.target.value)}
        autoComplete="off"
      />
      <button type="submit">{editing ? '수정 저장' : '추가'}</button>
      {editing ? (
        <button type="button" className="ghost" onClick={onCancelEdit}>
          취소
        </button>
      ) : null}
    </form>
  );
}
```

### `src/components/TodoList.jsx`

```jsx
export function TodoList({ todos, onToggle, onEdit, onDelete }) {
  if (todos.length === 0) {
    return <p className="muted">표시할 항목이 없습니다.</p>;
  }

  return (
    <ul className="todo-list">
      {todos.map((todo) => (
        <li key={todo.id} className={todo.done ? 'item done' : 'item'}>
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

### `src/components/TodoFilterBar.jsx`

```jsx
const FILTERS = [
  { value: 'all', label: '전체' },
  { value: 'active', label: '할 일' },
  { value: 'completed', label: '완료' },
];

export function TodoFilterBar({ filter, onChange, activeCount, completedCount }) {
  return (
    <div className="filter-bar">
      {FILTERS.map(({ value, label }) => (
        <button
          key={value}
          type="button"
          className={filter === value ? 'filter active' : 'filter'}
          onClick={() => onChange(value)}
        >
          {label}
          {value === 'active' ? ` (${activeCount})` : null}
          {value === 'completed' ? ` (${completedCount})` : null}
        </button>
      ))}
    </div>
  );
}
```

### `src/components/TodoStats.jsx`

```jsx
import { countByDone } from '../lib/todoQueries';

export function TodoStats({ todos }) {
  const { active, completed } = countByDone(todos);
  return (
    <p className="stats">
      남은 할 일 <strong>{active}</strong>개 · 완료 <strong>{completed}</strong>개
    </p>
  );
}
```

---

## 6. `lib/todoQueries.js` (순수 함수)

React에 의존하지 않아 **테스트·재사용**하기 쉽습니다.

- **`filterTodos(todos, filter)`**  
  - `'active'`: `!done`  
  - `'completed'`: `done`  
  - 그 외(기본 `'all'`): 전체 배열 반환

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

## 8. `App.jsx` 전체 코드 (참고)

```jsx
import { useMemo, useState } from 'react';
import { TodoFilterBar } from './components/TodoFilterBar';
import { TodoForm } from './components/TodoForm';
import { TodoList } from './components/TodoList';
import { TodoStats } from './components/TodoStats';
import TodoPageHeader from './components/TodoPageHeader';
import { countByDone, filterTodos } from './lib/todoQueries';

function nextId(list) {
  if (list.length === 0) return 1;
  return Math.max(...list.map((t) => t.id)) + 1;
}

export default function App() {
  const [todos, setTodos] = useState([
    { id: 1, text: 'JavaScript 객체로 Todo 모델', done: true },
    { id: 2, text: 'Todo CRUD 완성하기', done: false },
    { id: 3, text: 'filterTodos 따라가 보기', done: false },
  ]);
  const [editing, setEditing] = useState(null);
  const [filter, setFilter] = useState('all');

  const visible = useMemo(() => filterTodos(todos, filter), [todos, filter]);

  const handleCreate = (text) => {
    setTodos((prev) => [...prev, { id: nextId(prev), text, done: false }]);
  };

  const handleUpdate = (updated) => {
    setTodos((prev) => prev.map((t) => (t.id === updated.id ? updated : t)));
    setEditing(null);
  };

  const handleToggle = (id) => {
    setTodos((prev) => prev.map((t) => (t.id === id ? { ...t, done: !t.done } : t)));
  };

  const handleDelete = (id) => {
    setTodos((prev) => prev.filter((t) => t.id !== id));
    setEditing((e) => (e?.id === id ? null : e));
  };

  const handleEdit = (id) => {
    const found = todos.find((t) => t.id === id);
    setEditing(found ?? null);
  };

  const { active, completed } = useMemo(() => countByDone(todos), [todos]);

  return (
    <div className="app">
      <TodoPageHeader title="Todo · JavaScript">
        <code>객체</code> 모델 · <code>useState</code> · <code>filterTodos</code> /{' '}
        <code>countByDone</code> · TS 버전과 동작은 같고 타입만 없습니다. 추가 컴포넌트:{' '}
        <code>TodoPageHeader.jsx</code>
      </TodoPageHeader>

      <main className="main">
        <section className="panel">
          <h2>{editing ? '할 일 수정' : '할 일 추가'}</h2>
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

프로젝트 루트(`example-js-todo`)에서:

```bash
npm install
npm run dev
```

브라우저에서 Vite가 안내하는 주소로 접속하면 Todo 앱을 확인할 수 있습니다.

---

## 10. 학습 포인트 체크리스트

- [ ] Todo 한 줄이 **객체**로 표현되는 이유 (`id`로 식별)
- [ ] **상향 데이터 흐름**: 자식은 콜백만 호출하고, 실제 배열 변경은 `App`의 `setTodos`
- [ ] **`visible`은 state가 아니라 `todos`+`filter`에서 계산** (`useMemo`)
- [ ] `filterTodos` / `countByDone`을 컴포넌트 밖으로 뺀 이유 (순수 함수)

이 문서는 `App.jsx`와 `src/components`, `src/lib/todoQueries.js`를 기준으로 작성되었습니다.
