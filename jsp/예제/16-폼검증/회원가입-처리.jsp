<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 완료</title>
<style>
	body {
		font-family: 'Malgun Gothic', sans-serif;
		background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
		min-height: 100vh;
		padding: 20px;
	}
	.container {
		max-width: 600px;
		margin: 0 auto;
		background-color: white;
		padding: 40px;
		border-radius: 15px;
		box-shadow: 0 10px 40px rgba(0,0,0,0.2);
	}
	h2 {
		color: #333;
		margin-bottom: 30px;
		text-align: center;
	}
	.result-box {
		background-color: #e7f3ff;
		border-left: 4px solid #667eea;
		padding: 20px;
		margin: 20px 0;
		border-radius: 8px;
	}
	.result-item {
		margin: 15px 0;
		padding: 10px;
		background-color: white;
		border-radius: 4px;
	}
	.result-label {
		font-weight: bold;
		color: #667eea;
		display: inline-block;
		width: 120px;
	}
	.btn {
		display: inline-block;
		padding: 12px 30px;
		background-color: #667eea;
		color: white;
		text-decoration: none;
		border-radius: 8px;
		margin-top: 20px;
		text-align: center;
	}
	.btn:hover {
		background-color: #764ba2;
	}
</style>
</head>
<body>
	<div class="container">
		<h2>✅ 회원가입 완료</h2>
		
		<div class="result-box">
			<%
				request.setCharacterEncoding("UTF-8");
				
				// 서버 사이드 검증 (보안을 위해 필수)
				String id = request.getParameter("id");
				String passwd = request.getParameter("passwd");
				String passwd2 = request.getParameter("passwd2");
				String name = request.getParameter("name");
				String email = request.getParameter("email");
				String phone1 = request.getParameter("phone1");
				String phone2 = request.getParameter("phone2");
				String phone3 = request.getParameter("phone3");
				String birthdate = request.getParameter("birthdate");
				String agree = request.getParameter("agree");
				
				// 정규식 패턴 (서버 사이드에서도 검증)
				java.util.regex.Pattern idPattern = java.util.regex.Pattern.compile("^[a-zA-Z][a-zA-Z0-9]{3,11}$");
				java.util.regex.Pattern namePattern = java.util.regex.Pattern.compile("^[가-힣]{2,10}$");
				java.util.regex.Pattern emailPattern = java.util.regex.Pattern.compile("^[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}$");
				java.util.regex.Pattern phonePattern = java.util.regex.Pattern.compile("^010-\\d{4}-\\d{4}$");
				
				// 아이디 검증
				if(id == null || id.trim().isEmpty()){
					out.println("<script>alert('아이디를 입력해 주세요.'); history.back();</script>");
					return;
				}
				if(!idPattern.matcher(id.trim()).matches()){
					out.println("<script>alert('아이디 형식이 올바르지 않습니다.'); history.back();</script>");
					return;
				}
				
				// 비밀번호 검증
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
				
				// 이름 검증
				if(name == null || name.trim().isEmpty()){
					out.println("<script>alert('이름을 입력해 주세요.'); history.back();</script>");
					return;
				}
				if(!namePattern.matcher(name.trim()).matches()){
					out.println("<script>alert('이름은 한글 2-10자로 입력해 주세요.'); history.back();</script>");
					return;
				}
				
				// 이메일 검증
				if(email == null || email.trim().isEmpty()){
					out.println("<script>alert('이메일을 입력해 주세요.'); history.back();</script>");
					return;
				}
				if(!emailPattern.matcher(email.trim()).matches()){
					out.println("<script>alert('올바른 이메일 형식을 입력해 주세요.'); history.back();</script>");
					return;
				}
				
				// 전화번호 검증
				if(phone1 == null || phone1.isEmpty() || 
				   phone2 == null || phone2.trim().isEmpty() || 
				   phone3 == null || phone3.trim().isEmpty()){
					out.println("<script>alert('전화번호를 모두 입력해 주세요.'); history.back();</script>");
					return;
				}
				String phone = phone1 + "-" + phone2.trim() + "-" + phone3.trim();
				if(!phonePattern.matcher(phone).matches()){
					out.println("<script>alert('올바른 전화번호 형식을 입력해 주세요.'); history.back();</script>");
					return;
				}
				
				// 약관 동의 검증
				if(agree == null || !agree.equals("on")){
					out.println("<script>alert('회원가입 약관에 동의해 주세요.'); history.back();</script>");
					return;
				}
			%>
			
			<h3>가입 정보</h3>
			
			<div class="result-item">
				<span class="result-label">아이디:</span>
				<%= id %>
			</div>
			
			<div class="result-item">
				<span class="result-label">이름:</span>
				<%= name %>
			</div>
			
			<div class="result-item">
				<span class="result-label">이메일:</span>
				<%= email %>
			</div>
			
			<div class="result-item">
				<span class="result-label">전화번호:</span>
				<%= phone %>
			</div>
			
			<% if(birthdate != null && !birthdate.trim().isEmpty()) { %>
			<div class="result-item">
				<span class="result-label">생년월일:</span>
				<%= birthdate %>
			</div>
			<% } %>
			
			<div class="result-item">
				<span class="result-label">비밀번호:</span>
				<%= passwd.replaceAll(".", "*") %>
			</div>
			
			<p style="margin-top: 20px; color: #666; font-size: 0.9em;">
				※ 실제 서비스에서는 데이터베이스에 저장하는 로직이 필요합니다.
			</p>
		</div>
		
		<div style="text-align: center;">
			<a href="회원가입-폼-완성본.jsp" class="btn">다시 가입하기</a>
		</div>
	</div>
</body>
</html>
