# MemberServiceExample — Optional 실전 설명

소스: [MemberServiceExample.java](./MemberServiceExample.java)

회원 이름을 ID로 찾을 때 **`null`을 직접 반환하는 방식**과 **`Optional`로 감싸는 방식**을 비교하는 예제입니다.

---

## 실행

```bash
cd java/Optional-실전-예제코드
javac MemberServiceExample.java
java optional.MemberServiceExample
```

패키지가 `optional`이므로, 상위 폴더에서 컴파일하는 방식이 더 안전할 수 있습니다.

```bash
cd java
javac Optional-실전-예제코드/MemberServiceExample.java -d .
java optional.MemberServiceExample
```

---

## 왜 Optional인가?

| | 기존 (`null`) | Optional |
|--|---------------|----------|
| 반환 타입 | `String` (없을 수도 있음이 안 보임) | `Optional<String>` (없을 수 있음이 **타입에 드러남**) |
| 위험 | `name.length()` → **NPE** | 값이 있을 때만 처리 |
| 호출부 | `if (x != null)` 매번 필요 | `orElse` / `ifPresent` 등 API로 처리 |

```text
findNameById(2)
    → 회원 없음
    → null 을 그대로 쓰면  → NullPointerException 위험
    → Optional.empty()   → "없음"을 안전하게 표현
```

---

## MemberService — 두 메서드 비교

### ❌ 기존: null 반환

```java
public String findNameById_Old(int id) {
    if (id == 1) {
        return "홍길동";
    }
    return null;  // 호출하는 쪽이 null 체크를 잊으면 사고
}
```

### ✅ Optional: 있음 / 없음 명시

```java
public Optional<String> findNameById(int id) {
    if (id == 1) {
        return Optional.of("홍길동");  // 값 있음
    }
    return Optional.empty();           // 값 없음 (null 아님)
}
```

| API | 의미 |
|-----|------|
| `Optional.of(값)` | **절대 null이 아닌** 값을 담음 |
| `Optional.empty()` | 비어 있음 |
| `Optional.ofNullable(값)` | null일 수도 있을 때 (이 예제에서는 미사용) |

이 예제에서 **id == 1** 만 "홍길동", 그 외(예: 2)는 없음.

---

## 예제 1 — `ifPresent` (있을 때만 실행)

```java
Optional<String> name = service.findNameById(2);  // empty

name.ifPresent(n -> System.out.println("  이름 길이: " + n.length()));
// 값이 없으므로 람다 실행 안 됨 → NPE 없음
System.out.println("  프로그램 정상 종료");
```

```text
값이 있으면 → 람다 실행
값이 없으면 → 아무 것도 안 함 (안전)
```

---

## 예제 2 — `orElse` (없으면 기본값)

```java
String result = service.findNameById(2)
        .orElse("이름없음");

System.out.println(result);           // 이름없음
System.out.println(result.length());  // 4 — null이 아니라서 안전
```

| 메서드 | 설명 |
|--------|------|
| `orElse(기본값)` | 비어 있으면 **항상** 그 값 사용 |
| `orElseGet(() -> …)` | 비어 있을 때만 supplier 실행 (비용 큰 기본값에 유리) |

---

## 예제 3 — `orElseThrow` (없으면 예외)

```java
try {
    String name2 = service.findNameById(2)
            .orElseThrow(() -> new RuntimeException("회원이 없습니다"));
    System.out.println("  이름: " + name2);
} catch (RuntimeException e) {
    System.out.println("  오류: " + e.getMessage());  // 회원이 없습니다
}
```

- “없어도 기본값”이 아니라 **반드시 있어야 하는 경우**에 사용  
- Spring/서비스에서 “회원 없음 → 404/비즈니스 예외” 패턴과 비슷  

---

## 예제 4 — `map` (값 변환)

```java
Optional<Integer> length = service.findNameById(1)
        .map(String::length);   // "홍길동" → 3

length.ifPresent(len -> System.out.println("  길이: " + len));  // 3
```

```text
Optional<String>  --map(길이)-->  Optional<Integer>
비어 있으면 map 결과도 empty (null 안 생김)
```

`String::length` = `n -> n.length()` 메서드 참조.

---

## 예제 5 — `ifPresentOrElse` (있을 때 / 없을 때)

```java
service.findNameById(1).ifPresentOrElse(
    n -> System.out.println("    찾음: " + n),   // 있음
    () -> System.out.println("    없음")         // 없음
);

service.findNameById(2).ifPresentOrElse(
    n -> System.out.println("    찾음: " + n),
    () -> System.out.println("    없음")         // ← 이쪽 실행
);
```

| | ifPresent | ifPresentOrElse |
|--|-----------|-----------------|
| 값 있을 때 | 실행 | 첫 번째 람다 |
| 값 없을 때 | 무시 | **두 번째** Runnable 실행 |

Java 9+ API입니다.

---

## 예제 6 — `filter` (조건에 맞을 때만 유지)

```java
// "홍길동".length() == 3 → 통과 → 출력
service.findNameById(1)
        .filter(n -> n.length() >= 3)
        .ifPresent(n -> System.out.println("    " + n));

// length >= 10 ? 거짓 → Optional.empty() → 출력 없음
service.findNameById(1)
        .filter(n -> n.length() >= 10)
        .ifPresent(n -> System.out.println("    " + n));
```

```text
값이 있어도 조건 실패 → empty 로 바뀜
(null이 되는 게 아님)
```

---

## 예제 7 — 한눈에 비교

```java
// 기존: null 체크 필수
String oldName = service.findNameById_Old(2);
if (oldName != null) {
    System.out.println("이름: " + oldName);
} else {
    System.out.println("이름이 없습니다");
}

// Optional: 의도가 API에 보임
service.findNameById(2)
        .ifPresentOrElse(
            n -> System.out.println("이름: " + n),
            () -> System.out.println("이름이 없습니다")
        );
```

결과는 비슷하지만, Optional은 **반환 타입만 봐도 “없을 수 있다”**는 계약이 분명합니다.

---

## 메서드 치트시트

| 메서드 | 역할 | 이 예제에서 |
|--------|------|-------------|
| `Optional.of` | 값 담기 | id==1 |
| `Optional.empty` | 빈 통 | id!=1 |
| `ifPresent` | 있을 때만 실행 | 예제 1 |
| `orElse` | 기본값 | 예제 2 |
| `orElseThrow` | 없으면 예외 | 예제 3 |
| `map` | 변환 | 예제 4 |
| `ifPresentOrElse` | 있음/없음 분기 | 예제 5, 7 |
| `filter` | 조건 필터 | 예제 6 |

---

## 예상 출력 (요약)

```
1. ifPresent        → (id 2라서 길이 출력 없음) 정상 종료
2. orElse           → 이름없음
3. orElseThrow      → 오류: 회원이 없습니다
4. map              → 길이: 3
5. ifPresentOrElse  → 찾음: 홍길동 / 없음
6. filter           → 홍길동 출력 / (10 이상) 출력 없음
7. 비교             → 둘 다 "이름이 없습니다"
```

---

## 수업에서 강조할 점

1. **반환 타입을 `Optional<T>`로 바꾸면** 호출부가 null을 잊기 어렵다.  
2. `get()`으로 무조건 꺼내지 말고 → `orElse` / `ifPresent` / `orElseThrow` 사용.  
3. `map` · `filter`로 **null 없이** 파이프라인 처리.  
4. 필드에 Optional을 넣거나, 파라미터로 Optional을 받는 패턴은 보통 비추천 (반환값 용도가 주 목적).

더 넓은 이론: `Java-Optional-완전정복.md` (있다면) · [Optional-실전-개선가이드.md](../Optional-실전-개선가이드.md)
