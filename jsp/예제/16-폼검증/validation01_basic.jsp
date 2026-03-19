<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기본 폼 검증 예제</title>
<script type="text/javascript">
	function checkLogin(){
		var form = document.loginForm;
		
		// 아이디 검증
		if(form.id.value === "" || form.id.value.trim() === ""){
			alert("아이디를 입력해 주세요.");
			form.id.focus();
			return false;
		}
		
		// 비밀번호 검증
		if(form.passwd.value === ""){
			alert("비밀번호를 입력해 주세요.");
			form.passwd.focus();
			return false;
		}
		
		return true;  // 모든 검증 통과
	}
</script>
</head>
<body>
	<h2>기본 폼 검증 예제</h2>
	<p>아이디와 비밀번호를 입력하지 않으면 폼이 제출되지 않습니다.</p>
	
	<form name="loginForm" action="validation01_process.jsp" method="post" 
	      onsubmit="return checkLogin()">
		<p>아이디 : <input type="text" name="id" placeholder="아이디를 입력하세요">
		<p>비밀번호 : <input type="password" name="passwd" placeholder="비밀번호를 입력하세요">
		<p>
			<input type="submit" value="전송">
			<input type="reset" value="초기화">
		</p>
	</form>
	
	<hr>
	<h3>설명</h3>
	<ul>
		<li><strong>onsubmit="return checkLogin()"</strong>: 폼 제출 전 검증 함수 실행</li>
		<li><strong>return false</strong>: 검증 실패 시 폼 제출 방지</li>
		<li><strong>return true</strong>: 검증 통과 시 폼 제출 허용</li>
		<li>Enter 키로 제출해도 검증이 작동합니다.</li>
	</ul>
</body>
</html>
