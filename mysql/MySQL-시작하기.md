# MySQL 시작하기

---

## 목차

1. [MySQL이란?](#1-mysql이란)
2. [SQL 언어 분류](#2-sql-언어-분류)
3. [MySQL 설치](#3-mysql-설치)
4. [MySQL 접속](#4-mysql-접속)
5. [MySQL Workbench](#5-mysql-workbench)

---

## 1. MySQL이란?

### 1-1. MySQL 소개

**MySQL**은 세계에서 가장 많이 쓰이는 **오픈 소스의 관계형 데이터베이스 관리 시스템(RDBMS)**입니다.

- **다중 스레드, 다중 사용자**를 지원합니다
- **구조질의어(SQL)** 형식의 데이터베이스를 관리합니다
- **오픈소스**로 무료로 사용할 수 있습니다
- **높은 성능**과 **안정성**이 뛰어납니다

### 1-2. MySQL의 특징

| 특징 | 설명 |
|------|------|
| **오픈소스** | 무료로 사용 가능하며 소스 코드가 공개되어 있습니다 |
| **크로스 플랫폼** | Windows, Linux, macOS 등 다양한 운영체제에서 동작합니다 |
| **다중 사용자** | 여러 사용자가 동시에 데이터베이스에 접근할 수 있습니다 |
| **다중 스레드** | 여러 작업을 동시에 처리할 수 있습니다 |
| **표준 SQL** | SQL 표준을 따르므로 다른 데이터베이스와 호환성이 좋습니다 |
| **높은 성능** | 빠른 데이터 처리 속도와 최적화된 쿼리 실행 |
| **확장성** | 대규모 데이터베이스도 효율적으로 관리할 수 있습니다 |

### 1-3. MySQL 사용 분야

- **웹 애플리케이션**: 사용자 정보, 게시글, 댓글 저장
- **쇼핑몰**: 상품 정보, 주문 내역, 회원 정보 관리
- **학교 시스템**: 학생 정보, 성적 관리, 수강 신청
- **병원 시스템**: 환자 정보, 진료 기록, 예약 관리
- **금융 시스템**: 계좌 정보, 거래 내역 관리
- **소셜 미디어**: 사용자 프로필, 게시물, 친구 관계 관리

---

## 2. SQL 언어 분류

SQL(Structured Query Language)은 데이터베이스를 조작하기 위한 언어로, 용도에 따라 세 가지로 분류됩니다.

### 2-1. 데이터 정의 언어 (DDL: Data Definition Language)

**DDL**은 데이터베이스와 테이블의 **구조를 정의하고 변경**하는 언어입니다.

#### 주요 명령어

| 명령어 | 설명 | 예시 |
|--------|------|------|
| `CREATE` | 데이터베이스나 테이블을 생성 | `CREATE DATABASE school;`<br>`CREATE TABLE student (...);` |
| `ALTER` | 데이터베이스나 테이블의 구조를 수정 | `ALTER TABLE student ADD COLUMN email VARCHAR(100);` |
| `DROP` | 데이터베이스나 테이블을 삭제 | `DROP DATABASE school;`<br>`DROP TABLE student;` |
| `TRUNCATE` | 테이블의 모든 데이터를 삭제 (구조는 유지) | `TRUNCATE TABLE student;` |
| `RENAME` | 테이블 이름 변경 | `RENAME TABLE old_name TO new_name;` |

#### DDL 특징

- ✅ **자동 커밋**: DDL 명령어는 실행 시 자동으로 커밋됩니다 (되돌릴 수 없음)
- ✅ **트랜잭션 불가**: DDL은 트랜잭션 내에서 실행할 수 없습니다
- ✅ **구조 변경**: 데이터베이스나 테이블의 구조를 변경합니다

---

### 2-2. 데이터 조작 언어 (DML: Data Manipulation Language)

**DML**은 데이터베이스에 저장된 **데이터를 조작**하는 언어입니다.

#### 주요 명령어

| 명령어 | 설명 | 예시 |
|--------|------|------|
| `INSERT` | 새로운 데이터를 추가 | `INSERT INTO student VALUES (1, '홍길동', 20);` |
| `UPDATE` | 기존 데이터를 수정 | `UPDATE student SET age = 21 WHERE id = 1;` |
| `DELETE` | 데이터를 삭제 | `DELETE FROM student WHERE id = 1;` |
| `SELECT` | 데이터를 조회 | `SELECT * FROM student;` |

#### DML 특징

- ✅ **수동 커밋**: DML 명령어는 명시적으로 커밋해야 합니다
- ✅ **트랜잭션 가능**: 여러 DML 명령어를 하나의 트랜잭션으로 묶을 수 있습니다
- ✅ **롤백 가능**: 커밋 전에는 변경사항을 되돌릴 수 있습니다
- ✅ **데이터 조작**: 테이블의 데이터를 추가, 수정, 삭제, 조회합니다

---

### 2-3. 데이터 제어 언어 (DCL: Data Control Language)

**DCL**은 데이터베이스에 대한 **접근 권한을 제어**하는 언어입니다.

#### 주요 명령어

| 명령어 | 설명 | 예시 |
|--------|------|------|
| `GRANT` | 사용자에게 권한을 부여 | `GRANT SELECT ON school.* TO 'user'@'localhost';` |
| `REVOKE` | 사용자의 권한을 회수 | `REVOKE SELECT ON school.* FROM 'user'@'localhost';` |
| `COMMIT` | 트랜잭션을 확정 (변경사항 저장) | `COMMIT;` |
| `ROLLBACK` | 트랜잭션을 취소 (변경사항 취소) | `ROLLBACK;` |

#### DCL 특징

- ✅ **권한 관리**: 사용자에게 데이터베이스 접근 권한을 부여하거나 회수합니다
- ✅ **트랜잭션 제어**: 데이터 변경사항을 확정하거나 취소합니다
- ✅ **보안**: 데이터베이스의 보안을 관리합니다

---

### 2-4. SQL 언어 분류 요약

```
SQL 언어
├── DDL (Data Definition Language)
│   ├── CREATE: 생성
│   ├── ALTER: 수정
│   └── DROP: 삭제
│
├── DML (Data Manipulation Language)
│   ├── INSERT: 추가
│   ├── UPDATE: 수정
│   ├── DELETE: 삭제
│   └── SELECT: 조회
│
└── DCL (Data Control Language)
    ├── GRANT: 권한 부여
    ├── REVOKE: 권한 회수
    ├── COMMIT: 확정
    └── ROLLBACK: 취소
```

---

## 3. MySQL 설치

### 3-1. 설치 사이트

**MySQL Community Downloads**
- URL: https://dev.mysql.com/downloads/mysql/

### 3-2. 설치 방법

#### Windows

1. **MySQL 공식 사이트 접속**
   - https://dev.mysql.com/downloads/mysql/ 접속

2. **설치 파일 다운로드**
   - Windows용 MySQL Installer 다운로드
   - `.msi` 파일 실행

3. **설치 마법사 따라 설치**
   - 설치 타입 선택 (Typical, Custom 등)
   - 필요한 구성 요소 선택

4. **root 비밀번호 설정**
   - 설치 중 root 사용자의 비밀번호를 설정합니다
   - **비밀번호를 반드시 기억**해두세요!

5. **설치 완료**
   - MySQL 서버가 자동으로 시작됩니다

#### Mac

**Homebrew를 사용한 설치:**

```bash
# Homebrew로 MySQL 설치
brew install mysql

# MySQL 서비스 시작
brew services start mysql
```

**또는 공식 사이트에서 설치 파일 다운로드:**
- macOS용 `.dmg` 파일 다운로드 및 설치

#### Linux

**Ubuntu/Debian:**

```bash
# 패키지 목록 업데이트
sudo apt-get update

# MySQL 서버 설치
sudo apt-get install mysql-server

# MySQL 서비스 시작
sudo systemctl start mysql
```

**CentOS/RHEL:**

```bash
# MySQL 설치
sudo yum install mysql-server

# MySQL 서비스 시작
sudo systemctl start mysqld
```

### 3-3. 설치 확인

설치가 완료되면 다음 명령어로 확인할 수 있습니다:

```bash
# MySQL 버전 확인
mysql --version

# 또는
mysql -V
```

**예상 출력:**

```
mysql  Ver 8.0.xx for Win64 on x86_64 (MySQL Community Server - GPL)
```

---

## 4. MySQL 접속

### 4-1. 명령줄(CLI) 접속 방법

MySQL에 접속하는 방법은 두 가지가 있습니다.

#### 방법 1: 비밀번호 입력 방식 (권장)

```bash
mysql -u root -p
```

**실행 과정:**

```bash
# 터미널/cmd 입력
mysql -u root -p

# 비밀번호 입력 프롬프트 표시
Enter password: *******
```

- `-u root`: root 사용자로 접속
- `-p`: 비밀번호 입력 요청 (비밀번호는 입력 시 화면에 표시되지 않음)
- **Enter password:** 프롬프트가 나타나면 비밀번호를 입력하고 Enter 키를 누릅니다

**접속 성공 시:**

```
Welcome to the MySQL monitor.  Commands end with ; or \g.
Your MySQL connection id is 8
Server version: 8.0.xx MySQL Community Server - GPL

Copyright (c) 2000, 2023, Oracle and/or its affiliates.

mysql>
```

#### 방법 2: 비밀번호 직접 입력 방식

```bash
mysql -u root -p비밀번호
```

**예시:**

```bash
# 비밀번호를 명령어에 직접 입력 (공백 없이)
mysql -u root -proot1234
```

⚠️ **주의사항:**
- `-p`와 비밀번호 사이에 **공백이 없어야** 합니다
- 비밀번호가 명령어에 노출되므로 **보안상 권장하지 않습니다**
- 다른 사용자가 명령어 히스토리를 볼 수 있습니다

### 4-2. 접속 확인

접속 후 다음 명령어로 확인할 수 있습니다:

```sql
-- MySQL 버전 확인
SELECT VERSION();

-- 현재 시간 확인
SELECT NOW();

-- 현재 사용자 확인
SELECT USER();

-- 현재 데이터베이스 확인
SELECT DATABASE();
```

**실행 예시:**

```sql
mysql> SELECT VERSION();
+-----------+
| VERSION() |
+-----------+
| 8.0.xx    |
+-----------+
1 row in set (0.00 sec)

mysql> SELECT NOW();
+---------------------+
| NOW()               |
+---------------------+
| 2024-01-15 10:30:45 |
+---------------------+
1 row in set (0.00 sec)
```

### 4-3. 접속 종료

MySQL에서 나가려면 다음 명령어 중 하나를 사용합니다:

```sql
-- 방법 1
EXIT;

-- 방법 2
QUIT;

-- 방법 3 (단축키)
\q
```

**또는:**

```bash
# Ctrl + C (Windows/Linux)
# Ctrl + D (Mac/Linux)
```

### 4-4. 접속 오류 해결

#### 오류 1: MySQL 서버가 실행되지 않음

```
ERROR 2003 (HY000): Can't connect to MySQL server on 'localhost' (10061)
```

**해결 방법:**

```bash
# Windows: 서비스 관리자에서 MySQL 서비스 시작
# 또는 명령 프롬프트에서
net start MySQL80

# Mac/Linux: MySQL 서비스 시작
sudo systemctl start mysql
# 또는
brew services start mysql
```

#### 오류 2: 비밀번호 오류

```
ERROR 1045 (28000): Access denied for user 'root'@'localhost' (using password: YES)
```

**해결 방법:**

- 비밀번호를 다시 확인하고 정확히 입력합니다
- 비밀번호를 잊어버린 경우 MySQL 비밀번호 재설정이 필요합니다

#### 오류 3: 사용자 없음

```
ERROR 1045 (28000): Access denied for user 'user'@'localhost' (using password: YES)
```

**해결 방법:**

- 존재하는 사용자로 접속하거나
- root 사용자로 접속하여 새 사용자를 생성합니다

---

## 5. MySQL Workbench

### 5-1. MySQL Workbench란?

**MySQL Workbench**는 MySQL을 **시각적으로 관리**할 수 있는 GUI(Graphical User Interface) 도구입니다.

- **데이터베이스 관리**: 테이블 생성, 수정, 삭제가 쉬움
- **SQL 쿼리 실행**: SQL 문을 작성하고 실행하여 결과를 확인
- **데이터베이스 구조 시각화**: ERD(Entity Relationship Diagram) 생성
- **데이터베이스 모델링**: 데이터베이스 설계 및 모델링
- **서버 관리**: 서버 상태 모니터링 및 관리

### 5-2. MySQL Workbench 다운로드

**MySQL Workbench Downloads**
- URL: https://dev.mysql.com/downloads/workbench/

### 5-3. MySQL Workbench 설치

1. **공식 사이트 접속**
   - https://dev.mysql.com/downloads/workbench/ 접속

2. **운영체제 선택**
   - Windows, macOS, Linux 중 선택

3. **설치 파일 다운로드**
   - 운영체제에 맞는 설치 파일 다운로드

4. **설치 실행**
   - Windows: `.msi` 파일 실행
   - macOS: `.dmg` 파일 실행
   - Linux: `.deb` 또는 `.rpm` 파일 설치

### 5-4. MySQL Workbench 사용법

#### 5-4-1. 데이터베이스 연결 설정

1. **MySQL Workbench 실행**

2. **새 연결 생성**
   - 좌측 상단의 `+` 버튼 클릭
   - 또는 `Database` → `Manage Connections...`

3. **연결 정보 입력**
   ```
   Connection Name: 로컬 MySQL (원하는 이름)
   Hostname: localhost
   Port: 3306
   Username: root
   Password: [비밀번호 입력]
   ```

4. **연결 테스트**
   - `Test Connection` 버튼 클릭
   - 성공 메시지 확인

5. **연결 저장**
   - `OK` 버튼 클릭

#### 5-4-2. 데이터베이스 연결

1. **생성한 연결 더블클릭**
   - 또는 연결 선택 후 우클릭 → `Open Connection`

2. **비밀번호 입력**
   - 저장된 비밀번호가 없으면 입력 요청

3. **연결 완료**
   - 좌측에 데이터베이스 목록이 표시됩니다

#### 5-4-3. SQL 쿼리 실행

1. **새 쿼리 탭 열기**
   - 상단 메뉴에서 `File` → `New Query Tab`
   - 또는 `Ctrl + T` (Windows/Linux), `Cmd + T` (Mac)

2. **SQL 문 작성**
   ```sql
   SHOW DATABASES;
   ```

3. **쿼리 실행**
   - 상단 툴바의 번개 모양 아이콘 클릭
   - 또는 `Ctrl + Enter` (Windows/Linux), `Cmd + Enter` (Mac)

4. **결과 확인**
   - 하단에 실행 결과가 표시됩니다

### 5-5. MySQL Workbench 주요 기능

| 기능 | 설명 |
|------|------|
| **Database Administration** | 데이터베이스 서버 관리 및 모니터링 |
| **SQL Development** | SQL 쿼리 작성 및 실행 |
| **Data Modeling** | 데이터베이스 모델 설계 및 ERD 생성 |
| **Server Status** | 서버 상태 및 성능 모니터링 |
| **User Management** | 사용자 및 권한 관리 |
| **Database Migration** | 다른 데이터베이스로 마이그레이션 |

### 5-6. MySQL Workbench vs 명령줄

| 구분 | MySQL Workbench | 명령줄 (CLI) |
|------|-----------------|--------------|
| **사용 편의성** | ⭐⭐⭐⭐⭐ (GUI, 시각적) | ⭐⭐⭐ (텍스트 기반) |
| **학습 난이도** | ⭐⭐ (쉬움) | ⭐⭐⭐ (보통) |
| **속도** | ⭐⭐⭐ (보통) | ⭐⭐⭐⭐⭐ (빠름) |
| **기능** | ⭐⭐⭐⭐⭐ (풍부함) | ⭐⭐⭐ (기본) |
| **모델링** | ⭐⭐⭐⭐⭐ (ERD 지원) | ❌ (지원 안 함) |
| **서버 관리** | ⭐⭐⭐⭐⭐ (시각적) | ⭐⭐⭐ (명령어 기반) |

---

## 전체 요약

### MySQL 시작하기 체크리스트

- [ ] MySQL 설치 (공식 사이트에서 다운로드)
- [ ] root 비밀번호 설정 및 기억
- [ ] 명령줄로 MySQL 접속 (`mysql -u root -p`)
- [ ] MySQL Workbench 설치 (선택사항)
- [ ] MySQL Workbench로 연결 설정 및 접속

### 핵심 포인트

1. **MySQL은 오픈소스 RDBMS**로 무료로 사용 가능합니다
2. **SQL 언어는 DDL, DML, DCL**로 분류됩니다
3. **접속 방법**: `mysql -u root -p` (비밀번호 입력 방식 권장)
4. **MySQL Workbench**는 GUI 도구로 시각적으로 관리할 수 있습니다
5. **root 비밀번호**는 반드시 기억해야 합니다

### 다음 단계

MySQL 설치와 접속이 완료되었다면:

1. **데이터베이스 생성 및 관리** 학습
   - `CREATE DATABASE`, `USE`, `DROP DATABASE` 등

2. **테이블 생성 및 관리** 학습
   - `CREATE TABLE`, `ALTER TABLE`, `DROP TABLE` 등

3. **데이터 조작** 학습
   - `INSERT`, `SELECT`, `UPDATE`, `DELETE` 등

---

## 참고 자료

- **MySQL 공식 문서**: https://dev.mysql.com/doc/
- **MySQL Community Downloads**: https://dev.mysql.com/downloads/mysql/
- **MySQL Workbench Downloads**: https://dev.mysql.com/downloads/workbench/
- **MySQL 튜토리얼**: https://dev.mysql.com/doc/refman/8.0/en/tutorial.html
