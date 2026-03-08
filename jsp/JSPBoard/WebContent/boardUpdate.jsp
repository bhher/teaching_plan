<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String loginId=(String)session.getAttribute("loginId");
    if(loginId==null){
        response.sendRedirect("login.jsp");
        return;
    }
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 수정</title>
</head>
<body>
    <jsp:include page="header.jsp"/>
    
    <h2>게시글 수정</h2>
    
    <form action="update" method="post">
        <input type="hidden" name="bno" value="${board.bno}">
        제목 <input type="text" name="title" value="${board.title}"><br>
        내용<br>
        <textarea name="content">${board.content}</textarea><br>
        <button>수정</button>
    </form>
    
    <a href="view?bno=${board.bno}">취소</a>
    
    <jsp:include page="footer.jsp"/>
</body>
</html>
