<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고급 폼 검증 예제</title>
<style>
	.error {
		color: red;
		font-size: 0.9em;
	}
	.success {
		color: green;
		font-size: 0.9em;
	}
	input[type="text"],
	input[type="password"],
	input[type="email"] {
		padding: 5px;
		margin: 5px 0;
	}
</style>
<script type="text/javascript">
	function checkRegister(){
		var form = document.registerForm;
		var isValid = true;
		
		// 아이디 검증
		var id = form.id.value.trim();
		if(id === ""){
			alert("아이디를 입력해 주세요.");
			form.id.focus();
			return false;
		}
		
		// 아이디 길이 검증 (4-12자)
		if(id.length < 4 || id.length > 12){
			alert("아이디는 4자 이상 12자 이하로 입력해 주세요.");
			form.id.focus();
			return false;
		}
		
		// 아이디 형식 검증 (영문, 숫자만)
		var idPattern = /^[a-zA-Z0-9]+$/;
		if(!idPattern.test(id)){
			alert("아이디는 영문과 숫자만 사용할 수 있습니다.");
			form.id.focus();
			return false;
		}
		
		// 비밀번호 검증
		if(form.passwd.value === ""){
			alert("비밀번호를 입력해 주세요.");
			form.passwd.focus();
			return false;
		}
		
		// 비밀번호 길이 검증 (8자 이상)
		if(form.passwd.value.length < 8){
			alert("비밀번호는 8자 이상 입력해 주세요.");
			form.passwd.focus();
			return false;
		}
		
		// 비밀번호 확인 검증
		if(form.passwd.value !== form.passwd2.value){
			alert("비밀번호가 일치하지 않습니다.");
			form.passwd2.focus();
			return false;
		}
		
		// 이메일 검증
		var email = form.email.value.trim();
		if(email === ""){
			alert("이메일을 입력해 주세요.");
			form.email.focus();
			return false;
		}
		
		var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
		if(!emailPattern.test(email)){
			alert("올바른 이메일 형식을 입력해 주세요.\n예: example@email.com");
			form.email.focus();
			return false;
		}
		
		return true;
	}
</script>
</head>
<body>
	<h2>고급 폼 검증 예제</h2>
	<p>다양한 검증 규칙을 적용한 회원가입 폼입니다.</p>
	
	<form name="registerForm" action="validation02_process.jsp" method="post" 
	      onsubmit="return checkRegister()">
		<p>
			아이디 (4-12자, 영문/숫자만):<br>
			<input type="text" name="id" placeholder="예: user123">
		</p>
		
		<p>
			비밀번호 (8자 이상):<br>
			<input type="password" name="passwd" placeholder="비밀번호를 입력하세요">
		</p>
		
		<p>
			비밀번호 확인:<br>
			<input type="password" name="passwd2" placeholder="비밀번호를 다시 입력하세요">
		</p>
		
		<p>
			이메일:<br>
			<input type="email" name="email" placeholder="example@email.com">
		</p>
		
		<p>
			<input type="submit" value="가입하기">
			<input type="reset" value="초기화">
		</p>
	</form>
	
	<hr>
	<h3>검증 규칙</h3>
	<ul>
		<li><strong>아이디</strong>: 4-12자, 영문과 숫자만 허용</li>
		<li><strong>비밀번호</strong>: 8자 이상</li>
		<li><strong>비밀번호 확인</strong>: 비밀번호와 일치해야 함</li>
		<li><strong>이메일</strong>: 올바른 이메일 형식</li>
	</ul>
</body>
</html>
