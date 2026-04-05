# 감정일기장 (emotion-diary) 설명

이 문서는 `react/emotion-diary` 프로젝트의 **React + JSX** 감정 일기 앱이 어떻게 동작하는지, `App.jsx`와 `constants.js`, 컴포넌트를 중심으로 정리한 것입니다.

---

## 1. 개요

- **일기 한 건(entry)** 은 `id`, `emotion`, `content`, `createdAt`을 가진 **JavaScript 객체**입니다.
- **`useState`** 의 초기값을 **`loadEntries` 함수**로 두어, 마운트 시 `localStorage`에서 목록을 읽어옵니다.
- **`useEffect`** 로 `entries`가 바뀔 때마다 **`saveEntries`** 로 다시 저장해 **새로고침 후에도** 기록이 남습니다.
- **추가**는 `DiaryForm` → `onSubmit` → `addEntry`, **삭제**는 `DiaryEntry` → `onDelete` → `deleteEntry`로 **상향 데이터 흐름**을 따릅니다.
- 감정 목록(이모지·라벨·색)은 **`constants.js`의 `EMOTIONS`** 한 곳에서 관리합니다.

---

## 2. 폴더 구조

```
emotion-diary/
├── src/
│   ├── App.jsx                 ← localStorage · entries 상태 · add/delete
│   ├── main.jsx                ← React 루트 마운트
│   ├── index.css
│   ├── constants.js            ← EMOTIONS (감정 메타데이터)
│   └── components/
│       ├── EmotionPicker.jsx   ← 감정 버튼 그리드
│       ├── DiaryForm.jsx       ← 폼 + EmotionPicker + textarea
│       ├── DiaryEntry.jsx      ← 한 줄 카드 (날짜·삭제)
│       └── DiaryList.jsx       ← 목록·정렬·빈 상태
├── Emotion-Diary-설명.md       ← 이 파일
└── index.html, vite.config.js, package.json …
```

---

## 3. 일기(entry) 데이터 모델

`App.jsx`의 `addEntry`에서 만드는 객체 형태입니다.

| 필드        | 타입   | 의미 |
|-------------|--------|------|
| `id`        | string | `crypto.randomUUID()` 고유 ID |
| `emotion`   | string | `EMOTIONS`의 `id`와 동일 (예: `'happy'`) |
| `content`   | string | 일기 본문 (`trim` 적용) |
| `createdAt` | number | `Date.now()` 타임스탬프(ms) |

---

## 4. `App.jsx` 흐름

### 4.1 localStorage

| 이름 | 역할 |
|------|------|
| `STORAGE_KEY` | `'emotion-diary-entries'` — 브라우저 저장 키 |
| `loadEntries()` | `getItem` → `JSON.parse`, 실패 시 `[]` |
| `saveEntries(entries)` | `JSON.stringify` 후 `setItem` |

### 4.2 상태

| state     | 역할 |
|-----------|------|
| `entries` | 일기 객체 배열. 초기값: `useState(loadEntries)` (lazy init) |

### 4.3 동기화

`useEffect(() => { saveEntries(entries); }, [entries]);`  
→ 목록이 바뀔 때마다 로컬에 반영합니다.

### 4.4 핸들러

| 함수          | 하는 일 |
|---------------|--------|
| `addEntry`    | 새 entry를 만들어 배열 **앞**에 추가 (`[entry, ...prev]`) |
| `deleteEntry` | `id`로 필터링해 제거 |

### 4.5 JSX 레이아웃

- 헤더: 앱 제목·태그라인
- **작성 영역**: `DiaryForm onSubmit={addEntry}`
- **목록 영역**: `DiaryList entries={entries} onDelete={deleteEntry}`

---

## 5. `constants.js` — `EMOTIONS`

- 각 항목: `id`, `label`, `emoji`, `color` (CSS에서 쓰는 hex).
- **`EmotionPicker`**: 버튼 선택 시 `emotion.id`를 부모에 넘깁니다.
- **`DiaryEntry`**: `entry.emotion`으로 `find`해 이모지·색·툴팁을 표시합니다. 저장 데이터에 없는 `id`면 `?.` 로 회색·📝 폴백.

---

## 6. 컴포넌트별 역할

### `EmotionPicker.jsx`

- `value`, `onChange(emotionId)` — 제어 컴포넌트 패턴.
- `EMOTIONS.map`으로 버튼; 선택된 항목에 `selected` 클래스.
- `style={{ '--emotion-color': emotion.color }}` 로 테마 변수 전달 (CSS에서 활용 가능).

### `DiaryForm.jsx`

- 로컬 state: `emotion`, `content`.
- 제출: 둘 다 있을 때만 `onSubmit({ emotion, content })`, 후 초기화.
- 저장 버튼은 `disabled={!emotion || !content.trim()}`.

### `DiaryEntry.jsx`

- `entry`, `onDelete(id)`.
- `formatDate(createdAt)`: 오늘이면 “오늘 HH:mm”, 아니면 한국 로케일 날짜+시간.

### `DiaryList.jsx`

- `entries`가 비면 빈 화면 문구.
- 있으면 `slice()` 복사 후 **`createdAt` 내림차순** 정렬해 최신이 위로 오게 표시.
- 각 행에 `DiaryEntry`, `key={entry.id}`.

---

## 7. 데이터 흐름 (한 줄 요약)

```
작성 폼 제출 → DiaryForm.onSubmit → App.addEntry → setEntries
  → useEffect가 localStorage 저장 → DiaryList에 새 props

삭제 클릭 → DiaryEntry.onDelete(id) → App.deleteEntry → setEntries
  → useEffect 저장 → 목록 갱신
```

---

## 8. 전체 코드 (`constants.js` · JSX)

### `src/constants.js`

```js
export const EMOTIONS = [
  { id: 'happy', label: '행복', emoji: '😊', color: '#fcd34d' },
  { id: 'sad', label: '슬픔', emoji: '😢', color: '#93c5fd' },
  { id: 'angry', label: '화남', emoji: '😤', color: '#f87171' },
  { id: 'calm', label: '평온', emoji: '😌', color: '#86efac' },
  { id: 'anxious', label: '불안', emoji: '😰', color: '#c4b5fd' },
  { id: 'excited', label: '설렘', emoji: '🥰', color: '#f9a8d4' },
  { id: 'tired', label: '지침', emoji: '😮‍💨', color: '#a8a29e' },
  { id: 'grateful', label: '감사', emoji: '🙏', color: '#fde68a' },
];
```

### `src/main.jsx`

```jsx
import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import './index.css';

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);
```

### `src/App.jsx`

```jsx
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
```

### `src/components/EmotionPicker.jsx`

```jsx
import { EMOTIONS } from '../constants';

function EmotionPicker({ value, onChange }) {
  return (
    <div className="emotion-picker">
      <span className="emotion-label">오늘의 감정</span>
      <div className="emotion-grid">
        {EMOTIONS.map((emotion) => (
          <button
            key={emotion.id}
            type="button"
            className={`emotion-btn ${value === emotion.id ? 'selected' : ''}`}
            onClick={() => onChange(emotion.id)}
            title={emotion.label}
            style={{
              '--emotion-color': emotion.color,
            }}
          >
            <span className="emotion-emoji">{emotion.emoji}</span>
            <span className="emotion-text">{emotion.label}</span>
          </button>
        ))}
      </div>
    </div>
  );
}

export default EmotionPicker;
```

### `src/components/DiaryForm.jsx`

```jsx
import { useState } from 'react';
import EmotionPicker from './EmotionPicker';

function DiaryForm({ onSubmit }) {
  const [emotion, setEmotion] = useState('');
  const [content, setContent] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!emotion || !content.trim()) return;
    onSubmit({ emotion, content: content.trim() });
    setEmotion('');
    setContent('');
  };

  return (
    <form className="diary-form" onSubmit={handleSubmit}>
      <EmotionPicker value={emotion} onChange={setEmotion} />
      <div className="form-group">
        <textarea
          placeholder="오늘 하루는 어땠나요? 마음을 적어보세요..."
          value={content}
          onChange={(e) => setContent(e.target.value)}
          rows={5}
        />
      </div>
      <button type="submit" disabled={!emotion || !content.trim()} className="submit-btn">
        일기 저장하기
      </button>
    </form>
  );
}

export default DiaryForm;
```

### `src/components/DiaryEntry.jsx`

```jsx
import { EMOTIONS } from '../constants';

function DiaryEntry({ entry, onDelete }) {
  const emotionData = EMOTIONS.find((e) => e.id === entry.emotion);

  const formatDate = (timestamp) => {
    const d = new Date(timestamp);
    const now = new Date();
    const isToday = d.toDateString() === now.toDateString();
    return isToday
      ? `오늘 ${d.toLocaleTimeString('ko-KR', { hour: '2-digit', minute: '2-digit' })}`
      : d.toLocaleDateString('ko-KR', {
          month: 'long',
          day: 'numeric',
          hour: '2-digit',
          minute: '2-digit',
        });
  };

  return (
    <article className="diary-entry">
      <div className="entry-header">
        <span
          className="entry-emotion"
          style={{ backgroundColor: emotionData?.color || '#e5e7eb' }}
          title={emotionData?.label}
        >
          {emotionData?.emoji || '📝'}
        </span>
        <span className="entry-date">{formatDate(entry.createdAt)}</span>
        <button
          type="button"
          className="entry-delete"
          onClick={() => onDelete(entry.id)}
          title="삭제"
        >
          ×
        </button>
      </div>
      <p className="entry-content">{entry.content}</p>
    </article>
  );
}

export default DiaryEntry;
```

### `src/components/DiaryList.jsx`

```jsx
import DiaryEntry from './DiaryEntry';

function DiaryList({ entries, onDelete }) {
  if (entries.length === 0) {
    return (
      <div className="diary-empty">
        <p className="empty-emoji">📔</p>
        <p>아직 작성한 일기가 없어요</p>
        <p>첫 번째 감정을 기록해보세요</p>
      </div>
    );
  }

  return (
    <div className="diary-list">
      {entries
        .slice()
        .sort((a, b) => b.createdAt - a.createdAt)
        .map((entry) => (
          <DiaryEntry key={entry.id} entry={entry} onDelete={onDelete} />
        ))}
    </div>
  );
}

export default DiaryList;
```

---

## 9. 실행 방법

프로젝트 루트(`emotion-diary`)에서:

```bash
npm install
npm run dev
```

---

## 10. 학습 포인트 체크리스트

- [ ] **`useState(함수)`** 로 초기 상태를 lazy하게 `localStorage`에서 읽는 패턴
- [ ] **`useEffect` 의존 배열 `[entries]`** 로 저장 시점 제어 (첫 마운트 포함 매번 저장)
- [ ] **상향 데이터 흐름**: 폼·삭제는 콜백만 호출, 배열 변경은 `App`의 `setEntries`
- [ ] **`constants.js` 단일 출처**: 감정 추가·수정 시 한 파일만 고치면 UI·표시가 함께 맞음
- [ ] **`DiaryList`에서 정렬**: 부모 배열 순서와 무관하게 화면에서는 최신순
- [ ] **`crypto.randomUUID()`** 로 클라이언트 쪽 안정적인 `key` / 삭제 식별자

이 문서는 `src/App.jsx`, `src/main.jsx`, `src/constants.js`, `src/components/*.jsx`를 기준으로 작성되었습니다.
