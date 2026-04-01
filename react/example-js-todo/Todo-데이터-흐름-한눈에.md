# Todo 앱 — 전체 구조·데이터 흐름 한눈에

`example-js-todo` 기준으로, **원본 상태 → 가공 → UI** 흐름을 정리한 문서입니다.

## 전체 구조 (한 줄)

```
todos 상태 (원본 데이터)
   ↓
filterTodos (보여줄 목록만 필터링)
   ↓
countByDone (전체 기준 개수 집계)
   ↓
UI 렌더링
```

- **목록**에는 `filterTodos` 결과만 넘깁니다 (`visible`).
- **통계**는 항상 **전체 `todos`** 기준으로 `countByDone` 합니다 (필터와 무관).

---

## 1. 상태 (State)

```js
const [todos, setTodos] = useState([...]);
const [filter, setFilter] = useState('all');
const [editing, setEditing] = useState(null);
```

| 상태 | 역할 |
|------|------|
| `todos` | 전체 할 일 목록 (원본·단일 소스) |
| `filter` | 현재 필터 (`'all'` / `'active'` / `'completed'`) |
| `editing` | 수정 중인 todo 객체 (`null`이면 추가 모드) |

---

## 2. 핵심 함수 — `filterTodos` (화면용 데이터)

```js
filterTodos(todos, filter)
```

**역할:** “지금 탭에 맞게 **보여줄 목록만** 골라준다.”

**내부 로직** (`lib/todoQueries.js`):

```js
switch (filter) {
  case 'active':      // 미완료만
  case 'completed':   // 완료만
  default:            // 전체
}
```

**앱에서의 사용:**

```js
const visible = useMemo(() => filterTodos(todos, filter), [todos, filter]);
```

- `visible`이 곧 **리스트에 그릴 데이터**입니다.
- `useMemo`는 `todos`·`filter`가 바뀔 때만 다시 계산해 불필요한 재계산을 줄입니다.

---

## 3. 핵심 함수 — `countByDone` (통계)

```js
countByDone(todos)
```

**역할:** “**전체 배열**을 한 번 돌며 active / completed 개수를 집계한다.”

**핵심:** `todos.reduce(...)` 로 `{ active, completed }` 누적.

**결과 예:**

```js
{ active: 2, completed: 1 }
```

**앱에서의 사용:**

- `App.jsx`에서 `TodoFilterBar`에 넘기는 `activeCount` / `completedCount`용으로 `useMemo(() => countByDone(todos), [todos])` 사용.
- `TodoStats.jsx`에서도 동일하게 `countByDone(todos)`로 표시 (컴포넌트 안에서 직접 호출).

👉 통계는 **필터링된 목록이 아니라 원본 `todos`** 기준이어야 “남은 할 일 / 완료” 의미가 맞습니다.

---

## 4. `handleEdit` — 수정 시작

```js
const handleEdit = (id) => {
  const found = todos.find((t) => t.id === id);
  setEditing(found ?? null);
};
```

**역할:** 수정할 한 건을 찾아 `editing`에 넣는다.

**흐름:**

1. 목록에서 수정 버튼 클릭 → `handleEdit(id)`
2. `todos`에서 해당 항목 `find`
3. `setEditing` → 폼이 “수정 모드”로 전환되고 입력값이 채워짐 (`TodoForm`의 `editing` prop)

---

## 5. `handleDelete` — 삭제

```js
const handleDelete = (id) => {
  setTodos((prev) => prev.filter((t) => t.id !== id));
  setEditing((e) => (e?.id === id ? null : e));
};
```

**역할:** 해당 id의 todo를 목록에서 제거한다.

**핵심 포인트**

- `filter`로 **삭제** (새 배열).
- `e?.id === id ? null : e` → **지금 편집 중인 항목을 삭제했으면** `editing`도 `null`로 초기화.  
  (다른 항목을 편집 중이면 `editing` 유지)

---

## 6. 전체 데이터 흐름

```
[사용자 행동]
   ↓
추가 / 삭제 / 수정 / 토글 / 필터 변경
   ↓
setTodos / setEditing / setFilter 실행
   ↓
상태 변경
   ↓
React가 리렌더링
   ↓
filterTodos → visible (목록)
countByDone → active, completed (통계·필터 바)
   ↓
화면 업데이트
```

---

## 7. UI 연결 예시 (실제 코드와 대응)

```js
const visible = useMemo(() => filterTodos(todos, filter), [todos, filter]);
const { active, completed } = useMemo(() => countByDone(todos), [todos]);
```

```jsx
<TodoStats todos={todos} />

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
```

- 리스트: **`visible`** (`filterTodos` 결과)
- 숫자 표시: **`countByDone(todos)`** (원본 기준)

---

## 8. 이 구조의 핵심 철학

| 원칙 | 이 앱에서 |
|------|-----------|
| 데이터는 한 곳에만 | `todos`가 원본 |
| 가공해서 사용 | `filterTodos` → 화면용, `countByDone` → 통계용 |
| 상태 변경은 setter로만 | `setTodos` / `setEditing` / `setFilter` |
| UI는 결과만 그린다 | `visible`, 통계 값을 props로 내려서 표시 |

이렇게 나누면 **표시 로직(`filterTodos`, `countByDone`)** 을 컴포넌트 밖·순수 함수로 두기 쉽고, 테스트와 재사용에 유리합니다. React에서 자주 쓰는 패턴입니다.

---

## 관련 파일

| 파일 | 내용 |
|------|------|
| `src/App.jsx` | 상태, 핸들러, `visible` / 통계, 하위 컴포넌트 조합 |
| `src/lib/todoQueries.js` | `filterTodos`, `countByDone` |
| `TODO-앱-구조-한눈에.md` | 컴포넌트·폴더 구조 등 넓은 개요 |
