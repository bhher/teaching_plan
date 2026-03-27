import { useRef } from 'react';
import { Link } from 'react-router-dom';
import {
  Container,
  Navbar,
  Nav,
  NavDropdown,
  Form,
  Button,
} from 'react-bootstrap';

export default function NavigationPage() {
  const homeRef = useRef(null);
  const aboutRef = useRef(null);
  const servicesRef = useRef(null);

  const scrollTo = (ref) => {
    ref.current?.scrollIntoView({ behavior: 'smooth', block: 'start' });
  };

  return (
    <div className="nav-demo-body">
      <div className="position-fixed top-0 end-0 p-2 z-3" style={{ marginTop: '4px' }}>
        <Button as={Link} to="/" variant="outline-light" size="sm">
          ← 메인
        </Button>
      </div>
      <Navbar bg="dark" variant="dark" expand="lg" fixed="top" className="shadow-sm">
        <Container>
          <Navbar.Brand href="#">
            <i className="bi bi-house-door me-2" />
            My Website
          </Navbar.Brand>
          <Navbar.Toggle aria-controls="demo-navbar" />
          <Navbar.Collapse id="demo-navbar">
            <Nav className="me-auto">
              <Nav.Link href="#home" onClick={(e) => { e.preventDefault(); scrollTo(homeRef); }}>
                <i className="bi bi-house me-1" />
                홈
              </Nav.Link>
              <Nav.Link href="#about" onClick={(e) => { e.preventDefault(); scrollTo(aboutRef); }}>
                <i className="bi bi-info-circle me-1" />
                소개
              </Nav.Link>
              <Nav.Link href="#services" onClick={(e) => { e.preventDefault(); scrollTo(servicesRef); }}>
                <i className="bi bi-briefcase me-1" />
                서비스
              </Nav.Link>
              <NavDropdown title={<><i className="bi bi-grid me-1" />더보기</>} id="nav-dropdown-demo">
                <NavDropdown.Item href="#">
                  <i className="bi bi-person me-2" />
                  프로필
                </NavDropdown.Item>
                <NavDropdown.Item href="#">
                  <i className="bi bi-gear me-2" />
                  설정
                </NavDropdown.Item>
                <NavDropdown.Divider />
                <NavDropdown.Item href="#">
                  <i className="bi bi-box-arrow-right me-2" />
                  로그아웃
                </NavDropdown.Item>
              </NavDropdown>
            </Nav>
            <Form className="d-flex me-3">
              <Form.Control type="search" placeholder="검색..." className="me-2" aria-label="Search" />
              <Button variant="outline-light" type="submit">
                <i className="bi bi-search" />
              </Button>
            </Form>
            <div className="d-flex">
              <Button variant="primary" className="me-2">
                <i className="bi bi-person-plus me-1" />
                회원가입
              </Button>
              <Button variant="outline-light">
                <i className="bi bi-box-arrow-in-right me-1" />
                로그인
              </Button>
            </div>
          </Navbar.Collapse>
        </Container>
      </Navbar>

      <Container className="mt-5 page-shell">
        <div ref={homeRef} id="home" className="content-section">
          <h1 className="display-4 mb-4">
            <i className="bi bi-house-door text-primary" />
            {' '}
            홈 페이지
          </h1>
          <p className="lead">
            Bootstrap 네비게이션 바 예제입니다. 다양한 스타일과 기능을 확인해보세요!
          </p>
        </div>
        <div ref={aboutRef} id="about" className="content-section">
          <h2 className="mb-4">
            <i className="bi bi-info-circle text-info" />
            {' '}
            소개
          </h2>
          <p>이 페이지는 Bootstrap의 네비게이션 컴포넌트를 보여줍니다.</p>
        </div>
        <div ref={servicesRef} id="services" className="content-section">
          <h2 className="mb-4">
            <i className="bi bi-briefcase text-success" />
            {' '}
            서비스
          </h2>
          <p>다양한 서비스를 제공합니다.</p>
        </div>
      </Container>
    </div>
  );
}
