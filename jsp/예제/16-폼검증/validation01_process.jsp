<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>검증 결과</title>
</head>
<body>
	<h2>폼 제출 성공</h2>
	<%
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		
		// 서버 사이드 검증 (보안을 위해 필수)
		if(id == null || id.trim().isEmpty()){
			out.println("<script>alert('아이디를 입력해 주세요.'); history.back();</script>");
			return;
		}
		
		if(passwd == null || passwd.trim().isEmpty()){
			out.println("<script>alert('비밀번호를 입력해 주세요.'); history.back();</script>");
			return;
		}
	%>
	
	<p>아이디: <%= id %></p>
	<p>비밀번호: <%= passwd.replaceAll(".", "*") %></p>
	
	<p><a href="validation01_basic.jsp">다시 입력</a></p>
</body>
</html>
