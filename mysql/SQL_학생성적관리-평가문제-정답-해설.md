# SQL 평가 문제 - 학생 성적 관리 시스템 (정답 - 해설)

## 테이블 생성 CRUD 문제 (총 26점)

### 1. 테이블 생성 (10점)

**문제:** 다음과 같이 테이블을 정의하시오.

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

**해설:**
- `student` 테이블: 학생 정보를 저장하는 테이블
  - `student_no`: 자동 증가하는 기본키
  - `student_id`: 학생 ID (문자형 20자)
  - `name`: 이름 (문자형 10자)
  - `gender`: 성별 (문자형 1자)
  - `age`: 나이 (정수형)
  - `major`: 전공 (문자형 30자)
  - `phone`: 전화번호 (문자형 20자)
  - `email`: 이메일 (문자형 50자)

- `grade` 테이블: 성적 정보를 저장하는 테이블
  - `grade_no`: 자동 증가하는 기본키
  - `student_id`: 학생 ID (student 테이블의 student_id와 연결)
  - `subject`: 과목명 (문자형 30자)
  - `midterm`: 중간고사 점수 (정수형)
  - `final`: 기말고사 점수 (정수형)
  - `assignment`: 과제 점수 (정수형)

---

### 2. 데이터 삽입 (10점)

**문제:** student 테이블과 grade 테이블에 아래 데이터를 삽입하세요.

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

**해설:**
- `INSERT INTO` 문을 사용하여 테이블에 데이터를 삽입합니다.
- `student` 테이블에는 8명의 학생 정보가 삽입됩니다.
- `grade` 테이블에는 각 학생의 과목별 성적 정보가 삽입됩니다.
- `student_id`는 `student` 테이블의 `student_id`와 일치해야 합니다.

---

### 3. 조건부 조회 (3점)

**문제:** '컴퓨터공학과' 소속 학생들의 이메일을 출력하시오.

**정답:**
```sql
select email from student where major = "컴퓨터공학과";
```

**해설:**
- `WHERE` 절을 사용하여 특정 조건에 맞는 데이터만 조회합니다.
- `major = "컴퓨터공학과"` 조건으로 컴퓨터공학과 소속 학생만 필터링합니다.
- 결과: kimms@univ.ac.kr, parkjh@univ.ac.kr, hanje@univ.ac.kr

---

### 4. 데이터 수정 (3점)

**문제:** '2021001' 학생의 '데이터베이스' 과목 중간고사 점수를 90으로 수정하시오.

**정답:**
```sql
update grade set midterm = 90 where student_id = "2021001" and subject = "데이터베이스";
```

**해설:**
- `UPDATE` 문을 사용하여 기존 데이터를 수정합니다.
- `SET` 절에서 수정할 컬럼과 값을 지정합니다.
- `WHERE` 절로 수정할 행을 지정합니다. 여러 조건은 `AND`로 연결합니다.
- 주의: `WHERE` 절을 생략하면 모든 행이 수정되므로 반드시 조건을 지정해야 합니다.

---

### 5. JOIN을 이용한 집계 (3점)

**문제:** 경영학과 학생들의 총점(중간고사+기말고사+과제)을 출력하시오.

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

**해설:**
- 두 테이블을 조인하여 전공 정보와 성적 정보를 결합합니다.
- `INNER JOIN`을 사용하여 `student.student_id`와 `grade.student_id`를 연결합니다.
- `WHERE` 절로 경영학과만 필터링합니다.
- `midterm + final + assignment`로 총점을 계산합니다.
- 결과: 각 경영학과 학생의 과목별 총점이 출력됩니다.

---

## 집계함수 문제 (총 21점)

### 6. 평균값 구하기 (3점)

**문제:** STUDENT 테이블에서 학생들의 전체 평균 나이를 구하세요.

**정답:**
```sql
select avg(age) as 평균나이 from student;
```

**해설:**
- `AVG()` 집계함수를 사용하여 평균값을 계산합니다.
- `age` 컬럼의 평균값을 구합니다.
- 결과: (20+21+19+22+20+21+19+20)/8 = 20.25세

---

### 7. 합계 구하기 (3점)

**문제:** 모든 학생들의 중간고사 점수 합계를 구하세요.

**정답:**
```sql
select sum(midterm) as 중간고사_총점 from grade;
```

**해설:**
- `SUM()` 집계함수를 사용하여 합계를 계산합니다.
- `midterm` 컬럼의 모든 값을 합산합니다.
- 결과: 모든 학생의 중간고사 점수 합계

---

### 8. 그룹별 집계 (3점)

**문제:** 전공별(major) 학생 수를 구하세요.

**정답:**
```sql
select major, count(major) as 학생수 
from student 
group by major;
```

**해설:**
- `GROUP BY` 절을 사용하여 전공별로 그룹화합니다.
- `COUNT()` 집계함수로 각 그룹의 행 수를 계산합니다.
- 결과: 각 전공별 학생 수가 출력됩니다.

---

### 9. 최대값 구하기 (3점)

**문제:** 기말고사 점수가 가장 높은 점수를 출력하세요.

**정답:**
```sql
select max(final) as 최고기말고사점수 from grade;

-- 또는
select final as 최고기말고사점수 
from grade 
order by final desc 
limit 1;
```

**해설:**
- `MAX()` 집계함수를 사용하여 최대값을 구하는 것이 가장 효율적입니다.
- 또는 `ORDER BY`와 `LIMIT`을 사용할 수도 있지만, 집계함수가 더 적합합니다.
- 결과: 95점

---

### 10. 최소값 구하기 (3점)

**문제:** 중간고사 점수가 가장 낮은 점수를 구하세요.

**정답:**
```sql
select min(midterm) as 최저중간고사점수 from grade;

-- 또는
select midterm as 최저중간고사점수 
from grade 
order by midterm asc 
limit 1;
```

**해설:**
- `MIN()` 집계함수를 사용하여 최소값을 구하는 것이 가장 효율적입니다.
- 또는 `ORDER BY`와 `LIMIT`을 사용할 수도 있습니다.
- 결과: 75점

---

### 11. HAVING 절 사용 (3점)

**문제:** 전공별로 평균 나이가 20세 이상인 전공만 출력하세요.

**정답:**
```sql
select major, avg(age) as 평균나이 
from student 
group by major 
having avg(age) >= 20;
```

**해설:**
- `GROUP BY`로 전공별 그룹화 후 `HAVING` 절로 그룹에 대한 조건을 지정합니다.
- `WHERE` 절은 그룹화 전 행에 대한 조건이고, `HAVING` 절은 그룹화 후 집계 결과에 대한 조건입니다.
- `HAVING` 절에서는 집계함수를 사용한 조건을 지정할 수 있습니다.

---

### 12. 계산식과 집계함수 (3점)

**문제:** 성적(grade 테이블)에서 중간고사 + 기말고사 + 과제를 합산한 총점의 합계를 구하세요.

**정답:**
```sql
select sum(midterm + final + assignment) as 전체총점합계 from grade;
```

**해설:**
- 먼저 각 행에서 `midterm + final + assignment`를 계산한 후, `SUM()`으로 모든 행의 합계를 구합니다.
- 집계함수 내에서 산술 연산을 사용할 수 있습니다.
- 결과: 모든 학생의 (중간고사 + 기말고사 + 과제) 합계

---

## JOIN 문제 (총 20점)

### 13. 기본 JOIN (5점)

**문제:** 학생의 이름과 총점(중간고사 + 기말고사 + 과제)를 조회하시오.

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

**해설:**
- 두 테이블을 조인하여 학생 이름과 성적 정보를 결합합니다.
- `INNER JOIN`을 사용하는 방법이 표준적이고 가독성이 좋습니다.
- `ON` 절에서 조인 조건을 지정합니다: `student.student_id = grade.student_id`
- 각 학생의 이름과 (중간고사 + 기말고사 + 과제) 총점이 출력됩니다.

---

### 14. JOIN과 WHERE 조건 (5점)

**문제:** 컴퓨터공학과 학생의 이름, 과목, 총점을 출력하시오.

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

**해설:**
- `JOIN`으로 두 테이블을 결합한 후, `WHERE` 절로 전공 조건을 추가합니다.
- `major = "컴퓨터공학과"` 조건으로 컴퓨터공학과 학생만 선택합니다.
- 결과: 컴퓨터공학과 학생들의 이름, 과목, 총점이 출력됩니다.

---

### 15. JOIN과 LIKE 조건 (5점)

**문제:** '데이터베이스' 과목을 수강한 학생의 이름과 중간고사, 기말고사 점수를 조회하시오.

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

**해설:**
- `JOIN`으로 두 테이블을 결합한 후, `WHERE` 절에서 과목명 조건을 지정합니다.
- `LIKE "%데이터베이스%"`를 사용하여 과목명에 "데이터베이스"가 포함된 행을 찾습니다.
- `%`는 와일드카드로, 앞뒤에 어떤 문자가 와도 됨을 의미합니다.
- 결과: 데이터베이스 과목을 수강한 학생들의 정보가 출력됩니다.

---

### 16. JOIN과 비교 조건 (5점)

**문제:** 기말고사 점수가 90점 이상인 학생의 이름, 전공, 과목, 기말고사 점수를 출력하시오.

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

**해설:**
- `JOIN`으로 두 테이블을 결합한 후, `WHERE` 절에서 기말고사 점수 조건을 지정합니다.
- `>=` 비교 연산자를 사용하여 90점 이상인 경우를 필터링합니다.
- 결과: 기말고사 점수가 90점 이상인 학생들의 이름, 전공, 과목, 기말고사 점수가 출력됩니다.

---

## SQL 사용자 생성 / 데이터베이스 생성 / 삭제 문제 (총 30점)

### 17. 사용자(User) 생성 (5점)

**문제:** 관리자로 로그인한 상태에서, 아래 조건에 맞는 사용자를 생성하는 SQL을 작성하세요.
- 사용자 ID : student_admin
- 비밀번호 : Admin2024!

**정답:**
```sql
create user 'student_admin'@'localhost' identified by 'Admin2024!';
```

**해설:**
- `CREATE USER` 문을 사용하여 새로운 사용자를 생성합니다.
- `'student_admin'@'localhost'` 형식으로 사용자명과 호스트를 지정합니다.
  - `localhost`: 로컬에서만 접속 가능
  - `%`: 모든 호스트에서 접속 가능
- `IDENTIFIED BY`로 비밀번호를 설정합니다.
- 비밀번호는 보안을 위해 대소문자, 숫자, 특수문자를 포함하는 것이 좋습니다.

---

### 18. 사용자에게 권한 부여 (5점)

**문제:** student_admin 계정에 모든 데이터베이스의 모든 권한을 부여하세요.

**정답:**
```sql
grant all privileges on *.* to 'student_admin'@'localhost';
flush privileges;
```

**해설:**
- `GRANT` 문을 사용하여 사용자에게 권한을 부여합니다.
- `ALL PRIVILEGES`: 모든 권한 (SELECT, INSERT, UPDATE, DELETE, CREATE, DROP 등)
- `*.*`: 모든 데이터베이스의 모든 테이블
- `FLUSH PRIVILEGES`: 권한 변경사항을 즉시 적용합니다.

---

### 19. 특정 데이터베이스 권한 부여 (5점)

**문제:** 17번 문제에서 생성한 student_admin 계정에 아래 권한을 부여하세요.
student_db라는 데이터베이스의 조회(SELECT)와 데이터 삽입(INSERT), 수정(UPDATE) 권한을 부여하는 SQL을 작성하세요.

**정답:**
```sql
grant select, insert, update on student_db.* to 'student_admin'@'localhost';
flush privileges;
```

**해설:**
- `GRANT` 문에서 특정 권한만 지정할 수 있습니다.
- `SELECT, INSERT, UPDATE`: 조회, 삽입, 수정 권한만 부여
- `student_db.*`: student_db 데이터베이스의 모든 테이블
- `FLUSH PRIVILEGES`: 권한 변경사항을 즉시 적용합니다.

---

### 20. 데이터베이스 생성 (5점)

**문제:** 새로운 학생 관리용 데이터베이스 student_db를 생성하는 SQL을 작성하세요.

**정답:**
```sql
create database student_db;
```

**해설:**
- `CREATE DATABASE` 문을 사용하여 새로운 데이터베이스를 생성합니다.
- 데이터베이스 이름은 영문, 숫자, 언더스코어를 사용할 수 있습니다.
- 생성 후 `USE student_db;`로 해당 데이터베이스를 사용할 수 있습니다.

---

### 21. 사용자 삭제 (5점)

**문제:** student_admin라는 이름의 사용자를 삭제하는 SQL을 작성하세요.

**정답:**
```sql
drop user 'student_admin'@'localhost';
```

**해설:**
- `DROP USER` 문을 사용하여 사용자를 삭제합니다.
- 사용자명과 호스트를 정확히 지정해야 합니다.
- 삭제 전에 해당 사용자에게 부여된 권한이 자동으로 제거됩니다.
- 주의: 삭제된 사용자는 복구할 수 없습니다.

---

### 22. 데이터베이스 삭제 (5점)

**문제:** student_db 데이터베이스를 삭제하는 SQL을 작성하세요.

**정답:**
```sql
drop database student_db;
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

6. **WHERE vs HAVING**
   - `WHERE`: 그룹화 전 행에 대한 조건 (집계함수 사용 불가)
   - `HAVING`: 그룹화 후 집계 결과에 대한 조건 (집계함수 사용 가능)

7. **데이터 무결성**
   - `UPDATE`나 `DELETE` 문을 사용할 때는 반드시 `WHERE` 절을 확인해야 합니다.
   - `WHERE` 절을 생략하면 모든 행에 영향을 미치므로 매우 위험합니다.

8. **조인 조건**
   - 여러 테이블을 조인할 때는 조인 조건을 정확히 지정해야 합니다.
   - 조인 조건이 없거나 잘못되면 카티션 곱(Cartesian Product)이 발생할 수 있습니다.
