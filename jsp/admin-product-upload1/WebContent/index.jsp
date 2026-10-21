<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    if (session.getAttribute("adminId") != null) {
        response.sendRedirect(request.getContextPath() + "/admin/productWrite.jsp");
    } else {
        response.sendRedirect(request.getContextPath() + "/admin/login.jsp");
    }
%>
