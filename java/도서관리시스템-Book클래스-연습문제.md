# 연습 문제: 도서 관리 시스템 (Book 클래스)

도서관이나 서점에서 책 정보를 관리하고, 할인 및 배송비를 계산하는 `Book` 클래스를 작성해 보세요.

---

## 요구사항 (클래스 설계 조건)

### 패키지 및 클래스명

- `a0108.book.Book`

### 필드 (private)

| 필드 | 타입 | 설명 |
|------|------|------|
| `title` | `String` | 책 제목 |
| `author` | `String` | 저자 |
| `price` | `int` | 정가 |
| `stock` | `int` | 재고 수량 |
| `discountRate` | `double` | 할인율 (기본값 `0.0`) |

### 생성자

- 책 제목, 저자, 정가, 재고 수량을 매개변수로 받아 초기화합니다.
- `discountRate`는 생성자에서 기본적으로 `0.0`으로 초기화하세요.

### Getter / Setter

- 모든 필드의 **Getter**를 만드세요.
- `title`, `author`, `price`, `stock`은 **Setter**를 만듭니다.
- `setDiscountRate(double discountRate)`:
  - 전달받은 할인율이 `0.0` 미만이거나 `1.0` 초과일 경우  
    `"할인율은 0.0 ~ 1.0 사이여야 합니다."` 를 출력하고 값을 변경하지 않도록 유효성 검사를 추가하세요.

### 계산 메서드 (private 추천)

| 메서드 | 설명 |
|--------|------|
| `calcTotalPrice()` | 정가 × 재고수량 (총 정가) 반환 |
| `calcDiscountAmount()` | 총 정가 × 할인율 (총 할인 금액, 정수형으로 형변환) |
| `calcFinalPrice()` | 총 정가 − 할인 금액 (할인 적용된 최종 금액) |
| `calcShippingFee()` | 최종 금액이 30,000원 미만이면 배송비 3,000원, 30,000원 이상이면 0원(무료배송) 반환 |

### 출력 메서드

| 메서드 | 설명 |
|--------|------|
| `printBookInfo()` | 책 정보(제목, 저자, 정가, 재고, 총 정가, 할인율이 있다면 할인 금액과 할인 후 금액)를 예쁘게 출력 |
| `printOrderSheet()` | `printBookInfo()` 호출 후, 배송비와 최종 결제 금액(할인 후 금액 + 배송비)을 포함한 주문서 영수증 출력 |

---

## 테스트 코드 예시

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
        book3.setDiscountRate(1.5); // 유효성 검사 테스트
        book3.printOrderSheet();
    }
}
```

---

## 참고

- 풀이 답안: [도서관리시스템-Book클래스-연습문제-풀이.md](./도서관리시스템-Book클래스-연습문제-풀이.md)
- 소스 코드: [a0108/book/Book.java](./a0108/book/Book.java)
- 관련 교안: [6장_메서드.md](./6장_메서드.md)
