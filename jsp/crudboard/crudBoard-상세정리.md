# crudBoard 상세 정리

**소스 폴더:** `jsp/crudboard`  
**DB 이름:** `crudboard`  
**특징:** 컬럼을 `id · title · content` 만 쓰는 **최소 CRUD** 게시판  
(Bootstrap 5 + MySQL + JSP + DAO)

이 문서는 수업용으로 **구조 · SQL · Java · JSP · 데이터 흐름**을 자세히 정리한 것입니다.

---

## 1. CRUD란?

| 영문 | 의미 | 이 프로젝트 |
|------|------|-------------|
| **C**reate | 생성 | 글쓰기 (`write` → `writeProcess`) |
| **R**ead | 조회 | 목록 (`list`) · 상세 (`view`) |
| **U**pdate | 수정 | 수정 폼 (`update` → `updateProcess`) |
| **D**elete | 삭제 | 삭제 (`deleteProcess`) |

```text
브라우저(JSP 화면)
        ↕
   BoardDAO (SQL 실행)
        ↕
   MySQL board 테이블
```

---

## 2. 전체 폴더 구조

```text
crudboard/                    ← 프로젝트(소스) 폴더
├── db_setup.sql                ← DB · 테이블 · 샘플 데이터
├── README.md
├── src/
│   ├── util/DBConnection.java  ← DB 연결
│   ├── dto/BoardDTO.java       ← 데이터 담는 객체
│   └── dao/BoardDAO.java       ← SQL CRUD 메서드
└── WebContent/
    ├── index.jsp               ← list.jsp 로 이동
    ├── list.jsp                ← 목록 (R)
    ├── write.jsp               ← 글쓰기 폼 (C)
    ├── writeProcess.jsp        ← 등록 처리
    ├── view.jsp                ← 상세 (R)
    ├── update.jsp              ← 수정 폼 (U)
    ├── updateProcess.jsp       ← 수정 처리
    ├── deleteProcess.jsp       ← 삭제 처리 (D)
    └── include/
        ├── header.jsp          ← Bootstrap 상단
        └── footer.jsp          ← 스크립트 하단
```

Eclipse에서는 보통:

- `src` → 프로젝트 **Java Resources / src**
- `WebContent` → **WebContent** (또는 `webapp`)

으로 복사해 Dynamic Web Project로 실행합니다.

---

## 3. 데이터베이스 (SQL)

### 3.1 db_setup.sql 역할

1. DB `crudboard` 생성  
2. `board` 테이블 생성  
3. 샘플 글 2개 INSERT  
4. `SELECT`로 확인  

### 3.2 테이블 설계 (최소)

```sql
CREATE TABLE board (
  id      INT AUTO_INCREMENT PRIMARY KEY,  -- 글 번호 (자동 증가)
  title   VARCHAR(200) NOT NULL,           -- 제목
  content TEXT NOT NULL                    -- 내용
);
```

```sql
INSERT INTO board (title, content) VALUES
('첫 글', '최소 CRUD 예제입니다.'),
('두번째 글', 'id · title · content 만 사용합니다.');
```


| 컬럼 | 타입 | 설명 |
|------|------|------|
| id | INT, PK, AUTO_INCREMENT | 식별자. INSERT 시 생략 |
| title | VARCHAR(200) | 제목 |
| content | TEXT | 긴 본문 |

작성자 · 비밀번호 · 조회수 · 날짜 컬럼은 **의도적으로 없음** (최소 학습용).

### 3.3 실행 방법

MySQL Workbench 또는 콘솔:

```bash
# 예: 콘솔
mysql -u root -p < db_setup.sql
```

또는 Workbench에서 `db_setup.sql` 전체 실행.

### 3.4 샘플 데이터

| id | title | content |
|----|-------|---------|
| 1 | 첫 글 | 최소 CRUD 예제입니다. |
| 2 | 두번째 글 | id · title · content 만 사용합니다. |

---

## 4. Java 계층

### 4.1 DBConnection — DB 연결

```java
package util;

public class DBConnection {
    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/crudboard?...",
            "root",
            "1234");  // ← 본인 MySQL 비밀번호로 변경
    }
}
```

| 항목 | 값 |
|------|-----|
| Driver | `com.mysql.cj.jdbc.Driver` (MySQL 8) |
| URL DB명 | `crudboard` |
| 기본 계정 | root / 1234 (수업 환경에 맞게 수정) |

필요 라이브러리: **mysql-connector-j** → `WEB-INF/lib`

---

### 4.2 BoardDTO — 한 행을 담는 객체

DB 한 줄 ↔ Java 객체 매핑.

```text
board 테이블 행          BoardDTO
─────────────────       ─────────
id          ←→         id
title       ←→         title
content     ←→         content
```

- 필드: `private`
- 접근: `getId` / `setId` … (자바빈즈 규칙)

JSP · DAO 사이에서 **데이터를 옮기는 상자** 역할입니다.

---

### 4.3 BoardDAO — SQL 실행 (CRUD 핵심)

| 메서드 | SQL | 역할 |
|--------|-----|------|
| `selectAll()` | `SELECT ... ORDER BY id DESC` | 목록 |
| `selectById(id)` | `SELECT ... WHERE id = ?` | 한 건 |
| `insert(board)` | `INSERT INTO board (title, content)` | 등록 |
| `update(board)` | `UPDATE ... SET title, content WHERE id` | 수정 |
| `delete(id)` | `DELETE ... WHERE id = ?` | 삭제 |

공통 패턴:

```java
try (Connection conn = DBConnection.getConnection();
     PreparedStatement ps = conn.prepareStatement(sql)) {
    // ? 자리에 값 넣기
    // executeQuery() 또는 executeUpdate()
}
```

- **PreparedStatement** + `?` → SQL Injection 완화  
- **try-with-resources** → Connection / Statement 자동 close  

#### insert 예

```java
String sql = "INSERT INTO board (title, content) VALUES (?, ?)";
ps.setString(1, board.getTitle());
ps.setString(2, board.getContent());
return ps.executeUpdate();  // 영향받은 행 수 (성공 시 1)
```

`id`는 AUTO_INCREMENT라 INSERT에 넣지 않습니다.

#### update 예

```java
String sql = "UPDATE board SET title = ?, content = ? WHERE id = ?";
ps.setString(1, board.getTitle());
ps.setString(2, board.getContent());
ps.setInt(3, board.getId());
```

#### delete 예

```java
String sql = "DELETE FROM board WHERE id = ?";
ps.setInt(1, id);
```

비밀번호 확인 없이 **id만으로 삭제**합니다 (최소 예제).

---

## 5. JSP 화면 · 처리

### 5.1 페이지 역할 한눈에

| 파일 | 구분 | 하는 일 |
|------|------|---------|
| index.jsp | 진입 | `list.jsp`로 redirect |
| list.jsp | 화면 | 전체 글 테이블 |
| write.jsp | 화면 | 제목·내용 입력 폼 |
| writeProcess.jsp | 처리 | INSERT 후 list로 이동 |
| view.jsp | 화면 | 한 글 보기 + 수정/삭제 링크 |
| update.jsp | 화면 | 기존 값 채운 수정 폼 |
| updateProcess.jsp | 처리 | UPDATE 후 view로 이동 |
| deleteProcess.jsp | 처리 | DELETE 후 list로 이동 |
| header / footer | 공통 | Bootstrap · nav |

**화면 JSP** = HTML 중심 + 데이터 표시  
**Process JSP** = 파라미터 받아 DAO 호출 + `sendRedirect` (PRG 패턴)

---

### 5.2 Create — 글쓰기

```text
write.jsp  --POST-->  writeProcess.jsp  -->  list.jsp
   폼 입력              INSERT                 목록 새로고침
```

**write.jsp**

- `method="post"` · `action="writeProcess.jsp"`
- `name="title"`, `name="content"` → 서버 파라미터 이름

**writeProcess.jsp**

```jsp
request.setCharacterEncoding("UTF-8");  // POST 한글
BoardDTO board = new BoardDTO();
board.setTitle(request.getParameter("title"));
board.setContent(request.getParameter("content"));
new BoardDAO().insert(board);
response.sendRedirect("list.jsp");
```

---

### 5.3 Read — 목록 · 상세

**list.jsp**

```jsp
List<BoardDTO> list = new BoardDAO().selectAll();
// for 문으로 id, title 출력
// 제목 링크: view.jsp?id=글번호
```

**view.jsp**

```jsp
int id = Integer.parseInt(request.getParameter("id"));
BoardDTO board = new BoardDAO().selectById(id);
// 없으면 list로 redirect
// title, content 출력
// 수정/삭제/목록 버튼
```

쿼리스트링: `view.jsp?id=1` → `request.getParameter("id")` = `"1"`

---

### 5.4 Update — 수정

```text
view --링크--> update.jsp?id=1 --POST--> updateProcess.jsp --> view.jsp?id=1
                 기존 값 표시              UPDATE
```

**update.jsp**

- hidden: `<input type="hidden" name="id" value="...">`  
  (폼에 id를 다시 실어 보냄)
- title · content에 기존 값 `value` / textarea 본문

**updateProcess.jsp**

```jsp
board.setId(...);
board.setTitle(...);
board.setContent(...);
dao.update(board);
response.sendRedirect("view.jsp?id=" + board.getId());
```

---

### 5.5 Delete — 삭제

```text
view.jsp  --확인 후-->  deleteProcess.jsp?id=1  -->  list.jsp
         confirm()           DELETE
```

```jsp
int id = Integer.parseInt(request.getParameter("id"));
new BoardDAO().delete(id);
response.sendRedirect("list.jsp");
```

`onclick="return confirm('삭제할까요?');"`  
→ 취소하면 링크 이동 안 함.

---

## 6. 전체 화면 흐름도

```text
                    ┌─────────────┐
                    │  index.jsp  │
                    └──────┬──────┘
                           │ redirect
                           ▼
┌──────────┐  글쓰기   ┌────────────┐  등록   ┌────────────────┐
│ list.jsp │ ───────► │ write.jsp  │ ─────► │ writeProcess   │
│  목록    │ ◄─────── │            │        │ → list         │
└────┬─────┘  목록     └────────────┘        └────────────────┘
     │ 제목 클릭
     ▼
┌──────────┐  수정   ┌────────────┐  저장   ┌────────────────┐
│ view.jsp │ ─────► │ update.jsp │ ─────► │ updateProcess  │
│  상세    │ ◄───── │            │        │ → view         │
└────┬─────┘  취소   └────────────┘        └────────────────┘
     │ 삭제(confirm)
     ▼
┌────────────────┐
│ deleteProcess  │ → list
└────────────────┘
```

---

## 7. Bootstrap 사용 포인트

`header.jsp`에 CDN:

```html
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
```

| 클래스 | 쓰인 곳 |
|--------|---------|
| `navbar` | 상단 메뉴 |
| `table table-bordered` | 목록 |
| `form-control` | 입력창 |
| `btn btn-primary` 등 | 버튼 |
| `card card-body` | 폼·본문 박스 |

디자인보다 **CRUD 동작**이 학습 목표입니다.

---

## 8. Eclipse 실행 체크리스트

1. [ ] MySQL 실행, `db_setup.sql` 성공  
2. [ ] Dynamic Web Project 생성 (이름 예: `crudboard`)  
3. [ ] `util` · `dto` · `dao` 패키지 복사  
4. [ ] WebContent JSP · include 복사  
5. [ ] `mysql-connector-j-*.jar` → `WEB-INF/lib`  
6. [ ] `DBConnection` 비밀번호 수정  
7. [ ] Tomcat에 프로젝트 Add 후 서버 Start  
8. [ ] 브라우저: `http://localhost:8080/프로젝트명/list.jsp`  

### 자주 나는 오류

| 증상 | 원인 · 조치 |
|------|-------------|
| ClassNotFoundException Driver | connector jar 누락 |
| Access denied | root 비밀번호 불일치 |
| Unknown database | `db_setup.sql` 미실행 |
| 한글 깨짐 | Process에 `setCharacterEncoding("UTF-8")` |
| 404 | 경로·프로젝트명·파일명 확인 |

---

## 9. 학습 순서 (수업 추천)

```text
① SQL 테이블 이해 (id, title, content)
② BoardDTO · DBConnection
③ BoardDAO.selectAll / list.jsp     ← Read 목록
④ selectById / view.jsp             ← Read 상세
⑤ insert / write · writeProcess     ← Create
⑥ update / update · updateProcess   ← Update
⑦ delete / deleteProcess            ← Delete
⑧ 전체 흐름 한 바퀴 직접 클릭
```

---

## 10. crud-board 와 비교

| | **crudboard** (이 프로젝트) | crud-board |
|--|------------------------------|------------|
| 컬럼 | id, title, content | + writer, password, hit, 날짜 |
| 삭제 | id만으로 삭제 | 비밀번호 확인 |
| 조회수 | 없음 | 있음 |
| 목적 | **최소 CRUD 입문** | 실무에 가까운 게시판 |

입문은 **crudboard** → 익숙해지면 `crud-board`로 확장.

---

## 11. 핵심 SQL ↔ 메서드 암기

```text
목록   SELECT * FROM board ORDER BY id DESC     → selectAll()
상세   SELECT * FROM board WHERE id = ?         → selectById()
등록   INSERT INTO board (title, content) ...   → insert()
수정   UPDATE board SET title=?, content=? ...  → update()
삭제   DELETE FROM board WHERE id = ?           → delete()
```

---

## 12. 학생용 한 줄 요약

```text
화면(JSP)이 파라미터를 받고
DAO가 SQL을 실행하며
결과는 다시 JSP로 보여 준다.

최소 게시판 = id + title + content + CRUD 다섯 메서드
```

---

## 관련 경로

| 경로 | 설명 |
|------|------|
| `jsp/crudboard/` | 이 문서의 소스 |
| `jsp/crudboard/db_setup.sql` | MySQL 스크립트 (DB명 `crudboard`) |
| `jsp/crudboard/crudBoard-문제답.md` | 객관식·서술·실습 문제와 답 |
| `jsp/crud-board/` | 확장판 (작성자·비밀번호 등) |
