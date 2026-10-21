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
    String title = request.getParameter("title");
    String content = request.getParameter("content");
    if (idParam == null || title == null || title.trim().isEmpty()
            || content == null || content.trim().isEmpty()) {
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

    BoardDTO b = new BoardDTO();
    b.setId(id);
    b.setTitle(title.trim());
    b.setContent(content.trim());
    dao.update(b);

    response.sendRedirect("view.jsp?id=" + id);
%>
