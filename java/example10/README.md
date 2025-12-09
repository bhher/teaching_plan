# 10장 예제 파일 실행 방법

## 명령줄에서 실행하기

### 1. ExceptionBasics.java
```bash
javac ExceptionBasics.java
java ExceptionBasics
```

### 2. TryCatchFinally.java
```bash
javac TryCatchFinally.java
java TryCatchFinally
```

### 3. ThrowThrows.java
```bash
javac ThrowThrows.java
java ThrowThrows
```

### 4. CustomException.java
```bash
javac CustomException.java
java CustomException
```

### 5. ExceptionDesign.java
```bash
javac ExceptionDesign.java
java ExceptionDesign
```

### 6. ExceptionComprehensive.java
```bash
javac ExceptionComprehensive.java
java ExceptionComprehensive
```

## VS Code에서 실행하기

1. 각 `.java` 파일을 열기
2. `main` 메서드 위에 나타나는 "Run" 버튼 클릭
3. 또는 `F5` 키를 눌러 실행

## 주의사항

- 클래스 이름과 파일 이름이 반드시 일치해야 합니다
- Java는 대소문자를 구분합니다
- 각 파일은 하나의 public 클래스를 포함해야 합니다
- Checked Exception은 반드시 try-catch 또는 throws로 처리해야 합니다
- Unchecked Exception은 선택적으로 처리할 수 있습니다
- finally 블록은 예외 발생 여부와 관계없이 항상 실행됩니다

## 학습 포인트

- **예외의 개념**: 프로그램 실행 중 발생하는 오류
- **try-catch-finally**: 예외 처리 기본 구조
- **throw**: 예외를 발생시키는 키워드
- **throws**: 예외를 선언하는 키워드
- **사용자 정의 예외**: 프로그램에 맞는 예외 클래스 생성
- **예외 처리 설계**: 효과적인 예외 처리 방법

## 주요 예외 종류

- `NullPointerException`: null 참조
- `ArrayIndexOutOfBoundsException`: 배열 인덱스 초과
- `ArithmeticException`: 산술 연산 오류
- `NumberFormatException`: 숫자 형식 오류
- `IllegalArgumentException`: 잘못된 인자

## 예외 처리 원칙

1. 구체적인 예외 처리
2. 적절한 예외 메시지
3. 예외를 숨기지 않기
4. 리소스 정리 (finally)
5. 예외 전파 vs 예외 처리 선택
6. 예외 변환으로 의미 있는 예외 제공

## Checked vs Unchecked Exception

- **Checked Exception**: 컴파일 시점에 반드시 처리해야 함 (Exception 상속, RuntimeException 제외)
- **Unchecked Exception**: 컴파일 시점에 처리하지 않아도 됨 (RuntimeException 상속)

