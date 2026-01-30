#include <stdio.h>

// 소수 판별 함수 (bool 대신 int 사용)
int isPrime(int n) {
    if (n < 2) return 0;
    if (n == 2) return 1;
    if (n % 2 == 0) return 0;
    
    // 3부터 √n까지 홀수만 확인
    for (int i = 3; i * i <= n; i += 2) {
        if (n % i == 0) {
            return 0;
        }
    }
    return 1;
}

// 자릿수 합 계산 함수
int sumOfDigits(int n) {
    int sum = 0;
    while (n > 0) {
        sum += n % 10;  // 일의 자리 추출
        n /= 10;        // 일의 자리 제거
    }
    return sum;
}

void printArray(int arr[], int count) {
    printf("[");
    for (int i = 0; i < count; i++) {
        printf("%d", arr[i]);
        if (i < count - 1) printf(", ");
    }
    printf("]\n");
}

int main() {
    int commonMultiples[100];
    int cmCount = 0;
    
    // 1. 3과 5의 공배수
    for (int i = 1; i <= 100; i++) {
        if (i % 3 == 0 && i % 5 == 0) {
            commonMultiples[cmCount++] = i;
        }
    }
    printf("3과 5의 공배수: ");
    printArray(commonMultiples, cmCount);
    
    // 2. 소수 찾기
    int primes[100];
    int primeCount = 0;
    for (int i = 2; i <= 100; i++) {
        if (isPrime(i)) {
            primes[primeCount++] = i;
        }
    }
    printf("소수: ");
    printArray(primes, primeCount);
    
    // 3. 자릿수 합이 10 이상인 수
    int digitSum10[100];
    int dsCount = 0;
    for (int i = 1; i <= 100; i++) {
        if (sumOfDigits(i) >= 10) {
            digitSum10[dsCount++] = i;
        }
    }
    printf("자릿수 합이 10 이상인 수: ");
    printArray(digitSum10, dsCount);
    
    return 0;
}
