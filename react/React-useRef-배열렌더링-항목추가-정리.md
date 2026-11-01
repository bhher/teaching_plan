# React 정리 — useRef · 배열 렌더링 · 항목 추가

> velopert 튜토리얼 10~13절 요약  
> (DOM ref / map / nextId / 배열 불변성)

---

## 한눈에

| 주제 | 핵심 |
|------|------|
| **useRef (DOM)** | `ref={이름}` → `이름.current` 로 DOM 접근 → `focus()` 등 |
| **배열 렌더링** | `users.map(...)` + **`key={user.id}`** |
| **useRef (변수)** | 리렌더링 없이 값 유지 (예: `nextId`) |
| **배열 추가** | `push` 금지 → `[...users, user]` 또는 `users.concat(user)` |

---

## 10. useRef 로 특정 DOM 선택하기

### 왜 쓰나
JS의 `getElementById` / `querySelector` 대신, React에서는 **ref**로 DOM을 집는다.

**예:** 포커스, 스크롤 위치, 크기 측정, Video/Chart 라이브러리 연결

### 패턴

```jsx
import { useState, useRef } from 'react'

function InputSample() {
  const [inputs, setInputs] = useState({ name: '', nickname: '' })
  const nameInput = useRef()          // 1) Ref 객체 생성
  const { name, nickname } = inputs

  const onChange = (e) => {
    const { name, value } = e.target
    setInputs({ ...inputs, [name]: value })
  }

  const onReset = () => {
    setInputs({ name: '', nickname: '' })
    nameInput.current.focus()         // 3) .current 로 DOM API 호출
  }

  return (
    <div>
      <input
        name="name"
        value={name}
        onChange={onChange}
        ref={nameInput}                 // 2) 원하는 DOM에 연결
      />
      <input name="nickname" value={nickname} onChange={onChange} />
      <button onClick={onReset}>초기화</button>
      <div>
        <b>값: </b>
        {name} ({nickname})
      </div>
    </div>
  )
}
```

**흐름:** `useRef()` → `ref={...}` → `ref.current` = 실제 DOM

---

## 11. 배열 렌더링하기

### 나쁜 예 (인덱스 하드코딩)

```jsx
<div>{users[0].username}</div>
<div>{users[1].username}</div>
<div>{users[2].username}</div>
```

### 좋은 예 — `User` 분리 + `map` + `key`

```jsx
function User({ user }) {
  return (
    <div>
      <b>{user.username}</b> <span>({user.email})</span>
    </div>
  )
}

function UserList({ users }) {
  return (
    <div>
      {users.map((user) => (
        <User user={user} key={user.id} />
      ))}
    </div>
  )
}
```

- 한 파일에 컴포넌트 여러 개 선언해도 됨
- **`key`는 고유 id** (인덱스 `key={i}` 는 비권장)

---

## 12. useRef 로 컴포넌트 안 변수 만들기

### DOM용 vs 변수용

| | **useState** | **useRef (변수)** |
|--|--------------|-------------------|
| 값 변경 시 | 리렌더링 O | 리렌더링 **X** |
| 조회 시점 | 다음 렌더 후 | **바로** `.current` |
| 용도 | 화면에 보이는 데이터 | id, 타이머 id, 스크롤 위치 등 |

**관리하기 좋은 값 예**
- `setTimeout` / `setInterval` id
- 외부 라이브러리 인스턴스
- scroll 위치
- **다음에 쓸 고유 id (`nextId`)**

### 배열은 App에서 관리 → props로 전달

```jsx
// App.js
const users = [ /* ... */ ]
return <UserList users={users} />

// UserList.js
function UserList({ users }) {
  return (
    <div>
      {users.map((user) => (
        <User user={user} key={user.id} />
      ))}
    </div>
  )
}
```

### nextId

```jsx
const nextId = useRef(4)   // .current 초기값 = 4

// 수정
nextId.current += 1

// 조회
nextId.current
```

`useRef(초기값)` → 그 값이 `.current`의 기본값

---

## 13. 배열에 항목 추가하기

### 역할 분담

| 컴포넌트 | 역할 |
|----------|------|
| **App** | `inputs`, `users` state / `onChange` / `onCreate` / `nextId` |
| **CreateUser** | UI만 — props로 값·함수 받음 (상태는 부모) |
| **UserList** | `users` props로 목록 출력 |

### CreateUser.js

```jsx
function CreateUser({ username, email, onChange, onCreate }) {
  return (
    <div>
      <input name="username" placeholder="계정명" onChange={onChange} value={username} />
      <input name="email" placeholder="이메일" onChange={onChange} value={email} />
      <button onClick={onCreate}>등록</button>
    </div>
  )
}
```

### App — 완성 흐름

```jsx
const [inputs, setInputs] = useState({ username: '', email: '' })
const { username, email } = inputs

const onChange = (e) => {
  const { name, value } = e.target
  setInputs({ ...inputs, [name]: value })
}

const [users, setUsers] = useState([/* 기존 3명 */])
const nextId = useRef(4)

const onCreate = () => {
  const user = {
    id: nextId.current,
    username,
    email,
  }

  // 방법 1
  setUsers([...users, user])
  // 방법 2
  // setUsers(users.concat(user))

  setInputs({ username: '', email: '' })
  nextId.current += 1
}

return (
  <>
    <CreateUser
      username={username}
      email={email}
      onChange={onChange}
      onCreate={onCreate}
    />
    <UserList users={users} />
  </>
)
```

### 불변성 (중요)

| 하면 안 됨 | 해야 함 |
|------------|---------|
| `users.push(user)` | `[...users, user]` |
| `splice`, `sort`로 원본 수정 | `concat` / 복사 후 변경 |

배열·객체 모두 **새 배열/새 객체**로 바꿔야 리렌더링이 된다.

---

## 학생 체크리스트

- [ ] 초기화 후 `nameInput.current.focus()` 동작
- [ ] `map` + `key={user.id}`
- [ ] `users`는 App state, UserList는 props만
- [ ] `nextId`는 `useRef` (화면 안 바뀌는 id 카운터)
- [ ] 등록 시 spread 또는 `concat`으로 추가
- [ ] CreateUser의 `name`이 `username` / `email`과 일치

---

## 이전 코드와의 관계

앞에서 올린 App 코드(`setUsers([...users, user])` + `nextId`)는 **13절 최종본과 동일**합니다.  
안 되면 App이 아니라 **CreateUser `name`/`onCreate` 연결** 또는 **UserList `map`**을 확인하면 됩니다.
