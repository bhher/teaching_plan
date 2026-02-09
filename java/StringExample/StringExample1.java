/**
 * 예제 1: 기본 String 사용
 * String 생성, 연결, 기본 메서드 사용법을 보여줍니다
 */
public class StringExample1 {
    public static void main(String[] args) {
        // String 생성
        String str1 = "Hello";
        String str2 = new String("World");
        
        // 문자열 연결
        String result = str1 + " " + str2;
        System.out.println(result);  // "Hello World"
        
        // 길이 확인
        System.out.println("길이: " + result.length());  // 11
        
        // 대소문자 변환
        System.out.println(result.toUpperCase());  // "HELLO WORLD"
        System.out.println(result.toLowerCase());  // "hello world"
    }
}
