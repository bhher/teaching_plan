# SQL 평가 문제 - 정답

## 테이블 생성 CRUD 문제 (총 26점)

### 1. 테이블 생성 (10점)

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

---

### 2. 데이터 삽입 (10점)

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

---

### 3. 조건부 조회 (3점)

**정답:**
```sql
select address from worker where department = "인사부";
```

---

### 4. 데이터 수정 (3점)

**정답:**
```sql
update salary set bonus = 500000 where worker_id = "yerin99";
```

---

### 5. JOIN을 이용한 집계 (3점)

**정답:**
```sql
-- 방법 1: INNER JOIN 사용
select (salary.base_salary + salary.bonus) as 급여_총액
from worker
inner join salary
on worker.id = salary.worker_id
where worker.department = "총무부";

-- 방법 2: 서브쿼리 사용
select (select (base_salary + bonus) as 급여_총액 from salary where worker_id = id) as 급여_총액
from worker
where worker.department = "총무부";
```

---

## 집계함수 문제 (총 21점)

### 6. 평균값 구하기 (3점)

**정답:**
```sql
select avg(age) as 평균나이 from worker;
```

---

### 7. 합계 구하기 (3점)

**정답:**
```sql
select sum(base_salary) as 총기본급 from salary;
```

---

### 8. 그룹별 집계 (3점)

**정답:**
```sql
select department, count(department) as 직원수 
from worker 
group by department;
```

---

### 9. 최대값 구하기 (3점)

**정답:**
```sql
select max(bonus) as 최대보너스 from salary;

-- 또는
select bonus as 최대보너스 
from salary 
order by bonus desc 
limit 1;
```

---

### 10. 최소값 구하기 (3점)

**정답:**
```sql
select min(base_salary) as 최저기본급 from salary;

-- 또는
select base_salary as 최저기본급 
from salary 
order by base_salary asc 
limit 1;
```

---

### 11. HAVING 절 사용 (3점)

**정답:**
```sql
select department, avg(age) as 평균나이 
from worker 
group by department 
having avg(age) >= 30;
```

---

### 12. 계산식과 집계함수 (3점)

**정답:**
```sql
select sum(base_salary + bonus) as 급여총액 from salary;
```

---

## JOIN 문제 (총 20점)

### 13. 기본 JOIN (5점)

**정답:**
```sql
-- 방법 1: INNER JOIN 사용 (권장)
select name, (base_salary + bonus) as 급여총액
from worker
inner join salary
on worker.id = salary.worker_id;

-- 방법 2: WHERE 절을 이용한 조인
select name, (base_salary + bonus) as 급여총액
from worker, salary
where worker.id = salary.worker_id;
```

---

### 14. JOIN과 WHERE 조건 (5점)

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

---

### 15. JOIN과 LIKE 조건 (5점)

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

---

### 16. JOIN과 비교 조건 (5점)

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

---

## SQL 사용자 생성 / 데이터베이스 생성 / 삭제 문제 (총 30점)

### 17. 사용자(User) 생성 (5점)

**정답:**
```sql
create user 'testuser'@'localhost' identified by 'Test1234!';
```

---

### 18. 사용자에게 권한 부여 (5점)

**정답:**
```sql
grant all privileges on *.* to 'testuser'@'localhost';
flush privileges;
```

---

### 19. 특정 데이터베이스 권한 부여 (5점)

**정답:**
```sql
grant select, insert on sampledb.* to 'testuser'@'localhost';
flush privileges;
```

---

### 20. 데이터베이스 생성 (5점)

**정답:**
```sql
create database project_db;
```

---

### 21. 사용자 삭제 (5점)

**정답:**
```sql
drop user 'testuser'@'localhost';
```

---

### 22. 데이터베이스 삭제 (5점)

**정답:**
```sql
drop database project_db;
```
