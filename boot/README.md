# Bootstrap 핵심 (boot)

Bootstrap **5.3.8** 수업용 정리 + 예제 모음입니다.

## 시작

1. 정리 문서:
   - [Bootstrap-핵심정리.md](./Bootstrap-핵심정리.md) (이론 · CDN · 수업 순서)
   - [Bootstrap-5-핵심클래스-정리.md](./Bootstrap-5-핵심클래스-정리.md) (자주 쓰는 클래스 ⭐)
   - [Bootstrap-5-핵심클래스-문제.md](./Bootstrap-5-핵심클래스-문제.md) · [정답](./Bootstrap-5-핵심클래스-정답.md)
2. 예제 목록: 브라우저에서 `index.html` 열기

설치 없이 CDN만 사용합니다.

## 폴더 구조

```
boot/
├── Bootstrap-핵심정리.md      # 이론 정리 (1~20)
├── index.html                 # 예제 목록 허브
├── layout.html                # Header · Nav · Card 3 · Footer
├── portfolio/                 # 포트폴리오 사이트 ⭐
│   ├── index.html
│   ├── css/style.css
│   └── js/main.js
├── css/style.css
├── README.md
└── examples/
    ├── 01-시작하기.html
    ├── 02-grid.html
    ├── 03-유틸리티.html
    ├── 04-컴포넌트.html
    ├── 05-navbar-modal-carousel.html
    └── 06-쇼핑몰.html         # 종합 실습 ⭐
```

## 핵심 3축

| 축 | 내용 |
|----|------|
| **Grid** | `container` → `row` → `col` |
| **Utility** | `m-*`, `p-*`, `d-flex`, `text-*`, `bg-*` |
| **Component** | Button, Card, Form, Navbar, Modal, Alert, Table |

## 추천 수업 순서

01 CDN → 02 Grid → 03 Utility → 04 Component → 05 Navbar/Modal → **06 쇼핑몰**

## 공식 문서

https://getbootstrap.com/docs/5.3/getting-started/introduction/
