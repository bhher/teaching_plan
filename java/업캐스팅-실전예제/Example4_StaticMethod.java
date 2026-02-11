/**
 * 예제 4: static 메서드와 업캐스팅
 * static 메서드는 오버라이딩되지 않고 메서드 숨김이 발생함을 보여주는 예제
 */
class Parent {
    static String className = "Parent";
    String instanceName = "Parent Instance";
    
    // static 메서드
    static void staticMethod() {
        System.out.println("Parent staticMethod()");
    }
    
    // 인스턴스 메서드
    void instanceMethod() {
        System.out.println("Parent instanceMethod()");
    }
    
    // static 필드 접근 메서드
    static String getClassName() {
        return className;
    }
    
    // 인스턴스 필드 접근 메서드
    String getInstanceName() {
        return instanceName;
    }
}

class Child extends Parent {
    static String className = "Child";  // static 필드 숨김
    String instanceName = "Child Instance";
    
    // static 메서드 숨김 (오버라이딩 아님!)
    static void staticMethod() {
        System.out.println("Child staticMethod()");
    }
    
    // 인스턴스 메서드 오버라이딩
    @Override
    void instanceMethod() {
        System.out.println("Child instanceMethod()");
    }
    
    // static 메서드 숨김
    static String getClassName() {
        return className;
    }
    
    // 인스턴스 메서드 오버라이딩
    @Override
    String getInstanceName() {
        return instanceName;
    }
}

public class Example4_StaticMethod {
    public static void main(String[] args) {
        System.out.println("=== static 메서드와 업캐스팅 예제 ===\n");
        
        // 업캐스팅
        Parent p = new Child();
        
        System.out.println("=== static 필드 접근 ===");
        System.out.println("Parent.className: " + Parent.className);  // "Parent"
        System.out.println("Child.className: " + Child.className);    // "Child"
        System.out.println("p.className: " + p.className);           // "Parent" (참조 변수 타입 기준)
        
        System.out.println("\n=== static 메서드 호출 ===");
        Parent.staticMethod();  // "Parent staticMethod()"
        Child.staticMethod();   // "Child staticMethod()"
        p.staticMethod();       // "Parent staticMethod()" (참조 변수 타입 기준!)
        
        System.out.println("\n=== static 메서드로 필드 접근 ===");
        System.out.println("Parent.getClassName(): " + Parent.getClassName());  // "Parent"
        System.out.println("Child.getClassName(): " + Child.getClassName());    // "Child"
        System.out.println("p.getClassName(): " + p.getClassName());            // "Parent" (참조 변수 타입 기준!)
        
        System.out.println("\n=== 인스턴스 메서드 호출 (동적 바인딩) ===");
        p.instanceMethod();  // "Child instanceMethod()" (실제 객체 타입 기준)
        System.out.println("p.getInstanceName(): " + p.getInstanceName());  // "Child Instance"
        
        System.out.println("\n=== 직접 Child 객체 생성 ===");
        Child c = new Child();
        c.staticMethod();  // "Child staticMethod()"
        c.instanceMethod(); // "Child instanceMethod()"
    }
}
