# example-js-todo — 전체 구조 한눈에 보기

이 앱은 크게 **6개 컴포넌트**와 **`lib/todoQueries`** 유틸로 구성됩니다.

```
App (메인 · 상태 보유)
 ├─ TodoPageHeader     (상단 제목·설명)
 ├─ TodoForm           (할 일 추가 / 수정)
 ├─ TodoStats          (개수 표시)
 ├─ TodoFilterBar      (필터 버튼)
 └─ TodoList           (목록 출력)

lib/todoQueries.js     (filterTodos, countByDone)
```

---

## 1. TodoPageHeader (헤더)

```jsx
export default function TodoPageHeader({ title, children })
```

| 항목 | 내용 |
|------|------|
| **역할** | 페이지 상단 제목 + 설명 출력 |
| **핵심** | `{children ? <p className="lead">{children}</p> : null}` — `children`이 있을 때만 설명 출력 |

**사용 예**

```jsx
<TodoPageHeader title="Todo · JavaScript">
  설명 내용 (코드 태그 등 포함 가능)
</TodoPageHeader>
```

---

## 2. TodoFilterBar (필터 버튼)

```js
const FILTERS = [
  { value: 'all', label: '전체' },
  { value: 'active', label: '할 일' },
  { value: 'completed', label: '완료' },
];
```

| 항목 | 내용 |
|------|------|
| **역할** | 전체 / 할 일 / 완료 필터 버튼 |
| **렌더** | `FILTERS.map(...)` 으로 버튼 반복 생성 |
| **클릭** | `onClick={() => onChange(value)}` → 부모(`App`)의 `filter` 상태 변경 |
| **카운트** | `activeCount`, `completedCount` 를 “할 일”, “완료” 버튼 옆에 표시 |

---

## 3. TodoForm (입력·수정)

`export function TodoForm({ editing, onCreate, onUpdate, onCancelEdit })`

| 항목 | 내용 |
|------|------|
| **역할** | 할 일 추가 또는 수정 |
| **로컬 상태** | `const [text, setText] = useState('')` |
| **수정 모드 동기화** | `useEffect(() => { setText(editing ? editing.text : ''); }, [editing]);` — `editing`이 바뀌면 입력창에 기존 텍스트 반영 |
| **제출** | `editing`이 있으면 `onUpdate(...)`, 없으면 `onCreate(trimmed)` 후 입력 비우기 |

---

## 4. TodoList (목록)

`export function TodoList({ todos, onToggle, onEdit, onDelete })`

| 항목 | 내용 |
|------|------|
| **역할** | 할 일 목록 출력 (`todos`가 비면 안내 문구) |
| **리스트** | `todos.map((todo) => ...)` |
| **체크박스** | `onChange={() => onToggle(todo.id)}` — 완료 여부 토글 |
| **버튼** | `onEdit(todo.id)`, `onDelete(todo.id)` — 부모로 이벤트 전달 |

---

## 5. TodoStats (개수)

```js
const { active, completed } = countByDone(todos);
```

| 항목 | 내용 |
|------|------|
| **역할** | “남은 할 일 N개 · 완료 M개” 형태로 표시 |
| **데이터** | `lib/todoQueries.js`의 `countByDone(todos)` 사용 |

---

## 6. App (핵심 컨트롤러)

가장 많은 **상태**와 **핸들러**를 가집니다.

### 상태

| 상태 | 역할 |
|------|------|
| `todos` | 전체 할 일 목록 |
| `editing` | 수정 중인 항목 (`null`이면 추가 모드) |
| `filter` | `'all' \| 'active' \| 'completed'` |

### 필터 적용

```js
const visible = useMemo(() => filterTodos(todos, filter), [todos, filter]);
```

`filter`에 따라 화면에 보여 줄 목록만 `visible`로 계산합니다.

### CRUD 요약

| 동작 | 방식 |
|------|------|
| 추가 | `setTodos((prev) => [...prev, { id, text, done: false }])` |
| 수정 | `map`으로 해당 `id`의 `text` 갱신 |
| 완료 토글 | `map`으로 해당 항목의 `done` 반전 |
| 삭제 | `filter`로 해당 `id` 제거 |
| 수정 대상 선택 | `todos.find((t) => t.id === id)` 후 `setEditing(found)` |

### 개수 (필터 바·통계용)

```js
const { active, completed } = useMemo(() => countByDone(todos), [todos]);
```

---

## 7. lib/todoQueries.js

| 함수 | 역할 |
|------|------|
| `filterTodos(todos, filter)` | 필터에 맞는 항목만 배열로 반환 |
| `countByDone(todos)` | `reduce`로 미완료·완료 개수 집계 |

---

## 데이터 흐름 (핵심)

```
App (state 보유)
    ↓ props
자식 컴포넌트
    ↓ 이벤트 (onCreate, onToggle …)
App의 핸들러
    ↓ setTodos / setFilter / setEditing
state 변경
    ↓
리렌더 → UI 자동 반영
```

---

## 이 코드에서 익히는 개념

| 개념 | 예시 위치 |
|------|-----------|
| `useState` | `App`, `TodoForm` |
| `useEffect` | `TodoForm` — `editing`과 입력창 동기화 |
| `useMemo` | `App` — `visible`, `active`/`completed` |
| props | 부모 → 자식 데이터·콜백 전달 |
| `map` | `TodoList`, `TodoFilterBar` |
| CRUD | `App`의 `setTodos` 패턴 |

---

*프로젝트 루트: `react/example-js-todo`*
