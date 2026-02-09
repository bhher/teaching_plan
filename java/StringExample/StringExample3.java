/**
 * 예제 3: 문자열 비교
 * == 연산자와 equals() 메서드의 차이를 보여줍니다
 */
public class StringExample3 {
    public static void main(String[] args) {
        String str1 = "Hello";
        String str2 = "Hello";
        String str3 = new String("Hello");
        
        System.out.println("=== 참조 비교 (==) ===");
        System.out.println("str1 == str2: " + (str1 == str2));      // true (같은 객체)
        System.out.println("str1 == str3: " + (str1 == str3));      // false (다른 객체)
        
        System.out.println("\n=== 내용 비교 (equals()) ===");
        System.out.println("str1.equals(str2): " + str1.equals(str2));  // true
        System.out.println("str1.equals(str3): " + str1.equals(str3));    // true
        
        System.out.println("\n=== 대소문자 무시 비교 ===");
        String str4 = "HELLO";
        System.out.println("str1.equals(str4): " + str1.equals(str4));  // false
        System.out.println("str1.equalsIgnoreCase(str4): " + str1.equalsIgnoreCase(str4));  // true
    }
}
