/**
 * 변수의 개념을 설명하는 예제
 * 변수 선언, 할당, 사용 방법을 보여줍니다
 */
public class VariableExample {
    public static void main(String[] args) {
        // 변수 선언
        int number;
        
        // 변수에 값 할당
        number = 10;
        System.out.println("숫자: " + number);
        
        // 값 변경
        number = 20;
        System.out.println("변경된 숫자: " + number);
        
        // 선언과 동시에 초기화
        int age = 25;
        String name = "홍길동";
        
        System.out.println("이름: " + name + ", 나이: " + age);
    }
}

