<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp" %>

<div class="p-4 bg-white border rounded shadow-sm mb-4">
  <h1 class="h3">여행지 소개 · 숙소 추천</h1>
  <p class="text-muted mb-3">지역별 검색 · 상세 · 숙소 · 찜 · 리뷰 · 관리자 CRUD · 이미지(URL/업로드)</p>
  <div class="d-flex gap-2 flex-wrap">
    <a class="btn btn-success" href="<%= ctx %>/destination/list.jsp">여행지 둘러보기</a>
<% if (loginId == null) { %>
    <a class="btn btn-outline-success" href="<%= ctx %>/member/login.jsp">로그인</a>
<% } else if (isAdmin) { %>
    <a class="btn btn-outline-dark" href="<%= ctx %>/admin/index.jsp">관리자</a>
<% } %>
  </div>
  <hr>
  <p class="small text-muted mb-0">
    사용자 <code>hong/1234</code> · 관리자 <code>admin/1234</code>
  </p>
</div>

<div class="row g-3">
  <div class="col-md-4">
    <div class="p-3 bg-white border rounded h-100">
      <h2 class="h6">사용자</h2>
      <p class="small text-muted mb-0">지역 검색, 상세·숙소, 찜, 리뷰·평점</p>
    </div>
  </div>
  <div class="col-md-4">
    <div class="p-3 bg-white border rounded h-100">
      <h2 class="h6">관리자</h2>
      <p class="small text-muted mb-0">여행지·숙소 등록/수정/삭제, 이미지 업로드(COS)</p>
    </div>
  </div>
  <div class="col-md-4">
    <div class="p-3 bg-white border rounded h-100">
      <h2 class="h6">학습 포인트</h2>
      <p class="small text-muted mb-0">CRUD, JOIN, 검색 조건, role, MultipartRequest</p>
    </div>
  </div>
</div>

<%@ include file="/include/footer.jsp" %>
