/** @typedef {"all"|"active"|"completed"} TodoFilter */

/**
 * @param {{ id: number; text: string; done: boolean }[]} todos
 * @param {TodoFilter} filter
 */
export function filterTodos(todos, filter) {
  switch (filter) {
    case "active":
      return todos.filter((t) => !t.done);
    case "completed":
      return todos.filter((t) => t.done);
    default:
      return todos;
  }
}

/**
 * @param {{ id: number; text: string; done: boolean }[]} todos
 * @returns {{ active: number; completed: number }}
 */
export function countByDone(todos) {
  return todos.reduce(
    (acc, t) => {
      if (t.done) acc.completed += 1;
      else acc.active += 1;
      return acc;
    },
    { active: 0, completed: 0 }
  );
}
