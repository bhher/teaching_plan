/**
 * 논리 연산자를 설명하는 예제
 * AND(&&), OR(||), NOT(!) 연산자를 보여줍니다
 */
public class LogicalOperators {
    public static void main(String[] args) {
        boolean a = true;
        boolean b = false;
        
        System.out.println("=== 논리 연산자 ===");
        System.out.println("a = " + a + ", b = " + b);
        
        // AND 연산 (&&)
        System.out.println("\nAND 연산 (&&):");
        System.out.println("a && a: " + (a && a));  // true
        System.out.println("a && b: " + (a && b));  // false
        System.out.println("b && a: " + (b && a));  // false
        System.out.println("b && b: " + (b && b));  // false
        
        // OR 연산 (||)
        System.out.println("\nOR 연산 (||):");
        System.out.println("a || a: " + (a || a));  // true
        System.out.println("a || b: " + (a || b));  // true
        System.out.println("b || a: " + (b || a));  // true
        System.out.println("b || b: " + (b || b));  // false
        
        // NOT 연산 (!)
        System.out.println("\nNOT 연산 (!):");
        System.out.println("!a: " + (!a));  // false
        System.out.println("!b: " + (!b));  // true
        System.out.println("!!a: " + (!!a));  // true (이중 부정)
        
        System.out.println("\n=== 복합 논리 연산 ===");
        boolean x = true;
        boolean y = true;
        boolean z = false;
        
        System.out.println("x = " + x + ", y = " + y + ", z = " + z);
        System.out.println("x && y && z: " + (x && y && z));      // false
        System.out.println("x || y || z: " + (x || y || z));      // true
        System.out.println("(x && y) || z: " + ((x && y) || z));  // true
        System.out.println("x && (y || z): " + (x && (y || z)));  // true
        
        System.out.println("\n=== 실용적인 예제 ===");
        
        // 나이와 면허 확인
        int age = 25;
        boolean hasLicense = true;
        
        if (age >= 18 && hasLicense) {
            System.out.println("운전 가능합니다.");
        } else {
            System.out.println("운전할 수 없습니다.");
        }
        
        // 점수 범위 확인
        int score = 85;
        if (score >= 60 && score <= 100) {
            System.out.println("합격 점수입니다.");
        }
        
        // 할인 조건 (학생이거나 65세 이상)
        int customerAge = 20;
        boolean isStudent = true;
        boolean isSenior = customerAge >= 65;
        
        if (isStudent || isSenior) {
            System.out.println("할인 대상입니다.");
        }
        
        System.out.println("\n=== 단락 평가 (Short-Circuit) ===");
        int num1 = 10;
        int num2 = 5;
        
        // && 연산자: 첫 번째 조건이 false이면 두 번째 조건을 확인하지 않음
        if (num1 < 5 && num2++ > 0) {
            // 실행되지 않음
        }
        System.out.println("num2 = " + num2);  // 5 (증가하지 않음)
        
        // || 연산자: 첫 번째 조건이 true이면 두 번째 조건을 확인하지 않음
        if (num1 > 5 || num2++ > 0) {
            // 실행됨
        }
        System.out.println("num2 = " + num2);  // 여전히 5 (증가하지 않음)
    }
}

