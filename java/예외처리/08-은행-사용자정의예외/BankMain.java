/** 08. 사용자 정의 예외 — 은행 출금 */
public class BankMain {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(10000);

        try {
            account.withdraw(3000);
            account.withdraw(9000); // 잔액 부족 → 예외
        } catch (InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        }
    }
}
