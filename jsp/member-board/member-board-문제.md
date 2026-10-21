# member-board 문제

이론: [member-board-상세정리.md](./member-board-상세정리.md)  
정답: [member-board-답.md](./member-board-답.md)  
소스: `jsp/member-board`

**추천:** 객관식 → 단답 → 서술 → 실습

---

## 객관식

### Q1. 이 프로젝트의 DB 이름(문서 기준)은?

① member_crud  
② member_board  
③ crudboard  
④ board  

### Q2. 글쓰기에 필요한 조건은?

① 누구나  
② **로그인 필수**  
③ admin만  
④ 이메일 인증만  

### Q3. 게시글 수정·삭제 권한으로 옳은 것은?

① 로그인한 사람 전원  
② **작성자 본인** 또는 **admin**  
③ 조회한 사람만  
④ 가입일 순 상위 3명  

### Q4. `BoardDTO.writerId`에 대응하는 DB 컬럼은?

① writer  
② writerId  
③ writer_id  
④ login_id  

### Q5. `DBConnection.getConnection()`이 static인 이유로 알맞은 것은?

① SQL을 자동 실행해서  
② 객체 생성 없이 `DBConnection.getConnection()`으로 호출하려고  
③ 세션을 만들기 위해  
④ Bootstrap을 로드하려고  

### Q6. 여러 게시글을 가져올 때 ResultSet에서 쓰는 것은?

① `if (rs.next())` 만  
② `while (rs.next())`  
③ `rs.close()` 만  
④ `executeUpdate()`  

### Q7. `executeQuery()`를 쓰는 SQL은?

① INSERT  
② UPDATE  
③ DELETE  
④ **SELECT**  

### Q8. `executeUpdate()`를 쓰는 SQL이 **아닌** 것은?

① INSERT  
② UPDATE  
③ DELETE  
④ SELECT  

### Q9. 로그인 실패 시 `loginProcess`가 하는 일은?

① `list.jsp`로 이동  
② `login.jsp?error=1`로 redirect  
③ 세션에 password 저장  
④ `insert()` 호출  

### Q10. 로그인 성공 후 세션에 넣는 값으로 바른 것은?

① loginId, loginName  
② password만  
③ board id만  
④ JSESSIONID를 직접 set  

### Q11. `writeProcess`에서 작성자 정보는 어디서 오나?

① 폼의 hidden만  
② **session**의 loginId, loginName  
③ URL 쿼리만  
④ Cookie만  

### Q12. `view.jsp`에서 `canEdit`이 true가 되는 경우는?

① sid == null  
② sid가 작성자이거나 admin  
③ 제목이 비어 있을 때  
④ 항상 true  

### Q13. `UPDATE`에서 `WHERE id = ?`가 중요한 이유는?

① 없어도 한 건만 수정되어서  
② 없으면 **여러/전체 글**이 바뀔 수 있어서  
③ SELECT만 가능해서  
④ 세션 때문  

### Q14. 아이디 중복 시 redirect 주소는?

① join.jsp?error=empty  
② join.jsp?error=dup  
③ login.jsp?error=1  
④ list.jsp?error=dup  

### Q15. `logout.jsp`의 핵심은?

① `dao.delete(loginId)`  
② `session.invalidate()`  
③ `selectAll()`  
④ `sendRedirect`만 하고 세션 유지  

### Q16. 공통 헤더를 넣는 지시자는?

① `<%! %>`  
② `<%@ include file="..." %>`  
③ `<jsp:forward>`  
④ `<%-- --%>`  

### Q17. SQL Injection을 줄이기 위해 쓰는 것은?

① Statement 문자열 더하기  
② PreparedStatement와 `?`  
③ System.out.println  
④ alert()  

### Q18. `MemberDAO.login`이 실패하면 반환하는 값은?

① 빈 MemberDTO  
② false  
③ null  
④ 0  

### Q19. `board.writer_id`와 `member.id`의 관계(문서 기준)는?

① 무관  
② FK로 회원과 연결  
③ board가 member를 삭제  
④ email로만 연결  

### Q20. Process JSP 처리 후 목록으로 보내는 대표 코드는?

① `forward`만 가능  
② `response.sendRedirect("list.jsp")`  
③ `out.println("list")`  
④ `alert("list")`  

---

## 단답 · 빈칸

### Q21
BoardDAO의 CRUD에 해당하는 메서드 이름을 채우세요.

```text
목록(R)  ________
상세(R)  ________
등록(C)  ________
수정(U)  ________
삭제(D)  ________
```

### Q22
회원가입 처리에서 호출 순서: 중복확인 메서드 → 저장 메서드

### Q23
`list.jsp`에서 제목 클릭 시 이동하는 URL 형태를 쓰세요. (예: `view.jsp?...`)

### Q24
세션 키 두 개(`loginId` 말고 이름용)를 쓰세요.

### Q25
try-with-resources를 쓰는 이유를 한 줄로.

---

## 서술

### Q26
글쓰기부터 목록에 글이 보이기까지 파일·메서드 흐름을 순서대로 쓰세요.  
(로그인된 상태라고 가정)

### Q27
「로그인만 하면 아무 글이나 수정 가능한가?」에 답하고, 근거 조건을 쓰세요.

### Q28
DTO와 DAO의 역할을 각각 한 문장으로 구분하세요.

### Q29
화면에서 수정 버튼을 숨기는 것과 `updateProcess`에서 권한을 다시 검사하는 이유가 다른 점을 설명하세요.

### Q30
`writerId`(Java)와 `writer_id`(DB)가 자동으로 같지 않은 이유를 쓰고, 코드에서 어떻게 연결하는지 예를 드세요.

---

## 코드 고치기

### Q31
다음 redirect의 문제점을 고치세요.

```jsp
response.sendRedirect("view.jsp?id" + id);
```

### Q32
다음 매칭이 틀린 것을 고치세요.

```text
selectAll  → DELETE FROM board WHERE id = ?
delete     → SELECT ... FROM board ORDER BY id DESC
insert     → INSERT INTO board (title, content, writer_id, writer_name) VALUES (?, ?, ?, ?)
```

### Q33
`writeProcess`에서 빠진 작성자 설정을 채우세요.

```jsp
b.setTitle(title.trim());
b.setContent(content.trim());
// ???
// ???
new BoardDAO().insert(b);
```

---

## 실습

### 실습 1 ⭐
`db_setup.sql` 실행 후 member·board 샘플이 보이는지 확인하세요.

### 실습 2 ⭐⭐
`hong`으로 로그인 → 글쓰기 → `admin`으로 로그인 → 그 글 수정·삭제가 되는지 확인하세요.  
(admin 권한)

### 실습 3 ⭐⭐
`hong` 글에 `admin`이 아닌 다른 계정으로 로그인했을 때 수정 버튼이 안 보이는지,  
URL로 `update.jsp?id=...` 직접 접근 시 어떻게 되는지 적으세요.

### 실습 4 ⭐⭐⭐
비로그인 상태로 `write.jsp`에 직접 접속하면 어디로 가는지, 어느 코드가 막는지 적으세요.
