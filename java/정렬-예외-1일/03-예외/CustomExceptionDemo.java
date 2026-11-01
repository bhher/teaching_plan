/**
 * 사용자 정의 예외 데모
 * - InvalidScoreException : Unchecked
 * - InsufficientBalanceException : Checked (throws / try-catch)
 */
public class CustomExceptionDemo {

    public static void main(String[] args) {
        System.out.println("=== 1) Unchecked: 점수 검증 ===");
        try {
            validateScore(85);
            validateScore(150);  // 예외
        } catch (InvalidScoreException e) {
            System.out.println("잡음 → " + e.getMessage());
        }

        System.out.println("\n=== 2) Checked: 출금 ===");
        Account acc = new Account(10000);
        try {
            acc.withdraw(3000);
            acc.withdraw(9000);  // 잔액 부족
        } catch (InsufficientBalanceException e) {
            System.out.println("잡음 → " + e.getMessage());
            System.out.println("현재 잔액: " + e.getBalance());
        }
    }

    static void validateScore(int score) {
        if (score < 0 || score > 100) {
            throw new InvalidScoreException(score);
        }
        System.out.println("유효한 점수: " + score);
    }
}

class Account {
    private int balance;

    Account(int balance) {
        this.balance = balance;
    }

    void withdraw(int amount) throws InsufficientBalanceException {
        if (amount > balance) {
            throw new InsufficientBalanceException(balance, amount);
        }
        balance -= amount;
        System.out.println(amount + "원 출금, 잔액: " + balance);
    }
}
