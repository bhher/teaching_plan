/**
 * 추상 클래스를 설명하는 예제
 * abstract 클래스와 추상 메서드를 보여줍니다
 */
// 추상 클래스
abstract class Animal {
    String name;
    int age;
    
    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    // 일반 메서드
    public void eat() {
        System.out.println(name + "이(가) 먹습니다.");
    }
    
    public void sleep() {
        System.out.println(name + "이(가) 잡니다.");
    }
    
    // 추상 메서드: 자식 클래스에서 반드시 구현해야 함
    public abstract void makeSound();
    
    public abstract void move();
    
    // 정보 출력
    public void displayInfo() {
        System.out.println("이름: " + name + ", 나이: " + age);
    }
}

// 추상 클래스를 상속받는 자식 클래스 1
class Dog extends Animal {
    String breed;
    
    public Dog(String name, int age, String breed) {
        super(name, age);
        this.breed = breed;
    }
    
    @Override
    public void makeSound() {
        System.out.println(name + "이(가) 멍멍 짖습니다.");
    }
    
    @Override
    public void move() {
        System.out.println(name + "이(가) 뛰어다닙니다.");
    }
}

// 추상 클래스를 상속받는 자식 클래스 2
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
    
    @Override
    public void move() {
        System.out.println(name + "이(가) 조용히 걸어다닙니다.");
    }
}

// 추상 클래스를 상속받는 자식 클래스 3
class Bird extends Animal {
    boolean canFly;
    
    public Bird(String name, int age, boolean canFly) {
        super(name, age);
        this.canFly = canFly;
    }
    
    @Override
    public void makeSound() {
        System.out.println(name + "이(가) 짹짹 웁니다.");
    }
    
    @Override
    public void move() {
        if (canFly) {
            System.out.println(name + "이(가) 날아다닙니다.");
        } else {
            System.out.println(name + "이(가) 걸어다닙니다.");
        }
    }
}

public class AbstractClassExample {
    public static void main(String[] args) {
        System.out.println("=== 추상 클래스 예제 ===\n");
        
        // 추상 클래스는 직접 객체 생성 불가
        // Animal animal = new Animal("동물", 5);  // 오류!
        
        // 자식 클래스 객체 생성
        System.out.println("--- Dog 객체 ---");
        Dog dog = new Dog("멍멍이", 3, "골든리트리버");
        dog.displayInfo();
        dog.eat();
        dog.sleep();
        dog.makeSound();  // 구현된 추상 메서드
        dog.move();       // 구현된 추상 메서드
        
        System.out.println("\n--- Cat 객체 ---");
        Cat cat = new Cat("야옹이", 2, "흰색");
        cat.displayInfo();
        cat.eat();
        cat.sleep();
        cat.makeSound();
        cat.move();
        
        System.out.println("\n--- Bird 객체 ---");
        Bird bird = new Bird("짹짹이", 1, true);
        bird.displayInfo();
        bird.eat();
        bird.sleep();
        bird.makeSound();
        bird.move();
        
        // 다형성 활용
        System.out.println("\n--- 다형성 활용 ---");
        Animal[] animals = {
            new Dog("강아지", 3, "시베리안 허스키"),
            new Cat("고양이", 2, "검은색"),
            new Bird("새", 1, true)
        };
        
        for (Animal animal : animals) {
            animal.displayInfo();
            animal.makeSound();
            animal.move();
            System.out.println();
        }
        
        System.out.println("=== 추상 클래스의 특징 ===");
        System.out.println("1. abstract 키워드로 선언");
        System.out.println("2. 직접 객체 생성 불가");
        System.out.println("3. 추상 메서드 포함 가능");
        System.out.println("4. 일반 메서드도 포함 가능");
        System.out.println("5. 자식 클래스에서 추상 메서드 구현 필수");
    }
}

