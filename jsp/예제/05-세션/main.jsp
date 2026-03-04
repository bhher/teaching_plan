<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String loginId = (String) session.getAttribute("loginId");
    if (loginId == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    
    java.util.Date loginTime = (java.util.Date) session.getAttribute("loginTime");
    Integer loginCount = (Integer) session.getAttribute("loginCount");
    if (loginCount == null) {
        loginCount = 1;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>메인 페이지</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            max-width: 800px;
            margin: 50px auto;
            padding: 20px;
        }
        .welcome-box {
            background-color: #e3f2fd;
            padding: 20px;
            border-radius: 10px;
            margin-bottom: 30px;
        }
        .info-box {
            background-color: #f5f5f5;
            padding: 20px;
            border-radius: 10px;
            margin-bottom: 20px;
        }
        .info-item {
            margin-bottom: 10px;
        }
        .info-label {
            font-weight: bold;
            color: #333;
        }
        .info-value {
            color: #666;
        }
        a {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #f44336;
            color: white;
            text-decoration: none;
            border-radius: 4px;
        }
        a:hover {
            background-color: #d32f2f;
        }
    </style>
</head>
<body>
    <div class="welcome-box">
        <h2>환영합니다, <%= loginId %>님!</h2>
    </div>
    
    <div class="info-box">
        <h3>세션 정보</h3>
        <div class="info-item">
            <span class="info-label">로그인 ID:</span>
            <span class="info-value"><%= loginId %></span>
        </div>
        <div class="info-item">
            <span class="info-label">로그인 시간:</span>
            <span class="info-value"><%= loginTime %></span>
        </div>
        <div class="info-item">
            <span class="info-label">로그인 횟수:</span>
            <span class="info-value"><%= loginCount %>회</span>
        </div>
        <div class="info-item">
            <span class="info-label">세션 ID:</span>
            <span class="info-value"><%= session.getId() %></span>
        </div>
        <div class="info-item">
            <span class="info-label">세션 생성 시간:</span>
            <span class="info-value"><%= new java.util.Date(session.getCreationTime()) %></span>
        </div>
        <div class="info-item">
            <span class="info-label">마지막 접근 시간:</span>
            <span class="info-value"><%= new java.util.Date(session.getLastAccessedTime()) %></span>
        </div>
        <div class="info-item">
            <span class="info-label">세션 유지 시간:</span>
            <span class="info-value"><%= session.getMaxInactiveInterval() %>초</span>
        </div>
    </div>
    
    <a href="logout.jsp">로그아웃</a>
</body>
</html>
