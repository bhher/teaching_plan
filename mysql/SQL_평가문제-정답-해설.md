# SQL 평가 문제 - 정답 - 해설

## 테이블 생성 CRUD 문제 (총 26점)

### 1. 테이블 생성 (10점)

**문제:** 다음과 같이 테이블을 정의하시오.

**정답:**
```sql
create table worker(
    num int auto_increment primary key,
    id char(20),
    name char(10),
    gender char(1),
    age int,
    department char(20),
    phone char(20),
    address char(100)
);

create table salary(
    salary_id int auto_increment primary key,
    worker_id char(20),
    base_salary int,
    bonus int
);
```

**해설:**
- `worker` 테이블: 직원 정보를 저장하는 테이블
  - `num`: 자동 증가하는 기본키
  - `id`: 직원 ID (문자형 20자)
  - `name`: 이름 (문자형 10자)
  - `gender`: 성별 (문자형 1자)
  - `age`: 나이 (정수형)
  - `department`: 부서 (문자형 20자)
  - `phone`: 전화번호 (문자형 20자)
  - `address`: 주소 (문자형 100자)

- `salary` 테이블: 급여 정보를 저장하는 테이블
  - `salary_id`: 자동 증가하는 기본키
  - `worker_id`: 직원 ID (worker 테이블의 id와 연결)
  - `base_salary`: 기본급 (정수형)
  - `bonus`: 보너스 (정수형)

---

### 2. 데이터 삽입 (10점)

**문제:** worker 테이블과 salary 테이블에 아래 데이터를 삽입하세요.

**정답:**
```sql
-- worker 테이블 데이터 삽입
insert into worker(id,name,gender,age,department,phone,address) values("chkim","김창훈","M",28,"총무부","010-3838-8577","경기도 용인시");
insert into worker(id,name,gender,age,department,phone,address) values("yckim","김영철","M",32,"인사부","010-2222-3334","경기도 용인시");
insert into worker(id,name,gender,age,department,phone,address) values("yerin99","김예린","F",25,"총무부","010-7777-9999","서울시 강서구");
insert into worker(id,name,gender,age,department,phone,address) values("seoklee","이석훈","M",35,"영업부","010-8888-1111","서울시 마포구");
insert into worker(id,name,gender,age,department,phone,address) values("minseo88","박민서","F",29,"기획부","010-9999-2222","경기도 성남시");
insert into worker(id,name,gender,age,department,phone,address) values("hjun45","홍준표","M",41,"인사부","010-3333-4444","서울시 동작구");
insert into worker(id,name,gender,age,department,phone,address) values("suekim77","김수애","F",27,"마케팅부","010-1212-3434","부산시 해운대구");
insert into worker(id,name,gender,age,department,phone,address) values("jkwon09","권지민","F",30,"개발부","010-5656-7878","대전시 유성구");

-- salary 테이블 데이터 삽입
insert into salary(worker_id, base_salary, bonus) values("chkim",3000000,300000);
insert into salary(worker_id, base_salary, bonus) values("yckim",2800000,250000);
insert into salary(worker_id, base_salary, bonus) values("yerin99",2700000,200000);
insert into salary(worker_id, base_salary, bonus) values("seoklee",3500000,400000);
insert into salary(worker_id, base_salary, bonus) values("minseo88",3200000,300000);
insert into salary(worker_id, base_salary, bonus) values("hjun45",4000000,500000);
insert into salary(worker_id, base_salary, bonus) values("suekim77",2900000,200000);
insert into salary(worker_id, base_salary, bonus) values("jkwon09",3300000,350000);
```

**해설:**
- `INSERT INTO` 문을 사용하여 테이블에 데이터를 삽입합니다.
- `worker` 테이블에는 8명의 직원 정보가 삽입됩니다.
- `salary` 테이블에는 각 직원의 급여 정보가 삽입됩니다.
- `worker_id`는 `worker` 테이블의 `id`와 일치해야 합니다.

---

### 3. 조건부 조회 (3점)

**문제:** '인사부' 소속 사원들의 주소를 출력하시오.

**정답:**
```sql
select address from worker where department = "인사부";
```

**해설:**
- `WHERE` 절을 사용하여 특정 조건에 맞는 데이터만 조회합니다.
- `department = "인사부"` 조건으로 인사부 소속 직원만 필터링합니다.
- 결과: 경기도 용인시, 서울시 동작구

---

### 4. 데이터 수정 (3점)

**문제:** 'yerin99' 사원의 보너스를 500000으로 수정하시오.

**정답:**
```sql
update salary set bonus = 500000 where worker_id = "yerin99";
```

**해설:**
- `UPDATE` 문을 사용하여 기존 데이터를 수정합니다.
- `SET` 절에서 수정할 컬럼과 값을 지정합니다.
- `WHERE` 절로 수정할 행을 지정합니다. (WHERE 절을 생략하면 모든 행이 수정되므로 주의!)

---

### 5. JOIN을 이용한 집계 (3점)

**문제:** 총무부 사원의 급여 총액(기본급+보너스)을 출력하시오.

**정답:**
```sql
-- 방법 1: INNER JOIN 사용
select (salary.base_salary + salary.bonus) as 급여_총액
from worker
inner join salary
on worker.id = salary.worker_id
where worker.department = "총무부";

-- 방법 2: 서브쿼리 사용 (비추천 - 여러 행이 있을 경우 문제 발생 가능)
select (select (base_salary + bonus) as 급여_총액 from salary where worker_id = id) as 급여_총액
from worker
where worker.department = "총무부";
```

**해설:**
- 두 테이블을 조인하여 부서 정보와 급여 정보를 결합합니다.
- `INNER JOIN`을 사용하여 `worker.id`와 `salary.worker_id`를 연결합니다.
- `WHERE` 절로 총무부만 필터링합니다.
- `base_salary + bonus`로 급여 총액을 계산합니다.
- 결과: 김창훈(3,300,000원), 김예린(2,900,000원 또는 3,200,000원 - 수정 후)

---

## 집계함수 문제 (총 21점)

### 6. 평균값 구하기 (3점)

**문제:** WORKER 테이블에서 직원들의 전체 평균 나이를 구하세요.

**정답:**
```sql
select avg(age) as 평균나이 from worker;
```

**해설:**
- `AVG()` 집계함수를 사용하여 평균값을 계산합니다.
- `age` 컬럼의 평균값을 구합니다.
- 결과: (28+32+25+35+29+41+27+30)/8 = 30.875세

---

### 7. 합계 구하기 (3점)

**문제:** 모든 직원들의 총 기본급(base_salary) 합계를 구하세요.

**정답:**
```sql
select sum(base_salary) as 총기본급 from salary;
```

**해설:**
- `SUM()` 집계함수를 사용하여 합계를 계산합니다.
- `base_salary` 컬럼의 모든 값을 합산합니다.
- 결과: 24,300,000원

---

### 8. 그룹별 집계 (3점)

**문제:** 부서별(department) 직원 수를 구하세요.

**정답:**
```sql
select department, count(department) as 직원수 
from worker 
group by department;
```

**해설:**
- `GROUP BY` 절을 사용하여 부서별로 그룹화합니다.
- `COUNT()` 집계함수로 각 그룹의 행 수를 계산합니다.
- 결과: 각 부서별 직원 수가 출력됩니다.

---

### 9. 최대값 구하기 (3점)

**문제:** 보너스를 가장 많이 받은 금액을 출력하세요.

**정답:**
```sql
select max(bonus) as 최대보너스 from salary;

-- 또는
select bonus as 최대보너스 
from salary 
order by bonus desc 
limit 1;
```

**해설:**
- `MAX()` 집계함수를 사용하여 최대값을 구하는 것이 가장 효율적입니다.
- 또는 `ORDER BY`와 `LIMIT`을 사용할 수도 있지만, 집계함수가 더 적합합니다.
- 결과: 500,000원

---

### 10. 최소값 구하기 (3점)

**문제:** 기본급이 가장 낮은 직원의 급여를 구하세요.

**정답:**
```sql
select min(base_salary) as 최저기본급 from salary;

-- 또는
select base_salary as 최저기본급 
from salary 
order by base_salary asc 
limit 1;
```

**해설:**
- `MIN()` 집계함수를 사용하여 최소값을 구하는 것이 가장 효율적입니다.
- 또는 `ORDER BY`와 `LIMIT`을 사용할 수도 있습니다.
- 결과: 2,700,000원

---

### 11. HAVING 절 사용 (3점)

**문제:** 부서별로 평균 나이가 30세 이상인 부서만 출력하세요.

**정답:**
```sql
select department, avg(age) as 평균나이 
from worker 
group by department 
having avg(age) >= 30;
```

**해설:**
- `GROUP BY`로 부서별 그룹화 후 `HAVING` 절로 그룹에 대한 조건을 지정합니다.
- `WHERE` 절은 그룹화 전 행에 대한 조건이고, `HAVING` 절은 그룹화 후 집계 결과에 대한 조건입니다.
- `HAVING` 절에서는 집계함수를 사용한 조건을 지정할 수 있습니다.

---

### 12. 계산식과 집계함수 (3점)

**문제:** 급여(salary 테이블)에서 기본급 + 보너스를 합산한 총액을 구하세요.

**정답:**
```sql
select sum(base_salary + bonus) as 급여총액 from salary;
```

**해설:**
- 먼저 각 행에서 `base_salary + bonus`를 계산한 후, `SUM()`으로 모든 행의 합계를 구합니다.
- 집계함수 내에서 산술 연산을 사용할 수 있습니다.
- 결과: 모든 직원의 (기본급 + 보너스) 합계

---

## JOIN 문제 (총 20점)

### 13. 기본 JOIN (5점)

**문제:** 사원의 이름과 급여 총액(기본급 + 보너스)를 조회하시오.

**정답:**
```sql
-- 방법 1: INNER JOIN 사용 (권장)
select name, (base_salary + bonus) as 급여총액
from worker
inner join salary
on worker.id = salary.worker_id;

-- 방법 2: WHERE 절을 이용한 조인 (구식 방법)
select name, (base_salary + bonus) as 급여총액
from worker, salary
where worker.id = salary.worker_id;
```

**해설:**
- 두 테이블을 조인하여 직원 이름과 급여 정보를 결합합니다.
- `INNER JOIN`을 사용하는 방법이 표준적이고 가독성이 좋습니다.
- `ON` 절에서 조인 조건을 지정합니다: `worker.id = salary.worker_id`
- 각 직원의 이름과 (기본급 + 보너스)가 출력됩니다.

---

### 14. JOIN과 WHERE 조건 (5점)

**문제:** 경기도에 거주하는 사원의 이름, 부서, 급여 총액을 출력하시오.

**정답:**
```sql
-- 방법 1: INNER JOIN 사용 (권장)
select name, department, (base_salary + bonus) as 급여총액
from worker
inner join salary
on worker.id = salary.worker_id
where address like "%경기도%";

-- 방법 2: WHERE 절을 이용한 조인
select name, department, (base_salary + bonus) as 급여총액
from worker, salary
where address like "%경기도%"
and worker.id = salary.worker_id;
```

**해설:**
- `JOIN`으로 두 테이블을 결합한 후, `WHERE` 절로 주소 조건을 추가합니다.
- `LIKE "%경기도%"`를 사용하여 주소에 "경기도"가 포함된 행만 선택합니다.
- `%`는 와일드카드로, 앞뒤에 어떤 문자가 와도 됨을 의미합니다.
- 결과: 경기도에 거주하는 직원들의 정보가 출력됩니다.

---

### 15. JOIN과 LIKE 조건 (5점)

**문제:** 연구소 근무 사원의 이름과 기본급, 상여금을 조회하시오.

**정답:**
```sql
-- 방법 1: INNER JOIN 사용 (권장)
select name, base_salary, bonus
from worker
inner join salary
on worker.id = salary.worker_id
where department like "%연구%";

-- 방법 2: WHERE 절을 이용한 조인
select name, base_salary, bonus
from worker, salary
where department like "%연구%"
and worker.id = salary.worker_id;
```

**해설:**
- 현재 데이터에는 "연구소"라는 부서가 없지만, 문제의 의도는 `LIKE` 연산자 사용법을 묻는 것입니다.
- `LIKE "%연구%"`를 사용하여 부서명에 "연구"가 포함된 행을 찾습니다.
- 만약 "연구소" 부서가 있다면 해당 직원들의 정보가 출력됩니다.

---

### 16. JOIN과 비교 조건 (5점)

**문제:** 보너스가 300,000원 이상인 사원의 이름, 부서, 보너스를 출력하시오.

**정답:**
```sql
-- 방법 1: INNER JOIN 사용 (권장)
select name, department, bonus
from worker
inner join salary
on worker.id = salary.worker_id
where bonus >= 300000;

-- 방법 2: WHERE 절을 이용한 조인
select name, department, bonus
from worker, salary
where bonus >= 300000
and worker.id = salary.worker_id;
```

**해설:**
- `JOIN`으로 두 테이블을 결합한 후, `WHERE` 절에서 보너스 조건을 지정합니다.
- `>=` 비교 연산자를 사용하여 300,000원 이상인 경우를 필터링합니다.
- 결과: 보너스가 300,000원 이상인 직원들의 이름, 부서, 보너스가 출력됩니다.

---

## SQL 사용자 생성 / 데이터베이스 생성 / 삭제 문제 (총 30점)

### 17. 사용자(User) 생성 (5점)

**문제:** 관리자로 로그인한 상태에서, 아래 조건에 맞는 사용자를 생성하는 SQL을 작성하세요.
- 사용자 ID : testuser
- 비밀번호 : Test1234!

**정답:**
```sql
create user 'testuser'@'localhost' identified by 'Test1234!';
```

**해설:**
- `CREATE USER` 문을 사용하여 새로운 사용자를 생성합니다.
- `'testuser'@'localhost'` 형식으로 사용자명과 호스트를 지정합니다.
  - `localhost`: 로컬에서만 접속 가능
  - `%`: 모든 호스트에서 접속 가능
- `IDENTIFIED BY`로 비밀번호를 설정합니다.
- 비밀번호는 보안을 위해 대소문자, 숫자, 특수문자를 포함하는 것이 좋습니다.

---

### 18. 사용자에게 권한 부여 (5점)

**문제:** testuser 계정에 모든 데이터베이스의 모든 권한을 부여하세요.

**정답:**
```sql
grant all privileges on *.* to 'testuser'@'localhost';
flush privileges;
```

**해설:**
- `GRANT` 문을 사용하여 사용자에게 권한을 부여합니다.
- `ALL PRIVILEGES`: 모든 권한 (SELECT, INSERT, UPDATE, DELETE, CREATE, DROP 등)
- `*.*`: 모든 데이터베이스의 모든 테이블
- `FLUSH PRIVILEGES`: 권한 변경사항을 즉시 적용합니다.

---

### 19. 특정 데이터베이스 권한 부여 (5점)

**문제:** 17번 문제에서 생성한 testuser 계정에 아래 권한을 부여하세요.
sampledb라는 데이터베이스의 조회(SELECT)와 데이터 삽입(INSERT) 권한을 부여하는 SQL을 작성하세요.

**정답:**
```sql
grant select, insert on sampledb.* to 'testuser'@'localhost';
flush privileges;
```

**해설:**
- `GRANT` 문에서 특정 권한만 지정할 수 있습니다.
- `SELECT, INSERT`: 조회와 삽입 권한만 부여
- `sampledb.*`: sampledb 데이터베이스의 모든 테이블
- `FLUSH PRIVILEGES`: 권한 변경사항을 즉시 적용합니다.

---

### 20. 데이터베이스 생성 (5점)

**문제:** 새로운 프로젝트용 데이터베이스 project_db를 생성하는 SQL을 작성하세요.

**정답:**
```sql
create database project_db;
```

**해설:**
- `CREATE DATABASE` 문을 사용하여 새로운 데이터베이스를 생성합니다.
- 데이터베이스 이름은 영문, 숫자, 언더스코어를 사용할 수 있습니다.
- 생성 후 `USE project_db;`로 해당 데이터베이스를 사용할 수 있습니다.

---

### 21. 사용자 삭제 (5점)

**문제:** testuser라는 이름의 사용자를 삭제하는 SQL을 작성하세요.

**정답:**
```sql
drop user 'testuser'@'localhost';
```

**해설:**
- `DROP USER` 문을 사용하여 사용자를 삭제합니다.
- 사용자명과 호스트를 정확히 지정해야 합니다.
- 삭제 전에 해당 사용자에게 부여된 권한이 자동으로 제거됩니다.
- 주의: 삭제된 사용자는 복구할 수 없습니다.

---

### 22. 데이터베이스 삭제 (5점)

**문제:** project_db 데이터베이스를 삭제하는 SQL을 작성하세요.

**정답:**
```sql
drop database project_db;
```

**해설:**
- `DROP DATABASE` 문을 사용하여 데이터베이스를 삭제합니다.
- 데이터베이스 내의 모든 테이블과 데이터가 함께 삭제됩니다.
- 주의: 삭제된 데이터베이스와 데이터는 복구할 수 없으므로 신중하게 실행해야 합니다.
- 삭제 전에 백업을 권장합니다.

---

## 총점 요약

- 테이블 생성 CRUD 문제: 26점
- 집계함수 문제: 21점
- JOIN 문제: 20점
- SQL 사용자 생성/데이터베이스 생성/삭제 문제: 30점
- **총점: 97점**

---

## 참고사항

### SQL 문법 주의사항

1. **대소문자 구분**
   - MySQL에서는 기본적으로 대소문자를 구분하지 않지만, 일관성을 위해 키워드는 대문자로 작성하는 것이 좋습니다.

2. **문자열 따옴표**
   - MySQL에서는 작은따옴표(`'`)와 큰따옴표(`"`) 모두 사용 가능하지만, 표준 SQL에서는 작은따옴표를 권장합니다.

3. **세미콜론(;)**
   - SQL 문의 끝에는 세미콜론을 붙입니다.

4. **JOIN vs WHERE**
   - `INNER JOIN`을 사용하는 것이 표준적이고 가독성이 좋습니다.
   - `WHERE` 절을 이용한 조인은 구식 방법이지만 여전히 사용 가능합니다.

5. **집계함수와 GROUP BY**
   - 집계함수(`COUNT`, `SUM`, `AVG`, `MAX`, `MIN`)를 사용할 때 `GROUP BY`가 필요한 경우가 많습니다.
   - `HAVING` 절은 그룹화 후의 조건을 지정할 때 사용합니다.
