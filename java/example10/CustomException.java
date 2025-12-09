/**
 * 사용자 정의 예외를 설명하는 예제
 * Checked Exception과 Unchecked Exception을 보여줍니다
 */
// Checked Exception (Exception 상속)
class InsufficientBalanceException extends Exception {
    private double balance;
    private double amount;
    
    public InsufficientBalanceException(double balance, double amount) {
        super("잔액 부족: 현재 잔액 " + balance + "원, 요청 금액 " + amount + "원");
        this.balance = balance;
        this.amount = amount;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public double getAmount() {
        return amount;
    }
}

// Unchecked Exception (RuntimeException 상속)
class InvalidScoreException extends RuntimeException {
    private int score;
    
    public InvalidScoreException(int score) {
        super("유효하지 않은 점수: " + score + " (0-100 범위를 벗어남)");
        this.score = score;
    }
    
    public int getScore() {
        return score;
    }
}

// Checked Exception
class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message);
    }
}

// Unchecked Exception
class NegativeNumberException extends RuntimeException {
    public NegativeNumberException(String message) {
        super(message);
    }
}

// 은행 계좌 클래스
class BankAccount {
    private double balance;
    
    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }
    
    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount > balance) {
            throw new InsufficientBalanceException(balance, amount);
        }
        balance -= amount;
        System.out.println(amount + "원 출금되었습니다. 잔액: " + balance + "원");
    }
    
    public double getBalance() {
        return balance;
    }
}

// 점수 관리 클래스
class ScoreManager {
    public static void validateScore(int score) {
        if (score < 0 || score > 100) {
            throw new InvalidScoreException(score);
        }
        System.out.println("유효한 점수: " + score);
    }
}

// 나이 검증 클래스
class AgeValidator {
    public static void validateAge(int age) throws InvalidAgeException {
        if (age < 0 || age > 150) {
            throw new InvalidAgeException("유효하지 않은 나이: " + age + " (0-150 범위)");
        }
        System.out.println("유효한 나이: " + age);
    }
}

public class CustomException {
    public static void main(String[] args) {
        System.out.println("=== 사용자 정의 예외 예제 ===\n");
        
        // 예제 1: Checked Exception (은행 계좌)
        System.out.println("--- 예제 1: Checked Exception (은행 계좌) ---");
        BankAccount account = new BankAccount(10000);
        
        try {
            account.withdraw(5000);  // 정상
            account.withdraw(10000);  // 예외 발생
        } catch (InsufficientBalanceException e) {
            System.out.println("예외 처리: " + e.getMessage());
            System.out.println("현재 잔액: " + e.getBalance() + "원");
            System.out.println("요청 금액: " + e.getAmount() + "원");
        }
        
        // 예제 2: Unchecked Exception (점수 검증)
        System.out.println("\n--- 예제 2: Unchecked Exception (점수 검증) ---");
        try {
            ScoreManager.validateScore(85);  // 정상
            ScoreManager.validateScore(150);  // 예외 발생
        } catch (InvalidScoreException e) {
            System.out.println("예외 처리: " + e.getMessage());
            System.out.println("입력된 점수: " + e.getScore());
        }
        
        // 예제 3: Checked Exception (나이 검증)
        System.out.println("\n--- 예제 3: Checked Exception (나이 검증) ---");
        try {
            AgeValidator.validateAge(25);  // 정상
            AgeValidator.validateAge(200);  // 예외 발생
        } catch (InvalidAgeException e) {
            System.out.println("예외 처리: " + e.getMessage());
        }
        
        // 예제 4: Unchecked Exception (음수 검증)
        System.out.println("\n--- 예제 4: Unchecked Exception (음수 검증) ---");
        try {
            calculateSquare(5);  // 정상
            calculateSquare(-3);  // 예외 발생
        } catch (NegativeNumberException e) {
            System.out.println("예외 처리: " + e.getMessage());
        }
        
        // 예제 5: 사용자 정의 예외 정보
        System.out.println("\n--- 예제 5: 사용자 정의 예외 정보 ---");
        try {
            processData(-10);
        } catch (NegativeNumberException e) {
            System.out.println("예외 클래스: " + e.getClass().getName());
            System.out.println("예외 메시지: " + e.getMessage());
            System.out.println("스택 트레이스:");
            e.printStackTrace();
        }
        
        System.out.println("\n=== Checked vs Unchecked Exception ===");
        System.out.println("Checked Exception:");
        System.out.println("- Exception을 상속 (RuntimeException 제외)");
        System.out.println("- 컴파일 시점에 반드시 처리해야 함");
        System.out.println("- try-catch 또는 throws 필수");
        System.out.println("\nUnchecked Exception:");
        System.out.println("- RuntimeException을 상속");
        System.out.println("- 컴파일 시점에 처리하지 않아도 됨");
        System.out.println("- try-catch 또는 throws 선택");
    }
    
    public static void calculateSquare(int number) {
        if (number < 0) {
            throw new NegativeNumberException("음수는 처리할 수 없습니다: " + number);
        }
        System.out.println(number + "의 제곱: " + (number * number));
    }
    
    public static void processData(int value) {
        if (value < 0) {
            throw new NegativeNumberException("데이터는 0 이상이어야 합니다: " + value);
        }
        System.out.println("데이터 처리: " + value);
    }
}

