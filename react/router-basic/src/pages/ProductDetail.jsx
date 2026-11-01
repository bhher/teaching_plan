import { useParams, useNavigate, Link } from 'react-router-dom';

const products = [
  { id: 1, name: '노트북', price: 1200000, desc: '가벼운 업무용 노트북' },
  { id: 2, name: '마우스', price: 30000, desc: '무선 마우스' },
  { id: 3, name: '키보드', price: 80000, desc: '기계식 키보드' },
];

function ProductDetail() {
  const { id } = useParams();
  const navigate = useNavigate();
  const product = products.find((p) => String(p.id) === id);

  return (
    <div className="page">
      <h1>상품 상세 페이지</h1>
      <p>
        선택한 상품 ID : <strong>{id}</strong>
      </p>

      {product ? (
        <>
          <h2 style={{ margin: '12px 0 8px' }}>{product.name}</h2>
          <p>{product.price.toLocaleString()}원</p>
          <p>{product.desc}</p>
        </>
      ) : (
        <p>상품을 찾을 수 없습니다.</p>
      )}

      <div style={{ marginTop: 16, display: 'flex', gap: 8, flexWrap: 'wrap' }}>
        <Link className="btn btn-ghost" to="/products">
          목록으로
        </Link>
        <button type="button" className="btn btn-ghost" onClick={() => navigate(-1)}>
          뒤로가기
        </button>
      </div>

      <div className="hint">
        실습 3: useParams()로 id 읽기 · 실습 4: navigate(-1) 뒤로가기
      </div>
    </div>
  );
}

export default ProductDetail;
