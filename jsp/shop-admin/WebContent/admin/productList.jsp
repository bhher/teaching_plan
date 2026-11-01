<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="dao.ProductDAO" %>
<%@ page import="dto.ProductDTO" %>
<%@ include file="/include/adminCheck.jspf" %>
<%@ include file="/include/header.jsp" %>
<%
    request.setCharacterEncoding("UTF-8");
    String keyword = request.getParameter("keyword");
    if (keyword == null) {
        keyword = "";
    }

    ProductDAO dao = new ProductDAO();
    List<ProductDTO> list;
    if (!keyword.trim().isEmpty()) {
        list = dao.searchByName(keyword.trim());
    } else {
        list = dao.selectAll();
    }
%>

<div class="d-flex justify-content-between align-items-center mb-3 flex-wrap gap-2">
  <h1 class="h4 mb-0">상품 목록</h1>
  <a href="productWrite.jsp" class="btn btn-primary btn-sm">상품 등록</a>
</div>

<form method="get" action="productList.jsp" class="row g-2 mb-3">
  <div class="col-md-8">
    <input type="search" name="keyword" class="form-control"
           placeholder="상품명 검색" value="<%= keyword %>">
  </div>
  <div class="col-md-4">
    <button type="submit" class="btn btn-outline-secondary w-100">검색</button>
  </div>
</form>

<% if (!keyword.trim().isEmpty()) { %>
<p class="small text-muted">검색어: <strong><%= keyword %></strong> · 결과 <%= list.size() %>건
  <a href="productList.jsp">전체 보기</a>
</p>
<% } %>

<div class="card shadow-sm">
  <div class="table-responsive">
    <table class="table table-hover align-middle mb-0">
      <thead class="table-light">
        <tr>
          <th style="width:70px">번호</th>
          <th>상품명</th>
          <th style="width:110px">가격</th>
          <th style="width:80px">재고</th>
          <th style="width:100px">카테고리</th>
          <th style="width:160px">등록일</th>
          <th style="width:90px" class="text-end">관리</th>
        </tr>
      </thead>
      <tbody>
<%
    if (list.isEmpty()) {
%>
        <tr>
          <td colspan="7" class="text-center text-muted py-4">등록된 상품이 없습니다.</td>
        </tr>
<%
    } else {
        for (ProductDTO p : list) {
%>
        <tr>
          <td><%= p.getId() %></td>
          <td>
            <a href="productView.jsp?id=<%= p.getId() %>" class="text-decoration-none">
              <%= p.getName() %>
            </a>
          </td>
          <td><%= String.format("%,d", p.getPrice()) %>원</td>
          <td><%= p.getStock() %></td>
          <td><span class="badge text-bg-secondary"><%= p.getCategory() %></span></td>
          <td class="small text-muted"><%= p.getRegDate() %></td>
          <td class="text-end">
            <a href="productView.jsp?id=<%= p.getId() %>" class="btn btn-sm btn-outline-primary">상세</a>
          </td>
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
