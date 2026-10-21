<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.MemberDAO" %>
<%@ page import="dto.MemberDTO" %>
<%
    request.setCharacterEncoding("UTF-8");
    String id = request.getParameter("id");
    String password = request.getParameter("password");
    String name = request.getParameter("name");
    String email = request.getParameter("email");

    if (id == null || id.trim().isEmpty()
            || password == null || password.isEmpty()
            || name == null || name.trim().isEmpty()) {
        response.sendRedirect("join.jsp?error=empty");
        return;
    }

    id = id.trim();
    name = name.trim();
    if (email != null) {
        email = email.trim();
        if (email.isEmpty()) email = null;
    }

    MemberDAO dao = new MemberDAO();
    if (dao.existsId(id)) {
        response.sendRedirect("join.jsp?error=dup");
        return;
    }

    MemberDTO m = new MemberDTO();
    m.setId(id);
    m.setPassword(password);
    m.setName(name);
    m.setEmail(email);
    dao.insert(m);

    response.sendRedirect("login.jsp");
%>
