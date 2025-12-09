/**
 * 대입 연산자를 설명하는 예제
 * 기본 대입 연산자와 복합 대입 연산자를 보여줍니다
 */
public class AssignmentOperators {
    public static void main(String[] args) {
        System.out.println("=== 기본 대입 연산자 ===");
        int a = 10;
        System.out.println("a = 10: " + a);
        
        System.out.println("\n=== 복합 대입 연산자 ===");
        
        // += 연산자
        int num1 = 10;
        System.out.println("초기값: " + num1);
        num1 += 5;  // num1 = num1 + 5;
        System.out.println("num1 += 5: " + num1);  // 15
        
        // -= 연산자
        int num2 = 20;
        num2 -= 8;  // num2 = num2 - 8;
        System.out.println("num2 -= 8: " + num2);  // 12
        
        // *= 연산자
        int num3 = 5;
        num3 *= 3;  // num3 = num3 * 3;
        System.out.println("num3 *= 3: " + num3);  // 15
        
        // /= 연산자
        int num4 = 20;
        num4 /= 4;  // num4 = num4 / 4;
        System.out.println("num4 /= 4: " + num4);  // 5
        
        // %= 연산자
        int num5 = 17;
        num5 %= 5;  // num5 = num5 % 5;
        System.out.println("num5 %= 5: " + num5);  // 2
        
        System.out.println("\n=== 복합 대입 연산자 연속 사용 ===");
        int value = 10;
        System.out.println("초기값: " + value);
        
        value += 5;   // 15
        System.out.println("value += 5: " + value);
        
        value -= 3;   // 12
        System.out.println("value -= 3: " + value);
        
        value *= 2;   // 24
        System.out.println("value *= 2: " + value);
        
        value /= 4;   // 6
        System.out.println("value /= 4: " + value);
        
        value %= 4;   // 2
        System.out.println("value %= 4: " + value);
        
        System.out.println("\n최종값: " + value);
        
        System.out.println("\n=== 실용적인 예제 ===");
        
        // 합계 계산
        int sum = 0;
        sum += 10;  // sum = sum + 10;
        sum += 20;  // sum = sum + 20;
        sum += 30;  // sum = sum + 30;
        System.out.println("합계: " + sum);  // 60
        
        // 곱셈 누적
        int product = 1;
        product *= 2;  // product = product * 2;
        product *= 3;  // product = product * 3;
        product *= 4;  // product = product * 4;
        System.out.println("곱셈 결과: " + product);  // 24
    }
}

