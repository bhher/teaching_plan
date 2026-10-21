# TypeScript 정리 (React 수업용)

React와 함께 쓸 때 자주 나오는 개념만 모았습니다.  
예제: `example-ts-basics`, `jsx-tsx-compare`, `example-ts-todo`

```bash
cd react/example-ts-basics
npm install
npm run dev
```

---

## 1. TypeScript란?

JavaScript에 **타입(type)** 을 붙여, 실행 전에 실수를 잡는 언어입니다.

```text
코드 작성 → 타입 검사(tsc) → JS로 변환 → 브라우저 실행
```

| | JavaScript | TypeScript |
|--|------------|------------|
| 파일 | `.js` / `.jsx` | `.ts` / `.tsx` |
| 타입 | 느슨함 | 명시·추론 |
| 오류 | 주로 실행 시 | 작성·빌드 시 |

> **TSX** = TypeScript + JSX (React UI)

---

## 2. 기본 타입

```ts
let name: string = "홍길동";
let age: number = 20;
let ok: boolean = true;
let scores: number[] = [90, 85, 88];
let pair: [string, number] = ["점수", 100]; // 튜플
```

| 타입 | 예 |
|------|-----|
| `string` | `"hello"` |
| `number` | `10`, `3.14` |
| `boolean` | `true` / `false` |
| `number[]` | `[1, 2, 3]` |
| `any` | 뭐든지 (가능하면 피할 것) |
| `void` | 반환값 없음 (함수) |
| `null` / `undefined` | 비어 있음 |

---

## 3. 유니온 (Union)

여러 타입 중 **하나**만 허용합니다.

```ts
function formatId(id: string | number): string {
  return typeof id === "number" ? `#${id}` : id;
}

type Status = "wait" | "done" | "error";
```

오타 `"don"` 같은 값은 컴파일 오류가 납니다.

---

## 4. 함수 타입

```ts
function add(a: number, b: number): number {
  return a + b;
}

const onClick: () => void = () => {
  console.log("클릭");
};
```

| 부분 | 의미 |
|------|------|
| `(a: number, b: number)` | 매개변수 타입 |
| `: number` | 반환 타입 |
| `() => void` | 인자 없고 반환 없음 |

---

## 5. interface · type

객체의 **모양**을 정의합니다.

```ts
interface User {
  id: number;
  name: string;
  email?: string; // 선택 속성
}

type UserId = number;
type TodoFilter = "all" | "active" | "completed";
```

| | interface | type |
|--|-----------|------|
| 주 용도 | 객체 형태 | 유니온·별칭·객체 모두 |
| React | Props에 많이 씀 | 필터 값 등에 많이 씀 |

수업에서는 **객체 → interface**, **문자 선택지 → type 유니온**으로 나누면 쉽습니다.

---

## 6. React Props

```tsx
interface DemoButtonProps {
  label: string;
  onClick: () => void;
}

export function DemoButton({ label, onClick }: DemoButtonProps) {
  return (
    <button type="button" onClick={onClick}>
      {label}
    </button>
  );
}
```

```tsx
// ❌ label에 숫자 → 타입 오류
<DemoButton label={1} onClick={() => {}} />

// ✅
<DemoButton label="확인" onClick={() => alert("ok")} />
```

---

## 7. useState 타입

```tsx
const [count, setCount] = useState<number>(0);
const [name, setName] = useState("");          // string으로 추론
const [user, setUser] = useState<User | null>(null);
```

| 코드 | 의미 |
|------|------|
| `useState<number>(0)` | 숫만 허용 |
| `useState<User \| null>(null)` | 아직 없거나 User 객체 |

---

## 8. 배열 · map

```ts
interface Todo {
  id: number;
  text: string;
  done: boolean;
}

const todos: Todo[] = [
  { id: 1, text: "TS 공부", done: false },
];

todos.map((todo) => todo.text); // todo는 Todo로 추론됨
```

---

## 9. 자주 쓰는 패턴 치트시트

```ts
// props
type Props = { title: string; children?: React.ReactNode };

// 이벤트
const onChange = (e: React.ChangeEvent<HTMLInputElement>) => {
  console.log(e.target.value);
};

// 클릭
const onClick = (e: React.MouseEvent<HTMLButtonElement>) => {
  e.preventDefault();
};
```

---

## 10. JSX vs TSX (한 줄)

```text
JSX  = UI 문법 (타입 없음)
TSX  = UI 문법 + 타입

화면 결과는 같고, TSX는 작성 중 실수를 더 잘 잡는다.
```

비교 앱: `react/jsx-tsx-compare`

---

## 11. 학생용 암기

1. `: string` / `: number` / `: boolean`  
2. `string | number` 유니온  
3. `interface`로 props 모양  
4. `({ ... }: Props)` 로 받기  
5. `useState<타입>(초기값)`  

```text
값에 이름표(타입)를 붙여 둔다 → 잘못된 값이 오면 컴파일이 막는다
```
