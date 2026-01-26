# SQLD 시험 준비 교안

## (SQL Developer 자격증 대비)

---

## 목차

1. [SQLD 시험 개요](#1-sqld-시험-개요)
2. [데이터 모델링의 이해](#2-데이터-모델링의-이해)
3. [SQL 기본 및 활용](#3-sql-기본-및-활용)
4. [SQL 고급 활용 및 튜닝](#4-sql-고급-활용-및-튜닝)
5. [기출 문제 유형 분석](#5-기출-문제-유형-분석)
6. [핵심 개념 정리](#6-핵심-개념-정리)
7. [시험 전 체크리스트](#7-시험-전-체크리스트)

---

## 1. SQLD 시험 개요

### 1-1. SQLD란?

**SQLD (SQL Developer)**는 **한국데이터산업진흥원**에서 주관하는 **SQL 개발자 자격증**입니다.

- **목적**: SQL을 활용한 데이터베이스 개발 역량 검증
- **대상**: 데이터베이스 개발자, 데이터 분석가, 백엔드 개발자
- **난이도**: 중급 수준

### 1-2. 시험 정보

| 항목 | 내용 |
|------|------|
| **시험 시간** | 90분 |
| **문제 수** | 50문제 (객관식) |
| **합격 기준** | 60점 이상 (100점 만점) |
| **시험 비용** | 약 50,000원 |
| **시험 횟수** | 연 4회 (분기별) |

### 1-3. 출제 범위 및 배점

| 과목 | 세부 내용 | 배점 |
|------|----------|------|
| **데이터 모델링의 이해** | 데이터 모델링, 정규화, ERD | 10문제 (20점) |
| **SQL 기본 및 활용** | DDL, DML, DCL, 함수, JOIN | 30문제 (60점) |
| **SQL 고급 활용 및 튜닝** | 서브쿼리, 집합 연산자, 윈도우 함수, 옵티마이저 | 10문제 (20점) |

### 1-4. 시험 전략

- ✅ **SQL 기본 및 활용**이 가장 중요 (60점)
- ✅ **데이터 모델링**은 개념 이해 중심
- ✅ **SQL 고급**은 실무 경험이 도움됨
- ✅ **기출 문제** 반복 학습 필수

---

## 2. 데이터 모델링의 이해

### 2-1. 데이터 모델링의 개념

**데이터 모델링**은 현실 세계의 데이터를 데이터베이스에 저장하기 위한 구조를 설계하는 과정입니다.

#### 데이터 모델의 3단계

1. **개념적 데이터 모델 (Conceptual Data Model)**
   - 업무의 본질을 파악
   - 엔티티와 관계 정의
   - ERD 작성

2. **논리적 데이터 모델 (Logical Data Model)**
   - 정규화 수행
   - 속성 정의
   - 관계 설정

3. **물리적 데이터 모델 (Physical Data Model)**
   - 실제 DBMS에 맞게 구현
   - 테이블 생성
   - 인덱스 설계

### 2-2. 엔티티 (Entity)

**엔티티**는 데이터베이스에 저장할 대상입니다.

#### 엔티티의 특징

- ✅ 업무에서 관리하고자 하는 대상
- ✅ 유일한 식별자(Identifier)를 가짐
- ✅ 두 개 이상의 인스턴스 존재
- ✅ 속성(Attribute)을 가짐

#### 엔티티의 분류

| 분류 | 설명 | 예시 |
|------|------|------|
| **유형 엔티티** | 물리적 형태가 있는 것 | 학생, 상품, 직원 |
| **개념 엔티티** | 개념적 존재 | 학과, 부서 |
| **사건 엔티티** | 업무 수행 시 발생 | 주문, 수강신청 |

### 2-3. 속성 (Attribute)

**속성**은 엔티티가 가지는 특성입니다.

#### 속성의 분류

| 분류 | 설명 | 예시 |
|------|------|------|
| **기본 속성** | 업무로부터 도출된 본래의 속성 | 학생명, 학번 |
| **설계 속성** | 데이터 모델링 과정에서 생성 | 등록일시, 수정일시 |
| **파생 속성** | 다른 속성으로부터 계산된 값 | 나이, 총점 |

### 2-4. 관계 (Relationship)

**관계**는 엔티티 간의 연관성을 나타냅니다.

#### 관계의 차수 (Cardinality)

| 관계 | 설명 | 예시 |
|------|------|------|
| **1:1** | 한 엔티티가 다른 엔티티와 1:1 대응 | 학생 ↔ 학생증 |
| **1:N** | 한 엔티티가 여러 엔티티와 대응 | 학과 ↔ 학생 |
| **M:N** | 여러 엔티티가 여러 엔티티와 대응 | 학생 ↔ 과목 |

#### 관계의 선택성 (Optionality)

- **필수 관계**: 반드시 존재해야 함 (|)
- **선택 관계**: 존재하지 않을 수 있음 (O)

### 2-5. 정규화 (Normalization)

**정규화**는 데이터 중복을 제거하고 데이터 무결성을 보장하기 위한 과정입니다.

#### 정규화의 목적

- ✅ 데이터 중복 제거
- ✅ 데이터 무결성 보장
- ✅ 저장 공간 효율화
- ✅ 데이터 일관성 유지

#### 정규화 단계

**1NF (제1정규형): 원자값**

- 모든 속성은 원자값(Atomic Value)이어야 함
- 반복되는 그룹 제거

**예시:**
```
❌ 비정규화
학생번호 | 학생명 | 과목
---------+--------+--------
1        | 홍길동  | 수학,영어

✅ 1NF
학생번호 | 학생명 | 과목
---------+--------+------
1        | 홍길동  | 수학
1        | 홍길동  | 영어
```

**2NF (제2정규형): 부분 함수 종속 제거**

- 1NF이면서 부분 함수 종속 제거
- 기본키의 일부에만 종속된 속성 제거

**예시:**
```
❌ 부분 함수 종속
주문번호 | 상품코드 | 상품명 | 수량 | 주문금액
---------+----------+--------+------+----------
O001     | P001     | 노트북   | 1    | 1000000

✅ 2NF
주문 테이블:
주문번호 | 상품코드 | 수량 | 주문금액

상품 테이블:
상품코드 | 상품명
```

**3NF (제3정규형): 이행 함수 종속 제거**

- 2NF이면서 이행 함수 종속 제거
- 기본키가 아닌 속성에 종속된 속성 제거

**예시:**
```
❌ 이행 함수 종속
학생번호 | 학생명 | 학과코드 | 학과명
---------+--------+----------+--------
1        | 홍길동  | D001     | 컴퓨터공학

✅ 3NF
학생 테이블:
학생번호 | 학생명 | 학과코드

학과 테이블:
학과코드 | 학과명
```

**BCNF (Boyce-Codd 정규형)**

- 모든 결정자가 후보키(Candidate Key)여야 함
- 3NF의 강화된 형태

### 2-6. 반정규화 (Denormalization)

**반정규화**는 성능 향상을 위해 정규화를 역으로 수행하는 것입니다.

#### 반정규화의 목적

- ✅ 조회 성능 향상
- ✅ 복잡한 JOIN 감소
- ✅ 데이터 중복 허용

#### 반정규화 기법

| 기법 | 설명 |
|------|------|
| **테이블 통합** | 1:1 관계 테이블 통합 |
| **테이블 분할** | 수평/수직 분할 |
| **중복 컬럼 추가** | 자주 조회하는 컬럼 중복 |
| **파생 컬럼 추가** | 계산된 값 저장 |
| **이력 테이블 분리** | 이력 데이터 별도 관리 |

---

## 3. SQL 기본 및 활용

### 3-1. DDL (Data Definition Language)

**DDL**은 데이터베이스 구조를 정의하는 언어입니다.

#### CREATE TABLE

```sql
CREATE TABLE student (
    id INT PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    age INT,
    email VARCHAR(50) UNIQUE,
    created_date DATE DEFAULT CURRENT_DATE
);
```

#### ALTER TABLE

```sql
-- 컬럼 추가
ALTER TABLE student ADD COLUMN phone VARCHAR(20);

-- 컬럼 수정
ALTER TABLE student MODIFY COLUMN name VARCHAR(30);

-- 컬럼 삭제
ALTER TABLE student DROP COLUMN phone;

-- 제약조건 추가
ALTER TABLE student ADD CONSTRAINT pk_student PRIMARY KEY (id);
```

#### DROP TABLE

```sql
DROP TABLE student;
```

### 3-2. DML (Data Manipulation Language)

**DML**은 데이터를 조작하는 언어입니다.

#### INSERT

```sql
-- 기본 INSERT
INSERT INTO student (id, name, age)
VALUES (1, '홍길동', 20);

-- 여러 행 삽입
INSERT INTO student (id, name, age)
VALUES 
    (2, '김영희', 25),
    (3, '박철수', 22);

-- 서브쿼리 사용
INSERT INTO student (id, name, age)
SELECT id, name, age FROM temp_student;
```

#### UPDATE

```sql
-- 단일 행 수정
UPDATE student
SET age = 21
WHERE id = 1;

-- 여러 컬럼 수정
UPDATE student
SET age = 21, email = 'hong@example.com'
WHERE id = 1;

-- 서브쿼리 사용
UPDATE student
SET age = (SELECT AVG(age) FROM student)
WHERE id = 1;
```

#### DELETE

```sql
-- 조건부 삭제
DELETE FROM student
WHERE id = 1;

-- 전체 삭제
DELETE FROM student;

-- 서브쿼리 사용
DELETE FROM student
WHERE id IN (SELECT id FROM temp_student);
```

### 3-3. DCL (Data Control Language)

**DCL**은 데이터베이스 접근 권한을 제어하는 언어입니다.

#### GRANT (권한 부여)

```sql
-- 사용자에게 권한 부여
GRANT SELECT, INSERT ON student TO user1;

-- 모든 권한 부여
GRANT ALL PRIVILEGES ON student TO user1;

-- 권한을 다른 사용자에게도 부여 가능하게
GRANT SELECT ON student TO user1 WITH GRANT OPTION;
```

#### REVOKE (권한 회수)

```sql
-- 권한 회수
REVOKE SELECT ON student FROM user1;

-- 모든 권한 회수
REVOKE ALL PRIVILEGES ON student FROM user1;
```

### 3-4. SELECT 기본

#### 기본 문법

```sql
SELECT 컬럼1, 컬럼2, ...
FROM 테이블명
WHERE 조건
GROUP BY 컬럼
HAVING 그룹조건
ORDER BY 컬럼
LIMIT 개수;
```

#### 실행 순서

```
FROM → WHERE → GROUP BY → HAVING → SELECT → ORDER BY → LIMIT
```

### 3-5. JOIN

#### INNER JOIN

```sql
SELECT m.name, o.price
FROM member m
INNER JOIN orders o
ON m.id = o.member_id;
```

#### LEFT JOIN

```sql
SELECT m.name, o.price
FROM member m
LEFT JOIN orders o
ON m.id = o.member_id;
```

#### RIGHT JOIN

```sql
SELECT m.name, o.price
FROM member m
RIGHT JOIN orders o
ON m.id = o.member_id;
```

#### FULL OUTER JOIN (MySQL에서는 지원 안 함)

```sql
-- MySQL에서는 UNION으로 구현
SELECT m.name, o.price
FROM member m
LEFT JOIN orders o ON m.id = o.member_id
UNION
SELECT m.name, o.price
FROM member m
RIGHT JOIN orders o ON m.id = o.member_id;
```

### 3-6. 집계 함수

| 함수 | 설명 | 예시 |
|------|------|------|
| `COUNT(*)` | 행의 개수 | `SELECT COUNT(*) FROM student;` |
| `SUM(컬럼)` | 합계 | `SELECT SUM(price) FROM orders;` |
| `AVG(컬럼)` | 평균 | `SELECT AVG(age) FROM student;` |
| `MAX(컬럼)` | 최댓값 | `SELECT MAX(price) FROM orders;` |
| `MIN(컬럼)` | 최솟값 | `SELECT MIN(price) FROM orders;` |

### 3-7. GROUP BY와 HAVING

```sql
SELECT member_id, SUM(price) AS total_price
FROM orders
GROUP BY member_id
HAVING SUM(price) >= 100000;
```

**WHERE vs HAVING:**
- **WHERE**: GROUP BY 이전, 개별 행 필터링
- **HAVING**: GROUP BY 이후, 그룹 필터링

### 3-8. 함수

#### 문자열 함수

| 함수 | 설명 | 예시 |
|------|------|------|
| `CONCAT(str1, str2)` | 문자열 연결 | `CONCAT('Hello', 'World')` → 'HelloWorld' |
| `SUBSTRING(str, pos, len)` | 부분 문자열 | `SUBSTRING('Hello', 1, 3)` → 'Hel' |
| `LENGTH(str)` | 문자열 길이 | `LENGTH('Hello')` → 5 |
| `UPPER(str)` | 대문자 변환 | `UPPER('hello')` → 'HELLO' |
| `LOWER(str)` | 소문자 변환 | `LOWER('HELLO')` → 'hello' |
| `TRIM(str)` | 공백 제거 | `TRIM(' Hello ')` → 'Hello' |
| `REPLACE(str, old, new)` | 문자열 치환 | `REPLACE('Hello', 'l', 'L')` → 'HeLLo' |

#### 숫자 함수

| 함수 | 설명 | 예시 |
|------|------|------|
| `ABS(n)` | 절댓값 | `ABS(-5)` → 5 |
| `ROUND(n, d)` | 반올림 | `ROUND(3.14159, 2)` → 3.14 |
| `CEIL(n)` | 올림 | `CEIL(3.14)` → 4 |
| `FLOOR(n)` | 내림 | `FLOOR(3.14)` → 3 |
| `MOD(n, m)` | 나머지 | `MOD(10, 3)` → 1 |
| `POWER(n, m)` | 거듭제곱 | `POWER(2, 3)` → 8 |

#### 날짜 함수

| 함수 | 설명 | 예시 |
|------|------|------|
| `NOW()` | 현재 날짜/시간 | `NOW()` → '2024-01-15 14:30:00' |
| `CURDATE()` | 현재 날짜 | `CURDATE()` → '2024-01-15' |
| `CURTIME()` | 현재 시간 | `CURTIME()` → '14:30:00' |
| `DATE_FORMAT(date, format)` | 날짜 포맷 | `DATE_FORMAT(NOW(), '%Y-%m-%d')` |
| `DATEDIFF(date1, date2)` | 날짜 차이 | `DATEDIFF('2024-01-20', '2024-01-15')` → 5 |
| `DATE_ADD(date, INTERVAL n DAY)` | 날짜 더하기 | `DATE_ADD('2024-01-15', INTERVAL 7 DAY)` |

#### NULL 처리 함수

| 함수 | 설명 | 예시 |
|------|------|------|
| `IFNULL(expr1, expr2)` | NULL이면 expr2 반환 | `IFNULL(age, 0)` |
| `COALESCE(expr1, expr2, ...)` | 첫 번째 NULL이 아닌 값 반환 | `COALESCE(age, 0, 1)` |
| `NULLIF(expr1, expr2)` | 같으면 NULL 반환 | `NULLIF(age, 0)` |

### 3-9. CASE WHEN

```sql
SELECT 
    name,
    CASE 
        WHEN age >= 20 THEN '성인'
        WHEN age >= 13 THEN '청소년'
        ELSE '어린이'
    END AS age_group
FROM student;
```

---

## 4. SQL 고급 활용 및 튜닝

### 4-1. 서브쿼리 (Subquery)

**서브쿼리**는 쿼리 안에 포함된 쿼리입니다.

#### 스칼라 서브쿼리 (Scalar Subquery)

```sql
-- 단일 값을 반환하는 서브쿼리
SELECT 
    name,
    (SELECT AVG(age) FROM student) AS avg_age
FROM student;
```

#### 인라인 뷰 (Inline View)

```sql
-- FROM 절에 사용되는 서브쿼리
SELECT *
FROM (
    SELECT name, age
    FROM student
    WHERE age >= 20
) AS adult_student;
```

#### 서브쿼리 연산자

**IN / NOT IN**

```sql
SELECT *
FROM student
WHERE id IN (SELECT student_id FROM enrollment);
```

**EXISTS / NOT EXISTS**

```sql
SELECT *
FROM student s
WHERE EXISTS (
    SELECT 1
    FROM enrollment e
    WHERE e.student_id = s.id
);
```

**ANY / ALL**

```sql
-- ANY: 서브쿼리 결과 중 하나라도 만족
SELECT *
FROM student
WHERE age > ANY (SELECT age FROM student WHERE name LIKE '김%');

-- ALL: 서브쿼리 결과 모두 만족
SELECT *
FROM student
WHERE age > ALL (SELECT age FROM student WHERE name LIKE '김%');
```

### 4-2. 집합 연산자 (Set Operators)

#### UNION (중복 제거)

```sql
SELECT name FROM student
UNION
SELECT name FROM teacher;
```

#### UNION ALL (중복 허용)

```sql
SELECT name FROM student
UNION ALL
SELECT name FROM teacher;
```

#### INTERSECT (교집합)

```sql
-- MySQL에서는 지원 안 함, JOIN으로 구현
SELECT DISTINCT s.name
FROM student s
INNER JOIN teacher t ON s.name = t.name;
```

#### EXCEPT (차집합)

```sql
-- MySQL에서는 지원 안 함, NOT EXISTS로 구현
SELECT name
FROM student
WHERE NOT EXISTS (
    SELECT 1 FROM teacher WHERE teacher.name = student.name
);
```

### 4-3. 윈도우 함수 (Window Function)

**윈도우 함수**는 행의 집합에 대해 계산을 수행하는 함수입니다.

#### ROW_NUMBER()

```sql
SELECT 
    name,
    age,
    ROW_NUMBER() OVER (ORDER BY age DESC) AS rn
FROM student;
```

#### RANK()

```sql
SELECT 
    name,
    score,
    RANK() OVER (ORDER BY score DESC) AS ranking
FROM student;
```

#### DENSE_RANK()

```sql
SELECT 
    name,
    score,
    DENSE_RANK() OVER (ORDER BY score DESC) AS ranking
FROM student;
```

#### PARTITION BY

```sql
SELECT 
    department,
    name,
    salary,
    ROW_NUMBER() OVER (PARTITION BY department ORDER BY salary DESC) AS dept_rank
FROM employee;
```

#### 집계 함수와 윈도우 함수

```sql
SELECT 
    name,
    salary,
    SUM(salary) OVER (PARTITION BY department) AS dept_total,
    AVG(salary) OVER (PARTITION BY department) AS dept_avg
FROM employee;
```

### 4-4. 옵티마이저 (Optimizer)

**옵티마이저**는 SQL 쿼리를 최적의 실행 계획으로 변환하는 모듈입니다.

#### 실행 계획 확인 (EXPLAIN)

```sql
EXPLAIN SELECT * FROM student WHERE age >= 20;
```

#### 인덱스 활용

```sql
-- 인덱스 생성
CREATE INDEX idx_age ON student(age);

-- 복합 인덱스
CREATE INDEX idx_name_age ON student(name, age);

-- 인덱스 삭제
DROP INDEX idx_age ON student;
```

### 4-5. 성능 튜닝

#### 인덱스 활용 원칙

- ✅ WHERE 절에 자주 사용되는 컬럼
- ✅ JOIN 조건에 사용되는 컬럼
- ✅ ORDER BY에 사용되는 컬럼
- ❌ 자주 변경되는 컬럼은 인덱스 비권장
- ❌ 카디널리티가 낮은 컬럼은 인덱스 비효율적

#### 쿼리 최적화 팁

1. **불필요한 SELECT * 사용 지양**
   ```sql
   -- ❌ 비효율적
   SELECT * FROM student;
   
   -- ✅ 효율적
   SELECT id, name FROM student;
   ```

2. **인덱스 활용 가능한 WHERE 조건 사용**
   ```sql
   -- ✅ 인덱스 활용 가능
   WHERE age >= 20
   
   -- ❌ 인덱스 활용 어려움
   WHERE age + 10 >= 30
   ```

3. **JOIN 순서 고려**
   ```sql
   -- 작은 테이블을 먼저 JOIN
   SELECT *
   FROM small_table s
   INNER JOIN large_table l ON s.id = l.id;
   ```

---

## 5. 기출 문제 유형 분석

### 5-1. 데이터 모델링 문제

**문제 유형:**
- 정규화 단계 판단
- 엔티티 관계 설정
- 속성 분류
- 정규화/반정규화 판단

**예시 문제:**

```
다음 중 제3정규형(3NF)의 조건으로 옳은 것은?

① 모든 속성이 원자값이어야 한다
② 부분 함수 종속이 없어야 한다
③ 이행 함수 종속이 없어야 한다
④ 모든 결정자가 후보키여야 한다

정답: ③
```

### 5-2. SQL 기본 문제

**문제 유형:**
- JOIN 결과 예측
- 집계 함수 결과 계산
- GROUP BY / HAVING 사용
- 함수 결과 계산

**예시 문제:**

```sql
-- 다음 쿼리의 결과는?

SELECT member_id, COUNT(*) AS cnt
FROM orders
GROUP BY member_id
HAVING COUNT(*) >= 2;

-- 주문 횟수가 2회 이상인 회원의 ID와 주문 횟수
```

### 5-3. SQL 고급 문제

**문제 유형:**
- 서브쿼리 결과 예측
- 윈도우 함수 결과 계산
- 실행 계획 분석
- 성능 튜닝 방법

**예시 문제:**

```sql
-- 다음 쿼리의 결과는?

SELECT 
    name,
    salary,
    RANK() OVER (ORDER BY salary DESC) AS rk
FROM employee;

-- 급여 내림차순으로 순위 매기기
```

### 5-4. 자주 출제되는 패턴

1. **JOIN 결과 행 수 계산**
2. **GROUP BY + HAVING 조합**
3. **서브쿼리 IN / EXISTS 차이**
4. **윈도우 함수 RANK vs DENSE_RANK**
5. **NULL 처리 함수**
6. **날짜 함수 계산**
7. **집계 함수와 GROUP BY**
8. **인덱스 활용 조건**

---

## 6. 핵심 개념 정리

### 6-1. 데이터 모델링 핵심

| 개념 | 설명 |
|------|------|
| **엔티티** | 데이터베이스에 저장할 대상 |
| **속성** | 엔티티의 특성 |
| **관계** | 엔티티 간의 연관성 |
| **정규화** | 데이터 중복 제거 |
| **반정규화** | 성능 향상을 위한 중복 허용 |

### 6-2. SQL 핵심 문법

#### 실행 순서

```
FROM → WHERE → GROUP BY → HAVING → SELECT → ORDER BY → LIMIT
```

#### JOIN 종류

| JOIN | 설명 |
|------|------|
| **INNER JOIN** | 양쪽에 데이터 있는 경우만 |
| **LEFT JOIN** | 왼쪽 테이블 모두 + 오른쪽 매칭 |
| **RIGHT JOIN** | 오른쪽 테이블 모두 + 왼쪽 매칭 |

#### 집계 함수

- `COUNT(*)`: 행의 개수
- `SUM(컬럼)`: 합계
- `AVG(컬럼)`: 평균
- `MAX(컬럼)`: 최댓값
- `MIN(컬럼)`: 최솟값

### 6-3. 서브쿼리 vs JOIN

| 구분 | 서브쿼리 | JOIN |
|------|---------|------|
| **성능** | 상대적으로 느림 | 상대적으로 빠름 |
| **가독성** | 복잡할 수 있음 | 명확함 |
| **사용 시기** | 단일 값 필요 시 | 여러 컬럼 필요 시 |

### 6-4. 윈도우 함수 비교

| 함수 | 동일 값 처리 |
|------|------------|
| **ROW_NUMBER()** | 순차 번호 (1, 2, 3, 4) |
| **RANK()** | 동일 값 건너뜀 (1, 2, 2, 4) |
| **DENSE_RANK()** | 동일 값 건너뛰지 않음 (1, 2, 2, 3) |

### 6-5. 인덱스 활용 조건

**인덱스 활용 가능:**
- `WHERE 컬럼 = 값`
- `WHERE 컬럼 > 값`
- `WHERE 컬럼 BETWEEN 값1 AND 값2`
- `WHERE 컬럼 IN (값1, 값2, ...)`
- `WHERE 컬럼 LIKE '값%'` (앞부분 일치)

**인덱스 활용 어려움:**
- `WHERE 함수(컬럼) = 값`
- `WHERE 컬럼 LIKE '%값%'` (앞부분 불일치)
- `WHERE 컬럼 IS NULL`

---

## 7. 시험 전 체크리스트

### 7-1. 시험 전 1주일

- [ ] 데이터 모델링 개념 정리
- [ ] SQL 기본 문법 복습
- [ ] JOIN, GROUP BY, HAVING 연습
- [ ] 서브쿼리 문제 풀이
- [ ] 윈도우 함수 연습
- [ ] 기출 문제 3회 이상 풀이

### 7-2. 시험 전 1일

- [ ] 핵심 개념 한 번 더 정리
- [ ] 자주 틀리는 문제 유형 확인
- [ ] 시험 시간 배분 계획
- [ ] 시험장 위치 확인
- [ ] 신분증 준비

### 7-3. 시험 당일

- [ ] 시험 시간 30분 전 도착
- [ ] 신분증 지참
- [ ] 계산기 준비 (필요 시)
- [ ] 시간 배분 지키기
  - 데이터 모델링: 15분 (10문제)
  - SQL 기본: 50분 (30문제)
  - SQL 고급: 20분 (10문제)
  - 검토: 5분

### 7-4. 시험 중 팁

1. **문제를 끝까지 읽기**
   - "가장 적절한 것", "옳지 않은 것" 구분

2. **모르는 문제는 표시하고 넘어가기**
   - 시간 낭비 방지

3. **계산 문제는 꼼꼼히 확인**
   - JOIN 결과 행 수
   - 집계 함수 결과

4. **마킹 실수 주의**
   - 문제 번호와 답안 번호 일치 확인

---

## 8. 실전 문제 풀이

### 문제 1: JOIN 결과 행 수

```sql
-- member 테이블: 4행
-- orders 테이블: 7행
-- member.id = orders.member_id

SELECT m.name, o.price
FROM member m
INNER JOIN orders o ON m.id = o.member_id;

-- 결과: 7행 (주문 건수만큼)
```

### 문제 2: GROUP BY + HAVING

```sql
SELECT member_id, SUM(price) AS total
FROM orders
GROUP BY member_id
HAVING SUM(price) >= 100000;

-- 총 주문금액이 10만원 이상인 회원
```

### 문제 3: 서브쿼리

```sql
SELECT name
FROM student
WHERE age > (SELECT AVG(age) FROM student);

-- 평균 나이보다 많은 학생
```

### 문제 4: 윈도우 함수

```sql
SELECT 
    name,
    score,
    RANK() OVER (ORDER BY score DESC) AS ranking
FROM student;

-- 점수 내림차순 순위
```

---

## 9. 참고 자료

### 9-1. 공식 자료

- 한국데이터산업진흥원: https://www.dataq.or.kr/
- SQLD 시험 안내: https://www.dataq.or.kr/www/sub/a/06.do

### 9-2. 추천 교재

- SQL 전문가 가이드 (한국데이터산업진흥원)
- SQLD 자격검정 실전문제집

### 9-3. 온라인 학습 사이트

- SQLBolt: https://sqlbolt.com/
- LeetCode Database: https://leetcode.com/problemset/database/
- 프로그래머스 SQL 고득점 Kit

---

## 📌 TIP

1. **SQL 기본 및 활용이 가장 중요 (60점)**
   - JOIN, GROUP BY, HAVING 집중 학습

2. **기출 문제 반복 풀이**
   - 유형 파악 및 시간 단축

3. **실행 순서 암기**
   - FROM → WHERE → GROUP BY → HAVING → SELECT → ORDER BY → LIMIT

4. **함수 결과 직접 계산 연습**
   - 문자열 함수, 날짜 함수, NULL 처리 함수

5. **JOIN 결과 행 수 계산 연습**
   - INNER JOIN: 교집합
   - LEFT JOIN: 왼쪽 테이블 행 수

6. **시험 시간 배분 철저히**
   - 모르는 문제는 과감히 넘어가기

---

**작성일:** 2024년  
**버전:** 1.0  
**합격을 기원합니다! 🎯**
