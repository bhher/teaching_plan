<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.io.File" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="jsp.imageboard.*" %>
<%
    request.setCharacterEncoding("UTF-8");
    
    String idParam = request.getParameter("id");
    if (idParam == null || idParam.isEmpty()) {
        response.sendRedirect("board_list.jsp");
        return;
    }
    
    int id = Integer.parseInt(idParam);
    String password = request.getParameter("password");
    
    Connection conn = null;
    ImageBoardDTO board = null;
    
    try {
        conn = DBConnection.getConnection();
        ImageBoardDAO boardDAO = new ImageBoardDAO(conn);
        
        // 게시글 정보 가져오기
        board = boardDAO.selectBoardById(id);
        if (board == null) {
            response.sendRedirect("board_list.jsp");
            return;
        }
        
        // 비밀번호 확인
        if (!boardDAO.checkPassword(id, password)) {
            out.println("<script>alert('비밀번호가 일치하지 않습니다.'); history.back();</script>");
            return;
        }
        
        // 이미지 파일 삭제
        if (board.hasImage()) {
            String uploadPath = application.getRealPath("/") + FileUploadUtil.getUploadDir();
            FileUploadUtil.deleteFile(uploadPath, board.getImageFile());
        }
        
        // 데이터베이스에서 삭제
        int result = boardDAO.deleteBoard(id);
        
        if (result > 0) {
            response.sendRedirect("board_list.jsp");
        } else {
            out.println("<script>alert('게시글 삭제에 실패했습니다.'); history.back();</script>");
        }
        
    } catch (Exception e) {
        e.printStackTrace();
        out.println("<script>alert('게시글 삭제 중 오류가 발생했습니다.'); history.back();</script>");
    } finally {
        DBConnection.close(conn);
    }
%>
