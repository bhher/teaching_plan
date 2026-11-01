public class MathExample {

    public static void main(String[] args) {

        // 1. Math.abs() : 절대값
        System.out.println("=== Math.abs() 절대값 ===");
        System.out.println("Math.abs(-10) = " + Math.abs(-10));
        System.out.println("Math.abs(10) = " + Math.abs(10));
        System.out.println("Math.abs(-3.5) = " + Math.abs(-3.5));
        System.out.println();

        // 2. Math.max() / Math.min() : 최대·최소
        System.out.println("=== Math.max() / Math.min() 최대·최소 ===");
        System.out.println("Math.max(10, 20) = " + Math.max(10, 20));
        System.out.println("Math.min(10, 20) = " + Math.min(10, 20));
        System.out.println("Math.max(3.14, 2.71) = " + Math.max(3.14, 2.71));
        System.out.println("Math.min(3.14, 2.71) = " + Math.min(2.71, 3.14));
        System.out.println();

        // 3. Math.pow() : 거듭제곱
        System.out.println("=== Math.pow() 거듭제곱 ===");
        System.out.println("Math.pow(2, 3) = " + Math.pow(2, 3));   // 2의 3승 = 8
        System.out.println("Math.pow(5, 2) = " + Math.pow(5, 2));   // 5의 2승 = 25
        System.out.println("Math.pow(10, 0) = " + Math.pow(10, 0)); // 10의 0승 = 1
        System.out.println();

        // 4. Math.sqrt() : 제곱근
        System.out.println("=== Math.sqrt() 제곱근 ===");
        System.out.println("Math.sqrt(9) = " + Math.sqrt(9));
        System.out.println("Math.sqrt(16) = " + Math.sqrt(16));
        System.out.println("Math.sqrt(2) = " + Math.sqrt(2));
        System.out.println();

        // 5. Math.round() : 반올림
        System.out.println("=== Math.round() 반올림 ===");
        System.out.println("Math.round(3.4) = " + Math.round(3.4));
        System.out.println("Math.round(3.5) = " + Math.round(3.5));
        System.out.println("Math.round(3.6) = " + Math.round(3.6));
        System.out.println("Math.round(-2.5) = " + Math.round(-2.5));
        System.out.println();

        // 6. Math.random() : 난수 생성 (0.0 이상 1.0 미만)
        System.out.println("=== Math.random() 난수 생성 ===");
        System.out.println("Math.random() = " + Math.random());

        // 0 ~ 9 사이 정수 난수
        int random0to9 = (int) (Math.random() * 10);
        System.out.println("0~9 난수 = " + random0to9);

        // 1 ~ 100 사이 정수 난수
        int random1to100 = (int) (Math.random() * 100) + 1;
        System.out.println("1~100 난수 = " + random1to100);
        System.out.println();

        // 7. Math.PI : 원주율
        System.out.println("=== Math.PI 원주율 ===");
        System.out.println("Math.PI = " + Math.PI);

        // 반지름 5인 원의 넓이
        double radius = 5;
        double area = Math.PI * Math.pow(radius, 2);
        System.out.println("반지름 5 원의 넓이 = " + area);

        // 반지름 5인 원의 둘레
        double circumference = 2 * Math.PI * radius;
        System.out.println("반지름 5 원의 둘레 = " + circumference);
    }
}
