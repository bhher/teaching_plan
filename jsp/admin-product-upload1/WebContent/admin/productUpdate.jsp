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
    String imgSrc = (p.getImage() != null)
            ? (request.getContextPath() + "/uploads/" + p.getImage())
            : "https://placehold.co/200x200?text=No";
%>
<%@ include file="/include/header.jsp" %>

<div class="row justify-content-center">
  <div class="col-md-7">
    <div class="card shadow-sm">
      <div class="card-body p-4">
        <h1 class="h4 mb-3">상품 수정</h1>
<%
    String err = request.getParameter("error");
    if ("empty".equals(err)) {
%>
        <div class="alert alert-danger py-2">상품명·가격은 필수입니다.</div>
<% } %>
        <div class="mb-3 text-center">
          <img src="<%= imgSrc %>" alt="" style="max-width:180px;max-height:180px;object-fit:cover;border-radius:8px;">
          <div class="form-text">현재 사진 (새 파일을 고르지 않으면 유지)</div>
        </div>

        <form method="post" action="productUpdateProcess.jsp" enctype="multipart/form-data">
          <input type="hidden" name="id" value="<%= p.getId() %>">

          <div class="mb-3">
            <label class="form-label">상품 번호</label>
            <input type="text" class="form-control" value="<%= p.getId() %>" disabled>
          </div>
          <div class="mb-3">
            <label class="form-label">상품명 *</label>
            <input type="text" name="name" class="form-control" required maxlength="100"
                   value="<%= p.getName() %>">
          </div>
          <div class="mb-3">
            <label class="form-label">가격(원) *</label>
            <input type="number" name="price" class="form-control" required min="0"
                   value="<%= p.getPrice() %>">
          </div>
          <div class="mb-3">
            <label class="form-label">상품 사진 변경 (선택)</label>
            <input type="file" name="imageFile" class="form-control" accept="image/*">
          </div>
          <div class="mb-3">
            <label class="form-label">설명</label>
            <textarea name="description" class="form-control" rows="3"><%= p.getDescription() != null ? p.getDescription() : "" %></textarea>
          </div>
          <button type="submit" class="btn btn-primary">저장</button>
          <a href="productList.jsp" class="btn btn-outline-secondary">목록</a>
        </form>
      </div>
    </div>
  </div>
</div>

<%@ include file="/include/footer.jsp" %>
