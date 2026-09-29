# Bootstrap 핵심 정리

> **Bootstrap** = HTML에 **미리 만들어진 CSS 클래스**를 적용해서 빠르게 UI를 만드는 프론트엔드 프레임워크

**공식 문서:** [Bootstrap 5.3](https://getbootstrap.com/docs/5.3/getting-started/introduction/)  
**예제:** [examples/](./examples/) · **종합 실습:** [examples/06-쇼핑몰.html](./examples/06-쇼핑몰.html)

---

## 1. Bootstrap이란?

일반 CSS라면:

```css
.btn {
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    background: blue;
    color: white;
}
```

Bootstrap에서는:

```html
<button class="btn btn-primary">저장</button>
```

```text
직접 CSS 작성  →  시간 많이 걸림
Bootstrap      →  미리 만든 class 사용  →  빠른 UI 제작
```

---

## 2. Bootstrap 시작하기 (CDN)

가장 간단한 방법은 **CDN**입니다. (Bootstrap **5.3.8**)

```html
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bootstrap</title>
    <link
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
        rel="stylesheet">
</head>
<body>
    <h1>Hello Bootstrap</h1>

    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js">
    </script>
</body>
</html>
```

- JS 컴포넌트(Modal, Dropdown, Carousel 등)를 쓰려면 `bootstrap.bundle.min.js` 사용
- Bundle에는 **Popper**가 포함되어 있음

예제: [examples/01-시작하기.html](./examples/01-시작하기.html)

---

## 3. 수업에서 배울 순서

```text
① Container
      ↓
② Grid
      ↓
③ Typography
      ↓
④ Color
      ↓
⑤ Button
      ↓
⑥ Margin / Padding
      ↓
⑦ Flex
      ↓
⑧ Form
      ↓
⑨ Card
      ↓
⑩ Navbar
      ↓
⑪ Modal
      ↓
⑫ Carousel
```

---

## 4. Container

페이지의 **전체 영역**을 잡습니다.

```html
<div class="container">
    <h1>Bootstrap</h1>
    <p>내용입니다.</p>
</div>
```

| 클래스 | 의미 |
|--------|------|
| `container` | 반응형 **고정 폭** 컨테이너 |
| `container-fluid` | 화면 **전체 너비** |

공식 Grid 기본 구조:

```text
container  →  row  →  column
```

---

## 5. Grid 시스템 ⭐⭐⭐

Bootstrap Grid는 기본적으로 **12컬럼**입니다.

```html
<div class="container">
    <div class="row">
        <div class="col-4">왼쪽</div>
        <div class="col-4">가운데</div>
        <div class="col-4">오른쪽</div>
    </div>
</div>
```

```text
12칸
┌────┬────┬────┐
│ 4  │ 4  │ 4  │
└────┴────┴────┘
```

**6 + 6**

```html
<div class="row">
    <div class="col-6">왼쪽</div>
    <div class="col-6">오른쪽</div>
</div>
```

**3 + 9**

```html
<div class="row">
    <div class="col-3">메뉴</div>
    <div class="col-9">본문</div>
</div>
```

예제: [examples/02-grid.html](./examples/02-grid.html)

---

## 6. 반응형 Grid ⭐⭐⭐

```html
<div class="row">
    <div class="col-md-4">상품 1</div>
    <div class="col-md-4">상품 2</div>
    <div class="col-md-4">상품 3</div>
</div>
```

- **md 이상:** 한 줄에 3개
- **작은 화면:** 자연스럽게 줄바꿈

Bootstrap은 **mobile-first**이며, Grid에 **6개 breakpoint**가 있습니다.

---

## 7. Breakpoint

| 이름 | 최소 너비 |
|------|-----------|
| xs (기본) | — |
| sm | ≥ 576px |
| md | ≥ 768px |
| lg | ≥ 992px |
| xl | ≥ 1200px |
| xxl | ≥ 1400px |

```html
<div class="col-12 col-md-6 col-lg-4">상품</div>
```

| 화면 | 칸 |
|------|-----|
| 모바일 | 12 (1열) |
| 태블릿(md) | 6 (2열) |
| PC(lg) | 4 (3열) |

```text
모바일          태블릿            PC
┌────────┐    ┌────┬────┐    ┌───┬───┬───┐
│  상품  │    │상품│상품│    │상품│상품│상품│
└────────┘    └────┴────┘    └───┴───┴───┘
```

---

## 8. 색상

```html
<p class="text-primary">파란색</p>
<p class="text-danger">빨간색</p>
<p class="text-success">성공</p>
<p class="text-warning">경고</p>
<p class="text-info">정보</p>

<div class="bg-primary">내용</div>
<div class="bg-dark text-white">내용</div>
```

대표 색상: `primary` · `secondary` · `success` · `danger` · `warning` · `info` · `light` · `dark`

---

## 9. Button ⭐

```html
<button class="btn btn-primary">저장</button>
```

종류:

```html
<button class="btn btn-primary">Primary</button>
<button class="btn btn-secondary">Secondary</button>
<button class="btn btn-success">Success</button>
<button class="btn btn-danger">Danger</button>
<button class="btn btn-warning">Warning</button>
```

외곽선 / 크기:

```html
<button class="btn btn-outline-primary">확인</button>
<button class="btn btn-primary btn-lg">큰 버튼</button>
<button class="btn btn-primary btn-sm">작은 버튼</button>
```

> 기본 클래스(`.btn`) + modifier(`.btn-primary`)를 **조합**하는 방식이 많습니다.

---

## 10. Margin / Padding ⭐⭐⭐

### Margin

| 클래스 | 의미 |
|--------|------|
| `m-3` | 전체 margin |
| `mt-3` | margin-top |
| `mb-3` | margin-bottom |
| `ms-3` | margin-start (왼쪽) |
| `me-3` | margin-end (오른쪽) |

### Padding

| 클래스 | 의미 |
|--------|------|
| `p-3` | 전체 padding |
| `pt-3` | 위 |
| `pb-3` | 아래 |

### 기억법

```text
m = margin    p = padding
t = top       b = bottom
s = start     e = end
x = 좌우      y = 상하
```

```html
<div class="mx-auto">좌우 margin auto</div>
<div class="py-3">위아래 padding</div>
```

예제: [examples/03-유틸리티.html](./examples/03-유틸리티.html)

---

## 11. Flex

```html
<div class="d-flex">...</div>
<div class="d-flex justify-content-center">가운데</div>
<div class="d-flex justify-content-between">양쪽</div>
<div class="d-flex align-items-center">세로 가운데</div>
```

실전 예:

```html
<div class="d-flex justify-content-between align-items-center">
    <h2>상품 목록</h2>
    <button class="btn btn-primary">상품등록</button>
</div>
```

```text
상품 목록                         [상품등록]
```

---

## 12. Card ⭐⭐

상품 목록에서 많이 사용합니다.

```html
<div class="card" style="width: 18rem;">
    <img src="product.jpg" class="card-img-top" alt="">
    <div class="card-body">
        <h5 class="card-title">노트북</h5>
        <p class="card-text">최신 노트북입니다.</p>
        <button class="btn btn-primary">구매</button>
    </div>
</div>
```

```text
┌─────────────────┐
│      이미지     │
├─────────────────┤
│ 노트북           │
│ 최신 노트북...   │
│ [ 구매 ]         │
└─────────────────┘
```

---

## 13. Form ⭐⭐⭐

```html
<div class="mb-3">
    <label class="form-label">이름</label>
    <input type="text" class="form-control" placeholder="이름을 입력하세요">
</div>

<select class="form-select">
    <option>서울</option>
    <option>부산</option>
    <option>대구</option>
</select>

<button class="btn btn-primary">등록</button>
```

---

## 14. Alert

```html
<div class="alert alert-success">저장되었습니다.</div>
<div class="alert alert-primary">안내</div>
<div class="alert alert-danger">오류가 발생했습니다.</div>
<div class="alert alert-warning">주의하세요.</div>
```

예제: [examples/04-컴포넌트.html](./examples/04-컴포넌트.html)

---

## 15. Navbar

```html
<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container">
        <a class="navbar-brand" href="#">MySite</a>
        <div class="navbar-nav">
            <a class="nav-link" href="#">Home</a>
            <a class="nav-link" href="#">About</a>
            <a class="nav-link" href="#">Contact</a>
        </div>
    </div>
</nav>
```

Navbar는 **반응형**을 지원하는 대표 컴포넌트입니다.

---

## 16. Modal

팝업 창입니다.

```html
<button class="btn btn-primary"
        data-bs-toggle="modal"
        data-bs-target="#myModal">
    열기
</button>

<div class="modal fade" id="myModal" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">회원가입</h5>
                <button type="button" class="btn-close"
                        data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body">
                회원가입 내용
            </div>
        </div>
    </div>
</div>
```

JS 기반 컴포넌트: Modal, Dropdown, Carousel, Collapse, Navbar, Offcanvas, Toast 등

예제: [examples/05-navbar-modal-carousel.html](./examples/05-navbar-modal-carousel.html)

---

## 17. Carousel

이미지 슬라이드입니다. (예전 Slick / bxSlider와 비슷한 역할)

```html
<div id="carouselExample" class="carousel slide">
    <div class="carousel-inner">
        <div class="carousel-item active">
            <img src="img1.jpg" class="d-block w-100" alt="">
        </div>
        <div class="carousel-item">
            <img src="img2.jpg" class="d-block w-100" alt="">
        </div>
    </div>
</div>
```

---

## 18. Table

```html
<table class="table">
    <thead>
        <tr>
            <th>번호</th>
            <th>이름</th>
            <th>나이</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>1</td>
            <td>홍길동</td>
            <td>25</td>
        </tr>
    </tbody>
</table>
```

| 클래스 | 효과 |
|--------|------|
| `table-striped` | 줄무늬 |
| `table-bordered` | 테두리 |
| `table-hover` | 마우스 효과 |

---

## 19. 자주 쓰는 클래스 (먼저 외우기)

| 목적 | 클래스 |
|------|--------|
| 컨테이너 | `container` |
| 전체 너비 | `container-fluid` |
| 행 | `row` |
| 컬럼 | `col`, `col-6`, `col-md-4` |
| 버튼 | `btn btn-primary` |
| 입력 | `form-control` |
| Select | `form-select` |
| 카드 | `card` |
| 테이블 | `table` |
| Flex | `d-flex` |
| 가운데 정렬 | `justify-content-center` |
| 양쪽 정렬 | `justify-content-between` |
| 세로 가운데 | `align-items-center` |
| Margin | `m-*`, `mt-*`, `mb-*` |
| Padding | `p-*`, `pt-*`, `pb-*` |
| 글자 색 | `text-primary` |
| 배경 색 | `bg-primary` |
| 숨김 / 표시 | `d-none` / `d-block` |

---

## 20. Bootstrap의 핵심은 사실 3개

전체를 암기하기보다 **이 3축**부터 잡습니다.

```text
             Bootstrap
                 │
       ┌─────────┼─────────┐
       ↓         ↓         ↓
     Grid     Utility   Component
       │         │         │
 container     margin     Button
 row           padding    Card
 col           flex       Form
               color      Navbar
                          Modal
```

| 축 | 배울 것 |
|----|---------|
| **① Grid** | `container` → `row` → `col` |
| **② Utility** | `m-3`, `p-3`, `d-flex`, `text-center`, `text-primary`, `bg-dark` |
| **③ Component** | Button, Card, Form, Navbar, Modal, Alert, Carousel, Table |

---

## ⭐ 수업 추천 실습: 쇼핑몰 상품 페이지

클래스만 외우기보다 **한 페이지를 완성**하는 것이 효과적입니다.

```text
Navbar
  ↓
Container
  ↓
Grid
  ↓
Card × 6
  ↓
Button
  ↓
Modal
```

```text
┌──────────────────────────────────────────┐
│ MyShop       상품   장바구니   로그인    │
├──────────────────────────────────────────┤
│ 상품 목록                                │
│ ┌──────┐ ┌──────┐ ┌──────┐              │
│ │상품1 │ │상품2 │ │상품3 │              │
│ │[구매]│ │[구매]│ │[구매]│              │
│ └──────┘ └──────┘ └──────┘              │
│ ┌──────┐ ┌──────┐ ┌──────┐              │
│ │상품4 │ │상품5 │ │상품6 │              │
│ │[구매]│ │[구매]│ │[구매]│              │
│ └──────┘ └──────┘ └──────┘              │
└──────────────────────────────────────────┘
```

→ Grid + 반응형 + Utility + Card + Button + Navbar + Modal을 **한 번에** 익힘

완성 예제: [examples/06-쇼핑몰.html](./examples/06-쇼핑몰.html)

---

## 관련 파일

| 파일 | 내용 |
|------|------|
| [index.html](./index.html) | 예제 목록 허브 |
| [layout.html](./layout.html) | Header · Nav · Card 3 · Footer |
| [examples/01-시작하기.html](./examples/01-시작하기.html) | CDN · Hello |
| [examples/02-grid.html](./examples/02-grid.html) | Grid · Breakpoint |
| [examples/03-유틸리티.html](./examples/03-유틸리티.html) | Color · Spacing · Flex |
| [examples/04-컴포넌트.html](./examples/04-컴포넌트.html) | Button · Card · Form · Alert · Table |
| [examples/05-navbar-modal-carousel.html](./examples/05-navbar-modal-carousel.html) | Navbar · Modal · Carousel |
| [examples/06-쇼핑몰.html](./examples/06-쇼핑몰.html) | 종합 실습 |
