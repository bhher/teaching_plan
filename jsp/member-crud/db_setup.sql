-- 회원 로그인 · 회원가입 · CRUD
CREATE DATABASE IF NOT EXISTS member_crud
  DEFAULT CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE member_crud;

DROP TABLE IF EXISTS member;

CREATE TABLE member (
  id       VARCHAR(50)  NOT NULL PRIMARY KEY COMMENT '아이디',
  password VARCHAR(100) NOT NULL COMMENT '비밀번호',
  name     VARCHAR(50)  NOT NULL COMMENT '이름',
  email    VARCHAR(100) NULL COMMENT '이메일',
  reg_date DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '가입일'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO member (id, password, name, email) VALUES
('admin', '1234', '관리자', 'admin@test.com'),
('hong', '1234', '홍길동', 'hong@test.com');

SELECT * FROM member;
