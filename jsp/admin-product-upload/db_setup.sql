-- 상품 등록 연습용 (이미지 업로드)
CREATE DATABASE IF NOT EXISTS shop_upload
  DEFAULT CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE shop_upload;

DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS admin;

CREATE TABLE admin (
  id       VARCHAR(50)  NOT NULL PRIMARY KEY,
  password VARCHAR(100) NOT NULL,
  name     VARCHAR(50)  NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE product (
  id          INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name        VARCHAR(100) NOT NULL COMMENT '상품명',
  price       INT          NOT NULL COMMENT '가격',
  image       VARCHAR(255) NULL COMMENT '업로드 파일명',
  description TEXT         NULL,
  reg_date    DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO admin (id, password, name) VALUES
('admin', '1234', '관리자');

SELECT * FROM admin;
SELECT * FROM product;
