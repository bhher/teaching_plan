# MySQL JOIN 입문 설명

> **JOIN** = 여러 테이블을 **연결**해서 필요한 데이터를 **한 번에** 조회하는 것  
> PK(기본키) · FK(외래키) · GROUP BY와 연결해서 이해하면 쉽습니다.

**DB 생성:** [sql/join-입문-member-orders.sql](./sql/join-입문-member-orders.sql)  
**연습 문제:** [MySQL-JOIN-입문-문제.md](./MySQL-JOIN-입문-문제.md) · [정답](./MySQL-JOIN-입문-정답.md)

```bash
mysql -u root -p --default-character-set=utf8mb4 < "경로\join-입문-member-orders.sql"
```

Workbench: 파일 열고 전체 실행 → `USE join_intro;`

**실습 순서:** JOIN → LEFT JOIN → JOIN + WHERE → JOIN + GROUP BY → 3개 테이블 JOIN

---

## 1. JOIN이란?

회원 정보와 주문 정보가 **테이블마다 나뉘어** 있다고 합니다.

### member (회원)

| member_id | name | age |
|-----------|------|-----|
| 1 | 홍길동 | 25 |
| 2 | 김철수 | 30 |
| 3 | 이영희 | 28 |
| 4 | 박민수 | 35 |

### orders (주문)

| order_id | member_id | product | price |
|----------|-----------|---------|-------|
| 101 | 1 | 노트북 | 1200000 |
| 102 | 2 | 마우스 | 30000 |
| 103 | 1 | 키보드 | 80000 |
| 104 | 3 | 모니터 | 300000 |

회원 **이름**과 주문 **상품**을 같이 보려면:

```sql
USE join_intro;

SELECT *
FROM member
JOIN orders
  ON member.member_id = orders.member_id;
```

### 결과 (4행)

| member_id | name | order_id | product |
|-----------|------|----------|---------|
| 1 | 홍길동 | 101 | 노트북 |
| 2 | 김철수 | 102 | 마우스 |
| 1 | 홍길동 | 103 | 키보드 |
| 3 | 이영희 | 104 | 모니터 |

연결 기준:

```text
member.member_id
       ↓
orders.member_id
```

---

## 2. 왜 테이블을 나누나요?

실무에서는 모든 데이터를 **하나의 테이블**에 넣지 않습니다.

| 회원 | 주문 |
|------|------|
| 회원번호 | 주문번호 |
| 이름 | 회원번호 |
| 전화번호 | 상품 |
| 주소 | 가격 |

**회원번호**가 두 테이블을 잇는 연결고리입니다.

```text
member
┌───────────┐
│ member_id │ ← PK (기본키)
│ name      │
│ age       │
└─────┬─────┘
      │
      │ FK (외래키)
      ↓
┌───────────┐
│ orders    │
│ order_id  │
│ member_id │
│ product   │
│ price     │
└───────────┘
```

> **PK와 FK를 이용해서 테이블을 연결하는 것이 JOIN이다.**

```text
PK ↔ FK  →  JOIN  →  여러 테이블의 데이터를 하나로 조회
```

---

## 3. 예제 테이블 만들기

수업 DB는 SQL 파일 한 번 실행으로 준비됩니다.

```sql
CREATE TABLE member (
    member_id INT PRIMARY KEY,
    name VARCHAR(20),
    age INT
);

INSERT INTO member VALUES
(1, '홍길동', 25),
(2, '김철수', 30),
(3, '이영희', 28),
(4, '박민수', 35);
```

```sql
CREATE TABLE orders (
    order_id INT PRIMARY KEY,
    member_id INT,
    product_id INT,
    product VARCHAR(50),
    price INT
);

INSERT INTO orders VALUES
(101, 1, 10, '노트북', 1200000),
(102, 2, 20, '마우스', 30000),
(103, 1, 30, '키보드', 80000),
(104, 3, 40, '모니터', 300000);
```

3개 테이블 연습용 **product** 테이블도 포함되어 있습니다.

```sql
CREATE TABLE product (
    product_id INT PRIMARY KEY,
    product_name VARCHAR(50),
    price INT
);

INSERT INTO product VALUES
(10, '노트북', 1200000),
(20, '마우스', 30000),
(30, '키보드', 80000),
(40, '모니터', 300000);
```

---

## 4. INNER JOIN

가장 먼저 가르치는 JOIN입니다.

```sql
SELECT
    member.member_id,
    member.name,
    orders.product,
    orders.price
FROM member
INNER JOIN orders
  ON member.member_id = orders.member_id;
```

### 결과 (4행)

| member_id | name | product | price |
|-----------|------|---------|-------|
| 1 | 홍길동 | 노트북 | 1200000 |
| 1 | 홍길동 | 키보드 | 80000 |
| 2 | 김철수 | 마우스 | 30000 |
| 3 | 이영희 | 모니터 | 300000 |

**박민수(4)** 는 나오지 않습니다. `orders`에 `member_id = 4`인 주문이 없기 때문입니다.

> **INNER JOIN 핵심:** 양쪽 테이블에 **모두 존재하는** 데이터만 가져온다.

`JOIN`만 써도 MySQL에서는 보통 **INNER JOIN**과 같습니다.

---

## 5. JOIN과 ON

```sql
FROM member
JOIN orders
ON member.member_id = orders.member_id
```

| 부분 | 의미 |
|------|------|
| `JOIN` | 두 테이블을 **연결**한다 |
| `ON ... = ...` | **무엇을 기준**으로 연결할지 지정한다 |

즉:

- **JOIN** = 연결해!
- **ON** = 무엇을 기준으로 연결할까?

---

## 6. 테이블 별칭 사용하기

실무에서는 별칭을 많이 사용합니다.

```sql
SELECT
    m.member_id,
    m.name,
    o.product,
    o.price
FROM member AS m
JOIN orders AS o
  ON m.member_id = o.member_id;
```

`AS`를 생략해도 됩니다. (추천)

```sql
SELECT
    m.member_id,
    m.name,
    o.product,
    o.price
FROM member m
JOIN orders o
  ON m.member_id = o.member_id;
```

---

## 7. WHERE와 JOIN을 같이 사용

30세 이상 회원의 주문만 조회:

```sql
SELECT
    m.name,
    m.age,
    o.product,
    o.price
FROM member m
JOIN orders o
  ON m.member_id = o.member_id
WHERE m.age >= 30;
```

### 결과 (1행)

| name | age | product | price |
|------|-----|---------|-------|
| 김철수 | 30 | 마우스 | 30000 |

박민수(35)는 나이 조건은 맞지만 **주문이 없어** INNER JOIN 결과에 포함되지 않습니다.

---

## 8. LEFT JOIN

```sql
SELECT
    m.member_id,
    m.name,
    o.product
FROM member m
LEFT JOIN orders o
  ON m.member_id = o.member_id;
```

### 결과 (5행)

| member_id | name | product |
|-----------|------|---------|
| 1 | 홍길동 | 노트북 |
| 1 | 홍길동 | 키보드 |
| 2 | 김철수 | 마우스 |
| 3 | 이영희 | 모니터 |
| 4 | 박민수 | NULL |

**박민수**도 나옵니다. LEFT JOIN은 **왼쪽 테이블(member)의 모든 행**을 보여주기 때문입니다.

```text
왼쪽(member)     오른쪽(orders)
     ↓                  ↓
  전부 표시      없으면 NULL
```

> **LEFT JOIN 핵심:** 왼쪽 테이블은 **무조건** 나온다.

---

## 9. INNER JOIN vs LEFT JOIN

| 회원 | 주문 | INNER JOIN | LEFT JOIN |
|------|------|------------|-----------|
| 홍길동 | 있음 | O | O |
| 김철수 | 있음 | O | O |
| 이영희 | 있음 | O | O |
| 박민수 | 없음 | X | O + NULL |

```text
INNER JOIN                    LEFT JOIN
회원 + 주문 모두 존재  →  출력    회원은 무조건 출력
                               주문 있으면 주문 정보
                               없으면 NULL
```

---

## 10. 주문이 없는 회원 찾기

실무에서 자주 쓰는 패턴입니다.

> **"주문을 한 번도 하지 않은 회원을 찾아라."**

```sql
SELECT
    m.member_id,
    m.name
FROM member m
LEFT JOIN orders o
  ON m.member_id = o.member_id
WHERE o.member_id IS NULL;
```

### 결과

| member_id | name |
|-----------|------|
| 4 | 박민수 |

```text
LEFT JOIN
    ↓
오른쪽(orders) 데이터가 없는 경우
    ↓
WHERE o.member_id IS NULL
```

---

## 11. RIGHT JOIN

```sql
SELECT
    m.name,
    o.product
FROM member m
RIGHT JOIN orders o
  ON m.member_id = o.member_id;
```

**오른쪽 테이블(orders)을 모두** 보여줍니다.

실무·교육에서는 **LEFT JOIN**을 더 많이 씁니다.  
RIGHT JOIN은 테이블 순서를 바꿔 LEFT JOIN으로 표현할 수 있습니다.

```sql
-- member m RIGHT JOIN orders o
-- 와 같은 방향:
SELECT m.name, o.product
FROM orders o
LEFT JOIN member m
  ON m.member_id = o.member_id;
```

---

## 12. 3개 테이블 JOIN

```text
member  →  orders  →  product
```

```sql
SELECT
    m.name,
    o.order_id,
    p.product_name,
    p.price
FROM member m
JOIN orders o
  ON m.member_id = o.member_id
JOIN product p
  ON o.product_id = p.product_id;
```

JOIN을 **여러 번** 이어 붙이면 3개 이상 테이블도 연결할 수 있습니다.

---

## 13. JOIN + GROUP BY

회원별 **주문 횟수**를 구합니다.

```sql
SELECT
    m.name,
    COUNT(o.order_id) AS 주문수
FROM member m
LEFT JOIN orders o
  ON m.member_id = o.member_id
GROUP BY m.member_id, m.name;
```

### 결과 (4행)

| name | 주문수 |
|------|--------|
| 홍길동 | 2 |
| 김철수 | 1 |
| 이영희 | 1 |
| 박민수 | 0 |

LEFT JOIN을 썼기 때문에 주문이 없는 **박민수(0)** 도 포함됩니다.

---

## 14. JOIN 전체 정리

```text
                    JOIN
                     │
        ┌────────────┼────────────┐
        ↓            ↓            ↓
   INNER JOIN     LEFT JOIN    RIGHT JOIN
   양쪽 모두       왼쪽 전부      오른쪽 전부
   존재하는 것     + 없으면 NULL  + 없으면 NULL
```

| JOIN | 의미 |
|------|------|
| **INNER JOIN** | 양쪽에 모두 존재하는 데이터 |
| **LEFT JOIN** | 왼쪽은 모두 표시 |
| **RIGHT JOIN** | 오른쪽은 모두 표시 |

### 학생들에게 가르칠 핵심 공식

```sql
SELECT 컬럼
FROM 테이블1 A
JOIN 테이블2 B
  ON A.공통컬럼 = B.공통컬럼;
```

이것만 먼저 확실히 이해시킨 뒤,

```text
PK ↔ FK  →  JOIN  →  여러 테이블의 데이터를 하나로 조회
```

관계를 잡아 주면 됩니다.

---

## 혼자 확인용 SQL

```sql
USE join_intro;

SELECT * FROM member;
SELECT * FROM orders;
SELECT * FROM product;

-- INNER JOIN
SELECT m.name, m.age, o.product, o.price
FROM member m
JOIN orders o ON m.member_id = o.member_id;

-- LEFT JOIN
SELECT m.name, m.age, o.product, o.price
FROM member m
LEFT JOIN orders o ON m.member_id = o.member_id;

-- 주문 없는 회원
SELECT m.name
FROM member m
LEFT JOIN orders o ON m.member_id = o.member_id
WHERE o.member_id IS NULL;

-- JOIN + GROUP BY
SELECT m.name, COUNT(o.order_id) AS 주문수
FROM member m
LEFT JOIN orders o ON m.member_id = o.member_id
GROUP BY m.member_id, m.name;

-- 3개 테이블 JOIN
SELECT m.name, o.order_id, p.product_name, p.price
FROM member m
JOIN orders o ON m.member_id = o.member_id
JOIN product p ON o.product_id = p.product_id;
```
