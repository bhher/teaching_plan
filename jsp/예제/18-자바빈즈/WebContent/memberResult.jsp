<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
  request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>JavaBean — property="*"</title>
  <style>
    body { font-family: 'Malgun Gothic', sans-serif; max-width: 480px; margin: 40px auto; padding: 0 16px; }
    p { line-height: 1.8; }
    .hint { margin-top: 20px; padding: 12px; background: #fff8e1; border-radius: 8px; font-size: 0.9rem; }
  </style>
</head>
<body>
  <h2>전송 결과 (property="*")</h2>

  <jsp:useBean id="member" class="com.example.Member" scope="page" />
  <jsp:setProperty name="member" property="*" />

  <p>
    아이디 :
    <jsp:getProperty name="member" property="id" />
  </p>
  <p>
    이름 :
    <jsp:getProperty name="member" property="name" />
  </p>

  <div class="hint">
    <code>property="*"</code> → request 파라미터 이름과 Bean setter가 같으면 자동 연결<br>
    name="id" → setId() · name="name" → setName()
  </div>

  <p style="margin-top: 16px;"><a href="form.jsp">← 다시 입력</a></p>
</body>
</html>
