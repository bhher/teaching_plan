# 21차시. React 스타일링

## 학습 목표
- CSS Module의 개념을 이해할 수 있다
- styled-components의 개념을 이해할 수 있다
- Tailwind CSS의 개요를 이해할 수 있다
- 다양한 스타일링 방법을 비교할 수 있다

---

## 1. 스타일링 방법 개요

### 1.1 주요 방법

1. **인라인 스타일**
2. **CSS 파일**
3. **CSS Module**
4. **styled-components**
5. **Tailwind CSS**

---

## 2. CSS Module

### 2.1 CSS Module이란?

**CSS Module**은 CSS 클래스명을 자동으로 고유하게 만드는 방법입니다.

**사용법:**
```jsx
// Button.module.css
.button {
  padding: 10px 20px;
  background-color: #007bff;
  color: white;
}

// Button.jsx
import styles from './Button.module.css';

function Button() {
  return <button className={styles.button}>클릭</button>;
}
```

**장점:**
- ✅ 클래스명 충돌 방지
- ✅ 기존 CSS 사용 가능

---

## 3. styled-components

### 3.1 개념

**styled-components**는 CSS-in-JS 라이브러리입니다.

**설치:**
```bash
npm install styled-components
```

**사용:**
```jsx
import styled from 'styled-components';

const Button = styled.button`
  padding: 10px 20px;
  background-color: #007bff;
  color: white;
`;

function App() {
  return <Button>클릭</Button>;
}
```

---

## 4. Tailwind CSS

### 4.1 개념

**Tailwind CSS**는 유틸리티 우선 CSS 프레임워크입니다.

**설치:**
```bash
npm install -D tailwindcss
npx tailwindcss init
```

**사용:**
```jsx
function Button() {
  return (
    <button className="px-4 py-2 bg-blue-500 text-white rounded">
      클릭
    </button>
  );
}
```

---

## 5. 비교

| 방법 | 장점 | 단점 |
|------|------|------|
| 인라인 스타일 | 간단 | 재사용 어려움 |
| CSS 파일 | 전통적 | 클래스명 충돌 |
| CSS Module | 충돌 방지 | 설정 필요 |
| styled-components | 동적 스타일 | 런타임 오버헤드 |
| Tailwind | 빠른 개발 | 학습 필요 |

---

## 6. 다음 차시 예고

다음 차시에서는 **컴포넌트 최적화**를 배웁니다:
- React.memo
- useCallback
- useMemo

---

## 요약

### 핵심 개념

1. **CSS Module**: 클래스명 자동 고유화
2. **styled-components**: CSS-in-JS
3. **Tailwind CSS**: 유틸리티 우선

### 체크리스트

- [ ] CSS Module 개념 이해
- [ ] styled-components 개념 이해
- [ ] Tailwind CSS 개요 이해

---

**다음 차시에서 만나요! 🚀**

