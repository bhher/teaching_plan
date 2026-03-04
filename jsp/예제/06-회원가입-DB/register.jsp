<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
            background-color: #f5f5f5;
            padding: 20px;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
            background-color: white;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        h2 {
            text-align: center;
            color: #333;
            margin-bottom: 30px;
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
        input[type="email"],
        select,
        textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 14px;
        }
        input[type="text"]:focus,
        input[type="password"]:focus,
        input[type="email"]:focus,
        select:focus,
        textarea:focus {
            outline: none;
            border-color: #4CAF50;
        }
        .radio-group,
        .checkbox-group {
            margin-top: 5px;
        }
        .radio-group label,
        .checkbox-group label {
            display: inline;
            font-weight: normal;
            margin-right: 20px;
            cursor: pointer;
        }
        input[type="radio"],
        input[type="checkbox"] {
            width: auto;
            margin-right: 5px;
            cursor: pointer;
        }
        textarea {
            resize: vertical;
            min-height: 100px;
        }
        .btn-group {
            margin-top: 30px;
            text-align: center;
        }
        button {
            padding: 12px 30px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
            margin: 0 10px;
        }
        button:hover {
            background-color: #45a049;
        }
        button[type="reset"] {
            background-color: #999;
        }
        button[type="reset"]:hover {
            background-color: #777;
        }
        .error {
            color: red;
            font-size: 12px;
            margin-top: 5px;
        }
        .success {
            color: green;
            font-size: 14px;
            margin-bottom: 20px;
            padding: 10px;
            background-color: #e8f5e9;
            border-radius: 4px;
        }
        .info {
            font-size: 12px;
            color: #999;
            margin-top: 5px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>회원가입</h2>
        
        <%
            String error = request.getParameter("error");
            String success = request.getParameter("success");
            
            if ("duplicate".equals(error)) {
        %>
            <div class="error">이미 사용 중인 아이디입니다.</div>
        <%
            } else if ("empty".equals(error)) {
        %>
            <div class="error">필수 항목을 모두 입력해주세요.</div>
        <%
            } else if ("db_error".equals(error)) {
        %>
            <div class="error">데이터베이스 오류가 발생했습니다.</div>
        <%
            }
            
            if ("1".equals(success)) {
        %>
            <div class="success">회원가입이 완료되었습니다! <a href="login.jsp">로그인하기</a></div>
        <%
            }
        %>
        
        <form action="register_process.jsp" method="post" id="registerForm">
            <div class="form-group">
                <label for="user_id">아이디 <span style="color: red;">*</span></label>
                <input type="text" id="user_id" name="user_id" required 
                       value="<%= request.getParameter("user_id") != null ? request.getParameter("user_id") : "" %>">
                <div class="info">4자 이상 20자 이하, 영문/숫자/언더스코어만 사용 가능</div>
            </div>
            
            <div class="form-group">
                <label for="password">비밀번호 <span style="color: red;">*</span></label>
                <input type="password" id="password" name="password" required>
                <div class="info">8자 이상, 영문/숫자 조합 권장</div>
            </div>
            
            <div class="form-group">
                <label for="password_confirm">비밀번호 확인 <span style="color: red;">*</span></label>
                <input type="password" id="password_confirm" name="password_confirm" required>
            </div>
            
            <div class="form-group">
                <label for="name">이름 <span style="color: red;">*</span></label>
                <input type="text" id="name" name="name" required
                       value="<%= request.getParameter("name") != null ? request.getParameter("name") : "" %>">
            </div>
            
            <div class="form-group">
                <label for="email">이메일</label>
                <input type="email" id="email" name="email"
                       value="<%= request.getParameter("email") != null ? request.getParameter("email") : "" %>">
            </div>
            
            <div class="form-group">
                <label>성별</label>
                <div class="radio-group">
                    <input type="radio" id="male" name="gender" value="남" 
                           <%= "남".equals(request.getParameter("gender")) ? "checked" : "" %>>
                    <label for="male">남</label>
                    <input type="radio" id="female" name="gender" value="여"
                           <%= "여".equals(request.getParameter("gender")) ? "checked" : "" %>>
                    <label for="female">여</label>
                </div>
            </div>
            
            <div class="form-group">
                <label>취미</label>
                <div class="checkbox-group">
                    <input type="checkbox" id="hobby1" name="hobby" value="독서"
                           <%= request.getParameterValues("hobby") != null && 
                               java.util.Arrays.asList(request.getParameterValues("hobby")).contains("독서") ? "checked" : "" %>>
                    <label for="hobby1">독서</label>
                    
                    <input type="checkbox" id="hobby2" name="hobby" value="운동"
                           <%= request.getParameterValues("hobby") != null && 
                               java.util.Arrays.asList(request.getParameterValues("hobby")).contains("운동") ? "checked" : "" %>>
                    <label for="hobby2">운동</label>
                    
                    <input type="checkbox" id="hobby3" name="hobby" value="영화"
                           <%= request.getParameterValues("hobby") != null && 
                               java.util.Arrays.asList(request.getParameterValues("hobby")).contains("영화") ? "checked" : "" %>>
                    <label for="hobby3">영화</label>
                    
                    <input type="checkbox" id="hobby4" name="hobby" value="음악"
                           <%= request.getParameterValues("hobby") != null && 
                               java.util.Arrays.asList(request.getParameterValues("hobby")).contains("음악") ? "checked" : "" %>>
                    <label for="hobby4">음악</label>
                    
                    <input type="checkbox" id="hobby5" name="hobby" value="여행"
                           <%= request.getParameterValues("hobby") != null && 
                               java.util.Arrays.asList(request.getParameterValues("hobby")).contains("여행") ? "checked" : "" %>>
                    <label for="hobby5">여행</label>
                </div>
            </div>
            
            <div class="form-group">
                <label for="city">거주지</label>
                <select id="city" name="city">
                    <option value="">선택하세요</option>
                    <option value="서울" <%= "서울".equals(request.getParameter("city")) ? "selected" : "" %>>서울</option>
                    <option value="부산" <%= "부산".equals(request.getParameter("city")) ? "selected" : "" %>>부산</option>
                    <option value="대구" <%= "대구".equals(request.getParameter("city")) ? "selected" : "" %>>대구</option>
                    <option value="인천" <%= "인천".equals(request.getParameter("city")) ? "selected" : "" %>>인천</option>
                    <option value="광주" <%= "광주".equals(request.getParameter("city")) ? "selected" : "" %>>광주</option>
                    <option value="대전" <%= "대전".equals(request.getParameter("city")) ? "selected" : "" %>>대전</option>
                    <option value="울산" <%= "울산".equals(request.getParameter("city")) ? "selected" : "" %>>울산</option>
                    <option value="기타" <%= "기타".equals(request.getParameter("city")) ? "selected" : "" %>>기타</option>
                </select>
            </div>
            
            <div class="form-group">
                <label for="bio">자기소개</label>
                <textarea id="bio" name="bio" placeholder="간단한 자기소개를 입력하세요"><%= request.getParameter("bio") != null ? request.getParameter("bio") : "" %></textarea>
            </div>
            
            <div class="btn-group">
                <button type="submit">가입하기</button>
                <button type="reset">다시 입력</button>
            </div>
        </form>
    </div>
    
    <script>
        // 비밀번호 확인 검증
        document.getElementById('registerForm').addEventListener('submit', function(e) {
            var password = document.getElementById('password').value;
            var passwordConfirm = document.getElementById('password_confirm').value;
            
            if (password !== passwordConfirm) {
                e.preventDefault();
                alert('비밀번호가 일치하지 않습니다.');
                document.getElementById('password_confirm').focus();
                return false;
            }
            
            // 아이디 형식 검증
            var userId = document.getElementById('user_id').value;
            if (!/^[a-zA-Z0-9_]{4,20}$/.test(userId)) {
                e.preventDefault();
                alert('아이디는 4-20자의 영문/숫자/언더스코어만 사용 가능합니다.');
                document.getElementById('user_id').focus();
                return false;
            }
        });
    </script>
</body>
</html>
