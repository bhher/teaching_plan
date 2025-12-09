/**
 * 객체지향 핵심 개념 종합 예제
 * 캡슐화, 상속, 다형성, 추상 클래스, 인터페이스를 모두 활용
 */
// 추상 클래스: 동물
abstract class Animal {
    // 캡슐화: private 필드
    private String name;
    private int age;
    
    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    // Getter
    public String getName() {
        return name;
    }
    
    public int getAge() {
        return age;
    }
    
    // 일반 메서드
    public void eat() {
        System.out.println(name + "이(가) 먹습니다.");
    }
    
    public void sleep() {
        System.out.println(name + "이(가) 잡니다.");
    }
    
    // 추상 메서드
    public abstract void makeSound();
    public abstract void move();
}

// 인터페이스 1
interface Flyable {
    void fly();
}

// 인터페이스 2
interface Swimmable {
    void swim();
}

// 자식 클래스 1: 새 (상속 + 인터페이스 구현)
class Bird extends Animal implements Flyable {
    private String species;  // 캡슐화
    
    public Bird(String name, int age, String species) {
        super(name, age);
        this.species = species;
    }
    
    public String getSpecies() {
        return species;
    }
    
    @Override
    public void makeSound() {
        System.out.println(getName() + "이(가) 짹짹 웁니다.");
    }
    
    @Override
    public void move() {
        System.out.println(getName() + "이(가) 날아다닙니다.");
    }
    
    @Override
    public void fly() {
        System.out.println(getName() + "이(가) 하늘을 날아갑니다.");
    }
}

// 자식 클래스 2: 물고기 (상속 + 인터페이스 구현)
class Fish extends Animal implements Swimmable {
    private String habitat;  // 캡슐화
    
    public Fish(String name, int age, String habitat) {
        super(name, age);
        this.habitat = habitat;
    }
    
    public String getHabitat() {
        return habitat;
    }
    
    @Override
    public void makeSound() {
        System.out.println(getName() + "은(는) 소리를 낼 수 없습니다.");
    }
    
    @Override
    public void move() {
        System.out.println(getName() + "이(가) 헤엄칩니다.");
    }
    
    @Override
    public void swim() {
        System.out.println(getName() + "이(가) " + habitat + "에서 헤엄칩니다.");
    }
}

// 자식 클래스 3: 오리 (상속 + 여러 인터페이스 구현)
class Duck extends Animal implements Flyable, Swimmable {
    private String color;  // 캡슐화
    
    public Duck(String name, int age, String color) {
        super(name, age);
        this.color = color;
    }
    
    public String getColor() {
        return color;
    }
    
    @Override
    public void makeSound() {
        System.out.println(getName() + "이(가) 꽥꽥 웁니다.");
    }
    
    @Override
    public void move() {
        System.out.println(getName() + "이(가) 걷거나 헤엄칩니다.");
    }
    
    @Override
    public void fly() {
        System.out.println(getName() + "이(가) 낮게 날아갑니다.");
    }
    
    @Override
    public void swim() {
        System.out.println(getName() + "이(가) 연못에서 헤엄칩니다.");
    }
}

public class OOPConceptsComprehensive {
    // 다형성을 활용한 메서드
    public static void performAction(Animal animal) {
        System.out.println("\n=== " + animal.getName() + "의 행동 ===");
        animal.eat();
        animal.sleep();
        animal.makeSound();
        animal.move();
    }
    
    public static void main(String[] args) {
        System.out.println("=== 객체지향 핵심 개념 종합 예제 ===\n");
        
        // 객체 생성
        Bird bird = new Bird("짹짹이", 2, "참새");
        Fish fish = new Fish("니모", 1, "바다");
        Duck duck = new Duck("도날드", 3, "노란색");
        
        // 캡슐화: Getter 사용
        System.out.println("--- 캡슐화 (Getter 사용) ---");
        System.out.println("새 이름: " + bird.getName());
        System.out.println("새 종류: " + bird.getSpecies());
        System.out.println("물고기 이름: " + fish.getName());
        System.out.println("물고기 서식지: " + fish.getHabitat());
        System.out.println("오리 이름: " + duck.getName());
        System.out.println("오리 색깔: " + duck.getColor());
        
        // 상속: 부모 클래스의 메서드 사용
        System.out.println("\n--- 상속 (부모 클래스 메서드) ---");
        bird.eat();
        fish.sleep();
        duck.eat();
        
        // 다형성: 부모 클래스 타입으로 참조
        System.out.println("\n--- 다형성 (부모 클래스 타입) ---");
        Animal[] animals = {bird, fish, duck};
        
        for (Animal animal : animals) {
            performAction(animal);
        }
        
        // 인터페이스: 인터페이스 타입으로 참조
        System.out.println("\n--- 인터페이스 (Flyable) ---");
        Flyable[] flyables = {bird, duck};
        for (Flyable flyable : flyables) {
            flyable.fly();
        }
        
        System.out.println("\n--- 인터페이스 (Swimmable) ---");
        Swimmable[] swimmables = {fish, duck};
        for (Swimmable swimmable : swimmables) {
            swimmable.swim();
        }
        
        // instanceof 활용
        System.out.println("\n--- instanceof 활용 ---");
        for (Animal animal : animals) {
            if (animal instanceof Flyable) {
                ((Flyable) animal).fly();
            }
            if (animal instanceof Swimmable) {
                ((Swimmable) animal).swim();
            }
        }
        
        System.out.println("\n=== 객체지향 프로그래밍의 4대 핵심 개념 ===");
        System.out.println("1. 캡슐화: private 필드와 getter/setter로 데이터 보호");
        System.out.println("2. 상속: extends로 부모 클래스의 속성과 메서드 상속");
        System.out.println("3. 다형성: 하나의 타입으로 여러 객체 처리");
        System.out.println("4. 추상화: abstract 클래스와 interface로 공통 기능 정의");
    }
}

