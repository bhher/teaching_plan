# MySQL 2일차 — SELECT 기본

**선수:** [1일차](./MySQL-1일차.md)  
**다음:** [3일차 WHERE·정렬](./MySQL-3일차.md)

---

## 1. 학습목표

- SELECT의 기본 문법을 이해한다.
- 원하는 컬럼만 조회한다.
- 컬럼에 별칭(Alias)을 사용할 수 있다.
- DISTINCT를 사용할 수 있다.
- 산술연산을 SQL에서 사용할 수 있다.

---

## 2. SELECT 기본 구조

```sql
SELECT 컬럼
FROM 테이블;
```

영어처럼 읽기:

> product에서 name을 선택해라.

```sql
SELECT name
FROM member;
```

여러 컬럼:

```sql
SELECT name, age
FROM member;
```

전체:

```sql
SELECT *
FROM member;
```

---

## 3. 별칭 Alias

```sql
SELECT name AS 이름
FROM member;
```

`AS`는 생략 가능:

```sql
SELECT name 이름
FROM member;
```

---

## 4. DISTINCT (중복 제거)

```sql
SELECT DISTINCT age
FROM member;
```

예: 나이 데이터가 `20, 20, 21, 22, 22` 이면 결과는

```
20
21
22
```

---

## 5. 산술연산

```sql
SELECT 10 + 20;
```

테이블에서:

```sql
SELECT price, price * 1.1
FROM product;
```

---

## 6. 실습용 테이블 (오늘 데이터)

```sql
USE academy_db;   -- 없으면 CREATE DATABASE 후 USE

CREATE TABLE product (
    id INT,
    name VARCHAR(50),
    price INT,
    stock INT
);

INSERT INTO product VALUES
(1, '노트북', 1200000, 10),
(2, '키보드', 50000, 30),
(3, '마우스', 30000, 50),
(4, '모니터', 300000, 20),
(5, '노트북 가방', 80000, 15);
```

> 이 `product` 테이블은 **3~4일차, 7일차**에서도 계속 사용합니다.

---

## 7. 예제 (시연)

```sql
SELECT * FROM product;
```

```sql
SELECT name
FROM product;
```

```sql
SELECT name, price
FROM product;
```

부가세 10%:

```sql
SELECT name, price, price * 1.1 AS total_price
FROM product;
```

재고 금액:

```sql
SELECT name, price * stock AS stock_amount
FROM product;
```

---

## 8. 실습문제

**문제 1.** 상품명과 가격을 조회하시오.

**문제 2.** 상품명과 가격을 각각 `상품명`, `가격`으로 표시하시오.

**문제 3.** 상품의 가격에 10%를 더한 가격을 계산하시오.

**문제 4.** 재고 수량의 중복을 제거해서 조회하시오.

---

## 9. 정답

```sql
SELECT name, price
FROM product;
```

```sql
SELECT name AS 상품명,
       price AS 가격
FROM product;
```

```sql
SELECT name,
       price * 1.1 AS 판매가격
FROM product;
```

```sql
SELECT DISTINCT stock
FROM product;
```

---

## 10. 강사 설명 포인트

SQL을 **영어 문장**처럼 읽게 합니다.

```sql
SELECT name
FROM product;
```

→

> product에서 name을 선택해라.

이 습관이 생기면 3일차 WHERE부터 훨씬 쉬워집니다.

### 오늘 체크

- [ ] SELECT 컬럼 / *
- [ ] AS 별칭
- [ ] DISTINCT
- [ ] price * 1.1 계산
