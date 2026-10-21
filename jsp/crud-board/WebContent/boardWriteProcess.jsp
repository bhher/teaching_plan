<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.BoardDAO" %>
<%@ page import="dto.BoardDTO" %>
<%
  request.setCharacterEncoding("UTF-8");

  BoardDTO board = new BoardDTO();
  board.setTitle(request.getParameter("title"));
  board.setContent(request.getParameter("content"));
  board.setWriter(request.getParameter("writer"));
  board.setPassword(request.getParameter("password"));

  BoardDAO dao = new BoardDAO();
  int result = dao.insert(board);

  if (result > 0) {
    response.sendRedirect("boardList.jsp");
  } else {
%>
<%@ include file="include/header.jsp" %>
<div class="alert alert-danger">등록에 실패했습니다.</div>
<a href="boardWrite.jsp" class="btn btn-secondary">다시 작성</a>
<%@ include file="include/footer.jsp" %>
<%
  }
%>
