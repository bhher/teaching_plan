/**
 * 배열 처리 메서드 예제
 * 배열을 다루는 다양한 메서드를 보여줍니다
 */
public class ArrayProcessor {
    // 배열의 최댓값 찾기
    public int findMax(int[] numbers) {
        if (numbers.length == 0) {
            System.out.println("배열이 비어있습니다!");
            return Integer.MIN_VALUE;
        }
        
        int max = numbers[0];
        for (int num : numbers) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }
    
    // 배열의 최솟값 찾기
    public int findMin(int[] numbers) {
        if (numbers.length == 0) {
            System.out.println("배열이 비어있습니다!");
            return Integer.MAX_VALUE;
        }
        
        int min = numbers[0];
        for (int num : numbers) {
            if (num < min) {
                min = num;
            }
        }
        return min;
    }
    
    // 배열의 합계 계산
    public int calculateSum(int[] numbers) {
        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        return sum;
    }
    
    // 배열의 평균 계산
    public double calculateAverage(int[] numbers) {
        if (numbers.length == 0) {
            return 0.0;
        }
        return (double) calculateSum(numbers) / numbers.length;
    }
    
    // 배열에서 특정 값 찾기
    public int findIndex(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == target) {
                return i;
            }
        }
        return -1;  // 찾지 못함
    }
    
    // 배열에서 특정 값의 개수 세기
    public int countOccurrences(int[] numbers, int target) {
        int count = 0;
        for (int num : numbers) {
            if (num == target) {
                count++;
            }
        }
        return count;
    }
    
    // 배열 출력
    public void printArray(int[] numbers) {
        System.out.print("[");
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i]);
            if (i < numbers.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
    
    // 배열 역순으로 변경
    public void reverseArray(int[] numbers) {
        for (int i = 0; i < numbers.length / 2; i++) {
            int temp = numbers[i];
            numbers[i] = numbers[numbers.length - 1 - i];
            numbers[numbers.length - 1 - i] = temp;
        }
    }
    
    // 배열 복사
    public int[] copyArray(int[] numbers) {
        int[] copy = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            copy[i] = numbers[i];
        }
        return copy;
    }
    
    public static void main(String[] args) {
        ArrayProcessor processor = new ArrayProcessor();
        
        int[] numbers = {10, 5, 8, 20, 3, 15, 8};
        
        System.out.println("=== 배열 처리 메서드 예제 ===\n");
        
        System.out.print("배열: ");
        processor.printArray(numbers);
        
        System.out.println("\n최댓값: " + processor.findMax(numbers));
        System.out.println("최솟값: " + processor.findMin(numbers));
        System.out.println("합계: " + processor.calculateSum(numbers));
        System.out.println("평균: " + processor.calculateAverage(numbers));
        
        int target = 8;
        int index = processor.findIndex(numbers, target);
        if (index != -1) {
            System.out.println("\n" + target + "의 위치: 인덱스 " + index);
        } else {
            System.out.println("\n" + target + "을(를) 찾을 수 없습니다.");
        }
        
        System.out.println(target + "의 개수: " + processor.countOccurrences(numbers, target));
        
        System.out.println("\n=== 배열 복사 ===");
        int[] original = {1, 2, 3, 4, 5};
        int[] copied = processor.copyArray(original);
        
        System.out.print("원본: ");
        processor.printArray(original);
        System.out.print("복사본: ");
        processor.printArray(copied);
        
        copied[0] = 100;
        System.out.print("복사본 수정 후 원본: ");
        processor.printArray(original);
        System.out.print("복사본: ");
        processor.printArray(copied);
        
        System.out.println("\n=== 배열 역순 ===");
        int[] arr = {1, 2, 3, 4, 5};
        System.out.print("원본: ");
        processor.printArray(arr);
        
        processor.reverseArray(arr);
        System.out.print("역순: ");
        processor.printArray(arr);
    }
}

