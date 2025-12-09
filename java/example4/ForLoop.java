/**
 * for 반복문을 설명하는 예제
 * 다양한 for 문 사용법을 보여줍니다
 */
public class ForLoop {
    public static void main(String[] args) {
        System.out.println("=== 기본 for 문 (1부터 10까지) ===");
        for (int i = 1; i <= 10; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        
        System.out.println("\n=== 역순 출력 (10부터 1까지) ===");
        for (int i = 10; i >= 1; i--) {
            System.out.print(i + " ");
        }
        System.out.println();
        
        System.out.println("\n=== 2씩 증가 ===");
        for (int i = 0; i <= 10; i += 2) {
            System.out.print(i + " ");
        }
        System.out.println();
        
        System.out.println("\n=== 5씩 감소 ===");
        for (int i = 20; i >= 0; i -= 5) {
            System.out.print(i + " ");
        }
        System.out.println();
        
        System.out.println("\n=== 합계 계산 ===");
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            sum += i;
        }
        System.out.println("1부터 100까지의 합: " + sum);
        
        System.out.println("\n=== 곱셈 계산 (팩토리얼) ===");
        int factorial = 1;
        int n = 5;
        for (int i = 1; i <= n; i++) {
            factorial *= i;
        }
        System.out.println(n + "! = " + factorial);
        
        System.out.println("\n=== 짝수만 출력 ===");
        for (int i = 1; i <= 20; i++) {
            if (i % 2 == 0) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
        
        System.out.println("\n=== 구구단 2단 ===");
        for (int i = 1; i <= 9; i++) {
            System.out.println("2 × " + i + " = " + (2 * i));
        }
        
        System.out.println("\n=== 문자열 반복 ===");
        String message = "Hello";
        for (int i = 0; i < 5; i++) {
            System.out.println((i + 1) + ": " + message);
        }
        
        System.out.println("\n=== 변수 선언 위치 ===");
        // for 문 밖에서 변수 선언
        int j;
        for (j = 1; j <= 5; j++) {
            System.out.print(j + " ");
        }
        System.out.println("\nj의 최종값: " + j);
        
        // for 문 안에서 변수 선언 (for 문 밖에서 사용 불가)
        for (int k = 1; k <= 5; k++) {
            System.out.print(k + " ");
        }
        // System.out.println(k);  // 오류! k는 for 문 안에서만 사용 가능
    }
}

