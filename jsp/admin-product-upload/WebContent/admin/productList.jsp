<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="dao.ProductDAO" %>
<%@ page import="dto.ProductDTO" %>
<%@ include file="/include/adminCheck.jspf" %>
<%@ include file="/include/header.jsp" %>
<%
    List<ProductDTO> list = new ProductDAO().selectAll();
%>

<div class="d-flex justify-content-between align-items-center mb-3">
  <h1 class="h4 mb-0">올린 상품 목록</h1>
  <a href="productWrite.jsp" class="btn btn-sm btn-primary">상품 올리기</a>
</div>

<div class="card shadow-sm">
  <div class="table-responsive">
    <table class="table table-hover align-middle mb-0">
      <thead class="table-light">
        <tr>
          <th>사진</th>
          <th>번호</th>
          <th>상품명</th>
          <th>가격</th>
          <th>등록일</th>
        </tr>
      </thead>
      <tbody>
<% if (list.isEmpty()) { %>
        <tr><td colspan="5" class="text-center text-muted py-4">아직 올린 상품이 없습니다.</td></tr>
<% } else {
    for (ProductDTO p : list) {
        String img = (p.getImage() != null)
            ? (ctx + "/uploads/" + p.getImage())
            : "https://placehold.co/72x72?text=No";
%>
        <tr>
          <td><img class="thumb" src="<%= img %>" alt=""></td>
          <td><%= p.getId() %></td>
          <td>
            <strong><%= p.getName() %></strong>
            <% if (p.getDescription() != null) { %>
            <div class="small text-muted"><%= p.getDescription() %></div>
            <% } %>
          </td>
          <td><%= String.format("%,d", p.getPrice()) %>원</td>
          <td class="small text-muted"><%= p.getRegDate() %></td>
        </tr>
<%
    }
  }
%>
      </tbody>
    </table>
  </div>
</div>

<%@ include file="/include/footer.jsp" %>
