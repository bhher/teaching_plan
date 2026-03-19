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
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		
		// 서버 사이드 검증
		if(id == null || id.trim().isEmpty()){
			out.println("<script>alert('아이디를 입력해 주세요.'); history.back();</script>");
			return;
		}
		
		if(passwd == null || passwd.isEmpty()){
			out.println("<script>alert('비밀번호를 입력해 주세요.'); history.back();</script>");
			return;
		}
	%>
	
	<p><strong>아이디:</strong> <%= id %></p>
	<p><strong>비밀번호:</strong> <%= passwd.replaceAll(".", "*") %></p>
	
	<p><a href="validation03_realtime.jsp">다시 입력</a></p>
</body>
</html>
