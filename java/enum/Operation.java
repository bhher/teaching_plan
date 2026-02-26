package a0219.enum_example;

public enum Operation {
    PLUS {
        public double apply(double x, double y) {
            return x + y;
        }
    },
    MINUS {
        public double apply(double x, double y) {
            return x - y;
        }
    },
    TIMES {
        public double apply(double x, double y) {
            return x * y;
        }
    },
    DIVIDE {
        public double apply(double x, double y) {
            if (y == 0) {
                throw new IllegalArgumentException("0으로 나눌 수 없습니다!");
            }
            return x / y;
        }
    };
    
    // 추상 메서드 (각 Enum 값에서 구현해야 함)
    public abstract double apply(double x, double y);
}
