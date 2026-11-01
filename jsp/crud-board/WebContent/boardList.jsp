<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="dao.BoardDAO" %>
<%@ page import="dto.BoardDTO" %>
<%
  BoardDAO dao = new BoardDAO();
  List<BoardDTO> list = dao.selectAll();
%>
<%@ include file="include/header.jsp" %>

<div class="d-flex justify-content-between align-items-center mb-3">
  <h1 class="h3 mb-0">게시판 목록</h1>
  <a href="boardWrite.jsp" class="btn btn-primary">글쓰기</a>
</div>

<div class="card shadow-sm">
  <div class="table-responsive">
    <table class="table table-hover mb-0 align-middle">
      <thead class="table-dark">
        <tr>
          <th style="width:80px">번호</th>
          <th>제목</th>
          <th style="width:120px">작성자</th>
          <th style="width:80px">조회</th>
          <th style="width:160px">작성일</th>
        </tr>
      </thead>
      <tbody>
      <%
        if (list == null || list.isEmpty()) {
      %>
        <tr>
          <td colspan="5" class="text-center text-muted py-4">등록된 글이 없습니다.</td>
        </tr>
      <%
        } else {
          for (BoardDTO b : list) {
      %>
        <tr>
          <td><%= b.getId() %></td>
          <td>
            <a href="boardView.jsp?id=<%= b.getId() %>" class="text-decoration-none">
              <%= b.getTitle() %>
            </a>
          </td>
          <td><%= b.getWriter() %></td>
          <td><%= b.getHit() %></td>
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

<%@ include file="include/footer.jsp" %>
