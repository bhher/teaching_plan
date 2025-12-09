/**
 * 1차원 배열을 설명하는 예제
 * 배열 선언, 초기화, 접근 방법을 보여줍니다
 */
public class OneDimensionalArray {
    public static void main(String[] args) {
        System.out.println("=== 배열 선언과 초기화 ===");
        
        // 방법 1: 선언 후 초기화
        int[] numbers1;
        numbers1 = new int[5];
        numbers1[0] = 10;
        numbers1[1] = 20;
        numbers1[2] = 30;
        numbers1[3] = 40;
        numbers1[4] = 50;
        
        // 방법 2: 선언과 동시에 초기화
        int[] numbers2 = {10, 20, 30, 40, 50};
        
        // 방법 3: new 연산자와 함께
        int[] numbers3 = new int[]{10, 20, 30, 40, 50};
        
        System.out.println("\n=== 배열 접근 ===");
        System.out.println("numbers2[0] = " + numbers2[0]);  // 10
        System.out.println("numbers2[1] = " + numbers2[1]);  // 20
        System.out.println("numbers2[4] = " + numbers2[4]);  // 50
        
        System.out.println("\n=== 배열 길이 ===");
        System.out.println("배열 길이: " + numbers2.length);  // 5
        System.out.println("마지막 요소: " + numbers2[numbers2.length - 1]);
        
        System.out.println("\n=== 배열의 기본값 ===");
        int[] defaultArray = new int[5];
        System.out.println("int 배열 기본값:");
        for (int i = 0; i < defaultArray.length; i++) {
            System.out.println("defaultArray[" + i + "] = " + defaultArray[i]);
        }
        
        boolean[] boolArray = new boolean[3];
        System.out.println("\nboolean 배열 기본값:");
        for (int i = 0; i < boolArray.length; i++) {
            System.out.println("boolArray[" + i + "] = " + boolArray[i]);
        }
        
        System.out.println("\n=== 배열과 반복문 (일반 for) ===");
        int[] numbers = {10, 20, 30, 40, 50};
        for (int i = 0; i < numbers.length; i++) {
            System.out.println("numbers[" + i + "] = " + numbers[i]);
        }
        
        System.out.println("\n=== 향상된 for 문 (for-each) ===");
        for (int num : numbers) {
            System.out.print(num + " ");
        }
        System.out.println();
        
        System.out.println("\n=== String 배열 ===");
        String[] names = {"홍길동", "김철수", "이영희"};
        for (String name : names) {
            System.out.println(name);
        }
        
        System.out.println("\n=== 배열 요소 수정 ===");
        int[] values = {1, 2, 3, 4, 5};
        System.out.println("수정 전:");
        for (int val : values) {
            System.out.print(val + " ");
        }
        
        values[2] = 100;  // 세 번째 요소 수정
        System.out.println("\n수정 후:");
        for (int val : values) {
            System.out.print(val + " ");
        }
        System.out.println();
        
        System.out.println("\n=== 배열 역순 출력 ===");
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println("역순:");
        for (int i = arr.length - 1; i >= 0; i--) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}

