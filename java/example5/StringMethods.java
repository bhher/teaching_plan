/**
 * String 주요 메서드를 설명하는 예제
 * 문자열 처리에 자주 사용하는 메서드들을 보여줍니다
 */
public class StringMethods {
    public static void main(String[] args) {
        System.out.println("=== 길이와 빈 문자열 확인 ===");
        String str = "Hello World";
        System.out.println("문자열: \"" + str + "\"");
        System.out.println("길이: " + str.length());
        System.out.println("빈 문자열인가? " + str.isEmpty());
        
        String empty = "";
        System.out.println("\"" + empty + "\"는 빈 문자열인가? " + empty.isEmpty());
        
        System.out.println("\n=== 문자열 검색 ===");
        String text = "Hello World";
        
        // charAt(): 특정 위치의 문자
        System.out.println("charAt(0): " + text.charAt(0));  // 'H'
        System.out.println("charAt(6): " + text.charAt(6));   // 'W'
        
        // indexOf(): 문자열이나 문자의 첫 번째 위치
        System.out.println("indexOf('o'): " + text.indexOf('o'));        // 4
        System.out.println("indexOf(\"World\"): " + text.indexOf("World"));  // 6
        System.out.println("indexOf('x'): " + text.indexOf('x'));      // -1 (없음)
        
        // lastIndexOf(): 마지막 위치
        System.out.println("lastIndexOf('o'): " + text.lastIndexOf('o'));  // 7
        
        // contains(): 포함 여부
        System.out.println("contains(\"Hello\"): " + text.contains("Hello"));  // true
        System.out.println("contains(\"Java\"): " + text.contains("Java"));    // false
        
        System.out.println("\n=== 문자열 추출 ===");
        String str2 = "Hello World";
        
        // substring(): 부분 문자열 추출
        String sub1 = str2.substring(0, 5);   // "Hello" (0부터 5 전까지)
        String sub2 = str2.substring(6);       // "World" (6부터 끝까지)
        System.out.println("substring(0, 5): " + sub1);
        System.out.println("substring(6): " + sub2);
        
        // split(): 문자열 분리
        String date = "2024-01-15";
        String[] dateParts = date.split("-");
        System.out.println("날짜: " + date);
        System.out.print("분리된 부분: ");
        for (String part : dateParts) {
            System.out.print(part + " ");
        }
        System.out.println();
        
        String sentence = "안녕하세요 반갑습니다";
        String[] words = sentence.split(" ");
        System.out.println("문장: " + sentence);
        System.out.print("단어들: ");
        for (String word : words) {
            System.out.print(word + " ");
        }
        System.out.println();
        
        System.out.println("\n=== 문자열 변환 ===");
        String str3 = "Hello World";
        
        // toUpperCase(): 대문자로 변환
        String upper = str3.toUpperCase();
        System.out.println("대문자: " + upper);
        
        // toLowerCase(): 소문자로 변환
        String lower = str3.toLowerCase();
        System.out.println("소문자: " + lower);
        
        // trim(): 앞뒤 공백 제거
        String withSpaces = "  Hello World  ";
        String trimmed = withSpaces.trim();
        System.out.println("원본: \"" + withSpaces + "\"");
        System.out.println("trim 후: \"" + trimmed + "\"");
        
        // replace(): 문자열 치환
        String replaced1 = str3.replace("World", "Java");
        System.out.println("replace(\"World\", \"Java\"): " + replaced1);
        
        String replaced2 = str3.replace('l', 'L');
        System.out.println("replace('l', 'L'): " + replaced2);
        
        System.out.println("\n=== 문자열 비교 ===");
        String str4 = "apple";
        String str5 = "Apple";
        String str6 = "banana";
        
        // equals(): 대소문자 구분 비교
        System.out.println("str4.equals(str5): " + str4.equals(str5));  // false
        
        // equalsIgnoreCase(): 대소문자 무시 비교
        System.out.println("str4.equalsIgnoreCase(str5): " + str4.equalsIgnoreCase(str5));  // true
        
        // compareTo(): 사전식 비교
        System.out.println("str4.compareTo(str6): " + str4.compareTo(str6));  // 음수
        System.out.println("str6.compareTo(str4): " + str6.compareTo(str4));  // 양수
        System.out.println("str4.compareTo(str4): " + str4.compareTo(str4));  // 0
        
        // startsWith(): 접두사 확인
        System.out.println("str4.startsWith(\"app\"): " + str4.startsWith("app"));  // true
        
        // endsWith(): 접미사 확인
        System.out.println("str4.endsWith(\"le\"): " + str4.endsWith("le"));  // true
        
        System.out.println("\n=== 문자열과 숫자 변환 ===");
        // 문자열 → 숫자
        String numStr = "123";
        int num = Integer.parseInt(numStr);
        System.out.println("문자열 \"" + numStr + "\" → 정수: " + num);
        
        String doubleStr = "3.14";
        double dNum = Double.parseDouble(doubleStr);
        System.out.println("문자열 \"" + doubleStr + "\" → 실수: " + dNum);
        
        // 숫자 → 문자열
        int number = 123;
        String strFromNum1 = String.valueOf(number);
        String strFromNum2 = Integer.toString(number);
        String strFromNum3 = number + "";
        System.out.println("정수 " + number + " → 문자열: \"" + strFromNum1 + "\"");
        
        System.out.println("\n=== 실용적인 예제 ===");
        // 이메일 주소에서 도메인 추출
        String email = "user@example.com";
        int atIndex = email.indexOf('@');
        String domain = email.substring(atIndex + 1);
        System.out.println("이메일: " + email);
        System.out.println("도메인: " + domain);
        
        // 파일명에서 확장자 추출
        String filename = "document.pdf";
        int dotIndex = filename.lastIndexOf('.');
        String extension = filename.substring(dotIndex + 1);
        System.out.println("파일명: " + filename);
        System.out.println("확장자: " + extension);
        
        // 문자열 뒤집기
        String original = "Hello";
        String reversed = "";
        for (int i = original.length() - 1; i >= 0; i--) {
            reversed += original.charAt(i);
        }
        System.out.println("원본: " + original);
        System.out.println("뒤집기: " + reversed);
    }
}

