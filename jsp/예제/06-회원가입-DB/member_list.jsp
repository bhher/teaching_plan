<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="jsp.member.MemberDTO" %>
<%@ page import="jsp.member.MemberDAO" %>
<%@ page import="jsp.member.DBConnection" %>
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
    </style>
</head>
<body>
    <div class="container">
        <h2>회원 목록</h2>
        <a href="register.jsp" class="btn btn-register">회원가입</a>
        
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
        
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
%>
        <p style="color: red;">데이터베이스 드라이버를 찾을 수 없습니다.</p>
<%
    } catch (SQLException e) {
        e.printStackTrace();
%>
        <p style="color: red;">데이터베이스 오류가 발생했습니다: <%= e.getMessage() %></p>
<%
    } finally {
        // 리소스 해제
        DBConnection.close(conn);
    }
%>
        
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
        <%
            if (memberList != null && memberList.size() > 0) {
                int count = 0;
                for (MemberDTO member : memberList) {
                    count++;
        %>
                <tr>
                    <td><%= count %></td>
                    <td><%= member.getUserId() %></td>
                    <td><%= member.getName() %></td>
                    <td><%= member.hasEmail() ? member.getEmail() : "-" %></td>
                    <td><%= member.getGender() != null ? member.getGender() : "-" %></td>
                    <td><%= member.getCity() != null ? member.getCity() : "-" %></td>
                    <td class="hobby-list">
                        <%= member.hasHobbies() ? member.getHobbiesAsString() : "-" %>
                    </td>
                    <td><%= member.getRegDate() %></td>
                </tr>
        <%
                }
            } else {
        %>
                <tr>
                    <td colspan="8" style="text-align: center; padding: 30px;">
                        등록된 회원이 없습니다.
                    </td>
                </tr>
        <%
            }
        %>
            </tbody>
        </table>
    </div>
</body>
</html>
