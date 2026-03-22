# 16차시. 로딩 & 에러 처리

## 학습 목표
- 로딩 상태를 관리할 수 있다
- 에러 상태를 처리할 수 있다
- 로딩 스피너를 구현할 수 있다
- 사용자 친화적인 에러 메시지를 표시할 수 있다

---

## 1. 로딩 상태 관리

### 1.1 로딩 상태란?

**로딩 상태**는 데이터를 가져오는 동안의 상태입니다.

**필요한 이유:**
- 사용자에게 진행 상황 알림
- 빈 화면 대신 로딩 표시
- 사용자 경험 향상

### 1.2 로딩 상태 관리 패턴

**기본 패턴:**
```jsx
const [loading, setLoading] = useState(false);

const fetchData = async () => {
  setLoading(true);
  try {
    const data = await api.getData();
    // 데이터 처리
  } finally {
    setLoading(false);
  }
};
```

**조건부 렌더링:**
```jsx
if (loading) {
  return <LoadingSpinner />;
}

return <DataDisplay data={data} />;
```

---

## 2. 에러 처리

### 2.1 에러 상태 관리

**에러 상태 추가:**
```jsx
const [error, setError] = useState(null);

const fetchData = async () => {
  try {
    setError(null);
    const data = await api.getData();
    // 데이터 처리
  } catch (err) {
    setError(err.message);
  }
};
```

**에러 표시:**
```jsx
if (error) {
  return <ErrorMessage message={error} />;
}
```

### 2.2 에러 처리 패턴

**기본 패턴:**
```jsx
const [data, setData] = useState(null);
const [loading, setLoading] = useState(true);
const [error, setError] = useState(null);

useEffect(() => {
  const fetchData = async () => {
    try {
      setLoading(true);
      setError(null);
      const result = await api.getData();
      setData(result);
    } catch (err) {
      setError(err.message || '데이터를 가져오는데 실패했습니다');
    } finally {
      setLoading(false);
    }
  };
  
  fetchData();
}, []);
```

---

## 3. 로딩 스피너 구현

### 3.1 기본 로딩 스피너

**CSS 애니메이션:**
```jsx
function LoadingSpinner() {
  return (
    <div style={{
      display: 'flex',
      justifyContent: 'center',
      alignItems: 'center',
      height: '200px'
    }}>
      <div style={{
        width: '40px',
        height: '40px',
        border: '4px solid #f3f3f3',
        borderTop: '4px solid #007bff',
        borderRadius: '50%',
        animation: 'spin 1s linear infinite'
      }}></div>
      <style>{`
        @keyframes spin {
          0% { transform: rotate(0deg); }
          100% { transform: rotate(360deg); }
        }
      `}</style>
    </div>
  );
}
```

### 3.2 텍스트와 함께

```jsx
function LoadingSpinner({ message = '로딩 중...' }) {
  return (
    <div style={{
      display: 'flex',
      flexDirection: 'column',
      justifyContent: 'center',
      alignItems: 'center',
      height: '200px',
      gap: '1rem'
    }}>
      <div style={{
        width: '40px',
        height: '40px',
        border: '4px solid #f3f3f3',
        borderTop: '4px solid #007bff',
        borderRadius: '50%',
        animation: 'spin 1s linear infinite'
      }}></div>
      <p style={{ color: '#666' }}>{message}</p>
      <style>{`
        @keyframes spin {
          0% { transform: rotate(0deg); }
          100% { transform: rotate(360deg); }
        }
      `}</style>
    </div>
  );
}
```

### 3.3 스켈레톤 UI

```jsx
function SkeletonCard() {
  return (
    <div style={{
      border: '1px solid #ddd',
      borderRadius: '8px',
      padding: '1rem',
      marginBottom: '1rem'
    }}>
      <div style={{
        height: '20px',
        backgroundColor: '#f0f0f0',
        borderRadius: '4px',
        marginBottom: '0.5rem',
        animation: 'pulse 1.5s ease-in-out infinite'
      }}></div>
      <div style={{
        height: '16px',
        backgroundColor: '#f0f0f0',
        borderRadius: '4px',
        width: '60%',
        animation: 'pulse 1.5s ease-in-out infinite'
      }}></div>
      <style>{`
        @keyframes pulse {
          0%, 100% { opacity: 1; }
          50% { opacity: 0.5; }
        }
      `}</style>
    </div>
  );
}
```

---

## 4. 에러 메시지 컴포넌트

### 4.1 기본 에러 메시지

```jsx
function ErrorMessage({ message, onRetry }) {
  return (
    <div style={{
      padding: '2rem',
      textAlign: 'center',
      color: '#d32f2f'
    }}>
      <p style={{ fontSize: '1.2rem', marginBottom: '1rem' }}>
        ⚠️ {message}
      </p>
      {onRetry && (
        <button
          onClick={onRetry}
          style={{
            padding: '0.5rem 1rem',
            backgroundColor: '#007bff',
            color: 'white',
            border: 'none',
            borderRadius: '5px',
            cursor: 'pointer'
          }}
        >
          다시 시도
        </button>
      )}
    </div>
  );
}
```

### 4.2 다양한 에러 타입

```jsx
function ErrorDisplay({ error, onRetry }) {
  const getErrorMessage = (error) => {
    if (error.response) {
      // 서버 응답 에러
      switch (error.response.status) {
        case 404:
          return '요청한 리소스를 찾을 수 없습니다';
        case 500:
          return '서버 오류가 발생했습니다';
        default:
          return '요청 처리 중 오류가 발생했습니다';
      }
    } else if (error.request) {
      // 요청 전송 실패
      return '네트워크 연결을 확인해주세요';
    } else {
      // 기타 에러
      return error.message || '알 수 없는 오류가 발생했습니다';
    }
  };
  
  return (
    <div style={{
      padding: '2rem',
      textAlign: 'center',
      backgroundColor: '#ffebee',
      borderRadius: '8px',
      border: '1px solid #ef5350'
    }}>
      <p style={{ color: '#d32f2f', marginBottom: '1rem' }}>
        {getErrorMessage(error)}
      </p>
      {onRetry && (
        <button onClick={onRetry}>
          다시 시도
        </button>
      )}
    </div>
  );
}
```

---

## 5. 실습: 로딩 스피너 구현

### 실습 1: 기본 로딩 처리

**요구사항:**
- API 호출 시 로딩 표시
- 로딩 완료 후 데이터 표시

**코드:**
```jsx
import { useState, useEffect } from 'react';
import axios from 'axios';

function PostList() {
  const [posts, setPosts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  
  useEffect(() => {
    const fetchPosts = async () => {
      try {
        setLoading(true);
        setError(null);
        
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
  
  if (loading) {
    return (
      <div style={{
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        height: '400px'
      }}>
        <div style={{
          width: '50px',
          height: '50px',
          border: '5px solid #f3f3f3',
          borderTop: '5px solid #007bff',
          borderRadius: '50%',
          animation: 'spin 1s linear infinite'
        }}></div>
        <style>{`
          @keyframes spin {
            0% { transform: rotate(0deg); }
            100% { transform: rotate(360deg); }
          }
        `}</style>
      </div>
    );
  }
  
  if (error) {
    return (
      <div style={{
        padding: '2rem',
        textAlign: 'center',
        color: '#d32f2f'
      }}>
        <p>에러: {error}</p>
        <button onClick={() => window.location.reload()}>
          새로고침
        </button>
      </div>
    );
  }
  
  return (
    <div style={{ padding: '2rem' }}>
      <h2>게시글 목록</h2>
      {posts.map(post => (
        <div key={post.id} style={{
          border: '1px solid #ddd',
          borderRadius: '8px',
          padding: '1rem',
          marginBottom: '1rem'
        }}>
          <h3>{post.title}</h3>
          <p>{post.body}</p>
        </div>
      ))}
    </div>
  );
}

export default PostList;
```

### 실습 2: 재사용 가능한 컴포넌트

**LoadingSpinner.jsx:**
```jsx
function LoadingSpinner({ size = 40, color = '#007bff', message }) {
  return (
    <div style={{
      display: 'flex',
      flexDirection: 'column',
      justifyContent: 'center',
      alignItems: 'center',
      padding: '2rem',
      gap: '1rem'
    }}>
      <div style={{
        width: `${size}px`,
        height: `${size}px`,
        border: `4px solid #f3f3f3`,
        borderTop: `4px solid ${color}`,
        borderRadius: '50%',
        animation: 'spin 1s linear infinite'
      }}></div>
      {message && <p style={{ color: '#666' }}>{message}</p>}
      <style>{`
        @keyframes spin {
          0% { transform: rotate(0deg); }
          100% { transform: rotate(360deg); }
        }
      `}</style>
    </div>
  );
}

export default LoadingSpinner;
```

**ErrorMessage.jsx:**
```jsx
function ErrorMessage({ message, onRetry }) {
  return (
    <div style={{
      padding: '2rem',
      textAlign: 'center',
      backgroundColor: '#ffebee',
      borderRadius: '8px',
      border: '1px solid #ef5350'
    }}>
      <p style={{ color: '#d32f2f', marginBottom: '1rem', fontSize: '1.1rem' }}>
        ⚠️ {message}
      </p>
      {onRetry && (
        <button
          onClick={onRetry}
          style={{
            padding: '0.5rem 1rem',
            backgroundColor: '#007bff',
            color: 'white',
            border: 'none',
            borderRadius: '5px',
            cursor: 'pointer'
          }}
        >
          다시 시도
        </button>
      )}
    </div>
  );
}

export default ErrorMessage;
```

**사용:**
```jsx
import LoadingSpinner from './components/LoadingSpinner';
import ErrorMessage from './components/ErrorMessage';

function PostList() {
  const [posts, setPosts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  
  const fetchPosts = async () => {
    try {
      setLoading(true);
      setError(null);
      const response = await axios.get('/api/posts');
      setPosts(response.data);
    } catch (err) {
      setError('데이터를 가져오는데 실패했습니다');
    } finally {
      setLoading(false);
    }
  };
  
  useEffect(() => {
    fetchPosts();
  }, []);
  
  if (loading) {
    return <LoadingSpinner message="게시글을 불러오는 중..." />;
  }
  
  if (error) {
    return <ErrorMessage message={error} onRetry={fetchPosts} />;
  }
  
  return (
    <div>
      {posts.map(post => (
        <PostCard key={post.id} post={post} />
      ))}
    </div>
  );
}
```

---

## 6. 실습 과제

### 과제 1: 로딩 상태 관리

**요구사항:**
- API 호출 시 로딩 표시
- 로딩 스피너 구현
- 로딩 완료 후 데이터 표시

### 과제 2: 에러 처리

**요구사항:**
- 네트워크 에러 처리
- 서버 에러 처리
- 재시도 기능

---

## 7. 다음 단계 예고

다음 단계에서는 **라우팅 & 상태 관리**를 배웁니다:
- React Router
- URL 파라미터
- Context API
- 전역 상태 관리

---

## 요약

### 핵심 개념

1. **로딩 상태**: 데이터 가져오는 동안의 상태
2. **에러 처리**: 예외 상황 처리
3. **로딩 스피너**: 시각적 피드백
4. **에러 메시지**: 사용자 친화적 에러 표시

### 필수 패턴

```jsx
const [loading, setLoading] = useState(false);
const [error, setError] = useState(null);

const fetchData = async () => {
  try {
    setLoading(true);
    setError(null);
    const data = await api.getData();
    // 처리
  } catch (err) {
    setError(err.message);
  } finally {
    setLoading(false);
  }
};
```

### 체크리스트

- [ ] 로딩 상태 관리 가능
- [ ] 에러 상태 처리 가능
- [ ] 로딩 스피너 구현 완료
- [ ] 에러 메시지 표시 가능
- [ ] 재시도 기능 구현 가능

---

**다음 단계에서 만나요! 🚀**





