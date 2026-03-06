# 📚 JSP 회원관리 시스템 (기본)

## 📋 목차

1. [프로젝트 개요](#프로젝트-개요)
2. [데이터베이스 설정](#데이터베이스-설정)
3. [프로젝트 구조](#프로젝트-구조)
4. [코드 구현](#코드-구현)
5. [실행 방법](#실행-방법)

---

## 🎯 프로젝트 개요

JSP를 사용한 기본적인 회원관리 시스템입니다. CRUD 기능을 포함합니다.

### 주요 기능
- ✅ 회원가입 (Create)
- ✅ 로그인 (Read)
- ✅ 회원 목록 조회 (Read)
- ✅ 회원 정보 수정 (Update)
- ✅ 회원 삭제 (Delete)

---

## 🗄️ 데이터베이스 설정

### 1️⃣ MySQL 테이블 생성

```sql
CREATE DATABASE jspdb DEFAULT CHARACTER SET utf8mb4;

USE jspdb;

CREATE TABLE member (
    id INT PRIMARY KEY AUTO_INCREMENT,
    userid VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    username VARCHAR(50) NOT NULL,
    regdate TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### 테이블 구조 설명
- `id`: 회원 고유 번호 (자동 증가)
- `userid`: 사용자 아이디 (중복 불가)
- `password`: 비밀번호
- `username`: 사용자 이름
- `regdate`: 가입일시 (자동 설정)

---

## 📁 프로젝트 구조

```
JSPProject
 ├─ dto
 │   └─ MemberDTO.java          # 데이터 전송 객체
 ├─ dao
 │   └─ MemberDAO.java           # 데이터 접근 객체
 ├─ util
 │   └─ DBUtil.java              # 데이터베이스 연결 유틸리티
 ├─ join.jsp                     # 회원가입 폼
 ├─ joinProcess.jsp              # 회원가입 처리
 ├─ login.jsp                    # 로그인 폼
 ├─ loginProcess.jsp             # 로그인 처리
 ├─ memberList.jsp               # 회원 목록
 ├─ update.jsp                   # 회원 정보 수정
 └─ delete.jsp                   # 회원 삭제
```

---

## 💻 코드 구현

### ✅ 1. DB 연결 클래스

**📌 DBUtil.java**

```java
package util;

import java.sql.*;

public class DBUtil {

    public static Connection getConnection() throws Exception {
        String url = "jdbc:mysql://localhost:3306/jspdb?serverTimezone=Asia/Seoul";
        String user = "root";
        String password = "1234";

        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, password);
    }
}
```

**설명:**
- 데이터베이스 연결을 위한 유틸리티 클래스
- `getConnection()`: MySQL 데이터베이스 연결 반환
- URL, 사용자명, 비밀번호를 설정하여 연결

---

### ✅ 2. DTO 작성

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

    // Getter와 Setter
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
- 멤버 변수와 Getter/Setter 메서드 포함
- 생성자 오버로딩 지원

---

### ✅ 3. DAO 작성 (CRUD)

**📌 MemberDAO.java**

```java
package dao;

import java.sql.*;
import java.util.*;
import dto.MemberDTO;
import util.DBUtil;

public class MemberDAO {

    // 회원가입 (Create)
    public void insert(MemberDTO dto) throws Exception {
        Connection conn = DBUtil.getConnection();
        String sql = "INSERT INTO member(userid,password,username) VALUES(?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, dto.getUserid());
        pstmt.setString(2, dto.getPassword());
        pstmt.setString(3, dto.getUsername());

        pstmt.executeUpdate();
        conn.close();
    }

    // 로그인 (Read)
    public MemberDTO login(String userid, String password) throws Exception {
        Connection conn = DBUtil.getConnection();
        String sql = "SELECT * FROM member WHERE userid=? AND password=?";
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

        String sql = "SELECT * FROM member";
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

    // 수정
    public void update(MemberDTO dto) throws Exception {
        Connection conn = DBUtil.getConnection();
        String sql = "UPDATE member SET username=? WHERE id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, dto.getUsername());
        pstmt.setInt(2, dto.getId());

        pstmt.executeUpdate();
        conn.close();
    }

    // 삭제
    public void delete(int id) throws Exception {
        Connection conn = DBUtil.getConnection();
        String sql = "DELETE FROM member WHERE id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setInt(1, id);
        pstmt.executeUpdate();
        conn.close();
    }
}
```

**설명:**
- 데이터베이스 CRUD 작업을 담당하는 클래스
- `insert()`: 회원가입
- `login()`: 로그인 검증
- `selectAll()`: 전체 회원 조회
- `update()`: 회원 정보 수정
- `delete()`: 회원 삭제

---

### ✅ 4. 회원가입 JSP

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
        아이디: <input type="text" name="userid"><br>
        비밀번호: <input type="password" name="password"><br>
        이름: <input type="text" name="username"><br>
        <input type="submit" value="회원가입">
    </form>
    <a href="login.jsp">로그인으로</a>
</body>
</html>
```

**📌 joinProcess.jsp**

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.MemberDAO,dto.MemberDTO" %>
<%
    request.setCharacterEncoding("UTF-8");

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
- `joinProcess.jsp`: 폼 데이터를 받아 데이터베이스에 저장 후 로그인 페이지로 리다이렉트

---

### ✅ 5. 로그인 JSP

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
        아이디: <input type="text" name="userid"><br>
        비밀번호: <input type="password" name="password"><br>
        <input type="submit" value="로그인">
    </form>
    <a href="join.jsp">회원가입</a>
</body>
</html>
```

**📌 loginProcess.jsp**

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.MemberDAO,dto.MemberDTO" %>
<%
    String userid = request.getParameter("userid");
    String password = request.getParameter("password");

    MemberDAO dao = new MemberDAO();
    MemberDTO dto = dao.login(userid, password);

    if(dto != null) {
        session.setAttribute("loginUser", dto);
        response.sendRedirect("memberList.jsp");
    } else {
        out.println("<script>alert('로그인 실패'); history.back();</script>");
    }
%>
```

**설명:**
- `login.jsp`: 로그인 입력 폼
- `loginProcess.jsp`: 아이디/비밀번호 검증 후 세션에 저장하고 회원 목록 페이지로 이동

---

### ✅ 6. 회원 목록 (Read)

**📌 memberList.jsp**

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.MemberDAO,dto.MemberDTO,java.util.*" %>
<%
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
    <h2>회원목록</h2>
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
            <td><a href="update.jsp?id=<%=dto.getId()%>">수정</a></td>
            <td><a href="delete.jsp?id=<%=dto.getId()%>">삭제</a></td>
        </tr>
        <% } %>
    </table>
    <a href="login.jsp">로그인으로</a>
</body>
</html>
```

**설명:**
- 전체 회원 목록을 테이블로 표시
- 각 회원의 수정/삭제 링크 제공

---

### ✅ 7. 회원 정보 수정

**📌 update.jsp**

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.MemberDAO,dto.MemberDTO" %>
<%
    String idStr = request.getParameter("id");
    int id = Integer.parseInt(idStr);
    
    MemberDAO dao = new MemberDAO();
    // ID로 회원 조회하는 메서드가 필요하면 추가해야 함
    // 여기서는 간단히 ID만 전달
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원정보 수정</title>
</head>
<body>
    <h2>회원정보 수정</h2>
    <form action="updateProcess.jsp" method="post">
        <input type="hidden" name="id" value="<%=id%>">
        이름: <input type="text" name="username"><br>
        <input type="submit" value="수정">
    </form>
    <a href="memberList.jsp">목록으로</a>
</body>
</html>
```

**📌 updateProcess.jsp**

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.MemberDAO,dto.MemberDTO" %>
<%
    request.setCharacterEncoding("UTF-8");
    
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

---

### ✅ 8. 회원 삭제

**📌 delete.jsp**

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.MemberDAO" %>
<%
    String idStr = request.getParameter("id");
    int id = Integer.parseInt(idStr);
    
    MemberDAO dao = new MemberDAO();
    dao.delete(id);
    
    response.sendRedirect("memberList.jsp");
%>
```

**설명:**
- ID를 받아 해당 회원을 삭제하고 목록 페이지로 리다이렉트

---

## 🚀 실행 방법

### 1. 데이터베이스 설정
```sql
CREATE DATABASE jspdb DEFAULT CHARACTER SET utf8mb4;
USE jspdb;
-- 위의 CREATE TABLE 문 실행
```

### 2. 프로젝트 설정
- MySQL JDBC 드라이버를 프로젝트에 추가
- `DBUtil.java`의 연결 정보 수정 (URL, 사용자명, 비밀번호)

### 3. 실행 순서
1. `join.jsp` - 회원가입
2. `login.jsp` - 로그인
3. `memberList.jsp` - 회원 목록 확인
4. `update.jsp` - 회원 정보 수정
5. `delete.jsp` - 회원 삭제

---

## 📝 주요 개념

### DTO (Data Transfer Object)
- 데이터를 전송하기 위한 객체
- 멤버 변수와 Getter/Setter로 구성

### DAO (Data Access Object)
- 데이터베이스 접근 로직을 담당
- CRUD 작업을 메서드로 구현

### PreparedStatement
- SQL Injection 공격 방지
- 파라미터를 `?`로 설정하여 안전하게 처리

---

## ⚠️ 주의사항

1. **보안**
   - 비밀번호는 암호화하여 저장해야 함 (현재는 평문 저장)
   - SQL Injection 방지를 위해 PreparedStatement 사용

2. **에러 처리**
   - try-catch 블록으로 예외 처리 필요
   - 사용자에게 적절한 에러 메시지 표시

3. **세션 관리**
   - 로그인 상태 확인 로직 추가 필요
   - 로그아웃 기능 구현 필요

---

**이 예제는 JSP와 JDBC를 사용한 기본적인 회원관리 시스템입니다! 💪**
