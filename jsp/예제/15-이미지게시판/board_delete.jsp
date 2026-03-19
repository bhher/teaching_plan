<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="jsp.imageboard.*" %>
<%
    String idParam = request.getParameter("id");
    if (idParam == null || idParam.isEmpty()) {
        response.sendRedirect("board_list.jsp");
        return;
    }
    
    int id = Integer.parseInt(idParam);
    Connection conn = null;
    ImageBoardDTO board = null;
    
    try {
        conn = DBConnection.getConnection();
        ImageBoardDAO boardDAO = new ImageBoardDAO(conn);
        
        board = boardDAO.selectBoardById(id);
        if (board == null) {
            response.sendRedirect("board_list.jsp");
            return;
        }
        
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        DBConnection.close(conn);
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 삭제</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        body {
            font-family: 'Malgun Gothic', sans-serif;
            background-color: #f5f5f5;
            padding: 20px;
        }
        .container {
            max-width: 500px;
            margin: 0 auto;
            background-color: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        h2 {
            margin-bottom: 20px;
            color: #333;
        }
        .warning {
            background: #fff3cd;
            border: 1px solid #ffc107;
            padding: 15px;
            border-radius: 4px;
            margin-bottom: 20px;
            color: #856404;
        }
        .form-group {
            margin-bottom: 20px;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
            color: #555;
        }
        input[type="password"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 14px;
        }
        .btn-group {
            display: flex;
            gap: 10px;
            margin-top: 20px;
        }
        .btn {
            padding: 12px 30px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 14px;
            text-decoration: none;
            display: inline-block;
            text-align: center;
            flex: 1;
        }
        .btn-danger {
            background: #dc3545;
            color: white;
        }
        .btn-danger:hover {
            background: #c82333;
        }
        .btn-secondary {
            background: #6c757d;
            color: white;
        }
        .btn-secondary:hover {
            background: #5a6268;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>게시글 삭제</h2>
        
        <div class="warning">
            ⚠️ 삭제된 게시글은 복구할 수 없습니다.
        </div>
        
        <form action="board_delete_process.jsp" method="post" onsubmit="return confirm('정말 삭제하시겠습니까?')">
            <input type="hidden" name="id" value="<%= board.getId() %>">
            
            <div class="form-group">
                <label>제목</label>
                <div style="padding: 10px; background: #f8f9fa; border-radius: 4px;">
                    <%= board.getTitle() %>
                </div>
            </div>
            
            <div class="form-group">
                <label>비밀번호 <span style="color:red;">*</span></label>
                <input type="password" name="password" id="password" required>
            </div>
            
            <div class="btn-group">
                <button type="submit" class="btn btn-danger">삭제</button>
                <a href="board_view.jsp?id=<%= board.getId() %>" class="btn btn-secondary">취소</a>
            </div>
        </form>
    </div>
</body>
</html>
