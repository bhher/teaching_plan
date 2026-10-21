# admin-product-upload 상세 정리

**소스:** `jsp/admin-product-upload`  
**DB:** `shop_upload` (MySQL / MariaDB 동일 JDBC)  
**범위:** 관리자 로그인 + 상품 등록(이미지) + 목록 조회  
**수정·삭제 버전:** `jsp/admin-product-upload1`

처음 배울 때 읽기 좋은 순서:  
**전체 구조 → JSP → DAO → DTO → 데이터 흐름 → 다음에 연습할 것**

---

## 1. 프로젝트 전체 구조

```text
JSP 화면
  login.jsp · productWrite.jsp · productList.jsp
  → 화면 보여 주고 입력 받음
        ↓
JSP 처리
  loginProcess.jsp · productWriteProcess.jsp
  → 로그인 검증 · 이미지 업로드 · DB 저장
        ↓
DAO + DTO
  AdminDAO · ProductDAO / AdminDTO · ProductDTO
  → DAO = DB 작업, DTO = 데이터 상자
        ↓
DB (shop_upload)
  admin · product 테이블
```

### 쉬운 비유

| 역할 | 비유 |
|------|------|
| JSP | 접수 창구 (화면·입력) |
| DAO | DB에 조회·저장을 요청하는 담당자 |
| DTO | 데이터를 담아 전달하는 상자 |
| DB | 실제 저장소 |
| DBConnection | Java ↔ DB 통로 |

학습용으로 JSP 안에 Java를 두는 방식입니다.  
실무에서는 화면(JSP)과 처리(Servlet/Controller)를 더 나누는 편을 권장합니다.

---

## 2. JSP 파일별 역할

### ① `index.jsp` — 첫 접속

로그인 여부에 따라 이동만 합니다.

- `adminId` 있음 → `productWrite.jsp`  
- 없음 → `login.jsp`  

`request.getContextPath()` = 프로젝트 웹 경로 (예: `/admin-product-upload`)

### ② `login.jsp` — 로그인 화면

```html
<form method="post" action="loginProcess.jsp">
  <input name="id" ...>
  <input name="password" ...>
</form>
```

| 속성 | 의미 |
|------|------|
| `method="post"` | POST 전송 |
| `action` | 처리할 JSP |
| `name="id"` | `request.getParameter("id")`로 받음 |
| `required` | 브라우저 필수 검사 |

### ③ `loginProcess.jsp` — 로그인 처리

실제 코드 순서:

1. `setCharacterEncoding`  
2. 아이디·비밀번호 **null/빈값 검사** (그다음 `trim`)  
3. `AdminDAO.login()`  
4. 실패 → `login.jsp?error=1`  
5. 성공 → 세션에 `adminId`, `adminName` → `productWrite.jsp`  

`return` = 이 아래 코드를 더 실행하지 않음.  
`session.setAttribute` = 다른 페이지에서도 로그인 상태 확인 가능.

> 학습용은 비밀번호 **평문** 비교. 실무는 해시 검증이 필요.

### ④ `logout.jsp`

`session.invalidate()` 후 로그인 페이지로 이동.  
세션의 `adminId` / `adminName`도 함께 사라집니다.

### ⑤ `adminCheck.jspf` — 로그인 확인 조각

`.jspf` = **Page Fragment** (다른 JSP에 include하는 조각)

```jsp
if (session.getAttribute("adminId") == null) {
    response.sendRedirect(... + "/admin/login.jsp");
    return;
}
```

`productWrite.jsp`, `productList.jsp`, **`productWriteProcess.jsp`** 모두 include합니다.  
화면뿐 아니라 **처리 URL을 직접 치는 경우**도 막기 위함입니다.

### ⑥ `productWrite.jsp` — 상품 등록 화면

```html
<form method="post" action="productWriteProcess.jsp"
      enctype="multipart/form-data">
```

파일 업로드 시 **`enctype="multipart/form-data"` 필수**.  
`name="imageFile"`은 Process의 `getFilesystemName("imageFile")`과 **이름이 같아야** 합니다.

에러·성공 메시지:

| 쿼리 | 의미 |
|------|------|
| `?error=empty` | 상품명·가격 문제 |
| `?error=file` | 이미지 없음 |
| `?msg=ok` | 등록 완료 |

### ⑦ `productWriteProcess.jsp` — 등록 처리 (핵심)

```text
업로드 폴더 준비
  → MultipartRequest (파일 저장)
  → 폼값·파일명 읽기
  → 검증
  → ProductDTO에 담기
  → ProductDAO.insert()
  → productWrite.jsp?msg=ok
```

**1) 폴더**

```jsp
String savePath = application.getRealPath("/uploads");
if (!dir.exists()) dir.mkdirs();
```

**2) MultipartRequest (COS)**

| 인수 | 의미 |
|------|------|
| `request` | 요청 |
| `savePath` | 저장 폴더 |
| `5*1024*1024` | 최대 5MB |
| `"utf-8"` | 인코딩 |
| `DefaultFileRenamePolicy` | 같은 이름이면 자동 변경 |

`WEB-INF/lib`에 **`cos.jar`** 필요.

**3) 값 읽기**

- 텍스트: `multi.getParameter("name")` 등  
- 저장된 파일명: `multi.getFilesystemName("imageFile")`  
- 가격은 문자열 → `Integer.parseInt`

**4) DTO → DAO**

```jsp
p.setName(...); p.setPrice(...); p.setImage(fileName); ...
new ProductDAO().insert(p);
```

DB에는 **파일명만** 넣고, 실제 파일은 `uploads/`에 둡니다.

### ⑧ `productList.jsp` — 목록

```jsp
List<ProductDTO> list = new ProductDAO().selectAll();
for (ProductDTO p : list) { ... }
```

이미지 경로 (현재 코드):

```jsp
ctx + "/uploads/" + p.getImage()
```

`String.format("%,d", p.getPrice())` → `35000` → `35,000`

---

## 3. DAO

DAO = Data Access Object → **DB 접근 전담**

### `DBConnection.java`

```text
jdbc:mysql://localhost:3306/shop_upload
계정 root / 비밀번호(환경에 맞게)
```

MariaDB도 같은 MySQL JDBC URL로 접속하는 경우가 많습니다.

### `AdminDAO.login`

```sql
SELECT ... FROM admin WHERE id = ? AND password = ?
```

행 있으면 `AdminDTO` 반환, 없으면 `null`.

### `ProductDAO`

| 메서드 | SQL | 용도 |
|--------|-----|------|
| `selectAll()` | `SELECT ... ORDER BY id DESC` | 목록 |
| `insert(p)` | `INSERT (name, price, image, description)` | 등록 |

- 조회 → `executeQuery()` → `ResultSet`  
- 등록 → `executeUpdate()` → 영향 행 수  
- try-with-resources로 Connection 등 자동 close  

테이블 컬럼명은 **`description`** (SQL·DTO와 일치해야 함. `desc`는 예약어라 피하는 것이 좋음).

---

## 4. DTO

DTO = Data Transfer Object → **데이터 상자**

### `ProductDTO`

| 필드 | 의미 |
|------|------|
| id | 상품 번호 |
| name | 상품명 |
| price | 가격 |
| image | 이미지 **파일명** |
| description | 설명 |
| regDate | 등록일 |

`private` + getter/setter = 캡슐화.

### `AdminDTO`

`id`, `password`, `name` — 로그인 결과 전달용.

---

## 5. DTO vs DAO

| | DTO | DAO |
|--|-----|-----|
| 목적 | 보관·전달 | DB 작업 |
| 예 | `ProductDTO` | `ProductDAO` |
| 대표 | `setName` / `getName` | `executeUpdate` / `executeQuery` |
| 비유 | 상자 | DB 담당 직원 |

**한 줄:** DTO는 담고, DAO는 DB에 넣거나 꺼낸다.

---

## 6. header / footer

- `header.jsp`: HTML 시작, Bootstrap, 메뉴, `<main>` 시작, `ctx`  
- `footer.jsp`: `</main>`, Bootstrap JS, HTML 끝  
- `<%@ include %>`: 정적 include로 공통 UI 재사용  

---

## 7. 상품 등록 한눈에

```text
1. productWrite.jsp     입력·파일 선택
2. productWriteProcess  Multipart + 검증
3. ProductDTO           데이터 담기
4. ProductDAO.insert    INSERT
5. productList.jsp      selectAll → 표·썸네일
```

---

## 8. 꼭 확인할 점

1. **product 테이블**에 `description`, `image` 컬럼이 SQL과 같은지  
2. **cos.jar** + `uploads` 폴더  
3. Process에도 **adminCheck** (주소 직접 접근 방지) — 현재 적용됨  
4. 학습용 평문 비밀번호 · XSS 이스케이프는 이후 보안 단계에서 보강  
5. `insert` 성공 여부(반환값·예외)를 더 엄격히 처리하면 좋음  

### 추천 읽는 순서

`ProductDTO` → `ProductDAO` → `productWrite.jsp` → `productWriteProcess.jsp` → `productList.jsp`

---

## 9. 다음에 연습하면 좋은 것

지금 이미 익힌 것: HTML 폼, JSP, 세션, DTO, DAO, JDBC, SQL, 파일 업로드.

**기존 프로젝트를 확장**하는 순서를 추천합니다.

| 단계 | 내용 | 참고 |
|------|------|------|
| 1 | 상품 **수정·삭제** | → `admin-product-upload1` |
| 2 | 상품명 **검색** · **페이징** | `LIKE`, LIMIT |
| 3 | 보안 | 비밀번호 해시, 확장자·MIME 검사, XSS |
| 4 | 구조 | 처리 로직을 Servlet/Controller로 분리 |

새 프로젝트를 처음부터 만들기보다, **등록이 되는 이 프로젝트에 기능을 하나씩 붙이는 것**이 학습에 유리합니다.
