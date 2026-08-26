package a0825.shop;

public class OrderService {
    private Discount discount;
    private Payable payable;
    private Notifier notifier;

    public OrderService(Discount discount, Payable payable, Notifier notifier) {
        this.discount = discount;
        this.payable = payable;
        this.notifier = notifier;
    }

    public boolean checkout(String productName, int price) {
        System.out.println("상품: " + productName + " | 원가: " + price + "원");
        System.out.println("적용 할인: " + discount.getName());

        int payAmount = (int) discount.apply(price);
        System.out.println("결제 금액: " + payAmount + "원");

        boolean ok = payable.pay(payAmount);
        if (ok) {
            notifier.send(productName + " 결제 완료 (" + payAmount + "원)");
            System.out.println("주문 성공!");
        } else {
            System.out.println("주문 실패");
        }
        return ok;
    }
}
