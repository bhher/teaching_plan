<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>JavaBean — 폼 입력</title>
  <style>
    body { font-family: 'Malgun Gothic', sans-serif; max-width: 480px; margin: 40px auto; padding: 0 16px; }
    label { display: block; margin: 12px 0 4px; }
    input[type="text"] { width: 100%; padding: 8px; box-sizing: border-box; }
    button { margin-top: 16px; padding: 8px 16px; cursor: pointer; }
  </style>
</head>
<body>
  <h2>회원 정보 입력</h2>
  <p>input의 <code>name</code>이 Bean 프로퍼티(<code>id</code>, <code>name</code>)와 같아야 합니다.</p>

  <form action="memberResult.jsp" method="post">
    <label for="id">아이디</label>
    <input type="text" id="id" name="id" placeholder="hong" required />

    <label for="name">이름</label>
    <input type="text" id="name" name="name" placeholder="홍길동" required />

    <button type="submit">전송</button>
  </form>

  <p style="margin-top: 24px;"><a href="member.jsp">← 값 직접 넣기 예제</a></p>
</body>
</html>
