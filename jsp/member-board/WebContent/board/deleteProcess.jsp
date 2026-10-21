<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.BoardDAO" %>
<%@ page import="dto.BoardDTO" %>
<%
    request.setCharacterEncoding("UTF-8");

    String loginIdCheck = (String) session.getAttribute("loginId");
    if (loginIdCheck == null) {
        response.sendRedirect(request.getContextPath() + "/member/login.jsp");
        return;
    }

    String idParam = request.getParameter("id");
    if (idParam == null) {
        response.sendRedirect("list.jsp");
        return;
    }

    int id = Integer.parseInt(idParam.trim());
    BoardDAO dao = new BoardDAO();
    BoardDTO origin = dao.selectById(id);
    if (origin == null) {
        response.sendRedirect("list.jsp");
        return;
    }

    boolean isAdmin = "admin".equals(loginIdCheck);
    if (!loginIdCheck.equals(origin.getWriterId()) && !isAdmin) {
        response.sendRedirect("view.jsp?id=" + id);
        return;
    }

    dao.delete(id);
    response.sendRedirect("list.jsp");
%>
