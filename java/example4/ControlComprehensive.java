/**
 * 제어문 종합 예제
 * 조건문, 반복문, break, continue를 함께 사용하는 실전 예제
 */
public class ControlComprehensive {
    public static void main(String[] args) {
        System.out.println("=== 제어문 종합 예제 ===\n");
        
        // 1. 조건문: 성적 등급 판정
        System.out.println("--- 1. 성적 등급 판정 ---");
        int[] scores = {95, 85, 75, 65, 55};
        
        for (int score : scores) {
            String grade;
            if (score >= 90) {
                grade = "A";
            } else if (score >= 80) {
                grade = "B";
            } else if (score >= 70) {
                grade = "C";
            } else if (score >= 60) {
                grade = "D";
            } else {
                grade = "F";
            }
            System.out.println("점수: " + score + " → 등급: " + grade);
        }
        
        // 2. 반복문: 합계와 평균 계산
        System.out.println("\n--- 2. 합계와 평균 계산 ---");
        int[] numbers = {10, 20, 30, 40, 50};
        int sum = 0;
        
        for (int i = 0; i < numbers.length; i++) {
            sum += numbers[i];
        }
        double average = (double) sum / numbers.length;
        System.out.println("합계: " + sum);
        System.out.println("평균: " + average);
        
        // 3. 중첩 반복문: 구구단
        System.out.println("\n--- 3. 구구단 (2단~5단) ---");
        for (int i = 2; i <= 5; i++) {
            System.out.print(i + "단: ");
            for (int j = 1; j <= 9; j++) {
                System.out.print(i + "×" + j + "=" + (i * j) + " ");
            }
            System.out.println();
        }
        
        // 4. break: 조건 만족 시 종료
        System.out.println("\n--- 4. break: 합이 100을 넘으면 종료 ---");
        sum = 0;
        int count = 0;
        for (int i = 1; i <= 100; i++) {
            sum += i;
            count++;
            if (sum > 100) {
                System.out.println("합이 100을 넘는 순간: " + sum);
                System.out.println("더한 숫자의 개수: " + count);
                break;
            }
        }
        
        // 5. continue: 특정 조건 제외
        System.out.println("\n--- 5. continue: 3의 배수만 출력 ---");
        System.out.print("1부터 20까지의 3의 배수: ");
        for (int i = 1; i <= 20; i++) {
            if (i % 3 != 0) {
                continue;
            }
            System.out.print(i + " ");
        }
        System.out.println();
        
        // 6. 복합 예제: 소수 찾기
        System.out.println("\n--- 6. 소수 찾기 (1~30) ---");
        System.out.print("소수: ");
        for (int num = 2; num <= 30; num++) {
            boolean isPrime = true;
            
            for (int i = 2; i < num; i++) {
                if (num % i == 0) {
                    isPrime = false;
                    break;
                }
            }
            
            if (isPrime) {
                System.out.print(num + " ");
            }
        }
        System.out.println();
        
        // 7. switch와 반복문 조합
        System.out.println("\n--- 7. switch와 반복문 조합 ---");
        int[] days = {1, 3, 5, 7};
        for (int day : days) {
            String dayName;
            switch (day) {
                case 1:
                    dayName = "월요일";
                    break;
                case 2:
                    dayName = "화요일";
                    break;
                case 3:
                    dayName = "수요일";
                    break;
                case 4:
                    dayName = "목요일";
                    break;
                case 5:
                    dayName = "금요일";
                    break;
                case 6:
                    dayName = "토요일";
                    break;
                case 7:
                    dayName = "일요일";
                    break;
                default:
                    dayName = "잘못된 요일";
                    break;
            }
            System.out.println(day + " → " + dayName);
        }
        
        // 8. 중첩 제어문: 별 찍기
        System.out.println("\n--- 8. 별 찍기: 다이아몬드 패턴 ---");
        int size = 5;
        
        // 위쪽 삼각형
        for (int i = 1; i <= size; i++) {
            // 공백
            for (int j = 1; j <= size - i; j++) {
                System.out.print(" ");
            }
            // 별
            for (int j = 1; j <= 2 * i - 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        
        // 아래쪽 삼각형
        for (int i = size - 1; i >= 1; i--) {
            // 공백
            for (int j = 1; j <= size - i; j++) {
                System.out.print(" ");
            }
            // 별
            for (int j = 1; j <= 2 * i - 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        
        System.out.println("\n=== 예제 완료 ===");
    }
}

