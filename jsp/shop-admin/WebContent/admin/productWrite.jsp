<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/adminCheck.jspf" %>
<%@ include file="/include/header.jsp" %>

<div class="row justify-content-center">
  <div class="col-lg-8">
    <div class="card shadow-sm">
      <div class="card-body p-4">
        <h1 class="h4 mb-3">상품 등록</h1>
<%
    String err = request.getParameter("error");
    if ("empty".equals(err)) {
%>
        <div class="alert alert-danger py-2">필수 항목(상품명·가격·재고·카테고리)을 입력하세요.</div>
<% } else if ("number".equals(err)) { %>
        <div class="alert alert-danger py-2">가격·재고는 숫자로 입력하세요.</div>
<% } %>
        <form method="post" action="productWriteProcess.jsp">
          <div class="mb-3">
            <label class="form-label">상품명 <span class="text-danger">*</span></label>
            <input type="text" name="name" class="form-control" required maxlength="100">
          </div>
          <div class="row">
            <div class="col-md-6 mb-3">
              <label class="form-label">가격(원) <span class="text-danger">*</span></label>
              <input type="number" name="price" class="form-control" required min="0" step="1">
            </div>
            <div class="col-md-6 mb-3">
              <label class="form-label">재고 <span class="text-danger">*</span></label>
              <input type="number" name="stock" class="form-control" required min="0" step="1">
            </div>
          </div>
          <div class="mb-3">
            <label class="form-label">카테고리 <span class="text-danger">*</span></label>
            <select name="category" class="form-select" required>
              <option value="">선택</option>
              <option value="전자제품">전자제품</option>
              <option value="생활용품">생활용품</option>
              <option value="도서">도서</option>
              <option value="패션">패션</option>
              <option value="식품">식품</option>
              <option value="기타">기타</option>
            </select>
          </div>
          <div class="mb-3">
            <label class="form-label">상품 설명</label>
            <textarea name="description" class="form-control" rows="5" maxlength="2000"></textarea>
          </div>
          <div class="d-flex gap-2">
            <button type="submit" class="btn btn-primary">등록</button>
            <a href="productList.jsp" class="btn btn-outline-secondary">목록</a>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>

<%@ include file="/include/footer.jsp" %>
