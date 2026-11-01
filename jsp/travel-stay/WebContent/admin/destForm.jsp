<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.DestinationDAO" %>
<%@ page import="dto.DestinationDTO" %>
<%@ include file="adminCheck.jspf" %>
<%
    DestinationDTO d = null;
    String idParam = request.getParameter("id");
    if (idParam != null && !idParam.isEmpty()) {
        d = new DestinationDAO().selectById(Integer.parseInt(idParam));
    }
    boolean edit = d != null;
%>
<%@ include file="/include/header.jsp" %>

<h1 class="h4 mb-3"><%= edit ? "여행지 수정" : "여행지 등록" %></h1>
<form method="post" action="destSaveProcess.jsp" enctype="multipart/form-data" class="card card-body shadow-sm col-md-8">
<% if (edit) { %>
  <input type="hidden" name="id" value="<%= d.getId() %>">
<% } %>
  <div class="mb-3">
    <label class="form-label">이름 *</label>
    <input type="text" name="name" class="form-control" required
           value="<%= edit ? d.getName() : "" %>">
  </div>
  <div class="mb-3">
    <label class="form-label">지역 *</label>
    <input type="text" name="region" class="form-control" required placeholder="예: 서울, 부산, 제주"
           value="<%= edit ? d.getRegion() : "" %>">
  </div>
  <div class="mb-3">
    <label class="form-label">설명 *</label>
    <textarea name="description" class="form-control" rows="4" required><%= edit ? d.getDescription() : "" %></textarea>
  </div>
  <div class="mb-3">
    <label class="form-label">이미지 URL</label>
    <input type="text" name="imageUrl" class="form-control"
           value="<%= edit && d.getImage() != null ? d.getImage() : "" %>"
           placeholder="https://... 또는 비우고 파일 업로드">
  </div>
  <div class="mb-3">
    <label class="form-label">이미지 파일 업로드 (선택, COS)</label>
    <input type="file" name="imageFile" class="form-control" accept="image/*">
    <div class="form-text">파일 선택 시 URL보다 우선합니다. 저장 위치: uploads/</div>
  </div>
  <button type="submit" class="btn btn-success"><%= edit ? "저장" : "등록" %></button>
  <a href="destList.jsp" class="btn btn-outline-secondary">목록</a>
</form>
<%@ include file="/include/footer.jsp" %>
