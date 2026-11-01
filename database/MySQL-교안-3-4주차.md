# MySQL 교안 (3–4주차)

**목표**
- WHERE로 원하는 행만 조회
- ORDER BY · LIMIT
- 내장 함수 활용
- 집계 함수와 GROUP BY · HAVING

**전체 목차:** [MySQL-교안-전체.md](./MySQL-교안-전체.md)  
**선수:** [1–2주차](./MySQL-교안-1-2주차.md) CRUD

---

## 📅 3주차 – 조건·정렬·함수

### 📌 학습 목표
- 비교·논리·패턴 검색
- NULL 다루기
- 정렬과 상위 N건
- 문자열·날짜 함수

### 📘 이론

#### 3.1 WHERE 기본
```sql
SELECT * FROM student WHERE age >= 20;
SELECT * FROM student WHERE name = '김민수';
```

#### 3.2 비교·논리 연산자

| 연산 | 의미 |
|------|------|
| `=`, `<>` 또는 `!=` | 같음 / 다름 |
| `>`, `>=`, `<`, `<=` | 크기 비교 |
| `AND`, `OR`, `NOT` | 논리 |
| `BETWEEN a AND b` | a~b 사이 |
| `IN (…)` | 목록 중 하나 |
| `LIKE` | 패턴 |

```sql
SELECT * FROM student
WHERE age BETWEEN 19 AND 22;

SELECT * FROM student
WHERE name IN ('김민수', '이서연');

-- % : 여러 글자,  _ : 한 글자
SELECT * FROM student WHERE name LIKE '김%';
SELECT * FROM student WHERE email LIKE '%@test.com';
```

#### 3.3 NULL
```sql
-- NULL 비교는 = 이 아니라 IS NULL / IS NOT NULL
SELECT * FROM student WHERE email IS NULL;
SELECT * FROM student WHERE email IS NOT NULL;
```

#### 3.4 ORDER BY · LIMIT
```sql
SELECT * FROM student ORDER BY age ASC;   -- 오름차순
SELECT * FROM student ORDER BY age DESC;  -- 내림차순
SELECT * FROM student ORDER BY age DESC, name ASC;

SELECT * FROM student ORDER BY id DESC LIMIT 3;  -- 상위 3건
```

#### 3.5 자주 쓰는 함수

**문자열**
```sql
SELECT CONCAT(name, '(', age, '세)') AS label FROM student;
SELECT UPPER(email), LOWER(email) FROM student;
SELECT LENGTH(name), CHAR_LENGTH(name) FROM student; -- 바이트 vs 글자 수
SELECT SUBSTRING(name, 1, 1) AS 성 FROM student;
```

**숫자**
```sql
SELECT ROUND(avg_score, 1), CEIL(3.2), FLOOR(3.8);
```

**날짜**
```sql
SELECT NOW(), CURDATE(), CURTIME();
SELECT YEAR(enter_date), MONTH(enter_date) FROM student;
SELECT DATEDIFF(CURDATE(), enter_date) AS 경과일 FROM student;
```

**NULL 대체**
```sql
SELECT name, IFNULL(email, '없음') AS email FROM student;
-- MySQL 8+: COALESCE(email, '없음')
```

### 🧪 실습
1. 성이 '이'인 학생  
2. 나이 많은 순 3명  
3. 이메일이 없는 학생  
4. 이름과 나이를 `홍길동(20세)` 형태로 출력  

### ✅ 3주차 체크
- [ ] LIKE / IN / BETWEEN / IS NULL
- [ ] ORDER BY, LIMIT
- [ ] CONCAT, IFNULL, NOW 사용

---

## 📅 4주차 – 집계와 GROUP BY

### 📌 학습 목표
- COUNT, SUM, AVG, MAX, MIN
- GROUP BY로 묶기
- HAVING으로 그룹 조건

### 📘 이론

#### 4.1 집계 함수
```sql
SELECT COUNT(*) FROM student;
SELECT COUNT(email) FROM student;  -- NULL 제외
SELECT AVG(age), MAX(age), MIN(age) FROM student;
```

성적 테이블 예:
```sql
CREATE TABLE score (
  id INT PRIMARY KEY AUTO_INCREMENT,
  student_id INT,
  subject VARCHAR(30),
  point INT
);

INSERT INTO score (student_id, subject, point) VALUES
(1, '국어', 90), (1, '수학', 80),
(2, '국어', 70), (2, '수학', 95),
(3, '국어', 88), (3, '수학', 88);
```

```sql
SELECT SUM(point), AVG(point) FROM score WHERE student_id = 1;
```

#### 4.2 GROUP BY
```sql
-- 학생별 평균
SELECT student_id, AVG(point) AS avg_point
FROM score
GROUP BY student_id;

-- 과목별 응시 인원·평균
SELECT subject, COUNT(*) AS cnt, AVG(point) AS avg_point
FROM score
GROUP BY subject;
```

#### 4.3 HAVING
`WHERE` = 행 필터 → `GROUP BY` → `HAVING` = **그룹** 필터

```sql
SELECT student_id, AVG(point) AS avg_point
FROM score
GROUP BY student_id
HAVING AVG(point) >= 85;
```

#### 4.4 SELECT 실행 순서 (개념)

```
FROM → WHERE → GROUP BY → HAVING → SELECT → ORDER BY → LIMIT
```

### 📐 WHERE vs HAVING

```
score 테이블 행들
    │
    ▼ WHERE point >= 60     ← 개별 행
    │
    ▼ GROUP BY student_id   ← 묶기
    │
    ▼ HAVING AVG(point)>=85 ← 그룹 결과
```

### 🧪 실습
1. 학생 수, 평균 나이  
2. 과목별 최고점·최저점  
3. 평균 90점 이상인 학생 id  
4. 과목별 평균을 높은 순 정렬  

### ⚠️ 주의
- `SELECT`에 집계 아닌 컬럼을 쓰면 보통 **GROUP BY에 포함**해야 함
- `COUNT(*)` vs `COUNT(컬럼)` 차이 (NULL)

### ✅ 4주차 체크
- [ ] 5가지 집계 함수
- [ ] GROUP BY 작성
- [ ] HAVING과 WHERE 구분

---

## 다음 주 예고
여러 테이블 JOIN, 서브쿼리, PK/FK  
→ [MySQL-교안-5-6주차.md](./MySQL-교안-5-6주차.md)
