<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="dao.ProductDAO" %>
<%@ page import="dto.ProductDTO" %>
<%@ include file="/include/adminCheck.jspf" %>
<%@ include file="/include/header.jsp" %>
<%
    List<ProductDTO> list = new ProductDAO().selectAll();
%>

<div class="d-flex justify-content-between align-items-center mb-3 flex-wrap gap-2">
  <h1 class="h4 mb-0">상품 갤러리</h1>
  <div class="d-flex gap-2">
    <a href="productList.jsp" class="btn btn-sm btn-outline-secondary">표 목록</a>
    <a href="productWrite.jsp" class="btn btn-sm btn-primary">상품 올리기</a>
  </div>
</div>

<p class="small text-muted mb-3">
  Bootstrap 그리드: 큰 화면 한 줄 <strong>4개</strong> (<code>col-lg-3</code>),
  중간 <strong>3개</strong> (<code>col-md-4</code>), 작은 화면 2개·1개
</p>

<% if (list.isEmpty()) { %>
<div class="alert alert-light border text-center py-5">
  등록된 상품이 없습니다. <a href="productWrite.jsp">상품 올리기</a>
</div>
<% } else { %>
<div class="row g-3">
<%
    for (ProductDTO p : list) {
        String img = (p.getImage() != null)
            ? (ctx + "/uploads/" + p.getImage())
            : "https://placehold.co/400x400?text=No+Image";
%>
  <div class="col-6 col-md-4 col-lg-3">
    <div class="card gallery-card h-100 shadow-sm">
      <img src="<%= img %>" class="card-img-top gallery-img" alt="<%= p.getName() %>">
      <div class="card-body">
        <h2 class="h6 card-title mb-1"><%= p.getName() %></h2>
        <p class="card-text text-primary fw-semibold mb-1">
          <%= String.format("%,d", p.getPrice()) %>원
        </p>
        <% if (p.getDescription() != null && !p.getDescription().isEmpty()) { %>
        <p class="card-text small text-muted mb-0 gallery-desc"><%= p.getDescription() %></p>
        <% } %>
      </div>
      <div class="card-footer bg-white border-0 small text-muted">
        #<%= p.getId() %> · <%= p.getRegDate() %>
      </div>
    </div>
  </div>
<%
    }
%>
</div>
<% } %>

<%@ include file="/include/footer.jsp" %>
