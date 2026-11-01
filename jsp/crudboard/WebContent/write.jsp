<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="include/header.jsp" %>

<h1 class="h4 mb-3">글쓰기</h1>
<form action="writeProcess.jsp" method="post" class="card card-body shadow-sm">
  <div class="mb-3">
    <label class="form-label">title</label>
    <input type="text" name="title" class="form-control" required>
  </div>
  <div class="mb-3">
    <label class="form-label">content</label>
    <textarea name="content" class="form-control" rows="6" required></textarea>
  </div>
  <button type="submit" class="btn btn-primary">등록</button>
  <a href="list.jsp" class="btn btn-outline-secondary">목록</a>
</form>

<%@ include file="include/footer.jsp" %>
