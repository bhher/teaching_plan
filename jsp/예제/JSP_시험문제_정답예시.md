# JSP 시험 - 정답 예시

## 문제 1. join.jsp

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
    <form method="post" action="joinProcess.jsp">
        아이디 : <input type="text" name="userid" required><br><br>
        비밀번호 : <input type="password" name="userpw" required><br><br>
        이름 : <input type="text" name="username" required><br><br>
        이메일 : <input type="email" name="email" required><br><br>
        <button type="submit">회원가입</button>
    </form>
</body>
</html>
```

---

## 문제 2. joinProcess.jsp

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    request.setCharacterEncoding("UTF-8");
    String userid = request.getParameter("userid");
    String userpw = request.getParameter("userpw");
    String username = request.getParameter("username");
    String email = request.getParameter("email");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원가입 정보</title>
</head>
<body>
    <h2>회원가입 정보</h2>
    <table border="1">
        <tr>
            <th>항목</th>
            <th>값</th>
        </tr>
        <tr>
            <td>아이디</td>
            <td><%= userid %></td>
        </tr>
        <tr>
            <td>비밀번호</td>
            <td><%= userpw %></td>
        </tr>
        <tr>
            <td>이름</td>
            <td><%= username %></td>
        </tr>
        <tr>
            <td>이메일</td>
            <td><%= email %></td>
        </tr>
    </table>
</body>
</html>
```

---

## 문제 3. write.jsp

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 작성</title>
</head>
<body>
    <h2>게시글 작성</h2>
    <form method="post" action="writeProcess.jsp">
        작성자 : <input type="text" name="writer" required><br><br>
        제목 : <input type="text" name="title" required><br><br>
        내용 : <br>
        <textarea name="content" rows="10" cols="50"></textarea><br><br>
        <button type="submit">글쓰기</button>
    </form>
</body>
</html>
```

---

## 문제 4. list.jsp

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String[] writers = {"홍길동", "김철수", "이영희"};
    String[] titles = {"JSP 공부", "서블릿 공부", "JDBC 공부"};
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 목록</title>
</head>
<body>
    <h2>게시글 목록</h2>
    <table border="1">
        <tr>
            <th>번호</th>
            <th>작성자</th>
            <th>제목</th>
        </tr>
        <% for (int i = 0; i < writers.length; i++) { %>
        <tr>
            <td><%= i + 1 %></td>
            <td><%= writers[i] %></td>
            <td><%= titles[i] %></td>
        </tr>
        <% } %>
    </table>
</body>
</html>
```
