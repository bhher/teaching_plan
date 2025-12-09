## 5차시 – 폼 입력 & ToDo 앱 2단계 (추가/삭제)

### 0. 학습 목표

- Controlled Component 개념을 이해한다.
- 입력 폼(`input`)을 상태와 연결하여 제어할 수 있다.
- ToDo 리스트에 **할 일 추가/삭제 기능**을 구현한다.

---

### 1. 이론 (슬라이드 흐름)

#### 1) 지난 시간 복습

- `.map()`으로 리스트 렌더링
- `key`의 역할
- 조건부 렌더링으로 “할 일이 없습니다” 표시

#### 2) Controlled Component

- HTML 기본 폼:

```html
<input type="text" />
```

- React에서의 제어:
  - `value`를 state에 묶고
  - `onChange`에서 state를 업데이트

```jsx
const [value, setValue] = useState('');

return (
  <input
    value={value}
    onChange={(e) => setValue(e.target.value)}
  />
);
```

> 강의 멘트 예시  
> “입력값의 **진짜 소유자**는 DOM이 아니라 React state입니다.  
> input은 state를 화면에 보여주는 창문 역할만 해요.”

#### 3) ToDo 상태 구조 설계

- 4차시에서는 `initialTodos`만 사용 (고정)
- 5차시부터:

```jsx
const [todos, setTodos] = useState(initialTodos);
const [input, setInput] = useState('');
```

- 새 todo 추가:

```jsx
setTodos([
  ...todos,
  { id: nextId++, text: input, done: false },
]);
```

- 삭제:

```jsx
setTodos(todos.filter((todo) => todo.id !== id));
```

---

### 2. 실습 – ToDo 추가/삭제 기능 구현

#### 2.1 실습 목표

- 입력 폼에 텍스트 작성 → 추가 버튼 클릭 → 리스트에 새 할 일 추가
- 각 항목 옆 “삭제” 버튼으로 해당 항목 삭제

---

### 3. 코드 스켈레톤

#### 3.1 `TodoApp.jsx`

```jsx
// src/TodoApp.jsx
import { useState } from 'react';

let nextId = 3;

const initialTodos = [
  { id: 1, text: 'React 강의 듣기', done: false },
  { id: 2, text: 'JavaScript 복습하기', done: true },
];

function TodoApp() {
  const [todos, setTodos] = useState(initialTodos);
  const [input, setInput] = useState('');

  const handleChange = (e) => {
    setInput(e.target.value);
  };

  const handleAdd = () => {
    const text = input.trim();
    if (!text) return;

    setTodos([
      ...todos,
      { id: nextId++, text, done: false },
    ]);

    setInput('');
  };

  const handleRemove = (id) => {
    setTodos(todos.filter((todo) => todo.id !== id));
  };

  return (
    <div>
      <h2>ToDo App</h2>

      <div>
        <input
          value={input}
          onChange={handleChange}
          placeholder="할 일을 입력하세요"
        />
        <button onClick={handleAdd}>추가</button>
      </div>

      {todos.length === 0 ? (
        <p>할 일이 없습니다.</p>
      ) : (
        <ul>
          {todos.map((todo) => (
            <li key={todo.id}>
              {todo.text}
              <button onClick={() => handleRemove(todo.id)}>삭제</button>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}

export default TodoApp;
```

#### 3.2 `App.jsx`

```jsx
// src/App.jsx
import TodoApp from './TodoApp.jsx';

function App() {
  return (
    <div>
      <h1>5차시: 폼 입력 & ToDo 2단계</h1>
      <TodoApp />
    </div>
  );
}

export default App;
```

---

### 4. 과제

1. **Enter 키로 추가하기**
   - input에서 Enter 키를 눌렀을 때도 `handleAdd`가 실행되도록 구현

   ```jsx
   const handleKeyDown = (e) => {
     if (e.key === 'Enter') {
       handleAdd();
     }
   };

   // input에 onKeyDown 추가
   <input
     value={input}
     onChange={handleChange}
     onKeyDown={handleKeyDown}
   />
   ```

2. **완료 토글 기능 추가 (다음 차시 미리보기용)**
   - 각 항목에 체크박스 추가
   - 체크하면 `done` 값을 반전 (`!todo.done`)
   - 완료된 항목은 회색 + 취소선으로 표시

> 다음 차시 예고:  
> “다음 시간에는 이렇게 만든 ToDo 앱에서 **검색/필터 기능**을 추가하고,  
> 여러 컴포넌트로 상태를 공유하는 법을 연습해 봅니다.”




