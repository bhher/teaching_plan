/**
 * 예제 9: 결제 시스템
 * 업캐스팅과 다형성을 활용한 다양한 결제 수단 처리 시스템
 */
import java.util.ArrayList;

// 결제 수단 추상 클래스
abstract class PaymentMethod {
    protected String methodName;
    protected double amount;
    protected String transactionId;
    
    PaymentMethod(String methodName, double amount, String transactionId) {
        this.methodName = methodName;
        this.amount = amount;
        this.transactionId = transactionId;
    }
    
    // 추상 메서드: 각 결제 수단마다 다른 수수료 계산
    abstract double calculateFee();
    
    // 최종 결제 금액 계산
    double getTotalAmount() {
        return amount + calculateFee();
    }
    
    // 공통 메서드: 결제 정보 출력
    void printPaymentInfo() {
        System.out.println("결제수단: " + methodName + 
                          ", 결제금액: " + String.format("%,.0f원", amount) +
                          ", 수수료: " + String.format("%,.0f원", calculateFee()) +
                          ", 총액: " + String.format("%,.0f원", getTotalAmount()) +
                          ", 거래ID: " + transactionId);
    }
    
    // 추상 메서드: 결제 처리
    abstract boolean processPayment();
    
    String getMethodName() {
        return methodName;
    }
    
    double getAmount() {
        return amount;
    }
}

// 신용카드 결제
class CreditCard extends PaymentMethod {
    private static final double FEE_RATE = 0.03; // 3% 수수료
    private String cardNumber;
    private String cardCompany;
    
    CreditCard(double amount, String transactionId, String cardNumber, String cardCompany) {
        super("신용카드", amount, transactionId);
        this.cardNumber = cardNumber;
        this.cardCompany = cardCompany;
    }
    
    @Override
    double calculateFee() {
        return amount * FEE_RATE;
    }
    
    @Override
    boolean processPayment() {
        System.out.println(cardCompany + " 카드(" + maskCardNumber(cardNumber) + ")로 결제를 처리합니다.");
        return true;
    }
    
    private String maskCardNumber(String cardNumber) {
        if (cardNumber.length() > 4) {
            return "****-****-****-" + cardNumber.substring(cardNumber.length() - 4);
        }
        return cardNumber;
    }
    
    void checkCardLimit() {
        System.out.println(cardCompany + " 카드 한도를 확인합니다.");
    }
}

// 계좌이체
class BankTransfer extends PaymentMethod {
    private static final double FEE_RATE = 0.001; // 0.1% 수수료
    private String bankName;
    private String accountNumber;
    
    BankTransfer(double amount, String transactionId, String bankName, String accountNumber) {
        super("계좌이체", amount, transactionId);
        this.bankName = bankName;
        this.accountNumber = accountNumber;
    }
    
    @Override
    double calculateFee() {
        return amount * FEE_RATE;
    }
    
    @Override
    boolean processPayment() {
        System.out.println(bankName + " 계좌(" + maskAccountNumber(accountNumber) + ")로 이체합니다.");
        return true;
    }
    
    private String maskAccountNumber(String accountNumber) {
        if (accountNumber.length() > 4) {
            return "***-" + accountNumber.substring(accountNumber.length() - 4);
        }
        return accountNumber;
    }
    
    void verifyAccount() {
        System.out.println(bankName + " 계좌를 확인합니다.");
    }
}

// 모바일 결제
class MobilePayment extends PaymentMethod {
    private static final double FEE_RATE = 0.02; // 2% 수수료
    private String mobileCompany; // 카카오페이, 네이버페이 등
    private String phoneNumber;
    
    MobilePayment(double amount, String transactionId, String mobileCompany, String phoneNumber) {
        super("모바일결제", amount, transactionId);
        this.mobileCompany = mobileCompany;
        this.phoneNumber = phoneNumber;
    }
    
    @Override
    double calculateFee() {
        return amount * FEE_RATE;
    }
    
    @Override
    boolean processPayment() {
        System.out.println(mobileCompany + "(" + phoneNumber + ")로 결제를 처리합니다.");
        return true;
    }
    
    void sendNotification() {
        System.out.println(mobileCompany + "로 결제 알림을 전송합니다.");
    }
}

// 현금 결제
class CashPayment extends PaymentMethod {
    private static final double FEE_RATE = 0.0; // 수수료 없음
    
    CashPayment(double amount, String transactionId) {
        super("현금", amount, transactionId);
    }
    
    @Override
    double calculateFee() {
        return amount * FEE_RATE;
    }
    
    @Override
    boolean processPayment() {
        System.out.println("현금 " + String.format("%,.0f원", amount) + "을(를) 받았습니다.");
        return true;
    }
    
    void giveChange(double paidAmount) {
        double change = paidAmount - amount;
        if (change > 0) {
            System.out.println("거스름돈: " + String.format("%,.0f원", change));
        } else if (change == 0) {
            System.out.println("정확히 지불하셨습니다.");
        } else {
            System.out.println("부족한 금액: " + String.format("%,.0f원", Math.abs(change)));
        }
    }
}

public class Example9_PaymentSystem {
    public static void main(String[] args) {
        System.out.println("=== 실전 예제: 결제 시스템 ===\n");
        
        // 다양한 타입의 결제 수단을 부모 타입 배열에 저장 (업캐스팅)
        PaymentMethod[] payments = new PaymentMethod[5];
        payments[0] = new CreditCard(100000, "TXN001", "1234567890123456", "삼성카드");
        payments[1] = new BankTransfer(50000, "TXN002", "국민은행", "123-456-789012");
        payments[2] = new MobilePayment(75000, "TXN003", "카카오페이", "010-1234-5678");
        payments[3] = new CashPayment(30000, "TXN004");
        payments[4] = new CreditCard(200000, "TXN005", "9876543210987654", "신한카드");
        
        System.out.println("=== 모든 결제 정보 출력 ===");
        for (PaymentMethod payment : payments) {
            payment.printPaymentInfo();  // 다형성: 각 결제 수단에 맞는 수수료 계산
        }
        
        System.out.println("\n=== 전체 결제 통계 ===");
        double totalAmount = 0;
        double totalFee = 0;
        for (PaymentMethod payment : payments) {
            totalAmount += payment.getAmount();
            totalFee += payment.calculateFee();
        }
        System.out.println("총 결제금액: " + String.format("%,.0f원", totalAmount));
        System.out.println("총 수수료: " + String.format("%,.0f원", totalFee));
        System.out.println("총액: " + String.format("%,.0f원", totalAmount + totalFee));
        
        System.out.println("\n=== 결제 처리 ===");
        for (PaymentMethod payment : payments) {
            payment.processPayment();  // 다형성: 각 결제 수단에 맞는 처리
            System.out.println();
        }
        
        System.out.println("=== 결제 수단별 특수 기능 ===");
        for (PaymentMethod payment : payments) {
            if (payment instanceof CreditCard) {
                CreditCard cc = (CreditCard) payment;
                cc.checkCardLimit();
            } else if (payment instanceof BankTransfer) {
                BankTransfer bt = (BankTransfer) payment;
                bt.verifyAccount();
            } else if (payment instanceof MobilePayment) {
                MobilePayment mp = (MobilePayment) payment;
                mp.sendNotification();
            } else if (payment instanceof CashPayment) {
                CashPayment cp = (CashPayment) payment;
                cp.giveChange(35000); // 35000원 지불
            }
        }
        
        System.out.println("\n=== 특정 결제 수단 검색 ===");
        String searchMethod = "신용카드";
        ArrayList<PaymentMethod> foundPayments = findPaymentsByMethod(payments, searchMethod);
        if (!foundPayments.isEmpty()) {
            System.out.println(searchMethod + " 결제 목록:");
            for (PaymentMethod payment : foundPayments) {
                payment.printPaymentInfo();
            }
        }
    }
    
    // 업캐스팅을 활용한 공통 메서드: 결제 수단별 검색
    static ArrayList<PaymentMethod> findPaymentsByMethod(PaymentMethod[] payments, String methodName) {
        ArrayList<PaymentMethod> result = new ArrayList<>();
        for (PaymentMethod payment : payments) {
            if (payment.getMethodName().equals(methodName)) {
                result.add(payment);
            }
        }
        return result;
    }
}
