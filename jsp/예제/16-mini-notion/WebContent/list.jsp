<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="notion.NoteDTO" %>
<%
    List<NoteDTO> list = (List<NoteDTO>) request.getAttribute("list");
    if (list == null) list = java.util.Collections.emptyList();
    String ctx = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>미니 노션 - 전체 노트</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: 'Malgun Gothic', sans-serif; background: #f7f6f3; padding: 24px; }
        .container { max-width: 800px; margin: 0 auto; }
        h1 { margin-bottom: 20px; color: #37352f; font-size: 1.8em; }
        .toolbar { display: flex; gap: 12px; margin-bottom: 24px; }
        .btn { padding: 8px 16px; border-radius: 6px; text-decoration: none; font-size: 14px; cursor: pointer; border: none; }
        .btn-primary { background: #2383e2; color: white; }
        .btn-primary:hover { background: #1a6fc2; }
        .btn-secondary { background: #e9e9e7; color: #37352f; }
        .btn-secondary:hover { background: #ddd; }
        .btn-danger { background: #eb5757; color: white; }
        .btn-danger:hover { background: #d64545; }
        .note-list { background: white; border-radius: 8px; box-shadow: 0 1px 3px rgba(0,0,0,0.08); overflow: hidden; }
        .note-item { display: flex; align-items: center; padding: 12px 16px; border-bottom: 1px solid #eee; transition: background 0.2s; }
        .note-item:hover { background: #f7f6f3; }
        .note-item:last-child { border-bottom: none; }
        .note-indent { width: 24px; flex-shrink: 0; }
        .note-content { flex: 1; min-width: 0; }
        .note-title { font-weight: 600; color: #37352f; margin-bottom: 4px; }
        .note-meta { font-size: 12px; color: #9b9a97; }
        .note-actions { display: flex; gap: 8px; flex-shrink: 0; }
        .note-actions a, .note-actions button { font-size: 12px; padding: 4px 8px; }
        .empty { text-align: center; padding: 48px; color: #9b9a97; }
    </style>
</head>
<body>
    <div class="container">
        <h1>📒 미니 노션</h1>
        <div class="toolbar">
            <a href="<%= ctx %>/write.do" class="btn btn-primary">+ 새 노트</a>
            <a href="<%= ctx %>/trash.do" class="btn btn-secondary">휴지통</a>
        </div>

        <div class="note-list">
            <% if (list.isEmpty()) { %>
                <div class="empty">등록된 노트가 없습니다. 새 노트를 작성해보세요.</div>
            <% } else {
                for (NoteDTO n : list) {
                    int indent = n.getReLevel() * 24;
            %>
            <div class="note-item" style="padding-left: <%= 16 + indent %>px;">
                <div class="note-content">
                    <div class="note-title"><%= n.getTitle() != null && !n.getTitle().isEmpty() ? n.getTitle() : "(제목 없음)" %></div>
                    <div class="note-meta"><%= n.getWriter() %> · <%= n.getCreatedAt() != null ? n.getCreatedAt().toString().substring(0, 16) : "" %></div>
                </div>
                <div class="note-actions">
                    <a href="<%= ctx %>/write.do?parentId=<%= n.getId() %>" class="btn btn-secondary">답글</a>
                    <a href="<%= ctx %>/update.do?id=<%= n.getId() %>" class="btn btn-secondary">수정</a>
                    <a href="<%= ctx %>/delete.do?id=<%= n.getId() %>" class="btn btn-danger" onclick="return confirm('휴지통으로 이동할까요?');">삭제</a>
                </div>
            </div>
            <% }
            } %>
        </div>
    </div>
</body>
</html>
