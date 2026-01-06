# 22차시. 컴포넌트 최적화

## 학습 목표
- React.memo의 개념을 이해할 수 있다
- useCallback을 사용할 수 있다
- useMemo의 개념을 이해할 수 있다
- 불필요한 리렌더링을 방지할 수 있다

---

## 1. React.memo

### 1.1 개념

**React.memo**는 컴포넌트를 메모이제이션하여 불필요한 리렌더링을 방지합니다.

**사용법:**
```jsx
import { memo } from 'react';

const Button = memo(function Button({ onClick, children }) {
  return <button onClick={onClick}>{children}</button>;
});
```

**동작:**
- Props가 변경되지 않으면 리렌더링하지 않음

---

## 2. useCallback

### 2.1 개념

**useCallback**은 함수를 메모이제이션합니다.

**사용법:**
```jsx
import { useCallback } from 'react';

function Parent() {
  const handleClick = useCallback(() => {
    console.log('클릭됨');
  }, []); // 의존성 배열
  
  return <Child onClick={handleClick} />;
}
```

**의존성 배열:**
```jsx
const handleClick = useCallback(() => {
  console.log(count);
}, [count]); // count가 변경될 때만 함수 재생성
```

---

## 3. useMemo

### 3.1 개념

**useMemo**는 계산 결과를 메모이제이션합니다.

**사용법:**
```jsx
import { useMemo } from 'react';

function ExpensiveComponent({ items }) {
  const sortedItems = useMemo(() => {
    return items.sort((a, b) => a.value - b.value);
  }, [items]);
  
  return <div>{/* sortedItems 사용 */}</div>;
}
```

**주의:**
- 계산 비용이 높을 때만 사용
- 남용하지 않기

---

## 4. 다음 차시 예고

다음 차시에서는 **빌드 & 배포**를 배웁니다:
- npm run build
- Vercel 배포
- 환경 변수 설정

---

## 요약

### 핵심 개념

1. **React.memo**: 컴포넌트 메모이제이션
2. **useCallback**: 함수 메모이제이션
3. **useMemo**: 계산 결과 메모이제이션

### 체크리스트

- [ ] React.memo 이해
- [ ] useCallback 사용 가능
- [ ] useMemo 개념 이해

---

**다음 차시에서 만나요! 🚀**

