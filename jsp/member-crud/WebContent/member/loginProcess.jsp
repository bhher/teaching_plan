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

    MemberDAO dao = new MemberDAO();
    MemberDTO member = dao.login(id.trim(), password);

    if (member == null) {
        response.sendRedirect("login.jsp?error=1");
        return;
    }

    session.setAttribute("loginId", member.getId());
    session.setAttribute("loginName", member.getName());
    response.sendRedirect(request.getContextPath() + "/member/list.jsp");
%>
