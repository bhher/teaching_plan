import { Container, Row, Col, Navbar, Alert } from 'react-bootstrap'
import ServiceCard from './ServiceCard.jsx'

const cards = [
  { id: 1, title: '웹 개발', text: 'HTML · CSS · Bootstrap 반응형 레이아웃', icon: 'bi-laptop', bg: 'primary', btn: 'outline-primary' },
  { id: 2, title: '모바일 UI', text: '작은 화면 우선 Mobile First 설계', icon: 'bi-phone', bg: 'success', btn: 'outline-success' },
  { id: 3, title: '그리드', text: '12칸 시스템으로 카드·레이아웃 배치', icon: 'bi-grid-3x3-gap', bg: 'info', btn: 'outline-info' },
  { id: 4, title: '디자인', text: '색상 · 타이포 · 여백 Utility 활용', icon: 'bi-palette', bg: 'warning', btn: 'outline-warning' },
  { id: 5, title: '쇼핑몰', text: '상품 카드 · Modal · Navbar 구성', icon: 'bi-shop', bg: 'danger', btn: 'outline-danger' },
  { id: 6, title: '포트폴리오', text: '원페이지 · 탭 · 아코디언 활용', icon: 'bi-person-badge', bg: 'secondary', btn: 'outline-secondary' },
  { id: 7, title: '데이터베이스', text: 'MySQL · JOIN · 서브쿼리 연동 준비', icon: 'bi-database', bg: 'dark', btn: 'outline-dark' },
  { id: 8, title: 'JavaScript', text: '이벤트 · DOM · 인터랙션 기초', icon: 'bi-code-slash', bg: 'primary', btn: 'outline-primary' },
  { id: 9, title: 'React', text: '컴포넌트 · Props · map 렌더링', icon: 'bi-filetype-jsx', bg: 'success', btn: 'outline-success' },
]

export default function App() {
  return (
    <>
      <Navbar bg="dark" variant="dark">
        <Container>
          <Navbar.Brand>react-bootstrap — 카드 9장</Navbar.Brand>
          <Navbar.Text className="text-white-50 small">Card · Row · Col · map</Navbar.Text>
        </Container>
      </Navbar>

      <Container className="py-5">
        <div className="text-center mb-4">
          <h1 className="h3 fw-bold">카드 9장 (react-bootstrap)</h1>
          <p className="text-muted mb-0">
            <code>Container</code> · <code>Row</code> · <code>Col</code> · <code>Card</code> + <code>map</code>
          </p>
        </div>

        <Row className="g-4">
          {cards.map((card) => (
            <Col key={card.id} xs={12} md={6} lg={4}>
              <ServiceCard card={card} />
            </Col>
          ))}
        </Row>

        <Alert variant="info" className="mt-5 mb-0">
          <strong>react-bootstrap:</strong> 컴포넌트 props 사용.
          · 원본(className) → <code>../cards9-map</code>
        </Alert>
      </Container>
    </>
  )
}
