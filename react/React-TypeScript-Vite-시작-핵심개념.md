# React + TypeScript 시작 가이드 (Vite)

Vite로 프로젝트를 만들고, TypeScript에서 꼭 알아두면 좋은 핵심 5가지를 정리한 문서입니다.

### 실행 가능한 예제

- **`example-ts-run`**: `src/App.tsx` 한 파일만 보면 되고, `npm install` → `npm run dev`로 바로 실행합니다.
- **`example-ts-basics`**: 개념별로 파일이 나뉜 버전입니다. `README.md`를 참고해 실행하세요.
- **`example-ts-expense-tracker`**: [입문] `Transaction` 모델·`useState<Transaction[]>`·카테고리 합산·Props 타입으로 CRUD(간편 가계부)를 연습합니다.
- **`example-ts-todo`**: [입문] `Todo` 모델·`useState<Todo[]>`·`filterTodos` / `countByDone`·Props 타입으로 Todo CRUD를 연습합니다.
- **`example-todo-jsx`**: 위와 동일한 Todo 예제를 **JSX만**으로 구현한 버전(타입 없음). TS 예제와 나란히 비교할 때 사용합니다.

---

## 1단계: 기본 세팅 (필수)

### 가장 쉬운 시작: Vite 사용

터미널에서 아래처럼 실행합니다.

```bash
npm create vite@latest my-app
```

프롬프트가 나오면:

- **Framework:** React
- **Variant:** TypeScript (또는 TypeScript + SWC)

### 설치 및 실행

```bash
cd my-app
npm install
npm run dev
```

브라우저에 표시되는 주소(보통 `http://localhost:5173`)로 접속하면 개발 서버 화면을 볼 수 있습니다.

### 참고

- `npm create vite@latest`는 최신 Vite 템플릿을 받아옵니다. Node.js는 LTS 버전 사용을 권장합니다.
- 프로젝트 이름 `my-app`은 원하는 폴더명으로 바꿔도 됩니다.

---

## 2단계: 꼭 알아야 할 핵심 개념 (5가지)

아래 5가지만 먼저 익히면 React + TS 코드를 읽고 쓰기 훨씬 수월해집니다.

---

### 1. 타입 선언 (기본 타입)

변수에 **어떤 종류의 값**이 들어가는지 미리 정합니다.

```ts
let name: string = "홍길동";
let age: number = 20;
let isStudent: boolean = true;
let nothing: null = null;
let notDefined: undefined = undefined;
```

**배열·튜플 예시**

```ts
let scores: number[] = [90, 85, 88];
let tuple: [string, number] = ["점수", 100];
```

**유니온 (여러 타입 중 하나)**

```ts
let id: string | number = "abc-1";
id = 42;
```

---

### 2. 함수 타입

**인자**와 **반환값**에 타입을 붙입니다.

```ts
function add(a: number, b: number): number {
  return a + b;
}

// 화살표 함수
const multiply = (a: number, b: number): number => a * b;
```

**선택 인자·기본값**

```ts
function greet(name: string, title?: string): string {
  return title ? `${title} ${name}님` : `${name}님`;
}

function repeat(text: string, count: number = 2): string {
  return text.repeat(count);
}
```

**콜백 타입 (다른 함수에 넘길 때)**

```ts
function runTwice(fn: () => void): void {
  fn();
  fn();
}
```

---

### 3. 객체 타입 (`interface` / `type`)

객체의 **모양(필드 이름과 타입)** 을 정의합니다.

```ts
interface User {
  id: number;
  name: string;
  email?: string; // 선택 속성
}

const user: User = {
  id: 1,
  name: "김철수",
};
```

**`type`으로도 비슷하게 정의 가능**

```ts
type Product = {
  sku: string;
  price: number;
};
```

**확장**

```ts
interface Admin extends User {
  role: "admin" | "superadmin";
}
```

---

### 4. React Props 타입

컴포넌트가 받는 props에 `interface` 또는 `type`을 붙입니다.

```tsx
interface CardProps {
  title: string;
}

function Card({ title }: CardProps) {
  return <h1>{title}</h1>;
}

// 사용
<Card title="안녕하세요" />
```

**자식 노드(`children`)**

```tsx
import { ReactNode } from "react";

interface LayoutProps {
  children: ReactNode;
}

function Layout({ children }: LayoutProps) {
  return <div className="layout">{children}</div>;
}
```

**이벤트 핸들러**

```tsx
interface ButtonProps {
  label: string;
  onClick: () => void;
}

function Button({ label, onClick }: ButtonProps) {
  return <button type="button" onClick={onClick}>{label}</button>;
}
```

---

### 5. `useState` 타입

상태의 타입을 **제네릭** `<>` 으로 지정합니다.

```tsx
import { useState } from "react";

const [count, setCount] = useState<number>(0);

// 초기값이 null일 수 있을 때
const [user, setUser] = useState<User | null>(null);

// 타입 추론으로 충분한 경우 (초기값이 명확하면 생략 가능)
const [open, setOpen] = useState(false); // boolean으로 추론
```

**객체 상태 예시**

```tsx
interface FormState {
  email: string;
  password: string;
}

const [form, setForm] = useState<FormState>({
  email: "",
  password: "",
});

// 부분만 바꿀 때는 이전 상태를 펼쳐서 병합
setForm((prev) => ({ ...prev, email: "a@b.com" }));
```

---

## 한 페이지 요약

| 개념           | 역할                         |
|----------------|------------------------------|
| 변수 타입      | 값의 종류 제한               |
| 함수 타입      | 인자·반환값 계약             |
| `interface`    | 객체(및 props) 구조 정의     |
| Props 타입     | 컴포넌트 입력 규칙           |
| `useState<T>`  | 상태 값의 타입 `T` 지정      |

이 다섯 가지에 익숙해지면 `useEffect` 의존 배열, `fetch` 응답 타입, 라이브러리 타입 정의 등으로 자연스럽게 확장할 수 있습니다.
