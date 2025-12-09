import java.util.Scanner;

/**
 * 예외 처리 설계 방법을 설명하는 예제
 * 예외 처리 원칙과 패턴을 보여줍니다
 */
public class ExceptionDesign {
    // 예제 1: 구체적인 예외 처리
    public static void example1() {
        System.out.println("--- 예제 1: 구체적인 예외 처리 ---");
        try {
            String str = null;
            int[] numbers = {1, 2, 3};
            int result = 10 / 0;
        } catch (NullPointerException e) {
            System.out.println("null 참조 예외 처리");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("배열 인덱스 예외 처리");
        } catch (ArithmeticException e) {
            System.out.println("산술 연산 예외 처리");
        } catch (Exception e) {
            System.out.println("기타 예외 처리: " + e.getMessage());
        }
    }
    
    // 예제 2: 적절한 예외 메시지
    public static void example2() {
        System.out.println("\n--- 예제 2: 적절한 예외 메시지 ---");
        try {
            int age = -5;
            if (age < 0) {
                throw new IllegalArgumentException(
                    "나이는 0 이상이어야 합니다. 입력값: " + age
                );
            }
        } catch (IllegalArgumentException e) {
            System.out.println("예외 메시지: " + e.getMessage());
        }
    }
    
    // 예제 3: 예외를 숨기지 않기
    public static void example3() {
        System.out.println("\n--- 예제 3: 예외를 숨기지 않기 ---");
        try {
            int[] numbers = {1, 2, 3};
            System.out.println(numbers[10]);
        } catch (ArrayIndexOutOfBoundsException e) {
            // 예외를 로깅하거나 처리
            System.err.println("예외 발생: " + e.getMessage());
            System.err.println("예외 위치: " + e.getClass().getName());
            e.printStackTrace();
        }
    }
    
    // 예제 4: 리소스 정리
    public static void example4() {
        System.out.println("\n--- 예제 4: 리소스 정리 ---");
        String resource = "파일 리소스";
        try {
            System.out.println(resource + " 사용 중");
            int result = 10 / 0;  // 예외 발생
        } catch (ArithmeticException e) {
            System.out.println("예외 발생: " + e.getMessage());
        } finally {
            System.out.println(resource + " 정리 완료");
        }
    }
    
    // 예제 5: 예외 전파
    public static void example5() throws Exception {
        System.out.println("\n--- 예제 5: 예외 전파 ---");
        method1();
    }
    
    public static void method1() throws Exception {
        method2();
    }
    
    public static void method2() throws Exception {
        throw new Exception("예외가 전파됩니다.");
    }
    
    // 예제 6: 예외 변환
    public static void example6() {
        System.out.println("\n--- 예제 6: 예외 변환 ---");
        try {
            try {
                int number = Integer.parseInt("abc");
            } catch (NumberFormatException e) {
                // 다른 예외로 변환
                throw new IllegalArgumentException("잘못된 입력값입니다.", e);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("변환된 예외: " + e.getMessage());
            System.out.println("원본 예외: " + e.getCause().getClass().getName());
        }
    }
    
    // 예제 7: 예외 복구 (재시도)
    public static void example7() {
        System.out.println("\n--- 예제 7: 예외 복구 (재시도) ---");
        int retryCount = 0;
        int maxRetries = 3;
        boolean success = false;
        
        while (retryCount < maxRetries && !success) {
            try {
                // 시뮬레이션: 랜덤하게 성공/실패
                if (Math.random() > 0.5) {
                    System.out.println("작업 성공!");
                    success = true;
                } else {
                    throw new RuntimeException("작업 실패");
                }
            } catch (RuntimeException e) {
                retryCount++;
                System.out.println("재시도 " + retryCount + "/" + maxRetries);
                if (retryCount >= maxRetries) {
                    System.out.println("최대 재시도 횟수 초과");
                }
            }
        }
    }
    
    // 예제 8: 입력 검증
    public static void example8() {
        System.out.println("\n--- 예제 8: 입력 검증 ---");
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            try {
                System.out.print("나이를 입력하세요 (0-150): ");
                int age = Integer.parseInt(scanner.nextLine());
                
                if (age < 0 || age > 150) {
                    throw new IllegalArgumentException("나이는 0-150 범위여야 합니다.");
                }
                
                System.out.println("입력된 나이: " + age);
                break;  // 정상 입력 시 루프 종료
                
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력해주세요.");
            } catch (IllegalArgumentException e) {
                System.out.println("오류: " + e.getMessage());
            }
        }
        scanner.close();
    }
    
    public static void main(String[] args) {
        System.out.println("=== 예외 처리 설계 방법 예제 ===\n");
        
        example1();
        example2();
        example3();
        example4();
        
        try {
            example5();
        } catch (Exception e) {
            System.out.println("전파된 예외 처리: " + e.getMessage());
        }
        
        example6();
        example7();
        // example8();  // 사용자 입력이 필요하므로 주석 처리
        
        System.out.println("\n=== 예외 처리 원칙 ===");
        System.out.println("1. 구체적인 예외 처리");
        System.out.println("2. 적절한 예외 메시지");
        System.out.println("3. 예외를 숨기지 않기");
        System.out.println("4. 리소스 정리 (finally)");
        System.out.println("5. 예외 전파 vs 예외 처리 선택");
        System.out.println("6. 예외 변환으로 의미 있는 예외 제공");
    }
}

