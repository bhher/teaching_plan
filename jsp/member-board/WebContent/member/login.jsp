<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    if (session.getAttribute("loginId") != null) {
        response.sendRedirect(request.getContextPath() + "/board/list.jsp");
        return;
    }
%>
<%@ include file="/include/header.jsp" %>

<div class="row justify-content-center">
  <div class="col-md-5">
    <div class="card shadow-sm">
      <div class="card-body p-4">
        <h1 class="h4 mb-3">로그인</h1>
<% if ("1".equals(request.getParameter("error"))) { %>
        <div class="alert alert-danger py-2">아이디 또는 비밀번호가 올바르지 않습니다.</div>
<% } %>
        <form method="post" action="loginProcess.jsp">
          <div class="mb-3">
            <label class="form-label">아이디</label>
            <input type="text" name="id" class="form-control" required autofocus>
          </div>
          <div class="mb-3">
            <label class="form-label">비밀번호</label>
            <input type="password" name="password" class="form-control" required>
          </div>
          <button type="submit" class="btn btn-primary w-100">로그인</button>
        </form>
        <p class="mt-3 mb-0 small text-center">
          <a href="join.jsp">회원가입</a>
        </p>
      </div>
    </div>
  </div>
</div>

<%@ include file="/include/footer.jsp" %>
