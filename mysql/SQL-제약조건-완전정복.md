# SQL 제약조건(Constraint) 완전 정복

## 목차

1. [제약조건이란?](#제약조건이란)
2. [제약조건의 종류](#제약조건의-종류)
3. [PRIMARY KEY (기본키)](#primary-key-기본키)
4. [FOREIGN KEY (외래키)](#foreign-key-외래키)
5. [UNIQUE (고유 제약조건)](#unique-고유-제약조건)
6. [NOT NULL (널 제약조건)](#not-null-널-제약조건)
7. [CHECK (체크 제약조건)](#check-체크-제약조건)
8. [DEFAULT (기본값)](#default-기본값)
9. [제약조건 추가/삭제](#제약조건-추가삭제)
10. [실전 예제](#실전-예제)

---

## 제약조건이란?

**제약조건(Constraint)**은 데이터베이스 테이블의 데이터에 대한 규칙을 정의하는 것입니다.

### 제약조건의 목적

1. **데이터 무결성 보장**: 잘못된 데이터가 입력되는 것을 방지
2. **데이터 일관성 유지**: 데이터 간의 관계를 명확히 정의
3. **데이터 품질 향상**: 유효한 데이터만 저장되도록 보장

### 제약조건의 종류

| 제약조건 | 설명 | 예시 |
|---------|------|------|
| **PRIMARY KEY** | 기본키 (고유하고 NULL 불가) | 학번, 주민등록번호 |
| **FOREIGN KEY** | 외래키 (다른 테이블 참조) | 학생 테이블의 학번 참조 |
| **UNIQUE** | 고유값 (중복 불가, NULL 가능) | 이메일, 전화번호 |
| **NOT NULL** | NULL 불가 | 이름, 나이 |
| **CHECK** | 조건 검사 | 나이 >= 0, 점수 0~100 |
| **DEFAULT** | 기본값 | 등록일자 = 현재 날짜 |

---

## 제약조건의 종류

### 1. PRIMARY KEY (기본키)

**기본키**는 테이블의 각 행을 고유하게 식별하는 컬럼입니다.

**특징:**
- ✅ **고유성**: 중복 불가
- ✅ **NULL 불가**: 반드시 값이 있어야 함
- ✅ **하나만 존재**: 테이블당 하나의 기본키만 가능 (복합키는 여러 컬럼 조합)

**예제:**

```sql
-- 방법 1: 컬럼 정의 시 지정
CREATE TABLE student (
    student_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50)
);

-- 방법 2: 테이블 레벨에서 지정
CREATE TABLE student (
    student_id INT AUTO_INCREMENT,
    name VARCHAR(50),
    PRIMARY KEY (student_id)
);

-- 방법 3: 복합 기본키 (여러 컬럼 조합)
CREATE TABLE enrollment (
    student_id INT,
    course_id INT,
    PRIMARY KEY (student_id, course_id)  -- 두 컬럼의 조합이 고유
);
```

**실제 사용 예:**

```sql
CREATE TABLE student (
    student_id INT PRIMARY KEY AUTO_INCREMENT,  -- ✅ 기본키
    student_no CHAR(20) UNIQUE NOT NULL,
    name VARCHAR(50) NOT NULL
);
```

**설명:**
- `student_id`가 기본키로 설정됨
- 각 학생은 고유한 `student_id`를 가짐
- `AUTO_INCREMENT`로 자동 증가

---

### 2. FOREIGN KEY (외래키)

**외래키**는 다른 테이블의 기본키를 참조하는 컬럼입니다.

**특징:**
- ✅ **참조 무결성**: 참조하는 테이블에 존재하는 값만 입력 가능
- ✅ **관계 정의**: 테이블 간의 관계를 명시
- ✅ **NULL 가능**: 외래키는 NULL일 수 있음

**예제:**

```sql
-- 학생 테이블
CREATE TABLE student (
    student_id INT PRIMARY KEY AUTO_INCREMENT,
    student_no CHAR(20) UNIQUE NOT NULL,
    name VARCHAR(50)
);

-- 성적 테이블 (외래키 사용)
CREATE TABLE grade (
    grade_id INT PRIMARY KEY AUTO_INCREMENT,
    student_no CHAR(20),  -- 외래키 컬럼
    subject VARCHAR(50),
    score INT,
    FOREIGN KEY (student_no) REFERENCES student(student_no)  -- ✅ 외래키
);
```

**설명:**
- `grade` 테이블의 `student_no`는 `student` 테이블의 `student_no`를 참조
- `student` 테이블에 존재하지 않는 학번은 입력 불가
- 데이터 무결성 보장

**외래키 동작 옵션:**

```sql
-- ON DELETE CASCADE: 부모 삭제 시 자식도 삭제
FOREIGN KEY (student_no) REFERENCES student(student_no)
ON DELETE CASCADE;

-- ON DELETE SET NULL: 부모 삭제 시 자식을 NULL로 설정
FOREIGN KEY (student_no) REFERENCES student(student_no)
ON DELETE SET NULL;

-- ON DELETE RESTRICT: 부모 삭제 시 자식이 있으면 삭제 불가 (기본값)
FOREIGN KEY (student_no) REFERENCES student(student_no)
ON DELETE RESTRICT;
```

**문제 상황:**

```sql
-- ❌ 오류 발생: student 테이블에 S006이 없음
INSERT INTO grade (student_no, subject, score) 
VALUES ('S006', '네트워크', 95);
-- Error: Cannot add or update a child row: a foreign key constraint fails
```

**해결 방법:**

```sql
-- 방법 1: 외래키 체크 일시적으로 비활성화
SET FOREIGN_KEY_CHECKS = 0;
INSERT INTO grade (student_no, subject, score) 
VALUES ('S006', '네트워크', 95);
SET FOREIGN_KEY_CHECKS = 1;

-- 방법 2: 외래키 제약조건 제거 (권장하지 않음)
ALTER TABLE grade DROP FOREIGN KEY fk_grade_student;
```

---

### 3. UNIQUE (고유 제약조건)

**UNIQUE**는 컬럼의 값이 중복되지 않도록 하는 제약조건입니다.

**특징:**
- ✅ **중복 불가**: 같은 값이 두 번 이상 입력 불가
- ✅ **NULL 가능**: NULL은 여러 개 가능 (일부 DB는 NULL도 고유해야 함)
- ✅ **여러 개 가능**: 테이블에 여러 UNIQUE 제약조건 가능

**예제:**

```sql
-- 방법 1: 컬럼 정의 시 지정
CREATE TABLE student (
    student_id INT PRIMARY KEY,
    student_no CHAR(20) UNIQUE,  -- ✅ 고유값
    email VARCHAR(100) UNIQUE,    -- ✅ 이메일도 고유
    name VARCHAR(50)
);

-- 방법 2: 테이블 레벨에서 지정
CREATE TABLE student (
    student_id INT PRIMARY KEY,
    student_no CHAR(20),
    email VARCHAR(100),
    UNIQUE (student_no),
    UNIQUE (email)
);

-- 방법 3: 복합 UNIQUE (여러 컬럼 조합)
CREATE TABLE enrollment (
    student_id INT,
    course_id INT,
    UNIQUE (student_id, course_id)  -- 같은 학생이 같은 과목을 중복 수강 불가
);
```

**실제 사용 예:**

```sql
CREATE TABLE student (
    student_id INT PRIMARY KEY AUTO_INCREMENT,
    student_no CHAR(20) UNIQUE NOT NULL,  -- ✅ 학번은 고유하고 NULL 불가
    name VARCHAR(50) NOT NULL
);
```

**UNIQUE vs PRIMARY KEY:**

| 특징 | PRIMARY KEY | UNIQUE |
|------|------------|--------|
| NULL 허용 | ❌ 불가 | ✅ 가능 (일부 DB) |
| 개수 | 1개만 | 여러 개 가능 |
| 인덱스 | 자동 생성 | 자동 생성 |
| 목적 | 행 식별 | 중복 방지 |

---

### 4. NOT NULL (널 제약조건)

**NOT NULL**은 컬럼에 NULL 값을 허용하지 않는 제약조건입니다.

**특징:**
- ✅ **NULL 불가**: 반드시 값이 있어야 함
- ✅ **필수 데이터**: 중요한 데이터에 사용

**예제:**

```sql
CREATE TABLE student (
    student_id INT PRIMARY KEY AUTO_INCREMENT,
    student_no CHAR(20) UNIQUE NOT NULL,  -- ✅ NULL 불가
    name VARCHAR(50) NOT NULL,            -- ✅ NULL 불가
    major VARCHAR(50),                    -- NULL 가능
    grade INT                             -- NULL 가능
);
```

**설명:**
- `student_no`와 `name`은 반드시 값이 있어야 함
- `major`와 `grade`는 NULL 가능

**NULL vs NOT NULL 비교:**

```sql
-- ✅ 정상: 모든 필수 필드 입력
INSERT INTO student (student_no, name) 
VALUES ('S001', '김철수');

-- ❌ 오류: NOT NULL 컬럼에 NULL 입력
INSERT INTO student (student_no, name) 
VALUES (NULL, '김철수');
-- Error: Column 'student_no' cannot be null

-- ✅ 정상: NULL 가능한 컬럼은 생략 가능
INSERT INTO student (student_no, name) 
VALUES ('S002', '이영희');
-- major와 grade는 NULL로 자동 설정
```

---

### 5. CHECK (체크 제약조건)

**CHECK**는 컬럼 값이 특정 조건을 만족하도록 하는 제약조건입니다.

**특징:**
- ✅ **조건 검사**: 지정한 조건을 만족하는 값만 입력 가능
- ✅ **데이터 유효성**: 잘못된 데이터 입력 방지

**예제:**

```sql
-- 방법 1: 컬럼 정의 시 지정
CREATE TABLE student (
    student_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    age INT CHECK (age >= 0 AND age <= 150),  -- ✅ 나이는 0~150
    score INT CHECK (score >= 0 AND score <= 100),  -- ✅ 점수는 0~100
    gender CHAR(1) CHECK (gender IN ('M', 'F'))  -- ✅ 성별은 M 또는 F
);

-- 방법 2: 테이블 레벨에서 지정
CREATE TABLE student (
    student_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    age INT,
    score INT,
    CHECK (age >= 0 AND age <= 150),
    CHECK (score >= 0 AND score <= 100)
);
```

**실제 사용 예:**

```sql
CREATE TABLE grade (
    grade_id INT PRIMARY KEY AUTO_INCREMENT,
    student_no CHAR(20),
    subject VARCHAR(50),
    score INT CHECK (score >= 0 AND score <= 100)  -- ✅ 점수 범위 제한
);
```

**CHECK 제약조건 테스트:**

```sql
-- ✅ 정상: 조건 만족
INSERT INTO grade (student_no, subject, score) 
VALUES ('S001', '데이터베이스', 85);

-- ❌ 오류: 조건 위반
INSERT INTO grade (student_no, subject, score) 
VALUES ('S001', '데이터베이스', 150);
-- Error: Check constraint 'grade_chk_1' is violated.
```

**주의사항:**
- MySQL 8.0.16 이상에서만 CHECK 제약조건 지원
- 이전 버전에서는 트리거나 애플리케이션 레벨에서 검증 필요

---

### 6. DEFAULT (기본값)

**DEFAULT**는 컬럼에 값이 입력되지 않았을 때 사용할 기본값을 지정합니다.

**특징:**
- ✅ **자동 값**: 값이 없으면 기본값 사용
- ✅ **편의성**: 자주 사용하는 값 자동 입력

**예제:**

```sql
CREATE TABLE student (
    student_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    major VARCHAR(50) DEFAULT '미정',  -- ✅ 기본값 '미정'
    grade INT DEFAULT 1,                -- ✅ 기본값 1
    created_date DATE DEFAULT (CURRENT_DATE),  -- ✅ 기본값 현재 날짜
    status VARCHAR(20) DEFAULT '활성'  -- ✅ 기본값 '활성'
);
```

**DEFAULT 사용 예:**

```sql
-- ✅ major와 grade는 기본값 사용
INSERT INTO student (name) 
VALUES ('김철수');
-- major = '미정', grade = 1로 자동 설정

-- ✅ 명시적으로 값 지정 가능
INSERT INTO student (name, major, grade) 
VALUES ('이영희', '컴퓨터공학', 2);

-- ✅ NULL 명시 시 NULL 저장 (DEFAULT 무시)
INSERT INTO student (name, major) 
VALUES ('박민수', NULL);
-- major = NULL 저장
```

---

## 제약조건 추가/삭제

### 제약조건 추가 (ALTER TABLE)

```sql
-- PRIMARY KEY 추가
ALTER TABLE student 
ADD PRIMARY KEY (student_id);

-- FOREIGN KEY 추가
ALTER TABLE grade 
ADD CONSTRAINT fk_grade_student 
FOREIGN KEY (student_no) REFERENCES student(student_no);

-- UNIQUE 추가
ALTER TABLE student 
ADD CONSTRAINT uk_student_no UNIQUE (student_no);

-- CHECK 추가
ALTER TABLE grade 
ADD CONSTRAINT chk_score 
CHECK (score >= 0 AND score <= 100);

-- NOT NULL 추가
ALTER TABLE student 
MODIFY COLUMN name VARCHAR(50) NOT NULL;

-- DEFAULT 추가
ALTER TABLE student 
ALTER COLUMN major SET DEFAULT '미정';
```

### 제약조건 삭제 (ALTER TABLE)

```sql
-- PRIMARY KEY 삭제
ALTER TABLE student 
DROP PRIMARY KEY;

-- FOREIGN KEY 삭제
ALTER TABLE grade 
DROP FOREIGN KEY fk_grade_student;

-- UNIQUE 삭제
ALTER TABLE student 
DROP INDEX uk_student_no;

-- CHECK 삭제
ALTER TABLE grade 
DROP CHECK chk_score;

-- DEFAULT 삭제
ALTER TABLE student 
ALTER COLUMN major DROP DEFAULT;
```

---

## 실전 예제

### 예제 1: 학생 테이블 (모든 제약조건 사용)

```sql
CREATE TABLE student (
    -- 기본키
    student_id INT PRIMARY KEY AUTO_INCREMENT,
    
    -- UNIQUE + NOT NULL
    student_no CHAR(20) UNIQUE NOT NULL,
    
    -- NOT NULL
    name VARCHAR(50) NOT NULL,
    
    -- DEFAULT
    major VARCHAR(50) DEFAULT '미정',
    
    -- CHECK
    grade INT CHECK (grade >= 1 AND grade <= 4),
    
    -- CHECK
    age INT CHECK (age >= 0 AND age <= 150),
    
    -- DEFAULT
    status VARCHAR(20) DEFAULT '활성'
);
```

### 예제 2: 성적 테이블 (외래키 사용)

```sql
CREATE TABLE grade (
    -- 기본키
    grade_id INT PRIMARY KEY AUTO_INCREMENT,
    
    -- 외래키
    student_no CHAR(20),
    FOREIGN KEY (student_no) REFERENCES student(student_no)
        ON DELETE CASCADE,  -- 학생 삭제 시 성적도 삭제
    
    -- NOT NULL
    subject VARCHAR(50) NOT NULL,
    
    -- CHECK
    score INT CHECK (score >= 0 AND score <= 100),
    
    -- DEFAULT
    exam_date DATE DEFAULT (CURRENT_DATE)
);
```

### 예제 3: 제약조건 확인

```sql
-- 테이블의 제약조건 확인
SELECT 
    CONSTRAINT_NAME,
    CONSTRAINT_TYPE,
    TABLE_NAME
FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS
WHERE TABLE_SCHEMA = 'join_example'
  AND TABLE_NAME = 'student';

-- 외래키 제약조건 상세 확인
SELECT 
    CONSTRAINT_NAME,
    TABLE_NAME,
    COLUMN_NAME,
    REFERENCED_TABLE_NAME,
    REFERENCED_COLUMN_NAME
FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE
WHERE TABLE_SCHEMA = 'join_example'
  AND TABLE_NAME = 'grade'
  AND REFERENCED_TABLE_NAME IS NOT NULL;
```

---

## 제약조건 비교표

| 제약조건 | 목적 | NULL 허용 | 중복 허용 | 개수 제한 |
|---------|------|----------|----------|----------|
| **PRIMARY KEY** | 행 식별 | ❌ | ❌ | 1개만 |
| **FOREIGN KEY** | 참조 무결성 | ✅ | ✅ | 여러 개 |
| **UNIQUE** | 중복 방지 | ✅ | ❌ | 여러 개 |
| **NOT NULL** | 필수 데이터 | ❌ | ✅ | 여러 개 |
| **CHECK** | 조건 검사 | ✅ | ✅ | 여러 개 |
| **DEFAULT** | 기본값 | - | - | 여러 개 |

---

## 주의사항

### 1. 제약조건 이름 지정

제약조건에 이름을 지정하면 나중에 관리하기 쉽습니다.

```sql
-- ✅ 좋은 예: 제약조건 이름 지정
ALTER TABLE grade 
ADD CONSTRAINT fk_grade_student 
FOREIGN KEY (student_no) REFERENCES student(student_no);

-- ❌ 나쁜 예: 이름 없이 생성 (DB가 자동 생성)
ALTER TABLE grade 
ADD FOREIGN KEY (student_no) REFERENCES student(student_no);
```

### 2. 제약조건 순서

제약조건을 추가할 때 순서가 중요합니다.

```sql
-- ❌ 오류: 참조하는 테이블이 없음
CREATE TABLE grade (
    student_no CHAR(20),
    FOREIGN KEY (student_no) REFERENCES student(student_no)  -- student 테이블이 아직 없음
);

-- ✅ 올바른 순서
-- 1. 먼저 student 테이블 생성
CREATE TABLE student (...);

-- 2. 그 다음 grade 테이블 생성
CREATE TABLE grade (
    FOREIGN KEY (student_no) REFERENCES student(student_no)
);
```

### 3. 외래키 체크 비활성화

특수한 경우 외래키 체크를 일시적으로 비활성화할 수 있습니다.

```sql
-- 외래키 체크 비활성화
SET FOREIGN_KEY_CHECKS = 0;

-- 데이터 삽입/수정/삭제
INSERT INTO grade VALUES (...);

-- 외래키 체크 다시 활성화
SET FOREIGN_KEY_CHECKS = 1;
```

---

## 요약

### 제약조건 선택 가이드

| 상황 | 사용할 제약조건 |
|------|---------------|
| 각 행을 고유하게 식별 | **PRIMARY KEY** |
| 다른 테이블 참조 | **FOREIGN KEY** |
| 중복 방지 (이메일, 전화번호 등) | **UNIQUE** |
| 필수 데이터 (이름, 나이 등) | **NOT NULL** |
| 값 범위 제한 (나이, 점수 등) | **CHECK** |
| 기본값 설정 | **DEFAULT** |

### 핵심 포인트

1. **PRIMARY KEY**: 테이블당 하나만, NULL 불가, 고유
2. **FOREIGN KEY**: 참조 무결성 보장, 테이블 간 관계 정의
3. **UNIQUE**: 중복 방지, NULL 가능, 여러 개 가능
4. **NOT NULL**: 필수 데이터, NULL 불가
5. **CHECK**: 조건 검사, 데이터 유효성 보장
6. **DEFAULT**: 기본값 설정, 편의성 향상

---

**제약조건을 올바르게 사용하면 데이터 무결성이 보장됩니다!**
