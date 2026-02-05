# SQL 종합 실습 문제 채점 결과 - 이희수 학생

**채점일:** 2026-01-30  
**총점:** 100점 만점  
**획득 점수:** 88점

---

## 채점 요약

| 영역 | 문제 번호 | 배점 | 획득 점수 | 비율 |
|------|----------|------|----------|------|
| **1. 데이터베이스/테이블 생성 및 데이터 삽입** | 1~7 | 35점 | 30점 | 86% |
| **2. 데이터 수정 및 삭제** | 8~12 | 25점 | 23점 | 92% |
| **3. 집계함수** | 13~17 | 25점 | 20점 | 80% |
| **4. JOIN** | 18~20 | 15점 | 15점 | 100% |
| **총계** | 1~20 | 100점 | **88점** | **88%** |

**등급:** B (양호)

---

## 문제별 상세 채점

### 영역 1: 데이터베이스/테이블 생성 및 데이터 삽입 (문제 1~7, 35점)

#### 문제 1: 데이터베이스 생성 (5점) → **5점**

**학생 답안:**
```sql
create database library_db character set utf8mb4 collate utf8mb4_unicode_ci;
```

**채점 결과:**
- ✅ 데이터베이스명 정확: 2점
- ✅ UTF-8 설정 (CHARACTER SET utf8mb4): 2점
- ✅ 정렬 규칙 설정 (COLLATE utf8mb4_unicode_ci): 1점

**감점 사유:**
- 없음

**피드백:**
- 완벽합니다! UTF-8 설정까지 정확히 작성했습니다.

---

#### 문제 2: 회원 테이블 생성 (5점) → **4점**

**학생 답안:**
```sql
create table member(
    member_id int primary key auto_increment,
    member_no char(20) unique not null,
    name varchar(50) not null,
    phone char(20) unique,
    email varchar(100),
    join_date date default '2026-02-04',
    status char(20) default '활성'
)character set utf8mb4 collate utf8mb4_unicode_ci;
```

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
);
```

**채점 결과:**
- ✅ 기본 구조 정확: 1점
- ✅ PRIMARY KEY 설정: 1점
- ✅ UNIQUE 제약조건 (member_no, phone): 1점
- ✅ NOT NULL 제약조건 (member_no, name): 1점
- ⚠️ DEFAULT 값: `join_date`의 DEFAULT가 하드코딩된 날짜 (`'2026-02-04'`)
  - 정답은 `DEFAULT (CURRENT_DATE)` (현재 날짜 함수)
  - 부분 점수: 0점

**감점 사유:**
- `join_date`의 DEFAULT 값이 하드코딩됨 (정답은 CURRENT_DATE): -1점

**피드백:**
- 대부분 완벽하지만 `join_date`의 기본값이 하드코딩되었습니다.
- 문제 조건: "기본값 현재 날짜" → `DEFAULT (CURRENT_DATE)` 사용 필요
- 하드코딩된 날짜는 매일 변경해야 하므로 함수 사용이 더 적합합니다.
- UTF-8 설정까지 추가한 것은 좋은 습관입니다!

**수정된 정답:**
```sql
create table member(
    member_id int primary key auto_increment,
    member_no char(20) unique not null,
    name varchar(50) not null,
    phone char(20) unique,
    email varchar(100),
    join_date date default (current_date),  -- 함수 사용
    status char(20) default '활성'
)character set utf8mb4 collate utf8mb4_unicode_ci;
```

---

#### 문제 3: 도서 테이블 생성 (5점) → **5점**

**학생 답안:**
```sql
create table book(
    book_id int primary key auto_increment,
    isbn char(20) unique not null,
    title varchar(200) not null,
    author varchar(100),
    publisher varchar(100),
    category varchar(50),
    total_copies int default 1,
    available_copies int default 1
)character set utf8mb4 collate utf8mb4_unicode_ci;
```

**채점 결과:**
- ✅ 기본 구조 정확: 1점
- ✅ PRIMARY KEY 설정: 1점
- ✅ UNIQUE, NOT NULL 제약조건: 1점
- ✅ 모든 컬럼 정의: 1점
- ✅ DEFAULT 값 설정: 1점

**감점 사유:**
- 없음

**피드백:**
- 완벽합니다! 모든 컬럼과 제약조건을 정확히 작성했습니다.
- UTF-8 설정까지 추가한 것은 좋습니다!

---

#### 문제 4: 대출 테이블 생성 (5점) → **4점**

**학생 답안:**
```sql
create table loan(
    loan_id int primary key auto_increment,
    member_no char(20),
    foreign key (member_no) references member(member_no),
    isbn char(20),
    foreign key (isbn) references book(isbn),
    loan_date date default '2026-02-04',
    return_date date,
    due_date date not null,
    status char(20) default '대출중'
)character set utf8mb4 collate utf8mb4_unicode_ci;
```

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
);
```

**채점 결과:**
- ✅ 기본 구조 정확: 1점
- ✅ PRIMARY KEY 설정: 1점
- ✅ 외래키 제약조건 2개 정확: 2점
- ⚠️ DEFAULT 값: `loan_date`의 DEFAULT가 하드코딩된 날짜 (`'2026-02-04'`)
  - 정답은 `DEFAULT (CURRENT_DATE)` (현재 날짜 함수)
  - 부분 점수: 0점

**감점 사유:**
- `loan_date`의 DEFAULT 값이 하드코딩됨 (정답은 CURRENT_DATE): -1점

**피드백:**
- 외래키 제약조건을 정확히 작성했습니다!
- 다만 `loan_date`의 기본값이 하드코딩되었습니다.
- 문제 조건: "기본값 현재 날짜" → `DEFAULT (CURRENT_DATE)` 사용 필요
- UTF-8 설정까지 추가한 것은 좋습니다!

**수정된 정답:**
```sql
create table loan(
    loan_id int primary key auto_increment,
    member_no char(20),
    foreign key (member_no) references member(member_no),
    isbn char(20),
    foreign key (isbn) references book(isbn),
    loan_date date default (current_date),  -- 함수 사용
    return_date date,
    due_date date not null,
    status char(20) default '대출중'
)character set utf8mb4 collate utf8mb4_unicode_ci;
```

---

#### 문제 5: 회원 데이터 삽입 (5점) → **5점**

**학생 답안:**
```sql
insert into member (member_no, name, phone, email, join_date) values
('M001', '홍길동', '010-1111-2222', 'hong@email.com', '2020-01-10'),
('M002', '김영희', '010-2222-3333', 'kim@email.com', '2021-03-15'),
('M003', '이철수', '010-3333-4444', 'lee@email.com', '2022-05-20'),
('M004', '박민지', '010-4444-5555', 'park@email.com', '2021-07-08'),
('M005', '최수진', '010-5555-6666', 'choi@email.com', '2023-02-14');
```

**채점 결과:**
- ✅ INSERT 문법 정확: 1점
- ✅ 컬럼명 정확: 1점
- ✅ 5개 행 모두 정확: 3점

**감점 사유:**
- 없음

**피드백:**
- 완벽합니다! 모든 데이터를 정확히 삽입했습니다.

---

#### 문제 6: 도서 데이터 삽입 (5점) → **5점**

**학생 답안:**
```sql
insert into book (isbn, title, author, publisher, category, total_copies, available_copies) values
('ISBN001', '자바의 정석', '남궁성', '도우출판', '프로그래밍', 5, 3),
('ISBN002', '이것이 자바다', '신용권', '한빛미디어', '프로그래밍', 3, 2),
('ISBN003', '해리포터', 'J.K.롤링', '문학수첩', '소설', 10, 5),
('ISBN004', '데이터베이스 개론', '이상호', '정익사', '컴퓨터', 4, 4),
('ISBN005', '알고리즘 문제해결', '구종만', '인사이트', '프로그래밍', 2, 1);
```

**채점 결과:**
- ✅ INSERT 문법 정확: 1점
- ✅ 컬럼명 정확: 1점
- ✅ 5개 행 모두 정확: 3점

**감점 사유:**
- 없음

**피드백:**
- 완벽합니다! 모든 데이터를 정확히 삽입했습니다.

---

#### 문제 7: 대출 데이터 삽입 (5점) → **5점**

**학생 답안:**
```sql
insert into loan (member_no, isbn, loan_date, return_date, due_date, status) values
('M001', 'ISBN001', '2024-01-15', NULL, '2024-02-15', '대출중'),
('M002', 'ISBN002', '2024-02-01', NULL, '2024-03-01', '대출중'),
('M001', 'ISBN003', '2024-01-20', '2024-02-10', '2024-02-20', '반납완료'),
('M003', 'ISBN004', '2024-03-10', NULL, '2024-04-10', '대출중'),
('M004', 'ISBN005', '2024-02-25', NULL, '2024-03-25', '대출중');
```

**채점 결과:**
- ✅ INSERT 문법 정확: 1점
- ✅ 컬럼명 정확: 1점
- ✅ NULL 값 처리 정확: 1점
- ✅ 5개 행 모두 정확: 2점

**감점 사유:**
- 없음

**피드백:**
- 완벽합니다! NULL 값 처리도 정확히 했습니다.

---

### 영역 2: 데이터 수정 및 삭제 (문제 8~12, 25점)

#### 문제 8: 회원 상태 변경 (5점) → **5점**

**학생 답안:**
```sql
update member set status = '휴면' where member_no = 'M005';
```

**채점 결과:**
- ✅ UPDATE 문법 정확: 2점
- ✅ SET 절 정확: 1점
- ✅ WHERE 절 정확: 2점

**감점 사유:**
- 없음

**피드백:**
- 완벽합니다! WHERE 절을 정확히 사용했습니다.

---

#### 문제 9: 도서 재고 업데이트 (5점) → **5점**

**학생 답안:**
```sql
update book set available_copies = available_copies + 1 where isbn = 'ISBN001';
```

**채점 결과:**
- ✅ UPDATE 문법 정확: 2점
- ✅ 계산식 사용 정확 (available_copies + 1): 2점
- ✅ WHERE 절 정확: 1점

**감점 사유:**
- 없음

**피드백:**
- 완벽합니다! 계산식을 사용하여 값을 증가시켰습니다.

---

#### 문제 10: 대출 반납 처리 (5점) → **3점**

**학생 답안:**
```sql
update loan set return_date = '2026-02-04', status = '반납완료' where loan_id = 4;
```

**정답:**
```sql
UPDATE loan 
SET return_date = CURRENT_DATE,
    status = '반납완료'
WHERE loan_id = 4;
```

**채점 결과:**
- ✅ UPDATE 문법 정확: 2점
- ✅ 여러 컬럼 동시 수정 정확: 2점
- ⚠️ 날짜 함수: 하드코딩된 날짜 사용 (`'2026-02-04'`)
  - 문제: "오늘 날짜로" → `CURRENT_DATE` 또는 `CURDATE()` 사용 필요
  - 부분 점수: 1점 (문법은 맞지만 요구사항과 다름)

**감점 사유:**
- 날짜를 하드코딩함 (정답은 현재 날짜 함수 사용): -2점

**피드백:**
- 문법은 완벽하지만 문제 요구사항을 정확히 따르지 않았습니다.
- 문제: "반납일을 **오늘 날짜로**"
- 답안: `'2026-02-04'` (특정 날짜 하드코딩)
- **개선**: `CURRENT_DATE` 또는 `CURDATE()` 함수를 사용해야 합니다.

**수정된 정답:**
```sql
update loan set return_date = current_date, status = '반납완료' where loan_id = 4;
```

---

#### 문제 11: 조건부 삭제 (5점) → **5점**

**학생 답안:**
```sql
delete from member where status = '휴면';
```

**채점 결과:**
- ✅ DELETE 문법 정확: 2점
- ✅ FROM 절 정확: 1점
- ✅ WHERE 절 정확: 2점

**감점 사유:**
- 없음

**피드백:**
- 완벽합니다! WHERE 절을 정확히 사용하여 조건부 삭제를 했습니다.

---

#### 문제 12: 특정 레코드 삭제 (5점) → **5점**

**학생 답안:**
```sql
delete from member where member_no = 'M005';
```

**채점 결과:**
- ✅ DELETE 문법 정확: 2점
- ✅ FROM 절 정확: 1점
- ✅ WHERE 절 정확: 2점

**감점 사유:**
- 없음

**피드백:**
- 완벽합니다! 특정 회원을 정확히 삭제했습니다.

---

### 영역 3: 집계함수 (문제 13~17, 25점)

#### 문제 13: 전체 도서 수 (5점) → **5점**

**학생 답안:**
```sql
select count(*) as 전체도서수 from book;
```

**채점 결과:**
- ✅ SELECT 문법 정확: 1점
- ✅ COUNT(*) 사용 정확: 3점
- ✅ 컬럼 별칭 사용: 1점

**감점 사유:**
- 없음

**피드백:**
- 완벽합니다! COUNT 함수와 별칭을 정확히 사용했습니다.

---

#### 문제 14: 카테고리별 도서 수 (5점) → **5점**

**학생 답안:**
```sql
select category as 카테고리, count(*) as 도서수 
from book 
group by category 
order by count(*) desc;
```

**채점 결과:**
- ✅ SELECT 문법 정확: 1점
- ✅ COUNT(*) 사용 정확: 1점
- ✅ GROUP BY 사용 정확: 2점
- ✅ ORDER BY 사용 (추가 보너스): 1점

**감점 사유:**
- 없음

**피드백:**
- 완벽합니다! GROUP BY를 사용하여 카테고리별로 그룹화했습니다.
- ORDER BY까지 추가하여 결과를 정렬했습니다. 좋은 습관입니다!

---

#### 문제 15: 현재 대출 중인 도서 수 (5점) → **5점**

**학생 답안:**
```sql
select count(*) as 대출중인도서수 from loan where status = '대출중';
```

**채점 결과:**
- ✅ SELECT 문법 정확: 1점
- ✅ COUNT(*) 사용 정확: 2점
- ✅ WHERE 절 정확: 2점

**감점 사유:**
- 없음

**피드백:**
- 완벽합니다! WHERE 절과 COUNT 함수를 함께 사용했습니다.

---

#### 문제 16: 회원별 대출 횟수 (5점) → **0점**

**학생 답안:**
```sql
select m.member_no as 회원번호, m.name as 이름, count(*) as 대출횟수
from member m
inner join loan l
on m.member_no = l.member_no
group by l.member_no;
```

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

**채점 결과:**
- ✅ SELECT 문법 정확: 1점
- ⚠️ JOIN: INNER JOIN 사용 (정답은 LEFT JOIN)
  - INNER JOIN은 대출하지 않은 회원 제외
  - 하지만 문제에서 "각 회원별"이라고 했으므로 LEFT JOIN이 더 적합
  - 부분 점수: 1점
- ❌ GROUP BY: `m.name` 누락
  - SELECT 절에 `m.name`이 있는데 GROUP BY에 없음
  - SQL 표준에 위배됨 (MySQL 5.7+에서는 에러 발생)
  - 심각한 오류: -5점

**감점 사유:**
- GROUP BY에 `m.name` 누락: -5점 (SQL 표준 위배)

**피드백:**
- **중요한 오류**: GROUP BY 절에 `m.name`이 누락되었습니다.
- SELECT 절에 있는 모든 컬럼(집계함수 제외)은 GROUP BY에 포함되어야 합니다.
- MySQL 5.7 이상에서는 이 쿼리가 에러를 발생시킵니다.
- 또한 LEFT JOIN을 사용하면 대출하지 않은 회원도 포함됩니다.

**수정된 정답:**
```sql
select m.member_no as 회원번호, m.name as 이름, count(l.loan_id) as 대출횟수
from member m
left join loan l
on m.member_no = l.member_no
group by m.member_no, m.name;  -- m.name 추가
```

---

#### 문제 17: HAVING 사용 (5점) → **5점**

**학생 답안:**
```sql
select m.member_no as 회원번호, count(*) as 대출횟수
from member m
inner join loan l
on m.member_no = l.member_no
group by l.member_no
having count(*) >=2;
```

**채점 결과:**
- ✅ SELECT 문법 정확: 1점
- ✅ GROUP BY 사용 정확: 1점
- ✅ HAVING 사용 정확: 2점
- ✅ 조건 정확 (>= 2): 1점

**감점 사유:**
- 없음

**피드백:**
- 완벽합니다! HAVING 절을 사용하여 그룹화된 결과에 조건을 적용했습니다.
- 정답에서는 LEFT JOIN을 사용했지만, loan 테이블만 사용해도 결과는 동일합니다.
- 다만 GROUP BY에 `m.member_no` 대신 `l.member_no`를 사용했는데, 이는 문제 없습니다.

---

### 영역 4: JOIN (문제 18~20, 15점)

#### 문제 18: INNER JOIN - 기본 조인 (5점) → **5점**

**학생 답안:**
```sql
SELECT m.name as 회원이름, b.title as 도서제목 
from loan l 
inner join member m 
on l.member_no = m.member_no 
inner join book b 
on l.isbn = b.isbn 
where l.status = '대출중' 
order by m.name;
```

**채점 결과:**
- ✅ SELECT 문법 정확: 1점
- ✅ INNER JOIN 2개 사용 정확: 2점
- ✅ 조인 조건 정확: 1점
- ✅ WHERE 절 정확: 1점

**감점 사유:**
- 없음

**피드백:**
- 완벽합니다! 3개 테이블을 INNER JOIN으로 정확히 연결했습니다.
- WHERE 절로 대출 중인 도서만 필터링했습니다.
- ORDER BY까지 추가하여 결과를 정렬했습니다. 좋은 습관입니다!

---

#### 문제 19: INNER JOIN - 조건부 조인 (5점) → **5점**

**학생 답안:**
```sql
SELECT m.name as 회원이름, b.title as 도서제목, b.category as 카테고리
from loan l 
inner join member m 
on l.member_no = m.member_no 
inner join book b 
on l.isbn = b.isbn 
where l.status = '대출중' and b.category = '프로그래밍' 
order by m.name;
```

**채점 결과:**
- ✅ SELECT 문법 정확: 1점
- ✅ INNER JOIN 2개 사용 정확: 2점
- ✅ WHERE 절 조건 정확 (카테고리 필터링): 1점
- ✅ 조인 조건 정확: 1점

**감점 사유:**
- 없음

**피드백:**
- 완벽합니다! JOIN 후 WHERE 절로 카테고리를 정확히 필터링했습니다.
- `l.status = '대출중'` 조건까지 추가하여 더 정확한 결과를 얻었습니다.
- 카테고리까지 SELECT에 포함시켜 더 명확하게 작성했습니다.
- ORDER BY까지 추가하여 결과를 정렬했습니다. 매우 좋습니다!

---

#### 문제 20: LEFT JOIN - 매칭되지 않는 데이터 찾기 (5점) → **5점**

**학생 답안:**
```sql
SELECT m.member_no as 회원번호, m.name as 회원이름
FROM member m 
left join loan l 
on m.member_no = l.member_no 
where l.member_no is null;
```

**채점 결과:**
- ✅ SELECT 문법 정확: 1점
- ✅ LEFT JOIN 사용 정확: 2점
- ✅ WHERE 절 조건 정확 (IS NULL): 2점

**감점 사유:**
- 없음

**피드백:**
- 완벽합니다! LEFT JOIN과 IS NULL을 정확히 사용하여 대출하지 않은 회원을 찾았습니다.
- 조인 조건 순서도 정확합니다 (`m.member_no = l.member_no`).

---

## 종합 평가

### 강점

1. **기본 문법 정확성**: 대부분의 SQL 문법을 정확히 작성했습니다.
2. **제약조건 이해**: PRIMARY KEY, FOREIGN KEY, UNIQUE, NOT NULL 등을 정확히 사용했습니다.
3. **집계함수 활용**: COUNT, GROUP BY, HAVING을 정확히 사용했습니다.
4. **JOIN 이해**: INNER JOIN과 LEFT JOIN을 정확히 구분하여 사용했습니다.
5. **ORDER BY 추가**: 문제에서 요구하지 않았지만 ORDER BY를 추가하여 더 나은 결과를 제공했습니다.
6. **UTF-8 설정**: 테이블 생성 시 UTF-8 설정까지 추가한 것은 좋은 습관입니다.

### 개선이 필요한 사항

1. **DEFAULT 값**: `join_date`, `loan_date`의 DEFAULT 값을 하드코딩 대신 `CURRENT_DATE` 함수 사용 필요
2. **날짜 함수**: 문제 10에서 하드코딩된 날짜 대신 `CURRENT_DATE` 사용 필요
3. **GROUP BY 문법**: 문제 16에서 SELECT 절의 컬럼이 GROUP BY에 누락 (SQL 표준 위배)

### 총평

**88점 (B등급)** - 기본적인 SQL 문법을 잘 이해하고 있으며, UPDATE/DELETE와 집계함수, JOIN 문법을 정확히 사용할 수 있습니다. 다만 일부 문법 오류와 날짜 함수 사용 습관화가 필요합니다.

**특히 칭찬할 점:**
- 모든 JOIN 문제를 완벽하게 해결했습니다.
- ORDER BY를 추가하여 결과를 정렬했습니다.
- UTF-8 설정까지 추가한 것은 좋은 습관입니다.
- 문제 19에서 추가 조건까지 고려하여 더 정확한 결과를 얻었습니다.

**추가 학습 권장 사항:**
1. DEFAULT 값 설정 시 함수 사용 습관화 (`CURRENT_DATE`)
2. GROUP BY 문법 정확히 학습 (SELECT 절의 모든 컬럼 포함)
3. 날짜 함수 (`CURRENT_DATE`, `CURDATE()`) 사용 습관화

---

## 문제별 점수 요약

| 문제 | 배점 | 획득 | 비고 |
|------|------|------|------|
| 1 | 5 | 5 | 완벽 |
| 2 | 5 | 4 | join_date DEFAULT 하드코딩 |
| 3 | 5 | 5 | 완벽 |
| 4 | 5 | 4 | loan_date DEFAULT 하드코딩 |
| 5 | 5 | 5 | 완벽 |
| 6 | 5 | 5 | 완벽 |
| 7 | 5 | 5 | 완벽 |
| 8 | 5 | 5 | 완벽 |
| 9 | 5 | 5 | 완벽 |
| 10 | 5 | 3 | 날짜 하드코딩 |
| 11 | 5 | 5 | 완벽 |
| 12 | 5 | 5 | 완벽 |
| 13 | 5 | 5 | 완벽 |
| 14 | 5 | 5 | 완벽 |
| 15 | 5 | 5 | 완벽 |
| 16 | 5 | 0 | GROUP BY에 m.name 누락 |
| 17 | 5 | 5 | 완벽 |
| 18 | 5 | 5 | 완벽 |
| 19 | 5 | 5 | 완벽 |
| 20 | 5 | 5 | 완벽 |
| **총계** | **100** | **88** | **B등급** |

---

## 주요 오류 정리

### 1. DEFAULT 값 하드코딩
```sql
-- ❌ 잘못된 문법
join_date date default '2026-02-04'

-- ✅ 올바른 문법
join_date date default (current_date)
```

### 2. 날짜 하드코딩
```sql
-- ❌ 잘못된 문법
set return_date = '2026-02-04'

-- ✅ 올바른 문법
set return_date = current_date
```

### 3. GROUP BY 문법 오류
```sql
-- ❌ 잘못된 문법
select m.member_no, m.name, count(*)
from member m
group by m.member_no;  -- m.name 누락

-- ✅ 올바른 문법
select m.member_no, m.name, count(*)
from member m
group by m.member_no, m.name;  -- m.name 포함
```

---

**채점 완료일:** 2026-01-30  
**채점자:** AI Assistant
