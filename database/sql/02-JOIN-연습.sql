-- JOIN 연습 (5주차)
-- shop 미니 DB

DROP DATABASE IF EXISTS shop;
CREATE DATABASE shop DEFAULT CHARACTER SET utf8mb4;
USE shop;

CREATE TABLE member (
  id    INT PRIMARY KEY AUTO_INCREMENT,
  name  VARCHAR(50) NOT NULL,
  city  VARCHAR(30)
);

CREATE TABLE product (
  id    INT PRIMARY KEY AUTO_INCREMENT,
  name  VARCHAR(50) NOT NULL,
  price INT NOT NULL
);

CREATE TABLE orders (
  id         INT PRIMARY KEY AUTO_INCREMENT,
  member_id  INT NOT NULL,
  product_id INT NOT NULL,
  qty        INT DEFAULT 1,
  order_date DATE DEFAULT (CURRENT_DATE),
  CONSTRAINT fk_ord_member FOREIGN KEY (member_id) REFERENCES member(id),
  CONSTRAINT fk_ord_product FOREIGN KEY (product_id) REFERENCES product(id)
);

INSERT INTO member (name, city) VALUES
('김고객', '서울'), ('이구매', '부산'), ('박주문', '서울'), ('최미구매', '대구');

INSERT INTO product (name, price) VALUES
('키보드', 45000), ('마우스', 25000), ('모니터', 200000);

INSERT INTO orders (member_id, product_id, qty) VALUES
(1, 1, 1),
(1, 2, 2),
(2, 3, 1),
(3, 1, 1);
-- member id=4 는 주문 없음

-- INNER JOIN: 주문한 사람만
SELECT m.name, p.name AS product, o.qty, (p.price * o.qty) AS amount
FROM orders o
JOIN member m ON o.member_id = m.id
JOIN product p ON o.product_id = p.id;

-- LEFT JOIN: 주문 안 한 회원 찾기
SELECT m.name, o.id AS order_id
FROM member m
LEFT JOIN orders o ON m.id = o.member_id
WHERE o.id IS NULL;

-- 상품별 판매 수량
SELECT p.name, SUM(o.qty) AS sold
FROM product p
LEFT JOIN orders o ON p.id = o.product_id
GROUP BY p.id, p.name;
