import java.util.Scanner;

/** 03. Scanner + NumberFormatException (입력 실전) */
public class Ex03_ScannerParse {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("숫자를 입력하세요: ");

        try {
            int num = Integer.parseInt(sc.nextLine());
            System.out.println("입력한 숫자: " + num);
        } catch (NumberFormatException e) {
            System.out.println("숫자만 입력하세요.");
        }

        System.out.println("프로그램 종료");
        sc.close();
    }
}
