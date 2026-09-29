# MySQL Ch06 서브쿼리 — 정답

문제: [MySQL-Ch06-서브쿼리-문제.md](./MySQL-Ch06-서브쿼리-문제.md)  
정리: [MySQL-Ch06-서브쿼리-정리.md](./MySQL-Ch06-서브쿼리-정리.md)

```sql
USE 한빛무역;  -- DB 이름에 맞게
```

---

## Part A

| 번호 | 정답 | 해설 |
|------|------|------|
| 1 | ② | 쿼리 안의 SELECT |
| 2 | ② | `=` 는 단일 값 |
| 3 | ③ | `> ANY` ⇔ `> MIN` |
| 4 | ② | `> ALL` ⇔ `> MAX` |
| 5 | ④ | 여러 행이면 `=` 오류 → `IN`/`ANY`/`ALL` |
| 6 | ② | 존재하면 참 |
| 7 | ② | HAVING = 그룹 후 조건 |
| 8 | JOIN (INNER JOIN) | |
| 9 | MIN, MAX | `> ANY`⇔`> MIN`, `> ALL`⇔`> MAX` |
| 10 | ① | (A)-(D), (B)-(C) |

---

## Part B

### 11번
```sql
SELECT 고객번호, 고객회사명, 담당자명, 마일리지
FROM 고객
WHERE 마일리지 = (
    SELECT MIN(마일리지)
    FROM 고객
);
```

### 12번
```sql
SELECT 고객회사명, 담당자명
FROM 고객
WHERE 고객번호 = (
    SELECT 고객번호
    FROM 주문
    WHERE 주문번호 = 'H0250'
);
```

### 13번
```sql
SELECT 고객회사명, 담당자명
FROM 고객
INNER JOIN 주문
  ON 고객.고객번호 = 주문.고객번호
WHERE 주문번호 = 'H0250';
```

### 14번
```sql
SELECT 담당자명, 고객회사명, 마일리지
FROM 고객
WHERE 마일리지 > (
    SELECT MIN(마일리지)
    FROM 고객
    WHERE 도시 = '서울특별시'
);
```

### 15번
```sql
SELECT COUNT(*) AS 주문건수
FROM 주문
WHERE 고객번호 IN (
    SELECT 고객번호
    FROM 고객
    WHERE 도시 = '부산광역시'
);
```

### 16번
```sql
SELECT 담당자명, 고객회사명, 마일리지
FROM 고객
WHERE 마일리지 > ANY (
    SELECT 마일리지
    FROM 고객
    WHERE 도시 = '부산광역시'
);
```

### 17번
```sql
SELECT 담당자명, 고객회사명, 마일리지
FROM 고객
WHERE 마일리지 > ALL (
    SELECT AVG(마일리지)
    FROM 고객
    GROUP BY 지역
);
```

### 18번
```sql
SELECT 고객번호, 고객회사명
FROM 고객
WHERE EXISTS (
    SELECT *
    FROM 주문
    WHERE 주문.고객번호 = 고객.고객번호
);
```

### 19번
```sql
SELECT 고객번호, 고객회사명
FROM 고객
WHERE 고객번호 IN (
    SELECT DISTINCT 고객번호
    FROM 주문
);
```

### 20번
```sql
SELECT 도시
      ,AVG(마일리지) AS 평균마일리지
FROM 고객
GROUP BY 도시
HAVING AVG(마일리지) > (
    SELECT AVG(마일리지)
    FROM 고객
);
```

### 21번
```sql
SELECT 고객번호, 고객회사명
FROM 고객
WHERE NOT EXISTS (
    SELECT *
    FROM 주문
    WHERE 주문.고객번호 = 고객.고객번호
);
```

### 22번
```sql
SELECT 담당자명, 고객회사명, 마일리지
FROM 고객
WHERE 마일리지 > (
    SELECT MIN(마일리지)
    FROM 고객
    WHERE 도시 = '부산광역시'
);
```

(16번과 동일 의미: `> ANY` ⇔ `> MIN`)

---

## 예제 6-1 ~ 6-8 원본 요약

| 예제 | 핵심 |
|------|------|
| 6-1 | `= (SELECT MAX…)` 단일 행 |
| 6-2 | 서브쿼리 = JOIN |
| 6-3 | `> (SELECT MIN…)` |
| 6-4 | `IN (SELECT …)` |
| 6-5 | `> ANY` ⇔ `> MIN` |
| 6-6 | `> ALL` + `GROUP BY` 평균 |
| 6-7 | `EXISTS` / `IN` / `JOIN` |
| 6-8 | `HAVING` + 전체 평균 서브쿼리 |
