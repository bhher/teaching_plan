-- 최소 CRUD 게시판 (id, title, content)
CREATE DATABASE IF NOT EXISTS crudboard
  DEFAULT CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE crudboard;

DROP TABLE IF EXISTS board;

CREATE TABLE board (
  id      INT AUTO_INCREMENT PRIMARY KEY,
  title   VARCHAR(200) NOT NULL,
  content TEXT NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO board (title, content) VALUES
('첫 글', '최소 CRUD 예제입니다.'),
('두번째 글', 'id · title · content 만 사용합니다.');

SELECT * FROM board;
