import { useState } from 'react';
import {
  Container,
  Form,
  Button,
  InputGroup,
  Alert,
  Row,
  Col,
} from 'react-bootstrap';

export default function FormComponentsPage() {
  const [rangeValue, setRangeValue] = useState(50);
  const [validated, setValidated] = useState(false);
  const [completeResult, setCompleteResult] = useState(null);

  const handleValidationSubmit = (e) => {
    const form = e.currentTarget;
    e.preventDefault();
    if (form.checkValidity() === false) {
      e.stopPropagation();
    }
    setValidated(true);
  };

  const handleCompleteSubmit = (e) => {
    e.preventDefault();
    const fd = new FormData(e.currentTarget);
    const firstName = fd.get('firstName');
    const email = fd.get('completeEmail');
    setCompleteResult(
      <>
        <i className="bi bi-check-circle me-2" />
        <strong>성공!</strong> {firstName}님의 정보가 제출되었습니다. ({email})
      </>
    );
  };

  return (
    <Container className="py-4 page-shell">
      <h1 className="text-center mb-5">
        <i className="bi bi-input-cursor-text me-2" />
        Bootstrap 폼 컴포넌트 예제
      </h1>

      <div className="section">
        <div className="form-section">
          <h2 className="mb-4">1. 기본 입력 필드</h2>
          <Form.Group className="mb-3">
            <Form.Label>기본 입력</Form.Label>
            <Form.Control type="text" placeholder="텍스트를 입력하세요" />
          </Form.Group>
          <Form.Group className="mb-3">
            <Form.Label>이메일</Form.Label>
            <Form.Control type="email" placeholder="email@example.com" />
          </Form.Group>
          <Form.Group className="mb-3">
            <Form.Label>비밀번호</Form.Label>
            <Form.Control type="password" placeholder="비밀번호를 입력하세요" />
          </Form.Group>
          <Form.Group className="mb-3">
            <Form.Label>텍스트 영역</Form.Label>
            <Form.Control as="textarea" rows={3} placeholder="여러 줄 텍스트를 입력하세요" />
          </Form.Group>
        </div>
      </div>

      <div className="section">
        <div className="form-section">
          <h2 className="mb-4">2. 입력 그룹</h2>
          <InputGroup className="mb-3">
            <InputGroup.Text><i className="bi bi-person" /></InputGroup.Text>
            <Form.Control placeholder="사용자 이름" />
          </InputGroup>
          <InputGroup className="mb-3">
            <InputGroup.Text>@</InputGroup.Text>
            <Form.Control placeholder="사용자명" />
            <InputGroup.Text>.com</InputGroup.Text>
          </InputGroup>
          <InputGroup className="mb-3">
            <InputGroup.Text>₩</InputGroup.Text>
            <Form.Control type="number" placeholder="금액" />
            <InputGroup.Text>원</InputGroup.Text>
          </InputGroup>
        </div>
      </div>

      <div className="section">
        <div className="form-section">
          <h2 className="mb-4">3. 선택 박스</h2>
          <Form.Group className="mb-3">
            <Form.Label>기본 선택</Form.Label>
            <Form.Select defaultValue="">
              <option value="" disabled>선택하세요</option>
              <option value="1">옵션 1</option>
              <option value="2">옵션 2</option>
              <option value="3">옵션 3</option>
            </Form.Select>
          </Form.Group>
          <Form.Group className="mb-3">
            <Form.Label>다중 선택</Form.Label>
            <Form.Select multiple size={3}>
              <option value="1">옵션 1</option>
              <option value="2">옵션 2</option>
              <option value="3">옵션 3</option>
              <option value="4">옵션 4</option>
            </Form.Select>
            <Form.Text className="text-muted">Ctrl(또는 Cmd)를 누른 채로 여러 개 선택하세요</Form.Text>
          </Form.Group>
        </div>
      </div>

      <div className="section">
        <div className="form-section">
          <h2 className="mb-4">4. 체크박스와 라디오 버튼</h2>
          <Form.Group className="mb-3">
            <Form.Label>체크박스</Form.Label>
            <Form.Check type="checkbox" id="check1" label="옵션 1" />
            <Form.Check type="checkbox" id="check2" label="옵션 2 (기본 선택)" defaultChecked />
            <Form.Check type="checkbox" id="check3" label="옵션 3" />
          </Form.Group>
          <Form.Group className="mb-3">
            <Form.Label>라디오 버튼</Form.Label>
            <Form.Check type="radio" name="radioGroup" id="radio1" label="선택 1" defaultChecked />
            <Form.Check type="radio" name="radioGroup" id="radio2" label="선택 2" />
            <Form.Check type="radio" name="radioGroup" id="radio3" label="선택 3" />
          </Form.Group>
        </div>
      </div>

      <div className="section">
        <div className="form-section">
          <h2 className="mb-4">5. 범위 슬라이더</h2>
          <Form.Group className="mb-3">
            <Form.Label>
              기본 범위: <span>{rangeValue}</span>
            </Form.Label>
            <Form.Range min={0} max={100} value={rangeValue} onChange={(e) => setRangeValue(Number(e.target.value))} />
          </Form.Group>
          <Form.Group className="mb-3">
            <Form.Label>비활성화된 범위</Form.Label>
            <Form.Range min={0} max={100} defaultValue={30} disabled />
          </Form.Group>
        </div>
      </div>

      <div className="section">
        <div className="form-section">
          <h2 className="mb-4">6. 파일 입력</h2>
          <Form.Group className="mb-3">
            <Form.Label>파일 선택</Form.Label>
            <Form.Control type="file" />
          </Form.Group>
          <Form.Group className="mb-3">
            <Form.Label>여러 파일 선택</Form.Label>
            <Form.Control type="file" multiple />
          </Form.Group>
        </div>
      </div>

      <div className="section">
        <div className="form-section">
          <h2 className="mb-4">7. 폼 검증</h2>
          <Form noValidate validated={validated} onSubmit={handleValidationSubmit}>
            <Form.Group className="mb-3" controlId="validInput">
              <Form.Label>필수 입력</Form.Label>
              <Form.Control type="text" required />
              <Form.Control.Feedback type="valid">좋습니다!</Form.Control.Feedback>
              <Form.Control.Feedback type="invalid">이 필드는 필수입니다.</Form.Control.Feedback>
            </Form.Group>
            <Form.Group className="mb-3" controlId="validEmail">
              <Form.Label>이메일</Form.Label>
              <Form.Control type="email" required />
              <Form.Control.Feedback type="invalid">유효한 이메일을 입력하세요.</Form.Control.Feedback>
            </Form.Group>
            <Form.Group className="mb-3" controlId="validSelect">
              <Form.Label>선택</Form.Label>
              <Form.Select required defaultValue="">
                <option value="">선택하세요</option>
                <option value="1">옵션 1</option>
                <option value="2">옵션 2</option>
              </Form.Select>
              <Form.Control.Feedback type="invalid">옵션을 선택하세요.</Form.Control.Feedback>
            </Form.Group>
            <Button type="submit">제출</Button>
          </Form>
        </div>
      </div>

      <div className="section">
        <div className="form-section">
          <h2 className="mb-4">8. 완성된 폼 예제</h2>
          <Form onSubmit={handleCompleteSubmit}>
            <Row>
              <Col md={6}>
                <Form.Group className="mb-3">
                  <Form.Label>이름</Form.Label>
                  <Form.Control name="firstName" required />
                </Form.Group>
              </Col>
              <Col md={6}>
                <Form.Group className="mb-3">
                  <Form.Label>성</Form.Label>
                  <Form.Control name="lastName" required />
                </Form.Group>
              </Col>
            </Row>
            <Form.Group className="mb-3">
              <Form.Label>이메일</Form.Label>
              <InputGroup>
                <InputGroup.Text><i className="bi bi-envelope" /></InputGroup.Text>
                <Form.Control type="email" name="completeEmail" required />
              </InputGroup>
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label>비밀번호</Form.Label>
              <Form.Control type="password" name="completePassword" required />
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label>지역</Form.Label>
              <Form.Select name="region" required defaultValue="">
                <option value="">선택하세요</option>
                <option value="seoul">서울</option>
                <option value="busan">부산</option>
                <option value="daegu">대구</option>
              </Form.Select>
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Check
                type="checkbox"
                name="agree"
                id="agreeCheck"
                label="이용약관에 동의합니다"
                required
              />
            </Form.Group>
            <div className="d-grid gap-2">
              <Button type="submit" variant="primary" size="lg">
                <i className="bi bi-check-circle me-2" />
                제출하기
              </Button>
            </div>
          </Form>
        </div>
      </div>

      {completeResult && (
        <Alert variant="info" dismissible onClose={() => setCompleteResult(null)}>
          {completeResult}
        </Alert>
      )}
    </Container>
  );
}
