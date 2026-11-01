# MySQL Ch04 집계함수 — 정답

문제: [MySQL-Ch04-집계함수-문제.md](./MySQL-Ch04-집계함수-문제.md)  
정리: [MySQL-Ch04-집계함수-정리.md](./MySQL-Ch04-집계함수-정리.md)

```sql
USE 집계연습;
```

---

# Part A

## 1번
② 지역에 NULL이 있어서

## 2번
② HAVING

## 3번
② 소계·총계 행 추가

## 4번
총계(ROLLUP) 행이다. / 해당 컬럼이 집계용 NULL이다.

## 5번
`FROM` → `WHERE` → `GROUP BY` → `HAVING`

---

# Part B

## 6번
```sql
SELECT COUNT(*) AS 전체행
      ,COUNT(회원번호) AS 회원번호수
      ,COUNT(지역) AS 지역수
FROM 회원;
```

## 7번
```sql
SELECT SUM(포인트) AS 합계
      ,AVG(포인트) AS 평균
      ,MIN(포인트) AS 최소
      ,MAX(포인트) AS 최대
FROM 회원;
```

## 8번
```sql
SELECT SUM(포인트), AVG(포인트), MIN(포인트), MAX(포인트)
FROM 회원
WHERE 도시 = '서울특별시';
```

## 9번
```sql
SELECT 도시
      ,COUNT(*) AS 회원수
      ,AVG(포인트) AS 평균포인트
FROM 회원
GROUP BY 도시;
```

## 10번
```sql
SELECT 등급
      ,도시
      ,COUNT(*) AS 회원수
      ,AVG(포인트) AS 평균포인트
FROM 회원
GROUP BY 등급, 도시
ORDER BY 1, 2;
```

## 11번
```sql
SELECT 도시
      ,COUNT(*) AS 회원수
      ,AVG(포인트) AS 평균포인트
FROM 회원
GROUP BY 도시
HAVING COUNT(*) >= 3;
```

## 12번
```sql
SELECT 도시
      ,SUM(포인트) AS 포인트합계
FROM 회원
WHERE 회원번호 LIKE 'A%'
GROUP BY 도시
HAVING SUM(포인트) >= 100000;
```

## 13번
```sql
SELECT IFNULL(도시, '총계') AS 도시
      ,COUNT(*) AS 회원수
FROM 회원
WHERE 지역 IS NULL
GROUP BY 도시
WITH ROLLUP;
```

## 14번
```sql
SELECT 지역
      ,COUNT(*) AS 회원수
      ,GROUPING(지역) AS 구분
FROM 회원
WHERE 등급 = 'VIP'
GROUP BY 지역
WITH ROLLUP;
```

## 15번
```sql
SELECT 도시
      ,GROUP_CONCAT(회원명 SEPARATOR ', ') AS 회원명목록
FROM 회원
GROUP BY 도시;
```

---

# Part C

## 16번
```sql
SELECT GROUP_CONCAT(이름) AS 이름목록
FROM 직원;
```

## 17번
```sql
SELECT GROUP_CONCAT(DISTINCT 등급) AS 등급목록
FROM 회원;
```

## 18번
```sql
SELECT 주문월
      ,COUNT(*) AS 주문건수
      ,SUM(주문금액) AS 금액합계
FROM 주문
GROUP BY 주문월
ORDER BY 주문월;
```

## 19번
```sql
SELECT 주문월
      ,SUM(주문금액) AS 금액합계
FROM 주문
GROUP BY 주문월
HAVING SUM(주문금액) >= 50000
ORDER BY 주문월;
```

## 20번
```sql
SELECT IFNULL(등급, '전체') AS 등급
      ,COUNT(*) AS 회원수
      ,SUM(포인트) AS 포인트합계
      ,ROUND(AVG(포인트)) AS 포인트평균
FROM 회원
GROUP BY 등급
WITH ROLLUP
ORDER BY 포인트합계 DESC;
```

> `WITH ROLLUP`과 `ORDER BY`를 함께 쓰면 MySQL 버전에 따라 경고/제약이 있을 수 있습니다.  
> 그럴 때는 ROLLUP만 실행하거나, 서브쿼리로 감싸서 정렬하세요.

```sql
-- 호환용
SELECT *
FROM (
  SELECT IFNULL(등급, '전체') AS 등급
        ,COUNT(*) AS 회원수
        ,SUM(포인트) AS 포인트합계
        ,ROUND(AVG(포인트)) AS 포인트평균
  FROM 회원
  GROUP BY 등급
  WITH ROLLUP
) t
ORDER BY 포인트합계 DESC;
```

---

# 강사 팁

| 문항 | 대응 예제 개념 |
|------|----------------|
| 6 | 4-1 COUNT(*) vs COUNT(컬럼) |
| 7~8 | 4-2, 4-3 |
| 9~10 | 4-4, 4-5 GROUP BY |
| 11~12 | 4-6, 4-7 HAVING |
| 13~14 | 4-8~10 ROLLUP / GROUPING |
| 15~17 | 4-11~13 GROUP_CONCAT |
| 18~20 | 주문·종합 |

한빛무역 `고객`으로도 같은 유형을 내면 됩니다.  
더미(`집계연습`)는 예제와 **숫자·조건이 달라** 베끼기 어렵습니다.
