<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<%@ page import="jsp.member.MemberDTO" %>
<%@ page import="jsp.member.MemberDAO" %>
<%@ page import="jsp.member.DBConnection" %>
<%
    String userId = request.getParameter("user_id");
    String password = request.getParameter("password");
    
    // 필수 항목 체크
    if (userId == null || userId.trim().isEmpty() ||
        password == null || password.trim().isEmpty()) {
        response.sendRedirect("login_db.jsp?error=1");
        return;
    }
    
    Connection conn = null;
    
    try {
        // 데이터베이스 연결
        conn = DBConnection.getConnection();
        
        // DAO 객체 생성
        MemberDAO memberDAO = new MemberDAO(conn);
        
        // 비밀번호 확인
        if (memberDAO.checkPassword(userId, password)) {
            // 로그인 성공 - 회원 정보 조회
            MemberDTO member = memberDAO.selectMemberByUserId(userId);
            
            if (member != null) {
                // 세션에 정보 저장
                session.setAttribute("loginId", member.getUserId());
                session.setAttribute("loginName", member.getName());
                session.setAttribute("loginEmail", member.getEmail());
                session.setAttribute("loginTime", new java.util.Date());
                
                // 회원 정보를 세션에 저장 (선택사항)
                session.setAttribute("member", member);
                
                response.sendRedirect("main_db.jsp");
            } else {
                response.sendRedirect("login_db.jsp?error=1");
            }
        } else {
            // 비밀번호 불일치 또는 아이디 없음
            response.sendRedirect("login_db.jsp?error=1");
        }
        
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
        response.sendRedirect("login_db.jsp?error=1");
    } catch (SQLException e) {
        e.printStackTrace();
        response.sendRedirect("login_db.jsp?error=1");
    } finally {
        // 리소스 해제
        DBConnection.close(conn, null);
    }
%>
