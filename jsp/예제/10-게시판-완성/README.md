# 📚 JSP 게시판 시스템 (완성 버전)

## 📋 목차

1. [프로젝트 개요](#프로젝트-개요)
2. [데이터베이스 설정](#데이터베이스-설정)
3. [프로젝트 구조](#프로젝트-구조)
4. [코드 구현](#코드-구현)
5. [실행 순서](#실행-순서)
6. [주요 기능](#주요-기능)

---

## 🎯 프로젝트 개요

JSP를 사용한 완전한 게시판 시스템입니다. CRUD 기능을 포함합니다.

### 주요 기능
- ✅ 게시글 작성 (Create)
- ✅ 게시글 목록 조회 (Read)
- ✅ 게시글 상세 보기 (Read)
- ✅ 게시글 수정 (Update)
- ✅ 게시글 삭제 (Delete)

---

## 🗄️ 데이터베이스 설정

### 1️⃣ DB 테이블 생성

```sql
CREATE TABLE board (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(200),
    content TEXT,
    writer VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### 테이블 구조 설명
- `id`: 게시글 고유 번호 (자동 증가, 기본키)
- `title`: 게시글 제목
- `content`: 게시글 내용 (TEXT 타입)
- `writer`: 작성자 이름
- `created_at`: 작성일시 (자동 설정)

---

## 📁 프로젝트 구조

### 📌 프로젝트 구조

```
JSPBoard
 ├─ src
 │   ├─ dao
 │   │    └─ BoardDAO.java          # 데이터 접근 객체
 │   ├─ dto
 │   │    └─ BoardDTO.java          # 데이터 전송 객체
 │   └─ util
 │        └─ DBUtil.java            # 데이터베이스 연결 유틸리티
 │
 ├─ webapp
 │   ├─ boardList.jsp               # 게시글 목록
 │   ├─ boardWrite.jsp              # 글쓰기 폼
 │   ├─ boardWriteProcess.jsp       # 글쓰기 처리
 │   ├─ boardView.jsp               # 게시글 상세 보기
 │   ├─ boardDelete.jsp              # 게시글 삭제
 │   ├─ boardEdit.jsp                # 게시글 수정 폼
 │   └─ boardUpdate.jsp              # 게시글 수정 처리
```

---

## 💻 코드 구현

### ✅ 1. DBUtil (데이터베이스 연결 유틸리티)

**📌 DBUtil.java**

```java
package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {

    public static Connection getConnection() throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver");

        return DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/testdb",
            "root",
            "1234"
        );
    }
}
```

**설명:**
- 데이터베이스 연결을 위한 유틸리티 클래스
- `getConnection()`: MySQL 데이터베이스 연결을 반환하는 정적 메서드
- **데이터베이스명**: `testdb` (프로젝트에 맞게 수정 가능)
- **JDBC 드라이버**: `com.mysql.cj.jdbc.Driver` (MySQL 8.0 이상)
- 모든 DAO 클래스에서 이 메서드를 사용하여 데이터베이스 연결

**사용 예시:**
```java
Connection conn = DBUtil.getConnection();
// 데이터베이스 작업 수행
conn.close();
```

---

### ✅ 2. BoardDTO (데이터 전송 객체)

**📌 BoardDTO.java**

```java
package dto;

public class BoardDTO {

    private int id;
    private String title;
    private String content;
    private String writer;
    private String created_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
```

**설명:**
- 게시글 정보를 담는 데이터 전송 객체
- 모든 필드에 대한 Getter/Setter 메서드 포함
- `id`: 게시글 번호
- `title`: 제목
- `content`: 내용
- `writer`: 작성자
- `created_at`: 작성일시

---

### ✅ 3. BoardDAO (데이터 접근 객체)

**📌 BoardDAO.java**

```java
package dao;

import java.sql.*;
import java.util.*;

import dto.BoardDTO;
import util.DBUtil;

public class BoardDAO {

    // 글 작성
    public void insert(BoardDTO dto) throws Exception {

        Connection conn = DBUtil.getConnection();

        String sql = "INSERT INTO board(title,content,writer) VALUES(?,?,?)";

        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, dto.getTitle());
        pstmt.setString(2, dto.getContent());
        pstmt.setString(3, dto.getWriter());

        pstmt.executeUpdate();

        conn.close();
    }

    // 전체 글 목록
    public List<BoardDTO> selectAll() throws Exception {

        Connection conn = DBUtil.getConnection();

        List<BoardDTO> list = new ArrayList<>();

        String sql = "SELECT * FROM board ORDER BY id DESC";

        PreparedStatement pstmt = conn.prepareStatement(sql);

        ResultSet rs = pstmt.executeQuery();

        while(rs.next()) {

            BoardDTO dto = new BoardDTO();

            dto.setId(rs.getInt("id"));
            dto.setTitle(rs.getString("title"));
            dto.setWriter(rs.getString("writer"));
            dto.setCreated_at(rs.getString("created_at"));

            list.add(dto);
        }

        conn.close();

        return list;
    }

    // 글 상세보기
    public BoardDTO selectOne(int id) throws Exception {

        Connection conn = DBUtil.getConnection();

        String sql = "SELECT * FROM board WHERE id=?";

        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setInt(1, id);

        ResultSet rs = pstmt.executeQuery();

        if(rs.next()) {

            BoardDTO dto = new BoardDTO();

            dto.setId(rs.getInt("id"));
            dto.setTitle(rs.getString("title"));
            dto.setContent(rs.getString("content"));
            dto.setWriter(rs.getString("writer"));
            dto.setCreated_at(rs.getString("created_at"));

            return dto;
        }

        conn.close();

        return null;
    }

    // 삭제
    public void delete(int id) throws Exception {

        Connection conn = DBUtil.getConnection();

        String sql = "DELETE FROM board WHERE id=?";

        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setInt(1, id);

        pstmt.executeUpdate();

        conn.close();
    }

    // 수정
    public void update(BoardDTO dto) throws Exception {

        Connection conn = DBUtil.getConnection();

        String sql = "UPDATE board SET title=?,content=? WHERE id=?";

        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, dto.getTitle());
        pstmt.setString(2, dto.getContent());
        pstmt.setInt(3, dto.getId());

        pstmt.executeUpdate();

        conn.close();
    }
}
```

**설명:**
- `insert()`: 게시글 작성 - 새 게시글을 데이터베이스에 저장
- `selectAll()`: 전체 조회 - 모든 게시글 목록을 최신순으로 반환 (`ORDER BY id DESC`)
- `selectOne()`: 상세 보기 - ID로 특정 게시글 정보 조회
- `delete()`: 삭제 - ID로 게시글 삭제
- `update()`: 수정 - 게시글 제목과 내용 수정

---

### ✅ 4. 게시글 목록

**📌 boardList.jsp**

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.BoardDAO,dto.BoardDTO,java.util.*" %>

<%
    BoardDAO dao = new BoardDAO();
    List<BoardDTO> list = dao.selectAll();
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시판</title>
</head>
<body>
    <h2>게시판</h2>

    <a href="boardWrite.jsp">글쓰기</a>

    <table border="1">
        <tr>
            <th>ID</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
        </tr>

        <% for(BoardDTO dto : list) { %>
        <tr>
            <td><%=dto.getId()%></td>
            <td>
                <a href="boardView.jsp?id=<%=dto.getId()%>">
                    <%=dto.getTitle()%>
                </a>
            </td>
            <td><%=dto.getWriter()%></td>
            <td><%=dto.getCreated_at()%></td>
        </tr>
        <% } %>
    </table>
</body>
</html>
```

**설명:**
- 전체 게시글 목록을 테이블로 표시
- 제목 클릭 시 상세 보기 페이지로 이동
- 최신 게시글이 위에 표시됨 (`ORDER BY id DESC`)
- "글쓰기" 링크로 새 게시글 작성 가능

---

### ✅ 5. 글쓰기 페이지

**📌 boardWrite.jsp**

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>글쓰기</title>
</head>
<body>
    <h2>글쓰기</h2>

    <form action="boardWriteProcess.jsp" method="post">
        제목
        <input type="text" name="title"><br>

        내용
        <textarea name="content"></textarea><br>

        작성자
        <input type="text" name="writer"><br>

        <button>등록</button>
    </form>
</body>
</html>
```

**📌 boardWriteProcess.jsp**

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.BoardDAO,dto.BoardDTO" %>

<%
    request.setCharacterEncoding("UTF-8");

    String title = request.getParameter("title");
    String content = request.getParameter("content");
    String writer = request.getParameter("writer");

    BoardDTO dto = new BoardDTO();

    dto.setTitle(title);
    dto.setContent(content);
    dto.setWriter(writer);

    BoardDAO dao = new BoardDAO();

    dao.insert(dto);

    response.sendRedirect("boardList.jsp");
%>
```

**설명:**
- `boardWrite.jsp`: 게시글 작성 입력 폼
- `boardWriteProcess.jsp`: 
  - 폼 데이터를 받아 DTO 객체 생성
  - DAO를 통해 데이터베이스에 저장
  - 게시글 목록 페이지로 리다이렉트
  - **중요**: `request.setCharacterEncoding("UTF-8")`로 한글 인코딩 처리

---

### ✅ 6. 게시글 상세 보기

**📌 boardView.jsp**

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.BoardDAO,dto.BoardDTO" %>

<%
    int id = Integer.parseInt(request.getParameter("id"));

    BoardDAO dao = new BoardDAO();

    BoardDTO dto = dao.selectOne(id);
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 보기</title>
</head>
<body>
    <h2>게시글 보기</h2>

    제목 : <%=dto.getTitle()%><br>
    작성자 : <%=dto.getWriter()%><br>
    작성일 : <%=dto.getCreated_at()%><br>
    내용 : <%=dto.getContent()%><br>

    <br>

    <a href="boardEdit.jsp?id=<%=dto.getId()%>">수정</a>
    <a href="boardDelete.jsp?id=<%=dto.getId()%>">삭제</a>
    <a href="boardList.jsp">목록</a>
</body>
</html>
```

**설명:**
- URL 파라미터로 받은 ID로 게시글 조회
- 게시글의 모든 정보를 표시
- 수정/삭제/목록 링크 제공

---

### ✅ 7. 게시글 삭제

**📌 boardDelete.jsp**

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.BoardDAO" %>

<%
    int id = Integer.parseInt(request.getParameter("id"));

    BoardDAO dao = new BoardDAO();

    dao.delete(id);

    response.sendRedirect("boardList.jsp");
%>
```

**설명:**
- ID를 받아 해당 게시글을 데이터베이스에서 삭제
- 게시글 목록 페이지로 리다이렉트
- 삭제 확인 없이 바로 삭제됨 (실무에서는 확인 기능 추가 권장)

---

### ✅ 8. 게시글 수정

**📌 boardEdit.jsp**

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.BoardDAO,dto.BoardDTO" %>

<%
    int id = Integer.parseInt(request.getParameter("id"));

    BoardDAO dao = new BoardDAO();

    BoardDTO dto = dao.selectOne(id);
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 수정</title>
</head>
<body>
    <h2>게시글 수정</h2>

    <form action="boardUpdate.jsp" method="post">
        <input type="hidden" name="id" value="<%=dto.getId()%>">

        제목
        <input type="text" name="title" value="<%=dto.getTitle()%>"><br>

        내용
        <textarea name="content"><%=dto.getContent()%></textarea><br>

        <button>수정</button>
    </form>
</body>
</html>
```

**📌 boardUpdate.jsp**

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.BoardDAO,dto.BoardDTO" %>

<%
    request.setCharacterEncoding("UTF-8");

    int id = Integer.parseInt(request.getParameter("id"));
    String title = request.getParameter("title");
    String content = request.getParameter("content");

    BoardDTO dto = new BoardDTO();

    dto.setId(id);
    dto.setTitle(title);
    dto.setContent(content);

    BoardDAO dao = new BoardDAO();

    dao.update(dto);

    response.sendRedirect("boardList.jsp");
%>
```

**설명:**
- `boardEdit.jsp`: 
  - ID로 게시글 정보 조회
  - 수정 폼에 기존 정보 표시
  - 작성자는 수정 불가 (작성자 필드 없음)
- `boardUpdate.jsp`: 
  - 수정된 제목과 내용을 데이터베이스에 업데이트
  - 게시글 목록 페이지로 리다이렉트
  - **중요**: `request.setCharacterEncoding("UTF-8")`로 한글 인코딩 처리

---

## 🚀 실행 순서

### 1. 데이터베이스 설정
```sql
CREATE DATABASE IF NOT EXISTS testdb DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE testdb;

CREATE TABLE board (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(200),
    content TEXT,
    writer VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### 2. 프로젝트 설정
- MySQL JDBC 드라이버를 프로젝트에 추가
- `DBUtil.java`의 연결 정보 수정:
  - 데이터베이스명: `testdb` (또는 프로젝트에 맞게 변경)
  - 사용자명: `root` (또는 본인의 MySQL 사용자명)
  - 비밀번호: `1234` (또는 본인의 MySQL 비밀번호)

### 3. 실행 흐름
1. **게시글 목록**: `boardList.jsp` - 전체 게시글 확인
2. **글쓰기**: `boardWrite.jsp` → `boardWriteProcess.jsp` → `boardList.jsp`
3. **상세 보기**: `boardView.jsp` - 제목 클릭 시 이동
4. **수정**: `boardEdit.jsp` → `boardUpdate.jsp` → `boardList.jsp`
5. **삭제**: `boardDelete.jsp` → `boardList.jsp`

---

## 📝 주요 기능 설명

### 1. CRUD 기능
- **Create**: 게시글 작성 (`insert`)
- **Read**: 게시글 목록 조회, 상세 보기 (`selectAll`, `selectOne`)
- **Update**: 게시글 수정 (`update`)
- **Delete**: 게시글 삭제 (`delete`)

### 2. 데이터 정렬
- 게시글 목록은 최신순으로 정렬 (`ORDER BY id DESC`)
- 가장 최근에 작성한 게시글이 맨 위에 표시

### 3. 한글 인코딩
- 폼 데이터를 받는 JSP 파일에서 `request.setCharacterEncoding("UTF-8")` 설정
- 한글 입력 시 깨짐 방지

### 4. 페이지 이동
- 모든 작업 후 적절한 페이지로 리다이렉트
- `response.sendRedirect()` 사용

---

## ⚠️ 주의사항

### 1. 보안 개선 필요
- 작성자 인증 (로그인한 사용자만 작성 가능)
- 수정/삭제 권한 체크 (작성자만 수정/삭제 가능)
- XSS 방지를 위한 입력값 검증 및 이스케이프 처리

### 2. 에러 처리
- try-catch 블록으로 예외 처리
- 존재하지 않는 게시글 ID 접근 시 처리
- 데이터베이스 연결 실패 시 처리

### 3. 기능 개선
- 페이징 처리 (게시글이 많을 경우)
- 검색 기능 (제목, 내용, 작성자로 검색)
- 조회수 기능
- 댓글 기능
- 파일 첨부 기능

---

## 🔍 코드 분석

### DTO 패턴
- 데이터 전송 객체로 데이터 캡슐화
- Getter/Setter로 데이터 접근 제어
- 비즈니스 로직과 데이터 구조 분리

### DAO 패턴
- 데이터 접근 로직을 별도 클래스로 분리
- 재사용성과 유지보수성 향상
- DBUtil을 사용하여 데이터베이스 연결

### PreparedStatement
- SQL Injection 공격 방지
- 파라미터를 `?`로 설정하여 안전하게 처리
- 성능 향상 (컴파일된 SQL 재사용)

### ORDER BY
- `ORDER BY id DESC`: ID를 내림차순으로 정렬
- 최신 게시글이 먼저 표시됨

---

## 📚 학습 포인트

1. **JSP 기본 문법**
   - 스크립틀릿 (`<% %>`)
   - 표현식 (`<%= %>`)
   - 지시어 (`<%@ %>`)

2. **JDBC 사용**
   - Connection, PreparedStatement, ResultSet
   - 데이터베이스 CRUD 작업

3. **한글 인코딩**
   - `request.setCharacterEncoding("UTF-8")`
   - 폼 데이터 전송 시 필수

4. **MVC 패턴**
   - Model: DTO, DAO
   - View: JSP 파일
   - Controller: JSP Process 파일

5. **SQL 쿼리**
   - INSERT: 데이터 삽입
   - SELECT: 데이터 조회
   - UPDATE: 데이터 수정
   - DELETE: 데이터 삭제
   - ORDER BY: 정렬

---

## 💡 실무 개선 사항

### 1. 페이징 처리
```java
// BoardDAO에 추가
public List<BoardDTO> selectAll(int page, int pageSize) throws Exception {
    // LIMIT 사용하여 페이징
    String sql = "SELECT * FROM board ORDER BY id DESC LIMIT ?, ?";
    // ...
}
```

### 2. 검색 기능
```java
// BoardDAO에 추가
public List<BoardDTO> search(String keyword) throws Exception {
    String sql = "SELECT * FROM board WHERE title LIKE ? OR content LIKE ?";
    // ...
}
```

### 3. 조회수 증가
```sql
ALTER TABLE board ADD COLUMN view_count INT DEFAULT 0;
```

### 4. 비밀번호 기능
```sql
ALTER TABLE board ADD COLUMN password VARCHAR(50);
```

---

**이 예제는 JSP와 JDBC를 사용한 완전한 게시판 시스템입니다! 💪**
