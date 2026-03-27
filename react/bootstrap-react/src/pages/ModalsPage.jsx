import { useState } from 'react';
import {
  Container,
  Button,
  Modal,
  Form,
  Alert,
} from 'react-bootstrap';

export default function ModalsPage() {
  const [showBasic, setShowBasic] = useState(false);
  const [showLarge, setShowLarge] = useState(false);
  const [showFullscreen, setShowFullscreen] = useState(false);
  const [showForm, setShowForm] = useState(false);
  const [showConfirm, setShowConfirm] = useState(false);
  const [showScroll, setShowScroll] = useState(false);

  const [modalName, setModalName] = useState('');
  const [modalEmail, setModalEmail] = useState('');
  const [modalPassword, setModalPassword] = useState('');
  const [modalAgree, setModalAgree] = useState(false);

  const [result, setResult] = useState(null);

  const submitForm = () => {
    if (modalName && modalEmail) {
      setResult({
        variant: 'info',
        children: (
          <>
            <i className="bi bi-check-circle me-2" />
            <strong>성공!</strong> {modalName}님의 가입이 완료되었습니다. ({modalEmail})
          </>
        ),
      });
      setShowForm(false);
      setModalName('');
      setModalEmail('');
      setModalPassword('');
      setModalAgree(false);
    }
  };

  const deleteItem = () => {
    setResult({
      variant: 'danger',
      children: (
        <>
          <i className="bi bi-trash me-2" />
          <strong>삭제 완료!</strong> 항목이 삭제되었습니다.
        </>
      ),
    });
    setShowConfirm(false);
  };

  return (
    <Container className="py-4 page-shell">
      <h1 className="text-center mb-5">
        <i className="bi bi-window me-2" />
        Bootstrap 모달 예제
      </h1>

      {result && (
        <Alert variant={result.variant} onClose={() => setResult(null)} dismissible className="mb-4">
          {result.children}
        </Alert>
      )}

      <div className="section">
        <h2 className="mb-4">1. 기본 모달</h2>
        <Button variant="primary" onClick={() => setShowBasic(true)}>기본 모달 열기</Button>
        <Modal show={showBasic} onHide={() => setShowBasic(false)} centered>
          <Modal.Header closeButton>
            <Modal.Title>
              <i className="bi bi-info-circle me-2" />
              기본 모달
            </Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <p>이것은 기본 모달입니다. 사용자에게 정보를 표시하거나 확인을 받을 때 사용합니다.</p>
          </Modal.Body>
          <Modal.Footer>
            <Button variant="secondary" onClick={() => setShowBasic(false)}>닫기</Button>
            <Button variant="primary" onClick={() => setShowBasic(false)}>확인</Button>
          </Modal.Footer>
        </Modal>
      </div>

      <div className="section">
        <h2 className="mb-4">2. 큰 모달</h2>
        <Button variant="success" onClick={() => setShowLarge(true)}>큰 모달 열기</Button>
        <Modal show={showLarge} onHide={() => setShowLarge(false)} size="lg">
          <Modal.Header closeButton>
            <Modal.Title>큰 모달</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <p>이 모달은 더 넓은 공간을 제공합니다. 많은 내용을 표시할 때 유용합니다.</p>
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
          </Modal.Body>
          <Modal.Footer>
            <Button variant="secondary" onClick={() => setShowLarge(false)}>닫기</Button>
            <Button variant="success" onClick={() => setShowLarge(false)}>저장</Button>
          </Modal.Footer>
        </Modal>
      </div>

      <div className="section">
        <h2 className="mb-4">3. 전체 화면 모달</h2>
        <Button variant="warning" onClick={() => setShowFullscreen(true)}>전체 화면 모달 열기</Button>
        <Modal show={showFullscreen} onHide={() => setShowFullscreen(false)} fullscreen>
          <Modal.Header closeButton>
            <Modal.Title>전체 화면 모달</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <p>이 모달은 전체 화면을 차지합니다.</p>
          </Modal.Body>
          <Modal.Footer>
            <Button variant="secondary" onClick={() => setShowFullscreen(false)}>닫기</Button>
          </Modal.Footer>
        </Modal>
      </div>

      <div className="section">
        <h2 className="mb-4">4. 폼 모달</h2>
        <Button variant="info" onClick={() => setShowForm(true)}>폼 모달 열기</Button>
        <Modal show={showForm} onHide={() => setShowForm(false)}>
          <Modal.Header closeButton>
            <Modal.Title>
              <i className="bi bi-person-plus me-2" />
              회원가입
            </Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <Form>
              <Form.Group className="mb-3">
                <Form.Label>이름</Form.Label>
                <Form.Control
                  type="text"
                  value={modalName}
                  onChange={(e) => setModalName(e.target.value)}
                  required
                />
              </Form.Group>
              <Form.Group className="mb-3">
                <Form.Label>이메일</Form.Label>
                <Form.Control
                  type="email"
                  value={modalEmail}
                  onChange={(e) => setModalEmail(e.target.value)}
                  required
                />
              </Form.Group>
              <Form.Group className="mb-3">
                <Form.Label>비밀번호</Form.Label>
                <Form.Control
                  type="password"
                  value={modalPassword}
                  onChange={(e) => setModalPassword(e.target.value)}
                  required
                />
              </Form.Group>
              <Form.Check
                type="checkbox"
                id="modalAgree"
                label="이용약관에 동의합니다"
                checked={modalAgree}
                onChange={(e) => setModalAgree(e.target.checked)}
              />
            </Form>
          </Modal.Body>
          <Modal.Footer>
            <Button variant="secondary" onClick={() => setShowForm(false)}>취소</Button>
            <Button variant="primary" onClick={submitForm}>가입하기</Button>
          </Modal.Footer>
        </Modal>
      </div>

      <div className="section">
        <h2 className="mb-4">5. 확인 모달</h2>
        <Button variant="danger" onClick={() => setShowConfirm(true)}>삭제 확인 모달</Button>
        <Modal show={showConfirm} onHide={() => setShowConfirm(false)}>
          <Modal.Header closeButton closeVariant="white" className="bg-danger text-white border-0">
            <Modal.Title>
              <i className="bi bi-exclamation-triangle me-2" />
              확인 필요
            </Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <p>정말로 삭제하시겠습니까?</p>
            <p className="text-muted mb-0">이 작업은 되돌릴 수 없습니다.</p>
          </Modal.Body>
          <Modal.Footer>
            <Button variant="secondary" onClick={() => setShowConfirm(false)}>취소</Button>
            <Button variant="danger" onClick={deleteItem}>삭제</Button>
          </Modal.Footer>
        </Modal>
      </div>

      <div className="section">
        <h2 className="mb-4">6. 스크롤 가능한 모달</h2>
        <Button variant="primary" onClick={() => setShowScroll(true)}>스크롤 모달 열기</Button>
        <Modal show={showScroll} onHide={() => setShowScroll(false)} scrollable>
          <Modal.Header closeButton>
            <Modal.Title>스크롤 가능한 모달</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <p>이 모달은 내용이 많을 때 스크롤할 수 있습니다.</p>
            {Array.from({ length: 8 }).map((_, i) => (
              <p key={i}>
                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore
                et dolore magna aliqua. 문단 {i + 1}
              </p>
            ))}
          </Modal.Body>
          <Modal.Footer>
            <Button variant="secondary" onClick={() => setShowScroll(false)}>닫기</Button>
          </Modal.Footer>
        </Modal>
      </div>
    </Container>
  );
}
