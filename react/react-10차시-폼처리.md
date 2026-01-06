# 10차시. 폼 처리

## 학습 목표
- Controlled Component의 개념을 이해할 수 있다
- 입력값 상태 관리를 할 수 있다
- 폼 제출(Submit) 처리를 할 수 있다
- 회원가입 폼을 만들 수 있다

---

## 1. 폼 처리란?

### 1.1 폼의 중요성

**폼**은 사용자로부터 데이터를 입력받는 UI 요소입니다.

**주요 사용처:**
- 로그인
- 회원가입
- 검색
- 댓글 작성
- 설정 변경

### 1.2 React에서 폼 처리

**전통적인 HTML 폼:**
```html
<form>
  <input type="text" name="username" />
  <button type="submit">제출</button>
</form>
```

**React 폼:**
```jsx
function Form() {
  const [username, setUsername] = useState('');
  
  const handleSubmit = (e) => {
    e.preventDefault();
    console.log(username);
  };
  
  return (
    <form onSubmit={handleSubmit}>
      <input 
        value={username}
        onChange={(e) => setUsername(e.target.value)}
      />
      <button type="submit">제출</button>
    </form>
  );
}
```

---

## 2. Controlled Component

### 2.1 Controlled Component란?

**Controlled Component**는 React가 입력값을 제어하는 컴포넌트입니다.

**특징:**
- `value` 속성으로 값 제어
- `onChange`로 값 변경 감지
- React가 입력값을 완전히 제어

**기본 구조:**
```jsx
const [value, setValue] = useState('');

<input
  value={value}
  onChange={(e) => setValue(e.target.value)}
/>
```

### 2.2 Controlled vs Uncontrolled

**Controlled Component (권장):**
```jsx
function Input() {
  const [value, setValue] = useState('');
  
  return (
    <input
      value={value}
      onChange={(e) => setValue(e.target.value)}
    />
  );
}
```

**장점:**
- ✅ 입력값을 React가 제어
- ✅ 실시간 검증 가능
- ✅ 상태와 UI 동기화

**Uncontrolled Component:**
```jsx
function Input() {
  return <input />;  // React가 제어하지 않음
}
```

**단점:**
- ❌ 실시간 검증 어려움
- ❌ 상태와 UI 동기화 어려움

**권장:** Controlled Component 사용!

---

## 3. 입력 필드 처리

### 3.1 텍스트 입력

```jsx
import { useState } from 'react';

function TextInput() {
  const [text, setText] = useState('');
  
  return (
    <div>
      <input
        type="text"
        value={text}
        onChange={(e) => setText(e.target.value)}
        placeholder="입력하세요"
      />
      <p>입력값: {text}</p>
    </div>
  );
}
```

### 3.2 여러 입력 필드

**방법 1: 각각의 State**
```jsx
function Form() {
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  
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
      <input
        type="password"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
        placeholder="비밀번호"
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
    email: '',
    password: ''
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
      <input
        name="password"
        type="password"
        value={formData.password}
        onChange={handleChange}
        placeholder="비밀번호"
      />
    </form>
  );
}
```

### 3.3 다양한 입력 타입

**텍스트 영역:**
```jsx
const [message, setMessage] = useState('');

<textarea
  value={message}
  onChange={(e) => setMessage(e.target.value)}
  placeholder="메시지를 입력하세요"
/>
```

**체크박스:**
```jsx
const [isAgreed, setIsAgreed] = useState(false);

<input
  type="checkbox"
  checked={isAgreed}
  onChange={(e) => setIsAgreed(e.target.checked)}
/>
```

**라디오 버튼:**
```jsx
const [gender, setGender] = useState('');

<input
  type="radio"
  name="gender"
  value="male"
  checked={gender === 'male'}
  onChange={(e) => setGender(e.target.value)}
/>
<input
  type="radio"
  name="gender"
  value="female"
  checked={gender === 'female'}
  onChange={(e) => setGender(e.target.value)}
/>
```

**셀렉트:**
```jsx
const [country, setCountry] = useState('');

<select
  value={country}
  onChange={(e) => setCountry(e.target.value)}
>
  <option value="">선택하세요</option>
  <option value="kr">한국</option>
  <option value="us">미국</option>
  <option value="jp">일본</option>
</select>
```

---

## 4. 폼 제출 처리

### 4.1 onSubmit 이벤트

**기본 구조:**
```jsx
const handleSubmit = (e) => {
  e.preventDefault();  // 필수! 페이지 새로고침 방지
  // 폼 처리 로직
};

<form onSubmit={handleSubmit}>
  {/* 입력 필드들 */}
  <button type="submit">제출</button>
</form>
```

**예시:**
```jsx
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
    console.log('제출된 데이터:', formData);
    // 실제로는 API 호출
  };
  
  return (
    <form onSubmit={handleSubmit}>
      <input
        name="username"
        value={formData.username}
        onChange={handleChange}
      />
      <input
        name="password"
        type="password"
        value={formData.password}
        onChange={handleChange}
      />
      <button type="submit">로그인</button>
    </form>
  );
}
```

### 4.2 폼 검증

**기본 검증:**
```jsx
function Form() {
  const [formData, setFormData] = useState({
    email: '',
    password: ''
  });
  const [errors, setErrors] = useState({});
  
  const validate = () => {
    const newErrors = {};
    
    if (!formData.email) {
      newErrors.email = '이메일을 입력하세요';
    } else if (!formData.email.includes('@')) {
      newErrors.email = '올바른 이메일 형식이 아닙니다';
    }
    
    if (!formData.password) {
      newErrors.password = '비밀번호를 입력하세요';
    } else if (formData.password.length < 6) {
      newErrors.password = '비밀번호는 최소 6자 이상이어야 합니다';
    }
    
    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };
  
  const handleSubmit = (e) => {
    e.preventDefault();
    
    if (validate()) {
      console.log('폼 제출:', formData);
      // API 호출 등
    }
  };
  
  return (
    <form onSubmit={handleSubmit}>
      <div>
        <input
          name="email"
          value={formData.email}
          onChange={(e) => setFormData({ ...formData, email: e.target.value })}
        />
        {errors.email && <p style={{ color: 'red' }}>{errors.email}</p>}
      </div>
      
      <div>
        <input
          name="password"
          type="password"
          value={formData.password}
          onChange={(e) => setFormData({ ...formData, password: e.target.value })}
        />
        {errors.password && <p style={{ color: 'red' }}>{errors.password}</p>}
      </div>
      
      <button type="submit">제출</button>
    </form>
  );
}
```

---

## 5. 실습: 회원가입 폼

### 실습 1: 기본 회원가입 폼

**요구사항:**
- 이름, 이메일, 비밀번호, 비밀번호 확인 입력
- 제출 버튼
- 제출 시 데이터 콘솔에 출력

**코드:**
```jsx
import { useState } from 'react';

function SignupForm() {
  const [formData, setFormData] = useState({
    name: '',
    email: '',
    password: '',
    confirmPassword: ''
  });
  
  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };
  
  const handleSubmit = (e) => {
    e.preventDefault();
    console.log('회원가입 데이터:', formData);
    // 실제로는 API 호출
  };
  
  return (
    <div style={{
      maxWidth: '400px',
      margin: '50px auto',
      padding: '2rem',
      border: '1px solid #ddd',
      borderRadius: '8px'
    }}>
      <h2>회원가입</h2>
      
      <form onSubmit={handleSubmit}>
        <div style={{ marginBottom: '1rem' }}>
          <label style={{ display: 'block', marginBottom: '0.5rem' }}>
            이름
          </label>
          <input
            type="text"
            name="name"
            value={formData.name}
            onChange={handleChange}
            placeholder="이름을 입력하세요"
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
            이메일
          </label>
          <input
            type="email"
            name="email"
            value={formData.email}
            onChange={handleChange}
            placeholder="이메일을 입력하세요"
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
        
        <div style={{ marginBottom: '1rem' }}>
          <label style={{ display: 'block', marginBottom: '0.5rem' }}>
            비밀번호 확인
          </label>
          <input
            type="password"
            name="confirmPassword"
            value={formData.confirmPassword}
            onChange={handleChange}
            placeholder="비밀번호를 다시 입력하세요"
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
          회원가입
        </button>
      </form>
    </div>
  );
}

export default SignupForm;
```

### 실습 2: 검증 추가

**요구사항:**
- 실시간 검증
- 에러 메시지 표시
- 비밀번호 일치 확인

**코드:**
```jsx
import { useState } from 'react';

function SignupForm() {
  const [formData, setFormData] = useState({
    name: '',
    email: '',
    password: '',
    confirmPassword: ''
  });
  
  const [errors, setErrors] = useState({});
  
  const validateField = (name, value) => {
    let error = '';
    
    switch (name) {
      case 'name':
        if (!value.trim()) {
          error = '이름을 입력하세요';
        } else if (value.length < 2) {
          error = '이름은 최소 2글자 이상이어야 합니다';
        }
        break;
        
      case 'email':
        if (!value.trim()) {
          error = '이메일을 입력하세요';
        } else if (!value.includes('@')) {
          error = '올바른 이메일 형식이 아닙니다';
        }
        break;
        
      case 'password':
        if (!value) {
          error = '비밀번호를 입력하세요';
        } else if (value.length < 6) {
          error = '비밀번호는 최소 6자 이상이어야 합니다';
        }
        break;
        
      case 'confirmPassword':
        if (!value) {
          error = '비밀번호 확인을 입력하세요';
        } else if (value !== formData.password) {
          error = '비밀번호가 일치하지 않습니다';
        }
        break;
        
      default:
        break;
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
    if (name === 'confirmPassword') {
      // 비밀번호 확인은 비밀번호와 비교
      const error = value !== formData.password 
        ? '비밀번호가 일치하지 않습니다' 
        : '';
      setErrors({ ...errors, [name]: error });
    } else {
      const error = validateField(name, value);
      setErrors({ ...errors, [name]: error });
    }
  };
  
  const handleSubmit = (e) => {
    e.preventDefault();
    
    // 전체 검증
    const newErrors = {};
    Object.keys(formData).forEach(key => {
      const error = validateField(key, formData[key]);
      if (error) {
        newErrors[key] = error;
      }
    });
    
    // 비밀번호 일치 확인
    if (formData.password !== formData.confirmPassword) {
      newErrors.confirmPassword = '비밀번호가 일치하지 않습니다';
    }
    
    setErrors(newErrors);
    
    if (Object.keys(newErrors).length === 0) {
      console.log('회원가입 성공:', formData);
      alert('회원가입이 완료되었습니다!');
      // 실제로는 API 호출
    }
  };
  
  const isFormValid = Object.keys(errors).length === 0 && 
    formData.name && 
    formData.email && 
    formData.password && 
    formData.confirmPassword;
  
  return (
    <div style={{
      maxWidth: '400px',
      margin: '50px auto',
      padding: '2rem',
      border: '1px solid #ddd',
      borderRadius: '8px'
    }}>
      <h2>회원가입</h2>
      
      <form onSubmit={handleSubmit}>
        <div style={{ marginBottom: '1rem' }}>
          <label style={{ display: 'block', marginBottom: '0.5rem' }}>
            이름
          </label>
          <input
            type="text"
            name="name"
            value={formData.name}
            onChange={handleChange}
            placeholder="이름을 입력하세요"
            style={{
              width: '100%',
              padding: '0.5rem',
              fontSize: '1rem',
              border: errors.name ? '1px solid red' : '1px solid #ddd',
              borderRadius: '4px'
            }}
          />
          {errors.name && (
            <p style={{ color: 'red', fontSize: '0.875rem', marginTop: '0.25rem' }}>
              {errors.name}
            </p>
          )}
        </div>
        
        <div style={{ marginBottom: '1rem' }}>
          <label style={{ display: 'block', marginBottom: '0.5rem' }}>
            이메일
          </label>
          <input
            type="email"
            name="email"
            value={formData.email}
            onChange={handleChange}
            placeholder="이메일을 입력하세요"
            style={{
              width: '100%',
              padding: '0.5rem',
              fontSize: '1rem',
              border: errors.email ? '1px solid red' : '1px solid #ddd',
              borderRadius: '4px'
            }}
          />
          {errors.email && (
            <p style={{ color: 'red', fontSize: '0.875rem', marginTop: '0.25rem' }}>
              {errors.email}
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
        
        <div style={{ marginBottom: '1rem' }}>
          <label style={{ display: 'block', marginBottom: '0.5rem' }}>
            비밀번호 확인
          </label>
          <input
            type="password"
            name="confirmPassword"
            value={formData.confirmPassword}
            onChange={handleChange}
            placeholder="비밀번호를 다시 입력하세요"
            style={{
              width: '100%',
              padding: '0.5rem',
              fontSize: '1rem',
              border: errors.confirmPassword ? '1px solid red' : '1px solid #ddd',
              borderRadius: '4px'
            }}
          />
          {errors.confirmPassword && (
            <p style={{ color: 'red', fontSize: '0.875rem', marginTop: '0.25rem' }}>
              {errors.confirmPassword}
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
          회원가입
        </button>
      </form>
    </div>
  );
}

export default SignupForm;
```

---

## 6. 실습 과제

### 과제 1: 연락처 폼

**요구사항:**
- 이름, 이메일, 전화번호, 메시지 입력
- 전화번호 형식 검증
- 제출 시 데이터 출력

### 과제 2: 설문조사 폼

**요구사항:**
- 라디오 버튼으로 선택
- 체크박스로 다중 선택
- 드롭다운으로 선택
- 제출 시 선택한 값 출력

### 과제 3: 파일 업로드 폼

**요구사항:**
- 파일 선택 입력
- 파일명 표시
- 파일 크기 제한
- 제출 시 파일 정보 출력

---

## 7. 자주 발생하는 오류

### 오류 1: 페이지가 새로고침됨

**원인:** `preventDefault()` 누락

**해결:**
```jsx
const handleSubmit = (e) => {
  e.preventDefault();  // 필수!
  // ...
};
```

### 오류 2: 입력값이 업데이트되지 않음

**원인:** `value`와 `onChange`를 함께 사용하지 않음

**해결:**
```jsx
// ✅ Controlled Component
<input
  value={value}
  onChange={(e) => setValue(e.target.value)}
/>
```

### 오류 3: 여러 입력 필드에서 상태 업데이트 실패

**원인:** 스프레드 연산자 누락

**해결:**
```jsx
// ✅
setFormData({
  ...formData,
  [name]: value
});
```

---

## 8. 다음 차시 예고

다음 차시에서는 **상태 끌어올리기**를 배웁니다:
- 상태 공유 문제
- 부모 컴포넌트에서 상태 관리
- 댓글 추가/삭제 기능 만들기

---

## 요약

### 핵심 개념

1. **Controlled Component**: React가 입력값을 제어
2. **폼 상태 관리**: 객체 State로 여러 필드 관리
3. **폼 검증**: 실시간 또는 제출 시 검증
4. **onSubmit**: 폼 제출 처리

### 필수 문법

```jsx
// Controlled Component
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

- [ ] Controlled Component 이해
- [ ] 입력값 상태 관리 가능
- [ ] 폼 제출 처리 가능
- [ ] 회원가입 폼 만들기 완료
- [ ] 폼 검증 구현 가능

---

**다음 차시에서 만나요! 🚀**

