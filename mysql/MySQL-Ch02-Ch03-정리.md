# MySQL Ch02·Ch03 예제 정리

> SELECT 기본 · 조건 · 집합 · 문자열/숫자/날짜 함수 · 조건식

**문제:** [MySQL-Ch02-Ch03-문제.md](./MySQL-Ch02-Ch03-문제.md)  
**정답:** [MySQL-Ch02-Ch03-정답.md](./MySQL-Ch02-Ch03-정답.md)

실습 테이블: **`고객`** (예제 DB 기준)

---

## Ch02 — SELECT 기본

### 한눈에

| 예제 | 핵심 |
|------|------|
| 2-1 | `SELECT * FROM 고객` — 전체 조회 |
| 2-2 | 컬럼 선택, `AS` 별칭, 계산식 (`마일리지 * 1.1`) |
| 2-3 | `WHERE` 조건 |
| 2-4 | `WHERE` + `ORDER BY ... DESC` |
| 2-5 | `LIMIT n` — 상위 n행 |
| 2-6 | 정렬 후 `LIMIT` (TOP n) |
| 2-7 | `DISTINCT` — 중복 제거 |
| 2-8 | 산술 연산 (`+` 등) |
| 2-9 | 비교 연산 (`>= <= > < = != <>`) |
| 2-10 | `<>` (같지 않음) |
| 2-11 | `AND` |
| 2-12 | `UNION` (합집합, 중복 제거) + `ORDER BY` |
| 2-13 | `IS NULL` / 빈문자열 `''` / `UPDATE`로 NULL 정리 |
| 2-14 | `OR` ↔ `IN (...)` |
| 2-15 | 범위 ↔ `BETWEEN A AND B` |
| 2-16 | `LIKE` (`%`, `_`) + 괄호로 `AND`/`OR` 우선순위 |

### 문법 요약

```sql
SELECT 컬럼 [AS 별칭], 계산식
FROM 테이블
WHERE 조건
ORDER BY 컬럼 [ASC|DESC]
LIMIT 개수;
```

| 연산/키워드 | 의미 |
|-------------|------|
| `AS` | 별칭 (공백 있으면 `"10%인상된 마일리지"`처럼 따옴표) |
| `DISTINCT` | 중복 제거 |
| `AND` / `OR` | 그리고 / 또는 |
| `IN (a, b)` | a 또는 b |
| `BETWEEN a AND b` | a~b 포함 |
| `LIKE` | 패턴 (`%` 여러 글자, `_` 한 글자) |
| `IS NULL` | NULL 검사 (`= NULL` 아님!) |
| `UNION` | 두 SELECT 결과 합치기 (컬럼 개수·순서 맞출 것) |
| `ORDER BY 1` | 결과의 첫 번째 컬럼 기준 정렬 |

### NULL vs 빈문자열

```sql
WHERE 지역 IS NULL;   -- NULL
WHERE 지역 = '';      -- 빈 문자열 (다른 것!)

UPDATE 고객 SET 지역 = NULL WHERE 지역 = '';  -- 빈칸을 NULL로 정리
```

### LIKE 예 (2-16)

```sql
도시 LIKE '%광역시'           -- ~광역시로 끝남
고객번호 LIKE '_C%'           -- 2번째가 C
고객번호 LIKE '__C%'          -- 3번째가 C
```

---

## Ch03 — 함수

### 3-1~3-12 문자열

| 함수 | 역할 | 예 |
|------|------|-----|
| `CHAR_LENGTH` | 글자 수 | `'안녕'` → 2 |
| `LENGTH` | 바이트 수 | 한글은 보통 더 큼 |
| `CONCAT` / `CONCAT_WS` | 이어 붙이기 / 구분자 포함 | `CONCAT_WS('-', ...)` |
| `LEFT` / `RIGHT` / `SUBSTR` | 왼쪽·오른쪽·중간 자르기 | |
| `SUBSTRING_INDEX` | 구분자 기준 자르기 | 주소 시·구 |
| `LPAD` / `RPAD` | 왼쪽·오른쪽 채우기 | |
| `LTRIM` / `RTRIM` / `TRIM` | 공백(또는 문자) 제거 | |
| `FIELD` / `FIND_IN_SET` / `INSTR` / `LOCATE` | 위치 찾기 | |
| `ELT(n, ...)` | n번째 항목 | |
| `REPEAT` | 반복 | |
| `REPLACE` | 치환 | `.` → `-` |
| `REVERSE` | 뒤집기 | |

### 3-13~3-16 숫자

| 함수 | 역할 |
|------|------|
| `CEILING` / `FLOOR` / `ROUND` / `TRUNCATE` | 올림·버림·반올림· Truncate |
| `ABS` / `SIGN` | 절댓값 / 부호 |
| `MOD` / `%` | 나머지 |
| `POWER` / `SQRT` / `RAND` | 거듭제곱·제곱근·난수 |

### 3-17~3-21 날짜·시간

| 함수 | 역할 |
|------|------|
| `NOW` / `SYSDATE` / `CURDATE` / `CURTIME` | 현재 일시·날짜·시각 |
| `YEAR` `MONTH` `DAY` `HOUR` … | 부분 추출 |
| `DATEDIFF` / `TIMESTAMPDIFF` | 날짜 차이 |
| `ADDDATE` / `SUBDATE` | 날짜 더하기·빼기 |
| `LAST_DAY` / `DAYOFYEAR` / `MONTHNAME` / `WEEKDAY` | 말일·일차·월이름·요일 |

### 3-22~3-26 형변환·조건

| 함수 | 역할 |
|------|------|
| `CAST` / `CONVERT` | 형 변환 |
| `IF(조건, 참, 거짓)` | 간단 분기 |
| `IFNULL(값, 대체)` | NULL이면 대체 |
| `NULLIF(a, b)` | 같으면 NULL |
| `CASE WHEN ... THEN ... ELSE ... END` | 다중 분기 |

---

## 수업용 한 줄

> **Ch02:** 무엇을(`SELECT`) 어디서(`FROM`) 어떤 행(`WHERE`) 어떤 순(`ORDER BY`) 몇 개(`LIMIT`)  
> **Ch03:** 문자열·숫자·날짜를 **함수로 가공**, 조건은 `IF` / `CASE`
