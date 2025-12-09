/**
 * while 반복문을 설명하는 예제
 * while과 do-while의 차이를 보여줍니다
 */
public class WhileLoop {
    public static void main(String[] args) {
        System.out.println("=== 기본 while 문 ===");
        int i = 1;
        while (i <= 10) {
            System.out.print(i + " ");
            i++;  // 증감식을 직접 작성해야 함
        }
        System.out.println();
        
        System.out.println("\n=== 역순 출력 ===");
        int j = 10;
        while (j >= 1) {
            System.out.print(j + " ");
            j--;
        }
        System.out.println();
        
        System.out.println("\n=== 합계 계산 ===");
        int sum = 0;
        int num = 1;
        while (num <= 100) {
            sum += num;
            num++;
        }
        System.out.println("1부터 100까지의 합: " + sum);
        
        System.out.println("\n=== 조건 기반 반복 ===");
        int count = 0;
        int randomValue = 0;
        while (randomValue != 7) {
            randomValue = (int)(Math.random() * 10) + 1;  // 1~10 랜덤
            count++;
            System.out.println("시도 " + count + ": " + randomValue);
        }
        System.out.println("7이 나올 때까지 " + count + "번 시도했습니다.");
        
        System.out.println("\n=== do-while 문 ===");
        int k = 1;
        do {
            System.out.print(k + " ");
            k++;
        } while (k <= 10);
        System.out.println();
        
        System.out.println("\n=== do-while의 특징 (최소 1번 실행) ===");
        int m = 10;
        do {
            System.out.println("m = " + m);  // 조건이 false여도 한 번은 실행
            m++;
        } while (m < 5);
        System.out.println("최종 m = " + m);
        
        System.out.println("\n=== while vs do-while 비교 ===");
        
        // while: 조건이 false이면 실행 안 됨
        int x = 10;
        System.out.println("while 문 (x = 10, x < 5):");
        while (x < 5) {
            System.out.println("실행됨");
            x++;
        }
        System.out.println("실행 안 됨");
        
        // do-while: 조건이 false여도 한 번은 실행
        int y = 10;
        System.out.println("\ndo-while 문 (y = 10, y < 5):");
        do {
            System.out.println("실행됨 (최소 1번)");
            y++;
        } while (y < 5);
        
        System.out.println("\n=== 사용자 입력 시뮬레이션 ===");
        // 실제로는 Scanner를 사용하지만, 여기서는 시뮬레이션
        int userInput = 0;
        int attempts = 0;
        
        // 사용자가 5를 입력할 때까지 반복 (시뮬레이션)
        while (userInput != 5) {
            attempts++;
            userInput = (int)(Math.random() * 10) + 1;  // 1~10 랜덤
            System.out.println("시도 " + attempts + ": 입력값 = " + userInput);
            if (userInput == 5) {
                System.out.println("정답입니다!");
            }
        }
        
        System.out.println("\n=== 무한 루프 (주의!) ===");
        // 주석 처리: 실제로 실행하면 무한 반복
        /*
        while (true) {
            System.out.println("무한 반복");
        }
        */
        
        // break를 사용한 무한 루프 제어
        int counter = 0;
        while (true) {
            counter++;
            System.out.print(counter + " ");
            if (counter >= 5) {
                break;  // 무한 루프 탈출
            }
        }
        System.out.println("\n루프 종료");
    }
}

