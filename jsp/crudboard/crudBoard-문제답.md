# crudBoard 문제 · 답

이론: [crudBoard-상세정리.md](./crudBoard-상세정리.md)  
소스: `jsp/crudboard`

**추천:** 객관식 → 단답 → 실습 순  
실습은 Tomcat · MySQL 환경에서 확인

---

# A. 문제

## 객관식

### Q1. CRUD에서 **C**의 의미는?

① Create (생성)  
② Count (개수)  
③ Copy (복사)  
④ Close (닫기)  

### Q2. 이 최소 게시판 테이블에 **없는** 컬럼은?

① id  
② title  
③ content  
④ writer  

### Q3. `id` 컬럼을 INSERT 문에 넣지 않는 이유는?

① 필요 없어서  
② **AUTO_INCREMENT** 로 자동 증가하기 때문  
③ PRIMARY KEY가 아니어서  
④ TEXT 타입이라서  

### Q4. BoardDTO의 역할로 가장 알맞은 것은?

① SQL만 실행한다  
② DB 한 행 데이터를 Java 객체로 담는다  
③ HTML만 만든다  
④ Tomcat을 시작한다  

### Q5. BoardDAO에서 SQL Injection을 줄이기 위해 쓰는 것은?

① Statement에 문자열 더하기만  
② **PreparedStatement** 와 `?`  
③ System.out.println  
④ Cookie  

### Q6. 목록 조회에 쓰는 메서드·SQL 연결이 바른 것은?

① `insert()` — SELECT  
② `selectAll()` — `SELECT ... ORDER BY id DESC`  
③ `delete()` — UPDATE  
④ `update()` — INSERT  

### Q7. `write.jsp` → `writeProcess.jsp` 로 보낼 때 권장 method는?

① get  
② **post**  
③ put  
④ head  

### Q8. POST 한글이 깨지지 않게 Process JSP 맨 위에 넣는 코드는?

① `session.invalidate()`  
② `request.setCharacterEncoding("UTF-8")`  
③ `response.addCookie(...)`  
④ `Class.forName(...)`  

### Q9. Process JSP에서 처리 후 목록으로 보내는 코드는?

① `forward` 만 가능  
② `response.sendRedirect("list.jsp")`  
③ `out.println("list")`  
④ `alert("list")`  

### Q10. `view.jsp?id=3` 에서 id 값을 읽는 코드는?

① `request.getAttribute("id")`  
② `request.getParameter("id")`  
③ `session.getAttribute("id")`  
④ `cookie.getValue()`  

### Q11. 수정 폼에서 id를 다시 서버로 넘기기 위해 자주 쓰는 것은?

① `<input type="hidden" name="id" ...>`  
② `<input type="file">`  
③ `<select name="id">` 만  
④ 쿠키만  

### Q12. `deleteProcess` 에서 이 최소 예제의 삭제 조건은?

① 비밀번호 일치  
② **id만으로 DELETE**  
③ 제목 일치  
④ 세션 로그인만  

### Q13. DBConnection URL의 DB 이름(문서 기준)은?

① jspboard  
② **crudboard**  
③ mysql  
④ tomcat  

### Q14. mysql-connector jar를 둘 위치는?

① src/dto  
② **WEB-INF/lib**  
③ include/  
④ db_setup.sql 옆만  

### Q15. 화면 JSP와 Process JSP의 차이로 옳은 것은?

① 둘 다 HTML만 있다  
② 화면은 표시, Process는 DAO 호출 후 redirect  
③ Process만 Bootstrap을 쓴다  
④ 화면은 SQL을 직접 문자열로만 쓴다  

---

## 단답 · 빈칸

### Q16
CRUD 네 글자의 뜻을 한글로 쓰세요.

### Q17
`board` 테이블 컬럼 3개를 쓰세요.

### Q18
다음 빈칸을 채우세요.

```text
목록   SELECT ...          → __________()
상세   SELECT ... WHERE id → __________(id)
등록   INSERT ...          → __________(board)
수정   UPDATE ...          → __________(board)
삭제   DELETE ...          → __________(id)
```

### Q19
`onclick="return confirm('삭제할까요?');"` 에서 취소를 누르면 어떻게 되나요?

### Q20
try-with-resources로 Connection을 여는 이유는? (한 줄)

---

## 서술

### Q21
브라우저에서 글쓰기 버튼을 눌러 등록이 끝나 목록이 보이기까지  
거치는 JSP 파일 이름을 **순서대로** 쓰세요. (list에서 출발)

### Q22
DTO와 DAO의 역할을 각각 한 문장으로 구분하세요.

### Q23
왜 Process JSP에서 `sendRedirect`를 쓰나요? (간단히)

### Q24
이 최소 게시판과 `crud-board`(작성자·비밀번호 있는 버전)의 차이 2가지를 쓰세요.

---

## 코드 고치기

### Q25
다음 코드의 문제점을 말하고 고치세요.

```jsp
<%-- writeProcess.jsp (잘못된 예) --%>
<%
  BoardDTO board = new BoardDTO();
  board.setTitle(request.getParameter("title"));
  board.setContent(request.getParameter("content"));
  new BoardDAO().insert(board);
  // redirect 없음, 인코딩 없음
%>
등록됨
```

### Q26
다음 SQL/메서드 매칭이 틀린 것을 고치고, 바른 짝을 쓰세요.

```text
insert  → DELETE FROM board WHERE id = ?
delete  → INSERT INTO board (title, content) VALUES (?, ?)
```

---

## 실습

### 실습 1 ⭐
`db_setup.sql`을 실행한 뒤 `SELECT * FROM board;` 결과를 캡처·기록하세요.

### 실습 2 ⭐⭐
목록 → 글쓰기 → 등록 → 상세 → 수정 → 삭제까지 한 바퀴 실행하고,  
각 단계에서 호출되는 **DAO 메서드 이름**을 적으세요.

### 실습 3 ⭐⭐
`BoardDTO`에 필드를 추가하지 말고,  
목록 테이블에 **content 앞 20자**만 보이게 `list.jsp`를 수정해 보세요.  
(힌트: `substring` 또는 길이 체크)

### 실습 4 ⭐⭐⭐
삭제 전용 확인 페이지 `delete.jsp`를 추가해,  
바로 `deleteProcess`로 가지 않고 한 번 더 확인하게 바꾸세요. (설계만 서술해도 OK)

---

# B. 정답

## 객관식

| 번호 | 정답 | 해설 |
|------|------|------|
| Q1 | **①** | Create |
| Q2 | **④** | 최소판에 writer 없음 |
| Q3 | **②** | AUTO_INCREMENT |
| Q4 | **②** | 데이터 운반 객체 |
| Q5 | **②** | PreparedStatement |
| Q6 | **②** | selectAll + SELECT DESC |
| Q7 | **②** | post |
| Q8 | **②** | UTF-8 인코딩 |
| Q9 | **②** | sendRedirect |
| Q10 | **②** | getParameter |
| Q11 | **①** | hidden |
| Q12 | **②** | id만으로 삭제 |
| Q13 | **②** | DB명 `crudboard` (`DBConnection` · `db_setup.sql` 동일) |
| Q14 | **②** | WEB-INF/lib |
| Q15 | **②** | 화면 vs 처리+redirect |

---

## 단답 · 빈칸

**Q16** Create(생성) · Read(조회) · Update(수정) · Delete(삭제)

**Q17** id, title, content

**Q18**
```text
selectAll()
selectById(id)
insert(board)
update(board)
delete(id)
```

**Q19** `return false` 효과로 **링크 이동(삭제 요청)이 취소**된다.

**Q20** 사용 후 Connection 등을 **자동으로 close** 하여 자원 누수를 막는다.

---

## 서술

**Q21**  
`list.jsp` → `write.jsp` → `writeProcess.jsp` → `list.jsp`

**Q22**  
- DTO: DB 행을 담는 자바 객체  
- DAO: SQL을 실행해 DB에 접근하는 클래스  

**Q23**  
처리 후 주소창을 바꿔 **새로고침 시 중복 POST(재등록)** 를 줄이기 위해 (PRG).  
또한 목록/상세 URL로 깔끔히 이동.

**Q24** (예)  
- 최소판은 컬럼 3개만 / 확장판은 writer·password·hit 등  
- 최소판은 id만으로 삭제 / 확장판은 비밀번호 확인  

---

## 코드 고치기

**Q25**  
문제: UTF-8 미설정, redirect 없음 → 새로고침 시 재전송·한글 깨짐 가능.  

```jsp
<%
  request.setCharacterEncoding("UTF-8");
  BoardDTO board = new BoardDTO();
  board.setTitle(request.getParameter("title"));
  board.setContent(request.getParameter("content"));
  new BoardDAO().insert(board);
  response.sendRedirect("list.jsp");
%>
```

**Q26**  
서로 뒤바뀜 →  
`insert` ↔ INSERT …  
`delete` ↔ DELETE …

---

## 실습 가이드

| 실습 | 기대 |
|------|------|
| 1 | 샘플 2행 이상 조회 |
| 2 | selectAll → insert → selectById → update → delete |
| 3 | list에서 content 미리보기 (예: 길이&gt;20이면 substring) |
| 4 | delete.jsp(확인 폼) → deleteProcess.jsp |

---

## 배점 예시 (객관식 15 + 서술)

| 구간 | 배점 |
|------|------|
| Q1~Q15 | 각 4점 (60) |
| Q16~Q20 | 각 4점 (20) |
| Q21~Q24 | 각 5점 (20) |
| **합** | **100** |
| Q25~Q26 · 실습 | 가산 또는 숙제 |

---

## 수업 진행 팁

1. `crudBoard-상세정리.md` 흐름도(§6) 함께 보기  
2. 객관식 10분 → 단답 → **실습 2**를 꼭 손으로 한 바퀴  
3. 숙제: Q25 + 실습 3  
