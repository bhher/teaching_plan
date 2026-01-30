# MySQL SQL 명령어 실습 교안

---

## 목차

1. [SQL 명령 일괄 실행](#01-sql-명령-일괄-실행)
2. [테이블에의 데이터 삽입](#02-테이블에의-데이터-삽입)
3. [테이블 생성 후 데이터 삽입](#03-테이블-생성-후-데이터-삽입)
4. [데이터 검색과 관리](#04-데이터-검색과-관리)
5. [MySQL 사용자 계정 생성](#05-mysql-사용자-계정-생성)
6. [데이터베이스 백업과 복원](#06-데이터베이스-백업과-복원)

---

## 01 SQL 명령 일괄 실행

### 01-1. SQL 명령 일괄 실행이란?

**SQL 명령 일괄 실행**은 여러 개의 SQL 명령어를 한 번에 실행하는 방법입니다.

- 여러 SQL 명령어를 파일에 저장하여 한 번에 실행할 수 있습니다
- 대량의 데이터를 삽입하거나 복잡한 작업을 수행할 때 유용합니다
- `.sql` 파일 형식으로 저장하여 실행합니다

### 01-2. SQL 파일 생성 방법

#### 방법 1: 텍스트 에디터로 SQL 파일 생성

```sql
-- example.sql 파일 내용
CREATE DATABASE IF NOT EXISTS test_db;
USE test_db;

CREATE TABLE student (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    age INT
);

INSERT INTO student (name, age) VALUES ('홍길동', 20);
INSERT INTO student (name, age) VALUES ('김영희', 25);
```

#### 방법 2: MySQL 명령줄에서 직접 입력

```sql
-- 여러 명령어를 연속으로 입력
CREATE DATABASE test_db;
USE test_db;
CREATE TABLE student (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(50));
```

### 01-3. SQL 파일 실행 방법

#### 방법 1: MySQL 명령줄에서 실행

```bash
# Windows
mysql -u root -p < example.sql

# Linux/Mac
mysql -u root -p < example.sql
```

#### 방법 2: MySQL 접속 후 실행

```sql
mysql> source example.sql;
-- 또는
mysql> \. example.sql
```

#### 방법 3: MySQL Workbench에서 실행

1. File → Open SQL Script
2. SQL 파일 선택
3. Execute 버튼 클릭 (또는 Ctrl+Shift+Enter)

### 01-4. 일괄 실행 예시

**students.sql 파일:**

```sql
-- 데이터베이스 생성
CREATE DATABASE IF NOT EXISTS school;
USE school;

-- 테이블 생성
CREATE TABLE IF NOT EXISTS students (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    major VARCHAR(50),
    age INT,
    email VARCHAR(100)
);

-- 데이터 삽입
INSERT INTO students (name, major, age, email) VALUES
('김민수', '컴퓨터공학과', 20, 'kim@example.com'),
('이영희', '경영학과', 21, 'lee@example.com'),
('박준호', '컴퓨터공학과', 19, 'park@example.com'),
('최수진', '영어영문학과', 22, 'choi@example.com');

-- 데이터 확인
SELECT * FROM students;
```

**실행 방법:**

```bash
mysql -u root -p < students.sql
```

### 01-5. 주의사항

- ✅ 각 SQL 명령어 끝에 세미콜론(`;`)을 반드시 입력해야 합니다
- ✅ 파일 인코딩을 UTF-8로 저장하는 것이 좋습니다
- ✅ 대용량 파일의 경우 실행 시간이 오래 걸릴 수 있습니다
- ✅ 실행 전에 파일 내용을 확인하는 것이 좋습니다

---

## 02 테이블에의 데이터 삽입

### 02-1. INSERT 문 기본 문법

**`INSERT INTO`** 문은 테이블에 새로운 데이터를 추가하는 명령어입니다.

#### 기본 문법

```sql
INSERT INTO 테이블명 (컬럼1, 컬럼2, 컬럼3, ...)
VALUES (값1, 값2, 값3, ...);
```

### 02-2. 단일 행 삽입

#### 예시 1: 모든 컬럼에 데이터 삽입

```sql
-- student 테이블 생성
CREATE TABLE student (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    age INT,
    email VARCHAR(100)
);

-- 데이터 삽입
INSERT INTO student (name, age, email)
VALUES ('홍길동', 20, 'hong@example.com');
```

#### 예시 2: 일부 컬럼만 지정하여 삽입

```sql
-- name과 age만 지정 (email은 NULL)
INSERT INTO student (name, age)
VALUES ('김영희', 25);
```

#### 예시 3: 컬럼 순서 생략 (모든 컬럼 값 지정)

```sql
-- 컬럼명 생략 시 모든 컬럼에 순서대로 값 삽입
INSERT INTO student
VALUES (NULL, '박철수', 22, 'park@example.com');
-- id는 AUTO_INCREMENT이므로 NULL 또는 생략 가능
```

### 02-3. 여러 행 한 번에 삽입

#### 방법 1: VALUES 여러 번 사용

```sql
INSERT INTO student (name, age, email) VALUES
('홍길동', 20, 'hong@example.com'),
('김영희', 25, 'kim@example.com'),
('박철수', 22, 'park@example.com');
```

#### 방법 2: SELECT 문 결과 삽입

```sql
-- 다른 테이블에서 데이터 가져오기
INSERT INTO student (name, age, email)
SELECT name, age, email
FROM temp_student
WHERE age > 20;
```

### 02-4. 다양한 데이터 타입 삽입 예시

```sql
-- 다양한 데이터 타입을 가진 테이블
CREATE TABLE product (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    price DECIMAL(10, 2),
    stock INT,
    created_date DATE,
    is_available BOOLEAN,
    description TEXT
);

-- 데이터 삽입
INSERT INTO product (name, price, stock, created_date, is_available, description)
VALUES 
('노트북', 1200000.00, 10, '2024-01-15', TRUE, '고성능 노트북입니다.'),
('마우스', 25000.50, 50, '2024-01-20', TRUE, '무선 마우스입니다.'),
('키보드', 89000.00, 30, '2024-01-25', FALSE, '기계식 키보드입니다.');
```

### 02-5. INSERT 시 주의사항

- ✅ 컬럼명과 값의 개수가 일치해야 합니다
- ✅ 데이터 타입이 일치해야 합니다
- ✅ NOT NULL 제약조건이 있는 컬럼은 반드시 값을 입력해야 합니다
- ✅ PRIMARY KEY나 UNIQUE 제약조건을 위반하면 오류가 발생합니다
- ✅ AUTO_INCREMENT 컬럼은 NULL을 입력하거나 생략할 수 있습니다

### 02-6. INSERT IGNORE와 REPLACE

#### INSERT IGNORE: 중복 시 무시

```sql
-- 중복된 키가 있어도 오류 없이 무시
INSERT IGNORE INTO student (id, name, age)
VALUES (1, '홍길동', 20);
```

#### REPLACE: 중복 시 교체

```sql
-- 중복된 키가 있으면 기존 데이터를 새 데이터로 교체
REPLACE INTO student (id, name, age)
VALUES (1, '홍길동', 25);
```

---

## 03 테이블 생성 후 데이터 삽입

### 03-1. 테이블 생성과 데이터 삽입 순서

1. **데이터베이스 생성 및 선택**
2. **테이블 생성**
3. **데이터 삽입**

### 03-2. 전체 예시

#### 단계별 실행

```sql
-- 1단계: 데이터베이스 생성
CREATE DATABASE IF NOT EXISTS company;
USE company;

-- 2단계: 테이블 생성
CREATE TABLE employee (
    emp_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    department VARCHAR(50),
    position VARCHAR(50),
    salary DECIMAL(10, 2),
    hire_date DATE
);

-- 3단계: 데이터 삽입
INSERT INTO employee (name, department, position, salary, hire_date) VALUES
('김철수', '개발팀', '시니어 개발자', 5000000, '2020-01-15'),
('이영희', '마케팅팀', '마케팅 매니저', 4500000, '2019-03-20'),
('박민수', '개발팀', '주니어 개발자', 3500000, '2021-06-01'),
('최지영', '인사팀', '인사 담당자', 4000000, '2020-09-10'),
('정대현', '영업팀', '영업 대표', 5500000, '2018-11-05');
```

### 03-3. 실전 예시: 도서관 관리 시스템

```sql
-- 데이터베이스 생성
CREATE DATABASE library;
USE library;

-- 도서 테이블 생성
CREATE TABLE book (
    book_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    author VARCHAR(100),
    publisher VARCHAR(100),
    isbn VARCHAR(20) UNIQUE,
    publish_date DATE,
    price DECIMAL(10, 2),
    stock INT DEFAULT 0
);

-- 회원 테이블 생성
CREATE TABLE member (
    member_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    phone VARCHAR(20),
    email VARCHAR(100),
    join_date DATE DEFAULT (CURRENT_DATE),
    status ENUM('활성', '비활성', '정지') DEFAULT '활성'
);

-- 대출 테이블 생성
CREATE TABLE loan (
    loan_id INT AUTO_INCREMENT PRIMARY KEY,
    member_id INT,
    book_id INT,
    loan_date DATE DEFAULT (CURRENT_DATE),
    return_date DATE,
    FOREIGN KEY (member_id) REFERENCES member(member_id),
    FOREIGN KEY (book_id) REFERENCES book(book_id)
);

-- 도서 데이터 삽입
INSERT INTO book (title, author, publisher, isbn, publish_date, price, stock) VALUES
('데이터베이스 개론', '김데이터', '한빛출판사', '978-89-1234-5678', '2023-01-15', 35000, 5),
('SQL 완전정복', '이쿼리', '정보문화사', '978-89-2345-6789', '2023-03-20', 28000, 3),
('MySQL 마스터하기', '박마이에스', '위키북스', '978-89-3456-7890', '2023-05-10', 32000, 7);

-- 회원 데이터 삽입
INSERT INTO member (name, phone, email, join_date, status) VALUES
('홍길동', '010-1234-5678', 'hong@example.com', '2023-01-10', '활성'),
('김영희', '010-2345-6789', 'kim@example.com', '2023-02-15', '활성'),
('박철수', '010-3456-7890', 'park@example.com', '2023-03-20', '활성');

-- 대출 데이터 삽입
INSERT INTO loan (member_id, book_id, loan_date, return_date) VALUES
(1, 1, '2024-01-15', NULL),
(2, 2, '2024-01-20', '2024-02-05'),
(1, 3, '2024-01-25', NULL);
```

### 03-4. 외래키(Foreign Key) 관계 설정

```sql
-- 부서 테이블
CREATE TABLE department (
    dept_id INT AUTO_INCREMENT PRIMARY KEY,
    dept_name VARCHAR(50) NOT NULL
);

-- 직원 테이블 (부서 테이블 참조)
CREATE TABLE employee (
    emp_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    dept_id INT,
    FOREIGN KEY (dept_id) REFERENCES department(dept_id)
);

-- 부서 데이터 먼저 삽입
INSERT INTO department (dept_name) VALUES
('개발팀'),
('마케팅팀'),
('인사팀');

-- 직원 데이터 삽입 (부서 ID 참조)
INSERT INTO employee (name, dept_id) VALUES
('김철수', 1),  -- 개발팀
('이영희', 2),  -- 마케팅팀
('박민수', 1);  -- 개발팀
```

### 03-5. 체크 제약조건(Check Constraint) 예시

```sql
CREATE TABLE product (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DECIMAL(10, 2) CHECK (price > 0),
    stock INT CHECK (stock >= 0),
    category VARCHAR(50) CHECK (category IN ('전자제품', '의류', '식품', '도서'))
);

-- 유효한 데이터 삽입
INSERT INTO product (name, price, stock, category) VALUES
('노트북', 1200000, 10, '전자제품');

-- 제약조건 위반 시 오류 발생
-- INSERT INTO product (name, price, stock, category) VALUES
-- ('마우스', -1000, 5, '전자제품');  -- price가 0보다 작아서 오류
```

---

## 04 데이터 검색과 관리

### 04-1. SELECT 문 기본

#### 기본 문법

```sql
SELECT 컬럼1, 컬럼2, ...
FROM 테이블명
WHERE 조건
ORDER BY 정렬컬럼;
```

#### 전체 데이터 조회

```sql
-- 모든 컬럼 조회
SELECT * FROM student;

-- 특정 컬럼만 조회
SELECT name, age FROM student;
```

### 04-2. WHERE 절을 이용한 조건 검색

#### 비교 연산자

```sql
-- 나이가 20세 이상인 학생
SELECT * FROM student WHERE age >= 20;

-- 이름이 '홍길동'인 학생
SELECT * FROM student WHERE name = '홍길동';

-- 나이가 20세와 25세 사이인 학생
SELECT * FROM student WHERE age BETWEEN 20 AND 25;

-- 전공이 '컴퓨터공학과' 또는 '경영학과'인 학생
SELECT * FROM student WHERE major IN ('컴퓨터공학과', '경영학과');
```

#### LIKE 연산자 (패턴 검색)

```sql
-- 이름에 '김'이 포함된 학생
SELECT * FROM student WHERE name LIKE '%김%';

-- '김'으로 시작하는 학생
SELECT * FROM student WHERE name LIKE '김%';

-- '수'로 끝나는 학생
SELECT * FROM student WHERE name LIKE '%수';

-- 이름이 3글자인 학생
SELECT * FROM student WHERE name LIKE '___';
```

#### NULL 값 검색

```sql
-- 이메일이 NULL인 학생
SELECT * FROM student WHERE email IS NULL;

-- 이메일이 NULL이 아닌 학생
SELECT * FROM student WHERE email IS NOT NULL;
```

### 04-3. ORDER BY 정렬

```sql
-- 나이 오름차순 정렬
SELECT * FROM student ORDER BY age ASC;

-- 나이 내림차순 정렬
SELECT * FROM student ORDER BY age DESC;

-- 여러 컬럼 정렬 (전공 오름차순, 나이 내림차순)
SELECT * FROM student ORDER BY major ASC, age DESC;
```

### 04-4. LIMIT으로 결과 제한

```sql
-- 상위 5개만 조회
SELECT * FROM student ORDER BY age DESC LIMIT 5;

-- 3번째부터 5개 조회 (OFFSET 사용)
SELECT * FROM student ORDER BY age DESC LIMIT 3, 5;
```

### 04-5. UPDATE 문으로 데이터 수정

#### 기본 문법

```sql
UPDATE 테이블명
SET 컬럼1 = 값1, 컬럼2 = 값2, ...
WHERE 조건;
```

#### 예시

```sql
-- 특정 학생의 나이 수정
UPDATE student SET age = 21 WHERE name = '홍길동';

-- 여러 컬럼 동시 수정
UPDATE student 
SET age = 22, email = 'newemail@example.com' 
WHERE student_id = 1;

-- 조건에 맞는 여러 행 수정
UPDATE student 
SET major = '컴퓨터공학과' 
WHERE major = '정보통신공학과';
```

#### 주의사항

```sql
-- ⚠️ 위험: WHERE 절 없이 실행하면 모든 행이 수정됨
-- UPDATE student SET age = 20;  -- 모든 학생의 나이가 20으로 변경됨

-- 안전한 방법: WHERE 절 반드시 포함
UPDATE student SET age = 20 WHERE student_id = 1;
```

### 04-6. DELETE 문으로 데이터 삭제

#### 기본 문법

```sql
DELETE FROM 테이블명
WHERE 조건;
```

#### 예시

```sql
-- 특정 학생 삭제
DELETE FROM student WHERE student_id = 1;

-- 조건에 맞는 여러 행 삭제
DELETE FROM student WHERE age < 18;

-- 모든 데이터 삭제 (주의!)
-- DELETE FROM student;  -- WHERE 절 없으면 모든 데이터 삭제
```

#### TRUNCATE와 DELETE 차이

```sql
-- DELETE: 조건부 삭제 가능, 롤백 가능, 느림
DELETE FROM student WHERE age < 18;

-- TRUNCATE: 모든 데이터 삭제, 빠름, 롤백 불가
TRUNCATE TABLE student;
```

### 04-7. 집계 함수 사용

```sql
-- 학생 수
SELECT COUNT(*) FROM student;

-- 평균 나이
SELECT AVG(age) FROM student;

-- 최고 나이
SELECT MAX(age) FROM student;

-- 최저 나이
SELECT MIN(age) FROM student;

-- 나이 합계
SELECT SUM(age) FROM student;
```

### 04-8. GROUP BY 그룹화

```sql
-- 전공별 학생 수
SELECT major, COUNT(*) as 학생수
FROM student
GROUP BY major;

-- 전공별 평균 나이
SELECT major, AVG(age) as 평균나이
FROM student
GROUP BY major;

-- 전공별 평균 나이가 20세 이상인 그룹만
SELECT major, AVG(age) as 평균나이
FROM student
GROUP BY major
HAVING AVG(age) >= 20;
```

### 04-9. JOIN을 이용한 테이블 결합

#### INNER JOIN

```sql
-- 학생과 성적 테이블 조인
SELECT s.name, s.major, g.subject, g.score
FROM student s
INNER JOIN grade g ON s.student_id = g.student_id;
```

#### LEFT JOIN

```sql
-- 모든 학생과 그들의 성적 (성적이 없는 학생도 포함)
SELECT s.name, s.major, g.subject, g.score
FROM student s
LEFT JOIN grade g ON s.student_id = g.student_id;
```

#### 여러 테이블 조인

```sql
-- 학생, 과목, 성적 테이블 조인
SELECT s.name, sub.subject_name, g.score
FROM student s
INNER JOIN grade g ON s.student_id = g.student_id
INNER JOIN subject sub ON g.subject_id = sub.subject_id;
```

---

## 05 MySQL 사용자 계정 생성

### 05-1. 사용자 계정 생성

#### 기본 문법

```sql
CREATE USER '사용자명'@'호스트' IDENTIFIED BY '비밀번호';
```

#### 예시

```sql
-- 로컬에서만 접속 가능한 사용자
CREATE USER 'testuser'@'localhost' IDENTIFIED BY 'password123';

-- 모든 호스트에서 접속 가능한 사용자
CREATE USER 'testuser'@'%' IDENTIFIED BY 'password123';

-- 특정 IP에서만 접속 가능한 사용자
CREATE USER 'testuser'@'192.168.1.100' IDENTIFIED BY 'password123';
```

### 05-2. 사용자 권한 부여

#### GRANT 문 기본 문법

```sql
GRANT 권한1, 권한2, ... ON 데이터베이스.테이블 TO '사용자명'@'호스트';
```

#### 권한 종류

- **ALL PRIVILEGES**: 모든 권한
- **SELECT**: 데이터 조회
- **INSERT**: 데이터 삽입
- **UPDATE**: 데이터 수정
- **DELETE**: 데이터 삭제
- **CREATE**: 테이블 생성
- **DROP**: 테이블 삭제
- **ALTER**: 테이블 구조 변경

#### 예시

```sql
-- 모든 데이터베이스의 모든 권한 부여
GRANT ALL PRIVILEGES ON *.* TO 'testuser'@'localhost';

-- 특정 데이터베이스의 모든 권한 부여
GRANT ALL PRIVILEGES ON school.* TO 'testuser'@'localhost';

-- 특정 데이터베이스의 특정 권한만 부여
GRANT SELECT, INSERT, UPDATE ON school.* TO 'testuser'@'localhost';

-- 특정 테이블에 대한 권한 부여
GRANT SELECT, INSERT ON school.student TO 'testuser'@'localhost';
```

### 05-3. 권한 적용 및 확인

#### 권한 적용

```sql
-- 권한 변경사항 즉시 적용
FLUSH PRIVILEGES;
```

#### 현재 사용자 권한 확인

```sql
-- 현재 사용자의 권한 확인
SHOW GRANTS FOR 'testuser'@'localhost';

-- 현재 로그인한 사용자의 권한 확인
SHOW GRANTS;
```

### 05-4. 사용자 권한 회수

#### REVOKE 문

```sql
-- 특정 권한 회수
REVOKE SELECT ON school.* FROM 'testuser'@'localhost';

-- 모든 권한 회수
REVOKE ALL PRIVILEGES ON *.* FROM 'testuser'@'localhost';

-- 권한 변경사항 적용
FLUSH PRIVILEGES;
```

### 05-5. 사용자 비밀번호 변경

```sql
-- 방법 1: ALTER USER 사용 (MySQL 5.7.6 이상)
ALTER USER 'testuser'@'localhost' IDENTIFIED BY 'newpassword123';

-- 방법 2: SET PASSWORD 사용
SET PASSWORD FOR 'testuser'@'localhost' = PASSWORD('newpassword123');

-- 권한 적용
FLUSH PRIVILEGES;
```

### 05-6. 사용자 삭제

```sql
-- 사용자 삭제
DROP USER 'testuser'@'localhost';

-- 여러 사용자 동시 삭제
DROP USER 'user1'@'localhost', 'user2'@'localhost';
```

### 05-7. 사용자 목록 확인

```sql
-- 모든 사용자 목록 확인
SELECT user, host FROM mysql.user;

-- 특정 사용자 확인
SELECT user, host FROM mysql.user WHERE user = 'testuser';
```

### 05-8. 실전 예시: 웹 애플리케이션용 사용자 생성

```sql
-- 웹 애플리케이션 전용 사용자 생성
CREATE USER 'webapp'@'localhost' IDENTIFIED BY 'SecurePassword123!';

-- 데이터베이스 생성
CREATE DATABASE webapp_db;

-- 웹 애플리케이션 데이터베이스에 필요한 권한만 부여
GRANT SELECT, INSERT, UPDATE, DELETE ON webapp_db.* TO 'webapp'@'localhost';

-- 권한 적용
FLUSH PRIVILEGES;

-- 권한 확인
SHOW GRANTS FOR 'webapp'@'localhost';
```

---

## 06 데이터베이스 백업과 복원

### 06-1. mysqldump를 이용한 백업

#### mysqldump란?

**mysqldump**는 MySQL 데이터베이스를 백업하는 유틸리티입니다.

- 데이터베이스 구조와 데이터를 SQL 파일로 저장합니다
- 텍스트 형식으로 저장되어 편집이 가능합니다
- 특정 데이터베이스나 테이블만 선택적으로 백업할 수 있습니다

#### 기본 문법

```bash
mysqldump -u 사용자명 -p 데이터베이스명 > 백업파일명.sql
```

### 06-2. 전체 데이터베이스 백업

```bash
# 단일 데이터베이스 백업
mysqldump -u root -p school > school_backup.sql

# 모든 데이터베이스 백업
mysqldump -u root -p --all-databases > all_databases_backup.sql

# 특정 데이터베이스들만 백업
mysqldump -u root -p --databases school company > databases_backup.sql
```

### 06-3. 특정 테이블만 백업

```bash
# 특정 테이블만 백업
mysqldump -u root -p school student grade > tables_backup.sql

# 여러 테이블 백업
mysqldump -u root -p school student grade course > multiple_tables_backup.sql
```

### 06-4. 백업 옵션

#### 구조만 백업 (데이터 제외)

```bash
mysqldump -u root -p --no-data school > school_structure_only.sql
```

#### 데이터만 백업 (구조 제외)

```bash
mysqldump -u root -p --no-create-info school > school_data_only.sql
```

#### 추가 옵션

```bash
# 락 없이 백업 (서비스 중단 없이 백업)
mysqldump -u root -p --single-transaction school > school_backup.sql

# 특정 조건의 데이터만 백업
mysqldump -u root -p school student --where="age > 20" > filtered_backup.sql

# 압축하여 백업
mysqldump -u root -p school | gzip > school_backup.sql.gz
```

### 06-5. 데이터베이스 복원

#### 방법 1: 명령줄에서 복원

```bash
# 백업 파일로부터 복원
mysql -u root -p school < school_backup.sql

# 모든 데이터베이스 복원
mysql -u root -p < all_databases_backup.sql
```

#### 방법 2: MySQL 접속 후 복원

```sql
-- 데이터베이스 선택
USE school;

-- 백업 파일 실행
SOURCE school_backup.sql;
-- 또는
\. school_backup.sql
```

#### 방법 3: 압축 파일 복원

```bash
# 압축된 백업 파일 복원
gunzip < school_backup.sql.gz | mysql -u root -p school
```

### 06-6. 백업 전략

#### 1. 전체 백업 (Full Backup)

```bash
# 매일 전체 백업
mysqldump -u root -p --all-databases > backup_$(date +%Y%m%d).sql
```

#### 2. 증분 백업 (Incremental Backup)

```bash
# 바이너리 로그 활성화
# my.cnf 파일에 추가:
# log-bin=mysql-bin

# 특정 시점 이후의 변경사항만 백업
mysqlbinlog mysql-bin.000001 > incremental_backup.sql
```

#### 3. 자동화 스크립트 예시 (Linux)

```bash
#!/bin/bash
# backup.sh

BACKUP_DIR="/backup/mysql"
DATE=$(date +%Y%m%d_%H%M%S)
DB_NAME="school"
DB_USER="root"

# 백업 디렉토리 생성
mkdir -p $BACKUP_DIR

# 백업 실행
mysqldump -u $DB_USER -p$DB_PASS $DB_NAME > $BACKUP_DIR/${DB_NAME}_${DATE}.sql

# 7일 이상 된 백업 파일 삭제
find $BACKUP_DIR -name "*.sql" -mtime +7 -delete

echo "Backup completed: ${DB_NAME}_${DATE}.sql"
```

### 06-7. 복원 전 확인사항

1. ✅ 복원할 데이터베이스가 존재하는지 확인
2. ✅ 백업 파일이 손상되지 않았는지 확인
3. ✅ 충분한 디스크 공간이 있는지 확인
4. ✅ 복원 전에 현재 데이터를 백업할지 결정

### 06-8. 복원 예시

```sql
-- 1단계: 데이터베이스 생성 (없는 경우)
CREATE DATABASE IF NOT EXISTS school;

-- 2단계: 데이터베이스 선택
USE school;

-- 3단계: 백업 파일 복원
SOURCE school_backup.sql;
```

### 06-9. 주의사항

- ⚠️ **복원 시 기존 데이터가 덮어씌워질 수 있습니다**
- ⚠️ **복원 전에 반드시 현재 데이터를 백업하세요**
- ⚠️ **대용량 데이터베이스는 복원 시간이 오래 걸릴 수 있습니다**
- ⚠️ **복원 중에는 데이터베이스 접근을 제한하는 것이 좋습니다**

### 06-10. 실전 예시: 백업 및 복원 시나리오

```bash
# 시나리오: school 데이터베이스 백업 및 복원

# 1. 백업 실행
mysqldump -u root -p school > school_backup_20240129.sql

# 2. 백업 파일 확인
head -20 school_backup_20240129.sql

# 3. 실수로 데이터 삭제된 경우 복원
mysql -u root -p school < school_backup_20240129.sql

# 4. 다른 서버로 데이터베이스 이전
# 원본 서버에서 백업
mysqldump -u root -p school > school_backup.sql

# 대상 서버에서 복원
mysql -u root -p school < school_backup.sql
```

---

## 참고사항

### SQL 명령어 체크리스트

- ✅ 모든 SQL 명령어 끝에 세미콜론(`;`) 입력
- ✅ 문자열은 작은따옴표(`'`) 또는 큰따옴표(`"`) 사용
- ✅ 테이블명과 컬럼명은 대소문자 구분 (설정에 따라 다름)
- ✅ 예약어는 백틱(`)으로 감싸기 (필요시)

### 보안 주의사항

- 🔒 **root 계정 비밀번호는 강력하게 설정**
- 🔒 **애플리케이션용 사용자는 최소 권한만 부여**
- 🔒 **백업 파일은 안전한 곳에 보관**
- 🔒 **SQL 인젝션 공격 방지를 위해 입력값 검증 필수**

### 성능 최적화 팁

- ⚡ **인덱스(INDEX)를 적절히 사용하여 검색 속도 향상**
- ⚡ **대량 데이터 삽입 시 여러 행을 한 번에 삽입**
- ⚡ **불필요한 데이터는 정기적으로 삭제**
- ⚡ **정기적인 백업으로 데이터 손실 방지**
