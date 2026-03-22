# 11차시. 상태 끌어올리기 (Lifting State Up)

## 학습 목표
- 상태 공유 문제를 이해할 수 있다
- 상태 끌어올리기의 개념을 이해할 수 있다
- 부모 컴포넌트에서 상태를 관리할 수 있다
- 댓글 추가/삭제 기능을 만들 수 있다

---

## 1. 상태 공유 문제

### 1.1 문제 상황

**시나리오:** 두 개의 자식 컴포넌트가 같은 데이터를 공유해야 함

**문제 예시:**
```jsx
function App() {
  return (
    <div>
      <CounterA />  {/* 카운트: 5 */}
      <CounterB />  {/* 카운트: 3 */}
      {/* 두 카운터의 합을 표시하고 싶음 */}
    </div>
  );
}
```

**문제점:**
- ❌ 각 컴포넌트가 독립적인 상태를 가짐
- ❌ 부모 컴포넌트가 자식의 상태를 알 수 없음
- ❌ 자식 컴포넌트 간 상태 공유 불가

### 1.2 해결 방법: 상태 끌어올리기

**상태 끌어올리기 (Lifting State Up):**
- 공유해야 하는 상태를 가장 가까운 공통 부모로 이동
- Props로 자식에게 전달
- 자식에서 상태 변경 함수를 Props로 받아서 사용

**해결 예시:**
```jsx
function App() {
  const [countA, setCountA] = useState(0);
  const [countB, setCountB] = useState(0);
  
  return (
    <div>
      <CounterA count={countA} setCount={setCountA} />
      <CounterB count={countB} setCount={setCountB} />
      <p>합계: {countA + countB}</p>
    </div>
  );
}
```

---

## 2. 상태 끌어올리기 개념

### 2.1 기본 원리

**단계:**
1. 공유해야 하는 상태를 부모 컴포넌트로 이동
2. 상태를 Props로 자식에게 전달
3. 상태 변경 함수도 Props로 전달
4. 자식에서 Props로 받은 함수 호출

**다이어그램:**
```
부모 컴포넌트 (App)
  ├─ State: count
  ├─ Props 전달: count, setCount
  │
  ├─ 자식 A (CounterA)
  │   └─ Props: count, setCount
  │
  └─ 자식 B (CounterB)
      └─ Props: count, setCount
```

### 2.2 기본 예시

**부모 컴포넌트:**
```jsx
function App() {
  const [count, setCount] = useState(0);
  
  return (
    <div>
      <Counter count={count} setCount={setCount} />
      <Display count={count} />
    </div>
  );
}
```

**자식 컴포넌트:**
```jsx
function Counter({ count, setCount }) {
  return (
    <div>
      <button onClick={() => setCount(count + 1)}>+1</button>
      <p>카운트: {count}</p>
    </div>
  );
}

function Display({ count }) {
  return <p>현재 값: {count}</p>;
}
```

---

## 3. 실전 예제

### 예제 1: 온도 변환기

**요구사항:**
- 섭씨와 화씨 입력 필드
- 하나를 입력하면 다른 하나가 자동 변환

**코드:**
```jsx
import { useState } from 'react';

function TemperatureConverter() {
  const [celsius, setCelsius] = useState('');
  const [fahrenheit, setFahrenheit] = useState('');
  
  const handleCelsiusChange = (value) => {
    setCelsius(value);
    if (value === '') {
      setFahrenheit('');
    } else {
      setFahrenheit((parseFloat(value) * 9 / 5 + 32).toFixed(2));
    }
  };
  
  const handleFahrenheitChange = (value) => {
    setFahrenheit(value);
    if (value === '') {
      setCelsius('');
    } else {
      setCelsius(((parseFloat(value) - 32) * 5 / 9).toFixed(2));
    }
  };
  
  return (
    <div style={{ padding: '2rem' }}>
      <h2>온도 변환기</h2>
      <TemperatureInput
        scale="c"
        temperature={celsius}
        onTemperatureChange={handleCelsiusChange}
      />
      <TemperatureInput
        scale="f"
        temperature={fahrenheit}
        onTemperatureChange={handleFahrenheitChange}
      />
    </div>
  );
}

function TemperatureInput({ scale, temperature, onTemperatureChange }) {
  const scaleNames = {
    c: '섭씨',
    f: '화씨'
  };
  
  return (
    <div style={{ marginBottom: '1rem' }}>
      <label>
        {scaleNames[scale]}:
        <input
          type="number"
          value={temperature}
          onChange={(e) => onTemperatureChange(e.target.value)}
          style={{
            marginLeft: '0.5rem',
            padding: '0.5rem'
          }}
        />
      </label>
    </div>
  );
}

export default TemperatureConverter;
```

### 예제 2: 검색 필터

**요구사항:**
- 검색어 입력
- 필터링된 목록 표시

**코드:**
```jsx
import { useState } from 'react';

function SearchableList() {
  const [searchTerm, setSearchTerm] = useState('');
  const items = ['사과', '바나나', '오렌지', '포도', '딸기'];
  
  const filteredItems = items.filter(item =>
    item.toLowerCase().includes(searchTerm.toLowerCase())
  );
  
  return (
    <div style={{ padding: '2rem' }}>
      <SearchInput 
        searchTerm={searchTerm} 
        onSearchChange={setSearchTerm} 
      />
      <ItemList items={filteredItems} />
    </div>
  );
}

function SearchInput({ searchTerm, onSearchChange }) {
  return (
    <input
      type="text"
      value={searchTerm}
      onChange={(e) => onSearchChange(e.target.value)}
      placeholder="검색..."
      style={{
        width: '100%',
        padding: '0.5rem',
        marginBottom: '1rem'
      }}
    />
  );
}

function ItemList({ items }) {
  return (
    <ul>
      {items.map((item, index) => (
        <li key={index}>{item}</li>
      ))}
    </ul>
  );
}

export default SearchableList;
```

---

## 4. 실습: 댓글 추가/삭제

### 실습 1: 기본 댓글 기능

**요구사항:**
- 댓글 목록 표시
- 댓글 추가
- 댓글 삭제

**코드:**
```jsx
import { useState } from 'react';

function CommentApp() {
  const [comments, setComments] = useState([
    { id: 1, text: '첫 번째 댓글', author: '홍길동' },
    { id: 2, text: '두 번째 댓글', author: '김철수' }
  ]);
  
  const handleAddComment = (newComment) => {
    setComments([...comments, {
      id: comments.length + 1,
      ...newComment
    }]);
  };
  
  const handleDeleteComment = (id) => {
    setComments(comments.filter(comment => comment.id !== id));
  };
  
  return (
    <div style={{ padding: '2rem', maxWidth: '600px', margin: '0 auto' }}>
      <h2>댓글</h2>
      
      <CommentForm onAddComment={handleAddComment} />
      
      <CommentList 
        comments={comments} 
        onDeleteComment={handleDeleteComment} 
      />
    </div>
  );
}

function CommentForm({ onAddComment }) {
  const [text, setText] = useState('');
  const [author, setAuthor] = useState('');
  
  const handleSubmit = (e) => {
    e.preventDefault();
    
    if (text.trim() && author.trim()) {
      onAddComment({ text, author });
      setText('');
      setAuthor('');
    }
  };
  
  return (
    <form onSubmit={handleSubmit} style={{
      marginBottom: '2rem',
      padding: '1rem',
      border: '1px solid #ddd',
      borderRadius: '8px'
    }}>
      <div style={{ marginBottom: '1rem' }}>
        <input
          type="text"
          value={author}
          onChange={(e) => setAuthor(e.target.value)}
          placeholder="작성자"
          style={{
            width: '100%',
            padding: '0.5rem',
            marginBottom: '0.5rem'
          }}
        />
        <textarea
          value={text}
          onChange={(e) => setText(e.target.value)}
          placeholder="댓글을 입력하세요"
          style={{
            width: '100%',
            padding: '0.5rem',
            minHeight: '80px'
          }}
        />
      </div>
      <button type="submit" style={{
        padding: '0.5rem 1rem',
        backgroundColor: '#007bff',
        color: 'white',
        border: 'none',
        borderRadius: '5px',
        cursor: 'pointer'
      }}>
        댓글 작성
      </button>
    </form>
  );
}

function CommentList({ comments, onDeleteComment }) {
  if (comments.length === 0) {
    return <p>댓글이 없습니다</p>;
  }
  
  return (
    <div>
      {comments.map(comment => (
        <div 
          key={comment.id}
          style={{
            border: '1px solid #ddd',
            borderRadius: '8px',
            padding: '1rem',
            marginBottom: '1rem',
            position: 'relative'
          }}
        >
          <div style={{
            display: 'flex',
            justifyContent: 'space-between',
            alignItems: 'start',
            marginBottom: '0.5rem'
          }}>
            <strong>{comment.author}</strong>
            <button
              onClick={() => onDeleteComment(comment.id)}
              style={{
                backgroundColor: '#ff4444',
                color: 'white',
                border: 'none',
                borderRadius: '5px',
                padding: '5px 10px',
                cursor: 'pointer',
                fontSize: '0.875rem'
              }}
            >
              삭제
            </button>
          </div>
          <p style={{ margin: 0 }}>{comment.text}</p>
        </div>
      ))}
    </div>
  );
}

export default CommentApp;
```

### 실습 2: 댓글 수정 기능 추가

**요구사항:**
- 댓글 수정 기능 추가
- 수정 모드 토글

**코드:**
```jsx
import { useState } from 'react';

function CommentApp() {
  const [comments, setComments] = useState([
    { id: 1, text: '첫 번째 댓글', author: '홍길동' },
    { id: 2, text: '두 번째 댓글', author: '김철수' }
  ]);
  
  const handleAddComment = (newComment) => {
    setComments([...comments, {
      id: Date.now(),
      ...newComment
    }]);
  };
  
  const handleUpdateComment = (id, updatedText) => {
    setComments(comments.map(comment =>
      comment.id === id
        ? { ...comment, text: updatedText }
        : comment
    ));
  };
  
  const handleDeleteComment = (id) => {
    setComments(comments.filter(comment => comment.id !== id));
  };
  
  return (
    <div style={{ padding: '2rem', maxWidth: '600px', margin: '0 auto' }}>
      <h2>댓글</h2>
      
      <CommentForm onAddComment={handleAddComment} />
      
      <CommentList 
        comments={comments}
        onUpdateComment={handleUpdateComment}
        onDeleteComment={handleDeleteComment}
      />
    </div>
  );
}

function CommentItem({ comment, onUpdateComment, onDeleteComment }) {
  const [isEditing, setIsEditing] = useState(false);
  const [editText, setEditText] = useState(comment.text);
  
  const handleSave = () => {
    if (editText.trim()) {
      onUpdateComment(comment.id, editText);
      setIsEditing(false);
    }
  };
  
  const handleCancel = () => {
    setEditText(comment.text);
    setIsEditing(false);
  };
  
  return (
    <div style={{
      border: '1px solid #ddd',
      borderRadius: '8px',
      padding: '1rem',
      marginBottom: '1rem'
    }}>
      <div style={{
        display: 'flex',
        justifyContent: 'space-between',
        alignItems: 'start',
        marginBottom: '0.5rem'
      }}>
        <strong>{comment.author}</strong>
        <div>
          {isEditing ? (
            <>
              <button onClick={handleSave} style={{
                marginRight: '0.5rem',
                padding: '5px 10px',
                backgroundColor: '#007bff',
                color: 'white',
                border: 'none',
                borderRadius: '5px',
                cursor: 'pointer'
              }}>
                저장
              </button>
              <button onClick={handleCancel} style={{
                marginRight: '0.5rem',
                padding: '5px 10px',
                backgroundColor: '#666',
                color: 'white',
                border: 'none',
                borderRadius: '5px',
                cursor: 'pointer'
              }}>
                취소
              </button>
            </>
          ) : (
            <>
              <button onClick={() => setIsEditing(true)} style={{
                marginRight: '0.5rem',
                padding: '5px 10px',
                backgroundColor: '#007bff',
                color: 'white',
                border: 'none',
                borderRadius: '5px',
                cursor: 'pointer'
              }}>
                수정
              </button>
              <button onClick={() => onDeleteComment(comment.id)} style={{
                padding: '5px 10px',
                backgroundColor: '#ff4444',
                color: 'white',
                border: 'none',
                borderRadius: '5px',
                cursor: 'pointer'
              }}>
                삭제
              </button>
            </>
          )}
        </div>
      </div>
      
      {isEditing ? (
        <textarea
          value={editText}
          onChange={(e) => setEditText(e.target.value)}
          style={{
            width: '100%',
            padding: '0.5rem',
            minHeight: '60px'
          }}
        />
      ) : (
        <p style={{ margin: 0 }}>{comment.text}</p>
      )}
    </div>
  );
}

function CommentList({ comments, onUpdateComment, onDeleteComment }) {
  if (comments.length === 0) {
    return <p>댓글이 없습니다</p>;
  }
  
  return (
    <div>
      {comments.map(comment => (
        <CommentItem
          key={comment.id}
          comment={comment}
          onUpdateComment={onUpdateComment}
          onDeleteComment={onDeleteComment}
        />
      ))}
    </div>
  );
}

// CommentForm은 이전과 동일
```

---

## 5. 실습 과제

### 과제 1: 할일 목록 앱

**요구사항:**
- 할일 추가
- 할일 삭제
- 할일 완료 체크
- 완료된 할일 필터링

### 과제 2: 장바구니

**요구사항:**
- 상품 추가
- 수량 변경
- 상품 삭제
- 총 금액 계산

### 과제 3: 투표 앱

**요구사항:**
- 옵션 추가
- 투표하기
- 투표 결과 표시
- 실시간 업데이트

---

## 6. 상태 끌어올리기 모범 사례

### 사례 1: 최소 공통 부모 찾기

**✅ 좋은 예:**
```jsx
// 상태를 필요한 컴포넌트들의 최소 공통 부모에 위치
function App() {
  const [sharedState, setSharedState] = useState();
  return (
    <div>
      <ComponentA state={sharedState} setState={setSharedState} />
      <ComponentB state={sharedState} setState={setSharedState} />
    </div>
  );
}
```

### 사례 2: 명확한 함수명 사용

**✅ 좋은 예:**
```jsx
function App() {
  const handleAddComment = (comment) => { };
  const handleDeleteComment = (id) => { };
  
  return (
    <CommentList
      onAddComment={handleAddComment}
      onDeleteComment={handleDeleteComment}
    />
  );
}
```

---

## 7. 다음 차시 예고

다음 차시에서는 **컴포넌트 구조 설계**를 배웁니다:
- UI / Container 분리
- 폴더 구조 설계
- 게시판 컴포넌트 구조화

---

## 요약

### 핵심 개념

1. **상태 끌어올리기**: 공유 상태를 부모 컴포넌트로 이동
2. **Props로 전달**: 상태와 상태 변경 함수를 Props로 전달
3. **단방향 데이터 흐름**: 부모 → 자식으로만 데이터 흐름

### 필수 패턴

```jsx
// 부모에서 상태 관리
function Parent() {
  const [state, setState] = useState();
  
  return (
    <Child 
      state={state}
      setState={setState}
    />
  );
}

// 자식에서 사용
function Child({ state, setState }) {
  return (
    <button onClick={() => setState(newValue)}>
      변경
    </button>
  );
}
```

### 체크리스트

- [ ] 상태 공유 문제 이해
- [ ] 상태 끌어올리기 개념 이해
- [ ] 부모에서 상태 관리 가능
- [ ] 댓글 추가/삭제 기능 완료
- [ ] Props로 함수 전달 이해

---

**다음 차시에서 만나요! 🚀**





