import { useState, useEffect, useRef } from 'react';
import {
  Container,
  Button,
  ButtonGroup,
  Alert,
  Badge,
  Spinner,
} from 'react-bootstrap';

const dynamicMessages = {
  success: { icon: 'check-circle', text: '작업이 성공적으로 완료되었습니다!' },
  danger: { icon: 'x-circle', text: '오류가 발생했습니다!' },
  warning: { icon: 'exclamation-triangle', text: '경고: 주의가 필요합니다!' },
  info: { icon: 'info-circle', text: '정보: 참고하세요!' },
};

export default function ButtonsAlertsPage() {
  const [dynamicAlert, setDynamicAlert] = useState(null);
  const timerRef = useRef(null);

  const showAlert = (type) => {
    if (timerRef.current) clearTimeout(timerRef.current);
    setDynamicAlert({ type, id: Date.now() });
    timerRef.current = setTimeout(() => setDynamicAlert(null), 3000);
  };

  useEffect(() => () => clearTimeout(timerRef.current), []);

  return (
    <Container className="py-4 page-shell">
      <h1 className="text-center mb-5">
        <i className="bi bi-cursor-fill me-2" />
        Bootstrap 버튼과 알림 예제
      </h1>

      <div className="section">
        <h2 className="mb-4">1. 버튼 스타일</h2>
        <div className="row">
          <div className="col-md-6">
            <h4>기본 버튼</h4>
            <Button variant="primary" className="btn-demo">Primary</Button>
            <Button variant="secondary" className="btn-demo">Secondary</Button>
            <Button variant="success" className="btn-demo">Success</Button>
            <Button variant="danger" className="btn-demo">Danger</Button>
            <Button variant="warning" className="btn-demo">Warning</Button>
            <Button variant="info" className="btn-demo">Info</Button>
            <Button variant="light" className="btn-demo">Light</Button>
            <Button variant="dark" className="btn-demo">Dark</Button>
          </div>
          <div className="col-md-6">
            <h4>아웃라인 버튼</h4>
            <Button variant="outline-primary" className="btn-demo">Primary</Button>
            <Button variant="outline-secondary" className="btn-demo">Secondary</Button>
            <Button variant="outline-success" className="btn-demo">Success</Button>
            <Button variant="outline-danger" className="btn-demo">Danger</Button>
            <Button variant="outline-warning" className="btn-demo">Warning</Button>
            <Button variant="outline-info" className="btn-demo">Info</Button>
            <Button variant="outline-dark" className="btn-demo">Dark</Button>
          </div>
        </div>
      </div>

      <div className="section">
        <h2 className="mb-4">2. 버튼 크기</h2>
        <Button variant="primary" size="lg" className="me-2">Large</Button>
        <Button variant="primary" className="me-2">Default</Button>
        <Button variant="primary" size="sm">Small</Button>
      </div>

      <div className="section">
        <h2 className="mb-4">3. 아이콘 버튼</h2>
        <Button variant="primary" className="btn-demo">
          <i className="bi bi-heart me-2" />
          좋아요
        </Button>
        <Button variant="success" className="btn-demo">
          <i className="bi bi-check-circle me-2" />
          확인
        </Button>
        <Button variant="danger" className="btn-demo">
          <i className="bi bi-trash me-2" />
          삭제
        </Button>
        <Button variant="info" className="btn-demo">
          <i className="bi bi-download me-2" />
          다운로드
        </Button>
        <Button variant="warning" className="btn-demo">
          <i className="bi bi-star me-2" />
          즐겨찾기
        </Button>
      </div>

      <div className="section">
        <h2 className="mb-4">4. 버튼 그룹</h2>
        <ButtonGroup className="mb-3 me-3">
          <Button variant="primary">왼쪽</Button>
          <Button variant="primary">가운데</Button>
          <Button variant="primary">오른쪽</Button>
        </ButtonGroup>
        <ButtonGroup className="mb-3">
          <Button variant="outline-primary"><i className="bi bi-list" /></Button>
          <Button variant="outline-primary"><i className="bi bi-grid" /></Button>
          <Button variant="outline-primary"><i className="bi bi-three-dots" /></Button>
        </ButtonGroup>
      </div>

      <div className="section">
        <h2 className="mb-4">5. 알림 (Alert)</h2>
        <Alert variant="primary">
          <i className="bi bi-info-circle me-2" />
          <strong>정보:</strong> 이것은 기본 알림 메시지입니다.
        </Alert>
        <Alert variant="success">
          <i className="bi bi-check-circle me-2" />
          <strong>성공!</strong> 작업이 완료되었습니다.
        </Alert>
        <Alert variant="warning" dismissible>
          <i className="bi bi-exclamation-triangle me-2" />
          <strong>경고!</strong> 이것은 닫을 수 있는 알림입니다.
        </Alert>
        <Alert variant="danger">
          <i className="bi bi-x-circle me-2" />
          <strong>오류!</strong> 문제가 발생했습니다.
        </Alert>
        <Alert variant="info">
          <i className="bi bi-lightbulb me-2" />
          <strong>팁:</strong> 유용한 정보를 제공합니다.
        </Alert>
      </div>

      <div className="section">
        <h2 className="mb-4">6. 동적 알림 생성</h2>
        <div className="mb-3">
          <Button variant="success" className="me-2" onClick={() => showAlert('success')}>성공 알림</Button>
          <Button variant="danger" className="me-2" onClick={() => showAlert('danger')}>오류 알림</Button>
          <Button variant="warning" className="me-2" onClick={() => showAlert('warning')}>경고 알림</Button>
          <Button variant="info" onClick={() => showAlert('info')}>정보 알림</Button>
        </div>
        {dynamicAlert && (
          <Alert
            key={dynamicAlert.id}
            variant={dynamicAlert.type}
            dismissible
            onClose={() => setDynamicAlert(null)}
          >
            <i className={`bi bi-${dynamicMessages[dynamicAlert.type].icon} me-2`} />
            <strong>{dynamicMessages[dynamicAlert.type].text}</strong>
          </Alert>
        )}
      </div>

      <div className="section">
        <h2 className="mb-4">7. 배지 (Badge)</h2>
        <h3>
          알림{' '}
          <Badge bg="primary">5</Badge>
        </h3>
        <h3>
          메시지{' '}
          <Badge bg="success" pill>
            12
          </Badge>
        </h3>
        <Button variant="primary" className="me-2">
          알림 <Badge bg="light" text="dark">3</Badge>
        </Button>
        <Button variant="outline-secondary">
          프로필 <Badge bg="danger">New</Badge>
        </Button>
      </div>

      <div className="section">
        <h2 className="mb-4">8. 스피너 (로딩)</h2>
        <Spinner animation="border" variant="primary" className="me-2" />
        <Spinner animation="border" variant="success" className="me-2" />
        <Spinner animation="grow" variant="danger" className="me-2" />
        <div className="mt-3">
          <Button variant="primary" disabled>
            <Spinner animation="border" size="sm" className="me-2" />
            로딩 중...
          </Button>
        </div>
      </div>
    </Container>
  );
}
