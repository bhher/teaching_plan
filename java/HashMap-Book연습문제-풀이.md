# HashMap 연습문제 — 도서(Book) 관리 풀이

문제: [HashMap-Book연습문제.md](./HashMap-Book연습문제.md)

---

## 예제 1. Book.java

```java
public class Book {

    private String title;
    private String author;
    private int price;

    public Book(String title, String author, int price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "제목 : " + title +
               ", 저자 : " + author +
               ", 가격 : " + price;
    }
}
```

---

## 예제 2~6. BookMapTest.java

```java
import java.util.HashMap;
import java.util.Map;

public class BookMapTest {

    public static void main(String[] args) {

        Map<String, Book> bookMap = new HashMap<>();

        // 예제 2. 객체 저장
        bookMap.put("B001", new Book("자바의 정석", "남궁성", 30000));
        bookMap.put("B002", new Book("혼자 공부하는 자바", "신용권", 28000));
        bookMap.put("B003", new Book("이것이 자바다", "신용권", 35000));

        System.out.println("=== 전체 도서 ===");
        for (String key : bookMap.keySet()) {
            System.out.println(key + " : " + bookMap.get(key));
        }

        // 예제 3. 특정 도서 조회
        System.out.println("\n=== 특정 도서 조회 ===");
        Book book = bookMap.get("B002");
        System.out.println(book);

        // 예제 4. 가격 수정
        System.out.println("\n=== 가격 수정 ===");
        bookMap.get("B002").setPrice(25000);
        System.out.println(bookMap.get("B002"));

        // 예제 5. 도서 삭제
        System.out.println("\n=== 도서 삭제 ===");
        bookMap.remove("B003");
        System.out.println(bookMap);

        // 예제 6. entrySet으로 출력
        System.out.println("\n=== entrySet 출력 ===");
        for (Map.Entry<String, Book> entry : bookMap.entrySet()) {
            String bookNo = entry.getKey();
            Book b = entry.getValue();
            System.out.println(bookNo + " : " + b);
        }
    }
}
```

---

## 실행 결과 예시

```text
=== 전체 도서 ===
B001 : 제목 : 자바의 정석, 저자 : 남궁성, 가격 : 30000
B002 : 제목 : 혼자 공부하는 자바, 저자 : 신용권, 가격 : 28000
B003 : 제목 : 이것이 자바다, 저자 : 신용권, 가격 : 35000

=== 특정 도서 조회 ===
제목 : 혼자 공부하는 자바, 저자 : 신용권, 가격 : 28000

=== 가격 수정 ===
제목 : 혼자 공부하는 자바, 저자 : 신용권, 가격 : 25000

=== 도서 삭제 ===
{B001=제목 : 자바의 정석, 저자 : 남궁성, 가격 : 30000, B002=제목 : 혼자 공부하는 자바, 저자 : 신용권, 가격 : 25000}

=== entrySet 출력 ===
B001 : 제목 : 자바의 정석, 저자 : 남궁성, 가격 : 30000
B002 : 제목 : 혼자 공부하는 자바, 저자 : 신용권, 가격 : 25000
```

> HashMap은 순서가 보장되지 않으므로 출력 순서는 다를 수 있습니다.  
> 입력 순서를 유지하려면 `LinkedHashMap`을 사용하세요.

---

## 추가 도전 풀이

### 1) 가격 30000 이상만 출력

```java
for (Map.Entry<String, Book> entry : bookMap.entrySet()) {
    if (entry.getValue().getPrice() >= 30000) {
        System.out.println(entry.getKey() + " : " + entry.getValue());
    }
}
```

### 2) 저자 "신용권" 도서 개수

```java
int count = 0;
for (Book b : bookMap.values()) {
    if (b.getAuthor().equals("신용권")) {
        count++;
    }
}
System.out.println("신용권 도서 수: " + count);
```

### 3) Scanner로 조회

```java
Scanner sc = new Scanner(System.in);
System.out.print("도서번호 입력: ");
String no = sc.nextLine();

Book find = bookMap.get(no);
if (find != null) {
    System.out.println(find);
} else {
    System.out.println("해당 도서가 없습니다.");
}
```

---

## 핵심 메서드 정리

| 메서드 | 역할 |
|--------|------|
| `put(key, value)` | 저장 / 같은 key면 수정 |
| `get(key)` | 조회 |
| `remove(key)` | 삭제 |
| `keySet()` | 모든 key |
| `values()` | 모든 value |
| `entrySet()` | key+value 한 쌍씩 |
