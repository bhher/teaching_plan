<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="dao.BoardDAO" %>
<%@ page import="dto.BoardDTO" %>
<%@ include file="/include/header.jsp" %>
<%
    List<BoardDTO> list = new BoardDAO().selectAll();
%>

<div class="d-flex justify-content-between align-items-center mb-3">
  <h1 class="h4 mb-0">게시판</h1>
<% if (loginId != null) { %>
  <a class="btn btn-sm btn-primary" href="write.jsp">글쓰기</a>
<% } else { %>
  <a class="btn btn-sm btn-outline-secondary" href="<%= request.getContextPath() %>/member/login.jsp">로그인 후 글쓰기</a>
<% } %>
</div>

<div class="card shadow-sm">
  <div class="table-responsive">
    <table class="table table-hover mb-0 align-middle">
      <thead class="table-light">
        <tr>
          <th style="width:70px">번호</th>
          <th>제목</th>
          <th style="width:120px">작성자</th>
          <th style="width:160px">작성일</th>
        </tr>
      </thead>
      <tbody>
<%
    if (list.isEmpty()) {
%>
        <tr><td colspan="4" class="text-center text-muted py-4">게시글이 없습니다.</td></tr>
<%
    } else {
        for (BoardDTO b : list) {
%>
        <tr>
          <td><%= b.getId() %></td>
          <td><a href="view.jsp?id=<%= b.getId() %>"><%= b.getTitle() %></a></td>
          <td><%= b.getWriterName() %></td>
          <td class="small text-muted"><%= b.getRegDate() %></td>
        </tr>
<%
        }
    }
%>
      </tbody>
    </table>
  </div>
</div>

<%@ include file="/include/footer.jsp" %>
