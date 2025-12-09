/**
 * break와 continue 문을 설명하는 예제
 * 반복문의 흐름을 제어하는 방법을 보여줍니다
 */
public class BreakContinue {
    public static void main(String[] args) {
        System.out.println("=== break 문: 반복문 종료 ===");
        System.out.println("1부터 10까지 출력하되, 5에서 멈춤:");
        for (int i = 1; i <= 10; i++) {
            if (i == 5) {
                break;  // 반복문 종료
            }
            System.out.print(i + " ");
        }
        System.out.println();
        
        System.out.println("\n=== continue 문: 현재 반복 건너뛰기 ===");
        System.out.println("1부터 10까지 출력하되, 5는 건너뛰기:");
        for (int i = 1; i <= 10; i++) {
            if (i == 5) {
                continue;  // 현재 반복 건너뛰고 다음 반복 계속
            }
            System.out.print(i + " ");
        }
        System.out.println();
        
        System.out.println("\n=== break vs continue 비교 ===");
        System.out.println("break 사용:");
        for (int i = 1; i <= 5; i++) {
            if (i == 3) {
                break;
            }
            System.out.print(i + " ");
        }
        System.out.println(" (3에서 종료)");
        
        System.out.println("continue 사용:");
        for (int i = 1; i <= 5; i++) {
            if (i == 3) {
                continue;
            }
            System.out.print(i + " ");
        }
        System.out.println(" (3만 건너뛰고 계속)");
        
        System.out.println("\n=== 짝수만 출력 (continue 활용) ===");
        for (int i = 1; i <= 10; i++) {
            if (i % 2 != 0) {
                continue;  // 홀수는 건너뛰기
            }
            System.out.print(i + " ");
        }
        System.out.println();
        
        System.out.println("\n=== 3의 배수만 출력 ===");
        for (int i = 1; i <= 20; i++) {
            if (i % 3 != 0) {
                continue;
            }
            System.out.print(i + " ");
        }
        System.out.println();
        
        System.out.println("\n=== 조건 만족 시 종료 (break 활용) ===");
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            sum += i;
            if (sum > 1000) {
                System.out.println("합이 1000을 넘는 순간: " + sum);
                System.out.println("마지막으로 더한 수: " + i);
                break;
            }
        }
        
        System.out.println("\n=== 중첩 반복문에서의 break ===");
        System.out.println("안쪽 반복문만 종료:");
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                if (j == 2) {
                    break;  // 안쪽 for 문만 종료
                }
                System.out.println("i=" + i + ", j=" + j);
            }
        }
        
        System.out.println("\n=== 레이블과 함께 사용하는 break ===");
        outer: for (int i = 1; i <= 3; i++) {
            inner: for (int j = 1; j <= 3; j++) {
                if (j == 2) {
                    break outer;  // outer 반복문 종료
                }
                System.out.println("i=" + i + ", j=" + j);
            }
        }
        System.out.println("outer 반복문 종료");
        
        System.out.println("\n=== 레이블과 함께 사용하는 continue ===");
        outer: for (int i = 1; i <= 3; i++) {
            inner: for (int j = 1; j <= 3; j++) {
                if (j == 2) {
                    continue outer;  // outer의 다음 반복으로
                }
                System.out.println("i=" + i + ", j=" + j);
            }
        }
        
        System.out.println("\n=== while 문에서의 continue (주의) ===");
        int i = 0;
        while (i < 10) {
            i++;  // 증감을 먼저 해야 무한 루프 방지
            if (i % 2 == 0) {
                continue;  // 짝수는 건너뛰기
            }
            System.out.print(i + " ");
        }
        System.out.println();
        
        System.out.println("\n=== 실용적인 예제: 숫자 찾기 ===");
        int target = 7;
        boolean found = false;
        
        for (int num = 1; num <= 20; num++) {
            if (num == target) {
                found = true;
                System.out.println("숫자 " + target + "를 찾았습니다!");
                break;
            }
        }
        
        if (!found) {
            System.out.println("숫자를 찾지 못했습니다.");
        }
        
        System.out.println("\n=== 실용적인 예제: 유효한 입력만 처리 ===");
        // 1부터 10까지 중에서 5보다 작은 수만 출력
        for (int num = 1; num <= 10; num++) {
            if (num >= 5) {
                continue;  // 5 이상은 건너뛰기
            }
            System.out.print(num + " ");
        }
        System.out.println();
    }
}

