# MySQL 순수 SQL 완전정복 교안 - 1부 (기초와 기본)

> PHP를 제외한 순수 SQL과 MySQL 명령어에 집중한 실전 교안

---

## 전체 목차

**1부 (기초와 기본):**
1. SQL 기초와 MySQL 개요
2. 데이터베이스 관리 명령어
3. 테이블 생성과 관리
4. 데이터 조작 언어 (DML)
5. 데이터 조회 (SELECT) 심화

**2부 (고급과 실전):**
6. 조인 (JOIN)과 서브쿼리
7. 집계 함수와 그룹화
8. 인덱스와 성능 최적화
9. 트랜잭션과 락
10. 사용자 관리와 권한
11. 백업과 복원
12. 실전 예제와 실습

---

## 1부 목차

1. [SQL 기초와 MySQL 개요](#1-sql-기초와-mysql-개요)
2. [데이터베이스 관리 명령어](#2-데이터베이스-관리-명령어)
3. [테이블 생성과 관리](#3-테이블-생성과-관리)
4. [데이터 조작 언어 (DML)](#4-데이터-조작-언어-dml)
5. [데이터 조회 (SELECT) 심화](#5-데이터-조회-select-심화)

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

## 1부 마무리

이 1부에서는 SQL과 MySQL의 기초와 기본적인 데이터 조작 방법을 학습했습니다.

### 1부 학습 체크리스트

- [ ] SQL 언어 분류 이해 (DDL, DML, DCL, TCL)
- [ ] MySQL 접속 및 기본 명령어 사용
- [ ] 데이터베이스 생성/삭제/수정
- [ ] 테이블 생성/수정/삭제
- [ ] 데이터 타입 이해 (숫자, 문자열, 날짜, 불리언)
- [ ] 제약조건 사용 (PRIMARY KEY, FOREIGN KEY, UNIQUE, NOT NULL, CHECK, DEFAULT)
- [ ] 데이터 삽입/수정/삭제 (INSERT, UPDATE, DELETE)
- [ ] 데이터 조회 (SELECT)
- [ ] WHERE 절 조건 검색
- [ ] ORDER BY 정렬
- [ ] LIMIT 결과 제한
- [ ] 문자열/숫자/날짜 함수 사용
- [ ] CASE 문 사용

### 다음 단계

**2부에서는 다음 내용을 학습합니다:**
- 조인 (JOIN)과 서브쿼리
- 집계 함수와 그룹화
- 인덱스와 성능 최적화
- 트랜잭션과 락
- 사용자 관리와 권한
- 백업과 복원
- 실전 예제와 실습

**1부를 완료하셨다면 2부로 진행하세요!**
