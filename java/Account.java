package a0127.bank;

public class Account {
    private String accountNumber;  // 계좌번호
    private String ownerName;      // 예금주명
    private int balance;           // 잔액
    
    public Account(String accountNumber, String ownerName, int balance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
    }
    
    // 파일 저장용 문자열
    public String toFileString() {
        return accountNumber + "|" + ownerName + "|" + balance;
    }
    
    // 파일에서 읽은 문자열로 객체 생성
    public static Account fromFileString(String line) {
        try {
            String[] parts = line.split("\\|");
            if (parts.length == 3) {
                String accountNumber = parts[0].trim();
                String ownerName = parts[1].trim();
                int balance = Integer.parseInt(parts[2].trim());
                return new Account(accountNumber, ownerName, balance);
            }
        } catch (NumberFormatException e) {
            return null;
        }
        return null;
    }
    
    // Getter
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public String getOwnerName() {
        return ownerName;
    }
    
    public int getBalance() {
        return balance;
    }
    
    // Setter (잔액 변경용)
    public void setBalance(int balance) {
        this.balance = balance;
    }
    
    // 입금
    public void deposit(int amount) {
        this.balance += amount;
    }
    
    // 출금 (잔액 체크 포함)
    public boolean withdraw(int amount) {
        if (balance >= amount) {
            this.balance -= amount;
            return true;
        }
        return false;
    }
    
    @Override
    public String toString() {
        return "계좌번호: " + accountNumber + ", 예금주: " + ownerName + ", 잔액: " + balance + "원";
    }
}
