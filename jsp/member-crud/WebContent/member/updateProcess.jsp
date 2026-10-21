<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.MemberDAO" %>
<%@ page import="dto.MemberDTO" %>
<%
    request.setCharacterEncoding("UTF-8");

    String loginIdCheck = (String) session.getAttribute("loginId");
    if (loginIdCheck == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    String id = request.getParameter("id");
    String password = request.getParameter("password");
    String name = request.getParameter("name");
    String email = request.getParameter("email");

    if (id == null || id.trim().isEmpty()
            || password == null || password.isEmpty()
            || name == null || name.trim().isEmpty()) {
        response.sendRedirect("list.jsp");
        return;
    }
    id = id.trim();

    boolean isAdmin = "admin".equals(loginIdCheck);
    if (!loginIdCheck.equals(id) && !isAdmin) {
        response.sendRedirect("view.jsp?id=" + id);
        return;
    }

    if (email != null) {
        email = email.trim();
        if (email.isEmpty()) email = null;
    }

    MemberDTO m = new MemberDTO();
    m.setId(id);
    m.setPassword(password);
    m.setName(name.trim());
    m.setEmail(email);

    MemberDAO dao = new MemberDAO();
    dao.update(m);

    // 본인 정보 수정 시 세션 이름 갱신
    if (loginIdCheck.equals(id)) {
        session.setAttribute("loginName", m.getName());
    }

    response.sendRedirect("view.jsp?id=" + id);
%>
