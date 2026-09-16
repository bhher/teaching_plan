/** 07. throw — 직접 예외 발생 */
public class Ex07_Throw {
    public static void main(String[] args) {
        int age = 15;

        try {
            if (age < 18) {
                throw new RuntimeException("미성년자는 사용할 수 없습니다.");
            }
            System.out.println("회원가입 가능");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
