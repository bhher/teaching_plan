/**
 * 형 변환을 설명하는 예제
 * 자동 형 변환과 강제 형 변환을 보여줍니다
 */
public class TypeConversion {
    public static void main(String[] args) {
        System.out.println("=== 자동 형 변환 ===");
        
        // int → double (자동 변환)
        int intValue = 100;
        double doubleValue = intValue;  // 자동 변환
        System.out.println("int: " + intValue + " → double: " + doubleValue);
        
        // long → float (자동 변환)
        long longValue = 1000000L;
        float floatValue = longValue;  // 자동 변환
        System.out.println("long: " + longValue + " → float: " + floatValue);
        
        // char → int (자동 변환, ASCII 값)
        char ch = 'A';
        int ascii = ch;  // 자동 변환: 65
        System.out.println("char '" + ch + "' → int: " + ascii);
        
        System.out.println("\n=== 강제 형 변환 ===");
        
        // double → int (강제 변환, 소수점 손실)
        double pi = 3.14159;
        int intPi = (int) pi;  // 강제 변환
        System.out.println("double: " + pi + " → int: " + intPi);
        
        // int → char (강제 변환)
        int num = 66;
        char character = (char) num;  // 강제 변환: 'B'
        System.out.println("int: " + num + " → char: '" + character + "'");
        
        // 큰 범위 → 작은 범위 (데이터 손실 가능)
        int bigNum = 300;
        byte smallByte = (byte) bigNum;  // byte 범위(-128~127)를 초과
        System.out.println("int: " + bigNum + " → byte: " + smallByte + " (데이터 손실!)");
        
        System.out.println("\n=== 실용적인 예제 ===");
        
        // 정수 나눗셈을 실수로 변환
        int a = 10;
        int b = 3;
        double result1 = a / b;  // 정수 나눗셈 결과: 3.0
        double result2 = (double) a / b;  // 실수 나눗셈 결과: 3.333...
        
        System.out.println("10 / 3 (정수): " + result1);
        System.out.println("10 / 3 (실수): " + result2);
    }
}

