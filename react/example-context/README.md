# example-context

**useContext + Provider**로 동일한 기능을 구현합니다. 자식은 `useApp()`으로 상태를 읽습니다.

## 실행

```bash
cd react/example-context
npm install
npm run dev
```

## 구조

```
App.jsx
  └── AppProvider (context/AppContext.jsx)
        ├── AppShell   ← useApp()으로 theme만으로 className
        ├── Toolbar.jsx    ← useApp() — props 없음
        └── ContentPanel.jsx ← useApp() — props 없음
```

- `AppContext.jsx`: `createContext`, `AppProvider`, `useApp` 훅
- `useMemo`로 value 객체 참조를 안정화해 불필요한 리렌더를 줄입니다.

## example-props와 비교

| | props | context |
|------|--------|---------|
| 데이터 전달 | 부모에서 자식으로 명시적 | Provider 하위 어디서든 `useApp()` |
| 중간 컴포넌트 | props를 계속 넘겨야 할 수 있음 | 넘기지 않아도 됨 |
