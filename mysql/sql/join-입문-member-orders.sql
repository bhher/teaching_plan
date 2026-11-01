-- JOIN 입문용 DB (member + orders + product)
-- 실행 예:
--   mysql -u root -p --default-character-set=utf8mb4 < join-입문-member-orders.sql

CREATE DATABASE IF NOT EXISTS join_intro
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE join_intro;

DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS member;

CREATE TABLE member (
  member_id INT PRIMARY KEY,
  name      VARCHAR(20) NOT NULL,
  age       INT NOT NULL
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE product (
  product_id   INT PRIMARY KEY,
  product_name VARCHAR(50) NOT NULL,
  price        INT NOT NULL
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE orders (
  order_id   INT PRIMARY KEY,
  member_id  INT NOT NULL,
  product_id INT NOT NULL,
  product    VARCHAR(50) NOT NULL,
  price      INT NOT NULL,
  FOREIGN KEY (member_id) REFERENCES member(member_id),
  FOREIGN KEY (product_id) REFERENCES product(product_id)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

INSERT INTO member VALUES
(1, '홍길동', 25),
(2, '김철수', 30),
(3, '이영희', 28),
(4, '박민수', 35);

INSERT INTO product VALUES
(10, '노트북', 1200000),
(20, '마우스', 30000),
(30, '키보드', 80000),
(40, '모니터', 300000);

INSERT INTO orders VALUES
(101, 1, 10, '노트북', 1200000),
(102, 2, 20, '마우스', 30000),
(103, 1, 30, '키보드', 80000),
(104, 3, 40, '모니터', 300000);

-- 확인
SELECT 'member' AS 테이블, COUNT(*) AS 건수 FROM member
UNION ALL
SELECT 'product', COUNT(*) FROM product
UNION ALL
SELECT 'orders', COUNT(*) FROM orders;

SELECT * FROM member;
SELECT * FROM product;
SELECT * FROM orders;
