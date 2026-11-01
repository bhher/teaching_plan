<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.StayDAO" %>
<%@ include file="adminCheck.jspf" %>
<%
    String idStr = request.getParameter("id");
    if (idStr != null) {
        new StayDAO().delete(Integer.parseInt(idStr.trim()));
    }
    response.sendRedirect("stayList.jsp");
%>
