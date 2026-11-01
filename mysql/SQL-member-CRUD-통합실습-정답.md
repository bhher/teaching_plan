# SQL member1 · CRUD 통합 실습 정답

문제: [SQL-member-CRUD-통합실습-문제.md](./SQL-member-CRUD-통합실습-문제.md)

---

## 0. 준비

### 0-1

```sql
CREATE DATABASE member_db
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE member_db;
```

### 0-2

```sql
CREATE TABLE member1 (
  num INT NOT NULL AUTO_INCREMENT,
  id CHAR(20) NOT NULL,
  name CHAR(20) NOT NULL,
  gender CHAR(1),
  post_num CHAR(8),
  address CHAR(80),
  tel CHAR(20),
  age INT,
  PRIMARY KEY (num)
) CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;
```

### 0-3

문제 파일의 INSERT 문 실행 (35명).

### 0-4

```sql
SHOW TABLES;
DESC member1;
SELECT COUNT(*) FROM member1;  -- 35
```

---

## 1. CREATE (INSERT)

### 1

```sql
INSERT INTO member1 (id, name, gender, post_num, address, tel, age)
VALUES ('newsong', '송민호', 'M', '062-000', '서울시 강남구 역삼동', '555-1000', 29);

SELECT * FROM member1 WHERE id = 'newsong';
```

### 2

```sql
INSERT INTO member1 (id, name, gender, post_num, address, tel, age)
VALUES
('newlee', '이하늘', 'W', '480-000', '경기도 의정부시', '555-2000', 25),
('newpark', '박서준', 'M', '341-000', '세종특별자치시', '555-3000', 31);
```

---

## 2. READ (SELECT)

### 3

```sql
SELECT * FROM member1;
```

### 4

```sql
SELECT name, tel, address
FROM member1;
```

### 5

```sql
SELECT name AS 회원명, age AS 나이
FROM member1;
```

### 6

```sql
SELECT id, name, address, gender
FROM member1
WHERE gender = 'W';
```

### 7

```sql
SELECT name, tel, gender, age
FROM member1
WHERE age >= 50
AND gender = 'M';
```

### 8

```sql
SELECT name, gender, address, age
FROM member1
WHERE age BETWEEN 20 AND 29;
```

### 9

```sql
SELECT name, tel, age, gender
FROM member1
WHERE gender = 'M'
AND (
  age BETWEEN 30 AND 39
  OR age BETWEEN 50 AND 59
);
```

### 10

```sql
SELECT *
FROM member1
WHERE name = '안철영';
```

### 11

```sql
SELECT name, tel
FROM member1
WHERE name LIKE '박%';
```

### 12

```sql
SELECT num, name, address, gender
FROM member1
WHERE address LIKE '%서울%';
```

### 13

```sql
SELECT *
FROM member1
WHERE address LIKE '%은평구%';
```

### 14

```sql
SELECT *
FROM member1
WHERE name LIKE '김%'
AND address LIKE '%광주%';
```

### 15

```sql
SELECT name, age, address, tel, gender
FROM member1
WHERE address LIKE '%서울%'
ORDER BY age ASC;
```

### 16

```sql
SELECT name, age, address, tel
FROM member1
WHERE address LIKE '%부산%'
ORDER BY age DESC
LIMIT 3;
```

### 17

```sql
SELECT DISTINCT gender
FROM member1;
```

### 18

```sql
SELECT COUNT(*) AS 광주회원수
FROM member1
WHERE address LIKE '%광주%';
```

### 19

```sql
-- 평균
SELECT AVG(age) AS 평균나이
FROM member1;

-- 최고령 (방법1)
SELECT name, age
FROM member1
WHERE age = (SELECT MAX(age) FROM member1);

-- 최고령 (방법2)
SELECT name, age
FROM member1
ORDER BY age DESC
LIMIT 1;
```

### 20

```sql
SELECT gender,
       COUNT(*) AS 인원,
       AVG(age) AS 평균나이
FROM member1
GROUP BY gender;
```

---

## 3. UPDATE

### 21

```sql
SELECT * FROM member1 WHERE name = '고재진';

UPDATE member1
SET tel = '123-4567'
WHERE name = '고재진';

SELECT * FROM member1 WHERE name = '고재진';
```

### 22

```sql
SELECT name, age FROM member1 WHERE name = '정한나';

UPDATE member1
SET age = 37
WHERE name = '정한나';

SELECT name, age FROM member1 WHERE name = '정한나';
```

### 23

```sql
UPDATE member1
SET tel = '555-9999',
    age = 30
WHERE name = '송민호';

SELECT * FROM member1 WHERE name = '송민호';
```

### 24
② WHERE가 없어 모든 행이 수정될 수 있어서

---

## 4. DELETE

### 25

```sql
DELETE FROM member1
WHERE name = '김수련';

SELECT name
FROM member1
WHERE name LIKE '김%';
```

### 26

```sql
DELETE FROM member1
WHERE age BETWEEN 40 AND 49;

SELECT name, age, gender
FROM member1
WHERE age BETWEEN 35 AND 53
ORDER BY age ASC;
```

### 27
② 모든 행이 삭제된다 (테이블 구조는 남음)

---

## 5. 종합

### 28

```sql
-- 1) Create
INSERT INTO member1 (id, name, gender, post_num, address, tel, age)
VALUES ('test01', '테스트', 'M', '100-000', '서울시 중구 테스트동', '000-0000', 27);

-- 2) Read
SELECT * FROM member1 WHERE id = 'test01';

-- 3) Update
UPDATE member1
SET age = 28
WHERE id = 'test01';

SELECT * FROM member1 WHERE id = 'test01';

-- 4) Delete
DELETE FROM member1
WHERE id = 'test01';

-- 5) 확인 (0건)
SELECT * FROM member1 WHERE id = 'test01';
```

### 29

| CRUD | SQL |
|------|-----|
| Create | INSERT |
| Read | SELECT |
| Update | UPDATE |
| Delete | DELETE |

### 30 (예시)

DDL은 테이블·DB **구조**를 정의하고(CREATE/ALTER/DROP),  
DML은 그 안의 **데이터**를 조작한다(INSERT/SELECT/UPDATE/DELETE).

---

## 강사 팁

| 포인트 | 내용 |
|--------|------|
| 데이터 | member 실습 35명 + CRUD용 추가 행 |
| WHERE | UPDATE/DELETE 채점 핵심 |
| 1부 연결 | AS, BETWEEN, IN/OR, LIKE, ORDER BY, LIMIT, DISTINCT, COUNT/AVG/GROUP BY |
| 원본 차이 | 중복 id `shlee` → 실습본은 `shlee2` |
