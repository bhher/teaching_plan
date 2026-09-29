import { Container, Row, Col, Navbar, Alert } from 'react-bootstrap'
import PhotoCard from './PhotoCard.jsx'

/** 사진 포함 카드 6장 */
const cards = [
  {
    id: 1,
    title: '웹 개발',
    text: 'HTML · CSS · Bootstrap으로 반응형 사이트를 만듭니다.',
    img: 'https://images.unsplash.com/photo-1498050108023-c5249f4df085?w=600&q=80',
    btn: 'outline-primary',
  },
  {
    id: 2,
    title: '모바일 UI',
    text: '작은 화면 우선 Mobile First 레이아웃.',
    img: 'https://images.unsplash.com/photo-1512941937669-90a1b58e7e9c?w=600&q=80',
    btn: 'outline-success',
  },
  {
    id: 3,
    title: '쇼핑몰',
    text: '상품 카드 · Modal · Navbar 구성 예제.',
    img: 'https://images.unsplash.com/photo-1460925895917-afdab827c52f?w=600&q=80',
    btn: 'outline-danger',
  },
  {
    id: 4,
    title: '포트폴리오',
    text: '원페이지 · 탭 · 아코디언 활용.',
    img: 'https://images.unsplash.com/photo-1524758631624-e2822e304c36?w=600&q=80',
    btn: 'outline-secondary',
  },
  {
    id: 5,
    title: 'JavaScript',
    text: '이벤트 · DOM · 인터랙션 기초.',
    img: 'https://images.unsplash.com/photo-1555066931-4365d14bab8c?w=600&q=80',
    btn: 'outline-warning',
  },
  {
    id: 6,
    title: 'React',
    text: '컴포넌트 · Props · map 렌더링.',
    img: 'https://images.unsplash.com/photo-1633356122544-f134324a6cee?w=600&q=80',
    btn: 'outline-info',
  },
]

export default function App() {
  return (
    <>
      <Navbar bg="dark" variant="dark">
        <Container>
          <Navbar.Brand>react-bootstrap — 카드 6장</Navbar.Brand>
          <Navbar.Text className="text-white-50 small">사진 · Card.Img · map</Navbar.Text>
        </Container>
      </Navbar>

      <Container className="py-5">
        <div className="text-center mb-4">
          <h1 className="h3 fw-bold">카드 6장 (사진 + map)</h1>
          <p className="text-muted mb-0">
            <code>Card.Img</code> + <code>Row</code> / <code>Col</code> + <code>map</code>
          </p>
        </div>

        <Row className="g-4">
          {cards.map((card) => (
            <Col key={card.id} xs={12} md={6} lg={4}>
              <PhotoCard card={card} />
            </Col>
          ))}
        </Row>

        <Alert variant="info" className="mt-5 mb-0">
          HTML: <code>boot/examples/08-카드6장.html</code>
          · CDN React: <code>../cards6-map</code>
        </Alert>
      </Container>
    </>
  )
}
