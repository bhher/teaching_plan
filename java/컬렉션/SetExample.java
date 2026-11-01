import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;

/** Set(HashSet, LinkedHashSet) 기본 예제 */
public class SetExample {
    public static void main(String[] args) {
        System.out.println("=== Set / HashSet ===\n");

        HashSet<String> set = new HashSet<>();
        set.add("사과");
        set.add("바나나");
        set.add("사과");  // 중복 → 무시
        set.add("오렌지");

        System.out.println("HashSet: " + set);
        System.out.println("크기: " + set.size());  // 3
        System.out.println("사과 있음? " + set.contains("사과"));

        set.remove("바나나");
        System.out.println("삭제 후: " + set);

        // List → Set 으로 중복 제거
        System.out.println("\n=== 중복 제거 ===");
        ArrayList<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("A");
        list.add("C");
        list.add("B");
        System.out.println("원본 List: " + list);

        HashSet<String> unique = new HashSet<>(list);
        System.out.println("HashSet(순서 비보장): " + unique);

        LinkedHashSet<String> ordered = new LinkedHashSet<>(list);
        System.out.println("LinkedHashSet(입력순): " + ordered);
    }
}
