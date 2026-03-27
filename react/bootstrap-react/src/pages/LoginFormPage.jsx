import { useState } from 'react';
import { Link } from 'react-router-dom';
import { Container, Card, Form, Button, Alert } from 'react-bootstrap';

export default function LoginFormPage() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [rememberMe, setRememberMe] = useState(false);
  const [result, setResult] = useState(null);

  const handleSubmit = (e) => {
    e.preventDefault();
    if (username && password) {
      setResult({
        variant: 'success',
        text: (
          <>
            <strong>로그인 성공!</strong>
            <br />
            사용자: {username}
            <br />
            로그인 상태 유지: {rememberMe ? '예' : '아니오'}
          </>
        ),
      });
    } else {
      setResult({
        variant: 'danger',
        text: (
          <>
            <strong>오류!</strong> 사용자 이름과 비밀번호를 입력하세요.
          </>
        ),
      });
    }
  };

  return (
    <div className="login-page position-relative">
      <div className="position-fixed top-0 start-0 p-3 z-3">
        <Button as={Link} to="/" variant="light" size="sm" className="shadow-sm">
          ← 메인
        </Button>
      </div>
      <Container>
        <div className="login-container mx-auto">
          <Card className="login-card mb-4">
            <Card.Header className="login-header text-center py-4 border-0">
              <h3 className="mb-0 text-white">
                <i className="bi bi-person-circle me-2" />
                로그인
              </h3>
            </Card.Header>
            <Card.Body className="p-4">
              <Form onSubmit={handleSubmit}>
                <Form.Group className="mb-3" controlId="username">
                  <Form.Label>
                    <i className="bi bi-person me-2" />
                    사용자 이름
                  </Form.Label>
                  <Form.Control
                    type="text"
                    placeholder="사용자 이름을 입력하세요"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                    required
                  />
                </Form.Group>
                <Form.Group className="mb-3" controlId="password">
                  <Form.Label>
                    <i className="bi bi-lock me-2" />
                    비밀번호
                  </Form.Label>
                  <Form.Control
                    type="password"
                    placeholder="비밀번호를 입력하세요"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    required
                  />
                </Form.Group>
                <Form.Group className="mb-3" controlId="rememberMe">
                  <Form.Check
                    type="checkbox"
                    label="로그인 상태 유지"
                    checked={rememberMe}
                    onChange={(e) => setRememberMe(e.target.checked)}
                  />
                </Form.Group>
                <div className="d-grid">
                  <Button type="submit" variant="primary" size="lg">
                    <i className="bi bi-box-arrow-in-right me-2" />
                    로그인
                  </Button>
                </div>
                <div className="text-center mt-3">
                  <a href="#" className="text-decoration-none" onClick={(e) => e.preventDefault()}>
                    비밀번호를 잊으셨나요?
                  </a>
                </div>
              </Form>
            </Card.Body>
          </Card>
          {result && (
            <Alert variant={result.variant} className="d-flex align-items-start gap-2">
              <i className={`bi ${result.variant === 'success' ? 'bi-check-circle' : 'bi-exclamation-triangle'}`} />
              <div>{result.text}</div>
            </Alert>
          )}
        </div>
      </Container>
    </div>
  );
}
