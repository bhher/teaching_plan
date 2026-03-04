<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String id = request.getParameter("id");
    String pw = request.getParameter("pw");
    
    // 간단한 인증 (실제로는 DB에서 확인)
    if ("admin".equals(id) && "1234".equals(pw)) {
        // 로그인 성공
        session.setAttribute("loginId", id);
        session.setAttribute("loginTime", new java.util.Date());
        session.setAttribute("loginCount", 1);
        
        // 로그인 횟수 증가
        Integer count = (Integer) session.getAttribute("loginCount");
        if (count != null) {
            session.setAttribute("loginCount", count + 1);
        }
        
        response.sendRedirect("main.jsp");
    } else {
        // 로그인 실패
        response.sendRedirect("login.jsp?error=1");
    }
%>
