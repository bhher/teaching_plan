# 자바 Stream API 정리

Java 8부터 쓰는 **`java.util.stream.Stream`** 을 정리한 문서입니다.

> **이름 주의**  
> - **I/O 스트림**: 파일·키보드 데이터가 흐르는 통로 (`InputStream`, `FileReader` …) → [11장_입출력_IO_파일_처리.md](./11장_입출력_IO_파일_처리.md)  
> - **Stream API**: 컬렉션 데이터를 **파이프라인**으로 처리하는 도구 → **이 문서**

---

## 1. Stream이란?

**Stream**은 데이터를 저장하는 자료구조가 아니라,  
데이터를 **한 줄로 흘려보내며 가공·집계**하는 처리 흐름입니다.

```text
원본 데이터  →  걸러내기  →  바꾸기  →  결과 모으기
(List)         filter        map        collect / forEach
```

### for문과 비교

```java
// 기존 for문
List<Integer> result = new ArrayList<>();
for (int n : list) {
    if (n % 2 == 0) {
        result.add(n * 2);
    }
}

// Stream
List<Integer> result = list.stream()
        .filter(n -> n % 2 == 0)
        .map(n -> n * 2)
        .collect(Collectors.toList());
```

**장점**
- 코드가 짧고 의도가 분명함 (`짝수만`, `2배`)
- 원본 리스트는 바꾸지 않음
- 중간 연산을 이어 붙이기 쉬움

---

## 2. 파이프라인 구조 (가장 중요)

```java
리스트.stream()          // 1) 생성
    .filter(...)         // 2) 중간 연산 (여러 개 가능)
    .map(...)            // 2) 중간 연산
    .collect(...);       // 3) 최종 연산 (반드시 1개)
```

| 단계 | 역할 | 특징 |
|------|------|------|
| **생성** | 스트림 만들기 | `stream()`, `Stream.of()` |
| **중간 연산** | 걸러내기·변환·정렬 | 지연 실행, 체이닝, 결과는 또 Stream |
| **최종 연산** | 실제로 실행 + 끝 | 한 번만, 이후 재사용 불가 |

```text
중간 연산만 있으면 아직 실행되지 않음
최종 연산이 호출되는 순간 한꺼번에 처리됨  →  지연 평가(lazy)
```

---

## 3. 스트림 만들기

```java
import java.util.*;
import java.util.stream.*;

// 컬렉션
List<String> list = Arrays.asList("사과", "바나나", "포도");
Stream<String> s1 = list.stream();

// 배열
String[] arr = {"a", "b", "c"};
Stream<String> s2 = Arrays.stream(arr);

// 값 직접
Stream<Integer> s3 = Stream.of(1, 2, 3, 4, 5);

// 숫자 범위 (1~9 / 1~10)
IntStream r1 = IntStream.range(1, 10);
IntStream r2 = IntStream.rangeClosed(1, 10);
```

---

## 4. 중간 연산

중간 연산은 **Stream을 반환**하므로 `.` 으로 계속 연결합니다.

| 메서드 | 역할 | 예 |
|--------|------|-----|
| `filter` | 조건에 맞는 것만 | 짝수, 80점 이상 |
| `map` | 다른 값으로 변환 | 이름만 꺼내기, 제곱 |
| `distinct` | 중복 제거 | |
| `sorted` | 정렬 | `sorted()`, `sorted(Comparator.reverseOrder())` |
| `limit(n)` | 앞에서 n개 | |
| `skip(n)` | 앞에서 n개 건너뛰기 | |
| `peek` | 중간 확인(디버깅) | `peek(System.out::println)` |

### filter — 걸러내기

```java
List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6);

nums.stream()
    .filter(n -> n % 2 == 0)
    .forEach(System.out::println);  // 2 4 6
```

### map — 변환

```java
List<String> names = Arrays.asList("kim", "lee", "park");

names.stream()
    .map(s -> s.toUpperCase())
    .forEach(System.out::println);  // KIM LEE PARK
```

객체에서 필드만 꺼낼 때도 `map`을 씁니다.

```java
students.stream()
    .map(Student::getName)
    .forEach(System.out::println);
```

### sorted · distinct · limit

```java
Arrays.asList(3, 1, 2, 3, 2).stream()
    .distinct()          // 3, 1, 2
    .sorted()            // 1, 2, 3
    .limit(2)            // 1, 2
    .forEach(System.out::println);
```

---

## 5. 최종 연산

최종 연산이 있어야 **실제로 실행**됩니다.  
한 번 쓰면 그 스트림은 **다시 쓸 수 없습니다.**

| 메서드 | 역할 | 반환 |
|--------|------|------|
| `forEach` | 하나씩 처리(출력 등) | `void` |
| `collect` | List/Set/Map으로 모으기 | 컬렉션 |
| `count` | 개수 | `long` |
| `reduce` | 하나로 합치기(합계 등) | Optional 또는 값 |
| `anyMatch` | 하나라도 조건 만족? | `boolean` |
| `allMatch` | 모두 만족? | `boolean` |
| `noneMatch` | 하나도 안 맞음? | `boolean` |
| `findFirst` | 첫 번째 요소 | `Optional` |
| `min` / `max` | 최소 / 최대 | `Optional` |

### collect — 결과 모으기 (가장 많이 씀)

```java
List<Integer> even = nums.stream()
        .filter(n -> n % 2 == 0)
        .collect(Collectors.toList());

Set<String> set = names.stream()
        .collect(Collectors.toSet());

String joined = names.stream()
        .collect(Collectors.joining(", "));  // kim, lee, park
```

### count · match · find

```java
long cnt = nums.stream()
        .filter(n -> n > 3)
        .count();                 // 3  (4,5,6)

boolean hasEven = nums.stream().anyMatch(n -> n % 2 == 0);  // true
boolean allPositive = nums.stream().allMatch(n -> n > 0);   // true

Optional<Integer> first = nums.stream()
        .filter(n -> n > 4)
        .findFirst();             // 5
```

### reduce — 누적

```java
int sum = nums.stream()
        .reduce(0, (a, b) -> a + b);  // 21

int max = nums.stream()
        .reduce(Integer::max)
        .orElse(0);
```

숫자 합·평균은 기본형 스트림이 더 편합니다.

```java
int sum2 = nums.stream().mapToInt(n -> n).sum();
double avg = nums.stream().mapToInt(n -> n).average().orElse(0);
```

---

## 6. 실전 예제 (학생)

```java
class Student {
    private String name;
    private int score;

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }
    public String getName() { return name; }
    public int getScore() { return score; }
}

List<Student> list = Arrays.asList(
        new Student("홍길동", 90),
        new Student("김영희", 75),
        new Student("이철수", 88),
        new Student("박민수", 60)
);

// 80점 이상 이름만, 점수 높은 순
List<String> names = list.stream()
        .filter(s -> s.getScore() >= 80)
        .sorted((a, b) -> b.getScore() - a.getScore())
        .map(Student::getName)
        .collect(Collectors.toList());
// [홍길동, 이철수]

// 평균 점수
double average = list.stream()
        .mapToInt(Student::getScore)
        .average()
        .orElse(0);
```

---

## 7. 자주 쓰는 Collectors

```java
Collectors.toList()
Collectors.toSet()
Collectors.toMap(키함수, 값함수)
Collectors.joining(", ")
Collectors.groupingBy(분류함수)     // 그룹 나누기
Collectors.counting()
Collectors.averagingInt(...)
Collectors.summingInt(...)
```

```java
// 점수대별 그룹
Map<String, List<Student>> group = list.stream()
        .collect(Collectors.groupingBy(s -> s.getScore() >= 80 ? "합격" : "불합격"));
```

---

## 8. 기본형 스트림

박싱/언박싱을 줄이려면 `IntStream`, `LongStream`, `DoubleStream`을 씁니다.

```java
IntStream.rangeClosed(1, 10)
        .filter(n -> n % 2 == 0)
        .sum();   // 30

list.stream()
        .mapToInt(Student::getScore)  // Stream<Student> → IntStream
        .max()
        .orElse(0);
```

| 변환 | 의미 |
|------|------|
| `mapToInt` | 객체 스트림 → IntStream |
| `boxed()` | IntStream → `Stream<Integer>` |

---

## 9. 주의사항

1. **최종 연산 후 재사용 불가**
   ```java
   Stream<Integer> st = nums.stream();
   st.forEach(System.out::println);
   st.count();  // IllegalStateException
   ```
2. **원본은 안 바뀜** — 결과는 새 데이터
3. **중간만 있으면 실행 안 됨** — `collect` / `forEach` 등이 필요
4. **null 주의** — `filter(Objects::nonNull)` 등으로 걸러내기
5. **I/O Stream과 다름** — `java.io.*` 가 아님

---

## 10. 메서드 참조 (짧게)

람다가 “이미 있는 메서드 호출”이면 `::` 로 줄입니다.

```java
s -> System.out.println(s)   →   System.out::println
s -> s.toUpperCase()         →   String::toUpperCase
s -> s.getName()             →   Student::getName
() -> new ArrayList<>()      →   ArrayList::new
```

---

## 11. 치트시트

```java
list.stream()
    .filter(x -> 조건)
    .map(x -> 변환)
    .distinct()
    .sorted()
    .limit(n)
    .collect(Collectors.toList());

list.stream().forEach(System.out::println);
list.stream().count();
list.stream().anyMatch(x -> 조건);
list.stream().mapToInt(...).sum();
list.stream().mapToInt(...).average().orElse(0);
```

### 한 줄 요약

> **생성 → 중간(filter/map/sorted) → 최종(collect/forEach)**  
> 컬렉션을 for문 대신 **선언적으로** 처리하는 API

---

## 관련 자료

- [람다표현식-설명.md](./람다표현식-설명.md)
- [람다와-스트림-완전정복.md](./람다와-스트림-완전정복.md) — 람다+스트림 상세
- [Stream-연습문제-도서관리.md](./Stream-연습문제-도서관리.md)
- I/O 스트림: [11장_입출력_IO_파일_처리.md](./11장_입출력_IO_파일_처리.md)
