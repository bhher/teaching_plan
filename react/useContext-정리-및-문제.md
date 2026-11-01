# useContext 정리 · 문제

예제: `react/example-context`  
비교: `react/example-props` · `Props-vs-Context-비교.md`

```bash
cd react/example-context
npm install
npm run dev
```

---

## 1. useContext란?

**Context**는 여러 컴포넌트가 **같은 값**을 props로 일일이 넘기지 않고 공유하게 해 줍니다.  
**useContext**는 그 값을 **구독(읽기)** 하는 Hook입니다.

```text
Props drilling (불편)
App → A → B → C  (C만 필요한데 A,B도 props 전달)

Context
AppProvider (값 보관)
   ├── Toolbar     ← useContext / useApp()
   └── ContentPanel ← useContext / useApp()
```

---

## 2. 핵심 3단계

| 단계 | API / 코드 | 역할 |
|------|------------|------|
| ① 만들기 | `createContext(null)` | Context 객체 생성 |
| ② 제공 | `<AppContext.Provider value={...}>` | 하위 트리에 값 공급 |
| ③ 사용 | `useContext(AppContext)` | 가까운 Provider 값 읽기 |

이 예제에서는 ③을 **`useApp()`** 커스텀 훅으로 감쌌습니다.

```jsx
export function useApp() {
  const ctx = useContext(AppContext);
  if (!ctx) throw new Error('AppProvider 안에서만 사용');
  return ctx;
}
```

---

## 3. example-context 구조

```text
App.jsx
  └── AppProvider          ← theme, count, toggleTheme, increment
        └── AppShell       ← useApp()로 theme (className)
              ├── Toolbar      ← useApp() — props 없음
              └── ContentPanel ← useApp() — props 없음
```

### AppContext.jsx (요약)

```jsx
const AppContext = createContext(null);

export function AppProvider({ children }) {
  const [theme, setTheme] = useState('light');
  const [count, setCount] = useState(0);

  const value = useMemo(
    () => ({ theme, count, toggleTheme, increment }),
    [theme, count]
  );

  return (
    <AppContext.Provider value={value}>{children}</AppContext.Provider>
  );
}
```

### 자식에서 사용

```jsx
const { theme, count, toggleTheme, increment } = useApp();
```

---

## 4. Props vs Context

| | Props | Context |
|--|-------|---------|
| 전달 | 부모가 자식에 명시적으로 | Provider 아래면 어디서든 |
| 중간 컴포넌트 | 안 써도 넘겨야 할 수 있음 | 안 넘겨도 됨 |
| 적합한 경우 | 가까운 1~2단계 | 테마·로그인·언어 등 전역에 가까운 값 |
| 남용 시 | 코드가 길어짐 | 리렌더·의존 관계가 흐려질 수 있음 |

> **모든 걸 Context로 하지 마세요.**  
> 로컬 UI 상태는 `useState` + props가 더 단순할 때가 많습니다.

---

## 5. 작성 순서 (복습)

1. `AppContext.jsx` — createContext · Provider · useApp  
2. `Toolbar.jsx` / `ContentPanel.jsx` — useApp()  
3. `App.jsx` — `<AppProvider>`로 감싸기  

---

## 6. 주의할 점

| 주의 | 설명 |
|------|------|
| Provider 밖 useApp | 에러 (이 예제는 throw) |
| value를 매 렌더 새 객체로 | 구독 컴포넌트 불필요 리렌더 ↑ → `useMemo`로 완화 |
| 너무 큰 value | 일부만 바뀌어도 구독자 전부 리렌더될 수 있음 |

---

## 7. 학생용 한 줄

```text
createContext → Provider(value) → useContext / useApp()

props 전달 대신, Provider 아래에서 직접 꺼내 쓴다.
```

---

# 문제

## 객관식

### Q1. useContext의 역할로 가장 알맞은 것은?

① DOM을 직접 조작한다  
② Context Provider가 제공한 값을 읽는다  
③ CSS만 바꾼다  
④ 라우터 URL을 만든다  

### Q2. props drilling이란?

① Context를 여러 개 만드는 것  
② 중간 컴포넌트가 쓰지 않는 props를 계속 넘기는 것  
③ useState를 안 쓰는 것  
④ Provider를 두 번 감싸는 것  

### Q3. `createContext` 다음에 보통 하는 일은?

① 바로 useContext만 호출  
② **Provider로 value를 하위 트리에 제공**  
③ fetch만 호출  
④ index.html 수정  

### Q4. 이 예제에서 Toolbar가 theme를 얻는 방법은?

① props로만 받는다  
② **useApp() / useContext**  
③ localStorage만 본다  
④ CSS 변수만 본다  

### Q5. `useApp`을 AppProvider **밖**에서 호출하면?

① 항상 정상 동작  
② **에러(이 예제는 throw)**  
③ 자동으로 light 테마  
④ count만 0  

### Q6. Provider의 `value={{ theme, count }}`를 매 렌더 새 객체로 만들면?

① 아무 영향 없음  
② 구독 컴포넌트가 **자주 리렌더**될 수 있음  
③ Context가 삭제됨  
④ props drilling이 생김  

### Q7. Context를 쓰기에 **덜** 적절한 경우는?

① 앱 전체 테마  
② 로그인 사용자 정보  
③ **버튼 하나 안의 로컬 hover 여부**  
④ 다국어(언어) 설정  

### Q8. example-props와 example-context의 공통점은?

① 둘 다 Provider만 쓴다  
② **비슷한 UI(테마·카운트)를 다른 방식으로 구현**  
③ Context가 같다  
④ props가 없다  

---

## 단답 · 서술

### Q9
Context를 쓸 때 필요한 세 가지를 적으세요.  
(`createContext` / `Provider` / `useContext`)

### Q10
`useMemo`로 `value`를 감싸는 이유를 한 줄로 쓰세요.

### Q11
다음 빈칸을 채우세요.

```jsx
const AppContext = ___________(null);

<AppContext.__________ value={value}>
  {children}
</AppContext.__________>

const ctx = ___________(AppContext);
```

### Q12
Toolbar에 props를 주지 않아도 theme를 쓸 수 있는 이유를 쓰세요.

---

## 실습 문제

### 실습 1 ⭐
`AppContext`에 `username` state(`'게스트'`)와 `setUsername`을 추가하고,  
ContentPanel에 이름을 표시하세요. (useApp으로 읽기)

### 실습 2 ⭐⭐
Toolbar에 입력창을 두고 username을 바꾸게 하세요.  
ContentPanel은 입력창 없이 **표시만** 합니다. (props 전달 없이 Context만)

### 실습 3 ⭐⭐
`example-props`의 App을 열어, 같은 기능을 props로 전달하는 코드와  
context의 Toolbar를 **나란히 비교**해 차이 3가지를 메모하세요.

### 실습 4 ⭐⭐⭐ (변환)
작은 Counter를 props 버전으로 만든 뒤, Context 버전으로 바꾸세요.

- props: `App` → `Display(count)` · `Button(onIncrement)`  
- context: Provider에 count · increment, 두 컴포넌트는 useContext만  

---

# 정답

## 객관식

| 번호 | 정답 |
|------|------|
| Q1 | ② |
| Q2 | ② |
| Q3 | ② |
| Q4 | ② |
| Q5 | ② |
| Q6 | ② |
| Q7 | ③ |
| Q8 | ② |

## 단답 · 서술

**Q9** `createContext` · `Provider` · `useContext` (또는 useApp)

**Q10** value 객체 참조를 안정시켜 **불필요한 리렌더를 줄이기 위해**

**Q11**
```jsx
createContext
Provider / Provider
useContext
```

**Q12** `AppProvider` 아래라서 Context value를 `useApp()`으로 직접 구독하기 때문  
(중간에 props로 theme을 넘길 필요가 없음)

## 실습 가이드 (요지)

**실습 1~2** — `AppProvider`의 state/value에 `username` 추가 → Toolbar/ContentPanel에서 `useApp()`  

**실습 4** — props 트리와 Provider 트리를 각각 만든 뒤, Button/Display에서 props 제거·useContext 추가

---

## 체크리스트

- [ ] createContext → Provider → useContext 흐름을 말할 수 있다  
- [ ] props drilling과 Context 차이를 구분한다  
- [ ] Provider 밖에서 쓰면 안 된다는 것을 안다  
- [ ] 테마·인증 등 “여러 곳에서 쓰는 값”에 Context를 쓴다  
