<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<%@ page import="jsp.board.BoardDTO" %>
<%@ page import="jsp.board.BoardDAO" %>
<%@ page import="jsp.board.DBConnection" %>
<%
    // 파라미터 받기
    String title = request.getParameter("title");
    String content = request.getParameter("content");
    String writer = request.getParameter("writer");
    String password = request.getParameter("password");
    
    // 필수 항목 체크
    if (title == null || title.trim().isEmpty() ||
        content == null || content.trim().isEmpty() ||
        writer == null || writer.trim().isEmpty() ||
        password == null || password.trim().isEmpty()) {
        response.sendRedirect("board_write.jsp?error=empty&title=" + 
                            (title != null ? title : "") + 
                            "&writer=" + (writer != null ? writer : "") +
                            "&content=" + (content != null ? content : ""));
        return;
    }
    
    // DTO 객체 생성
    BoardDTO board = new BoardDTO();
    board.setTitle(title);
    board.setContent(content);
    board.setWriter(writer);
    board.setPassword(password);
    
    Connection conn = null;
    
    try {
        // 데이터베이스 연결
        conn = DBConnection.getConnection();
        BoardDAO boardDAO = new BoardDAO(conn);
        
        // 게시글 등록
        int boardId = boardDAO.insertBoard(board);
        
        if (boardId > 0) {
            // 성공
            response.sendRedirect("board_view.jsp?id=" + boardId);
        } else {
            // 실패
            response.sendRedirect("board_write.jsp?error=db_error");
        }
        
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
        response.sendRedirect("board_write.jsp?error=db_error");
    } catch (SQLException e) {
        e.printStackTrace();
        response.sendRedirect("board_write.jsp?error=db_error");
    } finally {
        DBConnection.close(conn);
    }
%>
