// 투수 — 방어율(era), 탈삼진(strikeOut)
public class Pitcher extends Player {
    double era;
    int strikeOut;

    Pitcher(String name, int number, double era, int strikeOut) {
        super(name, number);
        this.era = era;
        this.strikeOut = strikeOut;
    }

    @Override
    void info() {
        System.out.println(number + "번 투수 " + name
                + " | 방어율: " + era + " | 탈삼진: " + strikeOut);
    }

    @Override
    void play() {
        System.out.println("마운드에 올라갑니다");
    }

    void throwBall() {
        System.out.println("공을 던집니다!");
    }
}
