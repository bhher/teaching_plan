/**
 * 접근제어자를 설명하는 예제
 * public, private, default 접근제어자의 차이를 보여줍니다
 */
public class AccessModifier {
    // public: 어디서든 접근 가능
    public String publicField = "public 필드";
    
    // private: 같은 클래스 내에서만 접근 가능
    private String privateField = "private 필드";
    
    // default (접근제어자 없음): 같은 패키지에서만 접근 가능
    String defaultField = "default 필드";
    
    // public 메서드
    public void publicMethod() {
        System.out.println("public 메서드 호출");
    }
    
    // private 메서드
    private void privateMethod() {
        System.out.println("private 메서드 호출");
    }
    
    // default 메서드
    void defaultMethod() {
        System.out.println("default 메서드 호출");
    }
    
    // 같은 클래스 내에서는 모든 접근제어자 사용 가능
    public void testAccess() {
        System.out.println("\n=== 같은 클래스 내에서 접근 ===");
        System.out.println("publicField: " + publicField);
        System.out.println("privateField: " + privateField);  // 접근 가능
        System.out.println("defaultField: " + defaultField);
        
        publicMethod();
        privateMethod();  // 접근 가능
        defaultMethod();
    }
    
    public static void main(String[] args) {
        AccessModifier obj = new AccessModifier();
        
        System.out.println("=== 접근제어자 예제 ===\n");
        
        // public 접근
        System.out.println("--- public 접근 ---");
        System.out.println(obj.publicField);
        obj.publicMethod();
        
        // private 접근 (같은 클래스 내에서만 가능)
        // System.out.println(obj.privateField);  // 다른 클래스에서는 오류!
        // obj.privateMethod();  // 다른 클래스에서는 오류!
        
        // 같은 클래스 내에서 모든 접근제어자 사용
        obj.testAccess();
        
        System.out.println("\n=== 접근제어자 비교 ===");
        System.out.println("public: 어디서든 접근 가능");
        System.out.println("private: 같은 클래스 내에서만 접근 가능");
        System.out.println("default: 같은 패키지에서만 접근 가능");
        System.out.println("protected: 같은 패키지 + 상속 관계에서 접근 가능");
    }
}

