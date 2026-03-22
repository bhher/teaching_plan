# 8차시. 리스트 렌더링

## 학습 목표
- map 함수를 사용하여 배열을 렌더링할 수 있다
- key의 개념과 중요성을 이해할 수 있다
- 배열 데이터를 화면에 출력할 수 있다
- 게시글 목록을 만들 수 있다

---

## 1. 리스트 렌더링이란?

### 1.1 리스트 렌더링의 정의

**리스트 렌더링**은 배열 데이터를 화면에 표시하는 것입니다.

**예시:**
- 게시글 목록
- 상품 목록
- 댓글 목록
- 메뉴 항목

### 1.2 리스트 렌더링이 필요한 이유

**하드코딩 (문제):**
```jsx
function TodoList() {
  return (
    <ul>
      <li>할일 1</li>
      <li>할일 2</li>
      <li>할일 3</li>
    </ul>
  );
}
```

**문제점:**
- ❌ 데이터가 많아지면 코드가 길어짐
- ❌ 동적으로 추가/삭제 불가
- ❌ 재사용 불가

**리스트 렌더링 (해결):**
```jsx
function TodoList() {
  const todos = ['할일 1', '할일 2', '할일 3'];
  
  return (
    <ul>
      {todos.map((todo, index) => (
        <li key={index}>{todo}</li>
      ))}
    </ul>
  );
}
```

**장점:**
- ✅ 데이터 기반으로 자동 생성
- ✅ 동적으로 추가/삭제 가능
- ✅ 재사용 가능

---

## 2. map 함수

### 2.1 map 함수란?

**map**은 배열의 각 요소를 변환하여 새로운 배열을 만드는 함수입니다.

**기본 문법:**
```javascript
배열.map((요소, 인덱스) => {
  return 변환된값;
});
```

**예시:**
```javascript
const numbers = [1, 2, 3];
const doubled = numbers.map(num => num * 2);
// 결과: [2, 4, 6]
```

### 2.2 React에서 map 사용

**기본 사용법:**
```jsx
function NumberList() {
  const numbers = [1, 2, 3, 4, 5];
  
  return (
    <ul>
      {numbers.map((number) => (
        <li key={number}>{number}</li>
      ))}
    </ul>
  );
}
```

**결과:**
```html
<ul>
  <li>1</li>
  <li>2</li>
  <li>3</li>
  <li>4</li>
  <li>5</li>
</ul>
```

### 2.3 map 함수 예시

**문자열 배열:**
```jsx
function NameList() {
  const names = ['홍길동', '김철수', '이영희'];
  
  return (
    <ul>
      {names.map((name, index) => (
        <li key={index}>{name}</li>
      ))}
    </ul>
  );
}
```

**객체 배열:**
```jsx
function UserList() {
  const users = [
    { id: 1, name: '홍길동', age: 25 },
    { id: 2, name: '김철수', age: 30 },
    { id: 3, name: '이영희', age: 28 }
  ];
  
  return (
    <ul>
      {users.map((user) => (
        <li key={user.id}>
          {user.name} ({user.age}세)
        </li>
      ))}
    </ul>
  );
}
```

---

## 3. key 속성

### 3.1 key란?

**key**는 React가 리스트의 각 항목을 식별하기 위해 사용하는 고유한 값입니다.

**왜 필요한가?**
- React가 어떤 항목이 변경되었는지 추적
- 효율적인 리렌더링
- 성능 최적화

### 3.2 key 사용법

**기본 사용:**
```jsx
{items.map((item) => (
  <li key={item.id}>{item.name}</li>
))}
```

**key 규칙:**
- ✅ 고유해야 함 (형제 요소 간)
- ✅ 안정적이어야 함 (변하지 않아야 함)
- ✅ 예측 가능해야 함

### 3.3 key 값 선택

**✅ 좋은 key:**
```jsx
// 고유한 ID 사용 (가장 좋음)
{users.map(user => (
  <UserCard key={user.id} user={user} />
))}

// 고유한 문자열
{items.map(item => (
  <Item key={item.name} item={item} />
))}
```

**⚠️ 주의: index를 key로 사용**
```jsx
// 나쁜 예 (항목 순서가 바뀔 수 있으면 문제)
{items.map((item, index) => (
  <Item key={index} item={item} />
))}
```

**index를 key로 사용해도 되는 경우:**
- 리스트가 정적이고 변경되지 않을 때
- 항목이 재정렬되거나 삭제되지 않을 때

**권장:** 가능하면 고유한 ID 사용!

### 3.4 key가 없을 때

**경고 메시지:**
```
Warning: Each child in a list should have a unique "key" prop.
```

**해결:** key 추가
```jsx
// ❌
{items.map(item => <Item item={item} />)}

// ✅
{items.map(item => <Item key={item.id} item={item} />)}
```

---

## 4. 리스트 렌더링 패턴

### 패턴 1: 간단한 리스트

```jsx
function SimpleList() {
  const items = ['사과', '바나나', '오렌지'];
  
  return (
    <ul>
      {items.map((item, index) => (
        <li key={index}>{item}</li>
      ))}
    </ul>
  );
}
```

### 패턴 2: 객체 배열

```jsx
function ProductList() {
  const products = [
    { id: 1, name: '노트북', price: 1000000 },
    { id: 2, name: '마우스', price: 30000 },
    { id: 3, name: '키보드', price: 50000 }
  ];
  
  return (
    <div>
      {products.map(product => (
        <div key={product.id} style={{
          border: '1px solid #ddd',
          padding: '1rem',
          marginBottom: '1rem'
        }}>
          <h3>{product.name}</h3>
          <p>가격: {product.price.toLocaleString()}원</p>
        </div>
      ))}
    </div>
  );
}
```

### 패턴 3: 조건부 렌더링과 함께

```jsx
function TodoList({ todos }) {
  return (
    <div>
      {todos.length === 0 ? (
        <p>할일이 없습니다</p>
      ) : (
        <ul>
          {todos.map(todo => (
            <li key={todo.id}>{todo.text}</li>
          ))}
        </ul>
      )}
    </div>
  );
}
```

### 패턴 4: 중첩된 리스트

```jsx
function CategoryList() {
  const categories = [
    {
      id: 1,
      name: '전자제품',
      items: ['노트북', '스마트폰', '태블릿']
    },
    {
      id: 2,
      name: '의류',
      items: ['셔츠', '바지', '신발']
    }
  ];
  
  return (
    <div>
      {categories.map(category => (
        <div key={category.id}>
          <h3>{category.name}</h3>
          <ul>
            {category.items.map((item, index) => (
              <li key={index}>{item}</li>
            ))}
          </ul>
        </div>
      ))}
    </div>
  );
}
```

---

## 5. 실습: 게시글 목록 출력

### 실습 1: 기본 게시글 목록

**요구사항:**
- 게시글 배열 데이터
- 제목, 작성자, 내용 표시
- 카드 형태로 출력

**코드:**
```jsx
function PostList() {
  const posts = [
    {
      id: 1,
      title: 'React 배우기',
      author: '홍길동',
      content: 'React는 정말 재미있습니다!',
      date: '2024-01-15'
    },
    {
      id: 2,
      title: 'Props 이해하기',
      author: '김철수',
      content: 'Props는 부모에서 자식으로 데이터를 전달합니다.',
      date: '2024-01-16'
    },
    {
      id: 3,
      title: 'State 사용법',
      author: '이영희',
      content: 'useState Hook을 사용하여 상태를 관리합니다.',
      date: '2024-01-17'
    }
  ];
  
  return (
    <div style={{ padding: '2rem' }}>
      <h1>게시글 목록</h1>
      
      {posts.map(post => (
        <div 
          key={post.id}
          style={{
            border: '1px solid #ddd',
            borderRadius: '8px',
            padding: '1.5rem',
            marginBottom: '1rem',
            boxShadow: '0 2px 4px rgba(0,0,0,0.1)'
          }}
        >
          <h2 style={{ marginTop: 0 }}>{post.title}</h2>
          <div style={{
            fontSize: '0.9rem',
            color: '#666',
            marginBottom: '0.5rem'
          }}>
            <span>작성자: {post.author}</span>
            <span style={{ marginLeft: '1rem' }}>작성일: {post.date}</span>
          </div>
          <p>{post.content}</p>
        </div>
      ))}
    </div>
  );
}

export default PostList;
```

### 실습 2: 게시글 카드 컴포넌트 분리

**요구사항:**
- PostCard 컴포넌트 분리
- Props로 데이터 전달
- 재사용 가능하게 만들기

**코드:**
```jsx
// PostCard.jsx
function PostCard({ post }) {
  return (
    <div style={{
      border: '1px solid #ddd',
      borderRadius: '8px',
      padding: '1.5rem',
      marginBottom: '1rem',
      boxShadow: '0 2px 4px rgba(0,0,0,0.1)',
      transition: 'transform 0.2s',
      cursor: 'pointer'
    }}
    onMouseEnter={(e) => {
      e.currentTarget.style.transform = 'translateY(-4px)';
      e.currentTarget.style.boxShadow = '0 4px 8px rgba(0,0,0,0.15)';
    }}
    onMouseLeave={(e) => {
      e.currentTarget.style.transform = 'translateY(0)';
      e.currentTarget.style.boxShadow = '0 2px 4px rgba(0,0,0,0.1)';
    }}
    >
      <h2 style={{ marginTop: 0, color: '#333' }}>{post.title}</h2>
      <div style={{
        fontSize: '0.85rem',
        color: '#666',
        marginBottom: '1rem',
        display: 'flex',
        gap: '1rem'
      }}>
        <span>👤 {post.author}</span>
        <span>📅 {post.date}</span>
      </div>
      <p style={{ color: '#555', lineHeight: '1.6' }}>{post.content}</p>
    </div>
  );
}

// PostList.jsx
import PostCard from './PostCard';

function PostList() {
  const posts = [
    {
      id: 1,
      title: 'React 배우기',
      author: '홍길동',
      content: 'React는 정말 재미있습니다!',
      date: '2024-01-15'
    },
    {
      id: 2,
      title: 'Props 이해하기',
      author: '김철수',
      content: 'Props는 부모에서 자식으로 데이터를 전달합니다.',
      date: '2024-01-16'
    },
    {
      id: 3,
      title: 'State 사용법',
      author: '이영희',
      content: 'useState Hook을 사용하여 상태를 관리합니다.',
      date: '2024-01-17'
    }
  ];
  
  return (
    <div style={{ padding: '2rem', maxWidth: '800px', margin: '0 auto' }}>
      <h1 style={{ marginBottom: '2rem' }}>게시글 목록</h1>
      
      {posts.length === 0 ? (
        <p>게시글이 없습니다</p>
      ) : (
        posts.map(post => (
          <PostCard key={post.id} post={post} />
        ))
      )}
    </div>
  );
}

export default PostList;
```

### 실습 3: 동적 게시글 목록

**요구사항:**
- State로 게시글 관리
- 게시글 추가 기능
- 게시글 삭제 기능

**코드:**
```jsx
import { useState } from 'react';

function DynamicPostList() {
  const [posts, setPosts] = useState([
    {
      id: 1,
      title: 'React 배우기',
      author: '홍길동',
      content: 'React는 정말 재미있습니다!',
      date: '2024-01-15'
    },
    {
      id: 2,
      title: 'Props 이해하기',
      author: '김철수',
      content: 'Props는 부모에서 자식으로 데이터를 전달합니다.',
      date: '2024-01-16'
    }
  ]);
  
  const [newPost, setNewPost] = useState({
    title: '',
    author: '',
    content: ''
  });
  
  const handleAddPost = (e) => {
    e.preventDefault();
    
    if (newPost.title.trim() && newPost.author.trim() && newPost.content.trim()) {
      const post = {
        id: posts.length + 1,
        ...newPost,
        date: new Date().toISOString().split('T')[0]
      };
      
      setPosts([...posts, post]);
      setNewPost({ title: '', author: '', content: '' });
    }
  };
  
  const handleDeletePost = (id) => {
    setPosts(posts.filter(post => post.id !== id));
  };
  
  return (
    <div style={{ padding: '2rem', maxWidth: '800px', margin: '0 auto' }}>
      <h1>게시글 목록</h1>
      
      {/* 게시글 추가 폼 */}
      <form onSubmit={handleAddPost} style={{
        marginBottom: '2rem',
        padding: '1rem',
        border: '1px solid #ddd',
        borderRadius: '8px'
      }}>
        <h3>새 게시글 작성</h3>
        <input
          type="text"
          placeholder="제목"
          value={newPost.title}
          onChange={(e) => setNewPost({ ...newPost, title: e.target.value })}
          style={{
            width: '100%',
            padding: '0.5rem',
            marginBottom: '0.5rem'
          }}
        />
        <input
          type="text"
          placeholder="작성자"
          value={newPost.author}
          onChange={(e) => setNewPost({ ...newPost, author: e.target.value })}
          style={{
            width: '100%',
            padding: '0.5rem',
            marginBottom: '0.5rem'
          }}
        />
        <textarea
          placeholder="내용"
          value={newPost.content}
          onChange={(e) => setNewPost({ ...newPost, content: e.target.value })}
          style={{
            width: '100%',
            padding: '0.5rem',
            marginBottom: '0.5rem',
            minHeight: '100px'
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
          게시글 추가
        </button>
      </form>
      
      {/* 게시글 목록 */}
      {posts.length === 0 ? (
        <p>게시글이 없습니다</p>
      ) : (
        posts.map(post => (
          <div 
            key={post.id}
            style={{
              border: '1px solid #ddd',
              borderRadius: '8px',
              padding: '1.5rem',
              marginBottom: '1rem',
              position: 'relative'
            }}
          >
            <button
              onClick={() => handleDeletePost(post.id)}
              style={{
                position: 'absolute',
                top: '1rem',
                right: '1rem',
                backgroundColor: '#ff4444',
                color: 'white',
                border: 'none',
                borderRadius: '5px',
                padding: '5px 10px',
                cursor: 'pointer'
              }}
            >
              삭제
            </button>
            
            <h2 style={{ marginTop: 0 }}>{post.title}</h2>
            <div style={{
              fontSize: '0.9rem',
              color: '#666',
              marginBottom: '0.5rem'
            }}>
              <span>작성자: {post.author}</span>
              <span style={{ marginLeft: '1rem' }}>작성일: {post.date}</span>
            </div>
            <p>{post.content}</p>
          </div>
        ))
      )}
    </div>
  );
}

export default DynamicPostList;
```

---

## 6. 실습 과제

### 과제 1: 상품 목록

**요구사항:**
- 상품 배열 데이터 (이름, 가격, 이미지 URL)
- 카드 형태로 표시
- 가격 포맷팅 (예: 15000 → "15,000원")

### 과제 2: 할일 목록

**요구사항:**
- 할일 배열 데이터
- 완료/미완료 상태 표시
- 완료된 할일은 취소선 표시

### 과제 3: 댓글 목록

**요구사항:**
- 댓글 배열 데이터 (작성자, 내용, 작성시간)
- 시간 포맷팅
- 댓글이 없으면 "댓글이 없습니다" 메시지

---

## 7. 자주 발생하는 오류

### 오류 1: `Each child in a list should have a unique "key" prop`

**원인:** key 속성 누락

**해결:**
```jsx
// ❌
{items.map(item => <Item item={item} />)}

// ✅
{items.map(item => <Item key={item.id} item={item} />)}
```

### 오류 2: `Cannot read property of undefined`

**원인:** 배열이 비어있거나 undefined

**해결:**
```jsx
// ✅
{items && items.length > 0 && items.map(...)}
// 또는
{items?.map(...)}
```

### 오류 3: key가 중복됨

**원인:** 같은 key 값 사용

**해결:**
```jsx
// ❌
{items.map((item, index) => (
  <Item key={index} item={item} />  // index는 중복될 수 있음
))}

// ✅
{items.map(item => (
  <Item key={item.id} item={item} />  // 고유한 ID 사용
))}
```

---

## 8. 리스트 렌더링 모범 사례

### 사례 1: 고유한 key 사용

**✅ 좋은 예:**
```jsx
{users.map(user => (
  <UserCard key={user.id} user={user} />
))}
```

### 사례 2: 빈 배열 처리

**✅ 좋은 예:**
```jsx
{items.length === 0 ? (
  <p>항목이 없습니다</p>
) : (
  items.map(item => <Item key={item.id} item={item} />)
)}
```

### 사례 3: 컴포넌트 분리

**✅ 좋은 예:**
```jsx
function PostList({ posts }) {
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

## 9. 다음 차시 예고

다음 차시에서는 **useEffect**를 배웁니다:
- 생명주기 개념
- useEffect 기본 구조
- 의존성 배열
- 마운트 시 데이터 출력하기

---

## 요약

### 핵심 개념

1. **리스트 렌더링**: 배열 데이터를 화면에 표시
2. **map 함수**: 배열을 변환하여 렌더링
3. **key 속성**: 리스트 항목 식별 (고유해야 함)
4. **컴포넌트 분리**: 재사용 가능한 리스트 항목 컴포넌트

### 필수 문법

```jsx
// 기본 리스트 렌더링
{배열.map((요소, 인덱스) => (
  <컴포넌트 key={요소.id} prop={요소} />
))}

// 조건부 리스트
{배열.length > 0 && 배열.map(...)}
```

### 체크리스트

- [ ] map 함수 사용법 이해
- [ ] key 속성의 중요성 이해
- [ ] 배열 데이터 렌더링 가능
- [ ] 게시글 목록 만들기 완료
- [ ] 동적 리스트 관리 가능

---

**다음 차시에서 만나요! 🚀**





