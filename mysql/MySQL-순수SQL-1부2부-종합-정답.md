# MySQL 순수 SQL 1부~2부 종합 — 정답

문제: [MySQL-순수SQL-1부2부-종합-문제.md](./MySQL-순수SQL-1부2부-종합-문제.md)  
더미: [sql/순수SQL-1부2부-종합더미.sql](./sql/순수SQL-1부2부-종합더미.sql)

```sql
USE sql_full;
```

---

## Part A

| 번호 | 정답 | 해설 |
|------|------|------|
| 1 | ② | `CREATE DATABASE` |
| 2 | ② | FK가 PK를 참조 |
| 3 | ② | 모든 컬럼 |
| 4 | ② | `IS NULL` / `IS NOT NULL` |
| 5 | ② | 테이블 연결 조회 |
| 6 | ② | FROM 뒤가 LEFT JOIN의 왼쪽 |
| 7 | ② | `COUNT(컬럼)`은 NULL 제외 |
| 8 | ② | HAVING = 그룹 후 조건 |
| 9 | (예시) 포인트 높은 순 상위 3명 | |
| 10 | 강사 테이블의 모든 컬럼 | `별칭또는테이블.*` |

---

## Part B

### 11번
```sql
CREATE DATABASE IF NOT EXISTS sql_full
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE sql_full;
```

### 12번
```sql
CREATE TABLE 메모 (
  메모번호 INT PRIMARY KEY,
  내용     VARCHAR(100) NOT NULL,
  작성일   DATE
);
```

### 13번
```sql
INSERT INTO 수강생 (수강생번호, 이름, 도시, 연락처, 포인트)
VALUES (107, '강다은', '광주', '010-7777-1007', 100);
```

### 14번
```sql
UPDATE 수강생
SET 포인트 = 700
WHERE 수강생번호 = 104;
```

### 15번
```sql
DELETE FROM 수강생
WHERE 수강생번호 = 107;
```

---

## Part C

### 16번
```sql
SELECT * FROM 수강생;
```

### 17번
```sql
SELECT 이름, 포인트
FROM 수강생
WHERE 도시 = '서울';
```

(김하늘, 박서준, 오세훈)

### 18번
```sql
SELECT 이름, 도시, 포인트
FROM 수강생
WHERE 포인트 BETWEEN 500 AND 1200
ORDER BY 포인트 DESC;
```

또는 `포인트 >= 500 AND 포인트 <= 1200`

### 19번
```sql
SELECT *
FROM 수강생
WHERE 이름 LIKE '김%';
```

(김하늘)

### 20번
```sql
SELECT 수강생번호, 이름
FROM 수강생
WHERE 연락처 IS NULL;
```

(104 최유진)

### 21번
```sql
SELECT 이름, 포인트
FROM 수강생
ORDER BY 포인트 DESC
LIMIT 2;
```

(박서준 1500, 김하늘 1200)  
※ 14번 UPDATE를 실행했다면 순위가 달라질 수 있음 → **더미 재실행 후** 확인 권장

---

## Part D

### 22번
```sql
SELECT k.강좌명
      ,g.강사명
      ,k.수강료
FROM 강좌 k
INNER JOIN 강사 g
  ON k.강사번호 = g.강사번호;
```

(6행)

### 23번
```sql
SELECT s.이름
      ,k.강좌명
      ,e.점수
FROM 수강생 s
JOIN 수강 e
  ON s.수강생번호 = e.수강생번호
JOIN 강좌 k
  ON e.강좌번호 = k.강좌번호;
```

(8행)

### 24번
```sql
SELECT s.이름
      ,e.강좌번호
      ,e.점수
FROM 수강생 s
LEFT JOIN 수강 e
  ON s.수강생번호 = e.수강생번호;
```

정민아(105): `강좌번호`, `점수` = NULL  
김하늘·이도윤·박서준은 수강이 2건이라 이름 행이 2줄씩 나옴

### 25번
```sql
SELECT s.수강생번호
      ,s.이름
FROM 수강생 s
LEFT JOIN 수강 e
  ON s.수강생번호 = e.수강생번호
WHERE e.수강번호 IS NULL;
```

(105 정민아)

### 26번
```sql
SELECT k.강좌번호
      ,k.강좌명
FROM 강좌 k
LEFT JOIN 수강 e
  ON k.강좌번호 = e.강좌번호
WHERE e.수강번호 IS NULL;
```

(15 UI 디자인)

### 27번
```sql
SELECT k.강좌명
      ,g.강사명
FROM 강좌 k
JOIN 강사 g
  ON k.강사번호 = g.강사번호
WHERE k.카테고리 = 'DB';
```

| 강좌명 | 강사명 |
|--------|--------|
| MySQL 입문 | 박민수 |
| MySQL 심화 | 박민수 |

---

## Part E

### 28번
```sql
SELECT COUNT(*) AS 인원
FROM 수강생;
```

→ 6 (13번 INSERT 후라면 7)

### 29번
```sql
SELECT AVG(점수) AS 평균점수
      ,MAX(점수) AS 최고점
      ,MIN(점수) AS 최저점
FROM 수강;
```

점수 NULL(오세훈)은 AVG/MAX/MIN에서 **제외**

### 30번
```sql
SELECT 도시
      ,COUNT(*) AS 인원
      ,AVG(포인트) AS 평균포인트
FROM 수강생
GROUP BY 도시;
```

### 31번
```sql
SELECT 도시
      ,COUNT(*) AS 인원
FROM 수강생
GROUP BY 도시
HAVING COUNT(*) >= 2;
```

(서울 3명)

### 32번
```sql
SELECT k.강좌명
      ,COUNT(e.수강번호) AS 수강인원
      ,AVG(e.점수) AS 평균점수
FROM 강좌 k
LEFT JOIN 수강 e
  ON k.강좌번호 = e.강좌번호
GROUP BY k.강좌번호, k.강좌명;
```

UI 디자인은 수강인원 0, 평균점수 NULL

### 33번
```sql
SELECT k.강좌명
      ,COUNT(e.수강번호) AS 수강인원
FROM 강좌 k
JOIN 수강 e
  ON k.강좌번호 = e.강좌번호
GROUP BY k.강좌번호, k.강좌명
HAVING COUNT(e.수강번호) >= 2;
```

(MySQL 입문, HTML/CSS, Java 기초)

### 34번
```sql
SELECT s.이름
      ,COUNT(e.수강번호) AS 수강횟수
FROM 수강생 s
LEFT JOIN 수강 e
  ON s.수강생번호 = e.수강생번호
GROUP BY s.수강생번호, s.이름;
```

| 이름 | 수강횟수 |
|------|----------|
| 김하늘 | 2 |
| 이도윤 | 2 |
| 박서준 | 2 |
| 최유진 | 1 |
| 정민아 | 0 |
| 오세훈 | 1 |

`COUNT(*)`를 쓰면 정민아도 1이 되므로 **`COUNT(e.수강번호)`** 가 맞음

### 35번
```sql
SELECT g.강사명
      ,SUM(k.수강료) AS 수강료합계
FROM 강사 g
JOIN 강좌 k
  ON g.강사번호 = k.강사번호
GROUP BY g.강사번호, g.강사명;
```

| 강사명 | 수강료합계 |
|--------|------------|
| 박민수 | 450000 |
| 이서연 | 400000 |
| 최준호 | 230000 |
| 한지우 | 150000 |

---

## 핵심 복습

```text
CREATE DATABASE / TABLE  →  INSERT·UPDATE·DELETE
         ↓
      SELECT (WHERE, LIKE, ORDER BY, LIMIT)
         ↓
   JOIN (INNER / LEFT + IS NULL)
         ↓
 GROUP BY + COUNT·SUM·AVG + HAVING
```
