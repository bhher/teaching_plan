# MySQL Ch02·Ch03 정답

문제: [MySQL-Ch02-Ch03-문제.md](./MySQL-Ch02-Ch03-문제.md)  
정리: [MySQL-Ch02-Ch03-정리.md](./MySQL-Ch02-Ch03-정리.md)

---

# Part A

## 1번
② `*`

## 2번
① `AS`

## 3번
② NULL 비교는 `IS NULL`을 써야 해서

## 4번
② 글자 1개

## 5번
② `OR`로 두 직위 중 하나

## 6번 (예시)
마일리지가 가장 높은 고객 3명을 조회한다.

## 7번 (예시)
컬럼 **개수**, 컬럼 **순서(자료형 호환)**

---

# Part B

## 8번
```sql
SELECT 고객번호, 담당자명, 고객회사명
FROM 고객;
```

## 9번
```sql
SELECT 고객번호
      ,담당자명
      ,마일리지 AS 포인트
      ,마일리지 * 1.2 AS "20%인상"
FROM 고객;
```

## 10번
```sql
SELECT 고객번호, 담당자명, 마일리지
FROM 고객
WHERE 마일리지 >= 50000;
```

## 11번
```sql
SELECT 고객번호, 담당자명, 도시, 마일리지
FROM 고객
WHERE 도시 = '인천광역시'
ORDER BY 마일리지 ASC;
```

## 12번
```sql
SELECT *
FROM 고객
ORDER BY 마일리지 DESC
LIMIT 5;
```

## 13번
```sql
SELECT DISTINCT 담당자직위
FROM 고객;
```

## 14번
```sql
SELECT *
FROM 고객
WHERE 담당자직위 <> '대표 이사';
-- 또는: WHERE 담당자직위 != '대표 이사'
```

## 15번
```sql
SELECT *
FROM 고객
WHERE 도시 = '서울특별시'
AND 마일리지 > 10000;
```

## 16번
```sql
SELECT 고객번호, 담당자명, 도시
FROM 고객
WHERE 도시 = '서울특별시'
UNION
SELECT 고객번호, 담당자명, 도시
FROM 고객
WHERE 마일리지 < 500
ORDER BY 1;
```

## 17번
```sql
-- ① OR
SELECT 고객번호, 담당자명, 담당자직위
FROM 고객
WHERE 담당자직위 = '영업 과장'
OR 담당자직위 = '사원';

-- ② IN
SELECT 고객번호, 담당자명, 담당자직위
FROM 고객
WHERE 담당자직위 IN ('영업 과장', '사원');
```

## 18번
```sql
-- ①
SELECT 담당자명, 마일리지
FROM 고객
WHERE 마일리지 >= 50000
AND 마일리지 <= 150000;

-- ②
SELECT 담당자명, 마일리지
FROM 고객
WHERE 마일리지 BETWEEN 50000 AND 150000;
```

## 19번
```sql
SELECT *
FROM 고객
WHERE 도시 LIKE '%광역시'
AND (고객번호 LIKE '_A%' OR 고객번호 LIKE '__A%');
```

## 20번
```sql
SELECT *
FROM 고객
WHERE 지역 IS NULL;

UPDATE 고객
SET 지역 = NULL
WHERE 지역 = '';
```

---

# Part C

## 21번
`CHAR_LENGTH`

## 22번
② `REPLACE`

## 23번
`ROUND` → **123.6**  
`TRUNCATE` → **123.5**

## 24번 (예시)
`NOW()` (또는 `SYSDATE()`, `CURDATE()` 등)

## 25번
`0`

## 26번
`YES`

---

# Part D

## 27번
```sql
SELECT CONCAT_WS('/', '2026', '09', '18');
-- 결과: 2026/09/18
```

## 28번
```sql
SELECT LEFT('SQL 완전정복', 3)
      ,RIGHT('SQL 완전정복', 4);
```

## 29번
```sql
SELECT CEILING(45.2)
      ,FLOOR(45.2)
      ,ROUND(45.2);
-- 46, 45, 45
```

## 30번
```sql
SELECT YEAR(NOW())
      ,MONTH(NOW())
      ,DAY(NOW());
```

## 31번
```sql
SELECT ADDDATE(NOW(), 30);
-- 또는
SELECT ADDDATE(NOW(), INTERVAL 30 DAY);
```

## 32번
```sql
SELECT CAST('10' AS UNSIGNED)
      ,CONVERT(10, CHAR(2));
```

## 33번
```sql
SELECT IF(9800 * 600 > 5000000, '초과달성', '미달성');
```

## 34번
```sql
SELECT CASE
         WHEN 8000 * 600 > 5000000 THEN '초과달성'
         WHEN 8000 * 600 > 4000000 THEN '달성'
         ELSE '미달성'
       END;
```

## 35번
```sql
SELECT 담당자명
      ,마일리지
      ,IF(마일리지 >= 100000, 'VIP', '일반') AS 등급
FROM 고객
ORDER BY 마일리지 DESC
LIMIT 10;

-- CASE 버전
SELECT 담당자명
      ,마일리지
      ,CASE
         WHEN 마일리지 >= 100000 THEN 'VIP'
         ELSE '일반'
       END AS 등급
FROM 고객
ORDER BY 마일리지 DESC
LIMIT 10;
```

---

# 강사 팁

| 문항 | 포인트 |
|------|--------|
| 3, 20 | `IS NULL` vs `= ''` |
| 16 | `UNION` 컬럼 맞춤 + `ORDER BY 1` |
| 17~18 | `OR`=`IN`, 비교=`BETWEEN` |
| 19 | `LIKE` + 괄호 |
| 23 | ROUND vs TRUNCATE |
| 35 | 함수 + 테이블 SELECT 종합 |

예제 2-8은 원문이 잘린 형태(`SELECT 23 + 5 AS 더하기`)이므로, 수업에서는 `23-5`, `23*5`, `23/5`도 함께 보여 주면 좋습니다.
