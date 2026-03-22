# 6차시. 이벤트 처리

## 학습 목표
- React에서 이벤트를 처리하는 방법을 이해할 수 있다
- onClick, onChange 등 주요 이벤트를 사용할 수 있다
- 이벤트 객체를 활용할 수 있다
- 인풋 값을 제어하고 로그인 폼을 만들 수 있다

---

## 1. 이벤트 처리란?

### 1.1 이벤트의 개념

**이벤트**는 사용자의 행동(클릭, 입력, 스크롤 등)에 반응하는 것입니다.

**주요 이벤트:**
- 클릭: `onClick`
- 입력: `onChange`
- 제출: `onSubmit`
- 마우스: `onMouseEnter`, `onMouseLeave`
- 키보드: `onKeyDown`, `onKeyPress`

### 1.2 React 이벤트 처리 방식

**HTML 방식:**
```html
<button onclick="handleClick()">클릭</button>
```

**React 방식:**
```jsx
<button onClick={handleClick}>클릭</button>
```

**차이점:**
- HTML: 소문자 `onclick`
- React: camelCase `onClick`
- HTML: 문자열 `"handleClick()"`
- React: 함수 참조 `{handleClick}`

---

## 2. onClick 이벤트

### 2.1 기본 사용법

**인라인 함수:**
```jsx
function Button() {
  return (
    <button onClick={() => alert('클릭됨!')}>
      클릭하세요
    </button>
  );
}
```

**함수 정의 후 사용:**
```jsx
function Button() {
  const handleClick = () => {
    alert('클릭됨!');
  };
  
  return (
    <button onClick={handleClick}>
      클릭하세요
    </button>
  );
}
```

### 2.2 State와 함께 사용

```jsx
import { useState } from 'react';

function Counter() {
  const [count, setCount] = useState(0);
  
  const handleClick = () => {
    setCount(count + 1);
  };
  
  return (
    <div>
      <p>카운트: {count}</p>
      <button onClick={handleClick}>증가</button>
    </div>
  );
}
```

### 2.3 여러 버튼 처리

```jsx
import { useState } from 'react';

function Counter() {
  const [count, setCount] = useState(0);
  
  const increment = () => setCount(count + 1);
  const decrement = () => setCount(count - 1);
  const reset = () => setCount(0);
  
  return (
    <div>
      <p>카운트: {count}</p>
      <button onClick={increment}>+1</button>
      <button onClick={decrement}>-1</button>
      <button onClick={reset}>리셋</button>
    </div>
  );
}
```

---

## 3. onChange 이벤트

### 3.1 입력 필드 제어

**기본 사용법:**
```jsx
import { useState } from 'react';

function Input() {
  const [value, setValue] = useState('');
  
  const handleChange = (e) => {
    setValue(e.target.value);
  };
  
  return (
    <div>
      <input 
        type="text" 
        value={value}
        onChange={handleChange}
        placeholder="입력하세요"
      />
      <p>입력값: {value}</p>
    </div>
  );
}
```

### 3.2 Controlled Component

**Controlled Component (제어 컴포넌트):**
- React가 입력값을 제어
- `value`와 `onChange`를 함께 사용

**예시:**
```jsx
function ControlledInput() {
  const [text, setText] = useState('');
  
  return (
    <input 
      value={text}
      onChange={(e) => setText(e.target.value)}
    />
  );
}
```

**Uncontrolled Component (비제어 컴포넌트):**
```jsx
function UncontrolledInput() {
  return <input />;  // React가 제어하지 않음
}
```

**권장:** Controlled Component 사용!

### 3.3 여러 입력 필드 처리

**방법 1: 각각의 State**
```jsx
function Form() {
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  
  return (
    <form>
      <input 
        value={name}
        onChange={(e) => setName(e.target.value)}
        placeholder="이름"
      />
      <input 
        value={email}
        onChange={(e) => setEmail(e.target.value)}
        placeholder="이메일"
      />
    </form>
  );
}
```

**방법 2: 객체 State (권장)**
```jsx
function Form() {
  const [formData, setFormData] = useState({
    name: '',
    email: ''
  });
  
  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };
  
  return (
    <form>
      <input 
        name="name"
        value={formData.name}
        onChange={handleChange}
        placeholder="이름"
      />
      <input 
        name="email"
        value={formData.email}
        onChange={handleChange}
        placeholder="이메일"
      />
    </form>
  );
}
```

---

## 4. 이벤트 객체

### 4.1 이벤트 객체란?

**이벤트 객체**는 이벤트가 발생했을 때 전달되는 정보를 담은 객체입니다.

**주요 속성:**
- `target`: 이벤트가 발생한 요소
- `target.value`: 입력값
- `target.name`: 요소의 name 속성
- `preventDefault()`: 기본 동작 방지
- `stopPropagation()`: 이벤트 전파 중지

### 4.2 이벤트 객체 사용 예시

**입력값 가져오기:**
```jsx
function Input() {
  const handleChange = (e) => {
    console.log('입력값:', e.target.value);
    console.log('요소 이름:', e.target.name);
  };
  
  return (
    <input 
      name="username"
      onChange={handleChange}
    />
  );
}
```

**기본 동작 방지:**
```jsx
function Form() {
  const handleSubmit = (e) => {
    e.preventDefault();  // 폼 제출 기본 동작 방지
    console.log('제출됨!');
  };
  
  return (
    <form onSubmit={handleSubmit}>
      <input type="text" />
      <button type="submit">제출</button>
    </form>
  );
}
```

### 4.3 이벤트 객체 활용

**입력값 검증:**
```jsx
function Input() {
  const [value, setValue] = useState('');
  const [error, setError] = useState('');
  
  const handleChange = (e) => {
    const inputValue = e.target.value;
    setValue(inputValue);
    
    if (inputValue.length < 3) {
      setError('최소 3글자 이상 입력하세요');
    } else {
      setError('');
    }
  };
  
  return (
    <div>
      <input 
        value={value}
        onChange={handleChange}
      />
      {error && <p style={{ color: 'red' }}>{error}</p>}
    </div>
  );
}
```

---

## 5. onSubmit 이벤트

### 5.1 폼 제출 처리

**기본 사용법:**
```jsx
function Form() {
  const handleSubmit = (e) => {
    e.preventDefault();  // 페이지 새로고침 방지
    console.log('폼 제출됨!');
  };
  
  return (
    <form onSubmit={handleSubmit}>
      <input type="text" />
      <button type="submit">제출</button>
    </form>
  );
}
```

**주의사항:**
- `e.preventDefault()` 필수! (페이지 새로고침 방지)
- `button type="submit"` 또는 `form onSubmit` 사용

### 5.2 폼 데이터 수집

```jsx
function Form() {
  const [formData, setFormData] = useState({
    username: '',
    password: ''
  });
  
  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };
  
  const handleSubmit = (e) => {
    e.preventDefault();
    console.log('제출된 데이터:', formData);
    // 여기서 API 호출 등 처리
  };
  
  return (
    <form onSubmit={handleSubmit}>
      <input 
        name="username"
        value={formData.username}
        onChange={handleChange}
        placeholder="사용자명"
      />
      <input 
        name="password"
        type="password"
        value={formData.password}
        onChange={handleChange}
        placeholder="비밀번호"
      />
      <button type="submit">로그인</button>
    </form>
  );
}
```

---

## 6. 실습: 로그인 폼 만들기

### 실습 1: 기본 로그인 폼

**요구사항:**
- 사용자명과 비밀번호 입력 필드
- 제출 버튼
- 제출 시 입력값 콘솔에 출력

**코드:**
```jsx
import { useState } from 'react';

function LoginForm() {
  const [formData, setFormData] = useState({
    username: '',
    password: ''
  });
  
  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };
  
  const handleSubmit = (e) => {
    e.preventDefault();
    console.log('로그인 시도:', formData);
    // 여기서 실제 로그인 처리
  };
  
  return (
    <div style={{
      maxWidth: '400px',
      margin: '50px auto',
      padding: '2rem',
      border: '1px solid #ddd',
      borderRadius: '8px'
    }}>
      <h2>로그인</h2>
      
      <form onSubmit={handleSubmit}>
        <div style={{ marginBottom: '1rem' }}>
          <label style={{ display: 'block', marginBottom: '0.5rem' }}>
            사용자명
          </label>
          <input
            type="text"
            name="username"
            value={formData.username}
            onChange={handleChange}
            placeholder="사용자명을 입력하세요"
            style={{
              width: '100%',
              padding: '0.5rem',
              fontSize: '1rem',
              border: '1px solid #ddd',
              borderRadius: '4px'
            }}
          />
        </div>
        
        <div style={{ marginBottom: '1rem' }}>
          <label style={{ display: 'block', marginBottom: '0.5rem' }}>
            비밀번호
          </label>
          <input
            type="password"
            name="password"
            value={formData.password}
            onChange={handleChange}
            placeholder="비밀번호를 입력하세요"
            style={{
              width: '100%',
              padding: '0.5rem',
              fontSize: '1rem',
              border: '1px solid #ddd',
              borderRadius: '4px'
            }}
          />
        </div>
        
        <button
          type="submit"
          style={{
            width: '100%',
            padding: '0.75rem',
            fontSize: '1rem',
            backgroundColor: '#007bff',
            color: 'white',
            border: 'none',
            borderRadius: '4px',
            cursor: 'pointer'
          }}
        >
          로그인
        </button>
      </form>
    </div>
  );
}

export default LoginForm;
```

### 실습 2: 입력값 검증 추가

**요구사항:**
- 사용자명 최소 3글자
- 비밀번호 최소 6글자
- 에러 메시지 표시
- 유효성 검사 통과 시에만 제출 가능

**코드:**
```jsx
import { useState } from 'react';

function LoginForm() {
  const [formData, setFormData] = useState({
    username: '',
    password: ''
  });
  
  const [errors, setErrors] = useState({
    username: '',
    password: ''
  });
  
  const validate = (name, value) => {
    let error = '';
    
    if (name === 'username') {
      if (value.length < 3) {
        error = '사용자명은 최소 3글자 이상이어야 합니다';
      }
    }
    
    if (name === 'password') {
      if (value.length < 6) {
        error = '비밀번호는 최소 6글자 이상이어야 합니다';
      }
    }
    
    return error;
  };
  
  const handleChange = (e) => {
    const { name, value } = e.target;
    
    setFormData({
      ...formData,
      [name]: value
    });
    
    // 실시간 검증
    setErrors({
      ...errors,
      [name]: validate(name, value)
    });
  };
  
  const handleSubmit = (e) => {
    e.preventDefault();
    
    // 최종 검증
    const usernameError = validate('username', formData.username);
    const passwordError = validate('password', formData.password);
    
    if (usernameError || passwordError) {
      setErrors({
        username: usernameError,
        password: passwordError
      });
      return;
    }
    
    console.log('로그인 성공:', formData);
    alert('로그인 성공!');
  };
  
  const isFormValid = formData.username.length >= 3 && formData.password.length >= 6;
  
  return (
    <div style={{
      maxWidth: '400px',
      margin: '50px auto',
      padding: '2rem',
      border: '1px solid #ddd',
      borderRadius: '8px'
    }}>
      <h2>로그인</h2>
      
      <form onSubmit={handleSubmit}>
        <div style={{ marginBottom: '1rem' }}>
          <label style={{ display: 'block', marginBottom: '0.5rem' }}>
            사용자명
          </label>
          <input
            type="text"
            name="username"
            value={formData.username}
            onChange={handleChange}
            placeholder="사용자명을 입력하세요"
            style={{
              width: '100%',
              padding: '0.5rem',
              fontSize: '1rem',
              border: errors.username ? '1px solid red' : '1px solid #ddd',
              borderRadius: '4px'
            }}
          />
          {errors.username && (
            <p style={{ color: 'red', fontSize: '0.875rem', marginTop: '0.25rem' }}>
              {errors.username}
            </p>
          )}
        </div>
        
        <div style={{ marginBottom: '1rem' }}>
          <label style={{ display: 'block', marginBottom: '0.5rem' }}>
            비밀번호
          </label>
          <input
            type="password"
            name="password"
            value={formData.password}
            onChange={handleChange}
            placeholder="비밀번호를 입력하세요"
            style={{
              width: '100%',
              padding: '0.5rem',
              fontSize: '1rem',
              border: errors.password ? '1px solid red' : '1px solid #ddd',
              borderRadius: '4px'
            }}
          />
          {errors.password && (
            <p style={{ color: 'red', fontSize: '0.875rem', marginTop: '0.25rem' }}>
              {errors.password}
            </p>
          )}
        </div>
        
        <button
          type="submit"
          disabled={!isFormValid}
          style={{
            width: '100%',
            padding: '0.75rem',
            fontSize: '1rem',
            backgroundColor: isFormValid ? '#007bff' : '#ccc',
            color: 'white',
            border: 'none',
            borderRadius: '4px',
            cursor: isFormValid ? 'pointer' : 'not-allowed'
          }}
        >
          로그인
        </button>
      </form>
    </div>
  );
}

export default LoginForm;
```

### 실습 3: 로그인 상태 관리

**요구사항:**
- 로그인 성공 시 로그인 상태 변경
- 로그인 상태에 따라 다른 화면 표시

**코드:**
```jsx
import { useState } from 'react';

function LoginApp() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [formData, setFormData] = useState({
    username: '',
    password: ''
  });
  
  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };
  
  const handleSubmit = (e) => {
    e.preventDefault();
    
    // 간단한 검증 (실제로는 API 호출)
    if (formData.username === 'admin' && formData.password === 'password') {
      setIsLoggedIn(true);
    } else {
      alert('로그인 실패!');
    }
  };
  
  const handleLogout = () => {
    setIsLoggedIn(false);
    setFormData({ username: '', password: '' });
  };
  
  if (isLoggedIn) {
    return (
      <div style={{
        maxWidth: '400px',
        margin: '50px auto',
        padding: '2rem',
        textAlign: 'center'
      }}>
        <h2>환영합니다, {formData.username}님!</h2>
        <button onClick={handleLogout}>
          로그아웃
        </button>
      </div>
    );
  }
  
  return (
    <div style={{
      maxWidth: '400px',
      margin: '50px auto',
      padding: '2rem',
      border: '1px solid #ddd',
      borderRadius: '8px'
    }}>
      <h2>로그인</h2>
      
      <form onSubmit={handleSubmit}>
        <div style={{ marginBottom: '1rem' }}>
          <input
            type="text"
            name="username"
            value={formData.username}
            onChange={handleChange}
            placeholder="사용자명"
            style={{
              width: '100%',
              padding: '0.5rem',
              marginBottom: '0.5rem'
            }}
          />
          <input
            type="password"
            name="password"
            value={formData.password}
            onChange={handleChange}
            placeholder="비밀번호"
            style={{
              width: '100%',
              padding: '0.5rem',
              marginBottom: '0.5rem'
            }}
          />
        </div>
        
        <button type="submit" style={{
          width: '100%',
          padding: '0.75rem',
          backgroundColor: '#007bff',
          color: 'white',
          border: 'none',
          borderRadius: '4px',
          cursor: 'pointer'
        }}>
          로그인
        </button>
      </form>
      
      <p style={{ fontSize: '0.875rem', color: '#666', marginTop: '1rem' }}>
        테스트: admin / password
      </p>
    </div>
  );
}

export default LoginApp;
```

---

## 7. 다른 이벤트들

### 7.1 마우스 이벤트

```jsx
function MouseEvents() {
  const [message, setMessage] = useState('');
  
  return (
    <div
      onMouseEnter={() => setMessage('마우스가 들어왔습니다')}
      onMouseLeave={() => setMessage('마우스가 나갔습니다')}
      style={{
        padding: '2rem',
        border: '1px solid #ddd',
        borderRadius: '8px'
      }}
    >
      <p>{message || '마우스를 올려보세요'}</p>
    </div>
  );
}
```

### 7.2 키보드 이벤트

```jsx
function KeyboardEvents() {
  const [key, setKey] = useState('');
  
  const handleKeyDown = (e) => {
    setKey(`키 눌림: ${e.key}`);
  };
  
  return (
    <div>
      <input
        type="text"
        onKeyDown={handleKeyDown}
        placeholder="키를 눌러보세요"
      />
      <p>{key}</p>
    </div>
  );
}
```

---

## 8. 실습 과제

### 과제 1: 회원가입 폼

**요구사항:**
- 이름, 이메일, 비밀번호, 비밀번호 확인 입력
- 실시간 검증 (이메일 형식, 비밀번호 일치 등)
- 제출 버튼

### 과제 2: 검색창

**요구사항:**
- 입력값에 따라 실시간으로 필터링
- 검색어 하이라이트
- 엔터 키로 검색

### 과제 3: 할일 추가 폼

**요구사항:**
- 할일 입력 필드
- 추가 버튼 클릭 또는 엔터 키로 추가
- 입력 후 필드 초기화

---

## 9. 자주 발생하는 오류

### 오류 1: `Cannot read property 'value' of undefined`

**원인:** 이벤트 객체를 제대로 받지 못함

**해결:**
```jsx
// ❌
const handleChange = () => {
  console.log(e.target.value);  // e가 정의되지 않음
};

// ✅
const handleChange = (e) => {
  console.log(e.target.value);
};
```

### 오류 2: 폼 제출 시 페이지 새로고침

**원인:** `preventDefault()` 누락

**해결:**
```jsx
const handleSubmit = (e) => {
  e.preventDefault();  // 필수!
  // ...
};
```

### 오류 3: 입력값이 업데이트되지 않음

**원인:** `value`와 `onChange`를 함께 사용하지 않음

**해결:**
```jsx
// ✅ Controlled Component
<input 
  value={value}
  onChange={(e) => setValue(e.target.value)}
/>
```

---

## 10. 다음 차시 예고

다음 차시에서는 **조건부 렌더링**을 배웁니다:
- 삼항 연산자
- && 연산자
- 조건에 따른 화면 제어
- 로그인/로그아웃 화면 만들기

---

## 요약

### 핵심 개념

1. **이벤트 처리**: 사용자 행동에 반응
2. **onClick**: 클릭 이벤트
3. **onChange**: 입력값 변경 이벤트
4. **onSubmit**: 폼 제출 이벤트
5. **Controlled Component**: React가 입력값 제어

### 필수 문법

```jsx
// 클릭 이벤트
<button onClick={handleClick}>클릭</button>

// 입력 이벤트
<input 
  value={value}
  onChange={(e) => setValue(e.target.value)}
/>

// 폼 제출
<form onSubmit={(e) => {
  e.preventDefault();
  // 처리
}}>
```

### 체크리스트

- [ ] onClick 이벤트 사용 가능
- [ ] onChange 이벤트 사용 가능
- [ ] 이벤트 객체 활용 가능
- [ ] Controlled Component 이해
- [ ] 로그인 폼 만들기 완료

---

**다음 차시에서 만나요! 🚀**





