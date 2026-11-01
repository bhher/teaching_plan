<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.BoardDAO" %>
<%@ page import="dto.BoardDTO" %>
<%
    String loginIdCheck = (String) session.getAttribute("loginId");
    if (loginIdCheck == null) {
        response.sendRedirect(request.getContextPath() + "/member/login.jsp");
        return;
    }

    String idParam = request.getParameter("id");
    if (idParam == null) {
        response.sendRedirect("list.jsp");
        return;
    }
    int id = Integer.parseInt(idParam.trim());
    BoardDTO b = new BoardDAO().selectById(id);
    if (b == null) {
        response.sendRedirect("list.jsp");
        return;
    }

    boolean isAdmin = "admin".equals(loginIdCheck);
    if (!loginIdCheck.equals(b.getWriterId()) && !isAdmin) {
        response.sendRedirect("view.jsp?id=" + id);
        return;
    }
%>
<%@ include file="/include/header.jsp" %>

<div class="row justify-content-center">
  <div class="col-md-8">
    <div class="card shadow-sm">
      <div class="card-body p-4">
        <h1 class="h4 mb-3">글 수정</h1>
        <form method="post" action="updateProcess.jsp">
          <input type="hidden" name="id" value="<%= b.getId() %>">
          <div class="mb-3">
            <label class="form-label">작성자</label>
            <input type="text" class="form-control"
                   value="<%= b.getWriterName() %> (<%= b.getWriterId() %>)" disabled>
          </div>
          <div class="mb-3">
            <label class="form-label">제목</label>
            <input type="text" name="title" class="form-control"
                   value="<%= b.getTitle() %>" required>
          </div>
          <div class="mb-3">
            <label class="form-label">내용</label>
            <textarea name="content" class="form-control" rows="8" required><%= b.getContent() %></textarea>
          </div>
          <div class="d-flex gap-2">
            <button type="submit" class="btn btn-primary">저장</button>
            <a class="btn btn-outline-secondary" href="view.jsp?id=<%= b.getId() %>">취소</a>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>

<%@ include file="/include/footer.jsp" %>
