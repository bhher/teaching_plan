# Java Optional 완전 정복

## 목차

1. [Optional이란?](#optional이란)
2. [Optional을 사용하는 이유](#optional을-사용하는-이유)
3. [Optional 생성 방법](#optional-생성-방법)
4. [Optional 주요 메서드](#optional-주요-메서드)
5. [Optional 실전 예제](#optional-실전-예제)
6. [주의사항 및 베스트 프랙티스](#주의사항-및-베스트-프랙티스)

---

## Optional이란?

**Optional**은 Java 8에서 도입된 클래스로, **값이 있을 수도 있고 없을 수도 있는** 객체를 감싸는 래퍼 클래스입니다.

### 기본 개념

```java
Optional<T>  // T 타입의 값을 감싸는 Optional
```

- **값이 있는 경우**: `Optional.of(value)`
- **값이 없는 경우**: `Optional.empty()`
- **null일 수 있는 경우**: `Optional.ofNullable(value)`

### 왜 Optional이 필요한가?

**기존 방식의 문제점:**

```java
// ❌ 나쁜 예: NullPointerException 발생 가능
public String getName() {
    return name;  // name이 null이면?
}

String name = getName();
int length = name.length();  // NullPointerException!
```

**Optional 사용:**

```java
// ✅ 좋은 예: 안전하게 처리
public Optional<String> getName() {
    return Optional.ofNullable(name);
}

Optional<String> nameOpt = getName();
if (nameOpt.isPresent()) {
    String name = nameOpt.get();
    int length = name.length();
}
```

---

## Optional을 사용하는 이유

### 1. NullPointerException 방지

```java
// 기존 방식
String name = findNameById(1);
if (name != null) {  // null 체크 필수
    System.out.println(name.length());
}

// Optional 방식
Optional<String> nameOpt = findNameById(1);
nameOpt.ifPresent(name -> System.out.println(name.length()));
```

### 2. 명시적인 null 처리

```java
// Optional을 사용하면 "값이 없을 수 있다"는 것이 명확함
Optional<String> result = findUser(1);
// 메서드 시그니처만 봐도 null 가능성을 알 수 있음
```

### 3. 함수형 프로그래밍 스타일

```java
// 체이닝을 통한 깔끔한 코드
Optional.of("Hello")
    .map(String::toUpperCase)
    .filter(s -> s.length() > 3)
    .ifPresent(System.out::println);
```

---

## Optional 생성 방법

### 1. Optional.of() - null이 아닌 값

```java
// 값이 반드시 있어야 함 (null이면 예외 발생)
Optional<String> opt = Optional.of("Hello");
Optional<Integer> num = Optional.of(100);

// null을 넣으면 NullPointerException 발생
Optional<String> error = Optional.of(null);  // ❌ 예외 발생!
```

**사용 시기:** 값이 확실히 존재할 때

### 2. Optional.ofNullable() - null일 수 있는 값

```java
// null일 수도 있는 값
String name = getName();  // null 가능
Optional<String> opt = Optional.ofNullable(name);

// null이어도 안전하게 처리됨
Optional<String> empty = Optional.ofNullable(null);  // ✅ OK
```

**사용 시기:** 값이 null일 수 있을 때 (가장 많이 사용)

### 3. Optional.empty() - 빈 Optional

```java
// 값이 없는 Optional 생성
Optional<String> empty = Optional.empty();

// 조건에 따라 빈 Optional 반환
public Optional<String> findName(int id) {
    if (id < 0) {
        return Optional.empty();  // 값이 없음을 명시
    }
    return Optional.of("Name");
}
```

**사용 시기:** 값이 없을 때 명시적으로 반환

---

## Optional 주요 메서드

### 1. 값 확인 메서드

#### isPresent() - 값이 있는지 확인

```java
Optional<String> opt = Optional.of("Hello");

if (opt.isPresent()) {
    System.out.println("값이 있습니다: " + opt.get());
} else {
    System.out.println("값이 없습니다");
}
```

#### isEmpty() - 값이 없는지 확인 (Java 11+)

```java
Optional<String> opt = Optional.empty();

if (opt.isEmpty()) {
    System.out.println("값이 없습니다");
}
```

### 2. 값 가져오기 메서드

#### get() - 값 가져오기 (주의!)

```java
Optional<String> opt = Optional.of("Hello");
String value = opt.get();  // "Hello"

Optional<String> empty = Optional.empty();
String value2 = empty.get();  // ❌ NoSuchElementException 발생!
```

**주의:** 값이 없으면 예외 발생. `isPresent()` 확인 후 사용하거나 다른 메서드 사용 권장.

#### orElse() - 값이 없으면 기본값 반환

```java
Optional<String> opt = Optional.of("Hello");
String result = opt.orElse("기본값");  // "Hello"

Optional<String> empty = Optional.empty();
String result2 = empty.orElse("기본값");  // "기본값"
```

**특징:** 값이 있어도 기본값을 계산함 (성능 고려 필요)

#### orElseGet() - 값이 없으면 Supplier로 기본값 생성

```java
Optional<String> opt = Optional.of("Hello");
String result = opt.orElseGet(() -> "기본값");  // "Hello"

Optional<String> empty = Optional.empty();
String result2 = empty.orElseGet(() -> {
    System.out.println("기본값 생성");
    return "기본값";
});  // "기본값" (값이 없을 때만 실행)
```

**특징:** 값이 없을 때만 Supplier 실행 (성능 효율적)

#### orElseThrow() - 값이 없으면 예외 발생

```java
Optional<String> opt = Optional.of("Hello");
String result = opt.orElseThrow();  // "Hello"

Optional<String> empty = Optional.empty();
String result2 = empty.orElseThrow();  // ❌ NoSuchElementException

// 커스텀 예외
String result3 = empty.orElseThrow(() -> new IllegalArgumentException("값이 없습니다"));
```

### 3. 값 변환 메서드

#### map() - 값 변환

```java
Optional<String> opt = Optional.of("hello");
Optional<String> upper = opt.map(String::toUpperCase);  // "HELLO"

Optional<Integer> num = Optional.of(5);
Optional<Integer> squared = num.map(n -> n * n);  // 25

// null이면 빈 Optional 반환
Optional<String> empty = Optional.empty();
Optional<String> result = empty.map(String::toUpperCase);  // Optional.empty()
```

**특징:** 값이 없으면 빈 Optional 반환

#### flatMap() - 중첩 Optional 펼치기

```java
// map()의 문제: Optional<Optional<String>> 생성
Optional<Optional<String>> nested = Optional.of("hello")
    .map(s -> Optional.of(s.toUpperCase()));  // ❌ 중첩됨

// flatMap() 사용: Optional<String> 반환
Optional<String> flat = Optional.of("hello")
    .flatMap(s -> Optional.of(s.toUpperCase()));  // ✅ "HELLO"
```

**사용 예시:**

```java
public Optional<String> getName() {
    return Optional.of("John");
}

Optional<String> name = Optional.of(1)
    .flatMap(id -> getName());  // Optional<String> 반환
```

### 4. 필터링 메서드

#### filter() - 조건에 맞는 값만 유지

```java
Optional<Integer> num = Optional.of(10);
Optional<Integer> filtered = num.filter(n -> n > 5);  // Optional.of(10)

Optional<Integer> num2 = Optional.of(3);
Optional<Integer> filtered2 = num2.filter(n -> n > 5);  // Optional.empty()

// 실전 예시
Optional<String> name = Optional.of("John");
Optional<String> longName = name.filter(n -> n.length() > 3);  // "John"
Optional<String> shortName = name.filter(n -> n.length() > 10);  // empty
```

### 5. 실행 메서드

#### ifPresent() - 값이 있으면 실행

```java
Optional<String> opt = Optional.of("Hello");
opt.ifPresent(value -> System.out.println("값: " + value));  // "값: Hello"

Optional<String> empty = Optional.empty();
empty.ifPresent(value -> System.out.println("값: " + value));  // 실행 안 됨
```

#### ifPresentOrElse() - 값이 있으면 실행, 없으면 다른 실행 (Java 9+)

```java
Optional<String> opt = Optional.of("Hello");
opt.ifPresentOrElse(
    value -> System.out.println("값: " + value),  // 실행됨
    () -> System.out.println("값이 없습니다")
);

Optional<String> empty = Optional.empty();
empty.ifPresentOrElse(
    value -> System.out.println("값: " + value),
    () -> System.out.println("값이 없습니다")  // 실행됨
);
```

---

## Optional 실전 예제

### 예제 1: 사용자 조회 시스템

```java
import java.util.*;

class User {
    private String name;
    private int age;
    
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public String getName() { return name; }
    public int getAge() { return age; }
}

class UserRepository {
    private Map<Integer, User> users = new HashMap<>();
    
    public UserRepository() {
        users.put(1, new User("김철수", 25));
        users.put(2, new User("이영희", 30));
    }
    
    // ❌ 기존 방식: null 반환 가능
    public User findById(int id) {
        return users.get(id);  // null 가능
    }
    
    // ✅ Optional 방식: 명시적으로 null 가능성 표현
    public Optional<User> findByIdOptional(int id) {
        User user = users.get(id);
        return Optional.ofNullable(user);
    }
}

public class OptionalExample1 {
    public static void main(String[] args) {
        UserRepository repo = new UserRepository();
        
        // 기존 방식 (null 체크 필요)
        User user1 = repo.findById(1);
        if (user1 != null) {
            System.out.println("이름: " + user1.getName());
        }
        
        // Optional 방식 (깔끔한 처리)
        repo.findByIdOptional(1)
            .ifPresent(user -> System.out.println("이름: " + user.getName()));
        
        // 값이 없을 때 기본값
        String name = repo.findByIdOptional(999)
            .map(User::getName)
            .orElse("사용자를 찾을 수 없습니다");
        System.out.println(name);
    }
}
```

**출력:**
```
이름: 김철수
이름: 김철수
사용자를 찾을 수 없습니다
```

---

### 예제 2: 체이닝을 통한 데이터 처리

```java
import java.util.*;

public class OptionalExample2 {
    public static void main(String[] args) {
        // 중첩된 null 처리
        String result = processData("  HELLO  ");
        System.out.println(result);  // "HELLO"
        
        String result2 = processData(null);
        System.out.println(result2);  // "기본값"
    }
    
    public static String processData(String input) {
        return Optional.ofNullable(input)
            .map(String::trim)           // 공백 제거
            .map(String::toLowerCase)    // 소문자 변환
            .map(String::toUpperCase)    // 대문자 변환
            .filter(s -> s.length() > 0) // 길이 체크
            .orElse("기본값");           // 없으면 기본값
    }
}
```

---

### 예제 3: Stream과 함께 사용

```java
import java.util.*;
import java.util.stream.Collectors;

public class OptionalExample3 {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("김철수", null, "이영희", "", "박민수", null);
        
        // null 제거하고 필터링
        List<String> validNames = names.stream()
            .map(Optional::ofNullable)      // Optional로 감싸기
            .filter(Optional::isPresent)   // null 제거
            .map(Optional::get)            // 값 추출
            .filter(s -> !s.isEmpty())     // 빈 문자열 제거
            .collect(Collectors.toList());
        
        System.out.println(validNames);  // [김철수, 이영희, 박민수]
        
        // 더 간단한 방법
        List<String> validNames2 = names.stream()
            .filter(Objects::nonNull)       // null 제거
            .filter(s -> !s.isEmpty())      // 빈 문자열 제거
            .collect(Collectors.toList());
        
        System.out.println(validNames2);  // [김철수, 이영희, 박민수]
    }
}
```

---

### 예제 4: 중첩 Optional 처리

```java
import java.util.*;

class Address {
    private String city;
    
    public Address(String city) {
        this.city = city;
    }
    
    public String getCity() { return city; }
}

class Person {
    private Optional<Address> address;
    
    public Person(Address address) {
        this.address = Optional.ofNullable(address);
    }
    
    public Optional<Address> getAddress() { return address; }
}

public class OptionalExample4 {
    public static void main(String[] args) {
        Person person1 = new Person(new Address("서울"));
        Person person2 = new Person(null);
        
        // 중첩 Optional 처리
        String city1 = person1.getAddress()
            .flatMap(Address::getCity)  // Optional<String> 반환
            .orElse("주소 없음");
        System.out.println(city1);  // "서울"
        
        String city2 = person2.getAddress()
            .flatMap(Address::getCity)
            .orElse("주소 없음");
        System.out.println(city2);  // "주소 없음"
    }
}
```

**주의:** `getCity()`가 `Optional<String>`을 반환해야 `flatMap` 사용 가능

---

### 예제 5: 실전 활용 - 점수 계산 시스템

```java
import java.util.*;

class Student {
    private String name;
    private Optional<Integer> score;
    
    public Student(String name, Integer score) {
        this.name = name;
        this.score = Optional.ofNullable(score);
    }
    
    public String getName() { return name; }
    public Optional<Integer> getScore() { return score; }
}

public class OptionalExample5 {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
            new Student("김철수", 85),
            new Student("이영희", null),  // 점수 없음
            new Student("박민수", 92),
            new Student("최지영", null)
        );
        
        // 평균 점수 계산 (점수가 없는 학생 제외)
        double average = students.stream()
            .map(Student::getScore)           // Optional<Integer>
            .filter(Optional::isPresent)      // 값이 있는 것만
            .mapToInt(opt -> opt.get())       // int로 변환
            .average()
            .orElse(0.0);
        
        System.out.println("평균 점수: " + average);  // 88.5
        
        // 점수가 있는 학생만 출력
        students.stream()
            .filter(s -> s.getScore().isPresent())
            .forEach(s -> System.out.println(
                s.getName() + ": " + s.getScore().get()
            ));
        
        // 점수가 없는 학생 찾기
        students.stream()
            .filter(s -> s.getScore().isEmpty())
            .forEach(s -> System.out.println(s.getName() + ": 점수 없음"));
    }
}
```

**출력:**
```
평균 점수: 88.5
김철수: 85
박민수: 92
이영희: 점수 없음
최지영: 점수 없음
```

---

### 예제 6: 조건부 처리

```java
import java.util.*;

public class OptionalExample6 {
    public static void main(String[] args) {
        Optional<String> name = Optional.of("John");
        
        // 길이가 3보다 크면 처리
        name.filter(n -> n.length() > 3)
            .ifPresent(n -> System.out.println("긴 이름: " + n));
        
        // 짧은 이름 처리
        Optional<String> shortName = Optional.of("Jo");
        shortName.filter(n -> n.length() > 3)
            .ifPresentOrElse(
                n -> System.out.println("긴 이름: " + n),
                () -> System.out.println("이름이 너무 짧습니다")
            );
    }
}
```

---

## 주의사항 및 베스트 프랙티스

### ❌ 나쁜 사용법

#### 1. Optional을 필드로 사용하지 말 것

```java
// ❌ 나쁜 예
class User {
    private Optional<String> name;  // 피하세요!
}

// ✅ 좋은 예
class User {
    private String name;  // null 가능한 필드는 그냥 null 허용
    
    public Optional<String> getName() {
        return Optional.ofNullable(name);  // 메서드에서 Optional 반환
    }
}
```

#### 2. Optional을 매개변수로 사용하지 말 것

```java
// ❌ 나쁜 예
public void process(Optional<String> name) {
    // ...
}

// ✅ 좋은 예
public void process(String name) {
    Optional.ofNullable(name)
        .ifPresent(n -> {
            // 처리
        });
}
```

#### 3. get()을 바로 사용하지 말 것

```java
// ❌ 나쁜 예
Optional<String> opt = findName();
String name = opt.get();  // 값이 없으면 예외 발생!

// ✅ 좋은 예
String name = findName()
    .orElse("기본값");
    
// 또는
findName().ifPresent(name -> {
    // 처리
});
```

#### 4. null을 Optional.of()에 넣지 말 것

```java
// ❌ 나쁜 예
Optional<String> opt = Optional.of(null);  // NullPointerException!

// ✅ 좋은 예
Optional<String> opt = Optional.ofNullable(null);  // Optional.empty()
```

### ✅ 좋은 사용법

#### 1. 반환 타입으로 Optional 사용

```java
// ✅ 좋은 예: 반환 타입으로 Optional 사용
public Optional<User> findUser(int id) {
    User user = users.get(id);
    return Optional.ofNullable(user);
}
```

#### 2. 체이닝 활용

```java
// ✅ 좋은 예: 체이닝으로 깔끔하게 처리
findUser(1)
    .map(User::getName)
    .map(String::toUpperCase)
    .ifPresent(System.out::println);
```

#### 3. 기본값 제공

```java
// ✅ 좋은 예: 기본값 제공
String name = findName()
    .orElse("이름 없음");
    
// 또는 Supplier 사용 (성능 효율적)
String name = findName()
    .orElseGet(() -> expensiveOperation());
```

#### 4. 예외 처리

```java
// ✅ 좋은 예: 명시적인 예외 처리
String name = findName()
    .orElseThrow(() -> new IllegalArgumentException("이름을 찾을 수 없습니다"));
```

---

## Optional 메서드 정리표

| 메서드 | 설명 | 반환 타입 |
|--------|------|----------|
| `of(value)` | null이 아닌 값으로 Optional 생성 | `Optional<T>` |
| `ofNullable(value)` | null일 수 있는 값으로 Optional 생성 | `Optional<T>` |
| `empty()` | 빈 Optional 생성 | `Optional<T>` |
| `isPresent()` | 값이 있는지 확인 | `boolean` |
| `isEmpty()` | 값이 없는지 확인 (Java 11+) | `boolean` |
| `get()` | 값 가져오기 (주의!) | `T` |
| `orElse(default)` | 값이 없으면 기본값 반환 | `T` |
| `orElseGet(supplier)` | 값이 없으면 Supplier 실행 | `T` |
| `orElseThrow()` | 값이 없으면 예외 발생 | `T` |
| `map(function)` | 값 변환 | `Optional<U>` |
| `flatMap(function)` | 중첩 Optional 펼치기 | `Optional<U>` |
| `filter(predicate)` | 조건에 맞는 값만 유지 | `Optional<T>` |
| `ifPresent(consumer)` | 값이 있으면 실행 | `void` |
| `ifPresentOrElse(consumer, runnable)` | 값이 있으면 실행, 없으면 다른 실행 | `void` |

---

## 실습 문제

### 문제 1: 사용자 이름 찾기

사용자 ID로 이름을 찾되, 없으면 "알 수 없음"을 반환하시오.

**정답:**
```java
public String findUserName(int id) {
    return findUser(id)
        .map(User::getName)
        .orElse("알 수 없음");
}
```

---

### 문제 2: 점수가 80점 이상인 학생 찾기

학생 목록에서 점수가 80점 이상인 학생의 이름을 출력하시오.

**정답:**
```java
students.stream()
    .filter(s -> s.getScore().isPresent())
    .filter(s -> s.getScore().get() >= 80)
    .map(Student::getName)
    .forEach(System.out::println);
```

---

### 문제 3: 안전한 문자열 길이 계산

문자열이 null일 수 있을 때 안전하게 길이를 계산하시오.

**정답:**
```java
public int safeLength(String str) {
    return Optional.ofNullable(str)
        .map(String::length)
        .orElse(0);
}
```

---

## 요약

### Optional 사용 가이드

1. **반환 타입으로 사용**: 메서드가 null을 반환할 수 있을 때
2. **체이닝 활용**: `map`, `filter`, `flatMap`으로 깔끔하게 처리
3. **기본값 제공**: `orElse`, `orElseGet`으로 안전하게 처리
4. **명시적 처리**: `ifPresent`, `ifPresentOrElse`로 조건부 실행

### 핵심 포인트

- ✅ **반환 타입**으로 Optional 사용
- ✅ **체이닝**으로 깔끔한 코드 작성
- ✅ **기본값** 제공으로 안전성 확보
- ❌ **필드나 매개변수**로 Optional 사용 금지
- ❌ **get()** 직접 사용 금지

---

**Optional을 마스터하면 null 처리가 안전하고 깔끔해집니다!**
