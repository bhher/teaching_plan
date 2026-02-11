/**
 * 예제 7: 인터페이스와 업캐스팅
 * 인터페이스 타입으로 구현 클래스를 참조하는 예제
 */
interface Vehicle {
    void start();
    void stop();
    void accelerate();
}

class Car implements Vehicle {
    private String brand;
    
    Car(String brand) {
        this.brand = brand;
    }
    
    @Override
    public void start() {
        System.out.println(brand + " 자동차가 시동을 겁니다.");
    }
    
    @Override
    public void stop() {
        System.out.println(brand + " 자동차가 멈춥니다.");
    }
    
    @Override
    public void accelerate() {
        System.out.println(brand + " 자동차가 가속합니다.");
    }
    
    void honk() {
        System.out.println(brand + " 자동차가 경적을 울립니다.");
    }
}

class Motorcycle implements Vehicle {
    private String model;
    
    Motorcycle(String model) {
        this.model = model;
    }
    
    @Override
    public void start() {
        System.out.println(model + " 오토바이가 시동을 겁니다.");
    }
    
    @Override
    public void stop() {
        System.out.println(model + " 오토바이가 멈춥니다.");
    }
    
    @Override
    public void accelerate() {
        System.out.println(model + " 오토바이가 가속합니다.");
    }
    
    void wheelie() {
        System.out.println(model + " 오토바이가 휠리를 합니다.");
    }
}

class Bicycle implements Vehicle {
    private String type;
    
    Bicycle(String type) {
        this.type = type;
    }
    
    @Override
    public void start() {
        System.out.println(type + " 자전거가 출발합니다.");
    }
    
    @Override
    public void stop() {
        System.out.println(type + " 자전거가 멈춥니다.");
    }
    
    @Override
    public void accelerate() {
        System.out.println(type + " 자전거가 페달을 더 빠르게 밟습니다.");
    }
    
    void ringBell() {
        System.out.println(type + " 자전거가 벨을 울립니다.");
    }
}

public class Example7_InterfaceUpcasting {
    public static void main(String[] args) {
        System.out.println("=== 인터페이스와 업캐스팅 예제 ===\n");
        
        // 인터페이스 타입으로 구현 클래스 객체 참조 (업캐스팅과 유사)
        Vehicle[] vehicles = new Vehicle[4];
        vehicles[0] = new Car("현대");
        vehicles[1] = new Motorcycle("야마하");
        vehicles[2] = new Bicycle("산악용");
        vehicles[3] = new Car("기아");
        
        System.out.println("=== 모든 차량 동작 ===");
        for (Vehicle vehicle : vehicles) {
            vehicle.start();
            vehicle.accelerate();
            vehicle.stop();
            System.out.println();
        }
        
        System.out.println("=== 타입별 특수 기능 ===");
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Car) {
                Car car = (Car) vehicle;
                car.honk();
            } else if (vehicle instanceof Motorcycle) {
                Motorcycle bike = (Motorcycle) vehicle;
                bike.wheelie();
            } else if (vehicle instanceof Bicycle) {
                Bicycle bicycle = (Bicycle) vehicle;
                bicycle.ringBell();
            }
        }
        
        System.out.println("\n=== 공통 메서드 활용 ===");
        operateVehicle(vehicles[0]);
        operateVehicle(vehicles[1]);
        operateVehicle(vehicles[2]);
    }
    
    // 인터페이스 타입을 매개변수로 받는 메서드
    static void operateVehicle(Vehicle vehicle) {
        System.out.println("--- 차량 운행 시작 ---");
        vehicle.start();
        vehicle.accelerate();
        vehicle.stop();
        System.out.println("--- 차량 운행 종료 ---\n");
    }
}
