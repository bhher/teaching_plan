# jsx-tsx-compare-v2 설명

v1(`jsx-tsx-compare`: Counter · UserCard)과 **다른 예제**로  
`.jsx` / `.tsx`를 나란히 비교하는 수업용 앱입니다.

```bash
cd react/jsx-tsx-compare-v2
npm install
npm run dev
```

요약 치트시트: [../JSX-TSX-한눈비교-v2.md](../JSX-TSX-한눈비교-v2.md)

---

## 1. 이 앱이 보여주는 것

| 섹션 | 파일 | TS에서 배우는 점 |
|------|------|------------------|
| 1. LikeButton | `LikeButton.jsx` / `.tsx` | props · `useState<boolean>` |
| 2. ProductFilter | `ProductFilter.jsx` / `.tsx` | `type` 유니온 · 배열 props |
| 3. NameInput | `NameInput.jsx` / `.tsx` | `ChangeEvent<HTMLInputElement>` |

왼쪽 패널 = JSX, 오른쪽 패널 = TSX.  
**화면 동작은 같고**, TSX만 타입이 붙어 있습니다.

```text
App.tsx
  ├─ LikeJsx  + LikeTsx
  ├─ FilterJsx + FilterTsx  (공통 filter state)
  └─ NameJsx  + NameTsx
```

---

## 2. 폴더 구조

```text
jsx-tsx-compare-v2/
├── src/
│   ├── App.tsx
│   ├── main.tsx
│   ├── index.css
│   └── examples/
│       ├── LikeButton.jsx / LikeButton.tsx
│       ├── ProductFilter.jsx / ProductFilter.tsx
│       └── NameInput.jsx / NameInput.tsx
├── package.json
└── tsconfig.json   ← allowJs: true (tsx가 jsx import 가능)
```

> **TSX 안에서 JSX를 import해도 됩니다.**  
> 빌드는 되지만, `.jsx` 쪽 props는 타입 검사가 거의 없습니다.

---

## 3. LikeButton — boolean state

### JSX

```jsx
export default function LikeButton({ label }) {
  const [liked, setLiked] = useState(false);
  // ...
}
```

### TSX

```tsx
type LikeButtonProps = { label: string };

export default function LikeButton({ label }: LikeButtonProps) {
  const [liked, setLiked] = useState<boolean>(false);
  // ...
}
```

| | JSX | TSX |
|--|-----|-----|
| props | `{ label }` | `{ label }: LikeButtonProps` |
| state | `useState(false)` | `useState<boolean>(false)` |

클릭하면 좋아요 ↔ 취소 토글. 결과는 양쪽 동일합니다.

---

## 4. ProductFilter — 유니온 타입이 핵심

### 타입 정의 (TSX만)

```tsx
export type CategoryFilter = 'all' | 'IT' | '생활';
export type Product = {
  id: number;
  name: string;
  category: 'IT' | '생활';
};

type Props = { items: Product[]; filter: CategoryFilter };
```

### 필터 로직 (JSX/TSX 동일 개념)

```tsx
const list =
  filter === 'all'
    ? items
    : items.filter((p) => p.category === filter);
```

### App에서 필터 버튼

```tsx
const [filter, setFilter] = useState<CategoryFilter>('all');

{(['all', 'IT', '생활'] as CategoryFilter[]).map((f) => (
  <button onClick={() => setFilter(f)}>{f}</button>
))}
```

하나의 `filter` state를 **JSX·TSX 필터 컴포넌트 둘 다**에 넘깁니다.

| 실수 예 | TSX 반응 |
|---------|----------|
| `setFilter('al')` | 타입 오류 |
| `category: '의류'` (정의에 없음) | 타입 오류 |
| JSX만 쓸 때 같은 실수 | 실행해 봐야 알 수 있음 |

---

## 5. NameInput — 이벤트 타입

### JSX

```jsx
onChange={(e) => setName(e.target.value)}
```

### TSX

```tsx
import { useState, type ChangeEvent } from 'react';

const [name, setName] = useState<string>('');

const onChange = (e: ChangeEvent<HTMLInputElement>) => {
  setName(e.target.value);
};
```

| 타입 | 의미 |
|------|------|
| `ChangeEvent<HTMLInputElement>` | input의 onChange 이벤트 |
| `useState<string>('')` | 문자열만 set 가능 |

수업에서는 “input이면 `ChangeEvent<HTMLInputElement>`” 정도만 외워도 충분합니다.

---

## 6. App.tsx 역할

1. JSX/TSX 컴포넌트를 **둘 다 import**  
2. `Product[]` 샘플 데이터 준비  
3. `filter` state를 공유해 양쪽 ProductFilter에 전달  
4. 섹션별로 좌우 패널 배치  

```tsx
import LikeJsx from './examples/LikeButton.jsx';
import LikeTsx from './examples/LikeButton.tsx';
import type { CategoryFilter, Product } from './examples/ProductFilter.tsx';
```

`import type` → **타입만** 가져옴 (실행 코드에 안 남음).

---

## 7. v1과 무엇이 다른가

| | v1 (`jsx-tsx-compare`) | v2 (이 프로젝트) |
|--|------------------------|------------------|
| 예제 | Counter, UserCard | Like, Filter, NameInput |
| 포인트 | 기본 props · useState number | boolean · **유니온** · **이벤트 타입** |
| 문서 | `JSX-TSX-비교-설명.md` | 이 파일 + `JSX-TSX-한눈비교-v2.md` |

---

## 8. 학생용 한 줄

```text
JSX  = UI만
TSX  = UI + 타입 (props / state / 이벤트 / 유니온)

v2에서 꼭 볼 것:
  useState<boolean>
  'all' | 'IT' | '생활'
  ChangeEvent<HTMLInputElement>
```

---

## 9. 수업 팁 · 실습

1. Like TSX에서 `label={123}` → 빨간줄 확인  
2. `setFilter('전체' as any)` 없이 `'전체'`만 넣으면 오류인지 확인  
3. NameInput TSX의 `onChange` 타입을 지워 보고, 다시 채워 보기  
4. “TSX 앱에 JSX를 넣어도 되나?” → **됨. 다만 JSX는 타입 검사가 약함**

---

## 10. 관련 자료

| 경로 | 내용 |
|------|------|
| `../TypeScript-정리.md` | TS 이론 |
| `../TypeScript-문제답.md` | 문제·답 |
| `../jsx-tsx-compare/` | v1 비교 앱 |
| `../JSX-TSX-한눈비교-v2.md` | v2 치트시트 |
