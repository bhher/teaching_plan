# MySQL 교안 (1–2주차)

**목표**
- DB·테이블·SQL 개념 이해
- MySQL 설치·접속
- DATABASE / TABLE 생성
- INSERT · SELECT · UPDATE · DELETE

**전체 목차:** [MySQL-교안-전체.md](./MySQL-교안-전체.md)

---

## 📅 1주차 – DB 개념 & 환경

### 📌 학습 목표
- RDBMS와 MySQL의 역할 이해
- SQL 분류(DDL/DML/DQL) 구분
- Workbench 또는 CLI로 접속

### 📘 이론

#### 1.1 데이터베이스란?
데이터를 **체계적으로 저장·관리**하는 공간입니다.

```
데이터베이스 (school)
 ├── 테이블 student
 ├── 테이블 teacher
 └── 테이블 course
```

#### 1.2 RDBMS
**관계형 데이터베이스 관리 시스템**  
표(테이블)로 저장하고, 테이블끼리 **관계(키)** 로 연결합니다.

| 용어 | 설명 | 비유 |
|------|------|------|
| Database | DB 전체 | 파일 캐비닛 |
| Table | 표 | 엑셀 시트 |
| Row (레코드) | 한 줄 | 학생 1명 |
| Column (필드) | 열 | 이름, 점수… |
| Primary Key | 행을 구분하는 고유 값 | 학번 |

#### 1.3 MySQL
- 오픈소스 RDBMS
- 웹(회원, 게시판, 쇼핑몰)에서 많이 사용
- 표준 SQL + 자체 확장

#### 1.4 SQL 분류

| 분류 | 하는 일 | 예 |
|------|---------|-----|
| DDL | 구조 만들기 | CREATE, DROP, ALTER |
| DML | 데이터 넣기/고치기/지우기 | INSERT, UPDATE, DELETE |
| DQL | 조회 | SELECT |

### 🧪 실습

#### 접속 (CLI)
```bash
mysql -u root -p
```

#### 접속 확인
```sql
SELECT VERSION();
SHOW DATABASES;
```

#### Workbench
1. MySQL Connections → root 접속  
2. 쿼리 창에 SQL 입력 → 번개 아이콘 실행  

### ✅ 1주차 체크
- [ ] MySQL 설치·접속 성공
- [ ] `SHOW DATABASES;` 실행
- [ ] DDL / DML / DQL 구분 설명 가능

---

## 📅 2주차 – 테이블과 CRUD

### 📌 학습 목표
- DB·테이블 생성
- 자주 쓰는 자료형
- CRUD 전체 흐름

### 📘 이론

#### 2.1 DATABASE
```sql
CREATE DATABASE school
  DEFAULT CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci;

USE school;

SHOW DATABASES;
-- DROP DATABASE school;  -- 주의: 전체 삭제
```

#### 2.2 자주 쓰는 자료형

| 자료형 | 용도 | 예 |
|--------|------|-----|
| INT | 정수 | 나이, 학번 |
| BIGINT | 큰 정수 | 주문번호 |
| VARCHAR(n) | 가변 문자열 | 이름, 이메일 |
| CHAR(n) | 고정 문자열 | 성별 'M'/'F' |
| TEXT | 긴 글 | 게시글 |
| DATE | 날짜 | 1990-01-01 |
| DATETIME | 날짜+시간 | 가입일시 |
| DECIMAL(p,s) | 소수 | 가격 |
| BOOLEAN / TINYINT(1) | 참/거짓 | 사용여부 |

#### 2.3 CREATE TABLE
```sql
CREATE TABLE student (
  id        INT PRIMARY KEY AUTO_INCREMENT,
  name      VARCHAR(50) NOT NULL,
  age       INT,
  email     VARCHAR(100),
  enter_date DATE DEFAULT (CURRENT_DATE)
);

SHOW TABLES;
DESC student;   -- 또는 DESCRIBE student;
```

#### 2.4 INSERT (추가)
```sql
INSERT INTO student (name, age, email)
VALUES ('김민수', 20, 'min@test.com');

INSERT INTO student (name, age, email) VALUES
('이서연', 21, 'seo@test.com'),
('박준호', 19, 'jun@test.com');
```

#### 2.5 SELECT (조회)
```sql
SELECT * FROM student;
SELECT name, age FROM student;
SELECT name AS 이름, age AS 나이 FROM student;
```

#### 2.6 UPDATE (수정)
```sql
-- WHERE 없이 UPDATE 하면 전체 수정됨! 반드시 조건
UPDATE student
SET age = 22, email = 'new@test.com'
WHERE id = 1;
```

#### 2.7 DELETE (삭제)
```sql
DELETE FROM student WHERE id = 3;

-- 테이블 구조는 남기고 데이터만 전부 삭제
-- TRUNCATE TABLE student;
```

### 📐 CRUD 도식

```
CREATE TABLE  →  구조 만들기 (DDL)
      ↓
INSERT        →  행 추가     (Create)
SELECT        →  행 조회     (Read)
UPDATE        →  행 수정     (Update)
DELETE        →  행 삭제     (Delete)
```

### 🧪 실습 문제

**실습 DB:** [sql/00-시작-샘플.sql](./sql/00-시작-샘플.sql)

1. `school` DB와 `student` 테이블 생성  
2. 학생 5명 INSERT  
3. 나이가 20 이상인 학생만 SELECT  
4. id=2 학생의 email 수정  
5. id=5 학생 DELETE  

### ⚠️ 주의
- `UPDATE` / `DELETE` 는 **WHERE 필수** (실무·수업 모두)
- 한글은 `utf8mb4` 권장
- `DROP` / `TRUNCATE` 는 되돌리기 어려움

### ✅ 2주차 체크
- [ ] CREATE DATABASE / TABLE 작성
- [ ] INSERT 여러 행
- [ ] SELECT 컬럼 지정
- [ ] WHERE 있는 UPDATE / DELETE

---

## 다음 주 예고
조건 검색(LIKE, IN), 정렬, 집계 함수, GROUP BY  
→ [MySQL-교안-3-4주차.md](./MySQL-교안-3-4주차.md)
