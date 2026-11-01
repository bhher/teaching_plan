import java.util.HashMap;
import java.util.Map;

/** Map(HashMap) 기본 예제 */
public class MapExample {
    public static void main(String[] args) {
        System.out.println("=== Map / HashMap ===\n");

        HashMap<String, Integer> scores = new HashMap<>();
        scores.put("홍길동", 85);
        scores.put("김철수", 90);
        scores.put("이영희", 88);
        scores.put("홍길동", 95);  // 같은 키 → 값 덮어씀

        System.out.println("홍길동 점수: " + scores.get("홍길동"));  // 95
        System.out.println("박민수(없으면): " + scores.getOrDefault("박민수", 0));
        System.out.println("키 개수: " + scores.size());

        System.out.println("\n--- keySet ---");
        for (String name : scores.keySet()) {
            System.out.println(name + " → " + scores.get(name));
        }

        System.out.println("\n--- entrySet ---");
        for (Map.Entry<String, Integer> e : scores.entrySet()) {
            System.out.println(e.getKey() + ": " + e.getValue() + "점");
        }

        // 단어 빈도
        System.out.println("\n=== 단어 빈도 ===");
        String[] words = {"사과", "바나나", "사과", "오렌지", "바나나", "사과"};
        HashMap<String, Integer> freq = new HashMap<>();
        for (String w : words) {
            freq.put(w, freq.getOrDefault(w, 0) + 1);
        }
        for (Map.Entry<String, Integer> e : freq.entrySet()) {
            System.out.println(e.getKey() + ": " + e.getValue() + "번");
        }
    }
}
