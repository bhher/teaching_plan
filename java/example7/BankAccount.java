/**
 * 은행 계좌 클래스 예제
 * 캡슐화와 접근제어자를 활용한 실전 예제
 */
public class BankAccount {
    // private 필드: 외부에서 직접 접근 불가
    private String accountNumber;
    private String ownerName;
    private double balance;
    
    // 생성자
    public BankAccount(String accountNumber, String ownerName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        if (initialBalance >= 0) {
            this.balance = initialBalance;
        } else {
            this.balance = 0;
            System.out.println("초기 잔액은 0 이상이어야 합니다. 0으로 설정합니다.");
        }
    }
    
    // Getter 메서드
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public String getOwnerName() {
        return ownerName;
    }
    
    public double getBalance() {
        return balance;
    }
    
    // 입금 메서드
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println(amount + "원 입금되었습니다.");
            System.out.println("현재 잔액: " + balance + "원");
        } else {
            System.out.println("입금 금액은 0보다 커야 합니다.");
        }
    }
    
    // 출금 메서드
    public void withdraw(double amount) {
        if (amount > 0) {
            if (amount <= balance) {
                balance -= amount;
                System.out.println(amount + "원 출금되었습니다.");
                System.out.println("현재 잔액: " + balance + "원");
            } else {
                System.out.println("잔액이 부족합니다.");
                System.out.println("현재 잔액: " + balance + "원");
            }
        } else {
            System.out.println("출금 금액은 0보다 커야 합니다.");
        }
    }
    
    // 계좌 정보 출력
    public void displayAccountInfo() {
        System.out.println("=== 계좌 정보 ===");
        System.out.println("계좌번호: " + accountNumber);
        System.out.println("예금주: " + ownerName);
        System.out.println("잔액: " + balance + "원");
    }
    
    // 이체 메서드
    public boolean transfer(BankAccount targetAccount, double amount) {
        if (amount > 0 && amount <= balance) {
            this.withdraw(amount);
            targetAccount.deposit(amount);
            System.out.println(ownerName + " → " + targetAccount.ownerName + " " + amount + "원 이체 완료");
            return true;
        } else {
            System.out.println("이체 실패: 잔액 부족 또는 잘못된 금액");
            return false;
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== 은행 계좌 예제 ===\n");
        
        // 계좌 생성
        BankAccount account1 = new BankAccount("123-456-789", "홍길동", 10000);
        BankAccount account2 = new BankAccount("987-654-321", "김철수", 5000);
        
        // 계좌 정보 출력
        account1.displayAccountInfo();
        System.out.println();
        account2.displayAccountInfo();
        
        System.out.println("\n=== 입금 테스트 ===");
        account1.deposit(5000);
        
        System.out.println("\n=== 출금 테스트 ===");
        account1.withdraw(3000);
        account1.withdraw(20000);  // 잔액 부족
        
        System.out.println("\n=== 이체 테스트 ===");
        account1.transfer(account2, 5000);
        
        System.out.println("\n=== 최종 계좌 정보 ===");
        account1.displayAccountInfo();
        System.out.println();
        account2.displayAccountInfo();
        
        System.out.println("\n=== 캡슐화의 효과 ===");
        System.out.println("balance 필드는 private이므로 직접 접근 불가:");
        // account1.balance = 1000000;  // 오류! 직접 접근 불가
        System.out.println("잔액 변경은 deposit() 또는 withdraw() 메서드를 통해서만 가능");
    }
}

