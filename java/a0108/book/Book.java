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
