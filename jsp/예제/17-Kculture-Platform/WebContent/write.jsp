<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="kculture.*" %>
<%
    List<CategoryDTO> categories = (List<CategoryDTO>) request.getAttribute("categories");
    String ctx = request.getContextPath();
    if (categories == null) categories = java.util.Collections.emptyList();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Write Post - K-Culture Platform</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: 'Segoe UI', 'Malgun Gothic', sans-serif; background: linear-gradient(135deg, #1a1a2e 0%, #16213e 50%, #0f3460 100%); min-height: 100vh; color: #eee; }
        .header { background: rgba(0,0,0,0.3); padding: 16px 24px; }
        .header a { color: #eee; text-decoration: none; }
        .header a:hover { color: #e94560; }
        .container { max-width: 700px; margin: 0 auto; padding: 24px; }
        h1 { margin-bottom: 24px; }
        .form-group { margin-bottom: 20px; }
        .form-group label { display: block; margin-bottom: 8px; }
        .form-group input, .form-group select, .form-group textarea { width: 100%; padding: 12px; border-radius: 8px; border: 1px solid rgba(255,255,255,0.2); background: rgba(0,0,0,0.3); color: #eee; }
        .form-group textarea { min-height: 200px; resize: vertical; }
        .form-group input:focus, .form-group select:focus, .form-group textarea:focus { outline: none; border-color: #e94560; }
        .btn { padding: 12px 24px; border-radius: 8px; border: none; cursor: pointer; font-size: 14px; text-decoration: none; display: inline-block; }
        .btn-primary { background: #e94560; color: #fff; }
        .btn-secondary { background: rgba(255,255,255,0.2); color: #eee; margin-left: 12px; }
    </style>
</head>
<body>
    <div class="header"><a href="<%= ctx %>/list.do">← Back to List</a></div>
    <div class="container">
        <h1>📝 Write Post</h1>
        <form action="<%= ctx %>/writeProcess.do" method="post">
            <div class="form-group">
                <label>Category</label>
                <select name="categoryId" required>
                    <% for (CategoryDTO c : categories) { %>
                        <option value="<%= c.getId() %>"><%= c.getIcon() %> <%= c.getNameEn() %> - <%= c.getNameKo() %></option>
                    <% } %>
                </select>
            </div>
            <div class="form-group">
                <label>Title</label>
                <input type="text" name="title" required placeholder="Post title">
            </div>
            <div class="form-group">
                <label>Content</label>
                <textarea name="content" placeholder="Share your K-Culture experience..."></textarea>
            </div>
            <div>
                <button type="submit" class="btn btn-primary">Post</button>
                <a href="<%= ctx %>/list.do" class="btn btn-secondary">Cancel</a>
            </div>
        </form>
    </div>
</body>
</html>
