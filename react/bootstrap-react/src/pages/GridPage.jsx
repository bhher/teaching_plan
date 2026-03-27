import { Container, Row, Col, Card } from 'react-bootstrap';

export default function GridPage() {
  return (
    <Container className="py-4 grid-demo page-shell">
      <h1 className="text-center mb-5">
        <i className="bi bi-grid-3x3-gap me-2" />
        Bootstrap 그리드 시스템 예제
      </h1>

      <div className="section">
        <h2 className="mb-4">1. 기본 그리드 (12열 시스템)</h2>
        <Row>
          <Col xs={12}>
            <div className="grid-item">col-12 (전체 너비)</div>
          </Col>
          <Col xs={6}>
            <div className="grid-item">col-6 (50%)</div>
          </Col>
          <Col xs={6}>
            <div className="grid-item grid-item-alt">col-6 (50%)</div>
          </Col>
          <Col xs={4}>
            <div className="grid-item">col-4 (33.33%)</div>
          </Col>
          <Col xs={4}>
            <div className="grid-item grid-item-alt">col-4 (33.33%)</div>
          </Col>
          <Col xs={4}>
            <div className="grid-item">col-4 (33.33%)</div>
          </Col>
          <Col xs={3}>
            <div className="grid-item">col-3 (25%)</div>
          </Col>
          <Col xs={3}>
            <div className="grid-item grid-item-alt">col-3 (25%)</div>
          </Col>
          <Col xs={3}>
            <div className="grid-item">col-3 (25%)</div>
          </Col>
          <Col xs={3}>
            <div className="grid-item grid-item-alt">col-3 (25%)</div>
          </Col>
        </Row>
      </div>

      <div className="section">
        <h2 className="mb-4">2. 반응형 그리드</h2>
        <Row>
          <Col xs={12} md={6} lg={4}>
            <div className="demo-box">
              <strong>col-12 col-md-6 col-lg-4</strong>
              <br />
              모바일: 전체 너비
              <br />
              태블릿: 50%
              <br />
              데스크톱: 33.33%
            </div>
          </Col>
          <Col xs={12} md={6} lg={4}>
            <div className="demo-box">
              <strong>col-12 col-md-6 col-lg-4</strong>
              <br />
              모바일: 전체 너비
              <br />
              태블릿: 50%
              <br />
              데스크톱: 33.33%
            </div>
          </Col>
          <Col xs={12} md={12} lg={4}>
            <div className="demo-box">
              <strong>col-12 col-md-12 col-lg-4</strong>
              <br />
              모바일: 전체 너비
              <br />
              태블릿: 전체 너비
              <br />
              데스크톱: 33.33%
            </div>
          </Col>
        </Row>
      </div>

      <div className="section">
        <h2 className="mb-4">3. 오프셋 (Offset)</h2>
        <Row>
          <Col xs={{ span: 4, offset: 4 }}>
            <div className="grid-item">col-4 offset-4 (가운데 정렬)</div>
          </Col>
        </Row>
        <Row>
          <Col xs={{ span: 3, offset: 3 }}>
            <div className="grid-item">col-3 offset-3</div>
          </Col>
          <Col xs={{ span: 3, offset: 3 }}>
            <div className="grid-item grid-item-alt">col-3 offset-3</div>
          </Col>
        </Row>
      </div>

      <div className="section">
        <h2 className="mb-4">4. 중첩 그리드</h2>
        <Row>
          <Col md={8}>
            <div className="demo-box">
              <strong>외부 컬럼 (col-8)</strong>
              <Row className="mt-3">
                <Col xs={6}>
                  <div className="grid-item">내부 col-6</div>
                </Col>
                <Col xs={6}>
                  <div className="grid-item grid-item-alt">내부 col-6</div>
                </Col>
              </Row>
            </div>
          </Col>
          <Col md={4}>
            <div className="demo-box">
              <strong>외부 컬럼 (col-4)</strong>
            </div>
          </Col>
        </Row>
      </div>

      <div className="section">
        <h2 className="mb-4">5. 정렬 (Alignment)</h2>
        <Row className="align-items-start mb-3" style={{ minHeight: '100px' }}>
          <Col xs={4}>
            <div className="demo-box">align-items-start (위쪽 정렬)</div>
          </Col>
          <Col xs={4}>
            <div className="demo-box" style={{ minHeight: '60px' }}>높이가 다른 박스</div>
          </Col>
          <Col xs={4}>
            <div className="demo-box">세 번째 박스</div>
          </Col>
        </Row>
        <Row className="align-items-center mb-3" style={{ minHeight: '100px' }}>
          <Col xs={4}>
            <div className="demo-box">align-items-center (가운데 정렬)</div>
          </Col>
          <Col xs={4}>
            <div className="demo-box" style={{ minHeight: '60px' }}>높이가 다른 박스</div>
          </Col>
          <Col xs={4}>
            <div className="demo-box">세 번째 박스</div>
          </Col>
        </Row>
        <Row className="align-items-end" style={{ minHeight: '100px' }}>
          <Col xs={4}>
            <div className="demo-box">align-items-end (아래쪽 정렬)</div>
          </Col>
          <Col xs={4}>
            <div className="demo-box" style={{ minHeight: '60px' }}>높이가 다른 박스</div>
          </Col>
          <Col xs={4}>
            <div className="demo-box">세 번째 박스</div>
          </Col>
        </Row>
      </div>

      <div className="section">
        <h2 className="mb-4">6. 실제 레이아웃 예제</h2>
        <Row className="mb-3">
          <Col xs={12}>
            <div className="demo-box" style={{ backgroundColor: '#667eea', color: 'white' }}>
              <h4 className="mb-0">헤더 영역 (col-12)</h4>
            </div>
          </Col>
        </Row>
        <Row className="mb-3">
          <Col xs={12} md={8}>
            <div className="demo-box" style={{ minHeight: '200px' }}>
              <h5>메인 콘텐츠 (col-12 col-md-8)</h5>
              <p>모바일에서는 전체 너비, 태블릿 이상에서는 66.67%를 차지합니다.</p>
            </div>
          </Col>
          <Col xs={12} md={4}>
            <div
              className="demo-box"
              style={{ backgroundColor: '#764ba2', color: 'white', minHeight: '200px' }}
            >
              <h5>사이드바 (col-12 col-md-4)</h5>
              <p>모바일에서는 전체 너비, 태블릿 이상에서는 33.33%를 차지합니다.</p>
            </div>
          </Col>
        </Row>
        <Row>
          <Col xs={12} sm={6} lg={4} className="mb-3">
            <Card>
              <Card.Body>
                <Card.Title>카드 1</Card.Title>
                <Card.Text>col-12 col-sm-6 col-lg-4</Card.Text>
              </Card.Body>
            </Card>
          </Col>
          <Col xs={12} sm={6} lg={4} className="mb-3">
            <Card>
              <Card.Body>
                <Card.Title>카드 2</Card.Title>
                <Card.Text>col-12 col-sm-6 col-lg-4</Card.Text>
              </Card.Body>
            </Card>
          </Col>
          <Col xs={12} sm={6} lg={4} className="mb-3">
            <Card>
              <Card.Body>
                <Card.Title>카드 3</Card.Title>
                <Card.Text>col-12 col-sm-6 col-lg-4</Card.Text>
              </Card.Body>
            </Card>
          </Col>
        </Row>
        <Row className="mt-3">
          <Col xs={12}>
            <div className="demo-box" style={{ backgroundColor: '#667eea', color: 'white' }}>
              <h5 className="mb-0">푸터 영역 (col-12)</h5>
            </div>
          </Col>
        </Row>
      </div>

      <div className="section">
        <h2 className="mb-4">7. 유틸리티 클래스</h2>
        <Row>
          <Col xs={4}>
            <div className="demo-box">
              <div className="mb-2">여백 없음</div>
              <div className="p-3 bg-primary text-white rounded">padding-3</div>
            </div>
          </Col>
          <Col xs={4}>
            <div className="demo-box">
              <div className="mb-2">여백 있음</div>
              <div className="p-3 bg-success text-white rounded">padding-3</div>
            </div>
          </Col>
          <Col xs={4}>
            <div className="demo-box">
              <div className="mb-2">테두리</div>
              <div className="p-3 bg-info text-white rounded border border-dark border-3">border-3</div>
            </div>
          </Col>
        </Row>
      </div>
    </Container>
  );
}
