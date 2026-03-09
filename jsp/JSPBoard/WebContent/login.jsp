<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>로그인</title>
</head>
<body>
    <jsp:include page="header.jsp"/>
    <h2>로그인</h2>
    
    <form action="login" method="post">
        아이디 <input type="text" name="id"><br>
        비밀번호 <input type="password" name="password"><br>
        <button>로그인</button>
    </form>
    
    <a href="join.jsp">회원가입</a>
    
    <jsp:include page="footer.jsp"/>
</body>
</html>
