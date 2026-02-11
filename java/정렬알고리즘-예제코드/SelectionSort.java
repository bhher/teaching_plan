package a0331.sort;

import java.util.Arrays;

/**
 * 선택 정렬 (Selection Sort) 구현
 * 배열에서 가장 작은(또는 큰) 요소를 찾아서 맨 앞(또는 맨 뒤)으로 이동
 */
public class SelectionSort {
    
    /**
     * 선택 정렬 - 오름차순
     * @param arr 정렬할 배열
     */
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        
        // 외부 루프: n-1번 반복 (마지막 요소는 자동으로 정렬됨)
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i; // 최소값의 인덱스
            
            // 내부 루프: i+1부터 끝까지 최소값 찾기
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j; // 더 작은 값을 찾으면 인덱스 업데이트
                }
            }
            
            // 최소값을 현재 위치와 교환
            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
    }
    
    /**
     * 선택 정렬 - 내림차순
     */
    public static void selectionSortDescending(int[] arr) {
        int n = arr.length;
        
        for (int i = 0; i < n - 1; i++) {
            int maxIndex = i; // 최대값의 인덱스
            
            for (int j = i + 1; j < n; j++) {
                if (arr[j] > arr[maxIndex]) { // 부등호 방향 변경
                    maxIndex = j;
                }
            }
            
            if (maxIndex != i) {
                int temp = arr[i];
                arr[i] = arr[maxIndex];
                arr[maxIndex] = temp;
            }
        }
    }
    
    /**
     * 정렬 과정을 출력하는 선택 정렬
     */
    public static void selectionSortWithSteps(int[] arr) {
        int n = arr.length;
        System.out.println("정렬 시작: " + Arrays.toString(arr));
        
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            
            System.out.println("\n" + (i + 1) + "단계: 인덱스 " + i + "부터 최소값 찾기");
            
            // 최소값 찾기
            for (int j = i + 1; j < n; j++) {
                System.out.print("  비교: arr[" + minIndex + "]=" + arr[minIndex] + 
                               " vs arr[" + j + "]=" + arr[j]);
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                    System.out.println(" → 새로운 최소값!");
                } else {
                    System.out.println(" → 유지");
                }
            }
            
            System.out.println("  최소값: " + arr[minIndex] + " (인덱스 " + minIndex + ")");
            
            // 교환
            if (minIndex != i) {
                System.out.println("  교환: arr[" + i + "]=" + arr[i] + 
                                 " ↔ arr[" + minIndex + "]=" + arr[minIndex]);
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            } else {
                System.out.println("  이미 올바른 위치에 있음 (교환 불필요)");
            }
            
            System.out.println("  결과: " + Arrays.toString(arr));
        }
        
        System.out.println("\n최종 결과: " + Arrays.toString(arr));
    }
    
    // 테스트 코드
    public static void main(String[] args) {
        int[] arr1 = {5, 2, 8, 1, 9};
        System.out.println("=== 선택 정렬 테스트 ===");
        System.out.println("정렬 전: " + Arrays.toString(arr1));
        selectionSort(arr1);
        System.out.println("정렬 후: " + Arrays.toString(arr1));
        
        System.out.println("\n=== 정렬 과정 출력 ===");
        int[] arr2 = {5, 2, 8, 1, 9};
        selectionSortWithSteps(arr2);
        
        System.out.println("\n=== 내림차순 정렬 ===");
        int[] arr3 = {5, 2, 8, 1, 9};
        System.out.println("정렬 전: " + Arrays.toString(arr3));
        selectionSortDescending(arr3);
        System.out.println("정렬 후: " + Arrays.toString(arr3));
    }
}
