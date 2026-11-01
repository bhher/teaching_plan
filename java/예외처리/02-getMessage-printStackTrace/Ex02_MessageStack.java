/** 02. getMessage() / printStackTrace() */
public class Ex02_MessageStack {
    public static void main(String[] args) {
        System.out.println("=== getMessage() ===");
        try {
            int result = 10 / 0;
            System.out.println(result);
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage()); // / by zero
        }

        System.out.println("\n=== printStackTrace() ===");
        try {
            int result = 10 / 0;
            System.out.println(result);
        } catch (ArithmeticException e) {
            e.printStackTrace(); // 발생 위치까지 출력
        }

        System.out.println("프로그램 종료");
    }
}
