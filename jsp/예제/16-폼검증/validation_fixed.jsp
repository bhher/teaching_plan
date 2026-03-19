<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function checkLogin(){
		var form = document.loginForm;
		
		// 아이디 빈 값 검증 추가
		if(form.id.value === "" || form.id.value.trim() === ""){
			alert("아이디를 입력해 주세요.");
			form.id.focus();
			return false;
		}
		
		// 아이디 길이 검증
		if(form.id.value.length < 4 || form.id.value.length > 12){
			alert("아이디는 4~12자 이내로 입력가능합니다.");
			form.id.select();
			return false;
		}
		
		// 비밀번호 빈 값 검증 추가
		if(form.passwd.value === ""){
			alert("비밀번호를 입력해 주세요.");
			form.passwd.focus();
			return false;
		}
		
		// 비밀번호 길이 검증
		if(form.passwd.value.length < 4){
			alert("비밀번호는 4자 이상으로 입력해야 합니다!");
			form.passwd.select();
			return false;
		}
	
		return true;  // 모든 검증 통과 시 true 반환
	}
</script>
</head>
<body>
	<form name="loginForm" action="validation03_process.jsp" method="post" 
	      onsubmit="return checkLogin()">
		<p>아이디 : <input type="text" name="id">
		<p>비밀번호 : <input type="password" name="passwd">
		<p><input type="submit" value="전송">
	</form>
</body>
</html>
