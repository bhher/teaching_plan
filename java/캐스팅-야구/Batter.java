// 타자 — 타율(avg), 안타(hit)
public class Batter extends Player {
    double avg;
    int hit;

    Batter(String name, int number, double avg, int hit) {
        super(name, number);
        this.avg = avg;
        this.hit = hit;
    }

    @Override
    void info() {
        System.out.println(number + "번 타자 " + name
                + " | 타율: " + avg + " | 안타: " + hit);
    }

    @Override
    void play() {
        System.out.println("타석에 들어섭니다");
    }

    void swing() {
        System.out.println("스윙!");
    }
}
