<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="dao.DestinationDAO" %>
<%@ page import="dao.StayDAO" %>
<%@ page import="dto.DestinationDTO" %>
<%@ page import="dto.StayDTO" %>
<%@ include file="adminCheck.jspf" %>
<%
    StayDTO s = null;
    String idParam = request.getParameter("id");
    if (idParam != null && !idParam.isEmpty()) {
        s = new StayDAO().selectById(Integer.parseInt(idParam));
    }
    boolean edit = s != null;
    List<DestinationDTO> dests = new DestinationDAO().search("", "");
%>
<%@ include file="/include/header.jsp" %>

<h1 class="h4 mb-3"><%= edit ? "숙소 수정" : "숙소 등록" %></h1>
<form method="post" action="staySaveProcess.jsp" enctype="multipart/form-data" class="card card-body shadow-sm col-md-8">
<% if (edit) { %>
  <input type="hidden" name="id" value="<%= s.getId() %>">
<% } %>
  <div class="mb-3">
    <label class="form-label">여행지 *</label>
    <select name="destinationId" class="form-select" required>
<% for (DestinationDTO d : dests) { %>
      <option value="<%= d.getId() %>"
        <%= edit && s.getDestinationId() == d.getId() ? "selected" : "" %>>
        <%= d.getName() %> (<%= d.getRegion() %>)
      </option>
<% } %>
    </select>
  </div>
  <div class="mb-3">
    <label class="form-label">숙소명 *</label>
    <input type="text" name="name" class="form-control" required value="<%= edit ? s.getName() : "" %>">
  </div>
  <div class="mb-3">
    <label class="form-label">주소 *</label>
    <input type="text" name="address" class="form-control" required value="<%= edit ? s.getAddress() : "" %>">
  </div>
  <div class="mb-3">
    <label class="form-label">1박 가격(원) *</label>
    <input type="number" name="price" class="form-control" required min="0"
           value="<%= edit ? s.getPrice() : 0 %>">
  </div>
  <div class="mb-3">
    <label class="form-label">설명</label>
    <textarea name="description" class="form-control" rows="3"><%= edit && s.getDescription() != null ? s.getDescription() : "" %></textarea>
  </div>
  <div class="mb-3">
    <label class="form-label">이미지 URL</label>
    <input type="text" name="imageUrl" class="form-control"
           value="<%= edit && s.getImage() != null ? s.getImage() : "" %>">
  </div>
  <div class="mb-3">
    <label class="form-label">이미지 파일 (선택)</label>
    <input type="file" name="imageFile" class="form-control" accept="image/*">
  </div>
  <button type="submit" class="btn btn-success"><%= edit ? "저장" : "등록" %></button>
  <a href="stayList.jsp" class="btn btn-outline-secondary">목록</a>
</form>
<%@ include file="/include/footer.jsp" %>
