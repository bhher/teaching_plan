import { useEffect, useState } from 'react';

const API_BASE = '/api/items';

export default function App() {
  const [list, setList] = useState([]);
  const [title, setTitle] = useState('');

  const load = () =>
    fetch(API_BASE)
      .then((r) => r.json())
      .then(setList)
      .catch(console.error);

  useEffect(() => {
    load();
  }, []);

  const add = () => {
    fetch(API_BASE, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ title, done: false }),
    })
      .then((r) => r.json())
      .then(() => {
        setTitle('');
        load();
      });
  };

  const remove = (id) => {
    fetch(`${API_BASE}/${id}`, { method: 'DELETE' }).then(() => load());
  };

  return (
    <div className="app">
      <h1>Items (Express API)</h1>
      <div className="row">
        <input
          value={title}
          onChange={(e) => setTitle(e.target.value)}
          placeholder="제목"
        />
        <button type="button" onClick={add}>
          추가
        </button>
      </div>
      <ul>
        {list.map((row) => (
          <li key={row.id}>
            <span className={row.done ? 'done' : ''}>{row.title}</span>
            <button type="button" onClick={() => remove(row.id)}>
              삭제
            </button>
          </li>
        ))}
      </ul>
    </div>
  );
}
