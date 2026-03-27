import { Container, Row, Col, Card, CardGroup, Button, ListGroup } from 'react-bootstrap';

export default function CardsPage() {
  return (
    <Container className="py-4 cards-demo page-shell">
      <h1 className="text-center mb-5">
        <i className="bi bi-card-heading me-2" />
        Bootstrap 카드 컴포넌트 예제
      </h1>

      <Row className="mb-5">
        <Col md={12}>
          <h2 className="mb-4">1. 기본 카드</h2>
        </Col>
        <Col md={4}>
          <Card>
            <Card.Body>
              <Card.Title>카드 제목</Card.Title>
              <Card.Text>이것은 기본 카드입니다. 간단한 텍스트와 제목을 포함할 수 있습니다.</Card.Text>
              <Button variant="primary">자세히 보기</Button>
            </Card.Body>
          </Card>
        </Col>
        <Col md={4}>
          <Card>
            <Card.Body>
              <Card.Title>
                <i className="bi bi-star-fill text-warning me-2" />
                아이콘 카드
              </Card.Title>
              <Card.Text>아이콘과 함께 사용할 수 있는 카드입니다.</Card.Text>
              <Button variant="outline-primary">더보기</Button>
            </Card.Body>
          </Card>
        </Col>
        <Col md={4}>
          <Card border="success">
            <Card.Body>
              <Card.Title className="text-success">테두리 색상</Card.Title>
              <Card.Text>테두리 색상을 변경할 수 있습니다.</Card.Text>
              <Button variant="success">확인</Button>
            </Card.Body>
          </Card>
        </Col>
      </Row>

      <Row className="mb-5">
        <Col md={12}>
          <h2 className="mb-4">2. 이미지 카드</h2>
        </Col>
        <Col md={4}>
          <Card>
            <Card.Img
              variant="top"
              src="https://placehold.co/400x200/667eea/ffffff?text=Card+Image"
              alt="카드 이미지"
            />
            <Card.Body>
              <Card.Title>이미지 카드</Card.Title>
              <Card.Text>이미지가 포함된 카드입니다.</Card.Text>
              <Button variant="primary">자세히</Button>
            </Card.Body>
          </Card>
        </Col>
        <Col md={4}>
          <Card className="text-white">
            <Card.Img src="https://placehold.co/400x200/764ba2/ffffff?text=Overlay" alt="오버레이" />
            <Card.ImgOverlay>
              <Card.Title>오버레이 카드</Card.Title>
              <Card.Text>이미지 위에 텍스트를 올릴 수 있습니다.</Card.Text>
            </Card.ImgOverlay>
          </Card>
        </Col>
        <Col md={4}>
          <Card>
            <Card.Body className="text-center">
              <i className="bi bi-heart-fill icon-large text-danger mb-3 d-block" />
              <Card.Title>아이콘 카드</Card.Title>
              <Card.Text>아이콘을 사용한 카드입니다.</Card.Text>
              <Button variant="danger">좋아요</Button>
            </Card.Body>
          </Card>
        </Col>
      </Row>

      <Row className="mb-5">
        <Col md={12}>
          <h2 className="mb-4">3. 헤더/푸터 카드</h2>
        </Col>
        <Col md={6}>
          <Card>
            <Card.Header className="bg-primary text-white">
              <h5 className="mb-0">
                <i className="bi bi-info-circle me-2" />
                카드 헤더
              </h5>
            </Card.Header>
            <Card.Body>
              <Card.Text>헤더가 있는 카드입니다. 중요한 정보를 표시할 때 유용합니다.</Card.Text>
            </Card.Body>
            <Card.Footer className="text-muted">
              <small>2시간 전</small>
            </Card.Footer>
          </Card>
        </Col>
        <Col md={6}>
          <Card>
            <Card.Header className="bg-success text-white">
              <h5 className="mb-0">
                <i className="bi bi-check-circle me-2" />
                성공 메시지
              </h5>
            </Card.Header>
            <Card.Body>
              <Card.Text>작업이 성공적으로 완료되었습니다!</Card.Text>
            </Card.Body>
            <Card.Footer>
              <Button variant="success" size="sm">
                확인
              </Button>
            </Card.Footer>
          </Card>
        </Col>
      </Row>

      <Row className="mb-5">
        <Col md={12}>
          <h2 className="mb-4">4. 카드 그룹</h2>
        </Col>
        <Col md={12}>
          <CardGroup>
            <Card>
              <Card.Body className="text-center">
                <i className="bi bi-lightning-charge-fill icon-large text-warning mb-3 d-block" />
                <Card.Title>빠름</Card.Title>
                <Card.Text>빠른 성능</Card.Text>
              </Card.Body>
            </Card>
            <Card>
              <Card.Body className="text-center">
                <i className="bi bi-shield-check-fill icon-large text-success mb-3 d-block" />
                <Card.Title>안전</Card.Title>
                <Card.Text>보안 강화</Card.Text>
              </Card.Body>
            </Card>
            <Card>
              <Card.Body className="text-center">
                <i className="bi bi-phone-fill icon-large text-info mb-3 d-block" />
                <Card.Title>반응형</Card.Title>
                <Card.Text>모든 기기 지원</Card.Text>
              </Card.Body>
            </Card>
          </CardGroup>
        </Col>
      </Row>

      <Row>
        <Col md={12}>
          <h2 className="mb-4">5. 리스트 그룹 카드</h2>
        </Col>
        <Col md={6}>
          <Card>
            <Card.Header>
              <h5 className="mb-0">기능 목록</h5>
            </Card.Header>
            <ListGroup variant="flush">
              <ListGroup.Item>
                <i className="bi bi-check-circle-fill text-success me-2" />
                기능 1
              </ListGroup.Item>
              <ListGroup.Item>
                <i className="bi bi-check-circle-fill text-success me-2" />
                기능 2
              </ListGroup.Item>
              <ListGroup.Item>
                <i className="bi bi-x-circle-fill text-danger me-2" />
                기능 3 (비활성)
              </ListGroup.Item>
            </ListGroup>
            <Card.Footer>
              <Button variant="primary">업그레이드</Button>
            </Card.Footer>
          </Card>
        </Col>
        <Col md={6}>
          <Card>
            <Card.Header className="bg-dark text-white">
              <h5 className="mb-0">
                <i className="bi bi-list-ul me-2" />
                메뉴
              </h5>
            </Card.Header>
            <ListGroup variant="flush">
              <ListGroup.Item action href="#">
                <i className="bi bi-house me-2" />
                홈
              </ListGroup.Item>
              <ListGroup.Item action href="#">
                <i className="bi bi-person me-2" />
                프로필
              </ListGroup.Item>
              <ListGroup.Item action href="#">
                <i className="bi bi-gear me-2" />
                설정
              </ListGroup.Item>
            </ListGroup>
          </Card>
        </Col>
      </Row>
    </Container>
  );
}
