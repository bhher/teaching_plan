## 8차시 – `useEffect` & 외부 API 연동

### 0. 학습 목표

- `useEffect`를 사용해 **마운트 시점**에 코드를 실행할 수 있다.
- fetch 또는 axios로 API를 호출하고, 로딩/에러/데이터 상태를 화면에 표현할 수 있다.

---

### 1. 이론 (슬라이드 흐름)

#### 1) 컴포넌트 생명주기 개념

- 마운트(mount): 처음 화면에 나타나는 순간
- 업데이트(update): props/state가 바뀌어 다시 렌더링될 때
- 언마운트(unmount): 화면에서 사라질 때

#### 2) `useEffect` 기본 구조

```jsx
useEffect(() => {
  // 이 안의 코드는 컴포넌트가 렌더링된 후 실행된다.
}, []);
```

- 두 번째 인자로 **의존성 배열**을 받는다.
  - `[]` : 마운트 시 1번만 실행
  - `[value]` : value가 바뀔 때마다 실행

#### 3) API 호출 패턴

- 로딩/성공/실패 상태를 나누어 관리

```jsx
const [data, setData] = useState(null);
const [isLoading, setIsLoading] = useState(false);
const [error, setError] = useState(null);
```

---

### 2. 실습 – 게시글 목록 API 연동

#### 2.1 사용 API

- JSONPlaceholder: `https://jsonplaceholder.typicode.com/posts`
  - 무료 테스트용 API
  - 게시글 리스트 데이터를 JSON으로 제공

#### 2.2 실습 목표

- `PostList` 컴포넌트에서 마운트 시점에 API 호출
- 로딩 중에는 “로딩 중…”
- 에러 발생 시 에러 메시지 표시
- 성공 시 게시글 제목 목록 출력 (10개 정도만)

---

### 3. 코드 스켈레톤

#### 3.1 `PostList.jsx`

```jsx
// src/PostList.jsx
import { useEffect, useState } from 'react';

function PostList() {
  const [posts, setPosts] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState(null);

  useEffect(() => {
    async function fetchPosts() {
      try {
        setIsLoading(true);
        setError(null);

        const res = await fetch('https://jsonplaceholder.typicode.com/posts');
        if (!res.ok) {
          throw new Error('네트워크 에러가 발생했습니다.');
        }

        const data = await res.json();
        setPosts(data.slice(0, 10)); // 10개만 사용
      } catch (e) {
        setError(e.message);
      } finally {
        setIsLoading(false);
      }
    }

    fetchPosts();
  }, []); // 마운트 시 1번 실행

  if (isLoading) {
    return <p>로딩 중...</p>;
  }

  if (error) {
    return <p>에러 발생: {error}</p>;
  }

  return (
    <div>
      <h2>게시글 목록</h2>
      <ul>
        {posts.map((post) => (
          <li key={post.id}>{post.title}</li>
        ))}
      </ul>
    </div>
  );
}

export default PostList;
```

#### 3.2 `App.jsx`

```jsx
// src/App.jsx
import PostList from './PostList.jsx';

function App() {
  return (
    <div>
      <h1>8차시: useEffect & API 연동</h1>
      <PostList />
    </div>
  );
}

export default App;
```

---

### 4. 과제

1. **게시글 상세 표시**
   - 제목을 클릭하면, 아래에 해당 게시글의 `body` 내용을 보여주기
   - 힌트: 선택된 게시글 ID를 state로 관리 (`selectedId`)

2. **다시 불러오기 버튼**
   - “다시 불러오기” 버튼을 만들어서, 클릭 시 `fetchPosts` 다시 실행
   - 버튼 비활성화 조건:
     - 로딩 중일 때는 버튼을 `disabled` 처리

> 다음 차시 예고:  
> “다음 시간에는 전역 상태를 관리할 수 있는 **Context**와,  
> 중복되는 로직을 깔끔하게 정리하는 **Custom Hook**을 만들어 봅니다.”




