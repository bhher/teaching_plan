-- SQL 집계함수 실습 문제 정답

-- 문제 1: 전체 학생 수 구하기
SELECT COUNT(*) AS 학생수
FROM student;

-- 문제 2: 각 학과별 학생 수 구하기
SELECT 
    department AS 학과,
    COUNT(*) AS 학생수
FROM student
GROUP BY department
ORDER BY 학생수 DESC;

-- 문제 3: 각 학년별 학생 수 구하기
SELECT 
    grade AS 학년,
    COUNT(*) AS 학생수
FROM student
GROUP BY grade
ORDER BY grade;

-- 문제 4: 전체 학생의 평균 점수 구하기
SELECT ROUND(AVG(average), 2) AS 전체평균점수
FROM student;

-- 문제 5: 각 학과별 평균 점수 구하기
SELECT 
    department AS 학과,
    ROUND(AVG(average), 2) AS 평균점수
FROM student
GROUP BY department
ORDER BY 평균점수 DESC;

-- 문제 6: 최고 점수와 최저 점수 구하기
SELECT 
    MAX(average) AS 최고점수,
    MIN(average) AS 최저점수
FROM student;

-- 문제 7: 각 학과별 최고 점수와 최저 점수 구하기
SELECT 
    department AS 학과,
    MAX(average) AS 최고점수,
    MIN(average) AS 최저점수
FROM student
GROUP BY department
ORDER BY department;

-- 문제 8: 각 학과별 총점 합계 구하기
SELECT 
    department AS 학과,
    SUM(total_score) AS 총점합계
FROM student
GROUP BY department
ORDER BY 총점합계 DESC;

-- 문제 9: 평균 점수가 85점 이상인 학과 찾기
SELECT 
    department AS 학과,
    ROUND(AVG(average), 2) AS 평균점수
FROM student
GROUP BY department
HAVING AVG(average) >= 85
ORDER BY 평균점수 DESC;

-- 문제 10: 학생 수가 5명 이상인 학과 찾기
SELECT 
    department AS 학과,
    COUNT(*) AS 학생수
FROM student
GROUP BY department
HAVING COUNT(*) >= 5
ORDER BY 학생수 DESC;

-- 문제 11: 각 학과별 국어 평균 점수 구하기
SELECT 
    department AS 학과,
    ROUND(AVG(korean), 2) AS 국어평균점수
FROM student
GROUP BY department
ORDER BY 국어평균점수 DESC;

-- 문제 12: 각 학년별 수학 최고 점수 구하기
SELECT 
    grade AS 학년,
    MAX(math) AS 수학최고점수
FROM student
GROUP BY grade
ORDER BY grade;

-- 문제 13: 전체 학생의 총점 합계 구하기
SELECT SUM(total_score) AS 전체총점합계
FROM student;

-- 문제 14: 평균 점수가 90점 이상인 학생 수 구하기
SELECT COUNT(*) AS 학생수
FROM student
WHERE average >= 90;

-- 문제 15: 각 학과별 평균 점수와 학생 수를 함께 구하기
SELECT 
    department AS 학과,
    COUNT(*) AS 학생수,
    ROUND(AVG(average), 2) AS 평균점수
FROM student
GROUP BY department
ORDER BY 평균점수 DESC;

-- 문제 16: 학과별 통계 (학생수, 평균, 최고점, 최저점)
SELECT 
    department AS 학과,
    COUNT(*) AS 학생수,
    ROUND(AVG(average), 2) AS 평균점수,
    MAX(average) AS 최고점수,
    MIN(average) AS 최저점수
FROM student
GROUP BY department
ORDER BY 평균점수 DESC;

-- 문제 17: 학년별 통계 (학생수, 평균, 총점 합계)
SELECT 
    grade AS 학년,
    COUNT(*) AS 학생수,
    ROUND(AVG(average), 2) AS 평균점수,
    SUM(total_score) AS 총점합계
FROM student
GROUP BY grade
ORDER BY grade;

-- 문제 18: 평균 점수가 전체 평균보다 높은 학과 찾기
SELECT 
    department AS 학과,
    ROUND(AVG(average), 2) AS 평균점수
FROM student
GROUP BY department
HAVING AVG(average) > (SELECT AVG(average) FROM student)
ORDER BY 평균점수 DESC;

-- 문제 19: 각 학과별 영어 점수 합계와 평균 구하기
SELECT 
    department AS 학과,
    SUM(english) AS 영어점수합계,
    ROUND(AVG(english), 2) AS 영어평균점수
FROM student
GROUP BY department
ORDER BY 영어평균점수 DESC;

-- 문제 20: 학년별 과학 점수 최고점과 최저점의 차이 구하기
SELECT 
    grade AS 학년,
    MAX(science) AS 최고점수,
    MIN(science) AS 최저점수,
    MAX(science) - MIN(science) AS 점수차이
FROM student
GROUP BY grade
ORDER BY grade;
