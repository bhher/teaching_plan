<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    if (session.getAttribute("loginId") == null) {
        response.sendRedirect(request.getContextPath() + "/member/login.jsp");
        return;
    }
%>
<%@ include file="/include/header.jsp" %>

<div class="row justify-content-center">
  <div class="col-md-8">
    <div class="card shadow-sm">
      <div class="card-body p-4">
        <h1 class="h4 mb-3">글쓰기</h1>
        <p class="small text-muted">작성자: <%= loginName %> (<%= loginId %>) — 세션에서 자동 저장</p>
        <form method="post" action="writeProcess.jsp">
          <div class="mb-3">
            <label class="form-label">제목</label>
            <input type="text" name="title" class="form-control" required maxlength="200">
          </div>
          <div class="mb-3">
            <label class="form-label">내용</label>
            <textarea name="content" class="form-control" rows="8" required></textarea>
          </div>
          <div class="d-flex gap-2">
            <button type="submit" class="btn btn-primary">등록</button>
            <a class="btn btn-outline-secondary" href="list.jsp">취소</a>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>

<%@ include file="/include/footer.jsp" %>
