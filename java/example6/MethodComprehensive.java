/**
 * 메서드 종합 예제
 * 다양한 메서드 사용법을 종합적으로 보여줍니다
 */
public class MethodComprehensive {
    // 학생 점수 처리 클래스
    public static class StudentScore {
        // 점수 배열에서 평균 계산
        public static double calculateAverage(int[] scores) {
            if (scores.length == 0) {
                return 0.0;
            }
            int sum = 0;
            for (int score : scores) {
                sum += score;
            }
            return (double) sum / scores.length;
        }
        
        // 점수 배열에서 최댓값 찾기
        public static int findMax(int[] scores) {
            if (scores.length == 0) {
                return 0;
            }
            int max = scores[0];
            for (int score : scores) {
                if (score > max) {
                    max = score;
                }
            }
            return max;
        }
        
        // 점수 배열에서 최솟값 찾기
        public static int findMin(int[] scores) {
            if (scores.length == 0) {
                return 0;
            }
            int min = scores[0];
            for (int score : scores) {
                if (score < min) {
                    min = score;
                }
            }
            return min;
        }
        
        // 등급 판정
        public static String getGrade(int score) {
            if (score >= 90) return "A";
            else if (score >= 80) return "B";
            else if (score >= 70) return "C";
            else if (score >= 60) return "D";
            else return "F";
        }
        
        // 합격 여부 판정
        public static boolean isPass(int score) {
            return score >= 60;
        }
    }
    
    // 문자열 처리 유틸리티
    public static class StringUtils {
        // 문자열 역순
        public static String reverse(String str) {
            if (str == null || str.isEmpty()) {
                return str;
            }
            StringBuilder sb = new StringBuilder(str);
            return sb.reverse().toString();
        }
        
        // 회문(palindrome) 확인
        public static boolean isPalindrome(String str) {
            if (str == null) {
                return false;
            }
            String reversed = reverse(str.toLowerCase());
            return str.toLowerCase().equals(reversed);
        }
        
        // 단어 개수 세기
        public static int countWords(String sentence) {
            if (sentence == null || sentence.trim().isEmpty()) {
                return 0;
            }
            String[] words = sentence.trim().split("\\s+");
            return words.length;
        }
        
        // 문자열이 비어있는지 확인
        public static boolean isEmpty(String str) {
            return str == null || str.length() == 0;
        }
    }
    
    // 수학 유틸리티
    public static class MathUtils {
        // 팩토리얼
        public static long factorial(int n) {
            if (n <= 1) {
                return 1;
            }
            return n * factorial(n - 1);
        }
        
        // 소수 판별
        public static boolean isPrime(int number) {
            if (number < 2) {
                return false;
            }
            for (int i = 2; i < number; i++) {
                if (number % i == 0) {
                    return false;
                }
            }
            return true;
        }
        
        // 최대공약수 (GCD)
        public static int gcd(int a, int b) {
            while (b != 0) {
                int temp = b;
                b = a % b;
                a = temp;
            }
            return a;
        }
        
        // 최소공배수 (LCM)
        public static int lcm(int a, int b) {
            return (a * b) / gcd(a, b);
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== 메서드 종합 예제 ===\n");
        
        // 학생 점수 처리
        System.out.println("--- 학생 점수 처리 ---");
        int[] scores = {85, 90, 78, 92, 88};
        
        System.out.print("점수: ");
        for (int score : scores) {
            System.out.print(score + " ");
        }
        System.out.println();
        
        System.out.println("평균: " + StudentScore.calculateAverage(scores));
        System.out.println("최댓값: " + StudentScore.findMax(scores));
        System.out.println("최솟값: " + StudentScore.findMin(scores));
        
        System.out.println("\n개별 등급:");
        for (int score : scores) {
            System.out.println(score + "점 → " + StudentScore.getGrade(score) + "등급");
        }
        
        // 문자열 처리
        System.out.println("\n--- 문자열 처리 ---");
        String text = "Hello World";
        System.out.println("원본: " + text);
        System.out.println("역순: " + StringUtils.reverse(text));
        System.out.println("단어 개수: " + StringUtils.countWords(text));
        
        String palindrome = "level";
        System.out.println("\"" + palindrome + "\"는 회문인가? " + StringUtils.isPalindrome(palindrome));
        
        // 수학 유틸리티
        System.out.println("\n--- 수학 유틸리티 ---");
        System.out.println("5! = " + MathUtils.factorial(5));
        
        int[] testNumbers = {2, 3, 4, 17, 20};
        System.out.println("\n소수 판별:");
        for (int num : testNumbers) {
            System.out.println(num + "은(는) 소수인가? " + MathUtils.isPrime(num));
        }
        
        int a = 12, b = 18;
        System.out.println("\n최대공약수(" + a + ", " + b + "): " + MathUtils.gcd(a, b));
        System.out.println("최소공배수(" + a + ", " + b + "): " + MathUtils.lcm(a, b));
        
        System.out.println("\n=== 메서드의 장점 ===");
        System.out.println("1. 코드 재사용");
        System.out.println("2. 모듈화");
        System.out.println("3. 가독성 향상");
        System.out.println("4. 유지보수 용이");
    }
}

