import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * List 인터페이스를 설명하는 예제
 * ArrayList와 LinkedList의 사용법을 보여줍니다
 */
public class ListExample {
    public static void main(String[] args) {
        System.out.println("=== ArrayList 예제 ===\n");
        
        // ArrayList 생성
        ArrayList<String> arrayList = new ArrayList<>();
        
        // 요소 추가
        arrayList.add("사과");
        arrayList.add("바나나");
        arrayList.add("오렌지");
        System.out.println("초기 리스트: " + arrayList);
        
        // 특정 위치에 추가
        arrayList.add(1, "포도");
        System.out.println("인덱스 1에 포도 추가: " + arrayList);
        
        // 요소 접근
        System.out.println("인덱스 0: " + arrayList.get(0));
        System.out.println("인덱스 2: " + arrayList.get(2));
        
        // 요소 변경
        arrayList.set(0, "딸기");
        System.out.println("인덱스 0을 딸기로 변경: " + arrayList);
        
        // 요소 삭제 (인덱스)
        arrayList.remove(1);
        System.out.println("인덱스 1 삭제: " + arrayList);
        
        // 요소 삭제 (요소)
        arrayList.remove("바나나");
        System.out.println("바나나 삭제: " + arrayList);
        
        // 크기 확인
        System.out.println("리스트 크기: " + arrayList.size());
        
        // 포함 여부 확인
        System.out.println("오렌지 포함 여부: " + arrayList.contains("오렌지"));
        
        // 인덱스 찾기
        System.out.println("오렌지의 인덱스: " + arrayList.indexOf("오렌지"));
        
        // 반복문으로 순회
        System.out.println("\n반복문으로 순회:");
        for (String fruit : arrayList) {
            System.out.println("- " + fruit);
        }
        
        // 인덱스로 순회
        System.out.println("\n인덱스로 순회:");
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println(i + ": " + arrayList.get(i));
        }
        
        System.out.println("\n=== LinkedList 예제 ===\n");
        
        // LinkedList 생성
        LinkedList<String> linkedList = new LinkedList<>();
        
        linkedList.add("첫 번째");
        linkedList.add("두 번째");
        linkedList.add("세 번째");
        System.out.println("초기 리스트: " + linkedList);
        
        // 앞에 추가
        linkedList.addFirst("맨 앞");
        System.out.println("맨 앞에 추가: " + linkedList);
        
        // 뒤에 추가
        linkedList.addLast("맨 뒤");
        System.out.println("맨 뒤에 추가: " + linkedList);
        
        // 첫 번째/마지막 요소
        System.out.println("첫 번째 요소: " + linkedList.getFirst());
        System.out.println("마지막 요소: " + linkedList.getLast());
        
        // 첫 번째/마지막 요소 삭제
        linkedList.removeFirst();
        linkedList.removeLast();
        System.out.println("첫 번째와 마지막 삭제 후: " + linkedList);
        
        System.out.println("\n=== List 인터페이스로 사용 ===\n");
        
        // List 인터페이스 타입으로 선언 (다형성)
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        System.out.println("List 인터페이스: " + list);
        
        // LinkedList로 변경 가능
        list = new LinkedList<>();
        list.add("X");
        list.add("Y");
        list.add("Z");
        System.out.println("LinkedList로 변경: " + list);
        
        System.out.println("\n=== ArrayList vs LinkedList ===");
        System.out.println("ArrayList: 인덱스 접근이 빠름, 중간 삽입/삭제가 느림");
        System.out.println("LinkedList: 중간 삽입/삭제가 빠름, 인덱스 접근이 느림");
    }
}

