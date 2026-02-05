# SQL 종합 실습 문제 정답 및 해설

## 문제 1: 데이터베이스 생성 (5점)

**문제:** `company_db`라는 이름의 데이터베이스를 생성하시오. (UTF-8 설정 포함)

**정답:**
```sql
CREATE DATABASE company_db 
CHARACTER SET utf8mb4 
COLLATE utf8mb4_unicode_ci;
```

**해설:**
- `CREATE DATABASE`: 데이터베이스 생성 명령어
- `CHARACTER SET utf8mb4`: UTF-8 문자셋 설정 (한글 지원)
- `COLLATE utf8mb4_unicode_ci`: 정렬 규칙 설정
- 한글 데이터를 올바르게 저장하기 위해 UTF-8 설정 필수

---

## 문제 2: 테이블 생성 - 직원 테이블 (10점)

**문제:** 다음 조건에 맞는 `employee` 테이블을 생성하시오.

**정답:**
```sql
USE company_db;

CREATE TABLE employee (
    emp_id INT PRIMARY KEY AUTO_INCREMENT,
    emp_no CHAR(20) UNIQUE NOT NULL,
    name VARCHAR(50) NOT NULL,
    department VARCHAR(50),
    position VARCHAR(30),
    salary INT,
    hire_date DATE
) CHARACTER SET utf8mb4 
  COLLATE utf8mb4_unicode_ci;
```

**해설:**
- `PRIMARY KEY`: 기본키 설정
- `AUTO_INCREMENT`: 자동 증가
- `UNIQUE`: 고유값 제약조건
- `NOT NULL`: NULL 불가 제약조건
- `CHAR(20)`: 고정 길이 문자열
- `VARCHAR(50)`: 가변 길이 문자열
- `INT`: 정수형
- `DATE`: 날짜형

---

## 문제 3: 테이블 생성 - 프로젝트 테이블 (10점)

**문제:** 다음 조건에 맞는 `project` 테이블을 생성하시오.

**정답:**
```sql
CREATE TABLE project (
    project_id INT PRIMARY KEY AUTO_INCREMENT,
    project_name VARCHAR(100) NOT NULL,
    emp_no CHAR(20),
    start_date DATE,
    end_date DATE,
    status VARCHAR(20) DEFAULT '진행중',
    FOREIGN KEY (emp_no) REFERENCES employee(emp_no)
) CHARACTER SET utf8mb4 
  COLLATE utf8mb4_unicode_ci;
```

**해설:**
- `FOREIGN KEY`: 외래키 제약조건
- `REFERENCES employee(emp_no)`: employee 테이블의 emp_no 참조
- `DEFAULT '진행중'`: 기본값 설정
- 외래키로 인해 employee 테이블에 존재하는 emp_no만 입력 가능

---

## 문제 4: 데이터 삽입 - 직원 데이터 (5점)

**문제:** 다음 데이터를 `employee` 테이블에 삽입하시오.

**정답:**
```sql
INSERT INTO employee (emp_no, name, department, position, salary, hire_date) VALUES
('E001', '김철수', '개발팀', '선임개발자', 5000, '2020-01-15'),
('E002', '이영희', '기획팀', '기획자', 4500, '2021-03-20'),
('E003', '박민수', '개발팀', '주임개발자', 4000, '2022-06-10'),
('E004', '최지영', '디자인팀', '디자이너', 4200, '2021-09-05'),
('E005', '정대현', '개발팀', '선임개발자', 5200, '2019-11-12');
```

**해설:**
- `INSERT INTO`: 데이터 삽입 명령어
- 컬럼명을 명시하여 순서와 관계없이 삽입 가능
- 여러 행을 한 번에 삽입 가능
- 날짜는 문자열 형식으로 입력 ('YYYY-MM-DD')

**데이터 확인:**
```sql
SELECT * FROM employee;
```

---

## 문제 5: 데이터 삽입 - 프로젝트 데이터 (5점)

**문제:** 다음 데이터를 `project` 테이블에 삽입하시오.

**정답:**
```sql
INSERT INTO project (project_name, emp_no, start_date, end_date, status) VALUES
('웹사이트 리뉴얼', 'E001', '2024-01-01', '2024-06-30', '진행중'),
('모바일 앱 개발', 'E002', '2024-02-15', '2024-08-31', '진행중'),
('데이터베이스 구축', 'E001', '2023-10-01', '2024-03-31', '완료'),
('UI/UX 개선', 'E004', '2024-03-01', '2024-05-31', '진행중'),
('시스템 통합', 'E003', '2024-04-01', '2024-09-30', '진행중');
```

**해설:**
- 외래키 제약조건으로 인해 employee 테이블에 존재하는 emp_no만 입력 가능
- status는 기본값이 '진행중'이지만 명시적으로 입력 가능
- 한 직원이 여러 프로젝트를 담당할 수 있음 (1:N 관계)

**데이터 확인:**
```sql
SELECT * FROM project;
```

---

## 문제 6: 데이터 수정 - 급여 인상 (5점)

**문제:** 개발팀 직원들의 급여를 10% 인상하시오.

**정답:**
```sql
UPDATE employee 
SET salary = salary * 1.1
WHERE department = '개발팀';
```

**해설:**
- `UPDATE`: 데이터 수정 명령어
- `SET salary = salary * 1.1`: 현재 급여의 1.1배 (10% 인상)
- `WHERE department = '개발팀'`: 조건 지정 (개발팀만)
- WHERE 절을 생략하면 모든 직원의 급여가 인상됨 (주의!)

**수정 전/후 비교:**
```sql
-- 수정 전
SELECT emp_no, name, department, salary FROM employee WHERE department = '개발팀';

-- 수정 실행
UPDATE employee SET salary = salary * 1.1 WHERE department = '개발팀';

-- 수정 후
SELECT emp_no, name, department, salary FROM employee WHERE department = '개발팀';
```

**결과:**
- 김철수: 5000 → 5500
- 박민수: 4000 → 4400
- 정대현: 5200 → 5720

---

## 문제 7: 데이터 수정 - 직책 변경 (5점)

**문제:** 사원번호가 'E003'인 직원의 직책을 '선임개발자'로 변경하시오.

**정답:**
```sql
UPDATE employee 
SET position = '선임개발자'
WHERE emp_no = 'E003';
```

**해설:**
- `SET position = '선임개발자'`: 직책 변경
- `WHERE emp_no = 'E003'`: 특정 직원만 선택
- WHERE 절 필수 (없으면 모든 직원의 직책이 변경됨)

**확인:**
```sql
SELECT emp_no, name, position FROM employee WHERE emp_no = 'E003';
```

---

## 문제 8: 데이터 수정 - 프로젝트 상태 변경 (5점)

**문제:** 프로젝트명이 '데이터베이스 구축'인 프로젝트의 상태를 '진행중'로 변경하시오.

**정답:**
```sql
UPDATE project 
SET status = '진행중'
WHERE project_name = '데이터베이스 구축';
```

**해설:**
- `SET status = '진행중'`: 상태 변경
- `WHERE project_name = '데이터베이스 구축'`: 특정 프로젝트만 선택
- 문자열 비교 시 정확한 값 사용

**확인:**
```sql
SELECT project_name, status FROM project WHERE project_name = '데이터베이스 구축';
```

---

## 문제 9: 데이터 삭제 - 특정 조건 (5점)

**문제:** 급여가 4000 미만인 직원의 레코드를 삭제하시오.

**정답:**
```sql
DELETE FROM employee 
WHERE salary < 4000;
```

**해설:**
- `DELETE FROM`: 데이터 삭제 명령어
- `WHERE salary < 4000`: 조건 지정
- **주의:** WHERE 절을 생략하면 모든 레코드가 삭제됨!
- 삭제 전 확인 권장

**삭제 전 확인:**
```sql
SELECT * FROM employee WHERE salary < 4000;
```

**참고:** 실제 데이터에는 급여가 4000 미만인 직원이 없을 수 있음 (문제 4의 데이터 기준)

---

## 문제 10: 데이터 삭제 - 특정 레코드 (5점)

**문제:** 사원번호가 'E005'인 직원의 레코드를 삭제하시오.

**정답:**
```sql
DELETE FROM employee 
WHERE emp_no = 'E005';
```

**해설:**
- `DELETE FROM employee`: employee 테이블에서 삭제
- `WHERE emp_no = 'E005'`: 특정 직원만 삭제
- 외래키 제약조건이 있으면 참조하는 데이터가 있을 때 삭제 불가

**주의사항:**
- project 테이블에서 E005를 참조하는 프로젝트가 있으면 삭제 불가
- 먼저 참조하는 데이터를 삭제하거나 외래키 제약조건을 확인해야 함

**확인:**
```sql
-- 참조하는 프로젝트 확인
SELECT * FROM project WHERE emp_no = 'E005';

-- 참조하는 프로젝트가 없으면 삭제 가능
DELETE FROM employee WHERE emp_no = 'E005';
```

---

## 문제 11: 집계함수 - 전체 직원 수 (5점)

**문제:** 전체 직원 수를 구하시오.

**정답:**
```sql
SELECT COUNT(*) AS 직원수
FROM employee;
```

**해설:**
- `COUNT(*)`: 모든 행의 개수 반환
- `AS 직원수`: 컬럼 별칭 지정
- NULL 값도 포함하여 카운트

**결과:**
```
직원수
5
```

---

## 문제 12: 집계함수 - 부서별 직원 수 (5점)

**문제:** 각 부서별 직원 수를 구하시오.

**정답:**
```sql
SELECT 
    department AS 부서,
    COUNT(*) AS 직원수
FROM employee
GROUP BY department
ORDER BY 직원수 DESC;
```

**해설:**
- `GROUP BY department`: 부서별로 그룹화
- `COUNT(*)`: 각 그룹의 직원 수 계산
- `ORDER BY 직원수 DESC`: 직원 수가 많은 순으로 정렬

**결과:**
```
부서      | 직원수
----------|-------
개발팀    | 3
기획팀    | 1
디자인팀  | 1
```

---

## 문제 13: 집계함수 - 평균 급여 (5점)

**문제:** 전체 직원의 평균 급여를 구하시오.

**정답:**
```sql
SELECT AVG(salary) AS 평균급여
FROM employee;
```

**또는 소수점 둘째 자리까지:**
```sql
SELECT ROUND(AVG(salary), 2) AS 평균급여
FROM employee;
```

**해설:**
- `AVG(salary)`: 급여의 평균값 계산
- NULL 값은 계산에서 제외됨
- `ROUND()`: 소수점 자리수 제한

**결과:**
```
평균급여
4580.0000
```

---

## 문제 14: 집계함수 - 최고/최저 급여 (5점)

**문제:** 최고 급여와 최저 급여를 구하시오.

**정답:**
```sql
SELECT 
    MAX(salary) AS 최고급여,
    MIN(salary) AS 최저급여
FROM employee;
```

**해설:**
- `MAX(salary)`: 최대값
- `MIN(salary)`: 최소값
- 여러 집계함수를 함께 사용 가능

**결과:**
```
최고급여 | 최저급여
---------|----------
5200     | 4000
```

---

## 문제 15: 집계함수 - 부서별 평균 급여 (5점)

**문제:** 각 부서별 평균 급여를 구하시오. (부서별로 그룹화)

**정답:**
```sql
SELECT 
    department AS 부서,
    AVG(salary) AS 평균급여
FROM employee
GROUP BY department
ORDER BY 평균급여 DESC;
```

**또는 소수점 둘째 자리까지:**
```sql
SELECT 
    department AS 부서,
    ROUND(AVG(salary), 2) AS 평균급여
FROM employee
GROUP BY department
ORDER BY 평균급여 DESC;
```

**해설:**
- `GROUP BY department`: 부서별로 그룹화
- `AVG(salary)`: 각 부서의 평균 급여 계산
- `ORDER BY 평균급여 DESC`: 평균 급여가 높은 순으로 정렬

**결과:**
```
부서      | 평균급여
----------|----------
개발팀    | 4733.33
디자인팀  | 4200.00
기획팀    | 4500.00
```

---

## 문제 16: 집계함수 - HAVING 사용 (5점)

**문제:** 평균 급여가 4500 이상인 부서와 그 평균 급여를 구하시오.

**정답:**
```sql
SELECT 
    department AS 부서,
    AVG(salary) AS 평균급여
FROM employee
GROUP BY department
HAVING AVG(salary) >= 4500
ORDER BY 평균급여 DESC;
```

**해설:**
- `GROUP BY department`: 부서별로 그룹화
- `HAVING AVG(salary) >= 4500`: 그룹화된 결과에 조건 적용
- **HAVING vs WHERE:**
  - `WHERE`: 그룹화 전 조건 (개별 행 필터링)
  - `HAVING`: 그룹화 후 조건 (그룹 결과 필터링)

**결과:**
```
부서      | 평균급여
----------|----------
개발팀    | 4733.33
기획팀    | 4500.00
```

---

## 문제 17: INNER JOIN - 기본 조인 (10점)

**문제:** 직원 이름과 그들이 담당하는 프로젝트명을 조회하시오.

**정답:**
```sql
SELECT 
    e.name AS 직원이름,
    p.project_name AS 프로젝트명
FROM employee e
INNER JOIN project p ON e.emp_no = p.emp_no
ORDER BY e.name, p.project_name;
```

**해설:**
- `INNER JOIN`: 두 테이블의 교집합 (매칭되는 데이터만)
- `ON e.emp_no = p.emp_no`: 조인 조건
- `e`, `p`: 테이블 별칭 (alias)
- `ORDER BY`: 정렬

**결과:**
```
직원이름 | 프로젝트명
---------|------------------
김철수   | 데이터베이스 구축
김철수   | 웹사이트 리뉴얼
박민수   | 시스템 통합
이영희   | 모바일 앱 개발
최지영   | UI/UX 개선
```

**설명:**
- INNER JOIN이므로 프로젝트를 담당하지 않는 직원은 제외됨
- 한 직원이 여러 프로젝트를 담당할 수 있으므로 중복 가능

---

## 문제 18: INNER JOIN - 조건부 조인 (10점)

**문제:** 개발팀 직원의 이름과 담당 프로젝트명을 조회하시오.

**정답:**
```sql
SELECT 
    e.name AS 직원이름,
    e.department AS 부서,
    p.project_name AS 프로젝트명
FROM employee e
INNER JOIN project p ON e.emp_no = p.emp_no
WHERE e.department = '개발팀'
ORDER BY e.name, p.project_name;
```

**해설:**
- `INNER JOIN`: 직원과 프로젝트 조인
- `WHERE e.department = '개발팀'`: 개발팀만 필터링
- WHERE 절은 JOIN 이후에 적용됨

**결과:**
```
직원이름 | 부서   | 프로젝트명
---------|--------|------------------
김철수   | 개발팀 | 데이터베이스 구축
김철수   | 개발팀 | 웹사이트 리뉴얼
박민수   | 개발팀 | 시스템 통합
```

---

## 문제 19: LEFT JOIN - 기본 조인 (10점)

**문제:** 모든 직원의 이름과 담당 프로젝트명을 조회하시오. (프로젝트가 없는 직원도 포함)

**정답:**
```sql
SELECT 
    e.name AS 직원이름,
    e.department AS 부서,
    p.project_name AS 프로젝트명
FROM employee e
LEFT JOIN project p ON e.emp_no = p.emp_no
ORDER BY e.name;
```

**해설:**
- `LEFT JOIN`: 왼쪽 테이블(employee)의 모든 행 포함
- 프로젝트가 없는 직원도 NULL로 표시되어 포함됨
- INNER JOIN과 달리 모든 직원이 결과에 포함됨

**결과:**
```
직원이름 | 부서      | 프로젝트명
---------|-----------|------------------
김철수   | 개발팀    | 데이터베이스 구축
김철수   | 개발팀    | 웹사이트 리뉴얼
박민수   | 개발팀    | 시스템 통합
이영희   | 기획팀    | 모바일 앱 개발
정대현   | 개발팀    | NULL
최지영   | 디자인팀  | UI/UX 개선
```

**설명:**
- `정대현`은 프로젝트를 담당하지 않으므로 NULL로 표시
- LEFT JOIN으로 모든 직원이 포함됨

---

## 문제 20: LEFT JOIN - 매칭되지 않는 데이터 찾기 (10점)

**문제:** 프로젝트를 담당하지 않는 직원을 찾아 출력하시오.

**정답:**
```sql
SELECT 
    e.emp_no AS 사원번호,
    e.name AS 직원이름,
    e.department AS 부서
FROM employee e
LEFT JOIN project p ON e.emp_no = p.emp_no
WHERE p.emp_no IS NULL;
```

**해설:**
- `LEFT JOIN`: 모든 직원 포함
- `WHERE p.emp_no IS NULL`: 프로젝트 테이블의 emp_no가 NULL인 경우
- 즉, 프로젝트를 담당하지 않는 직원 찾기
- **IS NULL 사용**: 등호(`=`) 사용 불가

**결과:**
```
사원번호 | 직원이름 | 부서
---------|----------|-------
E005     | 정대현   | 개발팀
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
```

### 2. 데이터 삽입

```sql
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
```

---

## 문제 유형별 요약

### 데이터베이스/테이블 생성 (문제 1-3)
- CREATE DATABASE
- CREATE TABLE
- 제약조건 (PRIMARY KEY, FOREIGN KEY, UNIQUE, NOT NULL, DEFAULT)

### 데이터 수정 (문제 6-8)
- UPDATE ... SET ... WHERE
- 조건부 수정
- 계산식 사용

### 데이터 삭제 (문제 9-10)
- DELETE FROM ... WHERE
- 조건부 삭제
- 외래키 제약조건 주의

### 집계함수 (문제 11-16)
- COUNT, AVG, MAX, MIN, SUM
- GROUP BY
- HAVING

### JOIN (문제 17-20)
- INNER JOIN
- LEFT JOIN
- 조건부 JOIN
- 매칭되지 않는 데이터 찾기

---

## 체크리스트

- [ ] 데이터베이스 생성 (UTF-8 설정)
- [ ] 테이블 생성 (제약조건 포함)
- [ ] 데이터 삽입 (INSERT)
- [ ] 데이터 수정 (UPDATE)
- [ ] 데이터 삭제 (DELETE)
- [ ] 집계함수 사용 (COUNT, AVG, MAX, MIN)
- [ ] GROUP BY 사용
- [ ] HAVING 사용
- [ ] INNER JOIN 사용
- [ ] LEFT JOIN 사용
- [ ] NULL 값 처리

---

**20문제를 모두 풀면 SQL 기본기를 마스터할 수 있습니다!**
