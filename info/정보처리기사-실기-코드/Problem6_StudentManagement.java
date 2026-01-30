package info.exam;

/**
 * 문제 6: 종합 알고리즘 문제 - 학생 점수 관리
 */
public class Problem6_StudentManagement {
    public static void main(String[] args) {
        String[] names = {"김철수", "이영희", "박민수", "최지영", "정수진"};
        int[] scores = {85, 92, 78, 88, 95};
        
        // 1. 평균 점수 계산
        int sum = 0;
        for (int score : scores) {
            sum += score;
        }
        double average = (double) sum / scores.length;
        System.out.printf("평균 점수: %.1f\n", average);
        
        // 2. 평균 이상 학생 출력
        System.out.println("평균 이상 학생:");
        for (int i = 0; i < names.length; i++) {
            if (scores[i] >= average) {
                System.out.println("  " + names[i] + ": " + scores[i] + "점");
            }
        }
        
        // 3. 등급 부여 및 집계
        int[] gradeCount = new int[4]; // A, B, C, D
        String[] grades = new String[names.length];
        
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] >= 90) {
                grades[i] = "A";
                gradeCount[0]++;
            } else if (scores[i] >= 80) {
                grades[i] = "B";
                gradeCount[1]++;
            } else if (scores[i] >= 70) {
                grades[i] = "C";
                gradeCount[2]++;
            } else {
                grades[i] = "D";
                gradeCount[3]++;
            }
        }
        
        // 4. 등급별 학생 수 출력
        System.out.println("\n등급별 학생 수:");
        System.out.println("  A등급: " + gradeCount[0] + "명");
        System.out.println("  B등급: " + gradeCount[1] + "명");
        System.out.println("  C등급: " + gradeCount[2] + "명");
        System.out.println("  D등급: " + gradeCount[3] + "명");
    }
}
