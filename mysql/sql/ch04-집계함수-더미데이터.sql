-- Ch04 집계함수 실습용 더미데이터
-- 실행: mysql -u root -p --default-character-set=utf8mb4 < ch04-집계함수-더미데이터.sql
-- 또는 Workbench에서 전체 실행

CREATE DATABASE IF NOT EXISTS 집계연습
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE 집계연습;

DROP TABLE IF EXISTS 주문;
DROP TABLE IF EXISTS 회원;
DROP TABLE IF EXISTS 직원;

CREATE TABLE 회원 (
  회원번호   VARCHAR(10) PRIMARY KEY,
  회원명     VARCHAR(50) NOT NULL,
  도시       VARCHAR(50),
  지역       VARCHAR(50),          -- NULL 가능 (COUNT 비교용)
  등급       VARCHAR(20),          -- VIP / 일반 / 신규
  포인트     INT NOT NULL DEFAULT 0,
  가입연도   INT
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE 직원 (
  직원번호 INT PRIMARY KEY AUTO_INCREMENT,
  이름     VARCHAR(50) NOT NULL,
  부서     VARCHAR(50)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE 주문 (
  주문번호 INT PRIMARY KEY AUTO_INCREMENT,
  회원번호 VARCHAR(10),
  주문금액 INT NOT NULL,
  주문월   INT,
  FOREIGN KEY (회원번호) REFERENCES 회원(회원번호)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

INSERT INTO 회원 (회원번호, 회원명, 도시, 지역, 등급, 포인트, 가입연도) VALUES
('A001', '김민수', '서울특별시', '강남',   'VIP',  120000, 2020),
('A002', '이서연', '서울특별시', '마포',   '일반',  45000,  2021),
('A003', '박지훈', '부산광역시', '해운대', 'VIP',  200000, 2019),
('A004', '최유진', '부산광역시', NULL,     '신규', 8000,   2024),
('A005', '정하늘', '대구광역시', '중구',   '일반',  32000,  2022),
('A006', '한소희', '인천광역시', NULL,     '일반',  15000,  2023),
('A007', '오준서', '서울특별시', '송파',   'VIP',  98000,  2020),
('A008', '윤채원', '광주광역시', '서구',   '신규', 5000,   2024),
('A009', '강동원', '부산광역시', '수영',   '일반',  67000,  2021),
('A010', '임나영', '대전광역시', NULL,     'VIP',  150000, 2018),
('B001', '서준호', '서울특별시', '강서',   '일반',  22000,  2022),
('B002', '문지아', '인천광역시', '연수',   'VIP',  110000, 2019),
('B003', '배성훈', '대구광역시', NULL,     '신규', 3000,   2025),
('B004', '신예린', '광주광역시', '북구',   '일반',  41000,  2021),
('B005', '홍길동', '서울특별시', '종로',   'VIP',  175000, 2017);

INSERT INTO 직원 (이름, 부서) VALUES
('김사원', '영업'),
('이사원', '영업'),
('박대리', '마케팅'),
('최과장', '마케팅'),
('정부장', '개발'),
('한대리', '개발'),
('오사원', '영업');

INSERT INTO 주문 (회원번호, 주문금액, 주문월) VALUES
('A001', 35000, 1),
('A001', 12000, 1),
('A002', 8000,  2),
('A003', 55000, 2),
('A003', 20000, 3),
('A005', 15000, 3),
('A007', 42000, 3),
('A007', 18000, 4),
('A009', 9000,  4),
('A010', 70000, 4),
('B001', 11000, 5),
('B002', 33000, 5),
('B002', 25000, 5),
('B004', 14000, 6),
('B005', 60000, 6),
('A004', 5000,  6),
('A006', 7000,  6),
('A008', 4000,  6);

SELECT '집계연습 DB 준비 완료' AS 메시지;
SELECT COUNT(*) AS 회원수 FROM 회원;
SELECT COUNT(*) AS 직원수 FROM 직원;
SELECT COUNT(*) AS 주문수 FROM 주문;
