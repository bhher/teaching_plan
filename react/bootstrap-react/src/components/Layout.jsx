import { Outlet, NavLink } from 'react-router-dom';
import { Navbar, Nav, Container, NavDropdown } from 'react-bootstrap';

const linkClass = ({ isActive }) =>
  isActive ? 'nav-link active text-white' : 'nav-link text-white-50';

export default function Layout() {
  return (
    <>
      <Navbar bg="dark" variant="dark" expand="lg" fixed="top" className="shadow-sm">
        <Container fluid>
          <Navbar.Brand as={NavLink} to="/" className="fw-bold">
            <i className="bi bi-bootstrap me-2" />
            Bootstrap React
          </Navbar.Brand>
          <Navbar.Toggle aria-controls="main-nav" />
          <Navbar.Collapse id="main-nav">
            <Nav className="me-auto">
              <Nav.Link as={NavLink} to="/01-login" className={linkClass}>
                01 로그인
              </Nav.Link>
              <Nav.Link as={NavLink} to="/02-nav" className={linkClass}>
                02 네비
              </Nav.Link>
              <Nav.Link as={NavLink} to="/03-card" className={linkClass} end>
                03 카드
              </Nav.Link>
              <Nav.Link as={NavLink} to="/04-buttons" className={linkClass}>
                04 버튼·알림
              </Nav.Link>
              <Nav.Link as={NavLink} to="/05-modals" className={linkClass}>
                05 모달
              </Nav.Link>
              <Nav.Link as={NavLink} to="/06-forms" className={linkClass}>
                06 폼
              </Nav.Link>
              <Nav.Link as={NavLink} to="/07-grid" className={linkClass}>
                07 그리드
              </Nav.Link>
            </Nav>
            <Nav>
              <NavDropdown title="바로가기" id="nav-dropdown" align="end">
                <NavDropdown.Item as={NavLink} to="/01-login">
                  로그인 폼
                </NavDropdown.Item>
                <NavDropdown.Item as={NavLink} to="/05-modals">
                  모달
                </NavDropdown.Item>
                <NavDropdown.Divider />
                <NavDropdown.Item href="https://react-bootstrap.github.io/" target="_blank" rel="noreferrer">
                  react-bootstrap 문서
                </NavDropdown.Item>
              </NavDropdown>
            </Nav>
          </Navbar.Collapse>
        </Container>
      </Navbar>
      <div style={{ paddingTop: '56px' }}>
        <Outlet />
      </div>
    </>
  );
}
