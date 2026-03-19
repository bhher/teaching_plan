<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="jakarta.servlet.http.Part" %>  <!-- Tomcat 10.1+ 호환 (javax.servlet → jakarta.servlet) -->
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
    String title = request.getParameter("title");
    String content = request.getParameter("content");
    String password = request.getParameter("password");
    
    Connection conn = null;
    ImageBoardDTO oldBoard = null;
    String oldImageFile = null;
    
    try {
        conn = DBConnection.getConnection();
        ImageBoardDAO boardDAO = new ImageBoardDAO(conn);
        
        // 기존 게시글 정보 가져오기
        oldBoard = boardDAO.selectBoardById(id);
        if (oldBoard == null) {
            response.sendRedirect("board_list.jsp");
            return;
        }
        
        // 비밀번호 확인
        if (!boardDAO.checkPassword(id, password)) {
            out.println("<script>alert('비밀번호가 일치하지 않습니다.'); history.back();</script>");
            return;
        }
        
        oldImageFile = oldBoard.getImageFile();
        
        // 파일 업로드 처리
        String imageFile = oldImageFile;  // 기본값: 기존 이미지 유지
        String imageOriginal = oldBoard.getImageOriginal();
        
        Part imagePart = request.getPart("image");
        if (imagePart != null && imagePart.getSize() > 0) {
            // 실제 업로드 경로
            String uploadPath = application.getRealPath("/") + FileUploadUtil.getUploadDir();
            
            // 새 파일 업로드
            String newImageFile = FileUploadUtil.uploadFile(imagePart, uploadPath);
            
            if (newImageFile != null) {
                // 기존 이미지 파일 삭제
                if (oldImageFile != null && !oldImageFile.isEmpty()) {
                    FileUploadUtil.deleteFile(uploadPath, oldImageFile);
                }
                
                imageFile = newImageFile;
                
                // 원본 파일명 가져오기
                String contentDisposition = imagePart.getHeader("content-disposition");
                if (contentDisposition != null) {
                    String[] tokens = contentDisposition.split(";");
                    for (String token : tokens) {
                        if (token.trim().startsWith("filename")) {
                            String fileName = token.substring(token.indexOf("=") + 2, token.length() - 1);
                            imageOriginal = fileName.substring(fileName.lastIndexOf("\\") + 1);
                            break;
                        }
                    }
                }
            }
        }
        
        // 게시글 수정
        ImageBoardDTO board = new ImageBoardDTO();
        board.setId(id);
        board.setTitle(title);
        board.setContent(content);
        board.setImageFile(imageFile);
        board.setImageOriginal(imageOriginal);
        
        int result = boardDAO.updateBoard(board);
        
        if (result > 0) {
            response.sendRedirect("board_view.jsp?id=" + id);
        } else {
            // 실패 시 새로 업로드된 파일 삭제
            if (imageFile != null && !imageFile.equals(oldImageFile)) {
                String uploadPath = application.getRealPath("/") + FileUploadUtil.getUploadDir();
                FileUploadUtil.deleteFile(uploadPath, imageFile);
            }
            out.println("<script>alert('게시글 수정에 실패했습니다.'); history.back();</script>");
        }
        
    } catch (Exception e) {
        e.printStackTrace();
        out.println("<script>alert('게시글 수정 중 오류가 발생했습니다.'); history.back();</script>");
    } finally {
        DBConnection.close(conn);
    }
%>
