# admin-product-upload 문제 — 갤러리

이론: [admin-product-upload-상세정리.md](./admin-product-upload-상세정리.md)  
정답: [admin-product-upload-갤러리-답.md](./admin-product-upload-갤러리-답.md)  
참고 구현: `WebContent/admin/productGallery.jsp`

**목표:** 표(`productList.jsp`) 대신 **카드 갤러리**로 상품을 한 줄에 3~4개 배치하기

---

## 객관식

### Q1. Bootstrap에서 한 줄에 카드를 **4개** 두려면 `col-*`로 알맞은 것은?

① `col-lg-4`  
② `col-lg-3`  
③ `col-lg-6`  
④ `col-lg-12`  

### Q2. 한 줄에 **3개**일 때 자주 쓰는 클래스는?

① `col-md-3`  
② `col-md-4`  
③ `col-md-6`  
④ `col-md-12`  

### Q3. 갤러리에서 상품 목록을 가져오는 DAO 호출로 바른 것은?

① `new AdminDAO().login(...)`  
② `new ProductDAO().selectAll()`  
③ `new ProductDAO().insert(p)`  
④ `DBConnection.getConnection()` 만  

### Q4. 이미지 URL을 만들 때 DB의 `image` 컬럼은 보통 무엇을 저장하나?

① 파일 전체 바이너리  
② **파일명** (예: `keyboard.jpg`)  
③ HTML 전체  
④ 세션 id  

### Q5. 반응형으로 모바일 2열·PC 4열이 되게 하려면?

① `col-6 col-lg-3`  
② `col-12` 만  
③ `table` 만 사용  
④ `col-lg-12`  

---

## 단답 · 빈칸

### Q6
다음 빈칸을 채우세요. (12칸 그리드)

```text
한 줄 4개 → col-lg-____   (12 ÷ 4)
한 줄 3개 → col-md-____   (12 ÷ 3)
한 줄 2개 → col-____      (12 ÷ 2)
```

### Q7
갤러리 JSP에서 이미지를 보이게 하는 경로 형태를 쓰세요.

```text
ctx + "/________/" + p.getImage()
```

### Q8
`productList.jsp`(표)와 `productGallery.jsp`(갤러리)의 **DAO 호출**은 같은가, 다른가?  
한 줄로 답하세요.

---

## 실습 문제

### 실습 1 ⭐
`admin/productGallery.jsp`를 새로 만들고,

1. `adminCheck` + `header` include  
2. `ProductDAO.selectAll()`로 목록 조회  
3. `row` + `col-6 col-md-4 col-lg-3` 카드로 출력  
4. 이미지·상품명·가격 표시  

까지 완성하세요. (이미 파일이 있으면 코드를 읽고 구조를 설명해도 됩니다.)

### 실습 2 ⭐⭐
`header.jsp` 메뉴에 **갤러리** 링크를 추가하세요.

```text
/admin/productGallery.jsp
```

### 실습 3 ⭐⭐
카드에 설명이 길면 2줄까지만 보이게 CSS를 적용하세요.  
(힌트: `-webkit-line-clamp` 또는 `text-truncate`)

### 실습 4 ⭐⭐⭐
상품이 **0개**일 때 “등록된 상품이 없습니다” + 올리기 링크를 보여 주세요.

### 실습 5 ⭐⭐⭐ (응용)
갤러리에서 카드 클릭 시 `productList.jsp`로 가게 하거나,  
카드에 `#번호`를 작게 표시해 보세요.

---

## 서술

### Q9
표 목록과 갤러리 목록의 **공통점**과 **차이점**을 각각 한 가지씩 쓰세요.

### Q10
`col-lg-3`만 쓰고 `col-6`을 안 쓰면 휴대폰에서 어떻게 보일 수 있는지 설명해 보세요.
