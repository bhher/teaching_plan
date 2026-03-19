<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>실시간 폼 검증 예제</title>
<style>
	.error {
		color: red;
		font-size: 0.9em;
		margin-left: 10px;
	}
	.success {
		color: green;
		font-size: 0.9em;
		margin-left: 10px;
	}
	input[type="text"],
	input[type="password"] {
		padding: 5px;
		margin: 5px 0;
		border: 1px solid #ccc;
	}
	input.error-border {
		border-color: red;
	}
	input.success-border {
		border-color: green;
	}
</style>
<script type="text/javascript">
	// 실시간 아이디 검증
	function checkIdRealTime(){
		var id = document.loginForm.id.value.trim();
		var idMsg = document.getElementById("idMsg");
		var idInput = document.loginForm.id;
		
		if(id === ""){
			idMsg.innerHTML = "";
			idInput.className = "";
			return;
		}
		
		if(id.length < 4){
			idMsg.innerHTML = "<span class='error'>❌ 아이디는 4자 이상이어야 합니다.</span>";
			idInput.className = "error-border";
		} else if(id.length > 12){
			idMsg.innerHTML = "<span class='error'>❌ 아이디는 12자 이하여야 합니다.</span>";
			idInput.className = "error-border";
		} else {
			var idPattern = /^[a-zA-Z0-9]+$/;
			if(!idPattern.test(id)){
				idMsg.innerHTML = "<span class='error'>❌ 영문과 숫자만 사용할 수 있습니다.</span>";
				idInput.className = "error-border";
			} else {
				idMsg.innerHTML = "<span class='success'>✅ 사용 가능한 아이디입니다.</span>";
				idInput.className = "success-border";
			}
		}
	}
	
	// 실시간 비밀번호 검증
	function checkPasswdRealTime(){
		var passwd = document.loginForm.passwd.value;
		var passwdMsg = document.getElementById("passwdMsg");
		var passwdInput = document.loginForm.passwd;
		
		if(passwd === ""){
			passwdMsg.innerHTML = "";
			passwdInput.className = "";
			return;
		}
		
		if(passwd.length < 8){
			passwdMsg.innerHTML = "<span class='error'>❌ 비밀번호는 8자 이상이어야 합니다. (현재: " + passwd.length + "자)</span>";
			passwdInput.className = "error-border";
		} else {
			passwdMsg.innerHTML = "<span class='success'>✅ 사용 가능한 비밀번호입니다.</span>";
			passwdInput.className = "success-border";
		}
	}
	
	// 폼 제출 전 최종 검증
	function checkLogin(){
		var form = document.loginForm;
		
		// 아이디 검증
		var id = form.id.value.trim();
		if(id === ""){
			alert("아이디를 입력해 주세요.");
			form.id.focus();
			return false;
		}
		
		if(id.length < 4 || id.length > 12){
			alert("아이디는 4자 이상 12자 이하여야 합니다.");
			form.id.focus();
			return false;
		}
		
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
		
		if(form.passwd.value.length < 8){
			alert("비밀번호는 8자 이상이어야 합니다.");
			form.passwd.focus();
			return false;
		}
		
		return true;
	}
</script>
</head>
<body>
	<h2>실시간 폼 검증 예제</h2>
	<p>입력하는 동안 실시간으로 검증 메시지가 표시됩니다.</p>
	
	<form name="loginForm" action="validation03_process.jsp" method="post" 
	      onsubmit="return checkLogin()">
		<p>
			아이디 (4-12자, 영문/숫자만):<br>
			<input type="text" name="id" placeholder="아이디를 입력하세요" 
			       onkeyup="checkIdRealTime()" onblur="checkIdRealTime()">
			<span id="idMsg"></span>
		</p>
		
		<p>
			비밀번호 (8자 이상):<br>
			<input type="password" name="passwd" placeholder="비밀번호를 입력하세요" 
			       onkeyup="checkPasswdRealTime()" onblur="checkPasswdRealTime()">
			<span id="passwdMsg"></span>
		</p>
		
		<p>
			<input type="submit" value="전송">
			<input type="reset" value="초기화" onclick="document.getElementById('idMsg').innerHTML=''; document.getElementById('passwdMsg').innerHTML='';">
		</p>
	</form>
	
	<hr>
	<h3>기능 설명</h3>
	<ul>
		<li><strong>onkeyup</strong>: 키를 누를 때마다 검증 실행</li>
		<strong>onblur</strong>: 입력 필드에서 포커스가 벗어날 때 검증 실행</li>
		<li>검증 통과 시 초록색, 실패 시 빨간색으로 표시</li>
		<li>입력 필드 테두리 색상도 함께 변경</li>
	</ul>
</body>
</html>
