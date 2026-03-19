<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="jakarta.servlet.http.Part" %>  <!-- Tomcat 10.1+ 호환 -->
<%@ page import="java.io.File" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="dto.*" %>    
<%@ page import="dao.*" %>   
<%@ page import="util.*" %>  
<%
request.setCharacterEncoding("UTF-8");

String title = request.getParameter("title");
String content = request.getParameter("content");
String writer = request.getParameter("writer");
String password = request.getParameter("password");

// 파일 업로드 처리
String imageFile = null;
String imageOriginal = null;
try{
	Part imagePart = request.getPart("image");
	
	if(imagePart != null && imagePart.getSize() > 0){
		// 실제 서버 하드디스크상의 경로 계산
		String uploadPath = application.getRealPath("/") + FileUploadUtil.getUploadDir();
		
		imageFile = FileUploadUtil.uploadFile(imagePart, uploadPath);
		
		// 원본 파일명 가져오기
		if (imageFile != null) {
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
	
	// 데이터베이스에 저장
	Connection conn = null;
	
	try{
		conn = DBConnection.getConnection();
		ImageBoardDAO boardDAO = new ImageBoardDAO(conn);
		
		ImageBoardDTO board = new ImageBoardDTO(title, content, writer, password, imageFile, imageOriginal);
		int boardId = boardDAO.insertBoard(board);
		
		// ✅ 성공 시 리다이렉트 추가!
		if (boardId > 0) {
			response.sendRedirect("board_view.jsp?id=" + boardId);
			return;  // 리다이렉트 후 실행 중단
		} else {
			// 실패 시 업로드된 파일 삭제
			if (imageFile != null) {
				String uploadPath = application.getRealPath("/") + FileUploadUtil.getUploadDir();
				FileUploadUtil.deleteFile(uploadPath, imageFile);
			}
			out.println("<script>alert('게시글 등록에 실패했습니다.'); history.back();</script>");
		}
		
	} catch (Exception e) {
		e.printStackTrace();
		// 실패 시 업로드된 파일 삭제
		if (imageFile != null) {
			String uploadPath = application.getRealPath("/") + FileUploadUtil.getUploadDir();
			FileUploadUtil.deleteFile(uploadPath, imageFile);
		}
		out.println("<script>alert('게시글 등록 중 오류가 발생했습니다.'); history.back();</script>");
	} finally {
		DBConnection.close(conn);
	}
	
} catch(Exception e){
	e.printStackTrace();
	out.println("<script>alert('파일 업로드 중 오류가 발생했습니다.'); history.back();</script>");
}
%>
