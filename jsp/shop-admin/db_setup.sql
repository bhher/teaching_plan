-- shop_db : 쇼핑몰 관리자 미니 프로젝트
CREATE DATABASE IF NOT EXISTS shop_db
  DEFAULT CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE shop_db;

DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS admin;

-- 관리자 테이블
CREATE TABLE admin (
  id       VARCHAR(50)  NOT NULL PRIMARY KEY COMMENT '관리자 아이디',
  password VARCHAR(100) NOT NULL COMMENT '비밀번호',
  name     VARCHAR(50)  NOT NULL COMMENT '이름',
  reg_date DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 상품 테이블
CREATE TABLE product (
  id          INT           NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '상품 번호',
  name        VARCHAR(100)  NOT NULL COMMENT '상품명',
  price       INT           NOT NULL COMMENT '가격',
  stock       INT           NOT NULL DEFAULT 0 COMMENT '재고',
  category    VARCHAR(50)   NOT NULL COMMENT '카테고리',
  description TEXT          NULL COMMENT '설명',
  reg_date    DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO admin (id, password, name) VALUES
('admin', '1234', '최고관리자');

INSERT INTO product (name, price, stock, category, description) VALUES
('무선 마우스', 25000, 50, '전자제품', '블루투스 무선 마우스'),
('노트북 파우치', 18000, 30, '생활용품', '15인치 노트북 파우치'),
('자바 입문서', 22000, 100, '도서', 'JSP·Servlet 입문 교재');

SELECT * FROM admin;
SELECT * FROM product;
