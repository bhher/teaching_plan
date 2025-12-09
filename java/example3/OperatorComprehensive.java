/**
 * 연산자 종합 예제
 * 모든 연산자를 함께 사용하는 실전 예제
 */
public class OperatorComprehensive {
    public static void main(String[] args) {
        System.out.println("=== 연산자 종합 예제 ===\n");
        
        // 초기값 설정
        int a = 10;
        int b = 3;
        int c = 5;
        
        System.out.println("초기값: a = " + a + ", b = " + b + ", c = " + c);
        
        // 산술 연산
        System.out.println("\n--- 산술 연산 ---");
        int sum = a + b;
        int diff = a - b;
        int prod = a * b;
        int quot = a / b;
        int mod = a % b;
        
        System.out.println("a + b = " + sum);
        System.out.println("a - b = " + diff);
        System.out.println("a * b = " + prod);
        System.out.println("a / b = " + quot);
        System.out.println("a % b = " + mod);
        
        // 비교 연산
        System.out.println("\n--- 비교 연산 ---");
        System.out.println("a > b: " + (a > b));
        System.out.println("a < b: " + (a < b));
        System.out.println("a >= b: " + (a >= b));
        System.out.println("a <= b: " + (a <= b));
        System.out.println("a == b: " + (a == b));
        System.out.println("a != b: " + (a != b));
        
        // 논리 연산
        System.out.println("\n--- 논리 연산 ---");
        boolean condition1 = a > b;
        boolean condition2 = b < c;
        
        System.out.println("condition1 (a > b): " + condition1);
        System.out.println("condition2 (b < c): " + condition2);
        System.out.println("condition1 && condition2: " + (condition1 && condition2));
        System.out.println("condition1 || condition2: " + (condition1 || condition2));
        System.out.println("!condition1: " + (!condition1));
        
        // 증감 연산
        System.out.println("\n--- 증감 연산 ---");
        int x = 5;
        System.out.println("초기값 x = " + x);
        System.out.println("++x = " + (++x) + " (x는 " + x + ")");
        System.out.println("x++ = " + (x++) + " (x는 " + x + ")");
        System.out.println("--x = " + (--x) + " (x는 " + x + ")");
        System.out.println("x-- = " + (x--) + " (x는 " + x + ")");
        
        // 복합 대입 연산
        System.out.println("\n--- 복합 대입 연산 ---");
        int value = 10;
        System.out.println("초기값: " + value);
        
        value += 5;
        System.out.println("value += 5: " + value);
        
        value -= 3;
        System.out.println("value -= 3: " + value);
        
        value *= 2;
        System.out.println("value *= 2: " + value);
        
        value /= 4;
        System.out.println("value /= 4: " + value);
        
        value %= 3;
        System.out.println("value %= 3: " + value);
        
        // 복합 연산 예제
        System.out.println("\n--- 복합 연산 예제 ---");
        int score1 = 85;
        int score2 = 90;
        int score3 = 78;
        
        // 평균 계산
        double average = (score1 + score2 + score3) / 3.0;
        System.out.println("점수: " + score1 + ", " + score2 + ", " + score3);
        System.out.println("평균: " + average);
        
        // 합격 여부 판단 (60점 이상)
        boolean isPass = average >= 60;
        System.out.println("합격 여부 (>= 60): " + isPass);
        
        // 우수 여부 판단 (90점 이상)
        boolean isExcellent = average >= 90;
        System.out.println("우수 여부 (>= 90): " + isExcellent);
        
        // 모든 점수가 80점 이상인지 확인
        boolean allAbove80 = score1 >= 80 && score2 >= 80 && score3 >= 80;
        System.out.println("모든 점수가 80점 이상: " + allAbove80);
        
        // 하나라도 90점 이상인지 확인
        boolean anyAbove90 = score1 >= 90 || score2 >= 90 || score3 >= 90;
        System.out.println("하나라도 90점 이상: " + anyAbove90);
        
        System.out.println("\n=== 예제 완료 ===");
    }
}

