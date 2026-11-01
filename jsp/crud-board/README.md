# JSP CRUD 게시판 (Bootstrap + MySQL)

목록 · 글쓰기 · 상세 · 수정 · 삭제 전체 CRUD 예제입니다.

## 구성

```text
crud-board/
├── db_setup.sql              ← MySQL 스키마 + 샘플
├── src/
│   ├── util/DBConnection.java
│   ├── dto/BoardDTO.java
│   └── dao/BoardDAO.java
└── WebContent/
    ├── index.jsp
    ├── boardList.jsp         ← Read (목록)
    ├── boardWrite.jsp        ← Create 폼
    ├── boardWriteProcess.jsp
    ├── boardView.jsp         ← Read (상세) + 조회수
    ├── boardUpdate.jsp       ← Update 폼
    ├── boardUpdateProcess.jsp
    ├── boardDelete.jsp       ← Delete 확인
    ├── boardDeleteProcess.jsp
    ├── include/header.jsp    ← Bootstrap 5 CDN
    └── include/footer.jsp
```

## 1. DB 준비

1. MySQL 실행
2. `db_setup.sql` 전체 실행 → DB `crud_board` 생성
3. `src/util/DBConnection.java` 의 계정/비밀번호를 환경에 맞게 수정

```java
USER = "root";
PASSWORD = "1234";  // 본인 MySQL 비밀번호
```

## 2. Eclipse 설정

1. **Dynamic Web Project** 생성 (예: `crud-board`)
2. `src` 패키지 복사: `util`, `dto`, `dao`
3. `WebContent` 내용 복사
4. **MySQL Connector/J** (`mysql-connector-j-8.x.jar`) 를  
   `WebContent/WEB-INF/lib` 에 추가
5. Tomcat 9 또는 10 서버에 프로젝트 Add
6. 실행 후  
   `http://localhost:8080/프로젝트명/boardList.jsp`

> `web.xml` 은 Jakarta(Tomcat 10) 기준입니다.  
> Tomcat 9이면 Eclipse가 생성한 web.xml을 쓰거나 네임스페이스를 `javax`/Servlet 4.0으로 바꾸세요.  
> 이 예제는 **순수 JSP + JDBC**라 Servlet API import가 거의 없어 양쪽에서 동작하기 쉽습니다.

## 3. CRUD 흐름

| 기능 | URL | SQL |
|------|-----|-----|
| 목록 | boardList.jsp | `SELECT ... ORDER BY id DESC` |
| 등록 | boardWrite → Process | `INSERT` |
| 상세 | boardView.jsp?id= | `SELECT` + `hit + 1` |
| 수정 | boardUpdate → Process | `UPDATE ... WHERE id AND password` |
| 삭제 | boardDelete → Process | `DELETE ... WHERE id AND password` |

샘플 글 비밀번호: **1234**

## 4. 학습 포인트

- DTO / DAO / DBConnection 분리
- `PreparedStatement` (SQL Injection 방지)
- Bootstrap 5 (`table`, `form-control`, `btn`, `card`)
- 수정·삭제는 **비밀번호** 확인

## 5. SQL 요약

```sql
CREATE DATABASE crud_board ...;
CREATE TABLE board (
  id INT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(200) NOT NULL,
  content TEXT NOT NULL,
  writer VARCHAR(50) NOT NULL,
  password VARCHAR(100) NOT NULL,
  hit INT DEFAULT 0,
  reg_date DATETIME DEFAULT CURRENT_TIMESTAMP,
  mod_date DATETIME NULL ON UPDATE CURRENT_TIMESTAMP
);
```

자세한 전체 스크립트는 `db_setup.sql` 을 실행하세요.
