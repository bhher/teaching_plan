-- SQL 종합 실습 문제 정답

USE company_db;

-- ============================================
-- 문제 1: 데이터베이스 생성
-- ============================================
CREATE DATABASE company_db 
CHARACTER SET utf8mb4 
COLLATE utf8mb4_unicode_ci;

-- ============================================
-- 문제 2: 직원 테이블 생성
-- ============================================
CREATE TABLE employee (
    emp_id INT PRIMARY KEY AUTO_INCREMENT,
    emp_no CHAR(20) UNIQUE NOT NULL,
    name VARCHAR(50) NOT NULL,
    department VARCHAR(50),
    position VARCHAR(30),
    salary INT,
    hire_date DATE
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- ============================================
-- 문제 3: 프로젝트 테이블 생성
-- ============================================
CREATE TABLE project (
    project_id INT PRIMARY KEY AUTO_INCREMENT,
    project_name VARCHAR(100) NOT NULL,
    emp_no CHAR(20),
    start_date DATE,
    end_date DATE,
    status VARCHAR(20) DEFAULT '진행중',
    FOREIGN KEY (emp_no) REFERENCES employee(emp_no)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- ============================================
-- 문제 4: 직원 데이터 삽입
-- ============================================
INSERT INTO employee (emp_no, name, department, position, salary, hire_date) VALUES
('E001', '김철수', '개발팀', '선임개발자', 5000, '2020-01-15'),
('E002', '이영희', '기획팀', '기획자', 4500, '2021-03-20'),
('E003', '박민수', '개발팀', '주임개발자', 4000, '2022-06-10'),
('E004', '최지영', '디자인팀', '디자이너', 4200, '2021-09-05'),
('E005', '정대현', '개발팀', '선임개발자', 5200, '2019-11-12');

-- ============================================
-- 문제 5: 프로젝트 데이터 삽입
-- ============================================
INSERT INTO project (project_name, emp_no, start_date, end_date, status) VALUES
('웹사이트 리뉴얼', 'E001', '2024-01-01', '2024-06-30', '진행중'),
('모바일 앱 개발', 'E002', '2024-02-15', '2024-08-31', '진행중'),
('데이터베이스 구축', 'E001', '2023-10-01', '2024-03-31', '완료'),
('UI/UX 개선', 'E004', '2024-03-01', '2024-05-31', '진행중'),
('시스템 통합', 'E003', '2024-04-01', '2024-09-30', '진행중');

-- ============================================
-- 문제 6: 급여 인상 (개발팀 10% 인상)
-- ============================================
UPDATE employee 
SET salary = salary * 1.1
WHERE department = '개발팀';

-- ============================================
-- 문제 7: 직책 변경
-- ============================================
UPDATE employee 
SET position = '선임개발자'
WHERE emp_no = 'E003';

-- ============================================
-- 문제 8: 프로젝트 상태 변경
-- ============================================
UPDATE project 
SET status = '완료'
WHERE project_name = '데이터베이스 구축';

-- ============================================
-- 문제 9: 조건부 삭제 (급여 4000 미만)
-- ============================================
DELETE FROM employee 
WHERE salary < 4000;

-- ============================================
-- 문제 10: 특정 레코드 삭제
-- ============================================
DELETE FROM employee 
WHERE emp_no = 'E005';

-- ============================================
-- 문제 11: 전체 직원 수
-- ============================================
SELECT COUNT(*) AS 직원수
FROM employee;

-- ============================================
-- 문제 12: 부서별 직원 수
-- ============================================
SELECT 
    department AS 부서,
    COUNT(*) AS 직원수
FROM employee
GROUP BY department
ORDER BY 직원수 DESC;

-- ============================================
-- 문제 13: 평균 급여
-- ============================================
SELECT ROUND(AVG(salary), 2) AS 평균급여
FROM employee;

-- ============================================
-- 문제 14: 최고/최저 급여
-- ============================================
SELECT 
    MAX(salary) AS 최고급여,
    MIN(salary) AS 최저급여
FROM employee;

-- ============================================
-- 문제 15: 부서별 평균 급여
-- ============================================
SELECT 
    department AS 부서,
    ROUND(AVG(salary), 2) AS 평균급여
FROM employee
GROUP BY department
ORDER BY 평균급여 DESC;

-- ============================================
-- 문제 16: HAVING 사용 (평균 급여 4500 이상)
-- ============================================
SELECT 
    department AS 부서,
    ROUND(AVG(salary), 2) AS 평균급여
FROM employee
GROUP BY department
HAVING AVG(salary) >= 4500
ORDER BY 평균급여 DESC;

-- ============================================
-- 문제 17: INNER JOIN - 기본 조인
-- ============================================
SELECT 
    e.name AS 직원이름,
    p.project_name AS 프로젝트명
FROM employee e
INNER JOIN project p ON e.emp_no = p.emp_no
ORDER BY e.name, p.project_name;

-- ============================================
-- 문제 18: INNER JOIN - 조건부 조인
-- ============================================
SELECT 
    e.name AS 직원이름,
    e.department AS 부서,
    p.project_name AS 프로젝트명
FROM employee e
INNER JOIN project p ON e.emp_no = p.emp_no
WHERE e.department = '개발팀'
ORDER BY e.name, p.project_name;

-- ============================================
-- 문제 19: LEFT JOIN - 기본 조인
-- ============================================
SELECT 
    e.name AS 직원이름,
    e.department AS 부서,
    p.project_name AS 프로젝트명
FROM employee e
LEFT JOIN project p ON e.emp_no = p.emp_no
ORDER BY e.name;

-- ============================================
-- 문제 20: LEFT JOIN - 매칭되지 않는 데이터 찾기
-- ============================================
SELECT 
    e.emp_no AS 사원번호,
    e.name AS 직원이름,
    e.department AS 부서
FROM employee e
LEFT JOIN project p ON e.emp_no = p.emp_no
WHERE p.emp_no IS NULL;
