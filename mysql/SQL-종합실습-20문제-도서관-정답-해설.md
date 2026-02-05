# SQL 종합 실습 문제 정답 및 해설 - 도서관 시스템

## 문제 1: 데이터베이스 생성 (5점)

**문제:** `library_db`라는 이름의 데이터베이스를 생성하시오. (UTF-8 설정 포함)

**정답:**
```sql
CREATE DATABASE library_db 
CHARACTER SET utf8mb4 
COLLATE utf8mb4_unicode_ci;
```

**해설:**
- `CREATE DATABASE`: 데이터베이스 생성 명령어
- `CHARACTER SET utf8mb4`: UTF-8 문자셋 설정 (한글 지원)
- `COLLATE utf8mb4_unicode_ci`: 정렬 규칙 설정
- 한글 데이터를 올바르게 저장하기 위해 UTF-8 설정 필수

---

## 문제 2: 테이블 생성 - 회원 테이블 (5점)

**문제:** 다음 조건에 맞는 `member` 테이블을 생성하시오.

**정답:**
```sql
USE library_db;

CREATE TABLE member (
    member_id INT PRIMARY KEY AUTO_INCREMENT,
    member_no CHAR(20) UNIQUE NOT NULL,
    name VARCHAR(50) NOT NULL,
    phone CHAR(20) UNIQUE,
    email VARCHAR(100),
    join_date DATE DEFAULT (CURRENT_DATE),
    status VARCHAR(20) DEFAULT '활성'
) CHARACTER SET utf8mb4 
  COLLATE utf8mb4_unicode_ci;
```

**해설:**
- `PRIMARY KEY`: 기본키 설정
- `UNIQUE`: 고유값 제약조건 (member_no, phone)
- `NOT NULL`: NULL 불가 제약조건
- `DEFAULT (CURRENT_DATE)`: 기본값을 현재 날짜로 설정
- `DEFAULT '활성'`: 기본값 설정

**주의사항:**
- MySQL 버전에 따라 `CURRENT_DATE` 대신 `CURDATE()` 사용 가능
- `DEFAULT (CURRENT_DATE)`는 MySQL 8.0.13 이상에서 지원

---

## 문제 3: 테이블 생성 - 도서 테이블 (5점)

**문제:** 다음 조건에 맞는 `book` 테이블을 생성하시오.

**정답:**
```sql
CREATE TABLE book (
    book_id INT PRIMARY KEY AUTO_INCREMENT,
    isbn CHAR(20) UNIQUE NOT NULL,
    title VARCHAR(200) NOT NULL,
    author VARCHAR(100),
    publisher VARCHAR(100),
    category VARCHAR(50),
    total_copies INT DEFAULT 1,
    available_copies INT DEFAULT 1
) CHARACTER SET utf8mb4 
  COLLATE utf8mb4_unicode_ci;
```

**해설:**
- `isbn`: 도서 고유 번호, UNIQUE 제약조건
- `title`: 도서 제목, NOT NULL
- `total_copies`: 전체 권수
- `available_copies`: 대출 가능 권수
- 기본값 설정으로 초기값 자동 입력

---

## 문제 4: 테이블 생성 - 대출 테이블 (10점)

**문제:** 다음 조건에 맞는 `loan` 테이블을 생성하시오.

**정답:**
```sql
CREATE TABLE loan (
    loan_id INT PRIMARY KEY AUTO_INCREMENT,
    member_no CHAR(20),
    isbn CHAR(20),
    loan_date DATE DEFAULT (CURRENT_DATE),
    return_date DATE,
    due_date DATE NOT NULL,
    status VARCHAR(20) DEFAULT '대출중',
    FOREIGN KEY (member_no) REFERENCES member(member_no),
    FOREIGN KEY (isbn) REFERENCES book(isbn)
) CHARACTER SET utf8mb4 
  COLLATE utf8mb4_unicode_ci;
```

**해설:**
- 두 개의 외래키 제약조건 설정
- `member_no`: member 테이블 참조
- `isbn`: book 테이블 참조
- `due_date`: 반납 예정일, NOT NULL
- `status`: 대출 상태, 기본값 '대출중'

---

## 문제 5: 데이터 삽입 - 회원 데이터 (5점)

**문제:** 다음 데이터를 `member` 테이블에 삽입하시오.

**정답:**
```sql
INSERT INTO member (member_no, name, phone, email, join_date) VALUES
('M001', '홍길동', '010-1111-2222', 'hong@email.com', '2020-01-10'),
('M002', '김영희', '010-2222-3333', 'kim@email.com', '2021-03-15'),
('M003', '이철수', '010-3333-4444', 'lee@email.com', '2022-05-20'),
('M004', '박민지', '010-4444-5555', 'park@email.com', '2021-07-08'),
('M005', '최수진', '010-5555-6666', 'choi@email.com', '2023-02-14');
```

**해설:**
- `join_date`와 `status`는 기본값이 있지만 명시적으로 입력 가능
- `phone`은 UNIQUE 제약조건이므로 중복 불가
- 여러 행을 한 번에 삽입 가능

**데이터 확인:**
```sql
SELECT * FROM member;
```

---

## 문제 6: 데이터 삽입 - 도서 데이터 (5점)

**문제:** 다음 데이터를 `book` 테이블에 삽입하시오.

**정답:**
```sql
INSERT INTO book (isbn, title, author, publisher, category, total_copies, available_copies) VALUES
('ISBN001', '자바의 정석', '남궁성', '도우출판', '프로그래밍', 5, 3),
('ISBN002', '이것이 자바다', '신용권', '한빛미디어', '프로그래밍', 3, 2),
('ISBN003', '해리포터', 'J.K.롤링', '문학수첩', '소설', 10, 5),
('ISBN004', '데이터베이스 개론', '이상호', '정익사', '컴퓨터', 4, 4),
('ISBN005', '알고리즘 문제해결', '구종만', '인사이트', '프로그래밍', 2, 1);
```

**해설:**
- `isbn`은 UNIQUE 제약조건이므로 중복 불가
- `total_copies`와 `available_copies`의 차이: 대출된 권수
- 예: ISBN001은 전체 5권 중 3권 대출 가능 (2권 대출 중)

**데이터 확인:**
```sql
SELECT * FROM book;
```

---

## 문제 7: 데이터 삽입 - 대출 데이터 (5점)

**문제:** 다음 데이터를 `loan` 테이블에 삽입하시오.

**정답:**
```sql
INSERT INTO loan (member_no, isbn, loan_date, return_date, due_date, status) VALUES
('M001', 'ISBN001', '2024-01-15', NULL, '2024-02-15', '대출중'),
('M002', 'ISBN002', '2024-02-01', NULL, '2024-03-01', '대출중'),
('M001', 'ISBN003', '2024-01-20', '2024-02-10', '2024-02-20', '반납완료'),
('M003', 'ISBN004', '2024-03-10', NULL, '2024-04-10', '대출중'),
('M004', 'ISBN005', '2024-02-25', NULL, '2024-03-25', '대출중');
```

**해설:**
- 외래키 제약조건으로 member와 book 테이블에 존재하는 값만 입력 가능
- `return_date`가 NULL이면 아직 반납하지 않은 상태
- `due_date`는 NOT NULL이므로 반드시 입력 필요

**데이터 확인:**
```sql
SELECT * FROM loan;
```

---

## 문제 8: 데이터 수정 - 회원 상태 변경 (5점)

**문제:** 회원번호가 'M005'인 회원의 상태를 '휴면'으로 변경하시오.

**정답:**
```sql
UPDATE member 
SET status = '휴면'
WHERE member_no = 'M005';
```

**해설:**
- `UPDATE`: 데이터 수정 명령어
- `SET status = '휴면'`: 상태 변경
- `WHERE member_no = 'M005'`: 특정 회원만 선택
- WHERE 절 필수 (없으면 모든 회원의 상태가 변경됨)

**확인:**
```sql
SELECT member_no, name, status FROM member WHERE member_no = 'M005';
```

---

## 문제 9: 데이터 수정 - 도서 재고 업데이트 (5점)

**문제:** ISBN이 'ISBN001'인 도서의 대출 가능 권수를 1권 증가시키시오.

**정답:**
```sql
UPDATE book 
SET available_copies = available_copies + 1
WHERE isbn = 'ISBN001';
```

**해설:**
- `SET available_copies = available_copies + 1`: 현재 값에 1 더하기
- 계산식을 사용하여 값 증가/감소 가능
- 반납 처리 시 사용되는 패턴

**확인:**
```sql
SELECT isbn, title, available_copies FROM book WHERE isbn = 'ISBN001';
```

**결과:**
- 수정 전: available_copies = 3
- 수정 후: available_copies = 4

---

## 문제 10: 데이터 수정 - 대출 반납 처리 (5점)

**문제:** 대출ID가 4인 대출의 반납일을 오늘 날짜로, 상태를 '반납완료'로 변경하시오.

**정답:**
```sql
UPDATE loan 
SET return_date = CURRENT_DATE,
    status = '반납완료'
WHERE loan_id = 4;
```

**또는:**
```sql
UPDATE loan 
SET return_date = CURDATE(),
    status = '반납완료'
WHERE loan_id = 4;
```

**해설:**
- 여러 컬럼을 동시에 수정 가능 (쉼표로 구분)
- `CURRENT_DATE` 또는 `CURDATE()`: 현재 날짜
- 반납 처리 시 일반적으로 사용되는 패턴

**확인:**
```sql
SELECT * FROM loan WHERE loan_id = 4;
```

---

## 문제 11: 데이터 삭제 - 특정 조건 (5점)

**문제:** 상태가 '휴면'인 회원의 레코드를 삭제하시오.

**정답:**
```sql
DELETE FROM member 
WHERE status = '휴면';
```

**해설:**
- `DELETE FROM`: 데이터 삭제 명령어
- `WHERE status = '휴면'`: 조건 지정
- **주의:** WHERE 절을 생략하면 모든 레코드가 삭제됨!
- 삭제 전 확인 권장

**삭제 전 확인:**
```sql
SELECT * FROM member WHERE status = '휴면';
```

**주의사항:**
- 외래키 제약조건이 있으면 참조하는 데이터가 있을 때 삭제 불가
- loan 테이블에서 해당 회원을 참조하는 대출 기록이 있으면 삭제 불가

---

## 문제 12: 데이터 삭제 - 특정 레코드 (5점)

**문제:** 회원번호가 'M005'인 회원의 레코드를 삭제하시오.

**정답:**
```sql
DELETE FROM member 
WHERE member_no = 'M005';
```

**해설:**
- 특정 회원만 삭제
- 외래키 제약조건 확인 필요

**주의사항:**
- loan 테이블에서 M005를 참조하는 대출 기록이 있으면 삭제 불가
- 먼저 참조하는 데이터를 삭제하거나 외래키 제약조건을 확인해야 함

**확인:**
```sql
-- 참조하는 대출 기록 확인
SELECT * FROM loan WHERE member_no = 'M005';

-- 참조하는 대출 기록이 없으면 삭제 가능
DELETE FROM member WHERE member_no = 'M005';
```

---

## 문제 13: 집계함수 - 전체 도서 수 (5점)

**문제:** 전체 도서 종류 수를 구하시오.

**정답:**
```sql
SELECT COUNT(*) AS 도서종류수
FROM book;
```

**해설:**
- `COUNT(*)`: 모든 행의 개수 반환
- `AS 도서종류수`: 컬럼 별칭 지정
- 도서 종류 수를 구하는 것이므로 COUNT 사용

**결과:**
```
도서종류수
5
```

---

## 문제 14: 집계함수 - 카테고리별 도서 수 (5점)

**문제:** 각 카테고리별 도서 종류 수를 구하시오.

**정답:**
```sql
SELECT 
    category AS 카테고리,
    COUNT(*) AS 도서수
FROM book
GROUP BY category
ORDER BY 도서수 DESC;
```

**해설:**
- `GROUP BY category`: 카테고리별로 그룹화
- `COUNT(*)`: 각 그룹의 도서 수 계산
- `ORDER BY 도서수 DESC`: 도서 수가 많은 순으로 정렬

**결과:**
```
카테고리      | 도서수
-------------|-------
프로그래밍   | 3
소설         | 1
컴퓨터       | 1
```

---

## 문제 15: 집계함수 - 현재 대출 중인 도서 수 (5점)

**문제:** 현재 대출 중인 도서 수를 구하시오. (status가 '대출중'인 레코드 수)

**정답:**
```sql
SELECT COUNT(*) AS 대출중인도서수
FROM loan
WHERE status = '대출중';
```

**해설:**
- `COUNT(*)`: 행의 개수 반환
- `WHERE status = '대출중'`: 조건 필터링
- 집계함수와 WHERE 절 함께 사용

**결과:**
```
대출중인도서수
4
```

---

## 문제 16: 집계함수 - 회원별 대출 횟수 (5점)

**문제:** 각 회원별 대출 횟수를 구하시오. (회원번호, 이름, 대출횟수)

**정답:**
```sql
SELECT 
    m.member_no AS 회원번호,
    m.name AS 이름,
    COUNT(l.loan_id) AS 대출횟수
FROM member m
LEFT JOIN loan l ON m.member_no = l.member_no
GROUP BY m.member_no, m.name
ORDER BY 대출횟수 DESC;
```

**해설:**
- `LEFT JOIN`: 모든 회원 포함 (대출하지 않은 회원도 포함)
- `COUNT(l.loan_id)`: 대출 횟수 계산 (NULL은 제외)
- `GROUP BY`: 회원별로 그룹화
- JOIN과 집계함수 함께 사용

**결과:**
```
회원번호 | 이름   | 대출횟수
---------|--------|----------
M001     | 홍길동 | 2
M002     | 김영희 | 1
M003     | 이철수 | 1
M004     | 박민지 | 1
M005     | 최수진 | 0
```

---

## 문제 17: 집계함수 - HAVING 사용 (5점)

**문제:** 대출 횟수가 2회 이상인 회원의 회원번호와 대출 횟수를 구하시오.

**정답:**
```sql
SELECT 
    m.member_no AS 회원번호,
    COUNT(l.loan_id) AS 대출횟수
FROM member m
LEFT JOIN loan l ON m.member_no = l.member_no
GROUP BY m.member_no
HAVING COUNT(l.loan_id) >= 2
ORDER BY 대출횟수 DESC;
```

**해설:**
- `GROUP BY`: 회원별로 그룹화
- `HAVING COUNT(l.loan_id) >= 2`: 그룹화된 결과에 조건 적용
- **HAVING vs WHERE:**
  - `WHERE`: 그룹화 전 조건
  - `HAVING`: 그룹화 후 조건 (집계함수 사용 가능)

**결과:**
```
회원번호 | 대출횟수
---------|----------
M001     | 2
```

---

## 문제 18: INNER JOIN - 기본 조인 (5점)

**문제:** 대출 중인 도서의 회원 이름과 도서 제목을 조회하시오.

**정답:**
```sql
SELECT 
    m.name AS 회원이름,
    b.title AS 도서제목,
    l.loan_date AS 대출일,
    l.due_date AS 반납예정일
FROM loan l
INNER JOIN member m ON l.member_no = m.member_no
INNER JOIN book b ON l.isbn = b.isbn
WHERE l.status = '대출중'
ORDER BY l.loan_date DESC;
```

**해설:**
- `INNER JOIN`: 두 테이블의 교집합 (매칭되는 데이터만)
- 3개 테이블 조인 (loan, member, book)
- `WHERE l.status = '대출중'`: 대출 중인 것만 필터링
- 각 조인마다 조건 지정 필요

**결과:**
```
회원이름 | 도서제목              | 대출일      | 반납예정일
---------|---------------------|-------------|------------
이철수   | 데이터베이스 개론   | 2024-03-10  | 2024-04-10
박민지   | 알고리즘 문제해결   | 2024-02-25  | 2024-03-25
김영희   | 이것이 자바다       | 2024-02-01  | 2024-03-01
홍길동   | 자바의 정석         | 2024-01-15  | 2024-02-15
```

---

## 문제 19: INNER JOIN - 조건부 조인 (10점)

**문제:** 프로그래밍 카테고리 도서를 대출한 회원의 이름과 도서 제목을 조회하시오.

**정답:**
```sql
SELECT 
    m.name AS 회원이름,
    b.title AS 도서제목,
    b.category AS 카테고리
FROM loan l
INNER JOIN member m ON l.member_no = m.member_no
INNER JOIN book b ON l.isbn = b.isbn
WHERE b.category = '프로그래밍'
ORDER BY m.name, b.title;
```

**해설:**
- `INNER JOIN`: 3개 테이블 조인
- `WHERE b.category = '프로그래밍'`: 프로그래밍 카테고리만 필터링
- JOIN 후 WHERE 절로 조건 적용

**결과:**
```
회원이름 | 도서제목              | 카테고리
---------|---------------------|----------
김영희   | 이것이 자바다       | 프로그래밍
박민지   | 알고리즘 문제해결   | 프로그래밍
홍길동   | 자바의 정석         | 프로그래밍
```

---

## 문제 20: LEFT JOIN - 매칭되지 않는 데이터 찾기 (5점)

**문제:** 한 번도 도서를 대출하지 않은 회원을 찾아 출력하시오.

**정답:**
```sql
SELECT 
    m.member_no AS 회원번호,
    m.name AS 회원이름,
    m.join_date AS 가입일
FROM member m
LEFT JOIN loan l ON m.member_no = l.member_no
WHERE l.member_no IS NULL;
```

**해설:**
- `LEFT JOIN`: 모든 회원 포함
- `WHERE l.member_no IS NULL`: 대출 테이블에 매칭되지 않는 회원
- 즉, 한 번도 대출하지 않은 회원 찾기
- **IS NULL 사용**: 등호(`=`) 사용 불가

**결과:**
```
회원번호 | 회원이름 | 가입일
---------|----------|------------
M005     | 최수진   | 2023-02-14
```

**설명:**
- LEFT JOIN 후 WHERE 절로 NULL 값 필터링
- 데이터 정합성 확인에 유용
- 실전에서 자주 사용되는 패턴

---

## 전체 실행 순서

### 1. 데이터베이스 및 테이블 생성

```sql
-- 데이터베이스 생성
CREATE DATABASE library_db 
CHARACTER SET utf8mb4 
COLLATE utf8mb4_unicode_ci;

USE library_db;

-- 회원 테이블 생성
CREATE TABLE member (
    member_id INT PRIMARY KEY AUTO_INCREMENT,
    member_no CHAR(20) UNIQUE NOT NULL,
    name VARCHAR(50) NOT NULL,
    phone CHAR(20) UNIQUE,
    email VARCHAR(100),
    join_date DATE DEFAULT (CURRENT_DATE),
    status VARCHAR(20) DEFAULT '활성'
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 도서 테이블 생성
CREATE TABLE book (
    book_id INT PRIMARY KEY AUTO_INCREMENT,
    isbn CHAR(20) UNIQUE NOT NULL,
    title VARCHAR(200) NOT NULL,
    author VARCHAR(100),
    publisher VARCHAR(100),
    category VARCHAR(50),
    total_copies INT DEFAULT 1,
    available_copies INT DEFAULT 1
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 대출 테이블 생성
CREATE TABLE loan (
    loan_id INT PRIMARY KEY AUTO_INCREMENT,
    member_no CHAR(20),
    isbn CHAR(20),
    loan_date DATE DEFAULT (CURRENT_DATE),
    return_date DATE,
    due_date DATE NOT NULL,
    status VARCHAR(20) DEFAULT '대출중',
    FOREIGN KEY (member_no) REFERENCES member(member_no),
    FOREIGN KEY (isbn) REFERENCES book(isbn)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### 2. 데이터 삽입

```sql
-- 회원 데이터 삽입
INSERT INTO member (member_no, name, phone, email, join_date) VALUES
('M001', '홍길동', '010-1111-2222', 'hong@email.com', '2020-01-10'),
('M002', '김영희', '010-2222-3333', 'kim@email.com', '2021-03-15'),
('M003', '이철수', '010-3333-4444', 'lee@email.com', '2022-05-20'),
('M004', '박민지', '010-4444-5555', 'park@email.com', '2021-07-08'),
('M005', '최수진', '010-5555-6666', 'choi@email.com', '2023-02-14');

-- 도서 데이터 삽입
INSERT INTO book (isbn, title, author, publisher, category, total_copies, available_copies) VALUES
('ISBN001', '자바의 정석', '남궁성', '도우출판', '프로그래밍', 5, 3),
('ISBN002', '이것이 자바다', '신용권', '한빛미디어', '프로그래밍', 3, 2),
('ISBN003', '해리포터', 'J.K.롤링', '문학수첩', '소설', 10, 5),
('ISBN004', '데이터베이스 개론', '이상호', '정익사', '컴퓨터', 4, 4),
('ISBN005', '알고리즘 문제해결', '구종만', '인사이트', '프로그래밍', 2, 1);

-- 대출 데이터 삽입
INSERT INTO loan (member_no, isbn, loan_date, return_date, due_date, status) VALUES
('M001', 'ISBN001', '2024-01-15', NULL, '2024-02-15', '대출중'),
('M002', 'ISBN002', '2024-02-01', NULL, '2024-03-01', '대출중'),
('M001', 'ISBN003', '2024-01-20', '2024-02-10', '2024-02-20', '반납완료'),
('M003', 'ISBN004', '2024-03-10', NULL, '2024-04-10', '대출중'),
('M004', 'ISBN005', '2024-02-25', NULL, '2024-03-25', '대출중');
```

---

## 문제 유형별 요약

### 데이터베이스/테이블 생성 (문제 1-4)
- CREATE DATABASE
- CREATE TABLE (3개 테이블)
- 제약조건 (PRIMARY KEY, FOREIGN KEY, UNIQUE, NOT NULL, DEFAULT)
- 외래키 제약조건 (2개)

### 데이터 삽입 (문제 5-7)
- INSERT INTO ... VALUES
- 여러 행 한 번에 삽입
- 외래키 제약조건 확인

### 데이터 수정 (문제 8-10)
- UPDATE ... SET ... WHERE
- 여러 컬럼 동시 수정
- 계산식 사용 (증가/감소)
- 날짜 함수 사용

### 데이터 삭제 (문제 11-12)
- DELETE FROM ... WHERE
- 조건부 삭제
- 외래키 제약조건 주의

### 집계함수 (문제 13-17)
- COUNT
- GROUP BY
- HAVING
- JOIN과 집계함수 조합

### JOIN (문제 18-20)
- INNER JOIN (3개 테이블)
- LEFT JOIN
- 조건부 JOIN
- 매칭되지 않는 데이터 찾기

---

## 기존 문제와의 차이점 (변별력)

### 1. 도메인 차이
- **기존**: 회사/직원/프로젝트
- **새로운**: 도서관/회원/도서/대출

### 2. 테이블 구조 차이
- **기존**: 2개 테이블 (employee, project)
- **새로운**: 3개 테이블 (member, book, loan)

### 3. 제약조건 차이
- **기존**: 기본적인 제약조건
- **새로운**: UNIQUE 제약조건 추가 (phone), DEFAULT 값 다양

### 4. 문제 유형 차이
- **기존**: 기본적인 CRUD와 JOIN
- **새로운**: 3개 테이블 JOIN, 재고 관리 개념, 대출/반납 처리

### 5. 실전성 차이
- **기존**: 회사 관리 시스템
- **새로운**: 도서관 시스템 (실제 사용되는 시스템)

---

## 체크리스트

- [ ] 데이터베이스 생성 (UTF-8 설정)
- [ ] 3개 테이블 생성 (제약조건 포함)
- [ ] 데이터 삽입 (3개 테이블)
- [ ] 데이터 수정 (여러 컬럼 동시 수정)
- [ ] 데이터 삭제 (외래키 제약조건 주의)
- [ ] 집계함수 사용 (COUNT)
- [ ] GROUP BY 사용
- [ ] HAVING 사용
- [ ] INNER JOIN 사용 (3개 테이블)
- [ ] LEFT JOIN 사용
- [ ] NULL 값 처리

---

**20문제를 모두 풀면 SQL 실전 활용 능력이 향상됩니다!**
