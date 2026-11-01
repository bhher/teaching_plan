# MySQL 7일차 — INSERT, UPDATE, DELETE와 서브쿼리

**선수:** [6일차 JOIN](./MySQL-6일차.md), [2일차 product](./MySQL-2일차.md)  
**다음:** [8일차 설계·종합](./MySQL-8일차.md)

---

## 1. 학습목표

- INSERT로 데이터를 입력한다.
- UPDATE로 수정한다. (WHERE 필수)
- DELETE로 삭제한다. (WHERE 필수)
- 서브쿼리의 기본 개념을 이해한다.

---

## 2. INSERT

```sql
USE academy_db;

INSERT INTO product
VALUES (6, '헤드셋', 70000, 20);
```

컬럼 지정:

```sql
INSERT INTO product
(id, name, price, stock)
VALUES
(7, 'USB', 10000, 100);
```

---

## 3. UPDATE

```sql
UPDATE product
SET price = 60000
WHERE id = 2;
```

여러 컬럼:

```sql
UPDATE product
SET price = 65000,
    stock = 50
WHERE id = 2;
```

---

## 4. DELETE

```sql
DELETE FROM product
WHERE id = 7;
```

---

## 5. 매우 중요한 주의사항

**위험:**

```sql
UPDATE product
SET price = 50000;
-- WHERE 없음 → 모든 상품 가격 변경!
```

```sql
DELETE FROM product;
-- WHERE 없음 → 전체 삭제!
```

> UPDATE / DELETE 할 때는 **WHERE를 먼저** 생각한다.

학생에게:

```sql
UPDATE member
SET age = 20;
```

→ 모든 회원 나이가 20살이 됩니다.  
올바른 예:

```sql
UPDATE member
SET age = 20
WHERE id = 3;
```

---

## 6. 서브쿼리

SQL 안에 SQL.

평균보다 비싼 상품:

```sql
SELECT *
FROM product
WHERE price > (
    SELECT AVG(price)
    FROM product
);
```

흐름:

```
안쪽: AVG(price) 계산  →  예: 332000
바깥: price > 332000 인 행 조회
```

---

## 7. 실습문제

**문제 1.** 새 상품 추가  
`id=8, name='웹캠', price=90000, stock=10`

**문제 2.** 웹캠 가격을 80000원으로 수정

**문제 3.** 웹캠 삭제

**문제 4.** 평균 가격보다 비싼 상품 조회

---

## 8. 정답

```sql
INSERT INTO product
VALUES (8, '웹캠', 90000, 10);
```

```sql
UPDATE product
SET price = 80000
WHERE id = 8;
```

```sql
DELETE FROM product
WHERE id = 8;
```

```sql
SELECT *
FROM product
WHERE price > (
    SELECT AVG(price)
    FROM product
);
```

---

## 9. 강사 설명 포인트

- CRUD 중 **U/D는 실무 사고 포인트**
- 서브쿼리는 "먼저 안쪽 실행 → 바깥에서 사용"으로만 이해시켜도 충분
- 8일차에 JOIN + 서브쿼리를 한꺼번에 씀

### 오늘 체크

- [ ] INSERT
- [ ] UPDATE … WHERE
- [ ] DELETE … WHERE
- [ ] 평균 비교 서브쿼리
