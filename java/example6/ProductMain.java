/**
 * Product 클래스 테스트를 위한 Main 파일
 * 상품 총금액 계산 메서드 예제를 실행
 */
public class ProductMain {
    public static void main(String[] args) {
        System.out.println("========== 상품 총금액 계산 예제 ==========\n");
        
        // 상품 객체 생성
        Product product1 = new Product("노트북", 1000000, 2);
        Product product2 = new Product("마우스", 25000, 3);
        Product product3 = new Product("키보드", 50000, 1);
        
        // 할인율 설정
        product1.setDiscountRate(0.1);  // 10% 할인
        product2.setDiscountRate(0.15); // 15% 할인
        // product3는 할인 없음 (기본 0%)
        
        System.out.println();
        // 상품1 정보 출력
        product1.printProductInfo();
        System.out.println();
        
        // 상품2 정보 출력
        product2.printProductInfo();
        System.out.println();
        
        // 상품3 영수증 출력
        product3.printReceipt();
        System.out.println();
        product1.printReceipt();
        System.out.println();
        
        System.out.println("\n========== 개별 메서드 테스트 ==========");
        
        // 총 금액 계산 테스트
        System.out.println(product1.getName() + " 총 금액: " + 
                          String.format("%,d", product1.calculateTotalPrice()) + "원");
        System.out.println(product2.getName() + " 총 금액: " + 
                          String.format("%,d", product2.calculateTotalPrice()) + "원");
        
        // 할인 금액 계산 테스트
        System.out.println(product1.getName() + " 할인 금액: " + 
                          String.format("%,d", product1.calculateDiscountAmount()) + "원");
        System.out.println(product2.getName() + " 할인 금액: " + 
                          String.format("%,d", product2.calculateDiscountAmount()) + "원");
        
        // 최종 금액 계산 테스트
        System.out.println(product1.getName() + " 최종 금액: " + 
                          String.format("%,d", product1.calculateFinalPrice()) + "원");
        System.out.println(product2.getName() + " 최종 금액: " + 
                          String.format("%,d", product2.calculateFinalPrice()) + "원");
        
        // 부가세 계산 테스트
        System.out.println(product1.getName() + " 부가세: " + 
                          String.format("%,d", product1.calculateVAT()) + "원");
        System.out.println(product2.getName() + " 부가세: " + 
                          String.format("%,d", product2.calculateVAT()) + "원");
        
        // 부가세 포함 금액 계산 테스트
        System.out.println(product1.getName() + " 부가세 포함 금액: " + 
                          String.format("%,d", product1.calculatePriceWithVAT()) + "원");
        System.out.println(product2.getName() + " 부가세 포함 금액: " + 
                          String.format("%,d", product2.calculatePriceWithVAT()) + "원");
        
        System.out.println("\n========== Getter 메서드 테스트 ==========");
        System.out.println("상품명: " + product1.getName());
        System.out.println("단가: " + String.format("%,d", product1.getPrice()) + "원");
        System.out.println("수량: " + product1.getQuantity() + "개");
        System.out.println("할인율: " + (product1.getDiscountRate() * 100) + "%");
        
        System.out.println("\n========== Setter 유효성 검사 테스트 ==========");
        // 잘못된 할인율 설정 시도 (음수)
        System.out.println("잘못된 할인율 설정 시도: -0.1");
        product1.setDiscountRate(-0.1);
        System.out.println("현재 할인율: " + (product1.getDiscountRate() * 100) + "%");
        
        // 잘못된 할인율 설정 시도 (1.0 초과)
        System.out.println("\n잘못된 할인율 설정 시도: 1.5");
        product1.setDiscountRate(1.5);
        System.out.println("현재 할인율: " + (product1.getDiscountRate() * 100) + "%");
        
        // 올바른 할인율 설정
        System.out.println("\n올바른 할인율 설정: 0.2 (20%)");
        product1.setDiscountRate(0.2);
        System.out.println("현재 할인율: " + (product1.getDiscountRate() * 100) + "%");
        System.out.println("변경된 최종 금액: " + 
                          String.format("%,d", product1.calculateFinalPrice()) + "원");
    }
}






