import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Comparator — 클래스 수정 없이 정렬 기준을 바꿈
 * (이름순 / 나이순 / 점수 내림차순)
 */
public class ComparatorPractice {
    public static void main(String[] args) {
        ArrayList<Student> list = new ArrayList<>();
        list.add(new Student("홍길동", 20, 85));
        list.add(new Student("김철수", 22, 92));
        list.add(new Student("이영희", 21, 78));
        list.add(new Student("박민수", 19, 92));

        System.out.println("=== 이름 오름차순 ===");
        Collections.sort(list, new Comparator<Student>() {
            @Override
            public int compare(Student a, Student b) {
                return a.name.compareTo(b.name);
            }
        });
        print(list);

        System.out.println("\n=== 나이 오름차순 (람다) ===");
        Collections.sort(list, (a, b) -> a.age - b.age);
        print(list);

        System.out.println("\n=== 점수 내림차순 (람다) ===");
        Collections.sort(list, (a, b) -> b.score - a.score);
        print(list);

        System.out.println("\n=== 점수 내림차순, 같으면 이름순 ===");
        Collections.sort(list, (a, b) -> {
            if (b.score != a.score) {
                return b.score - a.score;
            }
            return a.name.compareTo(b.name);
        });
        print(list);
    }

    static void print(ArrayList<Student> list) {
        for (Student s : list) {
            System.out.println(s);
        }
    }
}
