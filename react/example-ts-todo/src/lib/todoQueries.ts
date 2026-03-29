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
