<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.BoardDAO" %>
<%@ page import="dto.BoardDTO" %>
<%
  request.setCharacterEncoding("UTF-8");
  BoardDTO board = new BoardDTO();
  board.setId(Integer.parseInt(request.getParameter("id")));
  board.setTitle(request.getParameter("title"));
  board.setContent(request.getParameter("content"));
  new BoardDAO().update(board);
  response.sendRedirect("view.jsp?id=" + board.getId());
%>
