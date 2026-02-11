package a0331.sort;

import java.util.Arrays;

/**
 * 버블 정렬 (Bubble Sort) 구현
 * 인접한 두 요소를 비교하여 큰 값을 오른쪽으로 이동시키는 방식
 */
public class BubbleSort {
    
    /**
     * 버블 정렬 - 오름차순
     * @param arr 정렬할 배열
     */
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        
        // 외부 루프: n-1번 반복 (마지막 요소는 자동으로 정렬됨)
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false; // 최적화: 교환이 일어났는지 확인
            
            // 내부 루프: 인접한 요소들을 비교
            // i번째 순회 후에는 뒤에서 i개의 요소가 이미 정렬됨
            for (int j = 0; j < n - 1 - i; j++) {
                // 인접한 두 요소 비교
                if (arr[j] > arr[j + 1]) {
                    // 교환
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            
            // 교환이 일어나지 않았다면 이미 정렬된 상태
            if (!swapped) {
                break; // 조기 종료 (최적화)
            }
        }
    }
    
    /**
     * 버블 정렬 - 내림차순
     */
    public static void bubbleSortDescending(int[] arr) {
        int n = arr.length;
        
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] < arr[j + 1]) { // 부등호 방향만 변경
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            
            if (!swapped) {
                break;
            }
        }
    }
    
    /**
     * 정렬 과정을 출력하는 버블 정렬
     */
    public static void bubbleSortWithSteps(int[] arr) {
        int n = arr.length;
        System.out.println("정렬 시작: " + Arrays.toString(arr));
        
        for (int i = 0; i < n - 1; i++) {
            System.out.println("\n" + (i + 1) + "단계:");
            boolean swapped = false;
            
            for (int j = 0; j < n - 1 - i; j++) {
                System.out.print("  비교: arr[" + j + "]=" + arr[j] + 
                                 " vs arr[" + (j+1) + "]=" + arr[j+1]);
                
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                    System.out.println(" → 교환!");
                } else {
                    System.out.println(" → 유지");
                }
            }
            
            System.out.println("  결과: " + Arrays.toString(arr));
            
            if (!swapped) {
                System.out.println("  조기 종료 (이미 정렬됨)");
                break;
            }
        }
        
        System.out.println("\n최종 결과: " + Arrays.toString(arr));
    }
    
    // 테스트 코드
    public static void main(String[] args) {
        int[] arr1 = {5, 2, 8, 1, 9};
        System.out.println("=== 버블 정렬 테스트 ===");
        System.out.println("정렬 전: " + Arrays.toString(arr1));
        bubbleSort(arr1);
        System.out.println("정렬 후: " + Arrays.toString(arr1));
        
        System.out.println("\n=== 정렬 과정 출력 ===");
        int[] arr2 = {5, 2, 8, 1, 9};
        bubbleSortWithSteps(arr2);
        
        System.out.println("\n=== 내림차순 정렬 ===");
        int[] arr3 = {5, 2, 8, 1, 9};
        System.out.println("정렬 전: " + Arrays.toString(arr3));
        bubbleSortDescending(arr3);
        System.out.println("정렬 후: " + Arrays.toString(arr3));
    }
}
