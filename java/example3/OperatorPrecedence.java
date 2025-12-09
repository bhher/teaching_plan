/**
 * 연산자 우선순위를 설명하는 예제
 * 여러 연산자가 함께 사용될 때의 우선순위를 보여줍니다
 */
public class OperatorPrecedence {
    public static void main(String[] args) {
        System.out.println("=== 산술 연산자 우선순위 ===");
        
        // 곱셈/나눗셈이 덧셈/뺄셈보다 우선
        int result1 = 2 + 3 * 4;
        System.out.println("2 + 3 * 4 = " + result1);  // 14 (곱셈 먼저)
        
        int result2 = (2 + 3) * 4;
        System.out.println("(2 + 3) * 4 = " + result2);  // 20 (괄호 먼저)
        
        int result3 = 10 / 2 + 3;
        System.out.println("10 / 2 + 3 = " + result3);  // 8 (나눗셈 먼저)
        
        int result4 = 10 / (2 + 3);
        System.out.println("10 / (2 + 3) = " + result4);  // 2 (괄호 먼저)
        
        System.out.println("\n=== 복합 연산 우선순위 ===");
        
        int a = 5;
        int b = 3;
        int c = 2;
        
        // 곱셈, 나눗셈, 나머지가 같은 우선순위 (왼쪽에서 오른쪽으로)
        int result5 = a * b / c;
        System.out.println("a * b / c = " + result5);  // 7 (5*3=15, 15/2=7)
        
        int result6 = a + b * c;
        System.out.println("a + b * c = " + result6);  // 11 (곱셈 먼저)
        
        int result7 = (a + b) * c;
        System.out.println("(a + b) * c = " + result7);  // 16 (괄호 먼저)
        
        System.out.println("\n=== 비교 연산자와 논리 연산자 우선순위 ===");
        
        int x = 10;
        int y = 5;
        int z = 8;
        
        // 비교 연산자가 논리 연산자보다 우선
        boolean result8 = x > y && y < z;
        System.out.println("x > y && y < z = " + result8);  // true
        
        boolean result9 = x > y || y > z;
        System.out.println("x > y || y > z = " + result9);  // true
        
        // &&가 ||보다 우선
        boolean result10 = x > 5 && y < 10 || z > 10;
        System.out.println("x > 5 && y < 10 || z > 10 = " + result10);  // true
        
        boolean result11 = (x > 5 && y < 10) || z > 10;
        System.out.println("(x > 5 && y < 10) || z > 10 = " + result11);  // true
        
        System.out.println("\n=== 증감 연산자 우선순위 ===");
        
        int num1 = 5;
        int num2 = 10;
        
        // 증감 연산자가 산술 연산자보다 우선
        int result12 = ++num1 + num2;
        System.out.println("++num1 + num2 = " + result12);  // 16
        
        int num3 = 5;
        int num4 = 10;
        int result13 = num3++ + num4;
        System.out.println("num3++ + num4 = " + result13);  // 15
        System.out.println("num3 = " + num3);  // 6
        
        System.out.println("\n=== 대입 연산자 우선순위 ===");
        
        int value = 10;
        value += 5 * 2;  // value = value + (5 * 2)
        System.out.println("value += 5 * 2 = " + value);  // 20
        
        int value2 = 20;
        value2 -= 10 / 2;  // value2 = value2 - (10 / 2)
        System.out.println("value2 -= 10 / 2 = " + value2);  // 15
        
        System.out.println("\n=== 가독성을 위한 괄호 사용 (권장) ===");
        
        // 혼란스러운 코드
        int confusing = 10 + 5 * 2 - 3 / 1;
        System.out.println("혼란스러운 코드: 10 + 5 * 2 - 3 / 1 = " + confusing);
        
        // 명확한 코드 (괄호 사용)
        int clear = 10 + (5 * 2) - (3 / 1);
        System.out.println("명확한 코드: 10 + (5 * 2) - (3 / 1) = " + clear);
        
        // 더 명확하게
        int veryClear = 10 + ((5 * 2) - (3 / 1));
        System.out.println("더 명확한 코드: 10 + ((5 * 2) - (3 / 1)) = " + veryClear);
        
        System.out.println("\n=== 우선순위 요약 ===");
        System.out.println("1. 괄호 () - 최우선");
        System.out.println("2. 증감 연산자 ++, --");
        System.out.println("3. 산술 연산자 *, /, % → +, -");
        System.out.println("4. 비교 연산자 <, >, <=, >=");
        System.out.println("5. 등가 연산자 ==, !=");
        System.out.println("6. 논리 연산자 && → ||");
        System.out.println("7. 대입 연산자 =, +=, -= 등");
    }
}

