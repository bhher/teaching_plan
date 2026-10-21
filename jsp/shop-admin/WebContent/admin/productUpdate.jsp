<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.ProductDAO" %>
<%@ page import="dto.ProductDTO" %>
<%@ include file="/include/adminCheck.jspf" %>
<%
    String idParam = request.getParameter("id");
    if (idParam == null || idParam.trim().isEmpty()) {
        response.sendRedirect("productList.jsp");
        return;
    }

    int id;
    try {
        id = Integer.parseInt(idParam.trim());
    } catch (NumberFormatException e) {
        response.sendRedirect("productList.jsp");
        return;
    }

    ProductDTO p = new ProductDAO().selectById(id);
    if (p == null) {
        response.sendRedirect("productList.jsp");
        return;
    }
%>
<%@ include file="/include/header.jsp" %>

<div class="row justify-content-center">
  <div class="col-lg-8">
    <div class="card shadow-sm">
      <div class="card-body p-4">
        <h1 class="h4 mb-3">상품 수정</h1>
<%
    String err = request.getParameter("error");
    if ("empty".equals(err)) {
%>
        <div class="alert alert-danger py-2">필수 항목을 입력하세요.</div>
<% } else if ("number".equals(err)) { %>
        <div class="alert alert-danger py-2">가격·재고는 숫자로 입력하세요.</div>
<% } %>
        <form method="post" action="productUpdateProcess.jsp">
          <input type="hidden" name="id" value="<%= p.getId() %>">

          <div class="mb-3">
            <label class="form-label">상품 번호</label>
            <input type="text" class="form-control" value="<%= p.getId() %>" disabled>
          </div>
          <div class="mb-3">
            <label class="form-label">상품명 <span class="text-danger">*</span></label>
            <input type="text" name="name" class="form-control" required maxlength="100"
                   value="<%= p.getName() %>">
          </div>
          <div class="row">
            <div class="col-md-6 mb-3">
              <label class="form-label">가격(원) <span class="text-danger">*</span></label>
              <input type="number" name="price" class="form-control" required min="0"
                     value="<%= p.getPrice() %>">
            </div>
            <div class="col-md-6 mb-3">
              <label class="form-label">재고 <span class="text-danger">*</span></label>
              <input type="number" name="stock" class="form-control" required min="0"
                     value="<%= p.getStock() %>">
            </div>
          </div>
          <div class="mb-3">
            <label class="form-label">카테고리 <span class="text-danger">*</span></label>
            <select name="category" class="form-select" required>
<%
    String[] cats = {"전자제품", "생활용품", "도서", "패션", "식품", "기타"};
    for (String c : cats) {
%>
              <option value="<%= c %>" <%= c.equals(p.getCategory()) ? "selected" : "" %>><%= c %></option>
<% } %>
            </select>
          </div>
          <div class="mb-3">
            <label class="form-label">상품 설명</label>
            <textarea name="description" class="form-control" rows="5"><%= p.getDescription() != null ? p.getDescription() : "" %></textarea>
          </div>
          <div class="d-flex gap-2">
            <button type="submit" class="btn btn-primary">저장</button>
            <a href="productView.jsp?id=<%= p.getId() %>" class="btn btn-outline-secondary">취소</a>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>

<%@ include file="/include/footer.jsp" %>
