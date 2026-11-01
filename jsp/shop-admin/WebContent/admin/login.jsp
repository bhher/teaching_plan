<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    if (session.getAttribute("adminId") != null) {
        response.sendRedirect(request.getContextPath() + "/admin/productList.jsp");
        return;
    }
%>
<%@ include file="/include/header.jsp" %>

<div class="row justify-content-center">
  <div class="col-md-5">
    <div class="card shadow-sm">
      <div class="card-body p-4">
        <h1 class="h4 mb-3 text-center">관리자 로그인</h1>
<%
    String err = request.getParameter("error");
    if ("1".equals(err)) {
%>
        <div class="alert alert-danger py-2">아이디 또는 비밀번호가 올바르지 않습니다.</div>
<% } else if ("empty".equals(err)) { %>
        <div class="alert alert-warning py-2">아이디와 비밀번호를 입력하세요.</div>
<% } %>
        <form method="post" action="loginProcess.jsp">
          <div class="mb-3">
            <label class="form-label">아이디</label>
            <input type="text" name="id" class="form-control" required autofocus
                   autocomplete="username">
          </div>
          <div class="mb-3">
            <label class="form-label">비밀번호</label>
            <input type="password" name="password" class="form-control" required
                   autocomplete="current-password">
          </div>
          <button type="submit" class="btn btn-dark w-100">로그인</button>
        </form>
        <p class="mt-3 mb-0 small text-muted text-center">
          테스트 계정: <code>admin</code> / <code>1234</code>
        </p>
      </div>
    </div>
  </div>
</div>

<%@ include file="/include/footer.jsp" %>
