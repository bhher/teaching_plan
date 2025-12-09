## 4차시 – 리스트 & 조건부 렌더링, ToDo 1단계

### 0. 학습 목표

- 배열 데이터를 `.map()`으로 JSX에 렌더링할 수 있다.
- `key` 속성의 필요성을 이해한다.
- 조건부 렌더링으로 “없음” 메시지 등을 표시할 수 있다.

---

### 1. 이론 (슬라이드 흐름)

#### 1) 지난 시간 복습

- 컴포넌트 분리, props 전송, children 개념 다시 확인

#### 2) 리스트 렌더링 개념

- 배열 → 화면:

```jsx
const fruits = ['사과', '바나나', '포도'];

return (
  <ul>
    {fruits.map((fruit) => (
      <li key={fruit}>{fruit}</li>
    ))}
  </ul>
);
```

- `key` 역할:
  - React가 “어떤 항목이 추가/삭제/이동되었는지” 구분하는 기준
  - 성능 + 올바른 업데이트를 위해 중요

#### 3) 조건부 렌더링

- `조건 && JSX` 패턴:

```jsx
{todos.length === 0 && <p>할 일이 없습니다.</p>}
```

- 삼항 연산자:

```jsx
{isLoading ? <p>로딩 중...</p> : <TodoList />}
```

#### 4) ToDo 데이터 구조 설계

- 예시:

```js
const initialTodos = [
  { id: 1, text: 'React 강의 듣기', done: false },
  { id: 2, text: 'JavaScript 복습하기', done: true },
];
```

---

### 2. 실습 – 정적(고정) ToDo 리스트 만들기

#### 2.1 실습 목표

- 고정된 배열을 기반으로 ToDo 리스트를 화면에 그려본다.
- 완료된 항목은 스타일을 다르게 표현한다.
- 항목이 없을 때 “할 일이 없습니다” 메시지 표시.

---

### 3. 코드 스켈레톤

#### 3.1 `TodoList.jsx`

```jsx
// src/TodoList.jsx
const initialTodos = [
  { id: 1, text: 'React 강의 듣기', done: false },
  { id: 2, text: 'JavaScript 복습하기', done: true },
];

function TodoList() {
  const todos = initialTodos; // 4차시는 아직 state 없이 고정 데이터

  if (todos.length === 0) {
    return <p>할 일이 없습니다.</p>;
  }

  return (
    <div>
      <h2>할 일 목록</h2>
      <ul>
        {todos.map((todo) => (
          <li
            key={todo.id}
            style={{
              textDecoration: todo.done ? 'line-through' : 'none',
              color: todo.done ? '#999' : '#000',
            }}
          >
            {todo.text}
          </li>
        ))}
      </ul>
    </div>
  );
}

export default TodoList;
```

#### 3.2 `App.jsx`

```jsx
// src/App.jsx
import TodoList from './TodoList.jsx';

function App() {
  return (
    <div>
      <h1>4차시: 리스트 & 조건부 렌더링</h1>
      <TodoList />
    </div>
  );
}

export default App;
```

---

### 4. 추가 실습 – 완료 상태에 따른 표시

1. `done`이 `true`인 항목에는 앞에 `[완료]` 라벨을 표시해 보기

```jsx
{todo.done && <span style={{ color: 'green', marginRight: 8 }}>[완료]</span>}
{todo.text}
```

2. `done`이 `false`인 경우 `[진행중]` 표시

```jsx
<span style={{ color: todo.done ? 'green' : 'orange', marginRight: 8 }}>
  {todo.done ? '[완료]' : '[진행중]'}
</span>
{todo.text}
```

---

### 5. 과제

1. **필터 버튼 추가**
   - 버튼 3개:
     - “전체 보기”
     - “완료만 보기”
     - “진행중만 보기”
   - 아직은 state 없이, **각 버튼을 클릭했을 때 콘솔에 어떤 필터를 선택했는지 출력**만 하게 구현  
     (다음 차시에서 `useState`와 결합해 실제 필터링 구현 예정)

2. **UI 개선**
   - `<li>` 대신 카드 형태로 보여주기 (border, padding 등 CSS 연습)
   - 완료된 항목은 배경색을 연한 회색으로 표시

> 다음 차시 예고:  
> “다음 시간에는 오늘 만든 ToDo 리스트에 **입력 폼과 상태**를 추가해서, 실제로 할 일을 추가/삭제하는 ToDo 앱으로 발전시켜 봅니다.”



