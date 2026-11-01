<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.ProductDAO" %>
<%@ include file="/include/adminCheck.jspf" %>
<%
    request.setCharacterEncoding("UTF-8");

    String idParam = request.getParameter("id");
    if (idParam == null || idParam.trim().isEmpty()) {
        response.sendRedirect("productList.jsp");
        return;
    }

    int id;
    try {
        id = Integer.parseInt(idParam.trim());
    } catch (NumberFormatException e) {
        response.sendRedirect("productList.jsp");
        return;
    }

    new ProductDAO().delete(id);
    response.sendRedirect("productList.jsp");
%>
