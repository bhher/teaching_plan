<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    // 로그인 체크
    String loginId = (String) session.getAttribute("loginId");
    if (loginId == null) {
        response.sendRedirect("login_db.jsp?error=required");
        return;
    }
    
    String loginName = (String) session.getAttribute("loginName");
    String loginEmail = (String) session.getAttribute("loginEmail");
    java.util.Date loginTime = (java.util.Date) session.getAttribute("loginTime");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>메인 페이지</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        body {
            font-family: 'Malgun Gothic', sans-serif;
            background-color: #f5f5f5;
            padding: 20px;
        }
        .container {
            max-width: 800px;
            margin: 0 auto;
            background-color: white;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        h2 {
            color: #333;
            margin-bottom: 30px;
        }
        .welcome-box {
            background-color: #e3f2fd;
            padding: 20px;
            border-radius: 8px;
            margin-bottom: 30px;
        }
        .info-box {
            background-color: #f5f5f5;
            padding: 20px;
            border-radius: 8px;
            margin-bottom: 20px;
        }
        .info-item {
            margin-bottom: 10px;
            padding: 8px 0;
            border-bottom: 1px solid #ddd;
        }
        .info-item:last-child {
            border-bottom: none;
        }
        .info-label {
            font-weight: bold;
            color: #555;
            display: inline-block;
            width: 120px;
        }
        .info-value {
            color: #333;
        }
        .btn-group {
            margin-top: 30px;
            text-align: center;
        }
        .btn {
            display: inline-block;
            padding: 10px 20px;
            margin: 0 10px;
            background-color: #2196F3;
            color: white;
            text-decoration: none;
            border-radius: 4px;
        }
        .btn:hover {
            background-color: #1976D2;
        }
        .btn-danger {
            background-color: #f44336;
        }
        .btn-danger:hover {
            background-color: #d32f2f;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="welcome-box">
            <h2>환영합니다, <%= loginName %>님!</h2>
        </div>
        
        <div class="info-box">
            <h3>회원 정보</h3>
            <div class="info-item">
                <span class="info-label">아이디:</span>
                <span class="info-value"><%= loginId %></span>
            </div>
            <div class="info-item">
                <span class="info-label">이름:</span>
                <span class="info-value"><%= loginName %></span>
            </div>
            <div class="info-item">
                <span class="info-label">이메일:</span>
                <span class="info-value"><%= loginEmail != null ? loginEmail : "-" %></span>
            </div>
            <div class="info-item">
                <span class="info-label">로그인 시간:</span>
                <span class="info-value"><%= loginTime %></span>
            </div>
            <div class="info-item">
                <span class="info-label">세션 ID:</span>
                <span class="info-value"><%= session.getId() %></span>
            </div>
        </div>
        
        <div class="btn-group">
            <a href="member_list.jsp" class="btn">회원목록</a>
            <a href="logout_db.jsp" class="btn btn-danger">로그아웃</a>
        </div>
    </div>
</body>
</html>
