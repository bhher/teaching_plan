## 9차시 – Context & Custom Hook

### 0. 학습 목표

- Prop Drilling 문제를 이해한다.
- Context API로 전역 수준의 상태를 공유할 수 있다.
- 반복되는 로직을 Custom Hook으로 추출할 수 있다.

---

### 1. 이론 (슬라이드 흐름)

#### 1) Prop Drilling 문제

- 상위 → 하위 → 하위 → 하위…  
  계속해서 props를 넘기다 보면:
  - 중간 컴포넌트는 그 값을 **실제로 사용하지 않는데도** props만 전달하는 역할을 하게 됨.
- 이럴 때 유용한 도구가 **Context**.

#### 2) Context 개념

- “앱 전체 또는 특정 영역에서 **공유하고 싶은 값**을 제공하는 통로”
- 주요 API:
  - `createContext`
  - `Context.Provider`
  - `useContext`

#### 3) Custom Hook

- `use` 로 시작하는 **재사용 가능한 로직 묶음**
- 예: `useFetch`, `useInput`, `useToggle` 등
- 장점:
  - 로직 재사용
  - 컴포넌트 코드 가독성 향상

---

### 2. 실습 1 – 테마 Context 만들기

#### 2.1 목표

- 전체 앱에 Light / Dark 테마를 적용할 수 있는 Context 구성
- 버튼 한 번으로 테마 전환

---

### 3. 코드 스켈레톤 – Theme Context

#### 3.1 `ThemeContext.jsx`

```jsx
// src/ThemeContext.jsx
import { createContext, useContext, useState } from 'react';

const ThemeContext = createContext();

export function ThemeProvider({ children }) {
  const [theme, setTheme] = useState('light');

  const toggleTheme = () => {
    setTheme((prev) => (prev === 'light' ? 'dark' : 'light'));
  };

  const value = { theme, toggleTheme };

  return (
    <ThemeContext.Provider value={value}>
      {children}
    </ThemeContext.Provider>
  );
}

export function useTheme() {
  const context = useContext(ThemeContext);
  if (!context) {
    throw new Error('useTheme은 ThemeProvider 안에서만 사용해야 합니다.');
  }
  return context;
}
```

#### 3.2 `App.jsx` + ThemedLayout

```jsx
// src/App.jsx
import { ThemeProvider, useTheme } from './ThemeContext.jsx';

function ThemedLayout() {
  const { theme, toggleTheme } = useTheme();

  const styles = {
    background: theme === 'light' ? '#ffffff' : '#222222',
    color: theme === 'light' ? '#000000' : '#ffffff',
    minHeight: '100vh',
    padding: '20px',
  };

  return (
    <div style={styles}>
      <button onClick={toggleTheme}>테마 전환</button>
      <h1>9차시: Context & Custom Hook</h1>
      <p>현재 테마: {theme}</p>
      {/* 여기에 다른 컴포넌트들 배치 */}
    </div>
  );
}

function App() {
  return (
    <ThemeProvider>
      <ThemedLayout />
    </ThemeProvider>
  );
}

export default App;
```

---

### 4. 실습 2 – `useFetch` Custom Hook 만들기

#### 4.1 목표

- 8차시에서 썼던 API 호출 로직을 `useFetch`로 추출
- 여러 컴포넌트에서 같은 패턴을 재사용

#### 4.2 `useFetch.js` 스켈레톤

```jsx
// src/hooks/useFetch.js
import { useEffect, useState } from 'react';

export function useFetch(url) {
  const [data, setData] = useState(null);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState(null);

  useEffect(() => {
    if (!url) return;

    let isCancelled = false;

    async function fetchData() {
      try {
        setIsLoading(true);
        setError(null);
        const res = await fetch(url);
        if (!res.ok) throw new Error('네트워크 에러');
        const json = await res.json();
        if (!isCancelled) {
          setData(json);
        }
      } catch (e) {
        if (!isCancelled) {
          setError(e.message);
        }
      } finally {
        if (!isCancelled) {
          setIsLoading(false);
        }
      }
    }

    fetchData();

    return () => {
      isCancelled = true; // 언마운트 시 플래그
    };
  }, [url]);

  return { data, isLoading, error };
}
```

#### 4.3 `PostList`에서 `useFetch` 사용

```jsx
// src/PostList.jsx
import { useFetch } from './hooks/useFetch.js';

function PostList() {
  const { data, isLoading, error } = useFetch(
    'https://jsonplaceholder.typicode.com/posts'
  );

  if (isLoading) return <p>로딩 중...</p>;
  if (error) return <p>에러: {error}</p>;
  if (!data) return null;

  const posts = data.slice(0, 10);

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

---

### 5. 과제

1. **AuthContext 만들기**
   - `AuthContext`를 만들어 `isLoggedIn` 상태를 전역으로 관리
   - 헤더 컴포넌트에서:
     - 로그인 상태면 “로그아웃” 버튼
     - 아니면 “로그인” 버튼 표시

2. **`useToggle` Custom Hook 만들기**
   - 사용 예:

   ```jsx
   const [isOpen, toggleOpen] = useToggle(false);
   ```

   - `isOpen` 상태와 `toggleOpen` 함수를 제공
   - 이 Hook을 모달 열기/닫기, 아코디언 열기/닫기 등에 적용해 보기

> 다음 차시 예고:  
> “마지막 10차시에서는 지금까지 배운 모든 내용을 합쳐서  
> 작은 규모의 **종합 프로젝트**를 만들고, 폴더 구조와 리팩터링까지 경험해 봅니다.”




