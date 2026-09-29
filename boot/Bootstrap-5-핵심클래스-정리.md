# Bootstrap 5 핵심 클래스 정리

> 수업에서 **자주 쓰는 핵심 클래스** 위주로 정리했습니다.  
> 모든 클래스를 외우기보다 **8개 영역**으로 나눠 익히면 됩니다.

**관련:** [Bootstrap-핵심정리.md](./Bootstrap-핵심정리.md) · [예제 목록](./index.html)  
**연습:** [문제](./Bootstrap-5-핵심클래스-문제.md) · [정답](./Bootstrap-5-핵심클래스-정답.md)

---

## Bootstrap은 8개 영역으로 기억하기

```text
Bootstrap 5
│
├─ 1. Layout       → container / row / col
├─ 2. Spacing      → m / p
├─ 3. Display      → d-
├─ 4. Flex         → flex- / justify- / align-
├─ 5. Text         → text- / fs- / fw-
├─ 6. Color        → bg- / text-
├─ 7. Component    → btn / card / alert / modal …
└─ 8. Responsive   → sm / md / lg / xl / xxl
```

---

## 1. Container

페이지의 **전체 콘텐츠 폭**을 잡습니다.

### `.container`

```html
<div class="container">
    내용
</div>
```

화면 크기에 따라 **최대 너비**가 달라집니다.

### `.container-fluid`

```html
<div class="container-fluid">
    내용
</div>
```

화면 **전체 너비**를 사용합니다.

```text
.container
┌──────────────────────────────┐
│          내용                │
└──────────────────────────────┘

.container-fluid
┌────────────────────────────────────────┐
│                 내용                   │
└────────────────────────────────────────┘
```

---

## 2. Grid — 가장 중요 ⭐⭐⭐

Bootstrap에서 **가장 중요한** 부분 중 하나입니다.

### 기본 구조

```html
<div class="container">
    <div class="row">
        <div class="col-4">1</div>
        <div class="col-4">2</div>
        <div class="col-4">3</div>
    </div>
</div>
```

기본적으로 **12칸**을 사용합니다.

| 클래스 | 의미 |
|--------|------|
| `col-12` | 전체 (100%) |
| `col-6` | 6/12 = 50% |
| `col-4` | 4/12 ≈ 33.3% |
| `col-3` | 3/12 = 25% |
| `col-8` | 8/12 ≈ 66.7% (2/3) |

### 예: 4등분

```html
<div class="row">
    <div class="col-3">1</div>
    <div class="col-3">2</div>
    <div class="col-3">3</div>
    <div class="col-3">4</div>
</div>
```

→ 4개가 **한 줄**에 배치됩니다.

### 자주 사용하는 Grid

```html
<div class="col-12">전체</div>
<div class="col-6">절반</div>
<div class="col-4">1/3</div>
<div class="col-3">1/4</div>
<div class="col-8">2/3</div>
```

---

## 3. 반응형 Grid ⭐⭐⭐

실무에서 **정말 많이** 사용합니다.

```html
<div class="col-12 col-md-6 col-lg-4">
    상품
</div>
```

| 화면 | 칸 |
|------|-----|
| 모바일 | 12칸 (1열) |
| 태블릿 (`md`) | 6칸 (2열) |
| PC (`lg`) | 4칸 (3열) |

```text
모바일              PC
┌──────────┐      ┌────┬────┬────┐
│ 상품     │      │상품│상품│상품│
└──────────┘      └────┴────┴────┘
┌──────────┐
│ 상품     │
└──────────┘
```

### Bootstrap Breakpoint

| 클래스 | 기준 |
|--------|------|
| `col-` | 기본 (가장 작은 화면부터) |
| `col-sm-` | ≥ 576px |
| `col-md-` | ≥ 768px |
| `col-lg-` | ≥ 992px |
| `col-xl-` | ≥ 1200px |
| `col-xxl-` | ≥ 1400px |

---

## 4. Margin / Padding ⭐⭐⭐

수업·실무에서 **엄청 많이** 사용합니다.

### Margin

```html
<div class="m-3">전체 margin</div>
```

| 클래스 | 의미 |
|--------|------|
| `mt` | margin-top |
| `mb` | margin-bottom |
| `ms` | margin-start (왼쪽) |
| `me` | margin-end (오른쪽) |
| `mx` | 좌우 |
| `my` | 상하 |

```html
<div class="mt-3">위쪽 여백</div>
<div class="mb-3">아래쪽 여백</div>
<div class="mx-auto">좌우 자동 (가운데)</div>
<div class="my-3">상하 여백</div>
```

### Padding

```html
<div class="p-3">전체 padding</div>
```

| 클래스 | 의미 |
|--------|------|
| `pt` | padding-top |
| `pb` | padding-bottom |
| `ps` | padding-start |
| `pe` | padding-end |
| `px` | 좌우 |
| `py` | 상하 |

```html
<div class="px-4 py-3">내용</div>
```

### 숫자 (0 ~ 5)

```text
p-1  p-2  p-3  p-4  p-5
```

수업에서는 먼저 이것부터:

> `m-3` · `mb-3` · `p-3` · `px-3` · `py-3`

### 기억법

```text
m = margin    p = padding
t = top       b = bottom
s = start     e = end
x = 좌우      y = 상하
```

---

## 5. Display

`d-`로 시작합니다.

| 클래스 | 의미 |
|--------|------|
| `d-none` | 숨김 |
| `d-block` | 블록 표시 |
| `d-flex` | Flex 사용 |
| `d-inline` | 인라인 |
| `d-inline-block` | 인라인 블록 |

가장 많이 쓰는 것:

```html
<div class="d-none">숨김</div>
<div class="d-block">블록</div>
<div class="d-flex">Flex</div>
```

반응형 예: `d-none d-md-block` → 모바일에서 숨기고, md 이상에서 표시

---

## 6. Flex ⭐⭐⭐

메뉴·버튼 정렬에서 **굉장히 많이** 사용합니다.

```html
<div class="d-flex">…</div>
```

| 클래스 | 의미 |
|--------|------|
| `justify-content-center` | 가로 가운데 |
| `justify-content-between` | 양쪽 배치 |
| `align-items-center` | 세로 가운데 |

```html
<div class="d-flex justify-content-between">
    <div>로고</div>
    <div>메뉴</div>
</div>
```

```text
로고                              메뉴
```

### 자주 쓰는 조합 ⭐

```html
<div class="d-flex justify-content-center align-items-center">
    <!-- 가로 + 세로 모두 가운데 -->
</div>
```

---

## 7. Text

### 정렬

```html
text-start    <!-- 왼쪽 -->
text-center   <!-- 가운데 -->
text-end      <!-- 오른쪽 -->
```

```html
<h2 class="text-center">제목</h2>
```

### 글자 크기 (`fs-`)

| 클래스 | 크기 |
|--------|------|
| `fs-1` | 가장 큼 |
| `fs-2` … `fs-5` | 중간 |
| `fs-6` | 가장 작음 |

→ **숫자가 작을수록 큼**

### 글자 굵기

```html
fw-bold     <!-- 굵게 -->
fw-normal   <!-- 보통 -->
fw-light    <!-- 얇게 -->
```

```html
<p class="fw-bold">중요한 내용</p>
```

---

## 8. Text Color

```html
text-primary
text-secondary
text-success
text-danger
text-warning
text-info
text-dark
text-light
text-white
```

```html
<p class="text-primary">파란색 계열</p>
<p class="text-danger">오류</p>
<p class="text-success">성공</p>
```

---

## 9. Background Color

`bg-`를 사용합니다.

```html
bg-primary
bg-secondary
bg-success
bg-danger
bg-warning
bg-info
bg-dark
bg-light
```

```html
<div class="bg-primary text-white p-3">
    공지사항
</div>
```

---

## 10. Button ⭐⭐⭐

```html
<button class="btn btn-primary">확인</button>
```

### 자주 쓰는 색

```html
btn btn-primary
btn btn-secondary
btn btn-success
btn btn-danger
btn btn-warning
btn btn-info
btn btn-dark
btn btn-light
```

### Outline

```html
<button class="btn btn-outline-primary">확인</button>
```

### 크기

```html
<button class="btn btn-primary btn-lg">큰 버튼</button>
<button class="btn btn-primary btn-sm">작은 버튼</button>
```

> 기본 클래스(`.btn`) + modifier(`.btn-primary`)를 **조합**합니다.

---

## 11. Card ⭐⭐⭐

쇼핑몰 · 게시판 · 포트폴리오에서 **정말 많이** 사용합니다.

```html
<div class="card">
    <div class="card-body">
        <h5 class="card-title">상품명</h5>
        <p class="card-text">상품 설명입니다.</p>
        <button class="btn btn-primary">구매하기</button>
    </div>
</div>
```

```text
card
 └── card-body
      ├── card-title
      ├── card-text
      └── btn
```

### 이미지 포함

```html
<div class="card">
    <img src="image.jpg" class="card-img-top" alt="">
    <div class="card-body">
        <h5 class="card-title">상품명</h5>
        <p class="card-text">상품 설명</p>
    </div>
</div>
```

---

## 12. Form ⭐⭐⭐

회원가입 · 로그인에서 많이 사용합니다.

```html
<label class="form-label">이름</label>
<input type="text" class="form-control" placeholder="이름을 입력하세요">
```

### Select

```html
<select class="form-select">
    <option>서울</option>
    <option>부산</option>
</select>
```

### Checkbox

```html
<input type="checkbox" class="form-check-input" id="agree">
<label class="form-check-label" for="agree">동의합니다.</label>
```

### 주요 클래스

| 클래스 | 용도 |
|--------|------|
| `form-label` | 라벨 |
| `form-control` | input |
| `form-select` | select |
| `form-check` | 체크 그룹 |
| `form-check-input` | checkbox / radio |
| `form-check-label` | 체크 라벨 |

---

## 13. Table

게시판 · 관리자 페이지에서 많이 사용합니다.

```html
<table class="table">…</table>
<table class="table table-striped">…</table>   <!-- 줄무늬 -->
<table class="table table-bordered">…</table>  <!-- 테두리 -->
<table class="table table-hover">…</table>     <!-- 마우스 -->
```

실무 조합 예:

```html
<table class="table table-striped table-hover">…</table>
```

---

## 14. Alert

```html
<div class="alert alert-success">저장되었습니다.</div>
<div class="alert alert-danger">오류가 발생했습니다.</div>
```

종류: `alert-primary` · `secondary` · `success` · `danger` · `warning` · `info`

---

## 15. Badge

작은 상태 표시입니다.

```html
<span class="badge bg-primary">NEW</span>
<span class="badge bg-danger">NEW</span>
```

예: `공지사항 <span class="badge bg-danger">NEW</span>`

---

## 16. Accordion ⭐⭐

| 클래스 | 역할 |
|--------|------|
| `accordion` | 전체 래퍼 |
| `accordion-item` | 한 칸 |
| `accordion-header` | 제목 영역 |
| `accordion-button` | 클릭 버튼 |
| `accordion-collapse` | 펼쳐지는 영역 |
| `accordion-body` | 내용 |

핵심 속성:

```html
data-bs-toggle="collapse"
data-bs-target="#menu1"
```

예제: [onepage-portfolio](./onepage-portfolio/) Skills 섹션

---

## 17. Tab ⭐⭐⭐

| 영역 | 클래스 |
|------|--------|
| 탭 버튼 | `nav nav-tabs` · `nav-item` · `nav-link` |
| 내용 | `tab-content` · `tab-pane` |

```html
<ul class="nav nav-tabs">
    <li class="nav-item">
        <button class="nav-link active" data-bs-toggle="tab" data-bs-target="#home">홈</button>
    </li>
    <li class="nav-item">
        <button class="nav-link" data-bs-toggle="tab" data-bs-target="#product">상품</button>
    </li>
</ul>
<div class="tab-content">
    <div class="tab-pane fade show active" id="home">…</div>
    <div class="tab-pane fade" id="product">…</div>
</div>
```

예제: [onepage-portfolio](./onepage-portfolio/) About 섹션

---

## 18. Modal ⭐⭐⭐

팝업창입니다.

```text
modal
 └── modal-dialog
      └── modal-content
           ├── modal-header
           ├── modal-body
           └── modal-footer
```

열기 버튼:

```html
<button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#myModal">
    열기
</button>
```

---

## 19. Navbar ⭐⭐⭐

사이트 상단 메뉴입니다.

```html
<nav class="navbar navbar-expand-lg">…</nav>
```

자주 쓰는 클래스:

| 클래스 | 역할 |
|--------|------|
| `navbar` | 네비 전체 |
| `navbar-brand` | 로고/브랜드 |
| `navbar-nav` | 링크 목록 |
| `nav-item` / `nav-link` | 메뉴 항목 |
| `navbar-toggler` | 햄버거 버튼 |
| `navbar-collapse` | 접히는 메뉴 |

---

## 20. 이미지

```html
<img src="image.jpg" class="img-fluid" alt="">           <!-- 반응형 -->
<img src="profile.jpg" class="img-fluid rounded-circle" alt="">  <!-- 원형 -->
```

| 클래스 | 의미 |
|--------|------|
| `img-fluid` | 부모 너비를 넘지 않음 |
| `rounded` | 둥근 모서리 |
| `rounded-circle` | 원형 |

---

## 21. Border

```html
<div class="border rounded p-3">박스</div>
```

| 클래스 | 의미 |
|--------|------|
| `border` | 테두리 |
| `rounded` / `rounded-3` | 둥근 모서리 |
| `rounded-pill` | 알약형 |
| `rounded-circle` | 원형 |

---

## 22. Shadow

```html
shadow-sm
shadow
shadow-lg
```

```html
<div class="card shadow">…</div>
```

---

## 23. Width / Height

```html
w-25   w-50   w-75   w-100
h-25   h-50   h-75   h-100
```

```html
<div class="w-50">전체의 50%</div>
```

---

## ⭐ 수업에서는 이것부터

모든 클래스를 외우게 하기보다 **아래 표**를 핵심으로 잡습니다.

| 분류 | 핵심 클래스 |
|------|-------------|
| 레이아웃 | `container` |
| Grid | `row`, `col`, `col-6`, `col-md-6`, `col-lg-4` |
| 여백 | `m-3`, `mb-3`, `p-3`, `px-3`, `py-3` |
| Flex | `d-flex`, `justify-content-center`, `justify-content-between`, `align-items-center` |
| Display | `d-none`, `d-block`, `d-flex` |
| Text | `text-center`, `text-start`, `text-end`, `fw-bold` |
| 색상 | `text-primary`, `text-danger`, `bg-primary` |
| 버튼 | `btn`, `btn-primary`, `btn-outline-primary`, `btn-lg`, `btn-sm` |
| Card | `card`, `card-body`, `card-title`, `card-text` |
| Form | `form-label`, `form-control`, `form-select` |
| Table | `table`, `table-striped`, `table-hover`, `table-bordered` |
| Alert | `alert`, `alert-success`, `alert-danger` |
| Badge | `badge`, `bg-primary` |
| Tab | `nav`, `nav-tabs`, `nav-item`, `nav-link`, `tab-content`, `tab-pane` |
| Accordion | `accordion`, `accordion-item`, `accordion-button`, `accordion-body` |
| Modal | `modal`, `modal-dialog`, `modal-content` |
| Navbar | `navbar`, `navbar-brand`, `navbar-nav`, `nav-link` |
| 이미지 | `img-fluid`, `rounded`, `rounded-circle` |
| 테두리 | `border`, `rounded` |
| 그림자 | `shadow`, `shadow-sm`, `shadow-lg` |

---

## 특히 이 10개는 꼭 익히기

1. `container`
2. `row` / `col`
3. `m-3` / `p-3`
4. `d-flex`
5. `justify-content-center`
6. `align-items-center`
7. `btn btn-primary`
8. `card` / `card-body`
9. `form-control`
10. `nav-tabs` / `tab-pane`

---

## 암기보다 조합으로 가르치기

Bootstrap은 **클래스 이름을 외우는 것**보다 **조합하는 방식**으로 가르치는 것이 좋습니다.

```html
<div class="container">
    <div class="row">
        <div class="col-12 col-md-6 col-lg-4">
            <div class="card shadow mb-3">
                <div class="card-body text-center">
                    <h3 class="fw-bold">상품명</h3>
                    <p class="text-secondary">상품 설명</p>
                    <button class="btn btn-primary">구매하기</button>
                </div>
            </div>
        </div>
    </div>
</div>
```

여기에 들어 있는 흐름:

```text
container → row → col → card → spacing → text → button
```

이 **한 덩어리**만 완성해도 Grid · 반응형 · Utility · Card · Button을 한꺼번에 익힐 수 있습니다.

---

## 관련 예제

| 파일 | 내용 |
|------|------|
| [examples/02-grid.html](./examples/02-grid.html) | Grid · Breakpoint |
| [examples/03-유틸리티.html](./examples/03-유틸리티.html) | Spacing · Flex · Color |
| [examples/04-컴포넌트.html](./examples/04-컴포넌트.html) | Button · Card · Form · Alert · Table |
| [examples/06-쇼핑몰.html](./examples/06-쇼핑몰.html) | 종합 실습 |
| [onepage-portfolio/](./onepage-portfolio/) | Nav · Carousel · Tab · Accordion |

공식 문서: [Bootstrap 5.3](https://getbootstrap.com/docs/5.3/getting-started/introduction/)
