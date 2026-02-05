# 데이터베이스 종합 실습 평가 내용 및 채점기준 - 도서관 시스템

## 평가 개요

**과목명:** 데이터베이스 SQL 실습  
**평가 유형:** 실기 평가  
**총점:** 100점  
**소요 시간:** 90분

---

## 평가 영역별 구성

| 영역 | 문제 번호 | 배점 | 비율 |
|------|----------|------|------|
| **1. 데이터베이스/테이블 생성 및 데이터 삽입** | 1~7 | 35점 | 35% |
| **2. 데이터 수정 및 삭제** | 8~12 | 25점 | 25% |
| **3. 집계함수** | 13~17 | 25점 | 25% |
| **4. JOIN** | 18~20 | 15점 | 15% |
| **총계** | 1~20 | 100점 | 100% |

---

## 영역 1: 데이터베이스/테이블 생성 및 데이터 삽입 (문제 1~7, 35점)

### 평가 내용

데이터베이스 생성, 테이블 생성, 데이터 삽입에 대한 기본 능력을 평가합니다.

**포함 문제:**
- 문제 1: 데이터베이스 생성 (5점)
- 문제 2: 회원 테이블 생성 (5점)
- 문제 3: 도서 테이블 생성 (5점)
- 문제 4: 대출 테이블 생성 (5점)
- 문제 5: 회원 데이터 삽입 (5점)
- 문제 6: 도서 데이터 삽입 (5점)
- 문제 7: 대출 데이터 삽입 (5점)

### 채점기준

#### 문제 1: 데이터베이스 생성 (5점)

**정답:**
```sql
CREATE DATABASE library_db 
CHARACTER SET utf8mb4 
COLLATE utf8mb4_unicode_ci;
```

**채점 기준:**
- 데이터베이스명 정확: 2점
- UTF-8 설정 (CHARACTER SET utf8mb4): 2점
- 정렬 규칙 설정 (COLLATE utf8mb4_unicode_ci): 1점

**부분 점수:**
- 문법 오류 없이 작성: -1점 감점
- UTF-8 설정 누락: -2점 감점

---

#### 문제 2: 회원 테이블 생성 (5점)

**정답:**
```sql
CREATE TABLE member (
    member_id INT PRIMARY KEY AUTO_INCREMENT,
    member_no CHAR(20) UNIQUE NOT NULL,
    name VARCHAR(50) NOT NULL,
    phone CHAR(20) UNIQUE,
    email VARCHAR(100),
    join_date DATE DEFAULT (CURRENT_DATE),
    status VARCHAR(20) DEFAULT '활성'
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

**채점 기준:**
- 기본 구조 정확 (CREATE TABLE, 컬럼 정의): 1점
- PRIMARY KEY 설정: 1점
- UNIQUE 제약조건 (member_no, phone): 1점
- NOT NULL 제약조건 (member_no, name): 1점
- DEFAULT 값 설정 (join_date, status): 1점

**부분 점수:**
- 각 제약조건 누락 시 해당 점수 감점
- 데이터 타입 오류: -0.5점 감점

---

#### 문제 3: 도서 테이블 생성 (5점)

**정답:**
```sql
CREATE TABLE book (
    book_id INT PRIMARY KEY AUTO_INCREMENT,
    isbn CHAR(20) UNIQUE NOT NULL,
    title VARCHAR(200) NOT NULL,
    author VARCHAR(100),
    publisher VARCHAR(100),
    category VARCHAR(50),
    total_copies INT DEFAULT 1,
    available_copies INT DEFAULT 1
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

**채점 기준:**
- 기본 구조 정확: 1점
- PRIMARY KEY 설정: 1점
- UNIQUE, NOT NULL 제약조건: 1점
- 모든 컬럼 정의: 1점
- DEFAULT 값 설정: 1점

**부분 점수:**
- 컬럼 누락 시 해당 점수 감점
- 데이터 타입 오류: -0.5점 감점

---

#### 문제 4: 대출 테이블 생성 (5점)

**정답:**
```sql
CREATE TABLE loan (
    loan_id INT PRIMARY KEY AUTO_INCREMENT,
    member_no CHAR(20),
    isbn CHAR(20),
    loan_date DATE DEFAULT (CURRENT_DATE),
    return_date DATE,
    due_date DATE NOT NULL,
    status VARCHAR(20) DEFAULT '대출중',
    FOREIGN KEY (member_no) REFERENCES member(member_no),
    FOREIGN KEY (isbn) REFERENCES book(isbn)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

**채점 기준:**
- 기본 구조 정확: 1점
- PRIMARY KEY 설정: 1점
- 외래키 제약조건 2개 (member_no, isbn): 2점
- DEFAULT 값 및 NOT NULL 설정: 1점

**부분 점수:**
- 외래키 1개 누락: -1점 감점
- 외래키 문법 오류: -1점 감점

---

#### 문제 5: 회원 데이터 삽입 (5점)

**정답:**
```sql
INSERT INTO member (member_no, name, phone, email, join_date) VALUES
('M001', '홍길동', '010-1111-2222', 'hong@email.com', '2020-01-10'),
('M002', '김영희', '010-2222-3333', 'kim@email.com', '2021-03-15'),
('M003', '이철수', '010-3333-4444', 'lee@email.com', '2022-05-20'),
('M004', '박민지', '010-4444-5555', 'park@email.com', '2021-07-08'),
('M005', '최수진', '010-5555-6666', 'choi@email.com', '2023-02-14');
```

**채점 기준:**
- INSERT 문법 정확: 1점
- 컬럼명 정확: 1점
- 5개 행 모두 정확: 3점 (행당 0.6점)

**부분 점수:**
- 문법 오류: -1점 감점
- 데이터 누락: 행당 -0.6점 감점
- 데이터 오류: 행당 -0.3점 감점

---

#### 문제 6: 도서 데이터 삽입 (5점)

**정답:**
```sql
INSERT INTO book (isbn, title, author, publisher, category, total_copies, available_copies) VALUES
('ISBN001', '자바의 정석', '남궁성', '도우출판', '프로그래밍', 5, 3),
('ISBN002', '이것이 자바다', '신용권', '한빛미디어', '프로그래밍', 3, 2),
('ISBN003', '해리포터', 'J.K.롤링', '문학수첩', '소설', 10, 5),
('ISBN004', '데이터베이스 개론', '이상호', '정익사', '컴퓨터', 4, 4),
('ISBN005', '알고리즘 문제해결', '구종만', '인사이트', '프로그래밍', 2, 1);
```

**채점 기준:**
- INSERT 문법 정확: 1점
- 컬럼명 정확: 1점
- 5개 행 모두 정확: 3점 (행당 0.6점)

**부분 점수:**
- 문법 오류: -1점 감점
- 데이터 누락: 행당 -0.6점 감점

---

#### 문제 7: 대출 데이터 삽입 (5점)

**정답:**
```sql
INSERT INTO loan (member_no, isbn, loan_date, return_date, due_date, status) VALUES
('M001', 'ISBN001', '2024-01-15', NULL, '2024-02-15', '대출중'),
('M002', 'ISBN002', '2024-02-01', NULL, '2024-03-01', '대출중'),
('M001', 'ISBN003', '2024-01-20', '2024-02-10', '2024-02-20', '반납완료'),
('M003', 'ISBN004', '2024-03-10', NULL, '2024-04-10', '대출중'),
('M004', 'ISBN005', '2024-02-25', NULL, '2024-03-25', '대출중');
```

**채점 기준:**
- INSERT 문법 정확: 1점
- 컬럼명 정확: 1점
- NULL 값 처리 정확: 1점
- 5개 행 모두 정확: 2점 (행당 0.4점)

**부분 점수:**
- 문법 오류: -1점 감점
- NULL 처리 오류: -1점 감점
- 데이터 누락: 행당 -0.4점 감점

---

## 영역 2: 데이터 수정 및 삭제 (문제 8~12, 25점)

### 평가 내용

데이터 수정(UPDATE)과 삭제(DELETE) 문법 및 조건 사용 능력을 평가합니다.

**포함 문제:**
- 문제 8: 회원 상태 변경 (5점)
- 문제 9: 도서 재고 업데이트 (5점)
- 문제 10: 대출 반납 처리 (5점)
- 문제 11: 조건부 삭제 (5점)
- 문제 12: 특정 레코드 삭제 (5점)

### 채점기준

#### 문제 8: 회원 상태 변경 (5점)

**정답:**
```sql
UPDATE member 
SET status = '휴면'
WHERE member_no = 'M005';
```

**채점 기준:**
- UPDATE 문법 정확: 2점
- SET 절 정확: 1점
- WHERE 절 정확: 2점

**부분 점수:**
- 문법 오류: -2점 감점
- WHERE 절 누락: -2점 감점 (심각한 오류)
- 조건 오류: -1점 감점

---

#### 문제 9: 도서 재고 업데이트 (5점)

**정답:**
```sql
UPDATE book 
SET available_copies = available_copies + 1
WHERE isbn = 'ISBN001';
```

**채점 기준:**
- UPDATE 문법 정확: 2점
- 계산식 사용 정확 (available_copies + 1): 2점
- WHERE 절 정확: 1점

**부분 점수:**
- 문법 오류: -2점 감점
- 계산식 오류: -2점 감점
- WHERE 절 누락: -1점 감점

---

#### 문제 10: 대출 반납 처리 (5점)

**정답:**
```sql
UPDATE loan 
SET return_date = CURRENT_DATE,
    status = '반납완료'
WHERE loan_id = 3;
```

**채점 기준:**
- UPDATE 문법 정확: 2점
- 여러 컬럼 동시 수정 정확: 2점
- 날짜 함수 사용 정확: 1점

**부분 점수:**
- 문법 오류: -2점 감점
- 여러 컬럼 수정 문법 오류: -1점 감점
- 날짜 함수 오류: -1점 감점

---

#### 문제 11: 조건부 삭제 (5점)

**정답:**
```sql
DELETE FROM member 
WHERE status = '휴면';
```

**채점 기준:**
- DELETE 문법 정확: 2점
- FROM 절 정확: 1점
- WHERE 절 정확: 2점

**부분 점수:**
- 문법 오류: -2점 감점
- WHERE 절 누락: -2점 감점 (심각한 오류 - 모든 데이터 삭제)
- 조건 오류: -1점 감점

---

#### 문제 12: 특정 레코드 삭제 (5점)

**정답:**
```sql
DELETE FROM member 
WHERE member_no = 'M005';
```

**채점 기준:**
- DELETE 문법 정확: 2점
- FROM 절 정확: 1점
- WHERE 절 정확: 2점

**부분 점수:**
- 문법 오류: -2점 감점
- WHERE 절 누락: -2점 감점 (심각한 오류)
- 조건 오류: -1점 감점

---

## 영역 3: 집계함수 (문제 13~17, 25점)

### 평가 내용

집계함수(COUNT, AVG, MAX, MIN, SUM)와 GROUP BY, HAVING 사용 능력을 평가합니다.

**포함 문제:**
- 문제 13: 전체 도서 수 (5점)
- 문제 14: 카테고리별 도서 수 (5점)
- 문제 15: 현재 대출 중인 도서 수 (5점)
- 문제 16: 회원별 대출 횟수 (5점)
- 문제 17: HAVING 사용 (5점)

### 채점기준

#### 문제 13: 전체 도서 수 (5점)

**정답:**
```sql
SELECT COUNT(*) AS 도서종류수
FROM book;
```

**채점 기준:**
- SELECT 문법 정확: 1점
- COUNT(*) 사용 정확: 3점
- 컬럼 별칭 사용: 1점

**부분 점수:**
- 문법 오류: -1점 감점
- COUNT 함수 오류: -2점 감점
- 별칭 누락: -1점 감점

---

#### 문제 14: 카테고리별 도서 수 (5점)

**정답:**
```sql
SELECT 
    category AS 카테고리,
    COUNT(*) AS 도서수
FROM book
GROUP BY category
ORDER BY 도서수 DESC;
```

**채점 기준:**
- SELECT 문법 정확: 1점
- COUNT(*) 사용 정확: 1점
- GROUP BY 사용 정확: 2점
- ORDER BY 사용: 1점

**부분 점수:**
- 문법 오류: -1점 감점
- GROUP BY 누락: -2점 감점
- GROUP BY 컬럼 오류: -1점 감점
- ORDER BY 누락: -1점 감점

---

#### 문제 15: 현재 대출 중인 도서 수 (5점)

**정답:**
```sql
SELECT COUNT(*) AS 대출중인도서수
FROM loan
WHERE status = '대출중';
```

**채점 기준:**
- SELECT 문법 정확: 1점
- COUNT(*) 사용 정확: 2점
- WHERE 절 정확: 2점

**부분 점수:**
- 문법 오류: -1점 감점
- COUNT 함수 오류: -2점 감점
- WHERE 절 누락: -2점 감점
- 조건 오류: -1점 감점

---

#### 문제 16: 회원별 대출 횟수 (5점)

**정답:**
```sql
SELECT 
    m.member_no AS 회원번호,
    m.name AS 이름,
    COUNT(l.loan_id) AS 대출횟수
FROM member m
LEFT JOIN loan l ON m.member_no = l.member_no
GROUP BY m.member_no, m.name
ORDER BY 대출횟수 DESC;
```

**채점 기준:**
- SELECT 문법 정확: 1점
- LEFT JOIN 사용 정확: 2점
- COUNT와 GROUP BY 조합 정확: 1점
- ORDER BY 사용: 1점

**부분 점수:**
- 문법 오류: -1점 감점
- JOIN 누락 또는 오류: -2점 감점
- GROUP BY 누락: -1점 감점
- GROUP BY 컬럼 오류: -0.5점 감점

---

#### 문제 17: HAVING 사용 (5점)

**정답:**
```sql
SELECT 
    m.member_no AS 회원번호,
    COUNT(l.loan_id) AS 대출횟수
FROM member m
LEFT JOIN loan l ON m.member_no = l.member_no
GROUP BY m.member_no
HAVING COUNT(l.loan_id) >= 2
ORDER BY 대출횟수 DESC;
```

**채점 기준:**
- SELECT 문법 정확: 1점
- GROUP BY 사용 정확: 1점
- HAVING 사용 정확: 2점
- 조건 정확 (>= 2): 1점

**부분 점수:**
- 문법 오류: -1점 감점
- GROUP BY 누락: -1점 감점
- HAVING 누락: -2점 감점
- HAVING 조건 오류: -1점 감점
- WHERE와 HAVING 혼동: -1점 감점

---

## 영역 4: JOIN (문제 18~20, 15점)

### 평가 내용

INNER JOIN과 LEFT JOIN 사용 능력 및 복잡한 쿼리 작성 능력을 평가합니다.

**포함 문제:**
- 문제 18: INNER JOIN - 기본 조인 (5점)
- 문제 19: INNER JOIN - 조건부 조인 (5점)
- 문제 20: LEFT JOIN - 매칭되지 않는 데이터 찾기 (5점)

### 채점기준

#### 문제 18: INNER JOIN - 기본 조인 (5점)

**정답:**
```sql
SELECT 
    m.name AS 회원이름,
    b.title AS 도서제목,
    l.loan_date AS 대출일,
    l.due_date AS 반납예정일
FROM loan l
INNER JOIN member m ON l.member_no = m.member_no
INNER JOIN book b ON l.isbn = b.isbn
WHERE l.status = '대출중'
ORDER BY l.loan_date DESC;
```

**채점 기준:**
- SELECT 문법 정확: 1점
- INNER JOIN 2개 사용 정확: 2점
- 조인 조건 정확: 1점
- WHERE 절 정확: 1점

**부분 점수:**
- 문법 오류: -1점 감점
- JOIN 누락: -2점 감점
- JOIN 1개만 사용: -1점 감점
- 조인 조건 오류: -1점 감점
- WHERE 절 누락: -1점 감점

---

#### 문제 19: INNER JOIN - 조건부 조인 (5점)

**정답:**
```sql
SELECT 
    m.name AS 회원이름,
    b.title AS 도서제목,
    b.category AS 카테고리
FROM loan l
INNER JOIN member m ON l.member_no = m.member_no
INNER JOIN book b ON l.isbn = b.isbn
WHERE b.category = '프로그래밍'
ORDER BY m.name, b.title;
```

**채점 기준:**
- SELECT 문법 정확: 1점
- INNER JOIN 2개 사용 정확: 2점
- WHERE 절 조건 정확 (카테고리 필터링): 1점
- ORDER BY 사용: 1점

**부분 점수:**
- 문법 오류: -1점 감점
- JOIN 누락: -2점 감점
- WHERE 절 누락: -1점 감점
- 조건 오류: -1점 감점

---

#### 문제 20: LEFT JOIN - 매칭되지 않는 데이터 찾기 (5점)

**정답:**
```sql
SELECT 
    m.member_no AS 회원번호,
    m.name AS 회원이름,
    m.join_date AS 가입일
FROM member m
LEFT JOIN loan l ON m.member_no = l.member_no
WHERE l.member_no IS NULL;
```

**채점 기준:**
- SELECT 문법 정확: 1점
- LEFT JOIN 사용 정확: 2점
- WHERE 절 조건 정확 (IS NULL): 2점

**부분 점수:**
- 문법 오류: -1점 감점
- LEFT JOIN 대신 INNER JOIN 사용: -2점 감점
- WHERE 절 누락: -2점 감점
- IS NULL 대신 = NULL 사용: -1점 감점
- 조건 오류: -1점 감점

---

## 종합 평가 기준

### 점수 등급

| 점수 | 등급 | 평가 |
|------|------|------|
| 90점 이상 | A | 우수 |
| 80점 이상 | B | 양호 |
| 70점 이상 | C | 보통 |
| 60점 이상 | D | 미흡 |
| 60점 미만 | F | 재평가 필요 |

### 감점 기준

#### 심각한 오류 (각 항목당 -2점)
- WHERE 절 누락으로 인한 전체 데이터 수정/삭제
- 문법 오류로 인한 실행 불가
- 외래키 제약조건 위반

#### 일반 오류 (각 항목당 -1점)
- 제약조건 누락
- 컬럼명 오류
- 조건 오류
- 함수 사용 오류

#### 경미한 오류 (각 항목당 -0.5점)
- 데이터 타입 오류
- 별칭 누락
- 정렬 누락

---

## 채점 시 주의사항

### 1. 부분 점수 부여
- 문법은 맞지만 조건이 약간 틀린 경우 부분 점수 부여
- 실행 가능한 SQL이면 최소 점수 부여

### 2. 동의어 허용
- `CURRENT_DATE`와 `CURDATE()` 동일하게 인정
- `COUNT(*)`와 `COUNT(컬럼명)` 결과가 같으면 인정

### 3. 실행 결과 확인
- SQL 실행 결과가 정확하면 문법 오류가 있어도 부분 점수 부여
- 실행 불가능한 SQL은 해당 문제 0점 처리

### 4. 창의적 해결 방법 인정
- 정답과 다르지만 결과가 동일한 경우 인정
- 예: 서브쿼리 사용, 다른 JOIN 방법 사용 등

---

## 평가 시 주의사항

### 시험 환경
- MySQL Workbench 또는 명령줄 도구 사용 가능
- 인터넷 검색 불가
- 교재 및 참고 자료 사용 가능 (선택 사항)

### 제출 방법
- SQL 파일 제출 또는 스크린샷 제출
- 실행 결과 포함 권장

### 시간 배분 권장
- 영역 1 (문제 1~7): 30분
- 영역 2 (문제 8~12): 15분
- 영역 3 (문제 13~17): 25분
- 영역 4 (문제 18~20): 20분

---

**평가 기준을 참고하여 공정하고 객관적인 채점을 진행하세요.**
