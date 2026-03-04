<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    // 파라미터 받기
    String id = request.getParameter("id");
    String pw = request.getParameter("pw");
    String name = request.getParameter("name");
    String email = request.getParameter("email");
    String gender = request.getParameter("gender");
    String[] hobbies = request.getParameterValues("hobby");
    String city = request.getParameter("city");
    String bio = request.getParameter("bio");
    
    // 세션에 저장
    session.setAttribute("userId", id);
    session.setAttribute("userName", name);
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>가입 완료</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            max-width: 600px;
            margin: 50px auto;
            padding: 20px;
        }
        .info-box {
            background-color: #f5f5f5;
            padding: 20px;
            border-radius: 10px;
            margin-bottom: 20px;
        }
        .info-item {
            margin-bottom: 10px;
            padding: 8px;
            background-color: white;
            border-left: 4px solid #4CAF50;
        }
        .info-label {
            font-weight: bold;
            color: #333;
        }
        .info-value {
            color: #666;
        }
        .hobby-list {
            display: inline;
        }
        .hobby-item {
            display: inline-block;
            background-color: #e3f2fd;
            padding: 5px 10px;
            margin: 5px;
            border-radius: 5px;
        }
        a {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #2196F3;
            color: white;
            text-decoration: none;
            border-radius: 4px;
        }
        a:hover {
            background-color: #1976D2;
        }
    </style>
</head>
<body>
    <h2>회원가입 정보</h2>
    
    <div class="info-box">
        <div class="info-item">
            <span class="info-label">아이디:</span>
            <span class="info-value"><%= id %></span>
        </div>
        
        <div class="info-item">
            <span class="info-label">이름:</span>
            <span class="info-value"><%= name %></span>
        </div>
        
        <div class="info-item">
            <span class="info-label">이메일:</span>
            <span class="info-value">
                <%= email != null && !email.isEmpty() ? email : "없음" %>
            </span>
        </div>
        
        <div class="info-item">
            <span class="info-label">성별:</span>
            <span class="info-value">
                <%= gender != null ? gender : "선택 안함" %>
            </span>
        </div>
        
        <div class="info-item">
            <span class="info-label">취미:</span>
            <span class="info-value">
                <c:choose>
                    <c:when test="${paramValues.hobby != null && paramValues.hobby.length > 0}">
                        <c:forEach var="hobby" items="${paramValues.hobby}">
                            <span class="hobby-item">${hobby}</span>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        없음
                    </c:otherwise>
                </c:choose>
            </span>
        </div>
        
        <div class="info-item">
            <span class="info-label">거주지:</span>
            <span class="info-value">
                <%= city != null && !city.isEmpty() ? city : "선택 안함" %>
            </span>
        </div>
        
        <div class="info-item">
            <span class="info-label">자기소개:</span>
            <span class="info-value">
                <%= bio != null && !bio.isEmpty() ? bio.replace("\n", "<br>") : "없음" %>
            </span>
        </div>
    </div>
    
    <h3>전송된 모든 파라미터:</h3>
    <div class="info-box">
        <c:forEach var="paramName" items="${param}">
            <div class="info-item">
                <span class="info-label">${paramName.key}:</span>
                <span class="info-value">
                    <c:forEach var="value" items="${paramValues[paramName.key]}">
                        ${value}
                    </c:forEach>
                </span>
            </div>
        </c:forEach>
    </div>
    
    <a href="form.jsp">다시 가입하기</a>
    <a href="../05-세션/session_test.jsp">세션 테스트</a>
</body>
</html>
