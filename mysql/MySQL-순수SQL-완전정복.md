# MySQL 순수 SQL 완전정복 교안

> PHP를 제외한 순수 SQL과 MySQL 명령어에 집중한 실전 교안

---

## 목차

1. [SQL 기초와 MySQL 개요](#1-sql-기초와-mysql-개요)
2. [데이터베이스 관리 명령어](#2-데이터베이스-관리-명령어)
3. [테이블 생성과 관리](#3-테이블-생성과-관리)
4. [데이터 조작 언어 (DML)](#4-데이터-조작-언어-dml)
5. [데이터 조회 (SELECT) 심화](#5-데이터-조회-select-심화)
6. [조인 (JOIN)과 서브쿼리](#6-조인-join과-서브쿼리)
7. [집계 함수와 그룹화](#7-집계-함수와-그룹화)
8. [인덱스와 성능 최적화](#8-인덱스와-성능-최적화)
9. [트랜잭션과 락](#9-트랜잭션과-락)
10. [사용자 관리와 권한](#10-사용자-관리와-권한)
11. [백업과 복원](#11-백업과-복원)
12. [실전 예제와 실습](#12-실전-예제와-실습)

---

## 1. SQL 기초와 MySQL 개요

### 1-1. SQL이란?

**SQL (Structured Query Language)**은 관계형 데이터베이스 관리 시스템(RDBMS)에서 데이터를 관리하기 위한 표준 언어입니다.

#### SQL의 특징

- ✅ **표준 언어**: ANSI/ISO 표준을 따르는 국제 표준 언어
- ✅ **선언적 언어**: 무엇을 할지 선언하지만, 어떻게 할지는 DBMS가 결정
- ✅ **비절차적 언어**: 순서대로 실행하는 것이 아니라 결과 중심
- ✅ **대소문자 구분 없음**: 키워드는 대소문자를 구분하지 않음 (관례상 대문자 사용)

### 1-2. SQL 언어 분류

#### DDL (Data Definition Language) - 데이터 정의 언어

```sql
CREATE    -- 데이터베이스, 테이블 생성
ALTER     -- 테이블 구조 변경
DROP      -- 데이터베이스, 테이블 삭제
TRUNCATE  -- 테이블 데이터 전체 삭제
```

#### DML (Data Manipulation Language) - 데이터 조작 언어

```sql
SELECT    -- 데이터 조회
INSERT    -- 데이터 삽입
UPDATE    -- 데이터 수정
DELETE    -- 데이터 삭제
```

#### DCL (Data Control Language) - 데이터 제어 언어

```sql
GRANT     -- 권한 부여
REVOKE    -- 권한 회수
COMMIT    -- 트랜잭션 확정
ROLLBACK  -- 트랜잭션 취소
```

#### TCL (Transaction Control Language) - 트랜잭션 제어 언어

```sql
COMMIT    -- 변경사항 확정
ROLLBACK  -- 변경사항 취소
SAVEPOINT -- 중간 저장점 설정
```

### 1-3. MySQL 개요

**MySQL**은 세계에서 가장 널리 사용되는 오픈소스 관계형 데이터베이스 관리 시스템입니다.

#### MySQL의 장점

- ✅ **오픈소스**: 무료로 사용 가능
- ✅ **높은 성능**: 빠른 처리 속도
- ✅ **안정성**: 검증된 안정성
- ✅ **확장성**: 대규모 데이터 처리 가능
- ✅ **표준 SQL 준수**: 표준 SQL 문법 지원

### 1-4. MySQL 접속 방법

#### 명령줄 접속

```bash
# 기본 접속
mysql -u root -p

# 특정 데이터베이스 접속
mysql -u root -p database_name

# 호스트와 포트 지정
mysql -h localhost -P 3306 -u root -p

# 원격 서버 접속
mysql -h 192.168.1.100 -u username -p
```

#### 접속 확인

```sql
-- 현재 사용자 확인
SELECT USER();

-- 현재 데이터베이스 확인
SELECT DATABASE();

-- MySQL 버전 확인
SELECT VERSION();

-- 현재 시간 확인
SELECT NOW();
```

### 1-5. SQL 문법 기본 규칙

#### 세미콜론(;) 사용

```sql
-- 모든 SQL 명령어 끝에 세미콜론 필수
SELECT * FROM student;

-- 여러 명령어 연속 실행
SELECT * FROM student; SELECT * FROM teacher;
```

#### 주석 사용

```sql
-- 한 줄 주석 (하이픈 2개)

/* 
   여러 줄 주석
   블록 주석
*/

# 한 줄 주석 (해시 기호)
```

#### 대소문자

```sql
-- 키워드는 대소문자 구분 없음 (관례상 대문자)
SELECT * FROM student;
select * from student;  -- 동일하게 작동

-- 테이블명과 컬럼명은 설정에 따라 다름
SELECT Name FROM Student;  -- 대소문자 구분 설정에 따라 다름
```

---

## 2. 데이터베이스 관리 명령어

### 2-1. 데이터베이스 생성

#### 기본 문법

```sql
CREATE DATABASE 데이터베이스명;
```

#### 예시

```sql
-- 기본 생성
CREATE DATABASE school;

-- 문자셋과 콜레이션 지정
CREATE DATABASE school 
CHARACTER SET utf8mb4 
COLLATE utf8mb4_unicode_ci;

-- 이미 존재하면 오류 방지
CREATE DATABASE IF NOT EXISTS school;
```

### 2-2. 데이터베이스 목록 확인

```sql
-- 모든 데이터베이스 목록
SHOW DATABASES;

-- 특정 패턴의 데이터베이스 찾기
SHOW DATABASES LIKE 'school%';

-- 현재 데이터베이스 정보 확인
SHOW CREATE DATABASE school;
```

### 2-3. 데이터베이스 선택

```sql
-- 사용할 데이터베이스 선택
USE school;

-- 선택 확인
SELECT DATABASE();
```

### 2-4. 데이터베이스 수정

```sql
-- 문자셋 변경
ALTER DATABASE school 
CHARACTER SET utf8mb4 
COLLATE utf8mb4_unicode_ci;

-- 데이터베이스 옵션 확인
SHOW VARIABLES LIKE 'character_set_database';
SHOW VARIABLES LIKE 'collation_database';
```

### 2-5. 데이터베이스 삭제

```sql
-- 데이터베이스 삭제
DROP DATABASE school;

-- 존재하지 않아도 오류 방지
DROP DATABASE IF EXISTS school;
```

**⚠️ 주의사항:**
- 데이터베이스 삭제 시 모든 테이블과 데이터가 함께 삭제됩니다
- 복구가 불가능하므로 신중하게 실행해야 합니다
- 삭제 전에 반드시 백업을 받아야 합니다

---

## 3. 테이블 생성과 관리

### 3-1. 테이블 생성 기본

#### 기본 문법

```sql
CREATE TABLE 테이블명 (
    컬럼명1 데이터타입 [제약조건],
    컬럼명2 데이터타입 [제약조건],
    ...
);
```

#### 기본 예시

```sql
CREATE TABLE student (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    age INT,
    email VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### 3-2. 데이터 타입

#### 숫자 타입

```sql
-- 정수형
TINYINT      -- -128 ~ 127 (1 byte)
SMALLINT     -- -32,768 ~ 32,767 (2 bytes)
MEDIUMINT    -- -8,388,608 ~ 8,388,607 (3 bytes)
INT          -- -2,147,483,648 ~ 2,147,483,647 (4 bytes)
BIGINT       -- 매우 큰 정수 (8 bytes)

-- 부호 없는 정수
INT UNSIGNED -- 0 ~ 4,294,967,295

-- 실수형
FLOAT(M, D)  -- 단정밀도 부동소수점
DOUBLE(M, D) -- 배정밀도 부동소수점
DECIMAL(M, D) -- 고정소수점 (정확한 계산)

-- 예시
price DECIMAL(10, 2)  -- 총 10자리, 소수점 2자리
```

#### 문자열 타입

```sql
CHAR(n)      -- 고정 길이 문자열 (최대 255)
VARCHAR(n)   -- 가변 길이 문자열 (최대 65,535)
TEXT         -- 긴 텍스트 (최대 65,535)
TINYTEXT     -- 짧은 텍스트 (최대 255)
MEDIUMTEXT   -- 중간 텍스트 (최대 16,777,215)
LONGTEXT     -- 매우 긴 텍스트 (최대 4,294,967,295)

-- 예시
name VARCHAR(50)      -- 최대 50자
description TEXT      -- 긴 설명
```

#### 날짜/시간 타입

```sql
DATE         -- 날짜만 (YYYY-MM-DD)
TIME         -- 시간만 (HH:MM:SS)
DATETIME     -- 날짜와 시간 (YYYY-MM-DD HH:MM:SS)
TIMESTAMP    -- 타임스탬프 (1970-01-01 이후)
YEAR         -- 연도만 (YYYY)

-- 예시
birth_date DATE
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
```

#### 불리언 타입

```sql
BOOLEAN      -- TRUE 또는 FALSE (실제로는 TINYINT(1))
BOOL         -- BOOLEAN과 동일

-- 예시
is_active BOOLEAN DEFAULT TRUE
```

### 3-3. 제약조건 (Constraints)

#### PRIMARY KEY (기본키)

```sql
-- 단일 컬럼 기본키
CREATE TABLE student (
    id INT PRIMARY KEY,
    name VARCHAR(50)
);

-- AUTO_INCREMENT와 함께 사용
CREATE TABLE student (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50)
);

-- 복합 기본키
CREATE TABLE enrollment (
    student_id INT,
    course_id INT,
    PRIMARY KEY (student_id, course_id)
);
```

#### FOREIGN KEY (외래키)

```sql
-- 외래키 설정
CREATE TABLE grade (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    score INT,
    FOREIGN KEY (student_id) REFERENCES student(id)
);

-- 외래키 옵션
CREATE TABLE grade (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    score INT,
    FOREIGN KEY (student_id) 
        REFERENCES student(id)
        ON DELETE CASCADE      -- 부모 삭제 시 자식도 삭제
        ON UPDATE CASCADE       -- 부모 수정 시 자식도 수정
);
```

#### UNIQUE (고유 제약)

```sql
-- 단일 컬럼 고유 제약
CREATE TABLE student (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) UNIQUE,
    name VARCHAR(50)
);

-- 복합 고유 제약
CREATE TABLE enrollment (
    student_id INT,
    course_id INT,
    UNIQUE KEY (student_id, course_id)
);
```

#### NOT NULL (널 값 금지)

```sql
CREATE TABLE student (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NULL,  -- NULL 허용 (기본값)
    age INT NOT NULL
);
```

#### CHECK (체크 제약)

```sql
-- MySQL 8.0.16 이상
CREATE TABLE product (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    price DECIMAL(10, 2) CHECK (price > 0),
    stock INT CHECK (stock >= 0)
);
```

#### DEFAULT (기본값)

```sql
CREATE TABLE student (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20) DEFAULT 'active',
    age INT DEFAULT 20
);
```

### 3-4. 테이블 구조 확인

```sql
-- 테이블 구조 확인
DESCRIBE student;
-- 또는
DESC student;

-- 상세 정보 확인
SHOW CREATE TABLE student;

-- 테이블 목록 확인
SHOW TABLES;

-- 특정 패턴의 테이블 찾기
SHOW TABLES LIKE 'stu%';
```

### 3-5. 테이블 수정 (ALTER TABLE)

#### 컬럼 추가

```sql
-- 컬럼 추가
ALTER TABLE student ADD COLUMN phone VARCHAR(20);

-- 특정 위치에 컬럼 추가
ALTER TABLE student ADD COLUMN email VARCHAR(100) AFTER name;
ALTER TABLE student ADD COLUMN address VARCHAR(200) FIRST;
```

#### 컬럼 수정

```sql
-- 컬럼 타입 변경
ALTER TABLE student MODIFY COLUMN name VARCHAR(100);

-- 컬럼명과 타입 동시 변경
ALTER TABLE student CHANGE COLUMN name full_name VARCHAR(100);
```

#### 컬럼 삭제

```sql
-- 컬럼 삭제
ALTER TABLE student DROP COLUMN phone;
```

#### 제약조건 추가/삭제

```sql
-- 기본키 추가
ALTER TABLE student ADD PRIMARY KEY (id);

-- 외래키 추가
ALTER TABLE grade 
ADD FOREIGN KEY (student_id) REFERENCES student(id);

-- 고유 제약 추가
ALTER TABLE student ADD UNIQUE (email);

-- 제약조건 삭제
ALTER TABLE student DROP PRIMARY KEY;
ALTER TABLE grade DROP FOREIGN KEY grade_ibfk_1;
ALTER TABLE student DROP INDEX email;
```

### 3-6. 테이블 삭제

```sql
-- 테이블 삭제
DROP TABLE student;

-- 존재하지 않아도 오류 방지
DROP TABLE IF EXISTS student;

-- 여러 테이블 동시 삭제
DROP TABLE student, teacher, course;
```

### 3-7. 테이블 이름 변경

```sql
-- 테이블명 변경
ALTER TABLE student RENAME TO students;

-- 또는
RENAME TABLE student TO students;
```

### 3-8. 테이블 데이터만 삭제

```sql
-- TRUNCATE: 빠르지만 롤백 불가
TRUNCATE TABLE student;

-- DELETE: 느리지만 롤백 가능
DELETE FROM student;
```

---

## 4. 데이터 조작 언어 (DML)

### 4-1. INSERT - 데이터 삽입

#### 기본 문법

```sql
INSERT INTO 테이블명 (컬럼1, 컬럼2, ...)
VALUES (값1, 값2, ...);
```

#### 단일 행 삽입

```sql
-- 모든 컬럼 지정
INSERT INTO student (name, age, email)
VALUES ('홍길동', 20, 'hong@example.com');

-- 컬럼명 생략 (모든 컬럼 값 지정)
INSERT INTO student
VALUES (NULL, '김영희', 25, 'kim@example.com');

-- 일부 컬럼만 지정
INSERT INTO student (name, age)
VALUES ('박철수', 22);
```

#### 여러 행 삽입

```sql
-- VALUES 여러 번 사용
INSERT INTO student (name, age, email) VALUES
('홍길동', 20, 'hong@example.com'),
('김영희', 25, 'kim@example.com'),
('박철수', 22, 'park@example.com');
```

#### SELECT 결과 삽입

```sql
-- 다른 테이블에서 데이터 가져오기
INSERT INTO student_backup (name, age, email)
SELECT name, age, email
FROM student
WHERE age > 20;
```

#### INSERT IGNORE와 REPLACE

```sql
-- 중복 시 무시
INSERT IGNORE INTO student (id, name, age)
VALUES (1, '홍길동', 20);

-- 중복 시 교체
REPLACE INTO student (id, name, age)
VALUES (1, '홍길동', 25);
```

### 4-2. UPDATE - 데이터 수정

#### 기본 문법

```sql
UPDATE 테이블명
SET 컬럼1 = 값1, 컬럼2 = 값2, ...
WHERE 조건;
```

#### 예시

```sql
-- 단일 행 수정
UPDATE student 
SET age = 21 
WHERE id = 1;

-- 여러 컬럼 동시 수정
UPDATE student 
SET age = 22, email = 'newemail@example.com' 
WHERE id = 1;

-- 여러 행 수정
UPDATE student 
SET age = age + 1 
WHERE age < 20;

-- 조건부 수정
UPDATE student 
SET status = 'graduated' 
WHERE age >= 25;
```

#### ⚠️ 주의사항

```sql
-- WHERE 절 없이 실행하면 모든 행이 수정됨!
-- UPDATE student SET age = 20;  -- 위험!

-- 안전한 방법: 항상 WHERE 절 포함
UPDATE student SET age = 20 WHERE id = 1;
```

### 4-3. DELETE - 데이터 삭제

#### 기본 문법

```sql
DELETE FROM 테이블명
WHERE 조건;
```

#### 예시

```sql
-- 단일 행 삭제
DELETE FROM student WHERE id = 1;

-- 여러 행 삭제
DELETE FROM student WHERE age < 18;

-- 조건부 삭제
DELETE FROM student 
WHERE status = 'inactive' AND age > 30;
```

#### ⚠️ 주의사항

```sql
-- WHERE 절 없이 실행하면 모든 데이터 삭제!
-- DELETE FROM student;  -- 위험!

-- 안전한 방법: 항상 WHERE 절 포함
DELETE FROM student WHERE id = 1;
```

#### TRUNCATE vs DELETE

```sql
-- DELETE: 조건부 삭제 가능, 롤백 가능, 느림
DELETE FROM student WHERE age < 18;

-- TRUNCATE: 모든 데이터 삭제, 빠름, 롤백 불가
TRUNCATE TABLE student;
```

---

## 5. 데이터 조회 (SELECT) 심화

### 5-1. SELECT 기본 문법

```sql
SELECT [DISTINCT] 컬럼1, 컬럼2, ...
FROM 테이블명
[WHERE 조건]
[GROUP BY 컬럼]
[HAVING 조건]
[ORDER BY 컬럼 [ASC|DESC]]
[LIMIT 개수];
```

### 5-2. 기본 조회

```sql
-- 모든 컬럼 조회
SELECT * FROM student;

-- 특정 컬럼만 조회
SELECT name, age FROM student;

-- 컬럼에 별칭(ALIAS) 사용
SELECT name AS 이름, age AS 나이 FROM student;
SELECT name 이름, age 나이 FROM student;  -- AS 생략 가능

-- 계산식 사용
SELECT name, age, age + 1 AS 다음년도나이 FROM student;
SELECT name, price, price * 0.1 AS 할인금액 FROM product;
```

### 5-3. DISTINCT - 중복 제거

```sql
-- 중복 제거
SELECT DISTINCT major FROM student;

-- 여러 컬럼 조합의 중복 제거
SELECT DISTINCT major, grade FROM student;
```

### 5-4. WHERE 절 - 조건 검색

#### 비교 연산자

```sql
-- 같음
SELECT * FROM student WHERE age = 20;

-- 다름
SELECT * FROM student WHERE age != 20;
SELECT * FROM student WHERE age <> 20;

-- 크기 비교
SELECT * FROM student WHERE age > 20;
SELECT * FROM student WHERE age >= 20;
SELECT * FROM student WHERE age < 25;
SELECT * FROM student WHERE age <= 25;

-- 범위 검색
SELECT * FROM student WHERE age BETWEEN 20 AND 25;
SELECT * FROM student WHERE age NOT BETWEEN 20 AND 25;

-- 리스트 검색
SELECT * FROM student WHERE major IN ('컴퓨터공학과', '경영학과');
SELECT * FROM student WHERE major NOT IN ('컴퓨터공학과');
```

#### LIKE 연산자 - 패턴 검색

```sql
-- 포함 검색
SELECT * FROM student WHERE name LIKE '%김%';

-- 시작 검색
SELECT * FROM student WHERE name LIKE '김%';

-- 끝 검색
SELECT * FROM student WHERE name LIKE '%수';

-- 정확한 길이 검색
SELECT * FROM student WHERE name LIKE '김__';  -- 김으로 시작하는 3글자

-- 이스케이프 문자 사용
SELECT * FROM student WHERE name LIKE '%\_%';  -- 언더스코어 포함
```

#### NULL 값 검색

```sql
-- NULL인 경우
SELECT * FROM student WHERE email IS NULL;

-- NULL이 아닌 경우
SELECT * FROM student WHERE email IS NOT NULL;
```

#### 논리 연산자

```sql
-- AND (그리고)
SELECT * FROM student 
WHERE age >= 20 AND major = '컴퓨터공학과';

-- OR (또는)
SELECT * FROM student 
WHERE major = '컴퓨터공학과' OR major = '경영학과';

-- NOT (아님)
SELECT * FROM student 
WHERE NOT age < 20;

-- 복합 조건
SELECT * FROM student 
WHERE (age >= 20 AND major = '컴퓨터공학과') 
   OR (age >= 22 AND major = '경영학과');
```

### 5-5. ORDER BY - 정렬

```sql
-- 오름차순 정렬 (기본값)
SELECT * FROM student ORDER BY age ASC;
SELECT * FROM student ORDER BY age;  -- ASC 생략 가능

-- 내림차순 정렬
SELECT * FROM student ORDER BY age DESC;

-- 여러 컬럼 정렬
SELECT * FROM student 
ORDER BY major ASC, age DESC;

-- 컬럼 번호로 정렬
SELECT name, age, major FROM student 
ORDER BY 2 DESC;  -- 2번째 컬럼(age) 기준
```

### 5-6. LIMIT - 결과 제한

```sql
-- 상위 N개만 조회
SELECT * FROM student ORDER BY age DESC LIMIT 5;

-- OFFSET 사용 (페이징)
SELECT * FROM student ORDER BY id LIMIT 10 OFFSET 20;  -- 21~30번째
SELECT * FROM student ORDER BY id LIMIT 20, 10;  -- 동일 (OFFSET, 개수)
```

### 5-7. 문자열 함수

```sql
-- CONCAT: 문자열 연결
SELECT CONCAT(name, '(', age, ')') AS info FROM student;

-- SUBSTRING: 문자열 추출
SELECT SUBSTRING(name, 1, 1) AS 성 FROM student;

-- LENGTH: 문자열 길이
SELECT name, LENGTH(name) AS 이름길이 FROM student;

-- UPPER/LOWER: 대소문자 변환
SELECT UPPER(name) AS 대문자이름 FROM student;
SELECT LOWER(email) AS 소문자이메일 FROM student;

-- TRIM: 공백 제거
SELECT TRIM('  홍길동  ') AS 이름;
```

### 5-8. 숫자 함수

```sql
-- ABS: 절댓값
SELECT ABS(-10) AS 절댓값;

-- ROUND: 반올림
SELECT ROUND(3.14159, 2) AS 반올림;  -- 3.14

-- FLOOR: 내림
SELECT FLOOR(3.9) AS 내림;  -- 3

-- CEIL: 올림
SELECT CEIL(3.1) AS 올림;  -- 4

-- MOD: 나머지
SELECT MOD(10, 3) AS 나머지;  -- 1
```

### 5-9. 날짜/시간 함수

```sql
-- 현재 날짜/시간
SELECT NOW() AS 현재시간;
SELECT CURDATE() AS 오늘날짜;
SELECT CURTIME() AS 현재시간;

-- 날짜 추출
SELECT YEAR(NOW()) AS 연도;
SELECT MONTH(NOW()) AS 월;
SELECT DAY(NOW()) AS 일;
SELECT DAYNAME(NOW()) AS 요일;

-- 날짜 계산
SELECT DATE_ADD(NOW(), INTERVAL 1 DAY) AS 내일;
SELECT DATE_SUB(NOW(), INTERVAL 1 MONTH) AS 한달전;
SELECT DATEDIFF('2024-12-31', NOW()) AS 남은일수;

-- 날짜 포맷
SELECT DATE_FORMAT(NOW(), '%Y-%m-%d %H:%i:%s') AS 포맷된날짜;
```

### 5-10. CASE 문 - 조건부 처리

```sql
-- 기본 CASE 문
SELECT name, age,
    CASE 
        WHEN age >= 20 THEN '성인'
        WHEN age >= 13 THEN '청소년'
        ELSE '어린이'
    END AS 연령대
FROM student;

-- CASE 문을 이용한 집계
SELECT 
    SUM(CASE WHEN age >= 20 THEN 1 ELSE 0 END) AS 성인수,
    SUM(CASE WHEN age < 20 THEN 1 ELSE 0 END) AS 미성년자수
FROM student;
```

---

## 6. 조인 (JOIN)과 서브쿼리

### 6-1. JOIN 기본 개념

**JOIN**은 두 개 이상의 테이블을 연결하여 데이터를 조회하는 방법입니다.

### 6-2. INNER JOIN (내부 조인)

#### 기본 문법

```sql
SELECT 컬럼들
FROM 테이블1
INNER JOIN 테이블2 ON 테이블1.컬럼 = 테이블2.컬럼;
```

#### 예시

```sql
-- 학생과 성적 조인
SELECT s.name, s.major, g.subject, g.score
FROM student s
INNER JOIN grade g ON s.student_id = g.student_id;

-- 여러 테이블 조인
SELECT s.name, c.course_name, g.score
FROM student s
INNER JOIN grade g ON s.student_id = g.student_id
INNER JOIN course c ON g.course_id = c.course_id;
```

#### WHERE 절을 이용한 조인 (구식 방법)

```sql
-- INNER JOIN과 동일한 결과
SELECT s.name, g.subject, g.score
FROM student s, grade g
WHERE s.student_id = g.student_id;
```

### 6-3. LEFT JOIN (왼쪽 외부 조인)

```sql
-- 모든 학생과 그들의 성적 (성적이 없는 학생도 포함)
SELECT s.name, s.major, g.subject, g.score
FROM student s
LEFT JOIN grade g ON s.student_id = g.student_id;

-- 성적이 없는 학생만 조회
SELECT s.name, s.major
FROM student s
LEFT JOIN grade g ON s.student_id = g.student_id
WHERE g.score IS NULL;
```

### 6-4. RIGHT JOIN (오른쪽 외부 조인)

```sql
-- 모든 성적과 그 학생 정보 (학생 정보가 없는 성적도 포함)
SELECT s.name, g.subject, g.score
FROM student s
RIGHT JOIN grade g ON s.student_id = g.student_id;
```

### 6-5. FULL OUTER JOIN

```sql
-- MySQL에서는 FULL OUTER JOIN을 직접 지원하지 않음
-- LEFT JOIN과 RIGHT JOIN을 UNION으로 결합
SELECT s.name, g.subject, g.score
FROM student s
LEFT JOIN grade g ON s.student_id = g.student_id
UNION
SELECT s.name, g.subject, g.score
FROM student s
RIGHT JOIN grade g ON s.student_id = g.student_id;
```

### 6-6. CROSS JOIN (교차 조인)

```sql
-- 모든 가능한 조합 생성 (카티션 곱)
SELECT s.name, c.course_name
FROM student s
CROSS JOIN course c;
```

### 6-7. SELF JOIN (자기 조인)

```sql
-- 같은 테이블을 조인 (예: 상사-부하 관계)
SELECT e1.name AS 직원, e2.name AS 상사
FROM employee e1
LEFT JOIN employee e2 ON e1.manager_id = e2.employee_id;
```

### 6-8. 서브쿼리 (Subquery)

#### 스칼라 서브쿼리 (단일 값 반환)

```sql
-- 각 학생의 평균 점수보다 높은 점수 조회
SELECT s.name, g.score
FROM student s
INNER JOIN grade g ON s.student_id = g.student_id
WHERE g.score > (SELECT AVG(score) FROM grade);
```

#### 서브쿼리와 IN 연산자

```sql
-- 특정 전공 학생들의 성적 조회
SELECT * FROM grade
WHERE student_id IN (
    SELECT student_id FROM student WHERE major = '컴퓨터공학과'
);
```

#### 서브쿼리와 EXISTS

```sql
-- 성적이 있는 학생만 조회
SELECT * FROM student s
WHERE EXISTS (
    SELECT 1 FROM grade g WHERE g.student_id = s.student_id
);
```

#### 상관 서브쿼리

```sql
-- 각 학생의 평균 점수보다 높은 점수만 조회
SELECT s.name, g.subject, g.score
FROM student s
INNER JOIN grade g ON s.student_id = g.student_id
WHERE g.score > (
    SELECT AVG(score) 
    FROM grade 
    WHERE student_id = s.student_id
);
```

#### 서브쿼리를 테이블로 사용

```sql
-- 서브쿼리 결과를 테이블처럼 사용
SELECT * FROM (
    SELECT student_id, AVG(score) AS avg_score
    FROM grade
    GROUP BY student_id
) AS avg_grades
WHERE avg_score > 80;
```

---

## 7. 집계 함수와 그룹화

### 7-1. 집계 함수 (Aggregate Functions)

#### COUNT - 개수

```sql
-- 전체 행 수
SELECT COUNT(*) FROM student;

-- NULL 제외한 개수
SELECT COUNT(email) FROM student;

-- 중복 제거한 개수
SELECT COUNT(DISTINCT major) FROM student;
```

#### SUM - 합계

```sql
-- 총합
SELECT SUM(score) AS 총점 FROM grade;

-- 조건부 합계
SELECT SUM(CASE WHEN score >= 80 THEN score ELSE 0 END) AS 우수점수합계
FROM grade;
```

#### AVG - 평균

```sql
-- 평균
SELECT AVG(score) AS 평균점수 FROM grade;

-- NULL 제외 평균
SELECT AVG(score) AS 평균점수 FROM grade WHERE score IS NOT NULL;
```

#### MAX/MIN - 최대/최소

```sql
-- 최대값
SELECT MAX(score) AS 최고점수 FROM grade;

-- 최소값
SELECT MIN(score) AS 최저점수 FROM grade;

-- 최고점을 받은 학생
SELECT name, score 
FROM student s
INNER JOIN grade g ON s.student_id = g.student_id
WHERE g.score = (SELECT MAX(score) FROM grade);
```

### 7-2. GROUP BY - 그룹화

#### 기본 사용법

```sql
-- 전공별 학생 수
SELECT major, COUNT(*) AS 학생수
FROM student
GROUP BY major;

-- 전공별 평균 나이
SELECT major, AVG(age) AS 평균나이
FROM student
GROUP BY major;
```

#### 여러 컬럼 그룹화

```sql
-- 전공과 학년별 학생 수
SELECT major, grade, COUNT(*) AS 학생수
FROM student
GROUP BY major, grade;
```

### 7-3. HAVING - 그룹 조건

```sql
-- 평균 나이가 20세 이상인 전공만
SELECT major, AVG(age) AS 평균나이
FROM student
GROUP BY major
HAVING AVG(age) >= 20;

-- 학생 수가 5명 이상인 전공만
SELECT major, COUNT(*) AS 학생수
FROM student
GROUP BY major
HAVING COUNT(*) >= 5;
```

#### WHERE vs HAVING

```sql
-- WHERE: 그룹화 전 조건 (행 단위 필터링)
SELECT major, AVG(age) AS 평균나이
FROM student
WHERE age >= 18  -- 그룹화 전 필터링
GROUP BY major;

-- HAVING: 그룹화 후 조건 (그룹 단위 필터링)
SELECT major, AVG(age) AS 평균나이
FROM student
GROUP BY major
HAVING AVG(age) >= 20;  -- 그룹화 후 필터링
```

### 7-4. 집계 함수와 JOIN 조합

```sql
-- 전공별 평균 점수
SELECT s.major, AVG(g.score) AS 평균점수
FROM student s
INNER JOIN grade g ON s.student_id = g.student_id
GROUP BY s.major;

-- 과목별 최고점과 최저점
SELECT subject, MAX(score) AS 최고점, MIN(score) AS 최저점
FROM grade
GROUP BY subject;
```

### 7-5. ROLLUP - 소계

```sql
-- 전공별 학생 수와 전체 합계
SELECT major, COUNT(*) AS 학생수
FROM student
GROUP BY major WITH ROLLUP;
```

---

## 8. 인덱스와 성능 최적화

### 8-1. 인덱스란?

**인덱스**는 데이터베이스에서 데이터를 빠르게 찾기 위한 자료구조입니다.

- ✅ 검색 속도 향상
- ✅ 정렬 속도 향상
- ⚠️ 저장 공간 추가 필요
- ⚠️ INSERT/UPDATE/DELETE 속도 저하 가능

### 8-2. 인덱스 생성

#### 기본 인덱스

```sql
-- 단일 컬럼 인덱스
CREATE INDEX idx_name ON student(name);

-- 복합 인덱스
CREATE INDEX idx_major_age ON student(major, age);

-- 고유 인덱스
CREATE UNIQUE INDEX idx_email ON student(email);
```

#### 테이블 생성 시 인덱스 지정

```sql
CREATE TABLE student (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    email VARCHAR(100),
    INDEX idx_name (name),
    UNIQUE INDEX idx_email (email)
);
```

### 8-3. 인덱스 확인

```sql
-- 테이블의 인덱스 확인
SHOW INDEX FROM student;

-- 인덱스 사용 여부 확인
EXPLAIN SELECT * FROM student WHERE name = '홍길동';
```

### 8-4. 인덱스 삭제

```sql
-- 인덱스 삭제
DROP INDEX idx_name ON student;

-- ALTER TABLE로 삭제
ALTER TABLE student DROP INDEX idx_name;
```

### 8-5. 인덱스 사용 팁

- ✅ 자주 검색되는 컬럼에 인덱스 생성
- ✅ WHERE 절에서 자주 사용되는 컬럼
- ✅ JOIN 조건에 사용되는 컬럼
- ✅ ORDER BY에 자주 사용되는 컬럼
- ⚠️ 너무 많은 인덱스는 성능 저하
- ⚠️ 자주 변경되는 컬럼은 인덱스 신중히 사용

---

## 9. 트랜잭션과 락

### 9-1. 트랜잭션이란?

**트랜잭션**은 데이터베이스 작업의 논리적 단위입니다.

#### ACID 속성

- **Atomicity (원자성)**: 모두 성공하거나 모두 실패
- **Consistency (일관성)**: 데이터의 일관성 유지
- **Isolation (격리성)**: 동시 실행 트랜잭션 간 격리
- **Durability (지속성)**: 커밋된 변경사항은 영구 저장

### 9-2. 트랜잭션 사용

#### 기본 문법

```sql
-- 트랜잭션 시작
START TRANSACTION;
-- 또는
BEGIN;

-- 작업 수행
UPDATE account SET balance = balance - 1000 WHERE id = 1;
UPDATE account SET balance = balance + 1000 WHERE id = 2;

-- 커밋 (확정)
COMMIT;

-- 롤백 (취소)
ROLLBACK;
```

#### 자동 커밋 설정

```sql
-- 자동 커밋 확인
SELECT @@autocommit;

-- 자동 커밋 비활성화
SET autocommit = 0;

-- 자동 커밋 활성화
SET autocommit = 1;
```

### 9-3. SAVEPOINT - 중간 저장점

```sql
START TRANSACTION;

UPDATE account SET balance = balance - 1000 WHERE id = 1;
SAVEPOINT sp1;

UPDATE account SET balance = balance + 500 WHERE id = 2;
SAVEPOINT sp2;

-- 특정 저장점으로 롤백
ROLLBACK TO sp1;

-- 트랜잭션 커밋
COMMIT;
```

### 9-4. 트랜잭션 격리 수준

```sql
-- 격리 수준 확인
SELECT @@transaction_isolation;

-- 격리 수준 설정
SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;
SET TRANSACTION ISOLATION LEVEL READ COMMITTED;
SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
```

---

## 10. 사용자 관리와 권한

### 10-1. 사용자 생성

```sql
-- 기본 사용자 생성
CREATE USER 'username'@'localhost' IDENTIFIED BY 'password';

-- 모든 호스트에서 접속 가능
CREATE USER 'username'@'%' IDENTIFIED BY 'password';

-- 특정 IP에서만 접속 가능
CREATE USER 'username'@'192.168.1.100' IDENTIFIED BY 'password';
```

### 10-2. 권한 부여

#### 기본 문법

```sql
GRANT 권한 ON 데이터베이스.테이블 TO '사용자'@'호스트';
```

#### 권한 종류

```sql
-- 모든 권한
GRANT ALL PRIVILEGES ON *.* TO 'user'@'localhost';

-- 특정 데이터베이스의 모든 권한
GRANT ALL PRIVILEGES ON school.* TO 'user'@'localhost';

-- 특정 권한만 부여
GRANT SELECT, INSERT, UPDATE ON school.* TO 'user'@'localhost';

-- 특정 테이블에 대한 권한
GRANT SELECT, INSERT ON school.student TO 'user'@'localhost';
```

### 10-3. 권한 적용

```sql
-- 권한 변경사항 즉시 적용
FLUSH PRIVILEGES;
```

### 10-4. 권한 확인

```sql
-- 사용자 권한 확인
SHOW GRANTS FOR 'user'@'localhost';

-- 현재 사용자 권한 확인
SHOW GRANTS;
```

### 10-5. 권한 회수

```sql
-- 특정 권한 회수
REVOKE SELECT ON school.* FROM 'user'@'localhost';

-- 모든 권한 회수
REVOKE ALL PRIVILEGES ON *.* FROM 'user'@'localhost';

-- 권한 적용
FLUSH PRIVILEGES;
```

### 10-6. 사용자 비밀번호 변경

```sql
-- ALTER USER 사용 (MySQL 5.7.6 이상)
ALTER USER 'user'@'localhost' IDENTIFIED BY 'newpassword';

-- SET PASSWORD 사용
SET PASSWORD FOR 'user'@'localhost' = PASSWORD('newpassword');
```

### 10-7. 사용자 삭제

```sql
-- 사용자 삭제
DROP USER 'user'@'localhost';

-- 여러 사용자 동시 삭제
DROP USER 'user1'@'localhost', 'user2'@'localhost';
```

### 10-8. 사용자 목록 확인

```sql
-- 모든 사용자 목록
SELECT user, host FROM mysql.user;

-- 특정 사용자 확인
SELECT user, host FROM mysql.user WHERE user = 'username';
```

---

## 11. 백업과 복원

### 11-1. mysqldump를 이용한 백업

#### 기본 문법

```bash
mysqldump -u 사용자명 -p 데이터베이스명 > 백업파일.sql
```

#### 백업 예시

```bash
# 단일 데이터베이스 백업
mysqldump -u root -p school > school_backup.sql

# 모든 데이터베이스 백업
mysqldump -u root -p --all-databases > all_backup.sql

# 특정 테이블만 백업
mysqldump -u root -p school student grade > tables_backup.sql

# 구조만 백업 (데이터 제외)
mysqldump -u root -p --no-data school > structure_only.sql

# 데이터만 백업 (구조 제외)
mysqldump -u root -p --no-create-info school > data_only.sql
```

### 11-2. 데이터베이스 복원

#### 명령줄에서 복원

```bash
# 백업 파일로부터 복원
mysql -u root -p school < school_backup.sql

# 모든 데이터베이스 복원
mysql -u root -p < all_backup.sql
```

#### MySQL 접속 후 복원

```sql
-- 데이터베이스 선택
USE school;

-- 백업 파일 실행
SOURCE school_backup.sql;
-- 또는
\. school_backup.sql
```

### 11-3. 압축 백업과 복원

```bash
# 압축하여 백업
mysqldump -u root -p school | gzip > school_backup.sql.gz

# 압축 파일 복원
gunzip < school_backup.sql.gz | mysql -u root -p school
```

---

## 12. 실전 예제와 실습

### 12-1. 학생 관리 시스템

```sql
-- 데이터베이스 생성
CREATE DATABASE school_management;
USE school_management;

-- 학생 테이블
CREATE TABLE student (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    major VARCHAR(50),
    age INT,
    email VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 과목 테이블
CREATE TABLE course (
    course_id INT AUTO_INCREMENT PRIMARY KEY,
    course_name VARCHAR(100) NOT NULL,
    credits INT,
    professor VARCHAR(50)
);

-- 성적 테이블
CREATE TABLE grade (
    grade_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    course_id INT,
    score INT CHECK (score >= 0 AND score <= 100),
    semester VARCHAR(20),
    FOREIGN KEY (student_id) REFERENCES student(student_id),
    FOREIGN KEY (course_id) REFERENCES course(course_id)
);

-- 데이터 삽입
INSERT INTO student (name, major, age, email) VALUES
('김민수', '컴퓨터공학과', 20, 'kim@school.ac.kr'),
('이영희', '경영학과', 21, 'lee@school.ac.kr'),
('박준호', '컴퓨터공학과', 19, 'park@school.ac.kr');

INSERT INTO course (course_name, credits, professor) VALUES
('데이터베이스', 3, '홍교수'),
('자바프로그래밍', 3, '김교수'),
('경영정보시스템', 2, '이교수');

INSERT INTO grade (student_id, course_id, score, semester) VALUES
(1, 1, 85, '2024-1'),
(1, 2, 90, '2024-1'),
(2, 3, 88, '2024-1'),
(3, 1, 92, '2024-1');

-- 학생별 평균 점수 조회
SELECT s.name, s.major, AVG(g.score) AS 평균점수
FROM student s
LEFT JOIN grade g ON s.student_id = g.student_id
GROUP BY s.student_id, s.name, s.major;

-- 과목별 평균 점수 조회
SELECT c.course_name, AVG(g.score) AS 평균점수, COUNT(g.student_id) AS 수강인원
FROM course c
LEFT JOIN grade g ON c.course_id = g.course_id
GROUP BY c.course_id, c.course_name;
```

### 12-2. 쇼핑몰 시스템

```sql
-- 데이터베이스 생성
CREATE DATABASE shop;
USE shop;

-- 상품 테이블
CREATE TABLE product (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(100) NOT NULL,
    price DECIMAL(10, 2) NOT NULL CHECK (price > 0),
    stock INT DEFAULT 0 CHECK (stock >= 0),
    category VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 고객 테이블
CREATE TABLE customer (
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE,
    phone VARCHAR(20),
    address VARCHAR(200)
);

-- 주문 테이블
CREATE TABLE orders (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total_amount DECIMAL(10, 2),
    status VARCHAR(20) DEFAULT '주문완료',
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
);

-- 주문 상세 테이블
CREATE TABLE order_detail (
    detail_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT,
    product_id INT,
    quantity INT CHECK (quantity > 0),
    price DECIMAL(10, 2),
    FOREIGN KEY (order_id) REFERENCES orders(order_id),
    FOREIGN KEY (product_id) REFERENCES product(product_id)
);

-- 데이터 삽입
INSERT INTO product (product_name, price, stock, category) VALUES
('노트북', 1200000, 10, '전자제품'),
('마우스', 25000, 50, '전자제품'),
('키보드', 89000, 30, '전자제품');

INSERT INTO customer (name, email, phone, address) VALUES
('홍길동', 'hong@example.com', '010-1234-5678', '서울시 강남구'),
('김영희', 'kim@example.com', '010-2345-6789', '서울시 서초구');

INSERT INTO orders (customer_id, total_amount) VALUES
(1, 1225000),
(2, 89000);

INSERT INTO order_detail (order_id, product_id, quantity, price) VALUES
(1, 1, 1, 1200000),
(1, 2, 1, 25000),
(2, 3, 1, 89000);

-- 고객별 주문 총액 조회
SELECT c.name, SUM(o.total_amount) AS 총주문액
FROM customer c
LEFT JOIN orders o ON c.customer_id = o.customer_id
GROUP BY c.customer_id, c.name;

-- 카테고리별 판매량 조회
SELECT p.category, SUM(od.quantity) AS 총판매량, SUM(od.quantity * od.price) AS 총매출
FROM product p
INNER JOIN order_detail od ON p.product_id = od.product_id
GROUP BY p.category;
```

### 12-3. 실습 문제

#### 문제 1: 학생 정보 조회
```sql
-- 나이가 20세 이상인 학생의 이름과 전공을 조회하시오.
SELECT name, major FROM student WHERE age >= 20;
```

#### 문제 2: 평균 점수 계산
```sql
-- 각 학생의 평균 점수를 구하고, 평균 점수가 80점 이상인 학생만 조회하시오.
SELECT s.name, AVG(g.score) AS 평균점수
FROM student s
INNER JOIN grade g ON s.student_id = g.student_id
GROUP BY s.student_id, s.name
HAVING AVG(g.score) >= 80;
```

#### 문제 3: 상위 N개 조회
```sql
-- 점수가 가장 높은 상위 5명의 학생을 조회하시오.
SELECT s.name, g.score
FROM student s
INNER JOIN grade g ON s.student_id = g.student_id
ORDER BY g.score DESC
LIMIT 5;
```

---

## 부록: 유용한 MySQL 명령어 모음

### 시스템 정보 확인

```sql
-- MySQL 버전
SELECT VERSION();

-- 현재 사용자
SELECT USER();

-- 현재 데이터베이스
SELECT DATABASE();

-- 현재 시간
SELECT NOW();

-- 서버 상태
SHOW STATUS;

-- 변수 확인
SHOW VARIABLES;
SHOW VARIABLES LIKE 'character_set%';
```

### 테이블 정보 확인

```sql
-- 테이블 목록
SHOW TABLES;

-- 테이블 구조
DESCRIBE 테이블명;
DESC 테이블명;

-- 테이블 생성문 확인
SHOW CREATE TABLE 테이블명;

-- 테이블 상태
SHOW TABLE STATUS;
SHOW TABLE STATUS LIKE '테이블명';
```

### 성능 관련

```sql
-- 쿼리 실행 계획 확인
EXPLAIN SELECT * FROM student WHERE name = '홍길동';

-- 프로세스 목록
SHOW PROCESSLIST;

-- 느린 쿼리 로그 확인
SHOW VARIABLES LIKE 'slow_query_log%';
```

---

## 마무리

이 교안은 순수 SQL과 MySQL 명령어에 집중하여 작성되었습니다. 

### 학습 체크리스트

- [ ] 데이터베이스 생성/삭제/수정
- [ ] 테이블 생성/수정/삭제
- [ ] 데이터 삽입/수정/삭제/조회
- [ ] WHERE, ORDER BY, LIMIT 사용
- [ ] JOIN (INNER, LEFT, RIGHT)
- [ ] 집계 함수와 GROUP BY
- [ ] 서브쿼리 사용
- [ ] 인덱스 생성/관리
- [ ] 트랜잭션 사용
- [ ] 사용자 관리와 권한
- [ ] 백업과 복원

### 추가 학습 자료

- MySQL 공식 문서: https://dev.mysql.com/doc/
- SQL 표준 문서
- 실전 프로젝트를 통한 연습

**좋은 SQL 쿼리를 작성하는 핵심은 연습입니다!**
