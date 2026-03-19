<%@ page language="java" contentType="image/*" pageEncoding="UTF-8" %>
<%@ page import="java.io.File" %>
<%@ page import="java.io.FileInputStream" %>
<%@ page import="java.io.IOException" %>
<%@ page import="jsp.imageboard.FileUploadUtil" %>
<%
    // 요청된 파일명 추출
    String fileName = null;
    
    // 방법 1: PathInfo 사용 (서블릿 매핑을 통한 경우)
    String pathInfo = request.getPathInfo();
    if (pathInfo != null && !pathInfo.isEmpty() && !pathInfo.equals("/")) {
        fileName = pathInfo.substring(1); // 앞의 / 제거
    }
    
    // 방법 2: URL 파라미터 사용 (PathInfo가 없는 경우)
    if (fileName == null || fileName.isEmpty()) {
        fileName = request.getParameter("file");
    }
    
    // 방법 3: 요청 URI에서 파일명 추출
    if (fileName == null || fileName.isEmpty()) {
        String requestURI = request.getRequestURI();
        int lastSlash = requestURI.lastIndexOf("/");
        if (lastSlash >= 0 && lastSlash < requestURI.length() - 1) {
            fileName = requestURI.substring(lastSlash + 1);
        }
    }
    
    if (fileName == null || fileName.isEmpty()) {
        response.sendError(HttpServletResponse.SC_NOT_FOUND, "파일명이 지정되지 않았습니다.");
        return;
    }
    
    // 실제 파일 경로 구성
    String uploadDir = application.getRealPath("/") + FileUploadUtil.getUploadDir();
    File imageFile = new File(uploadDir, fileName);
    
    // 파일이 존재하지 않으면 404 에러
    if (!imageFile.exists() || !imageFile.isFile()) {
        response.sendError(HttpServletResponse.SC_NOT_FOUND, "이미지 파일을 찾을 수 없습니다: " + fileName);
        return;
    }
    
    // 파일 확장자로 MIME 타입 결정
    String contentType = "application/octet-stream";
    String extension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
    switch (extension) {
        case "jpg":
        case "jpeg":
            contentType = "image/jpeg";
            break;
        case "png":
            contentType = "image/png";
            break;
        case "gif":
            contentType = "image/gif";
            break;
        case "bmp":
            contentType = "image/bmp";
            break;
        case "webp":
            contentType = "image/webp";
            break;
    }
    
    response.setContentType(contentType);
    response.setContentLengthLong(imageFile.length());
    
    // 캐시 설정 (선택사항)
    response.setHeader("Cache-Control", "public, max-age=3600");
    
    // 파일 읽기 및 전송
    try (FileInputStream fis = new FileInputStream(imageFile);
         java.io.OutputStream out = response.getOutputStream()) {
        
        byte[] buffer = new byte[8192];
        int bytesRead;
        while ((bytesRead = fis.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
        }
        out.flush();
    } catch (IOException e) {
        // 이미 응답이 시작된 경우 에러 처리 불가
        if (!response.isCommitted()) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "파일 읽기 오류");
        }
    }
%>
