/**
 * 2차원 배열을 설명하는 예제
 * 2차원 배열 선언, 초기화, 접근 방법을 보여줍니다
 */
public class TwoDimensionalArray {
    public static void main(String[] args) {
        System.out.println("=== 2차원 배열 선언과 초기화 ===");
        
        // 방법 1: 선언 후 초기화
        int[][] matrix1;
        matrix1 = new int[3][4];  // 3행 4열
        matrix1[0][0] = 1;
        matrix1[0][1] = 2;
        matrix1[1][0] = 5;
        
        // 방법 2: 선언과 동시에 초기화
        int[][] matrix2 = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12}
        };
        
        System.out.println("\n=== 2차원 배열 접근 ===");
        System.out.println("matrix2[0][0] = " + matrix2[0][0]);  // 1
        System.out.println("matrix2[1][2] = " + matrix2[1][2]);    // 7
        System.out.println("matrix2[2][3] = " + matrix2[2][3]);    // 12
        
        System.out.println("\n=== 배열 크기 ===");
        System.out.println("행의 개수: " + matrix2.length);           // 3
        System.out.println("첫 번째 행의 열 개수: " + matrix2[0].length);  // 4
        
        System.out.println("\n=== 2차원 배열 출력 (이중 for) ===");
        for (int i = 0; i < matrix2.length; i++) {
            for (int j = 0; j < matrix2[i].length; j++) {
                System.out.print(matrix2[i][j] + "\t");
            }
            System.out.println();
        }
        
        System.out.println("\n=== 향상된 for 문으로 출력 ===");
        for (int[] row : matrix2) {
            for (int num : row) {
                System.out.print(num + "\t");
            }
            System.out.println();
        }
        
        System.out.println("\n=== 가변 배열 (Jagged Array) ===");
        int[][] jagged = {
            {1, 2},
            {3, 4, 5, 6},
            {7, 8, 9}
        };
        
        System.out.println("가변 배열 출력:");
        for (int i = 0; i < jagged.length; i++) {
            for (int j = 0; j < jagged[i].length; j++) {
                System.out.print(jagged[i][j] + " ");
            }
            System.out.println();
        }
        
        System.out.println("\n=== 2차원 배열 합계 계산 ===");
        int[][] numbers = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        
        int totalSum = 0;
        for (int[] row : numbers) {
            for (int num : row) {
                totalSum += num;
            }
        }
        System.out.println("전체 합계: " + totalSum);
        
        // 각 행의 합계
        System.out.println("\n각 행의 합계:");
        for (int i = 0; i < numbers.length; i++) {
            int rowSum = 0;
            for (int j = 0; j < numbers[i].length; j++) {
                rowSum += numbers[i][j];
            }
            System.out.println((i + 1) + "행 합계: " + rowSum);
        }
        
        System.out.println("\n=== 2차원 배열 최댓값/최솟값 ===");
        int max = numbers[0][0];
        int min = numbers[0][0];
        
        for (int[] row : numbers) {
            for (int num : row) {
                if (num > max) max = num;
                if (num < min) min = num;
            }
        }
        System.out.println("최댓값: " + max);
        System.out.println("최솟값: " + min);
        
        System.out.println("\n=== 3x3 행렬 생성 ===");
        int[][] matrix3x3 = new int[3][3];
        int value = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrix3x3[i][j] = value++;
            }
        }
        
        System.out.println("3x3 행렬:");
        for (int[] row : matrix3x3) {
            for (int num : row) {
                System.out.print(num + "\t");
            }
            System.out.println();
        }
    }
}

