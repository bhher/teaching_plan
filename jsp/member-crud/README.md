# member-crud — 로그인 · 회원가입 · 회원 CRUD (JSP)

Eclipse Dynamic Web Project + Tomcat + MySQL 예제입니다.

**파일별 상세 설명:** [member-crud-상세정리.md](./member-crud-상세정리.md)  
**문제:** [member-crud-문제.md](./member-crud-문제.md) · **정답:** [member-crud-답.md](./member-crud-답.md)

## 구성

| 구분 | 내용 |
|------|------|
| DB | `member_crud` / 테이블 `member` |
| 패턴 | DTO · DAO · JSP Process |
| UI | Bootstrap 5 CDN |
| 인증 | Session (`loginId`, `loginName`) |

### CRUD

| 기능 | 파일 |
|------|------|
| Create | `join.jsp` → `joinProcess.jsp` |
| Read | `list.jsp`, `view.jsp` |
| Update | `update.jsp` → `updateProcess.jsp` |
| Delete | `deleteProcess.jsp` |
| Login | `login.jsp` → `loginProcess.jsp` |
| Logout | `logout.jsp` |

권한: **본인** 또는 **admin**만 수정·삭제. `admin` 계정은 삭제 불가.

## 실행 순서

1. MySQL에서 `db_setup.sql` 실행  
2. Eclipse에서 Dynamic Web Project 생성 (프로젝트명 예: `member-crud`)  
3. 아래 소스를 배치  
   - `src/` → Java 소스  
   - `WebContent/` → 웹 루트  
4. `WEB-INF/lib`에 `mysql-connector-j` JAR 추가  
5. `util/DBConnection.java`의 DB 비밀번호를 환경에 맞게 수정 (`root` / `1234`)  
6. Tomcat 서버에 추가 후 실행  
7. 브라우저: `http://localhost:8080/member-crud/`

## 테스트 계정

- `admin` / `1234`
- `hong` / `1234`

## 폴더 구조

```
member-crud/
├── db_setup.sql
├── README.md
├── src/
│   ├── util/DBConnection.java
│   ├── dto/MemberDTO.java
│   └── dao/MemberDAO.java
└── WebContent/
    ├── index.jsp
    ├── include/header.jsp, footer.jsp
    ├── WEB-INF/web.xml
    └── member/
        ├── login.jsp, loginProcess.jsp, logout.jsp
        ├── join.jsp, joinProcess.jsp
        ├── list.jsp, view.jsp
        ├── update.jsp, updateProcess.jsp
        └── deleteProcess.jsp
```

## 흐름 요약

1. 회원가입 → `INSERT` 후 로그인 화면  
2. 로그인 성공 → `session`에 `loginId`, `loginName` 저장  
3. 목록/상세는 누구나 조회 가능  
4. 수정·삭제는 로그인 + 본인(또는 admin)  
5. 로그아웃 → `session.invalidate()`
