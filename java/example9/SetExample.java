import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;
import java.util.Set;

/**
 * Set 인터페이스를 설명하는 예제
 * HashSet, LinkedHashSet, TreeSet의 사용법을 보여줍니다
 */
public class SetExample {
    public static void main(String[] args) {
        System.out.println("=== HashSet 예제 ===\n");
        
        // HashSet 생성
        HashSet<String> hashSet = new HashSet<>();
        
        // 요소 추가
        hashSet.add("사과");
        hashSet.add("바나나");
        hashSet.add("오렌지");
        hashSet.add("사과");  // 중복 추가 시도
        hashSet.add("포도");
        
        System.out.println("HashSet: " + hashSet);
        System.out.println("크기: " + hashSet.size());  // 4 (중복 제거됨)
        
        // 요소 확인
        System.out.println("사과 포함 여부: " + hashSet.contains("사과"));
        System.out.println("수박 포함 여부: " + hashSet.contains("수박"));
        
        // 요소 삭제
        hashSet.remove("바나나");
        System.out.println("바나나 삭제 후: " + hashSet);
        
        // 반복문으로 순회
        System.out.println("\n반복문으로 순회:");
        for (String fruit : hashSet) {
            System.out.println("- " + fruit);
        }
        
        // 비어있는지 확인
        System.out.println("비어있는가? " + hashSet.isEmpty());
        
        // 모든 요소 삭제
        hashSet.clear();
        System.out.println("clear() 후: " + hashSet);
        System.out.println("비어있는가? " + hashSet.isEmpty());
        
        System.out.println("\n=== LinkedHashSet 예제 ===\n");
        
        // LinkedHashSet 생성 (삽입 순서 유지)
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();
        
        linkedHashSet.add("사과");
        linkedHashSet.add("바나나");
        linkedHashSet.add("오렌지");
        linkedHashSet.add("포도");
        
        System.out.println("LinkedHashSet: " + linkedHashSet);
        System.out.println("삽입 순서가 유지됨");
        
        System.out.println("\n순회:");
        for (String fruit : linkedHashSet) {
            System.out.println("- " + fruit);
        }
        
        System.out.println("\n=== TreeSet 예제 ===\n");
        
        // TreeSet 생성 (정렬된 순서)
        TreeSet<Integer> treeSet = new TreeSet<>();
        
        treeSet.add(5);
        treeSet.add(2);
        treeSet.add(8);
        treeSet.add(1);
        treeSet.add(9);
        treeSet.add(3);
        
        System.out.println("TreeSet: " + treeSet);
        System.out.println("자동으로 정렬됨");
        
        System.out.println("\n순회:");
        for (Integer num : treeSet) {
            System.out.println("- " + num);
        }
        
        // 최솟값, 최댓값
        System.out.println("최솟값: " + treeSet.first());
        System.out.println("최댓값: " + treeSet.last());
        
        // 범위 검색
        System.out.println("3 이상: " + treeSet.tailSet(3));
        System.out.println("5 이하: " + treeSet.headSet(6));
        
        System.out.println("\n=== 중복 제거 예제 ===\n");
        
        // 리스트에서 중복 제거
        java.util.ArrayList<String> list = new java.util.ArrayList<>();
        list.add("사과");
        list.add("바나나");
        list.add("사과");
        list.add("오렌지");
        list.add("바나나");
        list.add("포도");
        
        System.out.println("원본 리스트: " + list);
        
        // HashSet으로 중복 제거
        HashSet<String> uniqueSet = new HashSet<>(list);
        System.out.println("중복 제거: " + uniqueSet);
        
        // 다시 리스트로 변환
        java.util.ArrayList<String> uniqueList = new java.util.ArrayList<>(uniqueSet);
        System.out.println("리스트로 변환: " + uniqueList);
        
        System.out.println("\n=== Set 인터페이스로 사용 ===\n");
        
        // Set 인터페이스 타입으로 선언 (다형성)
        Set<String> set = new HashSet<>();
        set.add("A");
        set.add("B");
        set.add("C");
        System.out.println("Set 인터페이스: " + set);
        
        // TreeSet으로 변경 가능
        set = new TreeSet<>();
        set.add("Z");
        set.add("X");
        set.add("Y");
        System.out.println("TreeSet으로 변경: " + set);
        
        System.out.println("\n=== Set의 특징 ===");
        System.out.println("1. 중복을 허용하지 않음");
        System.out.println("2. HashSet: 순서 보장 안 됨, 빠른 검색");
        System.out.println("3. LinkedHashSet: 삽입 순서 유지");
        System.out.println("4. TreeSet: 정렬된 순서");
    }
}

