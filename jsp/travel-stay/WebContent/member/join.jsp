<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    if (session.getAttribute("loginId") != null) {
        response.sendRedirect(request.getContextPath() + "/destination/list.jsp");
        return;
    }
%>
<%@ include file="/include/header.jsp" %>
<div class="row justify-content-center">
  <div class="col-md-6">
    <div class="card shadow-sm">
      <div class="card-body p-4">
        <h1 class="h4 mb-3">회원가입</h1>
<%
    String err = request.getParameter("error");
    if ("dup".equals(err)) {
%>
        <div class="alert alert-warning py-2">이미 사용 중인 아이디입니다.</div>
<% } else if ("empty".equals(err)) { %>
        <div class="alert alert-danger py-2">필수 항목을 입력하세요.</div>
<% } %>
        <form method="post" action="joinProcess.jsp">
          <div class="mb-3"><label class="form-label">아이디</label>
            <input type="text" name="id" class="form-control" required></div>
          <div class="mb-3"><label class="form-label">비밀번호</label>
            <input type="password" name="password" class="form-control" required></div>
          <div class="mb-3"><label class="form-label">이름</label>
            <input type="text" name="name" class="form-control" required></div>
          <button type="submit" class="btn btn-success w-100">가입</button>
        </form>
      </div>
    </div>
  </div>
</div>
<%@ include file="/include/footer.jsp" %>
