/**
 * 클래스와 객체를 설명하는 예제
 * 클래스 정의, 객체 생성, 필드와 메서드 사용을 보여줍니다
 */
public class ClassAndObject {
    // 필드 (Field) - 객체의 속성
    String name;
    int age;
    String major;
    
    // 메서드 (Method) - 객체의 행동
    public void study() {
        System.out.println(name + "이(가) 공부합니다.");
    }
    
    public void introduce() {
        System.out.println("이름: " + name + ", 나이: " + age + ", 전공: " + major);
    }
    
    public void setInfo(String name, int age, String major) {
        this.name = name;
        this.age = age;
        this.major = major;
    }
    
    public static void main(String[] args) {
        System.out.println("=== 클래스와 객체 예제 ===\n");
        
        // 객체 생성
        ClassAndObject student1 = new ClassAndObject();
        ClassAndObject student2 = new ClassAndObject();
        
        System.out.println("--- 객체1 사용 ---");
        // 필드에 값 할당
        student1.name = "홍길동";
        student1.age = 20;
        student1.major = "컴퓨터공학";
        
        // 메서드 호출
        student1.introduce();
        student1.study();
        
        System.out.println("\n--- 객체2 사용 ---");
        // 메서드를 사용한 필드 설정
        student2.setInfo("김철수", 21, "전자공학");
        
        student2.introduce();
        student2.study();
        
        System.out.println("\n=== 객체 참조 ===");
        ClassAndObject student3 = student1;  // 같은 객체를 참조
        System.out.println("student1.name: " + student1.name);
        System.out.println("student3.name: " + student3.name);
        
        student3.name = "이영희";
        System.out.println("\nstudent3.name 변경 후:");
        System.out.println("student1.name: " + student1.name);  // 같은 객체이므로 변경됨
        System.out.println("student3.name: " + student3.name);
        
        System.out.println("\n=== 여러 객체 생성 ===");
        ClassAndObject[] students = new ClassAndObject[3];
        
        for (int i = 0; i < students.length; i++) {
            students[i] = new ClassAndObject();
            students[i].name = "학생" + (i + 1);
            students[i].age = 20 + i;
            students[i].major = "전공" + (i + 1);
        }
        
        for (ClassAndObject student : students) {
            student.introduce();
        }
    }
}

