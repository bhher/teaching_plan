import java.time.LocalDate;

/**
 * 메서드와 객체 역할 분리 테스트 클래스
 * 객체가 자신의 일을 스스로 하도록 설계된 예제들을 테스트
 */
public class MethodAndObjectTest {
    public static void main(String[] args) {
        System.out.println("========== 예제 1: 학생 점수 계산 ==========");
        testStudent();
        
        System.out.println("\n========== 예제 2: 회원 정보 출력 ==========");
        testMember();
        
        System.out.println("\n========== 예제 3: 상품 총금액 계산 ==========");
        testProduct();
    }
    
    // 예제 1: 학생 점수 계산
    public static void testStudent() {
        Student student1 = new Student("홍길동", 90, 85, 88);
        Student student2 = new Student("김철수", 75, 80, 70);
        
        // ✅ 올바른 방법: 객체가 자신의 정보를 출력
        student1.printInfo();
        System.out.println();
        student2.printReport();
        
        // 객체가 자신의 총점을 계산
        System.out.println("\n" + student1.getName() + "의 총점: " + student1.calculateTotal());
        System.out.println(student2.getName() + "의 등급: " + student2.calculateGrade());
    }
    
    // 예제 2: 회원 정보 출력
    public static void testMember() {
        Member member1 = new Member("이영희", "lee@example.com", 
                                   LocalDate.of(2023, 1, 1));
        Member member2 = new Member("박민수", "park@example.com", 
                                   LocalDate.of(2024, 6, 1));
        
        // ✅ 올바른 방법: 객체가 자신의 정보를 출력
        member1.printMemberInfo();
        System.out.println();
        member2.printSimpleInfo();
        System.out.println();
        member2.printWelcomeMessage();
    }
    
    // 예제 3: 상품 총금액 계산
    public static void testProduct() {
        Product product1 = new Product("노트북", 1000000, 2);
        product1.setDiscountRate(0.1);  // 10% 할인
        
        Product product2 = new Product("마우스", 25000, 3);
        product2.setDiscountRate(0.15);  // 15% 할인
        
        // ✅ 올바른 방법: 객체가 자신의 정보를 출력
        product1.printReceipt();
        System.out.println();
        product2.printProductInfo();
        
        // 객체가 자신의 금액을 계산
        System.out.println("\n노트북 최종 금액: " + String.format("%,d", product1.calculateFinalPrice()) + "원");
        System.out.println("마우스 최종 금액: " + String.format("%,d", product2.calculateFinalPrice()) + "원");
    }
}


