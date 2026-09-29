-- 학원 수강 시스템 더미 (문제 1~7 한 번에 실행)
-- mysql -u root -p --default-character-set=utf8mb4 < 학원수강-더미.sql

DROP DATABASE IF EXISTS academy_db;

CREATE DATABASE academy_db
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE academy_db;

CREATE TABLE student (
    student_id   INT PRIMARY KEY AUTO_INCREMENT,
    student_no   VARCHAR(20) UNIQUE NOT NULL,
    name         VARCHAR(50) NOT NULL,
    phone        VARCHAR(20) UNIQUE,
    email        VARCHAR(100),
    join_date    DATE DEFAULT (CURRENT_DATE),
    status       VARCHAR(20) DEFAULT '수강중'
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE course (
    course_id   INT PRIMARY KEY AUTO_INCREMENT,
    course_code VARCHAR(20) UNIQUE NOT NULL,
    title       VARCHAR(200) NOT NULL,
    teacher     VARCHAR(100),
    classroom   VARCHAR(50),
    category    VARCHAR(50),
    capacity    INT DEFAULT 20,
    enrolled    INT DEFAULT 0
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE enroll (
    enroll_id   INT PRIMARY KEY AUTO_INCREMENT,
    student_no  VARCHAR(20),
    course_code VARCHAR(20),
    enroll_date DATE DEFAULT (CURRENT_DATE),
    end_date    DATE,
    start_date  DATE NOT NULL,
    status      VARCHAR(20) DEFAULT '신청',
    FOREIGN KEY (student_no) REFERENCES student(student_no),
    FOREIGN KEY (course_code) REFERENCES course(course_code)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

INSERT INTO student (student_no, name, phone, email, join_date) VALUES
('S001', '홍길동', '010-1111-1001', 'hong@email.com', '2023-01-10'),
('S002', '김영희', '010-2222-2002', 'kim@email.com', '2023-03-15'),
('S003', '이철수', '010-3333-3003', 'lee@email.com', '2024-05-20'),
('S004', '박민지', '010-4444-4004', 'park@email.com', '2023-07-08'),
('S005', '최수진', '010-5555-5005', 'choi@email.com', '2024-02-14');

INSERT INTO course (course_code, title, teacher, classroom, category, capacity, enrolled) VALUES
('C001', 'MySQL 입문', '박민수', '301호', 'DB', 30, 12),
('C002', 'Java 기초', '이서연', '202호', '프로그래밍', 25, 18),
('C003', '웹 퍼블리싱', '김하늘', '105호', '웹', 20, 15),
('C004', 'React 실습', '최준호', '401호', '프론트', 20, 8),
('C005', '알고리즘', '한지우', '203호', '프로그래밍', 15, 10);

INSERT INTO enroll (student_no, course_code, enroll_date, end_date, start_date, status) VALUES
('S001', 'C001', '2024-01-15', NULL, '2024-02-01', '수강중'),
('S002', 'C002', '2024-02-01', NULL, '2024-02-15', '수강중'),
('S001', 'C003', '2024-01-20', '2024-03-20', '2024-02-01', '수료'),
('S003', 'C004', '2024-03-10', NULL, '2024-03-20', '수강중'),
('S004', 'C005', '2024-02-25', NULL, '2024-03-01', '수강중');

SELECT 'student' AS t, COUNT(*) AS cnt FROM student
UNION ALL SELECT 'course', COUNT(*) FROM course
UNION ALL SELECT 'enroll', COUNT(*) FROM enroll;
