<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.BoardDAO" %>
<%@ page import="dto.BoardDTO" %>
<%
  int id = Integer.parseInt(request.getParameter("id"));
  BoardDTO board = new BoardDAO().selectById(id);
  if (board == null) {
    response.sendRedirect("list.jsp");
    return;
  }
%>
<%@ include file="include/header.jsp" %>

<h1 class="h4 mb-3">수정</h1>
<form action="updateProcess.jsp" method="post" class="card card-body shadow-sm">
  <input type="hidden" name="id" value="<%= board.getId() %>">
  <div class="mb-3">
    <label class="form-label">title</label>
    <input type="text" name="title" class="form-control" required value="<%= board.getTitle() %>">
  </div>
  <div class="mb-3">
    <label class="form-label">content</label>
    <textarea name="content" class="form-control" rows="6" required><%= board.getContent() %></textarea>
  </div>
  <button type="submit" class="btn btn-warning">수정</button>
  <a href="view.jsp?id=<%= board.getId() %>" class="btn btn-outline-secondary">취소</a>
</form>

<%@ include file="include/footer.jsp" %>
