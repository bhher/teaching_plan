<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="kculture.*" %>
<%
    PostDTO post = (PostDTO) request.getAttribute("post");
    List<CommentDTO> comments = (List<CommentDTO>) request.getAttribute("comments");
    MemberDTO login = (MemberDTO) session.getAttribute("loginMember");
    String ctx = request.getContextPath();
    if (post == null) { response.sendRedirect(ctx + "/list.do"); return; }
    if (comments == null) comments = java.util.Collections.emptyList();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><%= post.getTitle() %> - K-Culture Platform</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: 'Segoe UI', 'Malgun Gothic', sans-serif; background: linear-gradient(135deg, #1a1a2e 0%, #16213e 50%, #0f3460 100%); min-height: 100vh; color: #eee; }
        .header { background: rgba(0,0,0,0.3); padding: 16px 24px; display: flex; justify-content: space-between; align-items: center; }
        .logo { font-size: 1.5em; font-weight: bold; color: #e94560; }
        .nav a { color: #eee; text-decoration: none; margin-left: 20px; }
        .nav a:hover { color: #e94560; }
        .container { max-width: 800px; margin: 0 auto; padding: 24px; }
        .post-header { margin-bottom: 24px; }
        .post-cat { font-size: 1.2em; color: #e94560; margin-bottom: 8px; }
        .post-title { font-size: 1.8em; margin-bottom: 12px; }
        .post-meta { color: #888; font-size: 14px; margin-bottom: 20px; }
        .post-content { background: rgba(255,255,255,0.05); padding: 24px; border-radius: 12px; line-height: 1.8; white-space: pre-wrap; margin-bottom: 24px; }
        .post-actions { margin-bottom: 32px; }
        .btn { display: inline-block; padding: 8px 16px; border-radius: 6px; text-decoration: none; margin-right: 8px; font-size: 14px; }
        .btn-primary { background: #e94560; color: #fff; border: none; cursor: pointer; }
        .btn-secondary { background: rgba(255,255,255,0.2); color: #eee; }
        .btn:hover { opacity: 0.9; }
        .comments { margin-top: 32px; }
        .comments h3 { margin-bottom: 16px; }
        .comment-item { background: rgba(255,255,255,0.05); padding: 16px; border-radius: 8px; margin-bottom: 12px; }
        .comment-meta { font-size: 12px; color: #888; margin-bottom: 8px; }
        .comment-form { margin-top: 20px; }
        .comment-form textarea { width: 100%; padding: 12px; border-radius: 8px; border: 1px solid rgba(255,255,255,0.2); background: rgba(0,0,0,0.3); color: #eee; min-height: 80px; margin-bottom: 12px; }
        .comment-form textarea:focus { outline: none; border-color: #e94560; }
    </style>
</head>
<body>
    <div class="header">
        <div class="logo">🇰🇷 K-Culture Platform</div>
        <nav class="nav">
            <a href="<%= ctx %>/list.do">Home</a>
            <% if (login != null) { %>
                <span><%= login.getName() %></span>
                <a href="<%= ctx %>/logout.do">Logout</a>
            <% } else { %>
                <a href="<%= ctx %>/login.do">Login</a>
            <% } %>
        </nav>
    </div>

    <div class="container">
        <div class="post-header">
            <div class="post-cat"><%= post.getCategoryIcon() %> <%= post.getCategoryName() %></div>
            <h1 class="post-title"><%= post.getTitle() %></h1>
            <div class="post-meta"><%= post.getMemberName() %> · 👁 <%= post.getViewCount() %> · <%= post.getCreatedAt() != null ? post.getCreatedAt().toString().substring(0, 16) : "" %></div>
        </div>

        <div class="post-content"><%= post.getContent() != null ? post.getContent() : "" %></div>

        <div class="post-actions">
            <a href="<%= ctx %>/list.do" class="btn btn-secondary">← List</a>
            <% if (login != null && login.getId() == post.getMemberId()) { %>
                <a href="<%= ctx %>/edit.do?id=<%= post.getId() %>" class="btn btn-secondary">Edit</a>
                <a href="<%= ctx %>/delete.do?id=<%= post.getId() %>" class="btn btn-primary" onclick="return confirm('Delete this post?');">Delete</a>
            <% } %>
        </div>

        <div class="comments">
            <h3>💬 Comments (<%= comments.size() %>)</h3>
            <% for (CommentDTO c : comments) { %>
            <div class="comment-item">
                <div class="comment-meta"><%= c.getMemberName() %> <% if (c.getNationality() != null) { %>(<%= c.getNationality() %>)<% } %> · <%= c.getCreatedAt() != null ? c.getCreatedAt().toString().substring(0, 16) : "" %></div>
                <div><%= c.getContent() %></div>
            </div>
            <% } %>

            <% if (login != null) { %>
            <form class="comment-form" action="<%= ctx %>/commentProcess.do" method="post">
                <input type="hidden" name="postId" value="<%= post.getId() %>">
                <textarea name="content" placeholder="Write a comment..." required></textarea>
                <button type="submit" class="btn btn-primary">Post Comment</button>
            </form>
            <% } else { %>
            <p style="color:#888; margin-top:16px;">Please <a href="<%= ctx %>/login.do" style="color:#e94560;">login</a> to comment.</p>
            <% } %>
        </div>
    </div>
</body>
</html>
