package info.exam;

import java.util.Arrays;

/**
 * 문제 1: 배열 처리 알고리즘
 */
public class Problem1_Array {
    public static void main(String[] args) {
        int[] arr = {3, 7, 2, 9, 1, 5, 8, 4, 6};
        
        // 1. 최댓값과 최솟값 찾기
        int max = arr[0];
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        System.out.println("최댓값: " + max);
        System.out.println("최솟값: " + min);
        
        // 2. 평균값 계산
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        double average = (double) sum / arr.length;
        System.out.printf("평균값: %.2f\n", average);
        
        // 3. 짝수만 필터링
        int evenCount = 0;
        for (int num : arr) {
            if (num % 2 == 0) {
                evenCount++;
            }
        }
        int[] evenArr = new int[evenCount];
        int index = 0;
        for (int num : arr) {
            if (num % 2 == 0) {
                evenArr[index++] = num;
            }
        }
        System.out.println("짝수 배열: " + Arrays.toString(evenArr));
        
        // 4. 오름차순 정렬
        int[] sortedArr = arr.clone();
        Arrays.sort(sortedArr);
        System.out.println("정렬된 배열: " + Arrays.toString(sortedArr));
    }
}
