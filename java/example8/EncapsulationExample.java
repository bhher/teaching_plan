/**
 * 캡슐화를 설명하는 예제
 * private 필드와 getter/setter를 통한 데이터 보호를 보여줍니다
 */
public class EncapsulationExample {
    // private 필드: 외부에서 직접 접근 불가
    private String name;
    private int age;
    private String email;
    private double salary;
    
    // 생성자
    public EncapsulationExample(String name, int age, String email) {
        this.name = name;
        this.setAge(age);  // setter를 통해 유효성 검사
        this.setEmail(email);
        this.salary = 0.0;
    }
    
    // Getter 메서드
    public String getName() {
        return name;
    }
    
    public int getAge() {
        return age;
    }
    
    public String getEmail() {
        return email;
    }
    
    public double getSalary() {
        return salary;
    }
    
    // Setter 메서드 (유효성 검사 포함)
    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        } else {
            System.out.println("이름은 비어있을 수 없습니다.");
        }
    }
    
    public void setAge(int age) {
        if (age >= 0 && age <= 150) {
            this.age = age;
        } else {
            System.out.println("유효하지 않은 나이입니다. (0-150)");
        }
    }
    
    public void setEmail(String email) {
        if (email != null && email.contains("@")) {
            this.email = email;
        } else {
            System.out.println("유효하지 않은 이메일 주소입니다.");
        }
    }
    
    public void setSalary(double salary) {
        if (salary >= 0) {
            this.salary = salary;
        } else {
            System.out.println("급여는 0 이상이어야 합니다.");
        }
    }
    
    // 정보 출력
    public void displayInfo() {
        System.out.println("이름: " + name);
        System.out.println("나이: " + age);
        System.out.println("이메일: " + email);
        System.out.println("급여: " + salary);
    }
    
    public static void main(String[] args) {
        System.out.println("=== 캡슐화 예제 ===\n");
        
        EncapsulationExample person = new EncapsulationExample("홍길동", 25, "hong@example.com");
        
        System.out.println("--- 초기 정보 ---");
        person.displayInfo();
        
        System.out.println("\n--- Getter 사용 ---");
        System.out.println("이름: " + person.getName());
        System.out.println("나이: " + person.getAge());
        
        System.out.println("\n--- Setter 사용 (유효한 값) ---");
        person.setName("김철수");
        person.setAge(30);
        person.setSalary(5000000);
        person.displayInfo();
        
        System.out.println("\n--- Setter 사용 (유효하지 않은 값) ---");
        person.setAge(-5);      // 유효성 검사 실패
        person.setEmail("invalid");  // 유효성 검사 실패
        person.setSalary(-1000);     // 유효성 검사 실패
        
        System.out.println("\n=== 캡슐화의 장점 ===");
        System.out.println("1. 데이터 보호: private 필드로 직접 접근 방지");
        System.out.println("2. 유효성 검사: setter에서 데이터 검증");
        System.out.println("3. 유지보수 용이: 필드 변경 시 getter/setter만 수정");
    }
}

