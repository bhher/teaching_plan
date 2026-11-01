# MySQL 4일차 — 함수와 집계함수

**선수:** [3일차](./MySQL-3일차.md) (`product` 테이블)  
**다음:** [5일차 GROUP BY](./MySQL-5일차.md)

---

## 1. 학습목표

- SQL 함수를 이해한다.
- 문자열·숫자·날짜 함수를 사용할 수 있다.
- COUNT, SUM, AVG, MAX, MIN을 사용할 수 있다.

---

## 2. 문자열 함수

```sql
SELECT UPPER(name) FROM product;
SELECT LOWER(name) FROM product;
SELECT name, LENGTH(name) FROM product;
```

> `LENGTH`는 바이트 길이. 한글 글자 수는 `CHAR_LENGTH(name)` 이 더 직관적일 수 있음 (참고).

---

## 3. 숫자 함수

```sql
SELECT ROUND(1234.567, 2);  -- 반올림
SELECT CEIL(1234.1);        -- 올림
SELECT FLOOR(1234.9);       -- 내림
```

---

## 4. 날짜 함수 (가볍게)

```sql
SELECT NOW();
SELECT CURDATE();
```

---

## 5. 집계함수

여러 행을 **계산해서 결과 하나로** 만듭니다.

| 함수 | 의미 |
|------|------|
| COUNT(*) | 행 개수 |
| SUM(컬럼) | 합 |
| AVG(컬럼) | 평균 |
| MAX(컬럼) | 최대 |
| MIN(컬럼) | 최소 |

```sql
SELECT COUNT(*) FROM product;
SELECT SUM(stock) FROM product;
SELECT AVG(price) FROM product;
SELECT MAX(price) FROM product;
SELECT MIN(price) FROM product;
```

별칭:

```sql
SELECT COUNT(*) AS 상품수 FROM product;
SELECT SUM(stock) AS 총재고 FROM product;
SELECT AVG(price) AS 평균가격 FROM product;
SELECT MAX(price) AS 최고가격 FROM product;
SELECT MIN(price) AS 최저가격 FROM product;
```

---

## 6. 일반 컬럼 vs 집계 — 꼭 구분

```sql
SELECT price FROM product;
```

→ **여러 행**

```sql
SELECT AVG(price) FROM product;
```

→ **하나의 결과**

```
일반 컬럼  →  여러 행
집계함수   →  여러 행을 계산 → 요약 결과
```

---

## 7. 실습문제

**문제 1.** 전체 상품 개수를 구하시오.

**문제 2.** 전체 재고 수량을 구하시오.

**문제 3.** 상품 가격의 평균을 구하시오.

**문제 4.** 최고 가격과 최저 가격을 구하시오.

**문제 5.** 평균 가격을 `평균가격`이라는 이름으로 출력하시오.

---

## 8. 정답

```sql
SELECT COUNT(*) AS 상품수
FROM product;
```

```sql
SELECT SUM(stock) AS 총재고
FROM product;
```

```sql
SELECT AVG(price) AS 평균가격
FROM product;
```

```sql
SELECT MAX(price) AS 최고가격,
       MIN(price) AS 최저가격
FROM product;
```

```sql
SELECT AVG(price) AS 평균가격
FROM product;
```

---

## 9. 강사 설명 포인트

오늘 끝나면 학생에게:

> "SELECT price 와 SELECT AVG(price) 결과가 왜 다르게 보이나요?"

→ 행 나열 vs 한 줄 요약.

5일차 GROUP BY는 **"묶은 뒤"** 이 집계를 그룹마다 하는 것입니다.

### 오늘 체크

- [ ] UPPER / LOWER / ROUND
- [ ] COUNT SUM AVG MAX MIN
- [ ] AS로 결과 이름 붙이기
