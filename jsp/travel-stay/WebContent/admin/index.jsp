<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="adminCheck.jspf" %>
<%@ include file="/include/header.jsp" %>

<h1 class="h4 mb-3">관리자</h1>
<div class="row g-3">
  <div class="col-md-6">
    <div class="card shadow-sm">
      <div class="card-body">
        <h2 class="h6">여행지 관리</h2>
        <p class="small text-muted">등록 · 수정 · 삭제 · 이미지 URL/업로드</p>
        <a href="destList.jsp" class="btn btn-success btn-sm">여행지 목록</a>
        <a href="destForm.jsp" class="btn btn-outline-success btn-sm">새 여행지</a>
      </div>
    </div>
  </div>
  <div class="col-md-6">
    <div class="card shadow-sm">
      <div class="card-body">
        <h2 class="h6">숙소 관리</h2>
        <p class="small text-muted">여행지에 연결된 숙소 CRUD</p>
        <a href="stayList.jsp" class="btn btn-success btn-sm">숙소 목록</a>
        <a href="stayForm.jsp" class="btn btn-outline-success btn-sm">새 숙소</a>
      </div>
    </div>
  </div>
</div>
<p class="small text-muted mt-3">업로드 파일은 <code>WebContent/uploads</code>에 저장됩니다. COS <code>cos.jar</code>가 필요합니다.</p>

<%@ include file="/include/footer.jsp" %>
