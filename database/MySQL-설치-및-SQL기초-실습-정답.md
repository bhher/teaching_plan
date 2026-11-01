# MySQL 설치 및 SQL 기초 — 정답

문제: [MySQL-설치-및-SQL기초-실습-문제.md](./MySQL-설치-및-SQL기초-실습-문제.md)  
교안: [MySQL-설치-및-SQL기초-실습-교안.md](./MySQL-설치-및-SQL기초-실습-교안.md)  
**작성일:** 2026-09-15

> 실습 DB: **`bookstore`** / 테이블: **`book`**  
> 종합(32번): **`cafe`** / **`menu`**

---

# Part 1

## 1번
② 관계형 데이터베이스 관리 시스템(RDBMS)이다

## 2번
② Server = 데이터베이스 서버, Workbench = GUI

## 3번
② `3306`

## 4번
③ Row

## 5번 (예시)
엑셀 파일(Database) 안에 시트(Table)가 있고, 한 줄이 Row, 열 제목이 Column이다.

## 6번
② `services.msc`

## 7번
① `SELECT 1;`

---

# Part 2

## 8번
② SELECT

## 9번

```sql
CREATE DATABASE bookstore;
```

## 10번

```sql
USE bookstore;
```

## 11번
② `SELECT DATABASE();`

## 12번

```sql
CREATE TABLE book (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100),
    author VARCHAR(50),
    price INT,
    stock INT
);
```

## 13번
② 행을 구분하는 고유한 값이다

## 14번
② 숫자를 자동으로 증가시켜 부여한다

## 15번
② `DESC book;`

---

# Part 3

## 16번

```sql
INSERT INTO book(title, author, price, stock)
VALUES ('자바의 정석', '남궁성', 30000, 10);
```

## 17번

```sql
INSERT INTO book(title, author, price, stock)
VALUES
('혼자 공부하는 SQL', '우재남', 24000, 15),
('Do it! 자바스크립트', '고경희', 22000, 8),
('스프링 부트와 AWS', '이동욱', 28000, 12);
```

## 18번

```sql
SELECT * FROM book;
```

## 19번

```sql
SELECT title, price
FROM book;
```

## 20번

```sql
SELECT *
FROM book
WHERE price = 24000;
```

## 21번

```sql
-- OR
SELECT *
FROM book
WHERE price = 22000
OR price = 30000;

-- 또는 IN
SELECT *
FROM book
WHERE price IN (22000, 30000);
```

## 22번

```sql
SELECT *
FROM book
WHERE price BETWEEN 22000 AND 28000;
```

## 23번

```sql
SELECT *
FROM book
WHERE title LIKE '혼자%';
```

## 24번

```sql
SELECT *
FROM book
WHERE title LIKE '%자바%';
```

## 25번

```sql
SELECT *
FROM book
ORDER BY price DESC;
```

## 26번

```sql
SELECT *
FROM book
ORDER BY price DESC
LIMIT 2;
```

---

# Part 4

## 27번

```sql
UPDATE book
SET stock = 9
WHERE title = '자바의 정석';
```

## 28번
② WHERE가 없어 모든 행의 가격이 바뀔 수 있어서

## 29번

```sql
DELETE FROM book
WHERE title = 'Do it! 자바스크립트';
```

## 30번
③ Update → **UPDATE** (ALTER는 테이블 구조 변경)

## 31번 (예시)
게시판의 글쓰기·목록·수정·삭제가 각각 INSERT·SELECT·UPDATE·DELETE에 대응한다.  
백엔드 API도 결국 이 네 가지 SQL을 실행하는 경우가 많다.  
그래서 CRUD를 먼저 익히면 Spring Boot 연동이 수월하다.

## 32번 (예시) — cafe / menu

```sql
DROP DATABASE IF EXISTS cafe;
CREATE DATABASE cafe;
USE cafe;

CREATE TABLE menu (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    price INT,
    category VARCHAR(30)
);

INSERT INTO menu(name, price, category)
VALUES
('아메리카노', 4000, '커피'),
('카페라떼', 4500, '커피'),
('치즈케이크', 5500, '디저트'),
('크로와상', 3500, '디저트');

SELECT *
FROM menu
WHERE price >= 4000;

SELECT *
FROM menu
ORDER BY price DESC
LIMIT 2;

SELECT *
FROM menu
WHERE category = '커피';
-- 또는: WHERE category LIKE '커피%';

UPDATE menu
SET price = 4200
WHERE name = '아메리카노';

-- DELETE FROM menu WHERE name = '크로와상';
```

---

# 한 번에 따라하기 (bookstore 복습용)

```sql
DROP DATABASE IF EXISTS bookstore;
CREATE DATABASE bookstore;
USE bookstore;

CREATE TABLE book (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100),
    author VARCHAR(50),
    price INT,
    stock INT
);

INSERT INTO book(title, author, price, stock)
VALUES
('자바의 정석', '남궁성', 30000, 10),
('혼자 공부하는 SQL', '우재남', 24000, 15),
('Do it! 자바스크립트', '고경희', 22000, 8),
('스프링 부트와 AWS', '이동욱', 28000, 12);

SELECT * FROM book;
SELECT title, price FROM book WHERE price >= 24000;
SELECT * FROM book WHERE title LIKE '%자바%';
SELECT * FROM book ORDER BY price DESC LIMIT 2;

UPDATE book SET stock = 9 WHERE title = '자바의 정석';
-- DELETE FROM book WHERE title = 'Do it! 자바스크립트';
```

---

# 강사 채점 팁

| 문항 | 포인트 |
|------|--------|
| 9~12 | `bookstore` / `book` (교안 shop/member와 구분) |
| 23~24 | LIKE `%` 위치 (`혼자%`, `%자바%`) |
| 27~29 | **WHERE 필수** |
| 32 | `cafe`·`menu` 사용 여부, category 조건 |

부분 점수: SQL이 동작하면 만점, WHERE 누락 시 UPDATE/DELETE는 **0점 처리** 권장.
