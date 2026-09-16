# MySQL 5일차 — GROUP BY와 HAVING

**선수:** [4일차](./MySQL-4일차.md) 집계함수  
**다음:** [6일차 JOIN](./MySQL-6일차.md)

---

## 1. 학습목표

- GROUP BY의 역할을 이해한다.
- 그룹별 집계가 가능하다.
- HAVING과 WHERE의 차이를 이해한다.
- 실무형 통계 SQL을 작성할 수 있다.

---

## 2. 실습용 테이블

```sql
USE academy_db;

CREATE TABLE orders (
    id INT,
    customer VARCHAR(50),
    category VARCHAR(30),
    price INT,
    quantity INT
);

INSERT INTO orders VALUES
(1, '홍길동', '전자제품', 100000, 2),
(2, '김철수', '전자제품', 200000, 1),
(3, '이영희', '의류', 50000, 3),
(4, '홍길동', '의류', 70000, 2),
(5, '김철수', '식품', 30000, 5),
(6, '이영희', '전자제품', 300000, 1);
```

---

## 3. GROUP BY란?

같은 값을 **하나의 그룹으로 묶어** 집계합니다.

```
홍길동
홍길동
김철수
김철수
이영희
        ↓ GROUP BY customer
홍길동 그룹
김철수 그룹
이영희 그룹
```

카테고리별 주문 건수:

```sql
SELECT category, COUNT(*)
FROM orders
GROUP BY category;
```

카테고리별 주문금액:

```sql
SELECT category,
       SUM(price * quantity) AS total
FROM orders
GROUP BY category;
```

고객별 주문금액:

```sql
SELECT customer,
       SUM(price * quantity) AS total
FROM orders
GROUP BY customer;
```

---

## 4. HAVING

**그룹 결과**에 조건을 겁니다.

```sql
SELECT customer,
       SUM(price * quantity) AS total
FROM orders
GROUP BY customer
HAVING total >= 300000;
```

---

## 5. WHERE vs HAVING

| | WHERE | HAVING |
|--|-------|--------|
| 시점 | 그룹화 **전** | 그룹화 **후** |
| 대상 | 원본 행 | 집계 결과 |

```
WHERE → 원본 필터
  ↓
GROUP BY → 묶기
  ↓
HAVING → 그룹 결과 필터
```

```sql
-- WHERE: 행 필터
SELECT *
FROM orders
WHERE price >= 100000;

-- HAVING: 그룹 필터
SELECT customer,
       SUM(price * quantity) AS total
FROM orders
GROUP BY customer
HAVING total >= 300000;
```

---

## 6. 실습문제

**문제 1.** 카테고리별 주문 건수를 구하시오.

**문제 2.** 고객별 총 주문금액을 구하시오.

**문제 3.** 카테고리별 총 주문금액이 200,000원 이상인 카테고리만 조회하시오.

**문제 4.** 고객별 주문금액을 높은 순으로 출력하시오.

---

## 7. 정답

```sql
SELECT category,
       COUNT(*) AS 주문건수
FROM orders
GROUP BY category;
```

```sql
SELECT customer,
       SUM(price * quantity) AS total
FROM orders
GROUP BY customer;
```

```sql
SELECT category,
       SUM(price * quantity) AS total
FROM orders
GROUP BY category
HAVING total >= 200000;
```

```sql
SELECT customer,
       SUM(price * quantity) AS total
FROM orders
GROUP BY customer
ORDER BY total DESC;
```

---

## 8. 강사 설명 포인트

GROUP BY를 어려워하면:

> "같은 이름을 가진 행끼리 바구니 모아서, 모둠마다 합계·개수를 구하는 것"

6일차 JOIN 뒤에도 `GROUP BY`를 자주 씁니다 (회원별 주문 건수 등).

### 오늘 체크

- [ ] GROUP BY + COUNT/SUM
- [ ] HAVING
- [ ] WHERE와 HAVING 구분 설명
