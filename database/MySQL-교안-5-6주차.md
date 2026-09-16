# MySQL 교안 (5–6주차)

**목표**
- INNER / LEFT JOIN으로 테이블 연결
- 서브쿼리 활용
- PK, FK 등 제약조건
- ALTER로 구조 변경

**전체 목차:** [MySQL-교안-전체.md](./MySQL-교안-전체.md)  
**실습 SQL:** [sql/02-JOIN-연습.sql](./sql/02-JOIN-연습.sql)

---

## 📅 5주차 – JOIN · 서브쿼리

### 📌 학습 목표
- 왜 JOIN이 필요한지
- INNER / LEFT JOIN 차이
- 서브쿼리 위치별 사용

### 📘 이론

#### 5.1 왜 JOIN인가?
학생 이름과 점수를 **한 번에** 보려면 두 테이블을 연결해야 합니다.

```
student                score
id | name              id | student_id | subject | point
1  | 김민수     ←──→    1  | 1          | 국어    | 90
```

#### 5.2 INNER JOIN
양쪽 모두 일치하는 행만
```sql
SELECT s.name, sc.subject, sc.point
FROM student s
INNER JOIN score sc ON s.id = sc.student_id;
```

#### 5.3 LEFT JOIN
왼쪽(student)은 모두, 오른쪽 없으면 NULL
```sql
SELECT s.name, sc.subject, sc.point
FROM student s
LEFT JOIN score sc ON s.id = sc.student_id;
-- 성적 없는 학생도 이름 나옴
```

#### 5.4 JOIN 도식

```
INNER JOIN          LEFT JOIN
  A ∩ B               A 전체 + 맞는 B

 ┌───┐                ┌───┐
 │ A ███ B │          │ A████ B │
 └───┘                └───┘
   ██만                 A 전부
```

#### 5.5 세 테이블 (회원·주문·상품 패턴)
```sql
SELECT m.name, o.order_date, p.product_name, o.qty
FROM member m
JOIN orders o ON m.id = o.member_id
JOIN product p ON o.product_id = p.id;
```

#### 5.6 서브쿼리

**WHERE 절**
```sql
-- 평균 나이보다 많은 학생
SELECT name, age FROM student
WHERE age > (SELECT AVG(age) FROM student);
```

**IN + 서브쿼리**
```sql
SELECT name FROM student
WHERE id IN (
  SELECT student_id FROM score WHERE point >= 90
);
```

**FROM 절 (인라인 뷰)**
```sql
SELECT student_id, avg_point
FROM (
  SELECT student_id, AVG(point) AS avg_point
  FROM score
  GROUP BY student_id
) t
WHERE avg_point >= 85;
```

### 🧪 실습
1. 학생 이름 + 과목 + 점수 INNER JOIN  
2. 성적이 없는 학생만 (LEFT JOIN + WHERE sc.id IS NULL)  
3. 전체 평균보다 높은 점수만  
4. 국어 점수가 있는 학생 이름 (IN 서브쿼리)  

### ✅ 5주차 체크
- [ ] INNER / LEFT 차이 설명
- [ ] ON 조건 작성
- [ ] 스칼라·IN 서브쿼리

---

## 📅 6주차 – 제약조건 · ALTER

### 📌 학습 목표
- 제약조건으로 데이터 무결성 지키기
- FK로 테이블 관계 설정
- ALTER TABLE

### 📘 이론

#### 6.1 제약조건 종류

| 제약 | 의미 |
|------|------|
| PRIMARY KEY | 행 고유 식별, NULL 불가 |
| FOREIGN KEY | 다른 테이블 PK 참조 |
| UNIQUE | 중복 불가 (NULL 허용 가능) |
| NOT NULL | 필수 입력 |
| DEFAULT | 기본값 |
| CHECK | 값 범위 조건 (MySQL 8.0.16+) |

```sql
CREATE TABLE member (
  id       INT PRIMARY KEY AUTO_INCREMENT,
  userid   VARCHAR(30) NOT NULL UNIQUE,
  name     VARCHAR(50) NOT NULL,
  age      INT CHECK (age >= 0),
  join_date DATE DEFAULT (CURRENT_DATE)
);

CREATE TABLE orders (
  id INT PRIMARY KEY AUTO_INCREMENT,
  member_id INT NOT NULL,
  product_name VARCHAR(100),
  qty INT DEFAULT 1,
  CONSTRAINT fk_orders_member
    FOREIGN KEY (member_id) REFERENCES member(id)
);
```

#### 6.2 FK 동작
- 없는 `member_id`로 주문 INSERT → **실패**
- 주문이 있는 회원 DELETE → 기본은 **실패** (설정에 따라 CASCADE)

```sql
-- 참고: 삭제 시 자식도 삭제
-- FOREIGN KEY (member_id) REFERENCES member(id) ON DELETE CASCADE
```

#### 6.3 ALTER TABLE
```sql
ALTER TABLE student ADD COLUMN phone VARCHAR(20);
ALTER TABLE student MODIFY COLUMN name VARCHAR(100) NOT NULL;
ALTER TABLE student DROP COLUMN phone;
ALTER TABLE student ADD CONSTRAINT uq_email UNIQUE (email);
```

#### 6.4 테이블 삭제 순서
FK가 있으면 **자식 → 부모** 순으로 DROP  
또는 `SET FOREIGN_KEY_CHECKS = 0;` (주의해서 사용)

### 📐 무결성

```
회원(member) 1 ──────< N 주문(orders)
     id (PK)              member_id (FK)

→ 주문은 반드시 "있는 회원"만 가리킴
```

### 🧪 실습
1. member / orders 테이블을 FK로 생성  
2. 회원 없이 주문 INSERT → 에러 확인  
3. student에 phone 컬럼 ADD 후 DROP  
4. email에 UNIQUE 추가  

### ⚠️ 주의
- FK 타입은 참조하는 PK와 **동일**해야 함
- `AUTO_INCREMENT`는 보통 PK 컬럼에
- 실습 후 `DROP TABLE` 순서 기억

### ✅ 6주차 체크
- [ ] PK / FK / UNIQUE / NOT NULL
- [ ] FK 위반 시 에러 이해
- [ ] ALTER ADD / MODIFY / DROP

---

## 다음 주 예고
VIEW, INDEX, 사용자 권한, 미니 프로젝트  
→ [MySQL-교안-7-8주차.md](./MySQL-교안-7-8주차.md)
