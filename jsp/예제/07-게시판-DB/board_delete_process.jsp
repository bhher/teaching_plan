<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<%@ page import="jsp.board.BoardDAO" %>
<%@ page import="jsp.board.DBConnection" %>
<%
    String idParam = request.getParameter("id");
    String password = request.getParameter("password");
    
    // 필수 항목 체크
    if (idParam == null || idParam.trim().isEmpty() ||
        password == null || password.trim().isEmpty()) {
        response.sendRedirect("board_delete.jsp?id=" + idParam + "&error=password");
        return;
    }
    
    int id = Integer.parseInt(idParam);
    Connection conn = null;
    
    try {
        conn = DBConnection.getConnection();
        BoardDAO boardDAO = new BoardDAO(conn);
        
        // 게시글 삭제
        int result = boardDAO.deleteBoard(id, password);
        
        if (result > 0) {
            // 성공
            response.sendRedirect("board_list.jsp");
        } else {
            // 실패 (비밀번호 불일치 또는 게시글 없음)
            response.sendRedirect("board_delete.jsp?id=" + id + "&error=password");
        }
        
    } catch (Exception e) {
        e.printStackTrace();
        response.sendRedirect("board_delete.jsp?id=" + id + "&error=db_error");
    } finally {
        DBConnection.close(conn);
    }
%>
