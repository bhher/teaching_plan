package a0825.shop;

public interface Discount {
    double apply(int price);
    String getName();
}
