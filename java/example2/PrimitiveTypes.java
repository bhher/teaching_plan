/**
 * 기본 자료형을 설명하는 예제
 * 정수형, 실수형, 문자형, 논리형을 모두 사용합니다
 */
public class PrimitiveTypes {
    public static void main(String[] args) {
        // 정수형
        byte byteValue = 100;
        short shortValue = 1000;
        int intValue = 100000;
        long longValue = 10000000000L;  // L 또는 l을 붙여야 함
        
        // 실수형
        float floatValue = 3.14f;      // f 또는 F를 붙여야 함
        double doubleValue = 3.141592653589793;
        
        // 문자형
        char charValue = 'A';
        char koreanChar = '가';
        char unicodeChar = '\u0041';  // 유니코드로 'A' 표현
        
        // 논리형
        boolean isTrue = true;
        boolean isFalse = false;
        boolean result = (10 > 5);  // true
        
        // 출력
        System.out.println("=== 정수형 ===");
        System.out.println("byte: " + byteValue);
        System.out.println("short: " + shortValue);
        System.out.println("int: " + intValue);
        System.out.println("long: " + longValue);
        
        System.out.println("\n=== 실수형 ===");
        System.out.println("float: " + floatValue);
        System.out.println("double: " + doubleValue);
        
        System.out.println("\n=== 문자형 ===");
        System.out.println("char: " + charValue);
        System.out.println("한글: " + koreanChar);
        System.out.println("유니코드: " + unicodeChar);
        
        System.out.println("\n=== 논리형 ===");
        System.out.println("isTrue: " + isTrue);
        System.out.println("isFalse: " + isFalse);
        System.out.println("result: " + result);
    }
}

