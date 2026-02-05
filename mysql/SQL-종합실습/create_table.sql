-- SQL 종합 실습용 테이블 생성 및 데이터 삽입

-- 데이터베이스 생성
CREATE DATABASE company_db 
CHARACTER SET utf8mb4 
COLLATE utf8mb4_unicode_ci;

USE company_db;

-- 직원 테이블 생성
CREATE TABLE employee (
    emp_id INT PRIMARY KEY AUTO_INCREMENT,
    emp_no CHAR(20) UNIQUE NOT NULL,
    name VARCHAR(50) NOT NULL,
    department VARCHAR(50),
    position VARCHAR(30),
    salary INT,
    hire_date DATE
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 프로젝트 테이블 생성
CREATE TABLE project (
    project_id INT PRIMARY KEY AUTO_INCREMENT,
    project_name VARCHAR(100) NOT NULL,
    emp_no CHAR(20),
    start_date DATE,
    end_date DATE,
    status VARCHAR(20) DEFAULT '진행중',
    FOREIGN KEY (emp_no) REFERENCES employee(emp_no)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 직원 데이터 삽입
INSERT INTO employee (emp_no, name, department, position, salary, hire_date) VALUES
('E001', '김철수', '개발팀', '선임개발자', 5000, '2020-01-15'),
('E002', '이영희', '기획팀', '기획자', 4500, '2021-03-20'),
('E003', '박민수', '개발팀', '주임개발자', 4000, '2022-06-10'),
('E004', '최지영', '디자인팀', '디자이너', 4200, '2021-09-05'),
('E005', '정대현', '개발팀', '선임개발자', 5200, '2019-11-12');

-- 프로젝트 데이터 삽입
INSERT INTO project (project_name, emp_no, start_date, end_date, status) VALUES
('웹사이트 리뉴얼', 'E001', '2024-01-01', '2024-06-30', '진행중'),
('모바일 앱 개발', 'E002', '2024-02-15', '2024-08-31', '진행중'),
('데이터베이스 구축', 'E001', '2023-10-01', '2024-03-31', '완료'),
('UI/UX 개선', 'E004', '2024-03-01', '2024-05-31', '진행중'),
('시스템 통합', 'E003', '2024-04-01', '2024-09-30', '진행중');

-- 데이터 확인
SELECT '=== 직원 테이블 ===' AS '';
SELECT * FROM employee;

SELECT '=== 프로젝트 테이블 ===' AS '';
SELECT * FROM project;
