# 5장 예제 파일 실행 방법

## 명령줄에서 실행하기

### 1. OneDimensionalArray.java
```bash
javac OneDimensionalArray.java
java OneDimensionalArray
```

### 2. TwoDimensionalArray.java
```bash
javac TwoDimensionalArray.java
java TwoDimensionalArray
```

### 3. ArrayExamples.java
```bash
javac ArrayExamples.java
java ArrayExamples
```

### 4. StringBasics.java
```bash
javac StringBasics.java
java StringBasics
```

### 5. StringMethods.java
```bash
javac StringMethods.java
java StringMethods
```

### 6. StringBuilderExample.java
```bash
javac StringBuilderExample.java
java StringBuilderExample
```

### 7. ArrayStringComprehensive.java
```bash
javac ArrayStringComprehensive.java
java ArrayStringComprehensive
```

## VS Code에서 실행하기

1. 각 `.java` 파일을 열기
2. `main` 메서드 위에 나타나는 "Run" 버튼 클릭
3. 또는 `F5` 키를 눌러 실행

## 주의사항

- 클래스 이름과 파일 이름이 반드시 일치해야 합니다
- Java는 대소문자를 구분합니다
- 각 파일은 하나의 public 클래스를 포함해야 합니다
- 배열 인덱스는 0부터 시작합니다 (범위를 벗어나면 ArrayIndexOutOfBoundsException 발생)
- String 비교는 `==` 대신 `equals()` 메서드를 사용하세요
- 문자열을 자주 변경할 때는 StringBuilder를 사용하는 것이 효율적입니다

## 학습 포인트

- **배열**: 같은 타입의 데이터를 효율적으로 관리
- **1차원 배열**: 선언, 초기화, 접근 방법
- **2차원 배열**: 행과 열로 구성된 데이터 구조
- **String**: 문자열 처리의 기본 클래스
- **String 메서드**: 검색, 추출, 변환 등 다양한 문자열 처리
- **StringBuilder**: 문자열을 자주 변경할 때 사용

