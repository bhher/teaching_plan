# MySQL 6일차 — JOIN

**선수:** [5일차](./MySQL-5일차.md)  
**다음:** [7일차 INSERT·UPDATE·DELETE·서브쿼리](./MySQL-7일차.md)

> 오늘 만든 관계는 **8일차 쇼핑몰 프로젝트**로 이어집니다.

---

## 1. 학습목표

- JOIN이 필요한 이유를 이해한다.
- PK와 FK 관계를 이해한다.
- INNER JOIN / LEFT JOIN을 사용할 수 있다.
- 테이블 별칭으로 여러 테이블을 연결한다.

---

## 2. 왜 JOIN이 필요한가?

```
member                          orders
─────────────────               ─────────────────────────
id    name                      id   member_id   product
1     홍길동                     1    1           노트북
2     김철수                     2    2           키보드
3     이영희                     3    1           마우스
```

`orders.member_id`로 **어느 회원의 주문인지** 연결합니다.

```
member.id  ←──→  orders.member_id
```

---

## 3. 테이블 생성

```sql
USE academy_db;

CREATE TABLE member2 (
    id INT PRIMARY KEY,
    name VARCHAR(50)
);

CREATE TABLE order2 (
    id INT PRIMARY KEY,
    member_id INT,
    product VARCHAR(50),
    price INT
);

INSERT INTO member2 VALUES
(1, '홍길동'),
(2, '김철수'),
(3, '이영희');

INSERT INTO order2 VALUES
(1, 1, '노트북', 1200000),
(2, 2, '키보드', 50000),
(3, 1, '마우스', 30000);
```

---

## 4. INNER JOIN

양쪽 **모두 일치**하는 행만.

```sql
SELECT
    m.name,
    o.product,
    o.price
FROM member2 m
INNER JOIN order2 o
ON m.id = o.member_id;
```

결과:

| name | product | price |
|------|---------|-------|
| 홍길동 | 노트북 | 1200000 |
| 김철수 | 키보드 | 50000 |
| 홍길동 | 마우스 | 30000 |

(이영희는 주문 없음 → INNER에는 안 나옴)

---

## 5. LEFT JOIN

**왼쪽(회원)은 전부**, 주문 없으면 NULL.

```sql
SELECT
    m.name,
    o.product,
    o.price
FROM member2 m
LEFT JOIN order2 o
ON m.id = o.member_id;
```

→ 이영희도 이름만 나옵니다.

---

## 6. JOIN 도식

```
member2                 order2

id  name                member_id  product
1   홍길동       ←────── 1          노트북
2   김철수       ←────── 2          키보드
3   이영희              (주문 없음)
```

핵심 한 줄:

```sql
ON m.id = o.member_id
```

별칭:

```sql
FROM member2 m
JOIN order2 o
-- m = member2, o = order2
```

---

## 7. 실습문제

**문제 1.** 회원 이름과 주문 상품을 조회하시오. (INNER)

**문제 2.** 회원 이름, 상품명, 가격을 조회하시오.

**문제 3.** 주문하지 않은 회원도 포함해서 회원 이름과 주문 상품을 조회하시오. (LEFT)

**문제 4.** 회원별 총 주문금액을 구하시오.

---

## 8. 정답

```sql
SELECT m.name, o.product
FROM member2 m
INNER JOIN order2 o
ON m.id = o.member_id;
```

```sql
SELECT m.name,
       o.product,
       o.price
FROM member2 m
JOIN order2 o
ON m.id = o.member_id;
```

```sql
SELECT m.name,
       o.product
FROM member2 m
LEFT JOIN order2 o
ON m.id = o.member_id;
```

```sql
SELECT m.name,
       SUM(o.price) AS total
FROM member2 m
LEFT JOIN order2 o
ON m.id = o.member_id
GROUP BY m.id, m.name;
```

---

## 9. 강사 설명 포인트

학생에게 반드시 질문:

> "왜 order2에 member_id가 필요한가?"

→ **어떤 회원의 주문인지 연결**하기 위해서.

JOIN = 테이블을 **가로로 이어 붙이는** 것.

### 오늘 체크

- [ ] INNER vs LEFT 결과 차이 설명
- [ ] ON 조건 작성
- [ ] JOIN + GROUP BY
