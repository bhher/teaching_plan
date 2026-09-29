# React 실습 정리 — App · CreateUser · UserList · User

**관련:** [React-useRef-배열렌더링-항목추가-정리.md](./React-useRef-배열렌더링-항목추가-정리.md)

> `useState` → Props → 이벤트 → `map` → `key` → 컴포넌트 분리를 한 실습으로 묶기

---

## 1. 전체 구조

```
                    App.jsx
                      │
          ┌───────────┴───────────┐
          │                       │
       inputs                    users
          │                       │
          ↓                       ↓
     CreateUser                UserList
          │                       │
     입력 / 등록               users.map()
                                  │
                         ┌────────┼────────┐
                         ↓        ↓        ↓
                       User     User     User
```

### 데이터 흐름

```
입력
 ↓
CreateUser
 ↓
onChange
 ↓
App의 inputs 변경
 ↓
등록 버튼
 ↓
onCreate
 ↓
App의 users 변경
 ↓
UserList
 ↓
map()
 ↓
User
```

| 파일 | 역할 |
|------|------|
| **App.jsx** | `inputs`, `users` / `onChange` / `onCreate` / `nextId` |
| **CreateUser.jsx** | 입력창·등록 버튼 UI만 |
| **UserList.jsx** | `users`를 `map`으로 목록 출력 |
| **User.jsx** | 한 명의 username / email 표시 |

---

## 2. App.jsx

### 완성 코드 (수업 주석 포함)

```jsx
import UserList from './UserList'
import CreateUser from './CreateUser'
import { useRef, useState } from 'react'

function App() {
  // 키보드 입력 상태를 inputs 객체로 관리
  const [inputs, setInputs] = useState({
    username: '',
    email: '',
  })

  // 구조 분해 할당
  // const username = inputs.username
  // const email = inputs.email
  const { username, email } = inputs

  const onChange = (e) => {
    const { name, value } = e.target
    // e.target.name  → "username" 또는 "email"
    // e.target.value → 키보드 입력값

    setInputs({
      ...inputs,     // 기존 값 유지
      [name]: value, // 해당 칸만 변경
    })
  }

  const [users, setUsers] = useState([
    {
      id: 1,
      username: 'velopert',
      email: 'public.velopert@gmail.com',
    },
    {
      id: 2,
      username: 'tester',
      email: 'tester@example.com',
    },
    {
      id: 3,
      username: 'liz',
      email: 'liz@example.com',
    },
  ])

  // 더미 id가 1~3이므로 다음 id 초기값 4
  const nextId = useRef(4)

  const onCreate = () => {
    const user = {
      id: nextId.current,
      username,
      email,
    }

    // 기존 1,2,3 + 새 user
    setUsers([...users, user])

    // 입력창 초기화
    setInputs({
      username: '',
      email: '',
    })

    nextId.current += 1
  }

  return (
    <div>
      <CreateUser
        username={username}
        email={email}
        onChange={onChange}
        onCreate={onCreate}
      />
      <UserList users={users} />
    </div>
  )
}

export default App
```

### App 포인트

| 코드 | 의미 |
|------|------|
| `inputs` 객체 state | 여러 input을 하나로 관리 |
| `...inputs, [name]: value` | 기존 유지 + 해당 칸만 변경 |
| `users` 배열 state | 목록 데이터 (화면 갱신) |
| `nextId = useRef(4)` | id 카운터 (리렌더링 없음) |
| `setUsers([...users, user])` | 불변성 지키며 추가 (`push` 금지) |

---

## 3. CreateUser.jsx

### 완성 코드

```jsx
function CreateUser({ username, email, onChange, onCreate }) {
  return (
    <div>
      <input
        name="username"
        placeholder="계정명"
        onChange={onChange}
        value={username}
      />
      <input
        name="email"
        placeholder="이메일"
        onChange={onChange}
        value={email}
      />
      <button onClick={onCreate}>등록</button>
    </div>
  )
}

export default CreateUser
```

> Vite + 최신 React에서는 `import React from 'react'` 없이도 됩니다.

---

### 포인트 1 — 역할

CreateUser는 **입력 화면만** 담당합니다. 실제 데이터는 **App**이 관리합니다.

```
App
 ├── username / email / onChange / onCreate
       ↓
  CreateUser → 입력창·버튼 표시
```

---

### 포인트 2 — Props

**App → CreateUser**

```jsx
<CreateUser
  username={username}
  email={email}
  onChange={onChange}
  onCreate={onCreate}
/>
```

| App에서 전달 | CreateUser에서 받음 |
|--------------|---------------------|
| `username={username}` | `username` |
| `email={email}` | `email` |
| `onChange={onChange}` | `onChange` |
| `onCreate={onCreate}` | `onCreate` |

---

### 포인트 3 — `name="username"`

사용자가 `hong` 입력 시:

| | 값 |
|--|-----|
| `e.target.name` | `"username"` |
| `e.target.value` | `"hong"` |

```jsx
setInputs({ ...inputs, [name]: value })
// → { username: 'hong', email: '' }
```

---

### 포인트 4 — `name="email"`

`name === "email"` → `email: value`

```js
{ username: 'hong', email: 'hong@test.com' }
```

**핵심:** input의 `name`과 state key가 **같아야** 하나의 `onChange`로 관리 가능

---

### 포인트 5 — 제어 컴포넌트

```
상태 username → value={username} → <input>
```

React state와 input을 연결한 형태 = **Controlled Component**

---

### 포인트 6 — 등록 버튼

`onCreate`는 CreateUser가 만든 함수가 아니라 **App에서 props로 받은 함수**입니다.

```
버튼 클릭 → App의 onCreate()
  → user 생성
  → setUsers([...users, user])
  → inputs 초기화
  → nextId.current += 1
```

---

## 4. UserList.jsx

```jsx
import User from './User'

function UserList({ users }) {
  return (
    <div>
      {users.map((user) => (
        <User user={user} key={user.id} />
      ))}
    </div>
  )
}

export default UserList
```

- `users`는 App에서 props로 받음  
- `key={user.id}`는 React용 (User의 props 아님)

---

## 5. User.jsx

```jsx
function User({ user }) {
  return (
    <div>
      <b>{user.username}</b> <span>({user.email})</span>
    </div>
  )
}

export default User
```

> 같은 코드를 파일에 **두 번** 넣지 마세요. 한 번만 작성합니다.

| props | 의미 |
|-------|------|
| `user` | `{ id, username, email }` 한 명 분 |

---

## 6. 수업 체크리스트

- [ ] CreateUser는 UI만, state는 App
- [ ] `name="username"` / `name="email"` 과 inputs key 일치
- [ ] `value`로 제어 컴포넌트 연결
- [ ] 등록 → App의 `onCreate`
- [ ] UserList는 `map` + `key={user.id}`
- [ ] 배열 추가는 `[...users, user]` (`push` 금지)
- [ ] `nextId`는 `useRef` (화면과 무관한 id)

---

## 7. 자주 하는 실수

| 실수 | 결과 |
|------|------|
| `name` 오타 | 해당 칸 state가 안 바뀜 |
| `value` 빠짐 | 입력과 state가 어긋남 |
| `onCreate` / `onChange` props 누락 | 버튼·입력이 무반응 |
| User.jsx 코드 중복 | 문법 오류 |
| `users`를 UserList 안에만 선언 | 등록해도 목록이 안 늘어남 |
| `key` 없음 | 콘솔 경고 |
