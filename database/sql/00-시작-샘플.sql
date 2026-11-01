-- school 실습 DB (1-2주차)
-- MySQL 8.x / Workbench에서 전체 실행

DROP DATABASE IF EXISTS school;
CREATE DATABASE school
  DEFAULT CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci;

USE school;

CREATE TABLE student (
  id         INT PRIMARY KEY AUTO_INCREMENT,
  name       VARCHAR(50) NOT NULL,
  age        INT,
  email      VARCHAR(100),
  enter_date DATE DEFAULT (CURRENT_DATE)
);

INSERT INTO student (name, age, email) VALUES
('김민수', 20, 'min@test.com'),
('이서연', 21, 'seo@test.com'),
('박준호', 19, NULL),
('최유나', 22, 'yuna@test.com'),
('정하늘', 20, 'sky@test.com');

CREATE TABLE score (
  id         INT PRIMARY KEY AUTO_INCREMENT,
  student_id INT NOT NULL,
  subject    VARCHAR(30) NOT NULL,
  point      INT NOT NULL,
  CONSTRAINT fk_score_student
    FOREIGN KEY (student_id) REFERENCES student(id)
);

INSERT INTO score (student_id, subject, point) VALUES
(1, '국어', 90), (1, '수학', 80),
(2, '국어', 70), (2, '수학', 95),
(3, '국어', 88), (3, '수학', 88),
(4, '국어', 92), (4, '수학', 91),
(5, '국어', 60), (5, '수학', 75);

-- 확인
SELECT * FROM student;
SELECT * FROM score;
