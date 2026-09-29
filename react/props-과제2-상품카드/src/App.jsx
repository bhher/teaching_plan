import ProductCard from './ProductCard.jsx'

function App() {
  return (
    <div>
      <h1 style={{ marginTop: 0 }}>과제 2 — 상품 카드</h1>
      <p style={{ color: '#555', marginBottom: '1.5rem' }}>
        가격 포맷팅 · 할인율 표시 (discount 없으면 정가만)
      </p>

      <div style={{ display: 'flex', flexWrap: 'wrap', gap: '1.25rem' }}>
        <ProductCard
          name="노트북"
          price={1500000}
          description="고성능 노트북"
          image="https://picsum.photos/seed/laptop/400/300"
          discount={10}
        />
        <ProductCard
          name="무선 마우스"
          price={45000}
          description="조용한 클릭, 장시간 배터리"
          image="https://picsum.photos/seed/mouse/400/300"
          discount={20}
        />
        <ProductCard
          name="기계식 키보드"
          price={129000}
          description="텐키리스, 갈축"
          image="https://picsum.photos/seed/keyboard/400/300"
        />
      </div>
    </div>
  )
}

export default App
