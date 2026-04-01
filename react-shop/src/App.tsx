import React, { useMemo, useState } from 'react';
import type { FakeStoreProduct } from './hooks/useFakeStoreProducts';
import { useFakeStoreProducts } from './hooks/useFakeStoreProducts';

type View = 'home' | 'best' | 'new' | 'event';

type CartItem = FakeStoreProduct & { quantity: number };

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

export const App: React.FC = () => {
  const { data: products, loading, error } = useFakeStoreProducts();
  const [view, setView] = useState<View>('home');
  const [search, setSearch] = useState('');
  const [selectedCategory, setSelectedCategory] = useState<string>('all');
  const [cartOpen, setCartOpen] = useState(false);
  const [cart, setCart] = useState<CartItem[]>([]);

  const categories = useMemo(() => {
    const set = new Set(products.map((p) => p.category));
    return Array.from(set);
  }, [products]);

  const addToCart = (product: FakeStoreProduct) => {
    setCart((prev) => {
      const existing = prev.find((p) => p.id === product.id);
      if (existing) {
        return prev.map((p) =>
          p.id === product.id ? { ...p, quantity: p.quantity + 1 } : p,
        );
      }
      return [...prev, { ...product, quantity: 1 }];
    });
    setCartOpen(true);
  };

  const cartCount = useMemo(
    () => cart.reduce((sum, item) => sum + item.quantity, 0),
    [cart],
  );

  const cartTotal = useMemo(
    () => cart.reduce((sum, item) => sum + item.price * item.quantity, 0),
    [cart],
  );

  const baseFiltered = useMemo(() => {
    let list = products;

    if (selectedCategory !== 'all') {
      list = list.filter((p) => p.category === selectedCategory);
    }

    if (search.trim()) {
      const q = search.trim().toLowerCase();
      list = list.filter(
        (p) =>
          p.title.toLowerCase().includes(q) ||
          getCategoryLabel(p.category).toLowerCase().includes(q),
      );
    }

    return list;
  }, [products, selectedCategory, search]);

  const viewProducts = useMemo(() => {
    switch (view) {
      case 'best':
        return [...baseFiltered]
          .sort((a, b) => (b.rating?.rate ?? 0) - (a.rating?.rate ?? 0))
          .slice(0, 8);
      case 'new':
        return [...baseFiltered].sort((a, b) => b.id - a.id).slice(0, 8);
      case 'event':
        return baseFiltered.filter((p) => p.category === 'electronics').slice(0, 8);
      case 'home':
      default:
        return baseFiltered;
    }
  }, [baseFiltered, view]);

  return (
    <div className="app">
      <header className="header">
        <div className="logo">K-Culture Shop</div>
        <nav className="nav">
          <button
            type="button"
            className="nav-button"
            data-active={view === 'home' || undefined}
            onClick={() => setView('home')}
          >
            홈
          </button>
          <button
            type="button"
            className="nav-button"
            data-active={view === 'best' || undefined}
            onClick={() => setView('best')}
          >
            베스트
          </button>
          <button
            type="button"
            className="nav-button"
            data-active={view === 'new' || undefined}
            onClick={() => setView('new')}
          >
            신상품
          </button>
          <button
            type="button"
            className="nav-button"
            data-active={view === 'event' || undefined}
            onClick={() => setView('event')}
          >
            이벤트
          </button>
        </nav>
        <div className="actions">
          <input
            className="search-input"
            placeholder="상품명 / 카테고리 검색"
            value={search}
            onChange={(e) => setSearch(e.target.value)}
          />
          <button
            className="icon-button"
            type="button"
            onClick={() => setCartOpen((open) => !open)}
          >
            장바구니 {cartCount > 0 && `(${cartCount})`}
          </button>
        </div>
      </header>

      <main className="main">
        <section className="hero-banner">
          <div className="hero-content">
            <h1>한국의 멋 + Fake Store 연습용 쇼핑몰</h1>
            <p>
              Fake Store API에서 상품을 불러와서, 베스트 · 신상품 · 이벤트 탭과
              검색, 장바구니 기능까지 한 번에 연습해보는 React 쇼핑몰입니다.
            </p>
            <button className="primary-button" onClick={() => setView('best')}>
              지금 인기 상품 보기
            </button>
          </div>
        </section>

        <section className="product-section">
          <div className="section-header">
            <h2>
              {view === 'home'
                ? '전체 상품'
                : view === 'best'
                  ? '베스트 상품'
                  : view === 'new'
                    ? '신상품'
                    : '이벤트 상품'}
            </h2>
            <span className="section-subtitle">
              {loading
                ? '상품을 불러오는 중입니다...'
                : `총 ${viewProducts.length}개 · 검색: "${
                    search || '전체'
                  }" · 카테고리: ${
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

          {error && (
            <div
              style={{
                marginTop: '0.75rem',
                marginBottom: '0.75rem',
                padding: '0.6rem 0.9rem',
                borderRadius: '0.75rem',
                border: '1px solid rgba(248,113,113,0.4)',
                backgroundColor: 'rgba(127,29,29,0.3)',
                color: '#fee2e2',
                fontSize: '0.82rem',
              }}
            >
              {error}
            </div>
          )}

          <div className="product-grid">
            {loading
              ? Array.from({ length: 8 }).map((_, index) => (
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
              : viewProducts.map((product) => (
                  <article key={product.id} className="product-card">
                    <div className="image-wrapper">
                      {view === 'best' && (
                        <span className="badge">
                          BEST {product.rating ? `★ ${product.rating.rate.toFixed(1)}` : ''}
                        </span>
                      )}
                      {view === 'new' && <span className="badge">NEW</span>}
                      {view === 'event' && <span className="badge">EVENT</span>}
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
                      <button
                        className="secondary-button"
                        type="button"
                        onClick={() => addToCart(product)}
                      >
                        장바구니 담기
                      </button>
                    </div>
                  </article>
                ))}
          </div>
        </section>
      </main>

      {cartOpen && (
        <aside className="cart-panel">
          <div className="cart-header">
            <h3>장바구니</h3>
            <button
              type="button"
              className="icon-button"
              onClick={() => setCartOpen(false)}
            >
              닫기
            </button>
          </div>
          {cart.length === 0 ? (
            <p className="cart-empty">장바구니에 담긴 상품이 없습니다.</p>
          ) : (
            <>
              <ul className="cart-list">
                {cart.map((item) => (
                  <li key={item.id} className="cart-item">
                    <div className="cart-item-main">
                      <img src={item.image} alt={item.title} />
                      <div>
                        <div className="cart-item-title">
                          {item.title.length > 32
                            ? `${item.title.slice(0, 32)}…`
                            : item.title}
                        </div>
                        <div className="cart-item-meta">
                          {formatPrice(item.price)} · 수량 {item.quantity}
                        </div>
                      </div>
                    </div>
                    <div className="cart-item-actions">
                      <button
                        type="button"
                        onClick={() =>
                          setCart((prev) =>
                            prev
                              .map((p) =>
                                p.id === item.id
                                  ? { ...p, quantity: Math.max(1, p.quantity - 1) }
                                  : p,
                              )
                              .filter((p) => p.quantity > 0),
                          )
                        }
                      >
                        -
                      </button>
                      <button
                        type="button"
                        onClick={() =>
                          setCart((prev) =>
                            prev.map((p) =>
                              p.id === item.id ? { ...p, quantity: p.quantity + 1 } : p,
                            ),
                          )
                        }
                      >
                        +
                      </button>
                      <button
                        type="button"
                        onClick={() =>
                          setCart((prev) => prev.filter((p) => p.id !== item.id))
                        }
                      >
                        삭제
                      </button>
                    </div>
                  </li>
                ))}
              </ul>
              <div className="cart-footer">
                <div>합계: {formatPrice(cartTotal)}</div>
                <button type="button" className="primary-button" disabled>
                  주문 연습 버튼
                </button>
              </div>
            </>
          )}
        </aside>
      )}

      <footer className="footer">
        <p>© {new Date().getFullYear()} K-Culture Shop. All rights reserved.</p>
      </footer>
    </div>
  );
};

export default App;
