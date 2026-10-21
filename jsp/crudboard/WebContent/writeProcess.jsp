<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.BoardDAO" %>
<%@ page import="dto.BoardDTO" %>
<%
  request.setCharacterEncoding("UTF-8");
  BoardDTO board = new BoardDTO();
  board.setTitle(request.getParameter("title"));
  board.setContent(request.getParameter("content"));
  new BoardDAO().insert(board);
  response.sendRedirect("list.jsp");
%>
