# React 3·4·5차시 + 여러 Input 관리 — 정답

문제: [React-3-4-5차시-Input관리-문제.md](./React-3-4-5차시-Input관리-문제.md)

---

# Part A. 컴포넌트

## 1번
② UI를 재사용 가능한 조각으로 나눈 것이다

## 2번
② 함수형 컴포넌트

## 3번

```jsx
function Welcome() {
  return <h1>환영합니다!</h1>
}

export default Welcome
```

## 4번

```jsx
// Header.jsx
function Header() {
  return <h1>사이트 제목</h1>
}

export default Header
```

```jsx
// App.jsx
import Header from './Header.jsx'

function App() {
  return (
    <div>
      <Header />
    </div>
  )
}

export default App
```

## 5번 (예시)
한 번 만든 UI를 여러 곳에서 다시 쓸 수 있다.

---

# Part B. Props

## 6번
② 부모 → 자식

## 7번
② 읽기 전용이다

## 8번

```jsx
function Card({ title, content }) {
  return (
    <div>
      <h3>{title}</h3>
      <p>{content}</p>
    </div>
  )
}

export default Card
```

```jsx
import Card from './Card.jsx'

function App() {
  return (
    <div>
      <Card title="첫 글" content="안녕하세요" />
      <Card title="둘째 글" content="Props 연습입니다" />
    </div>
  )
}

export default App
```

## 9번

```jsx
function Hello({ color, name }) {
  return <div style={{ color }}>안녕하세요 {name}</div>
}
```

## 10번

```jsx
function ProductCard({ name, price, seller }) {
  return (
    <div style={{ border: '1px solid #ddd', padding: '1rem', margin: '0.5rem 0' }}>
      <h3>{name}</h3>
      <p>가격: {price}원</p>
      <p>판매자: {seller}</p>
    </div>
  )
}

export default ProductCard
```

```jsx
import ProductCard from './ProductCard.jsx'

function App() {
  return (
    <div>
      <ProductCard name="키보드" price={45000} seller="홍길동" />
      <ProductCard name="마우스" price={25000} seller="김철수" />
    </div>
  )
}

export default App
```

## 11번
② `function Hello({ name = '손님' })`

---

# Part C. useState

## 12번
② State는 컴포넌트 내부에서 변경 가능, Props는 읽기 전용

## 13번
② state가 아니라서 리렌더링이 안 됨

## 14번

```jsx
import { useState } from 'react'

function Counter() {
  const [count, setCount] = useState(0)

  return (
    <button onClick={() => setCount(count + 1)}>
      {count}
    </button>
  )
}

export default Counter
```

## 15번

```jsx
import { useState } from 'react'

function LikeButton() {
  const [liked, setLiked] = useState(false)

  return (
    <button onClick={() => setLiked(!liked)}>
      {liked ? '좋아요 취소' : '좋아요'}
    </button>
  )
}

export default LikeButton
```

## 16번

```jsx
import { useState } from 'react'

function Light() {
  const [on, setOn] = useState(false)

  return (
    <div>
      <p>{on ? '켜짐' : '꺼짐'}</p>
      <button onClick={() => setOn(!on)}>전원</button>
    </div>
  )
}

export default Light
```

## 17번
첫 번째: 현재 state 값(`count`)  
두 번째: state를 바꾸는 함수(`setCount`)

---

# Part D. 여러 Input (상품 폼 변형)

## 18번
② `name` (state의 key와 동일)

## 19번 (예시)
기존 객체의 나머지 필드를 복사해 유지한다.

## 20번

```jsx
import { useState } from 'react'

function ProductForm() {
  const [form, setForm] = useState({
    title: '',
    price: '',
    category: '',
  })

  const { title, price, category } = form

  const onChange = (e) => {
    const { name, value } = e.target
    setForm({
      ...form,
      [name]: value,
    })
  }

  const onReset = () => {
    setForm({
      title: '',
      price: '',
      category: '',
    })
  }

  return (
    <div>
      <input
        name="title"
        placeholder="상품명"
        value={title}
        onChange={onChange}
      />
      <input
        name="price"
        placeholder="가격"
        value={price}
        onChange={onChange}
      />
      <input
        name="category"
        placeholder="분류 (예: 전자제품)"
        value={category}
        onChange={onChange}
      />

      <button type="button" onClick={onReset}>초기화</button>

      <div>
        [{category}] {title} - {price}원
      </div>
    </div>
  )
}

export default ProductForm
```

## 21번

```jsx
{/* 미리보기 부분만 교체 예 */}
<div>
  {title === ''
    ? '상품명을 입력하세요'
    : `[${category}] ${title} - ${price}원`}
</div>
```

## 22번

```jsx
import ProductForm from './ProductForm.jsx'

function App() {
  return (
    <div>
      <h1>상품 등록</h1>
      <ProductForm />
    </div>
  )
}

export default App
```

## 23번 (예시)
객체 state + input의 `name` + 하나의 `onChange`로 여러 칸을 관리한다.

## 24번
② `form.title = value` 직접 수정은 리렌더링을 보장하지 않음 (잘못된 설명)

---

# Part 종합 25번 (예시)

```jsx
// Header.jsx
function Header({ siteName }) {
  return <h1>{siteName}</h1>
}
export default Header
```

```jsx
// Counter.jsx
import { useState } from 'react'

function Counter() {
  const [views, setViews] = useState(0)
  return (
    <button onClick={() => setViews(views + 1)}>
      조회수 {views}
    </button>
  )
}
export default Counter
```

```jsx
// ProductForm.jsx → 20번 정답 사용
```

```jsx
// App.jsx
import Header from './Header.jsx'
import ProductForm from './ProductForm.jsx'
import Counter from './Counter.jsx'

function App() {
  return (
    <div style={{ padding: '1rem' }}>
      <Header siteName="미니 쇼핑몰" />
      <Counter />
      <hr />
      <ProductForm />
    </div>
  )
}

export default App
```

---

# 강사 채점 팁

| 문항 | 포인트 |
|------|--------|
| 8~10 | props + 재사용 |
| 14~16 | useState + 화면 갱신 |
| 20 | **상품 3필드** / onChange 하나 / name 일치 |
| 24 | 불변성 (직접 수정 X) |
| 25 | 3·4·5·Input 통합 |

권장: `setForm((prev) => ({ ...prev, [name]: value }))` 도 만점으로 인정.
