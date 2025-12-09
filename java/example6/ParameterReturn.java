/**
 * 매개변수와 반환값을 설명하는 예제
 * 다양한 타입의 매개변수와 반환값을 보여줍니다
 */
public class ParameterReturn {
    // 매개변수가 없는 메서드
    public int getRandomNumber() {
        return (int)(Math.random() * 100) + 1;
    }
    
    // 매개변수가 하나인 메서드
    public int square(int number) {
        return number * number;
    }
    
    // 매개변수가 여러 개인 메서드
    public int multiply(int a, int b, int c) {
        return a * b * c;
    }
    
    // 배열을 매개변수로 받는 메서드
    public int sumArray(int[] numbers) {
        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        return sum;
    }
    
    // 여러 값을 배열로 반환
    public int[] getMinMax(int[] numbers) {
        if (numbers.length == 0) {
            return new int[]{0, 0};
        }
        
        int min = numbers[0];
        int max = numbers[0];
        
        for (int num : numbers) {
            if (num < min) min = num;
            if (num > max) max = num;
        }
        
        return new int[]{min, max};
    }
    
    // void 메서드 (반환값 없음)
    public void printInfo(String name, int age) {
        System.out.println("이름: " + name);
        System.out.println("나이: " + age);
    }
    
    // 조건에 따라 다른 값 반환
    public String getGrade(int score) {
        if (score >= 90) {
            return "A";
        } else if (score >= 80) {
            return "B";
        } else if (score >= 70) {
            return "C";
        } else {
            return "F";
        }
    }
    
    // 조기 반환 (Early Return)
    public boolean isValidAge(int age) {
        if (age < 0) {
            return false;  // 조기 반환
        }
        if (age > 150) {
            return false;  // 조기 반환
        }
        return true;
    }
    
    public static void main(String[] args) {
        ParameterReturn obj = new ParameterReturn();
        
        System.out.println("=== 매개변수가 없는 메서드 ===");
        int random = obj.getRandomNumber();
        System.out.println("랜덤 숫자: " + random);
        
        System.out.println("\n=== 매개변수가 하나인 메서드 ===");
        int squared = obj.square(5);
        System.out.println("5의 제곱: " + squared);
        
        System.out.println("\n=== 매개변수가 여러 개인 메서드 ===");
        int product = obj.multiply(2, 3, 4);
        System.out.println("2 × 3 × 4 = " + product);
        
        System.out.println("\n=== 배열을 매개변수로 받는 메서드 ===");
        int[] numbers = {10, 20, 30, 40, 50};
        int sum = obj.sumArray(numbers);
        System.out.println("배열의 합: " + sum);
        
        System.out.println("\n=== 여러 값을 배열로 반환 ===");
        int[] minMax = obj.getMinMax(numbers);
        System.out.println("최솟값: " + minMax[0]);
        System.out.println("최댓값: " + minMax[1]);
        
        System.out.println("\n=== void 메서드 ===");
        obj.printInfo("홍길동", 25);
        
        System.out.println("\n=== 조건에 따라 다른 값 반환 ===");
        String grade1 = obj.getGrade(95);
        String grade2 = obj.getGrade(75);
        System.out.println("95점 → " + grade1 + "등급");
        System.out.println("75점 → " + grade2 + "등급");
        
        System.out.println("\n=== 조기 반환 ===");
        System.out.println("나이 25는 유효한가? " + obj.isValidAge(25));
        System.out.println("나이 -5는 유효한가? " + obj.isValidAge(-5));
        System.out.println("나이 200은 유효한가? " + obj.isValidAge(200));
    }
}

