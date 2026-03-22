# 9차시. useEffect

## 학습 목표
- 생명주기(Lifecycle)의 개념을 이해할 수 있다
- useEffect의 기본 구조를 이해할 수 있다
- 의존성 배열의 역할을 이해할 수 있다
- useEffect를 활용하여 마운트 시 데이터를 출력할 수 있다

---

## 1. 생명주기(Lifecycle)란?

### 1.1 생명주기의 개념

**생명주기**는 컴포넌트가 생성되고, 업데이트되고, 제거되는 과정입니다.

**주요 단계:**
1. **마운트(Mount)**: 컴포넌트가 화면에 나타남
2. **업데이트(Update)**: 컴포넌트가 업데이트됨
3. **언마운트(Unmount)**: 컴포넌트가 화면에서 사라짐

**비유:**
- 마운트: 사람이 태어남
- 업데이트: 사람이 성장/변화함
- 언마운트: 사람이 죽음

### 1.2 생명주기가 필요한 이유

**언제 사용하나?**
- 컴포넌트가 화면에 나타날 때 데이터 가져오기
- 컴포넌트가 사라질 때 정리 작업
- 특정 값이 변경될 때 작업 수행

**예시:**
```jsx
// 컴포넌트가 나타날 때
// → API 호출하여 데이터 가져오기

// 컴포넌트가 사라질 때
// → 타이머 정리, 구독 해제

// 특정 값이 변경될 때
// → 검색어 변경 시 검색 실행
```

---

## 2. useEffect Hook

### 2.1 useEffect란?

**useEffect**는 함수형 컴포넌트에서 생명주기 기능을 사용할 수 있게 해주는 Hook입니다.

**기본 구조:**
```jsx
import { useEffect } from 'react';

useEffect(() => {
  // 실행할 코드
}, [의존성배열]);
```

### 2.2 useEffect 기본 사용법

**기본 예시:**
```jsx
import { useEffect } from 'react';

function Component() {
  useEffect(() => {
    console.log('컴포넌트가 렌더링되었습니다');
  });
  
  return <div>Hello</div>;
}
```

**동작:**
- 컴포넌트가 렌더링될 때마다 실행됨

---

## 3. 의존성 배열

### 3.1 의존성 배열이란?

**의존성 배열**은 useEffect가 언제 실행될지를 결정하는 배열입니다.

**세 가지 경우:**

#### 1. 의존성 배열 없음 (매번 실행)

```jsx
useEffect(() => {
  console.log('매번 실행');
});
```

**동작:**
- 컴포넌트가 렌더링될 때마다 실행
- ⚠️ 주의: 무한 루프 가능성

#### 2. 빈 의존성 배열 (한 번만 실행)

```jsx
useEffect(() => {
  console.log('한 번만 실행');
}, []);
```

**동작:**
- 컴포넌트가 마운트될 때 한 번만 실행
- ✅ 데이터 가져오기, 초기 설정에 사용

#### 3. 의존성 배열에 값 포함 (값 변경 시 실행)

```jsx
useEffect(() => {
  console.log('count가 변경될 때 실행');
}, [count]);
```

**동작:**
- `count` 값이 변경될 때만 실행
- ✅ 특정 값 변경에 반응

### 3.2 의존성 배열 예시

**한 번만 실행:**
```jsx
function Component() {
  useEffect(() => {
    console.log('컴포넌트가 마운트되었습니다');
    // API 호출 등 초기 작업
  }, []);
  
  return <div>Hello</div>;
}
```

**특정 값 변경 시 실행:**
```jsx
function Component() {
  const [count, setCount] = useState(0);
  const [name, setName] = useState('');
  
  useEffect(() => {
    console.log('count가 변경되었습니다:', count);
  }, [count]);  // count만 감시
  
  useEffect(() => {
    console.log('name이 변경되었습니다:', name);
  }, [name]);  // name만 감시
  
  return <div>...</div>;
}
```

**여러 값 감시:**
```jsx
useEffect(() => {
  console.log('count 또는 name이 변경되었습니다');
}, [count, name]);
```

---

## 4. useEffect 사용 예제

### 예제 1: 마운트 시 데이터 출력

```jsx
import { useState, useEffect } from 'react';

function DataDisplay() {
  const [data, setData] = useState(null);
  
  useEffect(() => {
    // 컴포넌트가 마운트될 때 실행
    console.log('데이터를 가져옵니다...');
    
    // 실제로는 API 호출
    setTimeout(() => {
      setData('데이터 로드 완료!');
    }, 1000);
  }, []);  // 빈 배열 = 한 번만 실행
  
  return (
    <div>
      {data ? <p>{data}</p> : <p>로딩 중...</p>}
    </div>
  );
}
```

### 예제 2: 타이머

```jsx
import { useState, useEffect } from 'react';

function Timer() {
  const [seconds, setSeconds] = useState(0);
  
  useEffect(() => {
    const interval = setInterval(() => {
      setSeconds(prev => prev + 1);
    }, 1000);
    
    // 정리 함수: 컴포넌트가 언마운트될 때 실행
    return () => {
      clearInterval(interval);
    };
  }, []);  // 한 번만 실행
  
  return <div>경과 시간: {seconds}초</div>;
}
```

### 예제 3: 검색어 변경 시 검색

```jsx
import { useState, useEffect } from 'react';

function Search() {
  const [searchTerm, setSearchTerm] = useState('');
  const [results, setResults] = useState([]);
  
  useEffect(() => {
    if (searchTerm.trim()) {
      console.log('검색어:', searchTerm);
      // 실제로는 API 호출
      setResults([`${searchTerm} 결과 1`, `${searchTerm} 결과 2`]);
    } else {
      setResults([]);
    }
  }, [searchTerm]);  // searchTerm 변경 시 실행
  
  return (
    <div>
      <input
        value={searchTerm}
        onChange={(e) => setSearchTerm(e.target.value)}
        placeholder="검색..."
      />
      <ul>
        {results.map((result, index) => (
          <li key={index}>{result}</li>
        ))}
      </ul>
    </div>
  );
}
```

### 예제 4: 문서 제목 변경

```jsx
import { useEffect } from 'react';

function Page({ title }) {
  useEffect(() => {
    document.title = title;
  }, [title]);  // title 변경 시 문서 제목 변경
  
  return <h1>{title}</h1>;
}
```

---

## 5. 정리 함수(Cleanup Function)

### 5.1 정리 함수란?

**정리 함수**는 컴포넌트가 언마운트되거나 의존성이 변경되기 전에 실행되는 함수입니다.

**사용 목적:**
- 타이머 정리
- 구독 해제
- 메모리 누수 방지

### 5.2 정리 함수 사용법

**기본 구조:**
```jsx
useEffect(() => {
  // 실행할 코드
  
  return () => {
    // 정리 코드
  };
}, [의존성]);
```

**예시: 타이머 정리**
```jsx
useEffect(() => {
  const timer = setInterval(() => {
    console.log('타이머 실행');
  }, 1000);
  
  return () => {
    clearInterval(timer);  // 타이머 정리
  };
}, []);
```

**예시: 이벤트 리스너 제거**
```jsx
useEffect(() => {
  const handleResize = () => {
    console.log('화면 크기 변경');
  };
  
  window.addEventListener('resize', handleResize);
  
  return () => {
    window.removeEventListener('resize', handleResize);  // 리스너 제거
  };
}, []);
```

---

## 6. 실습: 마운트 시 데이터 출력

### 실습 1: 기본 데이터 로드

**요구사항:**
- 컴포넌트 마운트 시 데이터 가져오기
- 로딩 상태 표시

**코드:**
```jsx
import { useState, useEffect } from 'react';

function UserList() {
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(true);
  
  useEffect(() => {
    // 컴포넌트가 마운트될 때 실행
    console.log('사용자 데이터를 가져옵니다...');
    
    // 실제로는 API 호출
    setTimeout(() => {
      setUsers([
        { id: 1, name: '홍길동', email: 'hong@example.com' },
        { id: 2, name: '김철수', email: 'kim@example.com' },
        { id: 3, name: '이영희', email: 'lee@example.com' }
      ]);
      setLoading(false);
    }, 2000);  // 2초 후 데이터 로드
  }, []);  // 빈 배열 = 한 번만 실행
  
  if (loading) {
    return <p>로딩 중...</p>;
  }
  
  return (
    <div>
      <h2>사용자 목록</h2>
      <ul>
        {users.map(user => (
          <li key={user.id}>
            {user.name} - {user.email}
          </li>
        ))}
      </ul>
    </div>
  );
}

export default UserList;
```

### 실습 2: API 호출 시뮬레이션

**요구사항:**
- 게시글 목록 가져오기
- 로딩 및 에러 상태 처리

**코드:**
```jsx
import { useState, useEffect } from 'react';

function PostList() {
  const [posts, setPosts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  
  useEffect(() => {
    // API 호출 시뮬레이션
    const fetchPosts = async () => {
      try {
        setLoading(true);
        
        // 실제로는: const response = await fetch('/api/posts');
        await new Promise(resolve => setTimeout(resolve, 1500));
        
        const data = [
          {
            id: 1,
            title: 'React 배우기',
            author: '홍길동',
            content: 'React는 정말 재미있습니다!'
          },
          {
            id: 2,
            title: 'useEffect 이해하기',
            author: '김철수',
            content: 'useEffect는 생명주기를 관리합니다.'
          }
        ];
        
        setPosts(data);
        setError(null);
      } catch (err) {
        setError('데이터를 가져오는데 실패했습니다.');
      } finally {
        setLoading(false);
      }
    };
    
    fetchPosts();
  }, []);  // 마운트 시 한 번만 실행
  
  if (loading) {
    return (
      <div style={{ textAlign: 'center', padding: '2rem' }}>
        <p>로딩 중...</p>
      </div>
    );
  }
  
  if (error) {
    return (
      <div style={{ textAlign: 'center', padding: '2rem', color: 'red' }}>
        <p>{error}</p>
      </div>
    );
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
          <p>작성자: {post.author}</p>
          <p>{post.content}</p>
        </div>
      ))}
    </div>
  );
}

export default PostList;
```

### 실습 3: 타이머와 정리 함수

**요구사항:**
- 초시계 컴포넌트
- 컴포넌트 언마운트 시 타이머 정리

**코드:**
```jsx
import { useState, useEffect } from 'react';

function Clock() {
  const [time, setTime] = useState(new Date());
  
  useEffect(() => {
    // 타이머 설정
    const timer = setInterval(() => {
      setTime(new Date());
    }, 1000);
    
    // 정리 함수: 컴포넌트가 언마운트될 때 실행
    return () => {
      clearInterval(timer);
      console.log('타이머가 정리되었습니다');
    };
  }, []);  // 한 번만 실행
  
  return (
    <div style={{
      textAlign: 'center',
      padding: '2rem',
      fontSize: '2rem'
    }}>
      <h2>현재 시간</h2>
      <p>{time.toLocaleTimeString()}</p>
    </div>
  );
}

// 사용 예시
function App() {
  const [showClock, setShowClock] = useState(true);
  
  return (
    <div>
      <button onClick={() => setShowClock(!showClock)}>
        {showClock ? '시계 숨기기' : '시계 보기'}
      </button>
      {showClock && <Clock />}
    </div>
  );
}

export default App;
```

---

## 7. 실습 과제

### 과제 1: 뷰 카운터

**요구사항:**
- 컴포넌트가 마운트될 때 조회수 증가
- localStorage에 저장
- 페이지 새로고침해도 유지

### 과제 2: 검색 기능

**요구사항:**
- 검색어 입력 시 자동 검색
- 디바운싱 적용 (입력 멈춘 후 500ms 후 검색)
- 검색 결과 표시

### 과제 3: 윈도우 크기 감지

**요구사항:**
- 윈도우 크기 변경 감지
- 화면 크기에 따라 다른 메시지 표시
- 컴포넌트 언마운트 시 이벤트 리스너 제거

---

## 8. 자주 발생하는 오류

### 오류 1: 무한 루프

**원인:** 의존성 배열 없이 상태 변경

**해결:**
```jsx
// ❌ 무한 루프
useEffect(() => {
  setCount(count + 1);  // 매번 실행되어 무한 루프
});

// ✅ 의존성 배열 추가
useEffect(() => {
  // 초기 설정만
}, []);
```

### 오류 2: 의존성 배열 경고

**경고:**
```
React Hook useEffect has a missing dependency
```

**해결:**
```jsx
// ❌
useEffect(() => {
  console.log(count);
}, []);  // count를 사용하는데 의존성 배열에 없음

// ✅
useEffect(() => {
  console.log(count);
}, [count]);  // count를 의존성 배열에 추가
```

### 오류 3: 정리 함수 누락

**문제:** 메모리 누수 가능성

**해결:**
```jsx
// ✅ 항상 정리 함수 제공
useEffect(() => {
  const timer = setInterval(() => {}, 1000);
  return () => clearInterval(timer);
}, []);
```

---

## 9. useEffect 모범 사례

### 사례 1: 관련 로직 분리

**✅ 좋은 예:**
```jsx
// 각각의 useEffect로 분리
useEffect(() => {
  // 타이머 설정
}, []);

useEffect(() => {
  // 검색 실행
}, [searchTerm]);
```

**❌ 나쁜 예:**
```jsx
// 하나의 useEffect에 모든 로직
useEffect(() => {
  // 타이머 설정
  // 검색 실행
  // 기타 등등
}, []);
```

### 사례 2: 의존성 배열 정확히 작성

**✅ 좋은 예:**
```jsx
useEffect(() => {
  fetchData(userId);
}, [userId]);  // 사용하는 값 모두 포함
```

### 사례 3: 정리 함수 사용

**✅ 좋은 예:**
```jsx
useEffect(() => {
  const subscription = subscribe();
  return () => subscription.unsubscribe();
}, []);
```

---

## 10. 다음 차시 예고

다음 차시에서는 **폼 처리**를 배웁니다:
- Controlled Component
- 입력값 상태 관리
- Submit 처리
- 회원가입 폼 만들기

---

## 요약

### 핵심 개념

1. **생명주기**: 컴포넌트의 생성, 업데이트, 제거 과정
2. **useEffect**: 생명주기 기능을 사용하는 Hook
3. **의존성 배열**: useEffect 실행 시점 제어
4. **정리 함수**: 언마운트 시 정리 작업

### 필수 문법

```jsx
// 기본 사용
useEffect(() => {
  // 실행할 코드
}, [의존성]);

// 정리 함수
useEffect(() => {
  // 실행할 코드
  return () => {
    // 정리 코드
  };
}, [의존성]);
```

### 의존성 배열 종류

- `[]`: 한 번만 실행 (마운트 시)
- `[값]`: 값 변경 시 실행
- 없음: 매번 실행 (주의 필요)

### 체크리스트

- [ ] 생명주기 개념 이해
- [ ] useEffect 기본 구조 이해
- [ ] 의존성 배열의 역할 이해
- [ ] 마운트 시 데이터 출력 완료
- [ ] 정리 함수 사용법 이해

---

**다음 차시에서 만나요! 🚀**





