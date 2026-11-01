/**
 * 야구 선수 캐스팅 데모
 * - 업캐스팅: Player[] 에 Batter / Pitcher
 * - 다운캐스팅 + instanceof: swing, throwBall, 타율/방어율
 *
 * 실행: javac *.java
 *       java BaseballCastingDemo
 */
public class BaseballCastingDemo {

    public static void main(String[] args) {
        Player[] team = {
            new Batter("이정후", 51, 0.312, 150),
            new Batter("김하성", 7, 0.285, 130),
            new Pitcher("원태인", 18, 2.45, 120)
        };

        // 11번 — 라인업 (업캐스팅 + info 오버라이딩)
        System.out.println("=== 라인업 ===");
        for (Player p : team) {
            p.info();
        }

        // 8번 · 12번 — play / 특기
        System.out.println("\n=== play() ===");
        for (Player p : team) {
            p.play();
        }

        System.out.println("\n=== 특기 (다운캐스팅) ===");
        for (Player p : team) {
            specialAction(p);
        }

        // 13번 — 평균 타율 · 방어율
        System.out.println("\n=== 기록 리포트 ===");
        report(team);

        // 14번 — 선발 투수
        System.out.println("\n=== 선발 투수 ===");
        Player starter = new Pitcher("원태인", 18, 2.45, 120);
        starter.play();
        if (starter instanceof Pitcher) {
            Pitcher pit = (Pitcher) starter;
            pit.throwBall();
            pit.info();
        }

        // 15번 — 타율 비교
        System.out.println("\n=== 타율 비교 ===");
        Player p1 = new Batter("이정후", 51, 0.312, 150);
        Player p2 = new Batter("김하성", 7, 0.285, 130);
        compareAvg(p1, p2);

        // 5번 성공 / 6번은 주석 — ClassCastException 예
        System.out.println("\n=== 안전한 다운캐스팅 ===");
        Player p = new Batter("박병호", 52, 0.255, 100);
        if (p instanceof Batter) {
            Batter b = (Batter) p;
            System.out.println("타율: " + b.avg);
            b.swing();
        }
    }

    /** 12번 — 역할별 특기 */
    public static void specialAction(Player p) {
        if (p instanceof Batter) {
            Batter b = (Batter) p;
            b.swing();
        } else if (p instanceof Pitcher) {
            Pitcher pit = (Pitcher) p;
            pit.throwBall();
        } else {
            System.out.println("알 수 없는 선수");
        }
    }

    /** 13번 — 타율 · 방어율 평균 */
    public static void report(Player[] team) {
        double sumAvg = 0;
        int batterCount = 0;
        double sumEra = 0;
        int pitcherCount = 0;

        for (Player p : team) {
            if (p instanceof Batter) {
                Batter b = (Batter) p;
                sumAvg += b.avg;
                batterCount++;
            } else if (p instanceof Pitcher) {
                Pitcher pit = (Pitcher) p;
                sumEra += pit.era;
                pitcherCount++;
            }
        }

        if (batterCount == 0) {
            System.out.println("평균 타율: 없음");
        } else {
            System.out.printf("평균 타율: %.3f (%d명)%n",
                    sumAvg / batterCount, batterCount);
        }

        if (pitcherCount == 0) {
            System.out.println("평균 방어율: 없음");
        } else {
            System.out.printf("평균 방어율: %.2f (%d명)%n",
                    sumEra / pitcherCount, pitcherCount);
        }
    }

    /** 15번 — 타율 비교 */
    public static void compareAvg(Player p1, Player p2) {
        if (p1 instanceof Batter && p2 instanceof Batter) {
            Batter b1 = (Batter) p1;
            Batter b2 = (Batter) p2;

            if (b1.avg > b2.avg) {
                System.out.println(b1.name);
            } else if (b2.avg > b1.avg) {
                System.out.println(b2.name);
            } else {
                System.out.println("타율 동일");
            }
        }
    }
}
