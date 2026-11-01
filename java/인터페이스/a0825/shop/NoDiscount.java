package a0825.shop;

public class NoDiscount implements Discount {
    @Override
    public double apply(int price) {
        return price;
    }

    @Override
    public String getName() {
        return "할인없음";
    }
}
