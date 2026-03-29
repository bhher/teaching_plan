# example-ts-todo — TypeScript 상세 설명

이 문서는 **`example-ts-todo`** 예제가 어떤 타입 설계로 동작하는지, CRUD·필터·컴포넌트 분리가 어떻게 연결되는지 단계별로 정리한 것입니다. 코드를 읽을 때 이 파일과 소스를 같이 보시면 됩니다.

---

## 1. 이 예제로 연습하는 것

| 연습 포인트 | 이 프로젝트에서의 위치 |
|-------------|------------------------|
| 데이터 구조를 `interface`로 고정 | `src/types/todo.ts`의 `Todo` |
| 허용 값만 쓰고 싶을 때 문자열 유니온 | `TodoFilter` |
| 목록 상태의 타입 유지 | `useState<Todo[]>` |
| 배열을 다루는 순수 함수의 입·출력 타입 | `filterTodos`, `countByDone` |
| 자식에게 넘기는 값·콜백을 Props로 계약 | 각 컴포넌트의 `*Props` |

TypeScript를 쓰는 이유 중 하나는 **“잘못된 데이터나 잘못된 Props 조합”을 작성하는 순간 편집기에서 막아 주는 것**입니다. 아래에서 각 부분이 그 역할을 어떻게 하는지 설명합니다.

---

## 2. 실행 방법

```bash
cd example-ts-todo
npm install
npm run dev
```

빌드(타입 검사 포함):

```bash
npm run build
```

---

## 3. 데이터 모델: `Todo`와 `TodoFilter`

파일: `src/types/todo.ts`

```1:9:react/example-ts-todo/src/types/todo.ts
/** 할 일 한 건 */
export interface Todo {
  id: number;
  text: string;
  done: boolean;
}

/** 목록 필터 (타입 유니온으로 허용 값만 제한) */
export type TodoFilter = "all" | "active" | "completed";
```

### `Todo`

- **`id`**: 항목을 구분하는 숫자. React의 `key`와 “어떤 줄을 수정/삭제할지”에 쓰입니다.
- **`text`**: 사용자가 보는 할 일 문장.
- **`done`**: 완료 여부. 체크박스와 필터(할 일 / 완료)의 기준이 됩니다.

앱 전체에서 “할 일 한 건”은 항상 이 세 필드를 갖는다고 가정합니다. 필드 이름을 오타 내거나 `done` 대신 `completed`처럼 다른 이름을 쓰면, 다른 파일과 맞지 않아 타입 에러가 납니다.

### `TodoFilter`

`string` 대신 **`"all" | "active" | "completed"`** 만 허용하는 타입입니다.

- `useState<TodoFilter>("all")`처럼 쓰면, 나중에 `setFilter("active")`는 되지만 `setFilter("기타")`는 **컴파일 단계에서 거절**됩니다.
- `filterTodos`의 두 번째 인자도 `TodoFilter`이므로, 필터 로직과 UI 상태가 같은 “허용 값 집합”을 공유합니다.

---

## 4. 순수 함수: `filterTodos`와 `countByDone`

파일: `src/lib/todoQueries.ts`

```1:25:react/example-ts-todo/src/lib/todoQueries.ts
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

### `filterTodos(todos, filter)`

- **입력**: 전체 목록 `Todo[]`, 현재 필터 `TodoFilter`.
- **출력**: 조건에 맞는 `Todo[]` (원본 배열을 바꾸지 않고 새 배열을 반환).
- **`switch`**: `TodoFilter`의 세 가지 경우를 나눕니다. `default`는 `"all"`일 때 전체를 그대로 돌려줍니다.

이 함수는 React 컴포넌트 밖에 두었기 때문에 **테스트하기 쉽고**, “목록을 어떻게 거를지”가 한곳에 모여 있습니다.

### `countByDone(todos)`

- **입력**: `Todo[]`.
- **출력**: `{ active, completed }` 형태의 객체.
- 미완료 개수(`active`)와 완료 개수(`completed`)를 한 번의 순회로 계산합니다.

`App`에서는 필터 버튼 옆 숫자 표시용으로 `active` / `completed`를 쓰고, `TodoStats`는 같은 함수로 요약 문구를 만듭니다. **같은 규칙을 한 함수로 공유**해 두면 “한쪽만 고쳐서 숫자가 어긋나는” 실수를 줄일 수 있습니다.

---

## 5. 최상위 상태: `App.tsx`

```14:50:react/example-ts-todo/src/App.tsx
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
```

### 5.1 세 가지 `useState`

1. **`useState<Todo[]>(...)`**  
   모든 할 일의 **단일 출처(single source of truth)** 입니다. 추가·수정·삭제·완료 토글은 전부 `setTodos`로 반영됩니다.

2. **`useState<Todo | null>(null)`**  
   **지금 폼에서 수정 중인 항목**입니다. `null`이면 “새로 추가” 모드, `Todo`이면 그 항목의 `text`를 고치는 모드입니다.

3. **`useState<TodoFilter>("all")`**  
   목록 상단 필터(전체 / 할 일 / 완료)와 연결됩니다.

### 5.2 `useMemo`로 보이는 목록과 개수

- **`visible`**: `todos`와 `filter`가 바뀔 때만 `filterTodos`를 다시 실행합니다. 화면에 그리는 배열은 항상 `Todo[]`입니다.
- **`active` / `completed`**: `todos`가 바뀔 때만 `countByDone`을 다시 실행합니다.

데이터가 많아지면 메모이제이션이 의미가 있고, 적어도 **“파생 데이터는 여기서 만든다”**는 구조가 분명해집니다.

### 5.3 `nextId`

```8:12:react/example-ts-todo/src/App.tsx
function nextId(list: Todo[]): number {
  if (list.length === 0) return 1;
  return Math.max(...list.map((t) => t.id)) + 1;
}
```

새 항목에 붙일 `id`를 계산합니다. 인자 타입이 `Todo[]`이므로, 실수로 다른 배열을 넘기면 타입 검사에서 걸립니다.

---

## 6. CRUD 동작 정리

| 동작 | 트리거 | 상태 변화 |
|------|--------|-----------|
| **Create** | 폼 제출(추가 모드) | `setTodos`에 `{ id, text, done: false }` 추가 |
| **Read** | (항상) | `visible`로 필터된 `Todo[]`를 `TodoList`에 전달 |
| **Update** | 폼 제출(수정 모드) | 같은 `id`의 항목을 새 `text`로 교체 후 `editing` 초기화 |
| **Delete** | 목록의 삭제 버튼 | `id`로 제거; 편집 중이던 항목이면 `editing`도 `null` |
| **Toggle** | 체크박스 | 해당 `id`의 `done`만 반전 |

`TodoForm`은 **생성**일 때는 `onCreate(string)`만 호출하고, **수정**일 때는 기존 `Todo`를 펼친 뒤 `text`만 바꾼 객체를 `onUpdate`에 넘깁니다. 그래서 `id`와 `done`이 유지됩니다.

```23:28:react/example-ts-todo/src/components/TodoForm.tsx
    if (editing) {
      onUpdate({ ...editing, text: trimmed });
    } else {
      onCreate(trimmed);
      setText("");
    }
```

---

## 7. 컴포넌트와 Props (타입 계약)

### 7.1 `TodoForm` — `TodoFormProps`

```3:8:react/example-ts-todo/src/components/TodoForm.tsx
export interface TodoFormProps {
  editing: Todo | null;
  onCreate: (text: string) => void;
  onUpdate: (todo: Todo) => void;
  onCancelEdit: () => void;
}
```

- 부모(`App`)는 **반드시** 이 네 가지를 넘겨야 합니다.
- `onUpdate`에 `Todo`가 아닌 값(예: `text`만 있는 객체)을 넘기면 타입 에러가 납니다.
- `editing`이 바뀔 때 입력창 내용을 맞추기 위해 `useEffect`로 `text` state를 동기화합니다.

### 7.2 `TodoList` — `TodoListProps`

```3:8:react/example-ts-todo/src/components/TodoList.tsx
export interface TodoListProps {
  todos: Todo[];
  onToggle: (id: number) => void;
  onEdit: (id: number) => void;
  onDelete: (id: number) => void;
}
```

- 목록은 **`Todo[]`만** 받습니다. 문자열 배열 등을 넘기면 안 됩니다.
- 콜백은 모두 `id: number` 하나만 받도록 통일해, 자식은 “몇 번 항목인지”만 알면 됩니다.

### 7.3 `TodoFilterBar` — `TodoFilterBarProps`

```3:8:react/example-ts-todo/src/components/TodoFilterBar.tsx
export interface TodoFilterBarProps {
  filter: TodoFilter;
  onChange: (filter: TodoFilter) => void;
  activeCount: number;
  completedCount: number;
}
```

- `onChange`에 `TodoFilter`만 넘어가게 되어, 잘못된 필터 문자열로 상태를 바꾸기 어렵습니다.
- 버튼 목록 `FILTERS`도 `{ value: TodoFilter; label: string }[]`로 선언해 `value`가 타입과 일치합니다.

### 7.4 `TodoStats` — `TodoStatsProps`

```4:9:react/example-ts-todo/src/components/TodoStats.tsx
export interface TodoStatsProps {
  todos: Todo[];
}

export function TodoStats({ todos }: TodoStatsProps) {
  const { active, completed } = countByDone(todos);
```

전체 목록만 받아 내부에서 `countByDone`을 호출합니다. **표시 로직**만 담당하고, 데이터 변경은 부모에 맡깁니다.

---

## 8. Props 타입이 막아 주는 실수 (예시)

아래처럼 고치면 TypeScript가 에러를 냅니다.

- `TodoList`에 `todos={["a","b"]}` 넣기 → `string[]`는 `Todo[]`가 아님.
- `TodoFilterBar`의 `onChange`에 `(f: string) => ...`만 넘기고 내부에서 임의 문자열 호출 → `TodoFilter`와 맞지 않음.
- `handleUpdate`에서 `{ text: "만" }`만 `setTodos`에 넣기 → `Todo`에 필수인 `id`, `done` 누락.

즉, **컴포넌트 경계에서 타입을 선언해 두면** 리팩터링할 때도 “어디까지가 맞는 데이터인지”가 문서처럼 남습니다.

---

## 9. 파일 구조 한눈에 보기

```
example-ts-todo/
├── src/
│   ├── types/todo.ts          # Todo, TodoFilter
│   ├── lib/todoQueries.ts     # filterTodos, countByDone
│   ├── components/
│   │   ├── TodoForm.tsx
│   │   ├── TodoList.tsx
│   │   ├── TodoFilterBar.tsx
│   │   └── TodoStats.tsx
│   ├── App.tsx                # 상태 + 핸들러 + 조합
│   ├── main.tsx
│   └── index.css
├── README.md
└── Todo-TypeScript-상세설명.md   ← 이 문서
```

---

## 10. 다음에 확장해 볼 수 있는 것

- `Todo`에 `createdAt: string` 등 필드 추가 → `interface`만 고치면 타입 에러가 나는 위치를 따라가며 수정 가능.
- `filterTodos`에 정렬 옵션 추가 → 반환 타입은 그대로 `Todo[]`로 두고 인자만 늘리기.
- 로컬 스토리지 저장: `JSON.parse` 결과를 **`Todo` 형태인지 검사**하는 타입 가드(별도 학습 주제)와 함께 쓰면 안전합니다.

이 예제는 **모델(`Todo`) · 파생 로직(`todoQueries`) · UI 계약(`*Props`)** 을 나눈 작은 앱입니다. 같은 패턴을 가계부(`Transaction`) 등 다른 도메인에도 그대로 옮겨 쓸 수 있습니다.
