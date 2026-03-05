<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<%@ page import="jsp.board.BoardDTO" %>
<%@ page import="jsp.board.BoardDAO" %>
<%@ page import="jsp.board.DBConnection" %>
<%
    String idParam = request.getParameter("id");
    String title = request.getParameter("title");
    String content = request.getParameter("content");
    String password = request.getParameter("password");
    
    // 필수 항목 체크
    if (idParam == null || idParam.trim().isEmpty() ||
        title == null || title.trim().isEmpty() ||
        content == null || content.trim().isEmpty() ||
        password == null || password.trim().isEmpty()) {
        response.sendRedirect("board_modify.jsp?id=" + idParam + "&error=empty");
        return;
    }
    
    int id = Integer.parseInt(idParam);
    
    // DTO 객체 생성
    BoardDTO board = new BoardDTO();
    board.setId(id);
    board.setTitle(title);
    board.setContent(content);
    board.setPassword(password);
    
    Connection conn = null;
    
    try {
        conn = DBConnection.getConnection();
        BoardDAO boardDAO = new BoardDAO(conn);
        
        // 게시글 수정
        int result = boardDAO.updateBoard(board);
        
        if (result > 0) {
            // 성공
            response.sendRedirect("board_view.jsp?id=" + id);
        } else {
            // 실패 (비밀번호 불일치 또는 게시글 없음)
            response.sendRedirect("board_modify.jsp?id=" + id + "&error=password");
        }
        
    } catch (Exception e) {
        e.printStackTrace();
        response.sendRedirect("board_modify.jsp?id=" + id + "&error=db_error");
    } finally {
        DBConnection.close(conn);
    }
%>
