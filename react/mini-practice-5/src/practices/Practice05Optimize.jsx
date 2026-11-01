import { useState, useMemo, useCallback, memo } from 'react';

const products = [
  { id: 1, name: '노트북', price: 1200000 },
  { id: 2, name: '마우스', price: 30000 },
  { id: 3, name: '키보드', price: 80000 },
  { id: 4, name: '모니터', price: 350000 },
  { id: 5, name: '헤드셋', price: 89000 },
];

const ProductItem = memo(function ProductItem({ product, onSelect }) {
  console.log('ProductItem 렌더링:', product.name);

  return (
    <div className="product-card">
      <h3>{product.name}</h3>
      <p className="price">{product.price.toLocaleString()}원</p>
      <button type="button" className="btn btn-sm" onClick={() => onSelect(product)}>
        선택
      </button>
    </div>
  );
});

function ProductList({ products, onSelect }) {
  return (
    <div>
      {products.map((product) => (
        <ProductItem key={product.id} product={product} onSelect={onSelect} />
      ))}
    </div>
  );
}

/**
 * ⑤ 상품 목록 최적화
 * App → 검색창 + ProductList → ProductItem(memo)
 * useMemo(필터) · useCallback(선택) · memo(자식)
 */
export default function Practice05Optimize() {
  const [keyword, setKeyword] = useState('');
  const [selected, setSelected] = useState(null);
  const [tick, setTick] = useState(0);

  const filtered = useMemo(() => {
    console.log('✅ useMemo: 상품 필터');
    return products.filter((p) => p.name.includes(keyword));
  }, [keyword]);

  const handleSelect = useCallback((product) => {
    setSelected(product);
    alert(`${product.name} 선택`);
  }, []);

  return (
    <div className="panel">
      <h2>⑤ 🚀 상품 목록 최적화</h2>
      <p className="goal">
        학습: memo(컴포넌트) · useCallback(함수) · useMemo(값) — F12 콘솔 확인
      </p>

      <div className="row">
        <input
          type="text"
          value={keyword}
          onChange={(e) => setKeyword(e.target.value)}
          placeholder="상품 검색"
        />
        <button type="button" className="btn btn-ghost" onClick={() => setTick((t) => t + 1)}>
          부모만 리렌더 (+{tick})
        </button>
      </div>

      {selected && (
        <p style={{ marginBottom: 12, color: '#1565c0' }}>
          선택됨: <strong>{selected.name}</strong>
        </p>
      )}

      <ProductList products={filtered} onSelect={handleSelect} />

      <p className="log">
        &quot;부모만 리렌더&quot;를 눌러도 ProductItem 로그가 안 나오면 memo + useCallback 성공
      </p>

      <div className="hint">
        <strong>useMemo</strong> → 값 기억 · <strong>useCallback</strong> → 함수 기억 ·{' '}
        <strong>memo</strong> → 컴포넌트 렌더링 줄임
      </div>
    </div>
  );
}
