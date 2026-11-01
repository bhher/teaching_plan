<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.MemberDAO" %>
<%@ page import="dto.MemberDTO" %>
<%
    request.setCharacterEncoding("UTF-8");
    String id = request.getParameter("id");
    String password = request.getParameter("password");
    if (id == null || id.trim().isEmpty() || password == null || password.isEmpty()) {
        response.sendRedirect("login.jsp?error=1");
        return;
    }
    MemberDTO m = new MemberDAO().login(id.trim(), password);
    if (m == null) {
        response.sendRedirect("login.jsp?error=1");
        return;
    }
    session.setAttribute("loginId", m.getId());
    session.setAttribute("loginName", m.getName());
    session.setAttribute("loginRole", m.getRole());
    if (m.isAdmin()) {
        response.sendRedirect(request.getContextPath() + "/admin/index.jsp");
    } else {
        response.sendRedirect(request.getContextPath() + "/destination/list.jsp");
    }
%>
