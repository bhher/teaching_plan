# Static (정적 변수와 메서드)

## 📌 학습 목표

- static 변수의 개념과 특징을 이해한다
- static 메서드의 개념과 특징을 이해한다
- static과 인스턴스의 차이를 구분할 수 있다
- static을 언제 사용해야 하는지 알 수 있다

---

## 1. Static이란?

### 기본 개념

**static**은 "정적"이라는 의미로, **객체 생성 없이 사용할 수 있는** 변수나 메서드를 의미합니다.

- **static 변수**: 클래스 변수 (모든 객체가 공유)
- **static 메서드**: 클래스 메서드 (객체 생성 없이 호출 가능)

### 메모리 구조

```
┌─────────────────────────────────────┐
│         Method Area (Static)        │
│  ┌───────────────────────────────┐  │
│  │ static 변수                   │  │
│  │ static 메서드                 │  │
│  │ 클래스 정보                   │  │
│  └───────────────────────────────┘  │
└─────────────────────────────────────┘

┌─────────────────────────────────────┐
│         Heap (Instance)             │
│  ┌─────────┐  ┌─────────┐          │
│  │ 객체1   │  │ 객체2   │          │
│  │ (인스턴스│  │ (인스턴스│          │
│  │  변수)  │  │  변수)  │          │
│  └─────────┘  └─────────┘          │
└─────────────────────────────────────┘
```

- **static**: Method Area에 저장 (클래스 로드 시 생성)
- **인스턴스**: Heap에 저장 (객체 생성 시 생성)

---

## 2. Static 변수 (클래스 변수)

### Static 변수의 특징

1. **클래스가 로드될 때 생성** (객체 생성 전)
2. **모든 객체가 공유** (하나의 메모리 공간)
3. **클래스명으로 접근** (`클래스명.변수명`)
4. **프로그램 종료 시까지 유지**

### Static 변수 예제

```java
public class Counter {
    // 인스턴스 변수 (각 객체마다 별도로 존재)
    int instanceCount = 0;
    
    // static 변수 (모든 객체가 공유)
    static int staticCount = 0;
    
    public Counter() {
        instanceCount++;
        staticCount++;
    }
    
    public void printCount() {
        System.out.println("인스턴스 카운트: " + instanceCount);
        System.out.println("static 카운트: " + staticCount);
    }
}

// 사용 예제
public class CounterTest {
    public static void main(String[] args) {
        // static 변수는 객체 생성 전에도 접근 가능
        System.out.println("초기 staticCount: " + Counter.staticCount);
        
        Counter c1 = new Counter();
        c1.printCount();
        // 출력:
        // 인스턴스 카운트: 1
        // static 카운트: 1
        
        Counter c2 = new Counter();
        c2.printCount();
        // 출력:
        // 인스턴스 카운트: 1  (c2의 instanceCount는 1)
        // static 카운트: 2    (모든 객체가 공유하므로 증가)
        
        Counter c3 = new Counter();
        c3.printCount();
        // 출력:
        // 인스턴스 카운트: 1  (c3의 instanceCount는 1)
        // static 카운트: 3    (공유 변수이므로 계속 증가)
        
        // static 변수는 클래스명으로 직접 접근
        System.out.println("최종 staticCount: " + Counter.staticCount);  // 3
    }
}
```

### Static 변수 vs 인스턴스 변수 비교

| 구분 | Static 변수 | 인스턴스 변수 |
|------|------------|--------------|
| **생성 시점** | 클래스 로드 시 | 객체 생성 시 |
| **메모리 위치** | Method Area | Heap |
| **개수** | 1개 (클래스당) | 여러 개 (객체마다) |
| **공유** | 모든 객체가 공유 | 각 객체마다 독립 |
| **접근 방법** | `클래스명.변수명` | `객체명.변수명` |
| **초기화** | 자동 초기화 (기본값) | 자동 초기화 (기본값) |

### Static 변수 사용 예제

```java
public class Student {
    // 인스턴스 변수
    String name;
    int studentId;
    
    // static 변수: 학생 수 카운터
    static int totalStudents = 0;
    
    // static 변수: 학교명 (모든 학생이 같음)
    static String schoolName = "우리 학교";
    
    public Student(String name) {
        this.name = name;
        this.studentId = ++totalStudents;  // 학생 ID는 자동 증가
    }
    
    public void printInfo() {
        System.out.println("학교: " + schoolName);
        System.out.println("학번: " + studentId);
        System.out.println("이름: " + name);
    }
    
    // static 메서드: 총 학생 수 반환
    public static int getTotalStudents() {
        return totalStudents;
    }
    
    // static 메서드: 학교명 변경
    public static void setSchoolName(String name) {
        schoolName = name;
    }
}

// 사용 예제
public class StudentTest {
    public static void main(String[] args) {
        // static 변수 접근 (객체 생성 전)
        System.out.println("학교명: " + Student.schoolName);
        System.out.println("총 학생 수: " + Student.getTotalStudents());
        
        Student s1 = new Student("홍길동");
        Student s2 = new Student("김철수");
        Student s3 = new Student("이영희");
        
        s1.printInfo();
        s2.printInfo();
        s3.printInfo();
        
        // static 변수는 클래스명으로 접근
        System.out.println("총 학생 수: " + Student.getTotalStudents());  // 3
        
        // 학교명 변경 (모든 학생에게 영향)
        Student.setSchoolName("새로운 학교");
        System.out.println("변경된 학교명: " + Student.schoolName);
    }
}
```

---

## 3. Static 메서드 (클래스 메서드)

### Static 메서드의 특징

1. **객체 생성 없이 호출 가능**
2. **클래스명으로 호출** (`클래스명.메서드명()`)
3. **인스턴스 변수 접근 불가** (static 변수만 접근 가능)
4. **유틸리티 함수에 적합**

### Static 메서드 정의

```java
public class MathUtils {
    // static 메서드
    public static int add(int a, int b) {
        return a + b;
    }
    
    public static double calculateCircleArea(double radius) {
        return Math.PI * radius * radius;
    }
    
    // static 변수도 정의 가능
    static final double PI = 3.14159;
}
```

### Static 메서드 호출

```java
// ✅ 올바른 방법: 클래스명으로 호출
int sum = MathUtils.add(10, 20);
double area = MathUtils.calculateCircleArea(5.0);

// ❌ 잘못된 방법: 객체 생성 후 호출 (경고 발생, 작동은 함)
MathUtils obj = new MathUtils();
int sum2 = obj.add(10, 20);  // 경고: static 메서드를 인스턴스 방식으로 호출

// 같은 클래스 내에서는 클래스명 생략 가능
public class MathUtils {
    public static void main(String[] args) {
        int result = add(5, 3);  // 클래스명 생략 가능
    }
}
```

### Static 메서드 vs 인스턴스 메서드

| 구분 | Static 메서드 | 인스턴스 메서드 |
|------|--------------|----------------|
| **호출 방법** | `클래스명.메서드명()` | `객체명.메서드명()` |
| **객체 생성** | 불필요 | 필요 |
| **인스턴스 변수 접근** | 불가 ❌ | 가능 ✅ |
| **static 변수 접근** | 가능 ✅ | 가능 ✅ |
| **this 사용** | 불가 ❌ | 가능 ✅ |
| **사용 시기** | 유틸리티 함수, 공통 기능 | 객체의 상태를 다룰 때 |

### Static 메서드 예제

```java
public class StringUtils {
    // 문자열이 비어있는지 확인
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }
    
    // 문자열을 역순으로 변환
    public static String reverse(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str);
        return sb.reverse().toString();
    }
    
    // 두 문자열을 연결
    public static String concatenate(String str1, String str2) {
        return str1 + str2;
    }
    
    // 문자열 반복
    public static String repeat(String str, int count) {
        if (count <= 0) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(str);
        }
        return sb.toString();
    }
}

// 사용 예제
public class StringUtilsTest {
    public static void main(String[] args) {
        // 객체 생성 없이 바로 사용
        boolean empty = StringUtils.isEmpty("");  // true
        String reversed = StringUtils.reverse("Hello");  // "olleH"
        String joined = StringUtils.concatenate("Hello", "World");  // "HelloWorld"
        String repeated = StringUtils.repeat("Hi", 3);  // "HiHiHi"
        
        System.out.println("비어있음: " + empty);
        System.out.println("역순: " + reversed);
        System.out.println("연결: " + joined);
        System.out.println("반복: " + repeated);
    }
}
```

### Static 메서드의 제약사항

```java
public class Example {
    // 인스턴스 변수
    int instanceVar = 10;
    
    // static 변수
    static int staticVar = 20;
    
    // ❌ 에러: static 메서드에서 인스턴스 변수 접근 불가
    public static void method1() {
        // System.out.println(instanceVar);  // 컴파일 에러!
        System.out.println(staticVar);  // ✅ 가능
    }
    
    // ✅ 가능: 인스턴스 메서드에서 static 변수 접근 가능
    public void method2() {
        System.out.println(instanceVar);  // ✅ 가능
        System.out.println(staticVar);    // ✅ 가능
    }
    
    // ❌ 에러: static 메서드에서 this 사용 불가
    public static void method3() {
        // this.instanceVar = 100;  // 컴파일 에러!
    }
}
```

---

## 4. Static과 인스턴스 함께 사용하기

### 올바른 조합 예제

```java
public class Calculator {
    // static 변수: 계산 횟수 (모든 계산기 인스턴스가 공유)
    static int calculationCount = 0;
    
    // 인스턴스 변수: 계산기 이름
    String calculatorName;
    
    public Calculator(String name) {
        this.calculatorName = name;
    }
    
    // 인스턴스 메서드: 계산 (객체의 이름 사용)
    public int add(int a, int b) {
        calculationCount++;  // static 변수 접근 가능
        System.out.println(calculatorName + "에서 계산 수행");
        return a + b;
    }
    
    public int multiply(int a, int b) {
        calculationCount++;  // static 변수 접근 가능
        System.out.println(calculatorName + "에서 계산 수행");
        return a * b;
    }
    
    // static 메서드: 총 계산 횟수 반환
    public static int getCalculationCount() {
        return calculationCount;  // static 변수만 접근 가능
    }
    
    // 인스턴스 메서드: 정보 출력 (인스턴스 변수와 static 변수 모두 사용)
    public void printInfo() {
        System.out.println("계산기 이름: " + calculatorName);
        System.out.println("총 계산 횟수: " + calculationCount);
    }
}

// 사용 예제
public class CalculatorTest {
    public static void main(String[] args) {
        Calculator calc1 = new Calculator("계산기1");
        Calculator calc2 = new Calculator("계산기2");
        
        calc1.add(10, 20);
        calc2.multiply(5, 6);
        calc1.add(30, 40);
        
        // static 메서드로 총 계산 횟수 확인
        System.out.println("총 계산 횟수: " + Calculator.getCalculationCount());  // 3
        
        calc1.printInfo();
        calc2.printInfo();
    }
}
```

---

## 5. 실전 예제

### 예제 1: 상수 (Static Final)

```java
public class Constants {
    // static final: 상수 (변경 불가, 모든 객체가 공유)
    public static final double PI = 3.14159;
    public static final int MAX_STUDENTS = 100;
    public static final String COMPANY_NAME = "우리 회사";
    
    // 상수는 대문자와 언더스코어로 명명
    public static final int MAX_RETRY_COUNT = 3;
    
    public static void main(String[] args) {
        double area = PI * 5 * 5;
        System.out.println("원의 넓이: " + area);
        System.out.println("회사명: " + COMPANY_NAME);
        
        // PI = 3.14;  // ❌ 컴파일 에러: final 변수는 변경 불가
    }
}
```

### 예제 2: 유틸리티 클래스

```java
public class NumberUtils {
    // private 생성자: 객체 생성 방지 (유틸리티 클래스)
    private NumberUtils() {
        // 객체 생성을 막기 위해 private 생성자
    }
    
    // 절댓값
    public static int abs(int number) {
        return number < 0 ? -number : number;
    }
    
    // 최댓값
    public static int max(int a, int b) {
        return a > b ? a : b;
    }
    
    // 최솟값
    public static int min(int a, int b) {
        return a < b ? a : b;
    }
    
    // 팩토리얼
    public static long factorial(int n) {
        if (n <= 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }
    
    // 소수 판별
    public static boolean isPrime(int number) {
        if (number < 2) {
            return false;
        }
        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}

// 사용: 객체 생성 없이 바로 사용
public class NumberUtilsTest {
    public static void main(String[] args) {
        System.out.println("절댓값: " + NumberUtils.abs(-10));  // 10
        System.out.println("최댓값: " + NumberUtils.max(10, 20));  // 20
        System.out.println("팩토리얼: " + NumberUtils.factorial(5));  // 120
        System.out.println("소수인가? " + NumberUtils.isPrime(17));  // true
    }
}
```

### 예제 3: 싱글톤 패턴 (Static 활용)

```java
public class DatabaseConnection {
    // static 변수: 유일한 인스턴스
    private static DatabaseConnection instance;
    
    // private 생성자: 외부에서 객체 생성 방지
    private DatabaseConnection() {
        System.out.println("데이터베이스 연결 생성");
    }
    
    // static 메서드: 유일한 인스턴스 반환
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
    
    public void connect() {
        System.out.println("데이터베이스에 연결되었습니다.");
    }
}

// 사용: 항상 같은 인스턴스 반환
public class SingletonTest {
    public static void main(String[] args) {
        DatabaseConnection db1 = DatabaseConnection.getInstance();
        DatabaseConnection db2 = DatabaseConnection.getInstance();
        
        System.out.println(db1 == db2);  // true (같은 인스턴스)
        
        db1.connect();
        db2.connect();
    }
}
```

---

## 6. Main 메서드

### Main 메서드는 Static

`main` 메서드는 프로그램의 시작점이며, **반드시 static**이어야 합니다.

```java
public class HelloWorld {
    // main 메서드는 static
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

**왜 static이어야 하나?**
- 프로그램 시작 시점에는 아직 객체가 생성되지 않음
- JVM이 `클래스명.main()` 형태로 호출
- 따라서 객체 생성 없이 호출 가능한 static 메서드여야 함

---

## 7. 핵심 정리

### Static의 장점

1. **메모리 효율**: 하나의 메모리 공간만 사용 (모든 객체가 공유)
2. **편리성**: 객체 생성 없이 바로 사용 가능
3. **공통 데이터 관리**: 모든 객체가 공유하는 데이터에 적합
4. **유틸리티 함수**: 인스턴스 변수가 필요 없는 함수에 적합

### Static의 단점

1. **메모리 해제 불가**: 프로그램 종료 시까지 메모리에 유지
2. **인스턴스 변수 접근 불가**: static 메서드에서 인스턴스 변수 사용 불가
3. **테스트 어려움**: static 메서드는 모킹이 어려움
4. **전역 상태**: 모든 곳에서 접근 가능하여 의도치 않은 수정 위험

### 언제 Static을 사용해야 하나?

#### ✅ Static을 사용해야 할 때

1. **상수 정의**
   ```java
   public static final double PI = 3.14159;
   ```

2. **유틸리티 함수** (인스턴스 변수 불필요)
   ```java
   public static int max(int a, int b) { ... }
   ```

3. **공통 데이터** (모든 객체가 공유)
   ```java
   static int totalCount = 0;
   ```

4. **팩토리 메서드**
   ```java
   public static Student createStudent(String name) { ... }
   ```

#### ❌ Static을 사용하지 말아야 할 때

1. **객체의 상태가 필요한 경우**
   ```java
   // ❌ 잘못된 예
   public static void printName() {
       System.out.println(this.name);  // 에러!
   }
   
   // ✅ 올바른 예
   public void printName() {
       System.out.println(this.name);  // 정상
   }
   ```

2. **상태를 저장해야 하는 경우**
   ```java
   // ❌ 잘못된 예: 모든 Student가 같은 이름을 가짐
   static String name;
   
   // ✅ 올바른 예: 각 Student가 다른 이름을 가짐
   String name;
   ```

---

## 8. 비교표

### Static vs Instance 요약

| 구분 | Static | Instance |
|------|--------|----------|
| **변수/메서드** | static 변수, static 메서드 | 인스턴스 변수, 인스턴스 메서드 |
| **메모리 위치** | Method Area | Heap |
| **생성 시점** | 클래스 로드 시 | 객체 생성 시 |
| **개수** | 1개 (클래스당) | 여러 개 (객체마다) |
| **공유** | 모든 객체가 공유 | 각 객체마다 독립 |
| **접근 방법** | `클래스명.변수/메서드` | `객체명.변수/메서드` |
| **객체 생성** | 불필요 | 필요 |
| **인스턴스 변수 접근** | 불가 | 가능 |
| **static 변수 접근** | 가능 | 가능 |
| **this 사용** | 불가 | 가능 |

---

## 9. 학습 체크리스트

- [ ] static 변수의 개념을 이해했다
- [ ] static 메서드의 개념을 이해했다
- [ ] static과 인스턴스의 차이를 설명할 수 있다
- [ ] static 변수와 인스턴스 변수의 메모리 차이를 이해했다
- [ ] static 메서드를 올바르게 호출할 수 있다
- [ ] static 메서드에서 인스턴스 변수 접근이 불가능한 이유를 이해했다
- [ ] static을 언제 사용해야 하는지 판단할 수 있다
- [ ] 유틸리티 클래스를 만들 수 있다
- [ ] main 메서드가 static인 이유를 이해했다

---

## 📚 관련 자료

- [Java 6장: 메서드](./6장_메서드.md)
- [Java 7장: 객체지향 프로그래밍 기초](./7장_객체지향_프로그래밍_기초.md)
- [Java 12장: 메모리 구조 JVM 이해](./12장_메모리_구조_JVM_이해.md)
- [예제 코드: StaticMethods.java](./example6/StaticMethods.java)




