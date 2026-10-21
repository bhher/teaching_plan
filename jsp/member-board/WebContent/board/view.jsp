<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.BoardDAO" %>
<%@ page import="dto.BoardDTO" %>
<%
    String idParam = request.getParameter("id");
    if (idParam == null || idParam.trim().isEmpty()) {
        response.sendRedirect("list.jsp");
        return;
    }

    int id;
    try {
        id = Integer.parseInt(idParam.trim());
    } catch (NumberFormatException e) {
        response.sendRedirect("list.jsp");
        return;
    }

    BoardDTO b = new BoardDAO().selectById(id);
    if (b == null) {
%>
<%@ include file="/include/header.jsp" %>
<div class="alert alert-warning">존재하지 않는 글입니다. <a href="list.jsp">목록</a></div>
<%@ include file="/include/footer.jsp" %>
<%
        return;
    }

    String sid = (String) session.getAttribute("loginId");
    boolean canEdit = sid != null
            && (sid.equals(b.getWriterId()) || "admin".equals(sid));
%>
<%@ include file="/include/header.jsp" %>

<div class="card shadow-sm">
  <div class="card-body p-4">
    <h1 class="h4"><%= b.getTitle() %></h1>
    <p class="text-muted small mb-3">
      작성자: <%= b.getWriterName() %> (<%= b.getWriterId() %>)
      · <%= b.getRegDate() %>
    </p>
    <hr>
    <div class="mb-4" style="white-space: pre-wrap;"><%= b.getContent() %></div>
    <div class="d-flex gap-2 flex-wrap">
      <a class="btn btn-outline-secondary" href="list.jsp">목록</a>
<% if (canEdit) { %>
      <a class="btn btn-primary" href="update.jsp?id=<%= b.getId() %>">수정</a>
      <form method="post" action="deleteProcess.jsp" class="d-inline"
            onsubmit="return confirm('정말 삭제할까요?');">
        <input type="hidden" name="id" value="<%= b.getId() %>">
        <button type="submit" class="btn btn-outline-danger">삭제</button>
      </form>
<% } else if (sid == null) { %>
      <span class="text-muted small align-self-center">수정·삭제는 작성자 로그인 후 가능합니다.</span>
<% } %>
    </div>
  </div>
</div>

<%@ include file="/include/footer.jsp" %>
