-- 예제: 학생(또는 사원) 기본 테이블
-- 실행:
--   mysql -u root -p --default-character-set=utf8mb4 < student-예제.sql

CREATE DATABASE IF NOT EXISTS sample_db
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE sample_db;

DROP TABLE IF EXISTS student;

CREATE TABLE student (
  id   INT PRIMARY KEY,
  name VARCHAR(30) NOT NULL,
  age  INT NOT NULL,
  dept VARCHAR(30) NOT NULL
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

INSERT INTO student (id, name, age, dept) VALUES
(1, '홍길동', 25, '컴퓨터'),
(2, '김철수', 30, '디자인'),
(3, '이영희', 27, '컴퓨터'),
(4, '박민수', 35, '경영');

-- 확인
SELECT * FROM student;
