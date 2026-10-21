# admin-product-upload1 상세 정리

**소스:** `jsp/admin-product-upload1`  
**DB:** `shop_upload` (MySQL / MariaDB 동일 JDBC)  
**범위:** 관리자 로그인 + 상품 **등록·목록·수정·삭제** (이미지 업로드 포함)  
**등록만 버전:** `jsp/admin-product-upload`

관련 자료: [문제](./admin-product-upload1-문제.md) · [정답](./admin-product-upload1-답.md)

처음 배울 때 읽기 좋은 순서:  
**전체 구조 → JSP → DAO → DTO → 수정·삭제 핵심 → 데이터 흐름**

---

## 1. 프로젝트 전체 구조

```text
JSP 화면
  login.jsp · productWrite.jsp · productList.jsp · productUpdate.jsp
  → 화면 보여 주고 입력 받음
        ↓
JSP 처리
  loginProcess · productWriteProcess
  productUpdateProcess · productDeleteProcess
  → 로그인 · 업로드 · DB 저장/수정/삭제
        ↓
DAO + DTO
  AdminDAO · ProductDAO / AdminDTO · ProductDTO
  → DAO = DB 작업, DTO = 데이터 상자
        ↓
DB (shop_upload)
  admin · product 테이블
```

### upload vs upload1

| | admin-product-upload | **admin-product-upload1** |
|--|----------------------|---------------------------|
| 등록·목록 | O | O |
| 갤러리 | O | (없음) |
| **수정** | X | **O** (`selectById` + `update`) |
| **삭제** | X | **O** (`delete`) |

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

`request.getContextPath()` = 프로젝트 웹 경로 (예: `/admin-product-upload1`)

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

### ⑤ `adminCheck.jspf` — 로그인 확인 조각

`.jspf` = **Page Fragment** (다른 JSP에 include하는 조각)

```jsp
if (session.getAttribute("adminId") == null) {
    response.sendRedirect(... + "/admin/login.jsp");
    return;
}
```

`productWrite` · `productList` · `productUpdate` ·  
**Process 전부**(Write / Update / Delete)에 include합니다.  
화면뿐 아니라 **처리 URL을 직접 치는 경우**도 막기 위함입니다.

### ⑥ `productWrite.jsp` — 상품 등록 화면

```html
<form method="post" action="productWriteProcess.jsp"
      enctype="multipart/form-data">
```

파일 업로드 시 **`enctype="multipart/form-data"` 필수**.  
`name="imageFile"`은 Process의 `getFilesystemName("imageFile")`과 **이름이 같아야** 합니다.

| 쿼리 | 의미 |
|------|------|
| `?error=empty` | 상품명·가격 문제 |
| `?error=file` | 이미지 없음 |
| `?msg=ok` | 등록 완료 |

### ⑦ `productWriteProcess.jsp` — 등록 처리

```text
업로드 폴더 준비
  → MultipartRequest (파일 저장)
  → 폼값·파일명 읽기
  → 검증 (이미지 필수)
  → ProductDTO → ProductDAO.insert()
  → productWrite.jsp?msg=ok
```

| MultipartRequest 인수 | 의미 |
|----------------------|------|
| `request` | 요청 |
| `savePath` | `application.getRealPath("/uploads")` |
| `5*1024*1024` | 최대 5MB |
| `"utf-8"` | 인코딩 |
| `DefaultFileRenamePolicy` | 같은 이름이면 자동 변경 |

`WEB-INF/lib`에 **`cos.jar`** 필요.  
DB에는 **파일명만**, 실제 파일은 `uploads/`에 둡니다.

### ⑧ `productList.jsp` — 목록 (+ 관리 버튼)

```jsp
List<ProductDTO> list = new ProductDAO().selectAll();
for (ProductDTO p : list) { ... }
```

이미지 경로:

```jsp
ctx + "/uploads/" + p.getImage()
```

**upload1에서 추가된 관리 열:**

| 버튼 | 동작 |
|------|------|
| 수정 | `productUpdate.jsp?id=번호` |
| 삭제 | POST → `productDeleteProcess.jsp` + `confirm(...)` |

삭제 폼 예:

```html
<form method="post" action="productDeleteProcess.jsp"
      onsubmit="return confirm('정말 삭제할까요?');">
  <input type="hidden" name="id" value="...">
  <button type="submit">삭제</button>
</form>
```

### ⑨ `productUpdate.jsp` — 수정 화면 (핵심)

```text
?id= 받기 → 숫자 검사 → ProductDAO.selectById(id)
  → 없으면 목록으로
  → 있으면 기존 값으로 폼 채움 + 현재 사진 미리보기
```

| 포인트 | 설명 |
|--------|------|
| `hidden` id | Process가 **어느 행을 고칠지** 알기 위해 필수 |
| `enctype` | 사진 변경 가능 → multipart |
| 사진 `required` 없음 | **선택** — 안 고르면 기존 이미지 유지 |
| disabled 번호 | 화면용만, 실제 id는 hidden으로 전송 |

### ⑩ `productUpdateProcess.jsp` — 수정 처리 (핵심)

```text
MultipartRequest
  → id · name · price · description · 새 파일명
  → 검증 (상품명·가격 필수, 사진은 선택)
  → selectById로 기존 행 확인
  → 새 파일 있으면 image 교체, 없으면 기존 image 유지
  → ProductDAO.update(p)
  → productList.jsp
```

**이미지 유지 로직 (가장 중요):**

```jsp
String image = old.getImage();           // 기본 = 기존 파일명
if (fileName != null && !fileName.isEmpty()) {
    image = fileName;                    // 새 파일 올렸을 때만 교체
}
```

등록(Write)과 다른 점: Write는 **이미지 필수**, Update는 **이미지 선택**.

### ⑪ `productDeleteProcess.jsp` — 삭제 처리

```text
id 파라미터 → 숫자 검사 → ProductDAO.delete(id) → 목록
```

파일 업로드가 없으므로 **일반** `request.getParameter` 사용.  
(현재 코드는 DB 행만 삭제 — `uploads` 물리 파일 삭제는 확장 과제)

---

## 3. DAO

DAO = Data Access Object → **DB 접근 전담**

### `DBConnection.java`

```text
jdbc:mysql://localhost:3306/shop_upload
계정 root / 비밀번호(환경에 맞게)
```

### `AdminDAO.login`

```sql
SELECT ... FROM admin WHERE id = ? AND password = ?
```

행 있으면 `AdminDTO` 반환, 없으면 `null`.

### `ProductDAO` (upload1 전체)

| 메서드 | SQL | 용도 |
|--------|-----|------|
| `insert(p)` | `INSERT (name, price, image, description)` | 등록 |
| `selectAll()` | `SELECT ... ORDER BY id DESC` | 목록 |
| **`selectById(id)`** | `SELECT ... WHERE id = ?` | 수정 폼 채우기 |
| **`update(p)`** | `UPDATE ... WHERE id = ?` | 수정 저장 |
| **`delete(id)`** | `DELETE ... WHERE id = ?` | 삭제 |
| `map(rs)` | ResultSet → DTO | 조회 공통화 |

- 조회 → `executeQuery()` → `ResultSet`  
- 등록·수정·삭제 → `executeUpdate()` → 영향 행 수  
- try-with-resources로 Connection 등 자동 close  

테이블 컬럼명은 **`description`** (SQL·DTO와 일치).

---

## 4. DTO

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

## 7. CRUD 한눈에

```text
[Create] productWrite → Process → insert → (msg=ok)
[Read]   productList  → selectAll → 표·썸네일
[Update] 목록[수정] → productUpdate(?id=)
                    → Process (새 사진 없으면 image 유지)
                    → update → 목록
[Delete] 목록[삭제]+confirm → productDeleteProcess → delete → 목록
```

---

## 8. 꼭 확인할 점

1. **product 테이블**에 `description`, `image` 컬럼이 SQL과 같은지  
2. **cos.jar** + `uploads` 폴더 (등록·수정 모두)  
3. Write / Update / Delete Process에 **adminCheck** — 현재 적용됨  
4. 수정 시 **hidden id** + **기존 image 유지** 로직  
5. 목록 이미지 URL: `ctx + "/uploads/" + 파일명` (슬래시 빠지지 않기)  
6. 학습용 평문 비밀번호 · XSS · 업로드 파일 물리 삭제는 이후 보강  

### 추천 읽는 순서

`ProductDTO` → `ProductDAO` → `productWrite` → `productWriteProcess`  
→ `productList` → **`productUpdate` → `productUpdateProcess` → `productDeleteProcess`**

---

## 9. 다음에 연습하면 좋은 것

지금 이미 익힌 것: 폼, JSP, 세션, DTO, DAO, JDBC, 파일 업로드, **수정·삭제**.

| 단계 | 내용 |
|------|------|
| 1 | 삭제 시 `uploads`의 실제 파일도 지우기 |
| 2 | 상품명 **검색** · **페이징** (`LIKE`, `LIMIT`) |
| 3 | 확장자·MIME 검사, 비밀번호 해시, XSS 이스케이프 |
| 4 | 처리 로직을 Servlet/Controller로 분리 |

등록만 되는 `admin-product-upload`를 먼저 익힌 뒤,  
이 프로젝트에서 **수정·삭제만 집중**해도 학습 효과가 큽니다.
