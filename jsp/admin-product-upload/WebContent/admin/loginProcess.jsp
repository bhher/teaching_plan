<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.AdminDAO" %>
<%@ page import="dto.AdminDTO" %>
<%
    request.setCharacterEncoding("UTF-8");
    String id = request.getParameter("id");
    String password = request.getParameter("password");
    if (id == null || password == null || id.trim().isEmpty() || password.isEmpty()) {
        response.sendRedirect("login.jsp?error=1");
        return;
    }
    AdminDTO admin = new AdminDAO().login(id.trim(), password);
    if (admin == null) {
        response.sendRedirect("login.jsp?error=1");
        return;
    }
    session.setAttribute("adminId", admin.getId());
    session.setAttribute("adminName", admin.getName());
    response.sendRedirect(request.getContextPath() + "/admin/productWrite.jsp");
%>
