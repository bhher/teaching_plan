# SQL 종합 실습 — 학원 수강 시스템 정답

문제: [SQL-종합실습-학원수강-문제.md](./SQL-종합실습-학원수강-문제.md)

---

## 문제 1: 데이터베이스 생성 (5점)

```sql
CREATE DATABASE academy_db
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;
```

**해설:** 한글을 위해 `utf8mb4` + `utf8mb4_unicode_ci` 권장.

---

## 문제 2: student 테이블 (5점)

```sql
USE academy_db;

CREATE TABLE student (
    student_id   INT PRIMARY KEY AUTO_INCREMENT,
    student_no   VARCHAR(20) UNIQUE NOT NULL,
    name         VARCHAR(50) NOT NULL,
    phone        VARCHAR(20) UNIQUE,
    email        VARCHAR(100),
    join_date    DATE DEFAULT (CURRENT_DATE),
    status       VARCHAR(20) DEFAULT '수강중'
) CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;
```

**주의:** MySQL 8.0.13 미만이면 `DEFAULT (CURRENT_DATE)` 대신  
`join_date DATE`만 두고 INSERT 때 날짜를 넣거나, `CURDATE()` 사용을 검토.

---

## 문제 3: course 테이블 (5점)

```sql
CREATE TABLE course (
    course_id   INT PRIMARY KEY AUTO_INCREMENT,
    course_code VARCHAR(20) UNIQUE NOT NULL,
    title       VARCHAR(200) NOT NULL,
    teacher     VARCHAR(100),
    classroom   VARCHAR(50),
    category    VARCHAR(50),
    capacity    INT DEFAULT 20,
    enrolled    INT DEFAULT 0
) CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;
```

---

## 문제 4: enroll 테이블 (5점)

```sql
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
) CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;
```

**해설:** FK는 **참조되는 쪽(student, course)을 먼저** 만든 뒤 enroll 생성.

---

## 문제 5: 수강생 INSERT (5점)

```sql
INSERT INTO student (student_no, name, phone, email, join_date) VALUES
('S001', '홍길동', '010-1111-1001', 'hong@email.com', '2023-01-10'),
('S002', '김영희', '010-2222-2002', 'kim@email.com', '2023-03-15'),
('S003', '이철수', '010-3333-3003', 'lee@email.com', '2024-05-20'),
('S004', '박민지', '010-4444-4004', 'park@email.com', '2023-07-08'),
('S005', '최수진', '010-5555-5005', 'choi@email.com', '2024-02-14');
```

---

## 문제 6: 강좌 INSERT (5점)

```sql
INSERT INTO course (course_code, title, teacher, classroom, category, capacity, enrolled) VALUES
('C001', 'MySQL 입문', '박민수', '301호', 'DB', 30, 12),
('C002', 'Java 기초', '이서연', '202호', '프로그래밍', 25, 18),
('C003', '웹 퍼블리싱', '김하늘', '105호', '웹', 20, 15),
('C004', 'React 실습', '최준호', '401호', '프론트', 20, 8),
('C005', '알고리즘', '한지우', '203호', '프로그래밍', 15, 10);
```

---

## 문제 7: 수강신청 INSERT (5점)

```sql
INSERT INTO enroll (student_no, course_code, enroll_date, end_date, start_date, status) VALUES
('S001', 'C001', '2024-01-15', NULL, '2024-02-01', '수강중'),
('S002', 'C002', '2024-02-01', NULL, '2024-02-15', '수강중'),
('S001', 'C003', '2024-01-20', '2024-03-20', '2024-02-01', '수료'),
('S003', 'C004', '2024-03-10', NULL, '2024-03-20', '수강중'),
('S004', 'C005', '2024-02-25', NULL, '2024-03-01', '수강중');
```

**주의:** `student` · `course`에 없는 번호/코드는 FK 오류.

---

## 문제 8: 전체 조회 (5점)

```sql
SELECT * FROM student;
```

---

## 문제 9: 조건 조회 (5점)

```sql
SELECT course_code, title, teacher
FROM course
WHERE category = '프로그래밍';
```

(결과: C002 Java 기초, C005 알고리즘)

---

## 문제 10: JOIN (10점)

```sql
SELECT s.name
      ,c.title
      ,e.status
FROM student s
JOIN enroll e
  ON s.student_no = e.student_no
JOIN course c
  ON e.course_code = c.course_code;
```

---

## 문제 11: 집계 (5점)

```sql
SELECT category
      ,COUNT(*) AS 강좌수
      ,SUM(capacity) AS 정원합계
FROM course
GROUP BY category;
```

---

## 문제 12: EXISTS / IN (10점)

```sql
-- EXISTS
SELECT student_no, name
FROM student s
WHERE EXISTS (
    SELECT *
    FROM enroll e
    WHERE e.student_no = s.student_no
);

-- IN (동일 의미)
SELECT student_no, name
FROM student
WHERE student_no IN (
    SELECT DISTINCT student_no
    FROM enroll
);
```

(S001, S002, S003, S004 — S005는 신청 없음)

---

## 채점 참고

| 문항 | 감점 예 |
|------|---------|
| 1 | utf8 미설정 −2 |
| 2~4 | PK/FK/DEFAULT 누락 각 −1~2 |
| 5~7 | 행 수·컬럼 순서 오류 −1~3 |
| 10 | JOIN 조건 오류 −5 |
| 12 | 전체 수강생만 출력(미신청 포함) −5 |
