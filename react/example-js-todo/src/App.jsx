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
