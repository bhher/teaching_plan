# admin-product-upload 문제 — 관리자 이미지 업로드

이론: [admin-product-upload-상세정리.md](./admin-product-upload-상세정리.md)  
정답: [admin-product-upload-업로드-답.md](./admin-product-upload-업로드-답.md)  
관련 파일: `productWrite.jsp`, `productWriteProcess.jsp`, `ProductDAO`, `cos.jar`

**목표:** 관리자 로그인 후 **상품 사진**을 COS `MultipartRequest`로 올리고 DB·목록에 보이게 하기

---

## 객관식

### Q1. 파일(이미지)을 폼으로 보낼 때 form에 **반드시** 넣어야 하는 것은?

① `method="get"`  
② `enctype="multipart/form-data"`  
③ `action="login.jsp"`  
④ `target="_blank"`  

### Q2. COS로 파일+일반 입력을 함께 처리하는 클래스는?

① `HttpServlet`  
② `MultipartRequest`  
③ `ProductDTO`  
④ `ResultSet`  

### Q3. `productWrite.jsp`의 `<input type="file" name="???">` 이름이 Process와 같아야 하는 이유는?

① Bootstrap 규칙이라서  
② `getFilesystemName("이름")`으로 파일을 찾기 위해  
③ MySQL 예약어라서  
④ 세션 키와 맞춰야 해서  

### Q4. DB `product.image` 컬럼에 보통 무엇을 저장하나?

① 이미지 바이너리 전체  
② **저장된 파일명** (예: `shoes.jpg`)  
③ `uploads` 폴더의 절대경로만  
④ Base64 HTML  

### Q5. 실제 이미지 파일이 서버에 저장되는 위치(이 프로젝트)는?

① `src/dao/`  
② `WebContent/uploads/` (realPath)  
③ `db_setup.sql`  
④ 세션  

### Q6. `MultipartRequest` 생성 후 상품명은 어떻게 읽나?

① `request.getParameter("name")`  
② `multi.getParameter("name")`  
③ `session.getAttribute("name")`  
④ `rs.getString("name")`  

### Q7. 업로드된 파일의 **서버 저장 파일명**을 얻는 메서드는?

① `multi.getParameter("imageFile")`  
② `multi.getFilesystemName("imageFile")`  
③ `multi.getContentType()` 만  
④ `request.getRequestURI()`  

### Q8. 같은 이름 파일이 이미 있을 때 자동으로 이름을 바꿔 주는 것은?

① `DefaultFileRenamePolicy`  
② `adminCheck.jspf`  
③ `executeUpdate()`  
④ `trim()`  

### Q9. 관리자가 아닌 사람이 `productWriteProcess.jsp`에 직접 접근하지 못하게 하는 것은?

① Bootstrap CSS  
② `adminCheck.jspf` (세션 `adminId` 검사)  
③ `ORDER BY id DESC`  
④ `col-lg-3`  

### Q10. `5 * 1024 * 1024`의 의미는?

① 최대 5KB  
② 최대 **5MB**  
③ DB 포트  
④ 상품 개수 제한  

---

## 단답 · 빈칸

### Q11
상품 등록 흐름 빈칸을 채우세요.

```text
login → productWrite.jsp
         ↓ submit (multipart)
      productWrite________.jsp
         ↓ MultipartRequest → uploads/
         ↓ ProductDAO.________(p)
      DB product 테이블
         ↓
      productList / Gallery (이미지 표시)
```

### Q12
목록에서 이미지를 보이게 하는 경로를 완성하세요.

```jsp
ctx + "/__________/" + p.getImage()
```

### Q13
`WEB-INF/lib`에 넣어야 하는 업로드용 JAR 이름은? (약칭)

### Q14
파일이 선택되지 않았을 때 Process가 redirect하는 주소 예시는?

```text
productWrite.jsp?error=______
```

---

## 코드 고치기

### Q15
다음 폼의 **틀린 점 2가지**를 고치세요.

```html
<form method="get" action="productWriteProcess.jsp">
  <input type="text" name="name">
  <input type="file" name="photo">
  <button type="submit">등록</button>
</form>
```

(Process는 `name="imageFile"`, MultipartRequest 사용한다고 가정)

### Q16
다음 Process 코드의 문제를 말하세요.

```jsp
String name = request.getParameter("name");  // multipart 폼인데
MultipartRequest multi = new MultipartRequest(...);
String file = multi.getFilesystemName("imageFile");
```

---

## 서술

### Q17
왜 파일 업로드 폼에서는 일반 `request.getParameter`만으로 부족한지  
`enctype`과 관련해 2~3문장으로 설명하세요.

### Q18
DB에는 파일명만 넣고 실제 파일은 폴더에 두는 이유를 한 가지 쓰세요.

---

## 실습

### 실습 1 ⭐
1. `cos.jar`를 `WEB-INF/lib`에 넣는다.  
2. `admin/1234`로 로그인한다.  
3. 상품명·가격·이미지를 올려 등록한다.  
4. **목록**과 **갤러리**에서 사진이 보이는지 확인한다.

체크리스트에 O/X를 표시하세요.

| 항목 | O/X |
|------|-----|
| 로그인 성공 | |
| 등록 후 `msg=ok` 메시지 | |
| `uploads`에 파일 생성 | |
| 표 목록 썸네일 표시 | |
| 갤러리 카드 이미지 표시 | |

### 실습 2 ⭐⭐
이미지를 **안 고르고** 등록을 시도했을 때  
어떤 메시지가 나오는지, URL에 `error=` 값이 무엇인지 적으세요.  
(`required`를 잠깐 제거해 보고 서버 검증도 확인)

### 실습 3 ⭐⭐
`productWriteProcess.jsp`에서  
`savePath`, `MultipartRequest` 인수, `getFilesystemName`, `insert` 호출 위치를  
주석으로 번호를 매겨 설명해 보세요. (제출용)

### 실습 4 ⭐⭐⭐
최대 용량을 **2MB**로 바꾸려면 코드를 어디를 수정할지 적고,  
2MB보다 큰 파일을 올렸을 때 어떤 일이 생기는지 관찰해 기록하세요.

### 실습 5 ⭐⭐⭐ (응용)
허용 확장자를 jpg/png만 받도록 Process에서 파일명 끝부분을 검사하는  
의사코드(또는 실제 코드)를 작성하세요.
