<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.DestinationDAO" %>
<%@ include file="adminCheck.jspf" %>
<%
    String idStr = request.getParameter("id");
    if (idStr != null) {
        new DestinationDAO().delete(Integer.parseInt(idStr.trim()));
    }
    response.sendRedirect("destList.jsp");
%>
