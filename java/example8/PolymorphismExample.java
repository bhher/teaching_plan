/**
 * 다형성을 설명하는 예제
 * 업캐스팅, 다운캐스팅, 오버라이딩을 보여줍니다
 */
// 부모 클래스
class Animal {
    String name;
    
    public Animal(String name) {
        this.name = name;
    }
    
    public void makeSound() {
        System.out.println("동물이 소리를 냅니다.");
    }
    
    public void eat() {
        System.out.println(name + "이(가) 먹습니다.");
    }
}

// 자식 클래스 1
class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }
    
    @Override
    public void makeSound() {
        System.out.println(name + "이(가) 멍멍 짖습니다.");
    }
    
    public void bark() {
        System.out.println(name + "이(가) 왈왈 짖습니다.");
    }
}

// 자식 클래스 2
class Cat extends Animal {
    public Cat(String name) {
        super(name);
    }
    
    @Override
    public void makeSound() {
        System.out.println(name + "이(가) 야옹 웁니다.");
    }
    
    public void scratch() {
        System.out.println(name + "이(가) 할퀴뜁니다.");
    }
}

// 자식 클래스 3
class Bird extends Animal {
    public Bird(String name) {
        super(name);
    }
    
    @Override
    public void makeSound() {
        System.out.println(name + "이(가) 짹짹 웁니다.");
    }
    
    public void fly() {
        System.out.println(name + "이(가) 날아갑니다.");
    }
}

public class PolymorphismExample {
    // 다형성을 활용한 메서드
    public static void makeAnimalSound(Animal animal) {
        animal.makeSound();  // 실제 객체의 오버라이딩된 메서드 호출
    }
    
    public static void main(String[] args) {
        System.out.println("=== 다형성 예제 ===\n");
        
        // 업캐스팅: 자식 클래스 객체를 부모 클래스 타입으로 참조
        System.out.println("--- 업캐스팅 ---");
        Animal animal1 = new Dog("멍멍이");
        Animal animal2 = new Cat("야옹이");
        Animal animal3 = new Bird("짹짹이");
        
        animal1.makeSound();  // Dog의 makeSound() 호출
        animal2.makeSound();  // Cat의 makeSound() 호출
        animal3.makeSound();  // Bird의 makeSound() 호출
        
        // 배열을 이용한 다형성
        System.out.println("\n--- 배열을 이용한 다형성 ---");
        Animal[] animals = {
            new Dog("강아지1"),
            new Cat("고양이1"),
            new Bird("새1"),
            new Dog("강아지2")
        };
        
        for (Animal animal : animals) {
            animal.makeSound();  // 각 객체의 오버라이딩된 메서드 호출
        }
        
        // 다형성을 활용한 메서드 호출
        System.out.println("\n--- 다형성을 활용한 메서드 ---");
        makeAnimalSound(new Dog("멍멍이"));
        makeAnimalSound(new Cat("야옹이"));
        makeAnimalSound(new Bird("짹짹이"));
        
        // 다운캐스팅: 부모 클래스 타입을 자식 클래스 타입으로 변환
        System.out.println("\n--- 다운캐스팅 ---");
        Animal animal = new Dog("멍멍이");
        animal.makeSound();
        
        // instanceof로 타입 확인 후 다운캐스팅
        if (animal instanceof Dog) {
            Dog dog = (Dog) animal;  // 다운캐스팅
            dog.bark();  // Dog만의 메서드 호출
        }
        
        // instanceof 활용
        System.out.println("\n--- instanceof 활용 ---");
        Animal[] mixedAnimals = {
            new Dog("강아지"),
            new Cat("고양이"),
            new Bird("새")
        };
        
        for (Animal a : mixedAnimals) {
            a.makeSound();
            
            if (a instanceof Dog) {
                Dog d = (Dog) a;
                d.bark();
            } else if (a instanceof Cat) {
                Cat c = (Cat) a;
                c.scratch();
            } else if (a instanceof Bird) {
                Bird b = (Bird) a;
                b.fly();
            }
        }
        
        System.out.println("\n=== 다형성의 장점 ===");
        System.out.println("1. 코드 재사용: 하나의 메서드로 여러 타입 처리");
        System.out.println("2. 유연성: 새로운 클래스 추가 시 기존 코드 수정 최소화");
        System.out.println("3. 확장성: 부모 클래스 타입으로 모든 자식 클래스 처리");
    }
}

