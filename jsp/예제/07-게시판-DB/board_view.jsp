<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<%@ page import="jsp.board.BoardDTO" %>
<%@ page import="jsp.board.BoardDAO" %>
<%@ page import="jsp.board.DBConnection" %>
<%
    String idParam = request.getParameter("id");
    if (idParam == null || idParam.trim().isEmpty()) {
        response.sendRedirect("board_list.jsp");
        return;
    }
    
    int id = Integer.parseInt(idParam);
    Connection conn = null;
    BoardDTO board = null;
    
    try {
        conn = DBConnection.getConnection();
        BoardDAO boardDAO = new BoardDAO(conn);
        
        // 조회수 증가
        boardDAO.increaseHit(id);
        
        // 게시글 조회
        board = boardDAO.selectBoardById(id);
        
        if (board == null) {
            response.sendRedirect("board_list.jsp");
            return;
        }
        
    } catch (Exception e) {
        e.printStackTrace();
        response.sendRedirect("board_list.jsp");
    } finally {
        DBConnection.close(conn);
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 상세보기</title>
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
            max-width: 900px;
            margin: 0 auto;
            background-color: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        h2 {
            margin-bottom: 30px;
            color: #333;
            border-bottom: 2px solid #4CAF50;
            padding-bottom: 10px;
        }
        .board-info {
            background-color: #f9f9f9;
            padding: 15px;
            border-radius: 4px;
            margin-bottom: 20px;
        }
        .info-row {
            display: flex;
            margin-bottom: 10px;
        }
        .info-label {
            font-weight: bold;
            width: 100px;
            color: #555;
        }
        .info-value {
            flex: 1;
            color: #333;
        }
        .board-content {
            padding: 20px;
            min-height: 300px;
            line-height: 1.8;
            white-space: pre-wrap;
            word-wrap: break-word;
        }
        .btn-group {
            margin-top: 30px;
            text-align: center;
        }
        .btn {
            display: inline-block;
            padding: 10px 20px;
            margin: 0 5px;
            background-color: #2196F3;
            color: white;
            text-decoration: none;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 14px;
        }
        .btn:hover {
            background-color: #1976D2;
        }
        .btn-success {
            background-color: #4CAF50;
        }
        .btn-success:hover {
            background-color: #45a049;
        }
        .btn-danger {
            background-color: #f44336;
        }
        .btn-danger:hover {
            background-color: #d32f2f;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2><%= board.getTitle() %></h2>
        
        <div class="board-info">
            <div class="info-row">
                <span class="info-label">번호:</span>
                <span class="info-value"><%= board.getId() %></span>
            </div>
            <div class="info-row">
                <span class="info-label">작성자:</span>
                <span class="info-value"><%= board.getWriter() %></span>
            </div>
            <div class="info-row">
                <span class="info-label">조회수:</span>
                <span class="info-value"><%= board.getHit() %></span>
            </div>
            <div class="info-row">
                <span class="info-label">등록일:</span>
                <span class="info-value"><%= board.getRegDate() != null ? board.getRegDate().toString().substring(0, 19) : "" %></span>
            </div>
            <% if (board.getModDate() != null && !board.getModDate().equals(board.getRegDate())) { %>
            <div class="info-row">
                <span class="info-label">수정일:</span>
                <span class="info-value"><%= board.getModDate().toString().substring(0, 19) %></span>
            </div>
            <% } %>
        </div>
        
        <div class="board-content">
            <%= board.getContentWithBr() %>
        </div>
        
        <div class="btn-group">
            <a href="board_list.jsp" class="btn">목록</a>
            <a href="board_modify.jsp?id=<%= board.getId() %>" class="btn btn-success">수정</a>
            <a href="board_delete.jsp?id=<%= board.getId() %>" class="btn btn-danger">삭제</a>
        </div>
    </div>
</body>
</html>
