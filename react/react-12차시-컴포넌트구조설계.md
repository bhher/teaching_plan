# 12차시. 컴포넌트 구조 설계

## 학습 목표
- UI 컴포넌트와 Container 컴포넌트를 구분할 수 있다
- 프로젝트 폴더 구조를 설계할 수 있다
- 게시판 컴포넌트를 구조화할 수 있다
- 재사용 가능한 컴포넌트를 설계할 수 있다

---

## 1. 컴포넌트 구조 설계의 중요성

### 1.1 왜 구조 설계가 중요한가?

**좋은 구조의 장점:**
- ✅ 코드 가독성 향상
- ✅ 유지보수 용이
- ✅ 재사용성 증가
- ✅ 협업 효율성 향상
- ✅ 테스트 용이

**나쁜 구조의 문제점:**
- ❌ 코드 찾기 어려움
- ❌ 수정 시 사이드 이펙트
- ❌ 재사용 불가
- ❌ 유지보수 어려움

### 1.2 컴포넌트 분류

**컴포넌트 종류:**
1. **UI 컴포넌트 (Presentational Component)**
   - 화면 표시에 집중
   - Props로 데이터 받음
   - 상태 없거나 최소한

2. **Container 컴포넌트 (Container Component)**
   - 로직 처리에 집중
   - 상태 관리
   - API 호출

---

## 2. UI 컴포넌트 vs Container 컴포넌트

### 2.1 UI 컴포넌트 (Presentational)

**특징:**
- 화면 표시에 집중
- Props로 데이터 받음
- 상태 없거나 최소한
- 재사용 가능

**예시:**
```jsx
// UI 컴포넌트
function Button({ text, onClick, disabled }) {
  return (
    <button 
      onClick={onClick}
      disabled={disabled}
      style={{
        padding: '10px 20px',
        backgroundColor: disabled ? '#ccc' : '#007bff',
        color: 'white',
        border: 'none',
        borderRadius: '5px',
        cursor: disabled ? 'not-allowed' : 'pointer'
      }}
    >
      {text}
    </button>
  );
}

function Card({ title, content, author }) {
  return (
    <div style={{
      border: '1px solid #ddd',
      borderRadius: '8px',
      padding: '1rem',
      marginBottom: '1rem'
    }}>
      <h3>{title}</h3>
      <p>{content}</p>
      <small>작성자: {author}</small>
    </div>
  );
}
```

### 2.2 Container 컴포넌트

**특징:**
- 로직 처리에 집중
- 상태 관리
- API 호출
- UI 컴포넌트 조합

**예시:**
```jsx
// Container 컴포넌트
import { useState, useEffect } from 'react';
import Card from './Card';
import Button from './Button';

function PostList() {
  const [posts, setPosts] = useState([]);
  const [loading, setLoading] = useState(true);
  
  useEffect(() => {
    // API 호출
    fetchPosts().then(data => {
      setPosts(data);
      setLoading(false);
    });
  }, []);
  
  const handleDelete = (id) => {
    setPosts(posts.filter(post => post.id !== id));
  };
  
  if (loading) {
    return <p>로딩 중...</p>;
  }
  
  return (
    <div>
      {posts.map(post => (
        <Card
          key={post.id}
          title={post.title}
          content={post.content}
          author={post.author}
        />
      ))}
    </div>
  );
}
```

### 2.3 분리 예시

**분리 전 (나쁜 예):**
```jsx
function PostList() {
  const [posts, setPosts] = useState([]);
  
  return (
    <div>
      {posts.map(post => (
        <div style={{ border: '1px solid #ddd', padding: '1rem' }}>
          <h3>{post.title}</h3>
          <p>{post.content}</p>
          <button onClick={() => setPosts(...)}>삭제</button>
        </div>
      ))}
    </div>
  );
}
```

**분리 후 (좋은 예):**
```jsx
// UI 컴포넌트
function PostCard({ post, onDelete }) {
  return (
    <div style={{ border: '1px solid #ddd', padding: '1rem' }}>
      <h3>{post.title}</h3>
      <p>{post.content}</p>
      <button onClick={() => onDelete(post.id)}>삭제</button>
    </div>
  );
}

// Container 컴포넌트
function PostList() {
  const [posts, setPosts] = useState([]);
  
  const handleDelete = (id) => {
    setPosts(posts.filter(post => post.id !== id));
  };
  
  return (
    <div>
      {posts.map(post => (
        <PostCard 
          key={post.id} 
          post={post} 
          onDelete={handleDelete}
        />
      ))}
    </div>
  );
}
```

---

## 3. 폴더 구조 설계

### 3.1 기본 구조

**권장 구조:**
```
src/
├── components/          # 재사용 가능한 UI 컴포넌트
│   ├── Button/
│   │   ├── Button.jsx
│   │   └── Button.css
│   ├── Card/
│   │   ├── Card.jsx
│   │   └── Card.css
│   └── Input/
│       ├── Input.jsx
│       └── Input.css
│
├── containers/          # Container 컴포넌트
│   ├── PostList/
│   │   ├── PostList.jsx
│   │   └── PostList.css
│   └── UserProfile/
│       ├── UserProfile.jsx
│       └── UserProfile.css
│
├── pages/              # 페이지 컴포넌트
│   ├── Home.jsx
│   ├── About.jsx
│   └── Contact.jsx
│
├── hooks/              # Custom Hooks
│   ├── useAuth.js
│   └── useFetch.js
│
├── utils/              # 유틸리티 함수
│   ├── formatDate.js
│   └── validate.js
│
├── App.jsx
└── main.jsx
```

### 3.2 구조별 설명

**components/**
- 재사용 가능한 UI 컴포넌트
- Props로 데이터 받음
- 상태 없거나 최소한

**containers/**
- 로직이 있는 컴포넌트
- 상태 관리
- API 호출

**pages/**
- 전체 페이지 컴포넌트
- 라우팅과 연결

**hooks/**
- Custom Hooks
- 재사용 가능한 로직

**utils/**
- 순수 함수
- 유틸리티 함수

### 3.3 컴포넌트별 폴더 구조

**단일 파일:**
```
components/
  └── Button.jsx
```

**폴더 구조 (권장):**
```
components/
  └── Button/
      ├── Button.jsx
      ├── Button.css
      ├── Button.test.jsx
      └── index.js
```

**index.js 예시:**
```jsx
// components/Button/index.js
export { default } from './Button';
```

**사용:**
```jsx
import Button from './components/Button';
// 또는
import Button from './components/Button/Button';
```

---

## 4. 실습: 게시판 컴포넌트 구조화

### 실습 1: 기본 구조 설계

**요구사항:**
- 게시글 목록 표시
- 게시글 작성
- 게시글 삭제

**폴더 구조:**
```
src/
├── components/
│   ├── PostCard/
│   │   ├── PostCard.jsx
│   │   └── PostCard.css
│   └── PostForm/
│       ├── PostForm.jsx
│       └── PostForm.css
│
└── containers/
    └── PostBoard/
        ├── PostBoard.jsx
        └── PostBoard.css
```

**코드:**

**PostCard.jsx (UI 컴포넌트):**
```jsx
// components/PostCard/PostCard.jsx
function PostCard({ post, onDelete }) {
  return (
    <div className="post-card">
      <div className="post-card-header">
        <h3>{post.title}</h3>
        <button 
          onClick={() => onDelete(post.id)}
          className="delete-btn"
        >
          삭제
        </button>
      </div>
      <p className="post-content">{post.content}</p>
      <div className="post-meta">
        <span>작성자: {post.author}</span>
        <span>작성일: {post.date}</span>
      </div>
    </div>
  );
}

export default PostCard;
```

**PostForm.jsx (UI 컴포넌트):**
```jsx
// components/PostForm/PostForm.jsx
function PostForm({ onSubmit }) {
  const [formData, setFormData] = useState({
    title: '',
    content: '',
    author: ''
  });
  
  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };
  
  const handleSubmit = (e) => {
    e.preventDefault();
    if (formData.title.trim() && formData.content.trim()) {
      onSubmit(formData);
      setFormData({ title: '', content: '', author: '' });
    }
  };
  
  return (
    <form onSubmit={handleSubmit} className="post-form">
      <input
        name="title"
        value={formData.title}
        onChange={handleChange}
        placeholder="제목"
        className="form-input"
      />
      <textarea
        name="content"
        value={formData.content}
        onChange={handleChange}
        placeholder="내용"
        className="form-textarea"
      />
      <input
        name="author"
        value={formData.author}
        onChange={handleChange}
        placeholder="작성자"
        className="form-input"
      />
      <button type="submit" className="submit-btn">
        작성
      </button>
    </form>
  );
}

export default PostForm;
```

**PostBoard.jsx (Container 컴포넌트):**
```jsx
// containers/PostBoard/PostBoard.jsx
import { useState } from 'react';
import PostCard from '../../components/PostCard/PostCard';
import PostForm from '../../components/PostForm/PostForm';

function PostBoard() {
  const [posts, setPosts] = useState([
    {
      id: 1,
      title: '첫 번째 게시글',
      content: '내용입니다',
      author: '홍길동',
      date: '2024-01-15'
    }
  ]);
  
  const handleAddPost = (newPost) => {
    const post = {
      id: Date.now(),
      ...newPost,
      date: new Date().toISOString().split('T')[0]
    };
    setPosts([...posts, post]);
  };
  
  const handleDeletePost = (id) => {
    setPosts(posts.filter(post => post.id !== id));
  };
  
  return (
    <div className="post-board">
      <h1>게시판</h1>
      
      <PostForm onSubmit={handleAddPost} />
      
      <div className="post-list">
        {posts.length === 0 ? (
          <p>게시글이 없습니다</p>
        ) : (
          posts.map(post => (
            <PostCard
              key={post.id}
              post={post}
              onDelete={handleDeletePost}
            />
          ))
        )}
      </div>
    </div>
  );
}

export default PostBoard;
```

### 실습 2: 구조 개선

**개선 사항:**
- Custom Hook으로 로직 분리
- 유틸리티 함수 분리
- 스타일 컴포넌트 분리

**hooks/usePosts.js:**
```jsx
// hooks/usePosts.js
import { useState } from 'react';

function usePosts(initialPosts = []) {
  const [posts, setPosts] = useState(initialPosts);
  
  const addPost = (newPost) => {
    const post = {
      id: Date.now(),
      ...newPost,
      date: new Date().toISOString().split('T')[0]
    };
    setPosts([...posts, post]);
  };
  
  const deletePost = (id) => {
    setPosts(posts.filter(post => post.id !== id));
  };
  
  const updatePost = (id, updatedPost) => {
    setPosts(posts.map(post =>
      post.id === id ? { ...post, ...updatedPost } : post
    ));
  };
  
  return {
    posts,
    addPost,
    deletePost,
    updatePost
  };
}

export default usePosts;
```

**사용:**
```jsx
import usePosts from '../../hooks/usePosts';

function PostBoard() {
  const { posts, addPost, deletePost } = usePosts();
  
  return (
    <div>
      <PostForm onSubmit={addPost} />
      <PostList posts={posts} onDelete={deletePost} />
    </div>
  );
}
```

---

## 5. 컴포넌트 설계 원칙

### 원칙 1: 단일 책임 원칙

**✅ 좋은 예:**
```jsx
// 각 컴포넌트는 하나의 책임만
function Button({ text, onClick }) { }
function Input({ value, onChange }) { }
function Card({ title, content }) { }
```

**❌ 나쁜 예:**
```jsx
// 너무 많은 책임
function Everything({ ... }) {
  // 버튼, 입력, 카드 모두 처리
}
```

### 원칙 2: 재사용성

**✅ 좋은 예:**
```jsx
// 재사용 가능한 컴포넌트
function Button({ text, onClick, variant }) {
  return (
    <button 
      onClick={onClick}
      className={`btn btn-${variant}`}
    >
      {text}
    </button>
  );
}
```

### 원칙 3: Props 인터페이스 명확히

**✅ 좋은 예:**
```jsx
// 명확한 Props
function UserCard({ 
  user,      // 사용자 객체
  onEdit,    // 수정 함수
  onDelete   // 삭제 함수
}) { }
```

---

## 6. 실습 과제

### 과제 1: 쇼핑몰 구조 설계

**요구사항:**
- 상품 목록
- 상품 상세
- 장바구니
- 주문 폼

**폴더 구조 설계:**
```
components/
  ├── ProductCard/
  ├── CartItem/
  └── OrderForm/

containers/
  ├── ProductList/
  ├── ProductDetail/
  └── Cart/
```

### 과제 2: 블로그 구조 설계

**요구사항:**
- 포스트 목록
- 포스트 상세
- 댓글 시스템
- 관리자 페이지

---

## 7. 다음 단계 예고

다음 단계에서는 **React + API 연동**을 배웁니다:
- REST API 이해
- fetch / axios 사용
- 환경 변수 설정
- 로딩 & 에러 처리

---

## 요약

### 핵심 개념

1. **UI 컴포넌트**: 화면 표시에 집중
2. **Container 컴포넌트**: 로직 처리에 집중
3. **폴더 구조**: 체계적인 파일 관리
4. **재사용성**: 컴포넌트 재사용 가능하게 설계

### 폴더 구조

```
src/
├── components/    # UI 컴포넌트
├── containers/    # Container 컴포넌트
├── pages/         # 페이지 컴포넌트
├── hooks/         # Custom Hooks
└── utils/         # 유틸리티 함수
```

### 체크리스트

- [ ] UI / Container 분리 이해
- [ ] 폴더 구조 설계 가능
- [ ] 게시판 컴포넌트 구조화 완료
- [ ] 재사용 가능한 컴포넌트 설계 가능

---

**다음 단계에서 만나요! 🚀**

