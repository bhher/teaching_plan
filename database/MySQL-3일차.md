# MySQL 3일차 — WHERE, 조건 검색과 정렬

**선수:** [2일차](./MySQL-2일차.md) (`product` 테이블)  
**다음:** [4일차 함수·집계](./MySQL-4일차.md)

---

## 1. 학습목표

- WHERE로 데이터를 검색한다.
- 비교연산자, AND / OR를 사용한다.
- IN / BETWEEN / LIKE를 사용할 수 있다.
- ORDER BY로 정렬한다.
- LIMIT으로 상위 N건을 가져온다.

---

## 2. WHERE

```sql
SELECT *
FROM product
WHERE price >= 100000;
```

→ 가격이 10만원 **이상**인 상품만.

---

## 3. 비교연산자

| 연산자 | 의미 |
|--------|------|
| `=` | 같다 (비교!) |
| `<>` 또는 `!=` | 같지 않다 |
| `>` ` <` | 크다 / 작다 |
| `>=` `<=` | 이상 / 이하 |

- 문자열 → 따옴표: `WHERE name = '노트북'`
- 숫자 → 따옴표 없음: `WHERE price = 1200000`

---

## 4. AND / OR

둘 다 만족 (AND):

```sql
SELECT *
FROM product
WHERE price >= 50000
AND stock >= 20;
```

하나라도 만족 (OR):

```sql
SELECT *
FROM product
WHERE price >= 500000
OR stock >= 40;
```

---

## 5. BETWEEN

```sql
SELECT *
FROM product
WHERE price BETWEEN 50000 AND 300000;
```

→ 50,000 **이상** 300,000 **이하** (양 끝 포함).

---

## 6. IN

```sql
SELECT *
FROM product
WHERE name IN ('키보드', '마우스');
```

---

## 7. LIKE

| 패턴 | 의미 |
|------|------|
| `%노트%` | 노트 포함 |
| `노트%` | 노트로 시작 |
| `%노트` | 노트로 끝 |

```sql
SELECT *
FROM product
WHERE name LIKE '%노트%';
```

---

## 8. ORDER BY

오름차순 (기본):

```sql
SELECT *
FROM product
ORDER BY price ASC;
```

내림차순:

```sql
SELECT *
FROM product
ORDER BY price DESC;
```

`ASC`는 생략 가능합니다.

---

## 9. LIMIT

가격 상위 3개:

```sql
SELECT *
FROM product
ORDER BY price DESC
LIMIT 3;
```

---

## 10. 실습문제

**문제 1.** 가격이 100,000원 이상인 상품을 조회하시오.

**문제 2.** 가격이 50,000원 이상이고 재고가 20개 이상인 상품을 조회하시오.

**문제 3.** 상품명에 `노트북`이 포함된 상품을 조회하시오.

**문제 4.** 가격이 높은 순서로 상품을 조회하시오.

**문제 5.** 가격이 가장 비싼 상품 2개를 조회하시오.

---

## 11. 정답

```sql
SELECT *
FROM product
WHERE price >= 100000;
```

```sql
SELECT *
FROM product
WHERE price >= 50000
AND stock >= 20;
```

```sql
SELECT *
FROM product
WHERE name LIKE '%노트북%';
```

```sql
SELECT *
FROM product
ORDER BY price DESC;
```

```sql
SELECT *
FROM product
ORDER BY price DESC
LIMIT 2;
```

---

## 12. 강사 설명 포인트

자주 하는 실수:

- `=` 는 **대입이 아니라 비교**
- 문자열에 따옴표 빠뜨리기
- `WHERE` 없이 전체 조회만 하다가 조건 검색을 못 함

읽어 보기:

```sql
SELECT *
FROM product
WHERE price >= 100000
ORDER BY price DESC;
```

> product에서 가격이 10만 이상인 것을 골라, 가격 높은 순으로 보여 줘라.

### 오늘 체크

- [ ] WHERE + 비교
- [ ] AND / OR
- [ ] LIKE / IN / BETWEEN
- [ ] ORDER BY + LIMIT
