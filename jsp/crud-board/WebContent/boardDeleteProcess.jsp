<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.BoardDAO" %>
<%
  request.setCharacterEncoding("UTF-8");

  int id = Integer.parseInt(request.getParameter("id"));
  String password = request.getParameter("password");

  int result = new BoardDAO().delete(id, password);

  if (result > 0) {
    response.sendRedirect("boardList.jsp");
  } else {
%>
<%@ include file="include/header.jsp" %>
<div class="alert alert-danger">
  삭제에 실패했습니다. 비밀번호를 확인하세요.
</div>
<a href="boardDelete.jsp?id=<%= id %>" class="btn btn-danger">다시 시도</a>
<a href="boardView.jsp?id=<%= id %>" class="btn btn-secondary">상세로</a>
<%@ include file="include/footer.jsp" %>
<%
  }
%>
