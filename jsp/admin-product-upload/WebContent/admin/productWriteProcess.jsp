<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.File" %>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ page import="dao.ProductDAO" %>
<%@ page import="dto.ProductDTO" %>
<%@ include file="/include/adminCheck.jspf" %>
<%
    // 1) 업로드 폴더 (프로젝트 WebContent/uploads)
    String savePath = application.getRealPath("/uploads");
    File dir = new File(savePath);
    if (!dir.exists()) {
        dir.mkdirs();
    }

    // 2) MultipartRequest로 폼 + 파일 받기
    MultipartRequest multi = new MultipartRequest(
            request,
            savePath,
            5 * 1024 * 1024,
            "utf-8",
            new DefaultFileRenamePolicy());

    String name = multi.getParameter("name");
    String priceStr = multi.getParameter("price");
    String description = multi.getParameter("description");
    String fileName = multi.getFilesystemName("imageFile"); // 저장된 파일명

    if (name == null || name.trim().isEmpty()
            || priceStr == null || priceStr.trim().isEmpty()) {
        response.sendRedirect("productWrite.jsp?error=empty");
        return;
    }
    if (fileName == null || fileName.isEmpty()) {
        response.sendRedirect("productWrite.jsp?error=file");
        return;
    }

    int price;
    try {
        price = Integer.parseInt(priceStr.trim());
        if (price < 0) {
            response.sendRedirect("productWrite.jsp?error=empty");
            return;
        }
    } catch (NumberFormatException e) {
        response.sendRedirect("productWrite.jsp?error=empty");
        return;
    }

    if (description != null) {
        description = description.trim();
        if (description.isEmpty()) description = null;
    }

    // 3) DB 저장 (image 컬럼에 파일명만 저장)
    ProductDTO p = new ProductDTO();
    p.setName(name.trim());
    p.setPrice(price);
    p.setImage(fileName);
    p.setDescription(description);

    new ProductDAO().insert(p);

    response.sendRedirect("productWrite.jsp?msg=ok");
%>
