/**
 * try-catch-finally를 설명하는 예제
 * 여러 catch 블록과 finally 블록의 사용법을 보여줍니다
 */
public class TryCatchFinally {
    public static void main(String[] args) {
        System.out.println("=== try-catch-finally 예제 ===\n");
        
        // 예제 1: 단일 catch 블록
        System.out.println("--- 예제 1: 단일 catch 블록 ---");
        try {
            int result = 10 / 0;
        } catch (ArithmeticException e) {
            System.out.println("산술 연산 예외 발생");
        }
        System.out.println("프로그램 계속 실행");
        
        // 예제 2: 여러 catch 블록
        System.out.println("\n--- 예제 2: 여러 catch 블록 ---");
        try {
            String str = null;
            int length = str.length();
            int result = 10 / 0;
        } catch (NullPointerException e) {
            System.out.println("null 참조 예외 처리");
        } catch (ArithmeticException e) {
            System.out.println("산술 연산 예외 처리");
        } catch (Exception e) {
            System.out.println("기타 예외 처리: " + e.getMessage());
        }
        
        // 예제 3: finally 블록 (예외 발생)
        System.out.println("\n--- 예제 3: finally 블록 (예외 발생) ---");
        try {
            int[] numbers = {1, 2, 3};
            System.out.println(numbers[10]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("예외 발생: " + e.getMessage());
        } finally {
            System.out.println("finally 블록 실행 (예외 발생 시)");
        }
        
        // 예제 4: finally 블록 (정상 실행)
        System.out.println("\n--- 예제 4: finally 블록 (정상 실행) ---");
        try {
            int[] numbers = {1, 2, 3};
            System.out.println("인덱스 1: " + numbers[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("예외 발생");
        } finally {
            System.out.println("finally 블록 실행 (정상 실행 시)");
        }
        
        // 예제 5: 리소스 정리 (finally 사용)
        System.out.println("\n--- 예제 5: 리소스 정리 (finally 사용) ---");
        String resource = "리소스";
        try {
            System.out.println(resource + " 사용 중");
            int result = 10 / 0;  // 예외 발생
        } catch (ArithmeticException e) {
            System.out.println("예외 발생");
        } finally {
            System.out.println(resource + " 정리 완료");
        }
        
        // 예제 6: catch 블록 순서
        System.out.println("\n--- 예제 6: catch 블록 순서 ---");
        try {
            int[] numbers = {1, 2, 3};
            System.out.println(numbers[10]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("자식 예외 처리 (먼저)");
        } catch (RuntimeException e) {
            System.out.println("부모 예외 처리 (나중에)");
        } catch (Exception e) {
            System.out.println("최상위 예외 처리 (가장 마지막)");
        }
        
        // 예제 7: 중첩 try-catch
        System.out.println("\n--- 예제 7: 중첩 try-catch ---");
        try {
            try {
                int result = 10 / 0;
            } catch (ArithmeticException e) {
                System.out.println("내부 예외 처리");
                throw e;  // 예외 재발생
            }
        } catch (ArithmeticException e) {
            System.out.println("외부 예외 처리");
        }
        
        System.out.println("\n=== finally 블록의 특징 ===");
        System.out.println("1. 예외 발생 여부와 관계없이 항상 실행");
        System.out.println("2. 리소스 정리에 주로 사용");
        System.out.println("3. return 문이 있어도 실행됨");
    }
}

