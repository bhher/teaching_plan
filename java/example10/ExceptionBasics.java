/**
 * 예외 처리 기본을 설명하는 예제
 * try-catch 문의 기본 사용법을 보여줍니다
 */
public class ExceptionBasics {
    public static void main(String[] args) {
        System.out.println("=== 예외 처리 기본 예제 ===\n");
        
        // 예제 1: ArrayIndexOutOfBoundsException
        System.out.println("--- 예제 1: 배열 인덱스 예외 ---");
        try {
            int[] numbers = {1, 2, 3, 4, 5};
            System.out.println("인덱스 2: " + numbers[2]);  // 정상
            System.out.println("인덱스 10: " + numbers[10]);  // 예외 발생
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("배열 인덱스가 범위를 벗어났습니다.");
            System.out.println("예외 메시지: " + e.getMessage());
        }
        
        // 예제 2: NullPointerException
        System.out.println("\n--- 예제 2: null 참조 예외 ---");
        try {
            String str = null;
            int length = str.length();  // 예외 발생
        } catch (NullPointerException e) {
            System.out.println("null 객체에 접근했습니다.");
            System.out.println("예외 메시지: " + e.getMessage());
        }
        
        // 예제 3: ArithmeticException
        System.out.println("\n--- 예제 3: 산술 연산 예외 ---");
        try {
            int result = 10 / 0;  // 예외 발생
        } catch (ArithmeticException e) {
            System.out.println("0으로 나눌 수 없습니다.");
            System.out.println("예외 메시지: " + e.getMessage());
        }
        
        // 예제 4: NumberFormatException
        System.out.println("\n--- 예제 4: 숫자 형식 예외 ---");
        try {
            String str = "abc";
            int number = Integer.parseInt(str);  // 예외 발생
        } catch (NumberFormatException e) {
            System.out.println("숫자로 변환할 수 없는 문자열입니다.");
            System.out.println("예외 메시지: " + e.getMessage());
        }
        
        // 예제 5: 정상 실행
        System.out.println("\n--- 예제 5: 정상 실행 ---");
        try {
            int[] numbers = {1, 2, 3};
            System.out.println("인덱스 1: " + numbers[1]);  // 정상
            System.out.println("정상적으로 실행되었습니다.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("예외 발생");
        }
        
        // 예제 6: 예외 정보 출력
        System.out.println("\n--- 예제 6: 예외 정보 출력 ---");
        try {
            int[] numbers = {1, 2, 3};
            System.out.println(numbers[10]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("예외 클래스: " + e.getClass().getName());
            System.out.println("예외 메시지: " + e.getMessage());
            System.out.println("\n스택 트레이스:");
            e.printStackTrace();
        }
        
        System.out.println("\n=== 예외 처리의 장점 ===");
        System.out.println("1. 프로그램이 중단되지 않고 계속 실행됨");
        System.out.println("2. 사용자에게 친절한 오류 메시지 제공");
        System.out.println("3. 예외 상황을 적절히 처리 가능");
    }
}

