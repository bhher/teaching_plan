# MySQL 설치 및 SQL 기초 — 문제

교안: [MySQL-설치-및-SQL기초-실습-교안.md](./MySQL-설치-및-SQL기초-실습-교안.md)  
정답: [MySQL-설치-및-SQL기초-실습-정답.md](./MySQL-설치-및-SQL기초-실습-정답.md)  
**작성일:** 2026-09-15

> **실습 예시 (교안 `shop`/`member` 와 다른 데이터)**  
> - 데이터베이스: **`bookstore`**  
> - 테이블: **`book`**  
> - 컬럼: `id`, `title`(제목), `author`(저자), `price`(가격), `stock`(재고)

---

# Part 1. 개념 · 설치 (객관식 · 단답)

## 1번

MySQL에 대한 설명으로 **올바른** 것은?

① 웹 브라우저이다  
② 관계형 데이터베이스 관리 시스템(RDBMS)이다  
③ CSS 라이브러리이다  
④ Spring Boot 전용 언어이다  

---

## 2번

**MySQL Server**와 **MySQL Workbench**의 역할로 올바른 것은?

① Server = GUI, Workbench = 데이터베이스 본체  
② Server = 데이터베이스 서버, Workbench = SQL 입력·관리 GUI  
③ 둘 다 같은 프로그램이다  
④ Workbench 없이 Server는 설치할 수 없다  

---

## 3번

MySQL 기본 포트 번호는?

① `8080`  
② `3306`  
③ `80`  
④ `443`  

---

## 4번

다음 중 **테이블의 한 줄(하나의 데이터)** 을 의미하는 용어는?

① Database  
② Column  
③ Row  
④ Schema  

---

## 5번 (단답)

Database → Table → Row → Column 구조를 **한 줄**로 비유하여 설명하시오.  
(예: 엑셀에 비유)

---

## 6번

Windows에서 MySQL 서비스(예: MySQL80)를 확인하는 실행 명령은?

① `cmd`  
② `services.msc`  
③ `regedit`  
④ `notepad`  

---

## 7번

Workbench 접속이 정상인지 확인할 때 쓰는 SQL로 **적절한** 것은?

① `SELECT 1;`  
② `DROP DATABASE;`  
③ `DELETE FROM;`  
④ `INSTALL MYSQL;`  

---

# Part 2. SQL 기본 · DDL

## 8번

SQL에서 **조회**에 해당하는 명령은?

① INSERT  
② SELECT  
③ UPDATE  
④ DELETE  

---

## 9번 (코드)

`bookstore` 데이터베이스를 생성하는 SQL을 작성하시오.

---

## 10번 (코드)

`bookstore` 데이터베이스를 사용(선택)하는 SQL을 작성하시오.

---

## 11번

현재 선택된 데이터베이스를 확인하는 SQL은?

① `SHOW TABLES;`  
② `SELECT DATABASE();`  
③ `DESC book;`  
④ `SHOW COLUMNS;`  

---

## 12번 (코드)

다음 조건으로 `book` 테이블을 생성하는 SQL을 작성하시오.

| 컬럼 | 타입·제약 | 의미 |
|------|-----------|------|
| id | INT, PRIMARY KEY, AUTO_INCREMENT | 도서번호 |
| title | VARCHAR(100) | 제목 |
| author | VARCHAR(50) | 저자 |
| price | INT | 가격 |
| stock | INT | 재고 |

---

## 13번

`PRIMARY KEY`의 설명으로 **올바른** 것은?

① 아무 값이나 중복 가능하다  
② 행을 구분하는 **고유한** 값이다  
③ 문자열만 가능하다  
④ SELECT 전용 키워드이다  

---

## 14번

`AUTO_INCREMENT`의 역할은?

① 테이블을 삭제한다  
② 숫자를 자동으로 증가시켜 부여한다  
③ 비밀번호를 암호화한다  
④ 포트를 연다  

---

## 15번

테이블 구조를 확인하는 SQL로 **올바른** 것은?

① `SHOW DATABASES;`  
② `DESC book;`  
③ `SELECT 1;`  
④ `USE book;`  

---

# Part 3. INSERT · SELECT · 조건

> 12번에서 만든 **`bookstore`.`book`** 테이블을 기준으로 푸시오.  
> (없으면 아래 샘플을 먼저 실행)

**준비용 샘플 (참고)**

```sql
CREATE DATABASE IF NOT EXISTS bookstore;
USE bookstore;

CREATE TABLE IF NOT EXISTS book (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100),
    author VARCHAR(50),
    price INT,
    stock INT
);
```

## 16번 (코드)

다음 도서 1권을 추가하시오.

- 제목: 자바의 정석  
- 저자: 남궁성  
- 가격: 30000  
- 재고: 10  

(`id`는 AUTO_INCREMENT이므로 넣지 말 것)

---

## 17번 (코드)

다음 3권을 **한 번에** INSERT 하시오.

| title | author | price | stock |
|-------|--------|-------|-------|
| 혼자 공부하는 SQL | 우재남 | 24000 | 15 |
| Do it! 자바스크립트 | 고경희 | 22000 | 8 |
| 스프링 부트와 AWS | 이동욱 | 28000 | 12 |

---

## 18번 (코드)

`book` 전체 컬럼을 조회하시오.

---

## 19번 (코드)

제목과 가격만 조회하시오.

---

## 20번 (코드)

가격이 **24000**원인 도서만 조회하시오.

---

## 21번 (코드)

가격이 **22000 또는 30000**인 도서를 조회하시오. (`OR` 또는 `IN`)

---

## 22번 (코드)

가격이 **22000 이상 28000 이하**인 도서를 조회하시오. (`BETWEEN`)

---

## 23번 (코드)

제목이 `혼자`로 시작하는 도서를 조회하시오. (`LIKE`)

---

## 24번 (코드)

제목에 `자바`가 포함된 도서를 조회하시오.

---

## 25번 (코드)

가격 **내림차순**(비싼 순)으로 정렬하여 조회하시오.

---

## 26번 (코드)

가격 높은 순으로 정렬한 뒤 **상위 2권만** 조회하시오.

---

# Part 4. UPDATE · DELETE · CRUD

## 27번 (코드)

제목이 `자바의 정석`인 도서의 재고를 **9**로 수정하시오.

---

## 28번

다음 SQL이 **위험한** 이유는?

```sql
UPDATE book
SET price = 10000;
```

① 문법이 틀려서  
② WHERE가 없어 **모든** 행의 가격이 바뀔 수 있어서  
③ SELECT가 아니라서  
④ AUTO_INCREMENT가 깨져서  

---

## 29번 (코드)

제목이 `Do it! 자바스크립트`인 도서를 삭제하시오. (`WHERE` 포함)

---

## 30번

CRUD와 SQL 짝이 **잘못된** 것은?

① Create → INSERT  
② Read → SELECT  
③ Update → ALTER  
④ Delete → DELETE  

---

## 31번 (서술)

Spring Boot 게시판을 만들 때 CRUD가 왜 중요한지 **2~3문장**으로 쓰시오.

---

## 32번 (종합 실습) — 또 다른 DB

아래를 **순서대로** 실행할 SQL을 작성하시오.  
(`bookstore`와 다른 DB를 사용합니다.)

1. DB **`cafe`** 생성 후 사용  
2. 테이블 **`menu`** 생성  
   - `id` INT PK AI  
   - `name` VARCHAR(50) — 메뉴명  
   - `price` INT — 가격  
   - `category` VARCHAR(30) — 분류(예: 커피, 디저트)  
3. 메뉴 4개 INSERT (자유, category는 커피/디저트 섞기)  
4. 가격 4000 이상만 SELECT  
5. 가격 높은 순 LIMIT 2  
6. 커피 분류만 LIKE 또는 `=` 로 조회  
7. 한 메뉴 가격 UPDATE (`WHERE` 포함)  
8. (선택) 한 메뉴 DELETE (`WHERE` 포함)  

---

# 채점 가이드 (강사용)

| 구분 | 문항 | 배점 예 |
|------|------|---------|
| 객관·단답 | 1~8, 11, 13~15, 28, 30 | 각 2점 |
| 코드 | 9~10, 12, 16~27, 29 | 각 3점 |
| 서술·종합 | 5, 31, 32 | 5 / 5 / 15점 |
| **합계** | | **약 100점** |

**통과 권장:** 70점 + UPDATE/DELETE에 WHERE 사용 확인  
**데이터:** Part 2~4 = `bookstore.book` / Part 32 = `cafe.menu`
