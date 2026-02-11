/**
 * 예제 1: 기본 업캐스팅
 * 동물 클래스와 자식 클래스들을 통한 기본적인 업캐스팅 예제
 */
class Animal {
    String name;
    
    Animal(String name) {
        this.name = name;
    }
    
    void sound() {
        System.out.println("동물 소리");
    }
    
    void move() {
        System.out.println(name + "가 움직입니다.");
    }
}

class Dog extends Animal {
    Dog(String name) {
        super(name);
    }
    
    @Override
    void sound() {
        System.out.println(name + ": 멍멍!");
    }
    
    void wagTail() {
        System.out.println(name + "가 꼬리를 흔듭니다.");
    }
}

class Cat extends Animal {
    Cat(String name) {
        super(name);
    }
    
    @Override
    void sound() {
        System.out.println(name + ": 야옹~");
    }
    
    void scratch() {
        System.out.println(name + "가 할퀴기를 합니다.");
    }
}

public class Example1_BasicUpcasting {
    public static void main(String[] args) {
        System.out.println("=== 기본 업캐스팅 예제 ===\n");
        
        // 업캐스팅: 자식 객체를 부모 타입으로 참조
        Animal animal1 = new Dog("뽀삐");
        Animal animal2 = new Cat("나비");
        
        // 오버라이딩된 메서드는 자식 클래스의 메서드가 호출됨
        animal1.sound();  // "뽀삐: 멍멍!"
        animal2.sound();  // "나비: 야옹~"
        
        // 부모 클래스의 메서드도 호출 가능
        animal1.move();   // "뽀삐가 움직입니다."
        animal2.move();   // "나비가 움직입니다."
        
        // 자식 클래스에만 있는 메서드는 호출 불가
        // animal1.wagTail();  // 컴파일 오류!
        // animal2.scratch();  // 컴파일 오류!
        
        System.out.println("\n=== 다운캐스팅으로 자식 메서드 호출 ===");
        // 다운캐스팅 후 자식 클래스 메서드 호출 가능
        if (animal1 instanceof Dog) {
            Dog dog = (Dog) animal1;
            dog.wagTail();  // "뽀삐가 꼬리를 흔듭니다."
        }
        
        if (animal2 instanceof Cat) {
            Cat cat = (Cat) animal2;
            cat.scratch();  // "나비가 할퀴기를 합니다."
        }
    }
}
