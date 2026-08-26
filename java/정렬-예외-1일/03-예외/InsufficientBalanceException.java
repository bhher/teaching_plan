/** 잔액 부족 — Checked (Exception) → 호출부에서 반드시 처리 */
public class InsufficientBalanceException extends Exception {
    private int balance;
    private int amount;

    public InsufficientBalanceException(int balance, int amount) {
        super("잔액 부족: 잔액 " + balance + "원, 요청 " + amount + "원");
        this.balance = balance;
        this.amount = amount;
    }

    public int getBalance() {
        return balance;
    }

    public int getAmount() {
        return amount;
    }
}
