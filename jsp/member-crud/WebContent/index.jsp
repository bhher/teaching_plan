<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp" %>

<div class="row justify-content-center">
  <div class="col-md-8">
    <div class="p-4 bg-white border rounded shadow-sm">
      <h1 class="h3 mb-3">회원 로그인 · 회원가입 · CRUD</h1>
      <p class="text-muted mb-4">
        MySQL + JSP + DAO 패턴. 세션으로 로그인 상태를 유지합니다.
      </p>
      <ul class="mb-4">
        <li>회원가입 (Create)</li>
        <li>로그인 / 로그아웃 (Session)</li>
        <li>회원목록 · 상세 (Read)</li>
        <li>내 정보 수정 (Update)</li>
        <li>회원 삭제 (Delete)</li>
      </ul>
      <div class="d-flex gap-2 flex-wrap">
        <a class="btn btn-primary" href="<%= request.getContextPath() %>/member/list.jsp">회원목록</a>
<% if (loginId == null) { %>
        <a class="btn btn-outline-primary" href="<%= request.getContextPath() %>/member/login.jsp">로그인</a>
        <a class="btn btn-outline-secondary" href="<%= request.getContextPath() %>/member/join.jsp">회원가입</a>
<% } else { %>
        <a class="btn btn-outline-primary" href="<%= request.getContextPath() %>/member/view.jsp?id=<%= loginId %>">내 정보</a>
<% } %>
      </div>
      <hr class="my-4">
      <p class="small text-muted mb-0">
        테스트 계정: <code>admin / 1234</code>, <code>hong / 1234</code>
      </p>
    </div>
  </div>
</div>

<%@ include file="/include/footer.jsp" %>
