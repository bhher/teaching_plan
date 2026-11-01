# Company index1 — 문제

**실습 파일:** [index1.html](./index1.html) · [css/style1.css](./css/style1.css)  
**정답:** [Company-index1-정답.md](./Company-index1-정답.md)

> Card Hover · Modal · FAQ Accordion · Offcanvas 를 이해했는지 확인합니다.  
> 브라우저에서 `index1.html`을 연 뒤 풀어 보세요.

---

## Part A. 객관식 · 단답

### 1번
카드에 마우스를 올렸을 때 부드럽게 움직이게 하려면 기본 상태에 어떤 속성을 쓰는가?

① `animation`  
② **`transition`**  
③ `display`  
④ `float`

### 2번
`.portfolio-card:hover { transform: translateY(-10px); }` 의 의미로 적절한 것은?

① 카드가 사라진다  
② **마우스를 올리면 카드가 위로 10px 이동한다**  
③ 글자 색만 바뀐다  
④ 모달이 열린다

### 3번
Modal을 여는 버튼에 필요한 **data 속성** 조합은?

① `data-bs-ride` + `data-bs-slide`  
② **`data-bs-toggle="modal"` + `data-bs-target="#아이디"`**  
③ `data-bs-parent` + `data-bs-spy`  
④ `href="#"` 만

### 4번
Modal을 **닫을** 때 버튼에 쓰는 속성은?

① `data-bs-toggle="modal"`  
② **`data-bs-dismiss="modal"`**  
③ `data-bs-target="#faq1"`  
④ `type="submit"`

### 5번
FAQ 아코디언에서 버튼과 내용 패널을 연결하는 핵심은?

① `class="card"`  
② **`data-bs-toggle="collapse"` + `data-bs-target="#faq1"`**  
③ `onclick`만  
④ `form-control`

### 6번
한 FAQ만 열리게 하려면 collapse에 무엇을 넣는가?

① `data-bs-ride="carousel"`  
② **`data-bs-parent="#faqAccordion"`**  
③ `data-bs-spy="scroll"`  
④ `navbar-toggler`

### 7번
모바일에서 ☰ 버튼으로 **오른쪽에서 메뉴**가 나오게 하는 컴포넌트는?

① Carousel  
② Tab  
③ **Offcanvas**  
④ Badge

### 8번
Offcanvas를 열 때 버튼에 쓰는 속성은?

① `data-bs-toggle="tab"`  
② **`data-bs-toggle="offcanvas"` + `data-bs-target="#mobileMenu"`**  
③ `data-bs-dismiss="modal"`  
④ `class="accordion"`

### 9번 (단답)
`:hover` 는 언제 적용되는 가상 클래스인가? (한 줄)

### 10번 (단답)
`offcanvas-end` 클래스의 의미는?

---

## Part B. 코드 작성 · 실습

### 11번
아래 CSS의 빈칸을 채우시오.

```css
.portfolio-card {
    ______: 0.3s;
}
.portfolio-card:hover {
    ______: translateY(-10px);
    box-shadow: 0 10px 25px rgba(0,0,0,0.15) !important;
}
```

### 12번
Paris 모달을 여는 버튼 HTML을 완성하시오.  
(모달 id = `parisModal`)

```html
<button class="btn btn-primary"
        __________="modal"
        __________="#parisModal">
    자세히 보기
</button>
```

### 13번
Modal 기본 구조의 빈칸을 채우시오.

```text
modal
 └── __________
      └── modal-content
           ├── modal-header
           ├── __________
           └── modal-footer
```

### 14번
FAQ 첫 번째 질문에 맞는 `data-bs-target`과 패널 `id`를 **같은 값**으로 쓰시오.  
(예: `#faq1` / `faq1`)

버튼: `data-bs-target="____"`  
패널: `id="____"`

### 15번
모바일 Offcanvas 메뉴 버튼 HTML을 완성하시오.  
(대상 id = `mobileMenu`)

```html
<button class="navbar-toggler"
        data-bs-toggle="________"
        data-bs-target="#________">
    <span class="navbar-toggler-icon"></span>
</button>
```

### 16번 (실습)
`index1.html`에서:

1. Portfolio **Paris** 카드에 마우스를 올려 위로 떠오르는지 확인  
2. **자세히 보기** → Modal이 열리는지 확인  
3. FAQ 질문을 눌러 하나만 열리는지 확인  
4. 창 너비를 줄인 뒤 ☰ → 오른쪽 Offcanvas 메뉴 확인  

각각 성공/실패를 O/X로 표시하시오.

### 17번 (심화 · 작성)
Portfolio에 **Chicago** 카드를 하나 더 추가한다고 가정하고:

1. `image/chicago.jpg`를 쓰는 카드 HTML (Hover 클래스 포함)  
2. `chicagoModal` Modal HTML (제목·닫기 버튼 포함)  
3. 자세히 보기 버튼의 `data-bs-target`

을 작성하시오.

### 18번 (심화 · 단답)
Bootstrap이 JS 파일 없이도 CSS만으로 되는 것과,  
`bootstrap.bundle.min.js`가 **꼭 필요한** 컴포넌트 예를 각각 하나씩 쓰시오.

---

## 채점

| Part | 문항 | 배점 |
|------|------|------|
| A | 1~10 | 40 |
| B | 11~16 | 45 |
| 심화 | 17~18 | 15 |
| **합** | | **100** |

통과: 70점 + **16번 실습 4항목 모두 O**
