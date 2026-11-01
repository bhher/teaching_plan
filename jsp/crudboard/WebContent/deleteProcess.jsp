<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.BoardDAO" %>
<%
  int id = Integer.parseInt(request.getParameter("id"));
  new BoardDAO().delete(id);
  response.sendRedirect("list.jsp");
%>
