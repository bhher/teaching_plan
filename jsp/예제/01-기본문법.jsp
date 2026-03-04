<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>JSP 기본 문법 예제</title>
</head>
<body>
    <h2>JSP 기본 문법 예제</h2>
    
    <!-- 1. 스크립틀릿 -->
    <h3>1. 스크립틀릿 (Scriptlet)</h3>
    <%
        String name = "홍길동";
        int age = 25;
        double height = 175.5;
    %>
    <p>이름: <%= name %></p>
    <p>나이: <%= age %></p>
    <p>키: <%= height %>cm</p>
    
    <!-- 2. 표현식 -->
    <h3>2. 표현식 (Expression)</h3>
    <p>현재 시간: <%= new Date() %></p>
    <p>계산 결과: <%= 10 + 20 %></p>
    
    <!-- 3. 선언문 -->
    <%!
        int count = 0;
        
        public int add(int a, int b) {
            return a + b;
        }
    %>
    <h3>3. 선언문 (Declaration)</h3>
    <%
        count++;
    %>
    <p>방문 횟수: <%= count %></p>
    <p>덧셈 결과: <%= add(5, 3) %></p>
    
    <!-- 4. 조건문 -->
    <h3>4. 조건문</h3>
    <%
        int score = 85;
        String grade;
        
        if (score >= 90) {
            grade = "A";
        } else if (score >= 80) {
            grade = "B";
        } else if (score >= 70) {
            grade = "C";
        } else {
            grade = "F";
        }
    %>
    <p>점수: <%= score %>점</p>
    <p>등급: <%= grade %></p>
    
    <!-- 5. 반복문 -->
    <h3>5. 반복문</h3>
    <ul>
    <%
        for (int i = 1; i <= 5; i++) {
            out.println("<li>항목 " + i + "</li>");
        }
    %>
    </ul>
    
    <!-- 6. 배열 -->
    <h3>6. 배열 처리</h3>
    <%
        String[] fruits = {"사과", "바나나", "오렌지", "포도"};
    %>
    <ul>
    <%
        for (String fruit : fruits) {
    %>
        <li><%= fruit %></li>
    <%
        }
    %>
    </ul>
    
    <!-- 7. HTML과 혼합 -->
    <h3>7. HTML과 혼합</h3>
    <table border="1">
        <tr>
            <th>번호</th>
            <th>이름</th>
            <th>나이</th>
        </tr>
    <%
        String[] names = {"홍길동", "김철수", "이영희"};
        int[] ages = {25, 30, 28};
        
        for (int i = 0; i < names.length; i++) {
    %>
        <tr>
            <td><%= i + 1 %></td>
            <td><%= names[i] %></td>
            <td><%= ages[i] %></td>
        </tr>
    <%
        }
    %>
    </table>
</body>
</html>
