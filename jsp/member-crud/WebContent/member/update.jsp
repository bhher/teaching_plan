<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.MemberDAO" %>
<%@ page import="dto.MemberDTO" %>
<%
    String loginIdCheck = (String) session.getAttribute("loginId");
    if (loginIdCheck == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    String id = request.getParameter("id");
    if (id == null || id.trim().isEmpty()) {
        response.sendRedirect("list.jsp");
        return;
    }
    id = id.trim();

    boolean isAdmin = "admin".equals(loginIdCheck);
    if (!loginIdCheck.equals(id) && !isAdmin) {
        response.sendRedirect("view.jsp?id=" + id);
        return;
    }

    MemberDAO dao = new MemberDAO();
    MemberDTO m = dao.selectById(id);
    if (m == null) {
        response.sendRedirect("list.jsp");
        return;
    }
%>
<%@ include file="/include/header.jsp" %>

<div class="row justify-content-center">
  <div class="col-md-6">
    <div class="card shadow-sm">
      <div class="card-body p-4">
        <h1 class="h4 mb-3">회원 수정</h1>
        <form method="post" action="updateProcess.jsp">
          <input type="hidden" name="id" value="<%= m.getId() %>">
          <div class="mb-3">
            <label class="form-label">아이디</label>
            <input type="text" class="form-control" value="<%= m.getId() %>" disabled>
          </div>
          <div class="mb-3">
            <label class="form-label">비밀번호</label>
            <input type="password" name="password" class="form-control" value="<%= m.getPassword() %>" required>
          </div>
          <div class="mb-3">
            <label class="form-label">이름</label>
            <input type="text" name="name" class="form-control" value="<%= m.getName() %>" required>
          </div>
          <div class="mb-3">
            <label class="form-label">이메일</label>
            <input type="email" name="email" class="form-control"
                   value="<%= m.getEmail() != null ? m.getEmail() : "" %>">
          </div>
          <div class="d-flex gap-2">
            <button type="submit" class="btn btn-primary">저장</button>
            <a class="btn btn-outline-secondary" href="view.jsp?id=<%= m.getId() %>">취소</a>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>

<%@ include file="/include/footer.jsp" %>
