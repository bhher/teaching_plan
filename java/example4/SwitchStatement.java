/**
 * switch 문을 설명하는 예제
 * switch-case와 break의 중요성을 보여줍니다
 */
public class SwitchStatement {
    public static void main(String[] args) {
        System.out.println("=== 기본 switch 문 ===");
        int day = 3;
        String dayName;
        
        switch (day) {
            case 1:
                dayName = "월요일";
                break;
            case 2:
                dayName = "화요일";
                break;
            case 3:
                dayName = "수요일";
                break;
            case 4:
                dayName = "목요일";
                break;
            case 5:
                dayName = "금요일";
                break;
            case 6:
                dayName = "토요일";
                break;
            case 7:
                dayName = "일요일";
                break;
            default:
                dayName = "잘못된 요일";
                break;
        }
        System.out.println("요일: " + dayName);
        
        System.out.println("\n=== break가 없을 때 (fall-through) ===");
        int num = 2;
        System.out.println("num = " + num + "일 때:");
        
        switch (num) {
            case 1:
                System.out.println("1");
            case 2:
                System.out.println("2");  // break가 없어서 다음 case도 실행
            case 3:
                System.out.println("3");
                break;
            default:
                System.out.println("기타");
        }
        
        System.out.println("\n=== 여러 case를 하나로 묶기 ===");
        char grade = 'B';
        
        switch (grade) {
            case 'A':
            case 'a':
                System.out.println("우수");
                break;
            case 'B':
            case 'b':
                System.out.println("양호");
                break;
            case 'C':
            case 'c':
                System.out.println("보통");
                break;
            default:
                System.out.println("기타");
                break;
        }
        
        System.out.println("\n=== 계절 판단 예제 ===");
        int month = 3;
        String season;
        
        switch (month) {
            case 12:
            case 1:
            case 2:
                season = "겨울";
                break;
            case 3:
            case 4:
            case 5:
                season = "봄";
                break;
            case 6:
            case 7:
            case 8:
                season = "여름";
                break;
            case 9:
            case 10:
            case 11:
                season = "가을";
                break;
            default:
                season = "잘못된 월";
                break;
        }
        System.out.println(month + "월은 " + season + "입니다.");
        
        System.out.println("\n=== switch vs if-else ===");
        int score = 85;
        
        // if-else로 작성
        if (score >= 90) {
            System.out.println("A등급 (if-else)");
        } else if (score >= 80) {
            System.out.println("B등급 (if-else)");
        } else {
            System.out.println("C등급 이하 (if-else)");
        }
        
        // switch로 작성 (점수 범위는 switch에 부적합)
        // switch는 주로 정확한 값 비교에 사용
        int choice = 2;
        switch (choice) {
            case 1:
                System.out.println("메뉴 1 선택");
                break;
            case 2:
                System.out.println("메뉴 2 선택");
                break;
            case 3:
                System.out.println("메뉴 3 선택");
                break;
            default:
                System.out.println("잘못된 선택");
                break;
        }
    }
}

