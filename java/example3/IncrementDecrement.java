/**
 * 증감 연산자를 설명하는 예제
 * 전위 증감과 후위 증감의 차이를 보여줍니다
 */
public class IncrementDecrement {
    public static void main(String[] args) {
        System.out.println("=== 전위 증가 연산자 (++변수) ===");
        int a = 5;
        System.out.println("초기값 a = " + a);
        
        int b = ++a;  // a를 먼저 1 증가시킨 후(6), b에 할당
        System.out.println("b = ++a 실행 후:");
        System.out.println("a = " + a);  // 6
        System.out.println("b = " + b);  // 6
        
        System.out.println("\n=== 후위 증가 연산자 (변수++) ===");
        int c = 5;
        System.out.println("초기값 c = " + c);
        
        int d = c++;  // c의 현재 값(5)을 d에 할당한 후, c를 1 증가
        System.out.println("d = c++ 실행 후:");
        System.out.println("c = " + c);  // 6
        System.out.println("d = " + d);  // 5
        
        System.out.println("\n=== 전위 감소 연산자 (--변수) ===");
        int e = 10;
        System.out.println("초기값 e = " + e);
        
        int f = --e;  // e를 먼저 1 감소시킨 후(9), f에 할당
        System.out.println("f = --e 실행 후:");
        System.out.println("e = " + e);  // 9
        System.out.println("f = " + f);  // 9
        
        System.out.println("\n=== 후위 감소 연산자 (변수--) ===");
        int g = 10;
        System.out.println("초기값 g = " + g);
        
        int h = g--;  // g의 현재 값(10)을 h에 할당한 후, g를 1 감소
        System.out.println("h = g-- 실행 후:");
        System.out.println("g = " + g);  // 9
        System.out.println("h = " + h);  // 10
        
        System.out.println("\n=== 증감 연산자 단독 사용 ===");
        int i = 5;
        i++;  // 후위든 전위든 결과는 동일
        System.out.println("i++ 후: " + i);  // 6
        
        int j = 5;
        ++j;  // 후위든 전위든 결과는 동일
        System.out.println("++j 후: " + j);  // 6
        
        System.out.println("\n=== 복잡한 표현식 (주의 필요) ===");
        int x = 5;
        int y = x++ + ++x;  // 복잡하고 혼란스러움
        System.out.println("x = 5일 때, y = x++ + ++x");
        System.out.println("결과: y = " + y + ", x = " + x);
        
        // 권장: 명확하게 작성
        System.out.println("\n=== 명확한 작성 방법 (권장) ===");
        int num = 5;
        int temp1 = num++;     // temp1 = 5, num = 6
        int temp2 = ++num;     // num = 7, temp2 = 7
        int result = temp1 + temp2;  // result = 12
        System.out.println("명확한 방법: " + result);
        
        System.out.println("\n=== 반복문에서의 활용 ===");
        System.out.println("for 루프에서 자주 사용:");
        for (int k = 0; k < 3; k++) {  // 후위 증가
            System.out.println("k = " + k);
        }
    }
}

