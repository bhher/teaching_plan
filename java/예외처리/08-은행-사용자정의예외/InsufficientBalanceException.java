/** 잔액 부족 — Checked Exception (extends Exception) */
public class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
