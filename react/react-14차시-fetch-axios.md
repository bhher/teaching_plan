# 14차시. fetch / axios

## 학습 목표
- fetch API를 사용하여 API를 호출할 수 있다
- axios 라이브러리를 사용할 수 있다
- GET과 POST 요청을 할 수 있다
- async/await를 사용하여 비동기 처리를 할 수 있다
- 게시글 목록 API를 연동할 수 있다

---

## 1. 비동기 처리란?

### 1.1 동기 vs 비동기

**동기 (Synchronous):**
- 순차적으로 실행
- 이전 작업이 끝나야 다음 작업 시작
- 블로킹 발생

**비동기 (Asynchronous):**
- 동시에 여러 작업 실행
- 이전 작업 완료를 기다리지 않음
- 논블로킹

**예시:**
```javascript
// 동기
console.log('1');
console.log('2');
console.log('3');
// 결과: 1, 2, 3 순서대로

// 비동기
console.log('1');
setTimeout(() => console.log('2'), 1000);
console.log('3');
// 결과: 1, 3, (1초 후) 2
```

### 1.2 API 호출은 비동기

**이유:**
- 네트워크 요청은 시간이 걸림
- 기다리는 동안 다른 작업 가능
- 사용자 경험 향상

---

## 2. fetch API

### 2.1 fetch란?

**fetch**는 브라우저에 내장된 API 호출 함수입니다.

**특징:**
- ✅ 브라우저 내장 (별도 설치 불필요)
- ✅ Promise 기반
- ✅ 간단한 사용법

### 2.2 기본 사용법

**GET 요청:**
```javascript
fetch('https://api.example.com/posts')
  .then(response => response.json())
  .then(data => console.log(data))
  .catch(error => console.error('에러:', error));
```

**POST 요청:**
```javascript
fetch('https://api.example.com/posts', {
  method: 'POST',
  headers: {
    'Content-Type': 'application/json',
  },
  body: JSON.stringify({
    title: '제목',
    content: '내용'
  })
})
  .then(response => response.json())
  .then(data => console.log(data))
  .catch(error => console.error('에러:', error));
```

### 2.3 fetch 응답 처리

**응답 단계:**
1. `fetch()` 호출 → Promise 반환
2. `.then()`으로 응답 받기
3. `.json()`으로 JSON 파싱
4. 데이터 사용

**예시:**
```javascript
fetch('/api/posts')
  .then(response => {
    // 응답 객체 확인
    if (!response.ok) {
      throw new Error('네트워크 응답이 올바르지 않습니다');
    }
    return response.json(); // JSON 파싱
  })
  .then(data => {
    // 파싱된 데이터 사용
    console.log(data);
  })
  .catch(error => {
    // 에러 처리
    console.error('에러:', error);
  });
```

---

## 3. async/await

### 3.1 async/await란?

**async/await**는 Promise를 더 쉽게 사용하는 문법입니다.

**장점:**
- ✅ 코드가 읽기 쉬움
- ✅ 동기 코드처럼 작성 가능
- ✅ 에러 처리 용이

### 3.2 기본 사용법

**async 함수:**
```javascript
async function fetchData() {
  try {
    const response = await fetch('/api/posts');
    const data = await response.json();
    console.log(data);
  } catch (error) {
    console.error('에러:', error);
  }
}
```

**화살표 함수:**
```javascript
const fetchData = async () => {
  try {
    const response = await fetch('/api/posts');
    const data = await response.json();
    return data;
  } catch (error) {
    console.error('에러:', error);
  }
};
```

### 3.3 async/await 예시

**GET 요청:**
```javascript
async function getPosts() {
  try {
    const response = await fetch('/api/posts');
    
    if (!response.ok) {
      throw new Error('데이터를 가져오는데 실패했습니다');
    }
    
    const posts = await response.json();
    return posts;
  } catch (error) {
    console.error('에러:', error);
    return [];
  }
}
```

**POST 요청:**
```javascript
async function createPost(postData) {
  try {
    const response = await fetch('/api/posts', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(postData)
    });
    
    if (!response.ok) {
      throw new Error('게시글 생성에 실패했습니다');
    }
    
    const newPost = await response.json();
    return newPost;
  } catch (error) {
    console.error('에러:', error);
    throw error;
  }
}
```

---

## 4. axios 라이브러리

### 4.1 axios란?

**axios**는 HTTP 클라이언트 라이브러리입니다.

**특징:**
- ✅ fetch보다 더 많은 기능
- ✅ 자동 JSON 변환
- ✅ 요청/응답 인터셉터
- ✅ 에러 처리 용이

### 4.2 axios 설치

```bash
npm install axios
```

### 4.3 axios 사용법

**기본 사용:**
```javascript
import axios from 'axios';

// GET 요청
axios.get('/api/posts')
  .then(response => console.log(response.data))
  .catch(error => console.error(error));

// POST 요청
axios.post('/api/posts', {
  title: '제목',
  content: '내용'
})
  .then(response => console.log(response.data))
  .catch(error => console.error(error));
```

**async/await 사용:**
```javascript
import axios from 'axios';

async function getPosts() {
  try {
    const response = await axios.get('/api/posts');
    return response.data;
  } catch (error) {
    console.error('에러:', error);
    return [];
  }
}

async function createPost(postData) {
  try {
    const response = await axios.post('/api/posts', postData);
    return response.data;
  } catch (error) {
    console.error('에러:', error);
    throw error;
  }
}
```

### 4.4 axios vs fetch

| 항목 | fetch | axios |
|------|-------|-------|
| 설치 | 불필요 (내장) | 필요 |
| JSON 변환 | 수동 | 자동 |
| 에러 처리 | 수동 | 자동 |
| 요청 취소 | AbortController | CancelToken |
| 인터셉터 | 없음 | 있음 |

---

## 5. 실습: 게시글 목록 API 연동

### 실습 1: fetch로 API 호출

**요구사항:**
- 게시글 목록 가져오기
- 로딩 상태 표시
- 에러 처리

**코드:**
```jsx
import { useState, useEffect } from 'react';

function PostList() {
  const [posts, setPosts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  
  useEffect(() => {
    const fetchPosts = async () => {
      try {
        setLoading(true);
        setError(null);
        
        // 실제 API 호출 (예시: JSONPlaceholder)
        const response = await fetch('https://jsonplaceholder.typicode.com/posts');
        
        if (!response.ok) {
          throw new Error('데이터를 가져오는데 실패했습니다');
        }
        
        const data = await response.json();
        setPosts(data.slice(0, 10)); // 처음 10개만
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };
    
    fetchPosts();
  }, []);
  
  if (loading) {
    return <p>로딩 중...</p>;
  }
  
  if (error) {
    return <p style={{ color: 'red' }}>에러: {error}</p>;
  }
  
  return (
    <div style={{ padding: '2rem' }}>
      <h2>게시글 목록</h2>
      {posts.map(post => (
        <div 
          key={post.id}
          style={{
            border: '1px solid #ddd',
            borderRadius: '8px',
            padding: '1rem',
            marginBottom: '1rem'
          }}
        >
          <h3>{post.title}</h3>
          <p>{post.body}</p>
        </div>
      ))}
    </div>
  );
}

export default PostList;
```

### 실습 2: axios로 API 호출

**요구사항:**
- axios 설치 및 사용
- 게시글 목록 가져오기
- 게시글 생성

**코드:**
```jsx
import { useState, useEffect } from 'react';
import axios from 'axios';

function PostList() {
  const [posts, setPosts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [newPost, setNewPost] = useState({ title: '', body: '' });
  
  // 게시글 목록 가져오기
  useEffect(() => {
    const fetchPosts = async () => {
      try {
        setLoading(true);
        const response = await axios.get('https://jsonplaceholder.typicode.com/posts');
        setPosts(response.data.slice(0, 10));
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };
    
    fetchPosts();
  }, []);
  
  // 게시글 생성
  const handleSubmit = async (e) => {
    e.preventDefault();
    
    try {
      const response = await axios.post('https://jsonplaceholder.typicode.com/posts', {
        title: newPost.title,
        body: newPost.body,
        userId: 1
      });
      
      setPosts([response.data, ...posts]);
      setNewPost({ title: '', body: '' });
    } catch (err) {
      console.error('에러:', err);
    }
  };
  
  if (loading) {
    return <p>로딩 중...</p>;
  }
  
  if (error) {
    return <p style={{ color: 'red' }}>에러: {error}</p>;
  }
  
  return (
    <div style={{ padding: '2rem' }}>
      <h2>게시글 목록</h2>
      
      {/* 게시글 작성 폼 */}
      <form onSubmit={handleSubmit} style={{
        marginBottom: '2rem',
        padding: '1rem',
        border: '1px solid #ddd',
        borderRadius: '8px'
      }}>
        <input
          type="text"
          value={newPost.title}
          onChange={(e) => setNewPost({ ...newPost, title: e.target.value })}
          placeholder="제목"
          style={{
            width: '100%',
            padding: '0.5rem',
            marginBottom: '0.5rem'
          }}
        />
        <textarea
          value={newPost.body}
          onChange={(e) => setNewPost({ ...newPost, body: e.target.value })}
          placeholder="내용"
          style={{
            width: '100%',
            padding: '0.5rem',
            minHeight: '80px',
            marginBottom: '0.5rem'
          }}
        />
        <button type="submit" style={{
          padding: '0.5rem 1rem',
          backgroundColor: '#007bff',
          color: 'white',
          border: 'none',
          borderRadius: '5px',
          cursor: 'pointer'
        }}>
          작성
        </button>
      </form>
      
      {/* 게시글 목록 */}
      {posts.map(post => (
        <div 
          key={post.id}
          style={{
            border: '1px solid #ddd',
            borderRadius: '8px',
            padding: '1rem',
            marginBottom: '1rem'
          }}
        >
          <h3>{post.title}</h3>
          <p>{post.body}</p>
        </div>
      ))}
    </div>
  );
}

export default PostList;
```

### 실습 3: API 함수 분리

**요구사항:**
- API 호출 함수를 별도 파일로 분리
- 재사용 가능하게 만들기

**api/posts.js:**
```javascript
import axios from 'axios';

const API_URL = 'https://jsonplaceholder.typicode.com/posts';

export const getPosts = async () => {
  try {
    const response = await axios.get(API_URL);
    return response.data;
  } catch (error) {
    console.error('게시글 목록 가져오기 실패:', error);
    throw error;
  }
};

export const getPost = async (id) => {
  try {
    const response = await axios.get(`${API_URL}/${id}`);
    return response.data;
  } catch (error) {
    console.error('게시글 가져오기 실패:', error);
    throw error;
  }
};

export const createPost = async (postData) => {
  try {
    const response = await axios.post(API_URL, postData);
    return response.data;
  } catch (error) {
    console.error('게시글 생성 실패:', error);
    throw error;
  }
};

export const updatePost = async (id, postData) => {
  try {
    const response = await axios.put(`${API_URL}/${id}`, postData);
    return response.data;
  } catch (error) {
    console.error('게시글 수정 실패:', error);
    throw error;
  }
};

export const deletePost = async (id) => {
  try {
    await axios.delete(`${API_URL}/${id}`);
  } catch (error) {
    console.error('게시글 삭제 실패:', error);
    throw error;
  }
};
```

**사용:**
```jsx
import { useState, useEffect } from 'react';
import { getPosts, createPost } from './api/posts';

function PostList() {
  const [posts, setPosts] = useState([]);
  
  useEffect(() => {
    const loadPosts = async () => {
      const data = await getPosts();
      setPosts(data.slice(0, 10));
    };
    loadPosts();
  }, []);
  
  // ...
}
```

---

## 6. 실습 과제

### 과제 1: 사용자 목록 API 연동

**요구사항:**
- 사용자 목록 가져오기
- 사용자 상세 정보 조회
- fetch 또는 axios 사용

### 과제 2: CRUD 완성

**요구사항:**
- 게시글 생성 (Create)
- 게시글 조회 (Read)
- 게시글 수정 (Update)
- 게시글 삭제 (Delete)

---

## 7. 자주 발생하는 오류

### 오류 1: CORS 에러

**에러 메시지:**
```
Access to fetch at '...' from origin '...' has been blocked by CORS policy
```

**원인:** 다른 도메인에서 API 호출

**해결:**
- 백엔드에서 CORS 설정
- 프록시 서버 사용
- 개발 환경에서는 프록시 설정

### 오류 2: 네트워크 에러

**해결:**
```javascript
try {
  const response = await fetch('/api/posts');
  if (!response.ok) {
    throw new Error('네트워크 응답이 올바르지 않습니다');
  }
} catch (error) {
  console.error('네트워크 에러:', error);
}
```

---

## 8. 다음 차시 예고

다음 차시에서는 **환경 변수**를 배웁니다:
- .env 파일 사용
- API URL 분리
- 배포 환경 대비 설정

---

## 요약

### 핵심 개념

1. **fetch**: 브라우저 내장 API 호출 함수
2. **axios**: HTTP 클라이언트 라이브러리
3. **async/await**: 비동기 처리 문법
4. **Promise**: 비동기 작업의 결과

### 필수 문법

```javascript
// fetch
const response = await fetch('/api/posts');
const data = await response.json();

// axios
const response = await axios.get('/api/posts');
const data = response.data;
```

### 체크리스트

- [ ] fetch API 사용 가능
- [ ] axios 사용 가능
- [ ] async/await 이해
- [ ] GET 요청 가능
- [ ] POST 요청 가능
- [ ] 게시글 목록 API 연동 완료

---

**다음 차시에서 만나요! 🚀**





