# MySQL JOIN 입문 — 문제

**설명:** [MySQL-JOIN-입문-설명.md](./MySQL-JOIN-입문-설명.md)  
**정답:** [MySQL-JOIN-입문-정답.md](./MySQL-JOIN-입문-정답.md)  
**DB:** [sql/join-입문-member-orders.sql](./sql/join-입문-member-orders.sql)

```bash
mysql -u root -p --default-character-set=utf8mb4 < "경로\join-입문-member-orders.sql"
```

```sql
USE join_intro;
```

---

## Part A. 객관식

### 1번
JOIN의 목적으로 가장 적절한 것은?

① 테이블을 삭제한다  
② **여러 테이블을 연결해 한 번에 조회한다**  
③ 비밀번호를 암호화한다  
④ 인덱스를 만든다  

### 2번
`member`와 `orders`를 연결할 때, `member.member_id`는 보통 어떤 역할인가?

① 외래키(FK)  
② **기본키(PK) — orders.member_id가 FK로 참조**  
③ NULL 허용 컬럼  
④ 집계 함수  

### 3번
아래 SQL에서 두 테이블을 **연결하는 조건**은 어느 절인가?

```sql
FROM member
JOIN orders
ON member.member_id = orders.member_id
```

① FROM  ② JOIN  ③ **ON**  ④ SELECT  

### 4번
`JOIN`과 `ON`의 역할로 올바른 것은?

① JOIN = 조건 필터, ON = 테이블 연결  
② **JOIN = 테이블 연결, ON = 연결 기준 지정**  
③ JOIN = 정렬, ON = 그룹화  
④ JOIN = 삭제, ON = 수정  

### 5번
`member INNER JOIN orders` 결과 **행 수**는?

① 3  ② **4**  ③ 5  ④ 6  

### 6번
INNER JOIN 결과에 **박민수가 안 나오는** 이유로 적절한 것은?

① age가 35라서  
② **orders에 member_id=4 주문이 없어서**  
③ member에 없어서  
④ price가 없어서  

### 7번
LEFT JOIN에서 **왼쪽 테이블**에 해당하는 것은?

① orders  
② **member**  
③ product  
④ ON 절  

### 8번
박민수처럼 **주문이 없는 회원**을 LEFT JOIN 결과에서 찾을 때, WHERE 조건으로 적절한 것은?

① `WHERE m.name IS NULL`  
② `WHERE o.product = NULL`  
③ **`WHERE o.member_id IS NULL`**  
④ `WHERE m.member_id = 4`  

### 9번
INNER JOIN과 LEFT JOIN 비교로 **올바른** 것은?

① INNER JOIN은 왼쪽 전부, LEFT JOIN은 양쪽 교집합  
② **INNER JOIN은 양쪽 모두 있는 행만, LEFT JOIN은 왼쪽 전부 + 없으면 NULL**  
③ LEFT JOIN은 주문만 출력한다  
④ INNER JOIN은 NULL을 반드시 포함한다  

### 10번
RIGHT JOIN에 대한 설명으로 적절한 것은?

① 왼쪽 테이블을 모두 보여준다  
② **오른쪽 테이블을 모두 보여준다**  
③ 양쪽 모두 NULL만 출력한다  
④ GROUP BY와 같다  

---

## Part B. SQL 작성 (기본 JOIN)

### 11번
`member`와 `orders`를 JOIN하여 **모든 컬럼**을 조회하시오.

### 12번
JOIN 결과에서 **이름, 나이, 상품, 가격** (`name`, `age`, `product`, `price`)만 조회하시오.

### 13번
별칭 `m`, `o`로 `name`, `age`, `order_id`, `product`, `price` 를 조회하시오.

### 14번
홍길동(`member_id = 1`)의 주문만 JOIN으로 조회하시오.  
(이름, 나이, 주문번호, 상품, 가격)

### 15번
나이가 **30세 이상**인 회원의 이름·나이·주문 상품·가격을 JOIN으로 조회하시오.

---

## Part C. SQL 작성 (LEFT JOIN · GROUP BY · 3테이블)

### 16번
모든 회원을 **LEFT JOIN**으로 출력하시오.  
(`name`, `age`, `product`, `price` — **박민수**는 주문 NULL)

### 17번
**주문을 한 번도 하지 않은 회원**의 `member_id`, `name`을 조회하시오.  
(LEFT JOIN + `IS NULL` 패턴)

### 18번
회원별 **주문 횟수**를 구하시오.  
(`name`, `주문수` — LEFT JOIN + GROUP BY, 박민수는 0)

### 19번
`member`, `orders`, `product` **3개 테이블**을 JOIN하여  
`name`, `order_id`, `product_name`, `price`를 조회하시오.

### 20번 (심화)
회원별 주문 **금액 합계**를 구하시오.  
(`name`, `총금액` — LEFT JOIN + GROUP BY + SUM, 박민수는 0 또는 NULL)

---

## 채점

| Part | 문항 | 배점 |
|------|------|------|
| A | 1~10 | 30 |
| B | 11~15 | 35 |
| C | 16~20 | 35 |
| **합** | | **100** |

통과: 70점 + 11·12·16번 실행 성공
