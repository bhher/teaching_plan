/**
 * String 모범 사례 및 주의사항 예제
 * 실전에서 자주 발생하는 실수와 올바른 사용법을 보여줍니다
 */
public class StringBestPractices {
    public static void main(String[] args) {
        System.out.println("=== 1. null 체크 ===");
        
        // ❌ 위험한 코드
        String str = null;
        // int len = str.length();  // NullPointerException 발생!
        
        // ✅ 안전한 코드
        String str2 = null;
        if (str2 != null) {
            int len = str2.length();
            System.out.println("길이: " + len);
        } else {
            System.out.println("문자열이 null입니다.");
        }
        
        // ✅ 더 안전한 코드 (Java 7+)
        String str3 = null;
        if (str3 != null && !str3.isEmpty()) {
            System.out.println("문자열 처리");
        } else {
            System.out.println("문자열이 null이거나 비어있습니다.");
        }
        
        System.out.println("\n=== 2. 문자열 비교 ===");
        
        // ❌ 잘못된 방법
        String str4 = "Hello";
        String str5 = "Hello";
        if (str4 == str5) {  // 참조 비교 - 우연히 true일 수 있음
            System.out.println("== 비교: true (우연히 같음)");
        }
        
        String str6 = new String("Hello");
        if (str4 == str6) {  // false (다른 객체)
            System.out.println("같음");
        } else {
            System.out.println("== 비교: false (다른 객체)");
        }
        
        // ✅ 올바른 방법
        if (str4 != null && str4.equals(str6)) {  // 내용 비교
            System.out.println("equals() 비교: true (내용이 같음)");
        }
        
        // ✅ 더 간단한 방법 (Java 7+)
        if (java.util.Objects.equals(str4, str6)) {  // null 안전
            System.out.println("Objects.equals() 비교: true");
        }
        
        System.out.println("\n=== 3. 많은 문자열 연결 ===");
        
        // ❌ 비효율적
        System.out.println("+ 연산자 사용 (비효율적):");
        long start1 = System.currentTimeMillis();
        String result1 = "";
        for (int i = 0; i < 1000; i++) {
            result1 += i;  // 매번 새 객체 생성
        }
        long end1 = System.currentTimeMillis();
        System.out.println("시간: " + (end1 - start1) + "ms");
        
        // ✅ 효율적
        System.out.println("StringBuilder 사용 (효율적):");
        long start2 = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append(i);
        }
        String result2 = sb.toString();
        long end2 = System.currentTimeMillis();
        System.out.println("시간: " + (end2 - start2) + "ms");
        
        System.out.println("\n=== 4. 문자열 풀 활용 ===");
        
        // ✅ 권장 (문자열 풀 사용)
        String str7 = "Hello";
        String str8 = "Hello";  // 같은 객체 재사용
        System.out.println("리터럴 방식: str7 == str8 ? " + (str7 == str8));  // true
        
        // ❌ 비권장 (새 객체 생성)
        String str9 = new String("Hello");
        String str10 = new String("Hello");  // 다른 객체
        System.out.println("new 방식: str9 == str10 ? " + (str9 == str10));  // false
        
        System.out.println("\n=== 5. 인덱스 범위 확인 ===");
        
        String str11 = "Hello";
        
        // ❌ 위험
        // char ch = str11.charAt(10);  // StringIndexOutOfBoundsException
        
        // ✅ 안전
        int index = 10;
        if (str11.length() > index) {
            char ch = str11.charAt(index);
            System.out.println("문자: " + ch);
        } else {
            System.out.println("인덱스 범위를 벗어났습니다.");
        }
        
        System.out.println("\n=== 6. 정규표현식 사용 시 주의 ===");
        
        String text = "Hello.World";
        
        // ❌ 잘못된 사용
        String[] parts1 = text.split(".");  // 모든 문자로 분리됨
        System.out.println("잘못된 split(\".\"): " + parts1.length + "개");
        
        // ✅ 올바른 사용
        String[] parts2 = text.split("\\.");  // 점(.)으로 분리
        System.out.println("올바른 split(\"\\\\.\"): " + parts2.length + "개");
        for (String part : parts2) {
            System.out.println("  - " + part);
        }
        
        System.out.println("\n=== 7. 빈 문자열과 공백 문자열 ===");
        
        String empty = "";
        String blank = "   ";
        String normal = "Hello";
        
        System.out.println("빈 문자열 isEmpty(): " + empty.isEmpty());  // true
        System.out.println("공백 문자열 isEmpty(): " + blank.isEmpty());  // false
        System.out.println("공백 문자열 trim().isEmpty(): " + blank.trim().isEmpty());  // true
        
        // Java 11+
        // System.out.println("공백 문자열 isBlank(): " + blank.isBlank());  // true
    }
}
