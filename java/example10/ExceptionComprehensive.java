/**
 * 예외 처리 종합 예제
 * 은행 계좌 시스템을 통한 실전 예외 처리
 */

// 사용자 정의 예외: 잔액 부족
class InsufficientBalanceException extends Exception {
    private double balance;
    private double amount;
    
    public InsufficientBalanceException(double balance, double amount) {
        super("잔액 부족: 현재 잔액 " + balance + "원, 요청 금액 " + amount + "원");
        this.balance = balance;
        this.amount = amount;
    }
    
    public double getBalance() { return balance; }
    public double getAmount() { return amount; }
}

// 사용자 정의 예외: 잘못된 금액
class InvalidAmountException extends Exception {
    public InvalidAmountException(double amount) {
        super("유효하지 않은 금액: " + amount + "원 (0보다 커야 함)");
    }
}

// 은행 계좌 클래스
class BankAccount {
    private String accountNumber;
    private String ownerName;
    private double balance;
    
    public BankAccount(String accountNumber, String ownerName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        if (initialBalance < 0) {
            this.balance = 0;
        } else {
            this.balance = initialBalance;
        }
    }
    
    // 입금
    public void deposit(double amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException(amount);
        }
        balance += amount;
        System.out.println(amount + "원 입금되었습니다. 잔액: " + balance + "원");
    }
    
    // 출금
    public void withdraw(double amount) throws InsufficientBalanceException, InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException(amount);
        }
        if (amount > balance) {
            throw new InsufficientBalanceException(balance, amount);
        }
        balance -= amount;
        System.out.println(amount + "원 출금되었습니다. 잔액: " + balance + "원");
    }
    
    // 이체
    public void transfer(BankAccount target, double amount) 
            throws InsufficientBalanceException, InvalidAmountException {
        System.out.println("\n=== 이체 시작 ===");
        try {
            this.withdraw(amount);
            target.deposit(amount);
            System.out.println(ownerName + " → " + target.ownerName + " " + amount + "원 이체 완료");
        } catch (InsufficientBalanceException | InvalidAmountException e) {
            System.out.println("이체 실패: " + e.getMessage());
            throw e;  // 예외 재발생
        } finally {
            System.out.println("=== 이체 종료 ===\n");
        }
    }
    
    public double getBalance() { return balance; }
    public String getAccountNumber() { return accountNumber; }
    public String getOwnerName() { return ownerName; }
    
    public void displayInfo() {
        System.out.println("계좌번호: " + accountNumber);
        System.out.println("예금주: " + ownerName);
        System.out.println("잔액: " + balance + "원");
    }
}

public class ExceptionComprehensive {
    public static void main(String[] args) {
        System.out.println("=== 예외 처리 종합 예제: 은행 계좌 시스템 ===\n");
        
        // 계좌 생성
        BankAccount account1 = new BankAccount("123-456-789", "홍길동", 10000);
        BankAccount account2 = new BankAccount("987-654-321", "김철수", 5000);
        
        System.out.println("--- 초기 계좌 정보 ---");
        account1.displayInfo();
        System.out.println();
        account2.displayInfo();
        
        // 예제 1: 정상 입금
        System.out.println("\n--- 예제 1: 정상 입금 ---");
        try {
            account1.deposit(5000);
        } catch (InvalidAmountException e) {
            System.out.println("예외 처리: " + e.getMessage());
        }
        
        // 예제 2: 잘못된 금액 입금
        System.out.println("\n--- 예제 2: 잘못된 금액 입금 ---");
        try {
            account1.deposit(-1000);
        } catch (InvalidAmountException e) {
            System.out.println("예외 처리: " + e.getMessage());
        }
        
        // 예제 3: 정상 출금
        System.out.println("\n--- 예제 3: 정상 출금 ---");
        try {
            account1.withdraw(3000);
        } catch (InsufficientBalanceException | InvalidAmountException e) {
            System.out.println("예외 처리: " + e.getMessage());
        }
        
        // 예제 4: 잔액 부족 출금
        System.out.println("\n--- 예제 4: 잔액 부족 출금 ---");
        try {
            account1.withdraw(50000);
        } catch (InsufficientBalanceException e) {
            System.out.println("예외 처리: " + e.getMessage());
            System.out.println("현재 잔액: " + e.getBalance() + "원");
            System.out.println("요청 금액: " + e.getAmount() + "원");
        } catch (InvalidAmountException e) {
            System.out.println("예외 처리: " + e.getMessage());
        }
        
        // 예제 5: 정상 이체
        System.out.println("\n--- 예제 5: 정상 이체 ---");
        try {
            account1.transfer(account2, 2000);
        } catch (InsufficientBalanceException | InvalidAmountException e) {
            System.out.println("이체 실패: " + e.getMessage());
        }
        
        // 예제 6: 잔액 부족 이체
        System.out.println("\n--- 예제 6: 잔액 부족 이체 ---");
        try {
            account1.transfer(account2, 100000);
        } catch (InsufficientBalanceException e) {
            System.out.println("이체 실패: " + e.getMessage());
        } catch (InvalidAmountException e) {
            System.out.println("이체 실패: " + e.getMessage());
        }
        
        // 최종 계좌 정보
        System.out.println("--- 최종 계좌 정보 ---");
        account1.displayInfo();
        System.out.println();
        account2.displayInfo();
        
        // 예제 7: 여러 예외 처리
        System.out.println("\n--- 예제 7: 여러 예외 처리 ---");
        try {
            account1.deposit(0);  // InvalidAmountException
            account1.withdraw(-100);  // InvalidAmountException
            account1.withdraw(100000);  // InsufficientBalanceException
        } catch (InsufficientBalanceException e) {
            System.out.println("잔액 부족 예외: " + e.getMessage());
        } catch (InvalidAmountException e) {
            System.out.println("잘못된 금액 예외: " + e.getMessage());
        }
        
        System.out.println("\n=== 예외 처리의 중요성 ===");
        System.out.println("1. 프로그램 안정성: 예외 상황에서도 안정적으로 동작");
        System.out.println("2. 사용자 경험: 친절한 오류 메시지 제공");
        System.out.println("3. 디버깅: 예외 정보로 문제 파악 용이");
        System.out.println("4. 리소스 관리: finally로 리소스 정리 보장");
    }
}

