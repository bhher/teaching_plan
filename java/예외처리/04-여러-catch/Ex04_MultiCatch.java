/** 04. 여러 종류의 catch */
public class Ex04_MultiCatch {
    public static void main(String[] args) {
        try {
            int num = Integer.parseInt("abc"); // NumberFormatException
            int result = 10 / 0;               // 위에서 이미 예외 → 여기 안 옴
            System.out.println(num + result);
        } catch (NumberFormatException e) {
            System.out.println("숫자 형식 오류");
        } catch (ArithmeticException e) {
            System.out.println("0으로 나눌 수 없습니다.");
        } catch (Exception e) {
            System.out.println("기타 예외: " + e.getMessage());
        }
    }
}
