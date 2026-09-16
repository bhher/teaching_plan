# MySQL 설치 및 SQL 기초 실습 교안

**작성일:** 2026-09-15  
**대상:** 비전공자·입문자  
**목표:** MySQL 설치 → Workbench 접속 → DB/테이블 생성 → CRUD·조건 검색까지

**관련 자료**
- [MySQL-1일차.md](./MySQL-1일차.md) (8일 과정)
- [sql/00-시작-샘플.sql](./sql/00-시작-샘플.sql)
- **문제 / 정답:** [MySQL-설치-및-SQL기초-실습-문제.md](./MySQL-설치-및-SQL기초-실습-문제.md) · [MySQL-설치-및-SQL기초-실습-정답.md](./MySQL-설치-및-SQL기초-실습-정답.md)

---

## 목차

1. [MySQL이란?](#1-mysql이란)
2. [데이터베이스 기본 개념](#2-데이터베이스-기본-개념)
3. [MySQL 설치](#3-mysql-설치)
4. [Installer 다운로드](#4-mysql-installer-다운로드)
5. [설치 과정](#5-설치-과정)
6. [MySQL Server 설정](#6-mysql-server-설정)
7. [포트](#7-mysql-포트)
8. [Root 비밀번호](#8-root-비밀번호-설정)
9. [Windows 서비스](#9-windows-서비스)
10. [Workbench 실행](#10-mysql-workbench-실행)
11. [접속 확인](#11-mysql-접속-확인)
12. [SQL이란?](#12-sql이란)
13. [DB 확인·생성·선택](#13-데이터베이스-확인--생성--선택)
14. [테이블 생성](#14-테이블-생성)
15. [PRIMARY KEY · AUTO_INCREMENT](#15-primary-key--auto_increment)
16. [테이블 확인](#16-테이블-목록--구조-확인)
17. [INSERT · SELECT](#17-데이터-입력--조회)
18. [WHERE · AND/OR · BETWEEN · IN · LIKE](#18-조건-검색)
19. [ORDER BY · LIMIT](#19-order-by--limit)
20. [UPDATE · DELETE](#20-update--delete)
21. [CRUD 정리](#21-crud-정리)
22. [오늘 실습 체크](#22-오늘-실습-체크리스트)

---

## 1. MySQL이란?

MySQL은 가장 많이 사용되는 **관계형 데이터베이스 관리 시스템(RDBMS)** 중 하나입니다.

쉽게 말하면,

> **프로그램에서 사용하는 데이터를 저장하고 관리해 주는 프로그램**

입니다.

쇼핑몰을 만든다면 다음 데이터를 저장해야 합니다.

- 회원
- 상품
- 주문
- 게시글
- 댓글
- 결제정보

파일로만 저장하면 데이터가 많아질수록 검색·수정·삭제·관리가 어렵습니다.  
그래서 **데이터베이스(Database)** 를 사용합니다.

---

## 2. 데이터베이스 기본 개념

### 데이터베이스(Database)

데이터를 체계적으로 저장하는 공간입니다.

```
Database
 ├── 회원 테이블
 ├── 상품 테이블
 ├── 주문 테이블
 └── 게시글 테이블
```

### 테이블(Table)

실제 데이터를 **행과 열** 형태로 저장합니다.

예: `member` 테이블

| id | name | age | email |
|----|------|-----|-------|
| 1 | 홍길동 | 20 | hong@test.com |
| 2 | 김철수 | 25 | kim@test.com |
| 3 | 이영희 | 22 | lee@test.com |

### 행(Row)

하나의 데이터입니다.

```
1, 홍길동, 20, hong@test.com
```

### 열(Column)

데이터의 속성입니다.

- `id`
- `name`
- `age`
- `email`

---

## 3. MySQL 설치

Windows에서 일반적으로 다음을 설치합니다.

| 구성 | 역할 |
|------|------|
| **MySQL Server** | 실제 데이터베이스 서버 |
| **MySQL Workbench** | SQL 입력·DB 관리 GUI |
| MySQL Shell | 명령줄 도구 (선택) |

교육에서는 **MySQL Server + MySQL Workbench** 만 설치해도 충분합니다.

- Server = 데이터베이스 본체  
- Workbench = 편하게 조작하는 프로그램  

공식 다운로드: [https://dev.mysql.com/downloads/](https://dev.mysql.com/downloads/)

---

## 4. MySQL Installer 다운로드

MySQL 공식 사이트에서 **Windows용 Installer**를 받습니다.

선택 가능한 구성 예:

- MySQL Server
- MySQL Workbench
- MySQL Shell
- MySQL Router

초급 교육 우선 설치:

1. MySQL Server  
2. MySQL Workbench  

---

## 5. 설치 과정

1. Installer 실행  
2. 설치 유형: 교육 환경이면 **Developer Default** 권장  

Developer Default를 선택하면 개발에 필요한 구성 요소를 함께 설치할 수 있습니다.

---

## 6. MySQL Server 설정

설치 중 서버 설정을 진행합니다.

일반적인 설정:

| 항목 | 교육용 선택 |
|------|-------------|
| Config Type | **Development Computer** |

개발용 PC에서는 Development Computer를 선택합니다.

---

## 7. MySQL 포트

기본 포트:

```
3306
```

즉 MySQL 서버는 기본적으로

```
localhost:3306
```

에서 실행됩니다.

Java / Spring Boot 연결 예:

```
localhost:3306
```

---

## 8. Root 비밀번호 설정

설치 중 관리자 계정 비밀번호를 설정합니다.

| 항목 | 값 |
|------|-----|
| Username | `root` |
| Password | 직접 설정 (교육 예: `1234`) |

> 실제 서비스에서는 `1234`처럼 쉬운 비밀번호를 사용하면 안 됩니다.

---

## 9. Windows 서비스

MySQL을 Windows 서비스로 등록하면 PC 부팅 후 서버를 자동 실행할 수 있습니다.

예: **MySQL80**

확인 방법:

1. `Win + R`  
2. `services.msc` 입력  
3. **MySQL80** 찾기  

---

## 10. MySQL Workbench 실행

설치 후 Workbench를 실행합니다.

연결 예:

- **MySQL Connections** → Local instance MySQL80  

접속 정보:

| 항목 | 값 |
|------|-----|
| Username | `root` |
| Password | 설치 시 설정한 비밀번호 |

---

## 11. MySQL 접속 확인

SQL Editor에서 실행:

```sql
SELECT 1;
```

결과:

```
1
```

나오면 연결 성공입니다.

추가로:

```sql
SELECT VERSION();
```

---

## 12. SQL이란?

**SQL(Structured Query Language)** 은 데이터베이스를 관리하는 언어입니다.

| 명령 | 의미 |
|------|------|
| SELECT | 조회 |
| INSERT | 추가 |
| UPDATE | 수정 |
| DELETE | 삭제 |
| CREATE | 생성 |
| ALTER | 구조 변경 |
| DROP | 삭제(구조) |

---

## 13. 데이터베이스 확인 · 생성 · 선택

### 목록 확인

```sql
SHOW DATABASES;
```

예: `information_schema`, `mysql`, `performance_schema`, `sys` 등

### 생성

```sql
CREATE DATABASE shop;
```

```sql
SHOW DATABASES;
```

→ `shop` 이 보이면 성공

### 사용

```sql
USE shop;
```

현재 DB 확인:

```sql
SELECT DATABASE();
```

결과: `shop`

---

## 14. 테이블 생성

회원 테이블:

```sql
CREATE TABLE member (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    age INT,
    email VARCHAR(100)
);
```

| 컬럼 | 의미 |
|------|------|
| `id INT` | 정수 |
| `name VARCHAR(50)` | 최대 50자 문자열 |
| `age INT` | 나이 |
| `email VARCHAR(100)` | 이메일 |

---

## 15. PRIMARY KEY · AUTO_INCREMENT

### PRIMARY KEY

```sql
id INT PRIMARY KEY
```

- 기본키 = 행을 구분하는 **고유 값**
- 회원 ID는 중복되면 안 됨

### AUTO_INCREMENT

```sql
id INT PRIMARY KEY AUTO_INCREMENT
```

데이터를 넣을 때 ID를 **자동 증가**합니다.

```sql
INSERT INTO member(name, age, email)
VALUES ('홍길동', 20, 'hong@test.com');
```

→ `id`에 `1` 자동 부여, 다음은 `2` …

---

## 16. 테이블 목록 · 구조 확인

```sql
SHOW TABLES;
```

```sql
DESC member;
-- 또는
DESCRIBE member;
```

결과 예:

| Field | Type | Key | Extra |
|-------|------|-----|-------|
| id | int | PRI | auto_increment |
| name | varchar(50) | | |
| age | int | | |
| email | varchar(100) | | |

---

## 17. 데이터 입력 · 조회

### INSERT

```sql
INSERT INTO member(name, age, email)
VALUES ('홍길동', 20, 'hong@test.com');
```

여러 명:

```sql
INSERT INTO member(name, age, email)
VALUES
('김철수', 25, 'kim@test.com'),
('이영희', 22, 'lee@test.com'),
('박민수', 30, 'park@test.com');
```

### SELECT

전체:

```sql
SELECT * FROM member;
```

`*` = 모든 컬럼

원하는 컬럼만:

```sql
SELECT name, age
FROM member;
```

---

## 18. 조건 검색

### WHERE

```sql
SELECT *
FROM member
WHERE age >= 20;
```

```sql
SELECT *
FROM member
WHERE age = 25;
```

```sql
SELECT *
FROM member
WHERE age != 25;
```

### AND / OR

```sql
SELECT *
FROM member
WHERE age >= 30
AND name = '박민수';
```

```sql
SELECT *
FROM member
WHERE age = 20
OR age = 30;
```

### BETWEEN

```sql
SELECT *
FROM member
WHERE age BETWEEN 20 AND 25;
```

### IN

```sql
SELECT *
FROM member
WHERE age IN (20, 25, 30);
```

### LIKE

`김`으로 시작:

```sql
SELECT *
FROM member
WHERE name LIKE '김%';
```

`영` 포함:

```sql
SELECT *
FROM member
WHERE name LIKE '%영%';
```

| 패턴 | 의미 |
|------|------|
| `김%` | 김으로 시작 |
| `%영%` | 영 포함 |
| `%수` | 수로 끝 |

---

## 19. ORDER BY · LIMIT

오름차순 (기본 = ASC):

```sql
SELECT *
FROM member
ORDER BY age;

SELECT *
FROM member
ORDER BY age ASC;
```

내림차순:

```sql
SELECT *
FROM member
ORDER BY age DESC;
```

처음 2명:

```sql
SELECT *
FROM member
LIMIT 2;
```

게시판 최근 10개 패턴:

```sql
SELECT *
FROM board
ORDER BY id DESC
LIMIT 10;
```

---

## 20. UPDATE · DELETE

### UPDATE

```sql
UPDATE member
SET age = 21
WHERE name = '홍길동';
```

⚠️ **WHERE가 매우 중요**

```sql
-- 위험: 모든 회원 나이가 21로 변경됨
UPDATE member
SET age = 21;
```

### DELETE

```sql
DELETE FROM member
WHERE name = '홍길동';
```

```sql
-- 위험: 전체 삭제
DELETE FROM member;
```

---

## 21. CRUD 정리

개발에서 가장 많이 쓰는 기본 기능입니다.

| 기능 | SQL |
|------|-----|
| Create | INSERT |
| Read | SELECT |
| Update | UPDATE |
| Delete | DELETE |

```
CRUD
 ↓
INSERT / SELECT / UPDATE / DELETE
```

Spring Boot 게시판도 결국 이 네 가지가 핵심입니다.

---

## 22. 오늘 실습 체크리스트

### 설치·접속

- [ ] MySQL Server 설치
- [ ] MySQL Workbench 설치
- [ ] root 비밀번호로 접속
- [ ] `SELECT 1;` 성공
- [ ] 서비스 `MySQL80` 확인 (선택)

### SQL

- [ ] `CREATE DATABASE shop;`
- [ ] `USE shop;`
- [ ] `CREATE TABLE member (...);`
- [ ] INSERT 4명
- [ ] `SELECT * FROM member;`
- [ ] WHERE / LIKE / ORDER BY 각 1회
- [ ] UPDATE · DELETE (WHERE 포함)

### 따라하기 한 번에 (복습용)

```sql
CREATE DATABASE shop;
USE shop;

CREATE TABLE member (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    age INT,
    email VARCHAR(100)
);

INSERT INTO member(name, age, email)
VALUES
('홍길동', 20, 'hong@test.com'),
('김철수', 25, 'kim@test.com'),
('이영희', 22, 'lee@test.com'),
('박민수', 30, 'park@test.com');

SELECT * FROM member;
SELECT name, age FROM member WHERE age >= 22;
SELECT * FROM member WHERE name LIKE '김%';
SELECT * FROM member ORDER BY age DESC LIMIT 2;

UPDATE member SET age = 21 WHERE name = '홍길동';
-- DELETE FROM member WHERE name = '홍길동';  -- 필요 시
```

---

## 강사 설명 포인트 (2026-09-15)

1. **MySQL ≠ Workbench** (서버 vs GUI)  
2. **Database → Table → Row → Column** 을 반복  
3. **UPDATE / DELETE = WHERE 먼저**  
4. CRUD 네 가지가 Spring 게시판의 뼈대  

**다음 수업 예고:** 집계함수, GROUP BY, JOIN ([MySQL-4일차](./MySQL-4일차.md) ~ [6일차](./MySQL-6일차.md))
