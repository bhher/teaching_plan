# JSX vs TSX 한눈 비교 (v2)

어제 `jsx-tsx-compare`(Counter · UserCard)와 **다른 예제**입니다.  
좋아요 토글 · 검색 필터 목록을 좌(JSX) / 우(TSX)로 비교합니다.

```bash
cd react/jsx-tsx-compare-v2
npm install
npm run dev
```

---

## 한 줄 차이

| | JSX | TSX |
|--|-----|-----|
| 파일 | `.jsx` | `.tsx` |
| 타입 | 없음 | props · state · 이벤트에 타입 |
| 오류 | 실행 때 | 작성·빌드 때 |

화면은 같고, **이름표(타입) 유무**만 다릅니다.

---

## 예제 A — 좋아요 토글

### JSX

```jsx
function LikeButton({ label }) {
  const [liked, setLiked] = useState(false);
  return (
    <button onClick={() => setLiked(!liked)}>
      {liked ? "🤍 취소" : "❤️ " + label}
    </button>
  );
}
```

### TSX

```tsx
type LikeButtonProps = { label: string };

function LikeButton({ label }: LikeButtonProps) {
  const [liked, setLiked] = useState<boolean>(false);
  return (
    <button type="button" onClick={() => setLiked(!liked)}>
      {liked ? "🤍 취소" : `❤️ ${label}`}
    </button>
  );
}
```

---

## 예제 B — 목록 필터 (유니온)

### JSX

```jsx
function ProductFilter({ items, filter }) {
  const list =
    filter === "all"
      ? items
      : items.filter((p) => p.category === filter);
  return list.map((p) => <li key={p.id}>{p.name}</li>);
}
// filter에 "al" 오타 → 실행해 봐야  squ
```

### TSX

```tsx
type CategoryFilter = "all" | "IT" | "생활";

type Product = { id: number; name: string; category: "IT" | "생활" };

type Props = { items: Product[]; filter: CategoryFilter };

function ProductFilter({ items, filter }: Props) {
  const list =
    filter === "all"
      ? items
      : items.filter((p) => p.category === filter);
  return (
    <ul>
      {list.map((p) => (
        <li key={p.id}>{p.name}</li>
      ))}
    </ul>
  );
}
// filter="al" → 타입 오류
```

---

## 예제 C — input onChange

### JSX

```jsx
function NameInput() {
  const [name, setName] = useState("");
  return (
    <input value={name} onChange={(e) => setName(e.target.value)} />
  );
}
```

### TSX

```tsx
function NameInput() {
  const [name, setName] = useState<string>("");
  const onChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setName(e.target.value);
  };
  return <input value={name} onChange={onChange} />;
}
```

---

## 표로 암기

| 상황 | JSX | TSX |
|------|-----|-----|
| props | `{ label }` | `{ label }: { label: string }` |
| boolean state | `useState(false)` | `useState<boolean>(false)` |
| 선택지 | 문자열 아무거나 | `"all" \| "IT" \| "생활"` |
| input 이벤트 | `(e) => ...` | `(e: ChangeEvent<HTMLInputElement>)` |

---

## 관련

- v1 앱: `jsx-tsx-compare` (Counter · UserCard)
- v2 앱: `jsx-tsx-compare-v2` (Like · Filter · NameInput)
- 이론: `TypeScript-정리.md`
