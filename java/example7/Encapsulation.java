/**
 * 캡슐화를 설명하는 예제
 * private 필드와 getter/setter 메서드를 보여줍니다
 */
public class Encapsulation {
    // private 필드: 외부에서 직접 접근 불가
    private String name;
    private int age;
    private String major;
    private double gpa;  // 학점
    
    // 생성자
    public Encapsulation(String name, int age, String major) {
        this.name = name;
        this.age = age;
        this.major = major;
        this.gpa = 0.0;
    }
    
    // Getter 메서드: 필드 값을 읽는 메서드
    public String getName() {
        return name;
    }
    
    public int getAge() {
        return age;
    }
    
    public String getMajor() {
        return major;
    }
    
    public double getGpa() {
        return gpa;
    }
    
    // Setter 메서드: 필드 값을 설정하는 메서드
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
    
    public void setMajor(String major) {
        this.major = major;
    }
    
    public void setGpa(double gpa) {
        if (gpa >= 0.0 && gpa <= 4.5) {
            this.gpa = gpa;
        } else {
            System.out.println("유효하지 않은 학점입니다. (0.0-4.5)");
        }
    }
    
    // 정보 출력 메서드
    public void displayInfo() {
        System.out.println("이름: " + name);
        System.out.println("나이: " + age);
        System.out.println("전공: " + major);
        System.out.println("학점: " + gpa);
    }
    
    public static void main(String[] args) {
        System.out.println("=== 캡슐화 예제 ===\n");
        
        Encapsulation student = new Encapsulation("홍길동", 20, "컴퓨터공학");
        
        System.out.println("--- 초기 정보 ---");
        student.displayInfo();
        
        System.out.println("\n--- Getter 사용 ---");
        System.out.println("이름: " + student.getName());
        System.out.println("나이: " + student.getAge());
        System.out.println("학점: " + student.getGpa());
        
        System.out.println("\n--- Setter 사용 (유효한 값) ---");
        student.setName("김철수");
        student.setAge(21);
        student.setGpa(3.8);
        student.displayInfo();
        
        System.out.println("\n--- Setter 사용 (유효하지 않은 값) ---");
        student.setAge(-5);  // 유효성 검사 실패
        student.setGpa(5.0);  // 유효성 검사 실패
        student.setName("");  // 유효성 검사 실패
        
        System.out.println("\n=== 캡슐화의 장점 ===");
        System.out.println("1. 데이터 보호: private 필드로 직접 접근 방지");
        System.out.println("2. 유효성 검사: setter에서 데이터 검증 가능");
        System.out.println("3. 유지보수 용이: 필드 변경 시 getter/setter만 수정");
        System.out.println("4. 접근 제어: 읽기 전용(getter만) 또는 쓰기 전용(setter만) 가능");
    }
}

