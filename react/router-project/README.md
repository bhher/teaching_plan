# Router Project

React Router로 만든 간단 포트폴리오형 사이트입니다.

## 실행

```bash
cd react/router-project
npm install
npm run dev
```

## 라우트

| 경로 | 페이지 | 내용 |
|------|--------|------|
| `/` | Home | 슬라이드 + 바로가기 카드 |
| `/about` | About | 소개 |
| `/portfolio` | Portfolio | 프로젝트 카드 그리드 |
| `/contact` | Contact | 문의 폼 |
| `*` | NotFound | 404 |

## 구조

```
src/
  components/
    Layout.jsx   # NavLink + Outlet
    Slider.jsx   # 자동/수동 슬라이드
    Card.jsx     # 재사용 카드
  pages/
    Home.jsx
    About.jsx
    Portfolio.jsx
    Contact.jsx
    NotFound.jsx
  App.jsx        # 중첩 Routes
```

## 학습 포인트

- `Layout` + `<Outlet />` 중첩 라우팅
- `NavLink` 활성 클래스
- Home 슬라이드(`useState` + `useEffect`)
- Portfolio `map` + `Card`
- Contact controlled form
