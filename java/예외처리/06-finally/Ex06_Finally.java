/** 06. finally — 예외와 무관하게 실행 */
public class Ex06_Finally {
    public static void main(String[] args) {
        System.out.println("=== 예외 없는 경우 ===");
        try {
            System.out.println("파일 열기");
        } catch (Exception e) {
            System.out.println("예외 처리");
        } finally {
            System.out.println("마무리 작업");
        }

        System.out.println("\n=== 예외 있는 경우 ===");
        try {
            System.out.println("파일 열기");
            int x = 10 / 0;
            System.out.println(x);
        } catch (Exception e) {
            System.out.println("예외 처리");
        } finally {
            System.out.println("마무리 작업");
        }
    }
}
