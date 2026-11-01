/** Comparable 예제용 학생 — 기본 정렬: 점수 오름차순 */
public class Student implements Comparable<Student> {
    String name;
    int age;
    int score;

    public Student(String name, int age, int score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }

    @Override
    public int compareTo(Student o) {
        // 음수: this가 앞, 0: 같음, 양수: this가 뒤
        return this.score - o.score;
    }

    @Override
    public String toString() {
        return name + "(나이:" + age + ", 점수:" + score + ")";
    }
}
