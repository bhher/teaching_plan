/**
 * 예제 2: 다형성과 업캐스팅
 * 배열과 반복문을 이용한 다형성 활용 예제
 */
abstract class Shape {
    String name;
    
    Shape(String name) {
        this.name = name;
    }
    
    abstract double getArea();
    abstract double getPerimeter();
    
    void printInfo() {
        System.out.println(name + " - 넓이: " + getArea() + ", 둘레: " + getPerimeter());
    }
}

class Circle extends Shape {
    double radius;
    
    Circle(String name, double radius) {
        super(name);
        this.radius = radius;
    }
    
    @Override
    double getArea() {
        return Math.PI * radius * radius;
    }
    
    @Override
    double getPerimeter() {
        return 2 * Math.PI * radius;
    }
}

class Rectangle extends Shape {
    double width;
    double height;
    
    Rectangle(String name, double width, double height) {
        super(name);
        this.width = width;
        this.height = height;
    }
    
    @Override
    double getArea() {
        return width * height;
    }
    
    @Override
    double getPerimeter() {
        return 2 * (width + height);
    }
}

class Triangle extends Shape {
    double base;
    double height;
    
    Triangle(String name, double base, double height) {
        super(name);
        this.base = base;
        this.height = height;
    }
    
    @Override
    double getArea() {
        return 0.5 * base * height;
    }
    
    @Override
    double getPerimeter() {
        // 간단한 예제이므로 둘레는 생략
        return base + height + Math.sqrt(base * base + height * height);
    }
}

public class Example2_Polymorphism {
    public static void main(String[] args) {
        System.out.println("=== 다형성과 업캐스팅 예제 ===\n");
        
        // 다양한 도형 객체들을 부모 타입 배열에 저장 (업캐스팅)
        Shape[] shapes = new Shape[4];
        shapes[0] = new Circle("원1", 5.0);
        shapes[1] = new Rectangle("사각형1", 4.0, 6.0);
        shapes[2] = new Triangle("삼각형1", 3.0, 4.0);
        shapes[3] = new Circle("원2", 3.0);
        
        // 다형성: 각 객체의 실제 타입에 따라 메서드가 호출됨
        System.out.println("=== 모든 도형 정보 출력 ===");
        for (Shape shape : shapes) {
            shape.printInfo();
        }
        
        System.out.println("\n=== 넓이 합계 계산 ===");
        double totalArea = 0;
        for (Shape shape : shapes) {
            totalArea += shape.getArea();
        }
        System.out.println("전체 넓이 합계: " + String.format("%.2f", totalArea));
        
        System.out.println("\n=== 원만 찾아서 출력 ===");
        for (Shape shape : shapes) {
            if (shape instanceof Circle) {
                Circle circle = (Circle) shape;
                System.out.println(circle.name + "의 반지름: " + circle.radius);
            }
        }
    }
}
