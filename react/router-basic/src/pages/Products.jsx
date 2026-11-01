import { Link } from 'react-router-dom';

const products = [
  { id: 1, name: '노트북', price: 1200000 },
  { id: 2, name: '마우스', price: 30000 },
  { id: 3, name: '키보드', price: 80000 },
];

function Products() {
  return (
    <div className="page">
      <h1>상품 목록</h1>
      <p>상품 → 상세보기 클릭 시 <code>/products/:id</code>로 이동합니다.</p>

      {products.map((product) => (
        <div className="product-card" key={product.id}>
          <h3>{product.name}</h3>
          <p>{product.price.toLocaleString()}원</p>
          <Link className="btn" to={`/products/${product.id}`}>
            상세보기
          </Link>
        </div>
      ))}

      <div className="hint">
        실습 2: map + Link · 노트북→/products/1 · 마우스→/products/2 · 키보드→/products/3
      </div>
    </div>
  );
}

export default Products;
