<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>JavaBean 실습 — 값 직접 넣기</title>
  <style>
    body { font-family: 'Malgun Gothic', sans-serif; max-width: 480px; margin: 40px auto; padding: 0 16px; }
    h2 { margin-bottom: 16px; }
    p { line-height: 1.8; }
    a { color: #1565c0; }
  </style>
</head>
<body>
  <h2>JavaBean 실습</h2>
  <p>useBean → setProperty → getProperty</p>

  <jsp:useBean id="member" class="com.example.Member" scope="page" />

  <jsp:setProperty name="member" property="id" value="admin" />
  <jsp:setProperty name="member" property="name" value="홍길동" />

  <p>
    아이디 :
    <jsp:getProperty name="member" property="id" />
  </p>
  <p>
    이름 :
    <jsp:getProperty name="member" property="name" />
  </p>

  <p><a href="form.jsp">폼에서 property="*" 실습 →</a></p>
</body>
</html>
