import { useState, useMemo } from 'react';

const products = [
  { id: 1, name: '노트북', category: '전자기기', price: 1200000 },
  { id: 2, name: '마우스', category: '전자기기', price: 30000 },
  { id: 3, name: '키보드', category: '전자기기', price: 80000 },
  { id: 4, name: '티셔츠', category: '의류', price: 25000 },
  { id: 5, name: '청바지', category: '의류', price: 60000 },
  { id: 6, name: '노트북 거치대', category: '전자기기', price: 35000 },
];

/** ③ 검색 필터 — useState, filter, useMemo */
export default function Practice03Search() {
  const [keyword, setKeyword] = useState('');
  const [category, setCategory] = useState('전체');

  const filteredProducts = useMemo(() => {
    console.log('🔍 필터링 실행');
    return products.filter((product) => {
      const keywordMatch = product.name.includes(keyword);
      const categoryMatch = category === '전체' || product.category === category;
      return keywordMatch && categoryMatch;
    });
  }, [keyword, category]);

  return (
    <div className="panel">
      <h2>③ 🔍 상품 검색 필터</h2>
      <p className="goal">학습: useState · filter · useMemo (검색어/카테고리 변경 시에만 재계산)</p>

      <div className="row">
        <input
          type="text"
          value={keyword}
          onChange={(e) => setKeyword(e.target.value)}
          placeholder="상품 검색"
        />
      </div>

      <div className="row">
        <label htmlFor="category">카테고리</label>
        <select
          id="category"
          value={category}
          onChange={(e) => setCategory(e.target.value)}
        >
          <option value="전체">전체</option>
          <option value="전자기기">전자기기</option>
          <option value="의류">의류</option>
        </select>
      </div>

      <hr style={{ margin: '16px 0', border: 'none', borderTop: '1px solid #eee' }} />

      {filteredProducts.length === 0 ? (
        <p style={{ color: '#999' }}>검색 결과가 없습니다.</p>
      ) : (
        filteredProducts.map((product) => (
          <div className="list-item" key={product.id}>
            <div>
              <strong>{product.name}</strong>
              <div style={{ fontSize: 12, color: '#888' }}>{product.category}</div>
            </div>
            <span className="price">{product.price.toLocaleString()}원</span>
          </div>
        ))
      )}

      <p className="log">콘솔: keyword / category 바뀔 때만 &quot;필터링 실행&quot; 출력</p>

      <div className="hint">
        검색어 또는 카테고리가 변경될 때만 다시 필터링합니다. (의존성: [keyword, category])
      </div>
    </div>
  );
}
