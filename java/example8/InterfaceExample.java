/**
 * 인터페이스를 설명하는 예제
 * interface 정의와 구현을 보여줍니다
 */
// 인터페이스 1
interface Flyable {
    // 추상 메서드 (abstract 키워드 생략 가능)
    void fly();
    
    // 상수 (public static final 생략 가능)
    int MAX_SPEED = 100;
}

// 인터페이스 2
interface Swimmable {
    void swim();
}

// 인터페이스 3
interface Walkable {
    void walk();
}

// 클래스가 여러 인터페이스 구현
class Duck implements Flyable, Swimmable, Walkable {
    String name;
    
    public Duck(String name) {
        this.name = name;
    }
    
    @Override
    public void fly() {
        System.out.println(name + "이(가) 날아갑니다.");
    }
    
    @Override
    public void swim() {
        System.out.println(name + "이(가) 수영합니다.");
    }
    
    @Override
    public void walk() {
        System.out.println(name + "이(가) 걷습니다.");
    }
}

// 인터페이스 하나만 구현
class Fish implements Swimmable {
    String name;
    
    public Fish(String name) {
        this.name = name;
    }
    
    @Override
    public void swim() {
        System.out.println(name + "이(가) 헤엄칩니다.");
    }
}

// 인터페이스 하나만 구현
class Airplane implements Flyable {
    String model;
    
    public Airplane(String model) {
        this.model = model;
    }
    
    @Override
    public void fly() {
        System.out.println(model + "이(가) 날아갑니다. (최대 속도: " + MAX_SPEED + "km/h)");
    }
}

// 인터페이스를 타입으로 사용 (다형성)
public class InterfaceExample {
    public static void makeFly(Flyable flyable) {
        flyable.fly();
    }
    
    public static void makeSwim(Swimmable swimmable) {
        swimmable.swim();
    }
    
    public static void main(String[] args) {
        System.out.println("=== 인터페이스 예제 ===\n");
        
        // Duck: 여러 인터페이스 구현
        System.out.println("--- Duck (여러 인터페이스 구현) ---");
        Duck duck = new Duck("도날드");
        duck.fly();
        duck.swim();
        duck.walk();
        
        // Fish: 하나의 인터페이스 구현
        System.out.println("\n--- Fish (Swimmable 구현) ---");
        Fish fish = new Fish("니모");
        fish.swim();
        // fish.fly();  // 오류! Flyable을 구현하지 않음
        
        // Airplane: 하나의 인터페이스 구현
        System.out.println("\n--- Airplane (Flyable 구현) ---");
        Airplane airplane = new Airplane("보잉 747");
        airplane.fly();
        
        // 인터페이스를 타입으로 사용 (다형성)
        System.out.println("\n--- 인터페이스를 타입으로 사용 ---");
        Flyable[] flyables = {
            new Duck("오리1"),
            new Airplane("에어버스 A380")
        };
        
        for (Flyable flyable : flyables) {
            makeFly(flyable);
        }
        
        Swimmable[] swimmables = {
            new Duck("오리2"),
            new Fish("물고기1")
        };
        
        for (Swimmable swimmable : swimmables) {
            makeSwim(swimmable);
        }
        
        // 인터페이스 상수 사용
        System.out.println("\n--- 인터페이스 상수 ---");
        System.out.println("Flyable.MAX_SPEED: " + Flyable.MAX_SPEED);
        
        System.out.println("\n=== 인터페이스의 특징 ===");
        System.out.println("1. interface 키워드로 선언");
        System.out.println("2. 직접 객체 생성 불가");
        System.out.println("3. 모든 메서드는 추상 메서드 (Java 8 이전)");
        System.out.println("4. 다중 구현 가능 (여러 인터페이스 구현)");
        System.out.println("5. 상수만 포함 가능 (public static final)");
    }
}

