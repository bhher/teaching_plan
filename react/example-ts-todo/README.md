# Todo (TypeScript 입문)

`Todo` 모델, `useState<Todo[]>`, `TodoFilter` 유니온, `filterTodos` / `countByDone` 함수, 컴포넌트 **Props** 타입으로 CRUD를 연습합니다.

상세 설명은 **[Todo-TypeScript-상세설명.md](./Todo-TypeScript-상세설명.md)** 를 참고하세요.

같은 구조를 **JSX만**으로 보려면 상위 폴더의 **`example-todo-jsx`** 를 실행하세요.

## 실행

```bash
cd example-ts-todo
npm install
npm run dev
```

## 구조

| 파일 | 내용 |
|------|------|
| `src/types/todo.ts` | `interface Todo`, `type TodoFilter` |
| `src/lib/todoQueries.ts` | `filterTodos`, `countByDone` (타입 있는 파라미터) |
| `src/App.tsx` | `useState<Todo[]>`, 필터 상태 |
| `src/components/*.tsx` | `TodoFormProps`, `TodoListProps` 등 |

## 기능

- 추가 / 목록 / 수정 / 삭제 / 완료 토글
- 필터: 전체 · 할 일 · 완료
