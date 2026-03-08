<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원가입</title>
</head>
<body>
    <jsp:include page="header.jsp"/>
    <h2>회원가입</h2>
    
    <form action="join" method="post">
        아이디 <input type="text" name="id"><br>
        비밀번호 <input type="password" name="password"><br>
        이름 <input type="text" name="name"><br>
        이메일 <input type="text" name="email"><br>
        <button>회원가입</button>
    </form>
    
    <jsp:include page="footer.jsp"/>
</body>
</html>
