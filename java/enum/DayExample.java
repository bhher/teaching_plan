package a0219.enum_example;

public class DayExample {
    public static void main(String[] args) {
        // Enum 변수 선언 및 사용
        Day today = Day.MONDAY;
        
        // 출력
        System.out.println("오늘은 " + today + "입니다.");
        // 출력: 오늘은 MONDAY입니다.
        
        // Enum 값 비교
        if (today == Day.MONDAY) {
            System.out.println("월요일입니다!");
        }
        
        // switch문에서 사용
        switch (today) {
            case MONDAY:
                System.out.println("월요일 - 시작하는 날");
                break;
            case FRIDAY:
                System.out.println("금요일 - 주말 전날");
                break;
            case SATURDAY:
            case SUNDAY:
                System.out.println("주말입니다!");
                break;
            default:
                System.out.println("평일입니다.");
        }
        
        // 모든 Enum 값 순회
        System.out.println("\n=== 모든 요일 ===");
        for (Day day : Day.values()) {
            System.out.println(day.name() + " - 순서: " + day.ordinal());
        }
    }
}
