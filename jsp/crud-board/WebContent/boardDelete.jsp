<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
  String id = request.getParameter("id");
  if (id == null || id.isEmpty()) {
    response.sendRedirect("boardList.jsp");
    return;
  }
%>
<%@ include file="include/header.jsp" %>

<h1 class="h3 mb-3">글 삭제</h1>

<div class="card shadow-sm border-danger">
  <div class="card-body">
    <p class="text-danger">삭제하면 복구할 수 없습니다. 비밀번호를 입력하세요.</p>
    <form action="boardDeleteProcess.jsp" method="post" class="row g-3">
      <input type="hidden" name="id" value="<%= id %>">
      <div class="col-md-6">
        <label class="form-label">비밀번호</label>
        <input type="password" name="password" class="form-control" required>
      </div>
      <div class="col-12 d-flex gap-2">
        <button type="submit" class="btn btn-danger"
                onclick="return confirm('정말 삭제하시겠습니까?');">삭제</button>
        <a href="boardView.jsp?id=<%= id %>" class="btn btn-outline-secondary">취소</a>
      </div>
    </form>
  </div>
</div>

<%@ include file="include/footer.jsp" %>
