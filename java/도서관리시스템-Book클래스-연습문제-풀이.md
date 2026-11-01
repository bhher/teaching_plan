# 연습 문제: 도서 관리 시스템 (Book 클래스) - 풀이

문제 원본: [도서관리시스템-Book클래스-연습문제.md](./도서관리시스템-Book클래스-연습문제.md)

`Product` 클래스와 같은 구조로 `Book` 클래스를 작성한 풀이입니다.

---

## Book.java

```java
package a0108.book;

public class Book {
    private String title;
    private String author;
    private int price;           // 정가
    private int stock;           // 재고 수량
    private double discountRate; // 할인율

    public Book(String title, String author, int price, int stock) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.stock = stock;
        this.discountRate = 0.0; // 기본 할인율 0%
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    // 할인율 설정 (유효성 검사)
    public void setDiscountRate(double discountRate) {
        if (discountRate < 0.0 || discountRate > 1.0) {
            System.out.println("할인율은 0.0 ~ 1.0 사이여야 합니다.");
            return;
        }
        this.discountRate = discountRate;
    }

    public void printBookInfo() {
        System.out.println("==== 도서 정보 ====");
        System.out.println("제목: " + title);
        System.out.println("저자: " + author);
        System.out.println("정가: " + String.format("%,d", price) + "원");
        System.out.println("재고: " + stock + "권");
        System.out.println("총 정가: " + String.format("%,d", calcTotalPrice()) + "원");

        if (discountRate > 0) {
            System.out.println("할인율: " + (discountRate * 100) + "%");
            System.out.println("할인 금액: " + String.format("%,d", calcDiscountAmount()) + "원");
            System.out.println("할인 후 금액: " + String.format("%,d", calcFinalPrice()) + "원");
        } else {
            System.out.println("할인 후 금액: " + String.format("%,d", calcFinalPrice()) + "원");
        }
    }

    public void printOrderSheet() {
        System.out.println("==== 주문서 ====");
        printBookInfo();
        System.out.println("배송비: " + String.format("%,d", calcShippingFee()) + "원");
        System.out.println("최종 결제 금액: " + String.format("%,d", calcFinalPayment()) + "원");
    }

    // 총 정가
    private int calcTotalPrice() {
        return price * stock;
    }

    // 할인 금액
    private int calcDiscountAmount() {
        return (int) (calcTotalPrice() * discountRate);
    }

    // 할인 후 금액
    private int calcFinalPrice() {
        return calcTotalPrice() - calcDiscountAmount();
    }

    // 배송비
    private int calcShippingFee() {
        if (calcFinalPrice() < 30000) {
            return 3000;
        }
        return 0;
    }

    // 최종 결제 금액 (할인 후 금액 + 배송비)
    private int calcFinalPayment() {
        return calcFinalPrice() + calcShippingFee();
    }
}
```

---

## BookMain.java (실행 예시)

```java
package a0108.book;

public class BookMain {
    public static void main(String[] args) {
        Book book1 = new Book("자바의 정석", "남궁성", 30000, 2);
        book1.printBookInfo();

        System.out.println();

        Book book2 = new Book("이것이 자바다", "신용권", 35000, 1);
        book2.setDiscountRate(0.1);
        book2.printOrderSheet();

        System.out.println();

        Book book3 = new Book("혼자 공부하는 자바", "신용권", 28000, 1);
        book3.setDiscountRate(0.2);
        book3.setDiscountRate(1.5);
        book3.printOrderSheet();
    }
}
```

---

## 실행 결과 예시

```
==== 도서 정보 ====
제목: 자바의 정석
저자: 남궁성
정가: 30,000원
재고: 2권
총 정가: 60,000원
할인 후 금액: 60,000원

==== 주문서 ====
==== 도서 정보 ====
제목: 이것이 자바다
저자: 신용권
정가: 35,000원
재고: 1권
총 정가: 35,000원
할인율: 10.0%
할인 금액: 3,500원
할인 후 금액: 31,500원
배송비: 0원
최종 결제 금액: 31,500원

할인율은 0.0 ~ 1.0 사이여야 합니다.
==== 주문서 ====
==== 도서 정보 ====
제목: 혼자 공부하는 자바
저자: 신용권
정가: 28,000원
재고: 1권
총 정가: 28,000원
할인율: 20.0%
할인 금액: 5,600원
할인 후 금액: 22,400원
배송비: 3,000원
최종 결제 금액: 25,400원
```

---

## Product 클래스와 비교

| 항목 | Product | Book |
|------|---------|------|
| 이름 필드 | `name` | `title` |
| 수량 필드 | `quantity` | `stock` |
| 정보 출력 | `printProductInfo()` | `printBookInfo()` |
| 영수증 출력 | `printReceipt()` | `printOrderSheet()` |
| 추가 계산 | `calcVAT()`, `calcPriceVAT()` (부가세) | `calcShippingFee()`, `calcFinalPayment()` (배송비) |

---

## 핵심 포인트

1. **캡슐화**: 필드는 `private`, 계산 메서드는 `private`로 숨기고 출력 메서드에서 호출
2. **유효성 검사**: `setDiscountRate()`에서 범위를 벗어나면 `return`으로 값 변경 방지
3. **형변환**: 할인 금액 계산 시 `(int)`로 소수점 버림
4. **메서드 분리**: 총 정가 → 할인 금액 → 할인 후 금액 → 배송비 → 최종 결제 금액 순으로 계산
5. **출력 포맷**: `String.format("%,d", 금액)`으로 천 단위 콤마 표시

---

## 실행 방법

```bash
cd java/a0108/book
javac Book.java BookMain.java
java a0108.book.BookMain
```

또는 `java` 폴더 기준:

```bash
cd java
javac a0108/book/Book.java a0108/book/BookMain.java
java a0108.book.BookMain
```
