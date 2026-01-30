# Stream API 연습문제 해설 - 도서 관리 시스템

## 전체 코드

```java
package a0401.streamEx;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookMain {
    public static void main(String[] args) {
        Author jkRowling = new Author("J.K. Rowling", "UK");
        Author georgeOrwell = new Author("George Orwell", "UK");
        Author harukiMurakami = new Author("Haruki Murakami", "Japan");
        Author stephenKing = new Author("Stephen King", "USA");
        Author leoTolstoy = new Author("Leo Tolstoy", "Russia");
        
        List<Book> books = Arrays.asList(
            new Book(jkRowling, 1997, 15000, "Harry Potter"),
            new Book(georgeOrwell, 1949, 12000, "1984"),
            new Book(harukiMurakami, 2002, 18000, "Kafka on the Shore"),
            new Book(stephenKing, 1977, 20000, "The Shining"),
            new Book(jkRowling, 1998, 15000, "Harry Potter 2"),
            new Book(georgeOrwell, 1945, 11000, "Animal Farm"),
            new Book(harukiMurakami, 2013, 19000, "Colorless Tsukuru"),
            new Book(stephenKing, 1986, 22000, "It"),
            new Book(leoTolstoy, 1869, 25000, "War and Peace")
        );
        
        practice1(books);
        practice2(books);
        practice3(books);
        practice4(books);
        practice5(books);
        practice6(books);
        practice7(books);
        practice8(books);
    }
    
    // 각 문제의 해설은 아래를 참고하세요
}
```

---

## 문제 1 해설: 2000년 이후 출판된 모든 도서를 찾아 가격 오름차순으로 정렬

### 해설

```java
private static void practice1(List<Book> books) {
    List<Book> result = books.stream()
        .filter(book -> book.getYear() >= 2000)  // 2000년 이후 필터링
        .sorted(Comparator.comparing(Book::getPrice))  // 가격 오름차순 정렬
        .collect(Collectors.toList());
    System.out.println(result);
}
```

### 단계별 설명

1. **`books.stream()`**: 리스트를 스트림으로 변환
2. **`.filter(book -> book.getYear() >= 2000)`**: 
   - 람다 표현식을 사용하여 2000년 이후 출판된 도서만 필터링
   - `book.getYear() >= 2000` 조건을 만족하는 요소만 통과
3. **`.sorted(Comparator.comparing(Book::getPrice))`**: 
   - `Comparator.comparing()`: 비교 기준을 설정
   - `Book::getPrice`: 메서드 참조로 가격을 추출하여 비교
   - 기본적으로 오름차순 정렬
4. **`.collect(Collectors.toList())`**: 스트림의 결과를 리스트로 수집

### 다른 정렬 방법

```java
// 내림차순 정렬
.sorted(Comparator.comparing(Book::getPrice).reversed())

// 연도별 정렬 후 가격별 정렬 (다중 정렬)
.sorted(Comparator.comparing(Book::getYear)
    .thenComparing(Book::getPrice))
```

---

## 문제 2 해설: 도서가 출판된 모든 국가를 중복 없이 나열

### 해설

```java
private static void practice2(List<Book> books) {
    List<String> result = books.stream()
        .map(book -> book.getAuthor().getCountry())  // 저자 국가 추출
        .distinct()  // 중복 제거
        .collect(Collectors.toList());
    System.out.println(result);
}
```

### 단계별 설명

1. **`.map(book -> book.getAuthor().getCountry())`**: 
   - 각 Book 객체에서 Author 객체를 가져온 후
   - Author의 국가 정보만 추출하여 String으로 변환
   - 스트림의 타입이 `Stream<Book>`에서 `Stream<String>`으로 변경됨
2. **`.distinct()`**: 중복된 국가명 제거
3. **`.collect(Collectors.toList())`**: 결과를 리스트로 수집

### 메서드 참조 사용

```java
// 람다 표현식 대신 메서드 참조 사용 (불가능한 경우)
// 이 경우는 중첩된 메서드 호출이므로 람다 표현식이 더 명확함
.map(book -> book.getAuthor().getCountry())
```

---

## 문제 3 해설: 영국(UK) 출신 저자의 모든 도서를 찾아 제목순으로 정렬

### 해설

```java
private static void practice3(List<Book> books) {
    List<Book> result = books.stream()
        .filter(book -> "UK".equals(book.getAuthor().getCountry()))  // UK 필터링
        .sorted(Comparator.comparing(Book::getTitle))  // 제목순 정렬
        .collect(Collectors.toList());
    System.out.println(result);
}
```

### 단계별 설명

1. **`.filter(book -> "UK".equals(book.getAuthor().getCountry()))`**: 
   - 저자의 국가가 "UK"인 도서만 필터링
   - `"UK".equals(...)` 형태로 작성하면 NullPointerException 방지 가능
2. **`.sorted(Comparator.comparing(Book::getTitle))`**: 
   - 제목을 기준으로 오름차순 정렬
   - 문자열의 경우 알파벳 순으로 정렬됨

### 안전한 문자열 비교

```java
// 권장: NullPointerException 방지
.filter(book -> "UK".equals(book.getAuthor().getCountry()))

// 위험: book.getAuthor()가 null이면 예외 발생
.filter(book -> book.getAuthor().getCountry().equals("UK"))
```

---

## 문제 4 해설: 일본(Japan) 출신 저자가 있는지 확인

### 해설

```java
private static void practice4(List<Book> books) {
    boolean result = books.stream()
        .anyMatch(book -> "Japan".equals(book.getAuthor().getCountry()));
    System.out.println(result);
}
```

### 단계별 설명

1. **`.anyMatch(...)`**: 
   - 조건을 만족하는 요소가 **하나라도** 있으면 `true` 반환
   - 조건을 만족하는 요소를 찾으면 즉시 중단 (short-circuit)
   - 모든 요소를 확인할 필요 없음
2. **반환 타입**: `boolean`
3. **수집 불필요**: 최종 연산이므로 `collect()` 불필요

### 다른 Match 메서드들

```java
// 모든 요소가 조건을 만족해야 true
.allMatch(book -> book.getPrice() > 10000)

// 모든 요소가 조건을 만족하지 않아야 true
.noneMatch(book -> book.getPrice() < 5000)
```

---

## 문제 5 해설: 미국(USA) 출신 저자의 모든 도서 가격 출력

### 해설

```java
private static void practice5(List<Book> books) {
    List<Integer> result = books.stream()
        .filter(book -> "USA".equals(book.getAuthor().getCountry()))  // USA 필터링
        .map(Book::getPrice)  // 가격만 추출
        .collect(Collectors.toList());
    System.out.println(result);
}
```

### 단계별 설명

1. **`.filter(...)`**: USA 출신 저자의 도서만 필터링
2. **`.map(Book::getPrice)`**: 
   - 메서드 참조를 사용하여 가격만 추출
   - `Stream<Book>` → `Stream<Integer>`로 변환
3. **`.collect(Collectors.toList())`**: 가격 리스트로 수집

### 메서드 참조 vs 람다 표현식

```java
// 메서드 참조 (간결함)
.map(Book::getPrice)

// 람다 표현식 (동일한 결과)
.map(book -> book.getPrice())
```

---

## 문제 6 해설: 모든 저자의 이름을 알파벳 순으로 정렬

### 해설

```java
private static void practice6(List<Book> books) {
    List<String> result = books.stream()
        .map(book -> book.getAuthor().getName())  // 저자 이름 추출
        .distinct()  // 중복 제거
        .sorted()  // 알파벳 순 정렬 (기본 오름차순)
        .collect(Collectors.toList());
    System.out.println(result);
}
```

### 단계별 설명

1. **`.map(book -> book.getAuthor().getName())`**: 저자 이름만 추출
2. **`.distinct()`**: 중복된 저자 이름 제거
3. **`.sorted()`**: 
   - 파라미터 없이 호출하면 자연 순서(natural order)로 정렬
   - String의 경우 알파벳 순으로 정렬
   - `Comparator.naturalOrder()`와 동일

### 정렬 옵션

```java
// 오름차순 (기본)
.sorted()

// 내림차순
.sorted(Comparator.reverseOrder())

// 대소문자 구분 없이 정렬
.sorted(String.CASE_INSENSITIVE_ORDER)
```

---

## 문제 7 해설: 가장 비싼 도서 찾기

### 해설

```java
private static void practice7(List<Book> books) {
    Optional<Book> result = books.stream()
        .max(Comparator.comparing(Book::getPrice));  // 최대값 찾기
    
    // 방법 1: orElse 사용
    Book book = result.orElse(null);
    System.out.println(book);
    
    // 방법 2: ifPresent 사용 (권장)
    result.ifPresent(System.out::println);
    
    // 방법 3: get 사용 (값이 확실할 때만)
    // System.out.println(result.get());
}
```

### 단계별 설명

1. **`.max(Comparator.comparing(Book::getPrice))`**: 
   - 가격을 기준으로 최대값을 가진 도서 찾기
   - 반환 타입: `Optional<Book>`
2. **Optional이 필요한 이유**: 
   - 스트림이 비어있을 수 있음
   - null을 안전하게 처리하기 위해 Optional 사용
3. **Optional 처리 방법**:
   - `orElse(null)`: 값이 없으면 null 반환
   - `ifPresent(...)`: 값이 있을 때만 실행 (권장)
   - `get()`: 값이 확실할 때만 사용 (값이 없으면 예외 발생)

### Optional 안전하게 사용하기

```java
// 권장: 값이 있을 때만 출력
result.ifPresent(System.out::println);

// 값이 없을 때 기본값 설정
Book book = result.orElse(new Book(null, 0, 0, "No Book"));

// 값이 없을 때 예외 발생
Book book = result.orElseThrow(() -> new RuntimeException("No book found"));
```

---

## 문제 8 해설: 가장 저렴한 도서의 가격 구하기

### 해설

```java
private static void practice8(List<Book> books) {
    Optional<Book> minBook = books.stream()
        .min(Comparator.comparing(Book::getPrice));  // 최소값 찾기
    
    // 방법 1: map으로 가격만 추출
    Optional<Integer> minPrice = minBook.map(Book::getPrice);
    System.out.println(minPrice.orElse(0));
    
    // 방법 2: 한 번에 처리
    Optional<Integer> result = books.stream()
        .map(Book::getPrice)
        .min(Integer::compareTo);
    System.out.println(result.orElse(0));
    
    // 방법 3: get 사용 (값이 확실할 때)
    // System.out.println(minBook.get().getPrice());
}
```

### 단계별 설명

1. **`.min(Comparator.comparing(Book::getPrice))`**: 
   - 가격을 기준으로 최소값을 가진 도서 찾기
   - `Optional<Book>` 반환
2. **`.map(Book::getPrice)`**: Optional 내부의 Book에서 가격만 추출
3. **`.orElse(0)`**: 값이 없으면 0 반환

### 더 간단한 방법

```java
// Integer 스트림으로 변환 후 min 사용
Optional<Integer> result = books.stream()
    .mapToInt(Book::getPrice)  // IntStream으로 변환
    .min();  // OptionalInt 반환

result.ifPresent(System.out::println);
```

---

## 추가 문제 해설

### 문제 9: 각 국가별 도서 수 집계

```java
private static void practice9(List<Book> books) {
    Map<String, Long> result = books.stream()
        .collect(Collectors.groupingBy(
            book -> book.getAuthor().getCountry(),  // 그룹화 기준
            Collectors.counting()  // 개수 집계
        ));
    System.out.println(result);
    // 출력: {USA=2, UK=4, Japan=2, Russia=1}
}
```

### 문제 10: 평균 가격 구하기

```java
private static void practice10(List<Book> books) {
    OptionalDouble average = books.stream()
        .mapToInt(Book::getPrice)  // IntStream으로 변환
        .average();  // 평균 계산
    
    average.ifPresent(avg -> System.out.println("평균 가격: " + avg));
    // 출력: 평균 가격: 17444.444...
}
```

---

## Stream API 핵심 정리

### 중간 연산 (Intermediate Operations)
- **지연 평가**: 최종 연산이 호출될 때까지 실행되지 않음
- **여러 개 연결 가능**: 파이프라인 형성
- 예: `filter()`, `map()`, `distinct()`, `sorted()`, `limit()`, `skip()`

### 최종 연산 (Terminal Operations)
- **즉시 실행**: 호출 시점에 모든 중간 연산 실행
- **스트림 종료**: 한 번만 호출 가능
- 예: `collect()`, `forEach()`, `count()`, `max()`, `min()`, `anyMatch()`, `allMatch()`

### 메서드 참조 (Method Reference)
```java
// 정적 메서드
String::valueOf

// 인스턴스 메서드
Book::getPrice  // book -> book.getPrice()

// 생성자
ArrayList::new  // () -> new ArrayList()
```

### Optional 사용 이유
- null 안전성: NullPointerException 방지
- 명시적 처리: 값이 없을 수 있음을 명확히 표현
- 함수형 스타일: map, filter 등과 함께 사용 가능
