import React from 'react';

type Product = {
  id: number;
  name: string;
  price: number;
  image: string;
  badge?: string;
};

const products: Product[] = [
  {
    id: 1,
    name: '한복 원피스',
    price: 89000,
    image: 'https://images.unsplash.com/photo-1529480780694-a15fcdbcfc56?auto=format&fit=crop&w=800&q=80',
    badge: 'BEST',
  },
  {
    id: 2,
    name: '전통 노리개 세트',
    price: 29000,
    image: 'https://images.unsplash.com/photo-1612428054520-892cb4c81780?auto=format&fit=crop&w=800&q=80',
    badge: 'NEW',
  },
  {
    id: 3,
    name: '수공예 도자기 찻잔',
    price: 49000,
    image: 'https://images.unsplash.com/photo-1461023058943-07fcbe16d735?auto=format&fit=crop&w=800&q=80',
  },
  {
    id: 4,
    name: '전통 부채',
    price: 19000,
    image: 'https://images.unsplash.com/photo-1514890547357-a9ee288728e0?auto=format&fit=crop&w=800&q=80',
  },
];

const formatPrice = (price: number) =>
  price.toLocaleString('ko-KR', { style: 'currency', currency: 'KRW' });

export const App: React.FC = () => {
  return (
    <div className="app">
      <header className="header">
        <div className="logo">K-Culture Shop</div>
        <nav className="nav">
          <a href="#">홈</a>
          <a href="#">베스트</a>
          <a href="#">신상품</a>
          <a href="#">이벤트</a>
        </nav>
        <div className="actions">
          <button className="icon-button">검색</button>
          <button className="icon-button">장바구니</button>
        </div>
      </header>

      <main className="main">
        <section className="hero-banner">
          <div className="hero-content">
            <h1>한국의 멋을 담은 라이프스타일 쇼핑몰</h1>
            <p>
              한복, 공예, 소품까지. 일상 속에서 쉽게 즐기는 K-컬처
              라이프스타일을 만나보세요.
            </p>
            <button className="primary-button">지금 인기 상품 보기</button>
          </div>
        </section>

        <section className="product-section">
          <div className="section-header">
            <h2>오늘의 추천 상품</h2>
            <span className="section-subtitle">지금 가장 많이 보고 있는 상품</span>
          </div>

          <div className="product-grid">
            {products.map((product) => (
              <article key={product.id} className="product-card">
                <div className="image-wrapper">
                  {product.badge && <span className="badge">{product.badge}</span>}
                  <img src={product.image} alt={product.name} />
                </div>
                <div className="product-info">
                  <h3>{product.name}</h3>
                  <p className="price">{formatPrice(product.price)}</p>
                  <button className="secondary-button">장바구니 담기</button>
                </div>
              </article>
            ))}
          </div>
        </section>
      </main>

      <footer className="footer">
        <p>© {new Date().getFullYear()} K-Culture Shop. All rights reserved.</p>
      </footer>
    </div>
  );
};

export default App;
