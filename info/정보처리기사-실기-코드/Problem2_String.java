package info.exam;

import java.util.ArrayList;
import java.util.List;

/**
 * 문제 2: 문자열 처리 알고리즘
 */
public class Problem2_String {
    public static void main(String[] args) {
        String text = "Hello World Java Programming";
        
        // 1. 공백 기준으로 단어 분리
        String[] words = text.split(" ");
        System.out.println("단어 배열: " + java.util.Arrays.toString(words));
        
        // 2. 각 단어의 길이 계산
        int[] lengths = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            lengths[i] = words[i].length();
        }
        System.out.println("각 단어의 길이: " + java.util.Arrays.toString(lengths));
        
        // 3. 대문자로 시작하는 단어 필터링
        List<String> upperWords = new ArrayList<>();
        for (String word : words) {
            if (word.length() > 0 && Character.isUpperCase(word.charAt(0))) {
                upperWords.add(word);
            }
        }
        System.out.println("대문자로 시작하는 단어: " + upperWords);
        
        // 4. 모든 단어를 역순으로 출력
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            String reversed = reverseString(words[i]);
            result.append(reversed);
            if (i < words.length - 1) {
                result.append(" ");
            }
        }
        System.out.println("역순 문자열: " + result.toString());
    }
    
    // 문자열을 역순으로 만드는 메서드
    private static String reverseString(String str) {
        return new StringBuilder(str).reverse().toString();
    }
}
