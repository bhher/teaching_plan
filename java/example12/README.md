# 12장 예제 파일 실행 방법

## 명령줄에서 실행하기

### 1. JVMStructure.java
```bash
javac JVMStructure.java
java JVMStructure
```

### 2. StackExample.java
```bash
javac StackExample.java
java StackExample
```

### 3. HeapExample.java
```bash
javac HeapExample.java
java HeapExample
```

### 4. MethodAreaExample.java
```bash
javac MethodAreaExample.java
java MethodAreaExample
```

### 5. GarbageCollectionExample.java
```bash
javac GarbageCollectionExample.java
java GarbageCollectionExample
```

### 6. MemoryErrorExample.java
```bash
javac MemoryErrorExample.java
java MemoryErrorExample
```

### 7. MemoryManagement.java
```bash
javac MemoryManagement.java
java MemoryManagement
```

## VS Code에서 실행하기

1. 각 `.java` 파일을 열기
2. `main` 메서드 위에 나타나는 "Run" 버튼 클릭
3. 또는 `F5` 키를 눌러 실행

## 주의사항

- 클래스 이름과 파일 이름이 반드시 일치해야 합니다
- Java는 대소문자를 구분합니다
- 각 파일은 하나의 public 클래스를 포함해야 합니다
- 메모리 관련 예제는 실행 환경에 따라 결과가 다를 수 있습니다
- System.gc()는 GC 실행을 요청할 뿐, 보장하지 않습니다

## 학습 포인트

- **JVM 구조**: 클래스 로더, 런타임 데이터 영역, 실행 엔진
- **Stack**: 메서드 호출, 지역 변수 저장
- **Heap**: 객체와 배열 저장
- **Method Area**: 클래스 정보, 정적 변수, 상수 풀
- **Garbage Collection**: 사용하지 않는 객체 자동 제거
- **메모리 오류**: NullPointerException, OutOfMemoryError 등

## 메모리 영역 요약

| 영역 | 저장 내용 | 특징 |
|------|----------|------|
| **Stack** | 지역 변수, 메서드 호출 정보 | LIFO, 스레드별 독립 |
| **Heap** | 객체, 배열 | 동적 할당, 공유, GC 대상 |
| **Method Area** | 클래스 정보, 정적 변수, 상수 | 공유, 프로그램 종료 시 제거 |

## 주요 개념

- **기본 타입**: 스택에 값 직접 저장
- **참조 타입**: 힙에 객체 저장, 스택에 참조 저장
- **가비지**: 참조가 없는 객체
- **GC**: 가비지를 자동으로 제거하는 과정

## 메모리 관리 팁

1. 불필요한 객체 생성 최소화
2. 큰 객체 신중하게 사용
3. 사용하지 않는 참조는 null로 설정
4. 메모리 사용량 모니터링
5. JVM 옵션으로 힙 크기 조정 (-Xmx, -Xms)

