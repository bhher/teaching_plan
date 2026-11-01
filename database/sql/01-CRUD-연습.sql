-- CRUD 연습 (1-2주차)
-- 먼저 00-시작-샘플.sql 실행 후 USE school;

USE school;

-- 1) 추가
INSERT INTO student (name, age, email)
VALUES ('한지민', 23, 'jimin@test.com');

-- 2) 조회
SELECT id, name, age FROM student WHERE age >= 21;

-- 3) 수정 (WHERE 필수!)
UPDATE student
SET email = 'han@test.com'
WHERE name = '한지민';

-- 4) 삭제
DELETE FROM student WHERE name = '한지민';

-- 확인
SELECT * FROM student;
