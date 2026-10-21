<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.WishlistDAO" %>
<%
    String loginId = (String) session.getAttribute("loginId");
    if (loginId == null) {
        response.sendRedirect(request.getContextPath() + "/member/login.jsp");
        return;
    }
    int destinationId = Integer.parseInt(request.getParameter("destinationId"));
    WishlistDAO dao = new WishlistDAO();
    if (dao.exists(loginId, destinationId)) {
        dao.remove(loginId, destinationId);
    } else {
        dao.add(loginId, destinationId);
    }
    response.sendRedirect(request.getContextPath() + "/destination/view.jsp?id=" + destinationId);
%>
