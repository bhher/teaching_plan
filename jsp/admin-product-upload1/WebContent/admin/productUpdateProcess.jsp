<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.File" %>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ page import="dao.ProductDAO" %>
<%@ page import="dto.ProductDTO" %>
<%@ include file="/include/adminCheck.jspf" %>
<%
    String savePath = application.getRealPath("/uploads");
    File dir = new File(savePath);
    if (!dir.exists()) {
        dir.mkdirs();
    }

    MultipartRequest multi = new MultipartRequest(
            request, savePath, 5 * 1024 * 1024, "utf-8", new DefaultFileRenamePolicy());

    String idStr = multi.getParameter("id");
    String name = multi.getParameter("name");
    String priceStr = multi.getParameter("price");
    String description = multi.getParameter("description");
    String fileName = multi.getFilesystemName("imageFile");

    if (idStr == null || idStr.trim().isEmpty()) {
        response.sendRedirect("productList.jsp");
        return;
    }

    int id = Integer.parseInt(idStr.trim());

    if (name == null || name.trim().isEmpty()
            || priceStr == null || priceStr.trim().isEmpty()) {
        response.sendRedirect("productUpdate.jsp?id=" + id + "&error=empty");
        return;
    }

    int price;
    try {
        price = Integer.parseInt(priceStr.trim());
        if (price < 0) {
            response.sendRedirect("productUpdate.jsp?id=" + id + "&error=empty");
            return;
        }
    } catch (NumberFormatException e) {
        response.sendRedirect("productUpdate.jsp?id=" + id + "&error=empty");
        return;
    }

    ProductDAO dao = new ProductDAO();
    ProductDTO old = dao.selectById(id);
    if (old == null) {
        response.sendRedirect("productList.jsp");
        return;
    }

    // 새 사진이 없으면 기존 파일명 유지
    String image = old.getImage();
    if (fileName != null && !fileName.isEmpty()) {
        image = fileName;
    }

    if (description != null) {
        description = description.trim();
        if (description.isEmpty()) description = null;
    }

    ProductDTO p = new ProductDTO();
    p.setId(id);
    p.setName(name.trim());
    p.setPrice(price);
    p.setImage(image);
    p.setDescription(description);

    dao.update(p);
    response.sendRedirect("productList.jsp");
%>
