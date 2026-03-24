import { useState, useEffect } from 'react';
import DiaryForm from './components/DiaryForm';
import DiaryList from './components/DiaryList';

const STORAGE_KEY = 'emotion-diary-entries';

function loadEntries() {
  try {
    const raw = localStorage.getItem(STORAGE_KEY);
    return raw ? JSON.parse(raw) : [];
  } catch {
    return [];
  }
}

function saveEntries(entries) {
  localStorage.setItem(STORAGE_KEY, JSON.stringify(entries));
}

function App() {
  const [entries, setEntries] = useState(loadEntries);

  useEffect(() => {
    saveEntries(entries);
  }, [entries]);

  const addEntry = (data) => {
    const entry = {
      id: crypto.randomUUID(),
      emotion: data.emotion,
      content: data.content,
      createdAt: Date.now(),
    };
    setEntries((prev) => [entry, ...prev]);
  };

  const deleteEntry = (id) => {
    setEntries((prev) => prev.filter((e) => e.id !== id));
  };

  return (
    <div className="app">
      <header className="header">
        <h1>
          <span className="header-icon">📔</span>
          감정일기장
        </h1>
        <p className="tagline">오늘의 감정을 기록하고 마음을 정리해보세요</p>
      </header>

      <main className="main">
        <section className="write-section">
          <h2>오늘의 일기</h2>
          <DiaryForm onSubmit={addEntry} />
        </section>

        <section className="history-section">
          <h2>나의 일기 모음</h2>
          <DiaryList entries={entries} onDelete={deleteEntry} />
        </section>
      </main>
    </div>
  );
}

export default App;
