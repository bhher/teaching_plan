<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="jsp.member.MemberDTO" %>
<%@ page import="jsp.member.MemberDAO" %>
<%@ page import="jsp.member.DBConnection" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    Connection conn = null;
    List<MemberDTO> memberList = null;
    
    try {
        // 데이터베이스 연결
        conn = DBConnection.getConnection();
        
        // DAO 객체 생성
        MemberDAO memberDAO = new MemberDAO(conn);
        
        // 모든 회원 조회
        memberList = memberDAO.selectAllMembers();
        
        // request에 저장 (JSTL 사용을 위해)
        request.setAttribute("memberList", memberList);
        
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // 리소스 해제
        DBConnection.close(conn, null);
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원 목록</title>
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
            max-width: 1200px;
            margin: 0 auto;
            background-color: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        h2 {
            margin-bottom: 20px;
            color: #333;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #4CAF50;
            color: white;
            font-weight: bold;
        }
        tr:hover {
            background-color: #f5f5f5;
        }
        .hobby-list {
            font-size: 12px;
            color: #666;
        }
        .btn {
            display: inline-block;
            padding: 8px 15px;
            background-color: #2196F3;
            color: white;
            text-decoration: none;
            border-radius: 4px;
            margin-top: 20px;
        }
        .btn:hover {
            background-color: #1976D2;
        }
        .btn-register {
            background-color: #4CAF50;
        }
        .btn-register:hover {
            background-color: #45a049;
        }
        .empty-message {
            text-align: center;
            padding: 30px;
            color: #999;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>회원 목록</h2>
        <a href="register.jsp" class="btn btn-register">회원가입</a>
        <a href="login_db.jsp" class="btn">로그인</a>
        
        <c:choose>
            <c:when test="${memberList != null && memberList.size() > 0}">
                <table>
                    <thead>
                        <tr>
                            <th>번호</th>
                            <th>아이디</th>
                            <th>이름</th>
                            <th>이메일</th>
                            <th>성별</th>
                            <th>거주지</th>
                            <th>취미</th>
                            <th>가입일</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="member" items="${memberList}" varStatus="status">
                            <tr>
                                <td>${status.count}</td>
                                <td>${member.userId}</td>
                                <td>${member.name}</td>
                                <td>${member.hasEmail() ? member.email : '-'}</td>
                                <td>${member.gender != null ? member.gender : '-'}</td>
                                <td>${member.city != null ? member.city : '-'}</td>
                                <td class="hobby-list">
                                    <c:choose>
                                        <c:when test="${member.hasHobbies()}">
                                            ${member.hobbiesAsString}
                                        </c:when>
                                        <c:otherwise>
                                            -
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>${member.regDate}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <div class="empty-message">
                    등록된 회원이 없습니다.
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
