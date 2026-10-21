# member-board 정답

문제: [member-board-문제.md](./member-board-문제.md)  
이론: [member-board-상세정리.md](./member-board-상세정리.md)

---

## 객관식

| 번호 | 정답 | 해설 |
|------|------|------|
| Q1 | **②** | `member_board` |
| Q2 | **②** | write/writeProcess에서 세션 검사 |
| Q3 | **②** | 작성자 또는 admin (로그인만으로는 부족) |
| Q4 | **③** | `writer_id` |
| Q5 | **②** | 정적 메서드로 바로 호출 |
| Q6 | **②** | 여러 행 → while |
| Q7 | **④** | SELECT → executeQuery |
| Q8 | **④** | SELECT는 executeQuery |
| Q9 | **②** | error=1 |
| Q10 | **①** | loginId, loginName |
| Q11 | **②** | 세션에서 작성자 설정 |
| Q12 | **②** | 본인 또는 admin |
| Q13 | **②** | WHERE 없으면 대량 수정 위험 |
| Q14 | **②** | error=dup |
| Q15 | **②** | invalidate |
| Q16 | **②** | include 지시자 |
| Q17 | **②** | PreparedStatement |
| Q18 | **③** | null = 실패 |
| Q19 | **②** | FK |
| Q20 | **②** | sendRedirect |

---

## 단답 · 빈칸

### Q21
```text
selectAll
selectById
insert
update
delete
```

### Q22
`existsId` → `insert`

### Q23
`view.jsp?id=게시글번호` (예: `view.jsp?id=7`)

### Q24
`loginName` (그리고 `loginId`)

### Q25
Connection / PreparedStatement / ResultSet을 블록 종료 시 자동으로 닫음

---

## 서술

### Q26
`write.jsp` → `writeProcess.jsp`(세션에서 작성자) → `BoardDAO.insert()` → `list.jsp`(`selectAll`)

### Q27
**아니요.** 로그인한 **작성자 본인**이거나 **admin**만 수정·삭제 가능.  
`sid.equals(writerId) || "admin".equals(sid)`

### Q28
- DTO: 회원·게시글 데이터를 객체로 담아 전달  
- DAO: SQL로 DB 조회·등록·수정·삭제  

### Q29
버튼 숨김은 UI뿐이라 URL로 Process를 직접 호출할 수 있음.  
서버에서 권한을 다시 검사해야 실제로 막을 수 있음.

### Q30
Java 이름과 SQL 컬럼명은 별개라 자동 매핑되지 않음.  
예: `b.setWriterId(rs.getString("writer_id"));`

---

## 코드 고치기

### Q31
```jsp
response.sendRedirect("view.jsp?id=" + id);
```
(`id=` 등호 필요)

### Q32
```text
selectAll → SELECT ... FROM board ORDER BY id DESC
delete    → DELETE FROM board WHERE id = ?
insert    → INSERT ... (맞음)
```

### Q33
```jsp
b.setWriterId(loginId);
b.setWriterName(loginName);
```

---

## 실습 가이드

### 실습 1
`admin`/`hong` 회원, 샘플 게시글 2건 정도 보이면 정상.

### 실습 2
admin은 타인 글도 수정·삭제 가능해야 함.

### 실습 3
버튼 없음 + `update.jsp` 접근 시 권한 없으면 `view.jsp`로 redirect (또는 로그인 페이지).

### 실습 4
`write.jsp` 상단에서 `loginId == null`이면 `member/login.jsp`로 redirect.
