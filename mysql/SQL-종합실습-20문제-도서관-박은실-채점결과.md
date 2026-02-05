# SQL 종합 실습 문제 채점 결과 - 박은실 학생

**채점일:** 2026-01-30  
**총점:** 100점 만점  
**획득 점수:** 83점

---

## 채점 요약

| 영역 | 문제 번호 | 배점 | 획득 점수 | 비율 |
|------|----------|------|----------|------|
| **1. 데이터베이스/테이블 생성 및 데이터 삽입** | 1~7 | 35점 | 18점 | 51% |
| **2. 데이터 수정 및 삭제** | 8~12 | 25점 | 25점 | 100% |
| **3. 집계함수** | 13~17 | 25점 | 25점 | 100% |
| **4. JOIN** | 18~20 | 15점 | 15점 | 100% |
| **총계** | 1~20 | 100점 | **83점** | **83%** |

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
- 완벽합니다! 정답과 일치합니다.

---

#### 문제 2: 회원 테이블 생성 (5점) → **5점**

**학생 답안:**
```sql
create table member(
    member_id int primary key auto_increment,
    member_no varchar(20) unique not null,
    name varchar(50) not null,
    phone varchar(20) unique,
    email varchar(100),
    join_date date default (current_date),
    status varchar(20) default '활성'
);
```

**채점 결과:**
- ✅ 기본 구조 정확: 1점
- ✅ PRIMARY KEY 설정: 1점
- ✅ UNIQUE 제약조건 (member_no, phone): 1점
- ✅ NOT NULL 제약조건 (member_no, name): 1점
- ✅ DEFAULT 값 설정 (join_date, status): 1점

**감점 사유:**
- 없음

**피드백:**
- 완벽합니다! 모든 제약조건을 정확히 작성했습니다.

---

#### 문제 3: 도서 테이블 생성 (5점) → **5점**

**학생 답안:**
```sql
create table book(
    book_id int primary key auto_increment,
    isbn varchar(20) unique not null,
    title varchar(200) not null,
    author varchar(100),
    publisher varchar(100),
    category varchar(50),
    total_copies int default 1,
    available_copies int default 1
);
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

---

#### 문제 4: 대출 테이블 생성 (5점) → **0점**

**학생 답안:**
```sql
create table loan(
    loan_id int primary key auto_increment,
    member_no varchar(20),
    isbn varchar(200),
    loan_date date (current_date),
    return_date date,
    due_date date not null,
    status varchar(20) default '대출중',
    foreign key member_no references member (member_no),
    foreign key isbn references book (isbn)
);
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
- ❌ 기본 구조: 문법 오류로 인해 실행 불가
- ❌ PRIMARY KEY 설정: 1점 (부분 점수)
- ❌ 외래키 제약조건: 문법 오류로 인해 실행 불가
- ❌ DEFAULT 값 및 NOT NULL 설정: 문법 오류

**주요 오류:**

1. **`isbn varchar(200)`** - 정답은 `varchar(20)` 또는 `char(20)`
   - 데이터 타입 크기 오류: -0.5점

2. **`loan_date date (current_date)`** - 문법 오류
   - `DEFAULT (CURRENT_DATE)`가 되어야 함
   - 문법 오류: -2점

3. **`foreign key member_no references member (member_no)`** - 문법 오류
   - `FOREIGN KEY (member_no) REFERENCES member(member_no)`가 되어야 함
   - 괄호 누락: -2점

4. **`foreign key isbn references book (isbn)`** - 문법 오류
   - `FOREIGN KEY (isbn) REFERENCES book(isbn)`가 되어야 함
   - 괄호 누락: -2점

**감점 사유:**
- 문법 오류로 인한 실행 불가: -5점

**피드백:**
- **중요한 문법 오류가 있습니다!**
  1. **DEFAULT 문법**: `date (current_date)` → `date default (current_date)`
  2. **FOREIGN KEY 문법**: `foreign key member_no` → `foreign key (member_no)`
     - FOREIGN KEY 뒤에 괄호로 컬럼명을 감싸야 합니다.
  3. **데이터 타입 크기**: `isbn varchar(200)` → `isbn varchar(20)`
     - ISBN은 보통 20자 이하입니다.

**수정된 정답:**
```sql
create table loan(
    loan_id int primary key auto_increment,
    member_no varchar(20),
    isbn varchar(20),  -- 200 → 20으로 수정
    loan_date date default (current_date),  -- 괄호 추가
    return_date date,
    due_date date not null,
    status varchar(20) default '대출중',
    foreign key (member_no) references member(member_no),  -- 괄호 추가
    foreign key (isbn) references book(isbn)  -- 괄호 추가
);
```

---

#### 문제 5: 회원 데이터 삽입 (5점) → **3점**

**학생 답안:**
```sql
insert into member (member_no,name,phone,email,join_date) values
('M001', '홍길동', '010-1111-2222', 'hong@email.com', '2020-01-10'),
('M002', '김영희', '010-2222-3333', 'kim@email.com', '2021-03-15'),
('M003' '이철수', '010-3333-4444', 'lee@email.com', '2022-05-20'),
('M004', '박민지', '010-4444-5555', 'park@email.com', '2021-07-08'),
('M005', '최수진', '010-5555-6666', 'choi@email.com', '2023-02-14');
```

**채점 결과:**
- ✅ INSERT 문법 정확: 1점
- ✅ 컬럼명 정확: 1점
- ⚠️ 5개 행: 3번째 행에 문법 오류
  - `('M003' '이철수', ...)` - 쉼표 누락
  - 문법 오류로 인해 실행 불가: -2점

**감점 사유:**
- 문법 오류 (쉼표 누락): -2점

**피드백:**
- **문법 오류**: `('M003' '이철수', ...)` → `('M003', '이철수', ...)`
- VALUES 절에서 각 값 사이에 쉼표(`,`)가 필요합니다.
- 이 오류로 인해 전체 INSERT 문이 실행되지 않습니다.

**수정된 정답:**
```sql
insert into member (member_no,name,phone,email,join_date) values
('M001', '홍길동', '010-1111-2222', 'hong@email.com', '2020-01-10'),
('M002', '김영희', '010-2222-3333', 'kim@email.com', '2021-03-15'),
('M003', '이철수', '010-3333-4444', 'lee@email.com', '2022-05-20'),  -- 쉼표 추가
('M004', '박민지', '010-4444-5555', 'park@email.com', '2021-07-08'),
('M005', '최수진', '010-5555-6666', 'choi@email.com', '2023-02-14');
```

---

#### 문제 6: 도서 데이터 삽입 (5점) → **5점**

**학생 답안:**
```sql
insert into book(isbn,title,author,publisher,category,total_copies,available_copies) values
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

#### 문제 7: 대출 데이터 삽입 (5점) → **0점**

**학생 답안:**
```sql
insert into loan(isbn,title,author,publisher,category,total_copies,available_copies) values
('M001', 'ISBN001', '2024-01-15', NULL, '2024-02-15', '대출중'),
('M002', 'ISBN002', '2024-02-01', NULL, '2024-03-01', '대출중'),
('M001', 'ISBN003', '2024-01-20', '2024-02-10', '2024-02-20', '반납완료'),
('M003', 'ISBN004', '2024-03-10', NULL, '2024-04-10', '대출중'),
('M004', 'ISBN005', '2024-02-25', NULL, '2024-03-25', '대출중');
```

**정답:**
```sql
INSERT INTO loan (member_no, isbn, loan_date, return_date, due_date, status) VALUES
('M001', 'ISBN001', '2024-01-15', NULL, '2024-02-15', '대출중'),
('M002', 'ISBN002', '2024-02-01', NULL, '2024-03-01', '대출중'),
('M001', 'ISBN003', '2024-01-20', '2024-02-10', '2024-02-20', '반납완료'),
('M003', 'ISBN004', '2024-03-10', NULL, '2024-04-10', '대출중'),
('M004', 'ISBN005', '2024-02-25', NULL, '2024-03-25', '대출중');
```

**채점 결과:**
- ❌ INSERT 문법: 컬럼명이 완전히 잘못됨
- ❌ 컬럼명: `isbn,title,author,publisher,category,total_copies,available_copies` (book 테이블 컬럼)
  - 정답: `member_no, isbn, loan_date, return_date, due_date, status` (loan 테이블 컬럼)
- ❌ 데이터: 컬럼과 값이 매칭되지 않음

**주요 오류:**

1. **컬럼명 오류**: `loan` 테이블에 `title, author, publisher, category, total_copies, available_copies` 컬럼이 없음
   - 이는 `book` 테이블의 컬럼입니다.
   - 심각한 오류: -5점

**감점 사유:**
- 테이블 구조를 잘못 이해함: -5점
- 컬럼명이 완전히 잘못됨: 실행 불가

**피드백:**
- **심각한 오류입니다!**
- `loan` 테이블의 컬럼을 확인하지 않고 `book` 테이블의 컬럼을 사용했습니다.
- **loan 테이블 컬럼**: `loan_id`, `member_no`, `isbn`, `loan_date`, `return_date`, `due_date`, `status`
- **book 테이블 컬럼**: `book_id`, `isbn`, `title`, `author`, `publisher`, `category`, `total_copies`, `available_copies`

**수정된 정답:**
```sql
insert into loan(member_no, isbn, loan_date, return_date, due_date, status) values
('M001', 'ISBN001', '2024-01-15', NULL, '2024-02-15', '대출중'),
('M002', 'ISBN002', '2024-02-01', NULL, '2024-03-01', '대출중'),
('M001', 'ISBN003', '2024-01-20', '2024-02-10', '2024-02-20', '반납완료'),
('M003', 'ISBN004', '2024-03-10', NULL, '2024-04-10', '대출중'),
('M004', 'ISBN005', '2024-02-25', NULL, '2024-03-25', '대출중');
```

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

#### 문제 10: 대출 반납 처리 (5점) → **4점**

**학생 답안:**
```sql
update loan set return_date = current_date, status = '반납완료' where loan_id = 3;
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
- ⚠️ WHERE 절: `loan_id = 3` (정답은 `loan_id = 4`)
  - 문제에서 "대출ID가 4인 대출"이라고 명시했지만 3을 사용
  - 조건 오류: -1점

**감점 사유:**
- WHERE 절 조건 오류: -1점

**피드백:**
- 문법은 완벽하지만 문제를 잘못 읽었습니다.
- 문제: "대출ID가 **4**인 대출"
- 답안: `loan_id = 3`
- **주의**: 문제를 정확히 읽고 조건을 확인하세요.

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
select category as 카테고리, count(*) as 도서수 from book group by category;
```

**채점 결과:**
- ✅ SELECT 문법 정확: 1점
- ✅ COUNT(*) 사용 정확: 1점
- ✅ GROUP BY 사용 정확: 2점
- ✅ 컬럼 별칭 사용: 1점

**감점 사유:**
- 없음

**피드백:**
- 완벽합니다! GROUP BY를 사용하여 카테고리별로 그룹화했습니다.

---

#### 문제 15: 현재 대출 중인 도서 수 (5점) → **5점**

**학생 답안:**
```sql
select count(*) as 대출중도서수 from loan where status = '대출중';
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

#### 문제 16: 회원별 대출 횟수 (5점) → **5점**

**학생 답안:**
```sql
select m.member_no as 회원번호, m.name as 이름, count(l.loan_id) as 대출횟수
from member m
left join loan l
on m.member_no = l.member_no
group by m.member_no, m.name;
```

**채점 결과:**
- ✅ SELECT 문법 정확: 1점
- ✅ LEFT JOIN 사용 정확: 2점
- ✅ COUNT와 GROUP BY 조합 정확: 1점
- ✅ GROUP BY 컬럼 정확: 1점

**감점 사유:**
- 없음

**피드백:**
- 완벽합니다! LEFT JOIN을 사용하여 대출하지 않은 회원도 포함시켰습니다.

---

#### 문제 17: HAVING 사용 (5점) → **5점**

**학생 답안:**
```sql
select m.member_no as 회원번호, count(l.loan_id) as 대출횟수
from member m
join loan l
on m.member_no = l.member_no
group by m.member_no
having count(l.loan_id) >= 2;
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
- 다만 `join`은 `inner join`의 축약형이므로, 대출하지 않은 회원은 제외됩니다.
- 정답에서는 `left join`을 사용했지만, 결과는 동일합니다.

---

### 영역 4: JOIN (문제 18~20, 15점)

#### 문제 18: INNER JOIN - 기본 조인 (5점) → **5점**

**학생 답안:**
```sql
select m.name as 회원이름, b.title as 도서제목
from loan l
inner join member m
on l.member_no = m.member_no
inner join book b
on l.isbn = b.isbn
where l.status = '대출중';
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
- SQL 문법이 완벽하므로 만점을 부여합니다.

---

#### 문제 19: INNER JOIN - 조건부 조인 (5점) → **5점**

**학생 답안:**
```sql
select m.name as 회원이름, b.title as 도서제목
from loan l
inner join member m
on l.member_no = m.member_no
inner join book b
on l.isbn = b.isbn
where b.category = '프로그래밍';
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
- SQL 문법이 완벽하므로 만점을 부여합니다.

---

#### 문제 20: LEFT JOIN - 매칭되지 않는 데이터 찾기 (5점) → **5점**

**학생 답안:**
```sql
select m.member_no, m.name
from member m
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
- SQL 문법이 완벽하므로 만점을 부여합니다.

---

## 종합 평가

### 강점

1. **UPDATE/DELETE 문법**: UPDATE와 DELETE 문을 정확히 작성했습니다.
2. **집계함수 활용**: COUNT, GROUP BY, HAVING을 정확히 사용했습니다.
3. **JOIN 이해**: INNER JOIN과 LEFT JOIN을 정확히 구분하여 사용했습니다.
4. **WHERE 절 사용**: UPDATE와 DELETE에서 WHERE 절을 항상 사용하여 안전하게 작성했습니다.

### 개선이 필요한 사항

1. **문법 오류**: 
   - FOREIGN KEY 문법 오류 (괄호 누락)
   - DEFAULT 문법 오류
   - INSERT 문에서 쉼표 누락

2. **테이블 구조 이해 부족**:
   - 문제 7에서 `loan` 테이블에 `book` 테이블의 컬럼을 사용
   - 테이블 구조를 정확히 파악하지 못함

3. **문제 읽기 부족**:
   - 문제 10에서 `loan_id = 4`를 `loan_id = 3`으로 작성

### 총평

**83점 (B등급)** - 기본적인 SQL 문법은 이해하고 있으며, UPDATE/DELETE와 집계함수, JOIN 문법을 정확히 사용할 수 있습니다. 다만 테이블 생성 시 문법 오류와 테이블 구조 이해 부족이 개선이 필요합니다.

**특히 개선이 필요한 부분:**
- FOREIGN KEY 문법 정확히 학습 필요
- DEFAULT 문법 정확히 학습 필요
- 테이블 구조를 정확히 파악하는 연습 필요
- 문제를 정확히 읽는 습관 필요

**칭찬할 점:**
- UPDATE, DELETE, SELECT 문법은 정확히 이해하고 있습니다.
- 집계함수와 JOIN을 정확히 사용할 수 있습니다.

**추가 학습 권장 사항:**
1. FOREIGN KEY 문법 정확히 학습
2. DEFAULT 문법 정확히 학습
3. 테이블 구조 파악 연습
4. 문제를 정확히 읽는 습관
5. SQL 문법 오류 체크 리스트 작성

---

## 문제별 점수 요약

| 문제 | 배점 | 획득 | 비고 |
|------|------|------|------|
| 1 | 5 | 5 | 완벽 |
| 2 | 5 | 5 | 완벽 |
| 3 | 5 | 5 | 완벽 |
| 4 | 5 | 0 | 문법 오류 (FOREIGN KEY, DEFAULT) |
| 5 | 5 | 3 | 쉼표 누락 |
| 6 | 5 | 5 | 완벽 |
| 7 | 5 | 0 | 컬럼명 오류 (테이블 구조 오해) |
| 8 | 5 | 5 | 완벽 |
| 9 | 5 | 5 | 완벽 |
| 10 | 5 | 4 | loan_id 오류 (3 → 4) |
| 11 | 5 | 5 | 완벽 |
| 12 | 5 | 5 | 완벽 |
| 13 | 5 | 5 | 완벽 |
| 14 | 5 | 5 | 완벽 |
| 15 | 5 | 5 | 완벽 |
| 16 | 5 | 5 | 완벽 |
| 17 | 5 | 5 | 완벽 |
| 18 | 5 | 5 | 완벽 (문법 정확) |
| 19 | 5 | 5 | 완벽 (문법 정확) |
| 20 | 5 | 5 | 완벽 (문법 정확) |
| **총계** | **100** | **83** | **B등급** |

---

## 주요 오류 정리

### 1. FOREIGN KEY 문법 오류
```sql
-- ❌ 잘못된 문법
foreign key member_no references member (member_no)

-- ✅ 올바른 문법
foreign key (member_no) references member(member_no)
```

### 2. DEFAULT 문법 오류
```sql
-- ❌ 잘못된 문법
loan_date date (current_date)

-- ✅ 올바른 문법
loan_date date default (current_date)
```

### 3. INSERT 문 쉼표 누락
```sql
-- ❌ 잘못된 문법
('M003' '이철수', ...)

-- ✅ 올바른 문법
('M003', '이철수', ...)
```

### 4. 테이블 구조 오해
```sql
-- ❌ 잘못된 컬럼 (book 테이블 컬럼 사용)
insert into loan(isbn,title,author,publisher,category,total_copies,available_copies)

-- ✅ 올바른 컬럼 (loan 테이블 컬럼 사용)
insert into loan(member_no, isbn, loan_date, return_date, due_date, status)
```

### 5. 문제 조건 오류
```sql
-- ❌ 잘못된 조건
where loan_id = 3

-- ✅ 올바른 조건
where loan_id = 4
```

---

**채점 완료일:** 2026-01-30  
**채점자:** AI Assistant
