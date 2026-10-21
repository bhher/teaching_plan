# member-crud 상세 정리

**소스 폴더:** `jsp/member-crud`  
**DB 이름:** `member_crud`  
**특징:** JSP + MySQL + DAO 패턴 회원관리 (가입 · 로그인 · 세션 · CRUD)

화면만 만드는 수준이 아니라 **회원가입, 로그인, 세션 유지, 목록·상세·수정·삭제**까지 연결되어 있습니다.  
파일별 역할과 연결 흐름을 차근차근 정리합니다.

---

## 1. 전체 프로젝트 구조

```text
member-crud/
├── db_setup.sql                 ← DB · 테이블 · 샘플 계정
├── README.md
├── member-crud-상세정리.md      ← 이 문서
├── src/
│   ├── util/DBConnection.java   ← DB 연결
│   ├── dto/MemberDTO.java       ← 회원 데이터 객체
│   └── dao/MemberDAO.java       ← SQL 실행
└── WebContent/
    ├── index.jsp                ← 메인
    ├── include/
    │   ├── header.jsp           ← 공통 상단
    │   └── footer.jsp           ← 공통 하단
    ├── WEB-INF/web.xml
    └── member/
        ├── join.jsp · joinProcess.jsp
        ├── login.jsp · loginProcess.jsp · logout.jsp
        ├── list.jsp · view.jsp
        ├── update.jsp · updateProcess.jsp
        └── deleteProcess.jsp
```

| 폴더·파일 | 역할 |
|-----------|------|
| `index.jsp` | 메인 화면 |
| `include/` | header · footer 공통 UI |
| `member/` | 가입·로그인·목록·상세·수정·삭제 |
| `WEB-INF/web.xml` | welcome 파일 등 기본 설정 |
| `src/dao · dto · util` | DB 접근 · 데이터 담기 · 연결 |

---

## 2. 먼저 알아야 할 4가지 개념

| 개념 | 역할 | 이 프로젝트의 예 |
|------|------|------------------|
| **JSP** | 화면 출력 · 요청 처리 | `join.jsp`, `loginProcess.jsp` |
| **DTO** | 회원 데이터 전달 | `MemberDTO` |
| **DAO** | 데이터베이스 접근 | `MemberDAO` |
| **Session** | 로그인 상태 유지 | `loginId`, `loginName` |

### 데이터 흐름

```text
사용자 화면
  회원가입 폼 · 로그인 폼 · 버튼 클릭
        ↓
JSP 처리 파일
  요청값 검사 · 권한 확인 · DAO 호출
        ↓
MemberDAO
  SQL 실행 (조회·저장·수정·삭제)
        ↓
MySQL (member 테이블)
  회원 정보 저장
```

---

## 3. DB · DTO · 연결

### 3.1 `db_setup.sql`

| 항목 | 내용 |
|------|------|
| DB | `member_crud` |
| 테이블 | `member` |
| PK | `id` (VARCHAR) |
| 컬럼 | `id`, `password`, `name`, `email`, `reg_date` |
| 샘플 | `admin/1234`, `hong/1234` |

### 3.2 `MemberDTO.java`

회원 **한 명**의 정보를 담는 객체입니다.

| 필드 | 타입 | 의미 |
|------|------|------|
| `id` | String | 아이디 |
| `password` | String | 비밀번호 |
| `name` | String | 이름 |
| `email` | String | 이메일 |
| `regDate` | Timestamp | 가입일 (`reg_date`) |

getter / setter로 JSP ↔ DAO 사이에 값을 주고받습니다.

### 3.3 `DBConnection.java`

```java
DriverManager.getConnection(
  "jdbc:mysql://localhost:3306/member_crud?...",
  "root", "1234");
```

비밀번호·포트는 본인 MySQL 환경에 맞게 수정합니다.

---

## 4. 공통 화면

### ① `include/header.jsp` — 공통 상단

```jsp
<%
    String loginId = (String) session.getAttribute("loginId");
    String loginName = (String) session.getAttribute("loginName");
    String uri = request.getRequestURI();
%>
```

| 코드 | 의미 |
|------|------|
| `session.getAttribute("loginId")` | 로그인한 아이디 |
| `session.getAttribute("loginName")` | 로그인한 이름 |
| `request.getRequestURI()` | 현재 페이지 URI |

```jsp
<% if (loginId == null) { %>
    <!-- 로그인 · 회원가입 메뉴 -->
<% } else { %>
    <!-- 이름(아이디) · 내정보 · 로그아웃 -->
<% } %>
```

- 미로그인 → 로그인·회원가입
- 로그인 → 이름·아이디·내정보·로그아웃

```jsp
uri.endsWith("list.jsp") ? "active" : ""
```

현재 페이지 메뉴에 Bootstrap `active` 클래스를 붙입니다.  
스타일은 Bootstrap CDN CSS에 정의되어 있습니다.

```jsp
href="<%= request.getContextPath() %>/member/list.jsp"
```

`getContextPath()`는 예: `/member-crud` 같은 앱 경로입니다.

**핵심:** 메뉴를 한 번 작성해 여러 페이지에서 재사용합니다.

### ② `include/footer.jsp` — 공통 하단

```html
</main>
<script src="...bootstrap.bundle.min.js"></script>
</body>
</html>
```

header가 연 `<main>`·HTML을 닫고 Bootstrap JS를 불러옵니다.

페이지에서 포함 방법:

```jsp
<%@ include file="/include/header.jsp" %>
<!-- 본문 -->
<%@ include file="/include/footer.jsp" %>
```

`<%@ include %>`는 **정적 include** — 처리 시 해당 파일 내용이 현재 JSP에 합쳐집니다.

### ③ `index.jsp` — 메인

헤더 include 후 `loginId`로 버튼(로그인/가입 vs 내정보)을 나눕니다.  
하단 테스트 계정 안내: `admin/1234`, `hong/1234`.

---

## 5. 회원가입

### ④ `join.jsp` — 입력 화면

이미 로그인이면 목록으로 보냄:

```jsp
if (session.getAttribute("loginId") != null) {
    response.sendRedirect(request.getContextPath() + "/member/list.jsp");
    return;
}
```

오류 표시 (`joinProcess`가 쿼리로 전달):

```jsp
String err = request.getParameter("error");
if ("dup".equals(err)) { /* 아이디 중복 */ }
else if ("empty".equals(err)) { /* 필수값 누락 */ }
```

| 값 | 의미 | 어디서 보냄 |
|----|------|-------------|
| `dup` | 아이디 중복 | `joinProcess.jsp?error=dup` |
| `empty` | 필수값 비어 있음 | `joinProcess.jsp?error=empty` |

폼:

```html
<form method="post" action="joinProcess.jsp">
  <input name="id" ...>
  <input name="password" ...>
  <input name="name" ...>
  <input name="email" ...>
</form>
```

- `method="post"` → POST 전송  
- `action="joinProcess.jsp"` → 처리 페이지  
- `name="id"` → `request.getParameter("id")`로 읽음  
- `required` → 브라우저 필수 검사  

### ⑤ `joinProcess.jsp` — 처리

**1) 인코딩 · 파라미터**

```jsp
request.setCharacterEncoding("UTF-8");  // getParameter 전에!
String id = request.getParameter("id");
// password, name, email ...
```

**2) 필수값 검사** → 실패 시 `join.jsp?error=empty`

**3) trim · 빈 이메일은 `null`**

**4) 중복 검사**

```jsp
if (dao.existsId(id)) {
    response.sendRedirect("join.jsp?error=dup");
    return;
}
```

**5) DTO에 담기 → `dao.insert(m)` → `login.jsp`로 이동**

```text
join.jsp → joinProcess.jsp → MemberDTO → MemberDAO.insert()
                                              ↓
                                         MySQL 저장
                                              ↓
                                         login.jsp
```

---

## 6. 로그인 · 로그아웃

### ⑥ `login.jsp`

이미 로그인이면 목록으로.  
`error=1`이면 「아이디 또는 비밀번호가 올바르지 않습니다.」

이 메시지는 **가입 직후 자동으로 뜨는 것이 아니라**,  
`loginProcess`가 실패했을 때 `login.jsp?error=1`로 보낼 때만 표시됩니다.

### ⑦ `loginProcess.jsp`

```jsp
MemberDTO member = dao.login(id.trim(), password);
if (member == null) {
    response.sendRedirect("login.jsp?error=1");
    return;
}
session.setAttribute("loginId", member.getId());
session.setAttribute("loginName", member.getName());
response.sendRedirect(... + "/member/list.jsp");
```

**핵심:** 세션에 `loginId`, `loginName`을 넣어 이후 페이지에서 로그인 상태를 유지합니다.

### ⑧ `logout.jsp`

```jsp
session.invalidate();
response.sendRedirect(... + "/member/login.jsp");
```

세션을 통째로 무효화 → 로그인 정보 사라짐.

---

## 7. 목록 · 상세

### ⑨ `list.jsp`

```jsp
MemberDAO dao = new MemberDAO();
List<MemberDTO> list = dao.selectAll();
```

- `list.isEmpty()` → 「회원 없음」
- `for (MemberDTO m : list)` → 표에 id, name, email, regDate
- 상세 링크: `view.jsp?id=<%= m.getId() %>`  
  예: `view.jsp?id=hong`

### ⑩ `view.jsp`

```jsp
String id = request.getParameter("id");
MemberDTO m = dao.selectById(id.trim());
```

권한(버튼 표시용):

```jsp
String sid = (String) session.getAttribute("loginId");
boolean canEdit =
    sid != null && (sid.equals(m.getId()) || "admin".equals(sid));
```

- 본인 또는 `admin` → 수정·삭제 버튼  
- 버튼만 숨기는 것과 별개로, **Process JSP에서도 권한을 다시 검사**합니다.

---

## 8. 수정 · 삭제

### ⑪ `update.jsp`

1. 미로그인 → `login.jsp`  
2. 본인/admin 아니면 → `view.jsp`  
3. `selectById`로 기존값 표시  

```html
<input type="hidden" name="id" value="...">   <!-- 전송용 -->
<input type="text" value="..." disabled>      <!-- 화면만, 전송 안 됨 -->
```

`disabled`는 제출되지 않으므로, 아이디는 **hidden**으로 따로 보냅니다.

### ⑫ `updateProcess.jsp`

로그인·권한 검사 → DTO → `dao.update(m)`  
본인 이름 변경 시:

```jsp
session.setAttribute("loginName", m.getName());
```

헤더에 옛 이름이 남지 않도록 세션도 갱신합니다.

### ⑬ `deleteProcess.jsp`

1. 로그인·권한 검사  
2. `admin` 계정 삭제 방지  
3. `dao.delete(id)`  
4. **본인 삭제** → `session.invalidate()` 후 로그인 페이지  
5. 타인 삭제(관리자) → 목록  

화면의 `confirm('정말 삭제할까요?')`는 UX용이며, 서버 권한 검사를 대체하지 않습니다.

---

## 9. `web.xml`

```xml
<welcome-file>index.jsp</welcome-file>
```

루트 접속 시 `index.jsp` 사용.  
`WEB-INF` 아래는 브라우저 URL로 직접 접근할 수 없는 설정·내부 영역입니다.

---

## 10. MemberDAO.java 상세

JSP가 DB를 건드릴 때 호출하는 클래스입니다.

```text
joinProcess / loginProcess / list / view / updateProcess / deleteProcess
                    ↓
              MemberDAO 메서드
                    ↓
                 MySQL
```

DAO가 없으면 JSP마다 SQL을 반복 작성하게 됩니다.

### JDBC 핵심 객체

| 객체 | 역할 |
|------|------|
| `Connection` | DB 연결 |
| `PreparedStatement` | SQL + `?` 파라미터 |
| `ResultSet` | SELECT 결과 행 |

- 조회 → `executeQuery()` → `ResultSet`  
- 변경(INSERT/UPDATE/DELETE) → `executeUpdate()` → 처리 행 수(`int`)

### 메서드 요약

| 메서드 | 기능 | 반환 |
|--------|------|------|
| `existsId(id)` | 아이디 존재 여부 | `boolean` (`rs.next()`) |
| `insert(m)` | 가입 저장 | `int` |
| `login(id, pw)` | 로그인 검증 | `MemberDTO` 또는 `null` |
| `selectAll()` | 전체 목록 | `List<MemberDTO>` |
| `selectById(id)` | 한 명 조회 | `MemberDTO` 또는 `null` |
| `update(m)` | 수정 | `int` |
| `delete(id)` | 삭제 | `int` |
| `map(rs)` | ResultSet → DTO | `MemberDTO` (private) |

### `existsId`

```java
return rs.next();
// 행 있으면 true(중복), 없으면 false
```

### `insert`

`?` 네 개에 `m.getId()`, `getPassword()`, `getName()`, `getEmail()` 설정 후 `executeUpdate()`.

### `login`

`WHERE id = ? AND password = ?`  
성공 → `map(rs)`, 실패 → `null`.

### `selectAll` / `selectById`

- 전체: `while (rs.next()) list.add(map(rs));` + `ORDER BY reg_date DESC`  
- 한 명: `if (rs.next()) return map(rs);` else `null`

### `update` / `delete`

`WHERE id = ?`가 있어야 해당 회원만 변경·삭제됩니다.

### `map(rs)` — 풀어 쓰면

```java
MemberDTO m = new MemberDTO();
m.setId(rs.getString("id"));
m.setPassword(rs.getString("password"));
m.setName(rs.getString("name"));
m.setEmail(rs.getString("email"));
m.setRegDate(rs.getTimestamp("reg_date"));
return m;
```

`login`, `selectAll`, `selectById`에서 같은 변환을 반복하지 않으려고 메서드로 묶었습니다.  
그래서 `list.add(map(rs))`처럼 **반환 타입이 `MemberDTO`이므로** `add`에 바로 넣을 수 있습니다.

### try-with-resources

```java
try (Connection conn = ...;
     PreparedStatement ps = ...) {
    // ...
}
```

블록이 끝나면 `Connection` / `PreparedStatement` / `ResultSet`이 자동으로 닫힙니다.

### `throws Exception`

DB 오류 등을 호출한 JSP 쪽으로 넘깁니다. 자동으로 고쳐 주지는 않습니다.

---

## 11. 실제 사용 순서

| 순서 | 흐름 |
|------|------|
| ① 가입 | `join` → `joinProcess` → `insert()` |
| ② 로그인 | `login` → `loginProcess` → 세션 저장 |
| ③ 조회 | `list` → `view` → `selectAll` / `selectById` |
| ④ 수정 | `update` → `updateProcess` → `update()` |
| ⑤ 삭제 | `view` → `deleteProcess` → `delete()` |
| ⑥ 로그아웃 | `logout` → `session.invalidate()` |

---

## 12. 학습용에서 알아 둘 개선 포인트

1. **비밀번호 평문** — 해싱(BCrypt 등) 권장. 수정 폼에 비밀번호를 value로 넣지 않기.  
2. **XSS** — `<%= m.getName() %>` 등 출력 시 HTML 이스케이프.  
3. **목록에서 password 조회** — 화면에 불필요하면 SQL에서 제외.  
4. **중복 아이디** — `existsId` + DB `PRIMARY KEY`/`UNIQUE` (이미 PK).  
5. **CSRF** — 삭제·수정 POST에 토큰 검토.  
6. **PreparedStatement `?` 사용** — SQL 문자열 연결보다 안전 (이 프로젝트는 사용 중).

---

## 13. 읽는 순서 추천

1. `join.jsp` + `joinProcess.jsp` (화면 → 검사 → DTO → DAO)  
2. `loginProcess.jsp` (세션)  
3. `list.jsp` / `view.jsp`  
4. `updateProcess` / `deleteProcess` (권한)  
5. `MemberDAO.java` + `MemberDTO.java` + `db_setup.sql`

같은 패턴이 가입·로그인·수정·삭제에 반복됩니다.

---

## 문제 · 정답

- 문제: [member-crud-문제.md](./member-crud-문제.md)  
- 정답: [member-crud-답.md](./member-crud-답.md)
