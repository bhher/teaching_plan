# SQL 실습 문제 - member1 테이블

※채점기준 : SQL 구문을 직접 작성하여 기입하시오(또는 캡처)

## 테이블 생성 및 데이터 삽입

### 테이블 생성

다음과 같이 테이블을 정의하시오.

**한글 데이터를 올바르게 저장하기 위해 UTF-8 설정이 필요합니다.**

```sql
-- 데이터베이스 생성 시 UTF-8 설정 (권장)
CREATE DATABASE 데이터베이스명 
CHARACTER SET utf8mb4 
COLLATE utf8mb4_unicode_ci;

-- 테이블 생성 (UTF-8 설정 포함)
CREATE TABLE member1 (
  num int not null auto_increment,
  id  char(20) not null,
  name char(20) not null,
  gender char(1),
  post_num char(8),
  address char(80),
  tel char(20),
  age int,
  primary key(num)
) CHARACTER SET utf8mb4 
  COLLATE utf8mb4_unicode_ci;
```

**또는 기존 테이블에 UTF-8 설정 추가:**

```sql
-- 기존 테이블을 UTF-8로 변경
ALTER TABLE member1 
CONVERT TO CHARACTER SET utf8mb4 
COLLATE utf8mb4_unicode_ci;
```

**참고:** 한글 데이터가 깨지는 경우 `MySQL-UTF8-인코딩-에러-해결가이드.md` 파일을 참고하세요.

### 데이터 삽입

member1 테이블에 아래 데이터를 삽입하세요.

```sql
insert into member1 (id, name, gender, post_num, address, tel, age) values ('yjhwang', '황영주', 'M', '100-011', '서울시 중구 충무로1가', '234-8879', 35);
insert into member1 (id, name, gender, post_num, address, tel, age) values ('khshul', '설기형', 'M', '607-010', '부산시 동래구 명륜동', '764-3784', 33);
insert into member1 (id, name, gender, post_num, address, tel, age) values ('chpark', '박철호', 'M', '503-200', '광주시 남구 지석동', '298-9730', 34);
insert into member1 (id, name, gender, post_num, address, tel, age) values ('shlee', '이상훈', 'M', '503-201', '광주시 남구 도금동', '838-4347', 32);
insert into member1 (id, name, gender, post_num, address, tel, age) values ('jyjang', '장영숙', 'W', '606-065', '부산시 영도구 봉래동5가', '399-9809', 24);
insert into member1 (id, name, gender, post_num, address, tel, age) values ('yjbae', '배용진', 'M', '122-014', '서울시 은평구 응암4동', '857-5683', 30);
insert into member1 (id, name, gender, post_num, address, tel, age) values ('hbpark', '박혜빈', 'W', '427-760', '경기도 과천시 중앙동', '234-7677', 22);
insert into member1 (id, name, gender, post_num, address, tel, age) values ('mskim', '김문수', 'M', '429-020', '경기도 시흥시 신천동', '370-6003', 63);
insert into member1 (id, name, gender, post_num, address, tel, age) values ('bkcha', '차범길', 'M', '302-121', '대전시 서구 둔산1동', '432-9877', 49);
insert into member1 (id, name, gender, post_num, address, tel, age) values ('kskim', '김길수', 'M', '440-747', '경기도 수원시 장안구 파장동', '324-5875', 54);
insert into member1 (id, name, gender, post_num, address, tel, age) values ('srkim', '김수련', 'M', '704-701', '대구시 달서구 신당동', '987-3688', 23);
insert into member1 (id, name, gender, post_num, address, tel, age) values ('shlee', '이성현', 'M', '441-081', '경기도 수원시 권선구 매산로1가', '243-6844', 36);
insert into member1 (id, name, gender, post_num, address, tel, age) values ('hnjang', '정한나', 'W', '502-763', '광주시 서구 화정4동', '845-4547', 58);
insert into member1 (id, name, gender, post_num, address, tel, age) values ('mylee', '이명연', 'W', '502-791', '광주시 서구 쌍촌동', '837-9432', 33);
insert into member1 (id, name, gender, post_num, address, tel, age) values ('yskim', '김영숙', 'W', '429-010', '경기도 시흥시 대야동', '374-8438', 53);
insert into member1 (id, name, gender, post_num, address, tel, age) values ('jekim', '김정은', 'W', '503-202', '광주시 남구 원산동', '347-8873', 29);
insert into member1 (id, name, gender, post_num, address, tel, age) values ('yjko', '고영주', 'W', '122-020', '서울시 은평구 녹번동', '479-3874', 32);
insert into member1 (id, name, gender, post_num, address, tel, age) values ('cyahn', '안철영', 'M', '122-030', '서울시 은평구 대조동', '347-4687', 34);
insert into member1 (id, name, gender, post_num, address, tel, age) values ('jmkim', '김진모', 'M', '530-140', '전라남도 목포시 항동', '379-8349', 28);
insert into member1 (id, name, gender, post_num, address, tel, age) values ('ycshul', '설영찬', 'M', '606-070', '부산시 영도구 청학동', '983-8748', 41);
insert into member1 (id, name, gender, post_num, address, tel, age) values ('jjko', '고재진', 'M', '100-013', '서울시 중구 충무로3가', '836-4655', 28);
insert into member1 (id, name, gender, post_num, address, tel, age) values ('hwlee', '이현우', 'M', '606-071', '부산시 영도구 청학1동', '346-8892', 32);
insert into member1 (id, name, gender, post_num, address, tel, age) values ('cskang', '강찬숙', 'W', '668-890', '경상남도 남해군 설천면', '377-6879', 21);
insert into member1 (id, name, gender, post_num, address, tel, age) values ('ypji', '지영필', 'M', '122-040', '서울시 은평구 불광동', '366-3747', 52);
insert into member1 (id, name, gender, post_num, address, tel, age) values ('jbkim', '김진배', 'M', '427-600', '경기도 과천시 과천동', '382-4993', 47);
insert into member1 (id, name, gender, post_num, address, tel, age) values ('jepark', '박지은', 'W', '670-800', '경상남도 거창군 거창읍', '328-8743', 26);
insert into member1 (id, name, gender, post_num, address, tel, age) values ('jhlee', '이지현', 'W', '704-702', '대구시 달서구 월성동', '386-7988', 27);
insert into member1 (id, name, gender, post_num, address, tel, age) values ('bykang', '강부영', 'M', '302-120', '대전시 서구 둔산동', '798-3243', 62);
insert into member1 (id, name, gender, post_num, address, tel, age) values ('jymoon', '문진영', 'W', '302-122', '대전시 서구 둔산2동', '987-3248', 18);
insert into member1 (id, name, gender, post_num, address, tel, age) values ('jyjun', '전지연', 'W', '100-012', '서울시 중구 충무로2가', '347-2236', 28);
insert into member1 (id, name, gender, post_num, address, tel, age) values ('jkko', '고진길', 'M', '122-013', '서울시 은평구 응암3동', '234-7466', 27);
insert into member1 (id, name, gender, post_num, address, tel, age) values ('myjung', '정명윤', 'M', '502-771', '광주시 서구 치평동', '374-8786', 47);
insert into member1 (id, name, gender, post_num, address, tel, age) values ('jsyou', '유지수', 'W', '502-772', '광주시 서구 치평동', '309-3897', 49);
insert into member1 (id, name, gender, post_num, address, tel, age) values ('dsshin', '신달성', 'W', '530-145', '전라남도 신안군 장산면', '399-8789', 53);
insert into member1 (id, name, gender, post_num, address, tel, age) values ('sjshin', '신수진', 'W', '606-796', '부산시 영도구 봉래동5가', '389-8930', 47);
```

---

## 기본 SELECT 문

### 예제 1: 전체 필드와 데이터 검색

member1 테이블의 전체 필드와 데이터를 조회하시오.

**명령 형식:**
```sql
SELECT * FROM 테이블명;
```

---

### 예제 2: 특정 필드의 전체 레코드 검색

member1 테이블의 전체 레코드에서 name, tel, address 필드 데이터만 검색하시오.

**명령 형식:**
```sql
SELECT 필드명1, 필드명2, 필드명3, ... FROM 테이블명;
```

---

### 예제 3: 조건 검색

member1 테이블에서 여성 회원의 아이디, 이름, 주소, 성별을 검색하시오.

**명령 형식:**
```sql
SELECT 필드명1, 필드명2, ... FROM 테이블명 WHERE 조건;
```

---

## WHERE 절을 이용한 조건 검색

### 문제 1: 50세 이상인 남성 회원 검색

50세 이상인 남성 회원의 이름, 전화번호, 성별, 나이를 검색하시오.

---

### 문제 2: 20대 회원 검색

20대 회원의 이름, 성별, 주소, 나이를 검색하시오.

---

### 문제 3: 30대 또는 50대 남성 회원 검색

30대 또는 50대 남성 회원의 이름, 전화번호, 나이, 성별을 검색하시오.

---

### 문제 4: 특정 이름 검색

이름이 '안철영'인 회원의 일련번호, 이름, 전화번호, 주소, 우편번호, 나이, 성별을 검색하시오.

---

## LIKE 연산자를 이용한 패턴 검색

### LIKE 연산자 기본

테이블에서 특정 글자가 들어있는 데이터를 찾아서, 원하는 컬럼만 보여달라는 명령 형식:

```sql
SELECT 필드명1, 필드명2
FROM 테이블명
WHERE 검색필드 LIKE '%문자열%';
```

**와일드카드:**
- `%`: 0개 이상의 임의의 문자를 의미
- `_`: 정확히 1개의 임의의 문자를 의미

---

### 문제 5: 성씨가 '박'씨인 회원 검색

성씨가 박씨인 회원의 이름, 전화번호를 검색하시오.

---

### 문제 6: 서울에 거주하는 회원 검색

서울에 거주하는 회원의 일련번호, 이름, 주소, 성별을 검색하시오.

---

### 문제 7: 은평구에 거주하는 회원 검색

은평구에 거주하는 회원의 모든 정보를 검색하시오.

---

### 문제 8: 복합 조건 검색

성이 김씨이고 광주에 거주하는 회원의 모든 정보를 검색하시오.

---

## ORDER BY를 이용한 정렬

### ORDER BY 기본

테이블에서 필드를 기준으로 정렬된 결과를 검색하는 명령 형식:

```sql
SELECT 필드명1, 필드명2, ... 
FROM 테이블명 
ORDER BY 필드명 [ASC|DESC];
```

- `ASC`: 오름차순 정렬 (기본값, 생략 가능)
- `DESC`: 내림차순 정렬

---

### 문제 9: 서울 거주 회원을 나이순 오름차순 정렬

서울에 사는 회원을 나이순으로 오름차순 정렬한 뒤 이름, 나이, 주소, 전화번호, 성별을 출력하시오.

---

### 문제 10: 부산 거주 회원을 나이순 내림차순 정렬

부산에 사는 회원을 나이순으로 내림차순 정렬한 뒤 이름, 나이, 주소, 전화번호를 출력하시오.

---

## 추가 실습 문제

### 문제 11: 특정 지역의 회원 수 세기

광주에 거주하는 회원의 수를 구하시오.

---

### 문제 12: 평균 나이 구하기

전체 회원의 평균 나이를 구하시오.

---

### 문제 13: 최고령 회원 찾기

나이가 가장 많은 회원의 이름과 나이를 구하시오.

---

### 문제 14: 지역별 회원 수

지역(시/도)별로 회원 수를 구하시오.

---

### 문제 15: 성별별 평균 나이

성별별 평균 나이를 구하시오.

---

## UPDATE를 이용한 레코드 수정

### UPDATE 기본

테이블에서 조건식을 만족하는 레코드의 필드 값을 변경하는 명령 형식:

```sql
UPDATE 테이블명 
SET 필드명 = 필드값 
WHERE 조건식;
```

---

### 문제 16: 전화번호 수정

이름이 '고재진'인 레코드의 전화번호를 '123-4567'로 변경한 뒤 데이터를 확인하시오.

---

### 문제 17: 나이 수정

이름이 '정한나'인 회원의 나이를 확인하고 값을 37로 변경한 뒤 확인하시오.

---

## DELETE를 이용한 레코드 삭제

### DELETE 기본

테이블에서 조건식을 만족하는 레코드를 삭제하는 명령 형식:

```sql
DELETE FROM 테이블명 
WHERE 조건식;
```

**주의사항:**
- 조건식을 생략하면 테이블의 전체 레코드가 삭제됩니다
- `DELETE FROM 테이블명;` 명령은 매우 위험하므로 신중하게 사용해야 합니다
- 삭제 전에 `SELECT` 문으로 확인하는 것이 좋습니다

---

### 문제 18: 특정 회원 삭제

이름이 '김수련'인 회원의 레코드를 삭제한 뒤, 성씨가 '김'씨인 회원들을 확인하시오.

---

### 문제 19: 조건부 삭제 및 확인

40대 회원의 레코드를 삭제한 뒤, 35세부터 53세까지의 회원의 이름, 나이, 성별을 나이순으로 출력하여 확인하시오.

---

## 참고사항

### 주요 SQL 키워드

- `SELECT`: 조회할 컬럼 지정
- `FROM`: 조회할 테이블 지정
- `WHERE`: 조건 지정
- `LIKE`: 패턴 매칭
- `ORDER BY`: 정렬
- `AND`: 논리곱 (모두 만족)
- `OR`: 논리합 (하나라도 만족)
- `COUNT()`: 개수 집계
- `AVG()`: 평균 계산
- `MAX()`: 최대값
- `MIN()`: 최소값
- `GROUP BY`: 그룹화

### LIKE 패턴 정리

- `'김%'`: '김'으로 시작
- `'%수'`: '수'로 끝남
- `'%김%'`: '김'이 포함됨
- `'김__'`: '김'으로 시작하고 3글자
- `'_김%'`: 두 번째 글자가 '김'

### 연산자 우선순위

1. 괄호 `()`
2. 비교 연산자 (`=`, `>`, `<`, `>=`, `<=`, `<>`, `!=`)
3. `NOT`
4. `AND`
5. `OR`

**주의:** `OR`보다 `AND`가 우선순위가 높으므로, `OR`를 사용할 때는 괄호를 사용하는 것이 안전합니다.
