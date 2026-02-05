-- SQL JOIN 예제 모음

USE join_example;

-- ============================================
-- 1. INNER JOIN 예제
-- ============================================

-- 예제 1-1: 기본 INNER JOIN
SELECT 
    s.name AS 학생이름,
    s.major AS 전공,
    g.subject AS 과목,
    g.score AS 점수
FROM student s
INNER JOIN grade g ON s.student_no = g.student_no;

-- 예제 1-2: 특정 전공 학생의 성적만 조회
SELECT 
    s.name AS 학생이름,
    g.subject AS 과목,
    g.score AS 점수
FROM student s
INNER JOIN grade g ON s.student_no = g.student_no
WHERE s.major = '컴퓨터공학'
ORDER BY g.score DESC;

-- 예제 1-3: 3개 테이블 JOIN
SELECT 
    s.name AS 학생이름,
    s.major AS 전공,
    d.dept_name AS 부서명,
    d.location AS 위치,
    g.subject AS 과목,
    g.score AS 점수
FROM student s
INNER JOIN grade g ON s.student_no = g.student_no
INNER JOIN department d ON s.major = d.dept_name;

-- ============================================
-- 2. LEFT JOIN 예제
-- ============================================

-- 예제 2-1: 기본 LEFT JOIN
SELECT 
    s.name AS 학생이름,
    s.major AS 전공,
    g.subject AS 과목,
    g.score AS 점수
FROM student s
LEFT JOIN grade g ON s.student_no = g.student_no;

-- 예제 2-2: 성적이 없는 학생 찾기
SELECT 
    s.name AS 학생이름,
    s.major AS 전공,
    s.grade AS 학년
FROM student s
LEFT JOIN grade g ON s.student_no = g.student_no
WHERE g.student_no IS NULL;

-- 예제 2-3: 집계 함수와 함께 사용
SELECT 
    s.name AS 학생이름,
    s.major AS 전공,
    AVG(g.score) AS 평균점수,
    COUNT(g.score) AS 과목수
FROM student s
LEFT JOIN grade g ON s.student_no = g.student_no
GROUP BY s.student_no, s.name, s.major;

-- ============================================
-- 3. RIGHT JOIN 예제
-- ============================================

-- 예제 3-1: 기본 RIGHT JOIN
SELECT 
    s.name AS 학생이름,
    s.major AS 전공,
    g.subject AS 과목,
    g.score AS 점수
FROM student s
RIGHT JOIN grade g ON s.student_no = g.student_no;

-- 예제 3-2: 학생 정보가 없는 성적 찾기
SELECT 
    g.student_no AS 학번,
    g.subject AS 과목,
    g.score AS 점수
FROM student s
RIGHT JOIN grade g ON s.student_no = g.student_no
WHERE s.student_no IS NULL;

-- ============================================
-- 4. FULL OUTER JOIN 예제 (UNION 사용)
-- ============================================

-- 예제 4-1: FULL OUTER JOIN 구현
SELECT 
    s.name AS 학생이름,
    s.major AS 전공,
    g.subject AS 과목,
    g.score AS 점수
FROM student s
LEFT JOIN grade g ON s.student_no = g.student_no

UNION

SELECT 
    s.name AS 학생이름,
    s.major AS 전공,
    g.subject AS 과목,
    g.score AS 점수
FROM student s
RIGHT JOIN grade g ON s.student_no = g.student_no;

-- ============================================
-- 5. CROSS JOIN 예제
-- ============================================

-- 예제 5-1: 학생과 부서의 모든 조합
SELECT 
    s.name AS 학생이름,
    d.dept_name AS 부서명,
    d.location AS 위치
FROM student s
CROSS JOIN department d;

-- ============================================
-- 6. SELF JOIN 예제
-- ============================================

-- 예제 6-1: 직원과 상사 정보 조회
SELECT 
    e1.emp_name AS 직원이름,
    e2.emp_name AS 상사이름
FROM employee e1
LEFT JOIN employee e2 ON e1.manager_id = e2.emp_id;

-- 예제 6-2: 같은 전공 학생 찾기
SELECT 
    s1.name AS 학생1,
    s2.name AS 학생2,
    s1.major AS 전공
FROM student s1
INNER JOIN student s2 ON s1.major = s2.major
WHERE s1.student_id < s2.student_id;

-- ============================================
-- 7. 실전 예제
-- ============================================

-- 예제 7-1: 학생별 총점과 평균
SELECT 
    s.name AS 학생이름,
    s.major AS 전공,
    COUNT(g.subject) AS 과목수,
    SUM(g.score) AS 총점,
    AVG(g.score) AS 평균점수
FROM student s
LEFT JOIN grade g ON s.student_no = g.student_no
GROUP BY s.student_no, s.name, s.major
ORDER BY 평균점수 DESC;

-- 예제 7-2: 전공별 평균 점수
SELECT 
    s.major AS 전공,
    COUNT(DISTINCT s.student_no) AS 학생수,
    COUNT(g.subject) AS 과목수,
    AVG(g.score) AS 전공평균점수
FROM student s
LEFT JOIN grade g ON s.student_no = g.student_no
GROUP BY s.major
ORDER BY 전공평균점수 DESC;

-- 예제 7-3: 복잡한 JOIN (3개 이상 테이블)
SELECT 
    s.student_no AS 학번,
    s.name AS 학생이름,
    s.major AS 전공,
    d.location AS 부서위치,
    g.subject AS 과목,
    g.score AS 점수,
    CASE 
        WHEN g.score >= 90 THEN 'A'
        WHEN g.score >= 80 THEN 'B'
        WHEN g.score >= 70 THEN 'C'
        ELSE 'F'
    END AS 등급
FROM student s
INNER JOIN grade g ON s.student_no = g.student_no
LEFT JOIN department d ON s.major = d.dept_name
ORDER BY s.name, g.score DESC;

-- 예제 7-4: 조건이 있는 JOIN
SELECT 
    s.name AS 학생이름,
    g.subject AS 과목,
    g.score AS 점수
FROM student s
INNER JOIN grade g ON s.student_no = g.student_no 
    AND g.score >= 80
ORDER BY g.score DESC;
