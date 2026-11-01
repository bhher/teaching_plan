<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.BoardDAO" %>
<%@ page import="dto.BoardDTO" %>
<%
    request.setCharacterEncoding("UTF-8");

    String loginId = (String) session.getAttribute("loginId");
    String loginName = (String) session.getAttribute("loginName");
    if (loginId == null) {
        response.sendRedirect(request.getContextPath() + "/member/login.jsp");
        return;
    }

    String title = request.getParameter("title");
    String content = request.getParameter("content");
    if (title == null || title.trim().isEmpty()
            || content == null || content.trim().isEmpty()) {
        response.sendRedirect("write.jsp");
        return;
    }

    BoardDTO b = new BoardDTO();
    b.setTitle(title.trim());
    b.setContent(content.trim());
    b.setWriterId(loginId);       // 세션 연동
    b.setWriterName(loginName);   // 세션 연동

    new BoardDAO().insert(b);
    response.sendRedirect("list.jsp");
%>
