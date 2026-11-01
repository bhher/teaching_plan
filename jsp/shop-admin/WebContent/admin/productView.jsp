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
%>
<%@ include file="/include/header.jsp" %>
<div class="alert alert-warning">존재하지 않는 상품입니다. <a href="productList.jsp">목록</a></div>
<%@ include file="/include/footer.jsp" %>
<%
        return;
    }
%>
<%@ include file="/include/header.jsp" %>

<div class="row justify-content-center">
  <div class="col-lg-8">
    <div class="card shadow-sm">
      <div class="card-body p-4">
        <h1 class="h4 mb-3">상품 상세</h1>
        <dl class="row mb-0">
          <dt class="col-sm-3">상품 번호</dt>
          <dd class="col-sm-9"><%= p.getId() %></dd>
          <dt class="col-sm-3">상품명</dt>
          <dd class="col-sm-9"><%= p.getName() %></dd>
          <dt class="col-sm-3">가격</dt>
          <dd class="col-sm-9"><%= String.format("%,d", p.getPrice()) %>원</dd>
          <dt class="col-sm-3">재고</dt>
          <dd class="col-sm-9"><%= p.getStock() %></dd>
          <dt class="col-sm-3">카테고리</dt>
          <dd class="col-sm-9"><%= p.getCategory() %></dd>
          <dt class="col-sm-3">설명</dt>
          <dd class="col-sm-9" style="white-space: pre-wrap;"><%= p.getDescription() != null ? p.getDescription() : "-" %></dd>
          <dt class="col-sm-3">등록일</dt>
          <dd class="col-sm-9"><%= p.getRegDate() %></dd>
        </dl>
        <hr>
        <div class="d-flex gap-2 flex-wrap">
          <a href="productList.jsp" class="btn btn-outline-secondary">목록</a>
          <a href="productUpdate.jsp?id=<%= p.getId() %>" class="btn btn-primary">수정</a>
          <form method="post" action="productDeleteProcess.jsp" class="d-inline"
                onsubmit="return confirm('정말 삭제할까요?');">
            <input type="hidden" name="id" value="<%= p.getId() %>">
            <button type="submit" class="btn btn-outline-danger">삭제</button>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>

<%@ include file="/include/footer.jsp" %>
