# MySQL UTF-8 인코딩 에러 해결 가이드

## 문제 상황

MySQL에서 한글 데이터를 다룰 때 다음과 같은 에러나 문제가 발생할 수 있습니다:
- 한글이 깨져서 표시됨 (예: `ì•„ë‹¤ë‹¤` 같은 문자)
- CSV 파일 import 시 한글이 깨짐
- 데이터 입력 시 에러 발생
- 쿼리 결과에서 한글이 제대로 표시되지 않음

---

## 해결 방법

### 1. 데이터베이스 생성 시 UTF-8 설정

**가장 중요한 첫 단계입니다!**

```sql
-- 데이터베이스 생성 시 UTF-8 설정
CREATE DATABASE 데이터베이스명 
CHARACTER SET utf8mb4 
COLLATE utf8mb4_unicode_ci;

-- 또는 기존 데이터베이스 변경
ALTER DATABASE 데이터베이스명 
CHARACTER SET utf8mb4 
COLLATE utf8mb4_unicode_ci;
```

**설명:**
- `utf8mb4`: 완전한 UTF-8 지원 (이모지 포함)
- `utf8mb4_unicode_ci`: 유니코드 정렬 규칙

---

### 2. 테이블 생성 시 UTF-8 설정

```sql
CREATE TABLE 테이블명 (
    컬럼1 VARCHAR(50),
    컬럼2 VARCHAR(100)
) CHARACTER SET utf8mb4 
  COLLATE utf8mb4_unicode_ci;
```

**기존 테이블 변경:**
```sql
ALTER TABLE 테이블명 
CONVERT TO CHARACTER SET utf8mb4 
COLLATE utf8mb4_unicode_ci;
```

---

### 3. CSV 파일 인코딩 확인 및 변환

**가장 흔한 원인: CSV 파일이 UTF-8이 아닌 경우**

#### 방법 1: 메모장으로 변환 (Windows)

1. CSV 파일을 메모장으로 엽니다
2. **파일 → 다른 이름으로 저장** 선택
3. **인코딩**을 **UTF-8**로 선택
4. 저장

#### 방법 2: Excel로 변환

1. Excel에서 CSV 파일 열기
2. **파일 → 다른 이름으로 저장**
3. **파일 형식**: **CSV UTF-8(쉼표로 구분)(*.csv)** 선택
4. 저장

#### 방법 3: VS Code 사용

1. VS Code에서 CSV 파일 열기
2. 우측 하단의 인코딩 표시 클릭 (예: "EUC-KR" 또는 "ANSI")
3. **인코딩하여 저장** → **UTF-8** 선택

---

### 4. MySQL Workbench에서 CSV Import 설정

#### Step 1: 테이블 생성 시 UTF-8 명시

```sql
CREATE TABLE 고객 (
    고객번호 CHAR(5) PRIMARY KEY,
    고객회사명 VARCHAR(30),
    담당자명 VARCHAR(20),
    주소 VARCHAR(20),
    전화번호 VARCHAR(20),
    마일리지 INT
) CHARACTER SET utf8mb4 
  COLLATE utf8mb4_unicode_ci;
```

#### Step 2: Import Wizard 설정

1. **Table Data Import Wizard** 실행
2. CSV 파일 선택
3. **Import Options** 화면에서:
   - **Character Set**: **utf8mb4** 선택
   - **Field Separator**: 쉼표(`,`) 확인
   - **Line Separator**: 자동 감지 또는 `\n` 선택

#### Step 3: Column Mapping 확인

- 각 컬럼이 올바르게 매핑되었는지 확인
- 데이터 타입이 올바른지 확인

---

### 5. MySQL 연결 설정 확인

#### MySQL Workbench 설정

1. **Edit → Preferences → SQL Editor**
2. **Default Encoding**: **utf8mb4** 확인

#### my.ini 또는 my.cnf 설정 (서버 설정)

```ini
[mysqld]
character-set-server=utf8mb4
collation-server=utf8mb4_unicode_ci

[client]
default-character-set=utf8mb4

[mysql]
default-character-set=utf8mb4
```

**설정 후 MySQL 서버 재시작 필요**

---

### 6. 연결 시 인코딩 설정

#### MySQL Workbench에서

연결 후 다음 명령어 실행:

```sql
SET NAMES utf8mb4;
SET CHARACTER SET utf8mb4;
```

#### 명령줄(Command Line)에서

```bash
mysql -u 사용자명 -p --default-character-set=utf8mb4 데이터베이스명
```

---

### 7. 현재 인코딩 설정 확인

```sql
-- 데이터베이스 인코딩 확인
SHOW CREATE DATABASE 데이터베이스명;

-- 테이블 인코딩 확인
SHOW CREATE TABLE 테이블명;

-- 현재 연결 인코딩 확인
SHOW VARIABLES LIKE 'character_set%';
SHOW VARIABLES LIKE 'collation%';
```

**예상 결과:**
```
character_set_client: utf8mb4
character_set_connection: utf8mb4
character_set_database: utf8mb4
character_set_results: utf8mb4
character_set_server: utf8mb4
```

---

## 단계별 해결 체크리스트

### ✅ Step 1: 데이터베이스 인코딩 확인 및 설정

```sql
-- 1. 현재 데이터베이스 인코딩 확인
SHOW CREATE DATABASE 데이터베이스명;

-- 2. UTF-8로 변경 (필요시)
ALTER DATABASE 데이터베이스명 
CHARACTER SET utf8mb4 
COLLATE utf8mb4_unicode_ci;
```

### ✅ Step 2: 테이블 인코딩 확인 및 설정

```sql
-- 1. 테이블 인코딩 확인
SHOW CREATE TABLE 테이블명;

-- 2. UTF-8로 변경 (필요시)
ALTER TABLE 테이블명 
CONVERT TO CHARACTER SET utf8mb4 
COLLATE utf8mb4_unicode_ci;
```

### ✅ Step 3: CSV 파일 인코딩 확인

- [ ] CSV 파일이 UTF-8로 저장되어 있는지 확인
- [ ] 메모장/Excel/VS Code로 UTF-8로 변환

### ✅ Step 4: Import 시 인코딩 설정

- [ ] MySQL Workbench Import Wizard에서 Character Set을 utf8mb4로 선택
- [ ] Column Mapping이 올바른지 확인

### ✅ Step 5: 연결 인코딩 설정

```sql
-- 연결 후 실행
SET NAMES utf8mb4;
```

### ✅ Step 6: 데이터 확인

```sql
-- 한글 데이터가 올바르게 표시되는지 확인
SELECT * FROM 테이블명 LIMIT 10;
```

---

## 실전 예제: 완전한 해결 과정

### 예제: 고객 테이블에 CSV 데이터 Import

#### 1. 데이터베이스 생성 (UTF-8)

```sql
CREATE DATABASE 한별무역 
CHARACTER SET utf8mb4 
COLLATE utf8mb4_unicode_ci;

USE 한별무역;
```

#### 2. 테이블 생성 (UTF-8)

```sql
CREATE TABLE 고객 (
    고객번호 CHAR(5) PRIMARY KEY,
    고객회사명 VARCHAR(30),
    담당자명 VARCHAR(20),
    주소 VARCHAR(20),
    도시 VARCHAR(20),
    지역 VARCHAR(20),
    전화번호 VARCHAR(20),
    마일리지 INT
) CHARACTER SET utf8mb4 
  COLLATE utf8mb4_unicode_ci;
```

#### 3. CSV 파일 준비

- `고객.csv` 파일을 UTF-8로 저장
- 메모장 또는 Excel에서 UTF-8로 변환

#### 4. Import 실행

1. MySQL Workbench에서 `고객` 테이블 우클릭
2. **Table Data Import Wizard** 선택
3. CSV 파일 선택
4. **Character Set**: **utf8mb4** 선택
5. Column Mapping 확인
6. Finish 클릭

#### 5. 연결 인코딩 설정

```sql
SET NAMES utf8mb4;
```

#### 6. 데이터 확인

```sql
SELECT * FROM 고객 LIMIT 10;
```

---

## 자주 발생하는 에러와 해결

### 에러 1: "Incorrect string value"

**원인:** 테이블이 UTF-8이 아님

**해결:**
```sql
ALTER TABLE 테이블명 
CONVERT TO CHARACTER SET utf8mb4 
COLLATE utf8mb4_unicode_ci;
```

### 에러 2: CSV Import 시 한글이 깨짐

**원인:** CSV 파일이 UTF-8이 아님

**해결:**
1. CSV 파일을 UTF-8로 변환
2. Import Wizard에서 Character Set을 utf8mb4로 선택

### 에러 3: 쿼리 결과에서 한글이 깨져서 표시

**원인:** 클라이언트 연결 인코딩이 UTF-8이 아님

**해결:**
```sql
SET NAMES utf8mb4;
```

또는 MySQL Workbench 설정에서 Default Encoding을 utf8mb4로 변경

### 에러 4: INSERT 시 한글이 깨짐

**원인:** 테이블 또는 데이터베이스가 UTF-8이 아님

**해결:**
```sql
-- 데이터베이스 변경
ALTER DATABASE 데이터베이스명 
CHARACTER SET utf8mb4 
COLLATE utf8mb4_unicode_ci;

-- 테이블 변경
ALTER TABLE 테이블명 
CONVERT TO CHARACTER SET utf8mb4 
COLLATE utf8mb4_unicode_ci;
```

---

## utf8 vs utf8mb4 차이

| 항목 | utf8 | utf8mb4 |
|------|------|---------|
| 최대 바이트 | 3바이트 | 4바이트 |
| 이모지 지원 | ❌ | ✅ |
| 한글 지원 | ✅ | ✅ |
| 권장 | ❌ | ✅ |

**권장사항:** `utf8mb4` 사용 (완전한 UTF-8 지원)

---

## 빠른 해결 요약

1. **데이터베이스**: `CHARACTER SET utf8mb4`
2. **테이블**: `CHARACTER SET utf8mb4`
3. **CSV 파일**: UTF-8로 저장
4. **Import**: Character Set을 utf8mb4로 선택
5. **연결**: `SET NAMES utf8mb4;`

---

## 추가 팁

### 이미 깨진 데이터 복구하기

데이터가 이미 깨진 경우:

1. 원본 CSV 파일이 UTF-8인지 확인
2. 테이블을 UTF-8로 변경
3. 데이터를 삭제하고 다시 Import

```sql
-- 깨진 데이터 삭제
DELETE FROM 테이블명;

-- 테이블 인코딩 변경
ALTER TABLE 테이블명 
CONVERT TO CHARACTER SET utf8mb4 
COLLATE utf8mb4_unicode_ci;

-- UTF-8 CSV 파일로 다시 Import
```

### 백업 시 주의사항

```bash
# mysqldump 시 인코딩 명시
mysqldump -u 사용자명 -p --default-character-set=utf8mb4 데이터베이스명 > backup.sql
```

---

## 체크리스트

- [ ] 데이터베이스가 utf8mb4로 설정되어 있음
- [ ] 테이블이 utf8mb4로 설정되어 있음
- [ ] CSV 파일이 UTF-8로 저장되어 있음
- [ ] Import 시 Character Set을 utf8mb4로 선택함
- [ ] 연결 후 `SET NAMES utf8mb4;` 실행함
- [ ] 데이터가 올바르게 표시되는지 확인함

**이 체크리스트를 모두 확인하면 UTF-8 에러가 해결됩니다!**
