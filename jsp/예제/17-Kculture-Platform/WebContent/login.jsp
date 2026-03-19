<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String error = (String) request.getAttribute("error");
    String ctx = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login - K-Culture Platform</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: 'Segoe UI', 'Malgun Gothic', sans-serif; background: linear-gradient(135deg, #1a1a2e 0%, #16213e 50%, #0f3460 100%); min-height: 100vh; color: #eee; display: flex; align-items: center; justify-content: center; }
        .login-box { background: rgba(255,255,255,0.08); padding: 40px; border-radius: 16px; width: 100%; max-width: 400px; }
        h1 { margin-bottom: 24px; text-align: center; }
        .form-group { margin-bottom: 20px; }
        .form-group label { display: block; margin-bottom: 8px; }
        .form-group input { width: 100%; padding: 12px; border-radius: 8px; border: 1px solid rgba(255,255,255,0.2); background: rgba(0,0,0,0.3); color: #eee; }
        .form-group input:focus { outline: none; border-color: #e94560; }
        .btn { width: 100%; padding: 14px; border-radius: 8px; border: none; cursor: pointer; font-size: 16px; background: #e94560; color: #fff; margin-top: 10px; }
        .btn:hover { background: #d63850; }
        .error { color: #e94560; margin-bottom: 16px; font-size: 14px; }
        .links { text-align: center; margin-top: 20px; }
        .links a { color: #e94560; text-decoration: none; }
    </style>
</head>
<body>
    <div class="login-box">
        <h1>🇰🇷 K-Culture Platform</h1>
        <p style="text-align:center; color:#888; margin-bottom:24px;">Login for Foreign Tourists</p>
        <% if (error != null) { %><div class="error"><%= error %></div><% } %>
        <form action="<%= ctx %>/loginProcess.do" method="post">
            <div class="form-group">
                <label>Email</label>
                <input type="email" name="email" required placeholder="your@email.com">
            </div>
            <div class="form-group">
                <label>Password</label>
                <input type="password" name="password" required>
            </div>
            <button type="submit" class="btn">Login</button>
        </form>
        <div class="links"><a href="<%= ctx %>/join.do">Create account</a> · <a href="<%= ctx %>/list.do">Back to Home</a></div>
    </div>
</body>
</html>
