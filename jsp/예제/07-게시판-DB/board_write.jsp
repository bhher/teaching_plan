<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 작성</title>
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
        input[type="text"]:focus,
        input[type="password"]:focus,
        textarea:focus {
            outline: none;
            border-color: #4CAF50;
        }
        textarea {
            resize: vertical;
            min-height: 300px;
        }
        .btn-group {
            margin-top: 30px;
            text-align: center;
        }
        .btn {
            display: inline-block;
            padding: 12px 30px;
            margin: 0 10px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }
        .btn:hover {
            background-color: #45a049;
        }
        .btn-cancel {
            background-color: #999;
        }
        .btn-cancel:hover {
            background-color: #777;
        }
        .info {
            font-size: 12px;
            color: #999;
            margin-top: 5px;
        }
        .error {
            color: red;
            font-size: 14px;
            margin-bottom: 20px;
            padding: 10px;
            background-color: #ffebee;
            border-radius: 4px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>게시글 작성</h2>
        
        <%
            String error = request.getParameter("error");
            if ("empty".equals(error)) {
        %>
            <div class="error">제목과 내용을 모두 입력해주세요.</div>
        <%
            } else if ("db_error".equals(error)) {
        %>
            <div class="error">데이터베이스 오류가 발생했습니다.</div>
        <%
            }
        %>
        
        <form action="board_write_process.jsp" method="post" id="writeForm">
            <div class="form-group">
                <label for="title">제목 <span style="color: red;">*</span></label>
                <input type="text" id="title" name="title" required 
                       value="<%= request.getParameter("title") != null ? request.getParameter("title") : "" %>">
            </div>
            
            <div class="form-group">
                <label for="writer">작성자 <span style="color: red;">*</span></label>
                <input type="text" id="writer" name="writer" required
                       value="<%= request.getParameter("writer") != null ? request.getParameter("writer") : "" %>">
            </div>
            
            <div class="form-group">
                <label for="password">비밀번호 <span style="color: red;">*</span></label>
                <input type="password" id="password" name="password" required>
                <div class="info">게시글 수정/삭제 시 필요합니다.</div>
            </div>
            
            <div class="form-group">
                <label for="content">내용 <span style="color: red;">*</span></label>
                <textarea id="content" name="content" required><%= request.getParameter("content") != null ? request.getParameter("content") : "" %></textarea>
            </div>
            
            <div class="btn-group">
                <button type="submit" class="btn">등록</button>
                <a href="board_list.jsp" class="btn btn-cancel">취소</a>
            </div>
        </form>
    </div>
</body>
</html>
