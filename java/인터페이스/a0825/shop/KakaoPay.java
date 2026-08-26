package a0825.shop;

public class KakaoPay implements Payable {
    private int balance;

    public KakaoPay(int balance) {
        this.balance = balance;
    }

    @Override
    public boolean pay(int amount) {
        if (amount > balance) {
            System.out.println("잔액(한도) 부족");
            return false;
        }
        balance -= amount;
        System.out.println("카카오페이 결제 완료: " + amount + "원");
        return true;
    }

    @Override
    public String getMethodName() {
        return "카카오페이";
    }
}
