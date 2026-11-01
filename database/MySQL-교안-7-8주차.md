# MySQL 교안 (7–8주차)

**목표**
- VIEW · INDEX 이해와 사용
- 사용자·권한(DCL) 기초
- 트랜잭션 개념
- ERD·정규화 입문 후 미니 프로젝트

**전체 목차:** [MySQL-교안-전체.md](./MySQL-교안-전체.md)

---

## 📅 7주차 – 뷰·인덱스·권한·트랜잭션

### 📌 학습 목표
- VIEW로 조회를 재사용
- INDEX가 필요한 이유
- GRANT / REVOKE
- COMMIT / ROLLBACK 개념

### 📘 이론

#### 7.1 VIEW (뷰)
자주 쓰는 SELECT를 **가상 테이블**로 저장합니다.

```sql
CREATE VIEW v_student_score AS
SELECT s.name, sc.subject, sc.point
FROM student s
JOIN score sc ON s.id = sc.student_id;

SELECT * FROM v_student_score WHERE point >= 90;

-- DROP VIEW v_student_score;
```

- 실제 데이터를 복사하지 않음 (정의만 저장)
- 복잡한 JOIN을 단순화할 때 유용

#### 7.2 INDEX (인덱스)
검색을 빠르게 하는 **찾아보기**입니다.

```sql
CREATE INDEX idx_student_name ON student(name);
SHOW INDEX FROM student;

-- DROP INDEX idx_student_name ON student;
```

| 상황 | 인덱스 |
|------|--------|
| WHERE / JOIN에 자주 쓰는 컬럼 | 고려 |
| 거의 안 찾는 컬럼 | 불필요 |
| PK / UNIQUE | 자동 인덱스 |

너무 많으면 INSERT/UPDATE가 느려질 수 있음.

#### 7.3 사용자·권한
```sql
CREATE USER 'student'@'localhost' IDENTIFIED BY 'pass1234';

GRANT SELECT, INSERT ON school.* TO 'student'@'localhost';
FLUSH PRIVILEGES;

SHOW GRANTS FOR 'student'@'localhost';

REVOKE INSERT ON school.* FROM 'student'@'localhost';
-- DROP USER 'student'@'localhost';
```

#### 7.4 트랜잭션 (개념)
여러 SQL을 **한 묶음**으로 성공 또는 취소.

```sql
START TRANSACTION;

UPDATE account SET balance = balance - 1000 WHERE id = 1;
UPDATE account SET balance = balance + 1000 WHERE id = 2;

COMMIT;    -- 확정
-- ROLLBACK;  -- 취소
```

InnoDB 엔진에서 지원. (MyISAM은 트랜잭션 약함)

### 🧪 실습
1. 학생+성적 VIEW 생성 후 조회  
2. name에 INDEX 생성, `SHOW INDEX`  
3. (가능하면) 실습용 유저 만들어 SELECT만 부여  

### ✅ 7주차 체크
- [ ] VIEW 생성·조회
- [ ] INDEX 목적 설명
- [ ] GRANT 의미 설명
- [ ] COMMIT / ROLLBACK 구분

---

## 📅 8주차 – 설계 입문 & 미니 프로젝트

### 📌 학습 목표
- ERD로 테이블 관계 그리기
- 정규화 필요성(중복 제거) 이해
- 작은 DB를 처음부터 설계·구현

### 📘 이론

#### 8.1 ERD 간단 표기
```
[회원] 1 ──< N [주문] N >── 1 [상품]
```

#### 8.2 정규화 입문 (중복을 나누기)
나쁜 예: 주문 테이블에 회원주소·상품명을 매번 반복 저장  
→ 회원 / 상품 / 주문으로 **분리** 후 FK로 연결

#### 8.3 프로젝트 주제 (택 1)

---

### 프로젝트 A. 도서 대출

**테이블 제안**
- `book` (id, title, author, stock)
- `member` (id, name, phone)
- `loan` (id, book_id, member_id, loan_date, return_date)

**구현 요구**
1. DB·테이블·FK 생성  
2. 샘플 데이터 10건 이상  
3. 대출 중인 도서 목록 (반납일 NULL)  
4. 회원별 대출 권수  
5. 특정 도서 재고 수정  

---

### 프로젝트 B. 미니 쇼핑몰

**테이블 제안**
- `customer`, `product`, `orders`, `order_item`

**구현 요구**
1. FK 관계 설정  
2. 주문 상세(고객명·상품명·수량·금액) JOIN 조회  
3. 상품별 판매 수량 SUM  
4. 한 번도 주문 안 한 고객 (LEFT JOIN)  

---

### 제출물 (수업용)
1. `.sql` 파일 (CREATE + INSERT + 조회 쿼리)  
2. 테이블 관계 간단 그림 (손그림·마크다운 OK)  
3. 조회 결과 스크린샷 또는 텍스트  

### ✅ 8주차 체크
- [ ] ERD로 1:N 표현
- [ ] FK 있는 스키마 작성
- [ ] JOIN·집계 조회 3개 이상
- [ ] 프로젝트 SQL 실행 성공

---

## 과정 마무리 체크리스트

- [ ] DDL / DML / DQL 구분
- [ ] CRUD + WHERE + ORDER BY
- [ ] GROUP BY / HAVING
- [ ] INNER · LEFT JOIN
- [ ] PK / FK
- [ ] VIEW · INDEX 개념
- [ ] 미니 프로젝트 1개 완성

---

## 더 공부하기

| 주제 | 자료 |
|------|------|
| 기초 상세 | [../mysql/MySQL-기초-교안.md](../mysql/MySQL-기초-교안.md) |
| 중급 | [../mysql/MySQL-중급-교안.md](../mysql/MySQL-중급-교안.md) |
| JOIN 실습 | [../mysql/SQL-JOIN-실습/](../mysql/SQL-JOIN-실습/) |
| 종합 20문제 | [../mysql/SQL-종합실습-20문제.md](../mysql/SQL-종합실습-20문제.md) |
| SQLD | [../mysql/SQLD-시험준비-교안.md](../mysql/SQLD-시험준비-교안.md) |
| JSP+DB | [../jsp/예제/06-회원가입-DB/](../jsp/예제/06-회원가입-DB/) |
