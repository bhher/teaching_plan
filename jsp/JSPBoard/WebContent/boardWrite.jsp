<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String loginId=(String)session.getAttribute("loginId");
    if(loginId==null){
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>글쓰기</title>
</head>
<body>
    <jsp:include page="header.jsp"/>
    
    <h2>글쓰기</h2>
    
    <form action="write" method="post">
        제목
        <input type="text" name="title"><br>
        
        내용
        <textarea name="content"></textarea><br>
        
        <button>등록</button>
    </form>
    
    <a href="list">목록</a>
    
    <jsp:include page="footer.jsp"/>
</body>
</html>
