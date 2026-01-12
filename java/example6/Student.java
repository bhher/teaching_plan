/**
 * 학생 점수 계산 메서드 예제
 * 객체가 자신의 일을 스스로 하도록 설계
 */
public class Student {
    // 필드 (속성) - 객체의 데이터
    private String name;
    private int korean;
    private int english;
    private int math;
    
    // 생성자
    public Student(String name, int korean, int english, int math) {
        this.name = name;
        this.korean = korean;
        this.english = english;
        this.math = math;
    }
    
    // Getter 메서드들
    public String getName() {
        return name;
    }
    
    public int getKorean() {
        return korean;
    }
    
    public int getEnglish() {
        return english;
    }
    
    public int getMath() {
        return math;
    }
    
    // 계산 메서드: 총점 계산
    public int calculateTotal() {
        return korean + english + math;
    }
    
    // 계산 메서드: 평균 계산
    public double calculateAverage() {
        return calculateTotal() / 3.0;
    }
    
    // 계산 메서드: 등급 계산
    public String calculateGrade() {
        double avg = calculateAverage();
        if (avg >= 90) return "A";
        else if (avg >= 80) return "B";
        else if (avg >= 70) return "C";
        else if (avg >= 60) return "D";
        else return "F";
    }
    
    // 출력 메서드: 학생 정보 출력
    public void printInfo() {
        System.out.println("=== " + name + " 학생 정보 ===");
        System.out.println("국어: " + korean + "점");
        System.out.println("영어: " + english + "점");
        System.out.println("수학: " + math + "점");
        System.out.println("총점: " + calculateTotal() + "점");
        System.out.println("평균: " + String.format("%.2f", calculateAverage()) + "점");
        System.out.println("등급: " + calculateGrade());
    }
    
    // 출력 메서드: 성적표 출력
    public void printReport() {
        printInfo();  // 정보 출력
        System.out.println("\n성적표 출력 완료!");
    }
}


