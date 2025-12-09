/**
 * 메서드 오버로딩을 설명하는 예제
 * 같은 이름의 메서드를 여러 개 정의하는 방법을 보여줍니다
 */
public class MethodOverloading {
    // 정수 두 개를 더하는 메서드
    public int add(int a, int b) {
        System.out.println("add(int, int) 호출");
        return a + b;
    }
    
    // 정수 세 개를 더하는 메서드 (매개변수 개수 다름)
    public int add(int a, int b, int c) {
        System.out.println("add(int, int, int) 호출");
        return a + b + c;
    }
    
    // 실수 두 개를 더하는 메서드 (매개변수 타입 다름)
    public double add(double a, double b) {
        System.out.println("add(double, double) 호출");
        return a + b;
    }
    
    // 정수와 실수를 더하는 메서드
    public double add(int a, double b) {
        System.out.println("add(int, double) 호출");
        return a + b;
    }
    
    // 실수와 정수를 더하는 메서드
    public double add(double a, int b) {
        System.out.println("add(double, int) 호출");
        return a + b;
    }
    
    // 배열의 합을 계산하는 메서드
    public int add(int[] numbers) {
        System.out.println("add(int[]) 호출");
        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        return sum;
    }
    
    // 문자열을 연결하는 메서드
    public String add(String str1, String str2) {
        System.out.println("add(String, String) 호출");
        return str1 + str2;
    }
    
    public static void main(String[] args) {
        MethodOverloading obj = new MethodOverloading();
        
        System.out.println("=== 오버로딩 예제 ===\n");
        
        // 정수 두 개
        int result1 = obj.add(10, 20);
        System.out.println("결과: " + result1 + "\n");
        
        // 정수 세 개
        int result2 = obj.add(10, 20, 30);
        System.out.println("결과: " + result2 + "\n");
        
        // 실수 두 개
        double result3 = obj.add(10.5, 20.5);
        System.out.println("결과: " + result3 + "\n");
        
        // 정수와 실수
        double result4 = obj.add(10, 20.5);
        System.out.println("결과: " + result4 + "\n");
        
        // 실수와 정수
        double result5 = obj.add(10.5, 20);
        System.out.println("결과: " + result5 + "\n");
        
        // 배열
        int[] numbers = {1, 2, 3, 4, 5};
        int result6 = obj.add(numbers);
        System.out.println("결과: " + result6 + "\n");
        
        // 문자열
        String result7 = obj.add("Hello", " World");
        System.out.println("결과: " + result7 + "\n");
        
        System.out.println("=== 오버로딩의 장점 ===");
        System.out.println("같은 이름으로 다양한 타입의 인자를 받을 수 있습니다!");
    }
}

