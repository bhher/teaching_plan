package a0331.sort;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 세 가지 정렬 알고리즘 통합 데모 프로그램
 * 버블 정렬, 삽입 정렬, 선택 정렬을 비교
 */
public class SortingAlgorithmsDemo {
    
    // 버블 정렬
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }
    
    // 삽입 정렬
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int current = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > current) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = current;
        }
    }
    
    // 선택 정렬
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
    }
    
    // 배열 복사 (원본 보존)
    public static int[] copyArray(int[] arr) {
        return Arrays.copyOf(arr, arr.length);
    }
    
    // 성능 측정
    public static long measureTime(Runnable task) {
        long startTime = System.nanoTime();
        task.run();
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
    
    // 메인 메서드
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("=== 정렬 알고리즘 비교 프로그램 ===\n");
        System.out.print("배열 크기 입력: ");
        int n = sc.nextInt();
        
        int[] original = new int[n];
        System.out.println("배열 요소 입력:");
        for (int i = 0; i < n; i++) {
            original[i] = sc.nextInt();
        }
        
        System.out.println("\n원본 배열: " + Arrays.toString(original));
        System.out.println("\n=== 정렬 결과 ===\n");
        
        // 버블 정렬
        int[] arr1 = copyArray(original);
        long time1 = measureTime(() -> bubbleSort(arr1));
        System.out.println("버블 정렬:   " + Arrays.toString(arr1) + 
                          " (시간: " + time1 + " ns)");
        
        // 삽입 정렬
        int[] arr2 = copyArray(original);
        long time2 = measureTime(() -> insertionSort(arr2));
        System.out.println("삽입 정렬:   " + Arrays.toString(arr2) + 
                          " (시간: " + time2 + " ns)");
        
        // 선택 정렬
        int[] arr3 = copyArray(original);
        long time3 = measureTime(() -> selectionSort(arr3));
        System.out.println("선택 정렬:   " + Arrays.toString(arr3) + 
                          " (시간: " + time3 + " ns)");
        
        // 결과 확인
        boolean allSame = Arrays.equals(arr1, arr2) && Arrays.equals(arr2, arr3);
        System.out.println("\n모든 알고리즘이 동일한 결과를 생성: " + allSame);
        
        sc.close();
    }
}
