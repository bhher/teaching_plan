<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp" %>

<div class="p-4 bg-white border rounded shadow-sm">
  <h1 class="h3 mb-3">회원 + 게시판 연동</h1>
  <p class="text-muted">
    회원가입·로그인(세션) 후 게시판에 글을 쓰고, <strong>본인 글만</strong> 수정·삭제할 수 있습니다.
  </p>
  <ul>
    <li>글쓰기: <strong>로그인 필수</strong> → 작성자는 세션의 <code>loginId</code> / <code>loginName</code></li>
    <li>수정·삭제: 작성자 본인 또는 <code>admin</code></li>
    <li>목록·상세: 누구나 조회</li>
  </ul>
  <div class="d-flex gap-2 flex-wrap mt-3">
    <a class="btn btn-primary" href="<%= request.getContextPath() %>/board/list.jsp">게시판</a>
<% if (loginId == null) { %>
    <a class="btn btn-outline-primary" href="<%= request.getContextPath() %>/member/login.jsp">로그인</a>
    <a class="btn btn-outline-secondary" href="<%= request.getContextPath() %>/member/join.jsp">회원가입</a>
<% } else { %>
    <a class="btn btn-success" href="<%= request.getContextPath() %>/board/write.jsp">글쓰기</a>
<% } %>
  </div>
  <hr>
  <p class="small text-muted mb-0">테스트: <code>admin/1234</code>, <code>hong/1234</code></p>
</div>

<%@ include file="/include/footer.jsp" %>
