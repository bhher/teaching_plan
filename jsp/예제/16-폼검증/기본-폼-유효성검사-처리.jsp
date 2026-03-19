<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>폼 제출 결과</title>
<style>
	body {
		font-family: 'Malgun Gothic', sans-serif;
		max-width: 600px;
		margin: 50px auto;
		padding: 20px;
		background-color: #f5f5f5;
	}
	.container {
		background-color: white;
		padding: 30px;
		border-radius: 10px;
		box-shadow: 0 2px 10px rgba(0,0,0,0.1);
	}
	h2 {
		color: #333;
		margin-bottom: 20px;
	}
	.result-box {
		background-color: #e7f3ff;
		border-left: 4px solid #667eea;
		padding: 15px;
		margin: 20px 0;
		border-radius: 4px;
	}
	.result-item {
		margin: 10px 0;
		padding: 8px;
		background-color: white;
		border-radius: 4px;
	}
	.btn {
		display: inline-block;
		padding: 10px 20px;
		background-color: #667eea;
		color: white;
		text-decoration: none;
		border-radius: 4px;
		margin-top: 20px;
	}
	.btn:hover {
		background-color: #764ba2;
	}
</style>
</head>
<body>
	<div class="container">
		<h2>✅ 폼 제출 성공</h2>
		
		<div class="result-box">
			<h3>제출된 정보</h3>
			<%
				request.setCharacterEncoding("UTF-8");
				
				String id = request.getParameter("id");
				String passwd = request.getParameter("passwd");
				String email = request.getParameter("email");
				
				// 서버 사이드 검증 (보안을 위해 필수)
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
				
				if(passwd.length() < 4){
					out.println("<script>alert('비밀번호는 4자 이상이어야 합니다.'); history.back();</script>");
					return;
				}
			%>
			
			<div class="result-item">
				<strong>아이디:</strong> <%= id %>
			</div>
			
			<div class="result-item">
				<strong>비밀번호:</strong> <%= passwd.replaceAll(".", "*") %>
			</div>
			
			<% if(email != null && !email.trim().isEmpty()) { %>
			<div class="result-item">
				<strong>이메일:</strong> <%= email %>
			</div>
			<% } %>
		</div>
		
		<a href="기본-폼-유효성검사-예제.jsp" class="btn">다시 입력</a>
	</div>
</body>
</html>
