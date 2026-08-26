import java.util.ArrayList;
import java.util.Collections;

/** Comparable — Collections.sort(list) 만으로 점수순 정렬 */
public class ComparableDemo {
    public static void main(String[] args) {
        ArrayList<Student> list = new ArrayList<>();
        list.add(new Student("홍길동", 20, 85));
        list.add(new Student("김철수", 22, 92));
        list.add(new Student("이영희", 21, 78));
        list.add(new Student("박민수", 20, 92));

        System.out.println("=== 정렬 전 ===");
        for (Student s : list) {
            System.out.println(s);
        }

        Collections.sort(list);  // Student.compareTo (점수 오름차순)

        System.out.println("\n=== 점수 오름차순 (Comparable) ===");
        for (Student s : list) {
            System.out.println(s);
        }
    }
}
