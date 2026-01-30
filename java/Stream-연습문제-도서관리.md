# Stream API 연습문제 - 도서 관리 시스템

## 문제 설명

도서관에서 도서와 저자 정보를 관리하는 시스템입니다. Stream API를 사용하여 다음 문제들을 해결하세요.

### 클래스 구조

```java
package a0401.streamEx;

public class Author {
    private String name;
    private String country;
    
    public Author(String name, String country) {
        this.name = name;
        this.country = country;
    }
    
    public String getName() {
        return name;
    }
    
    public String getCountry() {
        return country;
    }
    
    @Override
    public String toString() {
        return "Author [name=" + name + ", country=" + country + "]";
    }
}
```

```java
package a0401.streamEx;

public class Book {
    private Author author;
    private int year;
    private int price;
    private String title;
    
    public Book(Author author, int year, int price, String title) {
        this.author = author;
        this.year = year;
        this.price = price;
        this.title = title;
    }
    
    public Author getAuthor() {
        return author;
    }
    
    public int getYear() {
        return year;
    }
    
    public int getPrice() {
        return price;
    }
    
    public String getTitle() {
        return title;
    }
    
    @Override
    public String toString() {
        return "[title=" + title 
            + ", author=" + author.getName() 
            + ", country=" + author.getCountry() 
            + ", year=" + year 
            + ", price=" + price + "]";
    }
}
```

### 초기 데이터

```java
package a0401.streamEx;

import java.util.Arrays;
import java.util.List;

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
        
        // 아래 메서드들을 구현하세요
        practice1(books);  // 문제 1
        practice2(books);  // 문제 2
        practice3(books);  // 문제 3
        practice4(books);  // 문제 4
        practice5(books);  // 문제 5
        practice6(books);  // 문제 6
        practice7(books);  // 문제 7
        practice8(books);  // 문제 8
    }
    
    // 각 practice 메서드를 구현하세요
}
```

---

## 문제 1: 2000년 이후 출판된 모든 도서를 찾아 가격 오름차순으로 정렬

**요구사항:**
- 2000년 이후(2000년 포함)에 출판된 도서만 필터링
- 가격을 기준으로 오름차순 정렬
- 결과를 리스트로 반환

**예상 출력:**
```
[[title=Kafka on the Shore, author=Haruki Murakami, country=Japan, year=2002, price=18000], 
 [title=Colorless Tsukuru, author=Haruki Murakami, country=Japan, year=2013, price=19000]]
```

---

## 문제 2: 도서가 출판된 모든 국가를 중복 없이 나열

**요구사항:**
- 모든 도서의 저자 국가를 추출
- 중복 제거
- 결과를 리스트로 반환

**예상 출력:**
```
[UK, Japan, USA, Russia]
```

---

## 문제 3: 영국(UK) 출신 저자의 모든 도서를 찾아 제목순으로 정렬

**요구사항:**
- 저자의 국가가 "UK"인 도서만 필터링
- 제목을 기준으로 오름차순 정렬
- 결과를 리스트로 반환

**예상 출력:**
```
[[title=1984, author=George Orwell, country=UK, year=1949, price=12000], 
 [title=Animal Farm, author=George Orwell, country=UK, year=1945, price=11000], 
 [title=Harry Potter, author=J.K. Rowling, country=UK, year=1997, price=15000], 
 [title=Harry Potter 2, author=J.K. Rowling, country=UK, year=1998, price=15000]]
```

---

## 문제 4: 일본(Japan) 출신 저자가 있는지 확인

**요구사항:**
- 저자의 국가가 "Japan"인 도서가 하나라도 있는지 확인
- boolean 값 반환

**예상 출력:**
```
true
```

---

## 문제 5: 미국(USA) 출신 저자의 모든 도서 가격 출력

**요구사항:**
- 저자의 국가가 "USA"인 도서만 필터링
- 각 도서의 가격만 추출하여 리스트로 반환

**예상 출력:**
```
[20000, 22000]
```

---

## 문제 6: 모든 저자의 이름을 알파벳 순으로 정렬

**요구사항:**
- 모든 도서에서 저자 이름 추출
- 중복 제거
- 알파벳 순으로 정렬
- 결과를 리스트로 반환

**예상 출력:**
```
[George Orwell, Haruki Murakami, J.K. Rowling, Leo Tolstoy, Stephen King]
```

---

## 문제 7: 가장 비싼 도서 찾기

**요구사항:**
- 모든 도서 중 가격이 가장 높은 도서 찾기
- Optional을 사용하여 안전하게 처리
- 결과가 없을 경우 null 반환

**예상 출력:**
```
[title=War and Peace, author=Leo Tolstoy, country=Russia, year=1869, price=25000]
```

---

## 문제 8: 가장 저렴한 도서의 가격 구하기

**요구사항:**
- 모든 도서 중 가격이 가장 낮은 도서 찾기
- Optional을 사용하여 안전하게 처리
- 가격만 추출하여 출력

**예상 출력:**
```
11000
```

---

## 힌트

- `filter()`: 조건에 맞는 요소만 필터링
- `map()`: 요소를 다른 형태로 변환
- `distinct()`: 중복 제거
- `sorted()`: 정렬
- `collect(Collectors.toList())`: 리스트로 수집
- `max()`, `min()`: 최대값, 최소값 찾기
- `anyMatch()`: 조건을 만족하는 요소가 하나라도 있는지 확인
- `Optional`: null 안전하게 처리하기 위한 래퍼 클래스
- `Comparator.comparing()`: 비교 기준 설정

---

## 추가 도전 문제

### 문제 9: 각 국가별 도서 수 집계

**요구사항:**
- 국가별로 도서가 몇 권인지 집계
- Map<String, Long> 형태로 반환

**힌트:** `Collectors.groupingBy()` 사용

### 문제 10: 평균 가격 구하기

**요구사항:**
- 모든 도서의 평균 가격 계산
- OptionalDouble 반환

**힌트:** `mapToInt()`, `average()` 사용
