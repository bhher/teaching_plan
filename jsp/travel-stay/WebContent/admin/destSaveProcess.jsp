<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.File" %>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ page import="dao.DestinationDAO" %>
<%@ page import="dto.DestinationDTO" %>
<%@ include file="adminCheck.jspf" %>
<%
    // COS MultipartRequest: 일반 파라미터 + 파일 동시 처리
    String savePath = application.getRealPath("/uploads");
    File dir = new File(savePath);
    if (!dir.exists()) dir.mkdirs();

    MultipartRequest multi = new MultipartRequest(
            request, savePath, 5 * 1024 * 1024, "utf-8", new DefaultFileRenamePolicy());

    String idStr = multi.getParameter("id");
    String name = multi.getParameter("name");
    String region = multi.getParameter("region");
    String description = multi.getParameter("description");
    String imageUrl = multi.getParameter("imageUrl");
    String fileName = multi.getFilesystemName("imageFile");

    if (name == null || name.trim().isEmpty()
            || region == null || region.trim().isEmpty()
            || description == null || description.trim().isEmpty()) {
        response.sendRedirect("destForm.jsp");
        return;
    }

    String image = null;
    if (fileName != null && !fileName.isEmpty()) {
        image = fileName; // uploads/ 아래 파일명
    } else if (imageUrl != null && !imageUrl.trim().isEmpty()) {
        image = imageUrl.trim();
    }

    DestinationDTO d = new DestinationDTO();
    d.setName(name.trim());
    d.setRegion(region.trim());
    d.setDescription(description.trim());
    d.setImage(image);

    DestinationDAO dao = new DestinationDAO();
    if (idStr != null && !idStr.trim().isEmpty()) {
        d.setId(Integer.parseInt(idStr.trim()));
        // 수정 시 이미지 안 바꾸면 기존 유지
        if (image == null) {
            DestinationDTO old = dao.selectById(d.getId());
            if (old != null) d.setImage(old.getImage());
        }
        dao.update(d);
    } else {
        dao.insert(d);
    }
    response.sendRedirect("destList.jsp");
%>
