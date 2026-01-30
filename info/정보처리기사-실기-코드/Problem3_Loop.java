package info.exam;

import java.util.ArrayList;
import java.util.List;

/**
 * 문제 3: 반복문과 조건문 활용
 */
public class Problem3_Loop {
    public static void main(String[] args) {
        // 1. 3과 5의 공배수
        List<Integer> commonMultiples = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                commonMultiples.add(i);
            }
        }
        System.out.println("3과 5의 공배수: " + commonMultiples);
        
        // 2. 소수 찾기
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= 100; i++) {
            if (isPrime(i)) {
                primes.add(i);
            }
        }
        System.out.println("소수: " + primes);
        
        // 3. 자릿수 합이 10 이상인 수
        List<Integer> digitSum10 = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            if (sumOfDigits(i) >= 10) {
                digitSum10.add(i);
            }
        }
        System.out.println("자릿수 합이 10 이상인 수: " + digitSum10);
    }
    
    // 소수 판별 메서드
    private static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        if (n == 2) {
            return true;
        }
        if (n % 2 == 0) {
            return false;
        }
        // 3부터 √n까지 홀수만 확인
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
    
    // 자릿수 합 계산 메서드
    private static int sumOfDigits(int n) {
        int sum = 0;
        while (n > 0) {
            sum += n % 10;  // 일의 자리 추출
            n /= 10;        // 일의 자리 제거
        }
        return sum;
    }
}
