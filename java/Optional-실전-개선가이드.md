# Optional 실전 개선 가이드

## 목차

1. [문제 상황: null 반환의 위험성](#문제-상황-null-반환의-위험성)
2. [기존 방식의 문제점](#기존-방식의-문제점)
3. [Optional로 개선하기](#optional로-개선하기)
4. [실전 활용 패턴](#실전-활용-패턴)
5. [핵심 차이 요약](#핵심-차이-요약)

---

## 문제 상황: null 반환의 위험성

### 기존 코드 (문제가 있는 코드)

```java
class MemberService {
    public String findNameById(int id) {
        if(id == 1){
            return "홍길동";
        }
        return null;  // ❌ null 반환
    }
}

public class Main {
    public static void main(String[] args) {
        MemberService service = new MemberService();
        
        String name = service.findNameById(2);  // null 반환
        
        // ❌ NullPointerException 발생!
        System.out.println(name.length());  // 오류!
        
        System.out.println("프로그램 정상 종료");
    }
}
```

**실행 결과:**
```
Exception in thread "main" java.lang.NullPointerException
    at Main.main(Main.java:10)
```

**문제점:**
- `name`이 `null`인데 `.length()` 호출 시도
- 프로그램이 비정상 종료됨
- 예외 처리 코드가 없어서 오류 발생

---

## 기존 방식의 문제점

### 방식 1: null 반환 (가장 위험)

```java
public String findNameById(int id) {
    if(id == 1){
        return "홍길동";
    }
    return null;  // ❌ 위험!
}

// 사용 시
String name = service.findNameById(2);
int length = name.length();  // ❌ NullPointerException!
```

**문제점:**
- null 체크를 깜빡하면 예외 발생
- 컴파일 타임에 오류를 잡을 수 없음
- 코드를 읽는 사람이 null 가능성을 알기 어려움

### 방식 2: if문으로 null 체크 (안전하지만 번거로움)

```java
public String findNameById(int id) {
    if(id == 1){
        return "홍길동";
    }
    return null;
}

// 사용 시
String name = service.findNameById(2);
if(name != null) {  // ✅ null 체크
    System.out.println(name.length());
} else {
    System.out.println("이름이 없습니다");
}
```

**문제점:**
- 매번 null 체크 코드를 작성해야 함
- 코드가 길어지고 가독성이 떨어짐
- null 체크를 깜빡할 가능성 여전히 존재

---

## Optional로 개선하기

### 1️⃣ 서비스 메서드 수정

```java
import java.util.Optional;

class MemberService {
    
    // ✅ Optional을 반환 타입으로 사용
    public Optional<String> findNameById(int id) {
        if(id == 1){
            return Optional.of("홍길동");  // 값이 있을 때
        }
        return Optional.empty();  // 값이 없을 때
    }
}
```

**개선점:**
- `Optional<String>` 반환으로 "값이 없을 수 있다"는 것을 명시
- `null` 대신 `Optional.empty()` 반환
- 메서드 시그니처만 봐도 null 가능성을 알 수 있음

### 2️⃣ main에서 처리

```java
public class Main {
    public static void main(String[] args) {
        
        MemberService service = new MemberService();
        
        Optional<String> name = service.findNameById(2);
        
        // ✅ 값이 있을 때만 실행 (안전함)
        name.ifPresent(n -> System.out.println(n.length()));
        
        System.out.println("프로그램 정상 종료");
    }
}
```

**실행 결과:**
```
프로그램 정상 종료
```

**개선점:**
- ✅ 오류 없음!
- 값이 없으면 아무것도 실행하지 않음
- 프로그램이 정상 종료됨

---

## 실전 활용 패턴

### 패턴 1: 기본값 제공 (`orElse`)

**상황:** 값이 없을 때 기본값을 사용하고 싶을 때

```java
public class Main {
    public static void main(String[] args) {
        MemberService service = new MemberService();
        
        // ✅ 기본값 제공
        String result = service.findNameById(2)
                .orElse("이름없음");
        
        System.out.println(result.length());  // 안전하게 실행 가능
    }
}
```

**출력:**
```
3
```

**설명:**
- 값이 없으면 "이름없음" 반환
- 항상 문자열이 반환되므로 안전하게 `.length()` 호출 가능

### 패턴 2: 예외 던지기 (`orElseThrow`)

**상황:** 값이 없을 때 예외를 발생시키고 싶을 때

```java
public class Main {
    public static void main(String[] args) {
        MemberService service = new MemberService();
        
        try {
            // ✅ 커스텀 예외 던지기
            String name = service.findNameById(2)
                    .orElseThrow(() -> new RuntimeException("회원이 없습니다"));
            
            System.out.println(name.length());
        } catch (RuntimeException e) {
            System.out.println("오류: " + e.getMessage());
        }
    }
}
```

**출력:**
```
오류: 회원이 없습니다
```

**설명:**
- 값이 없으면 명시적인 예외 발생
- 예외 메시지로 상황을 명확히 전달
- try-catch로 예외 처리 가능

### 패턴 3: 값 변환 (`map`)

**상황:** 값을 변환하여 사용하고 싶을 때

```java
public class Main {
    public static void main(String[] args) {
        MemberService service = new MemberService();
        
        // ✅ map으로 값 변환
        Optional<Integer> length = service.findNameById(1)
                .map(String::length);  // 문자열 길이로 변환
        
        length.ifPresent(System.out::println);
    }
}
```

**출력:**
```
3
```

**설명:**
- `map()`으로 문자열을 길이(정수)로 변환
- 값이 없으면 빈 Optional 반환
- 체이닝으로 깔끔하게 처리

### 패턴 4: 조건부 실행 (`ifPresentOrElse`)

**상황:** 값이 있을 때와 없을 때 각각 다른 처리를 하고 싶을 때

```java
public class Main {
    public static void main(String[] args) {
        MemberService service = new MemberService();
        
        // ✅ 값이 있을 때와 없을 때 각각 처리
        service.findNameById(1)
                .ifPresentOrElse(
                    name -> System.out.println("이름: " + name),
                    () -> System.out.println("회원을 찾을 수 없습니다")
                );
        
        service.findNameById(2)
                .ifPresentOrElse(
                    name -> System.out.println("이름: " + name),
                    () -> System.out.println("회원을 찾을 수 없습니다")
                );
    }
}
```

**출력:**
```
이름: 홍길동
회원을 찾을 수 없습니다
```

### 패턴 5: 필터링 (`filter`)

**상황:** 특정 조건을 만족하는 값만 처리하고 싶을 때

```java
public class Main {
    public static void main(String[] args) {
        MemberService service = new MemberService();
        
        // ✅ 길이가 3 이상인 이름만 처리
        service.findNameById(1)
                .filter(name -> name.length() >= 3)
                .ifPresent(name -> System.out.println("긴 이름: " + name));
        
        // 짧은 이름은 처리 안 됨
        service.findNameById(1)
                .filter(name -> name.length() > 10)
                .ifPresent(name -> System.out.println("매우 긴 이름: " + name));
        // 출력 없음
    }
}
```

### 패턴 6: 중첩 Optional 처리 (`flatMap`)

**상황:** Optional 안에 Optional이 있을 때

```java
class MemberService {
    public Optional<String> findNameById(int id) {
        if(id == 1){
            return Optional.of("홍길동");
        }
        return Optional.empty();
    }
    
    // 이름으로 길이 찾기
    public Optional<Integer> getNameLength(String name) {
        return Optional.ofNullable(name)
                .map(String::length);
    }
}

public class Main {
    public static void main(String[] args) {
        MemberService service = new MemberService();
        
        // ❌ map 사용 시 중첩됨
        Optional<Optional<Integer>> nested = service.findNameById(1)
                .map(name -> service.getNameLength(name));
        // Optional<Optional<Integer>> - 중첩!
        
        // ✅ flatMap 사용 시 펼쳐짐
        Optional<Integer> flat = service.findNameById(1)
                .flatMap(name -> service.getNameLength(name));
        // Optional<Integer> - 깔끔!
        
        flat.ifPresent(length -> System.out.println("길이: " + length));
    }
}
```

---

## 비교: 기존 방식 vs Optional

### 예제 1: 기본 사용

**기존 방식:**
```java
String name = service.findNameById(2);
if(name != null) {
    System.out.println(name.length());
}
```

**Optional 방식:**
```java
service.findNameById(2)
    .ifPresent(n -> System.out.println(n.length()));
```

**차이점:**
- Optional: 한 줄로 처리 가능
- Optional: null 체크를 깜빡할 수 없음

### 예제 2: 기본값 제공

**기존 방식:**
```java
String name = service.findNameById(2);
String result = (name != null) ? name : "이름없음";
System.out.println(result.length());
```

**Optional 방식:**
```java
String result = service.findNameById(2)
    .orElse("이름없음");
System.out.println(result.length());
```

**차이점:**
- Optional: 더 명확하고 읽기 쉬움
- Optional: 삼항 연산자 불필요

### 예제 3: 값 변환

**기존 방식:**
```java
String name = service.findNameById(1);
Integer length = null;
if(name != null) {
    length = name.length();
}
if(length != null) {
    System.out.println(length);
}
```

**Optional 방식:**
```java
service.findNameById(1)
    .map(String::length)
    .ifPresent(System.out::println);
```

**차이점:**
- Optional: 체이닝으로 깔끔함
- Optional: 중간 변수 불필요

---

## 핵심 차이 요약

| 방식 | 특징 | 안전성 | 코드 길이 | 가독성 |
|------|------|--------|----------|--------|
| **null 반환** | 간단하지만 위험 | ❌ 낮음 | 짧음 | 나쁨 |
| **if(name!=null)** | 안전하지만 번거로움 | ✅ 높음 | 길음 | 보통 |
| **Optional** | 안전 + 깔끔 | ✅ 높음 | 짧음 | 좋음 |

### 한 줄 요약

> **Optional을 쓰면 null인지 아닌지 고민하지 않고, 안전하게 값이 있을 때만 처리 가능**

---

## 실전 예제: 완전한 코드

```java
import java.util.Optional;

// 서비스 클래스
class MemberService {
    
    public Optional<String> findNameById(int id) {
        if(id == 1){
            return Optional.of("홍길동");
        }
        return Optional.empty();
    }
}

// 메인 클래스
public class Main {
    public static void main(String[] args) {
        MemberService service = new MemberService();
        
        System.out.println("=== Optional 실전 예제 ===\n");
        
        // 1. 값이 있을 때만 실행
        System.out.println("1. ifPresent 사용:");
        service.findNameById(1)
            .ifPresent(n -> System.out.println("  이름: " + n));
        
        service.findNameById(2)
            .ifPresent(n -> System.out.println("  이름: " + n));
        // 출력 없음
        
        // 2. 기본값 제공
        System.out.println("\n2. orElse 사용:");
        String result1 = service.findNameById(1)
            .orElse("이름없음");
        System.out.println("  결과: " + result1);
        
        String result2 = service.findNameById(2)
            .orElse("이름없음");
        System.out.println("  결과: " + result2);
        
        // 3. 값 변환
        System.out.println("\n3. map 사용:");
        service.findNameById(1)
            .map(String::length)
            .ifPresent(length -> System.out.println("  길이: " + length));
        
        // 4. 조건부 실행
        System.out.println("\n4. ifPresentOrElse 사용:");
        service.findNameById(1)
            .ifPresentOrElse(
                name -> System.out.println("  찾음: " + name),
                () -> System.out.println("  없음")
            );
        
        service.findNameById(2)
            .ifPresentOrElse(
                name -> System.out.println("  찾음: " + name),
                () -> System.out.println("  없음")
            );
        
        // 5. 필터링
        System.out.println("\n5. filter 사용:");
        service.findNameById(1)
            .filter(name -> name.length() >= 3)
            .ifPresent(name -> System.out.println("  긴 이름: " + name));
        
        System.out.println("\n프로그램 정상 종료");
    }
}
```

**실행 결과:**
```
=== Optional 실전 예제 ===

1. ifPresent 사용:
  이름: 홍길동

2. orElse 사용:
  결과: 홍길동
  결과: 이름없음

3. map 사용:
  길이: 3

4. ifPresentOrElse 사용:
  찾음: 홍길동
  없음

5. filter 사용:
  긴 이름: 홍길동

프로그램 정상 종료
```

---

## 체크리스트

### Optional 사용 전 확인사항

- [ ] 메서드가 null을 반환할 수 있는가?
- [ ] 반환 타입을 `Optional<T>`로 변경할 수 있는가?
- [ ] 호출하는 쪽에서 Optional을 적절히 처리하는가?

### Optional 사용 시 주의사항

- [ ] `get()`을 바로 사용하지 않았는가?
- [ ] `orElse()` 또는 `orElseGet()`으로 기본값을 제공했는가?
- [ ] `ifPresent()`로 값이 있을 때만 처리했는가?
- [ ] Optional을 필드나 매개변수로 사용하지 않았는가?

---

## 요약

### Optional의 장점

1. **안전성**: NullPointerException 방지
2. **명확성**: 메서드 시그니처만 봐도 null 가능성 파악
3. **간결성**: null 체크 코드 불필요
4. **함수형**: 체이닝으로 깔끔한 코드 작성

### 핵심 메서드

- `Optional.of(value)` - 값이 있을 때
- `Optional.empty()` - 값이 없을 때
- `ifPresent(consumer)` - 값이 있을 때 실행
- `orElse(default)` - 기본값 제공
- `orElseThrow()` - 예외 던지기
- `map(function)` - 값 변환
- `filter(predicate)` - 조건 필터링

---

**Optional을 사용하면 null 처리가 안전하고 깔끔해집니다!**
