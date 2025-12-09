/**
 * this 키워드를 설명하는 예제
 * this로 필드 접근, 메서드 호출, 생성자 호출을 보여줍니다
 */
public class ThisKeyword {
    String name;
    int age;
    String major;
    
    // 생성자: this로 필드 접근
    public ThisKeyword(String name, int age, String major) {
        // this.name은 필드, name은 매개변수
        this.name = name;
        this.age = age;
        this.major = major;
        System.out.println("생성자: this로 필드에 값 할당");
    }
    
    // 생성자 체이닝: this로 다른 생성자 호출
    public ThisKeyword(String name, int age) {
        this(name, age, "미정");  // 다른 생성자 호출
        System.out.println("생성자 체이닝: this(name, age, \"미정\") 호출");
    }
    
    public ThisKeyword(String name) {
        this(name, 0);  // 다른 생성자 호출
        System.out.println("생성자 체이닝: this(name, 0) 호출");
    }
    
    // this로 필드 접근 (명시적)
    public void setName(String name) {
        this.name = name;  // 필드와 매개변수 구분
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    // this로 메서드 호출
    public void setInfo(String name, int age, String major) {
        this.setName(name);  // this로 메서드 호출
        this.setAge(age);
        this.major = major;
    }
    
    // this로 필드 접근 (명시적)
    public void displayInfo() {
        System.out.println("이름: " + this.name);
        System.out.println("나이: " + this.age);
        System.out.println("전공: " + this.major);
    }
    
    // 현재 객체 반환
    public ThisKeyword getCurrentObject() {
        return this;  // 현재 객체 반환
    }
    
    public static void main(String[] args) {
        System.out.println("=== this 키워드 예제 ===\n");
        
        // this로 필드 접근
        System.out.println("--- this로 필드 접근 ---");
        ThisKeyword student1 = new ThisKeyword("홍길동", 20, "컴퓨터공학");
        student1.displayInfo();
        
        // this로 메서드 호출
        System.out.println("\n--- this로 메서드 호출 ---");
        student1.setInfo("김철수", 21, "전자공학");
        student1.displayInfo();
        
        // 생성자 체이닝
        System.out.println("\n--- 생성자 체이닝 ---");
        ThisKeyword student2 = new ThisKeyword("이영희");
        student2.displayInfo();
        
        // this로 현재 객체 반환
        System.out.println("\n--- this로 현재 객체 반환 ---");
        ThisKeyword student3 = new ThisKeyword("박민수", 22, "기계공학");
        ThisKeyword sameObject = student3.getCurrentObject();
        System.out.println("같은 객체인가? " + (student3 == sameObject));
        
        System.out.println("\n=== this 사용 시 주의사항 ===");
        System.out.println("1. static 메서드에서는 this 사용 불가");
        System.out.println("2. 생성자에서 this() 호출은 첫 번째 줄에만 가능");
        System.out.println("3. this는 현재 객체를 가리킴");
    }
}

