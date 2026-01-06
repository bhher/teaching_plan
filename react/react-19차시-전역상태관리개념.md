# 19차시. 전역 상태 관리 개념

## 학습 목표
- Props drilling 문제를 이해할 수 있다
- 전역 상태 관리의 필요성을 이해할 수 있다
- Context API의 개념을 이해할 수 있다
- 상태 관리 라이브러리 개요를 이해할 수 있다

---

## 1. Props Drilling 문제

### 1.1 Props Drilling이란?

**Props Drilling**은 여러 컴포넌트를 거쳐 Props를 전달하는 것입니다.

**예시:**
```jsx
function App() {
  const [user, setUser] = useState(null);
  
  return (
    <Layout user={user} setUser={setUser}>
      <Header user={user} />
      <Content user={user}>
        <Sidebar user={user} />
        <Main user={user}>
          <PostList user={user} />
        </Main>
      </Content>
    </Layout>
  );
}
```

**문제점:**
- ❌ 중간 컴포넌트들이 사용하지 않는 Props를 전달해야 함
- ❌ 코드가 복잡해짐
- ❌ 유지보수 어려움

### 1.2 해결 방법

**전역 상태 관리:**
- Context API
- Redux
- Zustand
- Recoil

---

## 2. 전역 상태 관리의 필요성

### 2.1 언제 필요한가?

**필요한 경우:**
- 여러 컴포넌트에서 공유하는 상태
- 깊은 컴포넌트 트리에서 상태 전달
- 인증 상태, 테마 등

**예시:**
- 로그인 상태
- 사용자 정보
- 테마 설정
- 언어 설정

---

## 3. Context API 개념

### 3.1 Context API란?

**Context API**는 React에서 제공하는 전역 상태 관리 방법입니다.

**특징:**
- ✅ React 내장 (별도 설치 불필요)
- ✅ 간단한 사용법
- ✅ 중소 규모 프로젝트에 적합

**동작 원리:**
```
Context 생성
    ↓
Provider로 감싸기
    ↓
Consumer에서 사용
```

---

## 4. 상태 관리 라이브러리 비교

### 4.1 주요 라이브러리

| 라이브러리 | 특징 | 사용 시기 |
|-----------|------|----------|
| Context API | React 내장, 간단 | 중소 규모 |
| Redux | 강력한 기능, 복잡 | 대규모 |
| Zustand | 가벼움, 간단 | 중소 규모 |
| Recoil | Facebook 개발 | 복잡한 상태 |

---

## 5. 다음 차시 예고

다음 차시에서는 **Context API**를 배웁니다:
- createContext
- useContext
- Provider 설정
- 로그인 상태 전역 관리

---

## 요약

### 핵심 개념

1. **Props Drilling**: 여러 컴포넌트를 거쳐 Props 전달
2. **전역 상태 관리**: 공유 상태를 중앙에서 관리
3. **Context API**: React 내장 전역 상태 관리

### 체크리스트

- [ ] Props drilling 문제 이해
- [ ] 전역 상태 관리 필요성 이해
- [ ] Context API 개념 이해

---

**다음 차시에서 만나요! 🚀**

