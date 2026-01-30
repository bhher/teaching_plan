#include <stdio.h>
#include <stdlib.h>

void printArray(int arr[], int n) {
    printf("[");
    for (int i = 0; i < n; i++) {
        printf("%d", arr[i]);
        if (i < n - 1) {
            printf(", ");
        }
    }
    printf("]\n");
}

int main() {
    int arr[] = {3, 7, 2, 9, 1, 5, 8, 4, 6};
    int n = sizeof(arr) / sizeof(arr[0]);
    
    // 1. 최댓값과 최솟값 찾기
    int max = arr[0];
    int min = arr[0];
    for (int i = 1; i < n; i++) {
        if (arr[i] > max) {
            max = arr[i];
        }
        if (arr[i] < min) {
            min = arr[i];
        }
    }
    printf("최댓값: %d\n", max);
    printf("최솟값: %d\n", min);
    
    // 2. 평균값 계산
    int sum = 0;
    for (int i = 0; i < n; i++) {
        sum += arr[i];
    }
    double average = (double)sum / n;
    printf("평균값: %.2f\n", average);
    
    // 3. 짝수만 필터링
    int evenCount = 0;
    for (int i = 0; i < n; i++) {
        if (arr[i] % 2 == 0) {
            evenCount++;
        }
    }
    int* evenArr = (int*)malloc(evenCount * sizeof(int));
    int index = 0;
    for (int i = 0; i < n; i++) {
        if (arr[i] % 2 == 0) {
            evenArr[index++] = arr[i];
        }
    }
    printf("짝수 배열: ");
    printArray(evenArr, evenCount);
    free(evenArr);
    
    // 4. 오름차순 정렬 (버블 정렬)
    int* sortedArr = (int*)malloc(n * sizeof(int));
    for (int i = 0; i < n; i++) {
        sortedArr[i] = arr[i];
    }
    for (int i = 0; i < n - 1; i++) {
        for (int j = 0; j < n - 1 - i; j++) {
            if (sortedArr[j] > sortedArr[j + 1]) {
                int temp = sortedArr[j];
                sortedArr[j] = sortedArr[j + 1];
                sortedArr[j + 1] = temp;
            }
        }
    }
    printf("정렬된 배열: ");
    printArray(sortedArr, n);
    free(sortedArr);
    
    return 0;
}
