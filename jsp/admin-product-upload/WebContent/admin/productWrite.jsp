<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/adminCheck.jspf" %>
<%@ include file="/include/header.jsp" %>

<div class="row justify-content-center">
  <div class="col-md-7">
    <div class="card shadow-sm">
      <div class="card-body p-4">
        <h1 class="h4 mb-3">상품 올리기</h1>
        <p class="small text-muted mb-3">
          <code>enctype="multipart/form-data"</code> + COS <code>MultipartRequest</code>
        </p>
<%
    String err = request.getParameter("error");
    if ("empty".equals(err)) {
%>
        <div class="alert alert-danger py-2">상품명·가격은 필수입니다.</div>
<% } else if ("file".equals(err)) { %>
        <div class="alert alert-warning py-2">이미지 파일을 선택하세요.</div>
<% } else if ("ok".equals(request.getParameter("msg"))) { %>
        <div class="alert alert-success py-2">상품이 등록되었습니다. <a href="productList.jsp">목록 보기</a></div>
<% } %>

        <form method="post" action="productWriteProcess.jsp" enctype="multipart/form-data">
          <div class="mb-3">
            <label class="form-label">상품명 *</label>
            <input type="text" name="name" class="form-control" required maxlength="100">
          </div>
          <div class="mb-3">
            <label class="form-label">가격(원) *</label>
            <input type="number" name="price" class="form-control" required min="0">
          </div>
          <div class="mb-3">
            <label class="form-label">상품 사진 *</label>
            <input type="file" name="imageFile" class="form-control" accept="image/*" required>
            <div class="form-text">jpg, png, png 등 · 최대 5MB · 저장: uploads/</div>
          </div>
          <div class="mb-3">
            <label class="form-label">설명</label>
            <textarea name="description" class="form-control" rows="3"></textarea>
          </div>
          <button type="submit" class="btn btn-primary">등록하기</button>
          <a href="productList.jsp" class="btn btn-outline-secondary">목록</a>
        </form>
      </div>
    </div>
  </div>
</div>

<%@ include file="/include/footer.jsp" %>
