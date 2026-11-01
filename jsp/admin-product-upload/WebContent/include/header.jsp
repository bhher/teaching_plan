<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String adminId = (String) session.getAttribute("adminId");
    String adminName = (String) session.getAttribute("adminName");
    String ctx = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>상품 등록 연습</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<style>
  .thumb { width: 72px; height: 72px; object-fit: cover; border-radius: 8px; }
  .gallery-img { height: 180px; object-fit: cover; width: 100%; }
  .gallery-desc {
    display: -webkit-box; -webkit-line-clamp: 2; -webkit-box-orient: vertical;
    overflow: hidden;
  }
  .gallery-card { transition: transform .15s, box-shadow .15s; }
  .gallery-card:hover { transform: translateY(-3px); box-shadow: 0 .5rem 1rem rgba(0,0,0,.08)!important; }
</style>
</head>
<body class="bg-light">
<nav class="navbar navbar-dark bg-primary mb-4">
  <div class="container">
    <a class="navbar-brand" href="<%= ctx %>/admin/productList.jsp">상품 등록 연습</a>
<% if (adminId != null) { %>
    <div class="d-flex align-items-center gap-3 text-white-50">
      <a class="nav-link text-white" href="<%= ctx %>/admin/productWrite.jsp">상품 올리기</a>
      <a class="nav-link text-white" href="<%= ctx %>/admin/productList.jsp">목록</a>
      <a class="nav-link text-white" href="<%= ctx %>/admin/productGallery.jsp">갤러리</a>
      <span class="small"><%= adminName %></span>
      <a class="nav-link text-white" href="<%= ctx %>/admin/logout.jsp">로그아웃</a>
    </div>
<% } %>
  </div>
</nav>
<main class="container pb-5">
