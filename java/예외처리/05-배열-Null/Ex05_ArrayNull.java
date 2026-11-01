/** 05. 배열 범위 / NullPointerException */
public class Ex05_ArrayNull {
    public static void main(String[] args) {
        System.out.println("=== 배열 예외 ===");
        int[] arr = {10, 20, 30};
        try {
            System.out.println(arr[5]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("배열의 범위를 벗어났습니다.");
        }

        System.out.println("\n=== NullPointerException ===");
        String name = null;
        try {
            System.out.println(name.length());
        } catch (NullPointerException e) {
            System.out.println("문자열이 null입니다.");
        }

        System.out.println("\n=== Exception으로 받기 ===");
        try {
            Integer.parseInt("abc");
        } catch (Exception e) {
            System.out.println("예외가 발생했습니다: " + e.getClass().getSimpleName());
        }
    }
}
