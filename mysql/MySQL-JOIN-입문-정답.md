# MySQL JOIN 입문 — 정답

문제: [MySQL-JOIN-입문-문제.md](./MySQL-JOIN-입문-문제.md)

```sql
USE join_intro;
```

---

## Part A

| 번호 | 정답 | 해설 |
|------|------|------|
| 1 | ② | JOIN = 여러 테이블 연결 조회 |
| 2 | ② | member.member_id = PK, orders.member_id = FK |
| 3 | ③ | ON 절이 연결 조건 |
| 4 | ② | JOIN=연결, ON=기준 |
| 5 | ② | 주문 4건 → 4행 (박민수 제외) |
| 6 | ② | member_id=4 주문 없음 |
| 7 | ② | LEFT JOIN에서 FROM 뒤가 왼쪽 |
| 8 | ③ | 오른쪽(orders) 키가 NULL |
| 9 | ② | INNER=교집합, LEFT=왼쪽 전부 |
| 10 | ② | RIGHT JOIN = 오른쪽 전부 |

---

## Part B

### 11번
```sql
SELECT *
FROM member
JOIN orders
  ON member.member_id = orders.member_id;
```

### 12번
```sql
SELECT member.name
      ,member.age
      ,orders.product
      ,orders.price
FROM member
JOIN orders
  ON member.member_id = orders.member_id;
```

### 13번
```sql
SELECT m.name
      ,m.age
      ,o.order_id
      ,o.product
      ,o.price
FROM member m
JOIN orders o
  ON m.member_id = o.member_id;
```

### 14번
```sql
SELECT m.name
      ,m.age
      ,o.order_id
      ,o.product
      ,o.price
FROM member m
JOIN orders o
  ON m.member_id = o.member_id
WHERE m.member_id = 1;
```

(2행: 노트북, 키보드)

### 15번
```sql
SELECT m.name
      ,m.age
      ,o.product
      ,o.price
FROM member m
JOIN orders o
  ON m.member_id = o.member_id
WHERE m.age >= 30;
```

(INNER JOIN 결과 **1행**: 김철수만. 박민수는 35세지만 주문 없음)

---

## Part C

### 16번
```sql
SELECT m.name
      ,m.age
      ,o.product
      ,o.price
FROM member m
LEFT JOIN orders o
  ON m.member_id = o.member_id;
```

(5행: 주문 4건 + 박민수 1행 NULL)

### 17번
```sql
SELECT m.member_id
      ,m.name
FROM member m
LEFT JOIN orders o
  ON m.member_id = o.member_id
WHERE o.member_id IS NULL;
```

(1행: 박민수)

### 18번
```sql
SELECT m.name
      ,COUNT(o.order_id) AS 주문수
FROM member m
LEFT JOIN orders o
  ON m.member_id = o.member_id
GROUP BY m.member_id, m.name;
```

| name | 주문수 |
|------|--------|
| 홍길동 | 2 |
| 김철수 | 1 |
| 이영희 | 1 |
| 박민수 | 0 |

### 19번
```sql
SELECT m.name
      ,o.order_id
      ,p.product_name
      ,p.price
FROM member m
JOIN orders o
  ON m.member_id = o.member_id
JOIN product p
  ON o.product_id = p.product_id;
```

(4행)

### 20번
```sql
SELECT m.name
      ,SUM(o.price) AS 총금액
FROM member m
LEFT JOIN orders o
  ON m.member_id = o.member_id
GROUP BY m.member_id, m.name;
```

| name | 총금액 |
|------|--------|
| 홍길동 | 1280000 |
| 김철수 | 30000 |
| 이영희 | 300000 |
| 박민수 | NULL |

주문 없는 회원은 `SUM` 결과가 **NULL**입니다. 0으로 보이게 하려면:

```sql
SELECT m.name
      ,IFNULL(SUM(o.price), 0) AS 총금액
FROM member m
LEFT JOIN orders o
  ON m.member_id = o.member_id
GROUP BY m.member_id, m.name;
```
