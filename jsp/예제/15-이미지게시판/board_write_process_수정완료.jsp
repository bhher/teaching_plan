<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="jakarta.servlet.http.Part" %>
<%@ page import="java.io.File" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="dto.*" %>    
<%@ page import="dao.*" %>   
<%@ page import="util.*" %>  
<%
    request.setCharacterEncoding("UTF-8");
    
    // ✅ multipart/form-data에서는 request.getParameter()가 작동하지 않음!
    // Part API를 사용하여 파라미터 가져오기
    
    String title = null;
    String content = null;
    String writer = null;
    String password = null;
    
    // Part API로 일반 파라미터 가져오기
    try {
        // 모든 Part를 순회하면서 파라미터 추출
        for (Part part : request.getParts()) {
            String name = part.getName();
            
            if (name != null && part.getHeader("content-disposition") != null) {
                String contentDisposition = part.getHeader("content-disposition");
                
                // 파일이 아닌 일반 파라미터인 경우
                if (!contentDisposition.contains("filename")) {
                    // Part의 내용을 읽어서 파라미터 값으로 사용
                    BufferedReader reader = new BufferedReader(
                        new InputStreamReader(part.getInputStream(), "UTF-8"));
                    StringBuilder value = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        value.append(line);
                    }
                    
                    // 파라미터 이름에 따라 값 저장
                    if ("title".equals(name)) {
                        title = value.toString();
                    } else if ("content".equals(name)) {
                        content = value.toString();
                    } else if ("writer".equals(name)) {
                        writer = value.toString();
                    } else if ("password".equals(name)) {
                        password = value.toString();
                    }
                }
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    
    // 파일 업로드 처리
    String imageFile = null;
    String imageOriginal = null;
    
    try {
        // 파일 Part 가져오기
        Part imagePart = request.getPart("image");
        
        if (imagePart != null && imagePart.getSize() > 0) {
            // 실제 업로드 경로
            String uploadPath = application.getRealPath("/") + FileUploadUtil.getUploadDir();
            
            // 파일 업로드
            imageFile = FileUploadUtil.uploadFile(imagePart, uploadPath);
            
            if (imageFile != null) {
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
        
        // 데이터베이스에 저장
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            ImageBoardDAO boardDAO = new ImageBoardDAO(conn);
            
            ImageBoardDTO board = new ImageBoardDTO(title, content, writer, password, imageFile, imageOriginal);
            int boardId = boardDAO.insertBoard(board);
            
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
            out.println("<script>alert('게시글 등록 중 오류가 발생했습니다.\\n오류: " + e.getMessage() + "'); history.back();</script>");
        } finally {
            if (conn != null) {
                DBConnection.close(conn);
            }
        }
        
    } catch (Exception e) {
        e.printStackTrace();
        out.println("<script>alert('파일 업로드 중 오류가 발생했습니다.\\n오류: " + e.getMessage() + "'); history.back();</script>");
    }
%>
