# MySQL 8일차 — 테이블 설계와 종합 프로젝트

**선수:** 1~7일차  
**과정 마무리:** [8일 교안 README](./MySQL-8일-교안-README.md)

---

## 1. 학습목표

- PRIMARY KEY, FOREIGN KEY를 이해한다.
- NOT NULL, UNIQUE를 이해한다.
- 테이블 관계를 설계할 수 있다.
- 지금까지 배운 SQL로 쇼핑몰 DB를 구축한다.

---

## 2. PRIMARY KEY

행을 구분하는 **대표값**. 중복·NULL 불가.

```sql
CREATE TABLE member (
    id INT PRIMARY KEY,
    name VARCHAR(50),
    age INT
);
```

`1 홍길동 / 1 김철수` → **불가**

---

## 3. NOT NULL / UNIQUE

```sql
name VARCHAR(50) NOT NULL,     -- 필수
email VARCHAR(100) UNIQUE      -- 중복 불가
```

---

## 4. FOREIGN KEY

다른 테이블의 PK를 참조합니다.

```
member (PK: id)
    ↑
orders (FK: member_id)
```

---

## 5. 종합 프로젝트 — 쇼핑몰 주문 관리

### 주제

회원 · 상품 · 주문을 연결하는 DB

### 5-1. 새 DB로 시작 (권장)

```sql
DROP DATABASE IF EXISTS shop_db;
CREATE DATABASE shop_db;
USE shop_db;
```

### 5-2. 회원

```sql
CREATE TABLE member (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE,
    age INT
);
```

### 5-3. 상품

```sql
CREATE TABLE product (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    price INT NOT NULL,
    stock INT DEFAULT 0
);
```

### 5-4. 주문

```sql
CREATE TABLE orders (
    id INT PRIMARY KEY AUTO_INCREMENT,
    member_id INT,
    product_id INT,
    quantity INT NOT NULL,

    FOREIGN KEY (member_id) REFERENCES member(id),
    FOREIGN KEY (product_id) REFERENCES product(id)
);
```

### 5-5. 데이터

```sql
INSERT INTO member (name, email, age) VALUES
('홍길동', 'hong@test.com', 25),
('김철수', 'kim@test.com', 30),
('이영희', 'lee@test.com', 28),
('박민수', 'park@test.com', 35);

INSERT INTO product (name, price, stock) VALUES
('노트북', 1200000, 10),
('키보드', 50000, 30),
('마우스', 30000, 50),
('모니터', 300000, 20),
('헤드셋', 80000, 15);

INSERT INTO orders (member_id, product_id, quantity) VALUES
(1, 1, 1),
(1, 3, 2),
(2, 2, 1),
(3, 4, 1),
(4, 5, 2);
```

---

## 6. 종합 실습문제

1. 전체 회원 조회  
2. 가격 100,000원 이상 상품  
3. 가격 높은 순 상품  
4. 회원 이름과 주문 수량  
5. 회원 이름과 상품명  
6. 회원별 주문 건수  
7. 상품별 판매 수량  
8. 주문금액 높은 회원 순  
9. 한 번도 주문하지 않은 회원  
10. 평균 가격보다 비싼 상품  

---

## 7. 정답

**1**
```sql
SELECT * FROM member;
```

**2**
```sql
SELECT * FROM product
WHERE price >= 100000;
```

**3**
```sql
SELECT * FROM product
ORDER BY price DESC;
```

**4**
```sql
SELECT m.name, o.quantity
FROM member m
JOIN orders o ON m.id = o.member_id;
```

**5**
```sql
SELECT m.name AS 회원명, p.name AS 상품명
FROM orders o
JOIN member m ON o.member_id = m.id
JOIN product p ON o.product_id = p.id;
```

**6**
```sql
SELECT m.name AS 회원명, COUNT(o.id) AS 주문건수
FROM member m
LEFT JOIN orders o ON m.id = o.member_id
GROUP BY m.id, m.name;
```

**7**
```sql
SELECT p.name AS 상품명, SUM(o.quantity) AS 판매수량
FROM product p
JOIN orders o ON p.id = o.product_id
GROUP BY p.id, p.name;
```

**8**
```sql
SELECT m.name AS 회원명,
       SUM(p.price * o.quantity) AS 주문금액
FROM member m
JOIN orders o ON m.id = o.member_id
JOIN product p ON o.product_id = p.id
GROUP BY m.id, m.name
ORDER BY 주문금액 DESC;
```

**9**
```sql
SELECT m.name
FROM member m
LEFT JOIN orders o ON m.id = o.member_id
WHERE o.id IS NULL;
```

**10**
```sql
SELECT *
FROM product
WHERE price > (SELECT AVG(price) FROM product);
```

---

## 8. 최종 미션 (학생 자율)

요구사항만 주고 직접 작성하게 합니다.

**구현할 SQL**

1. 회원·상품·주문 등록  
2. 회원·상품 조회  
3. 가격 조건 검색·정렬  
4. 회원별 주문 건수  
5. 상품별 판매량  
6. 회원별 총 주문금액  
7. 주문하지 않은 회원  
8. 평균보다 비싼 상품  

---

## 9. SQL 실행 순서 (한 장)

```
SELECT
  ↓
WHERE
  ↓
GROUP BY
  ↓
HAVING
  ↓
ORDER BY
  ↓
LIMIT
```

예:

```sql
SELECT category, COUNT(*) AS cnt
FROM product
WHERE price >= 50000
GROUP BY category
HAVING cnt >= 2
ORDER BY cnt DESC
LIMIT 5;
```

---

## 10. 핵심 문법 한 장 정리

```sql
CREATE DATABASE db;   USE db;   SHOW DATABASES;

CREATE TABLE t (
  id INT PRIMARY KEY,
  name VARCHAR(50) NOT NULL
);

SELECT * FROM t;
SELECT * FROM t WHERE 조건;
ORDER BY 컬럼 DESC;
GROUP BY 컬럼;
HAVING 조건;

INSERT INTO t VALUES (...);
UPDATE t SET col = val WHERE 조건;
DELETE FROM t WHERE 조건;

SELECT * FROM t1 a JOIN t2 b ON a.id = b.t1_id;

SELECT * FROM t
WHERE price > (SELECT AVG(price) FROM t);
```

---

## 11. 8일 후 목표 SQL (이해하면 성공)

처음부터 작성할 필요는 없습니다. **흐름을 이해**하면 됩니다.

```sql
SELECT
    m.name AS 회원명,
    COUNT(o.id) AS 주문건수,
    SUM(p.price * o.quantity) AS 주문금액
FROM member m
LEFT JOIN orders o ON m.id = o.member_id
LEFT JOIN product p ON o.product_id = p.id
GROUP BY m.id, m.name
HAVING 주문금액 >= 100000
ORDER BY 주문금액 DESC;
```

```
회원 → 주문 연결 → 상품 연결
 → 회원별 그룹 → 금액 계산 → 조건 → 정렬
```

이 흐름을 알면 JDBC · MyBatis · JPA · Spring Boot + MySQL로 넘어가기 쉽습니다.

---

## 12. 강사 마무리

학생에게 강조할 5가지:

1. SQL은 영어처럼 읽는다  
2. SELECT / FROM / WHERE 먼저  
3. GROUP BY = 묶어서 계산  
4. JOIN = ON으로 연결  
5. UPDATE·DELETE = WHERE 확인  

### 과정 체크

- [ ] PK / FK / NOT NULL / UNIQUE
- [ ] shop_db 생성·데이터 입력
- [ ] 종합문제 1~10
- [ ] 최종 미션 일부 완성
