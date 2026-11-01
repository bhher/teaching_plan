<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="dao.DestinationDAO" %>
<%@ page import="dto.DestinationDTO" %>
<%@ include file="/include/header.jsp" %>
<%@ include file="/include/imgUtil.jspf" %>
<%
    String region = request.getParameter("region");
    String keyword = request.getParameter("keyword");
    if (region == null) region = "";
    if (keyword == null) keyword = "";
    DestinationDAO dao = new DestinationDAO();
    List<DestinationDTO> list = dao.search(region, keyword);
    List<String> regions = dao.selectRegions();
%>

<div class="d-flex justify-content-between align-items-center mb-3 flex-wrap gap-2">
  <h1 class="h4 mb-0">여행지 검색</h1>
</div>

<form class="row g-2 mb-4" method="get" action="list.jsp">
  <div class="col-md-3">
    <select name="region" class="form-select">
      <option value="">전체 지역</option>
<% for (String r : regions) { %>
      <option value="<%= r %>" <%= r.equals(region) ? "selected" : "" %>><%= r %></option>
<% } %>
    </select>
  </div>
  <div class="col-md-6">
    <input type="search" name="keyword" class="form-control" placeholder="여행지명·설명 검색"
           value="<%= keyword %>">
  </div>
  <div class="col-md-3">
    <button type="submit" class="btn btn-success w-100">검색</button>
  </div>
</form>

<div class="row g-3">
<% if (list.isEmpty()) { %>
  <p class="text-muted">검색 결과가 없습니다.</p>
<% } else {
    for (DestinationDTO d : list) {
%>
  <div class="col-md-4">
    <div class="card dest-card h-100 shadow-sm">
      <img src="<%= imgUrl(request, d.getImage()) %>" alt="<%= d.getName() %>">
      <div class="card-body">
        <span class="badge text-bg-success"><%= d.getRegion() %></span>
        <h2 class="h6 mt-2"><a class="text-decoration-none text-dark" href="view.jsp?id=<%= d.getId() %>"><%= d.getName() %></a></h2>
        <p class="small text-muted mb-1">숙소 <%= d.getStayCount() %>곳
          <% if (d.getAvgRating() != null) { %> · ★ <%= d.getAvgRating() %> (<%= d.getReviewCount() %>)<% } %>
        </p>
        <a href="view.jsp?id=<%= d.getId() %>" class="btn btn-sm btn-outline-success">상세</a>
      </div>
    </div>
  </div>
<%
    }
  }
%>
</div>

<%@ include file="/include/footer.jsp" %>
