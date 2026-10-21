<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String loginId = (String) session.getAttribute("loginId");
    String loginName = (String) session.getAttribute("loginName");
    String loginRole = (String) session.getAttribute("loginRole");
    boolean isAdmin = "ADMIN".equals(loginRole);
    String uri = request.getRequestURI();
    String ctx = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>여행지 · 숙소</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<style>
  .dest-card img { height: 160px; object-fit: cover; width: 100%; }
  .dest-card { transition: transform .15s; }
  .dest-card:hover { transform: translateY(-2px); }
</style>
</head>
<body class="bg-light">
<nav class="navbar navbar-expand-lg navbar-dark bg-success mb-4">
  <div class="container">
    <a class="navbar-brand" href="<%= ctx %>/index.jsp">TravelStay</a>
    <div class="navbar-nav ms-auto flex-row gap-2 align-items-center">
      <a class="nav-link <%= uri.contains("/destination/") ? "active" : "" %>" href="<%= ctx %>/destination/list.jsp">여행지</a>
<% if (loginId != null) { %>
      <a class="nav-link" href="<%= ctx %>/wishlist/list.jsp">찜</a>
<% } %>
<% if (isAdmin) { %>
      <a class="nav-link <%= uri.contains("/admin/") ? "active" : "" %>" href="<%= ctx %>/admin/index.jsp">관리자</a>
<% } %>
<% if (loginId == null) { %>
      <a class="nav-link" href="<%= ctx %>/member/login.jsp">로그인</a>
      <a class="nav-link" href="<%= ctx %>/member/join.jsp">회원가입</a>
<% } else { %>
      <span class="navbar-text text-white-50 small"><%= loginName %><%= isAdmin ? " ·관리자" : "" %></span>
      <a class="nav-link" href="<%= ctx %>/member/logout.jsp">로그아웃</a>
<% } %>
    </div>
  </div>
</nav>
<main class="container pb-5">
