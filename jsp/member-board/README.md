# member-board — 회원(로그인) + 게시판 CRUD 연동

회원가입·로그인(세션)과 게시판 CRUD가 **한 프로젝트**로 연결된 JSP 예제입니다.

**상세 정리:** [member-board-상세정리.md](./member-board-상세정리.md)  
**문제:** [member-board-문제.md](./member-board-문제.md) · **정답:** [member-board-답.md](./member-board-답.md)

## 연동 핵심

| 기능 | 연동 방식 |
|------|-----------|
| 글쓰기 | **로그인 필수**. 작성자 = `session`의 `loginId`, `loginName` |
| 수정·삭제 | **작성자 본인** 또는 `admin`만 |
| 목록·상세 | 로그인 없이 조회 가능 |
| FK | `board.writer_id` → `member.id` |

```text
회원가입/로그인 → session(loginId, loginName)
                         ↓
              글쓰기 시 writer_id / writer_name 저장
                         ↓
              수정·삭제 시 writer_id 와 loginId 비교
```

## DB

1. MySQL에서 `db_setup.sql` 실행 (`member_board`)
2. `DBConnection.java` 비밀번호 확인 (`root` / `1234`)

## 실행

1. Eclipse Dynamic Web Project (`member-board`)
2. `src/` · `WebContent/` 배치
3. `WEB-INF/lib`에 mysql-connector JAR
4. Tomcat 실행 → `http://localhost:8080/member-board/`

테스트 계정: `admin/1234`, `hong/1234`

## 폴더

```text
member-board/
├── db_setup.sql
├── README.md
├── src/
│   ├── util/DBConnection.java
│   ├── dto/MemberDTO.java, BoardDTO.java
│   └── dao/MemberDAO.java, BoardDAO.java
└── WebContent/
    ├── index.jsp
    ├── include/header.jsp, footer.jsp
    ├── member/  join · login · logout
    └── board/   list · view · write · update · delete
```

## 흐름

1. 가입 → 로그인 → 세션 저장  
2. 게시판 목록 → 글쓰기(로그인) → DB에 작성자 포함 INSERT  
3. 상세에서 본인 글만 수정·삭제 버튼  
4. 로그아웃 → `session.invalidate()`
