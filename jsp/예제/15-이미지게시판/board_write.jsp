<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>이미지 게시글 작성</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        body {
            font-family: 'Malgun Gothic', sans-serif;
            background-color: #f5f5f5;
            padding: 20px;
        }
        .container {
            max-width: 900px;
            margin: 0 auto;
            background-color: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        h2 {
            margin-bottom: 30px;
            color: #333;
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
        textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 14px;
            font-family: inherit;
        }
        input[type="file"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 14px;
        }
        textarea {
            min-height: 200px;
            resize: vertical;
        }
        .preview-container {
            margin-top: 10px;
            display: none;
        }
        .preview-image {
            max-width: 100%;
            max-height: 400px;
            border-radius: 4px;
            margin-top: 10px;
        }
        .btn-group {
            display: flex;
            gap: 10px;
            margin-top: 30px;
        }
        .btn {
            padding: 12px 30px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 14px;
            text-decoration: none;
            display: inline-block;
            text-align: center;
        }
        .btn-primary {
            background: #667eea;
            color: white;
        }
        .btn-primary:hover {
            background: #764ba2;
        }
        .btn-secondary {
            background: #6c757d;
            color: white;
        }
        .btn-secondary:hover {
            background: #5a6268;
        }
        .file-info {
            margin-top: 5px;
            font-size: 0.9em;
            color: #666;
        }
        .required {
            color: red;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>📷 이미지 게시글 작성</h2>
        
        <form action="board_write_process.jsp" method="post" enctype="multipart/form-data" onsubmit="return validateForm()">
            <div class="form-group">
                <label>제목 <span class="required">*</span></label>
                <input type="text" name="title" id="title" required>
            </div>
            
            <div class="form-group">
                <label>작성자 <span class="required">*</span></label>
                <input type="text" name="writer" id="writer" required>
            </div>
            
            <div class="form-group">
                <label>비밀번호 <span class="required">*</span></label>
                <input type="password" name="password" id="password" required>
                <div class="file-info">수정/삭제 시 필요합니다.</div>
            </div>
            
            <div class="form-group">
                <label>이미지 파일</label>
                <input type="file" name="image" id="image" accept="image/*" onchange="previewImage(this)">
                <div class="file-info">지원 형식: JPG, PNG, GIF, BMP, WEBP (최대 10MB)</div>
                <div class="preview-container" id="previewContainer">
                    <img id="previewImage" class="preview-image" alt="미리보기">
                </div>
            </div>
            
            <div class="form-group">
                <label>내용</label>
                <textarea name="content" id="content"></textarea>
            </div>
            
            <div class="btn-group">
                <button type="submit" class="btn btn-primary">등록</button>
                <a href="board_list.jsp" class="btn btn-secondary">목록</a>
            </div>
        </form>
    </div>
    
    <script>
        function previewImage(input) {
            var previewContainer = document.getElementById('previewContainer');
            var previewImg = document.getElementById('previewImage');  // 변수명 변경 (previewImage → previewImg)
            
            if (input.files && input.files[0]) {
                var reader = new FileReader();
                
                reader.onload = function(e) {
                    previewImg.src = e.target.result;  // 변수명 변경
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
</body>
</html>
