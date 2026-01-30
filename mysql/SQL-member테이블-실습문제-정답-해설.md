# SQL 실습 문제 - member1 테이블 (정답 - 해설)

## 테이블 생성 및 데이터 삽입

### 테이블 생성

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

**기존 테이블을 UTF-8로 변경:**

```sql
ALTER TABLE member1 
CONVERT TO CHARACTER SET utf8mb4 
COLLATE utf8mb4_unicode_ci;
```

**해설:**
- `num`: 회원 일련번호, 자동 증가하는 기본키
- `id`: 회원 아이디 (NOT NULL)
- `name`: 회원 이름 (NOT NULL)
- `gender`: 성별 (M: 남성, W: 여성)
- `post_num`: 우편번호
- `address`: 주소
- `tel`: 전화번호
- `age`: 나이
- `CHARACTER SET utf8mb4`: UTF-8 인코딩 설정 (한글 지원)
- `COLLATE utf8mb4_unicode_ci`: 유니코드 정렬 규칙

**UTF-8 설정이 필요한 이유:**
- 한글 데이터를 올바르게 저장하고 표시하기 위해 필요합니다
- 설정하지 않으면 한글이 깨져서 표시될 수 있습니다
- `utf8mb4`는 완전한 UTF-8 지원 (이모지 포함)

**한글 깨짐 문제 해결:**
- 한글 데이터가 깨지는 경우 `MySQL-UTF8-인코딩-에러-해결가이드.md` 파일을 참고하세요

### 데이터 삽입

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

### 전체 필드와 데이터 검색

**명령 형식:**
```sql
SELECT * FROM 테이블명;
```

**예제:**
```sql
SELECT * FROM member1;
```

**해설:**
- `*`는 모든 컬럼을 의미합니다
- 테이블의 모든 레코드와 모든 필드를 조회합니다
- 데이터 확인이나 전체 데이터를 볼 때 사용합니다

---

### 특정 필드의 전체 레코드 검색

**명령 형식:**
```sql
SELECT 필드명1, 필드명2, 필드명3, ... FROM 테이블명;
```

**예제:**
```sql
SELECT name, tel, address FROM member1;
```

**해설:**
- 필요한 컬럼만 선택하여 조회합니다
- `name`, `tel`, `address` 컬럼만 출력됩니다
- 불필요한 데이터를 제외하여 가독성을 높입니다
- 네트워크 트래픽과 메모리 사용을 줄일 수 있습니다

---

## WHERE 절을 이용한 조건 검색

### 기본 WHERE 절

**명령 형식:**
```sql
SELECT 필드명1, 필드명2, ... FROM 테이블명 WHERE 조건;
```

**예제:**
```sql
SELECT name, address, gender FROM member1 WHERE gender='W';
```

**해설:**
- `WHERE` 절은 조건에 맞는 행만 필터링합니다
- `gender='W'` 조건으로 여성 회원만 선택합니다
- 등호(`=`)를 사용하여 정확한 값 매칭을 수행합니다
- 결과: 여성 회원의 이름, 주소, 성별이 출력됩니다

---

### 문제 1: 50세 이상인 남성 회원 검색

**문제:** 50세 이상인 남성 회원의 이름, 전화번호, 성별, 나이 검색

**정답:**
```sql
SELECT name, tel, gender, age 
FROM member1 
WHERE age >= 50 AND gender = 'M';
```

**해설:**
- `age >= 50`: 나이가 50세 이상인 조건
- `gender = 'M'`: 성별이 남성인 조건
- `AND`: 두 조건을 모두 만족해야 함 (논리곱)
- 결과: 김문수(63세), 김길수(54세), 차범길(49세), 지영필(52세), 강부영(62세) 등이 출력됩니다

**주의사항:**
- `AND` 연산자는 대소문자를 구분하지 않지만, 관례상 대문자로 작성합니다
- 조건이 여러 개일 때는 괄호를 사용하여 우선순위를 명확히 할 수 있습니다

---

### 문제 2: 20대 회원 검색

**문제:** 20대 회원의 이름, 성별, 주소, 나이 검색

**정답:**
```sql
SELECT name, gender, address, age 
FROM member1 
WHERE age >= 20 AND age <= 29;
```

**또는:**
```sql
SELECT name, gender, address, age 
FROM member1 
WHERE age BETWEEN 20 AND 29;
```

**해설:**
- `age >= 20 AND age <= 29`: 나이가 20세 이상 29세 이하
- `BETWEEN 20 AND 29`: 위와 동일한 의미 (더 간결함)
- `BETWEEN`은 양쪽 끝 값을 포함합니다 (20과 29 모두 포함)
- 결과: 장영숙(24세), 박혜빈(22세), 김수련(23세), 강찬숙(21세), 박지은(26세), 이지현(27세), 문진영(18세 - 제외), 전지연(28세), 고진길(27세) 등이 출력됩니다

**참고:**
- `BETWEEN`은 숫자뿐만 아니라 날짜, 문자열에도 사용 가능합니다
- `NOT BETWEEN`을 사용하면 범위 밖의 값을 선택할 수 있습니다

---

### 문제 3: 30대 또는 50대 남성 회원 검색

**문제:** 30대 또는 50대 남성 회원의 이름, 전화번호, 나이, 성별 검색

**정답:**
```sql
SELECT name, tel, age, gender 
FROM member1 
WHERE ((age >= 30 AND age <= 39) OR (age >= 50 AND age <= 59)) 
  AND gender = 'M';
```

**해설:**
- `(age >= 30 AND age <= 39)`: 30대 조건
- `(age >= 50 AND age <= 59)`: 50대 조건
- `OR`: 두 조건 중 하나만 만족하면 됨 (논리합)
- `AND gender = 'M'`: 성별이 남성이어야 함
- 괄호를 사용하여 연산자 우선순위를 명확히 합니다
- 결과: 30대 남성과 50대 남성 회원이 모두 출력됩니다

**주의사항:**
- `OR` 연산자의 우선순위가 `AND`보다 낮으므로 괄호를 사용해야 합니다
- 괄호 없이 작성하면 의도와 다른 결과가 나올 수 있습니다

**잘못된 예:**
```sql
-- 이렇게 하면 30대이거나 50대이거나 남성인 모든 회원이 선택됨
SELECT name, tel, age, gender 
FROM member1 
WHERE age >= 30 AND age <= 39 OR age >= 50 AND age <= 59 AND gender = 'M';
```

---

### 문제 4: 특정 이름 검색

**문제:** 이름이 '안철영'인 회원의 일련번호, 이름, 전화번호, 주소, 우편번호, 나이, 성별 검색

**정답:**
```sql
SELECT num, name, tel, address, post_num, age, gender 
FROM member1 
WHERE name = '안철영';
```

**해설:**
- `name = '안철영'`: 이름이 정확히 '안철영'인 조건
- 등호(`=`)를 사용하여 정확한 문자열 매칭을 수행합니다
- 문자열은 작은따옴표(`'`) 또는 큰따옴표(`"`)로 감싸야 합니다
- 결과: 안철영 회원의 모든 정보가 출력됩니다

**참고:**
- 문자열 비교 시 대소문자를 구분하는지 여부는 데이터베이스 설정에 따라 다릅니다
- MySQL에서는 기본적으로 대소문자를 구분하지 않지만, 설정에 따라 다를 수 있습니다

---

## LIKE 연산자를 이용한 패턴 검색

### LIKE 연산자 기본

**명령 형식:**
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

**문제:** 성씨가 박씨인 회원의 이름, 전화번호 검색

**정답:**
```sql
SELECT name, tel 
FROM member1 
WHERE name LIKE '박%';
```

**해설:**
- `LIKE '박%'`: '박'으로 시작하는 문자열
- `%`는 0개 이상의 임의의 문자를 의미합니다
- '박' 뒤에 어떤 문자가 와도 매칭됩니다
- 결과: 박철호, 박혜빈, 박지은 등이 출력됩니다

**다양한 패턴 예시:**
```sql
-- '박'으로 시작
WHERE name LIKE '박%'

-- '박'으로 끝남
WHERE name LIKE '%박'

-- '박'이 포함됨
WHERE name LIKE '%박%'

-- '박'으로 시작하고 3글자
WHERE name LIKE '박__'
```

---

### 문제 6: 서울에 거주하는 회원 검색

**문제:** 서울에 거주하는 회원의 일련번호, 이름, 주소, 성별 검색

**정답:**
```sql
SELECT num, name, address, gender 
FROM member1 
WHERE address LIKE '서울%';
```

**해설:**
- `address LIKE '서울%'`: 주소가 '서울'로 시작하는 조건
- 주소 필드가 '서울시'로 시작하는 레코드를 찾습니다
- `%`를 사용하여 '서울' 뒤에 어떤 문자가 와도 매칭됩니다
- 결과: 황영주, 배용진, 고영주, 안철영, 고재진, 전지연, 고진길 등이 출력됩니다

**참고:**
- `LIKE` 연산자는 대소문자를 구분하지 않습니다 (설정에 따라 다를 수 있음)
- `NOT LIKE`를 사용하면 조건을 만족하지 않는 레코드를 선택할 수 있습니다

---

### 문제 7: 은평구에 거주하는 회원 검색

**문제:** 은평구에 거주하는 회원의 모든 정보 검색

**정답:**
```sql
SELECT * 
FROM member1 
WHERE address LIKE '%은평구%';
```

**해설:**
- `LIKE '%은평구%'`: '은평구'가 포함된 문자열
- 앞뒤에 `%`를 사용하여 '은평구' 앞뒤에 어떤 문자가 와도 매칭됩니다
- `SELECT *`: 모든 컬럼을 출력합니다
- 결과: 배용진, 고영주, 안철영, 고진길 등이 출력됩니다

**주의사항:**
- `LIKE '%문자열%'` 패턴은 인덱스를 사용하지 못할 수 있어 성능이 떨어질 수 있습니다
- 대용량 데이터에서는 성능을 고려해야 합니다

---

### 문제 8: 복합 조건 검색

**문제:** 성이 김씨이고 광주에 거주하는 회원의 모든 정보 검색

**정답:**
```sql
SELECT * 
FROM member1 
WHERE name LIKE '김%' AND address LIKE '%광주%';
```

**해설:**
- `name LIKE '김%'`: 이름이 '김'으로 시작
- `address LIKE '%광주%'`: 주소에 '광주'가 포함됨
- `AND`: 두 조건을 모두 만족해야 함
- 결과: 김씨이면서 광주에 거주하는 회원만 출력됩니다
- 예상 결과: 김정은(광주시 남구 원산동) 등

**참고:**
- 여러 조건을 조합할 때는 괄호를 사용하여 우선순위를 명확히 할 수 있습니다
- `OR`와 `AND`를 함께 사용할 때는 특히 주의해야 합니다

---

## ORDER BY를 이용한 정렬

### ORDER BY 기본

**명령 형식:**
```sql
SELECT 필드명1, 필드명2, ... 
FROM 테이블명 
ORDER BY 필드명 [ASC|DESC];
```

- `ASC`: 오름차순 정렬 (기본값, 생략 가능)
- `DESC`: 내림차순 정렬

---

### 문제 9: 서울 거주 회원을 나이순 오름차순 정렬

**문제:** 서울에 사는 회원을 나이순으로 오름차순 정렬한 뒤 이름, 나이, 주소, 전화번호, 성별 출력

**정답:**
```sql
SELECT name, age, address, tel, gender 
FROM member1 
WHERE address LIKE '서울%' 
ORDER BY age;
```

**또는:**
```sql
SELECT name, age, address, tel, gender 
FROM member1 
WHERE address LIKE '서울%' 
ORDER BY age ASC;
```

**해설:**
- `WHERE address LIKE '서울%'`: 서울에 거주하는 회원만 필터링
- `ORDER BY age`: 나이를 기준으로 오름차순 정렬 (작은 값부터 큰 값 순서)
- `ASC`는 기본값이므로 생략 가능합니다
- 결과: 서울 거주 회원들이 나이가 적은 순서대로 출력됩니다

**정렬 순서:**
- 숫자: 작은 값 → 큰 값
- 문자열: 사전식 순서 (A → Z, 가 → 힣)
- 날짜: 과거 → 미래

---

### 문제 10: 부산 거주 회원을 나이순 내림차순 정렬

**문제:** 부산에 사는 회원을 나이순으로 내림차순 정렬한 뒤 이름, 나이, 주소, 전화번호 출력

**정답:**
```sql
SELECT name, age, address, tel 
FROM member1 
WHERE address LIKE '부산%' 
ORDER BY age DESC;
```

**해설:**
- `WHERE address LIKE '부산%'`: 부산에 거주하는 회원만 필터링
- `ORDER BY age DESC`: 나이를 기준으로 내림차순 정렬 (큰 값부터 작은 값 순서)
- `DESC`는 내림차순을 의미하며 생략할 수 없습니다
- 결과: 부산 거주 회원들이 나이가 많은 순서대로 출력됩니다

**정렬 예시:**
- 오름차순 (ASC): 18, 22, 24, 27, 28, ...
- 내림차순 (DESC): 62, 54, 47, 41, 33, ...

**여러 컬럼 정렬:**
```sql
-- 나이 내림차순, 같은 나이면 이름 오름차순
SELECT name, age, address, tel 
FROM member1 
WHERE address LIKE '부산%' 
ORDER BY age DESC, name ASC;
```

---

## 추가 실습 문제

### 문제 11: 특정 지역의 회원 수 세기

**문제:** 광주에 거주하는 회원의 수를 구하시오.

**정답:**
```sql
SELECT COUNT(*) AS 회원수
FROM member1
WHERE address LIKE '%광주%';
```

**해설:**
- `COUNT(*)`: 조건을 만족하는 레코드의 개수를 반환
- `AS 회원수`: 결과 컬럼에 별칭을 지정
- `WHERE address LIKE '%광주%'`: 광주가 포함된 주소

---

### 문제 12: 평균 나이 구하기

**문제:** 전체 회원의 평균 나이를 구하시오.

**정답:**
```sql
SELECT AVG(age) AS 평균나이
FROM member1;
```

**해설:**
- `AVG(age)`: age 컬럼의 평균값을 계산
- NULL 값은 계산에서 제외됩니다
- 결과는 소수점이 포함될 수 있습니다

---

### 문제 13: 최고령 회원 찾기

**문제:** 나이가 가장 많은 회원의 이름과 나이를 구하시오.

**정답:**
```sql
SELECT name, age
FROM member1
ORDER BY age DESC
LIMIT 1;
```

**또는:**
```sql
SELECT name, age
FROM member1
WHERE age = (SELECT MAX(age) FROM member1);
```

**해설:**
- 첫 번째 방법: 나이 내림차순 정렬 후 첫 번째 레코드만 선택
- 두 번째 방법: 서브쿼리를 사용하여 최대 나이를 구한 후 해당 나이인 회원 선택
- 두 방법 모두 동일한 결과를 반환합니다

---

### 문제 14: 지역별 회원 수

**문제:** 지역(시/도)별로 회원 수를 구하시오.

**정답:**
```sql
SELECT 
    SUBSTRING_INDEX(address, '시', 1) AS 지역,
    COUNT(*) AS 회원수
FROM member1
GROUP BY SUBSTRING_INDEX(address, '시', 1)
ORDER BY 회원수 DESC;
```

**해설:**
- `SUBSTRING_INDEX(address, '시', 1)`: 주소에서 '시' 앞부분만 추출
- `GROUP BY`: 같은 지역끼리 그룹화
- `COUNT(*)`: 각 그룹의 회원 수 계산
- `ORDER BY 회원수 DESC`: 회원 수가 많은 순서대로 정렬

---

### 문제 15: 성별별 평균 나이

**문제:** 성별별 평균 나이를 구하시오.

**정답:**
```sql
SELECT 
    gender AS 성별,
    AVG(age) AS 평균나이
FROM member1
GROUP BY gender;
```

**해설:**
- `GROUP BY gender`: 성별로 그룹화
- `AVG(age)`: 각 그룹의 평균 나이 계산
- 결과: M(남성)과 W(여성)의 평균 나이가 각각 출력됩니다

---

## UPDATE를 이용한 레코드 수정

### UPDATE 기본

**명령 형식:**
```sql
UPDATE 테이블명 
SET 필드명 = 필드값 
WHERE 조건식;
```

**해설:**
- `UPDATE`: 레코드를 수정하는 명령어
- `SET`: 변경할 필드와 값을 지정
- `WHERE`: 조건을 만족하는 레코드만 수정
- **중요:** `WHERE` 절을 생략하면 모든 레코드가 수정됩니다

---

### 문제 16: 전화번호 수정

**문제:** 이름이 '고재진'인 레코드의 전화번호를 '123-4567'로 변경한 뒤 데이터를 확인하시오.

**정답:**
```sql
-- 전화번호 수정
UPDATE member1 
SET tel = '123-4567' 
WHERE name = '고재진';

-- 수정된 데이터 확인
SELECT name, tel 
FROM member1 
WHERE name = '고재진';
```

**해설:**
- `UPDATE member1`: member1 테이블의 레코드를 수정
- `SET tel = '123-4567'`: tel 필드의 값을 '123-4567'로 변경
- `WHERE name = '고재진'`: 이름이 '고재진'인 레코드만 수정
- 수정 후 `SELECT` 문으로 변경 사항을 확인합니다
- 결과: 고재진 회원의 전화번호가 '836-4655'에서 '123-4567'로 변경됩니다

**주의사항:**
- `WHERE` 절을 생략하면 모든 회원의 전화번호가 변경됩니다
- 수정 전에 `SELECT` 문으로 대상 레코드를 확인하는 것이 좋습니다

---

### 문제 17: 나이 수정

**문제:** 이름이 '정한나'인 회원의 나이를 확인하고 값을 37로 변경한 뒤 확인하시오.

**정답:**
```sql
-- 현재 나이 확인
SELECT name, age 
FROM member1 
WHERE name = '정한나';

-- 나이 수정
UPDATE member1 
SET age = 37 
WHERE name = '정한나';

-- 수정된 나이 확인
SELECT name, age 
FROM member1 
WHERE name = '정한나';
```

**해설:**
- 첫 번째 `SELECT`: 수정 전 나이를 확인 (58세)
- `UPDATE`: 나이를 37로 변경
- 두 번째 `SELECT`: 수정 후 나이를 확인 (37세)
- 수정 전후를 비교하여 올바르게 변경되었는지 확인합니다

**실무 팁:**
- 중요한 데이터 수정 시에는 트랜잭션을 사용하여 롤백 가능하게 하는 것이 좋습니다
- 여러 필드를 동시에 수정할 수 있습니다:
  ```sql
  UPDATE member1 
  SET age = 37, tel = '999-9999' 
  WHERE name = '정한나';
  ```

---

## DELETE를 이용한 레코드 삭제

### DELETE 기본

**명령 형식:**
```sql
DELETE FROM 테이블명 
WHERE 조건식;
```

**해설:**
- `DELETE FROM`: 레코드를 삭제하는 명령어
- `WHERE`: 조건을 만족하는 레코드만 삭제
- **매우 중요:** `WHERE` 절을 생략하면 테이블의 모든 레코드가 삭제됩니다

**주의사항:**
- 삭제된 데이터는 복구하기 어렵습니다
- 삭제 전에 `SELECT` 문으로 대상 레코드를 확인하는 것이 필수입니다
- 프로덕션 환경에서는 백업을 먼저 수행하는 것이 좋습니다

---

### 문제 18: 특정 회원 삭제

**문제:** 이름이 '김수련'인 회원의 레코드를 삭제한 뒤, 성씨가 '김'씨인 회원들을 확인하시오.

**정답:**
```sql
-- 김수련 회원 삭제
DELETE FROM member1 
WHERE name = '김수련';

-- 김씨 회원 확인
SELECT * 
FROM member1 
WHERE name LIKE '김%';
```

**해설:**
- `DELETE FROM member1`: member1 테이블에서 레코드 삭제
- `WHERE name = '김수련'`: 이름이 '김수련'인 레코드만 삭제
- `SELECT * FROM member1 WHERE name LIKE '김%'`: 삭제 후 김씨 회원들을 확인
- 결과: 김수련 회원이 삭제되어 김씨 회원 목록에서 제외됩니다

**삭제 전 확인 예시:**
```sql
-- 삭제할 레코드 확인
SELECT * FROM member1 WHERE name = '김수련';

-- 확인 후 삭제
DELETE FROM member1 WHERE name = '김수련';
```

---

### 문제 19: 조건부 삭제 및 확인

**문제:** 40대 회원의 레코드를 삭제한 뒤, 35세부터 53세까지의 회원의 이름, 나이, 성별을 나이순으로 출력하여 확인하시오.

**정답:**
```sql
-- 40대 회원 삭제
DELETE FROM member1 
WHERE age >= 40 AND age <= 49;

-- 35~53세 회원 확인
SELECT name, age, gender 
FROM member1 
WHERE age >= 35 AND age <= 53 
ORDER BY age;
```

**해설:**
- `DELETE FROM member1 WHERE age >= 40 AND age <= 49`: 40대(40~49세) 회원 삭제
- `SELECT ... WHERE age >= 35 AND age <= 53 ORDER BY age`: 35~53세 회원을 나이순으로 확인
- 결과: 40대 회원들이 삭제되어 35~39세와 50~53세 회원만 출력됩니다
- 삭제된 회원: 차범길(49세), 설영찬(41세), 김진배(47세), 정명윤(47세), 유지수(49세), 신수진(47세) 등

**복합 조건 삭제 예시:**
```sql
-- 여러 조건을 만족하는 레코드 삭제
DELETE FROM member1 
WHERE age >= 40 AND age <= 49 AND gender = 'M';
```

---

### 전체 레코드 삭제

**명령 형식:**
```sql
DELETE FROM 테이블명;
```

**해설:**
- `WHERE` 절을 생략하면 테이블의 모든 레코드가 삭제됩니다
- 테이블 구조는 유지되지만 데이터는 모두 삭제됩니다
- **매우 위험한 명령이므로 신중하게 사용해야 합니다**

**예시:**
```sql
-- 전체 레코드 삭제 (위험!)
DELETE FROM member1;

-- 삭제 확인
SELECT COUNT(*) FROM member1;  -- 결과: 0
```

**주의사항:**
- 실수로 전체 레코드를 삭제할 수 있으므로 주의가 필요합니다
- 프로덕션 환경에서는 권한을 제한하는 것이 좋습니다
- `TRUNCATE TABLE` 명령도 전체 레코드를 삭제하지만, `DELETE`와는 다르게 작동합니다:
  ```sql
  TRUNCATE TABLE member1;  -- 전체 레코드 삭제 (더 빠름, 롤백 불가)
  ```

---

## 정리

### 주요 SQL 키워드

| 키워드 | 용도 | 예시 |
|--------|------|------|
| `SELECT` | 조회할 컬럼 지정 | `SELECT name, age` |
| `FROM` | 조회할 테이블 지정 | `FROM member1` |
| `WHERE` | 조건 지정 | `WHERE age >= 20` |
| `LIKE` | 패턴 매칭 | `WHERE name LIKE '김%'` |
| `ORDER BY` | 정렬 | `ORDER BY age DESC` |
| `AND` | 논리곱 (모두 만족) | `WHERE age >= 20 AND age <= 29` |
| `OR` | 논리합 (하나라도 만족) | `WHERE age < 20 OR age > 60` |
| `COUNT()` | 개수 집계 | `COUNT(*)` |
| `AVG()` | 평균 계산 | `AVG(age)` |
| `MAX()` | 최대값 | `MAX(age)` |
| `MIN()` | 최소값 | `MIN(age)` |
| `GROUP BY` | 그룹화 | `GROUP BY gender` |
| `UPDATE` | 레코드 수정 | `UPDATE member1 SET age = 30 WHERE name = '홍길동'` |
| `DELETE` | 레코드 삭제 | `DELETE FROM member1 WHERE name = '홍길동'` |

### LIKE 패턴 정리

| 패턴 | 의미 | 예시 |
|------|------|------|
| `'김%'` | '김'으로 시작 | 김철수, 김영희 |
| `'%수'` | '수'로 끝남 | 철수, 영수 |
| `'%김%'` | '김'이 포함됨 | 김철수, 박김수 |
| `'김__'` | '김'으로 시작하고 3글자 | 김철수 |
| `'_김%'` | 두 번째 글자가 '김' | 박김수 |

### 연산자 우선순위

1. 괄호 `()`
2. 비교 연산자 (`=`, `>`, `<`, `>=`, `<=`, `<>`, `!=`)
3. `NOT`
4. `AND`
5. `OR`

**주의:** `OR`보다 `AND`가 우선순위가 높으므로, `OR`를 사용할 때는 괄호를 사용하는 것이 안전합니다.

---

## 실습 체크리스트

- [ ] 전체 데이터 조회 (`SELECT *`)
- [ ] 특정 컬럼만 조회 (`SELECT 컬럼명`)
- [ ] 조건 검색 (`WHERE`)
- [ ] 비교 연산자 사용 (`=`, `>`, `<`, `>=`, `<=`)
- [ ] 논리 연산자 사용 (`AND`, `OR`)
- [ ] 패턴 검색 (`LIKE`, `%`, `_`)
- [ ] 정렬 (`ORDER BY`, `ASC`, `DESC`)
- [ ] 집계 함수 사용 (`COUNT`, `AVG`, `MAX`, `MIN`)
- [ ] 그룹화 (`GROUP BY`)
- [ ] 레코드 수정 (`UPDATE`, `SET`)
- [ ] 레코드 삭제 (`DELETE FROM`)

**좋은 SQL 쿼리를 작성하는 핵심은 연습입니다!**

---

## UPDATE/DELETE 주의사항 요약

### UPDATE 주의사항

1. **WHERE 절 필수 확인**
   - `WHERE` 절 없이 `UPDATE` 실행 시 모든 레코드가 수정됩니다
   - 수정 전에 `SELECT` 문으로 대상 레코드를 확인하세요

2. **트랜잭션 사용 권장**
   ```sql
   START TRANSACTION;
   UPDATE member1 SET age = 30 WHERE name = '홍길동';
   -- 확인 후
   COMMIT;  -- 또는 ROLLBACK;
   ```

3. **여러 필드 동시 수정**
   ```sql
   UPDATE member1 
   SET age = 30, tel = '999-9999', address = '서울시' 
   WHERE name = '홍길동';
   ```

### DELETE 주의사항

1. **WHERE 절 필수 확인**
   - `WHERE` 절 없이 `DELETE` 실행 시 모든 레코드가 삭제됩니다
   - 삭제 전에 반드시 `SELECT` 문으로 확인하세요

2. **삭제 전 확인 절차**
   ```sql
   -- 1단계: 삭제할 레코드 확인
   SELECT * FROM member1 WHERE name = '홍길동';
   
   -- 2단계: 확인 후 삭제
   DELETE FROM member1 WHERE name = '홍길동';
   
   -- 3단계: 삭제 확인
   SELECT * FROM member1 WHERE name = '홍길동';
   ```

3. **백업 권장**
   - 중요한 데이터 삭제 전에는 백업을 수행하세요
   - 프로덕션 환경에서는 특히 주의가 필요합니다

4. **TRUNCATE vs DELETE**
   - `DELETE`: 조건부 삭제 가능, 롤백 가능, 느림
   - `TRUNCATE`: 전체 삭제만 가능, 롤백 불가, 빠름
   ```sql
   DELETE FROM member1;      -- 조건부 삭제 가능, 롤백 가능
   TRUNCATE TABLE member1;   -- 전체 삭제만, 롤백 불가
   ```
