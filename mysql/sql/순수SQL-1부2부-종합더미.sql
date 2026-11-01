-- MySQL 순수 SQL 1부~2부(JOIN·집계) 종합 실습 DB
-- 실행:
--   mysql -u root -p --default-character-set=utf8mb4 < 순수SQL-1부2부-종합더미.sql

CREATE DATABASE IF NOT EXISTS sql_full
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE sql_full;

DROP TABLE IF EXISTS 수강;
DROP TABLE IF EXISTS 강좌;
DROP TABLE IF EXISTS 수강생;
DROP TABLE IF EXISTS 강사;

-- 3. 테이블 생성 (PK / FK)
CREATE TABLE 강사 (
  강사번호   INT PRIMARY KEY,
  강사명     VARCHAR(30) NOT NULL,
  전공       VARCHAR(40) NOT NULL,
  입사일     DATE
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE 수강생 (
  수강생번호 INT PRIMARY KEY,
  이름       VARCHAR(30) NOT NULL,
  도시       VARCHAR(30),
  연락처     VARCHAR(20),
  포인트     INT NOT NULL DEFAULT 0
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE 강좌 (
  강좌번호   INT PRIMARY KEY,
  강좌명     VARCHAR(50) NOT NULL,
  강사번호   INT NOT NULL,
  카테고리   VARCHAR(20) NOT NULL,
  수강료     INT NOT NULL,
  FOREIGN KEY (강사번호) REFERENCES 강사(강사번호)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE 수강 (
  수강번호   INT PRIMARY KEY,
  수강생번호 INT NOT NULL,
  강좌번호   INT NOT NULL,
  점수       INT,
  수강일     DATE NOT NULL,
  FOREIGN KEY (수강생번호) REFERENCES 수강생(수강생번호),
  FOREIGN KEY (강좌번호) REFERENCES 강좌(강좌번호)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 4. DML — INSERT
INSERT INTO 강사 VALUES
(1, '박민수', '데이터베이스', '2020-03-01'),
(2, '이서연', '웹개발',       '2021-05-10'),
(3, '최준호', '프로그래밍',   '2019-09-15'),
(4, '한지우', '디자인',       '2022-01-20');

INSERT INTO 수강생 VALUES
(101, '김하늘', '서울', '010-1111-1001', 1200),
(102, '이도윤', '부산', '010-2222-1002', 800),
(103, '박서준', '서울', '010-3333-1003', 1500),
(104, '최유진', '대구', NULL,            500),
(105, '정민아', '인천', '010-5555-1005', 0),
(106, '오세훈', '서울', '010-6666-1006', 300);

-- 105 정민아: 수강 없음 → LEFT JOIN / 미수강 찾기용
INSERT INTO 강좌 VALUES
(10, 'MySQL 입문',     1, 'DB',   200000),
(11, 'MySQL 심화',     1, 'DB',   250000),
(12, 'HTML/CSS',       2, '웹',   180000),
(13, 'JavaScript',     2, '웹',   220000),
(14, 'Java 기초',      3, '언어', 230000),
(15, 'UI 디자인',      4, '디자인', 150000);

-- 15 UI 디자인: 수강생 없음 가능 (RIGHT/LEFT 연습용으로 일부만 수강)
INSERT INTO 수강 VALUES
(1001, 101, 10, 92, '2026-01-10'),
(1002, 101, 12, 88, '2026-01-15'),
(1003, 102, 10, 75, '2026-01-12'),
(1004, 102, 14, 80, '2026-02-01'),
(1005, 103, 11, 95, '2026-01-20'),
(1006, 103, 13, 90, '2026-02-05'),
(1007, 104, 12, 70, '2026-01-18'),
(1008, 106, 14, NULL, '2026-02-10');
-- 106 오세훈: 점수 NULL (COUNT 연습)
-- 105 정민아: 수강 행 없음
-- 강좌 15: 수강 없음

-- 확인
SHOW TABLES;

SELECT '강사' AS 테이블, COUNT(*) AS 건수 FROM 강사
UNION ALL SELECT '수강생', COUNT(*) FROM 수강생
UNION ALL SELECT '강좌', COUNT(*) FROM 강좌
UNION ALL SELECT '수강', COUNT(*) FROM 수강;

SELECT * FROM 강사;
SELECT * FROM 수강생;
SELECT * FROM 강좌;
SELECT * FROM 수강;
