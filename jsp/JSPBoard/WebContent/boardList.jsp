<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시판 목록</title>
</head>
<body>
    <jsp:include page="header.jsp"/>
    
    <h2>게시판</h2>
    
    <a href="boardWrite.jsp">글쓰기</a>
    
    <table border="1">
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>날짜</th>
        </tr>
        
        <c:forEach var="b" items="${list}">
            <tr>
                <td>${b.bno}</td>
                <td>
                    <a href="view?bno=${b.bno}">
                        ${b.title}
                    </a>
                </td>
                <td>${b.writer}</td>
                <td>${b.regdate}</td>
            </tr>
        </c:forEach>
    </table>
    
    <jsp:include page="footer.jsp"/>
</body>
</html>
