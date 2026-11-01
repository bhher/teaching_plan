// 야구 선수 캐스팅 — 부모 클래스
public class Player {
    String name;
    int number;

    Player(String name, int number) {
        this.name = name;
        this.number = number;
    }

    void info() {
        System.out.println(number + "번 " + name);
    }

    void play() {
        System.out.println("선수가 경기에 나갑니다");
    }
}
