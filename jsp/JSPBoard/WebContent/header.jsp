<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String loginId=(String)session.getAttribute("loginId");
%>
<div style="background: #f0f0f0; padding: 10px; margin-bottom: 20px;">
    <%
        if(loginId!=null){
    %>
        <span>${loginId}님 환영합니다.</span>
        <a href="logout">로그아웃</a>
    <%
        }else{
    %>
        <a href="login.jsp">로그인</a>
        <a href="join.jsp">회원가입</a>
    <%
        }
    %>
    <a href="list">게시판</a>
</div>
