import { useState, useMemo } from 'react';

const initialTodos = [
  { id: 1, text: 'HTML 복습', completed: true },
  { id: 2, text: 'React 공부하기', completed: false },
  { id: 3, text: 'JavaScript 공부하기', completed: false },
];

/** ④ Todo List — useState, map, filter, useMemo */
export default function Practice04Todo() {
  const [todos, setTodos] = useState(initialTodos);
  const [input, setInput] = useState('');

  const addTodo = () => {
    const text = input.trim();
    if (!text) return;

    const newTodo = {
      id: Date.now(),
      text,
      completed: false,
    };

    setTodos([newTodo, ...todos]);
    setInput('');
  };

  const toggleTodo = (id) => {
    setTodos(
      todos.map((todo) =>
        todo.id === id ? { ...todo, completed: !todo.completed } : todo
      )
    );
  };

  const deleteTodo = (id) => {
    setTodos(todos.filter((todo) => todo.id !== id));
  };

  const completedCount = useMemo(() => {
    return todos.filter((todo) => todo.completed).length;
  }, [todos]);

  return (
    <div className="panel">
      <h2>④ 📝 Todo List</h2>
      <p className="goal">
        학습: 추가(map 앞 spread) · 완료(toggle) · 삭제(filter) · 완료 개수(useMemo)
      </p>

      <div className="row">
        <input
          type="text"
          value={input}
          onChange={(e) => setInput(e.target.value)}
          placeholder="할 일 입력"
          onKeyDown={(e) => e.key === 'Enter' && addTodo()}
        />
        <button type="button" className="btn" onClick={addTodo}>
          추가
        </button>
      </div>

      {todos.map((todo) => (
        <div className={`todo-item ${todo.completed ? 'done' : ''}`} key={todo.id}>
          <input
            type="checkbox"
            checked={todo.completed}
            onChange={() => toggleTodo(todo.id)}
          />
          <span style={{ flex: 1 }}>{todo.text}</span>
          <button
            type="button"
            className="btn btn-danger btn-sm"
            onClick={() => deleteTodo(todo.id)}
          >
            삭제
          </button>
        </div>
      ))}

      <div className="stats">
        전체 {todos.length}개 · 완료 {completedCount}개
      </div>

      <div className="hint">
        흐름: useState → 입력 → 추가 → map → 완료 → filter 삭제 → useMemo(완료 개수)
      </div>
    </div>
  );
}
