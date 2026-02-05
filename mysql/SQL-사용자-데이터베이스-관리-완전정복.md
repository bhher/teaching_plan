# SQL 사용자 생성 및 데이터베이스 관리 완전 정복

## 목차

1. [사용자(User) 관리](#사용자user-관리)
2. [권한(Grant) 관리](#권한grant-관리)
3. [데이터베이스 관리](#데이터베이스-관리)
4. [실전 예제](#실전-예제)
5. [문제 풀이](#문제-풀이)

---

## 사용자(User) 관리

### 1. 사용자 생성 (CREATE USER)

#### 기본 문법

```sql
CREATE USER '사용자ID'@'호스트' IDENTIFIED BY '비밀번호';
```

#### 호스트 지정 방법

| 호스트 | 설명 | 예시 |
|--------|------|------|
| `localhost` | 로컬에서만 접속 가능 | `'user'@'localhost'` |
| `%` | 모든 호스트에서 접속 가능 | `'user'@'%'` |
| `192.168.1.%` | 특정 IP 대역에서만 접속 | `'user'@'192.168.1.%'` |
| `'192.168.1.100'` | 특정 IP에서만 접속 | `'user'@'192.168.1.100'` |

#### 예제 1: 기본 사용자 생성

**문제:** 사용자 ID `testuser`, 비밀번호 `Test1234!`로 사용자를 생성하세요.

**정답:**
```sql
CREATE USER 'testuser'@'localhost' IDENTIFIED BY 'Test1234!';
```

**설명:**
- `CREATE USER`: 사용자 생성 명령어
- `'testuser'`: 사용자 ID
- `'localhost'`: 로컬에서만 접속 가능
- `IDENTIFIED BY 'Test1234!'`: 비밀번호 설정

**실행 결과:**
```
Query OK, 0 rows affected (0.01 sec)
```

---

#### 예제 2: 원격 접속 가능한 사용자 생성

**문제:** 모든 호스트에서 접속 가능한 사용자 `admin`을 생성하세요. 비밀번호는 `Admin@2024`입니다.

**정답:**
```sql
CREATE USER 'admin'@'%' IDENTIFIED BY 'Admin@2024';
```

**설명:**
- `'%'`: 모든 호스트에서 접속 가능
- 네트워크를 통한 원격 접속 허용

---

#### 예제 3: 특정 IP 대역에서만 접속 가능한 사용자 생성

**문제:** 192.168.1.x 대역에서만 접속 가능한 사용자 `developer`를 생성하세요.

**정답:**
```sql
CREATE USER 'developer'@'192.168.1.%' IDENTIFIED BY 'Dev1234!';
```

**설명:**
- `'192.168.1.%'`: 192.168.1.0 ~ 192.168.1.255 범위의 IP에서만 접속 가능
- 보안 강화를 위한 접속 제한

---

### 2. 사용자 삭제 (DROP USER)

#### 기본 문법

```sql
DROP USER '사용자ID'@'호스트';
```

#### 예제 1: 사용자 삭제

**문제:** `testuser` 사용자를 삭제하세요.

**정답:**
```sql
DROP USER 'testuser'@'localhost';
```

**또는 호스트 지정 없이:**
```sql
DROP USER 'testuser';
```

**설명:**
- `DROP USER`: 사용자 삭제 명령어
- 호스트를 지정하지 않으면 모든 호스트의 해당 사용자 삭제

**주의사항:**
- 사용자가 접속 중이면 삭제 불가
- 먼저 사용자의 모든 세션을 종료해야 함

---

#### 예제 2: 여러 사용자 동시 삭제

**문제:** `user1`과 `user2` 사용자를 동시에 삭제하세요.

**정답:**
```sql
DROP USER 'user1'@'localhost', 'user2'@'localhost';
```

**설명:**
- 쉼표로 구분하여 여러 사용자 동시 삭제 가능

---

### 3. 사용자 확인

#### 현재 사용자 확인

```sql
SELECT USER();
```

**결과:**
```
+------------------+
| USER()           |
+------------------+
| root@localhost   |
+------------------+
```

#### 모든 사용자 목록 확인

```sql
SELECT user, host FROM mysql.user;
```

**결과:**
```
+-----------+-----------+
| user      | host      |
+-----------+-----------+
| root      | localhost |
| testuser  | localhost |
| admin     | %         |
+-----------+-----------+
```

---

## 권한(Grant) 관리

### 1. 권한 부여 (GRANT)

#### 기본 문법

```sql
GRANT 권한1, 권한2 ON 데이터베이스.테이블 TO '사용자'@'호스트';
```

#### 주요 권한 종류

| 권한 | 설명 | 예시 |
|------|------|------|
| `SELECT` | 조회 권한 | 데이터 읽기 |
| `INSERT` | 삽입 권한 | 데이터 추가 |
| `UPDATE` | 수정 권한 | 데이터 수정 |
| `DELETE` | 삭제 권한 | 데이터 삭제 |
| `CREATE` | 생성 권한 | 테이블/DB 생성 |
| `DROP` | 삭제 권한 | 테이블/DB 삭제 |
| `ALTER` | 변경 권한 | 테이블 구조 변경 |
| `ALL` | 모든 권한 | 모든 권한 부여 |

#### 권한 범위 지정

| 범위 | 설명 | 예시 |
|------|------|------|
| `*.*` | 모든 데이터베이스의 모든 테이블 | `GRANT ALL ON *.* TO 'user'@'localhost'` |
| `데이터베이스.*` | 특정 데이터베이스의 모든 테이블 | `GRANT SELECT ON sampledb.* TO 'user'@'localhost'` |
| `데이터베이스.테이블` | 특정 테이블만 | `GRANT SELECT ON sampledb.member TO 'user'@'localhost'` |

---

### 2. 권한 부여 예제

#### 예제 1: 모든 권한 부여

**문제:** `testuser`에게 모든 데이터베이스의 모든 권한을 부여하세요.

**정답:**
```sql
GRANT ALL ON *.* TO 'testuser'@'localhost';
```

**설명:**
- `ALL`: 모든 권한 (SELECT, INSERT, UPDATE, DELETE, CREATE, DROP 등)
- `*.*`: 모든 데이터베이스의 모든 테이블
- 관리자 권한과 유사

**권한 적용:**
```sql
FLUSH PRIVILEGES;  -- 권한 즉시 적용
```

---

#### 예제 2: 특정 권한만 부여

**문제:** `testuser`에게 `sampledb` 데이터베이스의 조회(SELECT)와 삽입(INSERT) 권한만 부여하세요.

**정답:**
```sql
GRANT SELECT, INSERT ON sampledb.* TO 'testuser'@'localhost';
FLUSH PRIVILEGES;
```

**설명:**
- `SELECT`: 데이터 조회만 가능
- `INSERT`: 데이터 삽입만 가능
- `sampledb.*`: sampledb 데이터베이스의 모든 테이블
- UPDATE, DELETE는 불가능

**권한 확인:**
```sql
SHOW GRANTS FOR 'testuser'@'localhost';
```

**결과:**
```
+----------------------------------------------------------+
| Grants for testuser@localhost                            |
+----------------------------------------------------------+
| GRANT SELECT, INSERT ON `sampledb`.* TO 'testuser'@'localhost' |
+----------------------------------------------------------+
```

---

#### 예제 3: 특정 테이블에만 권한 부여

**문제:** `testuser`에게 `sampledb.member` 테이블의 SELECT 권한만 부여하세요.

**정답:**
```sql
GRANT SELECT ON sampledb.member TO 'testuser'@'localhost';
FLUSH PRIVILEGES;
```

**설명:**
- `sampledb.member`: 특정 테이블만 지정
- 다른 테이블에는 접근 불가

---

#### 예제 4: 여러 권한 동시 부여

**문제:** `testuser`에게 `sampledb` 데이터베이스의 SELECT, INSERT, UPDATE 권한을 부여하세요.

**정답:**
```sql
GRANT SELECT, INSERT, UPDATE ON sampledb.* TO 'testuser'@'localhost';
FLUSH PRIVILEGES;
```

**설명:**
- 쉼표로 구분하여 여러 권한 동시 부여
- DELETE 권한은 없으므로 삭제 불가

---

### 3. 권한 회수 (REVOKE)

#### 기본 문법

```sql
REVOKE 권한 ON 데이터베이스.테이블 FROM '사용자'@'호스트';
```

#### 예제 1: 특정 권한 회수

**문제:** `testuser`의 `sampledb` 데이터베이스에 대한 INSERT 권한을 회수하세요.

**정답:**
```sql
REVOKE INSERT ON sampledb.* FROM 'testuser'@'localhost';
FLUSH PRIVILEGES;
```

**설명:**
- `REVOKE`: 권한 회수 명령어
- SELECT 권한은 유지, INSERT 권한만 제거

---

#### 예제 2: 모든 권한 회수

**문제:** `testuser`의 모든 권한을 회수하세요.

**정답:**
```sql
REVOKE ALL ON *.* FROM 'testuser'@'localhost';
FLUSH PRIVILEGES;
```

**설명:**
- 모든 데이터베이스의 모든 권한 회수
- 사용자는 존재하지만 권한 없음

---

### 4. 권한 확인

#### 사용자 권한 확인

```sql
SHOW GRANTS FOR 'testuser'@'localhost';
```

**결과:**
```
+----------------------------------------------------------+
| Grants for testuser@localhost                            |
+----------------------------------------------------------+
| GRANT SELECT, INSERT ON `sampledb`.* TO 'testuser'@'localhost' |
+----------------------------------------------------------+
```

---

## 데이터베이스 관리

### 1. 데이터베이스 생성 (CREATE DATABASE)

#### 기본 문법

```sql
CREATE DATABASE 데이터베이스명
CHARACTER SET 문자셋
COLLATE 정렬규칙;
```

#### 예제 1: 기본 데이터베이스 생성

**문제:** `project_db`라는 이름의 데이터베이스를 생성하세요.

**정답:**
```sql
CREATE DATABASE project_db;
```

**설명:**
- 가장 간단한 형태
- 기본 문자셋과 정렬 규칙 사용

---

#### 예제 2: UTF-8 설정 포함 데이터베이스 생성

**문제:** `project_db` 데이터베이스를 UTF-8 설정과 함께 생성하세요.

**정답:**
```sql
CREATE DATABASE project_db
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;
```

**설명:**
- `CHARACTER SET utf8mb4`: UTF-8 문자셋 설정 (한글 지원)
- `COLLATE utf8mb4_unicode_ci`: 정렬 규칙 설정
- 한글 데이터를 올바르게 저장하기 위해 필수

---

#### 예제 3: 데이터베이스 존재 여부 확인 후 생성

**문제:** `project_db` 데이터베이스가 없으면 생성하세요.

**정답:**
```sql
CREATE DATABASE IF NOT EXISTS project_db;
```

**설명:**
- `IF NOT EXISTS`: 데이터베이스가 없을 때만 생성
- 이미 존재하면 오류 없이 무시
- 스크립트 실행 시 유용

---

### 2. 데이터베이스 삭제 (DROP DATABASE)

#### 기본 문법

```sql
DROP DATABASE 데이터베이스명;
```

#### 예제 1: 데이터베이스 삭제

**문제:** `project_db` 데이터베이스를 삭제하세요.

**정답:**
```sql
DROP DATABASE project_db;
```

**설명:**
- 데이터베이스와 모든 데이터 삭제
- **주의**: 복구 불가능하므로 신중히 사용

---

#### 예제 2: 안전한 삭제

**문제:** `project_db` 데이터베이스가 있으면 삭제하세요.

**정답:**
```sql
DROP DATABASE IF EXISTS project_db;
```

**설명:**
- `IF EXISTS`: 데이터베이스가 있을 때만 삭제
- 존재하지 않으면 오류 없이 무시
- 스크립트 실행 시 유용

---

### 3. 데이터베이스 확인

#### 모든 데이터베이스 목록 확인

```sql
SHOW DATABASES;
```

**결과:**
```
+--------------------+
| Database           |
+--------------------+
| information_schema |
| mysql              |
| performance_schema |
| project_db         |
| sampledb           |
| sys                |
+--------------------+
```

#### 특정 데이터베이스 정보 확인

```sql
SHOW CREATE DATABASE project_db;
```

**결과:**
```
+-------------+------------------------------------------------------------------+
| Database    | Create Database                                                  |
+-------------+------------------------------------------------------------------+
| project_db  | CREATE DATABASE `project_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 */ |
+-------------+------------------------------------------------------------------+
```

---

### 4. 데이터베이스 사용 (USE)

#### 기본 문법

```sql
USE 데이터베이스명;
```

#### 예제

**문제:** `project_db` 데이터베이스를 사용하세요.

**정답:**
```sql
USE project_db;
```

**설명:**
- 이후 SQL 문은 해당 데이터베이스에서 실행
- 테이블명 앞에 데이터베이스명 생략 가능

**사용 예시:**
```sql
USE project_db;
SELECT * FROM member;  -- project_db.member와 동일
```

---

## 실전 예제

### 시나리오 1: 개발자 계정 생성 및 권한 부여

**요구사항:**
1. 개발자용 사용자 `developer` 생성 (비밀번호: `Dev@2024`)
2. `project_db` 데이터베이스 생성
3. `developer`에게 `project_db`의 모든 권한 부여

**SQL:**
```sql
-- 1. 사용자 생성
CREATE USER 'developer'@'localhost' IDENTIFIED BY 'Dev@2024';

-- 2. 데이터베이스 생성
CREATE DATABASE project_db
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

-- 3. 권한 부여
GRANT ALL ON project_db.* TO 'developer'@'localhost';
FLUSH PRIVILEGES;

-- 4. 확인
SHOW GRANTS FOR 'developer'@'localhost';
```

---

### 시나리오 2: 읽기 전용 사용자 생성

**요구사항:**
1. 읽기 전용 사용자 `readonly` 생성
2. `sampledb` 데이터베이스의 SELECT 권한만 부여

**SQL:**
```sql
-- 1. 사용자 생성
CREATE USER 'readonly'@'localhost' IDENTIFIED BY 'ReadOnly123!';

-- 2. SELECT 권한만 부여
GRANT SELECT ON sampledb.* TO 'readonly'@'localhost';
FLUSH PRIVILEGES;

-- 3. 확인
SHOW GRANTS FOR 'readonly'@'localhost';
```

**권한 확인 결과:**
```
+----------------------------------------------------------+
| Grants for readonly@localhost                            |
+----------------------------------------------------------+
| GRANT SELECT ON `sampledb`.* TO 'readonly'@'localhost' |
+----------------------------------------------------------+
```

---

### 시나리오 3: 웹 애플리케이션 사용자 생성

**요구사항:**
1. 웹 애플리케이션용 사용자 `webapp` 생성
2. `project_db` 데이터베이스 생성
3. SELECT, INSERT, UPDATE 권한 부여 (DELETE 제외)

**SQL:**
```sql
-- 1. 사용자 생성
CREATE USER 'webapp'@'%' IDENTIFIED BY 'WebApp@2024';

-- 2. 데이터베이스 생성
CREATE DATABASE project_db
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

-- 3. 권한 부여 (DELETE 제외)
GRANT SELECT, INSERT, UPDATE ON project_db.* TO 'webapp'@'%';
FLUSH PRIVILEGES;
```

**설명:**
- `'%'`: 모든 호스트에서 접속 가능 (웹 서버에서 접속)
- DELETE 권한 없음: 데이터 삭제 방지

---

### 시나리오 4: 사용자 및 데이터베이스 정리

**요구사항:**
1. 테스트용 사용자 `testuser` 삭제
2. 테스트용 데이터베이스 `test_db` 삭제

**SQL:**
```sql
-- 1. 사용자 삭제
DROP USER 'testuser'@'localhost';

-- 2. 데이터베이스 삭제
DROP DATABASE IF EXISTS test_db;

-- 3. 확인
SELECT user, host FROM mysql.user WHERE user = 'testuser';
SHOW DATABASES LIKE 'test_db';
```

---

## 문제 풀이

### 문제 17: 사용자 생성 (5점)

**문제:** 사용자 ID `testuser`, 비밀번호 `Test1234!`로 사용자를 생성하세요.

**정답:**
```sql
CREATE USER 'testuser'@'localhost' IDENTIFIED BY 'Test1234!';
```

**해설:**
- `CREATE USER`: 사용자 생성 명령어
- `'testuser'`: 사용자 ID (따옴표 필수)
- `'localhost'`: 로컬에서만 접속 가능
- `IDENTIFIED BY 'Test1234!'`: 비밀번호 설정

**채점 기준:**
- CREATE USER 문법 정확: 2점
- 사용자 ID 정확: 1점
- 호스트 지정 정확: 1점
- 비밀번호 설정 정확: 1점

---

### 문제 18: 모든 권한 부여 (5점)

**문제:** `testuser`에게 모든 데이터베이스의 모든 권한을 부여하세요.

**정답:**
```sql
GRANT ALL ON *.* TO 'testuser'@'localhost';
FLUSH PRIVILEGES;
```

**해설:**
- `GRANT ALL`: 모든 권한 부여
- `*.*`: 모든 데이터베이스의 모든 테이블
- `FLUSH PRIVILEGES`: 권한 즉시 적용

**채점 기준:**
- GRANT 문법 정확: 2점
- ALL 권한 지정: 1점
- `*.*` 범위 지정: 1점
- FLUSH PRIVILEGES: 1점 (선택사항이지만 권장)

---

### 문제 19: 특정 권한 부여 (5점)

**문제:** `testuser`에게 `sampledb` 데이터베이스의 SELECT와 INSERT 권한만 부여하세요.

**정답:**
```sql
GRANT SELECT, INSERT ON sampledb.* TO 'testuser'@'localhost';
FLUSH PRIVILEGES;
```

**해설:**
- `SELECT, INSERT`: 조회와 삽입 권한만 부여
- `sampledb.*`: sampledb 데이터베이스의 모든 테이블
- UPDATE, DELETE 권한 없음

**채점 기준:**
- GRANT 문법 정확: 2점
- SELECT, INSERT 권한 지정: 2점
- 데이터베이스 범위 지정 (`sampledb.*`): 1점

---

### 문제 20: 데이터베이스 생성 (5점)

**문제:** `project_db` 데이터베이스를 생성하세요.

**정답:**
```sql
CREATE DATABASE project_db;
```

**또는 UTF-8 설정 포함:**
```sql
CREATE DATABASE project_db
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;
```

**해설:**
- `CREATE DATABASE`: 데이터베이스 생성 명령어
- UTF-8 설정은 선택사항이지만 권장

**채점 기준:**
- CREATE DATABASE 문법 정확: 3점
- 데이터베이스명 정확: 2점
- UTF-8 설정: 보너스 점수 (선택사항)

---

### 문제 21: 사용자 삭제 (5점)

**문제:** `testuser` 사용자를 삭제하세요.

**정답:**
```sql
DROP USER 'testuser'@'localhost';
```

**또는:**
```sql
DROP USER 'testuser';
```

**해설:**
- `DROP USER`: 사용자 삭제 명령어
- 호스트 지정 없이 삭제 가능

**채점 기준:**
- DROP USER 문법 정확: 3점
- 사용자 ID 정확: 2점

---

### 문제 22: 데이터베이스 삭제 (5점)

**문제:** `project_db` 데이터베이스를 삭제하세요.

**정답:**
```sql
DROP DATABASE project_db;
```

**해설:**
- `DROP DATABASE`: 데이터베이스 삭제 명령어
- **주의**: 모든 데이터가 삭제되므로 신중히 사용

**채점 기준:**
- DROP DATABASE 문법 정확: 3점
- 데이터베이스명 정확: 2점

---

## 추가 실전 예제

### 예제 1: 사용자 생성부터 권한 부여까지 전체 과정

**시나리오:** 새로운 프로젝트를 위한 사용자와 데이터베이스 설정

```sql
-- 1. 사용자 생성
CREATE USER 'project_user'@'localhost' IDENTIFIED BY 'Project@2024';

-- 2. 데이터베이스 생성
CREATE DATABASE project_db
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

-- 3. 권한 부여
GRANT SELECT, INSERT, UPDATE, DELETE ON project_db.* TO 'project_user'@'localhost';
FLUSH PRIVILEGES;

-- 4. 확인
SHOW GRANTS FOR 'project_user'@'localhost';
SHOW DATABASES LIKE 'project_db';
```

---

### 예제 2: 읽기 전용 사용자 생성

**시나리오:** 보고서 생성용 읽기 전용 계정

```sql
-- 1. 사용자 생성
CREATE USER 'report_user'@'localhost' IDENTIFIED BY 'Report123!';

-- 2. SELECT 권한만 부여
GRANT SELECT ON sampledb.* TO 'report_user'@'localhost';
FLUSH PRIVILEGES;

-- 3. 테스트
-- report_user로 로그인 후
USE sampledb;
SELECT * FROM member;  -- ✅ 가능
INSERT INTO member VALUES (...);  -- ❌ 불가능 (권한 없음)
```

---

### 예제 3: 특정 테이블에만 권한 부여

**시나리오:** 특정 테이블만 접근 가능한 사용자

```sql
-- 1. 사용자 생성
CREATE USER 'limited_user'@'localhost' IDENTIFIED BY 'Limited123!';

-- 2. 특정 테이블에만 권한 부여
GRANT SELECT, INSERT ON sampledb.member TO 'limited_user'@'localhost';
GRANT SELECT ON sampledb.book TO 'limited_user'@'localhost';
FLUSH PRIVILEGES;

-- 3. 확인
SHOW GRANTS FOR 'limited_user'@'localhost';
```

**결과:**
```
+----------------------------------------------------------+
| Grants for limited_user@localhost                        |
+----------------------------------------------------------+
| GRANT SELECT, INSERT ON `sampledb`.`member` TO 'limited_user'@'localhost' |
| GRANT SELECT ON `sampledb`.`book` TO 'limited_user'@'localhost' |
+----------------------------------------------------------+
```

---

### 예제 4: 권한 수정 (추가/회수)

**시나리오:** 기존 사용자의 권한 수정

```sql
-- 기존: SELECT, INSERT 권한만 있음
GRANT SELECT, INSERT ON sampledb.* TO 'testuser'@'localhost';

-- UPDATE 권한 추가
GRANT UPDATE ON sampledb.* TO 'testuser'@'localhost';
FLUSH PRIVILEGES;

-- INSERT 권한 회수
REVOKE INSERT ON sampledb.* FROM 'testuser'@'localhost';
FLUSH PRIVILEGES;

-- 최종 권한: SELECT, UPDATE만 가능
SHOW GRANTS FOR 'testuser'@'localhost';
```

---

### 예제 5: 사용자 비밀번호 변경

**시나리오:** 사용자 비밀번호 변경

```sql
-- 방법 1: ALTER USER 사용 (MySQL 5.7.6+)
ALTER USER 'testuser'@'localhost' IDENTIFIED BY 'NewPassword123!';

-- 방법 2: SET PASSWORD 사용
SET PASSWORD FOR 'testuser'@'localhost' = PASSWORD('NewPassword123!');
-- 또는
SET PASSWORD FOR 'testuser'@'localhost' = 'NewPassword123!';
```

---

## 주의사항 및 모범 사례

### 1. 보안 주의사항

#### 강력한 비밀번호 사용

```sql
-- ❌ 나쁜 예
CREATE USER 'user'@'localhost' IDENTIFIED BY '1234';

-- ✅ 좋은 예
CREATE USER 'user'@'localhost' IDENTIFIED BY 'MyP@ssw0rd2024!';
```

**비밀번호 요구사항:**
- 최소 8자 이상
- 대문자, 소문자, 숫자, 특수문자 포함
- 예측하기 어려운 조합

---

#### 최소 권한 원칙

```sql
-- ❌ 나쁜 예: 모든 권한 부여
GRANT ALL ON *.* TO 'app_user'@'localhost';

-- ✅ 좋은 예: 필요한 권한만 부여
GRANT SELECT, INSERT, UPDATE ON project_db.* TO 'app_user'@'localhost';
```

**원칙:**
- 필요한 최소한의 권한만 부여
- DELETE 권한은 신중히 부여
- DROP, ALTER 권한은 개발자에게만

---

### 2. 호스트 지정 주의사항

#### 로컬 접속만 허용 (권장)

```sql
CREATE USER 'user'@'localhost' IDENTIFIED BY 'password';
```

**장점:**
- 보안 강화
- 외부 접속 차단

---

#### 원격 접속 허용 (주의 필요)

```sql
CREATE USER 'user'@'%' IDENTIFIED BY 'password';
```

**주의사항:**
- 강력한 비밀번호 필수
- 방화벽 설정 확인
- 특정 IP 대역으로 제한 권장

```sql
-- ✅ 더 안전한 방법
CREATE USER 'user'@'192.168.1.%' IDENTIFIED BY 'password';
```

---

### 3. FLUSH PRIVILEGES

#### 언제 필요한가?

**필요한 경우:**
- `GRANT`, `REVOKE` 명령어 사용 후
- 권한 변경 즉시 적용 필요 시

**불필요한 경우:**
- `CREATE USER`, `DROP USER` 사용 시 (자동 적용)

```sql
-- 권한 부여 후
GRANT SELECT ON sampledb.* TO 'user'@'localhost';
FLUSH PRIVILEGES;  -- ✅ 권한 즉시 적용

-- 사용자 생성 후
CREATE USER 'user'@'localhost' IDENTIFIED BY 'password';
-- FLUSH PRIVILEGES 불필요 (자동 적용)
```

---

### 4. 데이터베이스 삭제 주의사항

#### 삭제 전 확인

```sql
-- 1. 데이터베이스 사용 중인지 확인
SHOW PROCESSLIST;

-- 2. 데이터베이스 내용 확인
USE project_db;
SHOW TABLES;
SELECT COUNT(*) FROM 각테이블;

-- 3. 백업 (선택사항)
-- mysqldump project_db > backup.sql

-- 4. 삭제
DROP DATABASE project_db;
```

**주의:**
- 삭제 후 복구 불가능
- 중요한 데이터는 반드시 백업
- 테스트 환경에서 먼저 확인

---

## 실전 문제 풀이

### 문제 세트 1: 기본 사용자 및 데이터베이스 관리

**문제 1:** 사용자 `student`를 생성하고 비밀번호를 `Student123!`로 설정하세요.

**정답:**
```sql
CREATE USER 'student'@'localhost' IDENTIFIED BY 'Student123!';
```

---

**문제 2:** `student`에게 `school_db` 데이터베이스의 SELECT, INSERT, UPDATE 권한을 부여하세요.

**정답:**
```sql
GRANT SELECT, INSERT, UPDATE ON school_db.* TO 'student'@'localhost';
FLUSH PRIVILEGES;
```

---

**문제 3:** `library_db` 데이터베이스를 UTF-8 설정과 함께 생성하세요.

**정답:**
```sql
CREATE DATABASE library_db
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;
```

---

### 문제 세트 2: 고급 권한 관리

**문제 1:** `admin` 사용자에게 모든 데이터베이스의 모든 권한을 부여하세요.

**정답:**
```sql
GRANT ALL ON *.* TO 'admin'@'localhost';
FLUSH PRIVILEGES;
```

---

**문제 2:** `readonly` 사용자에게 `sampledb.member` 테이블의 SELECT 권한만 부여하세요.

**정답:**
```sql
GRANT SELECT ON sampledb.member TO 'readonly'@'localhost';
FLUSH PRIVILEGES;
```

---

**문제 3:** `testuser`의 `sampledb`에 대한 DELETE 권한을 회수하세요.

**정답:**
```sql
REVOKE DELETE ON sampledb.* FROM 'testuser'@'localhost';
FLUSH PRIVILEGES;
```

---

## 체크리스트

### 사용자 관리
- [ ] CREATE USER 문법 이해
- [ ] 호스트 지정 방법 이해 (`localhost`, `%`, IP 대역)
- [ ] DROP USER 문법 이해
- [ ] 사용자 목록 확인 방법

### 권한 관리
- [ ] GRANT 문법 이해
- [ ] 주요 권한 종류 이해 (SELECT, INSERT, UPDATE, DELETE, ALL)
- [ ] 권한 범위 지정 이해 (`*.*`, `DB.*`, `DB.table`)
- [ ] REVOKE 문법 이해
- [ ] FLUSH PRIVILEGES 사용 시점 이해
- [ ] 권한 확인 방법

### 데이터베이스 관리
- [ ] CREATE DATABASE 문법 이해
- [ ] UTF-8 설정 방법
- [ ] DROP DATABASE 문법 이해
- [ ] 데이터베이스 목록 확인 방법
- [ ] USE 문법 이해

---

## 요약

### 사용자 생성 패턴

```sql
CREATE USER '사용자ID'@'호스트' IDENTIFIED BY '비밀번호';
```

### 권한 부여 패턴

```sql
GRANT 권한 ON 범위 TO '사용자'@'호스트';
FLUSH PRIVILEGES;
```

### 데이터베이스 생성 패턴

```sql
CREATE DATABASE 데이터베이스명
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;
```

### 핵심 포인트

1. **보안**: 강력한 비밀번호, 최소 권한 원칙
2. **호스트 지정**: `localhost` (로컬만) vs `%` (모든 호스트)
3. **권한 범위**: `*.*` (전체) vs `DB.*` (특정 DB) vs `DB.table` (특정 테이블)
4. **FLUSH PRIVILEGES**: 권한 변경 후 즉시 적용

---

**작성일:** 2026-01-30  
**범위:** MySQL 사용자 및 데이터베이스 관리
