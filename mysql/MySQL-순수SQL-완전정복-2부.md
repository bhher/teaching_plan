# MySQL 순수 SQL 완전정복 교안 - 2부 (고급과 실전)

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

## 2부 목차

6. [조인 (JOIN)과 서브쿼리](#6-조인-join과-서브쿼리)
7. [집계 함수와 그룹화](#7-집계-함수와-그룹화)
8. [인덱스와 성능 최적화](#8-인덱스와-성능-최적화)
9. [트랜잭션과 락](#9-트랜잭션과-락)
10. [사용자 관리와 권한](#10-사용자-관리와-권한)
11. [백업과 복원](#11-백업과-복원)
12. [실전 예제와 실습](#12-실전-예제와-실습)

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

- [ ] JOIN (INNER, LEFT, RIGHT, FULL OUTER, CROSS, SELF)
- [ ] 서브쿼리 사용 (스칼라, IN, EXISTS, 상관 서브쿼리)
- [ ] 집계 함수와 GROUP BY
- [ ] HAVING 절 사용
- [ ] 인덱스 생성/관리
- [ ] 트랜잭션 사용
- [ ] 사용자 관리와 권한
- [ ] 백업과 복원
- [ ] 실전 프로젝트 구현

### 추가 학습 자료

- MySQL 공식 문서: https://dev.mysql.com/doc/
- SQL 표준 문서
- 실전 프로젝트를 통한 연습

**좋은 SQL 쿼리를 작성하는 핵심은 연습입니다!**

### 1부와 2부 완료

축하합니다! 1부와 2부를 모두 완료하셨습니다. 이제 실전 프로젝트를 통해 배운 내용을 적용해보세요.
