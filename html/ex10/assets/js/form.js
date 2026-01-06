// 폼 유효성 검사
document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('contactForm');
    if (!form) return;
    
    const nameInput = document.getElementById('name');
    const emailInput = document.getElementById('email');
    const subjectInput = document.getElementById('subject');
    const messageInput = document.getElementById('message');
    
    // 실시간 유효성 검사
    if (nameInput) {
        nameInput.addEventListener('blur', validateName);
        nameInput.addEventListener('input', clearErrorOnInput);
    }
    
    if (emailInput) {
        emailInput.addEventListener('blur', validateEmail);
        emailInput.addEventListener('input', clearErrorOnInput);
    }
    
    if (messageInput) {
        messageInput.addEventListener('blur', validateMessage);
        messageInput.addEventListener('input', clearErrorOnInput);
    }
    
    // 폼 제출
    form.addEventListener('submit', function(e) {
        e.preventDefault();
        
        if (validateForm()) {
            submitForm();
        }
    });
    
    // 이름 검사
    function validateName() {
        const name = nameInput.value.trim();
        const errorElement = document.getElementById('nameError');
        
        if (name === '') {
            showError('nameError', '이름을 입력해주세요.');
            return false;
        } else if (name.length < 2) {
            showError('nameError', '이름은 2자 이상 입력해주세요.');
            return false;
        } else {
            hideError('nameError');
            nameInput.classList.add('valid');
            nameInput.classList.remove('error');
            return true;
        }
    }
    
    // 이메일 검사
    function validateEmail() {
        const email = emailInput.value.trim();
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        
        if (email === '') {
            showError('emailError', '이메일을 입력해주세요.');
            return false;
        } else if (!emailRegex.test(email)) {
            showError('emailError', '올바른 이메일 형식이 아닙니다.');
            return false;
        } else {
            hideError('emailError');
            emailInput.classList.add('valid');
            emailInput.classList.remove('error');
            return true;
        }
    }
    
    // 메시지 검사
    function validateMessage() {
        const message = messageInput.value.trim();
        
        if (message === '') {
            showError('messageError', '메시지를 입력해주세요.');
            return false;
        } else if (message.length < 10) {
            showError('messageError', '메시지는 10자 이상 입력해주세요.');
            return false;
        } else {
            hideError('messageError');
            messageInput.classList.add('valid');
            messageInput.classList.remove('error');
            return true;
        }
    }
    
    // 전체 폼 검사
    function validateForm() {
        const isNameValid = validateName();
        const isEmailValid = validateEmail();
        const isMessageValid = validateMessage();
        
        return isNameValid && isEmailValid && isMessageValid;
    }
    
    // 에러 표시
    function showError(errorId, message) {
        const errorElement = document.getElementById(errorId);
        if (errorElement) {
            errorElement.textContent = message;
            errorElement.classList.add('show');
            errorElement.setAttribute('role', 'alert');
            
            const input = errorElement.previousElementSibling;
            if (input) {
                input.classList.add('error');
                input.classList.remove('valid');
                input.setAttribute('aria-invalid', 'true');
            }
        }
    }
    
    // 에러 숨김
    function hideError(errorId) {
        const errorElement = document.getElementById(errorId);
        if (errorElement) {
            errorElement.classList.remove('show');
            errorElement.textContent = '';
            errorElement.removeAttribute('role');
            
            const input = errorElement.previousElementSibling;
            if (input) {
                input.classList.remove('error');
                input.setAttribute('aria-invalid', 'false');
            }
        }
    }
    
    // 입력 시 에러 제거
    function clearErrorOnInput() {
        const input = this;
        input.classList.remove('error', 'valid');
        input.setAttribute('aria-invalid', 'false');
        
        // 해당하는 에러 메시지 찾기
        const errorId = input.id + 'Error';
        const errorElement = document.getElementById(errorId);
        if (errorElement) {
            errorElement.classList.remove('show');
        }
    }
    
    // 폼 제출
    function submitForm() {
        // 폼 데이터 수집
        const formData = {
            name: nameInput.value.trim(),
            email: emailInput.value.trim(),
            subject: subjectInput ? subjectInput.value.trim() : '',
            message: messageInput.value.trim()
        };
        
        // 성공 메시지 표시
        showSuccessMessage('메시지가 성공적으로 전송되었습니다!');
        
        // 폼 리셋
        form.reset();
        
        // 모든 valid 클래스 제거
        [nameInput, emailInput, messageInput].forEach(input => {
            if (input) {
                input.classList.remove('valid', 'error');
            }
        });
        
        // 실제로는 서버로 전송
        // fetch('/api/contact', {
        //     method: 'POST',
        //     headers: {
        //         'Content-Type': 'application/json'
        //     },
        //     body: JSON.stringify(formData)
        // })
        // .then(response => response.json())
        // .then(data => {
        //     showSuccessMessage('메시지가 성공적으로 전송되었습니다!');
        //     form.reset();
        // })
        // .catch(error => {
        //     showErrorMessage('전송 중 오류가 발생했습니다. 다시 시도해주세요.');
        // });
    }
    
    // 성공 메시지 표시
    function showSuccessMessage(message) {
        // 기존 메시지 제거
        const existingMessage = form.querySelector('.success-message');
        if (existingMessage) {
            existingMessage.remove();
        }
        
        const successDiv = document.createElement('div');
        successDiv.className = 'success-message';
        successDiv.textContent = message;
        successDiv.setAttribute('role', 'alert');
        form.insertBefore(successDiv, form.firstChild);
        
        // 스크롤 to 메시지
        successDiv.scrollIntoView({ behavior: 'smooth', block: 'nearest' });
        
        // 5초 후 제거
        setTimeout(() => {
            successDiv.remove();
        }, 5000);
    }
    
    // 에러 메시지 표시 (서버 오류 등)
    function showErrorMessage(message) {
        const errorDiv = document.createElement('div');
        errorDiv.className = 'error-message show';
        errorDiv.style.display = 'block';
        errorDiv.textContent = message;
        errorDiv.setAttribute('role', 'alert');
        form.insertBefore(errorDiv, form.firstChild);
        
        setTimeout(() => {
            errorDiv.remove();
        }, 5000);
    }
});


