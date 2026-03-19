<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>이미지 게시글 작성</title>
    
    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap Icons -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
    
    <style>
        body {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            padding: 20px 0;
        }
        .container {
            max-width: 900px;
        }
        .card {
            border: none;
            box-shadow: 0 10px 40px rgba(0,0,0,0.2);
        }
        .card-header {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            border: none;
            padding: 20px;
        }
        .card-header h2 {
            margin: 0;
            font-weight: bold;
        }
        .preview-container {
            margin-top: 15px;
            display: none;
        }
        .preview-image {
            max-width: 100%;
            max-height: 400px;
            border-radius: 8px;
            border: 2px solid #dee2e6;
        }
        .file-info {
            font-size: 0.875rem;
            color: #6c757d;
            margin-top: 5px;
        }
        .required {
            color: #dc3545;
        }
        .form-label {
            font-weight: 600;
            color: #495057;
            margin-bottom: 8px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="card">
            <div class="card-header">
                <h2><i class="bi bi-camera"></i> 이미지 게시글 작성</h2>
            </div>
            <div class="card-body">
                <form action="board_write_process.jsp" method="post" enctype="multipart/form-data" onsubmit="return validateForm()">
                    
                    <!-- 제목 -->
                    <div class="mb-3">
                        <label for="title" class="form-label">
                            제목 <span class="required">*</span>
                        </label>
                        <input type="text" class="form-control" id="title" name="title" required 
                               placeholder="게시글 제목을 입력하세요">
                    </div>
                    
                    <!-- 작성자 -->
                    <div class="mb-3">
                        <label for="writer" class="form-label">
                            작성자 <span class="required">*</span>
                        </label>
                        <input type="text" class="form-control" id="writer" name="writer" required 
                               placeholder="작성자 이름을 입력하세요">
                    </div>
                    
                    <!-- 비밀번호 -->
                    <div class="mb-3">
                        <label for="password" class="form-label">
                            비밀번호 <span class="required">*</span>
                        </label>
                        <input type="password" class="form-control" id="password" name="password" required 
                               placeholder="수정/삭제 시 사용할 비밀번호">
                        <div class="file-info">
                            <i class="bi bi-info-circle"></i> 수정/삭제 시 필요합니다.
                        </div>
                    </div>
                    
                    <!-- 이미지 파일 -->
                    <div class="mb-3">
                        <label for="image" class="form-label">
                            이미지 파일
                        </label>
                        <input type="file" class="form-control" id="image" name="image" 
                               accept="image/*" onchange="previewImage(this)">
                        <div class="file-info">
                            <i class="bi bi-file-image"></i> 지원 형식: JPG, PNG, GIF, BMP, WEBP (최대 10MB)
                        </div>
                        <div class="preview-container" id="previewContainer">
                            <img id="previewImage" class="preview-image mt-3" alt="미리보기">
                        </div>
                    </div>
                    
                    <!-- 내용 -->
                    <div class="mb-4">
                        <label for="content" class="form-label">내용</label>
                        <textarea class="form-control" id="content" name="content" rows="8" 
                                  placeholder="게시글 내용을 입력하세요"></textarea>
                    </div>
                    
                    <!-- 버튼 -->
                    <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                        <a href="board_list.jsp" class="btn btn-secondary me-md-2">
                            <i class="bi bi-list"></i> 목록
                        </a>
                        <button type="submit" class="btn btn-primary">
                            <i class="bi bi-check-circle"></i> 등록
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    
    <!-- Bootstrap 5 JS Bundle (Popper 포함) -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    
    <script>
        function previewImage(input) {
            var previewContainer = document.getElementById('previewContainer');
            var previewImage = document.getElementById('previewImage');
            
            if (input.files && input.files[0]) {
                var reader = new FileReader();
                
                reader.onload = function(e) {
                    previewImage.src = e.target.result;
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
                document.getElementById('title').focus();
                return false;
            }
            
            if (!writer) {
                alert('작성자를 입력하세요.');
                document.getElementById('writer').focus();
                return false;
            }
            
            if (!password) {
                alert('비밀번호를 입력하세요.');
                document.getElementById('password').focus();
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
