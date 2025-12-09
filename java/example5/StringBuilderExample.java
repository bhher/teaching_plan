/**
 * StringBuilder를 설명하는 예제
 * 문자열을 자주 변경할 때 사용하는 StringBuilder를 보여줍니다
 */
public class StringBuilderExample {
    public static void main(String[] args) {
        System.out.println("=== StringBuilder 기본 사용 ===");
        
        // StringBuilder 생성
        StringBuilder sb = new StringBuilder();
        
        // append(): 문자열 추가
        sb.append("Hello");
        sb.append(" ");
        sb.append("World");
        
        String result = sb.toString();
        System.out.println("결과: " + result);
        
        System.out.println("\n=== 체이닝 (Chaining) ===");
        StringBuilder sb2 = new StringBuilder();
        String result2 = sb2.append("안녕")
                            .append("하세요")
                            .append(" ")
                            .append("Java")
                            .toString();
        System.out.println("결과: " + result2);
        
        System.out.println("\n=== StringBuilder vs String 성능 비교 ===");
        
        // String 사용 (비효율적)
        long start1 = System.currentTimeMillis();
        String str = "";
        for (int i = 0; i < 10000; i++) {
            str += "a";  // 매번 새로운 String 객체 생성
        }
        long end1 = System.currentTimeMillis();
        System.out.println("String 사용 시간: " + (end1 - start1) + "ms");
        
        // StringBuilder 사용 (효율적)
        long start2 = System.currentTimeMillis();
        StringBuilder sb3 = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            sb3.append("a");  // 같은 객체에 추가
        }
        String result3 = sb3.toString();
        long end2 = System.currentTimeMillis();
        System.out.println("StringBuilder 사용 시간: " + (end2 - start2) + "ms");
        
        System.out.println("\n=== StringBuilder 주요 메서드 ===");
        StringBuilder sb4 = new StringBuilder("Hello");
        
        // insert(): 특정 위치에 삽입
        sb4.insert(5, " World");
        System.out.println("insert(5, \" World\"): " + sb4);
        
        // delete(): 삭제
        sb4.delete(5, 11);  // 5부터 11 전까지 삭제
        System.out.println("delete(5, 11): " + sb4);
        
        // replace(): 치환
        sb4.replace(0, 5, "Hi");
        System.out.println("replace(0, 5, \"Hi\"): " + sb4);
        
        // reverse(): 뒤집기
        sb4.reverse();
        System.out.println("reverse(): " + sb4);
        
        // length(), capacity()
        StringBuilder sb5 = new StringBuilder("Java");
        System.out.println("\n문자열: " + sb5);
        System.out.println("길이: " + sb5.length());
        System.out.println("용량: " + sb5.capacity());
        
        System.out.println("\n=== 실용적인 예제 ===");
        // 여러 문자열을 효율적으로 연결
        String[] words = {"안녕", "하세요", "Java", "프로그래밍"};
        StringBuilder sb6 = new StringBuilder();
        
        for (int i = 0; i < words.length; i++) {
            sb6.append(words[i]);
            if (i < words.length - 1) {
                sb6.append(" ");
            }
        }
        System.out.println("결과: " + sb6.toString());
        
        // HTML 태그 생성
        StringBuilder html = new StringBuilder();
        html.append("<div>");
        html.append("<h1>제목</h1>");
        html.append("<p>내용</p>");
        html.append("</div>");
        System.out.println("\nHTML:");
        System.out.println(html.toString());
    }
}

