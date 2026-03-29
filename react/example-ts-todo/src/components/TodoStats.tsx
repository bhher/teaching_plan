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
