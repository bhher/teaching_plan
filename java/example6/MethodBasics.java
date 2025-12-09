/**
 * 메서드 기본을 설명하는 예제
 * 메서드의 개념, 구조, 호출 방법을 보여줍니다
 */
public class MethodBasics {
    // 반환값이 없는 메서드
    public void sayHello() {
        System.out.println("안녕하세요!");
    }
    
    // 매개변수가 있는 메서드
    public void greet(String name) {
        System.out.println("안녕하세요, " + name + "님!");
    }
    
    // 반환값이 있는 메서드
    public int add(int a, int b) {
        return a + b;
    }
    
    // 여러 매개변수와 반환값
    public double calculateAverage(int a, int b, int c) {
        return (a + b + c) / 3.0;
    }
    
    // boolean 반환 메서드
    public boolean isEven(int number) {
        return number % 2 == 0;
    }
    
    // String 반환 메서드
    public String getFullName(String firstName, String lastName) {
        return firstName + " " + lastName;
    }
    
    public static void main(String[] args) {
        MethodBasics obj = new MethodBasics();
        
        System.out.println("=== 반환값이 없는 메서드 ===");
        obj.sayHello();
        obj.greet("홍길동");
        
        System.out.println("\n=== 반환값이 있는 메서드 ===");
        int sum = obj.add(10, 20);
        System.out.println("10 + 20 = " + sum);
        
        double avg = obj.calculateAverage(10, 20, 30);
        System.out.println("평균: " + avg);
        
        System.out.println("\n=== boolean 반환 메서드 ===");
        boolean result1 = obj.isEven(10);
        boolean result2 = obj.isEven(7);
        System.out.println("10은 짝수인가? " + result1);
        System.out.println("7은 짝수인가? " + result2);
        
        System.out.println("\n=== String 반환 메서드 ===");
        String fullName = obj.getFullName("홍", "길동");
        System.out.println("전체 이름: " + fullName);
        
        System.out.println("\n=== 메서드 체이닝 ===");
        int result = obj.add(obj.add(5, 3), obj.add(2, 4));
        System.out.println("(5 + 3) + (2 + 4) = " + result);
    }
}

