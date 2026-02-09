/**
 * 예제 2: 문자열 검색 및 추출
 * 문자열 검색, 부분 문자열 추출, 분리 방법을 보여줍니다
 */
public class StringExample2 {
    public static void main(String[] args) {
        String text = "Hello World Java";
        
        // 검색
        System.out.println("'o'의 위치: " + text.indexOf('o'));  // 4
        System.out.println("'World' 포함: " + text.contains("World"));  // true
        
        // 추출
        System.out.println("0~5: " + text.substring(0, 5));  // "Hello"
        System.out.println("6부터: " + text.substring(6));    // "World Java"
        
        // 분리
        String[] words = text.split(" ");
        System.out.println("\n단어 분리:");
        for (String word : words) {
            System.out.println(word);
        }
        // 출력:
        // Hello
        // World
        // Java
    }
}
