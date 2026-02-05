# Java Optional 예제 코드

Java Optional을 학습하기 위한 실습 예제 코드입니다.

## 파일 구조

```
Optional-예제코드/
├── OptionalExamples.java      # 기본 예제 모음
├── UserRepositoryExample.java # 실전 예제: 사용자 조회 시스템
└── README.md                  # 이 파일
```

## 컴파일 및 실행

### 1. 컴파일

```bash
javac -d . optional/*.java
```

### 2. 실행

```bash
# 기본 예제 실행
java optional.OptionalExamples

# 사용자 조회 시스템 예제 실행
java optional.UserRepositoryExample
```

## 포함된 예제

### OptionalExamples.java

1. **기본 사용법**
   - `Optional.of()`, `Optional.ofNullable()`, `Optional.empty()`
   - `isPresent()`, `orElse()`

2. **체이닝을 통한 데이터 처리**
   - `map()`, `filter()` 사용
   - null 안전 처리

3. **Stream과 함께 사용**
   - null 제거 및 필터링
   - 리스트 처리

4. **중첩 Optional 처리**
   - `flatMap()` 사용법
   - 중첩 Optional 펼치기

5. **점수 계산 시스템**
   - Optional과 Stream 조합
   - 집계 함수 사용

6. **조건부 처리**
   - `filter()`와 `ifPresentOrElse()` 사용

### UserRepositoryExample.java

실전 예제로 사용자 조회 시스템을 구현했습니다.

**주요 기능:**
- 사용자 ID로 사용자 조회
- Optional을 사용한 안전한 null 처리
- 기존 방식과 Optional 방식 비교

## 학습 순서

1. `OptionalExamples.java` 실행하여 기본 개념 이해
2. `UserRepositoryExample.java` 실행하여 실전 활용 확인
3. 각 예제 코드를 수정하며 실습
4. `Java-Optional-완전정복.md` 문서 참고

## 참고 자료

- `Java-Optional-완전정복.md` - 상세한 설명과 예제
