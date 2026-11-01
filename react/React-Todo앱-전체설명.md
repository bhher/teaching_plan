# React Todo 앱 전체 설명

> App · Header · TodoEditor · TodoList · TodoItem  
> **추가 / 완료 토글 / 삭제 / 검색** — 수업용 상세 해설

---

## 1. 전체 구조부터 이해하기

현재 프로젝트는 크게 4개의 컴포넌트로 구성되어 있습니다. (실질적으로는 Header까지 포함하면 5개)

```
App
 │
 ├── Header
 │
 ├── TodoEditor
 │      └── 할 일 입력
 │
 └── TodoList
        │
        └── TodoItem
             ├── 체크박스
             └── 삭제 버튼
```

그리고 **Todo 데이터는 App이 가지고 있습니다.**

```
App
 │
 │ todo State
 │
 ├── TodoEditor
 │      ↓
 │    onCreate
 │      ↓
 │    App의 todo 변경
 │
 └── TodoList
        ↓
      todo 전달
        ↓
     TodoItem
        │
        ├── 체크 → onUpdate → App
        │
        └── 삭제 → onDelete → App
```

### 핵심

> **State는 App이 가지고 있고,  
> 자식 컴포넌트는 Props를 통해 데이터를 받거나 함수를 전달받는다.**

---

## 2. App.jsx가 전체를 관리한다

### import

```jsx
import React, { useState, useRef } from 'react'
import './App.css'
import Header from './component/Header'
import TodoEditor from './component/TodoEditor'
import TodoList from './component/TodoList'
```

여기서 중요한 것은 React Hook입니다.

| Hook | 역할 |
|------|------|
| **useState** | 컴포넌트의 상태(State)를 관리 |
| **useRef** | 값을 기억하거나 DOM에 직접 접근 |

이번 코드에서 `useRef`는 **`idRef`**(다음 id)와 TodoEditor의 **`inputRef`**(DOM focus)에 사용합니다.

---

## 3. 초기 Todo 데이터

```jsx
const mockTodo = [
  {
    id: 0,
    isDone: false,
    content: 'react 공부하기',
    createdDate: new Date().getTime(),
  },
  {
    id: 1,
    isDone: false,
    content: '빨래 널기',
    createdDate: new Date().getTime(),
  },
  {
    id: 2,
    isDone: false,
    content: '노래 연습하기',
    createdDate: new Date().getTime(),
  },
]
```

Todo **하나**는 객체입니다.

```js
{
  id: 0,
  isDone: false,
  content: 'react 공부하기',
  createdDate: 123456789,
}
```

| 속성 | 의미 |
|------|------|
| `id` | Todo 고유 번호 |
| `isDone` | 완료 여부 |
| `content` | Todo 내용 |
| `createdDate` | 작성 날짜(타임스탬프) |

---

## 4. Todo State

```jsx
const [todo, setTodo] = useState(mockTodo)
```

여기가 가장 중요합니다.

처음에는:

```
todo
 ↓
mockTodo
```

즉 `todo`에는 **현재 Todo 목록**이 들어 있고,  
`setTodo()`로 목록을 변경합니다.

```
todo        → 현재 Todo 데이터
setTodo()   → Todo 데이터 변경
```

---

## 5. idRef

```jsx
const idRef = useRef(3)
```

현재 Todo의 **다음 ID**를 관리합니다.

기존 데이터가 `id = 0, 1, 2` 이므로 다음 Todo는 `id = 3`부터입니다.

`idRef.current`의 현재 값은 **3**입니다.

---

## 6. Todo 추가 — onCreate

```jsx
const onCreate = (content) => {
```

TodoEditor에서 입력한 내용을 전달받습니다.

사용자가 `운동하기`라고 입력하면:

```jsx
onCreate('운동하기')
```

### 새로운 Todo 객체 생성

```jsx
const newItem = {
  id: idRef.current,
  content,
  isDone: false,
  createdDate: new Date().getTime(),
}
```

결과:

```js
{
  id: 3,
  content: '운동하기',
  isDone: false,
  createdDate: /* 현재시간 */,
}
```

---

## 7. 배열 앞에 Todo 추가

```jsx
setTodo([newItem, ...todo])
```

`...todo`는 **전개 연산자(Spread Operator)** 입니다.

기존:

```js
[Todo0, Todo1, Todo2]
```

`[newItem, ...todo]` 결과:

```js
[newItem, Todo0, Todo1, Todo2]
```

→ **새로운 Todo를 배열의 맨 앞에 추가**합니다.

---

## 8. ID 증가

```jsx
idRef.current += 1
```

처음에는 `3` → 추가 후 `4`  
다음 Todo는 `id = 4`를 사용합니다.

---

## 9. Todo 수정 — onUpdate

```jsx
const onUpdate = (targetId) => {
```

체크박스를 클릭했을 때 실행됩니다.  
`targetId`는 클릭한 Todo의 ID입니다.

예: `빨래 널기`(id = 1) 체크 → `onUpdate(1)`

---

## 10. map()으로 Todo 수정

```jsx
setTodo(
  todo.map((it) => {
    // ...
  })
)
```

`map()`은 배열을 처음부터 끝까지 하나씩 확인하면서 **새로운 배열**을 만드는 함수입니다.

예: Todo 0, 1, 2를 하나씩 검사합니다.

---

## 11. 삼항 연산자로 수정

현재 코드:

```jsx
return it.id === targetId ? { ...it, isDone: !it.isDone } : it
```

이것은 다음 코드와 같습니다.

```jsx
if (it.id === targetId) {
  return {
    ...it,
    isDone: !it.isDone,
  }
} else {
  return it
}
```

### 핵심

`it.id === targetId` → 현재 Todo의 ID와 클릭한 ID가 같은지 확인  
같으면 `{ ...it, isDone: !it.isDone }`

---

## 12. !it.isDone 의 의미

| 현재 | `!it.isDone` |
|------|----------------|
| `false` | `true` |
| `true` | `false` |

→ **체크 상태를 반대로 뒤집는** 코드입니다.

```
false → true
true  → false
```

---

## 13. {...it} 가 중요한 이유

```jsx
{
  ...it,
  isDone: !it.isDone,
}
```

기존:

```js
{
  id: 1,
  content: '빨래 널기',
  isDone: false,
  createdDate: 12345,
}
```

결과:

```js
{
  id: 1,
  content: '빨래 널기',
  isDone: true,
  createdDate: 12345,
}
```

→ **나머지 속성은 유지하고 `isDone`만 변경**합니다.

---

## 14. Todo 삭제

```jsx
const onDelete = (targetId) => {
  setTodo(todo.filter((it) => it.id !== targetId))
}
```

이번에는 `filter()`를 사용합니다.

예: id 0, 1, 2 중 id 1 삭제 → `it.id !== 1`

| id | 결과 |
|----|------|
| 0 | 남김 |
| 1 | 삭제 |
| 2 | 남김 |

→ `[0, 2]`만 남습니다.

### filter() 정리

> 조건이 **true**인 요소만 새로운 배열로 만들어 반환한다.

---

## 15. App에서 자식 컴포넌트에게 전달

```jsx
<Header />
```

Header는 특별히 전달할 데이터가 없습니다.

```jsx
<TodoEditor onCreate={onCreate} />
```

TodoEditor에게 `onCreate` 함수를 전달합니다.

```jsx
<TodoList todo={todo} onUpdate={onUpdate} onDelete={onDelete} />
```

TodoList에는 `todo`, `onUpdate`, `onDelete`를 전달합니다.

→ App이 부모, TodoList가 자식입니다.

---

## 16. Header 컴포넌트

```jsx
<h3>오늘은 📆</h3>
<h1>{new Date().toDateString()}</h1>
```

현재 날짜를 화면에 출력합니다.

예:

```text
오늘은 📆
Fri Sep 18 2026
```

---

## 17. TodoList의 State

```jsx
const [search, setSearch] = useState('')
```

TodoList에서는 **검색어를 별도로** 관리합니다.

처음: `search = ""`  
사용자가 `react` 입력 → `search = "react"`

---

## 18. 검색어 변경

```jsx
const onChangeSearch = (e) => {
  setSearch(e.target.value)
}
```

`e.target.value`는 현재 input에 입력된 값입니다.

`react` 입력 → `setSearch("react")`

---

## 19. 검색 결과 만들기

```jsx
const getSearchResult = () => {
  return search === ''
    ? todo
    : todo.filter((it) =>
        it.content.toLowerCase().includes(search.toLowerCase())
      )
}
```

**삼항 연산자 + filter + 문자열 메서드**가 함께 쓰였습니다.

### 검색어가 없다면

```jsx
search === '' ? todo
```

→ 전체 Todo를 보여줍니다.

### 검색어가 있다면

```jsx
todo.filter(...)
```

→ 검색어가 포함된 Todo만 가져옵니다.

---

## 20. toLowerCase()

```jsx
it.content.toLowerCase()
```

문자열을 소문자로 바꿉니다. (`React` → `react`)

검색어도 `search.toLowerCase()`로 소문자로 바꿉니다.

→ `React` / `react` / `REACT`를 같은 방식으로 검색할 수 있습니다.

---

## 21. includes()

```jsx
.includes(search.toLowerCase())
```

문자열에 특정 문자열이 **포함**되어 있는지 확인합니다.

| 예 | 결과 |
|----|------|
| `"react 공부하기".includes("react")` | `true` |
| `"빨래 널기".includes("react")` | `false` |

---

## 22. TodoItem 생성

```jsx
{getSearchResult().map((it) => (
  <TodoItem
    key={it.id}
    {...it}
    onUpdate={onUpdate}
    onDelete={onDelete}
  />
))}
```

검색 결과를 `map()`으로 반복하면서 TodoItem을 만듭니다.  
Todo가 3개라면 TodoItem도 3개입니다.

---

## 23. key={it.id}

React에서 리스트를 반복 렌더링할 때 중요한 속성입니다.

```jsx
key={it.id}
```

각 Todo를 구분하기 위한 고유한 key입니다.

```
TodoItem key=0
TodoItem key=1
TodoItem key=2
```

React가 리스트의 각 항목을 **효율적으로 관리**하기 위해 사용합니다.

---

## 24. {...it}

```jsx
<TodoItem {...it} />
```

`it`가 아래와 같다면:

```js
{
  id: 1,
  content: '빨래 널기',
  isDone: false,
  createdDate: 12345,
}
```

다음과 **같은 의미**입니다.

```jsx
<TodoItem
  id={it.id}
  content={it.content}
  isDone={it.isDone}
  createdDate={it.createdDate}
/>
```

---

## 25. TodoItem의 구조분해 할당

```jsx
export default function TodoItem({
  id,
  content,
  isDone,
  createdDate,
  onUpdate,
  onDelete,
}) {
```

부모로부터 받은 Props를 바로 꺼내 사용합니다.

원래:

```jsx
props.id
props.content
props.isDone
```

구조분해 후:

```jsx
id
content
isDone
```

---

## 26. 체크박스 클릭

```jsx
const onChangeCheckbox = () => {
  onUpdate(id)
}
```

**자식 → 부모** 이벤트 전달이 발생합니다.

```
체크박스 클릭
     ↓
onChangeCheckbox()
     ↓
onUpdate(id)
     ↓
App의 onUpdate()
     ↓
todo State 변경
     ↓
화면 다시 렌더링
```

---

## 27. 삭제 버튼

```jsx
const onClickDelete = () => {
  onDelete(id)
}
```

```
TodoItem
   ↓
onDelete(id)
   ↓
TodoList
   ↓
App
   ↓
setTodo(todo.filter(...))
```

**실제 삭제는 App에서** 합니다.

```jsx
setTodo(todo.filter((it) => it.id !== targetId))
```

---

## 28. TodoEditor

새로운 Todo를 **입력**하는 역할입니다.

```jsx
const [content, setContent] = useState('')
```

사용자가 입력한 내용을 `content` State에 저장합니다.

---

## 29. useRef (input DOM)

```jsx
const inputRef = useRef()
```

input DOM을 가리키기 위한 Ref입니다.

```jsx
<input ref={inputRef} ... />
```

→ `inputRef.current`는 실제 input 요소를 가리킵니다.

> 빈 입력 시 `focus()`를 쓰려면 **반드시** input에 `ref={inputRef}`를 연결하세요.

---

## 30. 입력값 변경

```jsx
const onChangeContent = (e) => {
  setContent(e.target.value)
}
```

```
"React 공부하기" 입력
      ↓
e.target.value
      ↓
setContent()
      ↓
content
```

---

## 31. Enter 키 처리

```jsx
const onKeyDown = (e) => {
  if (e.keyCode == 13) {
    onSubmit()
  }
}
```

Enter → `keyCode = 13` → `onSubmit()`

다만 현재 React/JavaScript에서는 `keyCode`보다 **`e.key`** 사용을 권장합니다.

```jsx
const onKeyDown = (e) => {
  if (e.key === 'Enter') {
    onSubmit()
  }
}
```

**수업에서는 `e.key === "Enter"` 방식을 추천합니다.**

---

## 32. Todo 등록 — onSubmit

```jsx
const onSubmit = () => {
  if (!content) {
    inputRef.current.focus()
    return
  }
  // ...
}
```

내용이 비어 있으면 input으로 커서를 옮기고 중단합니다.  
→ 입력하지 않고 추가를 누르면 다시 입력창으로 포커스.

---

## 33. 부모의 onCreate 호출

입력값이 정상이라면:

```jsx
onCreate(content)
```

TodoEditor에는 원래 `onCreate`가 없습니다.  
**App에서 전달받은** 함수입니다.

```jsx
<TodoEditor onCreate={onCreate} />
```

TodoEditor에서 `onCreate(content)`를 호출하면 **App에 있는 함수**가 실행됩니다.

---

## 34. 등록 후 입력창 초기화

```jsx
setContent('')
```

```
React 공부하기
       ↓
등록
       ↓
content = ""
       ↓
<input> 비워짐
```

---

## 35. 전체 데이터 흐름 (수업 강조)

### Todo 추가

```
[TodoEditor]
사용자 입력
   ↓
content State
   ↓
onCreate(content)
   ↓
[App]
   ↓
setTodo()
   ↓
todo 변경
   ↓
[TodoList]
   ↓
[TodoItem]
   ↓
화면 업데이트
```

### Todo 체크

```
[TodoItem]
체크박스 클릭
   ↓
onUpdate(id)
   ↓
[App]
   ↓
map()
   ↓
isDone 변경
   ↓
setTodo()
   ↓
화면 업데이트
```

### Todo 삭제

```
[TodoItem]
삭제 버튼 클릭
   ↓
onDelete(id)
   ↓
[App]
   ↓
filter()
   ↓
해당 Todo 제거
   ↓
setTodo()
   ↓
화면 업데이트
```

### 검색

검색은 조금 다릅니다.

```
[TodoList]
검색어 입력
   ↓
search State
   ↓
getSearchResult()
   ↓
filter()
   ↓
TodoItem
   ↓
검색 결과 표시
```

> 검색은 App의 `todo` 자체를 삭제하거나 변경하지 **않습니다**.  
> 단순히 **화면에 보여줄 데이터만** 필터링합니다.

---

## 36. 이 예제에서 배우는 React 핵심 개념

| 개념 | 사용 부분 |
|------|-----------|
| 컴포넌트 | App, Header, TodoEditor, TodoList, TodoItem |
| useState | `todo`, `content`, `search` |
| useRef | `idRef`, `inputRef` |
| Props | `todo`, `onCreate`, `onUpdate`, `onDelete` |
| 이벤트 | `onChange`, `onClick`, `onKeyDown` |
| 구조분해 할당 | `TodoItem({ id, content... })` |
| Spread 연산자 | `{...it}`, `[newItem, ...todo]` |
| map() | Todo 수정 / 리스트 출력 |
| filter() | Todo 삭제 / 검색 |
| 삼항 연산자 | 검색 / Todo 수정 |
| includes() | 검색 |
| toLowerCase() | 대소문자 구분 없는 검색 |
| 불변성 | map, filter, Spread 사용 |
| 부모 → 자식 | Props 전달 |
| 자식 → 부모 | 함수 호출을 통한 이벤트 전달 |

---

## 37. 학생들에게 꼭 강조할 5가지

### ① State는 데이터를 관리한다

```jsx
const [todo, setTodo] = useState(mockTodo)
```

### ② Props는 부모가 자식에게 전달한다

```jsx
<TodoList todo={todo} />
```

### ③ 자식이 부모의 함수를 호출할 수 있다

```jsx
<TodoEditor onCreate={onCreate} />
```

자식에서:

```jsx
onCreate(content)
```

### ④ 배열 데이터는 map, filter로 처리한다

```jsx
todo.map(...)
todo.filter(...)
```

### ⑤ State를 직접 수정하지 않는다

잘못된 방식:

```jsx
todo.push(newItem)
```

권장 방식:

```jsx
setTodo([newItem, ...todo])       // 추가
setTodo(todo.map(...))            // 수정
setTodo(todo.filter(...))         // 삭제
```

---

## 한 문장으로 정리

> **데이터는 부모가 State로 관리하고,  
> 자식은 Props로 데이터를 받으며,  
> 자식에서 이벤트가 발생하면  
> 부모가 전달해준 함수를 호출해서  
> 부모의 State를 변경한다.**

---

## 부록 — App.jsx 전체 코드

```jsx
import React, { useState, useRef } from 'react'
import './App.css'
import Header from './component/Header'
import TodoEditor from './component/TodoEditor'
import TodoList from './component/TodoList'

const mockTodo = [
  {
    id: 0,
    isDone: false,
    content: 'react 공부하기',
    createdDate: new Date().getTime(),
  },
  {
    id: 1,
    isDone: false,
    content: '빨래 널기',
    createdDate: new Date().getTime(),
  },
  {
    id: 2,
    isDone: false,
    content: '노래 연습하기',
    createdDate: new Date().getTime(),
  },
]

export default function App() {
  const [todo, setTodo] = useState(mockTodo)
  const idRef = useRef(3)

  const onCreate = (content) => {
    const newItem = {
      id: idRef.current,
      content,
      isDone: false,
      createdDate: new Date().getTime(),
    }
    setTodo([newItem, ...todo])
    idRef.current += 1
  }

  const onUpdate = (targetId) => {
    setTodo(
      todo.map((it) =>
        it.id === targetId ? { ...it, isDone: !it.isDone } : it
      )
    )
  }

  const onDelete = (targetId) => {
    setTodo(todo.filter((it) => it.id !== targetId))
  }

  return (
    <div className="App">
      <Header />
      <TodoEditor onCreate={onCreate} />
      <TodoList todo={todo} onUpdate={onUpdate} onDelete={onDelete} />
    </div>
  )
}
```
