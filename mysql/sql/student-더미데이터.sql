-- ============================================================
-- student 테이블 더미 데이터
-- 교안: MySQL-순수SQL-완전정복-1부.md (기본 student 구조)
-- 컬럼: id, name, age, email, created_at
-- ============================================================

DROP DATABASE IF EXISTS school_db;
CREATE DATABASE school_db
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE school_db;

CREATE TABLE student (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    age INT,
    email VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

-- ---------- 더미 데이터 20명 ----------
INSERT INTO student (name, age, email, created_at) VALUES
('홍길동', 20, 'hong@example.com',     '2024-03-01 09:00:00'),
('김영희', 25, 'kim@example.com',      '2024-03-02 10:15:00'),
('박철수', 22, 'park@example.com',     '2024-03-03 11:20:00'),
('이수영', 19, 'lee@example.com',      '2024-03-05 14:00:00'),
('최민수', 28, 'choi@example.com',     '2024-03-07 16:30:00'),
('정하늘', 21, 'jung@example.com',     '2024-03-10 09:45:00'),
('강서연', 23, 'kang@example.com',     '2024-03-12 13:10:00'),
('윤도현', 26, NULL,                   '2024-03-15 08:00:00'),
('임지훈', 20, 'lim@example.com',      '2024-03-18 17:20:00'),
('한소희', 24, 'han@example.com',      '2024-03-20 12:00:00'),
('오세훈', 30, 'oh@example.com',       '2024-04-01 09:30:00'),
('서지원', 18, 'seo@example.com',      '2024-04-03 10:00:00'),
('신동엽', 27, 'shin@example.com',     '2024-04-05 15:40:00'),
('조은별', 22, 'cho@example.com',      '2024-04-08 11:11:00'),
('배성우', 29, NULL,                   '2024-04-10 18:00:00'),
('유나래', 21, 'yoo@example.com',      '2024-04-12 09:05:00'),
('문채원', 25, 'moon@example.com',     '2024-04-15 14:22:00'),
('송중기', 20, 'song@example.com',     '2024-04-18 16:00:00'),
('권지용', 23, 'kwon@example.com',     '2024-04-20 10:50:00'),
('황민호', 19, 'hwang@example.com',    '2024-04-22 13:33:00');

-- ---------- 확인 ----------
SELECT * FROM student;
SELECT COUNT(*) AS 학생수 FROM student;   -- 20

-- ---------- 연습용 쿼리 예시 (실행 확인용) ----------
-- SELECT * FROM student WHERE age >= 25;
-- SELECT * FROM student WHERE name LIKE '김%';
-- SELECT * FROM student WHERE email IS NULL;
-- SELECT * FROM student ORDER BY age DESC LIMIT 5;
-- SELECT AVG(age) AS 평균나이 FROM student;
