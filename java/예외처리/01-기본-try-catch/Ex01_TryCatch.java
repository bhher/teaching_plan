/** 01. 가장 기본적인 try-catch */
public class Ex01_TryCatch {
    public static void main(String[] args) {
        try {
            int result = 10 / 0;
            System.out.println(result); // 실행 안 됨
        } catch (ArithmeticException e) {
            System.out.println("0으로 나눌 수 없습니다.");
        }
        System.out.println("프로그램 종료");
    }
}
