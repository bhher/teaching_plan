import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Iterator를 설명하는 예제
 * 컬렉션을 순회하는 다양한 방법을 보여줍니다
 */
public class IteratorExample {
    public static void main(String[] args) {
        System.out.println("=== Iterator 기본 사용 ===\n");
        
        // ArrayList로 Iterator 사용
        ArrayList<String> list = new ArrayList<>();
        list.add("사과");
        list.add("바나나");
        list.add("오렌지");
        list.add("포도");
        
        System.out.println("--- ArrayList Iterator ---");
        Iterator<String> listIterator = list.iterator();
        
        while (listIterator.hasNext()) {
            String fruit = listIterator.next();
            System.out.println("- " + fruit);
        }
        
        System.out.println("\n--- Iterator로 안전하게 삭제 ---");
        listIterator = list.iterator();
        while (listIterator.hasNext()) {
            String fruit = listIterator.next();
            if (fruit.equals("바나나")) {
                listIterator.remove();  // 안전하게 삭제
                System.out.println("바나나 삭제됨");
            }
        }
        
        System.out.println("삭제 후 리스트: " + list);
        
        System.out.println("\n=== HashSet Iterator ===\n");
        
        HashSet<Integer> set = new HashSet<>();
        set.add(5);
        set.add(2);
        set.add(8);
        set.add(1);
        set.add(9);
        
        Iterator<Integer> setIterator = set.iterator();
        while (setIterator.hasNext()) {
            Integer num = setIterator.next();
            System.out.println("- " + num);
        }
        
        System.out.println("\n=== 향상된 for 문 vs Iterator ===\n");
        
        System.out.println("--- 향상된 for 문 (간단) ---");
        for (String fruit : list) {
            System.out.println("- " + fruit);
        }
        
        System.out.println("\n--- Iterator (삭제 가능) ---");
        listIterator = list.iterator();
        while (listIterator.hasNext()) {
            String fruit = listIterator.next();
            System.out.println("- " + fruit);
            // 필요시 listIterator.remove()로 삭제 가능
        }
        
        System.out.println("\n=== Map Iterator ===\n");
        
        HashMap<String, Integer> map = new HashMap<>();
        map.put("홍길동", 85);
        map.put("김철수", 90);
        map.put("이영희", 88);
        
        System.out.println("--- keySet() Iterator ---");
        Iterator<String> keyIterator = map.keySet().iterator();
        while (keyIterator.hasNext()) {
            String key = keyIterator.next();
            System.out.println(key + ": " + map.get(key));
        }
        
        System.out.println("\n--- values() Iterator ---");
        Iterator<Integer> valueIterator = map.values().iterator();
        while (valueIterator.hasNext()) {
            Integer value = valueIterator.next();
            System.out.println("점수: " + value);
        }
        
        System.out.println("\n--- entrySet() Iterator ---");
        Iterator<Map.Entry<String, Integer>> entryIterator = map.entrySet().iterator();
        while (entryIterator.hasNext()) {
            Map.Entry<String, Integer> entry = entryIterator.next();
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        
        System.out.println("\n=== Iterator의 장점 ===");
        System.out.println("1. 모든 컬렉션에서 사용 가능");
        System.out.println("2. 안전하게 요소 삭제 가능");
        System.out.println("3. 일관된 순회 방법 제공");
        
        System.out.println("\n=== 향상된 for 문의 장점 ===");
        System.out.println("1. 간결한 문법");
        System.out.println("2. 가독성 좋음");
        System.out.println("3. 대부분의 경우 충분");
        
        System.out.println("\n=== 언제 Iterator를 사용할까? ===");
        System.out.println("- 반복 중 요소를 삭제해야 할 때");
        System.out.println("- 모든 컬렉션 타입에 일관된 코드를 작성할 때");
    }
}

