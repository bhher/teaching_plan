/**
 * 예제 5: 실전 활용 예제
 * 실제 개발에서 자주 사용하는 업캐스팅 패턴 예제
 */

// 직원 관리 시스템
abstract class Employee {
    protected String name;
    protected int id;
    protected double baseSalary;
    
    Employee(String name, int id, double baseSalary) {
        this.name = name;
        this.id = id;
        this.baseSalary = baseSalary;
    }
    
    // 추상 메서드: 각 직원 타입마다 다른 계산 방식
    abstract double calculateSalary();
    
    // 공통 메서드
    void printInfo() {
        System.out.println("ID: " + id + ", 이름: " + name + 
                          ", 기본급: " + baseSalary + 
                          ", 실급여: " + calculateSalary());
    }
    
    String getName() {
        return name;
    }
}

class FullTimeEmployee extends Employee {
    private double bonus;
    
    FullTimeEmployee(String name, int id, double baseSalary, double bonus) {
        super(name, id, baseSalary);
        this.bonus = bonus;
    }
    
    @Override
    double calculateSalary() {
        return baseSalary + bonus;
    }
    
    void workFullTime() {
        System.out.println(name + "이(가) 정규직으로 일합니다.");
    }
}

class PartTimeEmployee extends Employee {
    private int workHours;
    private double hourlyRate;
    
    PartTimeEmployee(String name, int id, double baseSalary, int workHours, double hourlyRate) {
        super(name, id, baseSalary);
        this.workHours = workHours;
        this.hourlyRate = hourlyRate;
    }
    
    @Override
    double calculateSalary() {
        return baseSalary + (workHours * hourlyRate);
    }
    
    void workPartTime() {
        System.out.println(name + "이(가) 시간제로 " + workHours + "시간 일합니다.");
    }
}

class Manager extends Employee {
    private double teamBonus;
    private int teamSize;
    
    Manager(String name, int id, double baseSalary, double teamBonus, int teamSize) {
        super(name, id, baseSalary);
        this.teamBonus = teamBonus;
        this.teamSize = teamSize;
    }
    
    @Override
    double calculateSalary() {
        return baseSalary + teamBonus * teamSize;
    }
    
    void manageTeam() {
        System.out.println(name + "이(가) " + teamSize + "명의 팀을 관리합니다.");
    }
}

public class Example5_PracticalUse {
    public static void main(String[] args) {
        System.out.println("=== 실전 활용 예제: 직원 관리 시스템 ===\n");
        
        // 다양한 타입의 직원들을 부모 타입 배열에 저장 (업캐스팅)
        Employee[] employees = new Employee[5];
        employees[0] = new FullTimeEmployee("홍길동", 1, 3000000, 500000);
        employees[1] = new PartTimeEmployee("김영희", 2, 0, 20, 15000);
        employees[2] = new Manager("박철수", 3, 5000000, 100000, 5);
        employees[3] = new FullTimeEmployee("이미영", 4, 2500000, 300000);
        employees[4] = new PartTimeEmployee("최민수", 5, 0, 15, 12000);
        
        System.out.println("=== 모든 직원 정보 출력 ===");
        for (Employee emp : employees) {
            emp.printInfo();  // 다형성: 각 직원 타입에 맞는 계산
        }
        
        System.out.println("\n=== 전체 급여 합계 ===");
        double totalSalary = 0;
        for (Employee emp : employees) {
            totalSalary += emp.calculateSalary();
        }
        System.out.println("전체 급여 합계: " + String.format("%,.0f원", totalSalary));
        
        System.out.println("\n=== 직원 타입별 처리 ===");
        for (Employee emp : employees) {
            if (emp instanceof FullTimeEmployee) {
                FullTimeEmployee ft = (FullTimeEmployee) emp;
                ft.workFullTime();
            } else if (emp instanceof PartTimeEmployee) {
                PartTimeEmployee pt = (PartTimeEmployee) emp;
                pt.workPartTime();
            } else if (emp instanceof Manager) {
                Manager mgr = (Manager) emp;
                mgr.manageTeam();
            }
        }
        
        System.out.println("\n=== 특정 직원 검색 ===");
        String searchName = "홍길동";
        Employee found = findEmployee(employees, searchName);
        if (found != null) {
            System.out.println("검색 결과:");
            found.printInfo();
        } else {
            System.out.println(searchName + "을(를) 찾을 수 없습니다.");
        }
    }
    
    // 업캐스팅을 활용한 공통 메서드
    static Employee findEmployee(Employee[] employees, String name) {
        for (Employee emp : employees) {
            if (emp.getName().equals(name)) {
                return emp;
            }
        }
        return null;
    }
}
