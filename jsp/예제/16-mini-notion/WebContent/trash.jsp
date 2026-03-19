<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="notion.NoteDTO" %>
<%
    List<NoteDTO> trashList = (List<NoteDTO>) request.getAttribute("trashList");
    if (trashList == null) trashList = java.util.Collections.emptyList();
    String ctx = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>휴지통 - 미니 노션</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: 'Malgun Gothic', sans-serif; background: #f7f6f3; padding: 24px; }
        .container { max-width: 800px; margin: 0 auto; }
        h1 { margin-bottom: 20px; color: #37352f; font-size: 1.8em; }
        .toolbar { margin-bottom: 24px; }
        .btn { padding: 8px 16px; border-radius: 6px; text-decoration: none; font-size: 14px; display: inline-block; }
        .btn-secondary { background: #e9e9e7; color: #37352f; }
        .btn-secondary:hover { background: #ddd; }
        .note-list { background: white; border-radius: 8px; box-shadow: 0 1px 3px rgba(0,0,0,0.08); overflow: hidden; }
        .note-item { padding: 12px 16px; border-bottom: 1px solid #eee; }
        .note-item:last-child { border-bottom: none; }
        .note-title { font-weight: 600; color: #37352f; margin-bottom: 4px; }
        .note-meta { font-size: 12px; color: #9b9a97; }
        .empty { text-align: center; padding: 48px; color: #9b9a97; }
    </style>
</head>
<body>
    <div class="container">
        <h1>🗑️ 휴지통</h1>
        <div class="toolbar">
            <a href="<%= ctx %>/list.do" class="btn btn-secondary">← 목록으로</a>
        </div>

        <div class="note-list">
            <% if (trashList.isEmpty()) { %>
                <div class="empty">휴지통이 비어 있습니다.</div>
            <% } else {
                for (NoteDTO n : trashList) {
            %>
            <div class="note-item">
                <div class="note-title"><%= n.getTitle() != null && !n.getTitle().isEmpty() ? n.getTitle() : "(제목 없음)" %></div>
                <div class="note-meta"><%= n.getWriter() %> · 삭제: <%= n.getDeletedAt() != null ? n.getDeletedAt().toString().substring(0, 16) : "" %></div>
            </div>
            <% }
            } %>
        </div>
    </div>
</body>
</html>
