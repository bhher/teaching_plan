package a0825.shop;

/**
 * 인터페이스 실전 — 쇼핑몰 주문
 *
 * 실행 (인터페이스 폴더에서):
 *   javac a0825/shop/*.java
 *   java a0825.shop.ShopMain
 */
public class ShopMain {
    public static void main(String[] args) {
        System.out.println("===== 주문 1 =====");
        OrderService order1 = new OrderService(
                new MemberDiscount(),
                new CardPay(200000),
                new SmsNotifier()
        );
        order1.checkout("무선이어폰", 100000);

        System.out.println("\n===== 주문 2 =====");
        OrderService order2 = new OrderService(
                new CouponDiscount(5000),
                new KakaoPay(30000),
                new EmailNotifier()
        );
        order2.checkout("키보드", 50000);

        System.out.println("\n===== 주문 3 =====");
        OrderService order3 = new OrderService(
                new NoDiscount(),
                new BankTransfer(),
                new SmsNotifier()
        );
        order3.checkout("마우스", 20000);
    }
}
