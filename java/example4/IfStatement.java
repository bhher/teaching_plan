/**
 * if 문을 설명하는 예제
 * if, if-else, if-else if-else를 보여줍니다
 */
public class IfStatement {
    public static void main(String[] args) {
        System.out.println("=== 기본 if 문 ===");
        int score = 85;
        
        if (score >= 60) {
            System.out.println("합격입니다!");
        }
        
        System.out.println("\n=== if-else 문 ===");
        int age = 20;
        
        if (age >= 18) {
            System.out.println("성인입니다.");
        } else {
            System.out.println("미성년자입니다.");
        }
        
        System.out.println("\n=== if-else if-else 문 ===");
        int score2 = 85;
        
        if (score2 >= 90) {
            System.out.println("A등급");
        } else if (score2 >= 80) {
            System.out.println("B등급");
        } else if (score2 >= 70) {
            System.out.println("C등급");
        } else if (score2 >= 60) {
            System.out.println("D등급");
        } else {
            System.out.println("F등급");
        }
        
        System.out.println("\n=== 중첩 if 문 ===");
        int age2 = 20;
        boolean hasLicense = true;
        
        if (age2 >= 18) {
            if (hasLicense) {
                System.out.println("운전 가능합니다.");
            } else {
                System.out.println("면허가 필요합니다.");
            }
        } else {
            System.out.println("미성년자는 운전할 수 없습니다.");
        }
        
        System.out.println("\n=== 삼항 연산자 ===");
        int age3 = 20;
        String status = (age3 >= 18) ? "성인" : "미성년자";
        System.out.println("상태: " + status);
        
        int score3 = 85;
        String result = (score3 >= 60) ? "합격" : "불합격";
        System.out.println("결과: " + result);
        
        // 중첩 삼항 연산자
        int score4 = 85;
        String grade = (score4 >= 90) ? "A" : (score4 >= 80) ? "B" : "C";
        System.out.println("등급: " + grade);
    }
}

