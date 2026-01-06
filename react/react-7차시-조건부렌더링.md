# 7차시. 조건부 렌더링

## 학습 목표
- 조건부 렌더링의 개념을 이해할 수 있다
- 삼항 연산자를 사용하여 조건부 렌더링을 할 수 있다
- && 연산자를 사용하여 조건부 렌더링을 할 수 있다
- 조건에 따라 다른 화면을 보여줄 수 있다
- 로그인/로그아웃 화면을 만들 수 있다

---

## 1. 조건부 렌더링이란?

### 1.1 조건부 렌더링의 정의

**조건부 렌더링**은 특정 조건에 따라 다른 UI를 보여주는 것입니다.

**예시:**
- 로그인 상태에 따라 다른 화면 표시
- 데이터 유무에 따라 다른 메시지 표시
- 권한에 따라 다른 버튼 표시

### 1.2 조건부 렌더링이 필요한 이유

**조건 없이 (문제):**
```jsx
function App() {
  return (
    <div>
      <h1>환영합니다!</h1>
      <button>로그아웃</button>
      <h1>로그인이 필요합니다</h1>
      <button>로그인</button>
    </div>
  );
}
```

**문제점:**
- ❌ 항상 모든 요소가 표시됨
- ❌ 사용자 상태에 맞지 않는 UI

**조건부 렌더링 사용 (해결):**
```jsx
function App() {
  const isLoggedIn = true;
  
  return (
    <div>
      {isLoggedIn ? (
        <>
          <h1>환영합니다!</h1>
          <button>로그아웃</button>
        </>
      ) : (
        <>
          <h1>로그인이 필요합니다</h1>
          <button>로그인</button>
        </>
      )}
    </div>
  );
}
```

---

## 2. 삼항 연산자 (Ternary Operator)

### 2.1 기본 문법

**형식:**
```jsx
{조건 ? true일때 : false일때}
```

**예시:**
```jsx
function Greeting({ isLoggedIn }) {
  return (
    <div>
      {isLoggedIn ? (
        <h1>환영합니다!</h1>
      ) : (
        <h1>로그인이 필요합니다</h1>
      )}
    </div>
  );
}
```

### 2.2 다양한 사용 예시

**간단한 텍스트:**
```jsx
function Status({ isOnline }) {
  return <p>상태: {isOnline ? '온라인' : '오프라인'}</p>;
}
```

**복잡한 JSX:**
```jsx
function UserProfile({ user }) {
  return (
    <div>
      {user ? (
        <div>
          <h2>{user.name}</h2>
          <p>{user.email}</p>
        </div>
      ) : (
        <p>사용자 정보가 없습니다</p>
      )}
    </div>
  );
}
```

**인라인 스타일:**
```jsx
function Message({ isError }) {
  return (
    <p style={{ color: isError ? 'red' : 'green' }}>
      {isError ? '에러가 발생했습니다' : '성공했습니다'}
    </p>
  );
}
```

### 2.3 중첩된 삼항 연산자

**여러 조건:**
```jsx
function Score({ score }) {
  return (
    <div>
      {score >= 90 ? (
        <p>우수</p>
      ) : score >= 70 ? (
        <p>양호</p>
      ) : (
        <p>보통</p>
      )}
    </div>
  );
}
```

**주의:** 너무 복잡하면 가독성이 떨어지므로 함수로 분리하는 것이 좋습니다.

---

## 3. && 연산자 (Logical AND)

### 3.1 기본 문법

**형식:**
```jsx
{조건 && 렌더링할내용}
```

**동작:**
- 조건이 `true`면 오른쪽 내용 렌더링
- 조건이 `false`면 아무것도 렌더링하지 않음

**예시:**
```jsx
function Notification({ hasNotification }) {
  return (
    <div>
      {hasNotification && <p>새 알림이 있습니다!</p>}
    </div>
  );
}
```

### 3.2 && 연산자 사용 예시

**에러 메시지:**
```jsx
function Form({ error }) {
  return (
    <form>
      <input type="text" />
      {error && <p style={{ color: 'red' }}>{error}</p>}
    </form>
  );
}
```

**로딩 표시:**
```jsx
function DataDisplay({ isLoading, data }) {
  return (
    <div>
      {isLoading && <p>로딩 중...</p>}
      {data && <p>데이터: {data}</p>}
    </div>
  );
}
```

**리스트가 있을 때만 표시:**
```jsx
function TodoList({ todos }) {
  return (
    <div>
      {todos.length > 0 && (
        <ul>
          {todos.map(todo => <li key={todo.id}>{todo.text}</li>)}
        </ul>
      )}
    </div>
  );
}
```

### 3.3 && vs 삼항 연산자

**&& 연산자:**
- 조건이 `true`일 때만 표시
- `false`일 때는 아무것도 표시하지 않음

**삼항 연산자:**
- 조건에 따라 다른 내용 표시
- 항상 무언가를 표시함

**선택 기준:**
- 단순히 표시/숨김: `&&` 사용
- 다른 내용을 표시: 삼항 연산자 사용

---

## 4. 조건부 렌더링 패턴

### 패턴 1: Early Return

**함수 내에서 조건에 따라 다른 값 반환:**
```jsx
function UserProfile({ user }) {
  if (!user) {
    return <p>사용자 정보가 없습니다</p>;
  }
  
  return (
    <div>
      <h2>{user.name}</h2>
      <p>{user.email}</p>
    </div>
  );
}
```

**장점:**
- 가독성이 좋음
- 중첩이 줄어듦

### 패턴 2: 변수에 JSX 저장

**조건에 따라 변수에 JSX 할당:**
```jsx
function App({ isLoggedIn }) {
  let content;
  
  if (isLoggedIn) {
    content = <WelcomeScreen />;
  } else {
    content = <LoginScreen />;
  }
  
  return <div>{content}</div>;
}
```

### 패턴 3: 함수로 분리

**복잡한 조건을 함수로 분리:**
```jsx
function App({ user, isLoading }) {
  const renderContent = () => {
    if (isLoading) {
      return <LoadingSpinner />;
    }
    
    if (!user) {
      return <LoginForm />;
    }
    
    return <UserDashboard user={user} />;
  };
  
  return <div>{renderContent()}</div>;
}
```

---

## 5. 실습: 로그인/로그아웃 화면

### 실습 1: 기본 로그인 상태 관리

**요구사항:**
- 로그인 상태에 따라 다른 화면 표시
- 로그인/로그아웃 버튼

**코드:**
```jsx
import { useState } from 'react';

function LoginApp() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [username, setUsername] = useState('');
  
  const handleLogin = () => {
    if (username.trim()) {
      setIsLoggedIn(true);
    }
  };
  
  const handleLogout = () => {
    setIsLoggedIn(false);
    setUsername('');
  };
  
  return (
    <div style={{ padding: '2rem', maxWidth: '400px', margin: '0 auto' }}>
      {isLoggedIn ? (
        // 로그인된 화면
        <div>
          <h1>환영합니다, {username}님!</h1>
          <p>로그인 상태입니다.</p>
          <button 
            onClick={handleLogout}
            style={{
              padding: '10px 20px',
              backgroundColor: '#ff4444',
              color: 'white',
              border: 'none',
              borderRadius: '5px',
              cursor: 'pointer'
            }}
          >
            로그아웃
          </button>
        </div>
      ) : (
        // 로그인 화면
        <div>
          <h1>로그인이 필요합니다</h1>
          <input
            type="text"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            placeholder="사용자명을 입력하세요"
            style={{
              width: '100%',
              padding: '0.5rem',
              marginBottom: '1rem',
              fontSize: '1rem'
            }}
          />
          <button 
            onClick={handleLogin}
            style={{
              padding: '10px 20px',
              backgroundColor: '#007bff',
              color: 'white',
              border: 'none',
              borderRadius: '5px',
              cursor: 'pointer'
            }}
          >
            로그인
          </button>
        </div>
      )}
    </div>
  );
}

export default LoginApp;
```

### 실습 2: 로그인 폼과 대시보드 분리

**요구사항:**
- 로그인 폼 컴포넌트 분리
- 대시보드 컴포넌트 분리
- 조건부 렌더링으로 전환

**코드:**
```jsx
import { useState } from 'react';

// 로그인 폼 컴포넌트
function LoginForm({ onLogin }) {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  
  const handleSubmit = (e) => {
    e.preventDefault();
    if (username.trim() && password.trim()) {
      onLogin(username);
    }
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
        <input
          type="text"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          placeholder="사용자명"
          style={{
            width: '100%',
            padding: '0.5rem',
            marginBottom: '1rem'
          }}
        />
        <input
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          placeholder="비밀번호"
          style={{
            width: '100%',
            padding: '0.5rem',
            marginBottom: '1rem'
          }}
        />
        <button type="submit" style={{
          width: '100%',
          padding: '0.75rem',
          backgroundColor: '#007bff',
          color: 'white',
          border: 'none',
          borderRadius: '5px',
          cursor: 'pointer'
        }}>
          로그인
        </button>
      </form>
    </div>
  );
}

// 대시보드 컴포넌트
function Dashboard({ username, onLogout }) {
  return (
    <div style={{
      maxWidth: '600px',
      margin: '50px auto',
      padding: '2rem'
    }}>
      <div style={{
        display: 'flex',
        justifyContent: 'space-between',
        alignItems: 'center',
        marginBottom: '2rem'
      }}>
        <h1>대시보드</h1>
        <button onClick={onLogout} style={{
          padding: '10px 20px',
          backgroundColor: '#ff4444',
          color: 'white',
          border: 'none',
          borderRadius: '5px',
          cursor: 'pointer'
        }}>
          로그아웃
        </button>
      </div>
      
      <div style={{
        backgroundColor: '#f5f5f5',
        padding: '2rem',
        borderRadius: '8px'
      }}>
        <h2>환영합니다, {username}님!</h2>
        <p>로그인에 성공했습니다.</p>
        <p>여기에 대시보드 내용이 들어갑니다.</p>
      </div>
    </div>
  );
}

// 메인 App 컴포넌트
function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [username, setUsername] = useState('');
  
  const handleLogin = (user) => {
    setUsername(user);
    setIsLoggedIn(true);
  };
  
  const handleLogout = () => {
    setIsLoggedIn(false);
    setUsername('');
  };
  
  return (
    <div>
      {isLoggedIn ? (
        <Dashboard username={username} onLogout={handleLogout} />
      ) : (
        <LoginForm onLogin={handleLogin} />
      )}
    </div>
  );
}

export default App;
```

### 실습 3: 권한에 따른 메뉴 표시

**요구사항:**
- 사용자 역할(admin, user)에 따라 다른 메뉴 표시
- 관리자만 볼 수 있는 메뉴

**코드:**
```jsx
import { useState } from 'react';

function App() {
  const [user, setUser] = useState(null);
  
  const handleLogin = (role) => {
    setUser({ role });
  };
  
  const handleLogout = () => {
    setUser(null);
  };
  
  return (
    <div>
      {user ? (
        <div>
          <nav style={{
            backgroundColor: '#333',
            color: 'white',
            padding: '1rem',
            marginBottom: '2rem'
          }}>
            <a href="#" style={{ color: 'white', marginRight: '1rem' }}>
              홈
            </a>
            <a href="#" style={{ color: 'white', marginRight: '1rem' }}>
              프로필
            </a>
            {user.role === 'admin' && (
              <>
                <a href="#" style={{ color: 'white', marginRight: '1rem' }}>
                  관리자 페이지
                </a>
                <a href="#" style={{ color: 'white', marginRight: '1rem' }}>
                  사용자 관리
                </a>
              </>
            )}
            <button onClick={handleLogout} style={{
              float: 'right',
              backgroundColor: '#ff4444',
              color: 'white',
              border: 'none',
              padding: '5px 10px',
              cursor: 'pointer'
            }}>
              로그아웃
            </button>
          </nav>
          
          <main style={{ padding: '2rem' }}>
            <h1>환영합니다!</h1>
            <p>역할: {user.role}</p>
          </main>
        </div>
      ) : (
        <div style={{ textAlign: 'center', padding: '2rem' }}>
          <h1>로그인</h1>
          <div style={{ display: 'flex', gap: '1rem', justifyContent: 'center' }}>
            <button onClick={() => handleLogin('user')}>
              일반 사용자로 로그인
            </button>
            <button onClick={() => handleLogin('admin')}>
              관리자로 로그인
            </button>
          </div>
        </div>
      )}
    </div>
  );
}

export default App;
```

---

## 6. 실습 과제

### 과제 1: 다크 모드 토글

**요구사항:**
- 다크 모드 상태에 따라 배경색과 텍스트 색상 변경
- 토글 버튼으로 모드 전환

### 과제 2: 알림 표시

**요구사항:**
- 알림이 있을 때만 알림 배지 표시
- 알림 개수 표시
- 알림이 없으면 배지 숨김

### 과제 3: 사용자 프로필

**요구사항:**
- 사용자 정보가 있으면 프로필 표시
- 사용자 정보가 없으면 "로그인 필요" 메시지
- 프로필 사진이 있으면 표시, 없으면 기본 이미지

---

## 7. 자주 발생하는 오류

### 오류 1: `0`이 화면에 표시됨

**원인:** `&&` 연산자에서 숫자 0 사용

**해결:**
```jsx
// ❌
{count && <p>카운트: {count}</p>}  // count가 0이면 0이 표시됨

// ✅
{count > 0 && <p>카운트: {count}</p>}
// 또는
{count !== 0 && <p>카운트: {count}</p>}
```

### 오류 2: 조건이 항상 true/false

**원인:** 비교 연산자 오류

**해결:**
```jsx
// ❌
{user = null && <p>사용자 없음</p>}  // 할당 연산자 사용

// ✅
{user === null && <p>사용자 없음</p>}  // 비교 연산자 사용
```

### 오류 3: JSX를 조건 없이 반환

**원인:** 조건문 밖에서 JSX 반환

**해결:**
```jsx
// ❌
function Component() {
  if (condition) {
    return <div>A</div>;
  }
  <div>B</div>;  // 반환되지 않음
}

// ✅
function Component() {
  if (condition) {
    return <div>A</div>;
  }
  return <div>B</div>;
}
```

---

## 8. 조건부 렌더링 모범 사례

### 사례 1: 명확한 조건 사용

**✅ 좋은 예:**
```jsx
{isLoggedIn && <Dashboard />}
{user !== null && <UserProfile user={user} />}
{todos.length > 0 && <TodoList todos={todos} />}
```

**❌ 나쁜 예:**
```jsx
{user && <UserProfile user={user} />}  // user가 빈 객체일 수 있음
```

### 사례 2: 복잡한 조건은 함수로 분리

**✅ 좋은 예:**
```jsx
function App({ user, isLoading }) {
  const shouldShowDashboard = () => {
    return user && !isLoading;
  };
  
  return (
    <div>
      {shouldShowDashboard() && <Dashboard />}
    </div>
  );
}
```

### 사례 3: Early Return 활용

**✅ 좋은 예:**
```jsx
function Component({ data }) {
  if (!data) {
    return <p>데이터가 없습니다</p>;
  }
  
  return <DataDisplay data={data} />;
}
```

---

## 9. 다음 차시 예고

다음 차시에서는 **리스트 렌더링**을 배웁니다:
- map 함수 사용
- key 개념
- 배열 데이터 출력
- 게시글 목록 출력하기

---

## 요약

### 핵심 개념

1. **조건부 렌더링**: 조건에 따라 다른 UI 표시
2. **삼항 연산자**: `조건 ? true : false`
3. **&& 연산자**: 조건이 true일 때만 표시
4. **Early Return**: 함수 내에서 조건에 따라 반환

### 필수 문법

```jsx
// 삼항 연산자
{조건 ? <ComponentA /> : <ComponentB />}

// && 연산자
{조건 && <Component />}

// Early Return
if (!조건) {
  return <Fallback />;
}
return <MainComponent />;
```

### 체크리스트

- [ ] 조건부 렌더링 개념 이해
- [ ] 삼항 연산자 사용 가능
- [ ] && 연산자 사용 가능
- [ ] 로그인/로그아웃 화면 만들기 완료
- [ ] 조건에 따른 화면 제어 이해

---

**다음 차시에서 만나요! 🚀**

