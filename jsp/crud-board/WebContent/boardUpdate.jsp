<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.BoardDAO" %>
<%@ page import="dto.BoardDTO" %>
<%
  String idParam = request.getParameter("id");
  if (idParam == null || idParam.isEmpty()) {
    response.sendRedirect("boardList.jsp");
    return;
  }
  int id = Integer.parseInt(idParam);
  BoardDTO board = new BoardDAO().selectById(id);
  if (board == null) {
    response.sendRedirect("boardList.jsp");
    return;
  }
%>
<%@ include file="include/header.jsp" %>

<h1 class="h3 mb-3">글 수정</h1>

<div class="card shadow-sm">
  <div class="card-body">
    <form action="boardUpdateProcess.jsp" method="post" class="row g-3">
      <input type="hidden" name="id" value="<%= board.getId() %>">
      <div class="col-md-6">
        <label class="form-label">작성자</label>
        <input type="text" name="writer" class="form-control" required
               value="<%= board.getWriter() %>">
      </div>
      <div class="col-md-6">
        <label class="form-label">비밀번호 확인</label>
        <input type="password" name="password" class="form-control" required
               placeholder="등록 시 입력한 비밀번호">
      </div>
      <div class="col-12">
        <label class="form-label">제목</label>
        <input type="text" name="title" class="form-control" required
               value="<%= board.getTitle() %>">
      </div>
      <div class="col-12">
        <label class="form-label">내용</label>
        <textarea name="content" class="form-control" rows="8" required><%= board.getContent() %></textarea>
      </div>
      <div class="col-12 d-flex gap-2">
        <button type="submit" class="btn btn-warning">수정 완료</button>
        <a href="boardView.jsp?id=<%= board.getId() %>" class="btn btn-outline-secondary">취소</a>
      </div>
    </form>
  </div>
</div>

<%@ include file="include/footer.jsp" %>
