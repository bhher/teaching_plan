/**
 * 메모리 관점에서의 오류를 설명하는 예제
 * NullPointerException, OutOfMemoryError 등을 메모리 관점에서 설명
 */
public class MemoryErrorExample {
    public static void main(String[] args) {
        System.out.println("=== 메모리 관점에서의 오류 이해 ===\n");
        
        // 예제 1: NullPointerException
        System.out.println("--- 예제 1: NullPointerException ---");
        try {
            String str = null;
            System.out.println("str = null");
            System.out.println("str.length() 호출 시도...");
            int length = str.length();  // NullPointerException 발생
        } catch (NullPointerException e) {
            System.out.println("NullPointerException 발생!");
            System.out.println("원인: null 참조로 객체에 접근");
            System.out.println("\n메모리 상태:");
            System.out.println("Stack          Heap");
            System.out.println("┌─────┐       ┌─────┐");
            System.out.println("│ str │ ───→  │null │  ← 참조가 없음");
            System.out.println("└─────┘       └─────┘");
        }
        
        // 예제 2: ArrayIndexOutOfBoundsException
        System.out.println("\n--- 예제 2: ArrayIndexOutOfBoundsException ---");
        try {
            int[] numbers = new int[5];
            System.out.println("배열 크기: 5 (인덱스 0~4)");
            System.out.println("numbers[10] 접근 시도...");
            int value = numbers[10];  // ArrayIndexOutOfBoundsException 발생
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ArrayIndexOutOfBoundsException 발생!");
            System.out.println("원인: 배열 인덱스가 범위를 벗어남");
            System.out.println("\n메모리 상태:");
            System.out.println("Stack          Heap");
            System.out.println("┌─────────┐   ┌──────────────────┐");
            System.out.println("│ numbers │→  │ int[5] 배열      │");
            System.out.println("└─────────┘   │ [0][1][2][3][4]  │");
            System.out.println("              └──────────────────┘");
            System.out.println("                      ↑");
            System.out.println("              인덱스 10은 존재하지 않음");
        }
        
        // 예제 3: StackOverflowError (주의: 실제로 발생시키지 않음)
        System.out.println("\n--- 예제 3: StackOverflowError ---");
        System.out.println("원인: 스택이 가득 참 (주로 무한 재귀)");
        System.out.println("\n메모리 상태:");
        System.out.println("Stack (스택이 가득 참)");
        System.out.println("┌─────────────┐");
        System.out.println("│ recursive()  │ ← 스택 프레임");
        System.out.println("├─────────────┤");
        System.out.println("│ recursive()  │ ← 스택 프레임");
        System.out.println("├─────────────┤");
        System.out.println("│ recursive()  │ ← 스택 프레임");
        System.out.println("├─────────────┤");
        System.out.println("│ ...          │ ← 스택 오버플로우!");
        System.out.println("└─────────────┘");
        System.out.println("\n주의: 실제로 무한 재귀를 실행하면 StackOverflowError 발생");
        
        // 예제 4: OutOfMemoryError (주의: 실제로 발생시키지 않음)
        System.out.println("\n--- 예제 4: OutOfMemoryError ---");
        Runtime runtime = Runtime.getRuntime();
        System.out.println("현재 힙 메모리 상태:");
        System.out.println("전체 메모리: " + runtime.totalMemory() / 1024 / 1024 + " MB");
        System.out.println("최대 메모리: " + runtime.maxMemory() / 1024 / 1024 + " MB");
        System.out.println("사용 가능: " + runtime.freeMemory() / 1024 / 1024 + " MB");
        
        System.out.println("\n원인: 힙 메모리가 부족");
        System.out.println("해결 방법:");
        System.out.println("1. JVM 옵션으로 힙 크기 증가: -Xmx2g");
        System.out.println("2. 불필요한 객체 제거");
        System.out.println("3. 메모리 누수 확인");
        
        System.out.println("\n주의: 실제로 OutOfMemoryError를 발생시키려면:");
        System.out.println("List<String> list = new ArrayList<>();");
        System.out.println("while (true) {");
        System.out.println("    list.add(new String(\"Very long string...\"));");
        System.out.println("}");
        
        // 예제 5: 메모리 누수 시뮬레이션
        System.out.println("\n--- 예제 5: 메모리 누수 방지 ---");
        System.out.println("나쁜 예: 참조를 유지하여 GC 불가");
        System.out.println("List<String> list = new ArrayList<>();");
        System.out.println("// 계속 객체 추가만 하고 제거하지 않음");
        
        System.out.println("\n좋은 예: 사용 후 참조 해제");
        System.out.println("List<String> list = new ArrayList<>();");
        System.out.println("// 사용 후");
        System.out.println("list.clear();  // 또는 list = null;");
        
        System.out.println("\n=== 메모리 오류 요약 ===");
        System.out.println("1. NullPointerException:");
        System.out.println("   - null 참조로 객체 접근");
        System.out.println("   - 해결: null 체크");
        
        System.out.println("\n2. ArrayIndexOutOfBoundsException:");
        System.out.println("   - 배열 인덱스 범위 초과");
        System.out.println("   - 해결: 인덱스 범위 확인");
        
        System.out.println("\n3. StackOverflowError:");
        System.out.println("   - 스택이 가득 참 (무한 재귀)");
        System.out.println("   - 해결: 재귀 종료 조건 확인");
        
        System.out.println("\n4. OutOfMemoryError:");
        System.out.println("   - 힙 메모리 부족");
        System.out.println("   - 해결: 메모리 관리, 힙 크기 증가");
    }
}

