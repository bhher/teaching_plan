/**
 * 예제 6: 생성자 호출 순서
 * 업캐스팅 시 생성자와 초기화 블록의 호출 순서를 보여주는 예제
 */
class Parent {
    String parentField = "Parent Field";
    
    // 초기화 블록
    {
        System.out.println("1. Parent 초기화 블록 실행");
        System.out.println("   parentField: " + parentField);
    }
    
    // 생성자
    Parent() {
        System.out.println("2. Parent 생성자 실행");
        System.out.println("   parentField: " + parentField);
    }
    
    void parentMethod() {
        System.out.println("Parent 메서드 호출");
    }
}

class Child extends Parent {
    String childField = "Child Field";
    
    // 초기화 블록
    {
        System.out.println("3. Child 초기화 블록 실행");
        System.out.println("   childField: " + childField);
        System.out.println("   parentField (상속받음): " + parentField);
    }
    
    // 생성자
    Child() {
        // super()가 자동으로 호출됨
        System.out.println("4. Child 생성자 실행");
        System.out.println("   childField: " + childField);
        System.out.println("   parentField (상속받음): " + parentField);
    }
    
    @Override
    void parentMethod() {
        System.out.println("Child에서 오버라이딩된 메서드 호출");
    }
    
    void childMethod() {
        System.out.println("Child 고유 메서드 호출");
    }
}

public class Example6_ConstructorOrder {
    public static void main(String[] args) {
        System.out.println("=== 생성자 호출 순서 예제 ===\n");
        
        System.out.println("=== 업캐스팅으로 객체 생성 ===");
        Parent p = new Child();
        
        System.out.println("\n=== 메서드 호출 ===");
        p.parentMethod();  // 오버라이딩된 메서드 호출
        
        System.out.println("\n=== 필드 접근 ===");
        System.out.println("p.parentField: " + p.parentField);
        // System.out.println("p.childField: " + p.childField);  // 컴파일 오류
        
        System.out.println("\n=== 다운캐스팅 후 접근 ===");
        if (p instanceof Child) {
            Child c = (Child) p;
            System.out.println("c.parentField: " + c.parentField);
            System.out.println("c.childField: " + c.childField);
            c.childMethod();
        }
    }
}
