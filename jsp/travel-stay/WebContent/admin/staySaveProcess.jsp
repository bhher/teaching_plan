<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.File" %>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ page import="dao.StayDAO" %>
<%@ page import="dto.StayDTO" %>
<%@ include file="adminCheck.jspf" %>
<%
    String savePath = application.getRealPath("/uploads");
    File dir = new File(savePath);
    if (!dir.exists()) dir.mkdirs();

    MultipartRequest multi = new MultipartRequest(
            request, savePath, 5 * 1024 * 1024, "utf-8", new DefaultFileRenamePolicy());

    String idStr = multi.getParameter("id");
    String destIdStr = multi.getParameter("destinationId");
    String name = multi.getParameter("name");
    String address = multi.getParameter("address");
    String priceStr = multi.getParameter("price");
    String description = multi.getParameter("description");
    String imageUrl = multi.getParameter("imageUrl");
    String fileName = multi.getFilesystemName("imageFile");

    if (destIdStr == null || name == null || name.trim().isEmpty()
            || address == null || address.trim().isEmpty() || priceStr == null) {
        response.sendRedirect("stayForm.jsp");
        return;
    }

    String image = null;
    if (fileName != null && !fileName.isEmpty()) image = fileName;
    else if (imageUrl != null && !imageUrl.trim().isEmpty()) image = imageUrl.trim();

    StayDTO s = new StayDTO();
    s.setDestinationId(Integer.parseInt(destIdStr.trim()));
    s.setName(name.trim());
    s.setAddress(address.trim());
    s.setPrice(Integer.parseInt(priceStr.trim()));
    s.setDescription(description != null ? description.trim() : null);
    s.setImage(image);

    StayDAO dao = new StayDAO();
    if (idStr != null && !idStr.trim().isEmpty()) {
        s.setId(Integer.parseInt(idStr.trim()));
        if (image == null) {
            StayDTO old = dao.selectById(s.getId());
            if (old != null) s.setImage(old.getImage());
        }
        dao.update(s);
    } else {
        dao.insert(s);
    }
    response.sendRedirect("stayList.jsp");
%>
