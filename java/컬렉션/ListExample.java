import java.util.ArrayList;

/** List(ArrayList) 기본 예제 */
public class ListExample {
    public static void main(String[] args) {
        System.out.println("=== List / ArrayList ===\n");

        ArrayList<String> fruits = new ArrayList<>();
        fruits.add("사과");
        fruits.add("바나나");
        fruits.add("포도");
        fruits.add(0, "딸기");  // 맨 앞에 삽입

        System.out.println("목록: " + fruits);
        System.out.println("0번: " + fruits.get(0));
        System.out.println("크기: " + fruits.size());
        System.out.println("사과 포함? " + fruits.contains("사과"));
        System.out.println("바나나 위치: " + fruits.indexOf("바나나"));

        fruits.set(1, "수박");   // 수정
        fruits.remove("포도"); // 값으로 삭제
        System.out.println("수정·삭제 후: " + fruits);

        System.out.println("\n--- for-each ---");
        for (String f : fruits) {
            System.out.println("- " + f);
        }

        System.out.println("\n--- 인덱스로 순회 ---");
        for (int i = 0; i < fruits.size(); i++) {
            System.out.println("[" + i + "] " + fruits.get(i));
        }
    }
}
