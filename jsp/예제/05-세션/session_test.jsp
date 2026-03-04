<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>세션 테스트</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            max-width: 800px;
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
            border-left: 4px solid #2196F3;
        }
        .info-label {
            font-weight: bold;
            color: #333;
        }
        .info-value {
            color: #666;
        }
        form {
            background-color: #f5f5f5;
            padding: 20px;
            border-radius: 10px;
            margin-bottom: 20px;
        }
        input[type="text"] {
            width: 200px;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        button {
            padding: 8px 15px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        button:hover {
            background-color: #45a049;
        }
        .remove-btn {
            background-color: #f44336;
        }
        .remove-btn:hover {
            background-color: #d32f2f;
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
    <h2>세션 테스트 페이지</h2>
    
    <!-- 세션에 값 추가 -->
    <form action="session_test.jsp" method="post">
        <h3>세션에 값 추가</h3>
        <p>
            키: <input type="text" name="key" placeholder="키 입력">
            값: <input type="text" name="value" placeholder="값 입력">
            <button type="submit" name="action" value="add">추가</button>
        </p>
    </form>
    
    <!-- 세션에서 값 제거 -->
    <form action="session_test.jsp" method="post">
        <h3>세션에서 값 제거</h3>
        <p>
            키: <input type="text" name="removeKey" placeholder="제거할 키 입력">
            <button type="submit" name="action" value="remove" class="remove-btn">제거</button>
        </p>
    </form>
    
    <%
        // 세션 값 추가
        String action = request.getParameter("action");
        if ("add".equals(action)) {
            String key = request.getParameter("key");
            String value = request.getParameter("value");
            if (key != null && value != null && !key.isEmpty() && !value.isEmpty()) {
                session.setAttribute(key, value);
            }
        }
        
        // 세션 값 제거
        if ("remove".equals(action)) {
            String removeKey = request.getParameter("removeKey");
            if (removeKey != null && !removeKey.isEmpty()) {
                session.removeAttribute(removeKey);
            }
        }
    %>
    
    <!-- 세션 정보 표시 -->
    <div class="info-box">
        <h3>세션 정보</h3>
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
    
    <!-- 세션에 저장된 모든 값 표시 -->
    <div class="info-box">
        <h3>세션에 저장된 값들</h3>
        <%
            java.util.Enumeration<String> sessionNames = session.getAttributeNames();
            boolean hasAttributes = false;
            while (sessionNames.hasMoreElements()) {
                hasAttributes = true;
                String attrName = sessionNames.nextElement();
                Object attrValue = session.getAttribute(attrName);
        %>
            <div class="info-item">
                <span class="info-label"><%= attrName %>:</span>
                <span class="info-value"><%= attrValue %></span>
            </div>
        <%
            }
            if (!hasAttributes) {
        %>
            <p>세션에 저장된 값이 없습니다.</p>
        <%
            }
        %>
    </div>
    
    <a href="login.jsp">로그인 페이지로</a>
    <a href="main.jsp">메인 페이지로</a>
</body>
</html>
