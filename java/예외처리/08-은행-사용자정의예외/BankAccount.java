/**
 * 은행 계좌
 * throws = "이 메서드에서 예외가 날 수 있으니 호출부가 처리하세요"
 * throw  = 실제로 예외를 던짐
 */
public class BankAccount {
    private int balance;

    public BankAccount(int balance) {
        this.balance = balance;
    }

    public void withdraw(int money) throws InsufficientBalanceException {
        if (money > balance) {
            throw new InsufficientBalanceException("잔액이 부족합니다.");
        }
        balance -= money;
        System.out.println(money + "원 출금되었습니다.");
        System.out.println("현재 잔액: " + balance + "원");
    }
}
