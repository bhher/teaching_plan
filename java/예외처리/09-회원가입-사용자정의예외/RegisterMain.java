/** 09. 사용자 정의 예외 — 회원가입 나이 제한 */
public class RegisterMain {
    public static void register(String name, int age) throws InvalidAgeException {
        if (age < 14) {
            throw new InvalidAgeException("14세 미만은 가입할 수 없습니다.");
        }
        System.out.println(name + "님 회원가입 완료");
    }

    public static void main(String[] args) {
        try {
            register("홍길동", 12);
        } catch (InvalidAgeException e) {
            System.out.println(e.getMessage());
        }

        try {
            register("김철수", 20);
        } catch (InvalidAgeException e) {
            System.out.println(e.getMessage());
        }
    }
}
