# JSX → TSX 변환 문제 · 답

수업용. **문제만 먼저 풀게 한 뒤** 답을 공개하세요.  
관련 예제: `jsx-tsx-compare-v2` · `TypeScript-정리.md`

**추천 순서:** 1 → 2 → 3 → 4 → 5  
**5번:** 10~15분 개별 변환 후 정답 공개

| 문제 | 내용 | 난이도 |
|------|------|--------|
| 1 | useState + ChangeEvent | ⭐ |
| 2 | Props 타입 | ⭐ |
| 3 | 객체 배열 + Product[] | ⭐⭐ |
| 4 | Union Type | ⭐⭐ |
| 5 | useState + Union + map + filter | ⭐⭐⭐ |

---

# A. 문제

## 문제 1 — 가장 기본 ⭐

아래 JSX를 TSX로 변환하세요.

**NameInput.jsx**

```jsx
import { useState } from 'react';

export default function NameInput() {
  const [name, setName] = useState('');

  const onChange = (e) => {
    setName(e.target.value);
  };

  return (
    <div>
      <h2>이름 입력</h2>
      <input
        value={name}
        placeholder="이름을 입력하세요"
        onChange={onChange}
      />
      <p>안녕하세요, {name}</p>
    </div>
  );
}
```

**조건** — 다음 2곳에 타입 지정

1. `useState`
2. `onChange`의 이벤트 타입

**힌트**

```ts
useState< ??? >('')
const onChange = (e: ???) => {
```

---

## 문제 2 — Props 타입 추가 ⭐

**UserCard.jsx**

```jsx
export default function UserCard({ name, age, job }) {
  return (
    <div className="card">
      <h2>{name}</h2>
      <p>{age}세</p>
      <p>{job}</p>
    </div>
  );
}
```

**조건** — Props 타입을 직접 만들기

**힌트**

```ts
type Props = {
  name: ???;
  age: ???;
  job: ???;
};

export default function UserCard({ name, age, job }: Props) {
```

---

## 문제 3 — 배열 + Props ⭐⭐

**ProductList.jsx**

```jsx
export default function ProductList({ items }) {
  return (
    <ul>
      {items.map((item) => (
        <li key={item.id}>
          {item.name} - {item.price}원
        </li>
      ))}
    </ul>
  );
}
```

데이터 예:

```js
const items = [
  { id: 1, name: '노트북', price: 1200000 },
  { id: 2, name: '키보드', price: 80000 },
  { id: 3, name: '마우스', price: 40000 },
];
```

**조건** — 완성하기

```ts
type Product = {
  id: ???;
  name: ???;
  price: ???;
};

type Props = {
  items: ???;
};
```

---

## 문제 4 — 유니온 타입 ⭐⭐

```jsx
import { useState } from 'react';

export default function Category() {
  const [category, setCategory] = useState('all');
  const categories = ['all', 'IT', '생활'];

  return (
    <div>
      {categories.map((item) => (
        <button key={item} onClick={() => setCategory(item)}>
          {item}
        </button>
      ))}
      <p>선택: {category}</p>
    </div>
  );
}
```

**조건**

- `category`에는 `'all' | 'IT' | '생활'` 만 가능
- `type Category = ???;`
- `useState<Category>('all')` 사용

---

## 문제 5 — 종합 ⭐⭐⭐

다음 JSX를 TSX로 변환하세요. **10~15분** 직접 풀어 보세요.

```jsx
import { useState } from 'react';

const products = [
  { id: 1, name: '노트북', category: 'IT' },
  { id: 2, name: '텀블러', category: '생활' },
  { id: 3, name: '키보드', category: 'IT' },
];

export default function ProductFilter() {
  const [filter, setFilter] = useState('all');

  const list =
    filter === 'all'
      ? products
      : products.filter((p) => p.category === filter);

  return (
    <div>
      <div>
        {['all', 'IT', '생활'].map((item) => (
          <button key={item} onClick={() => setFilter(item)}>
            {item}
          </button>
        ))}
      </div>
      <ul>
        {list.map((p) => (
          <li key={p.id}>
            {p.name}
            <span>{p.category}</span>
          </li>
        ))}
      </ul>
    </div>
  );
}
```

**반드시 적용**

① 유니온

```ts
type CategoryFilter = 'all' | 'IT' | '생활';
```

② Product

```ts
type Product = {
  id: number;
  name: string;
  category: 'IT' | '생활';
};
```

③ useState

```ts
const [filter, setFilter] = useState<CategoryFilter>('all');
```

**생각해 보기**

`['all', 'IT', '생활'].map(...)` 에서 TS가 배열 타입을 넓게(`string[]`) 잡으면  
`setFilter(item)` 오류가 날 수 있습니다. 어떻게 고칠까요?

힌트:

```ts
const categories: CategoryFilter[] = ['all', 'IT', '생활'];
```

---

# B. 모범 답

## 답 1 — NameInput.tsx

```tsx
import { useState, type ChangeEvent } from 'react';

export default function NameInput() {
  const [name, setName] = useState<string>('');

  const onChange = (e: ChangeEvent<HTMLInputElement>) => {
    setName(e.target.value);
  };

  return (
    <div>
      <h2>이름 입력</h2>
      <input
        value={name}
        placeholder="이름을 입력하세요"
        onChange={onChange}
      />
      <p>안녕하세요, {name}</p>
    </div>
  );
}
```

| 자리 | 타입 |
|------|------|
| useState | `string` |
| onChange | `ChangeEvent<HTMLInputElement>` |

(`React.ChangeEvent<HTMLInputElement>` 도 동일)

---

## 답 2 — UserCard.tsx

```tsx
type Props = {
  name: string;
  age: number;
  job: string;
};

export default function UserCard({ name, age, job }: Props) {
  return (
    <div className="card">
      <h2>{name}</h2>
      <p>{age}세</p>
      <p>{job}</p>
    </div>
  );
}
```

---

## 답 3 — ProductList.tsx

```tsx
type Product = {
  id: number;
  name: string;
  price: number;
};

type Props = {
  items: Product[];
};

export default function ProductList({ items }: Props) {
  return (
    <ul>
      {items.map((item) => (
        <li key={item.id}>
          {item.name} - {item.price}원
        </li>
      ))}
    </ul>
  );
}
```

---

## 답 4 — Category.tsx

```tsx
import { useState } from 'react';

type Category = 'all' | 'IT' | '생활';

export default function Category() {
  const [category, setCategory] = useState<Category>('all');
  const categories: Category[] = ['all', 'IT', '생활'];

  return (
    <div>
      {categories.map((item) => (
        <button
          key={item}
          type="button"
          onClick={() => setCategory(item)}
        >
          {item}
        </button>
      ))}
      <p>선택: {category}</p>
    </div>
  );
}
```

> `categories`에 `Category[]`를 주면 `setCategory(item)`이 안전합니다.

---

## 답 5 — ProductFilter.tsx

```tsx
import { useState } from 'react';

type CategoryFilter = 'all' | 'IT' | '생활';

type Product = {
  id: number;
  name: string;
  category: 'IT' | '생활';
};

const products: Product[] = [
  { id: 1, name: '노트북', category: 'IT' },
  { id: 2, name: '텀블러', category: '생활' },
  { id: 3, name: '키보드', category: 'IT' },
];

const categories: CategoryFilter[] = ['all', 'IT', '생활'];

export default function ProductFilter() {
  const [filter, setFilter] = useState<CategoryFilter>('all');

  const list =
    filter === 'all'
      ? products
      : products.filter((p) => p.category === filter);

  return (
    <div>
      <div>
        {categories.map((item) => (
          <button
            key={item}
            type="button"
            onClick={() => setFilter(item)}
          >
            {item}
          </button>
        ))}
      </div>
      <ul>
        {list.map((p) => (
          <li key={p.id}>
            {p.name}
            <span>{p.category}</span>
          </li>
        ))}
      </ul>
    </div>
  );
}
```

**배열 추론 문제 해결 (생각해 보기 답)**

| 방법 | 코드 |
|------|------|
| 권장 | `const categories: CategoryFilter[] = ['all', 'IT', '생활']` |
| 대안 | `(['all', 'IT', '생활'] as CategoryFilter[]).map(...)` |
| 대안 | `as const` + 맞는 타입 설계 |

리터럴 배열을 그냥 쓰면 `string[]`로 넓혀져 `setFilter`와 안 맞을 수 있습니다.

---

## 채점 포인트

| 문제 | 합격 기준 |
|------|-----------|
| 1 | `useState<string>`, 이벤트 타입 있음 |
| 2 | Props 3필드 타입 + 함수에 적용 |
| 3 | `Product` + `items: Product[]` |
| 4 | 유니온 + `useState<Category>` |
| 5 | 세 조건 + 버튼 배열 타입 처리 |

---

## 수업 팁

- 1~2: 같이 풀기  
- 3~4: 짝 또는  individually  
- 5: **문제만 먼저** → 10~15분 → 정답·배열 추론 설명  
- `jsx-tsx-compare-v2`의 NameInput · ProductFilter와 대조하면 이해가 빠름  
