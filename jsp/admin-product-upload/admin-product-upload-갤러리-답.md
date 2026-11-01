# admin-product-upload 갤러리 — 정답

문제: [admin-product-upload-갤러리-문제.md](./admin-product-upload-갤러리-문제.md)  
구현: `WebContent/admin/productGallery.jsp`

---

## 객관식

| 번호 | 정답 | 해설 |
|------|------|------|
| Q1 | **②** | 12÷4=3 → `col-lg-3` |
| Q2 | **②** | 12÷3=4 → `col-md-4` |
| Q3 | **②** | 목록과 동일하게 `selectAll()` |
| Q4 | **②** | DB에는 파일명, 파일은 uploads/ |
| Q5 | **①** | `col-6`(2열) + `col-lg-3`(4열) |

---

## 단답

### Q6
```text
col-lg-3
col-md-4
col-6
```

### Q7
`uploads`  
→ `ctx + "/uploads/" + p.getImage()`

### Q8
**같다.** 둘 다 `ProductDAO.selectAll()` — 보여주는 HTML만 다름.

---

## 서술

### Q9 (예시)
- 공통: DB에서 같은 상품 목록을 가져옴  
- 차이: 표(행) vs 카드 그리드(열 배치)

### Q10
작은 화면에서 칸 지정이 애매하면 카드가 한 줄에 하나씩 너무 크게 보이거나,  
기본 동작에 맡겨져 레이아웃이 답답해질 수 있음.  
`col-6`으로 모바일 2열을 명시하는 편이 낫다.

---

## 실습 가이드

### 실습 1
핵심 골격:

```jsp
<div class="row g-3">
  <div class="col-6 col-md-4 col-lg-3">
    <div class="card h-100">
      <img class="card-img-top" src="<%= img %>" ...>
      <div class="card-body">...</div>
    </div>
  </div>
</div>
```

### 실습 2
`header.jsp`에  
`<a href="<%= ctx %>/admin/productGallery.jsp">갤러리</a>`

### 실습 3
`-webkit-line-clamp: 2` 등 (헤더 CSS의 `.gallery-desc` 참고)

### 실습 4
`list.isEmpty()`이면 alert + `productWrite.jsp` 링크

### 실습 5
자유 — `card-footer`에 `#<%= p.getId() %>` 등
