# 2차시. JSX 문법

## 학습 목표
- JSX의 기본 문법을 이해하고 사용할 수 있다
- JSX와 JavaScript의 차이점을 설명할 수 있다
- JavaScript 표현식을 JSX에서 사용할 수 있다
- JSX로 화면을 구성하고 변수를 출력할 수 있다

---

## 1. JSX란?

### 1.1 JSX의 정의

**JSX (JavaScript XML)**는 JavaScript에 XML/HTML과 유사한 문법을 추가한 것입니다.

**특징:**
- ✅ JavaScript의 확장 문법
- ✅ React에서 UI를 작성하는 방법
- ✅ 컴파일 시 일반 JavaScript로 변환됨

### 1.2 JSX의 필요성

**JSX 없이 작성 (복잡함):**
```javascript
const element = React.createElement(
  'h1',
  { className: 'greeting' },
  'Hello, World!'
);
```

**JSX로 작성 (간단함):**
```jsx
const element = <h1 className="greeting">Hello, World!</h1>;
```

**JSX의 장점:**
- ✅ HTML과 유사하여 직관적
- ✅ 코드가 읽기 쉬움
- ✅ React 컴포넌트 작성이 쉬움

---

## 2. JSX 기본 문법

### 2.1 JSX 요소 작성

**기본 구조:**
```jsx
const element = <h1>Hello, React!</h1>;
```

**여러 줄 작성:**
```jsx
const element = (
  <div>
    <h1>Hello</h1>
    <p>World</p>
  </div>
);
```

**주의사항:**
- 여러 줄 작성 시 괄호 `()`로 감싸기
- 하나의 부모 요소로 감싸야 함

### 2.2 JSX 규칙

#### 규칙 1: 하나의 부모 요소

**❌ 잘못된 예:**
```jsx
function App() {
  return (
    <h1>제목</h1>
    <p>내용</p>
  );
}
```

**✅ 올바른 예:**
```jsx
function App() {
  return (
    <div>
      <h1>제목</h1>
      <p>내용</p>
    </div>
  );
}
```

**또는 Fragment 사용:**
```jsx
function App() {
  return (
    <>
      <h1>제목</h1>
      <p>내용</p>
    </>
  );
}
```

#### 규칙 2: 닫는 태그 필수

**❌ 잘못된 예:**
```jsx
<img src="image.jpg">
<br>
<input type="text">
```

**✅ 올바른 예:**
```jsx
<img src="image.jpg" />
<br />
<input type="text" />
```

#### 규칙 3: 속성명은 camelCase

**HTML:**
```html
<div class="container">
  <button onclick="handleClick()">클릭</button>
</div>
```

**JSX:**
```jsx
<div className="container">
  <button onClick={handleClick}>클릭</button>
</div>
```

**주요 속성명 변경:**
- `class` → `className`
- `for` → `htmlFor`
- `onclick` → `onClick`
- `onchange` → `onChange`

---

## 3. JavaScript 표현식 사용 `{}`

### 3.1 표현식이란?

**표현식 (Expression)**: 값을 반환하는 코드

**예시:**
```javascript
10 + 20              // 30
user.name            // "홍길동"
count > 0            // true 또는 false
```

### 3.2 JSX에서 표현식 사용

**변수 출력:**
```jsx
function App() {
  const name = "홍길동";
  const age = 25;
  
  return (
    <div>
      <h1>이름: {name}</h1>
      <p>나이: {age}</p>
    </div>
  );
}
```

**계산식 사용:**
```jsx
function App() {
  const price = 1000;
  const quantity = 3;
  
  return (
    <div>
      <p>가격: {price}원</p>
      <p>수량: {quantity}개</p>
      <p>총액: {price * quantity}원</p>
    </div>
  );
}
```

**함수 호출:**
```jsx
function App() {
  const getGreeting = () => {
    return "안녕하세요!";
  };
  
  return (
    <div>
      <h1>{getGreeting()}</h1>
    </div>
  );
}
```

**조건부 표현식:**
```jsx
function App() {
  const isLoggedIn = true;
  
  return (
    <div>
      {isLoggedIn ? <p>로그인됨</p> : <p>로그인 필요</p>}
    </div>
  );
}
```

### 3.3 표현식 사용 규칙

#### 규칙 1: 표현식만 사용 가능

**❌ 잘못된 예 (문장 사용):**
```jsx
function App() {
  return (
    <div>
      {if (true) { return "Hello"; }}  {/* 에러! */}
      {let x = 10;}  {/* 에러! */}
    </div>
  );
}
```

**✅ 올바른 예:**
```jsx
function App() {
  const x = 10;
  const message = true ? "Hello" : "World";
  
  return (
    <div>
      <p>{message}</p>
      <p>{x}</p>
    </div>
  );
}
```

#### 규칙 2: 객체는 직접 출력 불가

**❌ 잘못된 예:**
```jsx
function App() {
  const user = { name: "홍길동", age: 25 };
  
  return (
    <div>
      {user}  {/* 에러! */}
    </div>
  );
}
```

**✅ 올바른 예:**
```jsx
function App() {
  const user = { name: "홍길동", age: 25 };
  
  return (
    <div>
      <p>이름: {user.name}</p>
      <p>나이: {user.age}</p>
    </div>
  );
}
```

---

## 4. JSX와 HTML의 차이

### 4.1 주요 차이점

| 항목 | HTML | JSX |
|------|------|-----|
| 속성명 | `class`, `for` | `className`, `htmlFor` |
| 이벤트 | `onclick` | `onClick` |
| 스타일 | 문자열 | 객체 |
| 주석 | `<!-- -->` | `{/* */}` |

### 4.2 스타일 속성

**HTML:**
```html
<div style="color: red; font-size: 20px;">텍스트</div>
```

**JSX:**
```jsx
function App() {
  return (
    <div style={{ color: 'red', fontSize: '20px' }}>
      텍스트
    </div>
  );
}
```

**주의사항:**
- 객체 형태로 작성
- 속성명은 camelCase
- 값은 문자열로 작성

### 4.3 주석

**HTML:**
```html
<!-- 이것은 주석입니다 -->
```

**JSX:**
```jsx
function App() {
  return (
    <div>
      {/* 이것은 JSX 주석입니다 */}
      <h1>제목</h1>
    </div>
  );
}
```

---

## 5. 실습 예제

### 실습 1: 변수 출력하기

**요구사항:**
- 이름, 나이, 이메일을 변수로 선언
- JSX에서 출력하기

**코드:**
```jsx
function App() {
  const name = "홍길동";
  const age = 25;
  const email = "hong@example.com";
  
  return (
    <div>
      <h1>프로필</h1>
      <p>이름: {name}</p>
      <p>나이: {age}세</p>
      <p>이메일: {email}</p>
    </div>
  );
}

export default App;
```

### 실습 2: 계산식 사용하기

**요구사항:**
- 상품 가격과 수량을 변수로 선언
- 총액을 계산하여 출력

**코드:**
```jsx
function App() {
  const price = 15000;
  const quantity = 3;
  const total = price * quantity;
  
  return (
    <div>
      <h2>주문 내역</h2>
      <p>단가: {price.toLocaleString()}원</p>
      <p>수량: {quantity}개</p>
      <p>총액: {total.toLocaleString()}원</p>
    </div>
  );
}

export default App;
```

### 실습 3: 함수 사용하기

**요구사항:**
- 현재 시간을 반환하는 함수 작성
- JSX에서 함수 호출하여 출력

**코드:**
```jsx
function App() {
  const getCurrentTime = () => {
    return new Date().toLocaleTimeString();
  };
  
  const getGreeting = () => {
    const hour = new Date().getHours();
    if (hour < 12) return "좋은 아침입니다!";
    if (hour < 18) return "좋은 오후입니다!";
    return "좋은 저녁입니다!";
  };
  
  return (
    <div>
      <h1>{getGreeting()}</h1>
      <p>현재 시간: {getCurrentTime()}</p>
    </div>
  );
}

export default App;
```

### 실습 4: 조건부 렌더링 (미리보기)

**요구사항:**
- 로그인 상태에 따라 다른 메시지 출력

**코드:**
```jsx
function App() {
  const isLoggedIn = true;
  
  return (
    <div>
      {isLoggedIn ? (
        <div>
          <h1>환영합니다!</h1>
          <button>로그아웃</button>
        </div>
      ) : (
        <div>
          <h1>로그인이 필요합니다</h1>
          <button>로그인</button>
        </div>
      )}
    </div>
  );
}

export default App;
```

---

## 6. 실습 과제

### 과제 1: 자기소개 카드 만들기

**요구사항:**
- 이름, 나이, 취미, 자기소개를 변수로 선언
- JSX로 카드 형태로 출력
- 스타일 적용 (인라인 스타일 또는 CSS)

**예시 출력:**
```
┌─────────────────┐
│   자기소개      │
├─────────────────┤
│ 이름: 홍길동     │
│ 나이: 25세      │
│ 취미: 독서      │
│ 소개: 안녕하세요│
└─────────────────┘
```

### 과제 2: 계산기 UI 만들기

**요구사항:**
- 두 개의 숫자를 변수로 선언
- 덧셈, 뺄셈, 곱셈, 나눗셈 결과를 계산하여 출력

**예시 출력:**
```
숫자 1: 10
숫자 2: 5
━━━━━━━━━━━━━━
덧셈: 15
뺄셈: 5
곱셈: 50
나눗셈: 2
```

### 과제 3: 날씨 정보 표시

**요구사항:**
- 도시명, 온도, 날씨 상태를 변수로 선언
- 온도에 따라 다른 메시지 출력 (예: 25도 이상이면 "덥습니다")

---

## 7. 자주 발생하는 오류

### 오류 1: `Adjacent JSX elements must be wrapped`

**에러 메시지:**
```
Adjacent JSX elements must be wrapped in an enclosing tag
```

**원인:** 여러 요소를 부모 요소 없이 반환

**해결:** 하나의 부모 요소로 감싸기
```jsx
// ❌
return (
  <h1>제목</h1>
  <p>내용</p>
);

// ✅
return (
  <div>
    <h1>제목</h1>
    <p>내용</p>
  </div>
);
```

### 오류 2: `Unexpected token`

**원인:** JSX 문법 오류

**해결:** 
- 닫는 태그 확인
- 중괄호 `{}` 올바르게 사용
- 따옴표 확인

### 오류 3: `Cannot read property of undefined`

**원인:** undefined 값에 접근

**해결:** 옵셔널 체이닝 사용
```jsx
{user?.name}
```

---

## 8. 다음 차시 예고

다음 차시에서는 **컴포넌트**를 배웁니다:
- 함수형 컴포넌트
- 컴포넌트 분리
- 컴포넌트 재사용

---

## 요약

### 핵심 개념

1. **JSX**: JavaScript에 HTML과 유사한 문법 추가
2. **표현식 `{}`**: JavaScript 값을 JSX에 삽입
3. **규칙**: 하나의 부모 요소, 닫는 태그 필수, camelCase 속성명

### 필수 문법

```jsx
// 변수 출력
{변수명}

// 계산식
{price * quantity}

// 함수 호출
{getGreeting()}

// 조건부
{조건 ? true일때 : false일때}
```

### 체크리스트

- [ ] JSX 기본 문법 이해
- [ ] 표현식 `{}` 사용법 이해
- [ ] JSX 규칙 이해 (부모 요소, 닫는 태그)
- [ ] 변수 출력 실습 완료
- [ ] 계산식 사용 실습 완료
- [ ] 함수 사용 실습 완료

---

**다음 차시에서 만나요! 🚀**

