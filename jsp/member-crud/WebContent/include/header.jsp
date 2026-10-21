<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String loginId = (String) session.getAttribute("loginId");
    String loginName = (String) session.getAttribute("loginName");
    String uri = request.getRequestURI();
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>member-crud</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-4">
  <div class="container">
    <a class="navbar-brand" href="<%= request.getContextPath() %>/index.jsp">member-crud</a>
    <div class="navbar-nav ms-auto">
      <a class="nav-link <%= uri.endsWith("list.jsp") ? "active" : "" %>"
         href="<%= request.getContextPath() %>/member/list.jsp">회원목록</a>
<% if (loginId == null) { %>
      <a class="nav-link <%= uri.endsWith("login.jsp") ? "active" : "" %>"
         href="<%= request.getContextPath() %>/member/login.jsp">로그인</a>
      <a class="nav-link <%= uri.endsWith("join.jsp") ? "active" : "" %>"
         href="<%= request.getContextPath() %>/member/join.jsp">회원가입</a>
<% } else { %>
      <span class="navbar-text text-white-50 me-3"><%= loginName %> (<%= loginId %>)</span>
      <a class="nav-link" href="<%= request.getContextPath() %>/member/view.jsp?id=<%= loginId %>">내정보</a>
      <a class="nav-link" href="<%= request.getContextPath() %>/member/logout.jsp">로그아웃</a>
<% } %>
    </div>
  </div>
</nav>
<main class="container pb-5">
