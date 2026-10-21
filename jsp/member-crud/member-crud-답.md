# member-crud 정답

문제: [member-crud-문제.md](./member-crud-문제.md)  
이론: [member-crud-상세정리.md](./member-crud-상세정리.md)

---

## 객관식

| 번호 | 정답 | 해설 |
|------|------|------|
| Q1 | **②** | Session으로 `loginId`, `loginName` 유지 |
| Q2 | **②** | DTO = 데이터 전달 객체 |
| Q3 | **③** | DAO = SQL / DB 접근 |
| Q4 | **②** | `member_crud` |
| Q5 | **③** | `id` PRIMARY KEY |
| Q6 | **②** | 정적 include |
| Q7 | **②** | Bootstrap CDN의 `.nav-link.active` |
| Q8 | **②** | `getParameter` **전**에 인코딩 |
| Q9 | **②** | `join.jsp?error=dup` |
| Q10 | **③** | 직접 정한 에러 플래그 문자열 |
| Q11 | **①** | `loginId`, `loginName` |
| Q12 | **②** | `session.invalidate()` |
| Q13 | **②** | `selectAll()` |
| Q14 | **②** | 쿼리 파라미터 → `getParameter` |
| Q15 | **②** | 본인 또는 admin |
| Q16 | **①** | hidden으로 id 전송 |
| Q17 | **②** | disabled는 미전송 → hidden 필요 |
| Q18 | **②** | `login.jsp?error=1` |
| Q19 | **②** | 행 존재 = 중복(true) |
| Q20 | **②** | SELECT → `executeQuery` |
| Q21 | **②** | 변경 → `executeUpdate` |
| Q22 | **②** | PreparedStatement + `?` |
| Q23 | **②** | `map` 반환형 = `MemberDTO` |
| Q24 | **②** | 자원 자동 해제 |
| Q25 | **②** | `WEB-INF/lib` |

---

## 단답 · 빈칸

### Q26
- **JSP**: 화면 출력·요청 처리  
- **DTO**: 회원 데이터 담기  
- **DAO**: DB SQL 실행  
- **Session**: 로그인 상태 유지  

### Q27
```text
existsId(id)
insert(m)
login(id, password)
selectAll()
selectById(id)
update(m)
delete(id)
```

### Q28
`login.jsp`

### Q29
`session.setAttribute("loginName", m.getName());` 로 세션 이름 갱신

### Q30
강의용 안전장치 — 관리자 계정이 삭제되면 실습이 어려워짐 (또는 관리자 보호)

---

## 서술

### Q31
`join.jsp` → `joinProcess.jsp` → (`MemberDAO.insert`) → `login.jsp`  
(메인에서 가입 링크로 들어왔다면 그 앞은 `index`/`header` 링크)

### Q32
로그인 성공 시 `loginProcess`가 세션에 `loginId` 등을 넣음.  
`header.jsp`가 `session.getAttribute("loginId")`가 `null`이 아니면  
로그인·가입 메뉴 대신 이름·내정보·로그아웃을 출력함.

### Q33
버튼 숨김은 UI뿐이라 URL로 `updateProcess`에 직접 요청할 수 있음.  
서버(Process)에서 권한을 다시 검사해야 실제로 막을 수 있음.

### Q34
이유: ResultSet → DTO 변환 코드 중복 제거.  
풀어 쓰기 예:

```java
MemberDTO m = new MemberDTO();
m.setId(rs.getString("id"));
m.setPassword(rs.getString("password"));
m.setName(rs.getString("name"));
m.setEmail(rs.getString("email"));
m.setRegDate(rs.getTimestamp("reg_date"));
list.add(m);
```

### Q35 (예시)
1. 비밀번호 평문 → 해시(BCrypt 등)  
2. 출력 시 XSS 이스케이프  
3. 목록에서 password 미조회 / CSRF 토큰 등  

---

## 코드 고치기

### Q36
**문제:** `setCharacterEncoding`이 `getParameter` **이후**라 한글 등이 깨질 수 있음.  
**수정:** 인코딩을 **맨 위(파라미터 읽기 전)**에 둔다.

```jsp
request.setCharacterEncoding("UTF-8");
String id = request.getParameter("id");
```

### Q37
```text
existsId  → SELECT id FROM member WHERE id = ?
insert    → INSERT INTO member (id, password, name, email) VALUES (?, ?, ?, ?)
login     → SELECT ... WHERE id = ? AND password = ?   (맞음)
```

### Q38
```jsp
session.setAttribute("loginId", member.getId());
session.setAttribute("loginName", member.getName());
```

---

## 실습 가이드

### 실습 1
`admin`, `hong` 두 행이 보이면 정상 (비밀번호 1234 등).

### 실습 2 (예시)
| 단계 | DAO |
|------|-----|
| 가입 처리 | `existsId`, `insert` |
| 로그인 처리 | `login` |
| 목록 | `selectAll` |
| 상세 | `selectById` |
| 수정 처리 | `update` |
| 로그아웃 | 없음 (`invalidate`) |

### 실습 3
Eclipse(또는 Tomcat) **Console** 창에 출력됨. 브라우저 화면에는 안 나옴.

### 실습 4
`update.jsp` 상단에서 `loginId == null`이면 `login.jsp`로 `sendRedirect`.  
로그인 없이는 수정 폼에 들어가지 못함.
