<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="kculture.*" %>
<%
    List<PostDTO> list = (List<PostDTO>) request.getAttribute("list");
    List<CategoryDTO> categories = (List<CategoryDTO>) request.getAttribute("categories");
    int categoryId = request.getAttribute("categoryId") != null ? (Integer) request.getAttribute("categoryId") : 0;
    int currentPage = request.getAttribute("currentPage") != null ? (Integer) request.getAttribute("currentPage") : 1;
    int totalPages = request.getAttribute("totalPages") != null ? (Integer) request.getAttribute("totalPages") : 1;
    int total = request.getAttribute("total") != null ? (Integer) request.getAttribute("total") : 0;
    MemberDTO login = (MemberDTO) session.getAttribute("loginMember");
    String ctx = request.getContextPath();
    if (list == null) list = java.util.Collections.emptyList();
    if (categories == null) categories = java.util.Collections.emptyList();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>K-Culture Platform - Community for Foreign Tourists</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: 'Segoe UI', 'Malgun Gothic', sans-serif; background: linear-gradient(135deg, #1a1a2e 0%, #16213e 50%, #0f3460 100%); min-height: 100vh; color: #eee; }
        .header { background: rgba(0,0,0,0.3); padding: 16px 24px; display: flex; justify-content: space-between; align-items: center; }
        .logo { font-size: 1.5em; font-weight: bold; color: #e94560; }
        .nav a { color: #eee; text-decoration: none; margin-left: 20px; }
        .nav a:hover { color: #e94560; }
        .container { max-width: 1000px; margin: 0 auto; padding: 24px; }
        h1 { margin-bottom: 24px; color: #fff; font-size: 1.8em; }
        .subtitle { color: #aaa; margin-bottom: 24px; font-size: 0.95em; }
        .categories { display: flex; flex-wrap: wrap; gap: 10px; margin-bottom: 24px; }
        .categories-title { flex-basis: 100%; font-size: 0.9em; color: #888; margin-bottom: 4px; }
        .cat-btn { padding: 10px 18px; border-radius: 8px; text-decoration: none; background: rgba(233,69,96,0.2); color: #e94560; border: 1px solid #e94560; transition: all 0.3s; }
        .cat-btn:hover, .cat-btn.active { background: #e94560; color: #fff; }
        .post-list { background: rgba(255,255,255,0.05); border-radius: 12px; overflow: hidden; }
        .post-item { display: flex; align-items: center; padding: 16px 20px; border-bottom: 1px solid rgba(255,255,255,0.1); transition: background 0.2s; }
        .post-item:hover { background: rgba(255,255,255,0.08); }
        .post-item:last-child { border-bottom: none; }
        .post-cat { font-size: 1.2em; margin-right: 12px; }
        .post-content { flex: 1; min-width: 0; }
        .post-title { font-weight: 600; margin-bottom: 4px; }
        .post-title a { color: #fff; text-decoration: none; }
        .post-title a:hover { color: #e94560; }
        .post-meta { font-size: 12px; color: #888; }
        .post-views { font-size: 12px; color: #888; }
        .write-btn { display: inline-block; padding: 10px 20px; background: #e94560; color: #fff; border-radius: 8px; text-decoration: none; margin-bottom: 20px; }
        .write-btn:hover { background: #d63850; }
        .pagination { margin-top: 24px; text-align: center; }
        .pagination a, .pagination span { display: inline-block; padding: 8px 14px; margin: 0 4px; border-radius: 6px; color: #eee; text-decoration: none; background: rgba(255,255,255,0.1); }
        .pagination a:hover { background: #e94560; }
        .pagination .current { background: #e94560; }
        .empty { text-align: center; padding: 60px; color: #888; }
    </style>
</head>
<body>
    <div class="header">
        <div class="logo">🇰🇷 K-Culture Platform</div>
        <nav class="nav">
            <a href="<%= ctx %>/list.do">Home</a>
            <% if (login != null) { %>
                <span><%= login.getName() %> (<%= login.getNationality() != null ? login.getNationality() : "Visitor" %>)</span>
                <a href="<%= ctx %>/write.do">Write</a>
                <a href="<%= ctx %>/logout.do">Logout</a>
            <% } else { %>
                <a href="<%= ctx %>/login.do">Login</a>
                <a href="<%= ctx %>/join.do">Join</a>
            <% } %>
        </nav>
    </div>

    <div class="container">
        <h1>Discover K-Culture</h1>
        <p class="subtitle">Community & Information Platform for Foreign Tourists in Korea</p>

        <% if (login != null) { %>
            <a href="<%= ctx %>/write.do" class="write-btn">+ Write Post</a>
        <% } %>

        <div class="categories">
            <span class="categories-title">📂 K-Culture Categories</span>
            <a href="<%= ctx %>/list.do" class="cat-btn <%= categoryId == 0 ? "active" : "" %>">All</a>
            <% for (CategoryDTO c : categories) { %>
                <a href="<%= ctx %>/list.do?categoryId=<%= c.getId() %>" class="cat-btn <%= categoryId == c.getId() ? "active" : "" %>"><%= c.getIcon() %> <%= c.getNameEn() %></a>
            <% } %>
        </div>

        <div class="post-list">
            <% if (list.isEmpty()) { %>
                <div class="empty">No posts yet. Be the first to share your K-Culture experience!</div>
            <% } else {
                for (PostDTO p : list) {
            %>
            <div class="post-item">
                <span class="post-cat"><%= p.getCategoryIcon() != null ? p.getCategoryIcon() : "📌" %></span>
                <div class="post-content">
                    <div class="post-title"><a href="<%= ctx %>/view.do?id=<%= p.getId() %>"><%= p.getTitle() %></a></div>
                    <div class="post-meta"><%= p.getMemberName() %> · <%= p.getCategoryName() %> · <%= p.getCreatedAt() != null ? p.getCreatedAt().toString().substring(0, 16) : "" %></div>
                </div>
                <div class="post-views">👁 <%= p.getViewCount() %></div>
            </div>
            <% }
            } %>
        </div>

        <% if (totalPages > 1) { %>
        <div class="pagination">
            <% if (currentPage > 1) { %>
                <a href="<%= ctx %>/list.do?page=<%= currentPage-1 %><%= categoryId > 0 ? "&categoryId="+categoryId : "" %>">← Prev</a>
            <% } %>
            <% for (int i = 1; i <= totalPages; i++) { %>
                <% if (i == currentPage) { %>
                    <span class="current"><%= i %></span>
                <% } else { %>
                    <a href="<%= ctx %>/list.do?page=<%= i %><%= categoryId > 0 ? "&categoryId="+categoryId : "" %>"><%= i %></a>
                <% } %>
            <% } %>
            <% if (currentPage < totalPages) { %>
                <a href="<%= ctx %>/list.do?page=<%= currentPage+1 %><%= categoryId > 0 ? "&categoryId="+categoryId : "" %>">Next →</a>
            <% } %>
        </div>
        <% } %>
    </div>
</body>
</html>
