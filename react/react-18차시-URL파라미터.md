# 18차시. URL 파라미터

## 학습 목표
- useParams Hook을 사용할 수 있다
- 동적 라우팅을 구현할 수 있다
- URL 파라미터로 데이터를 전달할 수 있다
- 게시글 상세보기 페이지를 만들 수 있다

---

## 1. URL 파라미터란?

### 1.1 URL 파라미터의 개념

**URL 파라미터**는 URL에 포함된 변수 값입니다.

**예시:**
```
/posts/1        → ID가 1인 게시글
/posts/2        → ID가 2인 게시글
/users/123      → ID가 123인 사용자
```

**장점:**
- ✅ URL로 데이터 전달
- ✅ 북마크 가능
- ✅ 공유 가능

### 1.2 동적 라우팅

**정적 라우팅:**
```jsx
<Route path="/posts/1" element={<PostDetail />} />
<Route path="/posts/2" element={<PostDetail />} />
```

**동적 라우팅:**
```jsx
<Route path="/posts/:id" element={<PostDetail />} />
```

---

## 2. useParams Hook

### 2.1 useParams 사용법

**기본 사용:**
```jsx
import { useParams } from 'react-router-dom';

function PostDetail() {
  const { id } = useParams();
  
  return <div>게시글 ID: {id}</div>;
}
```

**라우트 설정:**
```jsx
<Route path="/posts/:id" element={<PostDetail />} />
```

### 2.2 여러 파라미터

**라우트 설정:**
```jsx
<Route path="/users/:userId/posts/:postId" element={<UserPost />} />
```

**사용:**
```jsx
function UserPost() {
  const { userId, postId } = useParams();
  
  return (
    <div>
      사용자 ID: {userId}
      게시글 ID: {postId}
    </div>
  );
}
```

---

## 3. 실습: 게시글 상세보기

### 실습 1: 기본 상세 페이지

**요구사항:**
- 게시글 목록에서 클릭 시 상세 페이지 이동
- URL 파라미터로 ID 전달
- 상세 정보 표시

**PostList.jsx:**
```jsx
import { Link } from 'react-router-dom';

function PostList() {
  const [posts, setPosts] = useState([]);
  
  useEffect(() => {
    // 게시글 목록 가져오기
    fetchPosts().then(setPosts);
  }, []);
  
  return (
    <div>
      <h2>게시글 목록</h2>
      {posts.map(post => (
        <div key={post.id}>
          <Link to={`/posts/${post.id}`}>
            <h3>{post.title}</h3>
          </Link>
        </div>
      ))}
    </div>
  );
}
```

**PostDetail.jsx:**
```jsx
import { useParams } from 'react-router-dom';
import { useState, useEffect } from 'react';
import axios from 'axios';

function PostDetail() {
  const { id } = useParams();
  const [post, setPost] = useState(null);
  const [loading, setLoading] = useState(true);
  
  useEffect(() => {
    const fetchPost = async () => {
      try {
        const response = await axios.get(`https://jsonplaceholder.typicode.com/posts/${id}`);
        setPost(response.data);
      } catch (error) {
        console.error('에러:', error);
      } finally {
        setLoading(false);
      }
    };
    
    fetchPost();
  }, [id]);
  
  if (loading) {
    return <p>로딩 중...</p>;
  }
  
  if (!post) {
    return <p>게시글을 찾을 수 없습니다</p>;
  }
  
  return (
    <div style={{ padding: '2rem' }}>
      <h1>{post.title}</h1>
      <p>{post.body}</p>
    </div>
  );
}

export default PostDetail;
```

**App.jsx:**
```jsx
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import PostList from './pages/PostList';
import PostDetail from './pages/PostDetail';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/posts" element={<PostList />} />
        <Route path="/posts/:id" element={<PostDetail />} />
      </Routes>
    </BrowserRouter>
  );
}
```

---

## 4. 실습 과제

### 과제 1: 사용자 상세 페이지

**요구사항:**
- 사용자 목록에서 클릭 시 상세 페이지 이동
- 사용자 정보 표시

---

## 5. 다음 차시 예고

다음 차시에서는 **전역 상태 관리**를 배웁니다:
- Props drilling 문제
- Context API 개념
- Context API 사용법

---

## 요약

### 핵심 개념

1. **URL 파라미터**: URL에 포함된 변수 값
2. **동적 라우팅**: `:id` 형태로 파라미터 정의
3. **useParams**: URL 파라미터 가져오기

### 필수 문법

```jsx
// 라우트 설정
<Route path="/posts/:id" element={<PostDetail />} />

// 파라미터 사용
const { id } = useParams();
```

### 체크리스트

- [ ] URL 파라미터 개념 이해
- [ ] useParams 사용 가능
- [ ] 동적 라우팅 구현 가능
- [ ] 게시글 상세보기 완료

---

**다음 차시에서 만나요! 🚀**

