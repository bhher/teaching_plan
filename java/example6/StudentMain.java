/**
 * Student 클래스 테스트를 위한 Main 파일
 * 학생 점수 계산 메서드 예제를 실행
 */
public class StudentMain {
    public static void main(String[] args) {
        System.out.println("========== 학생 점수 계산 예제 ==========\n");
        
        // 학생 객체 생성
        Student student1 = new Student("홍길동", 90, 85, 88);
        Student student2 = new Student("김철수", 75, 80, 70);
        Student student3 = new Student("이영희", 95, 92, 88);
        
        // 학생1 정보 출력
        student1.printInfo();
        System.out.println();
        
        // 학생2 정보 출력
        student2.printInfo();
        System.out.println();
        
        // 학생3 성적표 출력
        student3.printReport();
        
        System.out.println("\n========== 개별 메서드 테스트 ==========");
        
        // 총점 계산 테스트
        System.out.println(student1.getName() + "의 총점: " + student1.calculateTotal() + "점");
        System.out.println(student2.getName() + "의 총점: " + student2.calculateTotal() + "점");
        
        // 평균 계산 테스트
        System.out.println(student1.getName() + "의 평균: " + 
                          String.format("%.2f", student1.calculateAverage()) + "점");
        System.out.println(student2.getName() + "의 평균: " + 
                          String.format("%.2f", student2.calculateAverage()) + "점");
        
        // 등급 계산 테스트
        System.out.println(student1.getName() + "의 등급: " + student1.calculateGrade());
        System.out.println(student2.getName() + "의 등급: " + student2.calculateGrade());
        System.out.println(student3.getName() + "의 등급: " + student3.calculateGrade());
        
        System.out.println("\n========== Getter 메서드 테스트 ==========");
        System.out.println("이름: " + student1.getName());
        System.out.println("국어: " + student1.getKorean() + "점");
        System.out.println("영어: " + student1.getEnglish() + "점");
        System.out.println("수학: " + student1.getMath() + "점");
    }
}


