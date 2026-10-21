# member-crud 문제

이론: [member-crud-상세정리.md](./member-crud-상세정리.md)  
정답: [member-crud-답.md](./member-crud-답.md)  
소스: `jsp/member-crud`

**추천:** 객관식 → 단답 → 서술 → 실습  
실습은 Tomcat · MySQL 환경에서 확인

---

## 객관식

### Q1. 이 프로젝트에서 **Session**의 역할로 가장 알맞은 것은?

① SQL만 실행한다  
② 로그인 상태(`loginId`, `loginName`)를 유지한다  
③ HTML만 만든다  
④ DB 테이블을 생성한다  

### Q2. `MemberDTO`의 역할은?

① SQL Injection을 막는다  
② 회원 한 명의 데이터를 Java 객체로 담아 전달한다  
③ Tomcat을 시작한다  
④ Bootstrap을 불러온다  

### Q3. `MemberDAO`의 역할은?

① 화면 HTML만 출력한다  
② 세션을 무효화한다  
③ 데이터베이스에 SQL을 실행한다 (조회·저장·수정·삭제)  
④ `web.xml`만 수정한다  

### Q4. DB 이름(문서 기준)은?

① member  
② member_crud  
③ crudboard  
④ mysql  

### Q5. `member` 테이블의 **PRIMARY KEY**는?

① email  
② name  
③ id  
④ password  

### Q6. 공통 헤더·푸터를 JSP에 넣는 지시자는?

① `<%! %>`  
② `<%@ include file="..." %>`  
③ `<%-- --%>`  
④ `<jsp:forward>`  

### Q7. `uri.endsWith("list.jsp") ? "active" : ""` 에서 `active`의 스타일은 어디에 있나?

① `footer.jsp`에 직접 작성  
② Bootstrap CDN CSS  
③ `MemberDAO.java`  
④ `db_setup.sql`  

### Q8. POST 한글이 깨지지 않게 Process JSP에서 **파라미터 읽기 전**에 넣는 코드는?

① `session.invalidate()`  
② `request.setCharacterEncoding("UTF-8")`  
③ `response.sendRedirect("join.jsp")`  
④ `Class.forName(...)`  

### Q9. 아이디 중복 시 `joinProcess`가 보내는 주소는?

① `join.jsp?error=empty`  
② `join.jsp?error=dup`  
③ `login.jsp?error=1`  
④ `list.jsp?error=dup`  

### Q10. `join.jsp`에서 `"dup".equals(err)` 의 `dup`은?

① DB 컬럼 이름  
② 세션 키  
③ `joinProcess`가 쿼리로 붙인 **문자열 플래그**  
④ Bootstrap 클래스  

### Q11. 로그인 성공 시 세션에 넣는 값으로 바른 것은?

① `loginId`, `loginName`  
② `password`, `email`만  
③ `JSESSIONID`만 수동 저장  
④ `admin` 고정  

### Q12. `logout.jsp`의 핵심 코드는?

① `session.setAttribute("loginId", null)` 만  
② `session.invalidate()`  
③ `dao.delete(loginId)`  
④ `request.getParameter("logout")`  

### Q13. `list.jsp`에서 전체 회원을 가져오는 호출은?

① `dao.login(id, pw)`  
② `dao.selectAll()`  
③ `dao.existsId(id)`  
④ `dao.insert(m)`  

### Q14. `view.jsp?id=hong` 에서 아이디를 읽는 코드는?

① `request.getAttribute("id")`  
② `request.getParameter("id")`  
③ `session.getAttribute("id")`  
④ `cookie.getValue()`  

### Q15. 수정·삭제 버튼(`canEdit`) 조건으로 옳은 것은?

① 아무나 가능  
② 로그인한 **본인** 또는 **admin**  
③ email이 있는 사람만  
④ 가입일 최신 회원만  

### Q16. 수정 폼에서 아이디를 서버로 다시 보내기 위해 쓰는 것은?

① `<input type="hidden" name="id" ...>`  
② `<input type="text" disabled>` 만으로 충분  
③ Cookie만  
④ GET 쿼리만 가능  

### Q17. `disabled` 입력란이 폼 제출 시 전송되지 않는 이유로, hidden을 쓰는 이유는?

① 예쁘게 보이려고  
② 화면 표시용과 **실제 전송용**을 나누기 위해  
③ DAO가 disabled만 읽어서  
④ Bootstrap 규칙이라서  

### Q18. 로그인 실패 시 이동 주소는?

① `join.jsp?error=dup`  
② `login.jsp?error=1`  
③ `list.jsp?error=1`  
④ `index.jsp?error=empty`  

### Q19. `existsId`가 `return rs.next()` 하는 의미는?

① 항상 true  
② 행이 있으면 true(중복), 없으면 false  
③ INSERT 성공 여부  
④ 세션 로그인 여부  

### Q20. SELECT에 쓰는 JDBC 메서드는?

① `executeUpdate()`  
② `executeQuery()`  
③ `execute()` 만 가능  
④ `commit()`  

### Q21. INSERT / UPDATE / DELETE에 쓰는 메서드는?

① `executeQuery()`  
② `executeUpdate()`  
③ `rs.next()`  
④ `getConnection()` 만  

### Q22. SQL Injection을 줄이기 위해 DAO에서 쓰는 것은?

① Statement에 문자열 더하기  
② **PreparedStatement** 와 `?`  
③ `System.out.println`  
④ `session`  

### Q23. `list.add(map(rs))`가 되는 이유는?

① `map`이 int를 반환해서  
② `map`이 **MemberDTO**를 반환해서  
③ `List`가 String만 받아서  
④ `rs`를 직접 add해서  

### Q24. try-with-resources의 주된 이점은?

① SQL을 자동 작성  
② Connection 등 자원을 **자동 close**  
③ 세션을 자동 생성  
④ 한글 인코딩 자동 설정  

### Q25. mysql-connector jar를 둘 위치는?

① `src/dto`  
② **WEB-INF/lib**  
③ `include/`  
④ `member/`  

---

## 단답 · 빈칸

### Q26
이 프로젝트의 핵심 4개념(JSP, DTO, DAO, Session)을 한 줄씩 역할만 쓰세요.

### Q27
다음 빈칸을 채우세요.

```text
아이디 중복 확인  → __________(id)
회원가입 저장     → __________(m)
로그인 검증       → __________(id, password)
전체 목록         → __________()
한 명 조회        → __________(id)
수정              → __________(m)
삭제              → __________(id)
```

### Q28
`join.jsp` → `joinProcess.jsp` → DB 저장 → 다음으로 가는 페이지 이름은?

### Q29
본인 이름을 수정한 뒤 헤더에 새 이름이 보이게 `updateProcess`에서 하는 일은?

### Q30
`admin` 계정을 `deleteProcess`에서 삭제하지 못하게 한 이유를 한 줄로.

---

## 서술

### Q31
브라우저에서 회원가입 버튼을 눌러 가입이 끝나고 로그인 화면이 보이기까지  
거치는 JSP 파일 이름을 **순서대로** 쓰세요.

### Q32
로그인 성공 후 `header.jsp`가 로그인/회원가입 대신 이름·로그아웃을 보여주는 원리를  
세션과 관련해 설명하세요.

### Q33
화면에서 수정 버튼을 숨기는 것과, `updateProcess.jsp`에서 권한을 다시 검사하는 것이  
둘 다 필요한 이유를 쓰세요.

### Q34
`map(rs)`를 쓴 이유와, `selectAll`의 while 안에서 풀어 쓴 형태를 간단히 보이세요.

### Q35
학습용 코드에서 개선하면 좋은 점 3가지를 쓰세요. (상세정리 12절 참고)

---

## 코드 고치기

### Q36
다음 코드의 문제점을 말하고 고치세요.

```jsp
<%-- joinProcess.jsp (잘못된 예) --%>
<%
  String id = request.getParameter("id");
  request.setCharacterEncoding("UTF-8");
  // ...
%>
```

### Q37
다음 매칭이 틀린 것을 고치고, 바른 짝을 쓰세요.

```text
existsId  → INSERT INTO member ...
insert    → SELECT id FROM member WHERE id = ?
login     → SELECT ... WHERE id = ? AND password = ?
```

### Q38
다음 `loginProcess` 조각에서 빠진 핵심 2줄을 채우세요.

```jsp
MemberDTO member = dao.login(id.trim(), password);
if (member == null) {
    response.sendRedirect("login.jsp?error=1");
    return;
}
// (여기에 세션 저장)
response.sendRedirect(request.getContextPath() + "/member/list.jsp");
```

---

## 실습

### 실습 1 ⭐
`db_setup.sql` 실행 후 `SELECT * FROM member;` 결과를 기록하세요.

### 실습 2 ⭐⭐
가입 → 로그인 → 목록 → 상세 → 수정 → 로그아웃까지 한 바퀴 돌리고,  
각 단계에서 호출되는 **DAO 메서드**(없으면 ‘없음’)를 적으세요.

### 실습 3 ⭐⭐
`System.out.println`으로 `joinProcess`에서 id, name을 콘솔에 출력해 보세요.  
어디에 보이는지 한 줄로 적으세요.

### 실습 4 ⭐⭐⭐
로그인하지 않은 채로 `update.jsp?id=hong` URL을 직접 입력하면 어떻게 되는지  
확인하고, 어느 코드가 막는지 파일·조건으로 적으세요.
