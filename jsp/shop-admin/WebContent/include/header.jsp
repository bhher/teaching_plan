<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String adminId = (String) session.getAttribute("adminId");
    String adminName = (String) session.getAttribute("adminName");
    String uri = request.getRequestURI();
    String ctx = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Shop Admin</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-4">
  <div class="container">
    <a class="navbar-brand" href="<%= ctx %>/admin/productList.jsp">Shop Admin</a>
<% if (adminId != null) { %>
    <div class="navbar-nav ms-auto align-items-lg-center">
      <a class="nav-link <%= uri.endsWith("productList.jsp") ? "active" : "" %>"
         href="<%= ctx %>/admin/productList.jsp">상품목록</a>
      <a class="nav-link <%= uri.endsWith("productWrite.jsp") ? "active" : "" %>"
         href="<%= ctx %>/admin/productWrite.jsp">상품등록</a>
      <span class="navbar-text text-white-50 mx-2"><%= adminName %> (<%= adminId %>)</span>
      <a class="nav-link" href="<%= ctx %>/admin/logout.jsp">로그아웃</a>
    </div>
<% } %>
  </div>
</nav>
<main class="container pb-5">
