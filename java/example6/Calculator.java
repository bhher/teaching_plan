/**
 * 계산기 클래스 예제
 * 메서드를 사용하여 계산기 기능을 구현합니다
 */
public class Calculator {
    // 덧셈
    public int add(int a, int b) {
        return a + b;
    }
    
    // 뺄셈
    public int subtract(int a, int b) {
        return a - b;
    }
    
    // 곱셈
    public int multiply(int a, int b) {
        return a * b;
    }
    
    // 나눗셈
    public double divide(int a, int b) {
        if (b == 0) {
            System.out.println("오류: 0으로 나눌 수 없습니다!");
            return 0;
        }
        return (double) a / b;
    }
    
    // 나머지
    public int modulo(int a, int b) {
        if (b == 0) {
            System.out.println("오류: 0으로 나눌 수 없습니다!");
            return 0;
        }
        return a % b;
    }
    
    // 제곱
    public int power(int base, int exponent) {
        int result = 1;
        for (int i = 0; i < exponent; i++) {
            result *= base;
        }
        return result;
    }
    
    // 절댓값
    public int abs(int number) {
        return number < 0 ? -number : number;
    }
    
    // 최댓값
    public int max(int a, int b) {
        return a > b ? a : b;
    }
    
    // 최솟값
    public int min(int a, int b) {
        return a < b ? a : b;
    }
    
    // 계산 결과 출력
    public void printResult(String operation, double result) {
        System.out.println(operation + " = " + result);
    }
    
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        
        System.out.println("=== 계산기 예제 ===\n");
        
        int a = 20;
        int b = 4;
        
        System.out.println("a = " + a + ", b = " + b + "\n");
        
        int sum = calc.add(a, b);
        calc.printResult("덧셈", sum);
        
        int diff = calc.subtract(a, b);
        calc.printResult("뺄셈", diff);
        
        int product = calc.multiply(a, b);
        calc.printResult("곱셈", product);
        
        double quotient = calc.divide(a, b);
        calc.printResult("나눗셈", quotient);
        
        int remainder = calc.modulo(a, b);
        calc.printResult("나머지", remainder);
        
        int power = calc.power(2, 5);
        calc.printResult("2의 5제곱", power);
        
        System.out.println("\n=== 추가 기능 ===");
        System.out.println("절댓값(-10): " + calc.abs(-10));
        System.out.println("최댓값(10, 20): " + calc.max(10, 20));
        System.out.println("최솟값(10, 20): " + calc.min(10, 20));
        
        System.out.println("\n=== 0으로 나누기 테스트 ===");
        calc.divide(10, 0);
        calc.modulo(10, 0);
    }
}

