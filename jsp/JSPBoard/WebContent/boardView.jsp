<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dto.BoardDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 보기</title>
</head>
<body>
    <jsp:include page="header.jsp"/>
    
    <h2>게시글 보기</h2>
    
    <table border="1">
        <tr>
            <th>번호</th>
            <td>${board.bno}</td>
        </tr>
        <tr>
            <th>제목</th>
            <td>${board.title}</td>
        </tr>
        <tr>
            <th>내용</th>
            <td>${board.content}</td>
        </tr>
        <tr>
            <th>작성자</th>
            <td>${board.writer}</td>
        </tr>
        <tr>
            <th>날짜</th>
            <td>${board.regdate}</td>
        </tr>
    </table>
    
    <%
        String loginId=(String)session.getAttribute("loginId");
        BoardDTO board=(BoardDTO)request.getAttribute("board");
        if(loginId!=null && board!=null && loginId.equals(board.getWriter())){
    %>
        <a href="update?bno=${board.bno}">수정</a>
        <a href="delete?bno=${board.bno}">삭제</a>
    <%
        }
    %>
    
    <a href="list">목록</a>
    
    <jsp:include page="footer.jsp"/>
</body>
</html>
