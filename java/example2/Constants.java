/**
 * 상수(final)를 설명하는 예제
 * 상수 선언과 사용 방법을 보여줍니다
 */
public class Constants {
    // 클래스 상수 (static final)
    public static final double PI = 3.14159;
    public static final int MAX_STUDENTS = 100;
    public static final String COMPANY_NAME = "ABC Corp";
    
    public static void main(String[] args) {
        // 지역 상수
        final int MIN_AGE = 18;
        final String MESSAGE = "환영합니다";
        final double GRAVITY = 9.8;
        
        System.out.println("=== 상수 사용 ===");
        System.out.println("원주율: " + PI);
        System.out.println("최대 학생 수: " + MAX_STUDENTS);
        System.out.println("회사명: " + COMPANY_NAME);
        System.out.println("최소 나이: " + MIN_AGE);
        System.out.println("인사말: " + MESSAGE);
        System.out.println("중력 가속도: " + GRAVITY + " m/s²");
        
        // 상수를 사용한 계산
        double radius = 5.0;
        double area = PI * radius * radius;
        double circumference = 2 * PI * radius;
        
        System.out.println("\n=== 원의 계산 ===");
        System.out.println("반지름: " + radius);
        System.out.println("넓이: " + area);
        System.out.println("둘레: " + circumference);
        
        // 오류 예시 (주석 처리)
        // MIN_AGE = 20;  // 컴파일 오류! 상수는 변경할 수 없음
        // PI = 3.14;     // 컴파일 오류!
    }
}

