<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="dao.StayDAO" %>
<%@ page import="dto.StayDTO" %>
<%@ include file="adminCheck.jspf" %>
<%@ include file="/include/header.jsp" %>
<%
    List<StayDTO> list = new StayDAO().selectAll();
%>
<div class="d-flex justify-content-between mb-3">
  <h1 class="h4 mb-0">숙소 관리</h1>
  <a href="stayForm.jsp" class="btn btn-sm btn-success">등록</a>
</div>
<table class="table table-hover bg-white shadow-sm">
  <thead><tr><th>번호</th><th>숙소</th><th>여행지</th><th>가격</th><th></th></tr></thead>
  <tbody>
<% for (StayDTO s : list) { %>
    <tr>
      <td><%= s.getId() %></td>
      <td><%= s.getName() %></td>
      <td><%= s.getDestinationName() %></td>
      <td><%= String.format("%,d", s.getPrice()) %></td>
      <td class="text-end">
        <a href="stayForm.jsp?id=<%= s.getId() %>" class="btn btn-sm btn-outline-secondary">수정</a>
        <form method="post" action="stayDeleteProcess.jsp" class="d-inline"
              onsubmit="return confirm('삭제할까요?');">
          <input type="hidden" name="id" value="<%= s.getId() %>">
          <button type="submit" class="btn btn-sm btn-outline-danger">삭제</button>
        </form>
      </td>
    </tr>
<% } %>
  </tbody>
</table>
<a href="index.jsp" class="btn btn-outline-secondary btn-sm">관리자 홈</a>
<%@ include file="/include/footer.jsp" %>
