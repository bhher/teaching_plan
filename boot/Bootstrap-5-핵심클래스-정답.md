# Bootstrap 5 핵심 클래스 — 정답

문제: [Bootstrap-5-핵심클래스-문제.md](./Bootstrap-5-핵심클래스-문제.md)  
정리: [Bootstrap-5-핵심클래스-정리.md](./Bootstrap-5-핵심클래스-정리.md)

---

## Part A

| 번호 | 정답 | 해설 |
|------|------|------|
| 1 | ② | `container` = 반응형 고정 폭 / `container-fluid` = 전체 |
| 2 | ② | Grid는 12칸 |
| 3 | ② | 4/12 ≈ 33.3% (`col-3`이 25%, `col-6`이 50%) |
| 4 | ② | mobile-first: 기본 12 → md 6 → lg 4 |
| 5 | ② | md ≥ 768px |
| 6 | ② | m=margin, b=bottom |
| 7 | ② | `mx-auto` |
| 8 | ③ | `d-none` |
| 9 | ② | `justify-content-between` |
| 10 | ② | flex + justify + align |
| 11 | ② | fs 숫자는 작을수록 큼 |
| 12 | ② | `btn` + `btn-primary` |
| 13 | ② | card → card-body → title/text |
| 14 | ② | input = `form-control` |
| 15 | ③ | `tab-content` / 패널은 `tab-pane` |

### 16번
```text
m = margin
p = padding
t = top
b = bottom
s = start (왼쪽)
e = end (오른쪽)
x = 좌우
y = 상하
```

### 17번
`img-fluid`

### 18번
```html
table table-striped table-hover
```
(`table-bordered`를 더해도 됨)

### 19번
```html
data-bs-toggle="modal"
data-bs-target="#모달아이디"
```

### 20번 (예시)
- Grid: `row` / `col` (또는 `col-md-6`)
- 버튼: `btn btn-primary`
- 폼: `form-control`

(정리 문서 「특히 이 10개」 기준이면 OK)

---

## Part B

| 번호 | 정답 | 해설 |
|------|------|------|
| 21 | ③ | `mt`는 **margin**-top (padding이 아님). padding-top은 `pt` |
| 22 | ② | `navbar-toggler`가 맞음. Accordion은 collapse, `w-50`은 너비 |
| 23 | ② | 오류 → `alert-danger` |
| 24 | ② | 탭은 `data-bs-toggle="tab"` + `data-bs-target="#..."` 필요 |

---

## Part C

### 25번
```html
<div class="container">
  <h2 class="text-center">Bootstrap 연습</h2>
</div>
```

### 26번
```html
<div class="row">
  <div class="col-6">왼쪽</div>
  <div class="col-6">오른쪽</div>
</div>
```

### 27번
```html
<div class="card">
  <div class="card-body">
    <h5 class="card-title">노트북</h5>
    <p class="card-text">최신 노트북입니다.</p>
    <button class="btn btn-primary">구매</button>
  </div>
</div>
```

### 28번
```html
<label class="form-label">이름</label>
<input type="text" class="form-control" placeholder="이름을 입력하세요">
```

### 29번
```html
<div class="alert alert-success">
  저장되었습니다.
</div>
```

### 30번
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

### 31번
```html
<div class="d-flex justify-content-between align-items-center">
  <div>상품 목록</div>
  <button class="btn btn-primary btn-sm">등록</button>
</div>
```

### 32번 (예시)
```html
<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container">
    <a class="navbar-brand" href="#">MySite</a>
    <ul class="navbar-nav">
      <li class="nav-item"><a class="nav-link" href="#">Home</a></li>
      <li class="nav-item"><a class="nav-link" href="#">About</a></li>
    </ul>
  </div>
</nav>
```

(토글 버튼·`navbar-collapse`까지 있으면 더 좋음)

---

## Part D

### 33번 (예시 답)
모바일에서는 세로 1열(12칸), 태블릿은 2열(6칸), PC는 3열(4칸)로 배치된다.

### 34번 (예시)
- Tab: `nav-tabs`, `nav-link`, `tab-pane`, `data-bs-toggle="tab"`
- Accordion: `accordion`, `accordion-button`, `data-bs-toggle="collapse"`, `data-bs-parent`

(3개만 적어도 됨)

### 35번 (예시)
Bootstrap은 클래스 이름을 전부 외우는 것보다, `container`로 폭을 잡고 `row`/`col`로 칸을 나눈 뒤 `card`와 `btn`을 넣는 **조합**이 중요하다. 여백(`mb-3`)·정렬(`text-center`)·강조(`fw-bold`)도 같은 방식으로 붙인다. 한 덩어리 상품 카드만 만들어도 Grid·Utility·Component를 한꺼번에 익힐 수 있다.

---

## 채점 참고

- Part C는 클래스 이름·구조가 맞으면 문구/들여쓰기 달라도 만점  
- 30번은 정리 문서의 **조합 예제**와 동일한 흐름이면 정답
