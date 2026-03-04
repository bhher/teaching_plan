<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원가입 폼</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            max-width: 600px;
            margin: 50px auto;
            padding: 20px;
        }
        form {
            background-color: #f5f5f5;
            padding: 30px;
            border-radius: 10px;
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        input[type="text"],
        input[type="password"],
        input[type="email"],
        select {
            width: 100%;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }
        input[type="radio"],
        input[type="checkbox"] {
            margin-right: 5px;
        }
        .radio-group,
        .checkbox-group {
            margin-top: 5px;
        }
        .radio-group label,
        .checkbox-group label {
            display: inline;
            font-weight: normal;
            margin-right: 15px;
        }
        button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }
        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <h2>회원가입</h2>
    <form action="form_process.jsp" method="post">
        <div class="form-group">
            <label for="id">아이디:</label>
            <input type="text" id="id" name="id" required>
        </div>
        
        <div class="form-group">
            <label for="pw">비밀번호:</label>
            <input type="password" id="pw" name="pw" required>
        </div>
        
        <div class="form-group">
            <label for="name">이름:</label>
            <input type="text" id="name" name="name" required>
        </div>
        
        <div class="form-group">
            <label for="email">이메일:</label>
            <input type="email" id="email" name="email">
        </div>
        
        <div class="form-group">
            <label>성별:</label>
            <div class="radio-group">
                <input type="radio" id="male" name="gender" value="남" checked>
                <label for="male">남</label>
                <input type="radio" id="female" name="gender" value="여">
                <label for="female">여</label>
            </div>
        </div>
        
        <div class="form-group">
            <label>취미:</label>
            <div class="checkbox-group">
                <input type="checkbox" id="hobby1" name="hobby" value="독서">
                <label for="hobby1">독서</label>
                <input type="checkbox" id="hobby2" name="hobby" value="운동">
                <label for="hobby2">운동</label>
                <input type="checkbox" id="hobby3" name="hobby" value="영화">
                <label for="hobby3">영화</label>
                <input type="checkbox" id="hobby4" name="hobby" value="음악">
                <label for="hobby4">음악</label>
            </div>
        </div>
        
        <div class="form-group">
            <label for="city">거주지:</label>
            <select id="city" name="city">
                <option value="">선택하세요</option>
                <option value="서울">서울</option>
                <option value="부산">부산</option>
                <option value="대구">대구</option>
                <option value="인천">인천</option>
                <option value="광주">광주</option>
            </select>
        </div>
        
        <div class="form-group">
            <label for="bio">자기소개:</label>
            <textarea id="bio" name="bio" rows="4" style="width: 100%; padding: 8px; border: 1px solid #ddd; border-radius: 4px; box-sizing: border-box;"></textarea>
        </div>
        
        <button type="submit">가입하기</button>
    </form>
</body>
</html>
