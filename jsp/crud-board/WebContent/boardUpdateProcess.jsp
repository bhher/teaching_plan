<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.BoardDAO" %>
<%@ page import="dto.BoardDTO" %>
<%
  request.setCharacterEncoding("UTF-8");

  int id = Integer.parseInt(request.getParameter("id"));

  BoardDTO board = new BoardDTO();
  board.setId(id);
  board.setTitle(request.getParameter("title"));
  board.setContent(request.getParameter("content"));
  board.setWriter(request.getParameter("writer"));
  board.setPassword(request.getParameter("password"));

  int result = new BoardDAO().update(board);

  if (result > 0) {
    response.sendRedirect("boardView.jsp?id=" + id);
  } else {
%>
<%@ include file="include/header.jsp" %>
<div class="alert alert-danger">
  수정에 실패했습니다. 비밀번호를 확인하세요.
</div>
<a href="boardUpdate.jsp?id=<%= id %>" class="btn btn-warning">다시 시도</a>
<a href="boardView.jsp?id=<%= id %>" class="btn btn-secondary">상세로</a>
<%@ include file="include/footer.jsp" %>
<%
  }
%>
