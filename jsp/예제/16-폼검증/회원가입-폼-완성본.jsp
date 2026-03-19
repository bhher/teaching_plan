<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
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
	.form-group {
		margin-bottom: 20px;
	}
	label {
		display: block;
		margin-bottom: 8px;
		font-weight: bold;
		color: #555;
		font-size: 14px;
	}
	.required {
		color: red;
		margin-left: 3px;
	}
	input[type="text"],
	input[type="password"],
	input[type="email"],
	input[type="tel"],
	input[type="date"],
	select {
		width: 100%;
		padding: 12px;
		border: 2px solid #ddd;
		border-radius: 8px;
		font-size: 14px;
		transition: border-color 0.3s;
	}
	input:focus,
	select:focus {
		border-color: #667eea;
		outline: none;
	}
	.phone-group {
		display: flex;
		gap: 10px;
		align-items: center;
	}
	.phone-group select,
	.phone-group input {
		flex: 1;
	}
	.phone-group span {
		color: #666;
		font-weight: bold;
	}
	.error-msg {
		color: red;
		font-size: 0.85em;
		margin-top: 5px;
		display: none;
	}
	.success-msg {
		color: green;
		font-size: 0.85em;
		margin-top: 5px;
		display: none;
	}
	.btn-group {
		display: flex;
		gap: 10px;
		margin-top: 30px;
	}
	.btn {
		flex: 1;
		padding: 14px;
		border: none;
		border-radius: 8px;
		font-size: 16px;
		font-weight: bold;
		cursor: pointer;
		transition: all 0.3s;
	}
	.btn-submit {
		background-color: #667eea;
		color: white;
	}
	.btn-submit:hover {
		background-color: #764ba2;
		transform: translateY(-2px);
		box-shadow: 0 5px 15px rgba(102, 126, 234, 0.4);
	}
	.btn-reset {
		background-color: #6c757d;
		color: white;
	}
	.btn-reset:hover {
		background-color: #5a6268;
	}
	.info-text {
		font-size: 0.85em;
		color: #666;
		margin-top: 5px;
	}
	.checkbox-group {
		display: flex;
		align-items: flex-start;
		gap: 10px;
		margin-top: 10px;
	}
	.checkbox-group input[type="checkbox"] {
		width: auto;
		margin-top: 3px;
	}
	.checkbox-group label {
		margin: 0;
		font-weight: normal;
	}
</style>
<script type="text/javascript">
	// ========== 정규식 패턴 정의 ==========
	var patterns = {
		// 아이디: 영문/숫자 조합, 4-12자, 첫 글자는 영문
		id: /^[a-zA-Z][a-zA-Z0-9]{3,11}$/,
		
		// 비밀번호: 영문/숫자/특수문자 조합, 8자 이상
		passwd: /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,}$/,
		
		// 이름: 한글만, 2-10자
		name: /^[가-힣]{2,10}$/,
		
		// 이메일: 표준 이메일 형식
		email: /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/,
		
		// 전화번호: 010-1234-5678 형식
		phone: /^010-\d{4}-\d{4}$/,
		
		// 생년월일: YYYY-MM-DD 형식
		birthdate: /^\d{4}-\d{2}-\d{2}$/
	};
	
	/**
	 * 회원가입 폼 검증 함수
	 * @returns {boolean} 검증 통과 시 true, 실패 시 false
	 */
	function validateForm() {
		var form = document.registerForm;
		var isValid = true;
		
		// ========== 1. 아이디 검증 ==========
		var id = form.id.value.trim();
		var idError = document.getElementById("idError");
		var idSuccess = document.getElementById("idSuccess");
		
		if(id === ""){
			showError(idError, "아이디를 입력해 주세요.");
			hideSuccess(idSuccess);
			form.id.focus();
			return false;
		}
		
		if(id.length < 4 || id.length > 12){
			showError(idError, "아이디는 4자 이상 12자 이하로 입력해 주세요.");
			hideSuccess(idSuccess);
			form.id.focus();
			return false;
		}
		
		if(!patterns.id.test(id)){
			showError(idError, "아이디는 영문으로 시작하고, 영문과 숫자만 사용할 수 있습니다.");
			hideSuccess(idSuccess);
			form.id.select();
			return false;
		}
		
		hideError(idError);
		showSuccess(idSuccess, "사용 가능한 아이디입니다.");
		
		// ========== 2. 비밀번호 검증 ==========
		var passwd = form.passwd.value;
		var passwdError = document.getElementById("passwdError");
		
		if(passwd === ""){
			showError(passwdError, "비밀번호를 입력해 주세요.");
			form.passwd.focus();
			return false;
		}
		
		if(passwd.length < 8){
			showError(passwdError, "비밀번호는 8자 이상 입력해 주세요.");
			form.passwd.focus();
			return false;
		}
		
		if(!patterns.passwd.test(passwd)){
			showError(passwdError, "비밀번호는 영문, 숫자, 특수문자를 각각 1개 이상 포함해야 합니다.");
			form.passwd.select();
			return false;
		}
		
		hideError(passwdError);
		
		// ========== 3. 비밀번호 확인 검증 ==========
		var passwd2 = form.passwd2.value;
		var passwd2Error = document.getElementById("passwd2Error");
		var passwd2Success = document.getElementById("passwd2Success");
		
		if(passwd2 === ""){
			showError(passwd2Error, "비밀번호 확인을 입력해 주세요.");
			hideSuccess(passwd2Success);
			form.passwd2.focus();
			return false;
		}
		
		if(passwd !== passwd2){
			showError(passwd2Error, "비밀번호가 일치하지 않습니다.");
			hideSuccess(passwd2Success);
			form.passwd2.select();
			return false;
		}
		
		hideError(passwd2Error);
		showSuccess(passwd2Success, "비밀번호가 일치합니다.");
		
		// ========== 4. 이름 검증 ==========
		var name = form.name.value.trim();
		var nameError = document.getElementById("nameError");
		
		if(name === ""){
			showError(nameError, "이름을 입력해 주세요.");
			form.name.focus();
			return false;
		}
		
		if(!patterns.name.test(name)){
			showError(nameError, "이름은 한글만 입력할 수 있으며, 2자 이상 10자 이하입니다.");
			form.name.select();
			return false;
		}
		
		hideError(nameError);
		
		// ========== 5. 이메일 검증 ==========
		var email = form.email.value.trim();
		var emailError = document.getElementById("emailError");
		
		if(email === ""){
			showError(emailError, "이메일을 입력해 주세요.");
			form.email.focus();
			return false;
		}
		
		if(!patterns.email.test(email)){
			showError(emailError, "올바른 이메일 형식을 입력해 주세요. 예: example@email.com");
			form.email.select();
			return false;
		}
		
		hideError(emailError);
		
		// ========== 6. 전화번호 검증 ==========
		var phone1 = form.phone1.value;
		var phone2 = form.phone2.value.trim();
		var phone3 = form.phone3.value.trim();
		var phone = phone1 + "-" + phone2 + "-" + phone3;
		var phoneError = document.getElementById("phoneError");
		
		if(phone1 === "" || phone2 === "" || phone3 === ""){
			showError(phoneError, "전화번호를 모두 입력해 주세요.");
			if(phone1 === "") form.phone1.focus();
			else if(phone2 === "") form.phone2.focus();
			else form.phone3.focus();
			return false;
		}
		
		if(!patterns.phone.test(phone)){
			showError(phoneError, "올바른 전화번호 형식을 입력해 주세요. 예: 010-1234-5678");
			form.phone2.focus();
			return false;
		}
		
		hideError(phoneError);
		
		// ========== 7. 생년월일 검증 (선택사항) ==========
		var birthdate = form.birthdate.value;
		var birthdateError = document.getElementById("birthdateError");
		
		if(birthdate !== ""){
			if(!patterns.birthdate.test(birthdate)){
				showError(birthdateError, "올바른 날짜 형식을 입력해 주세요.");
				form.birthdate.focus();
				return false;
			}
			
			// 날짜 유효성 검증
			var date = new Date(birthdate);
			var today = new Date();
			if(date > today){
				showError(birthdateError, "미래 날짜는 입력할 수 없습니다.");
				form.birthdate.focus();
				return false;
			}
		}
		
		hideError(birthdateError);
		
		// ========== 8. 약관 동의 검증 ==========
		if(!form.agree.checked){
			alert("회원가입 약관에 동의해 주세요.");
			form.agree.focus();
			return false;
		}
		
		// ========== 모든 검증 통과 ==========
		return true;
	}
	
	// ========== 유틸리티 함수 ==========
	function showError(errorElement, message){
		if(errorElement){
			errorElement.textContent = message;
			errorElement.style.display = "block";
		}
	}
	
	function hideError(errorElement){
		if(errorElement){
			errorElement.style.display = "none";
		}
	}
	
	function showSuccess(successElement, message){
		if(successElement){
			successElement.textContent = message;
			successElement.style.display = "block";
		}
	}
	
	function hideSuccess(successElement){
		if(successElement){
			successElement.style.display = "none";
		}
	}
	
	// ========== 실시간 검증 (선택사항) ==========
	function checkIdRealTime(){
		var id = document.registerForm.id.value.trim();
		var idError = document.getElementById("idError");
		var idSuccess = document.getElementById("idSuccess");
		
		if(id === ""){
			hideError(idError);
			hideSuccess(idSuccess);
			return;
		}
		
		if(id.length < 4 || id.length > 12){
			showError(idError, "아이디는 4자 이상 12자 이하로 입력해 주세요.");
			hideSuccess(idSuccess);
			return;
		}
		
		if(!patterns.id.test(id)){
			showError(idError, "영문으로 시작하고, 영문과 숫자만 사용할 수 있습니다.");
			hideSuccess(idSuccess);
		} else {
			hideError(idError);
			showSuccess(idSuccess, "사용 가능한 아이디입니다.");
		}
	}
	
	function checkPasswd2RealTime(){
		var passwd = document.registerForm.passwd.value;
		var passwd2 = document.registerForm.passwd2.value;
		var passwd2Error = document.getElementById("passwd2Error");
		var passwd2Success = document.getElementById("passwd2Success");
		
		if(passwd2 === ""){
			hideError(passwd2Error);
			hideSuccess(passwd2Success);
			return;
		}
		
		if(passwd !== passwd2){
			showError(passwd2Error, "비밀번호가 일치하지 않습니다.");
			hideSuccess(passwd2Success);
		} else {
			hideError(passwd2Error);
			showSuccess(passwd2Success, "비밀번호가 일치합니다.");
		}
	}
</script>
</head>
<body>
	<div class="container">
		<h2>📝 회원가입</h2>
		
		<form name="registerForm" action="회원가입-처리.jsp" method="post" 
		      onsubmit="return validateForm()">
			
			<!-- 아이디 -->
			<div class="form-group">
				<label for="id">아이디 <span class="required">*</span></label>
				<input type="text" id="id" name="id" 
				       placeholder="영문으로 시작, 4-12자 (영문/숫자)"
				       onkeyup="checkIdRealTime()" onblur="checkIdRealTime()">
				<div class="error-msg" id="idError"></div>
				<div class="success-msg" id="idSuccess"></div>
				<div class="info-text">영문으로 시작하며, 영문과 숫자만 사용 가능합니다.</div>
			</div>
			
			<!-- 비밀번호 -->
			<div class="form-group">
				<label for="passwd">비밀번호 <span class="required">*</span></label>
				<input type="password" id="passwd" name="passwd" 
				       placeholder="8자 이상 (영문/숫자/특수문자 조합)">
				<div class="error-msg" id="passwdError"></div>
				<div class="info-text">영문, 숫자, 특수문자를 각각 1개 이상 포함해야 합니다.</div>
			</div>
			
			<!-- 비밀번호 확인 -->
			<div class="form-group">
				<label for="passwd2">비밀번호 확인 <span class="required">*</span></label>
				<input type="password" id="passwd2" name="passwd2" 
				       placeholder="비밀번호를 다시 입력하세요"
				       onkeyup="checkPasswd2RealTime()" onblur="checkPasswd2RealTime()">
				<div class="error-msg" id="passwd2Error"></div>
				<div class="success-msg" id="passwd2Success"></div>
			</div>
			
			<!-- 이름 -->
			<div class="form-group">
				<label for="name">이름 <span class="required">*</span></label>
				<input type="text" id="name" name="name" 
				       placeholder="한글 2-10자">
				<div class="error-msg" id="nameError"></div>
			</div>
			
			<!-- 이메일 -->
			<div class="form-group">
				<label for="email">이메일 <span class="required">*</span></label>
				<input type="email" id="email" name="email" 
				       placeholder="example@email.com">
				<div class="error-msg" id="emailError"></div>
			</div>
			
			<!-- 전화번호 -->
			<div class="form-group">
				<label>전화번호 <span class="required">*</span></label>
				<div class="phone-group">
					<select name="phone1" id="phone1">
						<option value="">선택</option>
						<option value="010">010</option>
						<option value="011">011</option>
						<option value="016">016</option>
						<option value="017">017</option>
						<option value="019">019</option>
					</select>
					<span>-</span>
					<input type="text" name="phone2" id="phone2" maxlength="4" 
					       placeholder="1234" pattern="[0-9]{3,4}">
					<span>-</span>
					<input type="text" name="phone3" id="phone3" maxlength="4" 
					       placeholder="5678" pattern="[0-9]{4}">
				</div>
				<div class="error-msg" id="phoneError"></div>
			</div>
			
			<!-- 생년월일 (선택) -->
			<div class="form-group">
				<label for="birthdate">생년월일</label>
				<input type="date" id="birthdate" name="birthdate" 
				       max="<%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()) %>">
				<div class="error-msg" id="birthdateError"></div>
			</div>
			
			<!-- 약관 동의 -->
			<div class="form-group">
				<div class="checkbox-group">
					<input type="checkbox" id="agree" name="agree">
					<label for="agree">회원가입 약관에 동의합니다 <span class="required">*</span></label>
				</div>
			</div>
			
			<!-- 버튼 -->
			<div class="btn-group">
				<button type="submit" class="btn btn-submit">가입하기</button>
				<button type="reset" class="btn btn-reset">초기화</button>
			</div>
		</form>
	</div>
</body>
</html>
