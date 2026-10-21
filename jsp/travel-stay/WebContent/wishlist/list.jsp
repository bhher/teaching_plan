<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="dao.WishlistDAO" %>
<%@ page import="dto.DestinationDTO" %>
<%
    if (session.getAttribute("loginId") == null) {
        response.sendRedirect(request.getContextPath() + "/member/login.jsp");
        return;
    }
    String mid = (String) session.getAttribute("loginId");
    List<DestinationDTO> list = new WishlistDAO().selectByMember(mid);
%>
<%@ include file="/include/header.jsp" %>
<%@ include file="/include/imgUtil.jspf" %>

<h1 class="h4 mb-3">내 찜 목록</h1>
<% if (list.isEmpty()) { %>
<p class="text-muted">찜한 여행지가 없습니다.</p>
<% } else { %>
<div class="row g-3">
<% for (DestinationDTO d : list) { %>
  <div class="col-md-4">
    <div class="card dest-card h-100">
      <img src="<%= imgUrl(request, d.getImage()) %>" alt="">
      <div class="card-body">
        <span class="badge text-bg-success"><%= d.getRegion() %></span>
        <h2 class="h6 mt-2"><%= d.getName() %></h2>
        <a href="<%= ctx %>/destination/view.jsp?id=<%= d.getId() %>" class="btn btn-sm btn-outline-success">상세</a>
      </div>
    </div>
  </div>
<% } %>
</div>
<% } %>
<%@ include file="/include/footer.jsp" %>
