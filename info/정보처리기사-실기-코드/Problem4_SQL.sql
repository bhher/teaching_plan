-- 문제 4: 데이터베이스 SQL 작성

-- 테이블 생성
CREATE TABLE STUDENT (
    STUDENT_ID INT PRIMARY KEY,
    NAME VARCHAR(50) NOT NULL,
    AGE INT,
    DEPARTMENT VARCHAR(50),
    SCORE INT
);

-- 샘플 데이터 삽입
INSERT INTO STUDENT VALUES (1, '김철수', 20, '컴퓨터공학', 85);
INSERT INTO STUDENT VALUES (2, '이영희', 21, '전자공학', 92);
INSERT INTO STUDENT VALUES (3, '박민수', 20, '컴퓨터공학', 78);
INSERT INTO STUDENT VALUES (4, '최지영', 22, '전자공학', 88);
INSERT INTO STUDENT VALUES (5, '정수진', 21, '컴퓨터공학', 95);
INSERT INTO STUDENT VALUES (6, '한동욱', 20, '기계공학', 82);
INSERT INTO STUDENT VALUES (7, '송미영', 22, '컴퓨터공학', 90);
INSERT INTO STUDENT VALUES (8, '윤태호', 21, '전자공학', 87);

-- 문제 1: 컴퓨터공학과 학생들의 평균 점수를 구하세요.
SELECT AVG(SCORE) AS 평균점수
FROM STUDENT
WHERE DEPARTMENT = '컴퓨터공학';

-- 문제 2: 각 학과별 학생 수와 평균 점수를 구하세요.
SELECT 
    DEPARTMENT AS 학과,
    COUNT(*) AS 학생수,
    AVG(SCORE) AS 평균점수
FROM STUDENT
GROUP BY DEPARTMENT;

-- 문제 3: 점수가 85점 이상인 학생의 이름과 점수를 출력하세요.
SELECT NAME, SCORE
FROM STUDENT
WHERE SCORE >= 85
ORDER BY SCORE DESC;

-- 문제 4: 나이가 21세 이상인 학생 중 점수가 가장 높은 학생의 정보를 출력하세요.
-- 방법 1: LIMIT 사용 (MySQL, PostgreSQL)
SELECT *
FROM STUDENT
WHERE AGE >= 21
ORDER BY SCORE DESC
LIMIT 1;

-- 방법 2: 서브쿼리 사용 (모든 DBMS 호환)
SELECT *
FROM STUDENT
WHERE AGE >= 21
  AND SCORE = (
      SELECT MAX(SCORE)
      FROM STUDENT
      WHERE AGE >= 21
  );

-- 추가 쿼리 예제
-- 전체 학생의 평균 점수
SELECT AVG(SCORE) AS 전체평균점수 FROM STUDENT;

-- 최고점과 최저점
SELECT MAX(SCORE) AS 최고점, MIN(SCORE) AS 최저점 FROM STUDENT;

-- 학과별 최고점 학생
SELECT DEPARTMENT, NAME, SCORE
FROM STUDENT s1
WHERE SCORE = (
    SELECT MAX(SCORE)
    FROM STUDENT s2
    WHERE s2.DEPARTMENT = s1.DEPARTMENT
)
ORDER BY DEPARTMENT;
