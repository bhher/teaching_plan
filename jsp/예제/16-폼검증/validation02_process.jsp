<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가입 결과</title>
</head>
<body>
	<h2>회원가입 정보</h2>
	<%
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		String passwd2 = request.getParameter("passwd2");
		String email = request.getParameter("email");
		
		// 서버 사이드 검증
		if(id == null || id.trim().isEmpty()){
			out.println("<script>alert('아이디를 입력해 주세요.'); history.back();</script>");
			return;
		}
		
		if(id.length() < 4 || id.length() > 12){
			out.println("<script>alert('아이디는 4자 이상 12자 이하로 입력해 주세요.'); history.back();</script>");
			return;
		}
		
		if(passwd == null || passwd.isEmpty()){
			out.println("<script>alert('비밀번호를 입력해 주세요.'); history.back();</script>");
			return;
		}
		
		if(passwd.length() < 8){
			out.println("<script>alert('비밀번호는 8자 이상이어야 합니다.'); history.back();</script>");
			return;
		}
		
		if(!passwd.equals(passwd2)){
			out.println("<script>alert('비밀번호가 일치하지 않습니다.'); history.back();</script>");
			return;
		}
		
		if(email == null || email.trim().isEmpty()){
			out.println("<script>alert('이메일을 입력해 주세요.'); history.back();</script>");
			return;
		}
	%>
	
	<p><strong>아이디:</strong> <%= id %></p>
	<p><strong>비밀번호:</strong> <%= passwd.replaceAll(".", "*") %></p>
	<p><strong>이메일:</strong> <%= email %></p>
	
	<p><a href="validation02_advanced.jsp">다시 입력</a></p>
</body>
</html>
