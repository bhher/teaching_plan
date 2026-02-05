# SQL 사용자 및 데이터베이스 관리 실습 문제

## 목차

1. [기본 문제 (1-10번)](#기본-문제-1-10번)
2. [중급 문제 (11-20번)](#중급-문제-11-20번)
3. [고급 문제 (21-30번)](#고급-문제-21-30번)
4. [실전 시나리오 문제 (31-35번)](#실전-시나리오-문제-31-35번)
5. [정답 및 해설](#정답-및-해설)

---

## 기본 문제 (1-10번)

### 문제 1: 사용자 생성 (5점)

**문제:** 사용자 ID `student01`, 비밀번호 `Stu@2024`로 로컬에서만 접속 가능한 사용자를 생성하세요.

---

### 문제 2: 데이터베이스 생성 (5점)

**문제:** `school_db`라는 이름의 데이터베이스를 생성하세요. UTF-8 문자셋을 사용하세요.

---

### 문제 3: 기본 권한 부여 (5점)

**문제:** `student01` 사용자에게 `school_db` 데이터베이스의 SELECT 권한을 부여하세요.

---

### 문제 4: 여러 권한 동시 부여 (5점)

**문제:** `student01` 사용자에게 `school_db` 데이터베이스의 SELECT, INSERT, UPDATE 권한을 부여하세요.

---

### 문제 5: 사용자 목록 확인 (5점)

**문제:** 현재 MySQL에 등록된 모든 사용자 목록을 확인하는 SQL을 작성하세요.

---

### 문제 6: 데이터베이스 목록 확인 (5점)

**문제:** 현재 MySQL에 존재하는 모든 데이터베이스 목록을 확인하는 SQL을 작성하세요.

---

### 문제 7: 권한 확인 (5점)

**문제:** `student01` 사용자의 현재 권한을 확인하는 SQL을 작성하세요.

---

### 문제 8: 데이터베이스 사용 (5점)

**문제:** `school_db` 데이터베이스를 사용하도록 설정하는 SQL을 작성하세요.

---

### 문제 9: 사용자 삭제 (5점)

**문제:** `student01` 사용자를 삭제하는 SQL을 작성하세요.

---

### 문제 10: 데이터베이스 삭제 (5점)

**문제:** `school_db` 데이터베이스를 삭제하는 SQL을 작성하세요.

---

## 중급 문제 (11-20번)

### 문제 11: 원격 접속 가능한 사용자 생성 (5점)

**문제:** 모든 호스트에서 접속 가능한 사용자 `admin`을 생성하세요. 비밀번호는 `Admin@2024`입니다.

---

### 문제 12: 특정 IP 대역 접속 제한 (5점)

**문제:** 192.168.1.x 대역에서만 접속 가능한 사용자 `developer`를 생성하세요. 비밀번호는 `Dev@2024`입니다.

---

### 문제 13: 모든 권한 부여 (5점)

**문제:** `admin` 사용자에게 모든 데이터베이스의 모든 권한을 부여하세요.

---

### 문제 14: 특정 테이블 권한 부여 (5점)

**문제:** `student01` 사용자에게 `school_db.student` 테이블의 SELECT, INSERT 권한만 부여하세요.

---

### 문제 15: 권한 회수 (5점)

**문제:** `student01` 사용자의 `school_db` 데이터베이스에 대한 DELETE 권한을 회수하세요.

---

### 문제 16: 읽기 전용 사용자 생성 (5점)

**문제:** 읽기 전용 사용자 `readonly`를 생성하고, `school_db` 데이터베이스의 SELECT 권한만 부여하세요. 비밀번호는 `ReadOnly123!`입니다.

---

### 문제 17: 여러 사용자 동시 생성 (5점)

**문제:** `user1`과 `user2` 두 사용자를 한 번에 생성하세요. 비밀번호는 각각 `User1@2024`, `User2@2024`입니다.

---

### 문제 18: 데이터베이스 정보 확인 (5점)

**문제:** `school_db` 데이터베이스의 생성 정보(문자셋, 정렬 규칙 등)를 확인하는 SQL을 작성하세요.

---

### 문제 19: 현재 사용자 확인 (5점)

**문제:** 현재 로그인한 사용자를 확인하는 SQL을 작성하세요.

---

### 문제 20: 안전한 데이터베이스 생성 (5점)

**문제:** `test_db` 데이터베이스가 없을 경우에만 생성하는 SQL을 작성하세요. UTF-8 설정을 포함하세요.

---

## 고급 문제 (21-30번)

### 문제 21: 웹 애플리케이션 사용자 설정 (10점)

**문제:** 웹 애플리케이션용 사용자 `webapp`을 생성하고, `project_db` 데이터베이스를 생성한 후, SELECT, INSERT, UPDATE 권한을 부여하세요. (DELETE 권한은 제외) 비밀번호는 `WebApp@2024`입니다.

---

### 문제 22: 권한 수정 (추가) (5점)

**문제:** 기존에 SELECT, INSERT 권한만 있는 `student01` 사용자에게 UPDATE 권한을 추가로 부여하세요.

---

### 문제 23: 권한 수정 (회수) (5점)

**문제:** `student01` 사용자의 `school_db` 데이터베이스에 대한 INSERT 권한을 회수하세요.

---

### 문제 24: 여러 데이터베이스 권한 부여 (10점)

**문제:** `admin` 사용자에게 `school_db`와 `library_db` 두 데이터베이스의 모든 권한을 부여하세요.

---

### 문제 25: 사용자 비밀번호 변경 (5점)

**문제:** `student01` 사용자의 비밀번호를 `NewPass@2024`로 변경하세요.

---

### 문제 26: 특정 테이블만 접근 가능한 사용자 (10점)

**문제:** `limited_user` 사용자를 생성하고, `school_db.student` 테이블에는 SELECT, INSERT 권한을, `school_db.grade` 테이블에는 SELECT 권한만 부여하세요. 비밀번호는 `Limited123!`입니다.

---

### 문제 27: 여러 사용자 동시 삭제 (5점)

**문제:** `user1`과 `user2` 사용자를 한 번에 삭제하세요.

---

### 문제 28: 안전한 사용자 삭제 (5점)

**문제:** `testuser` 사용자가 존재하는 경우에만 삭제하는 SQL을 작성하세요.

---

### 문제 29: 안전한 데이터베이스 삭제 (5점)

**문제:** `test_db` 데이터베이스가 존재하는 경우에만 삭제하는 SQL을 작성하세요.

---

### 문제 30: 복합 권한 관리 (10점)

**문제:** 
1. `manager` 사용자를 생성하세요 (비밀번호: `Manager@2024`)
2. `company_db` 데이터베이스를 UTF-8 설정과 함께 생성하세요
3. `manager`에게 `company_db`의 SELECT, INSERT, UPDATE, DELETE 권한을 부여하세요
4. 권한을 확인하세요

---

## 실전 시나리오 문제 (31-35번)

### 문제 31: 개발 환경 설정 (15점)

**시나리오:** 새로운 프로젝트를 시작하기 위해 개발 환경을 설정해야 합니다.

**요구사항:**
1. 개발자용 사용자 `dev_user` 생성 (비밀번호: `Dev@2024`, 로컬 접속만)
2. `dev_project` 데이터베이스 생성 (UTF-8 설정)
3. `dev_user`에게 `dev_project`의 모든 권한 부여
4. 권한 확인

**SQL을 작성하세요.**

---

### 문제 32: 보고서 생성용 계정 설정 (15점)

**시나리오:** 외부 보고서 생성 시스템에서 데이터를 조회하기 위한 읽기 전용 계정이 필요합니다.

**요구사항:**
1. 읽기 전용 사용자 `report_user` 생성 (비밀번호: `Report@2024`, 모든 호스트에서 접속 가능)
2. `report_user`에게 `sales_db` 데이터베이스의 SELECT 권한만 부여
3. 권한 확인

**SQL을 작성하세요.**

---

### 문제 33: 권한 변경 및 테스트 (15점)

**시나리오:** 기존 사용자의 권한을 변경해야 합니다.

**초기 상태:**
- 사용자: `app_user`
- 권한: `sampledb` 데이터베이스의 SELECT, INSERT 권한

**요구사항:**
1. `app_user`에게 UPDATE 권한 추가
2. DELETE 권한은 부여하지 않음
3. 최종 권한 확인

**SQL을 작성하세요.**

---

### 문제 34: 사용자 및 데이터베이스 정리 (15점)

**시나리오:** 테스트가 완료되어 테스트용 사용자와 데이터베이스를 정리해야 합니다.

**요구사항:**
1. `test_user` 사용자 삭제
2. `test_db` 데이터베이스 삭제
3. 삭제 확인 (사용자 목록, 데이터베이스 목록 확인)

**SQL을 작성하세요.**

---

### 문제 35: 복합 시나리오 - 회사 데이터베이스 관리 (20점)

**시나리오:** 회사에서 새로운 부서를 위한 데이터베이스 시스템을 구축해야 합니다.

**요구사항:**
1. 부서별 사용자 생성:
   - `hr_dept` (인사부, 비밀번호: `Hr@2024`)
   - `sales_dept` (영업부, 비밀번호: `Sales@2024`)
   - `manager` (관리자, 비밀번호: `Manager@2024`)

2. 데이터베이스 생성:
   - `company_db` (UTF-8 설정)

3. 권한 부여:
   - `hr_dept`: `company_db`의 SELECT, INSERT, UPDATE 권한
   - `sales_dept`: `company_db`의 SELECT, INSERT 권한
   - `manager`: `company_db`의 모든 권한

4. 모든 사용자의 권한 확인

**SQL을 작성하세요.**

---

## 정답 및 해설

### 기본 문제 정답 (1-10번)

#### 문제 1: 사용자 생성

**정답:**
```sql
CREATE USER 'student01'@'localhost' IDENTIFIED BY 'Stu@2024';
```

**해설:**
- `CREATE USER`: 사용자 생성 명령어
- `'student01'`: 사용자 ID (따옴표 필수)
- `'localhost'`: 로컬에서만 접속 가능
- `IDENTIFIED BY 'Stu@2024'`: 비밀번호 설정

**채점 기준:**
- CREATE USER 문법 정확: 2점
- 사용자 ID 정확: 1점
- 호스트 지정 정확: 1점
- 비밀번호 설정 정확: 1점

---

#### 문제 2: 데이터베이스 생성

**정답:**
```sql
CREATE DATABASE school_db
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;
```

**해설:**
- `CREATE DATABASE`: 데이터베이스 생성 명령어
- `CHARACTER SET utf8mb4`: UTF-8 문자셋 설정 (한글 지원)
- `COLLATE utf8mb4_unicode_ci`: 정렬 규칙 설정

**채점 기준:**
- CREATE DATABASE 문법 정확: 2점
- 데이터베이스명 정확: 1점
- UTF-8 설정 정확: 2점

---

#### 문제 3: 기본 권한 부여

**정답:**
```sql
GRANT SELECT ON school_db.* TO 'student01'@'localhost';
FLUSH PRIVILEGES;
```

**해설:**
- `GRANT SELECT`: SELECT 권한 부여
- `school_db.*`: school_db 데이터베이스의 모든 테이블
- `FLUSH PRIVILEGES`: 권한 즉시 적용

**채점 기준:**
- GRANT 문법 정확: 2점
- SELECT 권한 지정: 1점
- 데이터베이스 범위 지정: 1점
- FLUSH PRIVILEGES: 1점

---

#### 문제 4: 여러 권한 동시 부여

**정답:**
```sql
GRANT SELECT, INSERT, UPDATE ON school_db.* TO 'student01'@'localhost';
FLUSH PRIVILEGES;
```

**해설:**
- 쉼표로 구분하여 여러 권한 동시 부여
- `school_db.*`: 해당 데이터베이스의 모든 테이블

**채점 기준:**
- GRANT 문법 정확: 2점
- SELECT, INSERT, UPDATE 권한 모두 지정: 2점
- FLUSH PRIVILEGES: 1점

---

#### 문제 5: 사용자 목록 확인

**정답:**
```sql
SELECT user, host FROM mysql.user;
```

**또는:**
```sql
SELECT user, host FROM mysql.user WHERE user = 'student01';
```

**해설:**
- `mysql.user`: MySQL 사용자 정보가 저장된 시스템 테이블
- `user`, `host`: 사용자 ID와 호스트 정보

**채점 기준:**
- SELECT 문법 정확: 2점
- mysql.user 테이블 사용: 2점
- user, host 컬럼 선택: 1점

---

#### 문제 6: 데이터베이스 목록 확인

**정답:**
```sql
SHOW DATABASES;
```

**해설:**
- `SHOW DATABASES`: 모든 데이터베이스 목록 표시

**채점 기준:**
- SHOW DATABASES 문법 정확: 5점

---

#### 문제 7: 권한 확인

**정답:**
```sql
SHOW GRANTS FOR 'student01'@'localhost';
```

**해설:**
- `SHOW GRANTS`: 특정 사용자의 권한 확인
- `FOR '사용자'@'호스트'`: 확인할 사용자 지정

**채점 기준:**
- SHOW GRANTS 문법 정확: 3점
- 사용자 및 호스트 지정 정확: 2점

---

#### 문제 8: 데이터베이스 사용

**정답:**
```sql
USE school_db;
```

**해설:**
- `USE`: 특정 데이터베이스를 사용하도록 설정
- 이후 SQL 문은 해당 데이터베이스에서 실행

**채점 기준:**
- USE 문법 정확: 3점
- 데이터베이스명 정확: 2점

---

#### 문제 9: 사용자 삭제

**정답:**
```sql
DROP USER 'student01'@'localhost';
```

**또는:**
```sql
DROP USER 'student01';
```

**해설:**
- `DROP USER`: 사용자 삭제 명령어
- 호스트 지정 없이 삭제 가능 (모든 호스트의 해당 사용자 삭제)

**채점 기준:**
- DROP USER 문법 정확: 3점
- 사용자 ID 정확: 2점

---

#### 문제 10: 데이터베이스 삭제

**정답:**
```sql
DROP DATABASE school_db;
```

**해설:**
- `DROP DATABASE`: 데이터베이스 삭제 명령어
- **주의**: 모든 데이터가 삭제되므로 신중히 사용

**채점 기준:**
- DROP DATABASE 문법 정확: 3점
- 데이터베이스명 정확: 2점

---

### 중급 문제 정답 (11-20번)

#### 문제 11: 원격 접속 가능한 사용자 생성

**정답:**
```sql
CREATE USER 'admin'@'%' IDENTIFIED BY 'Admin@2024';
```

**해설:**
- `'%'`: 모든 호스트에서 접속 가능
- 네트워크를 통한 원격 접속 허용

**채점 기준:**
- CREATE USER 문법 정확: 2점
- 사용자 ID 정확: 1점
- `'%'` 호스트 지정: 1점
- 비밀번호 설정 정확: 1점

---

#### 문제 12: 특정 IP 대역 접속 제한

**정답:**
```sql
CREATE USER 'developer'@'192.168.1.%' IDENTIFIED BY 'Dev@2024';
```

**해설:**
- `'192.168.1.%'`: 192.168.1.0 ~ 192.168.1.255 범위의 IP에서만 접속 가능
- 보안 강화를 위한 접속 제한

**채점 기준:**
- CREATE USER 문법 정확: 2점
- IP 대역 지정 정확: 2점
- 비밀번호 설정 정확: 1점

---

#### 문제 13: 모든 권한 부여

**정답:**
```sql
GRANT ALL ON *.* TO 'admin'@'localhost';
FLUSH PRIVILEGES;
```

**해설:**
- `ALL`: 모든 권한 (SELECT, INSERT, UPDATE, DELETE, CREATE, DROP 등)
- `*.*`: 모든 데이터베이스의 모든 테이블
- 관리자 권한과 유사

**채점 기준:**
- GRANT 문법 정확: 2점
- ALL 권한 지정: 1점
- `*.*` 범위 지정: 1점
- FLUSH PRIVILEGES: 1점

---

#### 문제 14: 특정 테이블 권한 부여

**정답:**
```sql
GRANT SELECT, INSERT ON school_db.student TO 'student01'@'localhost';
FLUSH PRIVILEGES;
```

**해설:**
- `school_db.student`: 특정 테이블만 지정
- 다른 테이블에는 접근 불가

**채점 기준:**
- GRANT 문법 정확: 2점
- SELECT, INSERT 권한 지정: 1점
- 특정 테이블 지정 (`school_db.student`): 1점
- FLUSH PRIVILEGES: 1점

---

#### 문제 15: 권한 회수

**정답:**
```sql
REVOKE DELETE ON school_db.* FROM 'student01'@'localhost';
FLUSH PRIVILEGES;
```

**해설:**
- `REVOKE`: 권한 회수 명령어
- DELETE 권한만 제거, 다른 권한은 유지

**채점 기준:**
- REVOKE 문법 정확: 2점
- DELETE 권한 지정: 1점
- 데이터베이스 범위 지정: 1점
- FLUSH PRIVILEGES: 1점

---

#### 문제 16: 읽기 전용 사용자 생성

**정답:**
```sql
CREATE USER 'readonly'@'localhost' IDENTIFIED BY 'ReadOnly123!';
GRANT SELECT ON school_db.* TO 'readonly'@'localhost';
FLUSH PRIVILEGES;
```

**해설:**
- 사용자 생성 후 SELECT 권한만 부여
- INSERT, UPDATE, DELETE 불가능

**채점 기준:**
- 사용자 생성 정확: 2점
- SELECT 권한만 부여: 2점
- FLUSH PRIVILEGES: 1점

---

#### 문제 17: 여러 사용자 동시 생성

**정답:**
```sql
CREATE USER 'user1'@'localhost' IDENTIFIED BY 'User1@2024',
            'user2'@'localhost' IDENTIFIED BY 'User2@2024';
```

**해설:**
- 쉼표로 구분하여 여러 사용자 동시 생성 가능

**채점 기준:**
- CREATE USER 문법 정확: 2점
- 두 사용자 모두 정확: 2점
- 비밀번호 설정 정확: 1점

---

#### 문제 18: 데이터베이스 정보 확인

**정답:**
```sql
SHOW CREATE DATABASE school_db;
```

**해설:**
- 데이터베이스의 생성 정보 확인
- 문자셋, 정렬 규칙 등 확인 가능

**채점 기준:**
- SHOW CREATE DATABASE 문법 정확: 3점
- 데이터베이스명 정확: 2점

---

#### 문제 19: 현재 사용자 확인

**정답:**
```sql
SELECT USER();
```

**해설:**
- 현재 로그인한 사용자 정보 반환
- `사용자ID@호스트` 형식으로 표시

**채점 기준:**
- SELECT USER() 문법 정확: 5점

---

#### 문제 20: 안전한 데이터베이스 생성

**정답:**
```sql
CREATE DATABASE IF NOT EXISTS test_db
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;
```

**해설:**
- `IF NOT EXISTS`: 데이터베이스가 없을 때만 생성
- 이미 존재하면 오류 없이 무시

**채점 기준:**
- CREATE DATABASE 문법 정확: 2점
- IF NOT EXISTS 사용: 1점
- UTF-8 설정: 2점

---

### 고급 문제 정답 (21-30번)

#### 문제 21: 웹 애플리케이션 사용자 설정

**정답:**
```sql
CREATE USER 'webapp'@'%' IDENTIFIED BY 'WebApp@2024';
CREATE DATABASE project_db
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;
GRANT SELECT, INSERT, UPDATE ON project_db.* TO 'webapp'@'%';
FLUSH PRIVILEGES;
```

**해설:**
- `'%'`: 모든 호스트에서 접속 가능 (웹 서버에서 접속)
- DELETE 권한 없음: 데이터 삭제 방지

**채점 기준:**
- 사용자 생성 정확: 2점
- 데이터베이스 생성 정확: 2점
- UTF-8 설정: 2점
- SELECT, INSERT, UPDATE 권한 부여: 3점
- FLUSH PRIVILEGES: 1점

---

#### 문제 22: 권한 수정 (추가)

**정답:**
```sql
GRANT UPDATE ON school_db.* TO 'student01'@'localhost';
FLUSH PRIVILEGES;
```

**해설:**
- 기존 권한에 UPDATE 권한 추가
- 기존 SELECT, INSERT 권한은 유지됨

**채점 기준:**
- GRANT 문법 정확: 2점
- UPDATE 권한 추가: 2점
- FLUSH PRIVILEGES: 1점

---

#### 문제 23: 권한 수정 (회수)

**정답:**
```sql
REVOKE INSERT ON school_db.* FROM 'student01'@'localhost';
FLUSH PRIVILEGES;
```

**해설:**
- INSERT 권한만 회수
- 다른 권한은 유지

**채점 기준:**
- REVOKE 문법 정확: 2점
- INSERT 권한 회수: 2점
- FLUSH PRIVILEGES: 1점

---

#### 문제 24: 여러 데이터베이스 권한 부여

**정답:**
```sql
GRANT ALL ON school_db.* TO 'admin'@'localhost';
GRANT ALL ON library_db.* TO 'admin'@'localhost';
FLUSH PRIVILEGES;
```

**해설:**
- 각 데이터베이스에 대해 별도로 권한 부여
- 두 개의 GRANT 문 사용

**채점 기준:**
- 첫 번째 GRANT 정확: 4점
- 두 번째 GRANT 정확: 4점
- FLUSH PRIVILEGES: 2점

---

#### 문제 25: 사용자 비밀번호 변경

**정답:**
```sql
ALTER USER 'student01'@'localhost' IDENTIFIED BY 'NewPass@2024';
```

**또는:**
```sql
SET PASSWORD FOR 'student01'@'localhost' = 'NewPass@2024';
```

**해설:**
- `ALTER USER`: 사용자 정보 변경 (MySQL 5.7.6+)
- `SET PASSWORD`: 비밀번호 변경

**채점 기준:**
- ALTER USER 또는 SET PASSWORD 문법 정확: 3점
- 사용자 지정 정확: 1점
- 새 비밀번호 설정 정확: 1점

---

#### 문제 26: 특정 테이블만 접근 가능한 사용자

**정답:**
```sql
CREATE USER 'limited_user'@'localhost' IDENTIFIED BY 'Limited123!';
GRANT SELECT, INSERT ON school_db.student TO 'limited_user'@'localhost';
GRANT SELECT ON school_db.grade TO 'limited_user'@'localhost';
FLUSH PRIVILEGES;
```

**해설:**
- 각 테이블에 대해 별도로 권한 부여
- `student` 테이블: SELECT, INSERT
- `grade` 테이블: SELECT만

**채점 기준:**
- 사용자 생성 정확: 2점
- student 테이블 권한 부여: 3점
- grade 테이블 권한 부여: 3점
- FLUSH PRIVILEGES: 2점

---

#### 문제 27: 여러 사용자 동시 삭제

**정답:**
```sql
DROP USER 'user1'@'localhost', 'user2'@'localhost';
```

**해설:**
- 쉼표로 구분하여 여러 사용자 동시 삭제 가능

**채점 기준:**
- DROP USER 문법 정확: 2점
- 두 사용자 모두 정확: 3점

---

#### 문제 28: 안전한 사용자 삭제

**정답:**
```sql
DROP USER IF EXISTS 'testuser'@'localhost';
```

**또는:**
```sql
DROP USER IF EXISTS 'testuser';
```

**해설:**
- `IF EXISTS`: 사용자가 존재할 때만 삭제
- 존재하지 않으면 오류 없이 무시

**채점 기준:**
- DROP USER 문법 정확: 2점
- IF EXISTS 사용: 2점
- 사용자 지정 정확: 1점

---

#### 문제 29: 안전한 데이터베이스 삭제

**정답:**
```sql
DROP DATABASE IF EXISTS test_db;
```

**해설:**
- `IF EXISTS`: 데이터베이스가 있을 때만 삭제
- 존재하지 않으면 오류 없이 무시

**채점 기준:**
- DROP DATABASE 문법 정확: 2점
- IF EXISTS 사용: 2점
- 데이터베이스명 정확: 1점

---

#### 문제 30: 복합 권한 관리

**정답:**
```sql
-- 1. 사용자 생성
CREATE USER 'manager'@'localhost' IDENTIFIED BY 'Manager@2024';

-- 2. 데이터베이스 생성
CREATE DATABASE company_db
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

-- 3. 권한 부여
GRANT SELECT, INSERT, UPDATE, DELETE ON company_db.* TO 'manager'@'localhost';
FLUSH PRIVILEGES;

-- 4. 권한 확인
SHOW GRANTS FOR 'manager'@'localhost';
```

**해설:**
- 전체 과정을 순서대로 실행
- 각 단계별 확인 가능

**채점 기준:**
- 사용자 생성 정확: 2점
- 데이터베이스 생성 정확: 2점
- UTF-8 설정: 1점
- 권한 부여 정확: 3점
- 권한 확인: 2점

---

### 실전 시나리오 문제 정답 (31-35번)

#### 문제 31: 개발 환경 설정

**정답:**
```sql
-- 1. 개발자용 사용자 생성
CREATE USER 'dev_user'@'localhost' IDENTIFIED BY 'Dev@2024';

-- 2. 데이터베이스 생성
CREATE DATABASE dev_project
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

-- 3. 권한 부여
GRANT ALL ON dev_project.* TO 'dev_user'@'localhost';
FLUSH PRIVILEGES;

-- 4. 권한 확인
SHOW GRANTS FOR 'dev_user'@'localhost';
```

**해설:**
- 개발 환경 구축을 위한 전체 과정
- 모든 권한 부여로 개발 편의성 확보

**채점 기준:**
- 사용자 생성 정확: 3점
- 데이터베이스 생성 정확: 4점
- UTF-8 설정: 2점
- 권한 부여 정확: 4점
- 권한 확인: 2점

---

#### 문제 32: 보고서 생성용 계정 설정

**정답:**
```sql
-- 1. 읽기 전용 사용자 생성
CREATE USER 'report_user'@'%' IDENTIFIED BY 'Report@2024';

-- 2. SELECT 권한만 부여
GRANT SELECT ON sales_db.* TO 'report_user'@'%';
FLUSH PRIVILEGES;

-- 3. 권한 확인
SHOW GRANTS FOR 'report_user'@'%';
```

**해설:**
- `'%'`: 모든 호스트에서 접속 가능 (외부 시스템 접속)
- SELECT 권한만으로 데이터 보호

**채점 기준:**
- 사용자 생성 정확 (원격 접속 가능): 4점
- SELECT 권한만 부여: 5점
- 권한 확인: 3점
- FLUSH PRIVILEGES: 3점

---

#### 문제 33: 권한 변경 및 테스트

**정답:**
```sql
-- 1. UPDATE 권한 추가
GRANT UPDATE ON sampledb.* TO 'app_user'@'localhost';
FLUSH PRIVILEGES;

-- 2. 최종 권한 확인
SHOW GRANTS FOR 'app_user'@'localhost';
```

**해설:**
- 기존 권한에 UPDATE 추가
- DELETE 권한은 부여하지 않음

**채점 기준:**
- UPDATE 권한 추가 정확: 6점
- DELETE 권한 부여하지 않음: 4점
- 권한 확인: 3점
- FLUSH PRIVILEGES: 2점

---

#### 문제 34: 사용자 및 데이터베이스 정리

**정답:**
```sql
-- 1. 사용자 삭제
DROP USER 'test_user'@'localhost';

-- 2. 데이터베이스 삭제
DROP DATABASE test_db;

-- 3. 삭제 확인
SELECT user, host FROM mysql.user WHERE user = 'test_user';
SHOW DATABASES LIKE 'test_db';
```

**해설:**
- 테스트 환경 정리
- 삭제 후 확인으로 검증

**채점 기준:**
- 사용자 삭제 정확: 4점
- 데이터베이스 삭제 정확: 4점
- 사용자 확인: 3점
- 데이터베이스 확인: 4점

---

#### 문제 35: 복합 시나리오 - 회사 데이터베이스 관리

**정답:**
```sql
-- 1. 부서별 사용자 생성
CREATE USER 'hr_dept'@'localhost' IDENTIFIED BY 'Hr@2024';
CREATE USER 'sales_dept'@'localhost' IDENTIFIED BY 'Sales@2024';
CREATE USER 'manager'@'localhost' IDENTIFIED BY 'Manager@2024';

-- 2. 데이터베이스 생성
CREATE DATABASE company_db
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

-- 3. 권한 부여
-- 인사부: SELECT, INSERT, UPDATE
GRANT SELECT, INSERT, UPDATE ON company_db.* TO 'hr_dept'@'localhost';

-- 영업부: SELECT, INSERT
GRANT SELECT, INSERT ON company_db.* TO 'sales_dept'@'localhost';

-- 관리자: 모든 권한
GRANT ALL ON company_db.* TO 'manager'@'localhost';

FLUSH PRIVILEGES;

-- 4. 모든 사용자의 권한 확인
SHOW GRANTS FOR 'hr_dept'@'localhost';
SHOW GRANTS FOR 'sales_dept'@'localhost';
SHOW GRANTS FOR 'manager'@'localhost';
```

**해설:**
- 부서별 역할에 따른 권한 차등 부여
- 최소 권한 원칙 적용

**채점 기준:**
- 세 사용자 생성 정확: 4점
- 데이터베이스 생성 정확: 3점
- UTF-8 설정: 2점
- hr_dept 권한 부여 정확: 3점
- sales_dept 권한 부여 정확: 3점
- manager 권한 부여 정확: 3점
- 권한 확인: 2점

---

## 총점 및 평가 기준

### 총점: 200점

- 기본 문제 (1-10번): 50점
- 중급 문제 (11-20번): 50점
- 고급 문제 (21-30번): 60점
- 실전 시나리오 문제 (31-35번): 40점

### 등급 기준

- **A (90점 이상)**: 모든 기본 개념 이해, 실전 문제 해결 가능
- **B (80-89점)**: 기본 개념 이해, 일부 고급 문제 해결 가능
- **C (70-79점)**: 기본 개념 이해, 기본 문제 해결 가능
- **D (60-69점)**: 기본 개념 부분 이해, 추가 학습 필요
- **F (60점 미만)**: 기본 개념 미흡, 기초 재학습 필요

---

## 학습 체크리스트

### 사용자 관리
- [ ] CREATE USER 문법 이해
- [ ] 호스트 지정 방법 이해 (`localhost`, `%`, IP 대역)
- [ ] DROP USER 문법 이해
- [ ] 사용자 목록 확인 방법
- [ ] 사용자 비밀번호 변경 방법

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
- [ ] IF EXISTS/IF NOT EXISTS 사용법

---

**작성일:** 2026-01-30  
**범위:** MySQL 사용자 및 데이터베이스 관리 실습 문제
