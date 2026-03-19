<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="kculture.*" %>
<%
    PostDTO post = (PostDTO) request.getAttribute("post");
    List<CategoryDTO> categories = (List<CategoryDTO>) request.getAttribute("categories");
    String ctx = request.getContextPath();
    if (post == null) { response.sendRedirect(ctx + "/list.do"); return; }
    if (categories == null) categories = java.util.Collections.emptyList();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Post - K-Culture Platform</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: 'Segoe UI', 'Malgun Gothic', sans-serif; background: linear-gradient(135deg, #1a1a2e 0%, #16213e 50%, #0f3460 100%); min-height: 100vh; color: #eee; }
        .header { background: rgba(0,0,0,0.3); padding: 16px 24px; }
        .header a { color: #eee; text-decoration: none; }
        .container { max-width: 700px; margin: 0 auto; padding: 24px; }
        h1 { margin-bottom: 24px; }
        .form-group { margin-bottom: 20px; }
        .form-group label { display: block; margin-bottom: 8px; }
        .form-group input, .form-group select, .form-group textarea { width: 100%; padding: 12px; border-radius: 8px; border: 1px solid rgba(255,255,255,0.2); background: rgba(0,0,0,0.3); color: #eee; }
        .form-group textarea { min-height: 200px; resize: vertical; }
        .btn { padding: 12px 24px; border-radius: 8px; border: none; cursor: pointer; font-size: 14px; text-decoration: none; display: inline-block; }
        .btn-primary { background: #e94560; color: #fff; }
        .btn-secondary { background: rgba(255,255,255,0.2); color: #eee; margin-left: 12px; }
    </style>
</head>
<body>
    <div class="header"><a href="<%= ctx %>/view.do?id=<%= post.getId() %>">← Back</a></div>
    <div class="container">
        <h1>✏️ Edit Post</h1>
        <form action="<%= ctx %>/editProcess.do" method="post">
            <input type="hidden" name="id" value="<%= post.getId() %>">
            <div class="form-group">
                <label>Title</label>
                <input type="text" name="title" value="<%= post.getTitle() %>" required>
            </div>
            <div class="form-group">
                <label>Content</label>
                <textarea name="content"><%= post.getContent() != null ? post.getContent() : "" %></textarea>
            </div>
            <div>
                <button type="submit" class="btn btn-primary">Save</button>
                <a href="<%= ctx %>/view.do?id=<%= post.getId() %>" class="btn btn-secondary">Cancel</a>
            </div>
        </form>
    </div>
</body>
</html>
