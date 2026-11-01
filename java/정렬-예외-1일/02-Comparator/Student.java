/** Comparator 예제용 — Comparable 없이 데이터만 보관 */
public class Student {
    String name;
    int age;
    int score;

    public Student(String name, int age, int score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }

    @Override
    public String toString() {
        return name + "(나이:" + age + ", 점수:" + score + ")";
    }
}
