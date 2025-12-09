/**
 * Method Area를 설명하는 예제
 * 클래스 정보, 정적 변수, 상수 풀을 보여줍니다
 */
public class MethodAreaExample {
    // 정적 변수: Method Area에 저장
    static int staticCount = 0;
    static String staticName = "MethodAreaExample";
    
    // 상수: Method Area에 저장
    final static double PI = 3.14159;
    final static String COMPANY = "ABC Corp";
    
    // 인스턴스 변수: Heap에 저장 (객체 생성 시)
    String instanceName;
    int instanceValue;
    
    public MethodAreaExample(String name, int value) {
        this.instanceName = name;
        this.instanceValue = value;
        staticCount++;  // Method Area의 변수 수정
    }
    
    // 메서드: Method Area에 저장
    public static void staticMethod() {
        System.out.println("정적 메서드 호출");
        System.out.println("staticCount: " + staticCount);
    }
    
    public void instanceMethod() {
        System.out.println("인스턴스 메서드 호출");
        System.out.println("instanceName: " + instanceName);
    }
    
    public static void main(String[] args) {
        System.out.println("=== Method Area 예제 ===\n");
        
        // 정적 변수 접근 (객체 생성 없이)
        System.out.println("--- 정적 변수 (Method Area) ---");
        System.out.println("staticCount: " + MethodAreaExample.staticCount);
        System.out.println("staticName: " + MethodAreaExample.staticName);
        System.out.println("PI: " + MethodAreaExample.PI);
        System.out.println("COMPANY: " + MethodAreaExample.COMPANY);
        
        // 정적 메서드 호출 (객체 생성 없이)
        System.out.println("\n--- 정적 메서드 (Method Area) ---");
        MethodAreaExample.staticMethod();
        
        // 객체 생성
        System.out.println("\n--- 객체 생성 ---");
        MethodAreaExample obj1 = new MethodAreaExample("객체1", 10);
        MethodAreaExample obj2 = new MethodAreaExample("객체2", 20);
        
        System.out.println("객체 생성 후 staticCount: " + MethodAreaExample.staticCount);
        
        // 인스턴스 변수 접근
        System.out.println("\n--- 인스턴스 변수 (Heap) ---");
        System.out.println("obj1.instanceName: " + obj1.instanceName);
        System.out.println("obj2.instanceName: " + obj2.instanceName);
        
        // 상수 풀 예제
        System.out.println("\n--- 상수 풀 (Method Area) ---");
        String str1 = "Hello";  // 상수 풀에 저장
        String str2 = "Hello";  // 같은 상수 풀의 객체 참조
        String str3 = new String("Hello");  // 힙에 새 객체 생성
        
        System.out.println("str1 == str2: " + (str1 == str2));  // true (같은 상수 풀)
        System.out.println("str1 == str3: " + (str1 == str3));  // false (다른 객체)
        
        System.out.println("\n=== Method Area의 특징 ===");
        System.out.println("1. 클래스 정보 저장");
        System.out.println("2. 정적 변수 (static) 저장");
        System.out.println("3. 상수 풀 (문자열 리터럴 등) 저장");
        System.out.println("4. 메서드의 바이트코드 저장");
        System.out.println("5. 모든 스레드가 공유");
        System.out.println("6. 프로그램 시작 시 생성, 종료 시 제거");
        
        System.out.println("\n--- 메모리 구조 ---");
        System.out.println("Method Area:");
        System.out.println("  - 클래스 정보");
        System.out.println("  - staticCount, staticName");
        System.out.println("  - PI, COMPANY");
        System.out.println("  - staticMethod(), instanceMethod() 코드");
        System.out.println("  - \"Hello\" (상수 풀)");
        System.out.println("\nHeap:");
        System.out.println("  - obj1 객체 (instanceName, instanceValue)");
        System.out.println("  - obj2 객체 (instanceName, instanceValue)");
        System.out.println("  - str3 객체 (new String)");
    }
}

