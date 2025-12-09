/**
 * static 메서드를 설명하는 예제
 * static 메서드의 특징과 사용법을 보여줍니다
 */
public class StaticMethods {
    // static 메서드: 객체 생성 없이 호출 가능
    public static int add(int a, int b) {
        return a + b;
    }
    
    public static double calculateCircleArea(double radius) {
        return Math.PI * radius * radius;
    }
    
    public static double calculateCircleCircumference(double radius) {
        return 2 * Math.PI * radius;
    }
    
    // 절댓값 계산
    public static int abs(int number) {
        return number < 0 ? -number : number;
    }
    
    // 최댓값
    public static int max(int a, int b) {
        return a > b ? a : b;
    }
    
    // 최솟값
    public static int min(int a, int b) {
        return a < b ? a : b;
    }
    
    // 팩토리얼 계산
    public static long factorial(int n) {
        if (n <= 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }
    
    // 소수 판별
    public static boolean isPrime(int number) {
        if (number < 2) {
            return false;
        }
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
    
    // 문자열을 역순으로 변환
    public static String reverse(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str);
        return sb.reverse().toString();
    }
    
    // 같은 클래스 내에서는 클래스명 생략 가능
    public static void printMessage(String message) {
        System.out.println("메시지: " + message);
    }
    
    public static void main(String[] args) {
        System.out.println("=== static 메서드 호출 ===");
        
        // 클래스명으로 직접 호출 (객체 생성 불필요)
        int sum = StaticMethods.add(10, 20);
        System.out.println("10 + 20 = " + sum);
        
        // 같은 클래스 내에서는 클래스명 생략 가능
        int sum2 = add(5, 3);
        System.out.println("5 + 3 = " + sum2);
        
        System.out.println("\n=== 원의 넓이와 둘레 ===");
        double radius = 5.0;
        double area = calculateCircleArea(radius);
        double circumference = calculateCircleCircumference(radius);
        System.out.println("반지름: " + radius);
        System.out.println("넓이: " + area);
        System.out.println("둘레: " + circumference);
        
        System.out.println("\n=== 수학 유틸리티 메서드 ===");
        System.out.println("abs(-10): " + abs(-10));
        System.out.println("max(10, 20): " + max(10, 20));
        System.out.println("min(10, 20): " + min(10, 20));
        System.out.println("5! = " + factorial(5));
        
        System.out.println("\n=== 소수 판별 ===");
        int[] testNumbers = {2, 3, 4, 5, 17, 20};
        for (int num : testNumbers) {
            System.out.println(num + "은(는) 소수인가? " + isPrime(num));
        }
        
        System.out.println("\n=== 문자열 처리 ===");
        String original = "Hello";
        String reversed = reverse(original);
        System.out.println("원본: " + original);
        System.out.println("역순: " + reversed);
        
        System.out.println("\n=== static 메서드의 특징 ===");
        System.out.println("1. 객체 생성 없이 호출 가능");
        System.out.println("2. 클래스명.메서드명() 형태로 호출");
        System.out.println("3. 인스턴스 변수에 접근 불가");
        System.out.println("4. 유틸리티 함수에 적합");
    }
}

