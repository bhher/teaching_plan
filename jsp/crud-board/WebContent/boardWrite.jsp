<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="include/header.jsp" %>

<h1 class="h3 mb-3">글쓰기</h1>

<div class="card shadow-sm">
  <div class="card-body">
    <form action="boardWriteProcess.jsp" method="post" class="row g-3">
      <div class="col-md-6">
        <label class="form-label">작성자</label>
        <input type="text" name="writer" class="form-control" required maxlength="50">
      </div>
      <div class="col-md-6">
        <label class="form-label">비밀번호 (수정/삭제용)</label>
        <input type="password" name="password" class="form-control" required maxlength="100">
      </div>
      <div class="col-12">
        <label class="form-label">제목</label>
        <input type="text" name="title" class="form-control" required maxlength="200">
      </div>
      <div class="col-12">
        <label class="form-label">내용</label>
        <textarea name="content" class="form-control" rows="8" required></textarea>
      </div>
      <div class="col-12 d-flex gap-2">
        <button type="submit" class="btn btn-primary">등록</button>
        <a href="boardList.jsp" class="btn btn-outline-secondary">목록</a>
      </div>
    </form>
  </div>
</div>

<%@ include file="include/footer.jsp" %>
