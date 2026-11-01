<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="dao.DestinationDAO" %>
<%@ page import="dto.DestinationDTO" %>
<%@ include file="adminCheck.jspf" %>
<%@ include file="/include/header.jsp" %>
<%
    List<DestinationDTO> list = new DestinationDAO().search("", "");
%>
<div class="d-flex justify-content-between mb-3">
  <h1 class="h4 mb-0">여행지 관리</h1>
  <a href="destForm.jsp" class="btn btn-sm btn-success">등록</a>
</div>
<table class="table table-hover bg-white shadow-sm">
  <thead><tr><th>번호</th><th>이름</th><th>지역</th><th>숙소</th><th></th></tr></thead>
  <tbody>
<% for (DestinationDTO d : list) { %>
    <tr>
      <td><%= d.getId() %></td>
      <td><%= d.getName() %></td>
      <td><%= d.getRegion() %></td>
      <td><%= d.getStayCount() %></td>
      <td class="text-end">
        <a href="destForm.jsp?id=<%= d.getId() %>" class="btn btn-sm btn-outline-secondary">수정</a>
        <form method="post" action="destDeleteProcess.jsp" class="d-inline"
              onsubmit="return confirm('삭제할까요? 숙소·리뷰·찜도 함께 삭제됩니다.');">
          <input type="hidden" name="id" value="<%= d.getId() %>">
          <button type="submit" class="btn btn-sm btn-outline-danger">삭제</button>
        </form>
      </td>
    </tr>
<% } %>
  </tbody>
</table>
<a href="index.jsp" class="btn btn-outline-secondary btn-sm">관리자 홈</a>
<%@ include file="/include/footer.jsp" %>
