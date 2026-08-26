import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * [문제] ComparatorPractice — TODO 부분을 채우세요
 * 설명: 문제.md 참고
 */
public class ComparatorPractice {
    public static void main(String[] args) {
        ArrayList<Student> list = new ArrayList<>();
        list.add(new Student("홍길동", 20, 85));
        list.add(new Student("김철수", 22, 92));
        list.add(new Student("이영희", 21, 78));
        list.add(new Student("박민수", 19, 92));

        // ① 이름 오름차순 (익명 클래스 Comparator)
        System.out.println("=== 이름 오름차순 ===");
        Collections.sort(list, new Comparator<Student>() {
            @Override
            public int compare(Student a, Student b) {
                // TODO: 이름 오름차순
                return 0;
            }
        });
        print(list);

        // ② 나이 오름차순 (람다)
        System.out.println("\n=== 나이 오름차순 (람다) ===");
        // TODO: Collections.sort(list, (a, b) -> …);
        print(list);

        // ③ 점수 내림차순 (람다)
        System.out.println("\n=== 점수 내림차순 (람다) ===");
        // TODO: Collections.sort(list, (a, b) -> …);
        print(list);

        // ④ 점수 내림차순, 같으면 이름 오름차순
        System.out.println("\n=== 점수 내림차순, 같으면 이름순 ===");
        // TODO: Collections.sort(list, (a, b) -> { … });
        print(list);
    }

    static void print(ArrayList<Student> list) {
        for (Student s : list) {
            System.out.println(s);
        }
    }
}
