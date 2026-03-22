# 20차시. Context API

## 학습 목표
- createContext를 사용할 수 있다
- useContext Hook을 사용할 수 있다
- Provider로 Context를 제공할 수 있다
- 로그인 상태를 전역으로 관리할 수 있다

---

## 1. Context API 기본 사용법

### 1.1 Context 생성

**createContext:**
```jsx
import { createContext } from 'react';

const UserContext = createContext();
```

### 1.2 Provider 설정

**Provider로 감싸기:**
```jsx
function App() {
  const [user, setUser] = useState(null);
  
  return (
    <UserContext.Provider value={{ user, setUser }}>
      <ChildComponent />
    </UserContext.Provider>
  );
}
```

### 1.3 useContext 사용

**값 가져오기:**
```jsx
import { useContext } from 'react';
import { UserContext } from './UserContext';

function ChildComponent() {
  const { user, setUser } = useContext(UserContext);
  
  return <div>{user?.name}</div>;
}
```

---

## 2. 실습: 로그인 상태 전역 관리

### 실습 1: AuthContext 생성

**contexts/AuthContext.jsx:**
```jsx
import { createContext, useState, useContext } from 'react';

const AuthContext = createContext();

export function AuthProvider({ children }) {
  const [user, setUser] = useState(null);
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  
  const login = (userData) => {
    setUser(userData);
    setIsAuthenticated(true);
  };
  
  const logout = () => {
    setUser(null);
    setIsAuthenticated(false);
  };
  
  return (
    <AuthContext.Provider value={{
      user,
      isAuthenticated,
      login,
      logout
    }}>
      {children}
    </AuthContext.Provider>
  );
}

export function useAuth() {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error('useAuth는 AuthProvider 내에서 사용해야 합니다');
  }
  return context;
}
```

**App.jsx:**
```jsx
import { AuthProvider } from './contexts/AuthContext';

function App() {
  return (
    <AuthProvider>
      <Routes>
        {/* 라우트들 */}
      </Routes>
    </AuthProvider>
  );
}
```

**사용:**
```jsx
import { useAuth } from './contexts/AuthContext';

function LoginButton() {
  const { login, isAuthenticated } = useAuth();
  
  const handleLogin = () => {
    login({ name: '홍길동', email: 'hong@example.com' });
  };
  
  return (
    <div>
      {isAuthenticated ? (
        <p>로그인됨</p>
      ) : (
        <button onClick={handleLogin}>로그인</button>
      )}
    </div>
  );
}
```

---

## 3. 실습 과제

### 과제 1: 테마 Context

**요구사항:**
- 다크/라이트 모드 전역 관리
- 모든 컴포넌트에서 테마 사용

---

## 4. 다음 단계 예고

다음 단계에서는 **실무 패턴 & 배포**를 배웁니다:
- React 스타일링
- 컴포넌트 최적화
- 빌드 & 배포

---

## 요약

### 핵심 개념

1. **createContext**: Context 생성
2. **Provider**: Context 제공
3. **useContext**: Context 사용
4. **Custom Hook**: 재사용 가능한 Hook

### 필수 문법

```jsx
// Context 생성
const MyContext = createContext();

// Provider
<MyContext.Provider value={value}>
  {children}
</MyContext.Provider>

// 사용
const value = useContext(MyContext);
```

### 체크리스트

- [ ] createContext 사용 가능
- [ ] useContext 사용 가능
- [ ] Provider 설정 가능
- [ ] 로그인 상태 전역 관리 완료

---

**다음 단계에서 만나요! 🚀**





