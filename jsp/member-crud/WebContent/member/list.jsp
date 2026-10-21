<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="dao.MemberDAO" %>
<%@ page import="dto.MemberDTO" %>
<%@ include file="/include/header.jsp" %>
<%
    MemberDAO dao = new MemberDAO();
    List<MemberDTO> list = dao.selectAll();
%>

<div class="d-flex justify-content-between align-items-center mb-3">
  <h1 class="h4 mb-0">회원목록</h1>
<% if (loginId == null) { %>
  <a class="btn btn-sm btn-primary" href="join.jsp">회원가입</a>
<% } %>
</div>

<div class="card shadow-sm">
  <div class="table-responsive">
    <table class="table table-hover mb-0 align-middle">
      <thead class="table-light">
        <tr>
          <th>아이디</th>
          <th>이름</th>
          <th>이메일</th>
          <th>가입일</th>
          <th class="text-end">관리</th>
        </tr>
      </thead>
      <tbody>
<%
    if (list.isEmpty()) {
%>
        <tr><td colspan="5" class="text-center text-muted py-4">회원이 없습니다.</td></tr>
<%
    } else {
        for (MemberDTO m : list) {
%>
        <tr>
          <td><a href="view.jsp?id=<%= m.getId() %>"><%= m.getId() %></a></td>
          <td><%= m.getName() %></td>
          <td><%= m.getEmail() != null ? m.getEmail() : "-" %></td>
          <td class="small text-muted"><%= m.getRegDate() %></td>
          <td class="text-end">
            <a class="btn btn-sm btn-outline-secondary" href="view.jsp?id=<%= m.getId() %>">상세</a>
          </td>
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
