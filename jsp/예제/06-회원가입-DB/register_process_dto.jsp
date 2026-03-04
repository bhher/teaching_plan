<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="jsp.member.MemberDTO" %>
<%@ page import="jsp.member.MemberDAO" %>
<%@ page import="jsp.member.DBConnection" %>
<%
    // 파라미터 받기
    String userId = request.getParameter("user_id");
    String password = request.getParameter("password");
    String passwordConfirm = request.getParameter("password_confirm");
    String name = request.getParameter("name");
    String email = request.getParameter("email");
    String gender = request.getParameter("gender");
    String[] hobbies = request.getParameterValues("hobby");
    String city = request.getParameter("city");
    String bio = request.getParameter("bio");
    
    // 필수 항목 체크
    if (userId == null || userId.trim().isEmpty() ||
        password == null || password.trim().isEmpty() ||
        name == null || name.trim().isEmpty()) {
        response.sendRedirect("register.jsp?error=empty");
        return;
    }
    
    // 비밀번호 확인
    if (!password.equals(passwordConfirm)) {
        response.sendRedirect("register.jsp?error=password_mismatch&user_id=" + userId + "&name=" + name);
        return;
    }
    
    // 취미 배열을 리스트로 변환
    List<String> hobbyList = null;
    if (hobbies != null && hobbies.length > 0) {
        hobbyList = Arrays.asList(hobbies);
    }
    
    // DTO 객체 생성
    MemberDTO member = new MemberDTO();
    member.setUserId(userId);
    member.setPassword(password);
    member.setName(name);
    member.setEmail(email != null && !email.isEmpty() ? email : null);
    member.setGender(gender != null && !gender.isEmpty() ? gender : null);
    member.setCity(city != null && !city.isEmpty() ? city : null);
    member.setBio(bio != null && !bio.isEmpty() ? bio : null);
    member.setHobbies(hobbyList);
    
    Connection conn = null;
    
    try {
        // 데이터베이스 연결
        conn = DBConnection.getConnection();
        
        // DAO 객체 생성
        MemberDAO memberDAO = new MemberDAO(conn);
        
        // 아이디 중복 체크
        if (memberDAO.isUserIdExists(userId)) {
            response.sendRedirect("register.jsp?error=duplicate&user_id=" + userId);
            return;
        }
        
        // 트랜잭션 시작
        conn.setAutoCommit(false);
        
        // 회원 등록
        int memberId = memberDAO.insertMember(member);
        
        if (memberId > 0) {
            // 성공
            conn.commit();
            conn.setAutoCommit(true);
            response.sendRedirect("register.jsp?success=1");
        } else {
            // 실패
            conn.rollback();
            conn.setAutoCommit(true);
            response.sendRedirect("register.jsp?error=db_error");
        }
        
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
        try {
            if (conn != null && !conn.getAutoCommit()) {
                conn.rollback();
                conn.setAutoCommit(true);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        response.sendRedirect("register.jsp?error=db_error");
    } catch (SQLException e) {
        e.printStackTrace();
        try {
            if (conn != null && !conn.getAutoCommit()) {
                conn.rollback();
                conn.setAutoCommit(true);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        response.sendRedirect("register.jsp?error=db_error");
    } finally {
        // 리소스 해제
        DBConnection.close(conn, null);
    }
%>
