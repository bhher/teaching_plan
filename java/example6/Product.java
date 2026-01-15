/**
 * 상품 총금액 계산 메서드 예제
 * 객체가 자신의 금액을 계산하고 출력하도록 설계
 */
public class Product {
    // 필드 (속성)
    private String name;
    private int price;           // 단가
    private int quantity;        // 수량
    private double discountRate; // 할인율 (0.0 ~ 1.0)
    
    // 생성자
    public Product(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.discountRate = 0.0;  // 기본 할인율 0%
    }
    
    // Getter 메서드들
    public String getName() {
        return name;
    }
    
    public int getPrice() {
        return price;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public double getDiscountRate() {
        return discountRate;
    }
    
    // Setter: 할인율 설정 (유효성 검사 포함)
    public void setDiscountRate(double discountRate) {
        if (discountRate < 0 || discountRate > 1.0) {
            System.out.println("할인율은 0.0 ~ 1.0 사이여야 합니다!");
            return;
        }
        this.discountRate = discountRate;
    }
    
    // 계산 메서드: 총 금액 (할인 전)
    public int calculateTotalPrice() {
        return price * quantity;
    }
    
    // 계산 메서드: 할인 금액
    public int calculateDiscountAmount() {
        return (int)(calculateTotalPrice() * discountRate);
    }
    
    // 계산 메서드: 최종 금액 (할인 후)
    public int calculateFinalPrice() {
        return calculateTotalPrice() - calculateDiscountAmount();
    }
    
    // 계산 메서드: 부가세 계산 (10%)
    public int calculateVAT() {
        return (int)(calculateFinalPrice() * 0.1);
    }
    
    // 계산 메서드: 부가세 포함 금액
    public int calculatePriceWithVAT() {
        return calculateFinalPrice() + calculateVAT();
    }
    
    // 출력 메서드: 상품 정보 출력
    public void printProductInfo() {
        System.out.println("=== 상품 정보 ===");
        System.out.println("상품명: " + name);
        System.out.println("단가: " + String.format("%,d", price) + "원");
        System.out.println("수량: " + quantity + "개");
        System.out.println("총 금액: " + String.format("%,d", calculateTotalPrice()) + "원");
        
        if (discountRate > 0) {
            System.out.println("할인율: " + (discountRate * 100) + "%");
            System.out.println("할인 금액: " + String.format("%,d", calculateDiscountAmount()) + "원");
            System.out.println("할인 후 금액: " + String.format("%,d", calculateFinalPrice()) + "원");
        } else {
            System.out.println("할인 후 금액: " + String.format("%,d", calculateFinalPrice()) + "원");
        }
    }
    
    // 출력 메서드: 영수증 출력
    public void printReceipt() {
        System.out.println("========== 영수증 ==========");
        printProductInfo();
        System.out.println("부가세: " + String.format("%,d", calculateVAT()) + "원");
        System.out.println("최종 결제 금액: " + String.format("%,d", calculatePriceWithVAT()) + "원");
        System.out.println("===========================");
    }
}





