# MySQL 데이터베이스 기본 명령어

---

## 목차

1. [세미콜론(;)의 역할](#1-세미콜론의-역할)
2. [데이터베이스 목록 확인 (SHOW DATABASES)](#2-데이터베이스-목록-확인-show-databases)
3. [데이터베이스 생성 (CREATE DATABASE)](#3-데이터베이스-생성-create-database)
4. [데이터베이스 선택 (USE)](#4-데이터베이스-선택-use)
5. [데이터베이스 삭제 (DROP DATABASE)](#5-데이터베이스-삭제-drop-database)

---

## 1. 세미콜론(;)의 역할

### 1-1. 세미콜론이란?

**세미콜론(`;`)**은 SQL 명령어의 **마침표**와 같은 역할을 합니다.

- SQL 명령어의 **끝을 표시**합니다
- 세미콜론을 입력해야 **명령어가 실행**됩니다
- 세미콜론을 입력하지 않으면 MySQL이 **명령어가 아직 끝나지 않았다고 판단**하여 계속 입력을 기다립니다

### 1-2. 사용 예시

```sql
-- 올바른 사용 (세미콜론 포함)
show databases;

-- 잘못된 사용 (세미콜론 없음)
show databases  -- MySQL이 계속 입력을 기다림
```

### 1-3. 주의사항

- ✅ **모든 SQL 명령어 끝에 세미콜론을 반드시 입력**해야 합니다
- ✅ 세미콜론을 입력하지 않으면 명령어가 실행되지 않습니다
- ✅ 여러 명령어를 연속으로 실행할 때 각 명령어 끝에 세미콜론을 입력합니다

---

## 2. 데이터베이스 목록 확인 (SHOW DATABASES)

### 2-1. 명령어 설명

**`SHOW DATABASES;`**는 MySQL에 존재하는 **모든 데이터베이스 목록을 확인**하는 명령어입니다.

- 데이터베이스는 **폴더**와 같은 개념입니다
- 데이터베이스 안에 여러 테이블과 데이터가 저장됩니다
- 현재 MySQL 서버에 있는 모든 데이터베이스를 확인할 수 있습니다

### 2-2. 기본 문법

```sql
SHOW DATABASES;
```

### 2-3. 실행 예시

```sql
mysql> show databases;
```

**실행 결과:**

```
+--------------------+
| Database           |
+--------------------+
| information_schema |
| mysql              |
| performance_schema |
| sakila             |
| sys                |
| world              |
+--------------------+
6 rows in set (0.00 sec)
```

### 2-4. 결과 설명

- **information_schema**: MySQL 시스템 정보를 저장하는 데이터베이스
- **mysql**: MySQL 사용자 정보와 권한을 저장하는 데이터베이스
- **performance_schema**: 성능 모니터링 정보를 저장하는 데이터베이스
- **sakila**: MySQL 샘플 데이터베이스 (영화 대여 시스템)
- **sys**: 시스템 정보를 저장하는 데이터베이스
- **world**: MySQL 샘플 데이터베이스 (국가 정보)

---

## 3. 데이터베이스 생성 (CREATE DATABASE)

### 3-1. 명령어 설명

**`CREATE DATABASE`**는 **새로운 데이터베이스를 생성**하는 명령어입니다.

- 데이터베이스 이름을 지정하여 생성합니다
- 정상적으로 생성되면 `Query OK` 메시지가 출력됩니다
- 생성 후 `SHOW DATABASES;`로 확인할 수 있습니다

### 3-2. 기본 문법

```sql
CREATE DATABASE database_name;
```

### 3-3. 실행 예시

#### 데이터베이스 생성

```sql
mysql> create database newdatabase;
```

**실행 결과:**

```
Query OK, 1 row affected (0.01 sec)
```

#### 생성된 데이터베이스 확인

```sql
mysql> show databases;
```

**실행 결과:**

```
+--------------------+
| Database           |
+--------------------+
| information_schema |
| mysql              |
| newdatabase        |  ← 새로 생성된 데이터베이스
| performance_schema |
| sakila             |
| sys                |
| world              |
+--------------------+
7 rows in set (0.00 sec)
```

### 3-4. 주의사항

- ✅ 데이터베이스 이름은 **영문, 숫자, 언더스코어(_)**를 사용할 수 있습니다
- ✅ 데이터베이스 이름은 **대소문자를 구분**합니다 (일부 운영체제)
- ✅ 이미 존재하는 데이터베이스 이름으로 생성하면 오류가 발생합니다
- ✅ 데이터베이스 이름에 공백을 사용하려면 백틱(`)으로 감싸야 합니다

### 3-5. 오류 처리

#### 이미 존재하는 데이터베이스 생성 시

```sql
mysql> create database newdatabase;
ERROR 1007 (HY000): Can't create database 'newdatabase'; database exists
```

**해결 방법:**

```sql
-- IF NOT EXISTS를 사용하여 오류 방지
mysql> create database if not exists newdatabase;
Query OK, 1 row affected, 1 warning (0.00 sec)
```

---

## 4. 데이터베이스 선택 (USE)

### 4-1. 명령어 설명

**`USE`**는 **작업할 데이터베이스를 선택**하는 명령어입니다.

- 폴더 안으로 들어가야 파일을 확인하고 사용하듯이, 데이터베이스도 **선택해야 사용**할 수 있습니다
- 데이터베이스를 선택하면 해당 데이터베이스의 테이블을 조작할 수 있습니다
- 정상적으로 선택되면 `Database changed` 메시지가 출력됩니다

### 4-2. 기본 문법

```sql
USE database_name;
```

### 4-3. 실행 예시

```sql
mysql> use newdatabase;
```

**실행 결과:**

```
Database changed
```

### 4-4. 선택된 데이터베이스 확인

```sql
-- 현재 선택된 데이터베이스 확인
mysql> select database();
```

**실행 결과:**

```
+--------------+
| database()   |
+--------------+
| newdatabase  |
+--------------+
1 row in set (0.00 sec)
```

### 4-5. 주의사항

- ✅ 데이터베이스를 선택하지 않으면 테이블을 생성하거나 조작할 수 없습니다
- ✅ 존재하지 않는 데이터베이스를 선택하면 오류가 발생합니다
- ✅ 한 번에 하나의 데이터베이스만 선택할 수 있습니다

### 4-6. 오류 처리

#### 존재하지 않는 데이터베이스 선택 시

```sql
mysql> use nonexistent;
ERROR 1049 (42000): Unknown database 'nonexistent'
```

**해결 방법:**

```sql
-- 먼저 데이터베이스 목록을 확인
mysql> show databases;

-- 존재하는 데이터베이스 선택
mysql> use newdatabase;
Database changed
```

---

## 5. 데이터베이스 삭제 (DROP DATABASE)

### 5-1. 명령어 설명

**`DROP DATABASE`**는 **데이터베이스를 삭제**하는 명령어입니다.

- 데이터베이스와 그 안의 모든 테이블과 데이터가 **완전히 삭제**됩니다
- 정상적으로 삭제되면 `Query OK` 메시지가 출력됩니다
- 삭제 후 복구할 수 없으므로 **주의**가 필요합니다

### 5-2. 기본 문법

```sql
DROP DATABASE database_name;
```

### 5-3. 실행 예시

#### 데이터베이스 삭제

```sql
mysql> drop database newdatabase;
```

**실행 결과:**

```
Query OK, 0 rows affected (0.02 sec)
```

#### 삭제 확인

```sql
mysql> show databases;
```

**실행 결과:**

```
+--------------------+
| Database           |
+--------------------+
| information_schema |
| mysql              |
| performance_schema |
| sakila             |
| sys                |
| world              |
+--------------------+
6 rows in set (0.00 sec)
```

**`newdatabase`가 목록에서 사라진 것을 확인할 수 있습니다.**

### 5-4. 주의사항

- ⚠️ **데이터베이스 삭제는 되돌릴 수 없습니다**
- ⚠️ 삭제 전에 **중요한 데이터를 백업**해야 합니다
- ⚠️ 삭제하려는 데이터베이스가 **현재 사용 중이면 삭제할 수 없습니다**
- ✅ 존재하지 않는 데이터베이스를 삭제하려고 하면 오류가 발생합니다

### 5-5. 오류 처리

#### 존재하지 않는 데이터베이스 삭제 시

```sql
mysql> drop database nonexistent;
ERROR 1008 (HY000): Can't drop database 'nonexistent'; database doesn't exist
```

**해결 방법:**

```sql
-- IF EXISTS를 사용하여 오류 방지
mysql> drop database if exists nonexistent;
Query OK, 0 rows affected, 1 warning (0.00 sec)
```

#### 사용 중인 데이터베이스 삭제 시

```sql
mysql> use newdatabase;
Database changed

mysql> drop database newdatabase;
ERROR 1010 (HY000): Error dropping database (can't rmdir './newdatabase', errno: 39)
```

**해결 방법:**

```sql
-- 다른 데이터베이스로 전환 후 삭제
mysql> use mysql;
Database changed

mysql> drop database newdatabase;
Query OK, 0 rows affected (0.02 sec)
```

---

## 전체 명령어 요약

### 기본 명령어 체크리스트

| 명령어 | 설명 | 예시 |
|--------|------|------|
| `SHOW DATABASES;` | 데이터베이스 목록 확인 | `show databases;` |
| `CREATE DATABASE name;` | 데이터베이스 생성 | `create database testdb;` |
| `USE name;` | 데이터베이스 선택 | `use testdb;` |
| `DROP DATABASE name;` | 데이터베이스 삭제 | `drop database testdb;` |

### 작업 흐름 예시

```sql
-- 1. 데이터베이스 목록 확인
mysql> show databases;

-- 2. 새 데이터베이스 생성
mysql> create database mydb;
Query OK, 1 row affected (0.01 sec)

-- 3. 데이터베이스 선택
mysql> use mydb;
Database changed

-- 4. 작업 수행 (테이블 생성, 데이터 조작 등)
-- ... (다음 단계에서 학습)

-- 5. 필요시 데이터베이스 삭제
mysql> drop database mydb;
Query OK, 0 rows affected (0.02 sec)
```

---

## 핵심 포인트

### 1. 세미콜론의 중요성
- 모든 SQL 명령어 끝에 세미콜론(`;`)을 반드시 입력해야 합니다
- 세미콜론이 없으면 명령어가 실행되지 않습니다

### 2. 데이터베이스 개념
- 데이터베이스는 **폴더**와 같은 개념입니다
- 데이터베이스 안에 여러 테이블과 데이터가 저장됩니다

### 3. 작업 순서
1. 데이터베이스 목록 확인 (`SHOW DATABASES`)
2. 데이터베이스 생성 (`CREATE DATABASE`)
3. 데이터베이스 선택 (`USE`)
4. 테이블 생성 및 데이터 조작
5. 필요시 데이터베이스 삭제 (`DROP DATABASE`)

### 4. 안전한 사용법
- `CREATE DATABASE IF NOT EXISTS`: 이미 존재하는 경우 오류 방지
- `DROP DATABASE IF EXISTS`: 존재하지 않는 경우 오류 방지
- 삭제 전 백업 필수

---

## 다음 단계

데이터베이스를 생성하고 선택했다면, 다음 단계로 **테이블 생성 및 관리**를 학습하세요.

1. 테이블 생성 (`CREATE TABLE`)
2. 테이블 구조 확인 (`DESCRIBE`, `SHOW TABLES`)
3. 테이블 수정 (`ALTER TABLE`)
4. 테이블 삭제 (`DROP TABLE`)
