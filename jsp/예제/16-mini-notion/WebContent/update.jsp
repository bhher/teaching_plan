<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="notion.NoteDTO" %>
<%
    NoteDTO note = (NoteDTO) request.getAttribute("note");
    String ctx = request.getContextPath();
    if (note == null) {
        response.sendRedirect(ctx + "/list.do");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>노트 수정 - 미니 노션</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: 'Malgun Gothic', sans-serif; background: #f7f6f3; padding: 24px; }
        .container { max-width: 700px; margin: 0 auto; }
        h1 { margin-bottom: 20px; color: #37352f; font-size: 1.5em; }
        .form-group { margin-bottom: 16px; }
        .form-group label { display: block; margin-bottom: 6px; font-weight: 500; color: #37352f; }
        .form-group input, .form-group textarea { width: 100%; padding: 10px 12px; border: 1px solid #e0e0e0; border-radius: 6px; font-size: 14px; }
        .form-group textarea { min-height: 200px; resize: vertical; }
        .form-group input:focus, .form-group textarea:focus { outline: none; border-color: #2383e2; box-shadow: 0 0 0 2px rgba(35,131,226,0.2); }
        .btn { padding: 10px 20px; border-radius: 6px; font-size: 14px; cursor: pointer; border: none; text-decoration: none; display: inline-block; }
        .btn-primary { background: #2383e2; color: white; }
        .btn-primary:hover { background: #1a6fc2; }
        .btn-secondary { background: #e9e9e7; color: #37352f; margin-left: 8px; }
        .btn-secondary:hover { background: #ddd; }
        .actions { margin-top: 20px; }
    </style>
</head>
<body>
    <div class="container">
        <h1>✏️ 노트 수정</h1>
        <form action="<%= ctx %>/updateProcess.do" method="post">
            <input type="hidden" name="id" value="<%= note.getId() %>">
            <div class="form-group">
                <label>제목</label>
                <input type="text" name="title" value="<%= note.getTitle() != null ? note.getTitle() : "" %>" required>
            </div>
            <div class="form-group">
                <label>내용</label>
                <textarea name="content"><%= note.getContent() != null ? note.getContent() : "" %></textarea>
            </div>
            <div class="form-group">
                <label>작성자</label>
                <input type="text" name="writer" value="<%= note.getWriter() != null ? note.getWriter() : "익명" %>">
            </div>
            <div class="actions">
                <button type="submit" class="btn btn-primary">저장</button>
                <a href="<%= ctx %>/list.do" class="btn btn-secondary">취소</a>
            </div>
        </form>
    </div>
</body>
</html>
