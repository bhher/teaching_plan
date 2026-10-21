# JSX → TSX 변환 문제 (CardList)

**대상 파일:** `src/examples/CardList.jsx` (문제) → `CardList.tsx` (학생이 작성)  
**정답 참고:** `src/examples/CardList.tsx`  
**실행:** `npm run dev` → 섹션 4에서 좌(JSX) / 우(TSX) 비교

---

## 학습 목표

- 객체·배열 props에 `type` / `interface` 붙이기
- 콜백 props `(card) => void` 타입 지정
- 유니온 (`'a' | 'b' | 'c'`) 사용
- `.jsx`를 `.tsx`로 바꿔도 **화면은 같게** 유지

---

## 문제용 JSX (그대로 제공)

아래 코드를 학생에게 주고 **TSX로 변환**하라고 합니다.

```jsx
/** CardList.jsx — 문제 */
export default function CardList({ cards, onSelect }) {
  return (
    <div className="card-grid">
      {cards.map((card) => (
        <article key={card.id} className="card">
          <div className={`card-thumb tone-${card.tone}`} />
          <div className="card-body">
            <span className="card-tag">{card.tag}</span>
            <h3>{card.title}</h3>
            <p>{card.desc}</p>
            <p className="card-price">{card.price.toLocaleString()}원</p>
            <button type="button" className="btn" onClick={() => onSelect(card)}>
              선택
            </button>
          </div>
        </article>
      ))}
    </div>
  );
}
```

부모에서 넘기는 데이터 형태(힌트):

```js
cards = [
  {
    id: 1,
    title: '무선 마우스',
    desc: '조용한 클릭',
    tag: '주변기기',
    price: 25000,
    tone: 'a', // 'a' | 'b' | 'c'
  },
  // ...
];

onSelect = (card) => { /* 선택한 card 처리 */ };
```

---

## 요구사항 (체크리스트)

학생이 `CardList.tsx`를 만들 때:

- [ ] 파일 확장자 `.tsx`
- [ ] `CardItem` 타입 (또는 interface) 정의  
      필드: `id`, `title`, `desc`, `tag`, `price`, `tone`
- [ ] `tone`은 `'a' | 'b' | 'c'` 유니온
- [ ] `CardListProps`: `cards: CardItem[]`, `onSelect: (card: CardItem) => void`
- [ ] 함수 시그니처: `({ cards, onSelect }: CardListProps)`
- [ ] UI·className·문구는 JSX와 **동일**

---

## 추가 문제

### 문제 2 — 일부러 깨보기 ⭐

정답 TSX를 연결한 뒤, App에서 다음을 하면 **타입 오류**가 나는지 확인하세요.

```tsx
// 1) price를 문자열로
{ id: 9, title: '테스트', desc: '', tag: 'x', price: '1000', tone: 'a' }

// 2) tone 오타
{ ..., tone: 'd' }

// 3) onSelect에 이상한 함수
onSelect={(n: number) => {}}
```

### 문제 3 — LikeButton도 변환 ⭐

`LikeButton.jsx`만 보고 TSX로 다시 적어 보세요. (정답: `LikeButton.tsx`)

### 문제 4 — 서술 ⭐

1. `onSelect: (card: CardItem) => void` 에서 `void`는 무슨 뜻인가?  
2. `cards: CardItem[]` 와 `cards: CardItem` 차이는?  
3. JSX를 TSX로 바꿀 때 **꼭 바꿔야 하는 것**과 **그대로 둬도 되는 것**은?

---

## 모범 답 (CardList.tsx)

```tsx
export type CardTone = 'a' | 'b' | 'c';

export type CardItem = {
  id: number;
  title: string;
  desc: string;
  tag: string;
  price: number;
  tone: CardTone;
};

type CardListProps = {
  cards: CardItem[];
  onSelect: (card: CardItem) => void;
};

export default function CardList({ cards, onSelect }: CardListProps) {
  return (
    <div className="card-grid">
      {cards.map((card) => (
        <article key={card.id} className="card">
          <div className={`card-thumb tone-${card.tone}`} />
          <div className="card-body">
            <span className="card-tag">{card.tag}</span>
            <h3>{card.title}</h3>
            <p>{card.desc}</p>
            <p className="card-price">{card.price.toLocaleString()}원</p>
            <button type="button" className="btn" onClick={() => onSelect(card)}>
              선택
            </button>
          </div>
        </article>
      ))}
    </div>
  );
}
```

---

## 서술 모범 답

1. **반환값이 없다** (호출만 하고 결과를 쓰지 않음).  
2. `CardItem[]` = 카드 **배열**, `CardItem` = 카드 **하나**.  
3. **바꿔야 함:** 확장자, props/state 타입. **그대로:** JSX 마크업, className, 로직.

---

## 수업 진행 제안

1. `CardList.jsx` 실행·동작 설명 (map · 선택 버튼)  
2. “타입 없이 위험한 점” 이야기 (`price`에 문자열 등)  
3. 학생 변환 실습 (정답 파일은 숨기거나 이름 변경)  
4. `CardList.tsx`와 diff로 비교  

**채점:** 타입이 맞고, 화면이 JSX와 동일하면 합격.
