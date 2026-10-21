<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.AdminDAO" %>
<%@ page import="dto.AdminDTO" %>
<%
    request.setCharacterEncoding("UTF-8");

    String id = request.getParameter("id");
    String password = request.getParameter("password");

    if (id == null || id.trim().isEmpty()
            || password == null || password.isEmpty()) {
        response.sendRedirect("login.jsp?error=empty");
        return;
    }

    AdminDAO dao = new AdminDAO();
    AdminDTO admin = dao.login(id.trim(), password);

    if (admin == null) {
        response.sendRedirect("login.jsp?error=1");
        return;
    }

    // 세션에 관리자 로그인 정보 저장
    session.setAttribute("adminId", admin.getId());
    session.setAttribute("adminName", admin.getName());

    response.sendRedirect(request.getContextPath() + "/admin/productList.jsp");
%>
