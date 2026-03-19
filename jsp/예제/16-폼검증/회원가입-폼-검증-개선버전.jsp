<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>Validation - 개선 버전</title>
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
	h3 {
		color: #333;
		margin-bottom: 20px;
	}
	.form-group {
		margin-bottom: 15px;
	}
	label {
		display: inline-block;
		width: 100px;
		font-weight: bold;
		color: #555;
	}
	input[type="text"],
	input[type="password"],
	select {
		padding: 8px;
		border: 1px solid #ddd;
		border-radius: 4px;
		font-size: 14px;
	}
	input[type="text"]:focus,
	input[type="password"]:focus,
	select:focus {
		border-color: #667eea;
		outline: none;
	}
	.phone-group {
		display: inline-block;
	}
	.btn {
		padding: 10px 20px;
		background-color: #667eea;
		color: white;
		border: none;
		border-radius: 4px;
		cursor: pointer;
		font-size: 14px;
		margin-top: 10px;
	}
	.btn:hover {
		background-color: #764ba2;
	}
	.info-box {
		background-color: #fff3cd;
		border-left: 4px solid #ffc107;
		padding: 15px;
		margin-top: 20px;
		border-radius: 4px;
		font-size: 0.9em;
	}
</style>
<script type="text/javascript">
	function checkMember() {
		// ✅ 개선된 정규식 패턴
		var regExpId = /^[a-zA-Z가-힣]/;  // 파이프 제거
		var regExpName = /^[가-힣]+$/;     // + 사용 (1개 이상)
		var regExpPasswd = /^[0-9]+$/;     // + 사용 (1개 이상)
		var regExpPhone = /^\d{3}-\d{3,4}-\d{4}$/;
		var regExpEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

		var form = document.Member;

		// 값 가져오기 (trim 추가)
		var id = form.id.value.trim();
		var name = form.name.value.trim();
		var passwd = form.passwd.value;
		var phone1 = form.phone1.value;
		var phone2 = form.phone2.value.trim();
		var phone3 = form.phone3.value.trim();
		var phone = phone1 + "-" + phone2 + "-" + phone3;
		var email = form.email.value.trim();

		// ========== 아이디 검증 ==========
		// ✅ 빈 값 검증 추가
		if(id === ""){
			alert("아이디를 입력해 주세요!");
			form.id.focus();
			return false;
		}
		if (!regExpId.test(id)) {
			alert("아이디는 문자로 시작해 주세요!");
			form.id.select();
			return false;  // ✅ return false 명시
		}
		
		// ========== 이름 검증 ==========
		// ✅ 빈 값 검증 추가
		if(name === ""){
			alert("이름을 입력해 주세요!");
			form.name.focus();
			return false;
		}
		if (!regExpName.test(name)) {
			alert("이름은 한글만으로 입력해 주세요!");
			form.name.select();  // ✅ select 추가
			return false;
		}
		
		// ========== 비밀번호 검증 ==========
		// ✅ 빈 값 검증 추가
		if(passwd === ""){
			alert("비밀번호를 입력해 주세요!");
			form.passwd.focus();
			return false;
		}
		// ✅ 길이 검증 추가
		if(passwd.length < 4){
			alert("비밀번호는 4자 이상 입력해 주세요!");
			form.passwd.select();
			return false;
		}
		if (!regExpPasswd.test(passwd)) {
			alert("비밀번호는 숫자만으로 입력해 주세요!");
			form.passwd.select();
			return false;
		}
		
		// ========== 전화번호 검증 ==========
		// ✅ 각 필드 빈 값 검증 추가
		if(phone1 === "" || phone2 === "" || phone3 === ""){
			alert("연락처를 모두 입력해 주세요!");
			if(phone1 === "") {
				form.phone1.focus();
			} else if(phone2 === "") {
				form.phone2.focus();
			} else {
				form.phone3.focus();
			}
			return false;
		}
		if (!regExpPhone.test(phone)) {
			alert("연락처 입력을 확인해 주세요!\n예: 010-1234-5678");
			form.phone2.focus();
			return false;
		}
		
		// ========== 이메일 검증 ==========
		// ✅ 빈 값 검증 추가
		if(email === ""){
			alert("이메일을 입력해 주세요!");
			form.email.focus();
			return false;
		}
		if (!regExpEmail.test(email)) {
			alert("이메일 입력을 확인해 주세요!\n예: example@email.com");
			form.email.select();
			return false;
		}
		
		// ✅ 모든 검증 통과 시 true 반환
		return true;
	}
</script>
</head>
<body>
	<div class="container">
		<h3>회원 가입</h3>
		
		<!-- ✅ onsubmit 사용으로 변경 -->
		<form action="validation05_process.jsp" name="Member" method="post" 
		      onsubmit="return checkMember()">
			
			<div class="form-group">
				<label>아이디:</label>
				<input type="text" name="id" placeholder="문자로 시작">
			</div>
			
			<div class="form-group">
				<label>비밀번호:</label>
				<input type="password" name="passwd" placeholder="숫자 4자 이상">
			</div>
			
			<div class="form-group">
				<label>이름:</label>
				<input type="text" name="name" placeholder="한글만 입력">
			</div>
			
			<div class="form-group">
				<label>연락처:</label>
				<div class="phone-group">
					<select name="phone1">
						<option value="010">010</option>
						<option value="011">011</option>
						<option value="016">016</option>
						<option value="017">017</option>
						<option value="019">019</option>
					</select> - 
					<input type="text" maxlength="4" size="4" name="phone2" placeholder="1234"> - 
					<input type="text" maxlength="4" size="4" name="phone3" placeholder="5678">
				</div>
			</div>
			
			<div class="form-group">
				<label>이메일:</label>
				<input type="text" name="email" placeholder="example@email.com">
			</div>
			
			<input type="submit" value="가입하기" class="btn">
		</form>
		
		<div class="info-box">
			<strong>개선 사항:</strong>
			<ul>
				<li>✅ onsubmit 사용 (Enter 키 지원)</li>
				<li>✅ 빈 값 검증 추가</li>
				<li>✅ 정규식 패턴 수정 (파이프 제거)</li>
				<li>✅ return false/true 명시</li>
				<li>✅ 모든 필드에 focus() 추가</li>
				<li>✅ 비밀번호 길이 검증 추가</li>
			</ul>
		</div>
	</div>
</body>
</html>
