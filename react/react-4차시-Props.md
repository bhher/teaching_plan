# 4차시. Props

## 학습 목표
- Props의 개념을 이해할 수 있다
- 부모 컴포넌트에서 자식 컴포넌트로 데이터를 전달할 수 있다
- Props 구조 분해 할당을 사용할 수 있다
- Props를 활용하여 게시글 카드 컴포넌트를 만들 수 있다

---

## 1. Props란?

### 1.1 Props의 정의

**Props (Properties)**는 부모 컴포넌트에서 자식 컴포넌트로 데이터를 전달하는 방법입니다.

**비유:**
- 함수의 매개변수와 유사
- 부모가 자식에게 "선물"을 전달하는 것

**Props의 특징:**
- ✅ **읽기 전용**: 자식 컴포넌트에서 수정 불가
- ✅ **단방향 데이터 흐름**: 부모 → 자식만 가능
- ✅ **재사용성**: 같은 컴포넌트에 다른 데이터 전달 가능

### 1.2 Props가 필요한 이유

**Props 없이 컴포넌트 사용 (문제):**
```jsx
function Card() {
  return (
    <div className="card">
      <h3>제목</h3>
      <p>내용</p>
    </div>
  );
}

function App() {
  return (
    <div>
      <Card />  {/* 모두 같은 내용 */}
      <Card />  {/* 모두 같은 내용 */}
      <Card />  {/* 모두 같은 내용 */}
    </div>
  );
}
```

**Props 사용 (해결):**
```jsx
function Card({ title, content }) {
  return (
    <div className="card">
      <h3>{title}</h3>
      <p>{content}</p>
    </div>
  );
}

function App() {
  return (
    <div>
      <Card title="첫 번째 제목" content="첫 번째 내용" />
      <Card title="두 번째 제목" content="두 번째 내용" />
      <Card title="세 번째 제목" content="세 번째 내용" />
    </div>
  );
}
```

---

## 2. Props 기본 사용법

### 2.1 Props 전달하기

**부모 컴포넌트에서 Props 전달:**
```jsx
function App() {
  return (
    <div>
      <Welcome name="홍길동" />
      <Welcome name="김철수" />
    </div>
  );
}
```

**자식 컴포넌트에서 Props 받기:**
```jsx
function Welcome(props) {
  return <h1>안녕하세요, {props.name}님!</h1>;
}
```

**결과:**
```
안녕하세요, 홍길동님!
안녕하세요, 김철수님!
```

### 2.2 여러 Props 전달하기

```jsx
function UserCard(props) {
  return (
    <div>
      <h2>{props.name}</h2>
      <p>나이: {props.age}세</p>
      <p>이메일: {props.email}</p>
    </div>
  );
}

function App() {
  return (
    <div>
      <UserCard 
        name="홍길동" 
        age={25} 
        email="hong@example.com" 
      />
    </div>
  );
}
```

**주의사항:**
- 문자열은 따옴표 `"홍길동"`
- 숫자는 중괄호 `{25}`
- 변수도 중괄호 `{변수명}`

### 2.3 Props 기본값 설정

**defaultProps 사용:**
```jsx
function Welcome(props) {
  return <h1>안녕하세요, {props.name}님!</h1>;
}

Welcome.defaultProps = {
  name: "게스트"
};

// 또는 함수 매개변수 기본값
function Welcome({ name = "게스트" }) {
  return <h1>안녕하세요, {name}님!</h1>;
}
```

---

## 3. 구조 분해 할당 (Destructuring)

### 3.1 구조 분해 할당이란?

**Props를 더 간편하게 사용하는 방법입니다.**

**일반 방법:**
```jsx
function UserCard(props) {
  return (
    <div>
      <h2>{props.name}</h2>
      <p>{props.age}세</p>
      <p>{props.email}</p>
    </div>
  );
}
```

**구조 분해 할당 사용:**
```jsx
function UserCard({ name, age, email }) {
  return (
    <div>
      <h2>{name}</h2>
      <p>{age}세</p>
      <p>{email}</p>
    </div>
  );
}
```

**장점:**
- ✅ 코드가 간결해짐
- ✅ `props.` 반복 없음
- ✅ 가독성 향상

### 3.2 구조 분해 할당 예시

**여러 Props:**
```jsx
function Product({ name, price, description }) {
  return (
    <div>
      <h3>{name}</h3>
      <p>가격: {price}원</p>
      <p>{description}</p>
    </div>
  );
}
```

**기본값과 함께:**
```jsx
function Button({ text = "클릭", onClick, disabled = false }) {
  return (
    <button onClick={onClick} disabled={disabled}>
      {text}
    </button>
  );
}
```

**나머지 Props:**
```jsx
function Card({ title, ...rest }) {
  return (
    <div {...rest}>
      <h3>{title}</h3>
    </div>
  );
}

// 사용
<Card title="제목" className="card" id="card1" />
```

---

## 4. 다양한 Props 타입

### 4.1 문자열 Props

```jsx
function Welcome({ name }) {
  return <h1>안녕하세요, {name}님!</h1>;
}

<Welcome name="홍길동" />
```

### 4.2 숫자 Props

```jsx
function Counter({ count }) {
  return <p>카운트: {count}</p>;
}

<Counter count={10} />
```

### 4.3 불린 Props

```jsx
function Button({ disabled }) {
  return (
    <button disabled={disabled}>
      클릭
    </button>
  );
}

<Button disabled={true} />
<Button disabled={false} />
```

### 4.4 배열 Props

```jsx
function List({ items }) {
  return (
    <ul>
      {items.map((item, index) => (
        <li key={index}>{item}</li>
      ))}
    </ul>
  );
}

<List items={["사과", "바나나", "오렌지"]} />
```

### 4.5 객체 Props

```jsx
function UserCard({ user }) {
  return (
    <div>
      <h2>{user.name}</h2>
      <p>{user.email}</p>
    </div>
  );
}

<UserCard user={{ name: "홍길동", email: "hong@example.com" }} />
```

### 4.6 함수 Props

```jsx
function Button({ onClick, text }) {
  return <button onClick={onClick}>{text}</button>;
}

function App() {
  const handleClick = () => {
    alert("클릭됨!");
  };
  
  return <Button onClick={handleClick} text="클릭하세요" />;
}
```

---

## 5. 실습: 게시글 카드 컴포넌트 만들기

### 실습 1: 기본 게시글 카드

**요구사항:**
- 제목, 작성자, 내용, 작성일을 Props로 받기
- 카드 형태로 표시

**코드:**
```jsx
// src/components/PostCard.jsx
function PostCard({ title, author, content, date }) {
  return (
    <div style={{
      border: '1px solid #ddd',
      borderRadius: '8px',
      padding: '1rem',
      margin: '1rem 0',
      boxShadow: '0 2px 4px rgba(0,0,0,0.1)'
    }}>
      <h3 style={{ marginTop: 0 }}>{title}</h3>
      <div style={{ 
        fontSize: '0.9rem', 
        color: '#666',
        marginBottom: '0.5rem'
      }}>
        <span>작성자: {author}</span>
        <span style={{ marginLeft: '1rem' }}>작성일: {date}</span>
      </div>
      <p>{content}</p>
    </div>
  );
}

export default PostCard;
```

**App.jsx에서 사용:**
```jsx
import PostCard from './components/PostCard';

function App() {
  return (
    <div style={{ padding: '2rem' }}>
      <h1>게시글 목록</h1>
      
      <PostCard 
        title="React 배우기"
        author="홍길동"
        content="React는 정말 재미있습니다!"
        date="2024-01-15"
      />
      
      <PostCard 
        title="Props 이해하기"
        author="김철수"
        content="Props는 부모에서 자식으로 데이터를 전달합니다."
        date="2024-01-16"
      />
      
      <PostCard 
        title="컴포넌트 분리"
        author="이영희"
        content="컴포넌트를 분리하면 코드가 깔끔해집니다."
        date="2024-01-17"
      />
    </div>
  );
}

export default App;
```

### 실습 2: 배열 데이터로 게시글 목록 만들기

**요구사항:**
- 게시글 데이터를 배열로 관리
- map 함수로 여러 카드 렌더링

**코드:**
```jsx
import PostCard from './components/PostCard';

function App() {
  const posts = [
    {
      id: 1,
      title: "React 배우기",
      author: "홍길동",
      content: "React는 정말 재미있습니다!",
      date: "2024-01-15"
    },
    {
      id: 2,
      title: "Props 이해하기",
      author: "김철수",
      content: "Props는 부모에서 자식으로 데이터를 전달합니다.",
      date: "2024-01-16"
    },
    {
      id: 3,
      title: "컴포넌트 분리",
      author: "이영희",
      content: "컴포넌트를 분리하면 코드가 깔끔해집니다.",
      date: "2024-01-17"
    }
  ];
  
  return (
    <div style={{ padding: '2rem' }}>
      <h1>게시글 목록</h1>
      
      {posts.map(post => (
        <PostCard
          key={post.id}
          title={post.title}
          author={post.author}
          content={post.content}
          date={post.date}
        />
      ))}
    </div>
  );
}

export default App;
```

**또는 구조 분해 할당 사용:**
```jsx
{posts.map(post => (
  <PostCard
    key={post.id}
    {...post}  // 모든 속성을 한 번에 전달
  />
))}
```

### 실습 3: 게시글 카드 스타일 개선

**요구사항:**
- 더 예쁜 디자인
- 호버 효과
- 반응형 디자인

**개선된 PostCard:**
```jsx
function PostCard({ title, author, content, date }) {
  return (
    <div style={{
      border: '1px solid #e0e0e0',
      borderRadius: '12px',
      padding: '1.5rem',
      margin: '1rem 0',
      boxShadow: '0 2px 8px rgba(0,0,0,0.1)',
      transition: 'transform 0.2s, box-shadow 0.2s',
      cursor: 'pointer'
    }}
    onMouseEnter={(e) => {
      e.currentTarget.style.transform = 'translateY(-4px)';
      e.currentTarget.style.boxShadow = '0 4px 12px rgba(0,0,0,0.15)';
    }}
    onMouseLeave={(e) => {
      e.currentTarget.style.transform = 'translateY(0)';
      e.currentTarget.style.boxShadow = '0 2px 8px rgba(0,0,0,0.1)';
    }}
    >
      <h3 style={{ 
        marginTop: 0, 
        color: '#333',
        fontSize: '1.5rem'
      }}>
        {title}
      </h3>
      
      <div style={{ 
        fontSize: '0.85rem', 
        color: '#666',
        marginBottom: '1rem',
        display: 'flex',
        gap: '1rem'
      }}>
        <span>👤 {author}</span>
        <span>📅 {date}</span>
      </div>
      
      <p style={{ 
        color: '#555',
        lineHeight: '1.6'
      }}>
        {content}
      </p>
    </div>
  );
}

export default PostCard;
```

---

## 6. 실습 과제

### 과제 1: 프로필 카드 컴포넌트

**요구사항:**
- 이름, 나이, 직업, 프로필 사진(URL)을 Props로 받기
- 카드 형태로 표시
- 여러 개의 프로필 카드 만들기

**예시:**
```jsx
<ProfileCard 
  name="홍길동"
  age={25}
  job="프론트엔드 개발자"
  image="https://example.com/image.jpg"
/>
```

### 과제 2: 상품 카드 컴포넌트

**요구사항:**
- 상품명, 가격, 설명, 이미지 URL을 Props로 받기
- 할인율이 있으면 표시
- 가격은 숫자로 받아서 포맷팅 (예: 15000 → "15,000원")

**예시:**
```jsx
<ProductCard 
  name="노트북"
  price={1500000}
  description="고성능 노트북"
  image="https://example.com/laptop.jpg"
  discount={10}  // 10% 할인
/>
```

### 과제 3: 버튼 컴포넌트

**요구사항:**
- 텍스트, 색상, 크기, 클릭 핸들러를 Props로 받기
- 다양한 스타일의 버튼 만들기

**예시:**
```jsx
<Button 
  text="클릭하세요"
  color="blue"
  size="large"
  onClick={() => alert("클릭!")}
/>
```

---

## 7. 자주 발생하는 오류

### 오류 1: `Cannot read property of undefined`

**원인:** Props를 전달하지 않았거나 구조 분해 할당 오류

**해결:**
```jsx
// ❌
function Card({ title }) {
  return <h3>{title}</h3>;
}
<Card />  // title이 undefined

// ✅
function Card({ title = "기본 제목" }) {
  return <h3>{title}</h3>;
}
```

### 오류 2: Props 이름 오타

**원인:** Props 이름이 일치하지 않음

**해결:**
```jsx
// ❌
<Card titel="제목" />  // 오타: titel

// ✅
<Card title="제목" />
```

### 오류 3: 숫자를 문자열로 전달

**원인:** 숫자를 따옴표로 감쌈

**해결:**
```jsx
// ❌
<Counter count="10" />  // 문자열 "10"

// ✅
<Counter count={10} />   // 숫자 10
```

---

## 8. Props vs State

### 차이점

| 항목 | Props | State |
|------|-------|-------|
| 정의 | 부모에서 전달받은 데이터 | 컴포넌트 내부 데이터 |
| 변경 가능 | ❌ 읽기 전용 | ✅ 변경 가능 |
| 전달 방향 | 부모 → 자식 | 컴포넌트 내부 |
| 사용 목적 | 데이터 전달 | 동적 데이터 관리 |

**예시:**
```jsx
// Props: 부모에서 받은 데이터 (변경 불가)
function Welcome({ name }) {
  return <h1>안녕하세요, {name}님!</h1>;
}

// State: 컴포넌트 내부 데이터 (변경 가능) - 다음 차시에서 배움
function Counter() {
  const [count, setCount] = useState(0);
  return <button onClick={() => setCount(count + 1)}>{count}</button>;
}
```

---

## 9. 다음 차시 예고

다음 차시에서는 **State (useState)**를 배웁니다:
- State 개념
- useState Hook 사용법
- 상태 변경 시 렌더링
- 버튼 클릭 카운트, 토글 버튼 만들기

---

## 요약

### 핵심 개념

1. **Props**: 부모에서 자식으로 데이터 전달
2. **읽기 전용**: Props는 수정 불가
3. **구조 분해 할당**: `{ name, age }` 형태로 간편하게 사용
4. **재사용성**: 같은 컴포넌트에 다른 Props 전달

### 필수 문법

```jsx
// Props 전달
<컴포넌트명 prop1="값" prop2={값} />

// Props 받기 (일반)
function 컴포넌트(props) {
  return <div>{props.name}</div>;
}

// Props 받기 (구조 분해)
function 컴포넌트({ name, age }) {
  return <div>{name}, {age}세</div>;
}
```

### 체크리스트

- [ ] Props의 개념 이해
- [ ] Props 전달 및 받기 가능
- [ ] 구조 분해 할당 사용 가능
- [ ] 게시글 카드 컴포넌트 만들기 완료
- [ ] 다양한 Props 타입 이해

---

**다음 차시에서 만나요! 🚀**

