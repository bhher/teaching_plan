/**
 * 중첩 제어문을 설명하는 예제
 * 중첩 if, 중첩 반복문을 보여줍니다
 */
public class NestedControl {
    public static void main(String[] args) {
        System.out.println("=== 중첩 if 문 ===");
        int score = 85;
        String grade;
        
        if (score >= 90) {
            if (score >= 95) {
                grade = "A+";
            } else {
                grade = "A";
            }
        } else if (score >= 80) {
            if (score >= 85) {
                grade = "B+";
            } else {
                grade = "B";
            }
        } else {
            grade = "C";
        }
        System.out.println("점수: " + score + ", 등급: " + grade);
        
        System.out.println("\n=== 중첩 반복문: 구구단 ===");
        // 2단부터 9단까지
        for (int i = 2; i <= 9; i++) {
            System.out.println("=== " + i + "단 ===");
            for (int j = 1; j <= 9; j++) {
                System.out.println(i + " × " + j + " = " + (i * j));
            }
            System.out.println();
        }
        
        System.out.println("=== 별 찍기: 직각삼각형 ===");
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        
        System.out.println("\n=== 별 찍기: 역직각삼각형 ===");
        for (int i = 5; i >= 1; i--) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        
        System.out.println("\n=== 별 찍기: 피라미드 ===");
        int rows = 5;
        for (int i = 1; i <= rows; i++) {
            // 공백 출력
            for (int j = 1; j <= rows - i; j++) {
                System.out.print(" ");
            }
            // 별 출력
            for (int j = 1; j <= 2 * i - 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        
        System.out.println("\n=== 2차원 배열 시뮬레이션 ===");
        // 3x3 행렬 출력
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print("[" + i + "," + j + "] ");
            }
            System.out.println();
        }
        
        System.out.println("\n=== 중첩 제어문: 조건 + 반복 ===");
        // 1부터 20까지의 수 중에서 소수 찾기
        System.out.println("1부터 20까지의 소수:");
        for (int num = 2; num <= 20; num++) {
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
        
        System.out.println("\n=== 구구단 표 형식으로 출력 ===");
        // 헤더 출력
        System.out.print("   ");
        for (int i = 1; i <= 9; i++) {
            System.out.printf("%4d", i);
        }
        System.out.println();
        System.out.println("   " + "----------------------------------------");
        
        // 구구단 출력
        for (int i = 1; i <= 9; i++) {
            System.out.print(i + " |");
            for (int j = 1; j <= 9; j++) {
                System.out.printf("%4d", i * j);
            }
            System.out.println();
        }
    }
}

