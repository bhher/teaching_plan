import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;
import java.util.Map;

/**
 * Map 인터페이스를 설명하는 예제
 * HashMap, LinkedHashMap, TreeMap의 사용법을 보여줍니다
 */
public class MapExample {
    public static void main(String[] args) {
        System.out.println("=== HashMap 예제 ===\n");
        
        // HashMap 생성
        HashMap<String, Integer> scores = new HashMap<>();
        
        // 요소 추가
        scores.put("홍길동", 85);
        scores.put("김철수", 90);
        scores.put("이영희", 88);
        scores.put("박민수", 92);
        
        System.out.println("초기 맵: " + scores);
        
        // 값 가져오기
        int score = scores.get("홍길동");
        System.out.println("홍길동의 점수: " + score);
        
        // 값 수정 (같은 키로 put)
        scores.put("홍길동", 95);
        System.out.println("홍길동 점수 수정 후: " + scores);
        
        // 키 존재 확인
        System.out.println("홍길동 키 존재: " + scores.containsKey("홍길동"));
        System.out.println("최지영 키 존재: " + scores.containsKey("최지영"));
        
        // 값 존재 확인
        System.out.println("90 점수 존재: " + scores.containsValue(90));
        
        // 요소 삭제
        scores.remove("박민수");
        System.out.println("박민수 삭제 후: " + scores);
        
        // 크기 확인
        System.out.println("맵 크기: " + scores.size());
        
        // 비어있는지 확인
        System.out.println("비어있는가? " + scores.isEmpty());
        
        System.out.println("\n=== 맵 순회 방법 ===\n");
        
        // 방법 1: keySet() 사용
        System.out.println("--- keySet()으로 순회 ---");
        for (String name : scores.keySet()) {
            System.out.println(name + ": " + scores.get(name) + "점");
        }
        
        // 방법 2: values() 사용
        System.out.println("\n--- values()로 순회 ---");
        for (Integer scoreValue : scores.values()) {
            System.out.println("점수: " + scoreValue);
        }
        
        // 방법 3: entrySet() 사용 (권장)
        System.out.println("\n--- entrySet()으로 순회 ---");
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + "점");
        }
        
        System.out.println("\n=== LinkedHashMap 예제 ===\n");
        
        // LinkedHashMap 생성 (삽입 순서 유지)
        LinkedHashMap<String, Integer> linkedMap = new LinkedHashMap<>();
        
        linkedMap.put("사과", 1000);
        linkedMap.put("바나나", 2000);
        linkedMap.put("오렌지", 1500);
        linkedMap.put("포도", 3000);
        
        System.out.println("LinkedHashMap: " + linkedMap);
        System.out.println("삽입 순서가 유지됨");
        
        System.out.println("\n순회:");
        for (String key : linkedMap.keySet()) {
            System.out.println(key + ": " + linkedMap.get(key) + "원");
        }
        
        System.out.println("\n=== TreeMap 예제 ===\n");
        
        // TreeMap 생성 (키 정렬)
        TreeMap<String, Integer> treeMap = new TreeMap<>();
        
        treeMap.put("사과", 1000);
        treeMap.put("바나나", 2000);
        treeMap.put("오렌지", 1500);
        treeMap.put("포도", 3000);
        
        System.out.println("TreeMap: " + treeMap);
        System.out.println("키가 정렬된 순서");
        
        System.out.println("\n순회:");
        for (String key : treeMap.keySet()) {
            System.out.println(key + ": " + treeMap.get(key) + "원");
        }
        
        // 첫 번째/마지막 키
        System.out.println("첫 번째 키: " + treeMap.firstKey());
        System.out.println("마지막 키: " + treeMap.lastKey());
        
        System.out.println("\n=== 단어 빈도 계산 예제 ===\n");
        
        String[] words = {"사과", "바나나", "사과", "오렌지", "바나나", "사과", "포도"};
        
        HashMap<String, Integer> frequency = new HashMap<>();
        
        for (String word : words) {
            if (frequency.containsKey(word)) {
                // 이미 존재하면 카운트 증가
                frequency.put(word, frequency.get(word) + 1);
            } else {
                // 처음이면 1로 설정
                frequency.put(word, 1);
            }
        }
        
        System.out.println("단어 빈도:");
        for (Map.Entry<String, Integer> entry : frequency.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + "번");
        }
        
        System.out.println("\n=== Map 인터페이스로 사용 ===\n");
        
        // Map 인터페이스 타입으로 선언 (다형성)
        Map<String, Integer> map = new HashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        System.out.println("Map 인터페이스: " + map);
        
        // TreeMap으로 변경 가능
        map = new TreeMap<>();
        map.put("Z", 26);
        map.put("X", 24);
        map.put("Y", 25);
        System.out.println("TreeMap으로 변경: " + map);
        
        System.out.println("\n=== Map의 특징 ===");
        System.out.println("1. 키-값 쌍으로 저장");
        System.out.println("2. 키는 중복 불가, 값은 중복 가능");
        System.out.println("3. HashMap: 순서 보장 안 됨, 빠른 검색");
        System.out.println("4. LinkedHashMap: 삽입 순서 유지");
        System.out.println("5. TreeMap: 키가 정렬된 순서");
    }
}

