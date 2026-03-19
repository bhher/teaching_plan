# previewImage 함수 오류 해결 가이드

## 🐛 오류 메시지

```
Uncaught TypeError: previewImage is not a function
    at HTMLInputElement.onchange (board_write.jsp:134:107)
```

---

## 🔍 문제 원인

### 변수 이름 충돌 (Variable Name Collision)

**문제 코드:**
```javascript
function previewImage(input) {
    var previewContainer = document.getElementById('previewContainer');
    var previewImage = document.getElementById('previewImage');  // ❌ 문제!
    // ↑ 함수 이름과 변수 이름이 같음
    
    reader.onload = function(e) {
        previewImage.src = e.target.result;  // 변수가 함수를 덮어씀
    };
}
```

**문제점:**
1. 함수 이름: `previewImage`
2. 변수 이름: `previewImage` (함수 내부)
3. 변수가 함수를 덮어써서 함수가 사라짐
4. `onchange="previewImage(this)"` 호출 시 함수를 찾을 수 없음

---

## ✅ 해결 방법

### 방법 1: 변수 이름 변경 (권장)

**수정 전:**
```javascript
function previewImage(input) {
    var previewImage = document.getElementById('previewImage');  // ❌
    previewImage.src = e.target.result;
}
```

**수정 후:**
```javascript
function previewImage(input) {
    var previewImg = document.getElementById('previewImage');  // ✅
    previewImg.src = e.target.result;
}
```

### 방법 2: 즉시 실행 함수 사용

```javascript
(function() {
    function previewImage(input) {
        var previewContainer = document.getElementById('previewContainer');
        var previewImg = document.getElementById('previewImage');
        
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            
            reader.onload = function(e) {
                previewImg.src = e.target.result;
                previewContainer.style.display = 'block';
            };
            
            reader.readAsDataURL(input.files[0]);
        } else {
            previewContainer.style.display = 'none';
        }
    }
    
    // 전역 스코프에 함수 등록
    window.previewImage = previewImage;
})();
```

---

## 🔧 수정된 완전한 코드

### HTML 부분

```html
<div class="form-group">
    <label>이미지파일</label>
    <input type="file" name="image" id="image" accept="image/*" onchange="previewImage(this)">
    <div class="file-info">지원 형식: JPG, PNG, GIF, BMP, WEBP (최대 10MB)</div>
    <div class="preview-container" id="previewContainer">
        <img id="previewImage" class="preview-image" alt="미리보기">
    </div>
</div>
```

### JavaScript 부분 (수정됨)

```javascript
<script>
    function previewImage(input) {
        var previewContainer = document.getElementById('previewContainer');
        var previewImg = document.getElementById('previewImage');  // ✅ 변수명 변경
        
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            
            reader.onload = function(e) {
                previewImg.src = e.target.result;  // ✅ 변수명 변경
                previewContainer.style.display = 'block';
            };
            
            reader.readAsDataURL(input.files[0]);
        } else {
            previewContainer.style.display = 'none';
        }
    }
    
    function validateForm() {
        var title = document.getElementById('title').value.trim();
        var writer = document.getElementById('writer').value.trim();
        var password = document.getElementById('password').value.trim();
        var image = document.getElementById('image').files[0];
        
        if (!title) {
            alert('제목을 입력하세요.');
            return false;
        }
        
        if (!writer) {
            alert('작성자를 입력하세요.');
            return false;
        }
        
        if (!password) {
            alert('비밀번호를 입력하세요.');
            return false;
        }
        
        // 파일 크기 확인 (10MB)
        if (image && image.size > 10 * 1024 * 1024) {
            alert('파일 크기는 10MB를 초과할 수 없습니다.');
            return false;
        }
        
        return true;
    }
</script>
```

---

## 🎯 변수 이름 충돌 설명

### JavaScript 스코프 규칙

```javascript
function previewImage(input) {
    // 함수 스코프 시작
    
    var previewImage = document.getElementById('previewImage');
    // ↑ 이 변수가 함수 이름과 같아서 함수를 덮어씀
    
    // 이 시점부터 previewImage는 변수가 되고 함수는 사라짐
    previewImage.src = ...;  // 변수 사용
}

// 함수 외부에서 호출 시
previewImage(this);  // ❌ 함수가 없어서 오류 발생
```

### 해결: 변수 이름 변경

```javascript
function previewImage(input) {
    // 함수 이름: previewImage (유지)
    
    var previewImg = document.getElementById('previewImage');
    // ↑ 변수 이름: previewImg (다름)
    
    previewImg.src = ...;  // 변수 사용
}

// 함수 외부에서 호출 시
previewImage(this);  // ✅ 함수가 정상 작동
```

---

## 🐛 다른 가능한 원인

### 1. 스크립트 태그 위치 문제

**문제:**
```html
<!-- 스크립트가 body 밖에 있거나 잘못된 위치 -->
<input onchange="previewImage(this)">
<!-- ... -->
<script>
    function previewImage(input) { ... }
</script>
```

**해결:**
- 스크립트를 `</body>` 전에 배치
- 또는 DOMContentLoaded 이벤트 사용

### 2. 스크립트 로드 순서 문제

**문제:**
- 스크립트가 로드되기 전에 함수 호출

**해결:**
```javascript
// DOM 로드 완료 후 함수 정의
document.addEventListener('DOMContentLoaded', function() {
    window.previewImage = function(input) {
        // 함수 내용
    };
});
```

### 3. 스크립트 오류로 인한 중단

**확인 방법:**
- 브라우저 콘솔(F12)에서 다른 오류 확인
- 스크립트가 중간에 중단되었는지 확인

---

## 🧪 테스트 방법

### 1. 함수 존재 확인

**브라우저 콘솔에서:**
```javascript
// 함수가 정의되어 있는지 확인
console.log(typeof previewImage);  // "function" 출력되어야 함

// 함수 호출 테스트
var testInput = document.getElementById('image');
previewImage(testInput);
```

### 2. 변수 확인

```javascript
// 함수 내부에서 변수 확인
function previewImage(input) {
    console.log('함수 실행됨');
    var previewImg = document.getElementById('previewImage');
    console.log('변수:', previewImg);  // img 요소 출력되어야 함
}
```

### 3. 파일 선택 테스트

1. 페이지 로드
2. 파일 입력 필드 클릭
3. 이미지 파일 선택
4. 콘솔에서 오류 확인
5. 미리보기 표시 확인

---

## ✅ 수정 체크리스트

- [ ] 변수 이름 변경 (`previewImage` → `previewImg`)
- [ ] 모든 변수 사용 부분 수정
- [ ] 스크립트 태그 위치 확인 (`</body>` 전)
- [ ] 브라우저 콘솔에서 오류 확인
- [ ] 파일 선택 테스트

---

## 📚 요약

### 오류 원인

**변수 이름 충돌:**
- 함수 이름: `previewImage`
- 변수 이름: `previewImage` (함수 내부)
- 변수가 함수를 덮어써서 함수가 사라짐

### 해결 방법

**변수 이름 변경:**
```javascript
// ❌ 잘못된 코드
var previewImage = document.getElementById('previewImage');

// ✅ 올바른 코드
var previewImg = document.getElementById('previewImage');
```

### 수정 사항

1. ✅ 변수 이름 변경: `previewImage` → `previewImg`
2. ✅ 모든 변수 사용 부분 수정
3. ✅ 함수 이름은 그대로 유지

**변수 이름을 변경하면 오류가 해결됩니다!** ✅
