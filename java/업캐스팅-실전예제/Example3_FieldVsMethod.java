/**
 * 예제 3: 필드와 메서드의 차이
 * 업캐스팅 시 필드는 정적 바인딩, 메서드는 동적 바인딩되는 것을 보여주는 예제
 */
class Parent {
    String name = "Parent";
    int value = 100;
    
    String getName() {
        return name;
    }
    
    int getValue() {
        return value;
    }
    
    void show() {
        System.out.println("Parent show() - name: " + name + ", value: " + value);
    }
}

class Child extends Parent {
    String name = "Child";  // 필드 숨김 (Field Hiding)
    int value = 200;
    
    @Override
    String getName() {
        return name;  // Child의 name 반환
    }
    
    @Override
    int getValue() {
        return value;  // Child의 value 반환
    }
    
    @Override
    void show() {
        System.out.println("Child show() - name: " + name + ", value: " + value);
    }
    
    void showParentField() {
        System.out.println("부모의 name: " + super.name);
        System.out.println("부모의 value: " + super.value);
    }
}

public class Example3_FieldVsMethod {
    public static void main(String[] args) {
        System.out.println("=== 필드와 메서드의 차이 예제 ===\n");
        
        // 업캐스팅
        Parent p = new Child();
        
        System.out.println("=== 필드 접근 (정적 바인딩) ===");
        System.out.println("p.name: " + p.name);      // "Parent" (참조 변수 타입 기준)
        System.out.println("p.value: " + p.value);   // 100 (참조 변수 타입 기준)
        
        System.out.println("\n=== 메서드 호출 (동적 바인딩) ===");
        System.out.println("p.getName(): " + p.getName());    // "Child" (실제 객체 타입 기준)
        System.out.println("p.getValue(): " + p.getValue());  // 200 (실제 객체 타입 기준)
        p.show();  // Child의 show() 호출
        
        System.out.println("\n=== 다운캐스팅 후 접근 ===");
        if (p instanceof Child) {
            Child c = (Child) p;
            System.out.println("c.name: " + c.name);      // "Child"
            System.out.println("c.value: " + c.value);   // 200
            c.showParentField();  // 부모 필드 접근
        }
        
        System.out.println("\n=== 직접 Child 객체 생성 ===");
        Child child = new Child();
        System.out.println("child.name: " + child.name);      // "Child"
        System.out.println("child.value: " + child.value);   // 200
        child.show();
    }
}
