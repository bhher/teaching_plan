# Java String 예제 코드

## 목차

1. [StringBasics.java](#stringbasicsjava)
2. [StringMethods.java](#stringmethodsjava)
3. [StringExample1.java](#stringexample1java)
4. [StringExample2.java](#stringexample2java)
5. [StringExample3.java](#stringexample3java)
6. [StringExample4.java](#stringexample4java)
7. [StringExample5.java](#stringexample5java)
8. [StringBestPractices.java](#stringbestpracticesjava)

---

## StringBasics.java

**설명:** String의 기본 개념을 설명하는 예제

**학습 내용:**
- String 생성 방법 (리터럴, new 연산자, char 배열)
- String 비교 (== vs equals)
- String 연결 (Concatenation)
- String의 불변성 (Immutability)
- null과 빈 문자열 처리
- String과 char 배열 변환

**실행 방법:**
```bash
javac StringBasics.java
java StringBasics
```

---

## StringMethods.java

**설명:** String의 주요 메서드를 설명하는 예제

**학습 내용:**
- 길이 및 빈 문자열 확인: `length()`, `isEmpty()`
- 문자열 검색: `charAt()`, `indexOf()`, `lastIndexOf()`, `contains()`, `startsWith()`, `endsWith()`
- 문자열 추출: `substring()`, `split()`, `trim()`
- 문자열 변환: `toUpperCase()`, `toLowerCase()`, `replace()`
- 문자열 비교: `compareTo()`, `compareToIgnoreCase()`, `equalsIgnoreCase()`
- 타입 변환: `valueOf()`

**실행 방법:**
```bash
javac StringMethods.java
java StringMethods
```

---

## StringExample1.java

**설명:** 기본 String 사용 예제

**학습 내용:**
- String 생성
- 문자열 연결
- 길이 확인
- 대소문자 변환

**실행 방법:**
```bash
javac StringExample1.java
java StringExample1
```

---

## StringExample2.java

**설명:** 문자열 검색 및 추출 예제

**학습 내용:**
- 문자열 검색 (`indexOf()`, `contains()`)
- 부분 문자열 추출 (`substring()`)
- 문자열 분리 (`split()`)

**실행 방법:**
```bash
javac StringExample2.java
java StringExample2
```

---

## StringExample3.java

**설명:** 문자열 비교 예제

**학습 내용:**
- `==` 연산자 (참조 비교)
- `equals()` 메서드 (내용 비교)
- `equalsIgnoreCase()` 메서드 (대소문자 무시 비교)

**실행 방법:**
```bash
javac StringExample3.java
java StringExample3
```

---

## StringExample4.java

**설명:** StringBuilder 사용 예제

**학습 내용:**
- StringBuilder 생성 및 사용
- `append()`, `insert()`, `delete()`, `replace()`, `reverse()` 메서드
- 성능 비교 (+ 연산자 vs StringBuilder)

**실행 방법:**
```bash
javac StringExample4.java
java StringExample4
```

---

## StringExample5.java

**설명:** 실용적인 문자열 처리 예제

**학습 내용:**
- 이메일 검증
- 파일 확장자 추출
- 공백 제거
- 문자열 반복 (`repeat()`)
- 전화번호 포맷팅
- 주민번호 마스킹
- 단어 개수 세기
- 문자열 뒤집기
- 대소문자 변환
- 문자열 치환

**실행 방법:**
```bash
javac StringExample5.java
java StringExample5
```

---

## StringBestPractices.java

**설명:** String 모범 사례 및 주의사항 예제

**학습 내용:**
- null 체크 방법
- 문자열 비교 시 주의사항
- 많은 문자열 연결 시 StringBuilder 사용
- 문자열 풀 활용
- 인덱스 범위 확인
- 정규표현식 사용 시 주의
- 빈 문자열과 공백 문자열 처리

**실행 방법:**
```bash
javac StringBestPractices.java
java StringBestPractices
```

---

## 전체 컴파일 및 실행

모든 파일을 한 번에 컴파일:
```bash
javac *.java
```

개별 실행:
```bash
java StringBasics
java StringMethods
java StringExample1
java StringExample2
java StringExample3
java StringExample4
java StringExample5
java StringBestPractices
```

---

## 학습 순서 권장

1. **StringBasics.java** - 기본 개념 이해
2. **StringMethods.java** - 주요 메서드 학습
3. **StringExample1.java** - 기본 사용법
4. **StringExample2.java** - 검색 및 추출
5. **StringExample3.java** - 비교 방법
6. **StringExample4.java** - StringBuilder 사용
7. **StringExample5.java** - 실전 활용
8. **StringBestPractices.java** - 모범 사례 및 주의사항

---

## 관련 문서

- `Java-String-완전정복.md`: String에 대한 상세한 설명 문서

---

**작성일:** 2026-01-30  
**범위:** Java String 예제 코드 모음
