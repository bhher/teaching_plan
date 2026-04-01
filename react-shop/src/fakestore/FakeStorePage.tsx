import React, { useMemo, useState } from 'react';
import { FakeStoreProduct, useFakeStoreProducts } from '../hooks/useFakeStoreProducts';

const SKELETON_COUNT = 8;

const formatPrice = (price: number) =>
  price.toLocaleString('ko-KR', { style: 'currency', currency: 'USD' });

const getCategoryLabel = (category: string) => {
  switch (category) {
    case "men's clothing":
      return '남성 의류';
    case "women's clothing":
      return '여성 의류';
    case 'jewelery':
      return '쥬얼리';
    case 'electronics':
      return '전자제품';
    default:
      return category;
  }
};

export const FakeStorePage: React.FC = () => {
  const state = useFakeStoreProducts();
  const [selectedCategory, setSelectedCategory] = useState<string>('all');

  useEffect(() => {
    let cancelled = false;

    const fetchProducts = async () => {
      setState((prev) => ({ ...prev, loading: true, error: null }));

      try {
        const res = await axios.get<FakeStoreProduct[]>('https://fakestoreapi.com/products');
        if (cancelled) return;
        setState({ data: res.data, loading: false, error: null });
      } catch (error) {
        if (cancelled) return;
        setState({
          data: [],
          loading: false,
          error: '상품을 불러오는 중 오류가 발생했습니다. 잠시 후 다시 시도해주세요.',
        });
      }
    };

    fetchProducts();

    return () => {
      cancelled = true;
    };
  }, []);

  const categories = useMemo(() => {
    const set = new Set(state.data.map((p) => p.category));
    return Array.from(set);
  }, [state.data]);

  const filteredProducts = useMemo(() => {
    if (selectedCategory === 'all') return state.data;
    return state.data.filter((p) => p.category === selectedCategory);
  }, [state.data, selectedCategory]);

  return (
    <div className="app">
      <header className="header">
        <div className="logo">Fake Store API</div>
        <nav className="nav">
          <span>상품 목록 연습</span>
        </nav>
      </header>

      <main className="main">
        <section className="hero-banner">
          <div className="hero-content">
            <h1>Fake Store API 상품 리스트</h1>
            <p>
              Axios로 외부 API에서 상품 데이터를 불러오고, 로딩 상태에선 스켈레톤 UI를,
              불러온 뒤에는 카테고리별로 필터링해서 보여줍니다.
            </p>
          </div>
        </section>

        <section className="product-section">
          <div className="section-header">
            <h2>전체 상품</h2>
            <span className="section-subtitle">
              {state.loading
                ? '상품을 불러오는 중입니다...'
                : `총 ${state.data.length}개 상품 · 선택된 카테고리: ${
                    selectedCategory === 'all'
                      ? '전체'
                      : getCategoryLabel(selectedCategory)
                  }`}
            </span>
          </div>

          <div style={{ marginBottom: '1rem', display: 'flex', gap: '0.5rem', flexWrap: 'wrap' }}>
            <button
              type="button"
              className="secondary-button"
              style={
                selectedCategory === 'all'
                  ? { backgroundColor: 'rgba(56,189,248,0.2)', color: '#e0f2fe' }
                  : undefined
              }
              onClick={() => setSelectedCategory('all')}
            >
              전체
            </button>
            {categories.map((category) => (
              <button
                key={category}
                type="button"
                className="secondary-button"
                style={
                  selectedCategory === category
                    ? { backgroundColor: 'rgba(56,189,248,0.2)', color: '#e0f2fe' }
                    : undefined
                }
                onClick={() => setSelectedCategory(category)}
              >
                {getCategoryLabel(category)}
              </button>
            ))}
          </div>

          {state.error && (
            <div
              style={{
                marginTop: '1rem',
                padding: '0.75rem 1rem',
                borderRadius: '0.75rem',
                border: '1px solid rgba(248,113,113,0.4)',
                backgroundColor: 'rgba(127,29,29,0.3)',
                color: '#fee2e2',
                fontSize: '0.85rem',
              }}
            >
              {state.error}
            </div>
          )}

          <div className="product-grid" style={{ marginTop: '0.75rem' }}>
            {state.loading
              ? Array.from({ length: SKELETON_COUNT }).map((_, index) => (
                  <article key={index} className="product-card">
                    <div className="image-wrapper">
                      <div
                        style={{
                          width: '100%',
                          height: '100%',
                          background:
                            'linear-gradient(90deg, rgba(30,64,175,0.5), rgba(15,23,42,0.8), rgba(30,64,175,0.5))',
                          backgroundSize: '200% 100%',
                          animation: 'skeleton 1.4s ease-in-out infinite',
                        }}
                      />
                    </div>
                    <div className="product-info">
                      <div
                        style={{
                          height: '0.8rem',
                          width: '80%',
                          borderRadius: '999px',
                          backgroundColor: 'rgba(148,163,184,0.2)',
                        }}
                      />
                      <div
                        style={{
                          marginTop: '0.35rem',
                          height: '0.8rem',
                          width: '40%',
                          borderRadius: '999px',
                          backgroundColor: 'rgba(148,163,184,0.25)',
                        }}
                      />
                      <div
                        style={{
                          marginTop: '0.6rem',
                          height: '1.9rem',
                          width: '55%',
                          borderRadius: '999px',
                          backgroundColor: 'rgba(15,23,42,0.9)',
                          border: '1px solid rgba(148,163,184,0.3)',
                        }}
                      />
                    </div>
                  </article>
                ))
              : filteredProducts.map((product) => (
                  <article key={product.id} className="product-card">
                    <div className="image-wrapper">
                      <img src={product.image} alt={product.title} loading="lazy" />
                    </div>
                    <div className="product-info">
                      <h3 title={product.title}>
                        {product.title.length > 38
                          ? `${product.title.slice(0, 38)}…`
                          : product.title}
                      </h3>
                      <p className="price">{formatPrice(product.price)}</p>
                      <small style={{ fontSize: '0.7rem', color: '#9ca3af' }}>
                        {getCategoryLabel(product.category)}
                      </small>
                    </div>
                  </article>
                ))}
          </div>
        </section>
      </main>
    </div>
  );
};

export default FakeStorePage;
