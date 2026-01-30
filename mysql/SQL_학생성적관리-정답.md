# SQL 평가 문제 - 학생 성적 관리 시스템 (정답)

## 테이블 생성 CRUD 문제 (총 26점)

### 1. 테이블 생성 (10점)

**정답:**
```sql
create table student(
    student_no int auto_increment primary key,
    student_id char(20),
    name char(10),
    gender char(1),
    age int,
    major char(30),
    phone char(20),
    email char(50)
);

create table grade(
    grade_no int auto_increment primary key,
    student_id char(20),
    subject char(30),
    midterm int,
    final int,
    assignment int
);
```

---

### 2. 데이터 삽입 (10점)

**정답:**
```sql
-- student 테이블 데이터 삽입
insert into student(student_id,name,gender,age,major,phone,email) values("2021001","김민수","M",20,"컴퓨터공학과","010-1234-5678","kimms@univ.ac.kr");
insert into student(student_id,name,gender,age,major,phone,email) values("2021002","이영희","F",21,"경영학과","010-2345-6789","leeyh@univ.ac.kr");
insert into student(student_id,name,gender,age,major,phone,email) values("2021003","박준호","M",19,"컴퓨터공학과","010-3456-7890","parkjh@univ.ac.kr");
insert into student(student_id,name,gender,age,major,phone,email) values("2021004","최수진","F",22,"영어영문학과","010-4567-8901","choisj@univ.ac.kr");
insert into student(student_id,name,gender,age,major,phone,email) values("2021005","정대현","M",20,"경영학과","010-5678-9012","jungdh@univ.ac.kr");
insert into student(student_id,name,gender,age,major,phone,email) values("2021006","한지은","F",21,"컴퓨터공학과","010-6789-0123","hanje@univ.ac.kr");
insert into student(student_id,name,gender,age,major,phone,email) values("2021007","윤태영","M",19,"경영학과","010-7890-1234","yoonty@univ.ac.kr");
insert into student(student_id,name,gender,age,major,phone,email) values("2021008","강미영","F",20,"영어영문학과","010-8901-2345","kangmy@univ.ac.kr");

-- grade 테이블 데이터 삽입
insert into grade(student_id, subject, midterm, final, assignment) values("2021001","데이터베이스",85,90,95);
insert into grade(student_id, subject, midterm, final, assignment) values("2021001","자바프로그래밍",78,82,88);
insert into grade(student_id, subject, midterm, final, assignment) values("2021002","경영정보시스템",92,88,90);
insert into grade(student_id, subject, midterm, final, assignment) values("2021002","회계원리",75,80,85);
insert into grade(student_id, subject, midterm, final, assignment) values("2021003","데이터베이스",90,95,92);
insert into grade(student_id, subject, midterm, final, assignment) values("2021003","자바프로그래밍",88,85,90);
insert into grade(student_id, subject, midterm, final, assignment) values("2021004","영문학개론",95,92,98);
insert into grade(student_id, subject, midterm, final, assignment) values("2021004","영어회화",88,90,85);
insert into grade(student_id, subject, midterm, final, assignment) values("2021005","경영정보시스템",82,85,80);
insert into grade(student_id, subject, midterm, final, assignment) values("2021005","회계원리",90,88,92);
insert into grade(student_id, subject, midterm, final, assignment) values("2021006","데이터베이스",88,92,90);
insert into grade(student_id, subject, midterm, final, assignment) values("2021006","자바프로그래밍",85,88,87);
insert into grade(student_id, subject, midterm, final, assignment) values("2021007","경영정보시스템",75,78,80);
insert into grade(student_id, subject, midterm, final, assignment) values("2021007","회계원리",80,82,78);
insert into grade(student_id, subject, midterm, final, assignment) values("2021008","영문학개론",92,95,90);
insert into grade(student_id, subject, midterm, final, assignment) values("2021008","영어회화",90,88,92);
```

---

### 3. 조건부 조회 (3점)

**정답:**
```sql
select email from student where major = "컴퓨터공학과";
```

---

### 4. 데이터 수정 (3점)

**정답:**
```sql
update grade set midterm = 90 where student_id = "2021001" and subject = "데이터베이스";
```

---

### 5. JOIN을 이용한 집계 (3점)

**정답:**
```sql
-- 방법 1: INNER JOIN 사용
select (grade.midterm + grade.final + grade.assignment) as 총점
from student
inner join grade
on student.student_id = grade.student_id
where student.major = "경영학과";

-- 방법 2: 서브쿼리 사용
select (midterm + final + assignment) as 총점
from grade
where student_id in (select student_id from student where major = "경영학과");
```

---

## 집계함수 문제 (총 21점)

### 6. 평균값 구하기 (3점)

**정답:**
```sql
select avg(age) as 평균나이 from student;
```

---

### 7. 합계 구하기 (3점)

**정답:**
```sql
select sum(midterm) as 중간고사_총점 from grade;
```

---

### 8. 그룹별 집계 (3점)

**정답:**
```sql
select major, count(major) as 학생수 
from student 
group by major;
```

---

### 9. 최대값 구하기 (3점)

**정답:**
```sql
select max(final) as 최고기말고사점수 from grade;

-- 또는
select final as 최고기말고사점수 
from grade 
order by final desc 
limit 1;
```

---

### 10. 최소값 구하기 (3점)

**정답:**
```sql
select min(midterm) as 최저중간고사점수 from grade;

-- 또는
select midterm as 최저중간고사점수 
from grade 
order by midterm asc 
limit 1;
```

---

### 11. HAVING 절 사용 (3점)

**정답:**
```sql
select major, avg(age) as 평균나이 
from student 
group by major 
having avg(age) >= 20;
```

---

### 12. 계산식과 집계함수 (3점)

**정답:**
```sql
select sum(midterm + final + assignment) as 전체총점합계 from grade;
```

---

## JOIN 문제 (총 20점)

### 13. 기본 JOIN (5점)

**정답:**
```sql
-- 방법 1: INNER JOIN 사용 (권장)
select name, (midterm + final + assignment) as 총점
from student
inner join grade
on student.student_id = grade.student_id;

-- 방법 2: WHERE 절을 이용한 조인
select name, (midterm + final + assignment) as 총점
from student, grade
where student.student_id = grade.student_id;
```

---

### 14. JOIN과 WHERE 조건 (5점)

**정답:**
```sql
-- 방법 1: INNER JOIN 사용 (권장)
select name, subject, (midterm + final + assignment) as 총점
from student
inner join grade
on student.student_id = grade.student_id
where major = "컴퓨터공학과";

-- 방법 2: WHERE 절을 이용한 조인
select name, subject, (midterm + final + assignment) as 총점
from student, grade
where major = "컴퓨터공학과"
and student.student_id = grade.student_id;
```

---

### 15. JOIN과 LIKE 조건 (5점)

**정답:**
```sql
-- 방법 1: INNER JOIN 사용 (권장)
select name, midterm, final
from student
inner join grade
on student.student_id = grade.student_id
where subject like "%데이터베이스%";

-- 방법 2: WHERE 절을 이용한 조인
select name, midterm, final
from student, grade
where subject like "%데이터베이스%"
and student.student_id = grade.student_id;
```

---

### 16. JOIN과 비교 조건 (5점)

**정답:**
```sql
-- 방법 1: INNER JOIN 사용 (권장)
select name, major, subject, final
from student
inner join grade
on student.student_id = grade.student_id
where final >= 90;

-- 방법 2: WHERE 절을 이용한 조인
select name, major, subject, final
from student, grade
where final >= 90
and student.student_id = grade.student_id;
```

---

## SQL 사용자 생성 / 데이터베이스 생성 / 삭제 문제 (총 30점)

### 17. 사용자(User) 생성 (5점)

**정답:**
```sql
create user 'student_admin'@'localhost' identified by 'Admin2024!';
```

---

### 18. 사용자에게 권한 부여 (5점)

**정답:**
```sql
grant all privileges on *.* to 'student_admin'@'localhost';
flush privileges;
```

---

### 19. 특정 데이터베이스 권한 부여 (5점)

**정답:**
```sql
grant select, insert, update on student_db.* to 'student_admin'@'localhost';
flush privileges;
```

---

### 20. 데이터베이스 생성 (5점)

**정답:**
```sql
create database student_db;
```

---

### 21. 사용자 삭제 (5점)

**정답:**
```sql
drop user 'student_admin'@'localhost';
```

---

### 22. 데이터베이스 삭제 (5점)

**정답:**
```sql
drop database student_db;
```
