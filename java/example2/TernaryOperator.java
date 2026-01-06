/**
 * 삼항 연산자를 설명하는 예제
 * 조건 ? 값1 : 값2 형태의 연산자를 보여줍니다
 */
public class TernaryOperator {
    public static void main(String[] args) {
        System.out.println("=== 기본 삼항 연산자 ===");
        
        // 가장 기본적인 형태
        int score = 85;
        String result = (score >= 60) ? "합격" : "불합격";
        System.out.println("점수: " + score + " → 결과: " + result);
        
        // 다른 점수로 테스트
        score = 45;
        result = (score >= 60) ? "합격" : "불합격";
        System.out.println("점수: " + score + " → 결과: " + result);
        
        System.out.println("\n=== 숫자 값 반환 ===");
        
        // 두 수 중 큰 값 찾기
        int a = 10;
        int b = 20;
        int max = (a > b) ? a : b;
        System.out.println("a: " + a + ", b: " + b + " → 최대값: " + max);
        
        // 절댓값 구하기
        int num = -15;
        int abs = (num < 0) ? -num : num;
        System.out.println("숫자: " + num + " → 절댓값: " + abs);
        
        System.out.println("\n=== 등급 판정 예제 ===");
        
        // 점수에 따른 등급 부여
        int gradeScore = 92;
        String grade = (gradeScore >= 90) ? "A" : 
                      (gradeScore >= 80) ? "B" : 
                      (gradeScore >= 70) ? "C" : "F";
        System.out.println("점수: " + gradeScore + " → 등급: " + grade);
        
        gradeScore = 75;
        grade = (gradeScore >= 90) ? "A" : 
               (gradeScore >= 80) ? "B" : 
               (gradeScore >= 70) ? "C" : "F";
        System.out.println("점수: " + gradeScore + " → 등급: " + grade);
        
        System.out.println("\n=== 짝수/홀수 판별 ===");
        
        int number = 7;
        String parity = (number % 2 == 0) ? "짝수" : "홀수";
        System.out.println("숫자: " + number + " → " + parity);
        
        number = 12;
        parity = (number % 2 == 0) ? "짝수" : "홀수";
        System.out.println("숫자: " + number + " → " + parity);
        
        System.out.println("\n=== 변수 초기화에 활용 ===");
        
        // 조건에 따라 초기값 설정
        int age = 20;
        String status = (age >= 19) ? "성인" : "미성년자";
        System.out.println("나이: " + age + " → 상태: " + status);
        
        // 기본값 설정
        String name = null;
        String displayName = (name != null) ? name : "이름 없음";
        System.out.println("이름: " + displayName);
        
        System.out.println("\n=== 계산식에 활용 ===");
        
        // 할인율 계산 (VIP 고객 20% 할인, 일반 10% 할인)
        boolean isVip = true;
        int price = 10000;
        double discount = isVip ? 0.2 : 0.1;
        int finalPrice = (int)(price * (1 - discount));
        System.out.println("원가: " + price + "원, VIP: " + isVip + 
                         " → 최종가격: " + finalPrice + "원");
        
        isVip = false;
        finalPrice = (int)(price * (1 - (isVip ? 0.2 : 0.1)));
        System.out.println("원가: " + price + "원, VIP: " + isVip + 
                         " → 최종가격: " + finalPrice + "원");
    }
}



