# 관리자 · 상품 올리기 (이미지 업로드 + 수정·삭제)

경로: `jsp/admin-product-upload1`  
(등록만 있는 버전: `jsp/admin-product-upload`)

**상세 정리:** [admin-product-upload1-상세정리.md](./admin-product-upload1-상세정리.md)  
**문제:** [admin-product-upload1-문제.md](./admin-product-upload1-문제.md) · **정답:** [admin-product-upload1-답.md](./admin-product-upload1-답.md)

---

## 기능

| 기능 | 파일 |
|------|------|
| 로그인 | `login.jsp` → `loginProcess.jsp` |
| 상품 등록 (+사진) | `productWrite.jsp` → `productWriteProcess.jsp` |
| 목록 | `productList.jsp` |
| 상품 수정 (+사진 선택 변경) | `productUpdate.jsp` → `productUpdateProcess.jsp` |
| 상품 삭제 | `productDeleteProcess.jsp` (id 기준) |

---

## 수정·삭제 흐름

```text
목록 → [수정] → productUpdate.jsp?id=
              → MultipartRequest (새 사진 없으면 기존 image 유지)
              → ProductDAO.update() → 목록

목록 → [삭제] confirm → productDeleteProcess.jsp
                     → ProductDAO.delete(id) → 목록
```

---

## 필요 JAR (`WEB-INF/lib`)

- `mysql-connector-j`
- **`cos.jar`** (등록·수정 업로드)

---

## 실행

1. `db_setup.sql` (`shop_upload`)  
2. Eclipse + Tomcat, JAR 추가  
3. `admin` / `1234`  
4. 올리기 → 목록에서 수정·삭제  
5. 예: `http://localhost:8080/admin-product-upload1/`

계정: `admin` / `1234` · DB `shop_upload` 공유 가능
