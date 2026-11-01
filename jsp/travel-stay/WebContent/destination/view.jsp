<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="dao.DestinationDAO" %>
<%@ page import="dao.StayDAO" %>
<%@ page import="dao.ReviewDAO" %>
<%@ page import="dao.WishlistDAO" %>
<%@ page import="dto.DestinationDTO" %>
<%@ page import="dto.StayDTO" %>
<%@ page import="dto.ReviewDTO" %>
<%
    String idParam = request.getParameter("id");
    if (idParam == null) {
        response.sendRedirect("list.jsp");
        return;
    }
    int id = Integer.parseInt(idParam.trim());
    DestinationDTO d = new DestinationDAO().selectById(id);
    if (d == null) {
        response.sendRedirect("list.jsp");
        return;
    }
    List<StayDTO> stays = new StayDAO().selectByDestination(id);
    List<ReviewDTO> reviews = new ReviewDAO().selectByDestination(id);
    String sid = (String) session.getAttribute("loginId");
    boolean wished = false;
    if (sid != null) {
        wished = new WishlistDAO().exists(sid, id);
    }
%>
<%@ include file="/include/header.jsp" %>
<%@ include file="/include/imgUtil.jspf" %>

<div class="row g-4">
  <div class="col-md-5">
    <img class="img-fluid rounded border" src="<%= imgUrl(request, d.getImage()) %>" alt="">
  </div>
  <div class="col-md-7">
    <span class="badge text-bg-success"><%= d.getRegion() %></span>
    <h1 class="h3 mt-2"><%= d.getName() %></h1>
    <p class="text-muted">
      <% if (d.getAvgRating() != null) { %>★ <%= d.getAvgRating() %> · 리뷰 <%= d.getReviewCount() %>개 · <% } %>
      숙소 <%= d.getStayCount() %>곳
    </p>
    <p style="white-space:pre-wrap;"><%= d.getDescription() %></p>
    <div class="d-flex gap-2 flex-wrap">
      <a href="list.jsp" class="btn btn-outline-secondary">목록</a>
<% if (sid != null) { %>
      <form method="post" action="<%= ctx %>/wishlist/toggleProcess.jsp" class="d-inline">
        <input type="hidden" name="destinationId" value="<%= d.getId() %>">
        <button type="submit" class="btn <%= wished ? "btn-warning" : "btn-outline-warning" %>">
          <%= wished ? "★ 찜됨" : "☆ 찜하기" %>
        </button>
      </form>
<% } else { %>
      <a class="btn btn-outline-warning" href="<%= ctx %>/member/login.jsp">로그인 후 찜</a>
<% } %>
    </div>
  </div>
</div>

<hr class="my-4">
<h2 class="h5">주변 숙소</h2>
<% if (stays.isEmpty()) { %>
<p class="text-muted">등록된 숙소가 없습니다.</p>
<% } else { %>
<div class="table-responsive">
  <table class="table table-hover bg-white">
    <thead><tr><th>숙소</th><th>주소</th><th>1박</th><th></th></tr></thead>
    <tbody>
<% for (StayDTO s : stays) { %>
      <tr>
        <td><%= s.getName() %></td>
        <td><%= s.getAddress() %></td>
        <td><%= String.format("%,d", s.getPrice()) %>원</td>
        <td class="small text-muted"><%= s.getDescription() != null ? s.getDescription() : "" %></td>
      </tr>
<% } %>
    </tbody>
  </table>
</div>
<% } %>

<hr class="my-4">
<h2 class="h5">리뷰 · 평점</h2>
<% if (sid != null) { %>
<form method="post" action="<%= ctx %>/review/writeProcess.jsp" class="card card-body mb-3 shadow-sm">
  <input type="hidden" name="destinationId" value="<%= d.getId() %>">
  <div class="row g-2 align-items-end">
    <div class="col-md-2">
      <label class="form-label">평점</label>
      <select name="rating" class="form-select" required>
        <option value="5">5</option><option value="4">4</option>
        <option value="3">3</option><option value="2">2</option><option value="1">1</option>
      </select>
    </div>
    <div class="col-md-8">
      <label class="form-label">내용</label>
      <input type="text" name="content" class="form-control" maxlength="500" required>
    </div>
    <div class="col-md-2">
      <button type="submit" class="btn btn-success w-100">등록</button>
    </div>
  </div>
</form>
<% } else { %>
<p class="small text-muted">리뷰는 로그인 후 작성할 수 있습니다.</p>
<% } %>

<ul class="list-group">
<% for (ReviewDTO r : reviews) { %>
  <li class="list-group-item">
    <strong>★ <%= r.getRating() %></strong>
    <span class="text-muted small"><%= r.getMemberName() %> · <%= r.getRegDate() %></span>
    <div><%= r.getContent() %></div>
  </li>
<% } %>
<% if (reviews.isEmpty()) { %>
  <li class="list-group-item text-muted">아직 리뷰가 없습니다.</li>
<% } %>
</ul>

<%@ include file="/include/footer.jsp" %>
