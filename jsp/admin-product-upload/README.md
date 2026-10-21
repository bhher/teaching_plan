# 관리자 · 상품 올리기 연습 (이미지 업로드)

**목적:** 수정·삭제 없이 **로그인 + 상품 등록(사진) + 목록 확인**만 연습

경로: `jsp/admin-product-upload`

**상세 설명:** [admin-product-upload-상세정리.md](./admin-product-upload-상세정리.md)  
**갤러리 문제:** [admin-product-upload-갤러리-문제.md](./admin-product-upload-갤러리-문제.md) · [답](./admin-product-upload-갤러리-답.md)  
**이미지 업로드 문제:** [admin-product-upload-업로드-문제.md](./admin-product-upload-업로드-문제.md) · [답](./admin-product-upload-업로드-답.md)  
> 수정·삭제까지 있는 버전: `jsp/admin-product-upload1`

갤러리 화면: `admin/productGallery.jsp` (한 줄 3~4개 카드)

---

## 연습 포인트

1. 관리자 세션 로그인  
2. `enctype="multipart/form-data"`  
3. COS `MultipartRequest`로 파일 저장 → `uploads/`  
4. DB에는 **파일명**만 저장, 목록에서 `/uploads/파일명`으로 표시  

```text
productWrite.jsp
   ↓ (multipart)
productWriteProcess.jsp  ← MultipartRequest
   ↓ 파일 → uploads/
   ↓ 파일명 → product.image
productList.jsp  ← 썸네일 확인
```

---

## 필요 JAR (`WEB-INF/lib`)

- `mysql-connector-j`
- **`cos.jar`** (MultipartRequest)

---

## 실행 순서

1. MySQL에서 `db_setup.sql` 실행 (`shop_upload`)  
2. Eclipse Dynamic Web Project + Tomcat 10.1  
3. `src` / `WebContent` 배치, JAR 추가  
4. `DBConnection` 비밀번호 확인  
5. `http://localhost:8080/admin-product-upload/`  
6. 로그인 `admin` / `1234` → **상품 올리기** → 목록에서 사진 확인  

---

## 파일

| 파일 | 역할 |
|------|------|
| `login.jsp` / `loginProcess.jsp` | 관리자 로그인 |
| `productWrite.jsp` | 상품명·가격·사진·설명 입력 |
| `productWriteProcess.jsp` | MultipartRequest + INSERT |
| `productList.jsp` | 올린 상품·썸네일 목록 |
| `ProductDAO.insert` / `selectAll` | 등록·목록만 |

수정·삭제는 `admin-product-upload1`을 보세요.
