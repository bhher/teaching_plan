<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="dao.BoardDAO" %>
<%@ page import="dto.BoardDTO" %>
<%
  List<BoardDTO> list = new BoardDAO().selectAll();
%>
<%@ include file="include/header.jsp" %>

<div class="d-flex justify-content-between mb-3">
  <h1 class="h4">목록</h1>
  <a href="write.jsp" class="btn btn-primary btn-sm">글쓰기</a>
</div>

<table class="table table-bordered table-hover bg-white">
  <thead class="table-light">
    <tr>
      <th style="width:80px">id</th>
      <th>title</th>
    </tr>
  </thead>
  <tbody>
  <% if (list.isEmpty()) { %>
    <tr><td colspan="2" class="text-center text-muted">글 없음</td></tr>
  <% } else {
       for (BoardDTO b : list) { %>
    <tr>
      <td><%= b.getId() %></td>
      <td><a href="view.jsp?id=<%= b.getId() %>"><%= b.getTitle() %></a></td>
    </tr>
  <%   }
     } %>
  </tbody>
</table>

<%@ include file="include/footer.jsp" %>
