/**
 * 변수와 자료형 종합 예제
 * 다양한 자료형을 사용하는 실전 예제
 */
public class DataTypeComprehensive {
    public static void main(String[] args) {
        // 학생 정보를 변수로 저장
        String name = "홍길동";
        int age = 20;
        double height = 175.5;
        float weight = 70.5f;
        char grade = 'A';
        boolean isActive = true;
        
        // 상수 정의
        final int PASS_SCORE = 60;
        final double PI = 3.14159;
        
        // 점수 정보
        int koreanScore = 85;
        int mathScore = 90;
        int englishScore = 88;
        
        // 평균 계산 (형 변환 활용)
        double average = (koreanScore + mathScore + englishScore) / 3.0;
        
        // 출력
        System.out.println("=== 학생 정보 ===");
        System.out.println("이름: " + name);
        System.out.println("나이: " + age + "세");
        System.out.println("키: " + height + "cm");
        System.out.println("몸무게: " + weight + "kg");
        System.out.println("등급: " + grade);
        System.out.println("활성 상태: " + isActive);
        
        System.out.println("\n=== 성적 정보 ===");
        System.out.println("국어: " + koreanScore + "점");
        System.out.println("수학: " + mathScore + "점");
        System.out.println("영어: " + englishScore + "점");
        System.out.println("평균: " + average + "점");
        System.out.println("합격 기준: " + PASS_SCORE + "점");
        System.out.println("합격 여부: " + (average >= PASS_SCORE ? "합격" : "불합격"));
        
        // 형 변환 예제
        System.out.println("\n=== 형 변환 예제 ===");
        int intAverage = (int) average;  // 소수점 제거
        System.out.println("평균(정수): " + intAverage + "점");
        
        // 문자와 숫자 변환
        char gradeChar = (char) (65 + (int)(average / 10) - 6);  // 점수에 따른 등급
        System.out.println("계산된 등급: " + gradeChar);
    }
}

