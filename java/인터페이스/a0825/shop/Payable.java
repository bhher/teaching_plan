package a0825.shop;

public interface Payable {
    boolean pay(int amount);
    String getMethodName();
}
