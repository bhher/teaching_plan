# MySQL 기초 교안

---

## 목차

1. [MySQL이란?](#1-mysql이란)
2. [데이터베이스 기본 개념](#2-데이터베이스-기본-개념)
3. [MySQL 설치 및 접속](#3-mysql-설치-및-접속)
4. [데이터베이스 관리](#4-데이터베이스-관리)
5. [테이블 생성 및 관리](#5-테이블-생성-및-관리)
6. [데이터 추가 (INSERT)](#6-데이터-추가-insert)
7. [데이터 조회 (SELECT)](#7-데이터-조회-select)
8. [데이터 수정 (UPDATE)](#8-데이터-수정-update)
9. [데이터 삭제 (DELETE)](#9-데이터-삭제-delete)
10. [조건과 정렬](#10-조건과-정렬)
11. [집계 함수](#11-집계-함수)
12. [실습 문제](#12-실습-문제)

---

## 1. MySQL이란?

### 1-1. MySQL 소개

**MySQL**은 **관계형 데이터베이스 관리 시스템(RDBMS: Relational Database Management System)**입니다.

- **데이터를 표(Table) 형태로 저장하고 관리**합니다
- **웹 서비스, 쇼핑몰, 게시판, 회원관리 시스템**에서 가장 많이 사용됩니다
- **오픈소스**이며 무료로 사용 가능합니다
- **빠른 성능**과 **안정성**이 뛰어납니다

### 1-2. MySQL의 특징

- ✅ **오픈소스**: 무료로 사용 가능
- ✅ **크로스 플랫폼**: Windows, Linux, macOS 모두 지원
- ✅ **높은 성능**: 빠른 데이터 처리 속도
- ✅ **표준 SQL**: SQL 표준을 따름
- ✅ **다양한 프로그래밍 언어 지원**: Java, Python, PHP, Node.js 등

### 1-3. MySQL 사용 예시

- **웹사이트**: 사용자 정보, 게시글, 댓글 저장
- **쇼핑몰**: 상품 정보, 주문 내역, 회원 정보 관리
- **학교 시스템**: 학생 정보, 성적 관리
- **병원 시스템**: 환자 정보, 진료 기록 관리

---

## 2. 데이터베이스 기본 개념

### 2-1. 데이터베이스(Database)

**데이터베이스**는 **데이터를 체계적으로 저장하는 공간**입니다.

- 여러 개의 테이블을 포함할 수 있습니다
- 예: `school` 데이터베이스 안에 `student`, `teacher`, `course` 테이블 존재

### 2-2. 테이블(Table)

**테이블**은 **행(Row)과 열(Column)로 구성된 데이터 구조**입니다.

#### 테이블 구조 예시

| id | name | age |
| -- | ---- | --- |
| 1  | 홍길동  | 20  |
| 2  | 김영희  | 25  |
| 3  | 박철수  | 22  |

- **행(Row)**: 한 명의 학생 정보 (레코드, Record)
- **열(Column)**: 데이터의 종류 (필드, Field)
  - `id`: 학생 번호
  - `name`: 학생 이름
  - `age`: 학생 나이

### 2-3. 주요 용어 정리

| 용어 | 설명 | 예시 |
|------|------|------|
| **데이터베이스** | 테이블들을 모아놓은 공간 | `school`, `shop` |
| **테이블** | 행과 열로 구성된 데이터 구조 | `student`, `product` |
| **행(Row)** | 한 건의 데이터 | 학생 한 명의 정보 |
| **열(Column)** | 데이터의 속성 | `name`, `age`, `email` |
| **레코드(Record)** | 행과 동일한 의미 | 한 명의 학생 정보 |
| **필드(Field)** | 열과 동일한 의미 | `name` 필드, `age` 필드 |
| **기본키(Primary Key)** | 각 행을 구분하는 고유한 값 | `id` |

---

## 3. MySQL 설치 및 접속

### 3-1. MySQL 설치

**Windows:**
1. MySQL 공식 사이트에서 설치 파일 다운로드
2. 설치 마법사 따라 설치
3. root 비밀번호 설정

**Mac:**
```bash
brew install mysql
```

**Linux:**
```bash
sudo apt-get install mysql-server
```

### 3-2. MySQL 접속

#### 명령줄(CLI) 접속

```bash
mysql -u root -p
```

- `-u root`: root 사용자로 접속
- `-p`: 비밀번호 입력 요청

#### 접속 후 확인

```sql
-- MySQL 버전 확인
SELECT VERSION();

-- 현재 시간 확인
SELECT NOW();
```

### 3-3. MySQL Workbench 사용

**MySQL Workbench**는 MySQL을 시각적으로 관리할 수 있는 GUI 도구입니다.

- 테이블 생성, 수정이 쉬움
- SQL 쿼리 실행 및 결과 확인
- 데이터베이스 구조 시각화

---

## 4. 데이터베이스 관리

### 4-1. 데이터베이스 생성

```sql
CREATE DATABASE school;
```

**실행 결과:**
```
Query OK, 1 row affected (0.01 sec)
```

**설명:**
- `CREATE DATABASE`: 데이터베이스를 생성하는 명령어
- `school`: 생성할 데이터베이스 이름
- 세미콜론(`;`)으로 명령어 종료

### 4-2. 데이터베이스 선택

```sql
USE school;
```

**실행 결과:**
```
Database changed
```

**설명:**
- `USE`: 사용할 데이터베이스를 선택하는 명령어
- 이후 모든 명령어는 선택한 데이터베이스에서 실행됨

### 4-3. 데이터베이스 목록 보기

```sql
SHOW DATABASES;
```

**실행 결과:**
```
+--------------------+
| Database           |
+--------------------+
| information_schema |
| mysql              |
| performance_schema |
| school             |
| sys                |
+--------------------+
5 rows in set (0.00 sec)
```

**설명:**
- `SHOW DATABASES`: 모든 데이터베이스 목록을 보여줌
- 시스템 데이터베이스(`information_schema`, `mysql` 등)도 함께 표시됨

### 4-4. 데이터베이스 삭제

```sql
DROP DATABASE school;
```

⚠️ **주의**: 데이터베이스를 삭제하면 내부의 모든 테이블과 데이터가 삭제됩니다!

---

## 5. 테이블 생성 및 관리

### 5-1. 테이블 생성 문법

```sql
CREATE TABLE student (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(20),
    age INT
);
```

**실행 결과:**
```
Query OK, 0 rows affected (0.02 sec)
```

### 5-2. 문법 설명

#### 기본 구조

```sql
CREATE TABLE 테이블명 (
    컬럼명1 데이터타입 [제약조건],
    컬럼명2 데이터타입 [제약조건],
    ...
);
```

#### 데이터 타입

| 데이터 타입 | 설명 | 예시 |
|------------|------|------|
| `INT` | 정수 | 1, 100, -50 |
| `VARCHAR(n)` | 가변 길이 문자열 | VARCHAR(20): 최대 20자 |
| `CHAR(n)` | 고정 길이 문자열 | CHAR(10): 정확히 10자 |
| `DATE` | 날짜 | '2024-01-15' |
| `DATETIME` | 날짜와 시간 | '2024-01-15 14:30:00' |
| `DECIMAL(m, n)` | 소수점 숫자 | DECIMAL(10, 2): 10자리, 소수점 2자리 |

#### 제약조건

| 제약조건 | 설명 |
|---------|------|
| `PRIMARY KEY` | 기본키 (고유한 값, NULL 불가) |
| `AUTO_INCREMENT` | 자동 증가 (1씩 자동 증가) |
| `NOT NULL` | NULL 값 불가 |
| `UNIQUE` | 중복 값 불가 |
| `DEFAULT 값` | 기본값 설정 |

#### 예제 분석

```sql
CREATE TABLE student (
    id INT PRIMARY KEY AUTO_INCREMENT,  -- 정수형, 기본키, 자동증가
    name VARCHAR(20),                  -- 최대 20자 문자열
    age INT                            -- 정수형
);
```

- `id`: 학생 번호, 자동으로 1, 2, 3... 증가
- `name`: 학생 이름, 최대 20자
- `age`: 학생 나이, 정수

### 5-3. 테이블 목록 확인

```sql
SHOW TABLES;
```

**실행 결과:**
```
+------------------+
| Tables_in_school |
+------------------+
| student          |
+------------------+
1 row in set (0.00 sec)
```

### 5-4. 테이블 구조 확인

```sql
DESC student;
```

또는

```sql
DESCRIBE student;
```

**실행 결과:**
```
+-------+-------------+------+-----+---------+----------------+
| Field | Type        | Null | Key | Default | Extra          |
+-------+-------------+------+-----+---------+----------------+
| id    | int         | NO   | PRI | NULL    | auto_increment |
| name  | varchar(20) | YES  |     | NULL    |                |
| age   | int         | YES  |     | NULL    |                |
+-------+-------------+------+-----+---------+----------------+
3 rows in set (0.00 sec)
```

**컬럼 설명:**
- `Field`: 컬럼 이름
- `Type`: 데이터 타입
- `Null`: NULL 허용 여부 (YES/NO)
- `Key`: 키 종류 (PRI=기본키)
- `Default`: 기본값
- `Extra`: 추가 정보 (auto_increment 등)

### 5-5. 테이블 삭제

```sql
DROP TABLE student;
```

⚠️ **주의**: 테이블을 삭제하면 모든 데이터가 삭제됩니다!

### 5-6. 테이블 수정 (ALTER)

#### 컬럼 추가

```sql
ALTER TABLE student ADD COLUMN email VARCHAR(50);
```

#### 컬럼 삭제

```sql
ALTER TABLE student DROP COLUMN email;
```

#### 컬럼 수정

```sql
ALTER TABLE student MODIFY COLUMN name VARCHAR(30);
```

---

## 6. 데이터 추가 (INSERT)

### 6-1. 기본 INSERT 문법

```sql
INSERT INTO student (name, age)
VALUES ('홍길동', 20);
```

**실행 결과:**
```
Query OK, 1 row affected (0.01 sec)
```

### 6-2. 문법 설명

#### 기본 구조

```sql
INSERT INTO 테이블명 (컬럼1, 컬럼2, ...)
VALUES (값1, 값2, ...);
```

#### 여러 개 추가

```sql
INSERT INTO student (name, age)
VALUES 
    ('홍길동', 20),
    ('김영희', 25),
    ('박철수', 22);
```

**실행 결과:**
```
Query OK, 3 rows affected (0.01 sec)
Records: 3  Duplicates: 0  Warnings: 0
```

### 6-3. AUTO_INCREMENT 컬럼 처리

`id`가 `AUTO_INCREMENT`로 설정되어 있으면, `id` 값을 생략할 수 있습니다.

```sql
-- id는 자동으로 1, 2, 3... 증가
INSERT INTO student (name, age) VALUES ('홍길동', 20);
INSERT INTO student (name, age) VALUES ('김영희', 25);
```

### 6-4. 데이터 확인

```sql
SELECT * FROM student;
```

**실행 결과:**
```
+----+--------+------+
| id | name   | age  |
+----+--------+------+
|  1 | 홍길동   |   20 |
|  2 | 김영희   |   25 |
|  3 | 박철수   |   22 |
+----+--------+------+
3 rows in set (0.00 sec)
```

---

## 7. 데이터 조회 (SELECT)

### 7-1. 전체 조회

```sql
SELECT * FROM student;
```

**설명:**
- `SELECT`: 데이터를 조회하는 명령어
- `*`: 모든 컬럼을 의미
- `FROM student`: `student` 테이블에서 조회

**실행 결과:**
```
+----+--------+------+
| id | name   | age  |
+----+--------+------+
|  1 | 홍길동   |   20 |
|  2 | 김영희   |   25 |
|  3 | 박철수   |   22 |
+----+--------+------+
3 rows in set (0.00 sec)
```

### 7-2. 특정 컬럼 조회

```sql
SELECT name, age FROM student;
```

**실행 결과:**
```
+--------+------+
| name   | age  |
+--------+------+
| 홍길동   |   20 |
| 김영희   |   25 |
| 박철수   |   22 |
+--------+------+
3 rows in set (0.00 sec)
```

### 7-3. 조건 조회 (WHERE)

#### 기본 WHERE 문법

```sql
SELECT * FROM student WHERE age >= 20;
```

**실행 결과:**
```
+----+--------+------+
| id | name   | age  |
+----+--------+------+
|  1 | 홍길동   |   20 |
|  2 | 김영희   |   25 |
|  3 | 박철수   |   22 |
+----+--------+------+
3 rows in set (0.00 sec)
```

#### 비교 연산자

| 연산자 | 의미 | 예시 |
|--------|------|------|
| `=` | 같다 | `WHERE age = 20` |
| `!=` 또는 `<>` | 같지 않다 | `WHERE age != 20` |
| `>` | 크다 | `WHERE age > 20` |
| `>=` | 크거나 같다 | `WHERE age >= 20` |
| `<` | 작다 | `WHERE age < 25` |
| `<=` | 작거나 같다 | `WHERE age <= 25` |

#### 예제

```sql
-- 나이가 20세인 학생
SELECT * FROM student WHERE age = 20;

-- 나이가 20세 이상인 학생
SELECT * FROM student WHERE age >= 20;

-- 이름이 '홍길동'인 학생
SELECT * FROM student WHERE name = '홍길동';
```

### 7-4. 문자열 검색 (LIKE)

```sql
-- '홍'으로 시작하는 이름
SELECT * FROM student WHERE name LIKE '홍%';

-- '영'이 포함된 이름
SELECT * FROM student WHERE name LIKE '%영%';

-- '수'로 끝나는 이름
SELECT * FROM student WHERE name LIKE '%수';
```

**와일드카드:**
- `%`: 0개 이상의 문자
- `_`: 정확히 1개의 문자

---

## 8. 데이터 수정 (UPDATE)

### 8-1. 기본 UPDATE 문법

```sql
UPDATE student
SET age = 21
WHERE id = 1;
```

**실행 결과:**
```
Query OK, 1 row affected (0.01 sec)
Rows matched: 1  Changed: 1  Warnings: 0
```

### 8-2. 문법 설명

#### 기본 구조

```sql
UPDATE 테이블명
SET 컬럼1 = 값1, 컬럼2 = 값2, ...
WHERE 조건;
```

#### 여러 컬럼 수정

```sql
UPDATE student
SET name = '홍길동', age = 21
WHERE id = 1;
```

### 8-3. ⚠️ 주의사항

**WHERE 조건 없으면 전체 수정됨!**

```sql
-- ❌ 위험: 모든 학생의 나이가 21로 변경됨!
UPDATE student SET age = 21;

-- ✅ 올바른 방법: 특정 학생만 수정
UPDATE student SET age = 21 WHERE id = 1;
```

### 8-4. 수정 확인

```sql
SELECT * FROM student WHERE id = 1;
```

**실행 결과:**
```
+----+--------+------+
| id | name   | age  |
+----+--------+------+
|  1 | 홍길동   |   21 |
+----+--------+------+
1 row in set (0.00 sec)
```

---

## 9. 데이터 삭제 (DELETE)

### 9-1. 기본 DELETE 문법

```sql
DELETE FROM student WHERE id = 1;
```

**실행 결과:**
```
Query OK, 1 row affected (0.01 sec)
```

### 9-2. 문법 설명

#### 기본 구조

```sql
DELETE FROM 테이블명
WHERE 조건;
```

### 9-3. ⚠️ 주의사항

**WHERE 조건 필수!**

```sql
-- ❌ 위험: 모든 데이터가 삭제됨!
DELETE FROM student;

-- ✅ 올바른 방법: 특정 학생만 삭제
DELETE FROM student WHERE id = 1;
```

### 9-4. 여러 조건으로 삭제

```sql
-- 나이가 20세 미만인 학생 삭제
DELETE FROM student WHERE age < 20;

-- 이름이 '홍길동'인 학생 삭제
DELETE FROM student WHERE name = '홍길동';
```

### 9-5. 삭제 확인

```sql
SELECT * FROM student;
```

---

## 10. 조건과 정렬

### 10-1. AND / OR

#### AND (그리고)

```sql
SELECT * FROM student 
WHERE age >= 20 AND age <= 25;
```

**설명:** 나이가 20세 이상 **그리고** 25세 이하인 학생

**실행 결과:**
```
+----+--------+------+
| id | name   | age  |
+----+--------+------+
|  1 | 홍길동   |   20 |
|  2 | 김영희   |   25 |
|  3 | 박철수   |   22 |
+----+--------+------+
3 rows in set (0.00 sec)
```

#### OR (또는)

```sql
SELECT * FROM student 
WHERE age = 20 OR age = 25;
```

**설명:** 나이가 20세 **또는** 25세인 학생

**실행 결과:**
```
+----+--------+------+
| id | name   | age  |
+----+--------+------+
|  1 | 홍길동   |   20 |
|  2 | 김영희   |   25 |
+----+--------+------+
2 rows in set (0.00 sec)
```

#### IN (여러 값 중 하나)

```sql
SELECT * FROM student 
WHERE age IN (20, 25, 30);
```

**설명:** 나이가 20, 25, 30 중 하나인 학생

### 10-2. 정렬 (ORDER BY)

#### 오름차순 정렬 (ASC)

```sql
SELECT * FROM student 
ORDER BY age ASC;
```

또는 (ASC는 생략 가능)

```sql
SELECT * FROM student 
ORDER BY age;
```

**실행 결과:**
```
+----+--------+------+
| id | name   | age  |
+----+--------+------+
|  1 | 홍길동   |   20 |
|  3 | 박철수   |   22 |
|  2 | 김영희   |   25 |
+----+--------+------+
3 rows in set (0.00 sec)
```

#### 내림차순 정렬 (DESC)

```sql
SELECT * FROM student 
ORDER BY age DESC;
```

**실행 결과:**
```
+----+--------+------+
| id | name   | age  |
+----+--------+------+
|  2 | 김영희   |   25 |
|  3 | 박철수   |   22 |
|  1 | 홍길동   |   20 |
+----+--------+------+
3 rows in set (0.00 sec)
```

#### 여러 컬럼 정렬

```sql
SELECT * FROM student 
ORDER BY age DESC, name ASC;
```

**설명:** 먼저 나이로 내림차순, 나이가 같으면 이름으로 오름차순

### 10-3. LIMIT (결과 개수 제한)

```sql
-- 상위 2개만 조회
SELECT * FROM student 
ORDER BY age DESC 
LIMIT 2;
```

**실행 결과:**
```
+----+--------+------+
| id | name   | age  |
+----+--------+------+
|  2 | 김영희   |   25 |
|  3 | 박철수   |   22 |
+----+--------+------+
2 rows in set (0.00 sec)
```

---

## 11. 집계 함수

### 11-1. COUNT (개수 세기)

```sql
SELECT COUNT(*) FROM student;
```

**실행 결과:**
```
+----------+
| COUNT(*) |
+----------+
|        3 |
+----------+
1 row in set (0.00 sec)
```

**설명:** 전체 학생 수

#### 조건과 함께 사용

```sql
SELECT COUNT(*) FROM student WHERE age >= 20;
```

**설명:** 나이가 20세 이상인 학생 수

### 11-2. AVG (평균)

```sql
SELECT AVG(age) FROM student;
```

**실행 결과:**
```
+----------+
| AVG(age) |
+----------+
|  22.3333 |
+----------+
1 row in set (0.00 sec)
```

**설명:** 평균 나이

### 11-3. SUM (합계)

```sql
SELECT SUM(age) FROM student;
```

**실행 결과:**
```
+----------+
| SUM(age) |
+----------+
|       67 |
+----------+
1 row in set (0.00 sec)
```

**설명:** 나이의 합계

### 11-4. MAX (최댓값)

```sql
SELECT MAX(age) FROM student;
```

**실행 결과:**
```
+----------+
| MAX(age) |
+----------+
|       25 |
+----------+
1 row in set (0.00 sec)
```

**설명:** 최대 나이

### 11-5. MIN (최솟값)

```sql
SELECT MIN(age) FROM student;
```

**실행 결과:**
```
+----------+
| MIN(age) |
+----------+
|       20 |
+----------+
1 row in set (0.00 sec)
```

**설명:** 최소 나이

### 11-6. 집계 함수 정리

| 함수 | 설명 | 예시 |
|------|------|------|
| `COUNT(*)` | 행의 개수 | `SELECT COUNT(*) FROM student;` |
| `AVG(컬럼)` | 평균값 | `SELECT AVG(age) FROM student;` |
| `SUM(컬럼)` | 합계 | `SELECT SUM(age) FROM student;` |
| `MAX(컬럼)` | 최댓값 | `SELECT MAX(age) FROM student;` |
| `MIN(컬럼)` | 최솟값 | `SELECT MIN(age) FROM student;` |

### 11-7. AS (별칭)

```sql
SELECT COUNT(*) AS 학생수 FROM student;
SELECT AVG(age) AS 평균나이 FROM student;
```

**실행 결과:**
```
+--------+
| 학생수   |
+--------+
|      3 |
+--------+
1 row in set (0.00 sec)
```

---

## 12. 실습 문제

### 문제 1: 데이터베이스 및 테이블 생성

**요구사항:**
1. `shop` 데이터베이스를 생성하세요
2. `member` 테이블을 생성하세요
   - `id`: INT, PRIMARY KEY, AUTO_INCREMENT
   - `name`: VARCHAR(20)
   - `email`: VARCHAR(50)

**정답:**

```sql
-- 1. 데이터베이스 생성
CREATE DATABASE shop;

-- 2. 데이터베이스 선택
USE shop;

-- 3. 테이블 생성
CREATE TABLE member (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(20),
    email VARCHAR(50)
);
```

---

### 문제 2: 데이터 추가

**요구사항:**
회원 3명을 추가하세요
- 홍길동, hong@example.com
- 김영희, kim@example.com
- 박철수, park@example.com

**정답:**

```sql
INSERT INTO member (name, email)
VALUES 
    ('홍길동', 'hong@example.com'),
    ('김영희', 'kim@example.com'),
    ('박철수', 'park@example.com');
```

**확인:**

```sql
SELECT * FROM member;
```

**실행 결과:**
```
+----+--------+-------------------+
| id | name   | email              |
+----+--------+-------------------+
|  1 | 홍길동   | hong@example.com  |
|  2 | 김영희   | kim@example.com    |
|  3 | 박철수   | park@example.com   |
+----+--------+-------------------+
3 rows in set (0.00 sec)
```

---

### 문제 3: 데이터 조회

**요구사항:**
1. 전체 회원을 조회하세요
2. 이름과 이메일만 조회하세요
3. 이름이 '홍길동'인 회원을 조회하세요

**정답:**

```sql
-- 1. 전체 조회
SELECT * FROM member;

-- 2. 이름과 이메일만 조회
SELECT name, email FROM member;

-- 3. 특정 회원 조회
SELECT * FROM member WHERE name = '홍길동';
```

---

### 문제 4: 데이터 수정

**요구사항:**
id가 1인 회원의 이메일을 `hong123@example.com`으로 수정하세요

**정답:**

```sql
UPDATE member
SET email = 'hong123@example.com'
WHERE id = 1;
```

**확인:**

```sql
SELECT * FROM member WHERE id = 1;
```

**실행 결과:**
```
+----+--------+----------------------+
| id | name   | email                |
+----+--------+----------------------+
|  1 | 홍길동   | hong123@example.com  |
+----+--------+----------------------+
1 row in set (0.00 sec)
```

---

### 문제 5: 데이터 삭제

**요구사항:**
id가 3인 회원을 삭제하세요

**정답:**

```sql
DELETE FROM member WHERE id = 3;
```

**확인:**

```sql
SELECT * FROM member;
```

**실행 결과:**
```
+----+--------+----------------------+
| id | name   | email                |
+----+--------+----------------------+
|  1 | 홍길동   | hong123@example.com  |
|  2 | 김영희   | kim@example.com      |
+----+--------+----------------------+
2 rows in set (0.00 sec)
```

---

### 문제 6: 집계 함수 사용

**요구사항:**
1. 전체 회원 수를 조회하세요
2. 회원 이름을 오름차순으로 정렬하여 조회하세요

**정답:**

```sql
-- 1. 전체 회원 수
SELECT COUNT(*) AS 회원수 FROM member;

-- 2. 이름 오름차순 정렬
SELECT * FROM member ORDER BY name ASC;
```

---

## 13. 핵심 포인트 정리

### 13-1. SQL 명령어 순서

```
SELECT → FROM → WHERE → ORDER BY → LIMIT
```

**예시:**
```sql
SELECT name, age 
FROM student 
WHERE age >= 20 
ORDER BY age DESC 
LIMIT 5;
```

### 13-2. 주의사항

#### ⚠️ UPDATE / DELETE는 항상 WHERE 확인!

```sql
-- ❌ 위험
UPDATE student SET age = 20;
DELETE FROM student;

-- ✅ 안전
UPDATE student SET age = 20 WHERE id = 1;
DELETE FROM student WHERE id = 1;
```

### 13-3. 데이터 타입 선택 가이드

| 데이터 종류 | 권장 타입 | 예시 |
|------------|----------|------|
| 정수 | `INT` | 나이, 수량 |
| 문자열 (가변) | `VARCHAR(n)` | 이름, 주소 |
| 문자열 (고정) | `CHAR(n)` | 우편번호, 코드 |
| 날짜 | `DATE` | 생년월일 |
| 날짜+시간 | `DATETIME` | 가입일시 |
| 소수점 | `DECIMAL(m, n)` | 가격, 평균 |

### 13-4. 자주 사용하는 패턴

```sql
-- 전체 조회
SELECT * FROM 테이블명;

-- 조건 조회
SELECT * FROM 테이블명 WHERE 조건;

-- 정렬 조회
SELECT * FROM 테이블명 ORDER BY 컬럼명 DESC;

-- 개수 조회
SELECT COUNT(*) FROM 테이블명;

-- 평균 조회
SELECT AVG(컬럼명) FROM 테이블명;
```

---

## 14. 다음 학습 추천

### 14-1. 중급 SQL

- **JOIN**: 여러 테이블 연결하기
- **GROUP BY / HAVING**: 그룹화 및 조건
- **서브쿼리**: 쿼리 안의 쿼리
- **인덱스(Index)**: 검색 속도 향상

### 14-2. 고급 SQL

- **트랜잭션**: 데이터 일관성 보장
- **뷰(View)**: 가상 테이블
- **프로시저(Procedure)**: 저장된 SQL 로직
- **트리거(Trigger)**: 자동 실행 SQL

### 14-3. 프로그래밍 연동

- **Java + JDBC**: Java에서 MySQL 사용
- **Spring Boot + JPA**: ORM 프레임워크
- **Python + MySQL**: Python에서 MySQL 사용
- **Node.js + MySQL**: Node.js에서 MySQL 사용

---

## 15. 참고 자료

### 15-1. 공식 문서

- MySQL 공식 문서: https://dev.mysql.com/doc/
- MySQL 튜토리얼: https://dev.mysql.com/doc/refman/8.0/en/tutorial.html

### 15-2. 학습 사이트

- SQLBolt: https://sqlbolt.com/
- W3Schools SQL: https://www.w3schools.com/sql/

---

## 📌 TIP

1. **SELECT → WHERE → ORDER BY 순서 기억하기**
2. **UPDATE / DELETE는 항상 WHERE 확인하기**
3. **테이블 생성 전에 구조를 먼저 설계하기**
4. **데이터 타입을 적절히 선택하기**
5. **PRIMARY KEY는 항상 설정하기**

---

**작성일:** 2024년  
**버전:** 1.0
