# JSX vs TSX 비교 설명

예제 앱: `react/jsx-tsx-compare`

```bash
cd react/jsx-tsx-compare
npm install
npm run dev
```

같은 UI를 **`.jsx` / `.tsx` 두 파일**로 나란히 보여 줍니다.

---

## 1. JSX와 TSX란?

| | JSX | TSX |
|--|-----|-----|
| 뜻 | JavaScript + XML 형태 UI 문법 | **TypeScript** + JSX |
| 파일 | `Counter.jsx` | `Counter.tsx` |
| 역할 | React 컴포넌트 UI 작성 | UI + **타입** |

둘 다 브라우저에 그대로 가지 않고, 빌드 과정에서 **일반 JavaScript**로 바뀝니다.  
그래서 **화면 결과는 같고**, 차이는 **개발할 때 타입을 검사하느냐**입니다.

```text
.jsx  →  (Babel/Vite)  →  JS  →  브라우저
.tsx  →  (TS 검사 + 변환) →  JS  →  브라우저
              ↑
         여기서 props/state 오류를 미리 잡음
```

---

## 2. 가장 쉬운 비교 — props

### JSX

```jsx
function Counter({ label }) {
  return <p>{label}</p>;
}

// <Counter label={123} />  ← 실행해 보기 전에는 모름
```

### TSX

```tsx
type CounterProps = { label: string };

function Counter({ label }: CounterProps) {
  return <p>{label}</p>;
}

// <Counter label={123} />  ← 에디터 빨간줄, 빌드 실패
```

---

## 3. useState 비교

### JSX

```jsx
const [count, setCount] = useState(0);
// 초기값으로 타입을 추측하지만, 검사 도구가 약함
```

### TSX

```tsx
const [count, setCount] = useState<number>(0);
// setCount("1") 불가
```

---

## 4. 이 예제 앱 구성

```text
src/examples/
  Counter.jsx     ← 타입 없음
  Counter.tsx     ← CounterProps, useState<number>
  UserCard.jsx
  UserCard.tsx    ← User 타입
App.tsx           ← 좌(JSX) / 우(TSX) 나란히 실행
```

| 섹션 | 배우는 점 |
|------|-----------|
| Counter | useState · 이벤트 · props 한 줄 |
| UserCard | 객체 props · type 정의 |
| 표 | 확장자 · 오류 시점 · 학습 비용 |

---

## 5. 언제 무엇을 쓰나? (수업용)

| 상황 | 추천 |
|------|------|
| React 문법 처음 | **JSX**로 useState, props, map부터 |
| 팀 프로젝트 · 규모↑ | **TSX** (실수 조기 발견) |
| 기존 JS 코드 유지 | JSX 유지 가능 |
| 취업/실무 대비 | TSX를 목표로 두면 유리 |

> “TSX가 JSX를 대체한다”기보다,  
> **JSX 문법 + TypeScript 타입**이 붙은 것이 TSX입니다.

---

## 6. 학생용 한 줄

```text
JSX  = React UI 문법 (JS)
TSX  = React UI 문법 + 타입 (TS)

화면은 같고, TSX는 작성 중에 실수를 더 잘 잡아 준다.
```

---

## 7. 실습 문제 (짧게)

1. `Counter.tsx`의 `label`을 `number`로 바꾸고, `App`에서 문자열을 넘기면 어떻게 되는지 확인하세요.  
2. `UserCard.tsx`에 `age: string`을 넘기도록 `sampleUser`를 일부러 깨 보세요. (빌드/타입 오류)  
3. 같은 컴포넌트를 JSX만 있는 프로젝트(`example-todo-jsx`)와 TSX 프로젝트(`example-ts-todo`)에서 한 번씩 열어 비교하세요.
