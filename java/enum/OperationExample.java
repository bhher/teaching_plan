package a0219.enum_example;

public class OperationExample {
    public static void main(String[] args) {
        double x = 10;
        double y = 5;
        
        // 각 연산 수행
        double result1 = Operation.PLUS.apply(x, y);
        double result2 = Operation.MINUS.apply(x, y);
        double result3 = Operation.TIMES.apply(x, y);
        double result4 = Operation.DIVIDE.apply(x, y);
        
        System.out.println(x + " + " + y + " = " + result1);
        System.out.println(x + " - " + y + " = " + result2);
        System.out.println(x + " * " + y + " = " + result3);
        System.out.println(x + " / " + y + " = " + result4);
        
        // 모든 연산 테스트
        System.out.println("\n=== 모든 연산 테스트 ===");
        for (Operation op : Operation.values()) {
            try {
                double result = op.apply(x, y);
                System.out.println(op.name() + ": " + x + " " + op.name() + " " + y + " = " + result);
            } catch (Exception e) {
                System.out.println(op.name() + ": " + e.getMessage());
            }
        }
    }
}
