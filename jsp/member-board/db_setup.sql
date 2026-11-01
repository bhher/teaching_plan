-- 회원 + 게시판 연동
CREATE DATABASE IF NOT EXISTS member_board
  DEFAULT CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE member_board;

DROP TABLE IF EXISTS board;
DROP TABLE IF EXISTS member;

CREATE TABLE member (
  id       VARCHAR(50)  NOT NULL PRIMARY KEY,
  password VARCHAR(100) NOT NULL,
  name     VARCHAR(50)  NOT NULL,
  email    VARCHAR(100) NULL,
  reg_date DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE board (
  id          INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  title       VARCHAR(200) NOT NULL,
  content     TEXT         NOT NULL,
  writer_id   VARCHAR(50)  NOT NULL,
  writer_name VARCHAR(50)  NOT NULL,
  reg_date    DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_board_member
    FOREIGN KEY (writer_id) REFERENCES member(id)
    ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO member (id, password, name, email) VALUES
('admin', '1234', '관리자', 'admin@test.com'),
('hong', '1234', '홍길동', 'hong@test.com');

INSERT INTO board (title, content, writer_id, writer_name) VALUES
('환영합니다', '회원 로그인 후 글쓰기가 가능합니다.', 'admin', '관리자'),
('첫 게시글', '세션의 loginId가 작성자로 저장됩니다.', 'hong', '홍길동');

SELECT * FROM member;
SELECT * FROM board;
