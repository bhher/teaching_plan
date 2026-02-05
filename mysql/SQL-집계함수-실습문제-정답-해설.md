# SQL 집계함수 실습 문제 정답 및 해설

## 테이블 생성 및 더미 데이터

### 데이터베이스 생성

```sql
CREATE DATABASE school_db 
CHARACTER SET utf8mb4 
COLLATE utf8mb4_unicode_ci;

USE school_db;
```

### 테이블 생성

```sql
CREATE TABLE student (
    student_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    department VARCHAR(50),
    grade INT,
    korean INT,
    english INT,
    math INT,
    science INT,
    total_score INT,
    average DECIMAL(5,2)
) CHARACTER SET utf8mb4 
  COLLATE utf8mb4_unicode_ci;
```

### 더미 데이터 삽입

```sql
INSERT INTO student (name, department, grade, korean, english, math, science, total_score, average) VALUES
('김철수', '컴퓨터공학', 1, 85, 90, 88, 92, 355, 88.75),
('이영희', '전자공학', 1, 92, 88, 95, 90, 365, 91.25),
('박민수', '컴퓨터공학', 2, 78, 82, 85, 80, 325, 81.25),
('최지영', '전자공학', 1, 88, 85, 90, 87, 350, 87.50),
('정수진', '컴퓨터공학', 3, 95, 92, 98, 96, 381, 95.25),
('한동욱', '기계공학', 2, 82, 78, 80, 85, 325, 81.25),
('송미영', '컴퓨터공학', 2, 90, 88, 92, 89, 359, 89.75),
('윤태호', '전자공학', 3, 87, 90, 85, 88, 350, 87.50),
('강민지', '컴퓨터공학', 1, 80, 85, 82, 88, 335, 83.75),
('임수현', '기계공학', 1, 75, 80, 78, 82, 315, 78.75),
('조현우', '컴퓨터공학', 3, 93, 95, 90, 94, 372, 93.00),
('배서연', '전자공학', 2, 85, 87, 88, 86, 346, 86.50),
('오준호', '컴퓨터공학', 1, 88, 90, 85, 90, 353, 88.25),
('신유진', '기계공학', 3, 79, 82, 80, 83, 324, 81.00),
('류성민', '컴퓨터공학', 2, 91, 89, 93, 91, 364, 91.00),
('황지은', '전자공학', 1, 86, 88, 87, 89, 350, 87.50),
('고재현', '컴퓨터공학', 3, 94, 96, 92, 95, 377, 94.25),
('문소희', '기계공학', 2, 81, 83, 79, 85, 328, 82.00),
('안준영', '컴퓨터공학', 1, 89, 91, 86, 88, 354, 88.50),
('전혜진', '전자공학', 3, 92, 94, 89, 93, 368, 92.00);
```

**데이터 확인:**
```sql
SELECT * FROM student;
```

---

## 문제별 정답 및 해설

### 문제 1: 전체 학생 수 구하기

**문제:** 전체 학생 수를 구하시오.

**정답:**
```sql
SELECT COUNT(*) AS 학생수
FROM student;
```

**또는:**
```sql
SELECT COUNT(student_id) AS 학생수
FROM student;
```

**해설:**
- `COUNT(*)`: 모든 행의 개수를 반환 (NULL 포함)
- `COUNT(student_id)`: student_id가 NULL이 아닌 행의 개수 반환
- 기본키는 NULL이 될 수 없으므로 결과는 동일
- **결과: 20명**

**출력:**
```
학생수
20
```

---

### 문제 2: 각 학과별 학생 수 구하기

**문제:** 각 학과별 학생 수를 구하시오.

**정답:**
```sql
SELECT 
    department AS 학과,
    COUNT(*) AS 학생수
FROM student
GROUP BY department
ORDER BY 학생수 DESC;
```

**해설:**
- `GROUP BY department`: 학과별로 그룹화
- `COUNT(*)`: 각 그룹의 학생 수 계산
- `ORDER BY 학생수 DESC`: 학생 수가 많은 순서대로 정렬
- **결과:**
  - 컴퓨터공학: 10명
  - 전자공학: 6명
  - 기계공학: 4명

**출력:**
```
학과          학생수
컴퓨터공학    10
전자공학      6
기계공학      4
```

---

### 문제 3: 각 학년별 학생 수 구하기

**문제:** 각 학년별 학생 수를 구하시오.

**정답:**
```sql
SELECT 
    grade AS 학년,
    COUNT(*) AS 학생수
FROM student
GROUP BY grade
ORDER BY grade;
```

**해설:**
- `GROUP BY grade`: 학년별로 그룹화
- `COUNT(*)`: 각 학년의 학생 수 계산
- `ORDER BY grade`: 학년 순서대로 정렬
- **결과:**
  - 1학년: 7명
  - 2학년: 6명
  - 3학년: 7명

**출력:**
```
학년  학생수
1     7
2     6
3     7
```

---

### 문제 4: 전체 학생의 평균 점수 구하기

**문제:** 전체 학생의 평균 점수(average 컬럼)를 구하시오.

**정답:**
```sql
SELECT AVG(average) AS 전체평균점수
FROM student;
```

**또는 소수점 둘째 자리까지:**
```sql
SELECT ROUND(AVG(average), 2) AS 전체평균점수
FROM student;
```

**해설:**
- `AVG(average)`: average 컬럼의 평균값 계산
- NULL 값은 계산에서 제외됨
- **결과: 약 87.35점**

**출력:**
```
전체평균점수
87.3500
```

---

### 문제 5: 각 학과별 평균 점수 구하기

**문제:** 각 학과별 평균 점수를 구하시오.

**정답:**
```sql
SELECT 
    department AS 학과,
    AVG(average) AS 평균점수
FROM student
GROUP BY department
ORDER BY 평균점수 DESC;
```

**또는 소수점 둘째 자리까지:**
```sql
SELECT 
    department AS 학과,
    ROUND(AVG(average), 2) AS 평균점수
FROM student
GROUP BY department
ORDER BY 평균점수 DESC;
```

**해설:**
- `GROUP BY department`: 학과별로 그룹화
- `AVG(average)`: 각 학과의 평균 점수 계산
- `ORDER BY 평균점수 DESC`: 평균 점수가 높은 순서대로 정렬
- **결과:**
  - 컴퓨터공학: 약 89.78점
  - 전자공학: 약 89.17점
  - 기계공학: 약 80.75점

**출력:**
```
학과          평균점수
컴퓨터공학    89.7750
전자공학      89.1667
기계공학      80.7500
```

---

### 문제 6: 최고 점수와 최저 점수 구하기

**문제:** 전체 학생 중 최고 점수와 최저 점수를 구하시오.

**정답:**
```sql
SELECT 
    MAX(average) AS 최고점수,
    MIN(average) AS 최저점수
FROM student;
```

**해설:**
- `MAX(average)`: 평균 점수의 최대값
- `MIN(average)`: 평균 점수의 최소값
- **결과:**
  - 최고점수: 95.25점
  - 최저점수: 78.75점

**출력:**
```
최고점수  최저점수
95.25    78.75
```

---

### 문제 7: 각 학과별 최고 점수와 최저 점수 구하기

**문제:** 각 학과별 최고 점수와 최저 점수를 구하시오.

**정답:**
```sql
SELECT 
    department AS 학과,
    MAX(average) AS 최고점수,
    MIN(average) AS 최저점수
FROM student
GROUP BY department
ORDER BY department;
```

**해설:**
- `GROUP BY department`: 학과별로 그룹화
- `MAX(average)`: 각 학과의 최고 점수
- `MIN(average)`: 각 학과의 최저 점수
- **결과:**
  - 컴퓨터공학: 최고 95.25점, 최저 81.25점
  - 전자공학: 최고 92.00점, 최저 86.50점
  - 기계공학: 최고 82.00점, 최저 78.75점

**출력:**
```
학과          최고점수  최저점수
기계공학      82.00    78.75
전자공학      92.00    86.50
컴퓨터공학    95.25    81.25
```

---

### 문제 8: 각 학과별 총점 합계 구하기

**문제:** 각 학과별 총점의 합계를 구하시오.

**정답:**
```sql
SELECT 
    department AS 학과,
    SUM(total_score) AS 총점합계
FROM student
GROUP BY department
ORDER BY 총점합계 DESC;
```

**해설:**
- `GROUP BY department`: 학과별로 그룹화
- `SUM(total_score)`: 각 학과의 총점 합계 계산
- **결과:**
  - 컴퓨터공학: 3,577점
  - 전자공학: 2,145점
  - 기계공학: 1,292점

**출력:**
```
학과          총점합계
컴퓨터공학    3577
전자공학      2145
기계공학      1292
```

---

### 문제 9: 평균 점수가 85점 이상인 학과 찾기

**문제:** 평균 점수가 85점 이상인 학과와 그 평균 점수를 구하시오.

**정답:**
```sql
SELECT 
    department AS 학과,
    AVG(average) AS 평균점수
FROM student
GROUP BY department
HAVING AVG(average) >= 85
ORDER BY 평균점수 DESC;
```

**또는:**
```sql
SELECT 
    department AS 학과,
    ROUND(AVG(average), 2) AS 평균점수
FROM student
GROUP BY department
HAVING AVG(average) >= 85
ORDER BY 평균점수 DESC;
```

**해설:**
- `GROUP BY department`: 학과별로 그룹화
- `HAVING AVG(average) >= 85`: 그룹화된 결과에 조건 적용
- **HAVING vs WHERE:**
  - `WHERE`: 그룹화 전 조건 (개별 행 필터링)
  - `HAVING`: 그룹화 후 조건 (그룹 결과 필터링)
- **결과:**
  - 컴퓨터공학: 89.78점
  - 전자공학: 89.17점

**출력:**
```
학과          평균점수
컴퓨터공학    89.7750
전자공학      89.1667
```

**주의사항:**
```sql
-- 잘못된 예시 (WHERE는 집계함수 사용 불가)
SELECT department, AVG(average)
FROM student
WHERE AVG(average) >= 85  -- 오류!
GROUP BY department;

-- 올바른 예시 (HAVING 사용)
SELECT department, AVG(average)
FROM student
GROUP BY department
HAVING AVG(average) >= 85;  -- 정상 작동
```

---

### 문제 10: 학생 수가 5명 이상인 학과 찾기

**문제:** 학생 수가 5명 이상인 학과와 학생 수를 구하시오.

**정답:**
```sql
SELECT 
    department AS 학과,
    COUNT(*) AS 학생수
FROM student
GROUP BY department
HAVING COUNT(*) >= 5
ORDER BY 학생수 DESC;
```

**해설:**
- `GROUP BY department`: 학과별로 그룹화
- `COUNT(*)`: 각 학과의 학생 수 계산
- `HAVING COUNT(*) >= 5`: 학생 수가 5명 이상인 학과만 선택
- **결과:**
  - 컴퓨터공학: 10명
  - 전자공학: 6명

**출력:**
```
학과          학생수
컴퓨터공학    10
전자공학      6
```

---

### 문제 11: 각 학과별 국어 평균 점수 구하기

**문제:** 각 학과별 국어 평균 점수를 구하시오.

**정답:**
```sql
SELECT 
    department AS 학과,
    AVG(korean) AS 국어평균점수
FROM student
GROUP BY department
ORDER BY 국어평균점수 DESC;
```

**또는 소수점 둘째 자리까지:**
```sql
SELECT 
    department AS 학과,
    ROUND(AVG(korean), 2) AS 국어평균점수
FROM student
GROUP BY department
ORDER BY 국어평균점수 DESC;
```

**해설:**
- `GROUP BY department`: 학과별로 그룹화
- `AVG(korean)`: 각 학과의 국어 평균 점수 계산
- **결과:**
  - 컴퓨터공학: 약 88.50점
  - 전자공학: 약 88.17점
  - 기계공학: 약 79.25점

**출력:**
```
학과          국어평균점수
컴퓨터공학    88.5000
전자공학      88.1667
기계공학      79.2500
```

---

### 문제 12: 각 학년별 수학 최고 점수 구하기

**문제:** 각 학년별 수학 최고 점수를 구하시오.

**정답:**
```sql
SELECT 
    grade AS 학년,
    MAX(math) AS 수학최고점수
FROM student
GROUP BY grade
ORDER BY grade;
```

**해설:**
- `GROUP BY grade`: 학년별로 그룹화
- `MAX(math)`: 각 학년의 수학 최고 점수
- **결과:**
  - 1학년: 95점
  - 2학년: 93점
  - 3학년: 98점

**출력:**
```
학년  수학최고점수
1     95
2     93
3     98
```

---

### 문제 13: 전체 학생의 총점 합계 구하기

**문제:** 전체 학생의 총점 합계를 구하시오.

**정답:**
```sql
SELECT SUM(total_score) AS 전체총점합계
FROM student;
```

**해설:**
- `SUM(total_score)`: 모든 학생의 총점 합계
- **결과: 6,996**

**출력:**
```
전체총점합계
6996
```

---

### 문제 14: 평균 점수가 90점 이상인 학생 수 구하기

**문제:** 평균 점수가 90점 이상인 학생 수를 구하시오.

**정답:**
```sql
SELECT COUNT(*) AS 학생수
FROM student
WHERE average >= 90;
```

**해설:**
- `WHERE average >= 90`: 평균 점수가 90점 이상인 학생만 필터링
- `COUNT(*)`: 조건을 만족하는 학생 수 계산
- **결과: 6명**

**출력:**
```
학생수
6
```

**참고:** WHERE는 그룹화 전에 사용하므로 집계함수 없이도 사용 가능

---

### 문제 15: 각 학과별 평균 점수와 학생 수를 함께 구하기

**문제:** 각 학과별 평균 점수와 학생 수를 함께 구하시오.

**정답:**
```sql
SELECT 
    department AS 학과,
    COUNT(*) AS 학생수,
    AVG(average) AS 평균점수
FROM student
GROUP BY department
ORDER BY 평균점수 DESC;
```

**또는 소수점 둘째 자리까지:**
```sql
SELECT 
    department AS 학과,
    COUNT(*) AS 학생수,
    ROUND(AVG(average), 2) AS 평균점수
FROM student
GROUP BY department
ORDER BY 평균점수 DESC;
```

**해설:**
- `GROUP BY department`: 학과별로 그룹화
- `COUNT(*)`: 학생 수 계산
- `AVG(average)`: 평균 점수 계산
- 여러 집계함수를 함께 사용 가능
- **결과:**
  - 컴퓨터공학: 10명, 89.78점
  - 전자공학: 6명, 89.17점
  - 기계공학: 4명, 80.75점

**출력:**
```
학과          학생수  평균점수
컴퓨터공학    10     89.7750
전자공학      6      89.1667
기계공학      4      80.7500
```

---

### 문제 16: 학과별 통계 (학생수, 평균, 최고점, 최저점)

**문제:** 각 학과별로 학생 수, 평균 점수, 최고 점수, 최저 점수를 모두 구하시오.

**정답:**
```sql
SELECT 
    department AS 학과,
    COUNT(*) AS 학생수,
    ROUND(AVG(average), 2) AS 평균점수,
    MAX(average) AS 최고점수,
    MIN(average) AS 최저점수
FROM student
GROUP BY department
ORDER BY 평균점수 DESC;
```

**해설:**
- 여러 집계함수를 한 번에 사용 가능
- `COUNT(*)`: 학생 수
- `AVG(average)`: 평균 점수
- `MAX(average)`: 최고 점수
- `MIN(average)`: 최저 점수
- **결과:**
  - 컴퓨터공학: 10명, 평균 89.78점, 최고 95.25점, 최저 81.25점
  - 전자공학: 6명, 평균 89.17점, 최고 92.00점, 최저 86.50점
  - 기계공학: 4명, 평균 80.75점, 최고 82.00점, 최저 78.75점

**출력:**
```
학과          학생수  평균점수  최고점수  최저점수
컴퓨터공학    10     89.78    95.25    81.25
전자공학      6      89.17    92.00    86.50
기계공학      4      80.75    82.00    78.75
```

---

### 문제 17: 학년별 통계 (학생수, 평균, 총점 합계)

**문제:** 각 학년별로 학생 수, 평균 점수, 총점 합계를 구하시오.

**정답:**
```sql
SELECT 
    grade AS 학년,
    COUNT(*) AS 학생수,
    ROUND(AVG(average), 2) AS 평균점수,
    SUM(total_score) AS 총점합계
FROM student
GROUP BY grade
ORDER BY grade;
```

**해설:**
- `GROUP BY grade`: 학년별로 그룹화
- 여러 집계함수 동시 사용
- **결과:**
  - 1학년: 7명, 평균 약 87.36점, 총점 합계 2,471점
  - 2학년: 6명, 평균 약 85.33점, 총점 합계 2,050점
  - 3학년: 7명, 평균 약 89.36점, 총점 합계 2,493점

**출력:**
```
학년  학생수  평균점수  총점합계
1     7       87.36    2471
2     6       85.33    2050
3     7       89.36    2493
```

---

### 문제 18: 평균 점수가 전체 평균보다 높은 학과 찾기

**문제:** 평균 점수가 전체 평균보다 높은 학과를 구하시오.

**정답:**
```sql
SELECT 
    department AS 학과,
    AVG(average) AS 평균점수
FROM student
GROUP BY department
HAVING AVG(average) > (SELECT AVG(average) FROM student)
ORDER BY 평균점수 DESC;
```

**해설:**
- **서브쿼리**: `(SELECT AVG(average) FROM student)` - 전체 평균 계산
- `HAVING`: 그룹화된 결과에 조건 적용
- 서브쿼리 결과와 비교
- **전체 평균: 약 87.35점**
- **결과:**
  - 컴퓨터공학: 89.78점
  - 전자공학: 89.17점

**출력:**
```
학과          평균점수
컴퓨터공학    89.7750
전자공학      89.1667
```

**단계별 설명:**
1. 서브쿼리 실행: 전체 평균 계산 (87.35점)
2. 각 학과별 평균 계산
3. HAVING으로 비교: 학과 평균 > 전체 평균
4. 조건 만족하는 학과만 출력

---

### 문제 19: 각 학과별 영어 점수 합계와 평균 구하기

**문제:** 각 학과별 영어 점수 합계와 평균을 구하시오.

**정답:**
```sql
SELECT 
    department AS 학과,
    SUM(english) AS 영어점수합계,
    ROUND(AVG(english), 2) AS 영어평균점수
FROM student
GROUP BY department
ORDER BY 영어평균점수 DESC;
```

**해설:**
- `GROUP BY department`: 학과별로 그룹화
- `SUM(english)`: 영어 점수 합계
- `AVG(english)`: 영어 평균 점수
- **결과:**
  - 컴퓨터공학: 합계 905점, 평균 90.50점
  - 전자공학: 합계 532점, 평균 88.67점
  - 기계공학: 합계 323점, 평균 80.75점

**출력:**
```
학과          영어점수합계  영어평균점수
컴퓨터공학    905         90.50
전자공학      532         88.67
기계공학      323         80.75
```

---

### 문제 20: 학년별 과학 점수 최고점과 최저점의 차이 구하기

**문제:** 각 학년별 과학 점수 최고점과 최저점의 차이를 구하시오.

**정답:**
```sql
SELECT 
    grade AS 학년,
    MAX(science) AS 최고점수,
    MIN(science) AS 최저점수,
    MAX(science) - MIN(science) AS 점수차이
FROM student
GROUP BY grade
ORDER BY grade;
```

**해설:**
- `GROUP BY grade`: 학년별로 그룹화
- `MAX(science)`: 최고 점수
- `MIN(science)`: 최저 점수
- `MAX(science) - MIN(science)`: 차이 계산
- 집계함수 결과로 계산식 사용 가능
- **결과:**
  - 1학년: 최고 92점, 최저 82점, 차이 10점
  - 2학년: 최고 91점, 최저 79점, 차이 12점
  - 3학년: 최고 96점, 최저 83점, 차이 13점

**출력:**
```
학년  최고점수  최저점수  점수차이
1     92       82       10
2     91       79       12
3     96       83       13
```

---

## 집계함수 상세 정리

### 1. COUNT() - 개수

**기본 사용법:**
```sql
COUNT(*)              -- 모든 행 개수 (NULL 포함)
COUNT(컬럼명)         -- NULL이 아닌 행 개수
COUNT(DISTINCT 컬럼명) -- 중복 제거 후 개수
```

**예시:**
```sql
SELECT COUNT(*) FROM student;                    -- 전체 학생 수
SELECT COUNT(department) FROM student;           -- department가 NULL이 아닌 학생 수
SELECT COUNT(DISTINCT department) FROM student;  -- 학과 종류 수
```

---

### 2. SUM() - 합계

**기본 사용법:**
```sql
SUM(컬럼명)  -- 컬럼 값의 합계
```

**예시:**
```sql
SELECT SUM(total_score) FROM student;  -- 전체 총점 합계
SELECT SUM(korean) FROM student;       -- 국어 점수 합계
```

**주의사항:**
- NULL 값은 계산에서 제외됨
- 숫자 타입만 사용 가능

---

### 3. AVG() - 평균

**기본 사용법:**
```sql
AVG(컬럼명)  -- 컬럼 값의 평균
```

**예시:**
```sql
SELECT AVG(average) FROM student;  -- 전체 평균 점수
SELECT AVG(korean) FROM student;    -- 국어 평균 점수
```

**주의사항:**
- NULL 값은 계산에서 제외됨
- 결과는 소수점 포함 가능
- `ROUND()`로 소수점 자리수 제한 가능

---

### 4. MAX() - 최대값

**기본 사용법:**
```sql
MAX(컬럼명)  -- 컬럼 값의 최대값
```

**예시:**
```sql
SELECT MAX(average) FROM student;  -- 최고 평균 점수
SELECT MAX(korean) FROM student;    -- 국어 최고 점수
```

**주의사항:**
- 숫자, 날짜, 문자열 모두 사용 가능
- 문자열의 경우 사전식 순서로 비교

---

### 5. MIN() - 최소값

**기본 사용법:**
```sql
MIN(컬럼명)  -- 컬럼 값의 최소값
```

**예시:**
```sql
SELECT MIN(average) FROM student;  -- 최저 평균 점수
SELECT MIN(korean) FROM student;    -- 국어 최저 점수
```

---

## GROUP BY 상세 설명

### GROUP BY 기본

**형식:**
```sql
SELECT 컬럼1, 집계함수(컬럼2)
FROM 테이블명
GROUP BY 컬럼1;
```

**규칙:**
- SELECT에 일반 컬럼이 있으면 GROUP BY에 포함되어야 함
- 집계함수는 GROUP BY 없이도 사용 가능 (전체 집계)
- 집계함수와 일반 컬럼을 함께 사용할 때는 GROUP BY 필수

**예시:**
```sql
-- 올바른 예시
SELECT department, COUNT(*)
FROM student
GROUP BY department;

-- 잘못된 예시 (오류 발생)
SELECT department, COUNT(*)
FROM student;
-- department는 GROUP BY 없이 사용 불가
```

---

## HAVING vs WHERE

### WHERE

**용도:** 그룹화 전에 행을 필터링

**예시:**
```sql
SELECT department, AVG(average)
FROM student
WHERE grade = 1          -- 1학년만 선택 (그룹화 전)
GROUP BY department;
```

**사용 가능:**
- 일반 컬럼
- 비교 연산자
- 집계함수 사용 불가

---

### HAVING

**용도:** 그룹화 후에 그룹을 필터링

**예시:**
```sql
SELECT department, AVG(average)
FROM student
GROUP BY department
HAVING AVG(average) >= 85;  -- 평균 85점 이상인 학과만 (그룹화 후)
```

**사용 가능:**
- 집계함수
- 그룹화된 컬럼
- WHERE와 함께 사용 가능

---

### WHERE와 HAVING 함께 사용

```sql
SELECT 
    department AS 학과,
    COUNT(*) AS 학생수,
    AVG(average) AS 평균점수
FROM student
WHERE grade >= 2              -- 2학년 이상만 선택 (그룹화 전)
GROUP BY department
HAVING AVG(average) >= 85     -- 평균 85점 이상인 학과만 (그룹화 후)
ORDER BY 평균점수 DESC;
```

**실행 순서:**
1. FROM: 테이블 선택
2. WHERE: 조건에 맞는 행 필터링
3. GROUP BY: 그룹화
4. HAVING: 그룹 결과 필터링
5. SELECT: 결과 선택
6. ORDER BY: 정렬

---

## 집계함수와 NULL

### NULL 처리

**집계함수는 NULL 값을 제외하고 계산합니다:**

```sql
-- NULL이 있는 경우
INSERT INTO student (name, korean, english) VALUES ('테스트', NULL, 90);

-- COUNT(*) vs COUNT(korean)
SELECT COUNT(*) FROM student;        -- NULL 포함, 전체 행 개수
SELECT COUNT(korean) FROM student;   -- NULL 제외, korean이 NULL이 아닌 행 개수

-- AVG, SUM, MAX, MIN도 NULL 제외
SELECT AVG(korean) FROM student;     -- NULL 제외하고 평균 계산
```

---

## 실전 활용 예시

### 예시 1: 학과별 상세 통계

```sql
SELECT 
    department AS 학과,
    COUNT(*) AS 학생수,
    ROUND(AVG(average), 2) AS 평균점수,
    MAX(average) AS 최고점수,
    MIN(average) AS 최저점수,
    SUM(total_score) AS 총점합계
FROM student
GROUP BY department
HAVING COUNT(*) >= 5
ORDER BY 평균점수 DESC;
```

**결과:** 학생 수가 5명 이상인 학과의 상세 통계

---

### 예시 2: 학년별 과목별 평균

```sql
SELECT 
    grade AS 학년,
    ROUND(AVG(korean), 2) AS 국어평균,
    ROUND(AVG(english), 2) AS 영어평균,
    ROUND(AVG(math), 2) AS 수학평균,
    ROUND(AVG(science), 2) AS 과학평균
FROM student
GROUP BY grade
ORDER BY grade;
```

**결과:** 각 학년별 4과목 평균 점수

---

### 예시 3: 복합 조건 통계

```sql
SELECT 
    department AS 학과,
    grade AS 학년,
    COUNT(*) AS 학생수,
    ROUND(AVG(average), 2) AS 평균점수
FROM student
WHERE average >= 80              -- 80점 이상만 (그룹화 전)
GROUP BY department, grade        -- 학과와 학년으로 그룹화
HAVING COUNT(*) >= 2              -- 학생 수 2명 이상 (그룹화 후)
ORDER BY department, grade;
```

**결과:** 학과별 학년별 통계 (80점 이상, 학생 수 2명 이상)

---

## 체크리스트

- [x] COUNT() 함수 사용법
- [x] SUM() 함수 사용법
- [x] AVG() 함수 사용법
- [x] MAX() 함수 사용법
- [x] MIN() 함수 사용법
- [x] GROUP BY 사용법
- [x] HAVING 사용법
- [x] WHERE vs HAVING 차이
- [x] 여러 집계함수 함께 사용
- [x] 서브쿼리와 집계함수 조합

---

## 주의사항

### 1. GROUP BY 규칙

```sql
-- 올바른 예시
SELECT department, COUNT(*)
FROM student
GROUP BY department;

-- 잘못된 예시 (오류)
SELECT name, department, COUNT(*)
FROM student
GROUP BY department;
-- name은 GROUP BY에 없으므로 오류 발생
```

### 2. HAVING 사용 위치

```sql
-- 올바른 순서
SELECT ...
FROM ...
WHERE ...
GROUP BY ...
HAVING ...
ORDER BY ...
```

### 3. 집계함수 중첩 불가

```sql
-- 잘못된 예시 (오류)
SELECT AVG(SUM(total_score))
FROM student
GROUP BY department;

-- 올바른 예시 (서브쿼리 사용)
SELECT AVG(total_sum)
FROM (
    SELECT SUM(total_score) AS total_sum
    FROM student
    GROUP BY department
) AS subquery;
```

---

**집계함수를 마스터하면 데이터 분석이 쉬워집니다!**
