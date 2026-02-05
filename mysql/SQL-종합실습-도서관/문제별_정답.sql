-- SQL 종합 실습 문제 정답 - 도서관 시스템

USE library_db;

-- ============================================
-- 문제 1: 데이터베이스 생성
-- ============================================
CREATE DATABASE library_db 
CHARACTER SET utf8mb4 
COLLATE utf8mb4_unicode_ci;

-- ============================================
-- 문제 2: 회원 테이블 생성
-- ============================================
CREATE TABLE member (
    member_id INT PRIMARY KEY AUTO_INCREMENT,
    member_no CHAR(20) UNIQUE NOT NULL,
    name VARCHAR(50) NOT NULL,
    phone CHAR(20) UNIQUE,
    email VARCHAR(100),
    join_date DATE DEFAULT (CURRENT_DATE),
    status VARCHAR(20) DEFAULT '활성'
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- ============================================
-- 문제 3: 도서 테이블 생성
-- ============================================
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

-- ============================================
-- 문제 4: 대출 테이블 생성
-- ============================================
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

-- ============================================
-- 문제 5: 회원 데이터 삽입
-- ============================================
INSERT INTO member (member_no, name, phone, email, join_date) VALUES
('M001', '홍길동', '010-1111-2222', 'hong@email.com', '2020-01-10'),
('M002', '김영희', '010-2222-3333', 'kim@email.com', '2021-03-15'),
('M003', '이철수', '010-3333-4444', 'lee@email.com', '2022-05-20'),
('M004', '박민지', '010-4444-5555', 'park@email.com', '2021-07-08'),
('M005', '최수진', '010-5555-6666', 'choi@email.com', '2023-02-14');

-- ============================================
-- 문제 6: 도서 데이터 삽입
-- ============================================
INSERT INTO book (isbn, title, author, publisher, category, total_copies, available_copies) VALUES
('ISBN001', '자바의 정석', '남궁성', '도우출판', '프로그래밍', 5, 3),
('ISBN002', '이것이 자바다', '신용권', '한빛미디어', '프로그래밍', 3, 2),
('ISBN003', '해리포터', 'J.K.롤링', '문학수첩', '소설', 10, 5),
('ISBN004', '데이터베이스 개론', '이상호', '정익사', '컴퓨터', 4, 4),
('ISBN005', '알고리즘 문제해결', '구종만', '인사이트', '프로그래밍', 2, 1);

-- ============================================
-- 문제 7: 대출 데이터 삽입
-- ============================================
INSERT INTO loan (member_no, isbn, loan_date, return_date, due_date, status) VALUES
('M001', 'ISBN001', '2024-01-15', NULL, '2024-02-15', '대출중'),
('M002', 'ISBN002', '2024-02-01', NULL, '2024-03-01', '대출중'),
('M001', 'ISBN003', '2024-01-20', '2024-02-10', '2024-02-20', '반납완료'),
('M003', 'ISBN004', '2024-03-10', NULL, '2024-04-10', '대출중'),
('M004', 'ISBN005', '2024-02-25', NULL, '2024-03-25', '대출중');

-- ============================================
-- 문제 8: 회원 상태 변경
-- ============================================
UPDATE member 
SET status = '휴면'
WHERE member_no = 'M005';

-- ============================================
-- 문제 9: 도서 재고 업데이트
-- ============================================
UPDATE book 
SET available_copies = available_copies + 1
WHERE isbn = 'ISBN001';

-- ============================================
-- 문제 10: 대출 반납 처리
-- ============================================
UPDATE loan 
SET return_date = CURRENT_DATE,
    status = '반납완료'
WHERE loan_id = 3;

-- ============================================
-- 문제 11: 조건부 삭제 (휴면 회원)
-- ============================================
DELETE FROM member 
WHERE status = '휴면';

-- ============================================
-- 문제 12: 특정 레코드 삭제
-- ============================================
DELETE FROM member 
WHERE member_no = 'M005';

-- ============================================
-- 문제 13: 전체 도서 수
-- ============================================
SELECT COUNT(*) AS 도서종류수
FROM book;

-- ============================================
-- 문제 14: 카테고리별 도서 수
-- ============================================
SELECT 
    category AS 카테고리,
    COUNT(*) AS 도서수
FROM book
GROUP BY category
ORDER BY 도서수 DESC;

-- ============================================
-- 문제 15: 현재 대출 중인 도서 수
-- ============================================
SELECT COUNT(*) AS 대출중인도서수
FROM loan
WHERE status = '대출중';

-- ============================================
-- 문제 16: 회원별 대출 횟수
-- ============================================
SELECT 
    m.member_no AS 회원번호,
    m.name AS 이름,
    COUNT(l.loan_id) AS 대출횟수
FROM member m
LEFT JOIN loan l ON m.member_no = l.member_no
GROUP BY m.member_no, m.name
ORDER BY 대출횟수 DESC;

-- ============================================
-- 문제 17: HAVING 사용 (대출 횟수 2회 이상)
-- ============================================
SELECT 
    m.member_no AS 회원번호,
    COUNT(l.loan_id) AS 대출횟수
FROM member m
LEFT JOIN loan l ON m.member_no = l.member_no
GROUP BY m.member_no
HAVING COUNT(l.loan_id) >= 2
ORDER BY 대출횟수 DESC;

-- ============================================
-- 문제 18: INNER JOIN - 기본 조인
-- ============================================
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

-- ============================================
-- 문제 19: INNER JOIN - 조건부 조인
-- ============================================
SELECT 
    m.name AS 회원이름,
    b.title AS 도서제목,
    b.category AS 카테고리
FROM loan l
INNER JOIN member m ON l.member_no = m.member_no
INNER JOIN book b ON l.isbn = b.isbn
WHERE b.category = '프로그래밍'
ORDER BY m.name, b.title;

-- ============================================
-- 문제 20: LEFT JOIN - 매칭되지 않는 데이터 찾기
-- ============================================
SELECT 
    m.member_no AS 회원번호,
    m.name AS 회원이름,
    m.join_date AS 가입일
FROM member m
LEFT JOIN loan l ON m.member_no = l.member_no
WHERE l.member_no IS NULL;
