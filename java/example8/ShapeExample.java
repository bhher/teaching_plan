/**
 * 도형 관리 시스템 종합 예제
 * 추상 클래스와 인터페이스를 활용한 실전 예제
 */
// 추상 클래스: 도형
abstract class Shape {
    String color;
    
    public Shape(String color) {
        this.color = color;
    }
    
    public String getColor() {
        return color;
    }
    
    // 추상 메서드
    public abstract double calculateArea();
    public abstract double calculatePerimeter();
    
    // 일반 메서드
    public void displayInfo() {
        System.out.println("색깔: " + color);
        System.out.println("넓이: " + String.format("%.2f", calculateArea()));
        System.out.println("둘레: " + String.format("%.2f", calculatePerimeter()));
    }
}

// 인터페이스: 크기 조정 가능
interface Resizable {
    void resize(double factor);
}

// 인터페이스: 그리기 가능
interface Drawable {
    void draw();
}

// 원 클래스
class Circle extends Shape implements Resizable, Drawable {
    double radius;
    
    public Circle(String color, double radius) {
        super(color);
        this.radius = radius;
    }
    
    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
    
    @Override
    public double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }
    
    @Override
    public void resize(double factor) {
        radius *= factor;
        System.out.println("반지름이 " + factor + "배로 조정되었습니다.");
    }
    
    @Override
    public void draw() {
        System.out.println(color + " 원을 그립니다. (반지름: " + radius + ")");
    }
}

// 직사각형 클래스
class Rectangle extends Shape implements Resizable, Drawable {
    double width;
    double height;
    
    public Rectangle(String color, double width, double height) {
        super(color);
        this.width = width;
        this.height = height;
    }
    
    @Override
    public double calculateArea() {
        return width * height;
    }
    
    @Override
    public double calculatePerimeter() {
        return 2 * (width + height);
    }
    
    @Override
    public void resize(double factor) {
        width *= factor;
        height *= factor;
        System.out.println("크기가 " + factor + "배로 조정되었습니다.");
    }
    
    @Override
    public void draw() {
        System.out.println(color + " 직사각형을 그립니다. (너비: " + width + ", 높이: " + height + ")");
    }
}

// 삼각형 클래스 (크기 조정 불가)
class Triangle extends Shape implements Drawable {
    double base;
    double height;
    
    public Triangle(String color, double base, double height) {
        super(color);
        this.base = base;
        this.height = height;
    }
    
    @Override
    public double calculateArea() {
        return 0.5 * base * height;
    }
    
    @Override
    public double calculatePerimeter() {
        // 간단한 예제이므로 생략
        return base + height + Math.sqrt(base * base + height * height);
    }
    
    @Override
    public void draw() {
        System.out.println(color + " 삼각형을 그립니다. (밑변: " + base + ", 높이: " + height + ")");
    }
}

public class ShapeExample {
    public static void main(String[] args) {
        System.out.println("=== 도형 관리 시스템 ===\n");
        
        // 원 객체
        System.out.println("--- Circle ---");
        Circle circle = new Circle("빨강", 5.0);
        circle.draw();
        circle.displayInfo();
        
        System.out.println("\n크기 조정 후:");
        circle.resize(2.0);
        circle.displayInfo();
        
        // 직사각형 객체
        System.out.println("\n--- Rectangle ---");
        Rectangle rectangle = new Rectangle("파랑", 4.0, 6.0);
        rectangle.draw();
        rectangle.displayInfo();
        
        System.out.println("\n크기 조정 후:");
        rectangle.resize(1.5);
        rectangle.displayInfo();
        
        // 삼각형 객체
        System.out.println("\n--- Triangle ---");
        Triangle triangle = new Triangle("초록", 3.0, 4.0);
        triangle.draw();
        triangle.displayInfo();
        
        // 다형성 활용
        System.out.println("\n--- 다형성 활용 ---");
        Shape[] shapes = {
            new Circle("노랑", 3.0),
            new Rectangle("보라", 5.0, 7.0),
            new Triangle("주황", 4.0, 5.0)
        };
        
        for (Shape shape : shapes) {
            shape.displayInfo();
            System.out.println();
        }
        
        // 인터페이스를 타입으로 사용
        System.out.println("--- Drawable 인터페이스 활용 ---");
        Drawable[] drawables = {
            new Circle("빨강", 2.0),
            new Rectangle("파랑", 3.0, 4.0),
            new Triangle("초록", 2.0, 3.0)
        };
        
        for (Drawable drawable : drawables) {
            drawable.draw();
        }
        
        System.out.println("\n--- Resizable 인터페이스 활용 ---");
        Resizable[] resizables = {
            new Circle("빨강", 5.0),
            new Rectangle("파랑", 4.0, 6.0)
        };
        
        for (Resizable resizable : resizables) {
            resizable.resize(2.0);
        }
    }
}

