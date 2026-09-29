import PhotoCard from './PhotoCard.jsx'

/** 사진 포함 카드 6장 — CDN Bootstrap className + map */
const cards = [
  {
    id: 1,
    title: '웹 개발',
    text: 'HTML · CSS · Bootstrap으로 반응형 사이트를 만듭니다.',
    img: 'https://images.unsplash.com/photo-1498050108023-c5249f4df085?w=600&q=80',
    btn: 'btn-outline-primary',
  },
  {
    id: 2,
    title: '모바일 UI',
    text: '작은 화면 우선 Mobile First 레이아웃.',
    img: 'https://images.unsplash.com/photo-1512941937669-90a1b58e7e9c?w=600&q=80',
    btn: 'btn-outline-success',
  },
  {
    id: 3,
    title: '쇼핑몰',
    text: '상품 카드 · Modal · Navbar 구성 예제.',
    img: 'https://images.unsplash.com/photo-1460925895917-afdab827c52f?w=600&q=80',
    btn: 'btn-outline-danger',
  },
  {
    id: 4,
    title: '포트폴리오',
    text: '원페이지 · 탭 · 아코디언 활용.',
    img: 'https://images.unsplash.com/photo-1524758631624-e2822e304c36?w=600&q=80',
    btn: 'btn-outline-secondary',
  },
  {
    id: 5,
    title: 'JavaScript',
    text: '이벤트 · DOM · 인터랙션 기초.',
    img: 'https://images.unsplash.com/photo-1555066931-4365d14bab8c?w=600&q=80',
    btn: 'btn-outline-warning',
  },
  {
    id: 6,
    title: 'React',
    text: '컴포넌트 · Props · map 렌더링.',
    img: 'https://images.unsplash.com/photo-1633356122544-f134324a6cee?w=600&q=80',
    btn: 'btn-outline-info',
  },
]

export default function App() {
  return (
    <>
      <nav className="navbar navbar-dark bg-dark">
        <div className="container">
          <span className="navbar-brand mb-0 h1 fs-5">CDN — 카드 6장 (사진)</span>
          <span className="navbar-text text-white-50 small">Bootstrap CDN + className + map</span>
        </div>
      </nav>

      <main className="container py-5">
        <div className="text-center mb-4">
          <h1 className="h3 fw-bold">카드 6장 (CDN)</h1>
          <p className="text-muted mb-0">
            <code>index.html</code>에서 Bootstrap CDN 연결 · <code>className</code> + <code>map</code>
          </p>
        </div>

        <div className="row g-4">
          {cards.map((card) => (
            <div className="col-12 col-md-6 col-lg-4" key={card.id}>
              <PhotoCard card={card} />
            </div>
          ))}
        </div>

        <div className="alert alert-secondary mt-5 mb-0">
          <strong>CDN:</strong> Bootstrap을 <code>npm</code> 설치하지 않고 링크로 사용합니다.
          · react-bootstrap 버전 → <code>../cards6-bootstrap</code>
        </div>
      </main>
    </>
  )
}
