<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.regex.Pattern" %>
<%@ page import="java.util.regex.Matcher" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 완료</title>
<style>
	* {
		margin: 0;
		padding: 0;
		box-sizing: border-box;
	}
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
		font-size: 2em;
	}
	.success-box {
		background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
		color: white;
		padding: 20px;
		border-radius: 10px;
		text-align: center;
		margin-bottom: 30px;
		font-size: 1.2em;
	}
	.result-box {
		background-color: #f8f9fa;
		border-left: 4px solid #667eea;
		padding: 25px;
		margin: 20px 0;
		border-radius: 8px;
	}
	.result-item {
		margin: 15px 0;
		padding: 12px;
		background-color: white;
		border-radius: 6px;
		display: flex;
		align-items: center;
	}
	.result-label {
		font-weight: bold;
		color: #667eea;
		display: inline-block;
		width: 120px;
		font-size: 14px;
	}
	.result-value {
		color: #333;
		font-size: 14px;
		flex: 1;
	}
	.btn-group {
		display: flex;
		gap: 10px;
		margin-top: 30px;
	}
	.btn {
		flex: 1;
		padding: 12px;
		background-color: #667eea;
		color: white;
		text-decoration: none;
		border-radius: 8px;
		text-align: center;
		font-weight: bold;
		display: inline-block;
		transition: all 0.3s;
	}
	.btn:hover {
		background-color: #764ba2;
		transform: translateY(-2px);
		box-shadow: 0 5px 15px rgba(102, 126, 234, 0.4);
	}
	.error-box {
		background-color: #fee;
		border-left: 4px solid #dc3545;
		padding: 20px;
		margin: 20px 0;
		border-radius: 8px;
		color: #c33;
	}
</style>
</head>
<body>
	<div class="container">
		<h2>회원가입 결과</h2>
		
		<%
			request.setCharacterEncoding("UTF-8");
			
			// 파라미터 가져오기
			String id = request.getParameter("id");
			String passwd = request.getParameter("passwd");
			String passwd2 = request.getParameter("passwd2");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String phone1 = request.getParameter("phone1");
			String phone2 = request.getParameter("phone2");
			String phone3 = request.getParameter("phone3");
			String agree = request.getParameter("agree");
			
			// ========== 서버 사이드 검증 ==========
			boolean isValid = true;
			String errorMessage = "";
			
			// 정규식 패턴 (Java Pattern 사용)
			Pattern idPattern = Pattern.compile("^[a-zA-Z][a-zA-Z0-9]{3,11}$");
			Pattern namePattern = Pattern.compile("^[가-힣]{2,10}$");
			Pattern emailPattern = Pattern.compile("^[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}$", Pattern.CASE_INSENSITIVE);
			Pattern phonePattern = Pattern.compile("^010-\\d{4}-\\d{4}$");
			
			// 1. 아이디 검증
			if(id == null || id.trim().isEmpty()){
				isValid = false;
				errorMessage = "아이디를 입력해 주세요.";
			} else if(id.length() < 4 || id.length() > 12){
				isValid = false;
				errorMessage = "아이디는 4자 이상 12자 이하로 입력해 주세요.";
			} else if(!idPattern.matcher(id.trim()).matches()){
				isValid = false;
				errorMessage = "아이디는 영문으로 시작하며, 영문과 숫자만 사용할 수 있습니다.";
			}
			
			// 2. 비밀번호 검증
			if(isValid){
				if(passwd == null || passwd.isEmpty()){
					isValid = false;
					errorMessage = "비밀번호를 입력해 주세요.";
				} else if(passwd.length() < 8){
					isValid = false;
					errorMessage = "비밀번호는 8자 이상이어야 합니다.";
				} else if(!passwd.equals(passwd2)){
					isValid = false;
					errorMessage = "비밀번호가 일치하지 않습니다.";
				}
			}
			
			// 3. 이름 검증
			if(isValid){
				if(name == null || name.trim().isEmpty()){
					isValid = false;
					errorMessage = "이름을 입력해 주세요.";
				} else if(!namePattern.matcher(name.trim()).matches()){
					isValid = false;
					errorMessage = "이름은 한글만 입력할 수 있으며, 2자 이상 10자 이하입니다.";
				}
			}
			
			// 4. 이메일 검증
			if(isValid){
				if(email == null || email.trim().isEmpty()){
					isValid = false;
					errorMessage = "이메일을 입력해 주세요.";
				} else if(!emailPattern.matcher(email.trim()).matches()){
					isValid = false;
					errorMessage = "올바른 이메일 형식을 입력해 주세요.";
				}
			}
			
			// 5. 전화번호 검증
			if(isValid){
				if(phone1 == null || phone1.isEmpty() || 
				   phone2 == null || phone2.trim().isEmpty() || 
				   phone3 == null || phone3.trim().isEmpty()){
					isValid = false;
					errorMessage = "전화번호를 모두 입력해 주세요.";
				} else {
					String phone = phone1 + "-" + phone2.trim() + "-" + phone3.trim();
					if(!phonePattern.matcher(phone).matches()){
						isValid = false;
						errorMessage = "올바른 전화번호 형식을 입력해 주세요. (예: 010-1234-5678)";
					}
				}
			}
			
			// 6. 약관 동의 검증
			if(isValid){
				if(agree == null || !agree.equals("on")){
					isValid = false;
					errorMessage = "회원가입 약관에 동의해 주세요.";
				}
			}
			
			// 검증 실패 시
			if(!isValid){
		%>
				<div class="error-box">
					<h3>❌ 가입 실패</h3>
					<p><strong>오류:</strong> <%= errorMessage %></p>
					<p style="margin-top: 15px;">
						<a href="javascript:history.back()" class="btn">다시 입력하기</a>
					</p>
				</div>
		<%
				return;
			}
			
			// 검증 통과 시
			String phone = phone1 + "-" + phone2.trim() + "-" + phone3.trim();
		%>
		
		<div class="success-box">
			✅ 회원가입이 완료되었습니다!
		</div>
		
		<div class="result-box">
			<h3 style="margin-bottom: 20px; color: #333;">가입 정보</h3>
			
			<div class="result-item">
				<span class="result-label">아이디:</span>
				<span class="result-value"><%= id %></span>
			</div>
			
			<div class="result-item">
				<span class="result-label">이름:</span>
				<span class="result-value"><%= name %></span>
			</div>
			
			<div class="result-item">
				<span class="result-label">이메일:</span>
				<span class="result-value"><%= email %></span>
			</div>
			
			<div class="result-item">
				<span class="result-label">전화번호:</span>
				<span class="result-value"><%= phone %></span>
			</div>
			
			<div class="result-item">
				<span class="result-label">비밀번호:</span>
				<span class="result-value"><%= passwd.replaceAll(".", "*") %></span>
			</div>
			
			<div class="result-item">
				<span class="result-label">가입일시:</span>
				<span class="result-value">
					<%= new java.text.SimpleDateFormat("yyyy년 MM월 dd일 HH:mm:ss").format(new java.util.Date()) %>
				</span>
			</div>
		</div>
		
		<div style="background-color: #e7f3ff; padding: 15px; border-radius: 8px; margin-top: 20px;">
			<p style="color: #666; font-size: 0.9em; margin-bottom: 10px;">
				<strong>참고사항:</strong>
			</p>
			<ul style="color: #666; font-size: 0.9em; margin-left: 20px;">
				<li>실제 서비스에서는 데이터베이스에 저장하는 로직이 필요합니다.</li>
				<li>비밀번호는 해시화하여 저장해야 합니다.</li>
				<li>이메일 인증이나 SMS 인증을 추가할 수 있습니다.</li>
			</ul>
		</div>
		
		<div class="btn-group">
			<a href="회원가입-Validation-보편적.jsp" class="btn">다시 가입하기</a>
		</div>
	</div>
</body>
</html>
