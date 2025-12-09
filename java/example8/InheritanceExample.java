/**
 * 상속을 설명하는 예제
 * 부모 클래스와 자식 클래스의 관계를 보여줍니다
 */
// 부모 클래스
class Animal {
    String name;
    int age;
    
    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public void eat() {
        System.out.println(name + "이(가) 먹습니다.");
    }
    
    public void sleep() {
        System.out.println(name + "이(가) 잡니다.");
    }
    
    public void makeSound() {
        System.out.println(name + "이(가) 소리를 냅니다.");
    }
    
    public void displayInfo() {
        System.out.println("이름: " + name + ", 나이: " + age);
    }
}

// 자식 클래스 1
class Dog extends Animal {
    String breed;
    
    public Dog(String name, int age, String breed) {
        super(name, age);  // 부모 클래스의 생성자 호출
        this.breed = breed;
    }
    
    // 메서드 오버라이딩
    @Override
    public void makeSound() {
        System.out.println(name + "이(가) 멍멍 짖습니다.");
    }
    
    // 자식 클래스만의 메서드
    public void bark() {
        System.out.println(name + "이(가) 왈왈 짖습니다.");
    }
    
    @Override
    public void displayInfo() {
        super.displayInfo();  // 부모 클래스의 메서드 호출
        System.out.println("품종: " + breed);
    }
}

// 자식 클래스 2
class Cat extends Animal {
    String color;
    
    public Cat(String name, int age, String color) {
        super(name, age);
        this.color = color;
    }
    
    @Override
    public void makeSound() {
        System.out.println(name + "이(가) 야옹 웁니다.");
    }
    
    // 자식 클래스만의 메서드
    public void scratch() {
        System.out.println(name + "이(가) 할퀴뜁니다.");
    }
    
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("색깔: " + color);
    }
}

// 메인 클래스
public class InheritanceExample {
    public static void main(String[] args) {
        System.out.println("=== 상속 예제 ===\n");
        
        // 부모 클래스 객체
        System.out.println("--- 부모 클래스 (Animal) ---");
        Animal animal = new Animal("동물", 5);
        animal.displayInfo();
        animal.eat();
        animal.sleep();
        animal.makeSound();
        
        // 자식 클래스 객체 1
        System.out.println("\n--- 자식 클래스 1 (Dog) ---");
        Dog dog = new Dog("멍멍이", 3, "골든리트리버");
        dog.displayInfo();  // 오버라이딩된 메서드
        dog.eat();           // 부모 클래스의 메서드
        dog.sleep();         // 부모 클래스의 메서드
        dog.makeSound();     // 오버라이딩된 메서드
        dog.bark();          // 자식 클래스만의 메서드
        
        // 자식 클래스 객체 2
        System.out.println("\n--- 자식 클래스 2 (Cat) ---");
        Cat cat = new Cat("야옹이", 2, "흰색");
        cat.displayInfo();
        cat.eat();
        cat.sleep();
        cat.makeSound();
        cat.scratch();
        
        System.out.println("\n=== 상속의 장점 ===");
        System.out.println("1. 코드 재사용: 부모 클래스의 메서드 재사용");
        System.out.println("2. 유지보수 용이: 부모 클래스 수정 시 자식 클래스에 자동 반영");
        System.out.println("3. 계층 구조: 클래스 간 관계 명확화");
    }
}

