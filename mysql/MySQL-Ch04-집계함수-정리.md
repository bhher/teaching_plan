# MySQL Ch04 집계함수 정리

> COUNT · SUM · AVG · MIN · MAX · GROUP BY · HAVING · WITH ROLLUP · GROUPING · GROUP_CONCAT

**더미데이터:** [sql/ch04-집계함수-더미데이터.sql](./sql/ch04-집계함수-더미데이터.sql)  
**문제:** [MySQL-Ch04-집계함수-문제.md](./MySQL-Ch04-집계함수-문제.md)  
**정답:** [MySQL-Ch04-집계함수-정답.md](./MySQL-Ch04-집계함수-정답.md)

한빛무역 `고객` 예제(4-1~4-13)와 같은 개념입니다.  
실습은 **더미 DB `집계연습`** 또는 한빛무역 둘 다 가능합니다.

---

## 1. 집계함수란?

여러 행을 **하나의 값**으로 요약하는 함수입니다.

| 함수 | 의미 | NULL |
|------|------|------|
| `COUNT(*)` | 전체 행 수 | 행이 있으면 셈 |
| `COUNT(컬럼)` | 해당 컬럼이 **NULL이 아닌** 개수 | NULL 제외 |
| `SUM(컬럼)` | 합계 | NULL 제외 |
| `AVG(컬럼)` | 평균 | NULL 제외 |
| `MIN(컬럼)` | 최솟값 | NULL 제외 |
| `MAX(컬럼)` | 최댓값 | NULL 제외 |
| `GROUP_CONCAT(컬럼)` | 값을 문자열로 이어 붙임 | |

```sql
SELECT COUNT(*), COUNT(도시), COUNT(지역), SUM(마일리지), AVG(마일리지)
FROM 고객;
```

> `COUNT(*)`와 `COUNT(지역)`이 다르면 → **지역에 NULL이 있다**는 뜻.

---

## 2. WHERE + 집계 (그룹 없이)

조건에 맞는 행만 모은 뒤 집계합니다.

```sql
SELECT SUM(마일리지), AVG(마일리지), MIN(마일리지), MAX(마일리지)
FROM 고객
WHERE 도시 = '서울특별시';
```

**순서:** `FROM` → `WHERE`(행 걸러냄) → 집계

---

## 3. GROUP BY — 그룹별 집계

같은 값끼리 묶어서 집계합니다.

```sql
SELECT 도시
      ,COUNT(*) AS 고객수
      ,AVG(마일리지) AS 평균마일리지
FROM 고객
GROUP BY 도시;

-- 컬럼 위치 번호로도 가능 (비권장, 알아두기)
GROUP BY 1;
```

### 여러 컬럼으로 그룹

```sql
SELECT 담당자직위, 도시, COUNT(*) AS 고객수, AVG(마일리지) AS 평균마일리지
FROM 고객
GROUP BY 담당자직위, 도시
ORDER BY 1, 2;
```

**규칙:** `SELECT`에 나온 일반 컬럼은 `GROUP BY`에 있어야 합니다.  
(집계함수만 있는 컬럼은 예외)

---

## 4. HAVING — 그룹 조건

| | WHERE | HAVING |
|--|-------|--------|
| 시점 | **그룹 전** 행 필터 | **그룹 후** 결과 필터 |
| 집계함수 | 보통 사용 불가 | `COUNT`, `SUM` 등 **사용 가능** |

```sql
SELECT 도시, COUNT(*) AS 고객수, AVG(마일리지) AS 평균마일리지
FROM 고객
GROUP BY 도시
HAVING COUNT(*) >= 10;
```

### WHERE + GROUP BY + HAVING 함께

```sql
SELECT 도시, SUM(마일리지)
FROM 고객
WHERE 고객번호 LIKE 'T%'      -- 먼저 행 걸러냄
GROUP BY 도시
HAVING SUM(마일리지) >= 1000; -- 그룹 결과 걸러냄
```

```
FROM → WHERE → GROUP BY → 집계 → HAVING → ORDER BY
```

---

## 5. WITH ROLLUP — 소계·총계

그룹 결과에 **합계 행**을 추가합니다.

```sql
SELECT 도시, COUNT(*) AS 고객수, AVG(마일리지) AS 평균마일리지
FROM 고객
WHERE 지역 IS NULL
GROUP BY 도시
WITH ROLLUP;
```

총계 행의 도시가 NULL로 보이므로:

```sql
SELECT IFNULL(도시, '총계') AS 도시
      ,COUNT(*) AS 고객수
      ,AVG(마일리지) AS 평균마일리지
FROM 고객
WHERE 지역 IS NULL
GROUP BY 도시
WITH ROLLUP;
```

### 두 컬럼 ROLLUP

```sql
GROUP BY 담당자직위, 도시 WITH ROLLUP;
```

→ 직위+도시 소계, 직위 소계, 전체 총계 행이 생깁니다.

### GROUPING() — 총계 행 구분

```sql
SELECT 지역
      ,COUNT(*) AS 고객수
      ,GROUPING(지역) AS 구분   -- 총계 행이면 1, 아니면 0
FROM 고객
WHERE 담당자직위 = '대표 이사'
GROUP BY 지역
WITH ROLLUP;
```

---

## 6. GROUP_CONCAT — 값 이어 붙이기

```sql
-- 모든 이름을 한 줄로
SELECT GROUP_CONCAT(이름) FROM 사원;

-- 중복 지역 제거 후 연결
SELECT GROUP_CONCAT(DISTINCT 지역) FROM 고객;

-- 도시별 회사명 목록
SELECT 도시
      ,GROUP_CONCAT(고객회사명) AS 고객회사명목록
FROM 고객
GROUP BY 도시;
```

구분자 바꾸기: `GROUP_CONCAT(이름 SEPARATOR ' / ')`

---

## 7. 예제 번호 ↔ 개념 (한빛무역)

| 예제 | 핵심 |
|------|------|
| 4-1 | `COUNT(*)` vs `COUNT(컬럼)` |
| 4-2 | `SUM AVG MIN MAX` |
| 4-3 | WHERE + 집계 |
| 4-4 | `GROUP BY` / `GROUP BY 1` |
| 4-5 | 다중 `GROUP BY` |
| 4-6 | `HAVING` |
| 4-7 | WHERE + GROUP BY + HAVING |
| 4-8~10 | `WITH ROLLUP`, `IFNULL`, `GROUPING` |
| 4-11~13 | `GROUP_CONCAT` / `DISTINCT` / 그룹별 연결 |

---

## 8. 한 줄 요약

> **집계**로 요약하고, **GROUP BY**로 묶고, **HAVING**으로 그룹을 거르고,  
> **ROLLUP**으로 총계를 붙이고, **GROUP_CONCAT**으로 값을 이어 붙인다.
