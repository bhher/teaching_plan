/**
 * throw와 throws를 설명하는 예제
 * 예외를 발생시키고 전파하는 방법을 보여줍니다
 */
public class ThrowThrows {
    // throw 예제: 예외 발생
    public static void checkAge(int age) {
        if (age < 0 || age > 150) {
            throw new IllegalArgumentException("나이는 0 이상 150 이하여야 합니다. 입력값: " + age);
        }
        System.out.println("유효한 나이: " + age);
    }
    
    // throws 예제: 예외 선언
    public static void divide(int a, int b) throws ArithmeticException {
        if (b == 0) {
            throw new ArithmeticException("0으로 나눌 수 없습니다.");
        }
        System.out.println("결과: " + (a / b));
    }
    
    // 여러 예외 선언
    public static void processNumber(String str) throws NumberFormatException, NullPointerException {
        if (str == null) {
            throw new NullPointerException("문자열이 null입니다.");
        }
        int number = Integer.parseInt(str);
        System.out.println("숫자: " + number);
    }
    
    // Checked Exception 선언
    public static void readFile(String filename) throws Exception {
        if (filename == null || filename.isEmpty()) {
            throw new Exception("파일명이 비어있습니다.");
        }
        System.out.println("파일 읽기: " + filename);
    }
    
    public static void main(String[] args) {
        System.out.println("=== throw 예제 ===\n");
        
        // 예제 1: throw로 예외 발생
        System.out.println("--- 예제 1: throw로 예외 발생 ---");
        try {
            checkAge(25);  // 정상
            checkAge(-5);  // 예외 발생
        } catch (IllegalArgumentException e) {
            System.out.println("예외 처리: " + e.getMessage());
        }
        
        // 예제 2: throw로 즉시 예외 발생
        System.out.println("\n--- 예제 2: throw로 즉시 예외 발생 ---");
        try {
            throw new RuntimeException("직접 예외 발생");
        } catch (RuntimeException e) {
            System.out.println("예외 처리: " + e.getMessage());
        }
        
        System.out.println("\n=== throws 예제 ===\n");
        
        // 예제 3: throws로 예외 전파
        System.out.println("--- 예제 3: throws로 예외 전파 ---");
        try {
            divide(10, 2);  // 정상
            divide(10, 0);  // 예외 발생
        } catch (ArithmeticException e) {
            System.out.println("예외 처리: " + e.getMessage());
        }
        
        // 예제 4: 여러 예외 처리
        System.out.println("\n--- 예제 4: 여러 예외 처리 ---");
        try {
            processNumber("123");  // 정상
            processNumber(null);   // NullPointerException
        } catch (NumberFormatException e) {
            System.out.println("숫자 형식 예외: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("null 참조 예외: " + e.getMessage());
        }
        
        // 예제 5: Checked Exception 처리
        System.out.println("\n--- 예제 5: Checked Exception 처리 ---");
        try {
            readFile("data.txt");  // 정상
            readFile("");           // 예외 발생
        } catch (Exception e) {
            System.out.println("예외 처리: " + e.getMessage());
        }
        
        // 예제 6: 예외 재발생
        System.out.println("\n--- 예제 6: 예외 재발생 ---");
        try {
            try {
                divide(10, 0);
            } catch (ArithmeticException e) {
                System.out.println("내부 예외 처리");
                throw e;  // 예외 재발생
            }
        } catch (ArithmeticException e) {
            System.out.println("외부 예외 처리: " + e.getMessage());
        }
        
        // 예제 7: 예외 변환
        System.out.println("\n--- 예제 7: 예외 변환 ---");
        try {
            try {
                int number = Integer.parseInt("abc");
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("잘못된 입력값", e);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("변환된 예외: " + e.getMessage());
        }
        
        System.out.println("\n=== throw vs throws ===");
        System.out.println("throw: 예외를 발생시킴 (메서드 내부)");
        System.out.println("throws: 예외를 선언함 (메서드 선언부)");
    }
}

