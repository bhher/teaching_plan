package a0331.sort;

import java.util.Arrays;

/**
 * 삽입 정렬 (Insertion Sort) 구현
 * 배열을 정렬된 부분과 정렬되지 않은 부분으로 나누고,
 * 정렬되지 않은 부분의 요소를 정렬된 부분의 적절한 위치에 삽입
 */
public class InsertionSort {
    
    /**
     * 삽입 정렬 - 오름차순
     * @param arr 정렬할 배열
     */
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        
        // 두 번째 요소부터 시작 (첫 번째는 이미 정렬된 것으로 간주)
        for (int i = 1; i < n; i++) {
            int current = arr[i]; // 현재 삽입할 요소
            int j = i - 1;       // 정렬된 부분의 마지막 인덱스
            
            // 정렬된 부분을 역순으로 순회하며 삽입 위치 찾기
            // current보다 큰 요소들을 오른쪽으로 이동
            while (j >= 0 && arr[j] > current) {
                arr[j + 1] = arr[j]; // 오른쪽으로 이동
                j--;                  // 왼쪽으로 이동
            }
            
            // 적절한 위치에 current 삽입
            arr[j + 1] = current;
        }
    }
    
    /**
     * 삽입 정렬 - 내림차순
     */
    public static void insertionSortDescending(int[] arr) {
        int n = arr.length;
        
        for (int i = 1; i < n; i++) {
            int current = arr[i];
            int j = i - 1;
            
            while (j >= 0 && arr[j] < current) { // 부등호 방향 변경
                arr[j + 1] = arr[j];
                j--;
            }
            
            arr[j + 1] = current;
        }
    }
    
    /**
     * 정렬 과정을 출력하는 삽입 정렬
     */
    public static void insertionSortWithSteps(int[] arr) {
        int n = arr.length;
        System.out.println("정렬 시작: " + Arrays.toString(arr));
        
        for (int i = 1; i < n; i++) {
            int current = arr[i];
            int j = i - 1;
            
            System.out.println("\n" + i + "단계: 요소 " + current + " 삽입");
            System.out.println("  정렬된 부분: " + Arrays.toString(Arrays.copyOf(arr, i)));
            System.out.println("  현재 요소: " + current);
            
            while (j >= 0 && arr[j] > current) {
                System.out.println("    비교: " + arr[j] + " > " + current + " → 이동");
                arr[j + 1] = arr[j];
                j--;
            }
            
            arr[j + 1] = current;
            System.out.println("  삽입 위치: 인덱스 " + (j + 1));
            System.out.println("  결과: " + Arrays.toString(arr));
        }
        
        System.out.println("\n최종 결과: " + Arrays.toString(arr));
    }
    
    // 테스트 코드
    public static void main(String[] args) {
        int[] arr1 = {5, 2, 8, 1, 9};
        System.out.println("=== 삽입 정렬 테스트 ===");
        System.out.println("정렬 전: " + Arrays.toString(arr1));
        insertionSort(arr1);
        System.out.println("정렬 후: " + Arrays.toString(arr1));
        
        System.out.println("\n=== 정렬 과정 출력 ===");
        int[] arr2 = {5, 2, 8, 1, 9};
        insertionSortWithSteps(arr2);
        
        System.out.println("\n=== 내림차순 정렬 ===");
        int[] arr3 = {5, 2, 8, 1, 9};
        System.out.println("정렬 전: " + Arrays.toString(arr3));
        insertionSortDescending(arr3);
        System.out.println("정렬 후: " + Arrays.toString(arr3));
    }
}
