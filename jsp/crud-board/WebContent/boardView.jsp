<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.BoardDAO" %>
<%@ page import="dto.BoardDTO" %>
<%
  String idParam = request.getParameter("id");
  if (idParam == null || idParam.isEmpty()) {
    response.sendRedirect("boardList.jsp");
    return;
  }
  int id = Integer.parseInt(idParam);

  BoardDAO dao = new BoardDAO();
  dao.increaseHit(id);
  BoardDTO board = dao.selectById(id);

  if (board == null) {
    response.sendRedirect("boardList.jsp");
    return;
  }
%>
<%@ include file="include/header.jsp" %>

<div class="d-flex justify-content-between align-items-center mb-3">
  <h1 class="h3 mb-0">글 상세</h1>
  <a href="boardList.jsp" class="btn btn-outline-secondary btn-sm">목록</a>
</div>

<div class="card shadow-sm mb-3">
  <div class="card-header bg-white">
    <h2 class="h5 mb-1"><%= board.getTitle() %></h2>
    <div class="small text-muted">
      작성자 <strong><%= board.getWriter() %></strong>
      · 조회 <%= board.getHit() %>
      · <%= board.getRegDate() %>
      <% if (board.getModDate() != null) { %>
        · 수정 <%= board.getModDate() %>
      <% } %>
    </div>
  </div>
  <div class="card-body" style="white-space: pre-wrap; min-height: 180px;"><%= board.getContent() %></div>
</div>

<div class="d-flex gap-2">
  <a href="boardUpdate.jsp?id=<%= board.getId() %>" class="btn btn-warning">수정</a>
  <a href="boardDelete.jsp?id=<%= board.getId() %>" class="btn btn-danger">삭제</a>
  <a href="boardList.jsp" class="btn btn-secondary">목록</a>
</div>

<%@ include file="include/footer.jsp" %>
