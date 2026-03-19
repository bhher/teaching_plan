<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="dto.*" %>    
<%@ page import="dao.*" %>   
<%@ page import="util.*" %>  
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
        
        // 조회수 증가
        boardDAO.increaseHit(id);
        board.setHit(board.getHit() + 1);
        
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        DBConnection.close(conn);
    }
    
    // 업로드 경로 (웹 경로)
    // 실제 파일이 저장된 위치 확인
    String realUploadPath = application.getRealPath("/upload/images/");
    String uploadPath = request.getContextPath() + "/upload/images/";
    
    // 디버깅: 실제 경로 확인 (필요시 주석 해제)
    // System.out.println("실제 저장 경로: " + realUploadPath);
    // System.out.println("웹 접근 경로: " + uploadPath);
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><%= board.getTitle() %></title>
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
        .board-header {
            border-bottom: 2px solid #667eea;
            padding-bottom: 20px;
            margin-bottom: 30px;
        }
        .board-title {
            font-size: 1.8em;
            font-weight: bold;
            color: #333;
            margin-bottom: 15px;
        }
        .board-meta {
            display: flex;
            gap: 20px;
            color: #666;
            font-size: 0.9em;
        }
        .board-image-container {
            margin-bottom: 30px;
            text-align: center;
        }
        .board-image {
            max-width: 100%;
            max-height: 600px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        .board-content {
            padding: 20px 0;
            line-height: 1.8;
            color: #333;
            min-height: 100px;
            white-space: pre-wrap;
        }
        .board-actions {
            margin-top: 30px;
            padding-top: 20px;
            border-top: 1px solid #ddd;
            display: flex;
            gap: 10px;
        }
        .btn {
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 14px;
            text-decoration: none;
            display: inline-block;
        }
        .btn-primary {
            background: #667eea;
            color: white;
        }
        .btn-primary:hover {
            background: #764ba2;
        }
        .btn-secondary {
            background: #6c757d;
            color: white;
        }
        .btn-secondary:hover {
            background: #5a6268;
        }
        .btn-danger {
            background: #dc3545;
            color: white;
        }
        .btn-danger:hover {
            background: #c82333;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="board-header">
            <div class="board-title"><%= board.getTitle() %></div>
            <div class="board-meta">
                <span>👤 작성자: <%= board.getWriter() %></span>
                <span>📅 작성일: <%= board.getRegDate() %></span>
                <span>👁 조회수: <%= board.getHit() %></span>
            </div>
        </div>
        
        <% if (board.hasImage()) { %>
            <div class="board-image-container">
                <img src="<%= uploadPath %><%= board.getImageFile() %>" 
                     alt="<%= board.getTitle() %>" 
                     class="board-image"
                     onerror="this.style.display='none'; this.parentElement.innerHTML='<p style=\\'color:#999; padding:40px;\\'>이미지를 불러올 수 없습니다.</p>';">
            </div>
        <% } %>
        
        <div class="board-content"><%= board.getContent() != null ? board.getContent() : "" %></div>
        
        <div class="board-actions">
            <a href="board_list.jsp" class="btn btn-secondary">목록</a>
            <a href="board_modify.jsp?id=<%= board.getId() %>" class="btn btn-primary">수정</a>
            <a href="board_delete.jsp?id=<%= board.getId() %>" class="btn btn-danger">삭제</a>
        </div>
    </div>
</body>
</html>
