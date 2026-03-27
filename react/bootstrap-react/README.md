# Bootstrap React 예제

`react/bootstrap` 폴더의 정적 HTML 예제를 **[react-bootstrap](https://react-bootstrap.github.io/)** + **Vite** + **React Router**로 옮긴 프로젝트입니다.

## 실행

```bash
cd react/bootstrap-react
npm install
npm run dev
```

브라우저에서 표시된 주소(기본 `http://localhost:5173`)로 접속합니다.

## 구성

| 경로 | 내용 |
|------|------|
| `/` | 목차(카드 링크) |
| `/01-login` | 로그인 폼 (전체 화면 그라데이션) |
| `/02-nav` | 네비게이션 + 앵커 스크롤 (전체 화면) |
| `/03-card` | 카드 컴포넌트 (`/03-cards`는 `/03-card`로 리다이렉트) |
| `/04-buttons` | 버튼, Alert, 배지, 스피너 |
| `/05-modals` | 모달 (크기, 폼, 확인, 스크롤) |
| `/06-forms` | 폼 입력·검증 |
| `/07-grid` | 그리드 시스템 |

## 의존성

- `bootstrap` — CSS
- `react-bootstrap` — React용 Bootstrap 컴포넌트
- `react-router-dom` — 페이지 이동

## 원본

`react/bootstrap/` 의 `01-로그인폼` ~ `07-그리드시스템` HTML과 대응합니다.
