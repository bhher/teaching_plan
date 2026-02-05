# SQL 종합 실습 문제 채점 결과 - 천다솜 학생

**채점일:** 2026-01-30  
**총점:** 100점 만점  
**획득 점수:** 92점

---

## 채점 요약

| 영역 | 문제 번호 | 배점 | 획득 점수 | 비율 |
|------|----------|------|----------|------|
| **1. 데이터베이스/테이블 생성 및 데이터 삽입** | 1~7 | 35점 | 33점 | 94% |
| **2. 데이터 수정 및 삭제** | 8~12 | 25점 | 25점 | 100% |
| **3. 집계함수** | 13~17 | 25점 | 25점 | 100% |
| **4. JOIN** | 18~20 | 15점 | 9점 | 60% |
| **총계** | 1~20 | 100점 | **92점** | **92%** |

**등급:** A (우수)

---

## 문제별 상세 채점

### 영역 1: 데이터베이스/테이블 생성 및 데이터 삽입 (문제 1~7, 35점)

#### 문제 1: 데이터베이스 생성 (5점) → **5점**

**학생 답안:**
```sql
CREATE DATABASE library_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
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
CREATE TABLE member(
    member_id int primary key auto_increment,
    member_no varchar(20) unique not null,
    name varchar(50) not null,
    phone varchar(20) unique,
    email varchar(100),
    join_date date,
    status varchar(20) default '활성'
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
- ⚠️ DEFAULT 값: `join_date`의 DEFAULT 누락
  - 문제 조건: "기본값 현재 날짜" → `DEFAULT (CURRENT_DATE)` 필요
  - 부분 점수: 0점

**감점 사유:**
- `join_date`의 DEFAULT 값 누락: -1점

**피드백:**
- 대부분 완벽하지만 `join_date`에 기본값이 누락되었습니다.
- 문제 조건: "기본값 현재 날짜" → `DEFAULT (CURRENT_DATE)` 추가 필요
- UTF-8 설정까지 추가한 것은 좋은 습관입니다!

**수정된 정답:**
```sql
CREATE TABLE member(
    member_id int primary key auto_increment,
    member_no varchar(20) unique not null,
    name varchar(50) not null,
    phone varchar(20) unique,
    email varchar(100),
    join_date date default (current_date),  -- DEFAULT 추가
    status varchar(20) default '활성'
)character set utf8mb4 collate utf8mb4_unicode_ci;
```

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

#### 문제 4: 대출 테이블 생성 (5점) → **5점**

**학생 답안:**
```sql
create table loan (
    loan_id int primary key auto_increment,
    member_no varchar(20),
    isbn varchar(20),
    loan_date date default (current_date),
    return_date date,
    due_date date not null,
    status varchar(20) default '대출중',
    foreign key (member_no) references member(member_no),
    foreign key (isbn) references book(isbn)
) character set utf8mb4 collate utf8mb4_unicode_ci;
```

**채점 결과:**
- ✅ 기본 구조 정확: 1점
- ✅ PRIMARY KEY 설정: 1점
- ✅ 외래키 제약조건 2개 정확: 2점
- ✅ DEFAULT 값 및 NOT NULL 설정: 1점

**감점 사유:**
- 없음

**피드백:**
- 완벽합니다! 외래키 제약조건을 정확히 작성했습니다.
- `loan_date`의 DEFAULT 값도 `CURRENT_DATE` 함수를 사용하여 정확합니다.
- UTF-8 설정까지 추가한 것은 좋습니다!

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
('M001', 'ISBN001', '2024-01-15', null, '2024-02-15', '대출중'),
('M002', 'ISBN002', '2024-02-01', null, '2024-03-01', '대출중'),
('M001', 'ISBN003', '2024-01-20', '2024-02-10', '2024-02-20', '반납완료'),
('M003', 'ISBN004', '2024-03-10', null, '2024-04-10', '대출중'),
('M004', 'ISBN005', '2024-02-25', null, '2024-03-25', '대출중');
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

#### 문제 10: 대출 반납 처리 (5점) → **5점**

**학생 답안:**
```sql
update loan set return_date = current_date, status = '반납완료' where loan_id = 4;
```

**채점 결과:**
- ✅ UPDATE 문법 정확: 2점
- ✅ 여러 컬럼 동시 수정 정확: 2점
- ✅ 날짜 함수 사용 정확 (`CURRENT_DATE`): 1점

**감점 사유:**
- 없음

**피드백:**
- 완벽합니다! 여러 컬럼을 동시에 수정하고 날짜 함수를 사용했습니다.

---

#### 문제 11: 조건부 삭제 (5점) → **5점**

**학생 답안:**
```sql
DELETE FROM member WHERE status = '휴면';
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
DELETE FROM member WHERE member_no = 'M005';
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
select category, count(*) as 카테고리별 from book group by category;
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
- 별칭이 "카테고리별"이지만, 이는 자유롭게 사용 가능하므로 문제 없습니다.

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

#### 문제 16: 회원별 대출 횟수 (5점) → **5점**

**학생 답안:**
```sql
select m.member_no, m.name, count(l.loan_id) as 회원별대출횟수
from member m
left join loan l
on m.member_no = l.member_no
group by m.member_no, m.name;
```

**채점 결과:**
- ✅ SELECT 문법 정확: 1점
- ✅ LEFT JOIN 사용 정확: 2점
- ✅ COUNT와 GROUP BY 조합 정확: 1점
- ✅ GROUP BY 컬럼 정확 (m.member_no, m.name 모두 포함): 1점

**감점 사유:**
- 없음

**피드백:**
- 완벽합니다! LEFT JOIN을 사용하여 대출하지 않은 회원도 포함시켰습니다.
- COUNT(l.loan_id)를 사용하여 NULL 값은 제외하고 계산했습니다.
- GROUP BY에 모든 컬럼을 정확히 포함시켰습니다.

---

#### 문제 17: HAVING 사용 (5점) → **5점**

**학생 답안:**
```sql
select member_no, count(*) as 대출횟수
from loan
group by member_no
having count(*) >= 2;
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

---

### 영역 4: JOIN (문제 18~20, 15점)

#### 문제 18: INNER JOIN - 기본 조인 (5점) → **5점**

**학생 답안:**
```sql
select m.name, b.title
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

---

#### 문제 19: INNER JOIN - 조건부 조인 (5점) → **5점**

**학생 답안:**
```sql
select m.name, b.title, b.category
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
- 카테고리까지 SELECT에 포함시켜 더 명확하게 작성했습니다.

---

#### 문제 20: LEFT JOIN - 매칭되지 않는 데이터 찾기 (5점) → **-1점 (4점)**

**학생 답안:**
```sql
select m.name
from member m
left join loan l
on m.member_no = l.member_no
where l.loan_id is null;
```

**정답:**
```sql
SELECT 
    m.member_no AS 회원번호,
    m.name AS 회원이름
FROM member m
LEFT JOIN loan l ON m.member_no = l.member_no
WHERE l.member_no IS NULL;
```

**채점 결과:**
- ✅ SELECT 문법 정확: 1점
- ✅ LEFT JOIN 사용 정확: 2점
- ⚠️ WHERE 절 조건: `l.loan_id IS NULL` 사용
  - 정답은 `l.member_no IS NULL`이지만, `l.loan_id IS NULL`도 동일한 결과
  - LEFT JOIN에서 매칭되지 않는 경우 모든 컬럼이 NULL이 되므로 결과는 동일
  - 부분 점수: 1점 (결과는 동일하지만 정답과 다름)

**감점 사유:**
- WHERE 절 조건이 정답과 다름 (`l.loan_id IS NULL` vs `l.member_no IS NULL`): -1점
  - 결과는 동일하지만, 일반적으로는 조인 키를 NULL로 확인하는 것이 더 명확

**피드백:**
- LEFT JOIN을 정확히 사용했습니다!
- `l.loan_id IS NULL`도 정답이지만, 일반적으로는 조인 키(`l.member_no`)를 NULL로 확인하는 것이 더 명확합니다.
- 결과는 동일하므로 실무에서는 문제없지만, 정답과 일치시키는 것이 좋습니다.

**수정된 정답:**
```sql
select m.member_no, m.name
from member m
left join loan l
on m.member_no = l.member_no
where l.member_no is null;  -- 조인 키 사용
```

---

## 종합 평가

### 강점

1. **기본 문법 정확성**: 대부분의 SQL 문법을 정확히 작성했습니다.
2. **제약조건 이해**: PRIMARY KEY, FOREIGN KEY, UNIQUE, NOT NULL 등을 정확히 사용했습니다.
3. **집계함수 활용**: COUNT, GROUP BY, HAVING을 정확히 사용했습니다.
4. **JOIN 이해**: INNER JOIN과 LEFT JOIN을 정확히 구분하여 사용했습니다.
5. **UPDATE/DELETE 문법**: UPDATE와 DELETE 문을 정확히 작성했습니다.
6. **날짜 함수 사용**: `CURRENT_DATE` 함수를 정확히 사용했습니다.
7. **UTF-8 설정**: 테이블 생성 시 UTF-8 설정까지 추가한 것은 좋은 습관입니다.

### 개선이 필요한 사항

1. **DEFAULT 값**: 문제 2에서 `join_date`의 DEFAULT 값 누락
2. **JOIN NULL 확인**: 문제 20에서 조인 키를 사용하는 것이 더 명확함

### 총평

**92점 (A등급)** - 매우 우수한 성적입니다!

- SQL 기본 문법을 완벽히 이해하고 있습니다.
- 제약조건, 집계함수, JOIN을 정확히 사용할 수 있습니다.
- 날짜 함수를 정확히 사용하는 습관이 있습니다.
- 실무에서 바로 사용 가능한 수준의 SQL 작성 능력을 보여주었습니다.

**특히 칭찬할 점:**
- 모든 UPDATE와 DELETE 문에 WHERE 절을 정확히 사용했습니다.
- LEFT JOIN을 사용하여 대출하지 않은 회원도 포함시켰습니다.
- HAVING 절을 정확히 이해하고 사용했습니다.
- 날짜 함수(`CURRENT_DATE`)를 정확히 사용했습니다.
- UTF-8 설정까지 추가한 것은 좋은 습관입니다.

**추가 학습 권장 사항:**
- DEFAULT 값 설정 습관화
- 서브쿼리 활용
- 윈도우 함수 (RANK, ROW_NUMBER 등)
- 트랜잭션 처리

---

## 문제별 점수 요약

| 문제 | 배점 | 획득 | 비고 |
|------|------|------|------|
| 1 | 5 | 5 | 완벽 |
| 2 | 5 | 4 | join_date DEFAULT 누락 |
| 3 | 5 | 5 | 완벽 |
| 4 | 5 | 5 | 완벽 |
| 5 | 5 | 5 | 완벽 |
| 6 | 5 | 5 | 완벽 |
| 7 | 5 | 5 | 완벽 |
| 8 | 5 | 5 | 완벽 |
| 9 | 5 | 5 | 완벽 |
| 10 | 5 | 5 | 완벽 |
| 11 | 5 | 5 | 완벽 |
| 12 | 5 | 5 | 완벽 |
| 13 | 5 | 5 | 완벽 |
| 14 | 5 | 5 | 완벽 |
| 15 | 5 | 5 | 완벽 |
| 16 | 5 | 5 | 완벽 |
| 17 | 5 | 5 | 완벽 |
| 18 | 5 | 5 | 완벽 |
| 19 | 5 | 5 | 완벽 |
| 20 | 5 | 4 | WHERE 조건 차이 (결과는 동일) |
| **총계** | **100** | **92** | **A등급** |

---

## 주요 오류 정리

### 1. DEFAULT 값 누락
```sql
-- ❌ 잘못된 문법
join_date date

-- ✅ 올바른 문법
join_date date default (current_date)
```

### 2. JOIN NULL 확인
```sql
-- ⚠️ 결과는 동일하지만 관례와 다름
where l.loan_id is null

-- ✅ 일반적인 관례
where l.member_no is null
```

---

**채점 완료일:** 2026-01-30  
**채점자:** AI Assistant
