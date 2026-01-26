# MySQL 중급 교안

## (JOIN · GROUP BY · HAVING)

---

## 목차

1. [학습 목표](#1-학습-목표)
2. [실습용 테이블 구조](#2-실습용-테이블-구조)
3. [JOIN (테이블 연결)](#3-join-테이블-연결)
4. [GROUP BY (그룹화)](#4-group-by-그룹화)
5. [HAVING (그룹 조건)](#5-having-그룹-조건)
6. [JOIN + GROUP BY + HAVING 종합 예제](#6-join--group-by--having-종합-예제)
7. [SQL 실행 순서](#7-sql-실행-순서-중요-)
8. [실습 문제](#8-실습-문제)
9. [자주 나오는 실수](#9-자주-나오는-실수)
10. [다음 학습 로드맵](#10-다음-학습-로드맵)

---

## 1. 학습 목표

- ✅ 여러 테이블을 연결하는 **JOIN**을 이해한다
- ✅ 데이터를 묶어서 분석하는 **GROUP BY**를 사용할 수 있다
- ✅ 그룹 조건을 거는 **HAVING**을 구분해서 사용할 수 있다
- ✅ JOIN, GROUP BY, HAVING을 함께 사용하여 복잡한 쿼리를 작성할 수 있다

---

## 2. 실습용 테이블 구조

### 2-1. 데이터베이스 및 테이블 생성

```sql
-- 데이터베이스 생성
CREATE DATABASE shop;
USE shop;

-- 회원 테이블 생성
CREATE TABLE member (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(20),
    age INT
);

-- 주문 테이블 생성
CREATE TABLE orders (
    order_id INT PRIMARY KEY AUTO_INCREMENT,
    member_id INT,
    price INT,
    order_date DATE
);
```

### 2-2. 테이블 구조 설명

#### 회원 테이블 (member)

| 컬럼명 | 데이터 타입 | 설명 |
|--------|-----------|------|
| `id` | INT | 회원 번호 (기본키) |
| `name` | VARCHAR(20) | 회원 이름 |
| `age` | INT | 회원 나이 |

#### 주문 테이블 (orders)

| 컬럼명 | 데이터 타입 | 설명 |
|--------|-----------|------|
| `order_id` | INT | 주문 번호 (기본키) |
| `member_id` | INT | 회원 번호 (외래키) |
| `price` | INT | 주문 금액 |
| `order_date` | DATE | 주문 날짜 |

### 2-3. 샘플 데이터 삽입

```sql
-- 회원 데이터 추가
INSERT INTO member (name, age) VALUES
    ('홍길동', 25),
    ('김영희', 30),
    ('박철수', 28),
    ('이미영', 22);

-- 주문 데이터 추가
INSERT INTO orders (member_id, price, order_date) VALUES
    (1, 50000, '2024-01-15'),
    (1, 30000, '2024-01-20'),
    (2, 80000, '2024-01-18'),
    (2, 20000, '2024-01-25'),
    (2, 50000, '2024-02-01'),
    (3, 100000, '2024-01-22'),
    (3, 40000, '2024-02-05');
```

### 2-4. 데이터 확인

```sql
-- 회원 데이터 확인
SELECT * FROM member;
```

**실행 결과:**
```
+----+--------+------+
| id | name   | age  |
+----+--------+------+
|  1 | 홍길동   |   25 |
|  2 | 김영희   |   30 |
|  3 | 박철수   |   28 |
|  4 | 이미영   |   22 |
+----+--------+------+
4 rows in set (0.00 sec)
```

```sql
-- 주문 데이터 확인
SELECT * FROM orders;
```

**실행 결과:**
```
+----------+-----------+-------+------------+
| order_id | member_id | price | order_date |
+----------+-----------+-------+------------+
|        1 |         1 | 50000 | 2024-01-15 |
|        2 |         1 | 30000 | 2024-01-20 |
|        3 |         2 | 80000 | 2024-01-18 |
|        4 |         2 | 20000 | 2024-01-25 |
|        5 |         2 | 50000 | 2024-02-01 |
|        6 |         3 | 100000| 2024-01-22 |
|        7 |         3 | 40000 | 2024-02-05 |
+----------+-----------+-------+------------+
7 rows in set (0.00 sec)
```

**관계 설명:**
- `member.id`와 `orders.member_id`가 연결됨
- 홍길동(id=1): 주문 2건
- 김영희(id=2): 주문 3건
- 박철수(id=3): 주문 2건
- 이미영(id=4): 주문 없음

---

## 3. JOIN (테이블 연결)

### 3-1. JOIN이란?

**JOIN**은 **공통 컬럼을 기준으로 여러 테이블을 연결**하는 기능입니다.

- 두 개 이상의 테이블을 하나의 결과로 합침
- 보통 **외래키(Foreign Key, FK)**를 사용하여 연결
- 관계형 데이터베이스의 핵심 기능

### 3-2. JOIN의 종류

| JOIN 종류 | 설명 |
|----------|------|
| **INNER JOIN** | 양쪽 테이블에 모두 데이터가 있는 경우만 조회 |
| **LEFT JOIN** | 왼쪽 테이블의 모든 데이터 + 오른쪽 테이블의 매칭 데이터 |
| **RIGHT JOIN** | 오른쪽 테이블의 모든 데이터 + 왼쪽 테이블의 매칭 데이터 |
| **FULL OUTER JOIN** | 양쪽 테이블의 모든 데이터 (MySQL에서는 지원 안 함) |

### 3-3. INNER JOIN (가장 기본)

#### 기본 문법

```sql
SELECT 컬럼1, 컬럼2, ...
FROM 테이블1 [별칭1]
INNER JOIN 테이블2 [별칭2]
ON 별칭1.공통컬럼 = 별칭2.공통컬럼;
```

#### 예제: 회원 이름과 주문 금액 조회

```sql
SELECT m.name, o.price
FROM member m
INNER JOIN orders o
ON m.id = o.member_id;
```

**설명:**
- `m`: member 테이블의 별칭
- `o`: orders 테이블의 별칭
- `ON m.id = o.member_id`: 연결 조건

**실행 결과:**
```
+--------+--------+
| name   | price  |
+--------+--------+
| 홍길동   |  50000 |
| 홍길동   |  30000 |
| 김영희   |  80000 |
| 김영희   |  20000 |
| 김영희   |  50000 |
| 박철수   | 100000 |
| 박철수   |  40000 |
+--------+--------+
7 rows in set (0.00 sec)
```

**특징:**
- ✅ 주문한 회원만 조회됨
- ✅ 이미영(id=4)은 주문이 없어서 조회되지 않음
- ✅ 주문이 여러 건인 회원은 여러 행으로 표시됨

#### INNER JOIN 동작 원리

```
member 테이블          orders 테이블
+----+--------+        +----------+-----------+
| id | name   |        | member_id | price    |
+----+--------+        +----------+-----------+
|  1 | 홍길동   |  ←→   |     1     |  50000   |
|  2 | 김영희   |  ←→   |     1     |  30000   |
|  3 | 박철수   |  ←→   |     2     |  80000   |
|  4 | 이미영   |        |     2     |  20000   |
+----+--------+        |     2     |  50000   |
                        |     3     | 100000   |
                        |     3     |  40000   |
                        +----------+-----------+

INNER JOIN 결과: 양쪽에 모두 있는 데이터만
```

### 3-4. LEFT JOIN

#### 기본 문법

```sql
SELECT 컬럼1, 컬럼2, ...
FROM 테이블1 [별칭1]
LEFT JOIN 테이블2 [별칭2]
ON 별칭1.공통컬럼 = 별칭2.공통컬럼;
```

#### 예제: 모든 회원과 주문 정보 조회

```sql
SELECT m.name, o.price
FROM member m
LEFT JOIN orders o
ON m.id = o.member_id;
```

**실행 결과:**
```
+--------+--------+
| name   | price  |
+--------+--------+
| 홍길동   |  50000 |
| 홍길동   |  30000 |
| 김영희   |  80000 |
| 김영희   |  20000 |
| 김영희   |  50000 |
| 박철수   | 100000 |
| 박철수   |  40000 |
| 이미영   |   NULL |
+--------+--------+
8 rows in set (0.00 sec)
```

**특징:**
- ✅ 주문이 없는 회원도 조회됨
- ✅ 이미영은 주문이 없어서 `price`가 `NULL`로 표시됨
- ✅ 왼쪽 테이블(member)의 모든 데이터가 포함됨

#### LEFT JOIN 동작 원리

```
member 테이블 (왼쪽)    orders 테이블 (오른쪽)
+----+--------+        +----------+-----------+
| id | name   |        | member_id | price    |
+----+--------+        +----------+-----------+
|  1 | 홍길동   |  ←→   |     1     |  50000   |
|  2 | 김영희   |  ←→   |     1     |  30000   |
|  3 | 박철수   |  ←→   |     2     |  80000   |
|  4 | 이미영   |  ✗    |     2     |  20000   |
+----+--------+        |     2     |  50000   |
                        |     3     | 100000   |
                        |     3     |  40000   |
                        +----------+-----------+

LEFT JOIN 결과: 왼쪽 테이블의 모든 데이터 + 매칭되는 오른쪽 데이터
```

### 3-5. RIGHT JOIN

#### 기본 문법

```sql
SELECT 컬럼1, 컬럼2, ...
FROM 테이블1 [별칭1]
RIGHT JOIN 테이블2 [별칭2]
ON 별칭1.공통컬럼 = 별칭2.공통컬럼;
```

#### 예제: 모든 주문과 회원 정보 조회

```sql
SELECT m.name, o.price
FROM member m
RIGHT JOIN orders o
ON m.id = o.member_id;
```

**실행 결과:**
```
+--------+--------+
| name   | price  |
+--------+--------+
| 홍길동   |  50000 |
| 홍길동   |  30000 |
| 김영희   |  80000 |
| 김영희   |  20000 |
| 김영희   |  50000 |
| 박철수   | 100000 |
| 박철수   |  40000 |
+--------+--------+
7 rows in set (0.00 sec)
```

**특징:**
- ✅ 오른쪽 테이블(orders)의 모든 데이터가 포함됨
- ✅ 주문이 없는 회원은 조회되지 않음
- ✅ LEFT JOIN과 반대 개념

### 3-6. JOIN 비교 정리

| 구분 | 설명 | 결과 |
|------|------|------|
| **INNER JOIN** | 양쪽에 데이터 있는 경우만 | 주문한 회원만 조회 |
| **LEFT JOIN** | 왼쪽 테이블은 모두 표시 | 모든 회원 조회 (주문 없으면 NULL) |
| **RIGHT JOIN** | 오른쪽 테이블은 모두 표시 | 모든 주문 조회 (회원 없으면 NULL) |

### 3-7. 여러 컬럼 조회 예제

```sql
SELECT m.id, m.name, m.age, o.order_id, o.price, o.order_date
FROM member m
INNER JOIN orders o
ON m.id = o.member_id;
```

**실행 결과:**
```
+----+--------+------+----------+--------+------------+
| id | name   | age  | order_id | price  | order_date |
+----+--------+------+----------+--------+------------+
|  1 | 홍길동   |   25 |        1 |  50000 | 2024-01-15 |
|  1 | 홍길동   |   25 |        2 |  30000 | 2024-01-20 |
|  2 | 김영희   |   30 |        3 |  80000 | 2024-01-18 |
|  2 | 김영희   |   30 |        4 |  20000 | 2024-01-25 |
|  2 | 김영희   |   30 |        5 |  50000 | 2024-02-01 |
|  3 | 박철수   |   28 |        6 | 100000 | 2024-01-22 |
|  3 | 박철수   |   28 |        7 |  40000 | 2024-02-05 |
+----+--------+------+----------+--------+------------+
7 rows in set (0.00 sec)
```

---

## 4. GROUP BY (그룹화)

### 4-1. GROUP BY란?

**GROUP BY**는 **같은 값을 가진 행을 하나의 그룹으로 묶는** 기능입니다.

- 집계 함수(COUNT, SUM, AVG, MAX, MIN)와 함께 사용
- 데이터를 그룹별로 분석할 때 사용
- 예: 회원별 주문 총액, 부서별 평균 급여 등

### 4-2. 집계 함수와 GROUP BY

#### 집계 함수만 사용 (전체 집계)

```sql
SELECT SUM(price) AS total_price
FROM orders;
```

**실행 결과:**
```
+-------------+
| total_price |
+-------------+
|      370000 |
+-------------+
1 row in set (0.00 sec)
```

**설명:** 모든 주문의 총 금액

#### GROUP BY 사용 (그룹별 집계)

```sql
SELECT member_id, SUM(price) AS total_price
FROM orders
GROUP BY member_id;
```

**실행 결과:**
```
+-----------+-------------+
| member_id | total_price |
+-----------+-------------+
|         1 |       80000 |
|         2 |      150000 |
|         3 |      140000 |
+-----------+-------------+
3 rows in set (0.00 sec)
```

**설명:** 회원별 주문 총액

### 4-3. 회원별 주문 총액

```sql
SELECT member_id, SUM(price) AS total_price
FROM orders
GROUP BY member_id;
```

**실행 결과:**
```
+-----------+-------------+
| member_id | total_price |
+-----------+-------------+
|         1 |       80000 |
|         2 |      150000 |
|         3 |      140000 |
+-----------+-------------+
3 rows in set (0.00 sec)
```

**동작 원리:**
```
원본 데이터:
member_id | price
----------+-------
    1     | 50000
    1     | 30000  ← 그룹 1
    2     | 80000
    2     | 20000  ← 그룹 2
    2     | 50000
    3     | 100000 ← 그룹 3
    3     | 40000

GROUP BY member_id 후:
member_id | SUM(price)
----------+-----------
    1     |   80000   (50000 + 30000)
    2     |  150000   (80000 + 20000 + 50000)
    3     |  140000   (100000 + 40000)
```

### 4-4. 회원별 주문 횟수

```sql
SELECT member_id, COUNT(*) AS order_count
FROM orders
GROUP BY member_id;
```

**실행 결과:**
```
+-----------+-------------+
| member_id | order_count |
+-----------+-------------+
|         1 |           2 |
|         2 |           3 |
|         3 |           2 |
+-----------+-------------+
3 rows in set (0.00 sec)
```

**설명:**
- `COUNT(*)`: 각 그룹의 행 개수
- 홍길동: 주문 2건
- 김영희: 주문 3건
- 박철수: 주문 2건

### 4-5. 회원별 평균 주문 금액

```sql
SELECT member_id, AVG(price) AS avg_price
FROM orders
GROUP BY member_id;
```

**실행 결과:**
```
+-----------+-----------+
| member_id | avg_price |
+-----------+-----------+
|         1 |  40000.00 |
|         2 |  50000.00 |
|         3 |  70000.00 |
+-----------+-----------+
3 rows in set (0.00 sec)
```

**설명:**
- 홍길동: 평균 40,000원 (80,000 / 2)
- 김영희: 평균 50,000원 (150,000 / 3)
- 박철수: 평균 70,000원 (140,000 / 2)

### 4-6. 회원별 최대/최소 주문 금액

```sql
SELECT 
    member_id,
    MAX(price) AS max_price,
    MIN(price) AS min_price
FROM orders
GROUP BY member_id;
```

**실행 결과:**
```
+-----------+-----------+-----------+
| member_id | max_price | min_price |
+-----------+-----------+-----------+
|         1 |     50000 |     30000 |
|         2 |     80000 |     20000 |
|         3 |    100000 |     40000 |
+-----------+-----------+-----------+
3 rows in set (0.00 sec)
```

### 4-7. 여러 집계 함수 함께 사용

```sql
SELECT 
    member_id,
    COUNT(*) AS order_count,
    SUM(price) AS total_price,
    AVG(price) AS avg_price,
    MAX(price) AS max_price,
    MIN(price) AS min_price
FROM orders
GROUP BY member_id;
```

**실행 결과:**
```
+-----------+-------------+-------------+-----------+-----------+-----------+
| member_id | order_count | total_price | avg_price | max_price | min_price |
+-----------+-------------+-------------+-----------+-----------+-----------+
|         1 |           2 |       80000 |  40000.00 |     50000 |     30000 |
|         2 |           3 |      150000 |  50000.00 |     80000 |     20000 |
|         3 |           2 |      140000 |  70000.00 |    100000 |     40000 |
+-----------+-------------+-------------+-----------+-----------+-----------+
3 rows in set (0.00 sec)
```

### 4-8. GROUP BY 주의사항

#### ❌ 잘못된 사용

```sql
-- SELECT에 집계 함수가 아닌 컬럼을 사용하면 오류 발생
SELECT member_id, name, SUM(price)
FROM orders
GROUP BY member_id;
```

**오류 메시지:**
```
ERROR 1055 (42000): Expression #2 of SELECT list is not in GROUP BY clause
```

**이유:** `name` 컬럼이 GROUP BY에 없고 집계 함수도 아님

#### ✅ 올바른 사용

```sql
-- 방법 1: GROUP BY에 포함
SELECT member_id, name, SUM(price)
FROM orders o
INNER JOIN member m ON o.member_id = m.id
GROUP BY member_id, name;

-- 방법 2: 집계 함수 사용
SELECT member_id, COUNT(*) AS order_count, SUM(price)
FROM orders
GROUP BY member_id;
```

---

## 5. HAVING (그룹 조건)

### 5-1. WHERE vs HAVING

| 구분 | 사용 위치 | 대상 | 집계 함수 사용 |
|------|----------|------|---------------|
| **WHERE** | GROUP BY 이전 | 개별 행 | ❌ 불가 |
| **HAVING** | GROUP BY 이후 | 그룹 | ✅ 가능 |

### 5-2. WHERE 사용 예제

```sql
-- 주문 금액이 50000원 이상인 주문만 그룹화
SELECT member_id, SUM(price) AS total_price
FROM orders
WHERE price >= 50000
GROUP BY member_id;
```

**실행 결과:**
```
+-----------+-------------+
| member_id | total_price |
+-----------+-------------+
|         1 |       50000 |
|         2 |      130000 |
|         3 |      140000 |
+-----------+-------------+
3 rows in set (0.00 sec)
```

**설명:**
- `WHERE price >= 50000`: 그룹화 전에 필터링
- 50000원 미만 주문 제외 후 그룹화

### 5-3. HAVING 사용 예제

#### 총 주문금액이 100000원 이상인 회원

```sql
SELECT member_id, SUM(price) AS total_price
FROM orders
GROUP BY member_id
HAVING SUM(price) >= 100000;
```

**실행 결과:**
```
+-----------+-------------+
| member_id | total_price |
+-----------+-------------+
|         2 |      150000 |
|         3 |      140000 |
+-----------+-------------+
2 rows in set (0.00 sec)
```

**설명:**
- `HAVING SUM(price) >= 100000`: 그룹화 후에 필터링
- 총 주문금액이 10만원 이상인 회원만 조회

#### ❌ WHERE에 집계 함수 사용 (오류)

```sql
-- 오류 발생!
SELECT member_id, SUM(price) AS total_price
FROM orders
WHERE SUM(price) >= 100000
GROUP BY member_id;
```

**오류 메시지:**
```
ERROR 1111 (HY000): Invalid use of group function
```

**이유:** WHERE는 개별 행을 필터링하는데, 집계 함수는 그룹 전체를 대상으로 함

### 5-4. WHERE와 HAVING 함께 사용

```sql
SELECT member_id, SUM(price) AS total_price
FROM orders
WHERE price >= 30000  -- 개별 주문 필터링
GROUP BY member_id
HAVING SUM(price) >= 100000;  -- 그룹 필터링
```

**실행 결과:**
```
+-----------+-------------+
| member_id | total_price |
+-----------+-------------+
|         2 |      130000 |
|         3 |      140000 |
+-----------+-------------+
2 rows in set (0.00 sec)
```

**동작 순서:**
1. `WHERE price >= 30000`: 30000원 미만 주문 제외
2. `GROUP BY member_id`: 회원별로 그룹화
3. `HAVING SUM(price) >= 100000`: 총액 10만원 이상인 그룹만 선택

### 5-5. 주문 횟수가 3회 이상인 회원

```sql
SELECT member_id, COUNT(*) AS order_count
FROM orders
GROUP BY member_id
HAVING COUNT(*) >= 3;
```

**실행 결과:**
```
+-----------+-------------+
| member_id | order_count |
+-----------+-------------+
|         2 |           3 |
+-----------+-------------+
1 row in set (0.00 sec)
```

**설명:** 주문 횟수가 3회 이상인 회원은 김영희(member_id=2)만

### 5-6. 평균 주문금액이 50000원 이상인 회원

```sql
SELECT member_id, AVG(price) AS avg_price
FROM orders
GROUP BY member_id
HAVING AVG(price) >= 50000;
```

**실행 결과:**
```
+-----------+-----------+
| member_id | avg_price |
+-----------+-----------+
|         2 |  50000.00 |
|         3 |  70000.00 |
+-----------+-----------+
2 rows in set (0.00 sec)
```

---

## 6. JOIN + GROUP BY + HAVING 종합 예제

### 6-1. 회원 이름과 총 주문금액 조회 (10만원 이상)

```sql
SELECT m.name, SUM(o.price) AS total_price
FROM member m
INNER JOIN orders o
ON m.id = o.member_id
GROUP BY m.name
HAVING SUM(o.price) >= 100000;
```

**실행 결과:**
```
+--------+-------------+
| name   | total_price |
+--------+-------------+
| 김영희   |      150000 |
| 박철수   |      140000 |
+--------+-------------+
2 rows in set (0.00 sec)
```

**설명:**
1. `FROM member m INNER JOIN orders o`: 두 테이블 연결
2. `ON m.id = o.member_id`: 연결 조건
3. `GROUP BY m.name`: 회원 이름별로 그룹화
4. `HAVING SUM(o.price) >= 100000`: 총 주문금액 10만원 이상인 그룹만 선택

### 6-2. 주문이 없는 회원도 포함하여 회원별 총 주문금액 조회

```sql
SELECT m.name, COALESCE(SUM(o.price), 0) AS total_price
FROM member m
LEFT JOIN orders o
ON m.id = o.member_id
GROUP BY m.name;
```

**실행 결과:**
```
+--------+-------------+
| name   | total_price |
+--------+-------------+
| 홍길동   |       80000 |
| 김영희   |      150000 |
| 박철수   |      140000 |
| 이미영   |           0 |
+--------+-------------+
4 rows in set (0.00 sec)
```

**설명:**
- `LEFT JOIN`: 주문이 없는 회원도 포함
- `COALESCE(SUM(o.price), 0)`: NULL을 0으로 변환
- 이미영은 주문이 없어서 0원으로 표시

### 6-3. 회원 이름, 주문 횟수, 총 주문금액 조회 (주문 2회 이상)

```sql
SELECT 
    m.name,
    COUNT(*) AS order_count,
    SUM(o.price) AS total_price
FROM member m
INNER JOIN orders o
ON m.id = o.member_id
GROUP BY m.name
HAVING COUNT(*) >= 2;
```

**실행 결과:**
```
+--------+-------------+-------------+
| name   | order_count | total_price |
+--------+-------------+-------------+
| 홍길동   |           2 |       80000 |
| 김영희   |           3 |      150000 |
| 박철수   |           2 |      140000 |
+--------+-------------+-------------+
3 rows in set (0.00 sec)
```

### 6-4. 회원별 평균 주문금액 조회 (평균 5만원 이상, 이름순 정렬)

```sql
SELECT 
    m.name,
    AVG(o.price) AS avg_price
FROM member m
INNER JOIN orders o
ON m.id = o.member_id
GROUP BY m.name
HAVING AVG(o.price) >= 50000
ORDER BY m.name;
```

**실행 결과:**
```
+--------+-----------+
| name   | avg_price |
+--------+-----------+
| 김영희   |  50000.00 |
| 박철수   |  70000.00 |
+--------+-----------+
2 rows in set (0.00 sec)
```

### 6-5. 총 주문금액이 가장 큰 회원 조회

```sql
SELECT 
    m.name,
    SUM(o.price) AS total_price
FROM member m
INNER JOIN orders o
ON m.id = o.member_id
GROUP BY m.name
ORDER BY total_price DESC
LIMIT 1;
```

**실행 결과:**
```
+--------+-------------+
| name   | total_price |
+--------+-------------+
| 김영희   |      150000 |
+--------+-------------+
1 row in set (0.00 sec)
```

**설명:**
- `ORDER BY total_price DESC`: 총 주문금액 내림차순 정렬
- `LIMIT 1`: 상위 1개만 조회

---

## 7. SQL 실행 순서 (중요 ⭐)

### 7-1. 실행 순서

```text
FROM → JOIN → WHERE → GROUP BY → HAVING → SELECT → ORDER BY → LIMIT
```

### 7-2. 단계별 설명

| 순서 | 키워드 | 설명 | 예시 |
|------|--------|------|------|
| 1 | **FROM** | 테이블 선택 | `FROM member m` |
| 2 | **JOIN** | 테이블 연결 | `INNER JOIN orders o ON m.id = o.member_id` |
| 3 | **WHERE** | 개별 행 필터링 | `WHERE o.price >= 30000` |
| 4 | **GROUP BY** | 그룹화 | `GROUP BY m.name` |
| 5 | **HAVING** | 그룹 필터링 | `HAVING SUM(o.price) >= 100000` |
| 6 | **SELECT** | 컬럼 선택 | `SELECT m.name, SUM(o.price)` |
| 7 | **ORDER BY** | 정렬 | `ORDER BY total_price DESC` |
| 8 | **LIMIT** | 개수 제한 | `LIMIT 10` |

### 7-3. 실행 순서 예제

```sql
SELECT 
    m.name,
    SUM(o.price) AS total_price
FROM member m
INNER JOIN orders o
ON m.id = o.member_id
WHERE o.price >= 30000
GROUP BY m.name
HAVING SUM(o.price) >= 100000
ORDER BY total_price DESC
LIMIT 5;
```

**실행 과정:**

1. **FROM member m**: member 테이블 선택
2. **INNER JOIN orders o ON m.id = o.member_id**: orders 테이블 연결
3. **WHERE o.price >= 30000**: 30000원 미만 주문 제외
   ```
   제외된 주문: member_id=2, price=20000
   ```
4. **GROUP BY m.name**: 회원 이름별로 그룹화
5. **HAVING SUM(o.price) >= 100000**: 총액 10만원 이상인 그룹만 선택
6. **SELECT m.name, SUM(o.price)**: 컬럼 선택
7. **ORDER BY total_price DESC**: 총액 내림차순 정렬
8. **LIMIT 5**: 상위 5개만 조회

### 7-4. 실행 순서를 이해해야 하는 이유

#### ❌ 잘못된 사용

```sql
-- SELECT에서 별칭을 WHERE에서 사용 (오류)
SELECT m.name, SUM(o.price) AS total_price
FROM member m
INNER JOIN orders o ON m.id = o.member_id
WHERE total_price >= 100000  -- 오류!
GROUP BY m.name;
```

**오류 이유:** WHERE는 SELECT보다 먼저 실행되므로 `total_price` 별칭을 사용할 수 없음

#### ✅ 올바른 사용

```sql
-- 방법 1: HAVING 사용
SELECT m.name, SUM(o.price) AS total_price
FROM member m
INNER JOIN orders o ON m.id = o.member_id
GROUP BY m.name
HAVING SUM(o.price) >= 100000;

-- 방법 2: WHERE에서 원본 컬럼 사용
SELECT m.name, SUM(o.price) AS total_price
FROM member m
INNER JOIN orders o ON m.id = o.member_id
WHERE o.price >= 50000  -- 개별 행 필터링
GROUP BY m.name
HAVING SUM(o.price) >= 100000;  -- 그룹 필터링
```

---

## 8. 실습 문제

### 문제 1: 회원별 주문 총액 구하기

**요구사항:**
회원 ID와 회원별 주문 총액을 조회하세요.

**정답:**

```sql
SELECT member_id, SUM(price) AS total_price
FROM orders
GROUP BY member_id;
```

**실행 결과:**
```
+-----------+-------------+
| member_id | total_price |
+-----------+-------------+
|         1 |       80000 |
|         2 |      150000 |
|         3 |      140000 |
+-----------+-------------+
3 rows in set (0.00 sec)
```

---

### 문제 2: 주문이 없는 회원도 포함하여 이름 조회하기

**요구사항:**
모든 회원의 이름을 조회하되, 주문이 없는 회원도 포함하세요.

**정답:**

```sql
SELECT m.name
FROM member m
LEFT JOIN orders o
ON m.id = o.member_id
GROUP BY m.name;
```

또는 (주문 정보가 필요 없는 경우)

```sql
SELECT name FROM member;
```

**실행 결과:**
```
+--------+
| name   |
+--------+
| 홍길동   |
| 김영희   |
| 박철수   |
| 이미영   |
+--------+
4 rows in set (0.00 sec)
```

---

### 문제 3: 주문 횟수가 3회 이상인 회원 조회하기

**요구사항:**
주문 횟수가 3회 이상인 회원의 ID와 주문 횟수를 조회하세요.

**정답:**

```sql
SELECT member_id, COUNT(*) AS order_count
FROM orders
GROUP BY member_id
HAVING COUNT(*) >= 3;
```

**실행 결과:**
```
+-----------+-------------+
| member_id | order_count |
+-----------+-------------+
|         2 |           3 |
+-----------+-------------+
1 row in set (0.00 sec)
```

---

### 문제 4: 회원 이름별 평균 주문금액 구하기

**요구사항:**
회원 이름과 평균 주문금액을 조회하세요.

**정답:**

```sql
SELECT m.name, AVG(o.price) AS avg_price
FROM member m
INNER JOIN orders o
ON m.id = o.member_id
GROUP BY m.name;
```

**실행 결과:**
```
+--------+-----------+
| name   | avg_price |
+--------+-----------+
| 홍길동   |  40000.00 |
| 김영희   |  50000.00 |
| 박철수   |  70000.00 |
+--------+-----------+
3 rows in set (0.00 sec)
```

---

### 문제 5: 총 주문금액이 가장 큰 회원 조회하기

**요구사항:**
총 주문금액이 가장 큰 회원의 이름과 총 주문금액을 조회하세요.

**정답:**

```sql
SELECT m.name, SUM(o.price) AS total_price
FROM member m
INNER JOIN orders o
ON m.id = o.member_id
GROUP BY m.name
ORDER BY total_price DESC
LIMIT 1;
```

**실행 결과:**
```
+--------+-------------+
| name   | total_price |
+--------+-------------+
| 김영희   |      150000 |
+--------+-------------+
1 row in set (0.00 sec)
```

---

### 문제 6: 회원 이름, 주문 횟수, 총 주문금액 조회하기 (주문 2회 이상)

**요구사항:**
주문 횟수가 2회 이상인 회원의 이름, 주문 횟수, 총 주문금액을 조회하세요.

**정답:**

```sql
SELECT 
    m.name,
    COUNT(*) AS order_count,
    SUM(o.price) AS total_price
FROM member m
INNER JOIN orders o
ON m.id = o.member_id
GROUP BY m.name
HAVING COUNT(*) >= 2;
```

**실행 결과:**
```
+--------+-------------+-------------+
| name   | order_count | total_price |
+--------+-------------+-------------+
| 홍길동   |           2 |       80000 |
| 김영희   |           3 |      150000 |
| 박철수   |           2 |      140000 |
+--------+-------------+-------------+
3 rows in set (0.00 sec)
```

---

### 문제 7: 평균 주문금액이 5만원 이상인 회원 조회하기 (이름순 정렬)

**요구사항:**
평균 주문금액이 5만원 이상인 회원의 이름과 평균 주문금액을 이름순으로 조회하세요.

**정답:**

```sql
SELECT 
    m.name,
    AVG(o.price) AS avg_price
FROM member m
INNER JOIN orders o
ON m.id = o.member_id
GROUP BY m.name
HAVING AVG(o.price) >= 50000
ORDER BY m.name;
```

**실행 결과:**
```
+--------+-----------+
| name   | avg_price |
+--------+-----------+
| 김영희   |  50000.00 |
| 박철수   |  70000.00 |
+--------+-----------+
2 rows in set (0.00 sec)
```

---

## 9. 자주 나오는 실수

### 9-1. ❌ WHERE에 집계 함수 사용

```sql
-- 오류 발생!
SELECT member_id, SUM(price)
FROM orders
WHERE SUM(price) >= 100000
GROUP BY member_id;
```

**오류 메시지:**
```
ERROR 1111 (HY000): Invalid use of group function
```

**해결 방법:**
```sql
-- ✅ HAVING 사용
SELECT member_id, SUM(price)
FROM orders
GROUP BY member_id
HAVING SUM(price) >= 100000;
```

---

### 9-2. ❌ GROUP BY 없이 집계 함수 사용

```sql
-- 오류 발생! (일부 DB에서는 가능하지만 MySQL에서는 오류)
SELECT member_id, SUM(price)
FROM orders;
```

**오류 메시지:**
```
ERROR 1140 (42000): In aggregated query without GROUP BY, expression #1 of SELECT list contains nonaggregated column 'orders.member_id'
```

**해결 방법:**
```sql
-- ✅ GROUP BY 추가
SELECT member_id, SUM(price)
FROM orders
GROUP BY member_id;
```

---

### 9-3. ❌ JOIN 조건 누락 (카티션 곱 발생)

```sql
-- 위험! 모든 조합이 생성됨
SELECT m.name, o.price
FROM member m, orders o;
```

**결과:** 4명 × 7건 = 28행 (모든 조합)

**해결 방법:**
```sql
-- ✅ JOIN 조건 명시
SELECT m.name, o.price
FROM member m
INNER JOIN orders o
ON m.id = o.member_id;
```

---

### 9-4. ❌ SELECT에 GROUP BY 없는 컬럼 사용

```sql
-- 오류 발생!
SELECT member_id, name, SUM(price)
FROM orders o
INNER JOIN member m ON o.member_id = m.id
GROUP BY member_id;
```

**오류 메시지:**
```
ERROR 1055 (42000): Expression #2 of SELECT list is not in GROUP BY clause
```

**해결 방법:**
```sql
-- ✅ GROUP BY에 name 추가
SELECT member_id, name, SUM(price)
FROM orders o
INNER JOIN member m ON o.member_id = m.id
GROUP BY member_id, name;
```

---

### 9-5. ❌ HAVING에서 별칭 사용 (일부 DB에서만 가능)

```sql
-- MySQL에서는 가능하지만, 표준 SQL에서는 권장하지 않음
SELECT member_id, SUM(price) AS total_price
FROM orders
GROUP BY member_id
HAVING total_price >= 100000;
```

**권장 방법:**
```sql
-- ✅ 집계 함수 직접 사용
SELECT member_id, SUM(price) AS total_price
FROM orders
GROUP BY member_id
HAVING SUM(price) >= 100000;
```

---

## 10. 다음 학습 로드맵

### 10-1. 중급 SQL

- **서브쿼리 (Subquery)**: 쿼리 안의 쿼리
- **CASE WHEN**: 조건부 로직
- **윈도우 함수 (Window Function)**: ROW_NUMBER(), RANK() 등
- **UNION / UNION ALL**: 여러 쿼리 결과 합치기

### 10-2. 고급 SQL

- **인덱스 (Index)**: 검색 속도 향상
- **트랜잭션 (Transaction)**: 데이터 일관성 보장
- **뷰 (View)**: 가상 테이블
- **프로시저 (Procedure)**: 저장된 SQL 로직
- **트리거 (Trigger)**: 자동 실행 SQL

### 10-3. 성능 튜닝

- **EXPLAIN**: 쿼리 실행 계획 분석
- **인덱스 최적화**: 적절한 인덱스 설계
- **쿼리 최적화**: 효율적인 쿼리 작성

### 10-4. 자격증 및 실전

- **SQLD (SQL Developer)**: SQL 개발자 자격증
- **SQLP (SQL Professional)**: SQL 전문가 자격증
- **실전 문제 풀이**: 다양한 쿼리 패턴 연습

### 10-5. 프로그래밍 연동

- **Java + JDBC**: Java에서 MySQL 사용
- **Spring Boot + JPA**: ORM 프레임워크
- **QueryDSL**: 타입 안전한 쿼리 빌더
- **MyBatis**: SQL 매퍼 프레임워크

---

## 11. 핵심 포인트 정리

### 11-1. JOIN 요약

| JOIN 종류 | 사용 시기 | 결과 |
|----------|----------|------|
| **INNER JOIN** | 양쪽 데이터 모두 필요할 때 | 교집합 |
| **LEFT JOIN** | 왼쪽 데이터 모두 필요할 때 | 왼쪽 전체 + 오른쪽 매칭 |
| **RIGHT JOIN** | 오른쪽 데이터 모두 필요할 때 | 오른쪽 전체 + 왼쪽 매칭 |

### 11-2. GROUP BY 요약

- **목적**: 같은 값을 가진 행을 그룹으로 묶기
- **필수**: 집계 함수와 함께 사용
- **주의**: SELECT에 집계 함수가 아닌 컬럼은 GROUP BY에 포함

### 11-3. HAVING vs WHERE

| 구분 | WHERE | HAVING |
|------|-------|--------|
| **위치** | GROUP BY 이전 | GROUP BY 이후 |
| **대상** | 개별 행 | 그룹 |
| **집계 함수** | ❌ 사용 불가 | ✅ 사용 가능 |

### 11-4. SQL 실행 순서 암기

```
FROM → JOIN → WHERE → GROUP BY → HAVING → SELECT → ORDER BY → LIMIT
```

**암기 팁:**
- "FROM에서 시작해서 JOIN하고, WHERE로 필터링하고, GROUP BY로 묶고, HAVING으로 그룹 필터링하고, SELECT로 선택하고, ORDER BY로 정렬하고, LIMIT로 제한한다"

---

## 📌 TIP

1. **JOIN 먼저, GROUP BY는 그 다음**
   - JOIN으로 테이블을 연결한 후 GROUP BY로 그룹화

2. **HAVING은 "그룹의 조건"이라고 생각하기**
   - WHERE: 개별 행의 조건
   - HAVING: 그룹 전체의 조건

3. **집계 함수는 GROUP BY와 함께 사용**
   - GROUP BY 없이 집계 함수 사용 시 오류 발생 가능

4. **JOIN 조건은 항상 명시하기**
   - 조건 없으면 카티션 곱 발생 (모든 조합 생성)

5. **실행 순서를 이해하면 쿼리 작성이 쉬워짐**
   - FROM → JOIN → WHERE → GROUP BY → HAVING → SELECT → ORDER BY → LIMIT

---

**작성일:** 2024년  
**버전:** 1.0
