# SQL 종합 실습 — 학원 수강 시스템 (기초 DDL·DML)

※ 채점: SQL을 직접 작성하거나 실행 화면을 캡처하시오.  
※ 도서관(`library_db`)과 **같은 형식**, 도메인만 **학원 수강**입니다.

**정답:** [SQL-종합실습-학원수강-정답.md](./SQL-종합실습-학원수강-정답.md)  
**더미(한 번에 실행):** [sql/학원수강-더미.sql](./sql/학원수강-더미.sql)

---

## 문제 1: 데이터베이스 생성 (5점)

`academy_db`라는 이름의 데이터베이스를 생성하시오. (UTF-8 설정 포함)

---

## 문제 2: 테이블 생성 — 수강생 테이블 (5점)

다음 조건에 맞는 `student` 테이블을 생성하시오.

**조건:**
- `student_id`: 정수형, 기본키, 자동증가
- `student_no`: 문자형(20자), 고유값, NULL 불가
- `name`: 문자형(50자), NULL 불가
- `phone`: 문자형(20자), 고유값
- `email`: 문자형(100자)
- `join_date`: 날짜형, 기본값 현재 날짜
- `status`: 문자형(20자), 기본값 '수강중'

---

## 문제 3: 테이블 생성 — 강좌 테이블 (5점)

다음 조건에 맞는 `course` 테이블을 생성하시오.

**조건:**
- `course_id`: 정수형, 기본키, 자동증가
- `course_code`: 문자형(20자), 고유값, NULL 불가
- `title`: 문자형(200자), NULL 불가
- `teacher`: 문자형(100자)
- `classroom`: 문자형(50자)
- `category`: 문자형(50자)
- `capacity`: 정수형, 기본값 20
- `enrolled`: 정수형, 기본값 0

---

## 문제 4: 테이블 생성 — 수강신청 테이블 (5점)

다음 조건에 맞는 `enroll` 테이블을 생성하시오.

**조건:**
- `enroll_id`: 정수형, 기본키, 자동증가
- `student_no`: 문자형(20자) — `student` 테이블의 `student_no` 참조
- `course_code`: 문자형(20자) — `course` 테이블의 `course_code` 참조
- `enroll_date`: 날짜형, 기본값 현재 날짜
- `end_date`: 날짜형
- `start_date`: 날짜형, NULL 불가
- `status`: 문자형(20자), 기본값 '신청'

---

## 문제 5: 데이터 삽입 — 수강생 데이터 (5점)

다음 데이터를 `student` 테이블에 삽입하시오.

| student_no | name | phone | email | join_date |
|------------|------|-------|-------|-----------|
| S001 | 홍길동 | 010-1111-1001 | hong@email.com | 2023-01-10 |
| S002 | 김영희 | 010-2222-2002 | kim@email.com | 2023-03-15 |
| S003 | 이철수 | 010-3333-3003 | lee@email.com | 2024-05-20 |
| S004 | 박민지 | 010-4444-4004 | park@email.com | 2023-07-08 |
| S005 | 최수진 | 010-5555-5005 | choi@email.com | 2024-02-14 |

(`student_id`는 자동증가, `status`는 기본값 사용)

---

## 문제 6: 데이터 삽입 — 강좌 데이터 (5점)

다음 데이터를 `course` 테이블에 삽입하시오.

| course_code | title | teacher | classroom | category | capacity | enrolled |
|-------------|-------|---------|-----------|----------|----------|----------|
| C001 | MySQL 입문 | 박민수 | 301호 | DB | 30 | 12 |
| C002 | Java 기초 | 이서연 | 202호 | 프로그래밍 | 25 | 18 |
| C003 | 웹 퍼블리싱 | 김하늘 | 105호 | 웹 | 20 | 15 |
| C004 | React 실습 | 최준호 | 401호 | 프론트 | 20 | 8 |
| C005 | 알고리즘 | 한지우 | 203호 | 프로그래밍 | 15 | 10 |

---

## 문제 7: 데이터 삽입 — 수강신청 데이터 (5점)

다음 데이터를 `enroll` 테이블에 삽입하시오.

| student_no | course_code | enroll_date | end_date | start_date | status |
|------------|-------------|-------------|---------|------------|--------|
| S001 | C001 | 2024-01-15 | NULL | 2024-02-01 | 수강중 |
| S002 | C002 | 2024-02-01 | NULL | 2024-02-15 | 수강중 |
| S001 | C003 | 2024-01-20 | 2024-03-20 | 2024-02-01 | 수료 |
| S003 | C004 | 2024-03-10 | NULL | 2024-03-20 | 수강중 |
| S004 | C005 | 2024-02-25 | NULL | 2024-03-01 | 수강중 |

---

## 문제 8: 조회 — 전체 수강생 (5점)

`student` 테이블의 **모든 컬럼**을 조회하시오.

---

## 문제 9: 조회 — 조건 (5점)

카테고리가 **'프로그래밍'** 인 강좌의 `course_code`, `title`, `teacher`를 조회하시오.

---

## 문제 10: JOIN (10점)

수강생 **이름**, 강좌 **제목**, 수강 **상태**를 JOIN으로 조회하시오.  
(`student` · `enroll` · `course`)

---

## 문제 11: 집계 (5점)

**카테고리별** 강좌 수와 정원 합계를 구하시오.  
(`category`, `강좌수`, `정원합계`)

---

## 문제 12: 서브쿼리 / EXISTS (10점)

**한 번이라도 수강신청한** 수강생의 `student_no`, `name`을 조회하시오.  
(`EXISTS` 또는 `IN` 사용)

---

## 참고: 테이블 구조

### student (수강생)
- student_id (PK, AUTO_INCREMENT)
- student_no (UNIQUE, NOT NULL)
- name (NOT NULL)
- phone (UNIQUE)
- email
- join_date (DEFAULT CURRENT_DATE)
- status (DEFAULT '수강중')

### course (강좌)
- course_id (PK, AUTO_INCREMENT)
- course_code (UNIQUE, NOT NULL)
- title (NOT NULL)
- teacher
- classroom
- category
- capacity (DEFAULT 20)
- enrolled (DEFAULT 0)

### enroll (수강신청)
- enroll_id (PK, AUTO_INCREMENT)
- student_no (FK → student.student_no)
- course_code (FK → course.course_code)
- enroll_date (DEFAULT CURRENT_DATE)
- end_date
- start_date (NOT NULL)
- status (DEFAULT '신청')

---

## 도서관 문제와의 대응

| 도서관 | 학원 |
|--------|------|
| library_db | academy_db |
| member | student |
| book | course |
| loan | enroll |
| member_no / isbn | student_no / course_code |

---

## 채점

| 문항 | 배점 |
|------|------|
| 1~7 | 각 5점 (35) |
| 8~9 | 각 5점 (10) |
| 10 | 10 |
| 11 | 5 |
| 12 | 10 |
| **합** | **70** |

(1~7만 실시하면 **35점** 만점으로 채점 가능)
