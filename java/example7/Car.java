/**
 * 자동차 클래스 종합 예제
 * 클래스, 객체, 생성자, this, 접근제어자를 모두 활용한 예제
 */
public class Car {
    // private 필드
    private String brand;
    private String model;
    private int year;
    private double speed;
    private boolean isRunning;
    
    // 생성자 1: 모든 필드 초기화
    public Car(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.speed = 0.0;
        this.isRunning = false;
        System.out.println(brand + " " + model + " (" + year + ") 생성되었습니다.");
    }
    
    // 생성자 2: 브랜드와 모델만
    public Car(String brand, String model) {
        this(brand, model, 2023);  // 생성자 체이닝
    }
    
    // Getter 메서드
    public String getBrand() {
        return brand;
    }
    
    public String getModel() {
        return model;
    }
    
    public int getYear() {
        return year;
    }
    
    public double getSpeed() {
        return speed;
    }
    
    public boolean isRunning() {
        return isRunning;
    }
    
    // Setter 메서드
    public void setBrand(String brand) {
        this.brand = brand;
    }
    
    public void setModel(String model) {
        this.model = model;
    }
    
    // 시동 걸기
    public void start() {
        if (!isRunning) {
            isRunning = true;
            System.out.println("시동이 걸렸습니다.");
        } else {
            System.out.println("이미 시동이 걸려있습니다.");
        }
    }
    
    // 시동 끄기
    public void stop() {
        if (isRunning) {
            isRunning = false;
            speed = 0.0;
            System.out.println("시동이 꺼졌습니다.");
        } else {
            System.out.println("이미 시동이 꺼져있습니다.");
        }
    }
    
    // 가속
    public void accelerate(double amount) {
        if (isRunning) {
            if (amount > 0) {
                speed += amount;
                System.out.println("속도가 " + amount + "km/h 증가했습니다. 현재 속도: " + speed + "km/h");
            }
        } else {
            System.out.println("시동을 먼저 걸어주세요.");
        }
    }
    
    // 감속
    public void brake(double amount) {
        if (isRunning) {
            if (amount > 0) {
                if (speed >= amount) {
                    speed -= amount;
                    System.out.println("속도가 " + amount + "km/h 감소했습니다. 현재 속도: " + speed + "km/h");
                } else {
                    speed = 0;
                    System.out.println("차량이 정지했습니다.");
                }
            }
        } else {
            System.out.println("시동을 먼저 걸어주세요.");
        }
    }
    
    // 정보 출력
    public void displayInfo() {
        System.out.println("=== 차량 정보 ===");
        System.out.println("브랜드: " + brand);
        System.out.println("모델: " + model);
        System.out.println("연식: " + year);
        System.out.println("현재 속도: " + speed + "km/h");
        System.out.println("시동 상태: " + (isRunning ? "켜짐" : "꺼짐"));
    }
    
    public static void main(String[] args) {
        System.out.println("=== 자동차 클래스 예제 ===\n");
        
        // 객체 생성 (생성자 1)
        Car car1 = new Car("현대", "소나타", 2023);
        Car car2 = new Car("기아", "K5", 2022);
        
        // 객체 생성 (생성자 2)
        Car car3 = new Car("테슬라", "모델3");
        
        System.out.println("\n=== 차량1 테스트 ===");
        car1.displayInfo();
        car1.start();
        car1.accelerate(50);
        car1.accelerate(30);
        car1.brake(20);
        car1.displayInfo();
        car1.stop();
        
        System.out.println("\n=== 차량2 테스트 ===");
        car2.displayInfo();
        car2.start();
        car2.accelerate(60);
        car2.displayInfo();
        
        System.out.println("\n=== 차량3 테스트 ===");
        car3.displayInfo();
        car3.start();
        car3.accelerate(100);
        car3.displayInfo();
        
        System.out.println("\n=== Getter 사용 ===");
        System.out.println("car1 브랜드: " + car1.getBrand());
        System.out.println("car1 속도: " + car1.getSpeed() + "km/h");
        
        System.out.println("\n=== Setter 사용 ===");
        car1.setModel("아반떼");
        System.out.println("모델 변경 후:");
        car1.displayInfo();
    }
}

