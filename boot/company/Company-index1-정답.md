# Company index1 — 정답

문제: [Company-index1-문제.md](./Company-index1-문제.md)  
실습: [index1.html](./index1.html) · [css/style1.css](./css/style1.css)

---

## Part A

| 번호 | 정답 | 해설 |
|------|------|------|
| 1 | ② | `transition`으로 변화 시간을 줌 |
| 2 | ② | `:hover` + `translateY(-10px)` = 위로 이동 |
| 3 | ② | toggle=modal, target=#id |
| 4 | ② | `data-bs-dismiss="modal"` |
| 5 | ② | collapse + target로 패널 연결 |
| 6 | ② | `data-bs-parent`로 하나만 열림 |
| 7 | ③ | Offcanvas |
| 8 | ② | toggle=offcanvas, target=#mobileMenu |
| 9 | 마우스(포인터)를 요소 위에 올렸을 때 | |
| 10 | Offcanvas가 **오른쪽**에서 나타남 (`end`) | |

---

## Part B

### 11번
```css
.portfolio-card {
    transition: 0.3s;
}
.portfolio-card:hover {
    transform: translateY(-10px);
    box-shadow: 0 10px 25px rgba(0,0,0,0.15) !important;
}
```

### 12번
```html
<button class="btn btn-primary"
        data-bs-toggle="modal"
        data-bs-target="#parisModal">
    자세히 보기
</button>
```

### 13번
```text
modal
 └── modal-dialog
      └── modal-content
           ├── modal-header
           ├── modal-body
           └── modal-footer
```

### 14번
- 버튼: `data-bs-target="#faq1"`
- 패널: `id="faq1"`

(`#`은 target에만, id에는 없음)

### 15번
```html
<button class="navbar-toggler"
        data-bs-toggle="offcanvas"
        data-bs-target="#mobileMenu">
    <span class="navbar-toggler-icon"></span>
</button>
```

### 16번
| 항목 | 정답 |
|------|------|
| Paris Hover | O |
| Modal 열림 | O |
| FAQ 하나만 열림 | O |
| Offcanvas ☰ | O |

### 17번 (예시)
```html
<!-- 카드 -->
<div class="card shadow-sm portfolio-card">
  <img class="card-img-top" src="image/chicago.jpg" alt="chicago">
  <div class="card-body">
    <h5 class="card-title">Chicago</h5>
    <p class="card-text">Yes, we built Chicago</p>
    <button class="btn btn-primary btn-sm"
            data-bs-toggle="modal"
            data-bs-target="#chicagoModal">
      자세히 보기
    </button>
  </div>
</div>

<!-- Modal -->
<div class="modal fade" id="chicagoModal" tabindex="-1">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Chicago Project</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>
      <div class="modal-body">
        Chicago 프로젝트 상세 내용입니다.
      </div>
      <div class="modal-footer">
        <button class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
      </div>
    </div>
  </div>
</div>
```

### 18번 (예시)
| 구분 | 예 |
|------|-----|
| CSS만 (대략) | Grid, Spacing, Button 색, Card 레이아웃, Hover(`:hover`) |
| JS 필요 | **Modal**, Accordion(collapse), Offcanvas, Carousel, Dropdown |

---

## 수업용 한 줄 정리

```text
Hover     → transition + transform + :hover   (CSS)
Modal     → data-bs-toggle="modal" + target
FAQ       → data-bs-toggle="collapse" + target + parent
Offcanvas → data-bs-toggle="offcanvas" + target
```
