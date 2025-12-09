/**
 * 생성자를 설명하는 예제
 * 기본 생성자, 매개변수가 있는 생성자, 생성자 오버로딩을 보여줍니다
 */
public class ConstructorExample {
    String name;
    int age;
    String major;
    
    // 생성자 1: 모든 필드 초기화
    public ConstructorExample(String name, int age, String major) {
        System.out.println("생성자 1 호출: 모든 필드 초기화");
        this.name = name;
        this.age = age;
        this.major = major;
    }
    
    // 생성자 2: 이름과 나이만 초기화
    public ConstructorExample(String name, int age) {
        System.out.println("생성자 2 호출: 이름과 나이만 초기화");
        this.name = name;
        this.age = age;
        this.major = "미정";
    }
    
    // 생성자 3: 이름만 초기화
    public ConstructorExample(String name) {
        System.out.println("생성자 3 호출: 이름만 초기화");
        this.name = name;
        this.age = 0;
        this.major = "미정";
    }
    
    // 생성자 4: 기본 생성자
    public ConstructorExample() {
        System.out.println("기본 생성자 호출");
        this.name = "이름 없음";
        this.age = 0;
        this.major = "미정";
    }
    
    // 정보 출력 메서드
    public void displayInfo() {
        System.out.println("이름: " + name + ", 나이: " + age + ", 전공: " + major);
    }
    
    public static void main(String[] args) {
        System.out.println("=== 생성자 예제 ===\n");
        
        // 생성자 1 사용
        System.out.println("--- 생성자 1 ---");
        ConstructorExample student1 = new ConstructorExample("홍길동", 20, "컴퓨터공학");
        student1.displayInfo();
        
        // 생성자 2 사용
        System.out.println("\n--- 생성자 2 ---");
        ConstructorExample student2 = new ConstructorExample("김철수", 21);
        student2.displayInfo();
        
        // 생성자 3 사용
        System.out.println("\n--- 생성자 3 ---");
        ConstructorExample student3 = new ConstructorExample("이영희");
        student3.displayInfo();
        
        // 기본 생성자 사용
        System.out.println("\n--- 기본 생성자 ---");
        ConstructorExample student4 = new ConstructorExample();
        student4.displayInfo();
        
        System.out.println("\n=== 생성자 오버로딩 ===");
        System.out.println("같은 이름의 생성자를 여러 개 정의할 수 있습니다.");
        System.out.println("매개변수의 개수나 타입이 달라야 합니다.");
    }
}

