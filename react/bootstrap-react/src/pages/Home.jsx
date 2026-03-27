import { Link } from 'react-router-dom';
import { Container, Row, Col, Card } from 'react-bootstrap';

const demos = [
  { to: '/01-login', title: '01 로그인 폼', desc: '카드형 로그인, 제출 시 알림', icon: 'bi-box-arrow-in-right' },
  { to: '/02-nav', title: '02 네비게이션', desc: '고정 네비 + 앵커 스크롤', icon: 'bi-list' },
  { to: '/03-card', title: '03 카드', desc: '기본·이미지·헤더·그룹·리스트', icon: 'bi-card-heading' },
  { to: '/04-buttons', title: '04 버튼·알림', desc: '버튼, Alert, 배지, 스피너', icon: 'bi-cursor-fill' },
  { to: '/05-modals', title: '05 모달', desc: '크기·폼·확인·스크롤', icon: 'bi-window' },
  { to: '/06-forms', title: '06 폼 컴포넌트', desc: '입력, 검증, 완성 폼', icon: 'bi-input-cursor-text' },
  { to: '/07-grid', title: '07 그리드', desc: '12열, 반응형, 오프셋', icon: 'bi-grid-3x3-gap' },
];

export default function Home() {
  return (
    <Container className="py-5 page-shell">
      <h1 className="text-center mb-2">
        <i className="bi bi-bootstrap-fill text-primary me-2" />
        Bootstrap + React
      </h1>
      <p className="text-center text-muted mb-5">
        기존 <code>react/bootstrap</code> HTML 예제를 <strong>react-bootstrap</strong>으로 옮긴 모음입니다.
      </p>
      <Row className="g-4">
        {demos.map((d) => (
          <Col key={d.to} md={6} lg={4}>
            <Link to={d.to} className="text-decoration-none text-dark">
              <Card className="h-100 shadow-sm border-0">
                <Card.Body>
                  <Card.Title className="fs-6">
                    <i className={`bi ${d.icon} text-primary me-2`} />
                    {d.title}
                  </Card.Title>
                  <Card.Text className="text-muted small mb-0">{d.desc}</Card.Text>
                </Card.Body>
              </Card>
            </Link>
          </Col>
        ))}
      </Row>
    </Container>
  );
}
