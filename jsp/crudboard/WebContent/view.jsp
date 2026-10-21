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

<h1 class="h4 mb-3"><%= board.getTitle() %></h1>
<div class="card card-body mb-3" style="white-space:pre-wrap;"><%= board.getContent() %></div>
<div class="d-flex gap-2">
  <a href="update.jsp?id=<%= board.getId() %>" class="btn btn-warning btn-sm">수정</a>
  <a href="deleteProcess.jsp?id=<%= board.getId() %>" class="btn btn-danger btn-sm"
     onclick="return confirm('삭제할까요?');">삭제</a>
  <a href="list.jsp" class="btn btn-secondary btn-sm">목록</a>
</div>

<%@ include file="include/footer.jsp" %>
