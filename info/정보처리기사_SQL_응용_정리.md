## 정보처리기사 실기 SQL 응용 정리

**목적**: 정보처리기사 실기에서 자주 나오는 **SQL 응용(질의/조인/서브쿼리/집계/DDL·DCL·TCL/트랜잭션/인덱스·뷰)** 핵심만 빠르게 복습하는 요약본

---

### 0. 한눈에 보는 분류 (DDL / DML / DCL / TCL)

- **DDL (정의어)**: `CREATE`, `ALTER`, `DROP`, `TRUNCATE`
  - 테이블/뷰/인덱스/제약조건 등 **스키마 객체** 정의
- **DML (조작어)**: `SELECT`, `INSERT`, `UPDATE`, `DELETE`
  - **데이터(튜플)** 조회/삽입/수정/삭제
- **DCL (제어어)**: `GRANT`, `REVOKE`
  - 사용자 권한/역할 제어
- **TCL (트랜잭션 제어어)**: `COMMIT`, `ROLLBACK`, `SAVEPOINT`
  - 트랜잭션 확정/취소/부분 저장

---

### 1. 키(Key) / 무결성 / 제약조건

#### 키의 종류

- **후보키(Candidate Key)**: 유일성 + 최소성 만족
- **기본키(PK)**: 후보키 중 대표 1개 (NULL 불가, 중복 불가)
- **대체키(Alternate Key)**: 기본키가 아닌 후보키
- **슈퍼키(Super Key)**: 유일성만 만족 (불필요 속성 포함 가능)
- **외래키(FK)**: 다른 테이블의 PK(또는 UNIQUE) 참조

#### 무결성(Integrity)

- **개체 무결성**: PK는 **NULL/중복 불가**
- **참조 무결성**: FK는 참조 대상이 존재해야 함(또는 NULL 허용)
- **도메인 무결성**: 속성 값은 정의된 도메인(타입/범위) 내

#### 제약조건 문법

```sql
CREATE TABLE STUDENT (
  STID   INT PRIMARY KEY,
  NAME   VARCHAR(20) NOT NULL,
  DEPTID INT,
  SCORE  INT CHECK (SCORE BETWEEN 0 AND 100),
  UNIQUE (NAME),
  FOREIGN KEY (DEPTID) REFERENCES DEPT(DEPTID)
);
```

---

### 2. SELECT 필수 포인트 (실기 단골 함정)

#### 실행 순서(논리적)

`FROM` → `WHERE` → `GROUP BY` → `HAVING` → `SELECT` → `ORDER BY`

- **WHERE**: 그룹화 전 필터
- **HAVING**: 그룹화 후(집계 결과) 필터

#### NULL 관련

- `=`로 비교 불가 → **`IS NULL` / `IS NOT NULL`**
- **`COUNT(컬럼)`은 NULL 제외**, `COUNT(*)`는 행 수

```sql
SELECT COUNT(*)     AS cnt_all,
       COUNT(score) AS cnt_score_not_null
FROM exam;
```

#### DISTINCT

- **중복 제거 후** 반환/집계

```sql
SELECT COUNT(DISTINCT dept) FROM student;
```

---

### 3. JOIN 정리 (INNER / OUTER / CROSS / SELF)

#### INNER JOIN (가장 많이 나옴)

```sql
SELECT s.name, d.dept_name
FROM student s
JOIN dept d ON s.deptid = d.deptid;
```

#### OUTER JOIN (누락되는 쪽이 핵심)

- **LEFT OUTER JOIN**: 왼쪽(기준) 전부 + 오른쪽 매칭 없으면 NULL

```sql
SELECT d.dept_name, s.name
FROM dept d
LEFT JOIN student s ON d.deptid = s.deptid;
```

#### CROSS JOIN

- 데카르트 곱(행 수 = \(A행 \times B행\))

```sql
SELECT *
FROM t1
CROSS JOIN t2;
```

#### SELF JOIN

```sql
SELECT e.emp_name, m.emp_name AS manager_name
FROM emp e
LEFT JOIN emp m ON e.manager_id = m.emp_id;
```

---

### 4. 서브쿼리 (IN / EXISTS / ANY·ALL / 스칼라)

#### IN (결과 집합과 비교)

```sql
SELECT name
FROM student
WHERE deptid IN (SELECT deptid FROM dept WHERE dept_name = '컴퓨터');
```

#### EXISTS (존재 여부)

```sql
SELECT d.dept_name
FROM dept d
WHERE EXISTS (
  SELECT 1
  FROM student s
  WHERE s.deptid = d.deptid
);
```

#### ANY / ALL

- `> ANY (서브쿼리)` : 서브쿼리 값 중 **하나라도** 만족 (보통 최소값 기준처럼 동작)
- `> ALL (서브쿼리)` : 서브쿼리 값 **전부**보다 커야 함 (최대값 초과)

#### 스칼라 서브쿼리 (SELECT 절에서 1개 값)

```sql
SELECT s.name,
       (SELECT d.dept_name FROM dept d WHERE d.deptid = s.deptid) AS dept_name
FROM student s;
```

---

### 5. 집계 / GROUP BY / HAVING

#### 기본 형태

```sql
SELECT deptid, COUNT(*) AS cnt, AVG(score) AS avg_score
FROM student
GROUP BY deptid
HAVING AVG(score) >= 80;
```

#### HAVING에 “집계 아닌 컬럼 조건”도 가능 (단, GROUP BY에 포함되거나 집계로 표현되어야 안전)

- 실기에서는 종종 `HAVING 과목이름='데이터베이스'`처럼 출제

---

### 6. DML (INSERT / UPDATE / DELETE) 핵심

#### INSERT

```sql
INSERT INTO student (stid, name, deptid, score)
VALUES (1001, '홍길동', 10, 90);
```

#### INSERT ... SELECT

```sql
INSERT INTO student_backup (stid, name)
SELECT stid, name
FROM student
WHERE score >= 90;
```

#### UPDATE

```sql
UPDATE student
SET score = score + 5
WHERE deptid = 10;
```

#### DELETE

```sql
DELETE FROM student
WHERE name = '민수';
```

---

### 7. DDL (CREATE / ALTER / DROP) 단골

#### CREATE TABLE + 타입 예시

```sql
CREATE TABLE dept (
  deptid    INT PRIMARY KEY,
  dept_name VARCHAR(30) NOT NULL
);
```

#### ALTER TABLE

```sql
ALTER TABLE student ADD addr VARCHAR(50);
-- DBMS마다 문법 차이 가능: MODIFY/ALTER COLUMN 등
```

#### DROP vs TRUNCATE

- **DROP**: 객체 자체 삭제(테이블 구조까지 제거)
- **TRUNCATE**: 테이블은 유지, **데이터만 전체 삭제**(빠름)

---

### 8. VIEW / INDEX

#### VIEW

- **가상 테이블**(보안/편의/복잡 쿼리 캡슐화)

```sql
CREATE VIEW v_student_high AS
SELECT stid, name, score
FROM student
WHERE score >= 90;
```

#### INDEX

- 검색(WHERE, JOIN) 성능에 영향
- 데이터 변경(INSERT/UPDATE/DELETE)에는 오버헤드

```sql
CREATE INDEX idx_student_name ON student(name);
```

---

### 9. 권한 (DCL: GRANT / REVOKE)

```sql
GRANT SELECT, INSERT ON student TO user1;
REVOKE INSERT ON student FROM user1;
```

---

### 10. 트랜잭션 (TCL) / ACID / 병행제어 핵심

#### TCL

```sql
SAVEPOINT sp1;
-- ... 작업 ...
ROLLBACK TO sp1;
COMMIT;
```

#### ACID

- **Atomicity(원자성)**: 전부 성공/전부 실패
- **Consistency(일관성)**: 무결성 제약을 항상 만족
- **Isolation(격리성)**: 동시에 실행돼도 결과는 독립 실행과 동일하게
- **Durability(지속성)**: 커밋된 결과는 장애 후에도 보존

#### 이상 현상(병행) 키워드

- **Dirty Read**: 커밋되지 않은 데이터 읽음
- **Non-repeatable Read**: 같은 조회가 다시 하면 값이 바뀜
- **Phantom Read**: 같은 조건 조회에서 행이 늘거나 줄어듦

---

### 11. 실기 빈출 체크리스트 (이것만은 꼭)

- **별칭(AS) 요구** / **작은따옴표로 별칭** 요구하는 문제 유형 존재
- **WHERE 사용 금지** → `GROUP BY` + `HAVING`으로 우회
- **대소문자 구분 없음** → `LOWER()`/`UPPER()`로 통일 후 비교
- **IN 사용 필수**, **JOIN ON 빈칸**, **ORDER BY DESC 빈칸**
- **COUNT(컬럼)은 NULL 제외** / `IS NULL` 비교

