# SQL JOIN 예제 상세 해설

이 문서는 `join_examples.sql` 파일의 각 예제에 대한 상세한 설명과 실행 결과를 제공합니다.

## 목차

1. [INNER JOIN 예제](#inner-join-예제)
2. [LEFT JOIN 예제](#left-join-예제)
3. [RIGHT JOIN 예제](#right-join-예제)
4. [FULL OUTER JOIN 예제](#full-outer-join-예제)
5. [CROSS JOIN 예제](#cross-join-예제)
6. [SELF JOIN 예제](#self-join-예제)
7. [실전 예제](#실전-예제)

---

## INNER JOIN 예제

### 예제 1-1: 기본 INNER JOIN

**SQL 코드:**
```sql
SELECT 
    s.name AS 학생이름,
    s.major AS 전공,
    g.subject AS 과목,
    g.score AS 점수
FROM student s
INNER JOIN grade g ON s.student_no = g.student_no;
```

**설명:**
- **목적**: 학생 테이블과 성적 테이블을 조인하여 학생 이름, 전공, 과목, 점수를 함께 조회
- **조인 조건**: `s.student_no = g.student_no` (학번으로 연결)
- **INNER JOIN 특징**: 두 테이블 모두에 존재하는 데이터만 반환 (교집합)

**예상 결과:**
```
학생이름 | 전공        | 과목              | 점수
---------|------------|-------------------|------
김철수   | 컴퓨터공학  | 데이터베이스      | 85
김철수   | 컴퓨터공학  | 자바프로그래밍    | 90
이영희   | 경영학      | 경영정보시스템    | 88
이영희   | 경영학      | 회계원리          | 92
박민수   | 컴퓨터공학  | 데이터베이스      | 78
```

**핵심 포인트:**
- `student` 테이블의 모든 학생이 포함되지 않음 (성적이 없는 학생 제외)
- `grade` 테이블의 `S006` 학생 성적도 제외됨 (학생 테이블에 없음)
- 총 5행 반환 (S001 2개, S002 2개, S003 1개)

---

### 예제 1-2: 특정 전공 학생의 성적만 조회

**SQL 코드:**
```sql
SELECT 
    s.name AS 학생이름,
    g.subject AS 과목,
    g.score AS 점수
FROM student s
INNER JOIN grade g ON s.student_no = g.student_no
WHERE s.major = '컴퓨터공학'
ORDER BY g.score DESC;
```

**설명:**
- **목적**: 컴퓨터공학 전공 학생의 성적만 조회하고 점수 높은 순으로 정렬
- **WHERE 절**: 전공이 '컴퓨터공학'인 학생만 필터링
- **ORDER BY**: 점수를 내림차순으로 정렬

**예상 결과:**
```
학생이름 | 과목              | 점수
---------|-------------------|------
김철수   | 자바프로그래밍    | 90
김철수   | 데이터베이스      | 85
박민수   | 데이터베이스      | 78
```

**핵심 포인트:**
- WHERE 절은 JOIN 이후에 적용됨
- ORDER BY로 결과 정렬 가능
- INNER JOIN + WHERE로 원하는 데이터만 추출

---

### 예제 1-3: 3개 테이블 JOIN

**SQL 코드:**
```sql
SELECT 
    s.name AS 학생이름,
    s.major AS 전공,
    d.dept_name AS 부서명,
    d.location AS 위치,
    g.subject AS 과목,
    g.score AS 점수
FROM student s
INNER JOIN grade g ON s.student_no = g.student_no
INNER JOIN department d ON s.major = d.dept_name;
```

**설명:**
- **목적**: 학생, 성적, 부서 정보를 모두 조인하여 상세 정보 조회
- **조인 순서**: 
  1. `student`와 `grade` 조인
  2. 결과와 `department` 조인
- **조인 조건**: 
  - 첫 번째: `s.student_no = g.student_no` (학번)
  - 두 번째: `s.major = d.dept_name` (전공 = 부서명)

**예상 결과:**
```
학생이름 | 전공        | 부서명      | 위치  | 과목              | 점수
---------|------------|------------|-------|-------------------|------
김철수   | 컴퓨터공학  | 컴퓨터공학  | 1호관 | 데이터베이스      | 85
김철수   | 컴퓨터공학  | 컴퓨터공학  | 1호관 | 자바프로그래밍    | 90
이영희   | 경영학      | 경영학      | 2호관 | 경영정보시스템    | 88
이영희   | 경영학      | 경영학      | 2호관 | 회계원리          | 92
박민수   | 컴퓨터공학  | 컴퓨터공학  | 1호관 | 데이터베이스      | 78
```

**핵심 포인트:**
- 여러 테이블을 연속으로 조인 가능
- 각 조인마다 조건을 지정해야 함
- 모든 조인 조건을 만족하는 데이터만 반환

---

## LEFT JOIN 예제

### 예제 2-1: 기본 LEFT JOIN

**SQL 코드:**
```sql
SELECT 
    s.name AS 학생이름,
    s.major AS 전공,
    g.subject AS 과목,
    g.score AS 점수
FROM student s
LEFT JOIN grade g ON s.student_no = g.student_no;
```

**설명:**
- **목적**: 모든 학생을 조회하고, 성적이 있으면 함께 표시
- **LEFT JOIN 특징**: 왼쪽 테이블(`student`)의 모든 행 포함
- **NULL 처리**: 성적이 없는 학생의 과목과 점수는 NULL로 표시

**예상 결과:**
```
학생이름 | 전공        | 과목              | 점수
---------|------------|-------------------|------
김철수   | 컴퓨터공학  | 데이터베이스      | 85
김철수   | 컴퓨터공학  | 자바프로그래밍    | 90
이영희   | 경영학      | 경영정보시스템    | 88
이영희   | 경영학      | 회계원리          | 92
박민수   | 컴퓨터공학  | 데이터베이스      | 78
최지영   | 영어영문    | NULL              | NULL
```

**핵심 포인트:**
- `student` 테이블의 모든 학생이 포함됨 (4명)
- 성적이 없는 학생(`최지영`)도 NULL로 표시되어 포함됨
- 총 6행 반환 (성적 있는 학생 5행 + 성적 없는 학생 1행)

---

### 예제 2-2: 성적이 없는 학생 찾기

**SQL 코드:**
```sql
SELECT 
    s.name AS 학생이름,
    s.major AS 전공,
    s.grade AS 학년
FROM student s
LEFT JOIN grade g ON s.student_no = g.student_no
WHERE g.student_no IS NULL;
```

**설명:**
- **목적**: 성적이 없는 학생을 찾아 출력
- **WHERE 조건**: `g.student_no IS NULL` - 조인된 성적 테이블의 학번이 NULL인 경우
- **실전 활용**: 데이터 정합성 확인, 미입력 데이터 찾기

**예상 결과:**
```
학생이름 | 전공    | 학년
---------|--------|-----
최지영   | 영어영문| 3
```

**핵심 포인트:**
- LEFT JOIN 후 WHERE 절로 NULL 값 필터링
- `IS NULL` 사용 (등호 `=` 사용 불가)
- 데이터 검증 및 관리에 유용

---

### 예제 2-3: 집계 함수와 함께 사용

**SQL 코드:**
```sql
SELECT 
    s.name AS 학생이름,
    s.major AS 전공,
    AVG(g.score) AS 평균점수,
    COUNT(g.score) AS 과목수
FROM student s
LEFT JOIN grade g ON s.student_no = g.student_no
GROUP BY s.student_no, s.name, s.major;
```

**설명:**
- **목적**: 각 학생의 평균 점수와 과목 수를 계산 (성적이 없는 학생도 포함)
- **집계 함수**: 
  - `AVG(g.score)`: 평균 점수 계산
  - `COUNT(g.score)`: NULL이 아닌 점수 개수만 카운트
- **GROUP BY**: 학생별로 그룹화

**예상 결과:**
```
학생이름 | 전공        | 평균점수  | 과목수
---------|------------|----------|-------
김철수   | 컴퓨터공학  | 87.5000  | 2
이영희   | 경영학      | 90.0000  | 2
박민수   | 컴퓨터공학  | 78.0000  | 1
최지영   | 영어영문    | NULL     | 0
```

**핵심 포인트:**
- LEFT JOIN으로 모든 학생 포함
- `COUNT(g.score)`는 NULL을 제외하고 카운트
- `AVG(g.score)`는 NULL이 있으면 NULL 반환
- 성적이 없는 학생도 결과에 포함됨

---

## RIGHT JOIN 예제

### 예제 3-1: 기본 RIGHT JOIN

**SQL 코드:**
```sql
SELECT 
    s.name AS 학생이름,
    s.major AS 전공,
    g.subject AS 과목,
    g.score AS 점수
FROM student s
RIGHT JOIN grade g ON s.student_no = g.student_no;
```

**설명:**
- **목적**: 모든 성적을 조회하고, 학생 정보가 있으면 함께 표시
- **RIGHT JOIN 특징**: 오른쪽 테이블(`grade`)의 모든 행 포함
- **NULL 처리**: 학생 정보가 없는 성적의 학생 이름과 전공은 NULL로 표시

**예상 결과:**
```
학생이름 | 전공        | 과목              | 점수
---------|------------|-------------------|------
김철수   | 컴퓨터공학  | 데이터베이스      | 85
김철수   | 컴퓨터공학  | 자바프로그래밍    | 90
이영희   | 경영학      | 경영정보시스템    | 88
이영희   | 경영학      | 회계원리          | 92
박민수   | 컴퓨터공학  | 데이터베이스      | 78
NULL     | NULL       | 네트워크          | 95
```

**핵심 포인트:**
- `grade` 테이블의 모든 성적이 포함됨 (6개)
- 학생 정보가 없는 성적(`S006`의 네트워크)도 NULL로 표시되어 포함됨
- 총 6행 반환

---

### 예제 3-2: 학생 정보가 없는 성적 찾기

**SQL 코드:**
```sql
SELECT 
    g.student_no AS 학번,
    g.subject AS 과목,
    g.score AS 점수
FROM student s
RIGHT JOIN grade g ON s.student_no = g.student_no
WHERE s.student_no IS NULL;
```

**설명:**
- **목적**: 학생 정보가 없는 성적을 찾아 출력
- **WHERE 조건**: `s.student_no IS NULL` - 조인된 학생 테이블의 학번이 NULL인 경우
- **실전 활용**: 데이터 정합성 확인, 고아 레코드(Orphan Record) 찾기

**예상 결과:**
```
학번 | 과목      | 점수
-----|----------|------
S006 | 네트워크  | 95
```

**핵심 포인트:**
- RIGHT JOIN 후 WHERE 절로 NULL 값 필터링
- 데이터 무결성 검증에 유용
- 참조 무결성 위반 데이터 찾기

---

## FULL OUTER JOIN 예제

### 예제 4-1: FULL OUTER JOIN 구현

**SQL 코드:**
```sql
SELECT 
    s.name AS 학생이름,
    s.major AS 전공,
    g.subject AS 과목,
    g.score AS 점수
FROM student s
LEFT JOIN grade g ON s.student_no = g.student_no

UNION

SELECT 
    s.name AS 학생이름,
    s.major AS 전공,
    g.subject AS 과목,
    g.score AS 점수
FROM student s
RIGHT JOIN grade g ON s.student_no = g.student_no;
```

**설명:**
- **목적**: 양쪽 테이블의 모든 데이터를 포함 (합집합)
- **MySQL 제한**: FULL OUTER JOIN을 직접 지원하지 않아 UNION으로 구현
- **UNION**: 중복 제거 (UNION ALL은 중복 포함)

**예상 결과:**
```
학생이름 | 전공        | 과목              | 점수
---------|------------|-------------------|------
김철수   | 컴퓨터공학  | 데이터베이스      | 85
김철수   | 컴퓨터공학  | 자바프로그래밍    | 90
이영희   | 경영학      | 경영정보시스템    | 88
이영희   | 경영학      | 회계원리          | 92
박민수   | 컴퓨터공학  | 데이터베이스      | 78
최지영   | 영어영문    | NULL              | NULL
NULL     | NULL       | 네트워크          | 95
```

**핵심 포인트:**
- LEFT JOIN과 RIGHT JOIN을 UNION으로 결합
- 양쪽 테이블의 모든 데이터 포함
- 중복 행은 자동 제거됨 (UNION의 특징)

---

## CROSS JOIN 예제

### 예제 5-1: 학생과 부서의 모든 조합

**SQL 코드:**
```sql
SELECT 
    s.name AS 학생이름,
    d.dept_name AS 부서명,
    d.location AS 위치
FROM student s
CROSS JOIN department d;
```

**설명:**
- **목적**: 학생과 부서의 모든 가능한 조합 생성
- **CROSS JOIN 특징**: 조건 없이 모든 조합 생성 (카티션 곱)
- **결과 행 수**: 학생 수 × 부서 수 = 4명 × 4개 부서 = 16행

**예상 결과:**
```
학생이름 | 부서명      | 위치
---------|------------|------
김철수   | 컴퓨터공학  | 1호관
김철수   | 경영학      | 2호관
김철수   | 영어영문    | 3호관
김철수   | 수학        | 4호관
이영희   | 컴퓨터공학  | 1호관
이영희   | 경영학      | 2호관
이영희   | 영어영문    | 3호관
이영희   | 수학        | 4호관
박민수   | 컴퓨터공학  | 1호관
박민수   | 경영학      | 2호관
박민수   | 영어영문    | 3호관
박민수   | 수학        | 4호관
최지영   | 컴퓨터공학  | 1호관
최지영   | 경영학      | 2호관
최지영   | 영어영문    | 3호관
최지영   | 수학        | 4호관
```

**핵심 포인트:**
- 조건 없이 모든 조합 생성
- 결과가 매우 많아질 수 있음 (주의 필요)
- 실무에서는 거의 사용하지 않음
- 특별한 경우(조합 생성, 테스트 데이터 등)에만 사용

---

## SELF JOIN 예제

### 예제 6-1: 직원과 상사 정보 조회

**SQL 코드:**
```sql
SELECT 
    e1.emp_name AS 직원이름,
    e2.emp_name AS 상사이름
FROM employee e1
LEFT JOIN employee e2 ON e1.manager_id = e2.emp_id;
```

**설명:**
- **목적**: 각 직원과 그들의 상사 정보를 조회
- **SELF JOIN**: 같은 테이블을 두 번 조인
- **별칭 사용**: `e1`(직원), `e2`(상사)로 구분
- **LEFT JOIN**: 상사가 없는 직원(대표)도 포함

**예상 결과:**
```
직원이름 | 상사이름
---------|----------
김대표   | NULL
이부장   | 김대표
박과장   | 이부장
최대리   | 이부장
정사원   | 박과장
```

**핵심 포인트:**
- 같은 테이블을 두 번 조인
- 별칭(alias)을 반드시 사용해야 함
- 계층 구조 데이터 처리에 유용
- 조직도, 카테고리 트리 등에 활용

---

### 예제 6-2: 같은 전공 학생 찾기

**SQL 코드:**
```sql
SELECT 
    s1.name AS 학생1,
    s2.name AS 학생2,
    s1.major AS 전공
FROM student s1
INNER JOIN student s2 ON s1.major = s2.major
WHERE s1.student_id < s2.student_id;
```

**설명:**
- **목적**: 같은 전공의 학생들을 쌍으로 조회
- **조인 조건**: `s1.major = s2.major` (같은 전공)
- **WHERE 조건**: `s1.student_id < s2.student_id` (중복 제거)
  - 예: (김철수, 박민수)와 (박민수, 김철수) 중 하나만 선택

**예상 결과:**
```
학생1 | 학생2 | 전공
------|------|----------
김철수 | 박민수 | 컴퓨터공학
```

**핵심 포인트:**
- 같은 테이블 내의 관계 찾기
- WHERE 절로 중복 제거
- 조합(Combination) 생성에 유용

---

## 실전 예제

### 예제 7-1: 학생별 총점과 평균

**SQL 코드:**
```sql
SELECT 
    s.name AS 학생이름,
    s.major AS 전공,
    COUNT(g.subject) AS 과목수,
    SUM(g.score) AS 총점,
    AVG(g.score) AS 평균점수
FROM student s
LEFT JOIN grade g ON s.student_no = g.student_no
GROUP BY s.student_no, s.name, s.major
ORDER BY 평균점수 DESC;
```

**설명:**
- **목적**: 각 학생의 과목 수, 총점, 평균 점수를 계산
- **LEFT JOIN**: 성적이 없는 학생도 포함
- **집계 함수**: COUNT, SUM, AVG 사용
- **GROUP BY**: 학생별로 그룹화
- **ORDER BY**: 평균 점수 높은 순으로 정렬

**예상 결과:**
```
학생이름 | 전공        | 과목수 | 총점 | 평균점수
---------|------------|--------|------|----------
이영희   | 경영학      | 2      | 180  | 90.0000
김철수   | 컴퓨터공학  | 2      | 175  | 87.5000
박민수   | 컴퓨터공학  | 1      | 78   | 78.0000
최지영   | 영어영문    | 0      | NULL | NULL
```

**핵심 포인트:**
- LEFT JOIN으로 모든 학생 포함
- 집계 함수와 GROUP BY 함께 사용
- ORDER BY로 결과 정렬
- 실전에서 가장 많이 사용되는 패턴

---

### 예제 7-2: 전공별 평균 점수

**SQL 코드:**
```sql
SELECT 
    s.major AS 전공,
    COUNT(DISTINCT s.student_no) AS 학생수,
    COUNT(g.subject) AS 과목수,
    AVG(g.score) AS 전공평균점수
FROM student s
LEFT JOIN grade g ON s.student_no = g.student_no
GROUP BY s.major
ORDER BY 전공평균점수 DESC;
```

**설명:**
- **목적**: 전공별로 학생 수, 과목 수, 평균 점수를 계산
- **COUNT(DISTINCT)**: 중복 제거하여 학생 수 계산
- **COUNT(g.subject)**: 과목 수 계산 (NULL 제외)
- **GROUP BY**: 전공별로 그룹화

**예상 결과:**
```
전공        | 학생수 | 과목수 | 전공평균점수
------------|--------|--------|-------------
경영학      | 1      | 2      | 90.0000
컴퓨터공학  | 2      | 3      | 83.3333
영어영문    | 1      | 0      | NULL
```

**핵심 포인트:**
- 전공별 집계
- COUNT(DISTINCT)로 중복 제거
- 그룹별 통계 계산

---

### 예제 7-3: 복잡한 JOIN (3개 이상 테이블)

**SQL 코드:**
```sql
SELECT 
    s.student_no AS 학번,
    s.name AS 학생이름,
    s.major AS 전공,
    d.location AS 부서위치,
    g.subject AS 과목,
    g.score AS 점수,
    CASE 
        WHEN g.score >= 90 THEN 'A'
        WHEN g.score >= 80 THEN 'B'
        WHEN g.score >= 70 THEN 'C'
        ELSE 'F'
    END AS 등급
FROM student s
INNER JOIN grade g ON s.student_no = g.student_no
LEFT JOIN department d ON s.major = d.dept_name
ORDER BY s.name, g.score DESC;
```

**설명:**
- **목적**: 학생, 성적, 부서 정보를 모두 조인하고 등급 계산
- **조인 순서**: 
  1. `student`와 `grade` INNER JOIN
  2. 결과와 `department` LEFT JOIN
- **CASE 문**: 점수에 따라 등급 부여
- **ORDER BY**: 학생 이름 순, 같은 학생은 점수 높은 순

**예상 결과:**
```
학번 | 학생이름 | 전공        | 부서위치 | 과목              | 점수 | 등급
-----|---------|------------|---------|-------------------|------|-----
S001 | 김철수   | 컴퓨터공학  | 1호관   | 자바프로그래밍    | 90   | A
S001 | 김철수   | 컴퓨터공학  | 1호관   | 데이터베이스      | 85   | B
S002 | 이영희   | 경영학      | 2호관   | 회계원리          | 92   | A
S002 | 이영희   | 경영학      | 2호관   | 경영정보시스템    | 88   | B
S003 | 박민수   | 컴퓨터공학  | 1호관   | 데이터베이스      | 78   | C
```

**핵심 포인트:**
- 여러 테이블 조인
- INNER JOIN과 LEFT JOIN 혼합 사용
- CASE 문으로 조건부 값 생성
- 복잡한 비즈니스 로직 구현

---

### 예제 7-4: 조건이 있는 JOIN

**SQL 코드:**
```sql
SELECT 
    s.name AS 학생이름,
    g.subject AS 과목,
    g.score AS 점수
FROM student s
INNER JOIN grade g ON s.student_no = g.student_no 
    AND g.score >= 80
ORDER BY g.score DESC;
```

**설명:**
- **목적**: 80점 이상인 성적만 조인하여 조회
- **조인 조건 확장**: `ON` 절에 추가 조건 포함
- **WHERE vs ON**: 
  - `ON` 절: 조인 시점에 필터링 (조인 전)
  - `WHERE` 절: 조인 후 필터링
- **성능**: ON 절 조건이 더 효율적일 수 있음

**예상 결과:**
```
학생이름 | 과목              | 점수
---------|-------------------|------
이영희   | 회계원리          | 92
김철수   | 자바프로그래밍    | 90
이영희   | 경영정보시스템    | 88
김철수   | 데이터베이스      | 85
```

**핵심 포인트:**
- ON 절에 조건 추가 가능
- 조인 시점에 필터링
- 성능 최적화에 유용
- WHERE 절과의 차이 이해

---

## 실행 방법

### 1. 테이블 생성 및 데이터 삽입

먼저 `create_table.sql` 파일을 실행하여 테이블과 데이터를 준비합니다.

```bash
mysql -u 사용자명 -p < create_table.sql
```

### 2. 예제 실행

`join_examples.sql` 파일의 원하는 예제만 선택하여 실행하거나, 전체를 실행할 수 있습니다.

```bash
mysql -u 사용자명 -p < join_examples.sql
```

또는 MySQL Workbench에서:
1. `join_examples.sql` 파일 열기
2. 실행하고 싶은 예제만 선택
3. 실행 (F9 또는 Ctrl+Shift+Enter)

---

## 학습 체크리스트

각 예제를 학습한 후 다음 사항을 확인하세요:

### INNER JOIN
- [ ] 두 테이블의 교집합만 반환하는 것을 이해했는가?
- [ ] WHERE 절과 함께 사용할 수 있는가?
- [ ] 3개 이상 테이블을 조인할 수 있는가?

### LEFT JOIN
- [ ] 왼쪽 테이블의 모든 행을 포함하는 것을 이해했는가?
- [ ] NULL 값으로 매칭되지 않는 데이터를 찾을 수 있는가?
- [ ] 집계 함수와 함께 사용할 수 있는가?

### RIGHT JOIN
- [ ] 오른쪽 테이블의 모든 행을 포함하는 것을 이해했는가?
- [ ] LEFT JOIN과의 차이를 이해했는가?

### FULL OUTER JOIN
- [ ] UNION을 사용하여 구현할 수 있는가?
- [ ] 양쪽 테이블의 모든 데이터를 포함하는 것을 이해했는가?

### CROSS JOIN
- [ ] 모든 조합을 생성하는 것을 이해했는가?
- [ ] 언제 사용해야 하는지 알고 있는가?

### SELF JOIN
- [ ] 같은 테이블을 두 번 조인할 수 있는가?
- [ ] 별칭을 사용하는 이유를 이해했는가?

### 실전 예제
- [ ] 집계 함수와 JOIN을 함께 사용할 수 있는가?
- [ ] 복잡한 조인을 작성할 수 있는가?
- [ ] ON 절에 조건을 추가할 수 있는가?

---

## 주의사항

### 1. NULL 값 처리

LEFT JOIN이나 RIGHT JOIN 사용 시 NULL 값이 발생할 수 있습니다. 집계 함수 사용 시 주의하세요.

```sql
-- COUNT(g.score)는 NULL을 제외하고 카운트
-- AVG(g.score)는 NULL이 있으면 NULL 반환
```

### 2. 성능 고려

- 큰 테이블 간 JOIN은 시간이 오래 걸릴 수 있음
- 인덱스를 적절히 사용
- 필요한 컬럼만 SELECT

### 3. 조인 순서

여러 테이블을 조인할 때 순서가 중요할 수 있습니다. 일반적으로 작은 테이블부터 조인하는 것이 효율적입니다.

---

## 요약

### JOIN 선택 가이드

| 상황 | 사용할 JOIN |
|------|------------|
| 두 테이블 모두에 있는 데이터만 필요 | **INNER JOIN** |
| 왼쪽 테이블 전체 + 매칭 데이터 | **LEFT JOIN** |
| 오른쪽 테이블 전체 + 매칭 데이터 | **RIGHT JOIN** |
| 양쪽 테이블 전체 | **FULL OUTER JOIN** (UNION) |
| 모든 조합 생성 | **CROSS JOIN** |
| 같은 테이블 내 관계 | **SELF JOIN** |

### 핵심 포인트

1. **INNER JOIN**이 가장 많이 사용됨
2. **LEFT JOIN**은 "모든 A와 매칭되는 B"를 조회할 때 사용
3. **JOIN 조건**은 보통 외래키 관계를 사용
4. **집계 함수**와 함께 사용하면 강력함
5. **NULL 값** 처리에 주의

---

**이 설명서와 함께 JOIN을 마스터하세요!**
