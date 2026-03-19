<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기본 폼 유효성 검사 예제</title>
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
	.form-group {
		margin-bottom: 20px;
	}
	label {
		display: block;
		margin-bottom: 5px;
		font-weight: bold;
		color: #555;
	}
	input[type="text"],
	input[type="password"],
	input[type="email"] {
		width: 100%;
		padding: 10px;
		border: 1px solid #ddd;
		border-radius: 4px;
		font-size: 14px;
		box-sizing: border-box;
	}
	input[type="text"]:focus,
	input[type="password"]:focus,
	input[type="email"]:focus {
		border-color: #667eea;
		outline: none;
	}
	.btn-group {
		margin-top: 20px;
	}
	.btn {
		padding: 10px 20px;
		border: none;
		border-radius: 4px;
		cursor: pointer;
		font-size: 14px;
		margin-right: 10px;
	}
	.btn-submit {
		background-color: #667eea;
		color: white;
	}
	.btn-submit:hover {
		background-color: #764ba2;
	}
	.btn-reset {
		background-color: #6c757d;
		color: white;
	}
	.btn-reset:hover {
		background-color: #5a6268;
	}
	.info-box {
		background-color: #e7f3ff;
		border-left: 4px solid #667eea;
		padding: 15px;
		margin-top: 20px;
		border-radius: 4px;
	}
	.info-box h3 {
		margin-top: 0;
		color: #667eea;
	}
	.info-box ul {
		margin: 10px 0;
		padding-left: 20px;
	}
</style>
<script type="text/javascript">
	/**
	 * 폼 유효성 검사 함수
	 * @returns {boolean} 검증 통과 시 true, 실패 시 false
	 */
	function validateForm(){
		// 폼 객체 가져오기
		var form = document.loginForm;
		
		// ========== 1. 아이디 검증 ==========
		var id = form.id.value.trim();  // 앞뒤 공백 제거
		
		// 1-1. 빈 값 검증
		if(id === ""){
			alert("아이디를 입력해 주세요.");
			form.id.focus();  // 아이디 입력 필드로 포커스 이동
			return false;  // 폼 제출 방지
		}
		
		// 1-2. 길이 검증 (4자 이상 12자 이하)
		if(id.length < 4 || id.length > 12){
			alert("아이디는 4자 이상 12자 이하로 입력해 주세요.\n현재 입력된 길이: " + id.length + "자");
			form.id.select();  // 입력된 텍스트 선택
			return false;
		}
		
		// ========== 2. 비밀번호 검증 ==========
		var passwd = form.passwd.value;
		
		// 2-1. 빈 값 검증
		if(passwd === ""){
			alert("비밀번호를 입력해 주세요.");
			form.passwd.focus();
			return false;
		}
		
		// 2-2. 길이 검증 (4자 이상)
		if(passwd.length < 4){
			alert("비밀번호는 4자 이상으로 입력해 주세요.\n현재 입력된 길이: " + passwd.length + "자");
			form.passwd.select();
			return false;
		}
		
		// ========== 3. 이메일 검증 (선택사항) ==========
		var email = form.email.value.trim();
		
		// 이메일이 입력된 경우에만 검증
		if(email !== ""){
			// 이메일 형식 검증 (정규식 사용)
			var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
			if(!emailPattern.test(email)){
				alert("올바른 이메일 형식을 입력해 주세요.\n예: example@email.com");
				form.email.focus();
				return false;
			}
		}
		
		// ========== 모든 검증 통과 ==========
		return true;  // 폼 제출 허용
	}
</script>
</head>
<body>
	<div class="container">
		<h2>📝 기본 폼 유효성 검사 예제</h2>
		
		<form name="loginForm" action="기본-폼-유효성검사-처리.jsp" method="post" 
		      onsubmit="return validateForm()">
			
			<div class="form-group">
				<label for="id">아이디 <span style="color:red;">*</span></label>
				<input type="text" id="id" name="id" 
				       placeholder="4자 이상 12자 이하">
			</div>
			
			<div class="form-group">
				<label for="passwd">비밀번호 <span style="color:red;">*</span></label>
				<input type="password" id="passwd" name="passwd" 
				       placeholder="4자 이상">
			</div>
			
			<div class="form-group">
				<label for="email">이메일 (선택)</label>
				<input type="email" id="email" name="email" 
				       placeholder="example@email.com">
			</div>
			
			<div class="btn-group">
				<button type="submit" class="btn btn-submit">제출</button>
				<button type="reset" class="btn btn-reset">초기화</button>
			</div>
		</form>
		
		<div class="info-box">
			<h3>📌 검증 규칙</h3>
			<ul>
				<li><strong>아이디</strong>: 필수 입력, 4자 이상 12자 이하</li>
				<li><strong>비밀번호</strong>: 필수 입력, 4자 이상</li>
				<li><strong>이메일</strong>: 선택 입력, 입력 시 올바른 형식이어야 함</li>
			</ul>
			
			<h3>🔑 핵심 포인트</h3>
			<ul>
				<li><code>onsubmit="return validateForm()"</code>: 폼 제출 전 검증 실행</li>
				<li><code>return false</code>: 검증 실패 시 폼 제출 방지</li>
				<li><code>return true</code>: 검증 통과 시 폼 제출 허용</li>
				<li><code>.trim()</code>: 앞뒤 공백 제거</li>
				<li><code>.focus()</code>: 해당 입력 필드로 포커스 이동</li>
				<li><code>.select()</code>: 입력된 텍스트 선택</li>
			</ul>
		</div>
	</div>
</body>
</html>
