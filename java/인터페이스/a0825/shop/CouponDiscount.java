package a0825.shop;

public class CouponDiscount implements Discount {
    private int couponAmount;

    public CouponDiscount(int couponAmount) {
        this.couponAmount = couponAmount;
    }

    @Override
    public double apply(int price) {
        int result = price - couponAmount;
        return result < 0 ? 0 : result;
    }

    @Override
    public String getName() {
        return "쿠폰할인(" + couponAmount + "원)";
    }
}
