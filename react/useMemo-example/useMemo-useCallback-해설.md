# useMemo & useCallback 예제 해설

`useMemo-example`은 **useMemo / useCallback**을 탭으로 나눠 보여주는 수업용 Vite 예제입니다.

## 실행 방법

```bash
npm install
npm run dev
```

브라우저에서 탭을 클릭하고, **F12 콘솔**로 로그를 확인하세요.

---

## 폴더 구조

```
useMemo-example/
├── src/
│   ├── App.jsx        # 탭 전환 (1~4)
│   ├── Example1.jsx   # useMemo 기초
│   ├── Example2.jsx   # useMemo + 비싼 계산
│   ├── Example3.jsx   # useCallback
│   ├── Example4.jsx   # useCallback + React.memo
│   ├── main.jsx
│   └── index.css
├── package.json
└── README.md
```

---

## App.jsx

`tab` state로 예제 4개를 바꿉니다.

| 버튼 | 컴포넌트 | Hook |
|------|----------|------|
| useMemo 1 | Example1 | useMemo |
| useMemo 2 | Example2 | useMemo |
| useCallback 1 | Example3 | useCallback |
| useCallback 2 | Example4 | useCallback + **React.memo** |

---

## Example1 — useMemo 기초

```jsx
const doubled = useMemo(() => count * 2, [count]);
```

- `count * 2` 결과를 **캐시**
- 의존성 `[count]` → count가 바뀔 때만 다시 계산
- 콘솔: `계산 실행`은 count +1 할 때만 출력

---

## Example2 — 비용 큰 연산 (팩토리얼)

```jsx
const result = useMemo(() => factorial(count), [count]);
```

- state: `count`(숫자), `name`(입력)
- **name만 바꿔도** 컴포넌트는 리렌더되지만, `factorial`은 **다시 안 돌림**
- count +1 할 때만 팩토리얼 재계산
- 주석의 `//const result = factorial(count)`와 비교하면 useMemo 효과를 바로 볼 수 있음

---

## Example3 — useCallback

```jsx
const handleIncrease = useCallback(() => {
  setCount((c) => c + 1);
}, []);
```

- 함수를 **같은 참조로 유지**
- `setCount(c => c + 1)` 함수형 업데이트라 의존성 `[]` 가능
- +1 / -1 버튼에 연결

---

## Example4 — useCallback + React.memo

탭 이름은 「useCallback 2」이지만, **React.memo를 쓰는 예제**입니다.

```jsx
const Child = memo(({ onClick, label }) => {
  console.log('Child 렌더:', label);
  return <button className="btn btn-primary" onClick={onClick}>{label}</button>;
});

const handleClick = useCallback(() => {
  setCount((c) => c + 1);
}, []);
```

- `memo`로 Child를 감쌈 → props가 같으면 자식 리렌더 스킵
- `useCallback`으로 `handleClick` 참조를 고정
- 부모(Example4)가 리렌더돼도, `onClick` 참조가 같으면 **Child는 리렌더 안 함**
- `useCallback`을 빼면 매 렌더마다 새 함수 → Child도 매번 렌더
- 콘솔: `Child 렌더`는 처음(또는 count 관련)만 보면 됨

---

## 한 줄 정리

| Hook / API | 역할 |
|------------|------|
| **useMemo** | **값(계산 결과)** 을 캐시 |
| **useCallback** | **함수** 참조를 캐시 |
| **React.memo** | props가 같으면 자식 리렌더 스킵 (보통 useCallback과 함께) |

## 수업 팁

- **예제 2**: name 입력해도 팩토리얼 계산 로그가 안 나옴 → useMemo
- **예제 4**: Child 렌더 로그 → useCallback + memo 조합
