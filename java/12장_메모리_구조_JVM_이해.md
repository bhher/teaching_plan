# 12장. 메모리 구조 & JVM 이해

## JVM 구조

### JVM이란?

**JVM(Java Virtual Machine)**은 Java 바이트코드를 실행하는 가상 머신입니다. Java 프로그램은 JVM 위에서 실행되며, 플랫폼 독립성을 제공합니다.

### JVM의 역할

1. **바이트코드 실행**: `.class` 파일을 실행
2. **메모리 관리**: 메모리 할당 및 가비지 컬렉션
3. **플랫폼 독립성**: 운영체제에 맞게 바이트코드를 기계어로 변환
4. **보안 관리**: Java 프로그램의 안전한 실행 보장

### JVM 구조

```
┌─────────────────────────────────┐
│         Java Application         │
├─────────────────────────────────┤
│         Class Loader             │  ← 클래스 로딩
├─────────────────────────────────┤
│    Runtime Data Area (메모리)    │
│  ┌───────────────────────────┐ │
│  │  Method Area               │ │
│  │  Heap                      │ │
│  │  Stack                     │ │
│  │  PC Register               │ │
│  │  Native Method Stack        │ │
│  └───────────────────────────┘ │
├─────────────────────────────────┤
│    Execution Engine              │  ← 바이트코드 실행
│  ┌───────────────────────────┐ │
│  │  Interpreter               │ │
│  │  JIT Compiler              │ │
│  │  Garbage Collector         │ │
│  └───────────────────────────┘ │
├─────────────────────────────────┤
│    Native Method Interface       │
└─────────────────────────────────┘
```

---

## Runtime Data Area (런타임 데이터 영역)

JVM이 프로그램을 실행할 때 사용하는 메모리 영역입니다.

### 주요 메모리 영역

1. **Method Area (메서드 영역)**
2. **Heap (힙 영역)**
3. **Stack (스택 영역)**
4. **PC Register (프로그램 카운터 레지스터)**
5. **Native Method Stack (네이티브 메서드 스택)**

---

## Stack (스택 영역)

### 스택이란?

**스택(Stack)**은 메서드 호출 시 생성되는 메모리 영역입니다. 각 스레드마다 독립적인 스택을 가집니다.

### 스택의 특징

- **LIFO (Last In First Out)**: 마지막에 들어온 것이 먼저 나감
- **스레드별 독립**: 각 스레드마다 별도의 스택
- **지역 변수 저장**: 메서드의 지역 변수와 매개변수 저장
- **메서드 호출 정보**: 메서드 호출 시 스택 프레임 생성

### 스택 프레임 (Stack Frame)

메서드가 호출될 때마다 스택에 생성되는 메모리 블록입니다.

#### 스택 프레임의 구성

```
┌─────────────────────┐
│   지역 변수 영역      │  ← 메서드의 지역 변수
├─────────────────────┤
│   피연산자 스택       │  ← 연산 중간 결과
├─────────────────────┤
│   프레임 데이터       │  ← 반환 주소, 예외 정보
└─────────────────────┘
```

### 스택 예제

```java
public class StackExample {
    public static void method1() {
        int a = 10;  // 스택에 저장
        method2();
    }
    
    public static void method2() {
        int b = 20;  // 스택에 저장
    }
    
    public static void main(String[] args) {
        method1();
    }
}
```

#### 메모리 상태

```
main() 호출:
┌─────────────┐
│ main 스택    │
│ args        │
└─────────────┘

method1() 호출:
┌─────────────┐
│ method2 스택 │
│ b = 20      │
├─────────────┤
│ method1 스택 │
│ a = 10      │
├─────────────┤
│ main 스택    │
│ args        │
└─────────────┘
```

### 스택의 장점

- **빠른 접근**: 스택 포인터로 빠르게 접근
- **자동 관리**: 메서드 종료 시 자동으로 해제
- **메모리 효율**: 필요한 만큼만 사용

---

## Heap (힙 영역)

### 힙이란?

**힙(Heap)**은 객체가 저장되는 메모리 영역입니다. 모든 객체와 배열이 힙에 저장됩니다.

### 힙의 특징

- **동적 할당**: 런타임에 메모리 할당
- **공유**: 모든 스레드가 공유
- **가비지 컬렉션**: 사용하지 않는 객체 자동 정리
- **참조로 접근**: 객체는 참조(주소)로 접근

### 힙 영역 구조

```
┌─────────────────────────────┐
│        Heap (힙 영역)         │
├─────────────────────────────┤
│  Young Generation            │
│  ┌───────────────────────┐ │
│  │  Eden                  │ │  ← 새 객체 생성
│  │  Survivor 0           │ │  ← GC 후 살아남은 객체
│  │  Survivor 1           │ │  ← GC 후 살아남은 객체
│  └───────────────────────┘ │
├─────────────────────────────┤
│  Old Generation             │  ← 오래된 객체
│  (Tenured Generation)        │
└─────────────────────────────┘
```

### 객체 생성과 힙

```java
// 객체 생성 시 힙에 저장
String str = new String("Hello");  // 힙에 String 객체 생성
int[] numbers = new int[10];        // 힙에 배열 생성
```

#### 메모리 상태

```
Stack                    Heap
┌─────────┐            ┌──────────────┐
│ str     │ ────────→  │ String 객체  │
│ (참조)  │            │ "Hello"      │
└─────────┘            └──────────────┘

┌─────────┐            ┌──────────────┐
│ numbers │ ────────→  │ int[10] 배열 │
│ (참조)  │            │ [0,0,0,...]  │
└─────────┘            └──────────────┘
```

### 힙의 장점

- **유연성**: 런타임에 크기 조정 가능
- **대용량**: 큰 객체도 저장 가능
- **공유**: 여러 참조가 같은 객체 공유 가능

---

## Method Area (메서드 영역)

### 메서드 영역이란?

**메서드 영역(Method Area)**은 클래스 정보, 메서드 정보, 상수 등을 저장하는 영역입니다.

### 메서드 영역의 특징

- **클래스 정보**: 클래스의 구조, 메서드, 필드 정보
- **정적 변수**: `static` 변수 저장
- **상수 풀**: 문자열 리터럴 등 상수 저장
- **메서드 코드**: 메서드의 바이트코드 저장

### 메서드 영역 저장 내용

```java
public class Student {
    static int count = 0;        // 메서드 영역에 저장
    final String SCHOOL = "ABC"; // 메서드 영역에 저장
    
    String name;                 // 힙에 저장 (인스턴스 변수)
    
    public void study() {        // 메서드 영역에 저장
        // 메서드 코드
    }
}
```

### 메서드 영역 구조

```
┌─────────────────────────────┐
│     Method Area              │
├─────────────────────────────┤
│  클래스 정보                  │
│  - 클래스 이름                │
│  - 부모 클래스 정보           │
│  - 메서드 정보                │
│  - 필드 정보                  │
├─────────────────────────────┤
│  정적 변수 (static)          │
│  - Student.count             │
├─────────────────────────────┤
│  상수 풀 (Constant Pool)     │
│  - 문자열 리터럴             │
│  - 상수 값                    │
└─────────────────────────────┘
```

---

## 객체 생성과 메모리

### 객체 생성 과정

```java
Student student = new Student("홍길동", 20);
```

#### 메모리 할당 과정

1. **힙에 객체 생성**: `new Student()` 호출 시 힙에 객체 할당
2. **생성자 실행**: 객체 초기화
3. **참조 변수에 주소 저장**: 스택의 `student` 변수에 힙 주소 저장

### 메모리 상태 예제

```java
public class MemoryExample {
    public static void main(String[] args) {
        // 기본 타입: 스택에 직접 저장
        int num = 10;  // 스택에 10 저장
        
        // 참조 타입: 힙에 객체 생성, 스택에 참조 저장
        String str = new String("Hello");  // 힙에 객체, 스택에 참조
        
        // 배열: 힙에 배열 생성
        int[] numbers = new int[5];  // 힙에 배열, 스택에 참조
        
        // 객체: 힙에 객체 생성
        Student student = new Student("홍길동", 20);  // 힙에 객체, 스택에 참조
    }
}
```

#### 메모리 상태

```
Stack (스택)
┌─────────────┐
│ num = 10    │  ← 기본 타입 (값 직접 저장)
├─────────────┤
│ str ────────┼──→ Heap (힙)
├─────────────┤    ┌──────────────┐
│ numbers ────┼──→ │ String 객체   │
├─────────────┤    │ "Hello"      │
│ student ────┼──→ └──────────────┘
└─────────────┘    ┌──────────────┐
                    │ int[5] 배열  │
                    │ [0,0,0,0,0] │
                    └──────────────┘
                    ┌──────────────┐
                    │ Student 객체 │
                    │ name, age    │
                    └──────────────┘
```

### 기본 타입 vs 참조 타입 메모리

| 구분 | 기본 타입 | 참조 타입 |
|------|----------|-----------|
| 저장 위치 | 스택 | 힙 (객체), 스택 (참조) |
| 저장 내용 | 실제 값 | 객체의 주소 (참조) |
| 크기 | 고정 (int: 4 bytes) | 가변 (객체 크기에 따라) |
| 예시 | `int num = 10;` | `String str = new String();` |

---

## Garbage Collection 개념

### 가비지 컬렉션이란?

**가비지 컬렉션(Garbage Collection, GC)**은 사용하지 않는 객체를 자동으로 메모리에서 제거하는 과정입니다.

### 가비지 컬렉션의 필요성

- **메모리 누수 방지**: 사용하지 않는 객체 제거
- **자동 메모리 관리**: 프로그래머가 직접 메모리 해제 불필요
- **안정성**: 잘못된 메모리 해제로 인한 오류 방지

### 객체가 가비지가 되는 조건

객체에 대한 참조가 없을 때 가비지가 됩니다.

```java
String str1 = new String("Hello");
str1 = null;  // 이제 "Hello" 객체는 가비지가 됨

String str2 = new String("World");
str2 = str1;   // "World" 객체도 가비지가 됨
```

### 힙 영역 구조와 GC

```
┌─────────────────────────────┐
│  Young Generation           │
│  ┌───────────────────────┐ │
│  │ Eden                  │ │  ← 새 객체 생성
│  │ (Minor GC 발생)        │ │
│  └───────────────────────┘ │
│  ┌───────┐  ┌───────┐     │
│  │ S0    │  │ S1    │     │  ← GC 후 살아남은 객체
│  └───────┘  └───────┘     │
├─────────────────────────────┤
│  Old Generation             │
│  (Major GC 발생)            │  ← 오래된 객체
└─────────────────────────────┘
```

### GC 과정

1. **Minor GC**: Young Generation에서 발생하는 가비지 컬렉션
   - Eden 영역이 가득 차면 발생
   - 살아있는 객체는 Survivor 영역으로 이동
   - 여러 번 살아남은 객체는 Old Generation으로 이동

2. **Major GC**: Old Generation에서 발생하는 가비지 컬렉션
   - Old Generation이 가득 차면 발생
   - 시간이 오래 걸림 (Stop-the-World)

### GC 동작 예제

```java
public class GarbageCollectionExample {
    public static void main(String[] args) {
        // 객체 생성
        String obj1 = new String("Object 1");
        String obj2 = new String("Object 2");
        
        // 참조 변경
        obj1 = obj2;  // "Object 1"은 가비지가 됨
        
        // null 할당
        obj2 = null;  // "Object 2"는 아직 참조됨 (obj1이 참조)
        
        obj1 = null;  // 이제 "Object 2"도 가비지가 됨
        
        // GC가 실행되면 가비지 객체들이 메모리에서 제거됨
    }
}
```

### GC 최적화 팁

1. **불필요한 객체 생성 최소화**: 루프 안에서 객체 생성 피하기
2. **큰 객체 주의**: 큰 객체는 Old Generation에 바로 할당
3. **참조 해제**: 사용하지 않는 참조는 `null`로 설정
4. **StringBuilder 사용**: 문자열 연결 시 StringBuilder 사용

---

## 메모리 관점에서의 오류 이해

### NullPointerException

**원인**: null 참조로 객체에 접근하려고 할 때 발생

```java
String str = null;
int length = str.length();  // NullPointerException 발생
```

#### 메모리 관점

```
Stack                Heap
┌─────────┐         ┌─────────┐
│ str     │ ────→   │  null   │  ← 참조가 없음
│ (null)  │         └─────────┘
└─────────┘
```

### OutOfMemoryError

**원인**: 힙 메모리가 부족할 때 발생

```java
// 너무 많은 객체 생성
List<String> list = new ArrayList<>();
while (true) {
    list.add(new String("Very long string..."));
    // 힙 메모리 부족 시 OutOfMemoryError 발생
}
```

#### 해결 방법

- JVM 옵션으로 힙 크기 증가: `-Xmx2g` (최대 2GB)
- 불필요한 객체 제거
- 메모리 누수 확인

### StackOverflowError

**원인**: 스택이 가득 찰 때 발생 (주로 무한 재귀)

```java
public void recursive() {
    recursive();  // 무한 재귀 → StackOverflowError
}
```

#### 메모리 관점

```
Stack (스택이 가득 참)
┌─────────────┐
│ recursive() │
├─────────────┤
│ recursive() │
├─────────────┤
│ recursive() │
├─────────────┤
│ ...         │  ← 스택 오버플로우!
└─────────────┘
```

### ArrayIndexOutOfBoundsException

**원인**: 배열 인덱스가 범위를 벗어날 때 발생

```java
int[] numbers = new int[5];
System.out.println(numbers[10]);  // ArrayIndexOutOfBoundsException
```

#### 메모리 관점

```
Stack                Heap
┌─────────┐         ┌──────────────────┐
│ numbers │ ────→   │ int[5] 배열      │
│         │         │ [0][1][2][3][4]  │
└─────────┘         └──────────────────┘
                              ↑
                    인덱스 10은 존재하지 않음
```

---

## 메모리 관리 실습 예제

### 예제 1: 메모리 사용 확인

```java
public class MemoryCheck {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        
        // 전체 메모리
        long totalMemory = runtime.totalMemory();
        // 사용 가능한 메모리
        long freeMemory = runtime.freeMemory();
        // 사용 중인 메모리
        long usedMemory = totalMemory - freeMemory;
        // 최대 메모리
        long maxMemory = runtime.maxMemory();
        
        System.out.println("전체 메모리: " + totalMemory / 1024 / 1024 + " MB");
        System.out.println("사용 중인 메모리: " + usedMemory / 1024 / 1024 + " MB");
        System.out.println("사용 가능한 메모리: " + freeMemory / 1024 / 1024 + " MB");
        System.out.println("최대 메모리: " + maxMemory / 1024 / 1024 + " MB");
    }
}
```

### 예제 2: 가비지 컬렉션 강제 실행

```java
public class GCExample {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        
        System.out.println("GC 전 메모리: " + 
            (runtime.totalMemory() - runtime.freeMemory()) / 1024 / 1024 + " MB");
        
        // 많은 객체 생성
        for (int i = 0; i < 100000; i++) {
            new String("Object " + i);
        }
        
        System.out.println("객체 생성 후 메모리: " + 
            (runtime.totalMemory() - runtime.freeMemory()) / 1024 / 1024 + " MB");
        
        // GC 강제 실행 (권장하지 않음, 예제용)
        System.gc();
        
        System.out.println("GC 후 메모리: " + 
            (runtime.totalMemory() - runtime.freeMemory()) / 1024 / 1024 + " MB");
    }
}
```

---

## 메모리 관리 팁

### 1. 객체 재사용

```java
// 나쁜 예: 매번 새 객체 생성
for (int i = 0; i < 1000; i++) {
    String str = new String("Hello");  // 불필요한 객체 생성
}

// 좋은 예: 객체 재사용
String str = "Hello";
for (int i = 0; i < 1000; i++) {
    // 같은 객체 재사용
}
```

### 2. 큰 객체 주의

```java
// 큰 배열 생성 시 주의
int[] largeArray = new int[10000000];  // 약 40MB
```

### 3. 참조 해제

```java
// 사용하지 않는 참조는 null로 설정
String largeString = new String("Very long string...");
// 사용 완료 후
largeString = null;  // GC가 메모리 해제 가능
```

### 4. 컬렉션 크기 관리

```java
// 초기 용량 지정으로 재할당 방지
ArrayList<String> list = new ArrayList<>(1000);
```

---

## 연습 문제

1. **메모리 영역 이해**
   - 기본 타입과 참조 타입이 각각 어느 메모리 영역에 저장되는지 설명하세요.

2. **스택 프레임**
   - 메서드 호출 시 스택에 생성되는 프레임의 내용을 설명하세요.

3. **가비지 컬렉션**
   - 객체가 가비지가 되는 조건을 설명하고 예제를 작성하세요.

4. **메모리 오류**
   - NullPointerException과 OutOfMemoryError의 원인을 메모리 관점에서 설명하세요.

5. **메모리 관리**
   - 메모리 사용량을 확인하고 GC를 이해하는 프로그램을 작성하세요.

---

## 다음 장 예고

다음 장에서는 Java의 고급 기능들을 학습하여 더욱 효율적이고 안전한 프로그램을 작성하는 방법을 배웁니다.

