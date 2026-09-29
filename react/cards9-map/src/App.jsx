import ServiceCard from './ServiceCard.jsx'

const cards = [
  { id: 1, title: '웹 개발', text: 'HTML · CSS · Bootstrap 반응형 레이아웃', icon: 'bi-laptop', bg: 'bg-primary', btn: 'btn-outline-primary' },
  { id: 2, title: '모바일 UI', text: '작은 화면 우선 Mobile First 설계', icon: 'bi-phone', bg: 'bg-success', btn: 'btn-outline-success' },
  { id: 3, title: '그리드', text: '12칸 시스템으로 카드·레이아웃 배치', icon: 'bi-grid-3x3-gap', bg: 'bg-info', btn: 'btn-outline-info' },
  { id: 4, title: '디자인', text: '색상 · 타이포 · 여백 Utility 활용', icon: 'bi-palette', bg: 'bg-warning', btn: 'btn-outline-warning' },
  { id: 5, title: '쇼핑몰', text: '상품 카드 · Modal · Navbar 구성', icon: 'bi-shop', bg: 'bg-danger', btn: 'btn-outline-danger' },
  { id: 6, title: '포트폴리오', text: '원페이지 · 탭 · 아코디언 활용', icon: 'bi-person-badge', bg: 'bg-secondary', btn: 'btn-outline-secondary' },
  { id: 7, title: '데이터베이스', text: 'MySQL · JOIN · 서브쿼리 연동 준비', icon: 'bi-database', bg: 'bg-dark', btn: 'btn-outline-dark' },
  { id: 8, title: 'JavaScript', text: '이벤트 · DOM · 인터랙션 기초', icon: 'bi-code-slash', bg: 'bg-primary', btn: 'btn-outline-primary' },
  { id: 9, title: 'React', text: '컴포넌트 · Props · map 렌더링', icon: 'bi-filetype-jsx', bg: 'bg-success', btn: 'btn-outline-success' },
]

/** 원본: Bootstrap className + map */
export default function App() {
  return (
    <>
      <nav className="navbar navbar-dark bg-dark">
        <div className="container">
          <span className="navbar-brand mb-0 h1 fs-5">원본 — Bootstrap 클래스 + map</span>
          <span className="navbar-text text-white-50 small">className 방식</span>
        </div>
      </nav>

      <main className="container py-5">
        <div className="text-center mb-4">
          <h1 className="h3 fw-bold">카드 9장 (원본)</h1>
          <p className="text-muted mb-0">
            HTML과 같은 <code>container</code> · <code>row</code> · <code>col</code> · <code>card</code> 클래스 + <code>map</code>
          </p>
        </div>

        <div className="row g-4">
          {cards.map((card) => (
            <div className="col-12 col-md-6 col-lg-4" key={card.id}>
              <ServiceCard card={card} />
            </div>
          ))}
        </div>

        <div className="alert alert-secondary mt-5 mb-0">
          <strong>원본:</strong> Bootstrap CSS를 <code>className</code>으로 직접 지정합니다.
          · react-bootstrap 버전 → <code>../cards9-map-bootstrap</code>
        </div>
      </main>
    </>
  )
}
