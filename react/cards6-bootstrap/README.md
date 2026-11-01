# 카드 6장 (사진) — react-bootstrap

사진이 있는 Bootstrap 카드 6장을 `map`으로 렌더링합니다.

## 관련

| 파일 | 내용 |
|------|------|
| `boot/examples/08-카드6장.html` | HTML Bootstrap + 사진 |
| **이 폴더** | react-bootstrap + `Card.Img` + map |

## 실행

```bash
cd react/cards6-bootstrap
npm install
npm run dev
```

## 핵심

```jsx
{cards.map((card) => (
  <Col key={card.id} xs={12} md={6} lg={4}>
    <PhotoCard card={card} />
  </Col>
))}
```

```jsx
<Card.Img variant="top" src={card.img} alt={card.title} />
```
