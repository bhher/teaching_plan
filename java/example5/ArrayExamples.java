/**
 * 배열 활용 예제
 * 배열을 사용한 다양한 실전 예제를 보여줍니다
 */
public class ArrayExamples {
    public static void main(String[] args) {
        System.out.println("=== 예제 1: 최댓값과 최솟값 찾기 ===");
        int[] numbers = {10, 5, 8, 20, 3, 15};
        
        int max = numbers[0];
        int min = numbers[0];
        
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > max) {
                max = numbers[i];
            }
            if (numbers[i] < min) {
                min = numbers[i];
            }
        }
        
        System.out.println("배열: " + java.util.Arrays.toString(numbers));
        System.out.println("최댓값: " + max);
        System.out.println("최솟값: " + min);
        
        System.out.println("\n=== 예제 2: 합계와 평균 계산 ===");
        int[] scores = {85, 90, 78, 92, 88};
        
        int sum = 0;
        for (int score : scores) {
            sum += score;
        }
        
        double average = (double) sum / scores.length;
        System.out.println("점수: " + java.util.Arrays.toString(scores));
        System.out.println("합계: " + sum);
        System.out.println("평균: " + average);
        
        System.out.println("\n=== 예제 3: 배열 요소 검색 ===");
        int[] data = {10, 20, 30, 40, 50};
        int target = 30;
        boolean found = false;
        int index = -1;
        
        for (int i = 0; i < data.length; i++) {
            if (data[i] == target) {
                found = true;
                index = i;
                break;
            }
        }
        
        if (found) {
            System.out.println(target + "을(를) 인덱스 " + index + "에서 찾았습니다.");
        } else {
            System.out.println(target + "을(를) 찾을 수 없습니다.");
        }
        
        System.out.println("\n=== 예제 4: 배열 복사 ===");
        int[] original = {1, 2, 3, 4, 5};
        
        // 방법 1: 반복문 사용
        int[] copy1 = new int[original.length];
        for (int i = 0; i < original.length; i++) {
            copy1[i] = original[i];
        }
        
        // 방법 2: System.arraycopy()
        int[] copy2 = new int[original.length];
        System.arraycopy(original, 0, copy2, 0, original.length);
        
        // 방법 3: clone()
        int[] copy3 = original.clone();
        
        System.out.println("원본: " + java.util.Arrays.toString(original));
        System.out.println("복사본1: " + java.util.Arrays.toString(copy1));
        System.out.println("복사본2: " + java.util.Arrays.toString(copy2));
        System.out.println("복사본3: " + java.util.Arrays.toString(copy3));
        
        System.out.println("\n=== 예제 5: 배열 역순 ===");
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println("원본: " + java.util.Arrays.toString(arr));
        
        // 역순으로 출력 (배열은 변경하지 않음)
        System.out.print("역순 출력: ");
        for (int i = arr.length - 1; i >= 0; i--) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        
        // 배열 자체를 역순으로 변경
        for (int i = 0; i < arr.length / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;
        }
        System.out.println("역순 변경 후: " + java.util.Arrays.toString(arr));
        
        System.out.println("\n=== 예제 6: 배열 요소 개수 세기 ===");
        int[] values = {1, 2, 2, 3, 2, 4, 2, 5};
        int targetValue = 2;
        int count = 0;
        
        for (int value : values) {
            if (value == targetValue) {
                count++;
            }
        }
        System.out.println("배열: " + java.util.Arrays.toString(values));
        System.out.println(targetValue + "의 개수: " + count);
        
        System.out.println("\n=== 예제 7: 짝수/홀수 분리 ===");
        int[] numbers2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int evenCount = 0;
        int oddCount = 0;
        
        for (int num : numbers2) {
            if (num % 2 == 0) {
                evenCount++;
            } else {
                oddCount++;
            }
        }
        
        System.out.println("배열: " + java.util.Arrays.toString(numbers2));
        System.out.println("짝수 개수: " + evenCount);
        System.out.println("홀수 개수: " + oddCount);
        
        System.out.println("\n=== 예제 8: 배열 요소 합치기 ===");
        int[] arr1 = {1, 2, 3};
        int[] arr2 = {4, 5, 6};
        int[] merged = new int[arr1.length + arr2.length];
        
        for (int i = 0; i < arr1.length; i++) {
            merged[i] = arr1[i];
        }
        for (int i = 0; i < arr2.length; i++) {
            merged[arr1.length + i] = arr2[i];
        }
        
        System.out.println("배열1: " + java.util.Arrays.toString(arr1));
        System.out.println("배열2: " + java.util.Arrays.toString(arr2));
        System.out.println("합친 배열: " + java.util.Arrays.toString(merged));
    }
}

