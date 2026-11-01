<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.ReviewDAO" %>
<%@ page import="dto.ReviewDTO" %>
<%
    request.setCharacterEncoding("UTF-8");
    String loginId = (String) session.getAttribute("loginId");
    if (loginId == null) {
        response.sendRedirect(request.getContextPath() + "/member/login.jsp");
        return;
    }
    int destinationId = Integer.parseInt(request.getParameter("destinationId"));
    int rating = Integer.parseInt(request.getParameter("rating"));
    String content = request.getParameter("content");
    if (content == null || content.trim().isEmpty() || rating < 1 || rating > 5) {
        response.sendRedirect(request.getContextPath() + "/destination/view.jsp?id=" + destinationId);
        return;
    }
    ReviewDTO r = new ReviewDTO();
    r.setDestinationId(destinationId);
    r.setMemberId(loginId);
    r.setRating(rating);
    r.setContent(content.trim());
    new ReviewDAO().insert(r);
    response.sendRedirect(request.getContextPath() + "/destination/view.jsp?id=" + destinationId);
%>
