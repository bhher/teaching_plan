package a0825.shop;

public class BankTransfer implements Payable {
    @Override
    public boolean pay(int amount) {
        System.out.println("계좌이체 완료: " + amount + "원");
        return true;
    }

    @Override
    public String getMethodName() {
        return "계좌이체";
    }
}
