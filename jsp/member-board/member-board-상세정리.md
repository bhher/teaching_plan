# member-board 상세 정리

**소스 폴더:** `jsp/member-board`  
**DB 이름:** `member_board`  
**특징:** 회원가입·로그인(세션) + 게시판 CRUD 연동

파일별 역할 → 실행 흐름 → 핵심 코드 → 수업 포인트 순으로 정리합니다.

관련 자료: [문제](./member-board-문제.md) · [정답](./member-board-답.md)

---

## 1. 프로젝트 전체 개요

Java + JSP + MySQL 기반 **회원 + 게시판** 웹 애플리케이션입니다.

| 기능 | 내용 |
|------|------|
| 회원 | 가입, 아이디 중복 확인, 로그인·로그아웃 |
| 세션 | `loginId`, `loginName`으로 로그인 유지 |
| 게시판 | 목록·상세·등록·수정·삭제 |
| 권한 | 글쓰기 = 로그인 필수 / 수정·삭제 = **작성자 본인** 또는 **admin** |
| UI | Bootstrap 5 CDN |

### 폴더 구성 (이 저장소 기준)

```text
member-board/
├── db_setup.sql
├── README.md
├── member-board-상세정리.md   ← 이 문서
├── member-board-문제.md
├── member-board-답.md
├── src/
│   ├── util/DBConnection.java
│   ├── dto/MemberDTO.java, BoardDTO.java
│   └── dao/MemberDAO.java, BoardDAO.java
└── WebContent/
    ├── index.jsp
    ├── include/header.jsp, footer.jsp
    ├── member/ join · login · logout (+ Process)
    ├── board/  list · view · write · update · delete (+ Process)
    └── WEB-INF/web.xml, lib/(mysql-connector)
```

| 위치 | 역할 | 예 |
|------|------|-----|
| `WebContent/` | 화면·요청 처리 | JSP, Bootstrap, 폼 |
| `src/dao/` | DB SQL | MemberDAO, BoardDAO |
| `src/dto/` | 데이터 객체 | MemberDTO, BoardDTO |
| `src/util/` | 공통 | DBConnection |
| `WEB-INF/lib/` | JDBC 드라이버 | mysql-connector |

### 기본 실행 구조

```text
브라우저 → JSP → DAO 메서드 → DBConnection → MySQL
                              → ResultSet → DTO → JSP 출력
```

예: 목록 요청 → `list.jsp` → `BoardDAO.selectAll()` → `List<BoardDTO>` → 반복 출력

JSP가 화면+요청을, DAO가 DB를 담당하는 **JSP 중심 MVC 스타일**입니다.

---

## 2. 공통 Java 파일

### 2-1. `DBConnection.java`

```java
Class.forName("com.mysql.cj.jdbc.Driver");
return DriverManager.getConnection(
  "jdbc:mysql://localhost:3306/member_board?...",
  "root", "1234");
```

| 부분 | 의미 |
|------|------|
| `localhost:3306` | 로컬 MySQL |
| `member_board` | DB 이름 |
| `static getConnection()` | `DBConnection.getConnection()`으로 호출 |

연결만 담당하고, SQL은 DAO가 실행합니다.

### 2-2. `MemberDTO.java`

| 필드 | 의미 |
|------|------|
| id, password, name, email | 회원 정보 |
| regDate (Timestamp) | 가입일 |

DTO = Data Transfer Object. 회원 정보를 객체 하나로 DAO에 전달합니다.

### 2-3. `BoardDTO.java`

| DTO 필드 | DB 컬럼 |
|----------|---------|
| id | id |
| title | title |
| content | content |
| writerId | writer_id |
| writerName | writer_name |
| regDate | reg_date |

`writerId` ↔ `writer_id`는 **코드에서 직접 매핑**합니다 (`setWriterId(rs.getString("writer_id"))`).

**수업 포인트:** DTO는 담기, DAO는 가져오기·저장하기.

---

## 3. BoardDAO

| 메서드 | 역할 | 반환 |
|--------|------|------|
| `selectAll()` | 전체 목록 | `List<BoardDTO>` |
| `selectById(id)` | 한 건 | `BoardDTO` / null |
| `insert(b)` | 등록 | int |
| `update(b)` | 수정 | int |
| `delete(id)` | 삭제 | int |

### selectAll

- `ORDER BY id DESC`
- `while (rs.next())` → 여러 행 (한 행만이면 `if`)
- try-with-resources로 Connection 등 자동 close

### selectById

- `WHERE id = ?` + `ps.setInt(1, id)`
- 없으면 `null` → 상세·수정·삭제에서 사용

### insert

`(title, content, writer_id, writer_name)` — 작성자는 **세션**에서 채움  
`executeUpdate()` → 보통 1

### update

`SET title=?, content=? WHERE id=?`  
**WHERE 필수** (없으면 전체 수정 위험)

### delete

`DELETE FROM board WHERE id = ?`

---

## 4. MemberDAO

| 메서드 | 역할 |
|--------|------|
| `login(id, pw)` | 일치 시 MemberDTO, 아니면 null |
| `existsId(id)` | 있으면 true (중복) |
| `insert(m)` | 회원가입 |

로그인: `WHERE id=? AND password=?`  
가입 INSERT에 `reg_date` 없으면 DB DEFAULT로 자동 저장 가능.

---

## 5. 회원 흐름

```text
join.jsp → joinProcess → existsId / insert
login.jsp → loginProcess → login → session 저장 → board/list
logout.jsp → invalidate → login.jsp
```

### joinProcess 요지

1. UTF-8 → 파라미터  
2. 필수값 → `?error=empty`  
3. `existsId` → `?error=dup`  
4. DTO → `insert` → `login.jsp`

### loginProcess 요지

```jsp
MemberDTO member = dao.login(...);
if (member == null) → login.jsp?error=1
session.setAttribute("loginId", ...);
session.setAttribute("loginName", ...);
→ board/list.jsp
```

---

## 6. 게시판 JSP

### list.jsp

`selectAll()` → 표 출력 / 제목 링크 `view.jsp?id=`  
로그인 시 글쓰기 버튼

### view.jsp

`getParameter("id")` → `selectById`  
권한:

```jsp
boolean canEdit = sid != null
    && (sid.equals(b.getWriterId()) || "admin".equals(sid));
```

본인 또는 admin만 수정·삭제 버튼. Process에서도 **다시 검사**.

### write / writeProcess

미로그인 → login으로.  
제목·내용은 폼, **writerId/writerName은 세션**.

### update / updateProcess

로그인 + 작성자/admin 확인 → `update()` → `view.jsp?id=`

### deleteProcess

로그인 + 권한 → `delete()` → `list.jsp`

---

## 7. header / footer / index

- **header:** 세션으로 메뉴 분기, Bootstrap CSS  
- **footer:** `</main>`, Bootstrap JS  
- **include:** `<%@ include file="/include/header.jsp" %>` (정적 include)  
- **index:** 기능 소개 + `.jsp` 링크 (`getContextPath()` 사용)

---

## 8. JSP 문법 요약

| 문법 | 용도 |
|------|------|
| `<%@ page %>` | 인코딩·import |
| `<%@ include %>` | 공통 파일 |
| `<% %>` | Java 코드 |
| `<%= %>` | 출력 |
| `getParameter` | 폼·URL |
| `sendRedirect` | 이동 |
| `session` set/get/invalidate | 로그인 |

---

## 9. 사용자 시나리오 (수업용)

1. 메인 `index.jsp`  
2. 가입: join → joinProcess → MemberDAO  
3. 로그인: login → loginProcess → 세션 → 목록  
4. 목록: `selectAll`  
5. 글쓰기: write → writeProcess → `insert` (세션 작성자)  
6. 상세·수정: `selectById` → update  
7. 삭제: deleteProcess → `delete`  
8. 로그아웃: `invalidate`

---

## 10. 수업에서 강조할 포인트

| 개념 | 확인할 파일 |
|------|-------------|
| JSP+HTML | list, view |
| 파라미터 | joinProcess, writeProcess |
| DTO/DAO 분리 | BoardDTO, BoardDAO |
| JDBC | DBConnection, DAO |
| PreparedStatement / ResultSet | MemberDAO, BoardDAO |
| List + for | selectAll, list.jsp |
| 세션 | loginProcess, header, writeProcess |
| redirect + return | Process JSP |
| CRUD | BoardDAO 5메서드 |
| 작성자 권한 | view, updateProcess, deleteProcess |
| 공통 include | header, footer |

### 네 가지 역할

1. **JSP** — 화면·요청  
2. **DTO** — 데이터 담기  
3. **DAO** — SQL  
4. **DBConnection** — 연결  

이 흐름을 잡으면 이후 Servlet·Spring MVC로 넘어가기 쉽습니다.
