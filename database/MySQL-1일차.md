# MySQL 1일차 — 설치와 데이터베이스 기초

**대상:** 비전공자·입문자  
**시간:** 약 150분  
**다음:** [2일차 SELECT 기본](./MySQL-2일차.md)

---

## 1. 학습목표

수업 후 학생은 다음을 할 수 있다.

- MySQL의 역할을 설명할 수 있다.
- MySQL Server와 Workbench의 차이를 이해한다.
- 데이터베이스와 테이블의 관계를 설명할 수 있다.
- 데이터베이스와 테이블을 생성할 수 있다.
- 기본적인 INSERT와 SELECT를 실행할 수 있다.

---

## 2. 개념 설명

### 2-1. 데이터베이스란?

데이터를 **체계적으로 저장하고 관리**하는 시스템이다.

쇼핑몰이라면 이런 데이터가 필요합니다.

- 회원
- 상품
- 주문
- 게시글
- 댓글

파일만으로 관리하면 검색·수정·중복 관리가 어렵습니다.  
그래서 **DBMS(Database Management System)** 를 사용합니다.

| DBMS | 비고 |
|------|------|
| MySQL | 웹에서 많이 사용 (수업 주교재) |
| MariaDB | MySQL과 비슷 |
| Oracle / PostgreSQL / SQL Server | 다른 RDBMS |

### 2-2. MySQL이란?

**관계형 데이터베이스 관리 시스템(RDBMS)** 입니다.

웹에서의 위치:

```
React / HTML·JS
        ↓
     서버 (Spring / JSP 등)
        ↓
      MySQL
```

### 2-3. Database → Table → Row → Column

엑셀과 비교하면 쉽습니다.

```
Database          ← 엑셀 파일 하나와 비슷
 └── Table        ← 시트(Sheet)
      ├── Column  ← 열 (id, name, age)
      └── Row     ← 행 (한 명의 데이터)
```

예:

```
student_db
 └── student
      ┌────┬──────┬─────┐
      │ id │ name │ age │
      ├────┼──────┼─────┤
      │ 1  │ 홍길동 │ 20  │
      │ 2  │ 김철수 │ 22  │
      └────┴──────┴─────┘
```

---

## 3. MySQL 설치 (Windows)

설치 후 확인할 것:

1. **MySQL Server** — 실제로 데이터를 관리하는 서버  
2. **MySQL Workbench** — SQL을 편하게 쓰고 실행하는 GUI  

> MySQL ≠ Workbench  
> Server = 데이터베이스 본체 / Workbench = 리모컨·조작판

---

## 4. 접속 확인

Workbench 접속 후:

```sql
SELECT VERSION();
```

현재 선택된 DB:

```sql
SELECT DATABASE();
```

(아직 DB를 고르지 않았다면 `NULL`이 나올 수 있습니다.)

---

## 5. 예제 코드 (강사 시연 → 따라하기)

### 5-1. 데이터베이스 생성

```sql
CREATE DATABASE school_db;
```

목록:

```sql
SHOW DATABASES;
```

사용:

```sql
USE school_db;
```

### 5-2. 테이블 생성

```sql
CREATE TABLE student (
    id INT,
    name VARCHAR(50),
    age INT
);
```

```sql
SHOW TABLES;
DESC student;
```

### 5-3. 데이터 입력

```sql
INSERT INTO student
VALUES (1, '홍길동', 20);

INSERT INTO student
VALUES (2, '김철수', 22);

INSERT INTO student
VALUES (3, '이영희', 21);
```

### 5-4. 데이터 조회

```sql
SELECT * FROM student;
```

특정 컬럼만:

```sql
SELECT name, age
FROM student;
```

---

## 6. 실습문제

**문제 1.** `academy_db` 데이터베이스를 생성하시오.

**문제 2.** `member` 테이블을 생성하시오.  
컬럼: `id INT`, `name VARCHAR(50)`, `age INT`

**문제 3.** 다음 회원 3명을 입력하시오.

| id | name | age |
|----|------|-----|
| 1 | 홍길동 | 25 |
| 2 | 김철수 | 30 |
| 3 | 이영희 | 28 |

**문제 4.** 모든 회원을 조회하시오.

**문제 5.** 회원의 이름과 나이만 조회하시오.

---

## 7. 정답

```sql
CREATE DATABASE academy_db;

USE academy_db;

CREATE TABLE member (
    id INT,
    name VARCHAR(50),
    age INT
);

INSERT INTO member
VALUES (1, '홍길동', 25);

INSERT INTO member
VALUES (2, '김철수', 30);

INSERT INTO member
VALUES (3, '이영희', 28);

SELECT * FROM member;

SELECT name, age
FROM member;
```

---

## 8. 강사 설명 포인트

### 반드시 설명할 것

학생들이 처음 혼동하는 것:

> **MySQL ≠ MySQL Workbench**

- MySQL = 데이터를 관리하는 **서버**
- Workbench = 그걸 편하게 쓰는 **프로그램**

구조를 반복해서 말합니다.

```
Database
   ↓
Table
   ↓
Row + Column
```

### 학생에게 질문

> "엑셀 파일 하나를 Database라고 하면, Table은 무엇일까요?"

→ **엑셀의 Sheet** 와 비슷하다.

### 오늘 체크

- [ ] Workbench 접속
- [ ] CREATE DATABASE / USE
- [ ] CREATE TABLE
- [ ] INSERT / SELECT
