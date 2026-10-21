<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.MemberDAO" %>
<%@ page import="dto.MemberDTO" %>
<%
    String id = request.getParameter("id");
    if (id == null || id.trim().isEmpty()) {
        response.sendRedirect("list.jsp");
        return;
    }

    MemberDAO dao = new MemberDAO();
    MemberDTO m = dao.selectById(id.trim());
    if (m == null) {
%>
<%@ include file="/include/header.jsp" %>
<div class="alert alert-warning">존재하지 않는 회원입니다. <a href="list.jsp">목록</a></div>
<%@ include file="/include/footer.jsp" %>
<%
        return;
    }

    String sid = (String) session.getAttribute("loginId");
    boolean canEdit = sid != null && (sid.equals(m.getId()) || "admin".equals(sid));
%>
<%@ include file="/include/header.jsp" %>

<div class="row justify-content-center">
  <div class="col-md-7">
    <div class="card shadow-sm">
      <div class="card-body p-4">
        <h1 class="h4 mb-3">회원 상세</h1>
        <dl class="row mb-0">
          <dt class="col-sm-3">아이디</dt>
          <dd class="col-sm-9"><%= m.getId() %></dd>
          <dt class="col-sm-3">이름</dt>
          <dd class="col-sm-9"><%= m.getName() %></dd>
          <dt class="col-sm-3">이메일</dt>
          <dd class="col-sm-9"><%= m.getEmail() != null ? m.getEmail() : "-" %></dd>
          <dt class="col-sm-3">가입일</dt>
          <dd class="col-sm-9"><%= m.getRegDate() %></dd>
        </dl>
        <hr>
        <div class="d-flex gap-2 flex-wrap">
          <a class="btn btn-outline-secondary" href="list.jsp">목록</a>
<% if (canEdit) { %>
          <a class="btn btn-primary" href="update.jsp?id=<%= m.getId() %>">수정</a>
          <form method="post" action="deleteProcess.jsp" class="d-inline"
                onsubmit="return confirm('정말 삭제할까요?');">
            <input type="hidden" name="id" value="<%= m.getId() %>">
            <button type="submit" class="btn btn-outline-danger">삭제</button>
          </form>
<% } else if (sid == null) { %>
          <span class="text-muted small align-self-center">수정·삭제는 로그인 후 본인(또는 admin)만 가능합니다.</span>
<% } %>
        </div>
      </div>
    </div>
  </div>
</div>

<%@ include file="/include/footer.jsp" %>
