# 📚 JSP 회원관리 시스템 (완성 버전)

## 📋 목차

1. [프로젝트 개요](#프로젝트-개요)
2. [데이터베이스 설정](#데이터베이스-설정)
3. [프로젝트 구조](#프로젝트-구조)
4. [코드 구현](#코드-구현)
5. [실행 순서](#실행-순서)
6. [주요 기능](#주요-기능)

---

## 🎯 프로젝트 개요

JSP를 사용한 완전한 회원관리 시스템입니다. CRUD 기능과 로그인/로그아웃 기능을 포함합니다.

### 주요 기능
- ✅ 회원가입 (Create)
- ✅ 로그인 / 로그아웃
- ✅ 회원 목록 조회 (Read)
- ✅ 회원 정보 수정 (Update)
- ✅ 회원 삭제 (Delete)
- ✅ 1명 조회 기능
- ✅ 세션 관리

---

## 🗄️ 데이터베이스 설정

### 1️⃣ 데이터베이스 및 테이블 생성

```sql
-- 데이터베이스 생성
CREATE DATABASE IF NOT EXISTS jsp_exem_db DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE jsp_exem_db;

-- 회원 테이블 생성
CREATE TABLE member (
    id INT AUTO_INCREMENT PRIMARY KEY,
    userid VARCHAR(50) UNIQUE,
    password VARCHAR(50),
    username VARCHAR(50)
);
```

### 테이블 구조 설명
- `id`: 회원 고유 번호 (자동 증가, 기본키)
- `userid`: 사용자 아이디 (중복 불가, UNIQUE)
- `password`: 비밀번호
- `username`: 사용자 이름

---

## 📁 프로젝트 구조

### 📌 실무 스타일 프로젝트 구조

```
src
 ├─ dao
 │   └─ MemberDAO.java          # 데이터 접근 객체
 ├─ dto
 │   └─ MemberDTO.java           # 데이터 전송 객체
 └─ util
     └─ DBUtil.java              # 데이터베이스 연결 유틸리티

webapp
 ├─ login.jsp                    # 로그인 폼
 ├─ loginProcess.jsp             # 로그인 처리
 ├─ logout.jsp                   # 로그아웃
 ├─ join.jsp                     # 회원가입 폼
 ├─ joinProcess.jsp              # 회원가입 처리
 ├─ memberList.jsp               # 회원 목록
 ├─ memberEdit.jsp               # 회원 수정 폼
 ├─ memberUpdate.jsp             # 회원 수정 처리
 └─ memberDelete.jsp             # 회원 삭제
```

---

## 💻 코드 구현

### ✅ 1. MemberDTO (데이터 전송 객체)

**📌 MemberDTO.java**

```java
package dto;

public class MemberDTO {

    private int id;
    private String userid;
    private String password;
    private String username;

    public MemberDTO() {}

    public MemberDTO(String userid, String password, String username) {
        this.userid = userid;
        this.password = password;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
```

**설명:**
- 회원 정보를 담는 데이터 전송 객체
- 기본 생성자와 파라미터 생성자 제공
- 모든 필드에 대한 Getter/Setter 메서드 포함

---

### ✅ 2. DBUtil (데이터베이스 연결 유틸리티)

**📌 DBUtil.java**

```java
package util;

import java.sql.*;

public class DBUtil {
    public static Connection getConnection() throws Exception {
        String url = "jdbc:mysql://localhost:3306/jsp_exem_db?serverTimezone=Asia/Seoul";
        String user = "root";
        String password = "1234";
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, password);
    }
}
```

**설명:**
- 데이터베이스 연결을 위한 유틸리티 클래스
- `getConnection()`: MySQL 데이터베이스 연결을 반환하는 정적 메서드
- **데이터베이스명**: `jsp_exem_db` (프로젝트에 맞게 수정 가능)
- **서버 타임존**: `Asia/Seoul` (한국 시간대 설정)
- **JDBC 드라이버**: `com.mysql.cj.jdbc.Driver` (MySQL 8.0 이상)
- 모든 DAO 클래스에서 이 메서드를 사용하여 데이터베이스 연결

**사용 예시:**
```java
Connection conn = DBUtil.getConnection();
// 데이터베이스 작업 수행
conn.close();
```

---

### ✅ 3. MemberDAO (데이터 접근 객체)

**📌 MemberDAO.java**

```java
package dao;

import java.sql.*;
import java.util.*;

import dto.MemberDTO;
import util.DBUtil;

public class MemberDAO {

    // 회원가입
    public void insert(MemberDTO dto) throws Exception {
        Connection conn = DBUtil.getConnection();

        String sql = "insert into member(userid,password,username) values (?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, dto.getUserid());
        pstmt.setString(2, dto.getPassword());
        pstmt.setString(3, dto.getUsername());

        pstmt.executeUpdate();
        conn.close();
    }

    // 로그인
    public MemberDTO login(String userid, String password) throws Exception {

        Connection conn = DBUtil.getConnection();

        String sql = "select * from member where userid=? and password=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, userid);
        pstmt.setString(2, password);

        ResultSet rs = pstmt.executeQuery();

        if(rs.next()) {
            MemberDTO dto = new MemberDTO();
            dto.setId(rs.getInt("id"));
            dto.setUserid(rs.getString("userid"));
            dto.setUsername(rs.getString("username"));
            return dto;
        }

        conn.close();
        return null;
    }

    // 전체조회
    public List<MemberDTO> selectAll() throws Exception {

        Connection conn = DBUtil.getConnection();

        List<MemberDTO> list = new ArrayList<>();

        String sql = "select * from member";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        ResultSet rs = pstmt.executeQuery();

        while(rs.next()) {
            MemberDTO dto = new MemberDTO();

            dto.setId(rs.getInt("id"));
            dto.setUserid(rs.getString("userid"));
            dto.setUsername(rs.getString("username"));

            list.add(dto);
        }

        conn.close();
        return list;
    }

    // 1명 조회
    public MemberDTO selectOne(int id) throws Exception {

        Connection conn = DBUtil.getConnection();

        String sql = "select * from member where id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setInt(1, id);

        ResultSet rs = pstmt.executeQuery();

        MemberDTO dto = null;

        if(rs.next()) {
            dto = new MemberDTO();
            dto.setId(rs.getInt("id"));
            dto.setUserid(rs.getString("userid"));
            dto.setUsername(rs.getString("username"));
        }

        conn.close();
        return dto;
    }

    // 수정
    public void update(MemberDTO dto) throws Exception {

        Connection conn = DBUtil.getConnection();

        String sql = "update member set username=? where id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, dto.getUsername());
        pstmt.setInt(2, dto.getId());

        pstmt.executeUpdate();

        conn.close();
    }

    // 삭제
    public void delete(int id) throws Exception {

        Connection conn = DBUtil.getConnection();

        String sql = "delete from member where id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setInt(1, id);

        pstmt.executeUpdate();

        conn.close();
    }
}
```

**설명:**
- `insert()`: 회원가입 - 새 회원 정보를 데이터베이스에 저장
- `login()`: 로그인 - 아이디와 비밀번호로 회원 검증
- `selectAll()`: 전체 조회 - 모든 회원 목록 반환
- `selectOne()`: 1명 조회 - ID로 특정 회원 정보 조회
- `update()`: 수정 - 회원 이름 수정
- `delete()`: 삭제 - ID로 회원 삭제

---

### ✅ 4. 로그인 페이지

**📌 login.jsp**

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>로그인</title>
</head>
<body>
    <h2>로그인</h2>

    <form action="loginProcess.jsp" method="post">
        아이디 : <input type="text" name="userid"><br>
        비밀번호 : <input type="password" name="password"><br>
        <button type="submit">로그인</button>
    </form>

    <a href="join.jsp">회원가입</a>
</body>
</html>
```

**📌 loginProcess.jsp**

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.MemberDAO,dto.MemberDTO"%>

<%
    String userid = request.getParameter("userid");
    String password = request.getParameter("password");

    MemberDAO dao = new MemberDAO();
    MemberDTO dto = dao.login(userid, password);

    if(dto != null) {
        session.setAttribute("loginUser", dto);
        response.sendRedirect("memberList.jsp");
    } else {
        out.println("로그인 실패");
    }
%>
```

**설명:**
- `login.jsp`: 로그인 입력 폼
- `loginProcess.jsp`: 
  - 아이디/비밀번호 검증
  - 로그인 성공 시 세션에 회원 정보 저장
  - 회원 목록 페이지로 리다이렉트
  - 로그인 실패 시 에러 메시지 표시

---

### ✅ 5. 로그아웃

**📌 logout.jsp**

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    session.invalidate();
    response.sendRedirect("login.jsp");
%>
```

**설명:**
- 세션을 무효화하여 로그아웃 처리
- 로그인 페이지로 리다이렉트

---

### ✅ 6. 회원가입

**📌 join.jsp**

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원가입</title>
</head>
<body>
    <h2>회원가입</h2>

    <form action="joinProcess.jsp" method="post">
        아이디 : <input type="text" name="userid"><br>
        비밀번호 : <input type="password" name="password"><br>
        이름 : <input type="text" name="username"><br>
        <button type="submit">가입</button>
    </form>
</body>
</html>
```

**📌 joinProcess.jsp**

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.MemberDAO,dto.MemberDTO"%>

<%
    String userid = request.getParameter("userid");
    String password = request.getParameter("password");
    String username = request.getParameter("username");

    MemberDTO dto = new MemberDTO(userid, password, username);

    MemberDAO dao = new MemberDAO();
    dao.insert(dto);

    response.sendRedirect("login.jsp");
%>
```

**설명:**
- `join.jsp`: 회원가입 입력 폼
- `joinProcess.jsp`: 
  - 폼 데이터를 받아 DTO 객체 생성
  - DAO를 통해 데이터베이스에 저장
  - 로그인 페이지로 리다이렉트

---

### ✅ 7. 회원 목록

**📌 memberList.jsp**

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.MemberDAO,dto.MemberDTO,java.util.*"%>

<%
    MemberDTO loginUser = (MemberDTO)session.getAttribute("loginUser");

    if(loginUser == null) {
        response.sendRedirect("login.jsp");
    }

    MemberDAO dao = new MemberDAO();
    List<MemberDTO> list = dao.selectAll();
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원목록</title>
</head>
<body>
    <h2><%=loginUser.getUserid()%>님 환영합니다</h2>

    <a href="logout.jsp">로그아웃</a>

    <table border="1">
        <tr>
            <th>ID</th>
            <th>아이디</th>
            <th>이름</th>
            <th>수정</th>
            <th>삭제</th>
        </tr>

        <% for(MemberDTO dto : list) { %>
        <tr>
            <td><%=dto.getId()%></td>
            <td><%=dto.getUserid()%></td>
            <td><%=dto.getUsername()%></td>
            <td>
                <a href="memberEdit.jsp?id=<%=dto.getId()%>">수정</a>
            </td>
            <td>
                <a href="memberDelete.jsp?id=<%=dto.getId()%>">삭제</a>
            </td>
        </tr>
        <% } %>
    </table>
</body>
</html>
```

**설명:**
- 세션에서 로그인 사용자 정보 확인 (로그인 체크)
- 로그인하지 않은 경우 로그인 페이지로 리다이렉트
- 전체 회원 목록을 테이블로 표시
- 각 회원의 수정/삭제 링크 제공

---

### ✅ 8. 회원 수정

**📌 memberEdit.jsp**

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.MemberDAO,dto.MemberDTO"%>

<%
    int id = Integer.parseInt(request.getParameter("id"));

    MemberDAO dao = new MemberDAO();
    MemberDTO dto = dao.selectOne(id);
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원 수정</title>
</head>
<body>
    <h2>회원 수정</h2>

    <form action="memberUpdate.jsp" method="post">
        <input type="hidden" name="id" value="<%=dto.getId()%>">

        아이디 : <%=dto.getUserid()%><br>

        이름 :
        <input type="text" name="username" value="<%=dto.getUsername()%>">

        <button type="submit">수정</button>
    </form>
</body>
</html>
```

**📌 memberUpdate.jsp**

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.MemberDAO,dto.MemberDTO"%>

<%
    int id = Integer.parseInt(request.getParameter("id"));
    String username = request.getParameter("username");

    MemberDTO dto = new MemberDTO();
    dto.setId(id);
    dto.setUsername(username);

    MemberDAO dao = new MemberDAO();
    dao.update(dto);

    response.sendRedirect("memberList.jsp");
%>
```

**설명:**
- `memberEdit.jsp`: 
  - ID로 회원 정보 조회
  - 수정 폼에 기존 정보 표시
  - 아이디는 읽기 전용으로 표시
- `memberUpdate.jsp`: 
  - 수정된 이름을 데이터베이스에 업데이트
  - 회원 목록 페이지로 리다이렉트

---

### ✅ 9. 회원 삭제

**📌 memberDelete.jsp**

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.MemberDAO"%>

<%
    int id = Integer.parseInt(request.getParameter("id"));

    MemberDAO dao = new MemberDAO();
    dao.delete(id);

    response.sendRedirect("memberList.jsp");
%>
```

**설명:**
- ID를 받아 해당 회원을 데이터베이스에서 삭제
- 회원 목록 페이지로 리다이렉트

---

## 🚀 실행 순서

### 1. 데이터베이스 설정
```sql
-- 데이터베이스 생성
CREATE DATABASE IF NOT EXISTS jsp_exem_db DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE jsp_exem_db;

-- 회원 테이블 생성
CREATE TABLE member (
    id INT AUTO_INCREMENT PRIMARY KEY,
    userid VARCHAR(50) UNIQUE,
    password VARCHAR(50),
    username VARCHAR(50)
);
```

### 2. 프로젝트 설정
- MySQL JDBC 드라이버를 프로젝트에 추가
- `DBUtil.java`의 연결 정보 수정:
  - 데이터베이스명: `jsp_exem_db` (또는 프로젝트에 맞게 변경)
  - 사용자명: `root` (또는 본인의 MySQL 사용자명)
  - 비밀번호: `1234` (또는 본인의 MySQL 비밀번호)

### 3. 실행 흐름
1. **회원가입**: `join.jsp` → `joinProcess.jsp` → `login.jsp`
2. **로그인**: `login.jsp` → `loginProcess.jsp` → `memberList.jsp`
3. **회원 목록**: `memberList.jsp` (로그인 체크 포함)
4. **회원 수정**: `memberEdit.jsp` → `memberUpdate.jsp` → `memberList.jsp`
5. **회원 삭제**: `memberDelete.jsp` → `memberList.jsp`
6. **로그아웃**: `logout.jsp` → `login.jsp`

---

## 📝 주요 기능 설명

### 1. 데이터베이스 연결
- `DBUtil.getConnection()`: 모든 DAO에서 공통으로 사용
- 연결 정보를 한 곳에서 관리하여 유지보수 용이
- 데이터베이스 변경 시 `DBUtil.java`만 수정하면 됨

### 2. 세션 관리
- 로그인 성공 시 세션에 회원 정보 저장
- `memberList.jsp`에서 로그인 체크
- 로그아웃 시 세션 무효화

### 3. CRUD 기능
- **Create**: 회원가입 (`insert`)
- **Read**: 로그인, 전체 조회, 1명 조회 (`login`, `selectAll`, `selectOne`)
- **Update**: 회원 정보 수정 (`update`)
- **Delete**: 회원 삭제 (`delete`)

### 4. 보안
- PreparedStatement 사용으로 SQL Injection 방지
- 세션을 통한 로그인 상태 관리
- 비밀번호는 평문 저장 (실무에서는 암호화 필요)

---

## ⚠️ 주의사항

### 1. 보안 개선 필요
- 비밀번호 암호화 (SHA-256, BCrypt 등)
- 세션 타임아웃 설정
- XSS 방지를 위한 입력값 검증

### 2. 에러 처리
- try-catch 블록으로 예외 처리
- 사용자에게 적절한 에러 메시지 표시
- 데이터베이스 연결 실패 시 처리

### 3. 기능 개선
- 아이디 중복 체크
- 비밀번호 확인 기능
- 회원 정보 상세 조회 페이지
- 페이징 처리 (회원이 많을 경우)

---

## 🔍 코드 분석

### DBUtil 패턴
- 데이터베이스 연결 로직을 유틸리티 클래스로 분리
- 모든 DAO에서 공통으로 사용하여 코드 중복 제거
- 연결 정보를 한 곳에서 관리하여 유지보수 용이

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

---

## 📚 학습 포인트

1. **JSP 기본 문법**
   - 스크립틀릿 (`<% %>`)
   - 표현식 (`<%= %>`)
   - 지시어 (`<%@ %>`)

2. **JDBC 사용**
   - Connection, PreparedStatement, ResultSet
   - 데이터베이스 CRUD 작업

3. **세션 관리**
   - `session.setAttribute()`: 세션에 데이터 저장
   - `session.getAttribute()`: 세션에서 데이터 가져오기
   - `session.invalidate()`: 세션 무효화

4. **MVC 패턴**
   - Model: DTO, DAO
   - View: JSP 파일
   - Controller: JSP Process 파일

---

**이 예제는 JSP와 JDBC를 사용한 완전한 회원관리 시스템입니다! 💪**
