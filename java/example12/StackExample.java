/**
 * Stack 영역을 설명하는 예제
 * 스택 프레임과 지역 변수의 메모리 저장을 보여줍니다
 */
public class StackExample {
    // 정적 변수: Method Area에 저장
    static int staticVar = 100;
    
    // 인스턴스 변수: Heap에 저장 (객체 생성 시)
    int instanceVar = 200;
    
    public static void method1() {
        // 지역 변수: Stack에 저장
        int localVar1 = 10;
        int localVar2 = 20;
        
        System.out.println("=== method1 스택 프레임 ===");
        System.out.println("localVar1: " + localVar1 + " (스택에 저장)");
        System.out.println("localVar2: " + localVar2 + " (스택에 저장)");
        System.out.println("staticVar: " + staticVar + " (메서드 영역에서 읽음)");
        
        method2();
        
        System.out.println("\nmethod1 종료 - 스택 프레임 제거");
    }
    
    public static void method2() {
        // 지역 변수: Stack에 저장
        int localVar3 = 30;
        String localStr = "Hello";  // 참조는 스택, 객체는 힙
        
        System.out.println("\n=== method2 스택 프레임 ===");
        System.out.println("localVar3: " + localVar3 + " (스택에 저장)");
        System.out.println("localStr: " + localStr + " (스택에 참조, 힙에 객체)");
        
        method3();
        
        System.out.println("\nmethod2 종료 - 스택 프레임 제거");
    }
    
    public static void method3() {
        // 지역 변수: Stack에 저장
        int localVar4 = 40;
        
        System.out.println("\n=== method3 스택 프레임 ===");
        System.out.println("localVar4: " + localVar4 + " (스택에 저장)");
        System.out.println("\nmethod3 종료 - 스택 프레임 제거");
    }
    
    public static void main(String[] args) {
        System.out.println("=== Stack 영역 예제 ===\n");
        
        // 지역 변수: Stack에 저장
        int mainVar = 5;
        String mainStr = "Main";
        
        System.out.println("=== main 스택 프레임 ===");
        System.out.println("mainVar: " + mainVar + " (스택에 저장)");
        System.out.println("mainStr: " + mainStr + " (스택에 참조, 힙에 객체)");
        System.out.println("staticVar: " + staticVar + " (메서드 영역에서 읽음)");
        
        System.out.println("\n--- 메서드 호출 순서 ---");
        method1();
        
        System.out.println("\n=== 스택의 특징 ===");
        System.out.println("1. LIFO (Last In First Out): 마지막에 들어온 것이 먼저 나감");
        System.out.println("2. 메서드 호출 시 스택 프레임 생성");
        System.out.println("3. 메서드 종료 시 스택 프레임 자동 제거");
        System.out.println("4. 지역 변수는 스택에 저장");
        System.out.println("5. 각 스레드마다 독립적인 스택");
        
        System.out.println("\n--- 스택 프레임 구조 (method1 호출 시) ---");
        System.out.println("┌─────────────────┐");
        System.out.println("│ method3 스택     │ ← 가장 위 (최근 호출)");
        System.out.println("├─────────────────┤");
        System.out.println("│ method2 스택     │");
        System.out.println("├─────────────────┤");
        System.out.println("│ method1 스택     │");
        System.out.println("├─────────────────┤");
        System.out.println("│ main 스택        │ ← 가장 아래 (처음 호출)");
        System.out.println("└─────────────────┘");
    }
}

