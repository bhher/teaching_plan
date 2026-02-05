-- SQL JOIN 실습용 테이블 생성 및 데이터 삽입

-- 데이터베이스 생성
CREATE DATABASE join_example 
CHARACTER SET utf8mb4 
COLLATE utf8mb4_unicode_ci;

USE join_example;

-- 학생 테이블
CREATE TABLE student (
    student_id INT PRIMARY KEY AUTO_INCREMENT,
    student_no CHAR(20) UNIQUE NOT NULL,
    name VARCHAR(50) NOT NULL,
    major VARCHAR(50),
    grade INT
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 성적 테이블 (외래키는 나중에 추가)
CREATE TABLE grade (
    grade_id INT PRIMARY KEY AUTO_INCREMENT,
    student_no CHAR(20),
    subject VARCHAR(50),
    score INT
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 부서 테이블
CREATE TABLE department (
    dept_id INT PRIMARY KEY AUTO_INCREMENT,
    dept_name VARCHAR(50),
    location VARCHAR(50)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 직원 테이블 (SELF JOIN 예제용)
CREATE TABLE employee (
    emp_id INT PRIMARY KEY AUTO_INCREMENT,
    emp_name VARCHAR(50),
    manager_id INT,
    FOREIGN KEY (manager_id) REFERENCES employee(emp_id)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 학생 데이터 삽입
INSERT INTO student (student_no, name, major, grade) VALUES
('S001', '김철수', '컴퓨터공학', 1),
('S002', '이영희', '경영학', 2),
('S003', '박민수', '컴퓨터공학', 1),
('S004', '최지영', '영어영문', 3);

-- 성적 데이터 삽입
-- S006은 존재하지 않는 학생이므로 외래키 체크를 일시적으로 비활성화
SET FOREIGN_KEY_CHECKS = 0;  -- 외래키 체크 비활성화

INSERT INTO grade (student_no, subject, score) VALUES
('S001', '데이터베이스', 85),
('S001', '자바프로그래밍', 90),
('S002', '경영정보시스템', 88),
('S002', '회계원리', 92),
('S003', '데이터베이스', 78),
('S006', '네트워크', 95);  -- 존재하지 않는 학생 (RIGHT JOIN 예제용)

SET FOREIGN_KEY_CHECKS = 1;  -- 외래키 체크 다시 활성화

-- 부서 데이터 삽입
INSERT INTO department (dept_name, location) VALUES
('컴퓨터공학', '1호관'),
('경영학', '2호관'),
('영어영문', '3호관'),
('수학', '4호관');

-- 직원 데이터 삽입 (SELF JOIN 예제용)
INSERT INTO employee (emp_name, manager_id) VALUES
('김대표', NULL),
('이부장', 1),
('박과장', 2),
('최대리', 2),
('정사원', 3);

-- 외래키 제약조건 추가 (데이터 삽입 후)
-- 참고: S006은 RIGHT JOIN 예제를 위해 존재하지 않는 학생으로 설정했습니다.
-- 외래키 제약조건을 추가하면 데이터 무결성이 보장되지만,
-- RIGHT JOIN 예제를 위해 일부러 제약조건을 추가하지 않았습니다.

-- 일반적인 경우: 외래키 제약조건 추가 (주석 해제하여 사용)
-- ALTER TABLE grade 
-- ADD CONSTRAINT fk_grade_student 
-- FOREIGN KEY (student_no) REFERENCES student(student_no);

-- 데이터 확인
SELECT '=== 학생 테이블 ===' AS '';
SELECT * FROM student;

SELECT '=== 성적 테이블 ===' AS '';
SELECT * FROM grade;

SELECT '=== 부서 테이블 ===' AS '';
SELECT * FROM department;

SELECT '=== 직원 테이블 ===' AS '';
SELECT * FROM employee;
