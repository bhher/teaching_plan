<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.MemberDAO" %>
<%
    request.setCharacterEncoding("UTF-8");

    String loginIdCheck = (String) session.getAttribute("loginId");
    if (loginIdCheck == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    String id = request.getParameter("id");
    if (id == null || id.trim().isEmpty()) {
        response.sendRedirect("list.jsp");
        return;
    }
    id = id.trim();

    boolean isAdmin = "admin".equals(loginIdCheck);
    if (!loginIdCheck.equals(id) && !isAdmin) {
        response.sendRedirect("view.jsp?id=" + id);
        return;
    }

    // admin 계정 자신은 삭제 방지(강의용 안전장치)
    if ("admin".equals(id)) {
        response.sendRedirect("view.jsp?id=admin");
        return;
    }

    MemberDAO dao = new MemberDAO();
    dao.delete(id);

    // 본인 삭제면 로그아웃
    if (loginIdCheck.equals(id)) {
        session.invalidate();
        response.sendRedirect(request.getContextPath() + "/member/login.jsp");
        return;
    }

    response.sendRedirect("list.jsp");
%>
