# admin-product-upload1 문제 — 상품 수정·삭제 CRUD

이론: [admin-product-upload1-상세정리.md](./admin-product-upload1-상세정리.md)  
정답: [admin-product-upload1-답.md](./admin-product-upload1-답.md)  
소스: `jsp/admin-product-upload1`  
선행: `admin-product-upload` (등록·목록·업로드)

**목표:** 목록에서 **수정·삭제**하고, 수정 시 **새 사진이 없으면 기존 이미지 유지**하기

---

## 객관식

### Q1. `admin-product-upload`와 `upload1`의 가장 큰 차이는?

① 로그인이 없다  
② **상품 수정·삭제**가 추가되었다  
③ DB를 사용하지 않는다  
④ cos.jar가 필요 없다  

### Q2. 수정 폼을 열 때 기존 상품을 가져오는 DAO 메서드는?

① `insert`  
② `selectAll`  
③ **`selectById(id)`**  
④ `delete`  

### Q3. `productUpdate.jsp`에서 Process로 **반드시** 넘겨야 하는 값은?

① 세션의 `adminName`만  
② **hidden `id`** (어느 행을 고칠지)  
③ Tomcat 포트  
④ `ctx`  

### Q4. 수정 폼도 사진을 바꿀 수 있으므로 form에 필요한 것은?

① `method="get"`만  
② **`enctype="multipart/form-data"`**  
③ `action` 없음  
④ `type="button"`만  

### Q5. 수정 시 새 사진을 **안 골랐을 때** 올바른 처리는?

① image를 무조건 null로 저장  
② **기존 `old.getImage()` 유지**  
③ 등록 페이지로 강제 이동  
④ DB에서 행을 삭제  

### Q6. `ProductDAO.update` SQL의 WHERE 조건은?

① `WHERE name = ?`  
② **`WHERE id = ?`**  
③ WHERE 없음  
④ `WHERE password = ?`  

### Q7. 목록의 삭제 버튼이 Process로 보내는 방식(이 프로젝트)은?

① GET만, confirm 없음  
② **POST + hidden id + confirm**  
③ 세션에 id만 넣고 끝  
④ SQL을 브라우저에서 실행  

### Q8. `productDeleteProcess.jsp`에서 id를 읽는 방법은? (파일 업로드 없음)

① `multi.getParameter("id")`만 가능  
② **`request.getParameter("id")`**  
③ `rs.getInt("id")`만  
④ `application.getAttribute("id")`  

### Q9. Write(등록)와 Update(수정)의 이미지 검증 차이로 맞는 것은?

① 둘 다 이미지 필수  
② 둘 다 이미지 불필요  
③ **Write는 필수, Update는 선택**  
④ Update만 필수  

### Q10. 수정·삭제 Process에 `adminCheck.jspf`를 넣는 이유는?

① Bootstrap 때문에  
② **로그인 없이 URL 직접 호출을 막기 위해**  
③ MySQL 문법  
④ 이미지 리사이즈  

---

## 단답 · 빈칸

### Q11
수정 흐름 빈칸을 채우세요.

```text
productList → [수정]
  → productUpdate.jsp?id=___
  → productUpdate________.jsp (MultipartRequest)
  → ProductDAO.________(p)
  → productList.jsp
```

### Q12
이미지 유지 코드를 완성하세요.

```jsp
String image = old.get________();
if (fileName != null && !fileName.isEmpty()) {
    image = fileName;
}
```

### Q13
삭제 SQL을 완성하세요.

```sql
DELETE FROM product WHERE ________ = ?
```

### Q14
목록에서 이미지를 보이게 하는 경로를 완성하세요.

```jsp
ctx + "/__________/" + p.getImage()
```

### Q15
`ProductDAO`에서 수정·삭제를 위해 **upload에 없던** 메서드 3개는?

---

## 코드 고치기

### Q16
다음 수정 Process의 **문제 2가지**를 고치세요.

```jsp
MultipartRequest multi = new MultipartRequest(...);
String fileName = multi.getFilesystemName("imageFile");
// 항상 새 파일명만 넣음
p.setImage(fileName);
dao.update(p);
```

(새 파일을 안 고르면 `fileName`이 null일 수 있음)

### Q17
다음 수정 폼의 틀린 점을 고치세요.

```html
<form method="post" action="productUpdateProcess.jsp">
  <!-- id 없음 -->
  <input type="text" name="name" value="신발">
  <input type="file" name="imageFile">
  <button type="submit">저장</button>
</form>
```

### Q18
삭제 링크가 이렇게만 되어 있다. 이 프로젝트 방식과 비교해 **부족한 점**을 쓰세요.

```html
<a href="productDeleteProcess.jsp?id=3">삭제</a>
```

---

## 서술

### Q19
왜 수정 화면에서 상품 사진을 `required`로 두지 않는지,  
「기존 이미지 유지」와 연결해 2~3문장으로 설명하세요.

### Q20
`selectById`가 수정에서 두 번 쓰일 수 있는 이유  
(폼 화면 / Process)를 각각 한 줄로 쓰세요.

---

## 실습

### 실습 1 ⭐
1. `admin` / `1234` 로그인  
2. 상품 등록(사진 포함)  
3. 목록에서 **수정** → 상품명만 바꾸고 저장  
4. 사진이 **그대로**인지 확인  

| 항목 | O/X |
|------|-----|
| 로그인 | |
| 등록 | |
| 수정 후 목록 반영 | |
| 사진 유지 | |

### 실습 2 ⭐⭐
같은 상품을 다시 수정해 **새 사진만** 바꾸고 저장한 뒤,  
목록·`uploads` 폴더에 새 파일이 보이는지 확인하세요.

### 실습 3 ⭐⭐
삭제 전 `confirm`이 뜨는지, 취소하면 삭제되지 않는지 확인하세요.

### 실습 4 ⭐⭐⭐
`productUpdateProcess.jsp`에 주석으로  
(1) Multipart (2) 검증 (3) 기존 행 조회 (4) image 유지/교체 (5) update (6) redirect  
번호를 매겨 제출용으로 정리하세요.

### 실습 5 ⭐⭐⭐ (응용)
삭제 시 DB뿐 아니라 `uploads`의 **실제 파일**도 지우려면  
어떤 순서로 코드를 짜면 될지 의사코드를 작성하세요.  
(힌트: 먼저 `selectById`로 파일명 확인 → `delete` → `File.delete`)
